<template>
  <div @click="setIsbn">
    <img class="hvr-grow-shadow mouse-pointer" 
      width="80px" 
      height="100px" 
      :src="this.book.book_cover" 
      alt="" 
      style="margin-left:4px; margin-right:4px;" 
    >
    <div class="detail-title" style="text-align: center; size : 4rem; margin-top: 5px; height:30px">{{this.book.book_title}}</div>
  </div>
</template>

<script>
export default {
  props : {
    book : null,
  },
  methods: {
    setIsbn(isbn) {
      this.$emit("setIsbn", isbn)
    },
  },            
}
</script>

<style>
.hvr-grow-shadow {
  box-shadow: 5px 5px 10px -5px grey;
}
.detail-title {
  width: 80px;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}
</style>