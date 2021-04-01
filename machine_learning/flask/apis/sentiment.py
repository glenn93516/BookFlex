import re
import numpy as np

import torch
from soynlp.normalizer import repeat_normalize
from transformers import AutoTokenizer, ElectraForSequenceClassification, AdamW

from flask import Blueprint, make_response, jsonify
from flask_restx import Api, Resource


sentiment = Blueprint("sentiment", __name__)
api = Api(sentiment)


device = torch.device("cpu")
model = ElectraForSequenceClassification.from_pretrained(
    "monologg/koelectra-base-v3-discriminator")
model.load_state_dict(torch.load(
    "data\koelectra_base_v3.pt", map_location=device))
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


def test_sentences(sentences):

    sentences = preprocessing(sentences)

    inputs = tokenizer(
        sentences,
        return_tensors='pt',
        truncation=True,
        max_length=64,
        pad_to_max_length=True,
        add_special_tokens=True
    )
    input_ids = inputs['input_ids'][0]
    attention_mask = inputs['attention_mask'][0]

    y_pred = model(
        input_ids.unsqueeze(0),
        attention_mask=attention_mask.unsqueeze(0)
    )

    return y_pred[0].detach().cpu().numpy()


@sentiment.route("/<int:book_isbn>/sentiment", methods=["GET"])
def get_book_sentiment(book_isbn):
    result = test_sentences(['연기는 별로지만 재미 하나는 끝내줌!'])
    print(result)

    result = np.argmax(result)
    print(result)
    res_obj = {
        "success": True,
        "data": {

        }
    }
    return make_response(jsonify(res_obj), 200)
