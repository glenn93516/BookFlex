<template>
  <div>
    <p 
      class="notice-message"
    >
      가입하실 이메일 주소를 입력해주세요.
    </p>
    <b-form @submit.prevent="duplicateCheck('enter', email)">
      <b-form-input
        v-model="email"
        class="id-input"
        :class="emailStatus"
        placeholder="이메일 (example@gmail.com)"
        @blur="duplicateCheck('blur', email)"
      >
      </b-form-input>
      <div 
        class="warning-msg"
        v-show="emailStatus === 'is-invalid duplicate'"
      >
        이미 가입된 회원입니다.
      </div>
      <div 
        class="warning-msg"
        v-show="emailStatus === 'is-invalid form-invalid'"
      >
        이메일 양식이 올바르지 않습니다.
      </div>
      <br>
      <b-button
        class="btn btn-success btn-block next-btn-submit"
        type="submit"
        :disabled="disableBtn"
      >
        확인
      </b-button>
    </b-form>
  </div>
</template>

<script>
export default {
  data() {
    return {
      pageData: {
        progress: 0,
        size: "back-sm"
      },
      email: "",
      // 현재 이메일이 유효한지 아닌지 ("", is-valid, is-invalid form-invalid, is-invalid duplicate)
      emailStatus: "",
      // 버튼 활성화 여부 (활성화: false, 비활성화: true)
      disableBtn: true,
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
        this.emailStatus = "is-invalid form-invalid"
        this.disableBtn = true
      } else {
        if (this.email.length > 0) {
          this.emailStatus = ""
          this.disableBtn = false
        } else {
          this.emailStatus = ""
          this.disableBtn = true
        }
      }
    },
    setEmail(email) {
      const user = {userEmail: email}
      this.$store.dispatch("SetEmail", user)
      this.$router.push({ name: 'CheckEmail' })
    },
    duplicateCheck(input, email) {
      if (this.emailStatus !== 'is-invalid form-invalid') {
        const user = {userEmail: email}
        this.$axios.get(`${this.$store.getters.getServer}/user/check`, {
          params: user
        })
        .then(res => {
          // 가입 불가능
          if (!res.data.success) {
            this.emailStatus = "is-invalid duplicate"
            this.disableBtn = true
          } else {
            // 가입 가능
            this.emailStatus = ""
            this.diableBtn = false
            if (input === 'enter') {
              const user = {userEmail: email}
              this.$store.dispatch("SetEmail", user)
              this.$router.push({ name: 'CheckEmail' })
            }
          }
        })
        .catch(err => {
          console.error(err)
        })
      } else {
        console.log('여기에는 뭘 넣을까')
      }
    }
  }
}
</script>

<style>
  .notice-message {
    color: rgb(108, 160, 29);
    margin-top: 20px;
  }
  .next-btn-submit{
    margin-top: 40px;
  }
  .form-control:focus{
    border-color: none;
    box-shadow: none;
  }
  .id-input{
    border: 0;
    border-bottom: 1px solid;
    border-radius: 0;
  }
  .warning-msg{
    color: red;
    margin-bottom: -24px;
  }
</style>