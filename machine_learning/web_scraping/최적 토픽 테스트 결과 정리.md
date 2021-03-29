# 최적 토픽 테스트

### 사용 코드  

```python
from tqdm import tqdm 
import re 
from gensim.models.ldamodel import LdaModel 
from gensim.models.callbacks import CoherenceMetric 
from gensim import corpora 
from gensim.models.callbacks import PerplexityMetric 
import logging 
import pickle 
import pyLDAvis.gensim 
from gensim.models.coherencemodel import CoherenceModel 
import matplotlib.pyplot as plt 

def compute_coherence_values(dictionary, corpus, texts, limit, start, step): 
    coherence_values = [] 
    model_list = [] 
    for num_topics in range(start, limit, step): 
        model = LdaModel(corpus=corpus, id2word=dictionary, num_topics=num_topics) 
        model_list.append(model) 
        coherencemodel = CoherenceModel(model=model, texts=texts, dictionary=dictionary, coherence='c_v') 
        coherence_values.append(coherencemodel.get_coherence())
        print('num_topics : ',num_topics)
    print('coherence_values : ',coherence_values)
    return model_list, coherence_values 
    
def find_optimal_number_of_topics(dictionary, corpus, processed_data): 
    limit = 30;
    start = 4; 
    step = 2; 
    
model_list, coherence_values = compute_coherence_values(dictionary=dictionary, corpus=corpus, texts=processed_data, start=start, limit=limit, step=step) 

x = range(start, limit, step) 
plt.plot(x, coherence_values) 
plt.xlabel("Num Topics") 
plt.ylabel("Coherence score") 
plt.legend(("coherence_values"), loc='best') # 주제의 일관성
plt.show() 
```
```python
## 실행 코드 ##

# 정수 인코딩과 빈도수 생성 
dictionary = corpora.Dictionary(book_train_nouns) 
# 출현빈도가 적거나 자주 등장하는 단어는 제거 
dictionary.filter_extremes(no_below=10, no_above=0.05) 
corpus = [dictionary.doc2bow(text) for text in book_train_nouns]
print('Number of unique tokens: %d' % len(dictionary)) 
print('Number of documents: %d' % len(corpus)) 
     
logging.basicConfig(format='%(asctime)s : %(levelname)s : %(message)s', level=logging.INFO) 
     
# 최적의 토픽 수 찾기 
find_optimal_number_of_topics(dictionary, corpus, book_train_nouns)

```



## 데이터 셋  : 명사

