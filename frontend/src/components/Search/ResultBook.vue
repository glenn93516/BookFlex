<template>
  <div>
    <h2><strong>검색 결과 - Book</strong></h2>
    <br>
    <div v-if="item==null"></div>
      <h4 style="text-align: center; ">검색된 결과가 없습니다.</h4>
    <div v-if="item!=null">
      <h4>도서명 검색 결과</h4>
      <br>
      <h4>작가명 검색 결과</h4>
      <br>
      <h4>내용 검색 결과</h4>
      <br>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    item: Object,
    }
}
</script>

<style>

</style>