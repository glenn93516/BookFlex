import glob

import pandas as pd
import requests
from bs4 import BeautifulSoup


foldername_list = glob.glob('csv_books/*')
for foldername in foldername_list:
    print(foldername)

    filename_list = glob.glob(f'{foldername}/*')
    for filename in filename_list:
        print(filename)
  
        df = pd.read_csv(filename)
        isbn13_list = df['ISBN13']

        # 결과값 저장, 2차원 리스트
        values = list()

        for idx1, isbn13 in enumerate(isbn13_list):
            try:
                isbn13 = int(isbn13)
                print(isbn13)

                url1 = f'https://www.aladin.co.kr/shop/wproduct.aspx?ISBN={isbn13}'

                res = requests.get(url1)
                res.raise_for_status()
                soup = BeautifulSoup(res.text, "lxml")

                item_id_url = soup.find('div', attrs={'class': 'tlist'}).ul.li.div.a['href']
                item_id = item_id_url[item_id_url.find('=') + 1:]


                url2 = f'https://www.aladin.co.kr/ucl/shop/product/ajax/GetCommunityListAjax.aspx?itemId={item_id}&IsAjax=true&pageType=1&sort=1&communitytype=CommentReview&IsOrderer=2&pageCount=500'

                res = requests.get(url2)
                res.raise_for_status()
                soup = BeautifulSoup(res.text, "lxml")

                np_40box_list_cont_list = soup.find_all("div", attrs={"class":"np_40box_list_cont"})

                for idx2, np_40box_list_cont in enumerate(np_40box_list_cont_list):

                    for idx3, np_40box_list_cont_element in enumerate(np_40box_list_cont):
                        if idx3 == 1:
                            src = np_40box_list_cont_element.img["src"]
                            rating = src[-5] if src[-5] != '0' else '10'
                        elif idx3 == 3:
                            review = np_40box_list_cont_element.get_text().strip()
                            break
                    values.append([isbn13, rating, review])

            except  Exception as e:
                print('#' * 20)
                print(url1)
                print(e)
                print('#' * 20)

        # csv 파일 쓰기
        foldername1, foldername2, filename = filename.split('\\')
        df = pd.DataFrame(values, columns=['ISBN13', 'rating', 'review'])
        df.to_csv(f'csv_reviews/{foldername2}/{filename}', index=False)
