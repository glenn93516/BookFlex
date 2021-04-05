<template>
  <div style="text-align: center; margin: 0 auto;">
    <img
      style="margin-top:20px"
      width=120px;
      height=120px; 
      src="data:image/svg+xml;base64,PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iaXNvLTg4NTktMSI/Pg0KPCEtLSBHZW5lcmF0b3I6IEFkb2JlIElsbHVzdHJhdG9yIDE5LjAuMCwgU1ZHIEV4cG9ydCBQbHVnLUluIC4gU1ZHIFZlcnNpb246IDYuMDAgQnVpbGQgMCkgIC0tPg0KPHN2ZyB2ZXJzaW9uPSIxLjEiIGlkPSJDYXBhXzEiIHhtbG5zPSJodHRwOi8vd3d3LnczLm9yZy8yMDAwL3N2ZyIgeG1sbnM6eGxpbms9Imh0dHA6Ly93d3cudzMub3JnLzE5OTkveGxpbmsiIHg9IjBweCIgeT0iMHB4Ig0KCSB2aWV3Qm94PSIwIDAgNDkwLjY2NyA0OTAuNjY3IiBzdHlsZT0iZW5hYmxlLWJhY2tncm91bmQ6bmV3IDAgMCA0OTAuNjY3IDQ5MC42Njc7IiB4bWw6c3BhY2U9InByZXNlcnZlIj4NCjxwYXRoIHN0eWxlPSJmaWxsOiNGRkQ1NEY7IiBkPSJNNDgwLjQyNywzMi42NDJMMzAxLjAxNCwxNjIuMzQ4bC0yOS40NCwyMS4zMzNjLTE1LjU5NiwxMS41MjItMzYuODg0LDExLjUyMi01Mi40OCwwbC0yOS40NC0yMS4zMzMNCglMMTAuMDI3LDMyLjg1NWM5Ljk5MS0xMy45NzIsMjYuMTMtMjIuMjQxLDQzLjMwNy0yMi4xODdoMzg0QzQ1NC4zODUsMTAuNjgzLDQ3MC40MDEsMTguODUsNDgwLjQyNywzMi42NDJ6Ii8+DQo8Zz4NCgk8cGF0aCBzdHlsZT0iZmlsbDojRkZDMTA3OyIgZD0iTTEwLjAyNywzMi44NTVsMTc5LjYyNywxMjkuNDkzbC0xNzQuMDgsMTc0LjA4QzUuNTc4LDMyNi40MDctMC4wMjUsMzEyLjgyMywwLDI5OC42NjhWNjQuMDAyDQoJCUMtMC4wNDcsNTIuODE4LDMuNDY0LDQxLjkxLDEwLjAyNywzMi44NTV6Ii8+DQoJPHBhdGggc3R5bGU9ImZpbGw6I0ZGQzEwNzsiIGQ9Ik00OTAuNjY3LDY0LjAwMnYyMzQuNjY3YzAuMDI1LDE0LjE1NS01LjU3NywyNy43MzktMTUuNTczLDM3Ljc2bC0xNzQuMDgtMTc0LjA4TDQ4MC40MjcsMzIuNjQyDQoJCUM0ODcuMDg4LDQxLjc0LDQ5MC42NzQsNTIuNzI1LDQ5MC42NjcsNjQuMDAyeiIvPg0KPC9nPg0KPHBhdGggc3R5bGU9ImZpbGw6IzQ1NUE2NDsiIGQ9Ik0zMjAsMzk0LjY2OGMtMTQuMTY1LDAtMjEuMzMzLTEwLjY2Ny0yMS4zMzMtMzJjMC4zNDktMjkuODA3LTIzLjUzMS01NC4yNTItNTMuMzM4LTU0LjYwMQ0KCWMtMjkuODA3LTAuMzQ5LTU0LjI1MiwyMy41MzEtNTQuNjAxLDUzLjMzOGMtMC4zNDksMjkuODA3LDIzLjUzMSw1NC4yNTIsNTMuMzM4LDU0LjYwMWMxNS45NzUsMC4xODcsMzEuMjEyLTYuNzEzLDQxLjYwOS0xOC44NDINCgljNy4wODMsMTIuMTM4LDIwLjI4MiwxOS4zODEsMzQuMzI1LDE4LjgzN2M0Mi42NjcsMCw0Mi42NjctNDAuMTQ5LDQyLjY2Ny01My4zMzNjMC4wMDEtNjQuODAxLTUyLjUzLTExNy4zMzQtMTE3LjMzMS0xMTcuMzM2DQoJUzEyOC4wMDIsMjk3Ljg2MiwxMjgsMzYyLjY2NGMtMC4wMDEsNjQuODAxLDUyLjUzLDExNy4zMzQsMTE3LjMzMSwxMTcuMzM2YzMwLjQ0NSwwLjAwMSw1OS42OTktMTEuODMzLDgxLjU4MS0zMw0KCWM0LjIzNi00LjEyNCw0LjMyNi0xMC45LDAuMjAzLTE1LjEzNmMtNC4xMjQtNC4yMzYtMTAuOS00LjMyNi0xNS4xMzYtMC4yMDNsMCwwYy0zOC4xNjEsMzYuODA3LTk4LjkzNSwzNS43MDktMTM1Ljc0Mi0yLjQ1Mg0KCXMtMzUuNzA5LTk4LjkzNSwyLjQ1Mi0xMzUuNzQyYzM4LjE2MS0zNi44MDcsOTguOTM1LTM1LjcwOSwxMzUuNzQyLDIuNDUyYzE3LjI4NCwxNy45MiwyNi45Myw0MS44NTQsMjYuOTAzLDY2Ljc1DQoJQzM0MS4zMzQsMzg2LjU4MywzMzUuOTU4LDM5NC42NjgsMzIwLDM5NC42Njh6IE0yNDUuMzM0LDM5NC42NjhjLTE3LjY3MywwLTMyLTE0LjMyNy0zMi0zMnMxNC4zMjctMzIsMzItMzJzMzIsMTQuMzI3LDMyLDMyDQoJUzI2My4wMDcsMzk0LjY2OCwyNDUuMzM0LDM5NC42Njh6Ii8+DQo8cGF0aCBzdHlsZT0iZmlsbDojRkZBMDAwOyIgZD0iTTMwMS4wMTQsMTYyLjM0OGwtMjkuNDQsMjEuMzMzYy0xNS41OTYsMTEuNTIyLTM2Ljg4NCwxMS41MjItNTIuNDgsMGwtMjkuNDQtMjEuMzMzbC0xNzQuMDgsMTc0LjA4DQoJYzEwLjAyMSw5Ljk5NiwyMy42MDUsMTUuNTk5LDM3Ljc2LDE1LjU3M0gxMDcuMmM1Ljc3MS03Ni4yODMsNzIuMjg4LTEzMy40NDUsMTQ4LjU3Mi0xMjcuNjc0DQoJYzY4LjI1OSw1LjE2NCwxMjIuNTEsNTkuNDE1LDEyNy42NzQsMTI3LjY3NGg1My44ODhjMTQuMTU1LDAuMDI1LDI3LjczOS01LjU3NywzNy43Ni0xNS41NzNMMzAxLjAxNCwxNjIuMzQ4eiIvPg0KPGc+DQo8L2c+DQo8Zz4NCjwvZz4NCjxnPg0KPC9nPg0KPGc+DQo8L2c+DQo8Zz4NCjwvZz4NCjxnPg0KPC9nPg0KPGc+DQo8L2c+DQo8Zz4NCjwvZz4NCjxnPg0KPC9nPg0KPGc+DQo8L2c+DQo8Zz4NCjwvZz4NCjxnPg0KPC9nPg0KPGc+DQo8L2c+DQo8Zz4NCjwvZz4NCjxnPg0KPC9nPg0KPC9zdmc+DQo=" 
    />
    <h3
      class="complete-title"
    >
      비밀번호의 초기화가
      <br>
      완료되었습니다</h3>
    <p class="notice-message notice-message-margin">
      본인의 이메일에서 초기화된 비밀번호를 확인하세요.
    </p>
  </div>
</template>

<script>
export default {
  data() {
    return {
    }
  },
  created() {
    console.log(this.$store.state.signupInfo.userEmail)
    this.$axios.post(`${this.$store.getters.getServer}/email/password`, {},{
      params: {
        'userEmail': this.$store.state.signupInfo.userEmail,
      }
    })
    .then(res => {
      console.log(res)
    })
    .catch(err => {
      console.error(err)
    })
  },
  methods: {
  }
}
</script>

<style>
  .notice-message-margin {
    margin: 8px 0 20px 0;
  }
  .complete-title {
    justify-content: center;
    font-weight: bold;
    margin-top: 1.5rem;
  }
  .button-group {
    display: flex;
    justify-content: space-around;
    margin-top: 2rem;
  }
  .complete-button {
    border-radius: 15px / 15px;
  }
  .button-text {
    display: inline-block; 
    margin: 15px 0;
  }
</style>