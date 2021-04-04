<template>
  <div class="row">
    <div 
      class="col-6 third-detail-cover-box" 
      style="height:700px; 
      border-right: 1px solid rgba(121, 121, 121, 0.692);
      "
    >
      <!-- 키워드 분석 : 왼쪽 페이지 -->
      <div 
        style="text-align: left; 
                  margin-top: 20px; 
                  margin-left: 20px;
                  margin-bottom: 10px;
                  color: rgba(100, 100, 100);
                  ">
        <h6 class="mr-1">키워드 분석</h6>
        <hr style="rgba(50, 50, 50); margin-bottom: 10px;">
      </div>

      <Topic :isbn="this.$route.params.bookIsbn" @setIsbn="getIsbn" />
    </div>

    <div class="col-6 third-detail-cover-box">
      <!-- 키워드 분석 : 오른쪽 페이지 -->
      <div 
        style="text-align: right; 
                  margin-top: 20px; 
                  margin-right: 20px;
                  margin-bottom: 10px;
                  color: rgba(100, 100, 100);
                  ">
        <h6 class="mr-1">키워드 보기</h6>
        <hr style="rgba(50, 50, 50); margin-bottom: 10px;">
      </div>

      <div v-if="anotherIsbn && isShow">
          <Topic :isbn="anotherIsbn" @setIsbn="getIsbn" style="margin-right: 20px;"/>
          <span
            style="cursor: pointer;
            position: absolute; 
            top:40px; 
            right:40px" 
            @click="closeCompare"
          >
          닫기
          </span>
      </div>
      <div v-else>
        <DetailSearch @setIsbn="getIsbn" />
      </div>
    </div>

  </div>
</template>

<script>
  import Topic from './Topics.vue'
  import DetailSearch from './DetailSearch.vue'

  export default {
    components: {
      Topic,
      DetailSearch,
    },
    data () {
      return {
        anotherIsbn: null,
        isShow: false,
      }
    },
    created() {
    },
    updated() { 
      console.log('updated'); 
      this.$nextTick(function () {  
        this.isShow = true
      }); 
    },
    methods: {
      getIsbn(isbn) {
        this.closeCompare()
        this.anotherIsbn = String(isbn)
      },
      closeCompare() {
        this.isShow = false
        this.anotherIsbn = null
      }
    }
  }
</script>

<style>

  .third-detail-cover-box {

  }

</style>