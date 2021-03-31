<template>
  <div class="selectStatus">
    <img class="book-img" :src="book.book_cover" :alt="book.book_title">
    <br>
    <hr>
    <div>
      <b-button class="btn-select-read" @click="$emit('go-reaction')">읽음</b-button>
      <b-button class="btn-select-more">더보기</b-button>
      <b-button class="btn-select-wish">위시리스트에 담기</b-button>  
    </div>
  </div>
</template>

<script>
export default {
  props: {
    book: Object,
  },
  data() {
    return {
    }
  },
  created() {
  },
  methods: {
  }
}
</script>

<style>
  .book-img {
    height: 450px;
    width: 300px;
  }
  .selectStatus {
    text-align: center;
  }
  .btn-select-read {
    background-color: rgba(26, 163, 255, 0.56);
    font-size: 20px;
  }
  .btn-select-more {
    background-color: rgba(255, 204, 0, 0.56);
    font-size: 20px;
  }
  .btn-select-wish {
    background-color: rgba(255, 0, 0, 0.34);
    font-size: 18px;
  }
  button[class*="btn-select"] {
    margin-left: 20px;
    margin-right: 20px;
    color: black;
    border: 0px;
    border-radius: 30px/ 30px;
    height: 60px;
    width: 175px;
  }
  button[class*="btn-select"]:hover {
    background-color: rgba(68, 36, 22, 0.8);
  }
</style>