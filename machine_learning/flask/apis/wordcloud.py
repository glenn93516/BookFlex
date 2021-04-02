import pickle
from flask import Blueprint, make_response, jsonify
from flask_restx import Api, Resource

wordcloud = Blueprint("wordcloud", __name__)
api = Api(wordcloud)

keyword_dict = {}
with open('data/keyword_count_dict.pickle', 'rb') as fr:
    keyword_dict = pickle.load(fr)


@wordcloud.route("/<int:book_isbn>", methods=["GET"])
def get_book_wordcloud(book_isbn):
    word_counts = []

    if book_isbn in keyword_dict:
        word_counts = keyword_dict[book_isbn]

    res_obj = {
        "status": True,
        "data": word_counts
    }
    return make_response(jsonify(res_obj), 200)