1. 명사 테스트 1

   * start : 5

   * limit : 40

   * step : 3

   * 필터링 O -  출현빈도가 적거나 자주 등장하는 단어는 제거 

     ```python
     Number of unique tokens: 11814
     Number of documents: 42337
     ```

   * 최적 토픽 수  : `35` (0.43445459689760973)

   * 결과

     ```python
     coherence_values :  [0.38810248394358976, 0.4411537296475936, 0.45515331919757074, 0.4528575031558801, 0.44971625956060984, 0.4749615222911377, 0.40991808070316443, 0.45882209037141397, 0.4482073858921735, 0.4441715494589151, 0.4572752310519807, 0.44660374909936335]
     ```

   * ![nn](https://lab.ssafy.com/s04-field/field-team4/raw/949120282e56ddfda6da8213603e4ec98bbc7f3b/machine_learning/web_scraping/img/TopicTest_start5_step3_max40.PNG)



2. 명사 테스트 2

   * start : 5

   * limit : 40

   * step : 3

   * 필터링 X -> 토큰은 많아짐...

     ```python
     Number of unique tokens: 52744
     Number of documents: 42337
     ```

   * 최적 토픽 수  : `20` (0.4749615222911377)

   * 결과

     ```python
     coherence_values :  [0.4270652551304176, 0.3924971585752651, 0.40393028685108495, 0.43153169905013505, 0.39877759205692814, 0.4125075703308897, 0.4075336974683363, 0.4200369298207293, 0.4073890433749906, 0.43216339594092595, 0.43445459689760973, 0.4010838864332929]
     ```

   * ![nn](https://lab.ssafy.com/s04-field/field-team4/raw/ML-unsupervised-hj/machine_learning/web_scraping/img/TopicTest_start5_step3_max40_filteringX.png)

3. 명사 테스트 3

   * start : 4

   * limit : 30

   * step : 2

   * 필터링 O

     ```python
     Number of unique tokens: 11814
     Number of documents: 42337
     ```

   * 최적 토픽 수  : `10` (0.4719482203427656)

   * 결과 

     ```python
     coherence_values :  [0.44654675032620483, 0.46779928521843245, 0.47035592561324846, 0.4719482203427656, 0.46493869594154735, 0.4427643782477451, 0.41451357001271305, 0.4677259316551389, 0.45254496137583977, 0.46281230914659055, 0.4546724090111736, 0.4534856786993435, 0.41636390801521983]
     ```

   * ![nn](https://lab.ssafy.com/s04-field/field-team4/raw/ML-unsupervised-hj/machine_learning/web_scraping/img/TopicTest_start4_step2_max30_filteringO.png)



	### 명사 데이터에서 최적 토픽은 `10개`인 것 같다



## 데이터 셋  : 형용사

 1. 형용사 테스트 1

    * start : 4

    * limit : 30

    * step : 2

    * 필터링 O

      * ```python
        Number of unique tokens: 988
        Number of documents: 37790
        ```

    * 최적 토픽 수  : `8` (0.602607287279428)

    * 결과 

      ```python
      coherence_values :  [0.577515273599057, 0.5667265504414116, 0.602607287279428, 0.5759165292118503, 0.5866446310124694, 0.5626399424189923, 0.536448324782405, 0.5268451807149964, 0.5226923050384469, 0.5044045140109469, 0.4974232550759461, 0.466543778885851, 0.4726046006771038]
      ```

    * ![nn](https://lab.ssafy.com/s04-field/field-team4/raw/ML-unsupervised-hj/machine_learning/web_scraping/img/TopicTest_adj_start4_step2_max30_filteringO.png)

2. 형용사 테스트 2

   * start : 4

   * limit : 30

   * step : 2

   * 필터링 X

     ```python
     Number of unique tokens: 1605
     Number of documents: 37790
     ```

   * 최적 토픽 수  : `8` (0.7129450359964224)

   * 결과 

     ```python
     coherence_values :  [0.6950044957014434, 0.7115034444875575, 0.7129450359964224, 0.6992680739026352, 0.6919703363876599, 0.7001176240202247, 0.6939679578706369, 0.6788257665883044, 0.684072894387563, 0.6570909124960044, 0.6554764535179204, 0.6524773506399708, 0.6442708580013116]
     ```

     ![nn](https://lab.ssafy.com/s04-field/field-team4/raw/ML-unsupervised-hj/machine_learning/web_scraping/img/TopicTest_adj_start4_step2_max30_filteringX.png)



#### 형용사 테스트의 Coherence 3가지 데이터 셋 중에서 제일 높다. 토픽 갯수도 적절하다.



## 데이터 셋  : 명사 + 형용사 (길이 1이상)

 1. 명사+형용사 테스트 1

    * start : 4

    * limit : 30

    * step : 2

    * 필터링 O

      * ```python
        Number of unique tokens: 12812
        Number of documents: 42465
        ```

    * 최적 토픽 수  : `24` (0.4693103871765351)

    * 결과 

      ```python
      coherence_values :  [0.427611263978068, 0.44547678894211407, 0.41562514865230754, 0.4487028404950806, 0.4063707761977278, 0.4058296394217458, 0.4430400222019587, 0.4349626234755797, 0.4443120521967422, 0.4365859994643184, 0.4693103871765351, 0.45060380399580746, 0.4501031270752332]
      ```

    * ![nn](https://lab.ssafy.com/s04-field/field-team4/raw/ML-unsupervised-hj/machine_learning/web_scraping/img/TopicTest_nouns_adj_start4_step2_max30_filteringO.png)

2. 명사+형용사 테스트 2

   * start : 4

   * limit : 30

   * step : 2

   * 필터링 X

     ```python
     Number of unique tokens: 54349
     Number of documents: 42465
     ```

   * 최적 토픽 수  : `28` (0.36046152712944757)

   * 결과 

     ```python
     coherence_values :  [0.19970424598465428, 0.24713215862930868, 0.2787677488003875, 0.2994772671551724, 0.3028138036148036, 0.30315327475818726, 0.3223644161390871, 0.30532769741117544, 0.341949112722366, 0.31747683286849704, 0.3503520215339222, 0.34823892606848844, 0.36046152712944757]
     ```

     ![nn](https://lab.ssafy.com/s04-field/field-team4/raw/ML-unsupervised-hj/machine_learning/web_scraping/img/TopicTest_nouns_adj_start4_step2_max30_filteringX.png)



#### 최적 토픽의 갯수가 너무 많다.