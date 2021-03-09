<template>
  <div style="display: flex; justify-content: center;">
    <div>
      <div class="signupbackground">
        <header style="display: flex; justify-content: space-between; align-items: center;">
          <h1 class="signupHeader mb-0">회원가입</h1>  
          <img 
            width="30px" 
            height="30px" 
            src="data:image/svg+xml;base64,PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iaXNvLTg4NTktMSI/Pg0KPCEtLSBHZW5lcmF0b3I6IEFkb2JlIElsbHVzdHJhdG9yIDE5LjAuMCwgU1ZHIEV4cG9ydCBQbHVnLUluIC4gU1ZHIFZlcnNpb246IDYuMDAgQnVpbGQgMCkgIC0tPg0KPHN2ZyB2ZXJzaW9uPSIxLjEiIGlkPSJDYXBhXzEiIHhtbG5zPSJodHRwOi8vd3d3LnczLm9yZy8yMDAwL3N2ZyIgeG1sbnM6eGxpbms9Imh0dHA6Ly93d3cudzMub3JnLzE5OTkveGxpbmsiIHg9IjBweCIgeT0iMHB4Ig0KCSB2aWV3Qm94PSIwIDAgNTEyIDUxMiIgc3R5bGU9ImVuYWJsZS1iYWNrZ3JvdW5kOm5ldyAwIDAgNTEyIDUxMjsiIHhtbDpzcGFjZT0icHJlc2VydmUiPg0KPGc+DQoJPGc+DQoJCTxwYXRoIGQ9Ik0yNTYsMEMxMTQuNTA4LDAsMCwxMTQuNDk3LDAsMjU2YzAsMTQxLjQ5MywxMTQuNDk3LDI1NiwyNTYsMjU2YzE0MS40OTIsMCwyNTYtMTE0LjQ5NywyNTYtMjU2DQoJCQlDNTEyLDExNC41MDcsMzk3LjUwMywwLDI1NiwweiBNMjU2LDQ3MmMtMTE5LjM4NCwwLTIxNi05Ni42MDctMjE2LTIxNmMwLTExOS4zODUsOTYuNjA3LTIxNiwyMTYtMjE2DQoJCQljMTE5LjM4NCwwLDIxNiw5Ni42MDcsMjE2LDIxNkM0NzIsMzc1LjM4NSwzNzUuMzkzLDQ3MiwyNTYsNDcyeiIvPg0KCTwvZz4NCjwvZz4NCjxnPg0KCTxnPg0KCQk8cGF0aCBkPSJNMzQzLjU4NiwzMTUuMzAyTDI4NC4yODQsMjU2bDU5LjMwMi01OS4zMDJjNy44MS03LjgxLDcuODExLTIwLjQ3MywwLjAwMS0yOC4yODRjLTcuODEyLTcuODExLTIwLjQ3NS03LjgxLTI4LjI4NCwwDQoJCQlMMjU2LDIyNy43MTZsLTU5LjMwMy01OS4zMDJjLTcuODA5LTcuODExLTIwLjQ3NC03LjgxMS0yOC4yODQsMGMtNy44MSw3LjgxMS03LjgxLDIwLjQ3NCwwLjAwMSwyOC4yODRMMjI3LjcxNiwyNTYNCgkJCWwtNTkuMzAyLDU5LjMwMmMtNy44MTEsNy44MTEtNy44MTIsMjAuNDc0LTAuMDAxLDI4LjI4NGM3LjgxMyw3LjgxMiwyMC40NzYsNy44MDksMjguMjg0LDBMMjU2LDI4NC4yODRsNTkuMzAzLDU5LjMwMg0KCQkJYzcuODA4LDcuODEsMjAuNDczLDcuODExLDI4LjI4NCwwQzM1MS4zOTgsMzM1Ljc3NSwzNTEuMzk3LDMyMy4xMTIsMzQzLjU4NiwzMTUuMzAyeiIvPg0KCTwvZz4NCjwvZz4NCjxnPg0KPC9nPg0KPGc+DQo8L2c+DQo8Zz4NCjwvZz4NCjxnPg0KPC9nPg0KPGc+DQo8L2c+DQo8Zz4NCjwvZz4NCjxnPg0KPC9nPg0KPGc+DQo8L2c+DQo8Zz4NCjwvZz4NCjxnPg0KPC9nPg0KPGc+DQo8L2c+DQo8Zz4NCjwvZz4NCjxnPg0KPC9nPg0KPGc+DQo8L2c+DQo8Zz4NCjwvZz4NCjwvc3ZnPg0K" 
            @click="sendLogin"
            class="cancel-btn"
          />
        </header>
        <router-view ref="progress"></router-view>
        <!-- <SubmitEmail ref="progress"/> -->

      </div>

      <!--progressbar들어갈 위치-->
      <div>
        <b-progress 
        class="mt-5"
        :value="progressPercent"
        height="2rem"
        max="100" 
        show-progress 
        animated
        style="
          background-color: white;
          border-radius: 10px / 10px;
          box-shadow: 0px 1px 3px rgba(0, 0, 0, 0.16);
        "
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
    }
  },
  methods: {
    getProgress() {
      console.log(" getProgress 실행!")
      const ProgressData = this.$refs.progress.serveProgressData()
      this.progressPercent = ProgressData * 20
      console.log(this.progressPercent)
    },
    sendLogin() {
      this.$router.push({ name: 'Login' })
    },
  },
  mounted() {
    this.getProgress()
  },
  watch: {
    $route(to) {
      this.urlName = to.name // url이름이 필요할 때 사용(불필요하면 지우도 괜찮음)
      setTimeout(this.getProgress, 50)
    }
  },
}
</script>

<style>
  .signupbackground {
    margin-top: 15vh;
    width: 550px;
    height: 400px;
    background-color: white;
    border-radius: 30px / 30px;
    margin-right: 0px;
    box-shadow: 0px 3px 6px rgba(0, 0, 0, 0.16);
    padding-top: 40px;
    padding-left: 75px;
    padding-right: 75px;
  }
  .signupHeader {
  color: black;
  font-size: 2rem;
  font-weight: bold;
  display: inline-block;
  } 
  .cancel-btn:hover {
    cursor: pointer;
  }
</style>