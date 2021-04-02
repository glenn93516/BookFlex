<template>
  <div>
    <div v-if="user!=null" style="justify-content: center; display: inline-block; width: 128px;">
      <b-avatar 
      :src="this.user.userProfileImg" 
      size="8rem"
      class="profile-left"
      button @click="clickImg()"
      style="margin-top: 20px;"
      ></b-avatar>
      <div style="text-align: center; size : 8rem; margin-top: 10px;">{{this.user.userNickname}}</div>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    user: null,
  },
  data() {
    return {
    }
  },
  mounted() {
    // this.profileImg = this.item.userProfileImg,
    // this.profileNickname = this.item.userNickname
  },
  methods: {
    clickImg(){
      alert('이미지 클릭')
    }
  },
}
</script>

<style>
/* .profile-left {
    display: inline-block;
    margin: 30px 30px 30px 0px;
} */
</style>