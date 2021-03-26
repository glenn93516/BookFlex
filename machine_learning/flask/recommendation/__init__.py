from flask import Blueprint, make_response, jsonify
from flask_restx import Api, Resource

recommendation = Blueprint("recommendation", __name__)
api = Api(recommendation)


@recommendation.route("", methods=["GET"])
def getRecommend():
    res_obj = {
        "data": {
            "message": "hello"
        }
    }

    return make_response(jsonify(res_obj), 200)


@recommendation.route("/<int:bookIsbn>")
def recommend_by_book(bookIsbn):
    res_obj = {
        "data": {
            "message": f"hello {bookIsbn}"
        }
    }
    return make_response(jsonify(res_obj), 200)
