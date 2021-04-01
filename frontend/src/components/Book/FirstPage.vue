<template>
  <!-- 북 커버, 워드 클라우드 페이지 -->
  <div class="row">
    <div class="col-6 detail-cover-box">
      <img :src="book.book_cover" :alt="book.book_title" class="detail-cover">
    </div>
    <div class="col-6" style="float: right;">
      <div>
        <wordcloud
          :data="defaultWords"
          nameKey="name"
          valueKey="value"
          :color="Accent"
          :showTooltip="true"
          :wordClick="wordClickHandler">
        </wordcloud>
      </div>
    </div>
  </div>
</template>

<script>
import wordcloud from 'vue-wordcloud'

export default {
  props: {
    book: Object,
  },
  components: {
    wordcloud,
  },
  data() {
    return {
      myColors: ['#1f77b4', '#629fc9', '#94bedb', '#c9e0ef'],
      defaultWords: [{
          "name": "Cat",
          "value": 26
        },
        {
          "name": "fish",
          "value": 19
        },
        {
          "name": "things",
          "value": 18
        },
        {
          "name": "look",
          "value": 16
        },
        {
          "name": "two",
          "value": 15
        },
        {
          "name": "fun",
          "value": 9
        },
        {
          "name": "know",
          "value": 9
        },
        {
          "name": "good",
          "value": 9
        },
        {
          "name": "play",
          "value": 6
        }
      ]
    }
  },
  created() {
    console.log(this.book)
    const isbn = this.book.book_isbn
    this.$axios.get(`${this.$store.getters.getServer}/book/${isbn}`)
    .then(res => {
      console.log(res)
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
    width: 500px;
    height: 680px;
  }
</style>