<template>
  <div class="row">
    <div 
      class="col-6" 
      style="height:700px; 
      border-right: 1px solid rgba(121, 121, 121, 0.692);"
    >
      <Topic :isbn="this.$route.params.bookIsbn" @setIsbn="getIsbn" />
    </div>

    <div class="col-6">
      <div v-if="anotherIsbn && isShow">
          <Topic :isbn="anotherIsbn" @setIsbn="getIsbn" />
          <h4 style="position: absolute; top:0px; right:20px" @click="closeCompare">닫기</h4>
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