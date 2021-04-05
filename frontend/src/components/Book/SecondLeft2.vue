<template>
  <div style="margin-left: 20px;">

    <div class="second-left-small row justify-content-center">
      <div v-if="!loading && !chartData.datasets[0].label" style="height:300px; display:flex; justify-content:center; align-items:center;">
        <h5 style="text-align: center; ">😥검색된 결과가 없습니다.</h5>
      </div>
      <div v-else>
        <div v-if="loading" style="height:300px; display:flex; justify-content:center; align-items:center;">
          <b-spinner style="width: 3rem; height: 3rem;" label="Large Spinner"></b-spinner>
        </div>
        <div v-else>
          <DoughnutChart :chartData="chartData" />
        </div>
      </div>
    </div>

  </div>
</template>

<script>
  import DoughnutChart from './DoughnutChart.js'

  export default {
    components: {
      DoughnutChart,
    },
    props: {
      isbn: {
        type: String,
      },
    },
    data () {
      return {
        loading: true, 
        bookSentiment: {},
        positiveReviews: [],
        negativeReviews: [],   
        chartData: {
          labels: ["긍정", "부정"],
          datasets: [{
            label: "",
            data: [0, 0],
            backgroundColor: [
              'rgb(54, 162, 235)',
              'rgb(255, 99, 132)',
            ],
            hoverOffset: 6
          },]
        }
      }
    },
    created() {
      this.getSentiment(this.isbn)
    },
    methods: {
      async getSentiment(isbn) {
        await this.$axios.get(`${this.$store.getters.getServer}/book/${isbn}/sentiment`)
        .then(res => {
          this.loading = false
          if(res.data.data) {
            this.bookSentiment = res.data.data.sentiment

            this.chartData.datasets[0].data[0] = this.bookSentiment.positive.ratio
            this.chartData.datasets[0].data[1] = this.bookSentiment.negative.ratio
            this.chartData.datasets[0].label = " "

            this.$emit("setBookSentiment", this.bookSentiment)
          }
        })
        .catch(err => {
          console.error(err)
        })
      },
    }
  }
</script>

<style>
  .second-left-small canvas {
    width: 275px !important;
    height: 275px !important;
  }
</style>