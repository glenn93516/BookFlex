<template>
  <div>
    <img :src="imgURL" class="reactionBookImg" alt="북커버">
    <p>이 책은 어떠셨나요?</p>
    <img src="@/assets/thumbsup.png" style="height: 100px;" alt="">
    <img src="@/assets/thumbsdown.png" style="height: 100px;" alt="">
    <br>
    <b-button>문장 담기</b-button>

  </div>
</template>

<script>
export default {
  props: {
    imgURL: String,
  }
}
</script>

<style>
  .reactionBookImg {
    width: 200px;
    height: 300px;
  }
</style>