<template>
  <div 
    style="
      background-color: rgba(255, 255, 255, 0.5);
      border-radius: 20px;
  ">
    <div style="display: flex;">
      <div v-for="book in readBooks" :key="book.index" style="padding-right: 20px;" class="mouse-pointer">
        <img width="150px" height="200px" :src="book.book_cover" alt="">
      </div>
    </div>
    <!-- 책 선반 -->
    <!-- <div 
      id="bookBottom"
      style="
        background-color: red;
        width: 1108px;
        height: 20px;
        border-radius: 10px;
      "
    >
    </div> -->
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
    // loadBookData() {
    //   const token = this.$store.getters.getAccessToken
    //   if (token != undefined) {
    //     this.getBookData()
    //   } else {
    //     this.getBookData()
    //   }
    // },
    getBookData() {
      this.$axios.get(`${this.$store.getters.getServer}/bookshelf/${this.$route.params.userName}`)
      .then(res => {
        console.log(res.data.data, '유저 읽음 res.data.data')
        this.readBooks = res.data.data
        // let bookList = []
        // bookList.append('1')
        // for (let i=0; i<20; i++) {
        //   if (res.data.data[i].book_cover !== undefined) {
        //     bookList.append(res.data.data[i].book_cover)
        //   }
        // }
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