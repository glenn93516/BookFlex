<template>
  <div class="shelf">
    <div 
      id="first-shelf-tag"
      class="tag"
      style="text-align: center;"
    >
      <div 
        class="tag-name"
        style="border: 1px solid yellow;"
      >
        맞춤형 추천
      </div>
    </div>
    <div 
      id="first-row"
      class="shelf-row"
    >
      <div class="d-flex justify-content-around books">
        <Book v-for="(book, idx) in suitRecommend" :key="idx" class="book" :imgSrc="suitRecommend[idx].imgSrc"></Book>
      </div>
      <Book-shelf />
    </div>
    <div
      id="second-shelf-tag"
      class="tag"
    >
      <div 
        class="tag-name"
        style="border: 1px solid green;"
      >
        장르 이름
      </div>
    </div>
    <div 
      id="second-row"
      class="shelf-row"
    >
      <div class="d-flex justify-content-around books">
        <Book v-for="(book, idx) in genreRecommend" :key="idx" class="book" :imgSrc="genreRecommend[idx].imgSrc"></Book>
      </div>
      <Book-shelf />
    </div>
    <div
      id="third-shelf-tag"
      class="tag"
    >
      <div 
        class="tag-name"
        style="border: 1px solid red;"
      >
        위시리스트
      </div>
    </div>
    <div
      id="third-row"
      class="shelf-row"
    >
      <div class="d-flex justify-content-around books">
        <Book v-for="(book, idx) in wishRecommend" :key="idx" class="book" :imgSrc="wishRecommend[idx].imgSrc"></Book>
      </div>
      <Book-shelf />
    </div>
  </div>
</template>

<script>
import Book from '@/components/Main/Book.vue'
import BookShelf from '@/components/Main/BookShelf.vue'

export default {
  name: 'main',
  components: {
    Book,
    BookShelf,
  },
  data() {
    return {
      suitRecommend: [
        {book: '1번책', imgSrc: 'http://bimage.interpark.com/goods_image/3/0/9/9/267393099g.jpg'},
        {book: '2번책', imgSrc: 'http://bimage.interpark.com/goods_image/6/4/1/8/308016418g.jpg'},
        {book: '3번책', imgSrc: 'http://bimage.interpark.com/goods_image/5/1/4/1/286555141g.jpg'},
        {book: '4번책', imgSrc: 'http://bimage.interpark.com/goods_image/3/9/6/1/347293961g.jpg'},
        ],
      genreRecommend: [
        {book: '1번책', imgSrc: 'http://bimage.interpark.com/goods_image/4/2/7/9/340174279g.jpg'},
        {book: '2번책', imgSrc: 'http://bimage.interpark.com/goods_image/8/8/9/0/310008890g.jpg'},
        {book: '3번책', imgSrc: 'http://bimage.interpark.com/goods_image/5/8/5/6/347485856g.jpg'},
        {book: '4번책', imgSrc: 'http://bimage.interpark.com/goods_image/1/4/8/4/319111484g.jpg'},
      ],
      wishRecommend: [
        {book: '1번책', imgSrc: 'http://bimage.interpark.com/goods_image/7/9/3/0/260947930g.jpg'},
        {book: '2번책', imgSrc: 'http://bimage.interpark.com/goods_image/5/9/6/1/241435961g.jpg'},
        {book: '3번책', imgSrc: 'http://bimage.interpark.com/goods_image/0/3/8/0/347230380g.jpg'},
        {book: '4번책', imgSrc: 'http://bimage.interpark.com/goods_image/3/7/2/5/346643725g.jpg'},
      ],
    }
  }
}
</script>

<style>
  .shelf{
    margin-top: 40px;
    background-color: rgba(255, 255, 255, 0.4);
    border-radius: 10px;
  }
  .shelf-row{
    text-align: center; margin: 0 auto;
  }
  .books {
    width: 950px;
    margin: auto;
  }
  .tag{
    text-align: center;
    margin-top: -20px;
  }
  .tag-name{
    margin-bottom: 20px;
    background-color: ivory;
    display: inline-block;
    font-size: 1.2rem;
    font-weight: 700;
    padding: 10px;
    width: 180px;
    box-shadow: 1px 1px 2px rgb(150, 150, 150);
    border-radius: 3px/ 3px;
  }
  .book {
    box-shadow: 2px 2px 3px rgb(102, 102, 102);
  }
</style>