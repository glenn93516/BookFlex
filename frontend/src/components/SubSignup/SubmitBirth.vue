<template>
  <div>
    <h4 style="margin-top: 5px;">생년월일을 입력해주세요</h4>

    <b-form-datepicker 
      id="datepicker" 
      v-model="birthdate"
      class="birth-datepicker"
      placeholder="날짜를 선택해주세요"
    >
    </b-form-datepicker>

    <b-button
      @click="submitUserBirth(birthdate)"
      @keydown.enter="submitUserBirth(birthdate)"
      block
      class="btn-success birth-btn login-button" 
    >
      확인
    </b-button>
  </div>
</template>

<script>
export default {
  data() {
    return {
      birthdate: '',
      pageData: {
        progress: 1,
        size: "back-md"
      },
    }
  },
  methods: {
    servePageInfo() {
      return this.pageData
    },
    submitUserBirth(birthday) {
      this.$store.dispatch("SubmitUserBirth", birthday)
      this.goToSubmitJob()
    },
    goToSubmitJob() {
      console.log('담겼나?', this.$store.getters.getUser)
      this.$router.push({ name: 'SubmitJob' })
    }
  },
}
</script>

<style>
  .birth-datepicker {
    margin-top: 40px; 
    margin-bottom: 40px;
  }
  #datepicker__dialog_ {
    background-color: white;
  }
  .birth-btn {
    margin-top: 5rem;
  }
</style>