<template>
  <!-- 북 커버, 워드 클라우드 페이지 -->
  <div class="row">
    <div class="col-6 detail-cover-box">
      <img :src="book.book_cover" :alt="book.book_title" class="detail-cover">
    </div>
    <div class="col-6" style="">
      <!-- 워드클라우드 2 -->
      <div style="text-align: center; margin-top: 20px;">
        <h2>키워드 분석</h2>
      </div>
      <div>
        <vue-word-cloud
          style="
            height: 600px;
            width: 450px;
            box-shadow: 
            box-shadow: 0px 5px 10px -10px gray;
            margin-left: 30px;
          "
          :words="words"
          :color="([, weight]) => weight > 5 ? 'DeepPink' : weight > 3 ? 'Red' : weight > 2 ? 'RoyalBlue' : 'Indigo'"
          font-family="Anton"
          font-weight="Bold"
        />
      </div>
    </div>
  </div>
</template>

<script>
import VueWordCloud from 'vuewordcloud';

export default {
  props: {
    book: Object,
  },
  components: {
    VueWordCloud
  },
  data() {
    return {
      words: []
    }
  },
  created() {
    console.log(this.book)
    const isbn = this.book.book_isbn
    this.$axios.get(`${this.$store.getters.getServer}/book/${isbn}/wordcloud`)
    .then(res => {
      console.log(res.data.data)
      this.words = res.data.data.slice(0, 30)
    })
    .catch(err => {
      console.error(err)
    })
    // wordcloud(document.getElementById('my_canvas'), { list: this.list } )
  },
  methods: {
    wordClickHandler(name, value, vm) {
      console.log('wordClickHandler', name, value, vm);
    }
  }
}
</script>

<style>
  .detail-cover-box {
    padding-top: 10px;
    padding-bottom: 10px;
    padding-right: 25px;
    padding-left: 25px;
    border-right: 1px solid rgba(121, 121, 121, 0.692);
  }
  .detail-cover {
    width: 530px;
    height: 680px;
  }
</style>