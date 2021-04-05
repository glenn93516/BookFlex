<template>
  <div style="margin-left: 20px;">

    <div class="small row justify-content-center">
      <div v-if="bookTopics[0]">
        <DoughnutChart :chartData="chartData" />
      </div>
      <div v-else style="height:300px; display:flex; justify-content:center; align-items:center;">
        <h5 style="text-align: center; ">리뷰 데이터 수집중</h5>
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
        recommends: [],
        bookInfo: {},
        bookTopics: [],        
        chartData: {
          labels: ["", "", "", "", ""],
          datasets: [{
            label: "",
            data: [0, 0, 0, 0, 0],
            fill: true,
            backgroundColor: [
              'rgb(255, 205, 86)',
              'rgb(75, 192, 192)',
              'rgb(54, 162, 235)',
              'rgb(255, 99, 132)',
              'rgb(201, 203, 207)',
            ],
            hoverOffset: 6
          },]
        }
      }
    },
    created() {
      this.getTopics(this.isbn)
    },
    methods: {
      async getTopics(isbn) {
        await this.$axios.get(`${this.$store.getters.getServer}/book/${isbn}/topics`)
        .then(res => {
          if(res.data.data) {
            const n = this.chartData.labels.length;

            this.bookTopics = res.data.data.slice(0,n)
            console.log(this.bookTopics)

            let i = 0;
            for(i = 0; i < n; i++) {
              this.chartData.labels[i] = this.bookTopics[i].topic_name
              this.chartData.datasets[0].data[i] = this.bookTopics[i].probability
            }
          }
        })
        .catch(err => {
          console.error(err)
        })
      },
    },
  }
</script>

<style>
  .small canvas {
    
    width: 300px !important;
    height: 300px !important;
  }
</style>