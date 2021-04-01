<template>
  <div>
    <h2><strong>검색 결과 - USER</strong></h2>
    <div v-if="userData==null">
      <h4 style="text-align: center; ">😥검색된 결과가 없습니다.</h4>
    </div>
    <div v-if="userData!=null">
      <user-detail v-bind:user="userData"></user-detail>
    </div>
  </div>
</template>

<script>
import UserDetail from './UserDetail.vue'
export default {
  components: { 
    UserDetail 
  },
  props: {
    userData: null,
  }
}
</script>

<style>

</style>