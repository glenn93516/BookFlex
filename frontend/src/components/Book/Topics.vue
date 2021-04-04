<template>
  <div style="margin-left: 20px;">
    <div >
      <img
        style="float:left;"
        height="100px"
        :src=bookInfo.book_cover
        alt=""
      >
    </div>

    <div class="bookinfo-content bookinfo-text" style="height:100px">
      <p>책 제목 : {{ bookInfo.book_title }}</p>
      <p>책 저자 : {{ bookInfo.book_author }}</p>
      <p>책 출판일 : {{ bookInfo.book_date }}</p>
      <p>출판사 : {{ bookInfo.book_publisher }}</p>
    </div>

    <!-- <div>
      <b-card no-body class="overflow-hidden" style="max-width: 540px;">
        <b-row no-gutters>
          <b-col md="6">
            <b-card-img src="https://picsum.photos/400/400/?image=20" alt="Image" class="rounded-0"></b-card-img>
          </b-col>
          <b-col md="6">
            <b-card-body title="Horizontal Card">
              <b-card-text>
                This is a wider card with supporting text as a natural lead-in to additional content.
                This content is a little bit longer.
              </b-card-text>
            </b-card-body>
          </b-col>
        </b-row>
      </b-card>
    </div> -->

    <br><hr><br>

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

    <br><hr><br>

    <div style="display: flex;">
      <div v-for="(recommend, idx) in recommends" :key="idx">
        <div style="margin:0px 8px">
          <img width="80px" height="120px" :src="recommend.book_cover" alt="" @click="setIsbn(recommend.book_isbn)">
          <!-- <p class="recommend-title">{{ recommend.book_title }}</p> -->
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
          this.recommends = res.data.data.customized_by_book.slice(0, 5)
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
    height: 300px !important;
  }

  .bookinfo-text {
    width: 300px;
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
  }
  .bookinfo-content {
    width: 300px;
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
  }

  .recommend-title {
    width: 80px;
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
  }
</style>