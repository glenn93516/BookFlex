<template>
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
      :color="([, weight]) => weight > 4 ? 'DeepPink' : weight > 2 ? 'Red' : weight > 1 ? 'RoyalBlue' : 'Indigo'"
      font-family="Anton"
      font-weight="Bold"
      @click="wordClickHandler(word)"
    />
  </div>
</template>

<script>
import VueWordCloud from 'vuewordcloud';

export default {
  props: {
    isbn: String,
  },
  components: {
    VueWordCloud
  },
  data() {
    return {
      words: [],
    }
  },
  created() {
    this.getWordCloud(this.isbn)
  },
  methods: {
    wordClickHandler(name, value, vm) {
      console.log('wordClickHandler', name, value, vm);
    },
    getWordCloud(isbn) {
      this.$axios.get(`${this.$store.getters.getServer}/book/${isbn}/wordcloud`)
      .then(res => {
        console.log(res.data.data)
        this.words = res.data.data.slice(0, 30)
      })
      .catch(err => {
        console.error(err)
      })
    }
  }

}
</script>

<style>

</style>