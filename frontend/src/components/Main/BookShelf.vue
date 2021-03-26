<template>
  <div class="book-shelf">
    <!-- <img width="1000px" height="30px" src="@/assets/wood_texture.jpg" alt="" style="border-radius: 10px;"> -->
    <div class="book-shelf-bot"></div>
  </div>
</template>

<script>
export default {

}
</script>

<style>
  .book-shelf{
    text-align: center; 
    margin: 0 auto;
    z-index: 1;
  }
  .book-shelf-bot {
    transform: skew(-45deg, 0);
    -webkit-transform:skew(-45deg, 0);
    margin-top: -30px;
    background-color: rgb(112, 87, 76); 
    border-radius: 10px; 
    height: 30px; 
    width: 1000px; 
    display: inline-block;
  }
</style>