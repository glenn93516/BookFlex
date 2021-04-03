<template>
  <!-- 책 디테일, 구매 페이지 -->
  <div class="row">
    <div class="col-6">
      <div>
        <h2>책 정보</h2>

        <h3>책 제목</h3>
        <div>
          {{ bookInfo.book_title }}
        </div>

        <h3>책 표지</h3>
        <div class="row justify-content-center">
          <img
            height="200px"
            :src=bookInfo.book_cover
            alt=""
          >
        </div>

        <h3>책 저자</h3>
        <div>
          {{ bookInfo.book_author }}
        </div>

        <h3>책 출판일</h3>
        <div>
          {{ bookInfo.book_date }}
        </div>

        <h3>책 목차</h3>
        <div>
          {{ bookInfo.book_contents }}
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

</style>