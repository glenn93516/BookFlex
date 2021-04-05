<template>
  <div class="password-main">
    <div>
      <div :class="size" id="password-background">
        <header class="password-header">
          <h1 
            class="password-h1 mb-0"
          >
            비밀번호 찾기
          </h1>
          <img 
            width="30px" 
            height="30px" 
            src="data:image/svg+xml;base64,PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iaXNvLTg4NTktMSI/Pg0KPCEtLSBHZW5lcmF0b3I6IEFkb2JlIElsbHVzdHJhdG9yIDE5LjAuMCwgU1ZHIEV4cG9ydCBQbHVnLUluIC4gU1ZHIFZlcnNpb246IDYuMDAgQnVpbGQgMCkgIC0tPg0KPHN2ZyB2ZXJzaW9uPSIxLjEiIGlkPSJDYXBhXzEiIHhtbG5zPSJodHRwOi8vd3d3LnczLm9yZy8yMDAwL3N2ZyIgeG1sbnM6eGxpbms9Imh0dHA6Ly93d3cudzMub3JnLzE5OTkveGxpbmsiIHg9IjBweCIgeT0iMHB4Ig0KCSB2aWV3Qm94PSIwIDAgNTEyIDUxMiIgc3R5bGU9ImVuYWJsZS1iYWNrZ3JvdW5kOm5ldyAwIDAgNTEyIDUxMjsiIHhtbDpzcGFjZT0icHJlc2VydmUiPg0KPGc+DQoJPGc+DQoJCTxwYXRoIGQ9Ik0yNTYsMEMxMTQuNTA4LDAsMCwxMTQuNDk3LDAsMjU2YzAsMTQxLjQ5MywxMTQuNDk3LDI1NiwyNTYsMjU2YzE0MS40OTIsMCwyNTYtMTE0LjQ5NywyNTYtMjU2DQoJCQlDNTEyLDExNC41MDcsMzk3LjUwMywwLDI1NiwweiBNMjU2LDQ3MmMtMTE5LjM4NCwwLTIxNi05Ni42MDctMjE2LTIxNmMwLTExOS4zODUsOTYuNjA3LTIxNiwyMTYtMjE2DQoJCQljMTE5LjM4NCwwLDIxNiw5Ni42MDcsMjE2LDIxNkM0NzIsMzc1LjM4NSwzNzUuMzkzLDQ3MiwyNTYsNDcyeiIvPg0KCTwvZz4NCjwvZz4NCjxnPg0KCTxnPg0KCQk8cGF0aCBkPSJNMzQzLjU4NiwzMTUuMzAyTDI4NC4yODQsMjU2bDU5LjMwMi01OS4zMDJjNy44MS03LjgxLDcuODExLTIwLjQ3MywwLjAwMS0yOC4yODRjLTcuODEyLTcuODExLTIwLjQ3NS03LjgxLTI4LjI4NCwwDQoJCQlMMjU2LDIyNy43MTZsLTU5LjMwMy01OS4zMDJjLTcuODA5LTcuODExLTIwLjQ3NC03LjgxMS0yOC4yODQsMGMtNy44MSw3LjgxMS03LjgxLDIwLjQ3NCwwLjAwMSwyOC4yODRMMjI3LjcxNiwyNTYNCgkJCWwtNTkuMzAyLDU5LjMwMmMtNy44MTEsNy44MTEtNy44MTIsMjAuNDc0LTAuMDAxLDI4LjI4NGM3LjgxMyw3LjgxMiwyMC40NzYsNy44MDksMjguMjg0LDBMMjU2LDI4NC4yODRsNTkuMzAzLDU5LjMwMg0KCQkJYzcuODA4LDcuODEsMjAuNDczLDcuODExLDI4LjI4NCwwQzM1MS4zOTgsMzM1Ljc3NSwzNTEuMzk3LDMyMy4xMTIsMzQzLjU4NiwzMTUuMzAyeiIvPg0KCTwvZz4NCjwvZz4NCjxnPg0KPC9nPg0KPGc+DQo8L2c+DQo8Zz4NCjwvZz4NCjxnPg0KPC9nPg0KPGc+DQo8L2c+DQo8Zz4NCjwvZz4NCjxnPg0KPC9nPg0KPGc+DQo8L2c+DQo8Zz4NCjwvZz4NCjxnPg0KPC9nPg0KPGc+DQo8L2c+DQo8Zz4NCjwvZz4NCjxnPg0KPC9nPg0KPGc+DQo8L2c+DQo8Zz4NCjwvZz4NCjwvc3ZnPg0K" 
            @click="goToLogin"
            class="cancel-btn"
          />
        </header>
        <router-view></router-view>

      </div>

    </div>
  </div>
</template>

<script>
export default {
  components: {
  },
  data() {
    return {
      size: "back-md",
    }
  },
  methods: {
    goToLogin() {
      this.$router.push({ name: 'Login' })
    },
    Logout() {
      this.$store.dispatch('Logout')
      localStorage.removeItem('jwt')
      this.userInfo = ""
      this.isLogin = false
    },
  },
  mounted() {
    this.Logout()
  },
  watch: {
  },
}
</script>

<style>
  .password-main {
    display: flex; 
    justify-content: center;
  }
  #password-background {
    margin-top: 15vh;
    /* margin-bottom: 15vh; */
    width: 550px;
    /* height: 400px; */
    background-color: white;
    border-radius: 30px / 30px;
    margin-right: 0px;
    box-shadow: 0px 3px 6px rgba(0, 0, 0, 0.16);
    padding-top: 40px;
    padding-left: 75px;
    padding-right: 75px;
  }
  .password-header {
    display: flex; 
    justify-content: space-between; 
    align-items: center;
  }
  .password-h1 {
  color: black;
  font-size: 2rem;
  font-weight: bold;
  display: inline-block;
  } 
  .cancel-btn:hover {
    cursor: pointer;
  }
  
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