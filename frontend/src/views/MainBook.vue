<template>
  <div class="shelf">
    <div 
      id="first-shelf-tag"
      class="tag"
    >
      <img src="@/assets/tag.png" style="width: 50px; margin-bottom: 10px; color: black;" alt="제목태그">
      <div 
        class="tag-name"
      >
        맞춤형 추천
      </div>
    </div>
    <div 
      id="first-row"
      class="shelf-row"
    >
      <div class="d-flex justify-content-around books">
        <Book v-for="(book, idx) in suitRecommend" :key="idx" class="book" :imgSrc="book.book_cover"></Book>
      </div>
      <Book-shelf />
    </div>
    <div
      id="second-shelf-tag"
      class="tag"
    >
      <img src="@/assets/tag.png" style="width: 50px; margin-bottom: 10px;" alt="제목태그">
      <div 
        class="tag-name"
      >
        스릴러
      </div>
      <img src="@/assets/tag.png" style="width: 50px; margin-bottom: 10px;" alt="제목태그">
      <div 
        class="tag-name"
      >
        정치
      </div>
    </div>
    <div 
      id="second-row"
      class="shelf-row"
    >
      <div class="d-flex justify-content-around books">
        <Book v-for="(book, idx) in genreRecommend" :key="idx" class="book" :imgSrc="book.book_cover"></Book>
      </div>
      <Book-shelf />
    </div>
    <div
      id="third-shelf-tag"
      class="tag"
    >
      <img src="@/assets/tag.png" style="width: 50px; margin-bottom: 10px;" alt="제목태그">
      <div 
        class="tag-name"
      >
        위시리스트
      </div>
    </div>
    <div
      id="third-row"
      class="shelf-row"
    >
      <div class="d-flex justify-content-around books">
        <Book v-for="(book, idx) in wishRecommend" :key="idx" class="book" :imgSrc="book.book_cover"></Book>
      </div>
      <Book-shelf />
    </div>
  </div>
</template>

<script>
import Book from '@/components/Main/Book.vue'
import BookShelf from '@/components/Main/BookShelf.vue'

export default {
  name: 'Main',
  components: {
    Book,
    BookShelf,
  },
  data() {
    return {
      suitRecommend: {},
      genreRecommend: {},
      wishRecommend: {},
      bookDataList: {},
    }
  },
  mounted() {
    this.getBookData()
  },
  methods: {
    // 책을 로드하는 함수
    loadBookData() {
      const token = this.$store.getters.getAccessToken
      if (token != undefined) {
        this.getBookData()
      } else {
        this.getBookData()
      }
    },
    // 로그인 되어 있지않을 때 일반적인 책 정보를 가져올 axios 함수(검색에서도 쓰임)
    getBookData() {
      this.$axios.get(`${this.$store.getters.getServer}/book`)
      .then(res => {
        this.bookDataList = res.data.data
        this.suitRecommend = this.bookDataList.slice(0, 4)
        this.genreRecommend = this.bookDataList.slice(4, 8)
        this.wishRecommend = this.bookDataList.slice(8, 12)
      })
    },
    getRcmdBook() {
      // 추천 알고리즘 완성시 api 연결
    },
  }
}
</script>

<style>
  .shelf{
    margin-top: 40px;
    background-color: rgba(255, 255, 255, 0);
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
    text-align: left;
    margin-top: -20px;
    margin-left: 50px;
  }
  .tag-name{
    margin-bottom: 20px;
    background-color: rgba(161, 114, 70, 0);
    display: inline-block;
    font-size: 1.5rem;
    font-weight: 700;
    padding: 10px;
    margin-left: 0px;
    /* box-shadow: 1px 1px 2px rgb(150, 150, 150); */
    border-radius: 3px/ 3px;
    border: 0px;
    color: rgb(90, 90, 90);
    align-items: left;
  }
</style>