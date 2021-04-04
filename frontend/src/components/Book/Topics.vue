<template>
  <div>
    <div>
      <img width="80px" :src="bookInfo.book_cover" alt="">
      <p class="recommend-title">{{ bookInfo.book_title }}</p>
    </div>
    <h3>리뷰 토픽 분석</h3>
    <div class="small row justify-content-center">
      <div v-if="bookTopics[0]">
        <BarChart  :chartData="chartData" ></BarChart>
      </div>
      <div v-else style="height:310px">
        <h2>리뷰데이터가 부족하여 분석할 수 없습니다.</h2>
        <br>
        <h2>다른 책을 선택해주세요.</h2>
      </div>
    </div>

    <h3>추천도서</h3>
    <div style="display: flex;">
      <div v-for="(recommend, idx) in recommends" :key="idx">
        <div style="margin:4px">
          <img width="80px" :src="recommend.book_cover" alt="" @click="setIsbn(recommend.book_isbn)">
          <p class="recommend-title">{{ recommend.book_title }}</p>
        </div>
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
        recommends: [],
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
              backgroundColor: '#f1f4c8',
              data: [0]
            },
            {
              label: "",
              backgroundColor: '#ceecd5',
              data: [0]
            },
            {
              label: "",
              backgroundColor: '#c6f0f4',
              data: [0]
            },
            {
              label: "",
              backgroundColor: '#e6d5e6',
              data: [0]
            },
          ]
        },
        
        
      }
    },
    created() {
      this.getRecommend(this.isbn)
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
      getRecommend(isbn) {
        this.$axios.get(`${this.$store.getters.getServer}/book/${isbn}/recommend`)
        .then(res => {
          this.recommends = res.data.data.customized_by_book.slice(0, 6)
        })
        .catch(err => {
          console.error(err)
        })
      },
      setIsbn(isbn) {
        console.log("isbn>>",isbn)
        this.$emit("setIsbn", isbn)
      },
    },
  }
</script>

<style>
  .small canvas {
    width: 400px !important;
    height: 310px !important;
  }

  .recommend-title {
    width: 80px;
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
  }
</style>