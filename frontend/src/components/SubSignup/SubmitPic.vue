<template>
  <div>
        <p class="describeP">추가 항목은 프로필 수정 페이지에서 추가 입력 / 수정이 가능합니다.</p>
        <h4>프로필 사진을 등록해주세요.</h4>
        <b-form-file
          v-model="profileImage"
          :state="Boolean(profileImage)"
          placeholder="Choose a file or drop it here..."
          drop-placeholder="Drop file here..."
        ></b-form-file>
          <div class="mt-3">Selected file: {{ profileImage ? profileImage.name : '' }}</div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      progressNum: 3,
      profileImage: null,
    }
  },
  methods: {
    serveProgressData() {
      console.log("여기는 프로필 입력")
      return this.progressNum
    },
  }
}
</script>

<style>

</style>