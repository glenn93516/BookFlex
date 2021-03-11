<template>
  <div>
    <p class="noticeMessage">가입하실 이메일 주소를 입력해주세요.</p>
    <b-form-input
      v-model="email"
      class="id-input"
      style="border: 0;
      border-bottom: 1px solid;
      border-radius: 0;
      "
      placeholder="이메일 (example@gmail.com)"
    >
    </b-form-input>
    <div style="color: red; margin-bottom: -24px;" v-show="isVisible">
      이메일 양식이 올바르지 않습니다.
    </div>
    <br>
    <router-link
      class="btn btn-primary btn-block" 
      :to="{ name: 'CheckEmail' }"
    >
      확인
    </router-link>
  </div>
</template>

<script>
export default {
  data() {
    return {
      progress: 0,
    }
  },
  created() {

  },
  methods: {
    serveProgressData() {
      console.log('여기는 서브밋')
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
  .id-input[type="text"]:focus {
  box-shadow: 0 0px 0px rgba(0, 0, 0, 0.075);
  outline: 0 none;
  }
</style>