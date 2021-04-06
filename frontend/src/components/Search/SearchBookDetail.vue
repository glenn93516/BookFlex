<template>
  <div style="width:170px;">
    <img class="hvr-grow-shadow mouse-pointer" 
      width="150px" 
      height="200px" 
      :src="this.book.book_cover" 
      alt="" 
      style="margin-left:10px; margin-right:10px;" 
      @click="clickImg" >
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
      if(this.book.book_isbn==undefined){
        this.$router.push({ name: 'FirstPage', params: { bookIsbn: String(this.book.isbn) } })
      }
      else{
        this.$router.push({ name: 'FirstPage', params: { bookIsbn: String(this.book.book_isbn) } })
      }
    },
  },
  // mounted() {
  //   console.log('props book >> ', this.book.book_isbn)
  // },         
}
</script>

<style>
.hvr-grow-shadow {
  box-shadow: 5px 5px 10px -5px grey;
}
</style>