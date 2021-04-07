<template>
  <div>
    <!-- <div id="pointer-pink" class="pointer"> -->
    <h2 class="result-title">USER</h2>
    <!-- </div> -->
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
  #pointer-pink {
    width: 140px;
    height: 40px;
    position: relative;
    background: rgba(146, 208, 80);
    align-items: center;
  }
  .pointer:after {
    content: "";
    position: absolute;
    left: 0;
    bottom: 0;
    width: 0;
    height: 0;
    border-left: 20px solid rgba(237, 234, 232, 1);
    border-top: 20px solid transparent;
    border-bottom: 20px solid transparent;
    align-items: center;
  }
  #pointer-pink:before {
    content: "";
    position: absolute;
    right: -20px;
    bottom: 0;
    width: 0;
    height: 0;
    border-left: 20px solid rgba(146, 208, 80);
    border-top: 20px solid transparent;
    border-bottom: 20px solid transparent;
    align-items: center;
  }
  .result-title {
    font-weight: bold;
    /* margin-left: 30px; */
    /* line-height: inherit; */
  }
</style>