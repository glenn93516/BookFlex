<template>
  <div>
    <p class="noticeMessage">가입하실 이메일 주소를 입력해주세요.</p>
    <b-form>
      <b-form-input
        v-model="email"
        class="id-input"
        :class="emailStatus"
        style="border: 0;
        border-bottom: 1px solid;
        border-radius: 0;
        "
        placeholder="이메일 (example@gmail.com)"
        @blur="duplicateCheck"
        @keydown.enter="setEmail"
      >
      </b-form-input>
      <div style="color: red; margin-bottom: -24px;" v-show="emailStatus === 'duplicate'">
        이미 가입된 회원입니다.
      </div>
      <div style="color: red; margin-bottom: -24px;" v-show="emailStatus === 'form-invalid'">
        이메일 양식이 올바르지 않습니다.
      </div>
      <br>
      <b-button
        class="btn btn-success btn-block next-btn"
        :class="able" 
        @click="setEmail"
      >
        확인
      </b-button>
    </b-form>
  </div>
</template>

<script>
// let emailValid = "btn btn-success btn-block"
// let emailInvalid = "btn btn-success btn-block disabled"
export default {
  data() {
    return {
      pageData: {
        progress: 0,
        size: "back-sm"
      },
      email: "",
      // 현재 이메일이 유효한지 아닌지 ("", form-invalid, duplicate)
      emailStatus: "",
      // 다음 단계로 넘어갈 수 있는지 (disabled, "")
      able: "disabled",
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
        this.emailStatus = "form-invalid"
        this.able = "disabled"
      } else {
        if (this.email.length > 0) {
          this.emailStatus = ""
          this.able = ""
        } else {
          this.emailStatus = ""
          this.able = "disabled"
        }
      }
    },
    setEmail() {
      // 여기서 가입된 이메일이면 alert
      this.duplicateCheck()
      const user = {userEmail: this.email}
      this.$store.dispatch("SetEmail", user)
      this.$router.push({ name: 'CheckEmail' })
    },
    duplicateCheck() {
      // console.log(`${this.$store.getters.getServer}/user/check`, '주소')
      // console.log(this.email, '이메일')
      this.$axios.get(`${this.$store.getters.getServer}/user/check`, {
        params: {
          userEmail: this.email
        }
      })
      .then(res => {
        // console.log(res.data)
        // 가입 불가능
        if (!res.data.success) {
          this.emailStatus = "duplicate"
          this.able = "disabled"
        } else {
          this.emailStatus = ""
          this.able = ""
        }
      })
      .catch(err => {
        console.error(err)
      })
    }
  }
}
</script>

<style>
  .noticeMessage {
    color: rgb(108, 160, 29);
    margin-top: 20px;
  }
  .next-btn{
    margin-top: 20px;
  }
  .form-control:focus{
    border-color: none;
    box-shadow: none;
  }
</style>