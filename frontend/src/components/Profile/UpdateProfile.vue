<template>
  <div>
    <header class="update-header">
      정보 수정
    </header>
    <hr class="update-hr">
    <div class="update-genre">
      <header class="update-title">직업군</header>

    </div>
    <div class="update-birth">
      <header class="update-title">생년월일</header>
      <b-form-datepicker></b-form-datepicker>

    </div>
    <div class="update-gender">
      <header class="update-title">성별</header>
      <!-- <checkbox></checkbox> -->

    </div>
    <div class="update-password">
      <header class="update-title">비밀번호 초기화</header>
      <!-- <checkbox></checkbox> -->

    </div>
    <div class="delete-info">
      <header class="update-title">회원탈퇴</header>
      <!-- <checkbox></checkbox> -->

    </div>
  </div>
</template>

<script>
// import Checkbox from '@/components/Element/Checkbox.vue'
export default {
  // components: { Checkbox },
  props: {
  },
  data() {
    return {
      option: null,
      // resetImg: false,
    }
  },
  created() {
    const User = this.$store.getters.getUser
    console.log(User)
  },
  // mounted() {
  //   this.getGenreInfo()
  // },
  methods: {
    completeChange() {
      const User = this.$store.getters.getUser
      this.$store.dispatch('UpdateUserInfo', User)
    },
    // getGenreInfo() {
    //   this.$axios.get(`${this.$store.getters.getServer}/genre`)
    //   .then(res => {
    //     console.log(res.data.data)
    //     this.option = res.data.data
    //   })
    // }
  },
}
</script>

<style>
  .update-header {
    text-align: center; 
    font-weight: bold; 
    font-size: 30px;
  }
  .update-hr {
    margin: 10px 0;
  }
  .update-title {
    margin-left: 10px;
    font-weight: bold;
    font-size: 20px;
  }
</style>