<template>
  <div>
    <h1>Readbookd</h1>
    <div>
      <img src="" alt="">
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      readBooks: [],
    }
  },
  mounted() {
    this.getBookData()
  },
  methods: {
    // loadBookData() {
    //   const token = this.$store.getters.getAccessToken
    //   if (token != undefined) {
    //     this.getBookData()
    //   } else {
    //     this.getBookData()
    //   }
    // },
    getBookData() {
      this.$axios.get(`${this.$store.getters.getServer}/book`)
      .then(res => {
        let bookList = []
        bookList.append('1')
        for (let i=0; i<20; i++) {
          if (res.data.data[i].book_cover !== undefined) {
            bookList.append(res.data.data[i].book_cover)
          }
        }
        // console.log(bookList.data[5], typeof(bookList))
        // console.log(bookList.slice(0, 4))
        // this.readBooks = res.data.data.slice(0, 4)
      })
      console.log(this.readBooks, 'readBookds')
    }
  }
}
</script>

<style>

</style>