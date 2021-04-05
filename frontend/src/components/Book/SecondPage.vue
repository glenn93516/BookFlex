<template>
  <!-- 리뷰 분석, 담긴 문장 페이지 -->
  <div class="row">
    <!-- 왼쪽 페이지: 비지도학습 키워드 분석 -->
    <div class="col-6" style="border-right: 1px solid rgba(121, 121, 121, 0.692); height: 700px;">
      <div 
        style="text-align: left; 
                  margin-top: 20px; 
                  margin-left: 20px;
                  margin-bottom: 50px;
                  color: rgba(100, 100, 100);
                  ">
        <h6 class="mr-1">도서 분류 분석</h6>
        <hr style="rgba(50, 50, 50); margin-bottom: 40px;">
      </div>
      <SecondLeft :isbn="this.$route.params.bookIsbn" />
      <SecondLeft2 :isbn="this.$route.params.bookIsbn" @setBookSentiment="getBookSentiment" />
    </div>

    <!-- 오른쪽 페이지: 지도학습 긍부정 분석 + 한줄문장 -->
    <div class="col-6">
      <div 
        style="text-align: right; 
                  margin-top: 20px; 
                  margin-right: 20px;
                  margin-bottom: 50px;
                  color: rgba(100, 100, 100);
                  ">
        <h6 class="mr-1">리뷰 감정 분석</h6>
        <hr style="rgba(50, 50, 50); margin-bottom: 40px;">
      </div>
      
      <SecondRight v-if="bookSentiment.positive" :bookSentiment="bookSentiment" />
    </div>

  </div>
</template>

<script>
import SecondLeft from './SecondLeft.vue'
import SecondLeft2 from './SecondLeft2.vue'
import SecondRight from './SecondRight.vue'

export default {
  components: {
    SecondLeft,
    SecondLeft2,
    SecondRight,
  },
  data() {
    return {
      bookSentiment: {}
    }
  },
  methods: {
    getBookSentiment(bookSentiment) {
      this.bookSentiment = bookSentiment
    }
  }
}
</script>

<style>
#container {
 height: 300px;
}

.highcharts-figure, .highcharts-data-table table {
  min-width: 320px; 
  max-width: 700px;
  margin: 1em auto;
}

.highcharts-data-table table {
	font-family: Verdana, sans-serif;
	border-collapse: collapse;
	border: 1px solid #EBEBEB;
	margin: 10px auto;
	text-align: center;
	width: 100%;
	max-width: 500px;
}
.highcharts-data-table caption {
  padding: 1em 0;
  font-size: 1.2em;
  color: #555;
}
.highcharts-data-table th {
	font-weight: 600;
  padding: 0.5em;
}
.highcharts-data-table td, .highcharts-data-table th, .highcharts-data-table caption {
  padding: 0.5em;
}
.highcharts-data-table thead tr, .highcharts-data-table tr:nth-child(even) {
  background: #f8f8f8;
}
.highcharts-data-table tr:hover {
  background: #f1f7ff;
}

</style>