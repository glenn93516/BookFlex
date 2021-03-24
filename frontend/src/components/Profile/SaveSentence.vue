<template>
  <div>
    <ul>
      <div class="sentence-list">
        <div class="sentence-item">
          <img width="340px" height="250px" src="https://cdn.pixabay.com/photo/2021/03/16/15/30/beach-6100209_960_720.jpg" alt="">
        </div>
        <div class="sentence-item">
          <img width="340px" height="250px" src="https://cdn.pixabay.com/photo/2021/03/03/07/09/background-6064645_960_720.jpg" alt="">
        </div>
        <div class="sentence-item">
          <img width="340px" height="250px" src="https://cdn.pixabay.com/photo/2021/01/30/18/07/salzburg-5964812_960_720.jpg" alt="">
        </div>
      </div>
    </ul>
    <div class="more">
      <hr class="more-line">
      <div class="more-word">더보기</div>
    </div>
  </div>
</template>

<script>
export default {

}
</script>

<style>
  .sentence-list {
    display: flex;
    justify-content: space-between;
    margin: 30px 0;
  }
  .sentence-item {
    display: absolute;
    z-index: 1;
    color: red;
  }
  .sentence-item:hover {
    cursor: pointer;
    background-color: black;
  }
  .more {
    display: flex;
    justify-content: center;
    align-items: center;
  }
  .more-line {
    display: absolute;
    width: 300px;
    left: 100px;
  }
  .more-word {
    display: absolute;
    left: 10px;
  }
</style>