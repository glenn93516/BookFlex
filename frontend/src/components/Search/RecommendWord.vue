<template>
  <div>
    <h2><strong>추천 검색어</strong></h2>
    <div v-if="item==null">
      <h4 style="text-align: center; ">😥추천 검색어가 없습니다.</h4>
    </div>
    <div v-if="item!=null">
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