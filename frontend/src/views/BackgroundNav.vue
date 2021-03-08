<template>
  <div style="
    background-color: rgba(61, 16, 5, 0.1); 
    height: 100vh;
    width: 100vw;
    color: white;
  "
  >
    <b-navbar 
      variant="faded" 
      type="light" 
      style="
        display: flex;
        align-items: center;
        justify-content: space-between !important;
        padding-top: 0;
        padding-bottom: 0;
        margin: 0 40px;
      "
    >
      <b-navbar-brand href="#">
        <img src="@/assets/book_logo.png" alt="book logo" height="55px">
      </b-navbar-brand>
      <b-navbar-nav>
        <!-- 검색 -->
        <b-nav-item>
          <img class="animate__animated animate__fadeIn" @click="showInput" v-if="checkInput" width="40" style="margin-top: 1px;" src="data:image/svg+xml;base64,PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iaXNvLTg4NTktMSI/Pg0KPCEtLSBHZW5lcmF0b3I6IEFkb2JlIElsbHVzdHJhdG9yIDE5LjAuMCwgU1ZHIEV4cG9ydCBQbHVnLUluIC4gU1ZHIFZlcnNpb246IDYuMDAgQnVpbGQgMCkgIC0tPg0KPHN2ZyB2ZXJzaW9uPSIxLjEiIGlkPSJDYXBhXzEiIHhtbG5zPSJodHRwOi8vd3d3LnczLm9yZy8yMDAwL3N2ZyIgeG1sbnM6eGxpbms9Imh0dHA6Ly93d3cudzMub3JnLzE5OTkveGxpbmsiIHg9IjBweCIgeT0iMHB4Ig0KCSB2aWV3Qm94PSIwIDAgNTEyIDUxMiIgc3R5bGU9ImVuYWJsZS1iYWNrZ3JvdW5kOm5ldyAwIDAgNTEyIDUxMjsiIHhtbDpzcGFjZT0icHJlc2VydmUiPg0KPGc+DQoJPGc+DQoJCTxwYXRoIGQ9Ik0zMTAsMTkwYy01LjUyLDAtMTAsNC40OC0xMCwxMHM0LjQ4LDEwLDEwLDEwYzUuNTIsMCwxMC00LjQ4LDEwLTEwUzMxNS41MiwxOTAsMzEwLDE5MHoiLz4NCgk8L2c+DQo8L2c+DQo8Zz4NCgk8Zz4NCgkJPHBhdGggZD0iTTUwMC4yODEsNDQzLjcxOWwtMTMzLjQ4LTEzMy40OEMzODguNTQ2LDI3Ny40ODUsNDAwLDIzOS41NTUsNDAwLDIwMEM0MDAsODkuNzIsMzEwLjI4LDAsMjAwLDBTMCw4OS43MiwwLDIwMA0KCQkJczg5LjcyLDIwMCwyMDAsMjAwYzM5LjU1NiwwLDc3LjQ4Ni0xMS40NTUsMTEwLjIzOS0zMy4xOThsMzYuODk1LDM2Ljg5NWMwLjAwNSwwLjAwNSwwLjAxLDAuMDEsMC4wMTYsMC4wMTZsOTYuNTY4LDk2LjU2OA0KCQkJQzQ1MS4yNzYsNTA3LjgzOCw0NjEuMzE5LDUxMiw0NzIsNTEyYzEwLjY4MSwwLDIwLjcyNC00LjE2MiwyOC4yNzgtMTEuNzE2QzUwNy44MzcsNDkyLjczMSw1MTIsNDgyLjY4Nyw1MTIsNDcyDQoJCQlTNTA3LjgzNyw0NTEuMjY5LDUwMC4yODEsNDQzLjcxOXogTTMwNS41MzYsMzQ1LjcyN2MwLDAuMDAxLTAuMDAxLDAuMDAxLTAuMDAyLDAuMDAyQzI3NC42NjcsMzY4LjE0OSwyMzguMTc1LDM4MCwyMDAsMzgwDQoJCQljLTk5LjI1MiwwLTE4MC04MC43NDgtMTgwLTE4MFMxMDAuNzQ4LDIwLDIwMCwyMHMxODAsODAuNzQ4LDE4MCwxODBjMCwzOC4xNzUtMTEuODUxLDc0LjY2Ny0zNC4yNzIsMTA1LjUzNQ0KCQkJQzMzNC41MTEsMzIwLjk4OCwzMjAuOTg5LDMzNC41MTEsMzA1LjUzNiwzNDUuNzI3eiBNMzI2LjUxNiwzNTQuNzkzYzEwLjM1LTguNDY3LDE5LjgxMS0xNy45MjgsMjguMjc3LTI4LjI3N2wyOC4zNzEsMjguMzcxDQoJCQljLTguNjI4LDEwLjE4My0xOC4wOTQsMTkuNjUtMjguMjc3LDI4LjI3N0wzMjYuNTE2LDM1NC43OTN6IE00ODYuMTM5LDQ4Ni4xMzljLTMuNzgsMy43OC04LjgwMSw1Ljg2MS0xNC4xMzksNS44NjENCgkJCXMtMTAuMzU5LTIuMDgxLTE0LjEzOS01Ljg2MWwtODguNzk1LTg4Ljc5NWMxMC4xMjctOC42OTEsMTkuNTg3LTE4LjE1LDI4LjI3Ny0yOC4yNzdsODguNzk4LDg4Ljc5OA0KCQkJQzQ4OS45MTksNDYxLjYzOSw0OTIsNDY2LjY1OCw0OTIsNDcyQzQ5Miw0NzcuMzQyLDQ4OS45MTksNDgyLjM2MSw0ODYuMTM5LDQ4Ni4xMzl6Ii8+DQoJPC9nPg0KPC9nPg0KPGc+DQoJPGc+DQoJCTxwYXRoIGQ9Ik0yMDAsNDBjLTg4LjIyNSwwLTE2MCw3MS43NzUtMTYwLDE2MHM3MS43NzUsMTYwLDE2MCwxNjBzMTYwLTcxLjc3NSwxNjAtMTYwUzI4OC4yMjUsNDAsMjAwLDQweiBNMjAwLDM0MA0KCQkJYy03Ny4xOTYsMC0xNDAtNjIuODA0LTE0MC0xNDBTMTIyLjgwNCw2MCwyMDAsNjBzMTQwLDYyLjgwNCwxNDAsMTQwUzI3Ny4xOTYsMzQwLDIwMCwzNDB6Ii8+DQoJPC9nPg0KPC9nPg0KPGc+DQoJPGc+DQoJCTxwYXRoIGQ9Ik0zMTIuMDY1LDE1Ny4wNzNjLTguNjExLTIyLjQxMi0yMy42MDQtNDEuNTc0LTQzLjM2LTU1LjQxM0MyNDguNDc5LDg3LjQ5LDIyNC43MjEsODAsMjAwLDgwYy01LjUyMiwwLTEwLDQuNDc4LTEwLDEwDQoJCQljMCw1LjUyMiw0LjQ3OCwxMCwxMCwxMGM0MS4wOTksMCw3OC42MzEsMjUuODE4LDkzLjM5Niw2NC4yNDdjMS41MjgsMy45NzYsNS4zMTcsNi40MTYsOS4zMzcsNi40MTYNCgkJCWMxLjE5MiwwLDIuNDA1LTAuMjE1LDMuNTg0LTAuNjY4QzMxMS40NzIsMTY4LjAxNCwzMTQuMDQ2LDE2Mi4yMjksMzEyLjA2NSwxNTcuMDczeiIvPg0KCTwvZz4NCjwvZz4NCjxnPg0KPC9nPg0KPGc+DQo8L2c+DQo8Zz4NCjwvZz4NCjxnPg0KPC9nPg0KPGc+DQo8L2c+DQo8Zz4NCjwvZz4NCjxnPg0KPC9nPg0KPGc+DQo8L2c+DQo8Zz4NCjwvZz4NCjxnPg0KPC9nPg0KPGc+DQo8L2c+DQo8Zz4NCjwvZz4NCjxnPg0KPC9nPg0KPGc+DQo8L2c+DQo8Zz4NCjwvZz4NCjwvc3ZnPg0K" />
          <b-form-input 
            v-else style="height: 40px; margin-top: 1px; border-radius: 20px / 20px;" 
            class="formcolor animate__animated animate__bounce"
            placeholder="검색어를 입력하세요."
            type="text"
            @keypress.enter="hideInput"
          ></b-form-input>
        </b-nav-item>
        <!-- 마이페이지 -->
        <b-nav-item-dropdown right>
          <template #button-content>
            <b-avatar size="2.5rem" src="@/assets/푸들.png"></b-avatar>
          </template>
          <b-dropdown-item href="#">로그아웃</b-dropdown-item>
            <hr>
          <b-dropdown-item href="#">나의서재</b-dropdown-item>
        </b-nav-item-dropdown>
      </b-navbar-nav>
    </b-navbar>
    <section class="container">
      <div>
        <!-- 메인페이지 입니다 :) -->
      </div>
    </section>
  </div>
