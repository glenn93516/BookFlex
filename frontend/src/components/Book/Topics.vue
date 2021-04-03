<template>
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

    <h3>리뷰 토픽 분석</h3>
    <div class="small row justify-content-center">
      <div v-if="bookTopics[0]">
        <BarChart  :chartData="chartData" ></BarChart>
      </div>
      <div v-else>
        리뷰데이터가 부족하여 분석할 수 없습니다.
        <br>
        다른 책을 선택해주세요.
      </div>
      
    </div>

  </div>
</template>

<script>
  import BarChart from './BarChart.js'

  export default {
    components: {
      BarChart,
    },
    props: {
      isbn: {
        type: String,
      },
    },
    data () {
      return {
        bookInfo: {},
        bookTopics: [],
        chartData: {
          labels: [],
          datasets: [
            {
              label: "",
              backgroundColor: '#f87979',
              data: [0]
            },
            {
              label: "",
              backgroundColor: '#f87979',
              data: [0]
            },
            {
              label: "",
              backgroundColor: '#f87979',
              data: [0]
            },
            {
              label: "",
              backgroundColor: '#f87979',
              data: [0]
            },
            {
              label: "",
              backgroundColor: '#f87979',
              data: [0]
            },
          ]
        },
        
        
      }
    },
    created() {
      this.getBookInfo(this.isbn)
      this.getTopics(this.isbn)
    },
    methods: {
      async getBookInfo(isbn) {
        await this.$axios.get(`${this.$store.getters.getServer}/book/${isbn}`)
        .then(res => {
          this.bookInfo = res.data.data
        })
        .catch(err => {
          console.error(err)
        })

        this.chartData['labels'] = [this.bookInfo.book_title]

      },
      async getTopics(isbn) {
        await this.$axios.get(`${this.$store.getters.getServer}/book/${isbn}/topics`)
        .then(res => {
          if(res.data.data) {
            const n = this.chartData.datasets.length;

            this.bookTopics = res.data.data.slice(0,n)

            let i = 0;
            for(i = 0; i < n; i++) {
              this.chartData.datasets[i].label = this.bookTopics[i].topic_name
              this.chartData.datasets[i].data[0] = this.bookTopics[i].probability
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