<template>
  <div>
      <!-- 이미지 넘기는거 취소하기 -->
    <div class="book-cover btn book" @click="$emit('open-Modal', book)">
      <!--alt에 책 이름도 같이 바인딩 해주기-->
      <img v-if="book" class="bookimg" :src="book.book_cover" :alt="book.book_title">
      <!-- <img class="bookimg" src="https://i.picsum.photos/id/288/200/300.jpg?hmac=45WLionXnoogi0-njKuSNnVY5hnswMhf-CrxwzKTcrc" alt="책표지"> -->
      <!-- <img src="@/assets/booktag.png" width="60px;" style="margin-right: -60px; margin-top: 10px; margin-bottom: -60px; z-index: 10;" alt="booktag"> -->
    </div>
  </div> 
</template>

<script>
export default {
  props: {
    book: Object,
  },
  mounted() {
  },
  methods: {
  }
}
</script>

<style>
  .book-cover{
    background-color: rgba(0, 0, 0, 0.4);
    padding: 15px;
    box-shadow: 0px 0px 1px rgba(0, 0, 0, 0.2); 
    transition-duration: 0.3s;
    z-index: 10;
    position: relative;
  }
  .book-cover:hover {
    box-shadow: 0px 0px 8px rgba(143, 143, 143, 0.8);
    border-radius: 5px/ 5px;
    z-index: 10;
  }
  .bookimg {
    width: 125px;
    height: 187.5px;
    z-index: 10;
  }
</style>