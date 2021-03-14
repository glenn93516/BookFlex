<template>
  <div>
    <h4>생년월일을 입력해주세요</h4>
    <b-form-datepicker 
      id="datepicker" 
      v-model="birthdate" 
      style="margin-top: 40px; margin-bottom: 40px;"
    >
    </b-form-datepicker>
    <router-link
      class="btn btn-success btn-block birth-btn" 
      :to="{ name: 'SubmitJob' }"
    >
      확인
    </router-link>
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
  }
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