from flask import Blueprint, make_response, jsonify
from flask_restx import Api, Resource

from models.Genre import Genre
from models.UserBook import UserBook
from models.UserGenre import UserGenre
from data.genre_category import genre_category
from apis.recommendation import books


statistics = Blueprint("statistics", __name__)
api = Api(statistics)


def get_user_genre_statistics(read_books, user_genres):
    ''' 유저별 장르 선호 통계 제공
    - paramter
        - read_books  : 유저가 읽은 책 목록
        - user_genres : 유저 선호 장르 목록 

    - return
        - response    : 유저 장르 선호 통계 json 데이터
    '''
    genres = {genre.genre_id: genre.genre_name for genre in Genre.query.all()}
    genre_count = {key: 0 for key in genres.keys()}

    def count_genre(book_genres):
        book_genres = book_genres.split(',')
        for genre in book_genres:
            genre_count[int(genre)] += 1

    # 장르별 읽은 책 개수 카운트
    books[books['book_isbn'].isin(read_books)]['book_genres'] \
        .map(lambda x: count_genre(x))

    # 유저가 선택한 선호 장르
    for user_genre in user_genres:
        genre_count[user_genre] += 1

    # 장르를 9개의 카테고리로 묶기
    response = get_categorize_genre_count(genres, genre_count)

    return response


def get_categorize_genre_count(genres, genre_count):
    ''' 유저가 장르별로 읽은 책 개수를 9개의 카테고리로 종합
    - paramter
        - genres        : 장르 목록
        - genre_count   : 장르별 유저가 읽은 책 개수

    - return
        - response      : json
    '''
    response = []
    for category_name, genre_ids in genre_category.items():
        category = {
            "category_name": category_name,
            "genres": []
        }

        for genre_id in genre_ids:
            data = {
                "genre_id": genre_id,
                "genre_name": genres[genre_id],
                "value": genre_count[genre_id]
            }
            category["genres"].append(data)

        response.append(category)
    return response


@statistics.route("/<int:userId>", methods=["GET"])
def recommend_by_user(userId):
    read_books = [book.book_isbn for book in UserBook.query.filter_by(
        user_id=userId).all()]
    user_genres = [genre.genre_id for genre in UserGenre.query.filter_by(
        user_id=userId).all()]

    res_obj = get_user_genre_statistics(read_books, user_genres)

    return make_response(jsonify(res_obj), 200)
