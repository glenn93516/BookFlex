<template>
  <div class="row">
    <div 
      class="col-6" 
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
        <h6 class="mr-1">도서 비교하기</h6>
        <hr style="rgba(50, 50, 50); margin-bottom: 10px;">
      </div>

      <Topic :isbn="this.$route.params.bookIsbn" @setIsbn="getIsbn" />
    </div>

    <div class="col-6">
      <!-- 키워드 분석 : 오른쪽 페이지 -->
      <div 
        style="text-align: right; 
                  margin-top: 20px; 
                  margin-right: 20px;
                  margin-bottom: 10px;
                  color: rgba(100, 100, 100);
                  ">
        <!-- x 아이콘 -->
        <div v-if="anotherIsbn && isShow">
          <div
            style="cursor: pointer; height: 19px;"
            @click="closeCompare"
          >
            <font-awesome-icon 
              style="height: 25px;  margin-top: -5px; margin-bottom: 5px;"
              class="mouse-pointer text-secondary bnt" 
              @mouseover="closeIcon = ['fas', 'times-circle']" 
              @mouseleave="closeIcon = ['far', 'times-circle']" 
              :icon="closeIcon" 
            />
          </div>
        </div>
        <!-- 검색하기 -->
        <div v-else>
          <h6 class="mr-1">검색하기</h6>
        </div>
        <hr style="rgba(50, 50, 50); margin-bottom: 10px;">
      </div>
      
      <!-- 책 상세정보(x 아이콘과 연동 - 상단바) -->
      <div v-if="anotherIsbn && isShow">
          <div style="margin-left: -5px;">
            <Topic :isbn="anotherIsbn" @setIsbn="getIsbn" style="margin-right: 20px;"/>
          </div>
      </div>
      <div v-else>
        <DetailSearch @setIsbn="getIsbn" />
      </div>
    </div>

  </div>
</template>

<script>
  import Topic from './Topic.vue'
  import DetailSearch from './DetailSearch.vue'

  export default {
    components: {
      Topic,
      DetailSearch,
    },
    data () {
      return {
        closeIcon: ['far', 'times-circle'],
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
        this.closeIcon = ['far', 'times-circle']
      }
    }
  }
</script>

<style>
</style>