<template>
  <div>
    <div class="book-cover">
      <!--alt에 책 이름도 같이 바인딩 해주기-->
      <img class="bookimg" :src="imgSrc" alt="책표지">
      <!-- <img class="bookimg" src="https://i.picsum.photos/id/288/200/300.jpg?hmac=45WLionXnoogi0-njKuSNnVY5hnswMhf-CrxwzKTcrc" alt="책표지"> -->
      <!-- <img src="@/assets/booktag.png" width="60px;" style="margin-right: -60px; margin-top: 10px; margin-bottom: -60px; z-index: 10;" alt="booktag"> -->
    </div>
  </div> 
</template>

<script>
export default {
  props: ['imgSrc'],
}
</script>

<style>
  .book-cover{
    background-color: rgba(255, 255, 240, 0.733);
    padding: 15px;
  }
  .bookimg {
    width: 125px;
    height: 187.5px;
  }
</style>