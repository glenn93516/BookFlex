<파일 설명>

`wordcloud_wordcnt.txt`

: 리뷰별 토큰 종류 및 갯수 저장된 파일

`wordcloud_wordlist.txt`

: 리뷰별 토큰화 된 단어들을 저장한 파일



-> 리뷰별 토큰화 단어와 갯수는 `wordcloud_wordcnt.txt`을 사용하면 된다.



```python
# 파일 로드 코드
count = []
with open('data/wordcloud_wordcnt.txt', 'rb') as lf:
    count = pickle.load(lf)
```

