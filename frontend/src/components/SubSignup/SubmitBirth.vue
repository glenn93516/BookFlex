<template>
  <div>
    <h4>생년월일을 입력해주세요</h4>
    <b-form-datepicker 
      id="datepicker" 
      v-model="birthdate" 
      style="margin-top: 40px; margin-bottom: 40px;"
    >
    </b-form-datepicker>
    <b-button
      class="btn-success btn-block birth-btn" 
      @click="submitUserBirth(birthdate)"
      @keydown.enter="submitUserBirth(birthdate)"
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
      this.$router.push({ name: 'SubmitJob' })
    }
  },
}
</script>

<style>
  .gender-radio{
    display: inline-block;
  }
  .birth-btn {
    margin-top: 5.8rem;
  }
</style>