</template>

<script>
export default {
  data() {
    return {
      checkInput: true,
    }
  },
  methods: {
    showInput() {
      this.checkInput = false
    },
    hideInput() {
      this.checkInput = true
    },
  }
}
</script>

<style>
/*여기입니다요 id가 이거래 .. 선택자로 찍어버렸어 약간 포크 */
  #__BVID__10 > ul{
    background-color: #5c463d;
    border-radius: 10px/ 10px;
    border: 0px;
    width: 10px;
  }
  #__BVID__10__BV_toggle_{ 
    border : 0;
    outline-style: none;
    text-decoration-style: none; 
    outline-offset: 0;
  }
  .animate__animated {
    --animate-duration: 1s;
  }
  input[type="text"]:focus {
    border-color: rgba(78, 35, 0, 0.8);
    box-shadow: 0 1px 1px rgba(0, 0, 0, 0.075) inset, 0 0 8px rgba(63, 20, 0, 0.6);
    outline: 0 none;
  }
  .dropdown-item:hover{
    background-color: #5c463d;
    font-weight: bold;
    color: rgba(255, 255, 255, 1);
  }
  .dropdown-item .a:hover{
    color: none;
  }
  .dropdown-menu .dropdown-menu-right .show > li{
    display: flex;
    justify-items: center;
    /* background-color: #3D1605; */
  }
  .dropdown-menu .dropdown-menu-right .show > li > a:hover{
    background-color: red;
  }
  .dropdown-item{
    text-align: center;
    /* background-color: #3D1605; */
    color: rgba(255, 255, 255, 0.9);
    
  }
  hr {
    margin-left: 15px;
    margin-right: 15px;
    margin-top: 5px;
    margin-bottom: 5px;
    background-color: white;
  }
  
</style>