<template>
  <div>
    <b-form-input
      v-model="name"
      class="input"
      style="border: 0;
      border-bottom: 1px solid;
      border-radius: 0;
      "
      placeholder="필명 (8자리 이내)"
    >
    </b-form-input>
    <div style="color: red; margin-bottom: -24px;">
      필명을 입력해주세요
    </div>
    <b-form-input
      v-model="password"
      class="input"
      style="border: 0;
      border-bottom: 1px solid;
      border-radius: 0;
      "
      placeholder="비밀번호 (8자리 이상, 영문 숫자 혼합 필수)"
    >
    </b-form-input>
    <div style="color: red; margin-bottom: -24px;">
      비밀번호는 8자리 이상, 영문 숫자를 혼합해주세요
    </div>
    <b-form-input
      v-model="confirmPassword"
      class="input"
      style="border: 0;
      border-bottom: 1px solid;
      border-radius: 0;
      "
      placeholder="비밀번호 확인"
    >
    </b-form-input>
    <div style="color: red; margin-bottom: -24px;">
      비밀번호와 일치하지 않습니다
    </div>
    <!-- <b-button
      block
      variant="success"
      @click="sendName"
    >
      확인
    </b-button> -->
    <router-link :to="{ name: 'SubmitPref' }" class="btn btn-primary btn-block">다음으로 넘어가기(임시)</router-link>
  </div>
</template>

<script>
export default {
  data() {
    return {
      progressNum: 2,
    }
  },
  methods: {
    serveProgressData() {
      console.log("여기는 인풋폼")
      return this.progressNum
    },
  }
}
</script>

<style>

</style>