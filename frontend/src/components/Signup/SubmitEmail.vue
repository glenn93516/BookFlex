<template>
  <div>
    <p class="noticeMessage">가입하실 이메일 주소를 입력해주세요.</p>
    <b-form-input
      v-model="email"
      id="id-input"
      class="id-input"
      :class="emailStatus"
      style="border: 0;
      border-bottom: 1px solid;
      border-radius: 0;
      "
      placeholder="이메일 (example@gmail.com)"
    >
    </b-form-input>
    <div style="color: red; margin-bottom: -24px;" v-show="emailStatus === 'is-invalid'">
      이메일 양식이 올바르지 않습니다.
    </div>
    <br>
    <!-- <div @click="setEmail">이거는</div> -->
    <b-button
      id="next-btn"
      :class="btnStatus" 
      @click="setEmail"
    >
      확인
    </b-button>
  </div>
</template>

<script>
let emailValid = "btn btn-success btn-block"
let emailInvalid = "btn btn-success btn-block disabled"
export default {
  data() {
    return {
      pageData: {
        progress: 0,
        size: "back-sm"
      },
      email: "",
      // 현재 이메일이 유효한지 아닌지 (is-valid, is-invalid)
      emailStatus: "",
      btnStatus: emailInvalid,
    }
  },
  created() {

  },
  watch: {
    email() {
      this.isValidEmail()
    }
  },
  methods: {
    servePageInfo() {
      return this.pageData
    },
    // 이메일 형태 유효성 검사
    isValidEmail() {
      const reg_email = /^([0-9a-zA-Z_\\.-]+)@([0-9a-zA-Z_-]+)(\.[0-9a-zA-Z_-]+){1,2}$/;
      if (this.email && !reg_email.test(this.email)) {
        this.emailStatus = "is-invalid"
        this.btnStatus = emailInvalid
      } else {
        if (this.email.length > 0) {
          this.emailStatus = "is-valid"
          this.btnStatus = emailValid
        } else {
          this.emailStatus = ""
          this.btnStatus = emailInvalid
        }
      }
    },
    setEmail() {
      console.log('커밋 완료')
      const user = {userEmail: this.email}
      this.$store.commit("setEmail", user)
      this.$router.push({ name: 'CheckEmail' })
    }
  }
}
</script>

<style>
  .noticeMessage {
    color: rgb(108, 160, 29);
    margin-top: 20px;
  }
  /* #id-input[type="text"]:focus {
    box-shadow: 0 0px 0px rgba(0, 0, 0, 0.075);
    outline: 0 none;
  } */
  #next-btn{
    margin-top: 20px;
  }
  .form-control:focus{
    border-color: none;
    box-shadow: none;
  }
</style>