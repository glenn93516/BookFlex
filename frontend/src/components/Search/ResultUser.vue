<template>
  <div>
    <h2><strong>검색 결과 - USER</strong></h2>
    <div v-if="item==null">
      <h4 style="text-align: center; ">검색된 결과가 없습니다.</h4>
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