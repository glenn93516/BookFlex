import pickle

from flask import Blueprint, make_response, jsonify
from flask_restx import Api, Resource

from gensim.corpora.dictionary import Dictionary
from gensim.models.ldamodel import LdaModel
from gensim import corpora

from data.topic_category import topic_category


wordcloud = Blueprint("wordcloud", __name__)
api = Api(wordcloud)

keyword_dict = {}
book_indices = {}
with open('data/wordcount.pickle', 'rb') as fr:
    keyword_dict = pickle.load(fr)

with open('data/lda/corpus_book_index.pickle', 'rb') as fr:
    book_indices = pickle.load(fr)

# LDAmodel, corpus, dictionary 불러오기
ldamodel = LdaModel.load("data/lda/nouns_17topics_8passes")
corpus = corpora.MmCorpus("data/lda/corpus_nouns_17topics_8passes.mm")
dictionary = Dictionary.load("data/lda/dictionary_nouns_17topics_8passes.dict")


def get_book_topic_data(index):
    topic_distribution, words_in_topic, _ = ldamodel.get_document_topics(
        corpus[index], minimum_probability=0.0, per_word_topics=True)
    topic_distribution = sorted(
        topic_distribution, key=lambda x: x[1], reverse=True)

    return topic_distribution, words_in_topic


def get_book_topic_response(topic_distribution, words_in_topic):
    res = {}
    # 토픽 분포 분석
    for topic_id, probability in topic_distribution:
        data = {
            "topic_name": topic_category[topic_id],
            "probability": probability * 100,
            "words": []
        }
        res_data = {
            topic_id: data
        }
        res.update(res_data)

    # 토픽 내 단어 확인
    for word_id, topics in words_in_topic:
        word = dictionary[word_id]

        for topic in topics:
            if len(res[topic]["words"]) < 10:
                res[topic]["words"].append(word)

    return list(res.values())


@wordcloud.route("/<int:book_isbn>/wordcloud", methods=["GET"])
def get_book_wordcloud(book_isbn):
    word_counts = []

    if book_isbn in keyword_dict:
        word_counts = keyword_dict[book_isbn]

    res_obj = {
        "status": True,
        "data": word_counts
    }
    return make_response(jsonify(res_obj), 200)


@wordcloud.route("/<int:book_isbn>/topics", methods=["GET"])
def get_book_topics(book_isbn):
    try:
        index = book_indices[book_isbn]

        topic_distribution, words_in_topic = get_book_topic_data(index)

        topic_word_response_data = get_book_topic_response(
            topic_distribution, words_in_topic)
        res_obj = {
            "success": True,
            "data": topic_word_response_data
        }

        return make_response(jsonify(res_obj), 200)
    except KeyError as e:
        res_obj = {
            "success": True,
            "message": "분석할 리뷰가 부족합니다"
        }
        return make_response(jsonify(res_obj), 200)
