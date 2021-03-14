<template>
  <div>
    <b-form-input
      v-model="name"
      :class="nameStatus"
      style="border: 0;
      border-bottom: 1px solid;
      border-radius: 0;
      margin-top: 20px;
      "
      placeholder="필명 (8자리 이내)"
    >
    </b-form-input>
    <div style="color: red; margin-bottom: -24px;" v-show="nameStatus == 'input is-invalid'">
      필명은 8자리 이내의 한글, 숫자, 영문만 가능합니다
    </div>
    <br>
    <b-form-input
      v-model="password"
      :class="pwStatus"
      style="border: 0;
      border-bottom: 1px solid;
      border-radius: 0;
      "
      type="password"
      placeholder="비밀번호 (8자리 이상, 영문 숫자 혼합 필수)"
    >
    </b-form-input>
    <div style="color: red; margin-bottom: -24px;" v-show="pwStatus == 'input is-invalid'">
      비밀번호는 8자리 이상, 영문 숫자를 혼합해주세요
    </div>
    <br>
    <b-form-input
      v-model="confirmPassword"
      :class="confirmStatus"
      style="border: 0;
      border-bottom: 1px solid;
      border-radius: 0;
      "
      type="password"
      placeholder="비밀번호 확인"
    >
    </b-form-input>
    <div style="color: red; margin-bottom: -24px;" v-show="confirmStatus == 'input is-invalid'">
      비밀번호와 일치하지 않습니다
    </div>
    <br>
    <!-- <b-button
      block
      variant="success"
      @click="sendName"
    >
      확인
    </b-button> -->
    <router-link 
      :to="{ name: 'SubmitPref' }" 
      :class="btnStatus"
      style="margin-top: 16px;"
    >
      다음으로 넘어가기(임시)
    </router-link>
  </div>
</template>

<script>
// 나중에 필명 겹치는지도 확인해줘야함

let nameValid = "input is-valid"
let nameInvalid = "input is-invalid"
let passwordValid = "input is-valid"
let passwordInvalid = "input is-invalid"
let confirmValid = "input is-valid"
let confirmInvalid = "input is-invalid"
let btnValid = "btn btn-success btn-block"
let btnInvalid = "btn btn-success btn-block disabled"

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
      nameStatus: "input",
      pwStatus: "input",
      confirmStatus: "input",
      btnStatus: "btn btn-success btn-block disabled",
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
      console.log("여기는 인풋 네임")
      return this.pageData
    },
    canNext() {
      if (this.nameStatus === nameValid 
          && this.pwStatus === passwordValid 
          && this.confirmStatus === confirmValid) {
            this.btnStatus = btnValid 
        } else {
          this.btnStatus = btnInvalid
        }
    },
    isValidName() {
      const reg_name = /^[\wㄱ-ㅎㅏ-ㅣ가-힣]{1,8}$/;
      if (this.name && !reg_name.test(this.name)) {
        this.nameStatus = nameInvalid
      } else {
        if (this.name.length > 0) {
          this.nameStatus = nameValid
        } else {
          this.nameStatus = ""
        }
      }
    },
    isValidPassword() {
      const reg_password = /^[a-zA-Z0-9]{8,20}$/;
      if (this.password && !reg_password.test(this.password)) {
        this.pwStatus = passwordInvalid
      } else {
        if (this.password.length > 0) {
          this.pwStatus = passwordValid
        } else {
          this.pwStatus = ""
        }
      }
    },
    isValidConfirmPassword() {
      if (this.pwStatus === passwordValid) {
        if (this.confirmPassword && this.password !== this.confirmPassword) {
          this.confirmStatus = confirmInvalid
        } else {
          if (this.confirmPassword.length > 0) {
            this.confirmStatus = confirmValid
          } else {
            this.confirmStatus = ""
          }
        }
      } else {
        this.confirmStatus = ""
      }
    },
  }
}
</script>

<style>
  .form-control:focus{
    border-color: none;
    box-shadow: none;
  }
</style>