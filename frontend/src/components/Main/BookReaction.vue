<template>
  <div class="bookReaction">
    <img :src="book.book_cover" class="reactionBookImg" :alt="book.book_title">
    <h3>이 책은 어떠셨나요?</h3>
    <div>
      <img src="@/assets/thumbsup.png" class="btn thumbs" alt="">
      <img src="@/assets/thumbsdown.png" class="btn thumbs" alt="">
    </div>
    <br>
    <b-button class="btn-put" @click="$emit('go-collect')">문장 담기</b-button>

  </div>
</template>

<script>
export default {
  props: {
  },
  data() {
    return {
      book: "",
    }
  },
  // 책 객체 뽑아오기 => 이미지 url 가져오기
  created() {
    this.getSelectedBook()
  },
  methods: {
    getSelectedBook() {
      this.book = this.$store.getters.getSelectedBook
    }
  }
}
</script>

<style>
  .bookReaction {
    text-align: center;
  }
  .reactionBookImg {
    height: 300px;
    width: 200px;
    margin-bottom: 35px;
  }
  img[class$="thumbs"] {
    height: 120px;
    margin: 30px 130px 80px 130px;
    filter: drop-shadow(1px 1px 1px rgba(0, 0, 0, 0.753));
  }
  img[class$="thumbs"]:hover {
    filter: drop-shadow(3px 3px 4px rgba(0, 0, 0, 0.5));
    transition: 0.1s;
  }
  .btn-put {
    background-color: rgba(255, 0, 128, 0.25);
    font-size: 20px;
  }
  
</style>