<template>
  <div class="subsignup-main">
    <div>
      <div id="signup-background" :class="size">
        <header class="subsignup-header">
          <h1 
            class="signup-h1 mb-0"
            v-show="signupTitle"
          >
            추가항목
          </h1>  
          <img 
            width="30px" 
            height="30px" 
            src="data:image/svg+xml;base64,PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iaXNvLTg4NTktMSI/Pg0KPCEtLSBHZW5lcmF0b3I6IEFkb2JlIElsbHVzdHJhdG9yIDE5LjAuMCwgU1ZHIEV4cG9ydCBQbHVnLUluIC4gU1ZHIFZlcnNpb246IDYuMDAgQnVpbGQgMCkgIC0tPg0KPHN2ZyB2ZXJzaW9uPSIxLjEiIGlkPSJDYXBhXzEiIHhtbG5zPSJodHRwOi8vd3d3LnczLm9yZy8yMDAwL3N2ZyIgeG1sbnM6eGxpbms9Imh0dHA6Ly93d3cudzMub3JnLzE5OTkveGxpbmsiIHg9IjBweCIgeT0iMHB4Ig0KCSB2aWV3Qm94PSIwIDAgNTEyIDUxMiIgc3R5bGU9ImVuYWJsZS1iYWNrZ3JvdW5kOm5ldyAwIDAgNTEyIDUxMjsiIHhtbDpzcGFjZT0icHJlc2VydmUiPg0KPGc+DQoJPGc+DQoJCTxwYXRoIGQ9Ik0yNTYsMEMxMTQuNTA4LDAsMCwxMTQuNDk3LDAsMjU2YzAsMTQxLjQ5MywxMTQuNDk3LDI1NiwyNTYsMjU2YzE0MS40OTIsMCwyNTYtMTE0LjQ5NywyNTYtMjU2DQoJCQlDNTEyLDExNC41MDcsMzk3LjUwMywwLDI1NiwweiBNMjU2LDQ3MmMtMTE5LjM4NCwwLTIxNi05Ni42MDctMjE2LTIxNmMwLTExOS4zODUsOTYuNjA3LTIxNiwyMTYtMjE2DQoJCQljMTE5LjM4NCwwLDIxNiw5Ni42MDcsMjE2LDIxNkM0NzIsMzc1LjM4NSwzNzUuMzkzLDQ3MiwyNTYsNDcyeiIvPg0KCTwvZz4NCjwvZz4NCjxnPg0KCTxnPg0KCQk8cGF0aCBkPSJNMzQzLjU4NiwzMTUuMzAyTDI4NC4yODQsMjU2bDU5LjMwMi01OS4zMDJjNy44MS03LjgxLDcuODExLTIwLjQ3MywwLjAwMS0yOC4yODRjLTcuODEyLTcuODExLTIwLjQ3NS03LjgxLTI4LjI4NCwwDQoJCQlMMjU2LDIyNy43MTZsLTU5LjMwMy01OS4zMDJjLTcuODA5LTcuODExLTIwLjQ3NC03LjgxMS0yOC4yODQsMGMtNy44MSw3LjgxMS03LjgxLDIwLjQ3NCwwLjAwMSwyOC4yODRMMjI3LjcxNiwyNTYNCgkJCWwtNTkuMzAyLDU5LjMwMmMtNy44MTEsNy44MTEtNy44MTIsMjAuNDc0LTAuMDAxLDI4LjI4NGM3LjgxMyw3LjgxMiwyMC40NzYsNy44MDksMjguMjg0LDBMMjU2LDI4NC4yODRsNTkuMzAzLDU5LjMwMg0KCQkJYzcuODA4LDcuODEsMjAuNDczLDcuODExLDI4LjI4NCwwQzM1MS4zOTgsMzM1Ljc3NSwzNTEuMzk3LDMyMy4xMTIsMzQzLjU4NiwzMTUuMzAyeiIvPg0KCTwvZz4NCjwvZz4NCjxnPg0KPC9nPg0KPGc+DQo8L2c+DQo8Zz4NCjwvZz4NCjxnPg0KPC9nPg0KPGc+DQo8L2c+DQo8Zz4NCjwvZz4NCjxnPg0KPC9nPg0KPGc+DQo8L2c+DQo8Zz4NCjwvZz4NCjxnPg0KPC9nPg0KPGc+DQo8L2c+DQo8Zz4NCjwvZz4NCjxnPg0KPC9nPg0KPGc+DQo8L2c+DQo8Zz4NCjwvZz4NCjwvc3ZnPg0K" 
            @click="sendCancel"
            class="cancel-btn"
            v-show="signupTitle"
          />
        </header>
        <p class="noticeMessage" style="font-size: 12px" v-show="signupTitle">
          추가 항목은 프로필 수정 페이지에서 추가 입력 및 수정이 가능합니다.
        </p>

        <router-view ref="progress"></router-view>
        <!-- <SubmitEmail ref="progress"/> -->

      </div>

      <!--progressbar들어갈 위치-->
      <div>
        <b-progress 
          class="mt-5 progress"
          :value="progressPercent"
          height="2rem"
          max="100" 
          show-progress 
          animated
        ></b-progress>
      </div>
    </div>
  </div>
