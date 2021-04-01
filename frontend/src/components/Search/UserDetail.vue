<template>
  <div>
    <b-avatar 
    :src="item.userProfileImg" 
    size="10rem"
    class="profile-left"
    @click="clickImg"
    ></b-avatar> 
  </div>
</template>

<script>
export default {
  props: {
    item: Object,
  },
  data() {
    return {
      
    }
  },
  methods: {
    clickImg(){
      alert('이미지 클릭')
    }
  },
}
</script>

<style>
.profile-left {
    display: inline-block;
    margin: 30px 30px 30px 0px;
}
</style>