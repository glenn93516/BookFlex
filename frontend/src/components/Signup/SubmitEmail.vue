<template>
  <div>
    <p class="noticeMessage">가입하실 이메일 주소를 입력해주세요.</p>
  <router-link :to="{ name: 'CheckEmail', params: { ProgressNum: 1 }}">확인</router-link>
  </div>
</template>

<script>
export default {
  data() {
    return {
      progress: 1,
    }
  },
  created() {

  },
  methods: {
    serveProgressData() {
      console.log(this.progress)
      console.log('this.progress')
      return this.progress
    }
  }
}
</script>

<style>
  .noticeMessage {
    color: rgb(108, 160, 29);
    margin-top: 20px;
  }
</style>