<template>
  <!-- 북 커버, 워드 클라우드 페이지 -->
  <div class="row">
    <div class="col-6 detail-cover-box">
      <img :src="book.book_cover" :alt="book.book_title" class="detail-cover">
    </div>
    <div class="col-6" style="">
      <!-- 워드클라우드 2 -->
      <div style="text-align: center; margin-top: 20px;">
        <h2>키워드 분석</h2>
      </div>
      <div>
        <WordCloud :isbn="isbn"/>
      </div>
    </div>
  </div>
</template>

<script>
import WordCloud from '@/components/Element/WordCloud.vue'

export default {
  props: {
  },
  components: {
    WordCloud,
  },
  data() {
    return {
      book: Object,
      isbn: "",
    }
  },
  created() {
    const isbn = this.$route.params.bookIsbn
    this.isbn = isbn
    // 책 정보 가져오는 함수
    this.getBookData(isbn)
  },
  methods: {
    getBookData(isbn) {
      this.$axios.get(`${this.$store.getters.getServer}/book/${isbn}`)
      .then(res => {
        console.log(res.data.data)
        this.book = res.data.data
      })
      .catch(err => {
        console.error(err)
      })
    },
  }
}
</script>

<style>
  .detail-cover-box {
    padding-top: 10px;
    padding-bottom: 10px;
    padding-right: 25px;
    padding-left: 25px;
    border-right: 1px solid rgba(121, 121, 121, 0.692);
  }
  .detail-cover {
    width: 530px;
    height: 680px;
  }
</style>