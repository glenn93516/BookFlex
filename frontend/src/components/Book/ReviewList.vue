<template>
  <div>
    <!-- 모달 제목: 리뷰 -->
    <h3 class="head mt-4 pl-5" style="text-align: center;">
      <strong  
        style="color: black;"
      >
      "{{ reviewKeyword }}"</strong> 단어가 포함된 리뷰
    </h3>
    <hr class="mt-3 mb-0" style="background-color: grey;">
    <!-- 리뷰 내용 -->
    <div v-if="keywordReview" style="overflow: auto; height:650px">
      <p v-for="(review, idx) in keywordReview" :key="idx"
        style="font-size: 20px;"
      >
        <br>
        "{{ review.review_content }}"
        <br>
      </p>
    </div>
  </div>
</template>

<script>
export default {
  name: "ReviewList",
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
  },
  beforeUpdate() {
    this.pickKeywordReview()
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