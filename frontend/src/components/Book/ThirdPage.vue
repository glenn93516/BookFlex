<template>
  <!-- 책 디테일, 구매 페이지 -->
  <div class="row">
    <div class="col-6">
      <div>
        <div>
          <h1>{{ bookInfo.book_title }}</h1>
        </div>

        <div >
          <img
            style="float:left;"
            height="165px"
            :src=bookInfo.book_cover
            alt=""
          >
        </div>

        <div style="height:165px">
          <h3>책 저자</h3>
          <div class="book-detail-text">
            {{ bookInfo.book_author }}
          </div>

          <h3>책 출판일</h3>
          <div class="book-detail-text">
            {{ bookInfo.book_date }}
          </div>

          <h3>출판사</h3>
          <div class="book-detail-text">
            {{ bookInfo.book_publisher }}
          </div>
        </div>

        <h3>책 목차</h3>
        <div>
          {{ bookInfo.book_contents }}
        </div>

        <h3>책 소개</h3>
        <div>
          ???
        </div>

      </div>
    </div>
    <div class="col-6">
      <div>
        <h2>구매하기</h2>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      bookInfo: {}
    }
  },
  created() {
    this.$axios.get(`${this.$store.getters.getServer}/book/${this.$route.params.bookIsbn}`)
    .then(res => {
      this.bookInfo = res.data.data
      console.log(res.data)
    })
    .catch(err => {
      console.error(err)
    })
  },
  methods: {
  }
}
</script>

<style>
  .book-detail-text {
    width: 300px;
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
  }
</style>