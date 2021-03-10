<template>
  <div style="color: black;">
    <p class="noticeMessage" style="margin-top: 20px;">{{ timeMin }}분 이내에 발급받은 인증 번호를 입력해주세요.</p>
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
      <a href="#" class="my-1" style="display: block; font-size: 0.9rem;" @click="resend">인증메일 재발송하기</a>
      <!-- <b-button
        variant="success"
        @click="isCorrect"
        :active="!timeOut"  
      >
        확인
      </b-button> -->
      <router-link
        class="btn btn-primary btn-block" 
        :to="{ name: 'InputName' }"
      >
        다음으로 넘어가기(임시)
      </router-link>
    </div>
  </div>
</template>

<script>
export default {
  props: {
  },
  data() {
    return {
      progressNum: 1,

      timeMin: 10, // 10분
      timeCounter: 600,
      resTimeData: "10 : 00",
      timeOut: false,
      mailNum: "",
      inputNum: "",
    }
  },
  methods: {
    serveProgressData() {
      console.log("여기는 체크")
      return this.progressNum
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
    }
  }

}
</script>

<style>
  .number-input[type="text"]:focus {
    box-shadow: 0 0px 0px rgba(0, 0, 0, 0.075);
    outline: 0 none;
  }
</style>