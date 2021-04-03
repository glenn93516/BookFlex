<template>
  <div>
    <!-- 모달 제목: 리뷰 -->
    <h3 class="head mt-4 pl-5" style="text-align: center;">
      <strong  
        style="color: black;"
      >
      "{{ reviewKeyword }}"</strong> 단어가 포함된 리뷰
    </h3>
    <hr class="mt-3 mb-0 mr-3 ml-3" style="background-color: grey;">
    <!-- 리뷰 내용 -->
    <div v-if="keywordReview">
      <p v-for="(review, idx) in keywordReview" :key="idx">{{ review.review_content }}</p>
      <br>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    reviewKeyword: String,
    reviewList: Array,
  },
  data() {
    return {
      keywordReview: [],
    }
  },
  created() {
    this.pickKeywordReview()
    // console.log(this.reviewList)
  },
  methods: {
    pickKeywordReview() {
      const keyword = this.reviewKeyword
      this.keywordReview = this.reviewList.filter(element => {
        if (element.review_content.indexOf(keyword) != -1) {
          return element
        }
      })
      // console.log(this.keywordReview)
    }
  },
}
</script>

<style>

</style>