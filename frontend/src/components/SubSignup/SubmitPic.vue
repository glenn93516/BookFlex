<template>
  <div>
    <h4>프로필 사진을 등록해주세요.</h4>
    <b-form-file
      v-model="profileImage"
      :state="Boolean(profileImage)"
      :placeholder="photoName"
      drop-placeholder="Drop file here..."
      style="margin-top: 30px; margin-bottom: 10px;"
    >
    </b-form-file>
      <!-- <div class="mt-3">Selected file: {{ profileImage ? profileImage.name : '' }}</div> -->

    <router-link
      class="btn btn-success btn-block" 
      :to="{ name: 'SubSignComplete' }"
    >
      확인
    </router-link>
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
    servePageInfo() {
      return this.pageData
    },
  }
}
</script>

<style>
  /* .custom-file-input:lang(ko) ~ .custom-file-label::after {
    content: 'B';
  } */
  .custom-file-label::after {
    content: 'B';
  }
  .img-btn {
    margin-top: 5.3rem;
  }
</style>