<template>
  <div id="readbook">
    <div>
      <div 
        v-for="book in readBooks" 
        :key="book.index"
        class="readbook-item" 
      >
        <img 
          width="200px" 
          height="270px" 
          :src="book.book_cover" 
          alt="" 
          class="hvr-grow-shadow mouse-pointer"
          @click="clickReadBook(book.book_isbn)"
        >
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      readBooks: [],
      displayImg: false,
    }
  },
  mounted() {
    this.getBookData()

  },
  methods: {
    getBookData() {
      this.$axios.get(`${this.$store.getters.getServer}/bookshelf/${this.$route.params.userName}`)
      .then(res => {
        console.log(res.data.data, '유저 읽음 res.data.data')
        this.readBooks = res.data.data
      })
      console.log(this.readBooks, 'readBookds')
    },
    clickReadBook(isbn) {
      // alert('읽은 책입니다(～￣▽￣)～✨')
      this.$router.push({ name: 'FirstPage', params: { bookIsbn: isbn } })
    },
  }
}
</script>

<style>
#readbook {
  display: inline-block;
  border-radius: 20px;
  width: 1110px;
}
.readbook-item {
  display: inline-block; 
  margin-left: 27px;
  margin-top: 30px;
  margin-bottom: 1rem;
}
#readbook div:nth-child(5n+1) {
  margin-left: 0;
} 
.readbook-item > img {
  box-shadow: 5px 5px 10px -5px grey;
}
</style>