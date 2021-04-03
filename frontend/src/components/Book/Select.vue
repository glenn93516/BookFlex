<template>

  <div>
    <h2>비교하기</h2>
    <h3>추천도서</h3>
    <div class="row">
      <div v-for="(recommend, idx) in recommends" :key="idx">
        <div class="col-2">
          <img width="60px" :src="recommend.book_cover" alt="" @click="setIsbn(recommend.book_isbn)">
        </div>
      </div>
    </div>

    <h3>도서검색</h3>
    <!-- 검색바 -->
    <SearchBar/>
    <!-- 검색결과 -->
    
  </div>

</template>

<script>
  import SearchBar from './SearchBar.vue'

  export default {
    components: {
      SearchBar,
    },
    props: {
      isbn: {
        type: String,
      },
    },
    data () {
      return {
        recommends: [],
      }
    },
    created() {
      this.getRecommend()
    },
    methods: {
      getRecommend() {
        this.$axios.get(`${this.$store.getters.getServer}/book/${this.isbn}/recommend`)
        .then(res => {
          this.recommends = res.data.data.customized_by_book.slice(0, 6)
        })
        .catch(err => {
          console.error(err)
        })
      },
      getSearch() {
        this.$axios.get(`${this.$store.getters.getServer}/book`)
        .then(res => {
          this.bookInfo = res.data.data
        })
        .catch(err => {
          console.error(err)
        })
      },

      setIsbn(isbn) {
        this.$emit("setIsbn", isbn)
      }
    },
  }
</script>