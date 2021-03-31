import random
import pandas as pd
import numpy as np
from sklearn.metrics.pairwise import cosine_similarity
from flask import Blueprint, make_response, jsonify
from flask_restx import Api, Resource

from models.Book import Book
from models.UserBook import UserBook
from models.UserGenre import UserGenre
from models.Genre import Genre
from models.Wishlist import Wishlist


recommendation = Blueprint("recommendation", __name__)
api = Api(recommendation)

'''
    추천에 필요한 데이터 + 함수들
    - books                 : 책 데이터(ISBN, 소개, 장르 등)
    - book_embedding_list   : 임베딩 리스트
'''
# 전처리된 책 데이터 불러오기
books = pd.read_pickle("data/book_data.pkl")

# 책 데이터 임베딩 처리한 데이터 불러오기
book_embedding_list = np.load("data/embedding_matrix.npy")


def calculate_score(similarity):
    ''' 유사도를 점수로 반환
    - paramter
        - similarity : 두 책간의 유사도 (0.0 ~ 1.0)

    - return
        - 환산 점수
    '''
    score = 1
    if similarity >= 0.9:
        score = 2
    elif similarity >= 0.8:
        score = 1.5
    elif similarity >= 0.7:
        score = 1
    elif similarity >= 0.6:
        score = 0.8
    else:
        score = 0.5

    return score * similarity


def recommend(isbn_list, genre_ids=None):
    ''' 책 리스트와 장르를 이용해 추천
    - paramter
        - isbn_list : 책 isbn 리스트
        - genre_ids : 장르 목록

    - return
        - list : 추천 책 20개
    '''
    indices = pd.Series(
        books.index, index=books['book_isbn']).drop_duplicates()
    # 전달받은 책들의 인덱스 저장
    book_indices = [indices[isbn] for isbn in isbn_list]

    sim_scores = [[i, 0] for i in range(len(book_embedding_list))]

    for idx in book_indices:
        # 해당 책과 다른 책들의 cosine similarity 계산
        cos_similarity = cosine_similarity(
            [book_embedding_list[idx]], book_embedding_list)

        # 해당 책과 다른 책들의 cosin similarity를 점수로 환산해 기존 값에 더해줌
        for i in range(len(sim_scores)):
            if i != idx:
                sim_scores[i][1] += calculate_score(cos_similarity[0][i])

    # 유저가 선호하는 장르내에서만 추천하는 경우 0점 처리
    if genre_ids is not None and len(genre_ids) > 0:
        like_list = set()
        result = []
        for genre_id in genre_ids:
            like_list.update(books[books['book_genres'].str.contains(
                r'\b{}\b'.format(str(genre_id)))].index)

        for idx in like_list:
            result.append([idx, sim_scores[idx][1]])

        sim_scores = result

    # 점수를 기준으로 내림차순 정렬
    sim_scores = sorted(sim_scores, key=lambda x: x[1], reverse=True)

    # 점수가 가장 높은 책 20권 추천
    sim_scores = sim_scores[1:20]

    # 가장 유사한 책 20권의 인덱스
    book_indices = [i[0] for i in sim_scores]

    # 전체 데이터프레임에서 해당 인덱스의 isbn만 추출.
    recommend = books.iloc[book_indices]['book_isbn'].tolist()

    results = Book.query.filter(Book.book_isbn.in_(recommend)).all()

    return [result.as_simple_dict(idx + 1) for idx, result in enumerate(results)]


def select_book_only_genres(genre_ids):
    ''' 선호 장르만 이용해 책 추천
    - paramter
        - genre_ids : 유저 선호 장르 목록

    - return
        - list : 선호 장르를 기준으로 뽑아낸 랜덤한 20개 책
    '''
    books_by_genre = pd.DataFrame()
    for genre in genre_ids:
        current_books = books[books['book_genres'].str.contains(
            r'\b{}\b'.format(genre))]
        books_by_genre = pd.concat([books_by_genre, current_books])

    recommend = books_by_genre['book_isbn'].drop_duplicates() \
        .sample(n=20).tolist()

    results = Book.query.filter(Book.book_isbn.in_(recommend)).all()

    return [result.as_simple_dict(idx + 1) for idx, result in enumerate(results)]


