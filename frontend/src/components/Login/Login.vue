<template>
  <div style="display: flex; justify-content: center;">
    <!-- 네모 -->
    <div
      class="loginbackground"
    >
      <h1 class="loginheader">로그인</h1>  
      <br>
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
      <b-form-input 
          class="id-input"
          style="border: 0;
          border-bottom: 1px solid;
          border-radius: 0;
          "
          type="password"
          placeholder="비밀번호"
        >
      </b-form-input>
      <a href="#" class="mb-1 mt-2" style="display: block; font-size: 0.9rem;">비밀번호를 잊어버리셨나요?</a>
      <a href="#" class="my-1" style="display: block; font-size: 0.9rem;">회원이 아니신가요?</a>
      <b-button block class="mt-3 btn-success" style="border-radius: 15px / 15px; font-weight: bold; font-size: 1.2rem;">로그인</b-button>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      email: '',
      isVisible: false,
    }
  },
  methods: {
    isEmail(email) {
      const regExp = /^[0-9a-zA-Z]([-_\\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
      return regExp.test(email); // 형식에 맞는 경우 true 리턴	
    },
  },
  watch: {
    // email 입력값이 변경될 때 마다 실행
    email(){
      if (this.email.length > 0) {
        if (this.isEmail(this.email)) {
          console.log('false')
          this.isVisible = false
        } else {
          console.log('true')
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
.loginbackground {
  margin-top: 15vh;
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
.form-control {
  font-size: 1.3rem;
}
.invalid-feedback{
  font-size: 100%;
}
a:hover {
  text-decoration: none;
}
</style>