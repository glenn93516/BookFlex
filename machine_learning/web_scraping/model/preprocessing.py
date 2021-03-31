import re
import konlpy
from konlpy.tag import Okt


def cleansing(text):
    # text = str(text)
    pattern = "([a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+.[a-zA-Z0-9-.]+)"  # e-mail 주소 제거
    text = re.sub(pattern=pattern, repl=" ", string=text)

    pattern = "(http|ftp|https)://(?:[-\w.]|(?:\da-fA-F]{2}))+"  # url 제거
    text = re.sub(pattern=pattern, repl=" ", string=text)

    pattern = "([^ㄷㅋㅎㅜㅠ가-힣a-zA-Z0-9])+"  # 한글 자음, 모음 제거 - without 'ㄷ,ㅋ,ㅎ,ㅜ,ㅠ'
    text = re.sub(pattern=pattern, repl=" ", string=text)

    pattern = "<[^>]*>"  # html tag 제거
    text = re.sub(pattern=pattern, repl=" ", string=text)

    pattern = "[\r|\n]"  # \r, \n 제거
    text = re.sub(pattern=pattern, repl=" ", string=text)

    pattern = re.compile(r"\s+")  # 이중 space 제거
    text = re.sub(pattern=pattern, repl=" ", string=text)

    return text


def preprosses(text, pos):  # text : 전처리할 text / pos : 0=명사, 1=형용사(길이>1), 2=명사+형용사
    #  도서 불용어를 정의
    stopwords = [
        "의",
        "가",
        "이",
        "은",
        "들",
        "는",
        "좀",
        "잘",
        "걍",
        "과",
        "도",
        "를",
        "으로",
        "자",
        "에",
        "와",
        "한",
        "하다",
        "것",
        "라고",
        "에게",
        "라면",
        "을",
        "이라",
        "라니",
        "있다",
        "아",
        "랑",
        "쯤된",
        "에서",
        "에선",
        "어",
        "이지만",
        "으로나",
        "때",
        "때는",
        "때라면",
        "때라서",
        "라",
        "이다",
        "있",
        "죠",
        "고",
        "니",
        "로",
        "있",
        "같",
        "어서",
        "어요",
        "는데",
        "습니다",
        "면서",
        "많이",
        "마",
        "더",
        "그렇다",
        "의",
        "당",
        "좀",
        "책",
        "안",
        "볼",
        "게",
        "안",
        "정말",
        "듯",
        "이제야",
        "여",
        "요",
        "게다가",
        "같다",
        "임",
        "로서",
        "이제",
        "만",
        "인",
        "붙이",
        "그",
        "저",
        "수",
        "가제",
        "부터",
        "닷",
        "저희",
        "적",
        "알",
        "쉬",
        "못",
        "꼭",
        "살",
        "제",
        "권",
        "제",
        "분",
        "나",
        "내",
        "진작",
        "전",
        "뿐",
        "대한",
        "대해",
        "책",
        "좋다",
    ]
    data = []  # return data

    cleansing_text = cleansing(text)  # cleansing text
    # print(cleansing_text)
    if cleansing_text == "":  # 남은 text가 없을 때
        return data

    okt = Okt()
    pos_text = okt.pos(cleansing_text)  # 품사 분류
    # print(pos_text)
    after_stopword = [(word, pos) for (word, pos) in pos_text if not word in stopwords]

    if not after_stopword:  # 불용어 제거되고 남은 text가 없을 때
        return data

    if pos == 0:  # 명사
        data = [word for (word, pos) in after_stopword if pos in "Noun"]  # 명사만 저장
    elif pos == 1:  # 형용사
        for (word, pos) in after_stopword:  # 형용사 : 길이가 1이상
            if pos == "Adjective" and len(word) > 1:
                data.append(word)
    elif pos == 2:  # 명사 + 형용사
        for (word, pos) in after_stopword:  # 형용사 : 길이가 1이상
            if pos == "Noun":
                data.append(word)
            elif pos == "Adjective" and len(word) > 1:
                data.append(word)
    return data


# ------- test------

# text = (
#     "네 방 좀 정리해라, 거실 좀 청소해라~~~~엄마의 계속되는 잔소리보다 더 약발 좋은 그림책! 병관이 지원이 시리즈는 어떤 걸 읽어도 우리집 얘기 같아 찔끔하지요.^^"
# )
# print(cleansing(text))

# for i in range(3):
#     print(i, " > ", preprosses(text, i))