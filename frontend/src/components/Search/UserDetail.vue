<template>
  <div>
    <b-avatar 
    :src="profileImg" 
    size="10rem"
    class="profile-left"
    @click="clickImg"
    ></b-avatar> 
  </div>
</template>

<script>
export default {
  props: {
    userData: null,
  },
  data() {
    return {
      profileImg : null,
      profileNickname : null
    }
  },
  mounted() {
    this.profileImg = this.item.userProfileImg,
    this.profileNickname = this.item.userNickname
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