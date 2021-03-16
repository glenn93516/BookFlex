<template>
  <div>
        <h4>프로필 사진을 등록해주세요.</h4>
        <b-form-file
          v-model="profileImage"
          :state="Boolean(profileImage)"
          placeholder="Choose a file or drop it here..."
          drop-placeholder="Drop file here..."
        ></b-form-file>
          <div class="mt-3">Selected file: {{ profileImage ? profileImage.name : '' }}</div>

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
    }
  },
  methods: {
    servePageInfo() {
      return this.pageData
    },
  }
}
</script>

<style>

</style>