</template>

<script>
// import SubmitEmail from './SubmitEmail.vue'
export default {
  components: {
    // SubmitEmail,
  },
  data() {
    return {
      progressPercent: 0,
      urlName: "",
      signupTitle: true,
      size: "back-md"
    }
  },
  methods: {
    getPageInfo() {
      console.log(" getPageInfo 실행!")
      const PageData = this.$refs.progress.servePageInfo()
      if (PageData.progress > 0) {
        this.progressPercent = PageData.progress * 25
        const progressGraph = document.querySelector('.progress-bar')
        progressGraph.innerHTML = this.progressPercent
      } else {
        this.progressPercent = 2
        const progressGraph = document.querySelector('.progress-bar')
        progressGraph.innerHTML = ""
      }
      this.size = PageData.size
    },
    sendCancel() {
      this.$router.push({ name: 'Main' })
    },
  },
  mounted() {
    this.getPageInfo()
  },
  watch: {
    $route(to) {
      this.urlName = to.name // url이름이 필요할 때 사용(불필요하면 지우도 괜찮음)
      console.log(this.urlName)
      if (this.urlName=="SubSignComplete") {
        this.signupTitle = false
      } else {
        this.signupTitle = true
      }
      setTimeout(this.getPageInfo, 50)
    }
  },
}
</script>

<style>
  .subsignup-main {
    display: flex; 
    justify-content: center;
  }
  #signup-background {
    margin-top: 15vh;
    width: 550px;
    background-color: white;
    border-radius: 30px / 30px;
    margin-right: 0px;
    box-shadow: 0px 3px 6px rgba(0, 0, 0, 0.16);
    padding-top: 40px;
    padding-left: 75px;
    padding-right: 75px;
  }
  .subsignup-header {
    display: flex; 
    justify-content: space-between; 
    align-items: center;
  }
  .signup-h1 {
  color: black;
  font-size: 2rem;
  font-weight: bold;
  display: inline-block;
  } 
  .noticeMessage {
    color: rgb(108, 160, 29);
    margin-top: 20px;
  }
  .cancel-btn:hover {
    cursor: pointer;
  }
  .progress {
    background-color: white;
    border-radius: 10px / 10px;
    box-shadow: 0px 1px 3px rgba(0, 0, 0, 0.16);
  }
  /* 라우터뷰에 따른 사이즈 속성값(컴포넌트에 따라 동적으로 변함) */
  .back-lg {
    height: 500px;
  }
  .back-md {
    height: 400px;
  }
  .back-sm {
    height: 300px;
  }
</style>