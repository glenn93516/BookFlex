<template>
  <div style="width:170px;">
    <img class="hvr-grow-shadow mouse-pointer" width="150px" height="200px" :src="this.book.book_cover" alt="" style="margin-left:10px; margin-right:10px;" @click="clickImg" >
    <div style="text-align: center; size : 8rem; margin-top: 10px;">{{this.book.book_title}}</div>
  </div>
</template>

<script>
export default {
  props : {
    book : null,
  },
  methods: {
    clickImg(){
      this.$router.push({ name: 'MainBook' })
    }
  },            
}
</script>

<style>
.hvr-grow-shadow {
  box-shadow: 5px 5px 10px -5px grey;
}
</style>