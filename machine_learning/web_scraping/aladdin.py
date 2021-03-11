import glob

import requests
from bs4 import BeautifulSoup
import pandas as pd


filename_list = glob.glob('*.csv')
print(filename_list)

for filename in filename_list:
    print(filename)

    # 결과 파일 로드 제외
    if filename[:2] == '결과': continue

    # csv 파일 읽기
    df = pd.read_csv(filename, error_bad_lines=False)
    isbn13_list = df['ISBN13'].dropna(how='any')

    # 결과값 저장, 2차원 리스트
    values = list()

    # for idx1, isbn13 in enumerate(isbn13_list):
    #     try:
    #         isbn13 = int(isbn13)
    #         print(isbn13)

    #         url1 = f'https://www.aladin.co.kr/shop/wproduct.aspx?ISBN={isbn13}'

    #         res = requests.get(url1)
    #         res.raise_for_status()
    #         soup = BeautifulSoup(res.text, "lxml")

    #         item_id_url = soup.find('div', attrs={'class': 'tlist'}).ul.li.div.a['href']
    #         item_id = item_id_url[item_id_url.find('=') + 1:]


    #         url2 = f'https://www.aladin.co.kr/ucl/shop/product/ajax/GetCommunityListAjax.aspx?itemId={item_id}&IsAjax=true&pageType=1&sort=1&communitytype=CommentReview&IsOrderer=2&pageCount=100'

    #         res = requests.get(url2)
    #         res.raise_for_status()
    #         soup = BeautifulSoup(res.text, "lxml")

    #         np_40box_list_cont_list = soup.find_all("div", attrs={"class":"np_40box_list_cont"})

    #         for idx2, np_40box_list_cont in enumerate(np_40box_list_cont_list):

    #             for idx3, np_40box_list_cont_element in enumerate(np_40box_list_cont):
    #                 if idx3 == 1:
    #                     src = np_40box_list_cont_element.img["src"]
    #                     rating = src[-5] if src[-5] != '0' else '10'
    #                 elif idx3 == 3:
    #                     review = np_40box_list_cont_element.get_text().strip()
    #                     break
    #             values.append([isbn13, rating, review])

    #     except  Exception as e:
    #         print('#' * 20)
    #         print(url1)
    #         print(e)
    #         print('#' * 20)

    # csv 파일 쓰기
    df = pd.DataFrame(values, columns=['ISBN13', 'rating', 'review'])
    df.to_csv('./월간베스트결과/결과' + filename, index=False)