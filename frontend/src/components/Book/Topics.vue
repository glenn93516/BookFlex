<template>
  <div style="margin-left: 20px;">

    <div>
      <b-card no-body class="overflow-hidden" style="margin: 0px 0px; width: 500px; height: 140px;">
        <b-row no-gutters>
          <b-col md="3">
            <b-card-img style="margin-left:10px; padding:10px; width:100px; height:140px;" :src=bookInfo.book_cover alt="Image" class="rounded-0"></b-card-img>
          </b-col>
          <b-col md="9">
            <b-card-body>
              <b-card-text style="font-size: 14px">
                <h4 class="bookinfo-title">{{ bookInfo.book_title }}</h4>
                <p>저자 : {{ bookInfo.book_author }}</p>
                <p>출판일 : {{ bookInfo.book_date }}</p>
                <p>출판사 : {{ bookInfo.book_publisher }}</p>
              </b-card-text>
            </b-card-body>
          </b-col>
        </b-row>
      </b-card>
    </div>

    <div class="small row justify-content-center">
      <div v-if="bookTopics[0]">
        <RadarChart :chartData="chartData" />
      </div>
      <div v-else style="height:320px; display:flex; justify-content:center; align-items:center;">
        <h5 style="text-align: center; ">😥검색된 결과가 없습니다.</h5>
      </div>
    </div>

    <label for="#recommend">추천도서</label>
    <div
      id="recommend"
      style="display: flex;
      justify-content: space-between;
      height: 142px; 
      margin:10px; 
      padding: 10px; 
      border:1px solid #dadada; 
      border-radius: 5px;"
    >
      <div v-for="(recommend, idx) in recommends" :key="idx">
        <div class="overlay-image" @click="setIsbn(recommend.book_isbn)">
          <img 
            width="80px" 
            height="120px" 
            :src="recommend.book_cover" 
            alt="" 
            style="cursor: pointer;"
          >
          <div class="hover">
            <div class="text">{{ recommend.book_title }}</div>
          </div>
        </div>
      </div>

    </div>

  </div>
</template>

<script>
  import RadarChart from './RadarChart.js'

  export default {
    components: {
      RadarChart,
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
            backgroundColor: 'rgba(255, 99, 132, 0.2)',
            borderColor: 'rgb(255, 99, 132)',
            pointBackgroundColor: 'rgb(255, 99, 132)',
            pointBorderColor: '#fff',
            pointHoverBackgroundColor: '#fff',
            pointHoverBorderColor: 'rgb(255, 99, 132)'
          },]
        }
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

        this.chartData.datasets.label = this.bookInfo.book_title
      },
      async getTopics(isbn) {
        await this.$axios.get(`${this.$store.getters.getServer}/book/${isbn}/topics`)
        .then(res => {
          if(res.data.data) {
            const n = this.chartData.labels.length;

            this.bookTopics = res.data.data.slice(0,n)

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
    
    width: 320px !important;
    height: 320px !important;
  }

  .bookinfo-title {
    width: 350px;
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
  }

  /* Main container */
  .overlay-image {
    position: relative;
    width: 100%;
  }

  /* Original image */
  .overlay-image .image {
    display: block;
    width: 100%;
    height: auto;
  }

  /* Original text overlay */
  .overlay-image .text {
    color: #fff;
    font-size: 14px;
    line-height: 1.5em;
    text-shadow: 2px 2px 2px #000;
    text-align: center;
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    width: 100%;
  }

  /* New overlay on hover */
  .overlay-image .hover {
    position: absolute;
    top: 0;
    height: 100%;
    width: 100%;
    opacity: 0;
    transition: .5s ease;
  }

  /* New overlay appearance on hover */
  .overlay-image:hover .hover {
    opacity: 1;
  }

  .overlay-image .hover {
    background-color: rgba(0,0,0,0.5);
  }
</style>