<template>
  <div>
    <h4>프로필 사진을 등록해주세요.</h4>
    <div 
      style="
        text-align: center; 
        margin-top: 2rem; 
        display: flex; 
        justify-content: space-around; 
        align-items: center;
      "
    >
      <b-avatar
        :src="profileImageLink"
        size="7rem"
        variant="light"
        style="margin-left: 2.3rem;"
      >
      </b-avatar>
      <div class="upload-image" style="width: 100%;">
        <input ref="imageInput" type="file" hidden @change="onChangeImages">
        <b-button style="padding: 0.5rem 1.5rem;" variant="warning" @click="onClickImageUpload">이미지 업로드</b-button>
      </div>
    </div>


    <b-button
      block
      class="btn-success profileImg-btn login-button" 
      @click="submitUserPic(profileImage)"
      @keydown.enter="submitUserPic(profileImage)"
      style="margin-top: 2.3rem;"
    >
      확인
    </b-button>
  </div>
</template>

<script>
export default {
  data() {
    return {
      pageData: {
        progress: 3,
        size: "back-md"
      },
      profileImageLink: null,
      profileImage: null,
      photoName: "사진을 선택해주세요."
    }
  },
  watch: {
    profileImage() {
      if (this.profileImage != null) {
        this.photoName = this.profileImage.name
      } else {
        this.photoName = "사진을 선택해주세요."
      }
    },
  },
  methods: {
    onClickImageUpload() {
      this.$refs.imageInput.click()
    },
    onChangeImages(e) {
      // console.log(e.target.files)
      const file = e.target.files[0]      
      this.profileImageLink = URL.createObjectURL(file)
      this.profileImage = file
    }, 
    servePageInfo() {
      return this.pageData
    },
    submitUserPic() {
      this.$store.dispatch('SubmitUserPic', this.profileImage)
      this.goToComplete()
    },
    goToComplete() {
      // console.log('담겼나?', this.$store.getters.getUser)
      this.$router.push({ name: "SubSignComplete" })
    }
  }
}
</script>

<style>
  .submit-PImg {
    margin-top: 1rem; 
    margin-bottom: 10px;
  }
  .profileImg-btn {
    margin-top: 0.5rem;
  }
</style>