<template>
  <div>
    <h2><strong>검색 결과 - USER</strong></h2>
    <div v-if="userData==null">
      <h4 style="text-align: center; ">😥검색된 결과가 없습니다.</h4>
    </div>
    <div v-if="userData!=null">
      <search-user-detail v-bind:user="userData"></search-user-detail>
    </div>
  </div>
</template>

<script>
import SearchUserDetail from './SearchUserDetail.vue'
export default {
  components: { 
    SearchUserDetail 
  },
  props: {
    userData: null,
  }
}
</script>

<style>

</style>