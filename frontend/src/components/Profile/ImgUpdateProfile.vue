<template>
  <div>
    <header class="update-header">
      프로필 수정
    </header>
    <hr class="update-hr">
    <div>
      <div class="update-profile-image">
        <!-- 기존 이미지 -->
        <b-avatar
          v-if="!uploadImg"
          :src="profileImg"
          size="10rem"
          class="updateProfile-left"
          variant="light"
        >
        </b-avatar>
        <!-- 이미지 업데이트 했을 때 -->
        <b-avatar
          v-if="uploadImg"
          :src="uploadImg"
          size="10rem"
          class="updateProfile-left"
          variant="light"
        >
        </b-avatar>
        <!-- reset img -->
        <!-- <b-avatar size="10rem" v-if="resetImg" src="" class="updateProfile-left"></b-avatar> -->
        <div class="upload-image">
          <input ref="imageInput" type="file" hidden @change="onChangeImages">
          <b-button variant="warning" @click="onClickImageUpload">이미지 업로드</b-button>
          <!-- <b-button variant="primary" @click="resetImage">기본 이미지로 변경</b-button> -->
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    profileImg: String,
  },
  data() {
    return {
      uploadImg: null,
      // resetImg: false,
    }
  },
  created() {
    const User = this.$store.getters.getUser
    console.log(User)
  },
  methods: {
    onClickImageUpload() {
      this.$refs.imageInput.click()
    },
    onChangeImages(e) {
      console.log(e.target.files)
      const file = e.target.files[0]
      this.uploadImg = URL.createObjectURL(file)
      this.submitImage(file)
    },
    // resetImage() {
    //   this.resetImg = true
    //   this.$refs.imageInput.value=null
    //   this.completeChange()
    //   // const User = this.$store.getters.getUserImg
    //   // const UserFile = this.$store.getters.getUserImgFile
    //   // console.log(User,  'FIKE1')
    //   // console.log(UserFile, 'FIKE2')
    //   // this.$store.dispatch('DeleteProfileImage')

    //   // const file = new File(["name"], "no-image",)
    //   // console.log('resetImage')
    //   // console.log(file, 'file')
    //   // this.$store.dispatch('SubmitUserPic', file)
    //   // this.completeChange()
    // },
    submitImage(Img) {
      this.$store.dispatch('SubmitUserPic', Img)
      this.completeChange()
    },
    completeChange() {
      const User = this.$store.getters.getUser
      this.$store.dispatch('UpdateUserInfo', User)
    },
  },
}
</script>

<style>
  .update-header {
    text-align: center; 
    font-weight: bold; 
    font-size: 30px;
  }
  .update-hr {
    margin: 10px 0;
  }
  .update-profile-image {
    display: flex; 
    justify-content: space-between; 
    align-items: center;
    margin: 20px 10px;
  }
  .upload-image {
    display: inline-block;
    width: 100%;
    text-align: center; 
  }
  .update-title {
    margin-left: 10px;
    font-weight: bold;
    font-size: 20px;
  }
</style>