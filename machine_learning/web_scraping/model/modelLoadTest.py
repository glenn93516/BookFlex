from gensim.models.ldamodel import LdaModel
from gensim import corpora

# Load a potentially pretrained model from disk.
lda = LdaModel.load("model/testmodel/nouns_17topics_8passes")
corpus = corpora.MmCorpus("model/testmodel/corpus_nouns_17topics_8passes.mm")
dictionary = corpora.Dictionary.load("model/testmodel/dictionary_nouns_17topics_8passes.dict")
