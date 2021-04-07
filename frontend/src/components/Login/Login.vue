<template>
  <div id="login">
    <!-- 네모 -->
    <!-- 로그인을 submit버튼으로 바꿔주기 -->
    <div
      class="loginbackground"
    >
      <h1 class="loginheader font-jeonnam">로그인</h1>  
      <br>
      <b-form @submit.prevent="userLogin()">
        <b-form-input
          v-model="email"
          class="id-input font-jeonnam"
          placeholder="이메일 (example@gmail.com)"
        >
      </b-form-input>
        <div class="warning-email font-jeonnam" v-show="isVisible">
          이메일 양식이 올바르지 않습니다.
        </div>
        <br>
        <b-form-input
          v-model="password" 
          class="id-input font-jeonnam"
          type="password"
          placeholder="비밀번호"
        >
        </b-form-input>
        <a href="#" 
          class="login-link first-login-link font-jeonnam"
          @click="goToPassword"
        >
        비밀번호를 잊어버리셨나요?
        </a>
        <!-- signup/1이 아니라 signup으로 보내게 수정해줘야함 !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! -->
        <a href="/signup/1" class="login-link second-login-link font-jeonnam">회원이 아니신가요?</a>
        <b-button 
          block
          type="submit"
          class="btn-success login-button font-jeonnam" 
          :disabled="isVisible"
        >
          로그인
        </b-button>
      </b-form>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      email: '',
      password: '',
      isVisible: false,
    }
  },
  methods: {
    isEmail(email) {
      const regExp = /^([0-9a-zA-Z_\\.-]+)@([0-9a-zA-Z_-]+)(\.[0-9a-zA-Z_-]+){1,2}$/;
      return regExp.test(email); // 형식에 맞는 경우 true 리턴	
    },
    goToSignup() {
      this.$router.push({ name: "SubmitEmail" })
    },
    userLogin() {
      const user = { userEmail: this.email, userPassword: this.password, mode: 1 }
      this.$store.dispatch('Login', user)
    },
    goToPassword() {
      this.$router.push({ name: "PasswordSubmitEmail" })
    }
  },
  watch: {
    // email 입력값이 변경될 때 마다 실행
    email(){
      if (this.email.length > 0) {
        if (this.isEmail(this.email)) {
          // console.log('email isVisible false')
          this.isVisible = false
        } else {
          // console.log('email isVisible true')
          this.isVisible = true
        }
      } else {
        this.isVisible = false
      }
    }
  }
}
</script>

<style>
#login {
  display: flex; 
  justify-content: center;
}
.loginbackground {
  margin-top: 15vh;
  margin-bottom: 15vh;
  height: 400px;
  width: 550px;
  background-color: white;
  border-radius: 30px / 30px;
  margin-right: 0px;
  box-shadow: 0px 3px 6px rgba(0, 0, 0, 0.16);
  padding-top: 40px;
  padding-left: 75px;
  padding-right: 75px;
}
.loginheader {
  color: black;
  font-size: 2rem;
  font-weight: bold;
}
.id-input[type="text"]:focus {
  box-shadow: 0 0px 0px rgba(0, 0, 0, 0.075);
  outline: 0 none;
}
.id-input[type="password"]:focus {
  box-shadow: 0 0px 0px rgba(0, 0, 0, 0.075);
  outline: 0 none;
}
.id-input {
  font-size: 1.3rem;
  border: 0;
  border-bottom: 1px solid;
  border-radius: 0;
}
.warning-email {
  color: red;
  margin-bottom: -24px;
}
.login-link {
  display: block;
  font-size: 0.8rem;
}
.login-link:hover {
  text-decoration: none;
}
.first-login-link {
  margin: 25px 0 0 0;
}
.second-login-link {
  margin: 5px 0;
}
</style>