<template>
  <div style="margin-left: 20px;">

    <div class="second-left-small row justify-content-center">
      <div v-if="chartData.datasets[0].label">
        <DoughnutChart :chartData="chartData" />
      </div>
      <div v-else style="height:300px; display:flex; justify-content:center; align-items:center;">
        <h5 style="text-align: center;">리뷰 데이터 수집중</h5>
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
        bookSentiment: {},        
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
          console.log(res.data.data.sentiment)
          if(res.data.data) {
            this.bookSentiment = res.data.data.sentiment

            this.chartData.datasets[0].data[0] = this.bookSentiment.positive.ratio
            this.chartData.datasets[0].data[1] = this.bookSentiment.negative.ratio
            this.chartData.datasets[0].label = " "
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
    width: 300px !important;
    height: 300px !important;
  }
</style>