from gensim.corpora.dictionary import Dictionary
from gensim.models.ldamodel import LdaModel
from gensim import corpora
import preprocessing

# Load a potentially pretrained model from disk.
ldamodel = LdaModel.load("model/testmodel/nouns_17topics_8passes")
corpus = corpora.MmCorpus("model/testmodel/corpus_nouns_17topics_8passes.mm")
dictionary = Dictionary.load("model/testmodel/dictionary_nouns_17topics_8passes.dict")

# topics = ldamodel.print_topics(num_words=10)
# for topic in topics:
#     print(topic)
text = "이 책 누가 보는지 모르겠다. 이딴 것도 책이라고 쯧쯧.... 얼탱이 없다. 진짜 어유ㅗㅗ -_- 어떤 글을 써야지 새로운 dictionary가 나올까? 영어로 써야되나? 죄다 있는 단어네.... 모던 웹을 위한 HTML 스프링 부트로 배우는 자바 웹 개발 자바스크립트 알고리즘 문제 해결 전략 없는 단어가 없네 없어 갤럭시 태블릿 노트2 동해물과 백두산이 마르고 닳도록"
pre = preprocessing.preprosses(text, 0)
new_corpus = dictionary.doc2bow(pre)
print(pre)
print("pre len >>  ", len(pre))
print(new_corpus)
print("corpus len >> ", len(new_corpus))
print(ldamodel[new_corpus])

print(len(dictionary))
print(type(dictionary))


# ---update
print("-----update 후-----")
tmp = [pre]
dictionary.add_documents(tmp)
print(len(dictionary))

new_corpus2 = dictionary.doc2bow(pre)
print(pre)
print("pre len >>  ", len(pre))
print(new_corpus2)
print("corpus len >> ", len(new_corpus2))
print(dictionary[1064])
print(dictionary[5328])
# print(ldamodel[new_corpus2])
# print(dictionary[12943])
