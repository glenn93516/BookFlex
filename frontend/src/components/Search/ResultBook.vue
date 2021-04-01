<template>
  <div>
    <h2><strong>검색 결과 - BOOK</strong></h2>
    <br>
    <div v-if="item==null">
      <h4 style="text-align: center; ">😥검색된 결과가 없습니다.</h4>
    </div>
    <div v-if="item!=null">
      {{this.item.titleData}}
      <!-- <h4>도서명 검색 결과</h4>
      <br>
      <h4>작가명 검색 결과</h4>
      <br>
      <h4>내용 검색 결과</h4>
      <br> -->
    </div>
  </div>
</template>

<script>
export default {
  props: {
    item: null,
  },
  // computed(){
  //   if(this.item!=null){
  //     console.log(this.item);
  //   }
  // }
}
</script>

<style>

</style>