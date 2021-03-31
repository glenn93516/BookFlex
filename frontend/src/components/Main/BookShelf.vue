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
    transform: skew(-55deg, 0);
    -webkit-transform:skew(-65deg, 0);
    margin-top: -20px;
    background-color: rgb(133, 108, 97); 
    border-radius: 50px; 
    height: 30px; 
    width: 1000px; 
    display: inline-block;
    box-shadow: 3px 3px 3px rgb(61, 45, 45);
  }
</style>