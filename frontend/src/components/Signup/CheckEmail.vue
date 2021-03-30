<template>
  <div style="color: black;">
    <p 
      class="noticeMessage" 
      style="margin-top: 20px;"
    >
      {{ timeMin }}분 이내에 발급받은 인증 번호를 입력해주세요.
    </p>
    <div>
      <h1 id="timer" class="text-center">{{ resTimeData }}</h1>
      <b-form-input
        v-model="inputNum"
        class="number-input"
        style="border: 0;
        border-bottom: 1px solid;
        border-radius: 0;
        display: inline-block;
        width=10%;
        "
        @keydown.enter="goToInputName"
        placeholder="6자리 입력"
        :autofocus="inputAutofocus"
      >
      </b-form-input>
      <a 
        href="#" 
        class="my-1" 
        style="display: block; font-size: 0.9rem;" 
        @click="resend"
      >
        인증메일 재발송하기
      </a>
    </div>
      <b-btn 
        class="btn-success btn-block next-btn-check"
        :disabled="btnDisabled" 
        @click="goToInputName" 
      >
        확인
      </b-btn>
  </div>
</template>

<script>
// import axios from "axios"

export default {
  props: {
  },
  data() {
    return {
      pageData: {
        progress: 1,
        size: "back-md"
      },
      timeMin: 10, // 10분
      timeCounter: 600,
      resTimeData: "10 : 00",
      timeOut: false,
      // 인증번호
      mailNum: "",
      // 사용자가 입력한 번호
      inputNum: "",
      btnDisabled: true,
      polling: "",
      inputAutofocus: false,
    }
  },
  created() {
    this.sendEmail()
  },
  methods: {
    // 이메일 전송하기
    sendEmail() {
      const userEmail = this.$store.state.signupInfo.userEmail
      this.$axios.post(`${this.$store.getters.getServer}/email/mail`, 
        {},
        {
          params: {
            'userEmail': userEmail,
          }
        }
      )
      .then(res => {
        console.log(res.data.data, '인증번호')
        this.mailNum = res.data.data
        alert(`인증번호가 발송되었습니다. ${ this.timeMin }분 이내에 발급받은 인증 번호를 입력해주세요.`)      
        this.start()
        this.btnDisabled = false
      })
      .catch(err => {
        console.log(err)
      })
    },
    servePageInfo() {
      console.log("여기는 페이지 체크")
      return this.pageData
    },
    start() {
      this.timeOut = false
      this.timeCounter = 600
      this.polling = setInterval(() => {
        this.timeCounter--
        this.resTimeData = this.prettyTime();
        if (this.timeCounter <= 0) {
          this.timeStop()
        }
      }, 1000)
      console.log(this.polling, 'polling')
    },
    prettyTime() {
      let time = this.timeCounter
      let minutes = parseInt(time / 60)
      let seconds = Math.round(time - (minutes * 60))
      return `${this.pad(minutes, 2)} : ${this.pad(seconds, 2)}`
    },
    pad(n, width) {
      n = n + "";
      return n.length >= width ? n : new Array(width - n.length + 1).join("0" + n)
    },
    timeStop() {
      clearInterval(this.polling)
      alert("인증번호 유효시간이 초과되었습니다.")
      this.timeOut = true
      // 버튼 비활성화
      this.btnDisabled = true
    },
    resend() {
      clearInterval(this.polling)
      this.timeOut = true
      this.mailNum = ""
      this.sendEmail()
    },
    beforeDestroy() {
      clearInterval(this.polling)
    },
    goToInputName() {
      if (this.inputNum === this.mailNum) {
        alert('인증되었습니다.')
        this.$router.push({ name : 'InputName' })
      } else {
        alert('다시 시도하세요.')
        this.inputNum = ""
        this.inputAutofocus = true
      }
      // 조건에 따라(axios요청 결과 일치여부확인, 불일치시 alert 띄우기)
    }
  }

}
</script>

<style>
  .number-input[type="text"]:focus {
    box-shadow: 0 0px 0px rgba(0, 0, 0, 0.075);
    outline: 0 none;
  }
  .noticeMessage {
    color: rgb(108, 160, 29);
    margin-top: 20px;
  }
  .next-btn-check {
    margin-top: 2.1rem;
  }
  #timer {
    margin: 2rem;
  }
</style>