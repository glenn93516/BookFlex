<template>
  <div style="margin-left: 20px;">

    <div class="second-left-small row justify-content-center">
      <div v-if="loading" style="height:250px; display:flex; justify-content:center; align-items:center;">
        <b-spinner style="width: 3rem; height: 3rem;" label="Large Spinner"></b-spinner>
      </div>
      <div v-else>
        <div v-if="bookSentiment['positive'].count || bookSentiment['negative'].count">
          <DoughnutChart :chartData="chartData" />
        </div>
        <div v-else>
          <h5 style="height:250px; display:flex; justify-content:center; align-items:center;">리뷰 데이터가 충분하지 않습니다.</h5>
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
          labels: ["", ""],
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
            // console.log(this.bookSentiment)

            this.chartData.labels[0] = "긍정"
            this.chartData.datasets[0].data[0] = this.bookSentiment.positive.ratio
            
            this.chartData.labels[1] = "부정"
            this.chartData.datasets[0].data[1] = this.bookSentiment.negative.ratio

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
    width: 250px !important;
    height: 250px !important;
  }
</style>