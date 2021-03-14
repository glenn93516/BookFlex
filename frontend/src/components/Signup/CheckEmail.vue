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
        v-model="confirmSubmit"
        class="number-input"
        style="border: 0;
        border-bottom: 1px solid;
        border-radius: 0;
        "
        placeholder="6자리 입력"
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
      <!-- <b-button
        variant="success"
        @click="isCorrect"
        :active="!timeOut"  
      >
        확인
      </b-button> -->
    </div>
      <b-btn 
        class="btn-success btn-block next-btn" 
        @click="goToInputName" 
        @keydown.enter="goToInputName"
      >
        다음으로 넘어가기
      </b-btn>
  </div>
</template>

<script>
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
      mailNum: "",
      inputNum: "",
      confirmSubmit: "",
    }
  },
  methods: {
    servePageInfo() {
      console.log("여기는 체크")
      return this.pageData
    },
    start() {
      this.timeOut = false
      this.polling = setInterval(() => {
        this.timeCounter--;
        this.resTimeData = this.prettyTime();
        if (this.timeCounter <= 0) {
          this.timeStop();
        }
      }, 1000);
    },
    prettyTime() {
      let time = this.timeCounter;
      let minutes = parseInt(time);
      let seconds = Math.round((time - minutes) * 60);
      return `${this.pad(minutes, 2)} : ${this.pad(seconds, 2)}`;
    },
    pad(n, width) {
      n = n + "";
      return n.length >= width ? n : new Array(width - n.length + 1).join("0" + n);
    },
    timeStop() {
      clearInterval(this.polling);
      alert("인증번호 유효시간이 초과되었습니다.");
      this.timeOut = true;
    },
    resend() {

    },
    beforeDestroy() {
      clearInterval(this.polling);
    },
    goToInputName() {
      // 조건에 따라(axios요청 결과 일치여부확인, 불일치시 alert 띄우기)
      this.$router.push({ name : 'InputName' })
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
  .next-btn {
    margin-top: 2rem;
  }
  #timer {
    margin: 2rem;
  }
</style>