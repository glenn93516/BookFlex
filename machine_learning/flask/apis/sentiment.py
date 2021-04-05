import re
import numpy as np

import torch
from soynlp.normalizer import repeat_normalize
from transformers import AutoTokenizer, ElectraForSequenceClassification, AdamW

from flask import Blueprint, make_response, jsonify
from flask_restx import Api, Resource

from models.Review import Review


sentiment = Blueprint("sentiment", __name__)
api = Api(sentiment)


device = torch.device("cpu")
model = ElectraForSequenceClassification.from_pretrained(
    "monologg/koelectra-base-v3-discriminator")
model.load_state_dict(torch.load(
    "data/koelectra_base_v3.pt", map_location=device))
model.eval()
tokenizer = AutoTokenizer.from_pretrained(
    "monologg/koelectra-base-v3-discriminator")


def preprocessing(reviews):

    corpus = []

    for review in reviews:
        # remove e-mail
        review = re.sub(
            '([a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+.[a-zA-Z0-9-.]+)', '', str(review))
        review = re.sub(
            '(http|ftp|https)://(?:[-\w.]|(?:\da-fA-F]{2}))+', '', str(review))  # remove url
        review = re.sub(r'<[^>]+>', '', review)  # remove Html tags
        review = re.sub(r'\[[^>]+\]', '', review)  # remove Html tags
        review = re.sub(r'\{[^>]+\}', '', review)  # remove Html tags
        review = re.sub(r'\([^>]+\)', '', review)  # remove Html tags

        # 한글, 숫자, 알파벳, 기본구두점 제외
        review = re.sub(
            r'[^ .,?!/@$%~％·∼()\x00-\x7Fㄱ-ㅎㅏ-ㅣ가-힣0-9a-zA-Z]+', '', str(review))
        # review = re.sub(r'[^ㄱ-ㅎㅏ-ㅣ가-힣0-9a-zA-Z]', ' ', str(review)) # 한글, 숫자, 알파벳, 기본구두점 제외

        review = re.sub(r'\s+', ' ', str(review))  # remove spaces
        review = re.sub(r"^\s+", '', str(review))  # remove space from start
        review = re.sub(r'\s+$', '', str(review))  # remove space from the end

        review = repeat_normalize(str(review))

        corpus.append(review)

    return corpus


def get_sentiment(sentences):
    book_sentiment = {
        "total_count": 0,
        "positive": {
            "count": 0,
            "ratio": 0.0
        },
        "negative": {
            "count": 0,
            "ratio": 0.0
        }
    }
    if len(sentences) == 0:
        return book_sentiment

    sentences = preprocessing(sentences)

    inputs = tokenizer(
        sentences,
        return_tensors='pt',
        truncation=True,
        max_length=64,
        pad_to_max_length=True,
        add_special_tokens=True
    )

    input_ids = inputs['input_ids']
    attention_mask = inputs['attention_mask']

    for input_id, attention in zip(input_ids, attention_mask):
        y_pred = model(
            input_id.unsqueeze(0),
            attention_mask=attention.unsqueeze(0)
        )
        y_pred = np.argmax(y_pred[0].detach().numpy())

        book_sentiment["total_count"] += 1
        if y_pred == 1:
            book_sentiment["positive"]["count"] += 1
        else:
            book_sentiment["negative"]["count"] += 1

    book_sentiment["positive"]["ratio"] = round(
        (book_sentiment["positive"]["count"] / book_sentiment["total_count"]) * 100, 4)
    book_sentiment["negative"]["ratio"] = round(
        (book_sentiment["negative"]["count"] / book_sentiment["total_count"]) * 100, 4)

    return book_sentiment


@sentiment.route("/<int:book_isbn>/sentiment", methods=["GET"])
def get_book_sentiment(book_isbn):
    reviews = [review.review_content for review in Review.query.filter_by(
        book_isbn=book_isbn).all()]

    result = get_sentiment(reviews)

    res_obj = {
        "success": True,
        "data": {
            "book_isbn": book_isbn,
            "sentiment": result
        }
    }
    return make_response(jsonify(res_obj), 200)