def find_customized_genre(read_books, user_genres):
    ''' 유저 맞춤 장르를 구하는 함수
    - paramter
        - read_books  : 유저가 읽은 책 목록
        - user_genres : 유저 선호 장르 목록 

    - return
        - genre_id      : 유저 맞춤 장르 id
        - genre_name    : 유저 맞춤 장르 이름
    '''
    genres = {genre.genre_id: genre.genre_name for genre in Genre.query.all()}
    genre_count = {key: 0 for key in genres.keys()}

    def count_genre(book_genres):
        book_genres = book_genres.split(',')
        for genre in book_genres:
            genre_count[int(genre)] += 1

    # 장르별 읽은 책 개수 카운트
    books[books['book_isbn'].isin(read_books)]['book_genres'].map(
        lambda x: count_genre(x))

    # 유저가 선택한 선호 장르는 가중치를 줌
    for user_genre in user_genres:
        genre_count[user_genre] += 10

    # 최적 장르 반환
    genre_id = max(genre_count, key=genre_count.get)
    genre_name = genres[genre_id]
    return genre_id, genre_name


def recommend_random_books():
    ''' 랜덤한 책 20개 추천하는 함수
    - paramter

    - return
        - list : 랜덤한 20개 책
    '''
    recommend = books['book_isbn'].drop_duplicates().sample(n=20).tolist()

    results = Book.query \
        .filter(Book.book_isbn.in_(recommend)) \
        .all()

    return [result.as_simple_dict(idx + 1) for idx, result in enumerate(results)]
# API Endpoints


@recommendation.route("/<int:userId>", methods=["GET"])
def recommend_by_user(userId):
    read_books = [book.book_isbn for book in UserBook.query.filter_by(
        user_id=userId).all()]
    user_genres = [genre.genre_id for genre in UserGenre.query.filter_by(
        user_id=userId).all()]
    wishlists = [wishlist.book_isbn for wishlist in Wishlist.query.filter_by(
        user_id=userId).all()]
    customized_genre_id, customized_genre_name = find_customized_genre(
        read_books, user_genres)

    customized_by_user = []     # 유저가 읽은 책 기반 추천
    customized_by_genre = []    # 유저 맞춤 장르 기반 추천
    customized_by_wishlist = []  # 유저 위시리스트 기반 추천

    if len(read_books) == 0:
        # 읽은 책 없으면 유저 선호장르를 기준으로 랜덤 샘플링
        customized_by_user = select_book_only_genres(user_genres)
    else:
        # 읽은 책 있는 경우 읽은 책 기준으로 추천
        customized_by_user = recommend(read_books)

    # 장르 기반 추천
    customized_by_genre = recommend(read_books, [customized_genre_id])

    # 위시리스트 기반 추천 (위시리스트에 책 없으면 빈칸으로 보냄)
    if len(wishlists) > 0:
        customized_by_wishlist = recommend(wishlists)

    res_obj = {
        "success": True,
        "data": {
            "customized_by_user": customized_by_user,
            "customized_by_genre": {
                "genre": {
                    "genre_id": customized_genre_id,
                    "genre_name": customized_genre_name
                },
                "customized_books": customized_by_genre
            },
            "customized_by_wishlist": customized_by_wishlist
        }
    }

    return make_response(jsonify(res_obj), 200)


@recommendation.route("", methods=["GET"])
def recommend_for_guest():
    genres = {genre.genre_id: genre.genre_name for genre in Genre.query.all()}
    random_genre_id = random.choice(list(genres.keys()))
    random_genre_name = genres[random_genre_id]

    random_books = recommend_random_books()
    random_books_genre = select_book_only_genres([random_genre_id])

    res_obj = {
        "success": True,
        "data": {
            "customized_by_user": random_books,
            "customized_by_genre": {
                "genre": {
                    "genre_id": random_genre_id,
                    "genre_name": random_genre_name
                },
                "customized_books": random_books_genre
            },
            "customized_by_wishlist": []
        }
    }
    return make_response(jsonify(res_obj), 200)


@recommendation.route("/book/<int:book_isbn>", methods=["GET"])
def recommend_for_book(book_isbn):
    customized_by_book = recommend([book_isbn])

    res_obj = {
        "success": True,
        "data": {
            "customized_by_book": customized_by_book
        }
    }
    return make_response(jsonify(res_obj), 200)
