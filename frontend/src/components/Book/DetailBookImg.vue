<template>
  <div style="margin-right: 12px; margin-bottom: 10px;">
    <div class="overlay-image" @click="setIsbn(book.book_isbn)">
      <img 
        width="90px" 
        height="120px" 
        :src="book.book_cover" 
        alt="" 
        style="cursor: pointer;"
      >
      <div class="hover" style="overflow: hidden;">
        <div class="text" style="width: 90px; height: 120px;">{{ book.book_title }}</div>
      </div>
    </div>
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