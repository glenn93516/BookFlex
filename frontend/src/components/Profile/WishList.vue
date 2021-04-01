<template>
  <div 
    style="
      background-color: rgba(255, 255, 255, 0.5);
      border-radius: 20px;
  ">
    <div style="display: flex;">
      <div v-for="book in wishLists" :key="book.index" style="padding-right: 20px;" class="mouse-pointer">
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
        console.log(res.data.data, '유저 읽음 res.data.data')
        this.wishLists = res.data.data
      })
    }
  }
}
</script>

<style>

</style>