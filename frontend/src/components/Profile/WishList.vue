<template>
  <div id="wishlist">
    <div>
      <div 
        v-for="book in wishLists" 
        :key="book.index" 
        class="readbook-item"
      >
        <img 
          width="200px" 
          height="270px" 
          :src="book.book_cover" 
          alt=""
          class="hvr-grow-shadow mouse-pointer"
          @click="clickWishBook()"
        >
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      wishLists: [],
      displayImg: false,
    }
  },
  mounted() {
    this.getBookData()

  },
  methods: {
    getBookData() {
      const token = localStorage.getItem('jwt')
      const headers = {
        'Authorization': token
      }
      this.$axios.get(`${this.$store.getters.getServer}/wishlist`, {headers})
      .then(res => {
        console.log(res)
        console.log(res.data.data, '위시리스트')
        this.wishLists = res.data.data
      })
    },
    clickWishBook() {
      alert('위시리스트 책입니닷')
    },
  }
}
</script>

<style>
#wishlist {
  display: inline-block;
  border-radius: 20px;
  width: 1110px;
}
.readbook-item {
  display: inline-block; 
  margin-left: 27px;
  margin-top: 30px;
}
#wishlist div:nth-child(5n+1) {
  margin-left: 0;
} 
.readbook-item > img {
  box-shadow: 5px 5px 10px -5px grey;
}
</style>