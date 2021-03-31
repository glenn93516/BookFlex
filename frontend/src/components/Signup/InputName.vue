<template>
  <div>
    <b-form @submit.prevent="duplicateCheck('enter', name)">
      <b-form-input
        v-model="name"
        class="input-userinfo input"
        :class="nameStatus"
        placeholder="필명 (8자리 이내)"
        @blur="duplicateCheck('blur', name)"
      >
      </b-form-input>
      <div 
        class="userinfo-alert"
        v-show="nameStatus === 'is-invalid duplicate'"
      >
        이미 존재하는 닉네임입니다.
      </div>
      <div 
        class="userinfo-alert"
        v-show="nameStatus === 'is-invalid'"
      >
        필명은 8자리 이내의 한글, 숫자, 영문만 가능합니다
      </div>
      <br>

      <b-form-input
        v-model="password"
        class="input-userinfo-second input"
        :class="pwStatus"
        type="password"
        placeholder="비밀번호 (8자리 이상, 영문 숫자 혼합 필수)"
      >
      </b-form-input>
      <div 
        class="userinfo-alert" 
        v-show="pwStatus == 'is-invalid'"
      >
        비밀번호는 8자리 이상, 영문 숫자를 혼합해주세요
      </div>
      <br>
      <b-form-input
        v-model="confirmPassword"
        class="input-userinfo-second"
        :class="confirmStatus"
        type="password"
        placeholder="비밀번호 확인"
      >
      </b-form-input>
      <div 
        class="userinfo-alert" 
        v-show="confirmStatus == 'is-invalid'"
      >
        비밀번호와 일치하지 않습니다
      </div>
      <br>
      <b-button 
        class="input-userinfo-btn btn btn-success btn-block"
        type="submit"
        :disabled="disableBtn"
        @click="submitUserName"
      >
        다음으로 넘어가기
      </b-button>
    </b-form>
  </div>
</template>

<script>
export default {
  data() {
    return {
      pageData: {
        progress: 2,
        size: "back-md"
      },
      name: "",
      password: "",
      confirmPassword: "",
      nameStatus: "",
      pwStatus: "",
      confirmStatus: "",
      // 버튼 활성화 여부 (활성화 : false, 비활성화: true)
      disableBtn: true,
    }
  },
  watch: {
    name() {
      this.isValidName()
      this.canNext()
    },
    password() {
      this.isValidPassword()
      this.isValidConfirmPassword()
      this.canNext()
    },
    confirmPassword() {
      this.isValidConfirmPassword()
      this.canNext()
    }
  },
  methods: {
    servePageInfo() {
      return this.pageData
    },
    canNext() {
      if (this.nameStatus === "is-valid"
        && this.pwStatus === "is-valid"
        && this.confirmStatus === "is-valid"
      ) {
        this.disableBtn = false
      } else {
        this.disableBtn = true
      }
    },
    isValidName() {
      const reg_name = /^[\wㄱ-ㅎㅏ-ㅣ가-힣]{1,8}$/;
      if (this.name && !reg_name.test(this.name)) {
        this.nameStatus = "is-invalid"
        this.disableBtn = true
      } else {
        if (this.name.length > 0) {
          this.nameStatus = "is-valid"
        } else {
          this.nameStatus = ""
          this.disableBtn = true
        }
      }
    },
    isValidPassword() {
      const reg_password = /^[a-zA-Z0-9]{8,20}$/;
      if (this.password && !reg_password.test(this.password)) {
        this.pwStatus = "is-invalid"
        this.disableBtn = true
      } else {
        if (this.password.length > 0) {
          this.pwStatus = "is-valid"
        } else {
          this.pwStatus = ""
          this.disableBtn = true
        }
      }
    },
    isValidConfirmPassword() {
      if (this.pwStatus === "is-valid") {
        if (this.confirmPassword && this.password !== this.confirmPassword) {
          this.confirmStatus = "is-invalid"
          this.disableBtn = true
        } else {
          if (this.confirmPassword.length > 0) {
            this.confirmStatus = "is-valid"
          } else {
            this.confirmStatus = ""
            this.disableBtn = true
          }
        }
      } else {
        this.confirmStatus = ""
        this.disableBtn = true
      }
    },
    submitUserName() {
      const user = { 
        userNickname: this.name ,
        userPassword: this.password
      }
      this.$store.dispatch("SetSignupInfo", user)
      this.goToSubmitPref()
    },
    goToSubmitPref() {
      this.$router.push({ name: "SubmitPref" })
    },
    // 중복 체크
    duplicateCheck(input, name) {
      if (name && name.length > 8) {
        this.nameStatus = "is-invalid"
      } else {
        if (name) {
          this.$axios.get(`${this.$store.getters.getServer}/user/${name}`)
          .then(res => {
            // 가입 불가능
            if (res.data.message === '조회 성공') {
              this.nameStatus = "is-invalid duplicate"
            }
          })
          .catch(err => {
            // 가입 가능
            if (err.response.data.message === '없는 유저입니다.') {
              this.nameStauts = "is-valid"
              if (input === 'enter') {
                const user = {userNickname: this.name, userPassword: this.password}
                this.$store.dispatch("SetSignupInfo", user)
                // this.$router.push({ name: 'SubmitPref' })
              }
            }
          })
        }
      }
    }
  }
}
</script>

<style>
  .input-userinfo {
    border: 0;
    border-bottom: 1px solid;
    border-radius: 0;
    margin-top: 20px;
  }
  .input-userinfo-second {
    border: 0;
    border-bottom: 1px solid;
    border-radius: 0;
  }
  .userinfo-alert {
    color: red; 
    margin-bottom: -24px;
  }
  .form-control:focus{
    border-color: none;
    box-shadow: none;
  }
  .input-userinfo-btn {
    margin-top: 2.95rem;
  }
</style>