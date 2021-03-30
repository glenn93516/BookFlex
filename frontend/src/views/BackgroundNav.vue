<template>
  <div class="background" style="margin-top: 100px;">
    <b-navbar 
      variant="faded" 
      type="light" 
      class="background-nav"
    >
      <b-navbar-brand
        @click="goToMain"
      >
        <div style="width: 290px;">
          <img
            id="title-logo" 
            src="@/assets/hansam.png" 
            alt="book logo" 
            height="35px"
            class="hvr-grow"
          >
        </div>
      </b-navbar-brand>
      <div>
        <router-link :to="{ name: 'MainBook' }" class="temp-menu">
          RECOMMEND
        </router-link>
        <router-link :to="{ name: 'Login' }" class="temp-menu">
          COMMUNITY
        </router-link>
        <router-link :to="{ name: 'Profile' }" class="temp-menu">
          MYPLACE
        </router-link>
      </div>
      <b-navbar-nav style="display: flex; align-items: center; width: 290px; justify-content: flex-end;">
        <!-- 검색 -->
        <b-nav-item>
          <!-- <img class="animate__animated animate__fadeIn" @click="showInput" v-if="checkInput" width="40" style="margin-top: 1px;" src="data:image/svg+xml;base64,PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iaXNvLTg4NTktMSI/Pg0KPCEtLSBHZW5lcmF0b3I6IEFkb2JlIElsbHVzdHJhdG9yIDE5LjAuMCwgU1ZHIEV4cG9ydCBQbHVnLUluIC4gU1ZHIFZlcnNpb246IDYuMDAgQnVpbGQgMCkgIC0tPg0KPHN2ZyB2ZXJzaW9uPSIxLjEiIGlkPSJDYXBhXzEiIHhtbG5zPSJodHRwOi8vd3d3LnczLm9yZy8yMDAwL3N2ZyIgeG1sbnM6eGxpbms9Imh0dHA6Ly93d3cudzMub3JnLzE5OTkveGxpbmsiIHg9IjBweCIgeT0iMHB4Ig0KCSB2aWV3Qm94PSIwIDAgMzk2LjgxOSAzOTYuODE5IiBzdHlsZT0iZW5hYmxlLWJhY2tncm91bmQ6bmV3IDAgMCAzOTYuODE5IDM5Ni44MTk7IiB4bWw6c3BhY2U9InByZXNlcnZlIj4NCjxnPg0KCTxnPg0KCQk8cGF0aCBkPSJNMzUyLjEwMyw0NC42NzljLTU5LjIzNy01OS40MDEtMTU1LjQxMi01OS41MzUtMjE0LjgxMy0wLjI5OGMtNTcuMzE2LDU3LjE1Ny01OS43NDEsMTQ5LjIxOC01LjUxNSwyMDkuMzE0bC0xMS42LDExLjYNCgkJCWMtOS4zNzItOS4zNzMtMjQuNTY4LTkuMzc1LTMzLjk0MS0wLjAwM2MtMC4wMDEsMC4wMDEtMC4wMDIsMC4wMDItMC4wMDMsMC4wMDNsLTc5LjIsNzkuMg0KCQkJYy05LjM3Myw5LjM3Mi05LjM3NSwyNC41NjgtMC4wMDMsMzMuOTQxYzAuMDAxLDAuMDAxLDAuMDAyLDAuMDAyLDAuMDAzLDAuMDAzbDExLjMxMiwxMS4zMTJjOS4zNzIsOS4zNjksMjQuNTY0LDkuMzY5LDMzLjkzNiwwDQoJCQlsNzkuMi03OS4yYzkuMzczLTkuMzcyLDkuMzc1LTI0LjU2OCwwLjAwMy0zMy45NDFjLTAuMDAxLTAuMDAxLTAuMDAyLTAuMDAyLTAuMDAzLTAuMDAzbDExLjYtMTEuNg0KCQkJYzYyLjI0LDU2LjI0NywxNTguMjkyLDUxLjM4OCwyMTQuNTM5LTEwLjg1MUM0MTEuOTQ4LDE5NC4wMzcsNDA5LjUyMSwxMDEuODU2LDM1Mi4xMDMsNDQuNjc5eiBNMTIwLjE3NSwyOTkuMjMxbC03OS4yLDc5LjINCgkJCWMtMy4xMjQsMy4xMjMtOC4xODgsMy4xMjMtMTEuMzEyLDBsLTExLjMxMi0xMS4zMmMtMy4xMTgtMy4xMjMtMy4xMTgtOC4xODEsMC0xMS4zMDRsNzkuMi03OS4yYzMuMTY0LTMuMDI1LDguMTQ4LTMuMDI1LDExLjMxMiwwDQoJCQlsMTEuMzEyLDExLjMyQzEyMy4yOTMsMjkxLjA1LDEyMy4yOTMsMjk2LjEwOCwxMjAuMTc1LDI5OS4yMzF6IE0zNDAuNzk5LDI0OC4zMjdjLTUzLjExMiw1My4xMS0xMzkuMjIzLDUzLjEwOS0xOTIuMzMzLTAuMDAzDQoJCQlzLTUzLjEwOS0xMzkuMjIzLDAuMDAzLTE5Mi4zMzNjNTMuMTExLTUzLjEwOSwxMzkuMjE5LTUzLjEwOSwxOTIuMzMsMEMzOTMuODI0LDEwOS4xMzksMzkzLjgyNCwxOTUuMTc5LDM0MC43OTksMjQ4LjMyN3oiLz4NCgk8L2c+DQo8L2c+DQo8Zz4NCgk8Zz4NCgkJPHBhdGggZD0iTTMyOS40NzksNjcuMzAzTDMyOS40NzksNjcuMzAzbC0xMS4zMTIsMTEuMzEyYzQwLjU0NSw0MC42NDQsNDAuNTQ1LDEwNi40MzYsMCwxNDcuMDhsMTEuMzEyLDExLjMxMg0KCQkJQzM3Ni4yNzUsMTkwLjExNywzNzYuMjc1LDExNC4xOTMsMzI5LjQ3OSw2Ny4zMDN6Ii8+DQoJPC9nPg0KPC9nPg0KPGc+DQo8L2c+DQo8Zz4NCjwvZz4NCjxnPg0KPC9nPg0KPGc+DQo8L2c+DQo8Zz4NCjwvZz4NCjxnPg0KPC9nPg0KPGc+DQo8L2c+DQo8Zz4NCjwvZz4NCjxnPg0KPC9nPg0KPGc+DQo8L2c+DQo8Zz4NCjwvZz4NCjxnPg0KPC9nPg0KPGc+DQo8L2c+DQo8Zz4NCjwvZz4NCjxnPg0KPC9nPg0KPC9zdmc+DQo=" /> -->
          <img height="30px" class="hvr-rotate" @click="showInput" v-if="checkInput" width="40" style="margin-top: 1px;" src="data:image/svg+xml;base64,PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iaXNvLTg4NTktMSI/Pg0KPCEtLSBHZW5lcmF0b3I6IEFkb2JlIElsbHVzdHJhdG9yIDE5LjAuMCwgU1ZHIEV4cG9ydCBQbHVnLUluIC4gU1ZHIFZlcnNpb246IDYuMDAgQnVpbGQgMCkgIC0tPg0KPHN2ZyB2ZXJzaW9uPSIxLjEiIGlkPSJMYXllcl8xIiB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHhtbG5zOnhsaW5rPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5L3hsaW5rIiB4PSIwcHgiIHk9IjBweCINCgkgdmlld0JveD0iMCAwIDUxMS45OTkgNTExLjk5OSIgc3R5bGU9ImVuYWJsZS1iYWNrZ3JvdW5kOm5ldyAwIDAgNTExLjk5OSA1MTEuOTk5OyIgeG1sOnNwYWNlPSJwcmVzZXJ2ZSI+DQo8Zz4NCgk8Zz4NCgkJPGc+DQoJCQk8cGF0aCBkPSJNNTAxLjAxMSw0NDcuOTAyTDM0Ny41NCwyOTQuNDMxYzIwLjAwOS0zMC4wNDYsMzEuNjg5LTY2LjA5MywzMS42ODktMTA0LjgyMUMzNzkuMjI5LDg1LjA1OSwyOTQuMTcsMCwxODkuNjE4LDANCgkJCQlTMC4wMDgsODUuMDU5LDAuMDA4LDE4OS42MXM4NS4wNTksMTg5LjYxLDE4OS42MSwxODkuNjFjMzguNzI4LDAsNzQuNzc0LTExLjY4LDEwNC44MjEtMzEuNjg5TDQ0Ny45MSw1MDEuMDAyDQoJCQkJYzcuMDkyLDcuMDkyLDE2LjUyMSwxMC45OTcsMjYuNTUxLDEwLjk5N2MxMC4wMywwLDE5LjQ1OC0zLjkwNSwyNi41NS0xMC45OThDNTE1LjY1LDQ4Ni4zNjMsNTE1LjY1LDQ2Mi41NDIsNTAxLjAxMSw0NDcuOTAyeg0KCQkJCSBNMTg5LjYxOCwzNTkuMjJjLTkzLjUyMywwLTE2OS42MS03Ni4wODYtMTY5LjYxLTE2OS42MUMyMC4wMDgsOTYuMDg3LDk2LjA5NSwyMCwxODkuNjE4LDIwczE2OS42MSw3Ni4wODcsMTY5LjYxLDE2OS42MQ0KCQkJCVMyODMuMTQxLDM1OS4yMiwxODkuNjE4LDM1OS4yMnogTTQ4Ni44NjgsNDg2Ljg2Yy0zLjMxNCwzLjMxNC03LjcyMSw1LjE0LTEyLjQwNyw1LjE0Yy00LjY4OCwwLTkuMDk0LTEuODI1LTEyLjQwOC01LjE0DQoJCQkJTDMxMC42NDgsMzM1LjQ1NWM5LjAxMy03LjQ5MiwxNy4zMjMtMTUuODAyLDI0LjgxNS0yNC44MTVsMTUxLjQwNSwxNTEuNDA0QzQ5My43MDksNDY4Ljg4Nyw0OTMuNzA5LDQ4MC4wMTksNDg2Ljg2OCw0ODYuODZ6Ii8+DQoJCQk8cGF0aCBkPSJNMTg5LjYxOSw0NS4xNzdjLTc5LjY0MSwwLTE0NC40MzMsNjQuNzkyLTE0NC40MzMsMTQ0LjQzM2MwLDUuNTIyLDQuNDc4LDEwLDEwLDEwYzUuNTIyLDAsMTAtNC40NzcsMTAtMTANCgkJCQljMC02OC42MTMsNTUuODIxLTEyNC40MzMsMTI0LjQzMy0xMjQuNDMzYzUuNTIyLDAsMTAtNC40NzgsMTAtMTBTMTk1LjE0MSw0NS4xNzcsMTg5LjYxOSw0NS4xNzd6Ii8+DQoJCQk8cGF0aCBkPSJNMzIwLjAyOSwxMjcuNDU3Yy0xNC4yMjMtMjkuNzktMzguNDYxLTU0LjAyOS02OC4yNS02OC4yNTNjLTQuOTg0LTIuMzgxLTEwLjk1NC0wLjI2OC0xMy4zMzMsNC43MTYNCgkJCQljLTIuMzc5LDQuOTgzLTAuMjY4LDEwLjk1Myw0LjcxNiwxMy4zMzNjMjUuNjcyLDEyLjI1OCw0Ni41NjEsMzMuMTQ3LDU4LjgxOCw1OC44MjFjMS43MTYsMy41OTMsNS4yOTgsNS42OTMsOS4wMzEsNS42OTMNCgkJCQljMS40NDQsMCwyLjkxMS0wLjMxNCw0LjMwMi0wLjk3N0MzMjAuMjk3LDEzOC40MSwzMjIuNDA4LDEzMi40NDEsMzIwLjAyOSwxMjcuNDU3eiIvPg0KCQk8L2c+DQoJPC9nPg0KPC9nPg0KPGc+DQo8L2c+DQo8Zz4NCjwvZz4NCjxnPg0KPC9nPg0KPGc+DQo8L2c+DQo8Zz4NCjwvZz4NCjxnPg0KPC9nPg0KPGc+DQo8L2c+DQo8Zz4NCjwvZz4NCjxnPg0KPC9nPg0KPGc+DQo8L2c+DQo8Zz4NCjwvZz4NCjxnPg0KPC9nPg0KPGc+DQo8L2c+DQo8Zz4NCjwvZz4NCjxnPg0KPC9nPg0KPC9zdmc+DQo=" />
          <!-- <img height="30px" class="animate__animated animate__fadeIn" @click="showInput" v-if="checkInput" width="40" style="margin-top: 1px;" src="data:image/svg+xml;base64,PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iaXNvLTg4NTktMSI/Pg0KPCEtLSBHZW5lcmF0b3I6IEFkb2JlIElsbHVzdHJhdG9yIDE5LjAuMCwgU1ZHIEV4cG9ydCBQbHVnLUluIC4gU1ZHIFZlcnNpb246IDYuMDAgQnVpbGQgMCkgIC0tPg0KPHN2ZyB2ZXJzaW9uPSIxLjEiIGlkPSJDYXBhXzEiIHhtbG5zPSJodHRwOi8vd3d3LnczLm9yZy8yMDAwL3N2ZyIgeG1sbnM6eGxpbms9Imh0dHA6Ly93d3cudzMub3JnLzE5OTkveGxpbmsiIHg9IjBweCIgeT0iMHB4Ig0KCSB2aWV3Qm94PSIwIDAgNTEyIDUxMiIgc3R5bGU9ImVuYWJsZS1iYWNrZ3JvdW5kOm5ldyAwIDAgNTEyIDUxMjsiIHhtbDpzcGFjZT0icHJlc2VydmUiPg0KPGc+DQoJPGc+DQoJCTxwYXRoIGQ9Ik0zMTAsMTkwYy01LjUyLDAtMTAsNC40OC0xMCwxMHM0LjQ4LDEwLDEwLDEwYzUuNTIsMCwxMC00LjQ4LDEwLTEwUzMxNS41MiwxOTAsMzEwLDE5MHoiLz4NCgk8L2c+DQo8L2c+DQo8Zz4NCgk8Zz4NCgkJPHBhdGggZD0iTTUwMC4yODEsNDQzLjcxOWwtMTMzLjQ4LTEzMy40OEMzODguNTQ2LDI3Ny40ODUsNDAwLDIzOS41NTUsNDAwLDIwMEM0MDAsODkuNzIsMzEwLjI4LDAsMjAwLDBTMCw4OS43MiwwLDIwMA0KCQkJczg5LjcyLDIwMCwyMDAsMjAwYzM5LjU1NiwwLDc3LjQ4Ni0xMS40NTUsMTEwLjIzOS0zMy4xOThsMzYuODk1LDM2Ljg5NWMwLjAwNSwwLjAwNSwwLjAxLDAuMDEsMC4wMTYsMC4wMTZsOTYuNTY4LDk2LjU2OA0KCQkJQzQ1MS4yNzYsNTA3LjgzOCw0NjEuMzE5LDUxMiw0NzIsNTEyYzEwLjY4MSwwLDIwLjcyNC00LjE2MiwyOC4yNzgtMTEuNzE2QzUwNy44MzcsNDkyLjczMSw1MTIsNDgyLjY4Nyw1MTIsNDcyDQoJCQlTNTA3LjgzNyw0NTEuMjY5LDUwMC4yODEsNDQzLjcxOXogTTMwNS41MzYsMzQ1LjcyN2MwLDAuMDAxLTAuMDAxLDAuMDAxLTAuMDAyLDAuMDAyQzI3NC42NjcsMzY4LjE0OSwyMzguMTc1LDM4MCwyMDAsMzgwDQoJCQljLTk5LjI1MiwwLTE4MC04MC43NDgtMTgwLTE4MFMxMDAuNzQ4LDIwLDIwMCwyMHMxODAsODAuNzQ4LDE4MCwxODBjMCwzOC4xNzUtMTEuODUxLDc0LjY2Ny0zNC4yNzIsMTA1LjUzNQ0KCQkJQzMzNC41MTEsMzIwLjk4OCwzMjAuOTg5LDMzNC41MTEsMzA1LjUzNiwzNDUuNzI3eiBNMzI2LjUxNiwzNTQuNzkzYzEwLjM1LTguNDY3LDE5LjgxMS0xNy45MjgsMjguMjc3LTI4LjI3N2wyOC4zNzEsMjguMzcxDQoJCQljLTguNjI4LDEwLjE4My0xOC4wOTQsMTkuNjUtMjguMjc3LDI4LjI3N0wzMjYuNTE2LDM1NC43OTN6IE00ODYuMTM5LDQ4Ni4xMzljLTMuNzgsMy43OC04LjgwMSw1Ljg2MS0xNC4xMzksNS44NjENCgkJCXMtMTAuMzU5LTIuMDgxLTE0LjEzOS01Ljg2MWwtODguNzk1LTg4Ljc5NWMxMC4xMjctOC42OTEsMTkuNTg3LTE4LjE1LDI4LjI3Ny0yOC4yNzdsODguNzk4LDg4Ljc5OA0KCQkJQzQ4OS45MTksNDYxLjYzOSw0OTIsNDY2LjY1OCw0OTIsNDcyQzQ5Miw0NzcuMzQyLDQ4OS45MTksNDgyLjM2MSw0ODYuMTM5LDQ4Ni4xMzl6Ii8+DQoJPC9nPg0KPC9nPg0KPGc+DQoJPGc+DQoJCTxwYXRoIGQ9Ik0yMDAsNDBjLTg4LjIyNSwwLTE2MCw3MS43NzUtMTYwLDE2MHM3MS43NzUsMTYwLDE2MCwxNjBzMTYwLTcxLjc3NSwxNjAtMTYwUzI4OC4yMjUsNDAsMjAwLDQweiBNMjAwLDM0MA0KCQkJYy03Ny4xOTYsMC0xNDAtNjIuODA0LTE0MC0xNDBTMTIyLjgwNCw2MCwyMDAsNjBzMTQwLDYyLjgwNCwxNDAsMTQwUzI3Ny4xOTYsMzQwLDIwMCwzNDB6Ii8+DQoJPC9nPg0KPC9nPg0KPGc+DQoJPGc+DQoJCTxwYXRoIGQ9Ik0zMTIuMDY1LDE1Ny4wNzNjLTguNjExLTIyLjQxMi0yMy42MDQtNDEuNTc0LTQzLjM2LTU1LjQxM0MyNDguNDc5LDg3LjQ5LDIyNC43MjEsODAsMjAwLDgwYy01LjUyMiwwLTEwLDQuNDc4LTEwLDEwDQoJCQljMCw1LjUyMiw0LjQ3OCwxMCwxMCwxMGM0MS4wOTksMCw3OC42MzEsMjUuODE4LDkzLjM5Niw2NC4yNDdjMS41MjgsMy45NzYsNS4zMTcsNi40MTYsOS4zMzcsNi40MTYNCgkJCWMxLjE5MiwwLDIuNDA1LTAuMjE1LDMuNTg0LTAuNjY4QzMxMS40NzIsMTY4LjAxNCwzMTQuMDQ2LDE2Mi4yMjksMzEyLjA2NSwxNTcuMDczeiIvPg0KCTwvZz4NCjwvZz4NCjxnPg0KPC9nPg0KPGc+DQo8L2c+DQo8Zz4NCjwvZz4NCjxnPg0KPC9nPg0KPGc+DQo8L2c+DQo8Zz4NCjwvZz4NCjxnPg0KPC9nPg0KPGc+DQo8L2c+DQo8Zz4NCjwvZz4NCjxnPg0KPC9nPg0KPGc+DQo8L2c+DQo8Zz4NCjwvZz4NCjxnPg0KPC9nPg0KPGc+DQo8L2c+DQo8Zz4NCjwvZz4NCjwvc3ZnPg0K" /> -->
          <b-form-input 
            id="navInput"
            v-else 
            style="display: absolute; left: 0; height: 40px; margin-top: 1px; border-radius: 20px / 20px;" 
            class="formcolor animate__animated animate__headShake"
            placeholder="검색어를 입력하세요."
            type="text"
            @keypress.enter="hideInput"
          ></b-form-input>
        </b-nav-item>
        <!-- 마이페이지 -->
        <b-nav-item-dropdown right>
          <template #button-content>
            <b-avatar size="2.5rem" :src="userInfo.userProfileImg"></b-avatar>
          </template>

          <b-dropdown-item
            @click="goToProfile"
            style="flex: inline-block; width: 100px; text-align: center; justify-content: center; margin: 0;"
          >
            <span style="font-size: 0.8rem;">프로필</span>
          </b-dropdown-item>
          <hr class="list-hr">
          
          <b-dropdown-item
            v-if="!isLogin" 
            href="/login" 
            style="flex: inline-block; width: 100px; text-align: center; justify-content: center; margin: 0;"
          >
            <span style="font-size: 0.8rem;">로그인</span>
          </b-dropdown-item>

          <b-dropdown-item
            v-if="isLogin" 
            @click="Logout"
            style="flex: inline-block; width: 100px; text-align: center; justify-content: center; margin: 0;"
          >
            <span style="font-size: 0.8rem;">로그아웃</span>
          </b-dropdown-item>
            <hr class="list-hr">

          <b-dropdown-item 
            v-if="isLogin" 
            @click="goToProfile"
            style="flex: inline-block; width: 100px; text-align: center; justify-content: center; margin: 0;"
          >
            <span style="font-size: 0.8rem;">나의서재</span>
          </b-dropdown-item>
            <!-- <hr class="list-hr"> -->

          <b-dropdown-item 
            v-if="!isLogin"
            href="/signup/1" 
            style="flex: inline-block; width: 100px; text-align: center; justify-content: center; margin: 0;"
          >
            <span style="font-size: 0.8rem;">회원가입</span>
          </b-dropdown-item>
        </b-nav-item-dropdown>
      </b-navbar-nav>
    </b-navbar>
    <!-- 메인페이지 입니다 :) -->
    <section class="container">
      <router-view></router-view>
    </section>
    <aside 
      style="
        position: fixed;
        bottom: 0px;
        right: 50px;
      "  
    >
      <!-- <img
        width="120px"
        height="120px"
        :src="postBox"
        @mouseover="postBox = openPostBox"
      /> -->

      <!-- 여기는 우체통 -->
      <img
        class="postbox"
        width="120px"
        height="120px" 
        :src="postBox"
        @mouseover="tempOverImg()"
        @mouseout="tempOutImg()"
        @click="postBoxClick" 
      />

    </aside>
  </div>
</template>

<script>
export default {
  data() {
    return {
      checkInput: true,
      isLogin: false,
      pageName: "",
      postBox: "data:image/svg+xml;base64,PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iaXNvLTg4NTktMSI/Pg0KPCEtLSBHZW5lcmF0b3I6IEFkb2JlIElsbHVzdHJhdG9yIDE5LjAuMCwgU1ZHIEV4cG9ydCBQbHVnLUluIC4gU1ZHIFZlcnNpb246IDYuMDAgQnVpbGQgMCkgIC0tPg0KPHN2ZyB2ZXJzaW9uPSIxLjEiIGlkPSJDYXBhXzEiIHhtbG5zPSJodHRwOi8vd3d3LnczLm9yZy8yMDAwL3N2ZyIgeG1sbnM6eGxpbms9Imh0dHA6Ly93d3cudzMub3JnLzE5OTkveGxpbmsiIHg9IjBweCIgeT0iMHB4Ig0KCSB2aWV3Qm94PSIwIDAgNTEyLjAwNSA1MTIuMDA1IiBzdHlsZT0iZW5hYmxlLWJhY2tncm91bmQ6bmV3IDAgMCA1MTIuMDA1IDUxMi4wMDU7IiB4bWw6c3BhY2U9InByZXNlcnZlIj4NCjxyZWN0IHg9IjIyNy43NzgiIHk9IjI4NC41NTQiIHN0eWxlPSJmaWxsOiM5NUFCQjA7IiB3aWR0aD0iODkuNTExIiBoZWlnaHQ9IjIxMC45MTIiLz4NCjxnPg0KCTxyZWN0IHg9IjIyNy43NzgiIHk9IjI4NC41NTQiIHN0eWxlPSJmaWxsOiM3ODg3OEM7IiB3aWR0aD0iODkuNTExIiBoZWlnaHQ9IjQ1LjY0MyIvPg0KCTxyZWN0IHg9IjIyNy43NzgiIHk9IjI4NC41NTQiIHN0eWxlPSJmaWxsOiM3ODg3OEM7IiB3aWR0aD0iMzkuMjU3IiBoZWlnaHQ9IjIxMC45MTIiLz4NCjwvZz4NCjxyZWN0IHk9IjI3MC45MzMiIHN0eWxlPSJmaWxsOiNGRkNBM0I7IiB3aWR0aD0iNTEyLjAwNSIgaGVpZ2h0PSIyOC4yNjYiLz4NCjxyZWN0IHk9IjI3MC45MzMiIHN0eWxlPSJmaWxsOiNFNUIwMkM7IiB3aWR0aD0iMjIxLjQxMyIgaGVpZ2h0PSIyOC4yNjUiLz4NCjxwYXRoIHN0eWxlPSJmaWxsOiMwMDg2Q0M7IiBkPSJNNTExLjk5OCwxMjcuMjUxVjI3MC45M0gxODUuMzgxTDE1OC45NDYsNjIuMDgxbC01Ljc1OC00NS41NDFoMjQ4LjEwOQ0KCWMzNi43NzgsMCw2OS4zNjksMTcuOTQ0LDg5LjQ5LDQ1LjU0MUM1MDQuMTM2LDgwLjM3LDUxMS45OTgsMTAyLjg4OSw1MTEuOTk4LDEyNy4yNTF6Ii8+DQo8Zz4NCgk8cGF0aCBzdHlsZT0iZmlsbDojMDI3QUFGOyIgZD0iTTE1My4xODcsMTYuNTQ0TDE1My4xODcsMTYuNTQ0Yy02MS4xNDEsMC0xMTAuNzA2LDQ5LjU2NC0xMTAuNzA2LDExMC43MDZ2MTQzLjY4MmgyMjEuNDExVjEyNy4yNQ0KCQlDMjYzLjg5Myw2Ni4xMDgsMjE0LjMyOCwxNi41NDQsMTUzLjE4NywxNi41NDR6Ii8+DQoJPHBhdGggc3R5bGU9ImZpbGw6IzAyN0FBRjsiIGQ9Ik0zMjAuOTcyLDE2LjU0NGgtOTUuNzg4YzY5LjU1MSwyNC4xOTEsNTguNTI1LDg3LjY5LDU3LjYxLDE3My4wODYNCgkJYy0wLjA1OSw1LjQ5Myw0LjM5Miw5Ljk3OSw5Ljg4NSw5Ljk3OWg0MC4zMTFMMzIwLjk3MiwxNi41NDQiLz4NCjwvZz4NCjxwYXRoIHN0eWxlPSJmaWxsOiNDMTEwMUQ7IiBkPSJNMzA0LjgxNCwyMDAuMjY0aDIwLjgwMmMzLjc5NSwwLDYuODcyLTMuMDc3LDYuODcyLTYuODcyVjkzLjExNmMwLTMuNzk1LDMuMDc3LTYuODcyLDYuODcyLTYuODcyDQoJaDU1LjM0OWMzLjc5NSwwLDYuODcyLTMuMDc3LDYuODcyLTYuODcyVjIzLjQxMmMwLTMuNzk1LTMuMDc3LTYuODcyLTYuODcyLTYuODcyaC02NS41NTZjLTE3LjIzNywwLTMxLjIxMiwxMy45NzQtMzEuMjEyLDMxLjIxMg0KCXYxNDUuNjQxQzI5Ny45NDIsMTk3LjE4NywzMDEuMDE5LDIwMC4yNjQsMzA0LjgxNCwyMDAuMjY0eiIvPg0KPGc+DQoJPHBhdGggc3R5bGU9ImZpbGw6IzMwMkQzNjsiIGQ9Ik0xNTMuMTg3LDExNi41OTVjLTE4Ljk1OCwwLTM0LjM4Mi0xNS40MjMtMzQuMzgyLTM0LjM4MnMxNS40MjMtMzQuMzgyLDM0LjM4Mi0zNC4zODINCgkJczM0LjM4MSwxNS40MjMsMzQuMzgxLDM0LjM4MlMxNzIuMTQ1LDExNi41OTUsMTUzLjE4NywxMTYuNTk1eiBNMTUzLjE4Nyw2My41MzVjLTEwLjI5OSwwLTE4LjY3OCw4LjM3OC0xOC42NzgsMTguNjc4DQoJCXM4LjM3OCwxOC42NzgsMTguNjc4LDE4LjY3OGMxMC4zLDAsMTguNjc3LTguMzc4LDE4LjY3Ny0xOC42NzhTMTYzLjQ4Niw2My41MzUsMTUzLjE4Nyw2My41MzV6Ii8+DQoJPHBhdGggc3R5bGU9ImZpbGw6IzMwMkQzNjsiIGQ9Ik0yMjAuMTksMTY5LjYzSDg2LjE4NGMtNC4zMzYsMC03Ljg1Mi0zLjUxNi03Ljg1Mi03Ljg1MmMwLTQuMzM2LDMuNTE2LTcuODUyLDcuODUyLTcuODUySDIyMC4xOQ0KCQljNC4zMzYsMCw3Ljg1MiwzLjUxNiw3Ljg1Miw3Ljg1MkMyMjguMDQyLDE2Ni4xMTUsMjI0LjUyNiwxNjkuNjMsMjIwLjE5LDE2OS42M3oiLz4NCgk8cGF0aCBzdHlsZT0iZmlsbDojMzAyRDM2OyIgZD0iTTE5Ny44NywyMTEuNTA3aC04OS4zNjdjLTQuMzM2LDAtNy44NTItMy41MTYtNy44NTItNy44NTJjMC00LjMzNiwzLjUxNi03Ljg1Miw3Ljg1Mi03Ljg1Mmg4OS4zNjcNCgkJYzQuMzM2LDAsNy44NTIsMy41MTYsNy44NTIsNy44NTJDMjA1LjcyMiwyMDcuOTkyLDIwMi4yMDcsMjExLjUwNywxOTcuODcsMjExLjUwN3oiLz4NCjwvZz4NCjxnPg0KPC9nPg0KPGc+DQo8L2c+DQo8Zz4NCjwvZz4NCjxnPg0KPC9nPg0KPGc+DQo8L2c+DQo8Zz4NCjwvZz4NCjxnPg0KPC9nPg0KPGc+DQo8L2c+DQo8Zz4NCjwvZz4NCjxnPg0KPC9nPg0KPGc+DQo8L2c+DQo8Zz4NCjwvZz4NCjxnPg0KPC9nPg0KPGc+DQo8L2c+DQo8Zz4NCjwvZz4NCjwvc3ZnPg0K",
      closePostBox: "data:image/svg+xml;base64,PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iaXNvLTg4NTktMSI/Pg0KPCEtLSBHZW5lcmF0b3I6IEFkb2JlIElsbHVzdHJhdG9yIDE5LjAuMCwgU1ZHIEV4cG9ydCBQbHVnLUluIC4gU1ZHIFZlcnNpb246IDYuMDAgQnVpbGQgMCkgIC0tPg0KPHN2ZyB2ZXJzaW9uPSIxLjEiIGlkPSJDYXBhXzEiIHhtbG5zPSJodHRwOi8vd3d3LnczLm9yZy8yMDAwL3N2ZyIgeG1sbnM6eGxpbms9Imh0dHA6Ly93d3cudzMub3JnLzE5OTkveGxpbmsiIHg9IjBweCIgeT0iMHB4Ig0KCSB2aWV3Qm94PSIwIDAgNTEyLjAwNSA1MTIuMDA1IiBzdHlsZT0iZW5hYmxlLWJhY2tncm91bmQ6bmV3IDAgMCA1MTIuMDA1IDUxMi4wMDU7IiB4bWw6c3BhY2U9InByZXNlcnZlIj4NCjxyZWN0IHg9IjIyNy43NzgiIHk9IjI4NC41NTQiIHN0eWxlPSJmaWxsOiM5NUFCQjA7IiB3aWR0aD0iODkuNTExIiBoZWlnaHQ9IjIxMC45MTIiLz4NCjxnPg0KCTxyZWN0IHg9IjIyNy43NzgiIHk9IjI4NC41NTQiIHN0eWxlPSJmaWxsOiM3ODg3OEM7IiB3aWR0aD0iODkuNTExIiBoZWlnaHQ9IjQ1LjY0MyIvPg0KCTxyZWN0IHg9IjIyNy43NzgiIHk9IjI4NC41NTQiIHN0eWxlPSJmaWxsOiM3ODg3OEM7IiB3aWR0aD0iMzkuMjU3IiBoZWlnaHQ9IjIxMC45MTIiLz4NCjwvZz4NCjxyZWN0IHk9IjI3MC45MzMiIHN0eWxlPSJmaWxsOiNGRkNBM0I7IiB3aWR0aD0iNTEyLjAwNSIgaGVpZ2h0PSIyOC4yNjYiLz4NCjxyZWN0IHk9IjI3MC45MzMiIHN0eWxlPSJmaWxsOiNFNUIwMkM7IiB3aWR0aD0iMjIxLjQxMyIgaGVpZ2h0PSIyOC4yNjUiLz4NCjxwYXRoIHN0eWxlPSJmaWxsOiMwMDg2Q0M7IiBkPSJNNTExLjk5OCwxMjcuMjUxVjI3MC45M0gxODUuMzgxTDE1OC45NDYsNjIuMDgxbC01Ljc1OC00NS41NDFoMjQ4LjEwOQ0KCWMzNi43NzgsMCw2OS4zNjksMTcuOTQ0LDg5LjQ5LDQ1LjU0MUM1MDQuMTM2LDgwLjM3LDUxMS45OTgsMTAyLjg4OSw1MTEuOTk4LDEyNy4yNTF6Ii8+DQo8Zz4NCgk8cGF0aCBzdHlsZT0iZmlsbDojMDI3QUFGOyIgZD0iTTE1My4xODcsMTYuNTQ0TDE1My4xODcsMTYuNTQ0Yy02MS4xNDEsMC0xMTAuNzA2LDQ5LjU2NC0xMTAuNzA2LDExMC43MDZ2MTQzLjY4MmgyMjEuNDExVjEyNy4yNQ0KCQlDMjYzLjg5Myw2Ni4xMDgsMjE0LjMyOCwxNi41NDQsMTUzLjE4NywxNi41NDR6Ii8+DQoJPHBhdGggc3R5bGU9ImZpbGw6IzAyN0FBRjsiIGQ9Ik0zMjAuOTcyLDE2LjU0NGgtOTUuNzg4YzY5LjU1MSwyNC4xOTEsNTguNTI1LDg3LjY5LDU3LjYxLDE3My4wODYNCgkJYy0wLjA1OSw1LjQ5Myw0LjM5Miw5Ljk3OSw5Ljg4NSw5Ljk3OWg0MC4zMTFMMzIwLjk3MiwxNi41NDQiLz4NCjwvZz4NCjxwYXRoIHN0eWxlPSJmaWxsOiNDMTEwMUQ7IiBkPSJNMzA0LjgxNCwyMDAuMjY0aDIwLjgwMmMzLjc5NSwwLDYuODcyLTMuMDc3LDYuODcyLTYuODcyVjkzLjExNmMwLTMuNzk1LDMuMDc3LTYuODcyLDYuODcyLTYuODcyDQoJaDU1LjM0OWMzLjc5NSwwLDYuODcyLTMuMDc3LDYuODcyLTYuODcyVjIzLjQxMmMwLTMuNzk1LTMuMDc3LTYuODcyLTYuODcyLTYuODcyaC02NS41NTZjLTE3LjIzNywwLTMxLjIxMiwxMy45NzQtMzEuMjEyLDMxLjIxMg0KCXYxNDUuNjQxQzI5Ny45NDIsMTk3LjE4NywzMDEuMDE5LDIwMC4yNjQsMzA0LjgxNCwyMDAuMjY0eiIvPg0KPGc+DQoJPHBhdGggc3R5bGU9ImZpbGw6IzMwMkQzNjsiIGQ9Ik0xNTMuMTg3LDExNi41OTVjLTE4Ljk1OCwwLTM0LjM4Mi0xNS40MjMtMzQuMzgyLTM0LjM4MnMxNS40MjMtMzQuMzgyLDM0LjM4Mi0zNC4zODINCgkJczM0LjM4MSwxNS40MjMsMzQuMzgxLDM0LjM4MlMxNzIuMTQ1LDExNi41OTUsMTUzLjE4NywxMTYuNTk1eiBNMTUzLjE4Nyw2My41MzVjLTEwLjI5OSwwLTE4LjY3OCw4LjM3OC0xOC42NzgsMTguNjc4DQoJCXM4LjM3OCwxOC42NzgsMTguNjc4LDE4LjY3OGMxMC4zLDAsMTguNjc3LTguMzc4LDE4LjY3Ny0xOC42NzhTMTYzLjQ4Niw2My41MzUsMTUzLjE4Nyw2My41MzV6Ii8+DQoJPHBhdGggc3R5bGU9ImZpbGw6IzMwMkQzNjsiIGQ9Ik0yMjAuMTksMTY5LjYzSDg2LjE4NGMtNC4zMzYsMC03Ljg1Mi0zLjUxNi03Ljg1Mi03Ljg1MmMwLTQuMzM2LDMuNTE2LTcuODUyLDcuODUyLTcuODUySDIyMC4xOQ0KCQljNC4zMzYsMCw3Ljg1MiwzLjUxNiw3Ljg1Miw3Ljg1MkMyMjguMDQyLDE2Ni4xMTUsMjI0LjUyNiwxNjkuNjMsMjIwLjE5LDE2OS42M3oiLz4NCgk8cGF0aCBzdHlsZT0iZmlsbDojMzAyRDM2OyIgZD0iTTE5Ny44NywyMTEuNTA3aC04OS4zNjdjLTQuMzM2LDAtNy44NTItMy41MTYtNy44NTItNy44NTJjMC00LjMzNiwzLjUxNi03Ljg1Miw3Ljg1Mi03Ljg1Mmg4OS4zNjcNCgkJYzQuMzM2LDAsNy44NTIsMy41MTYsNy44NTIsNy44NTJDMjA1LjcyMiwyMDcuOTkyLDIwMi4yMDcsMjExLjUwNywxOTcuODcsMjExLjUwN3oiLz4NCjwvZz4NCjxnPg0KPC9nPg0KPGc+DQo8L2c+DQo8Zz4NCjwvZz4NCjxnPg0KPC9nPg0KPGc+DQo8L2c+DQo8Zz4NCjwvZz4NCjxnPg0KPC9nPg0KPGc+DQo8L2c+DQo8Zz4NCjwvZz4NCjxnPg0KPC9nPg0KPGc+DQo8L2c+DQo8Zz4NCjwvZz4NCjxnPg0KPC9nPg0KPGc+DQo8L2c+DQo8Zz4NCjwvZz4NCjwvc3ZnPg0K",
      // openPostBox: "data:image/svg+xml;base64,PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iaXNvLTg4NTktMSI/Pg0KPCEtLSBHZW5lcmF0b3I6IEFkb2JlIElsbHVzdHJhdG9yIDE5LjAuMCwgU1ZHIEV4cG9ydCBQbHVnLUluIC4gU1ZHIFZlcnNpb246IDYuMDAgQnVpbGQgMCkgIC0tPg0KPHN2ZyB2ZXJzaW9uPSIxLjEiIGlkPSJDYXBhXzEiIHhtbG5zPSJodHRwOi8vd3d3LnczLm9yZy8yMDAwL3N2ZyIgeG1sbnM6eGxpbms9Imh0dHA6Ly93d3cudzMub3JnLzE5OTkveGxpbmsiIHg9IjBweCIgeT0iMHB4Ig0KCSB2aWV3Qm94PSIwIDAgNTEyLjAwNSA1MTIuMDA1IiBzdHlsZT0iZW5hYmxlLWJhY2tncm91bmQ6bmV3IDAgMCA1MTIuMDA1IDUxMi4wMDU7IiB4bWw6c3BhY2U9InByZXNlcnZlIj4NCjxyZWN0IHg9IjIyNy43NzgiIHk9IjI4Ny42OTQiIHN0eWxlPSJmaWxsOiM5NUFCQjA7IiB3aWR0aD0iODkuNTExIiBoZWlnaHQ9IjIwNy43NzEiLz4NCjxnPg0KCTxyZWN0IHg9IjIyNy43NzgiIHk9IjI4Ny42OTQiIHN0eWxlPSJmaWxsOiM3ODg3OEM7IiB3aWR0aD0iMzkuMjU3IiBoZWlnaHQ9IjIwNy43NzEiLz4NCgk8cmVjdCB4PSIyMjcuNzc4IiB5PSIyODQuNTU0IiBzdHlsZT0iZmlsbDojNzg4NzhDOyIgd2lkdGg9Ijg5LjUxMSIgaGVpZ2h0PSI0NS42NDMiLz4NCjwvZz4NCjxyZWN0IHk9IjI3MC45MzMiIHN0eWxlPSJmaWxsOiNGRkNBM0I7IiB3aWR0aD0iNTEyLjAwNSIgaGVpZ2h0PSIyOC4yNjYiLz4NCjxyZWN0IHk9IjI3MC45MzMiIHN0eWxlPSJmaWxsOiNFNUIwMkM7IiB3aWR0aD0iMjIxLjQxMyIgaGVpZ2h0PSIyOC4yNjUiLz4NCjxwYXRoIHN0eWxlPSJmaWxsOiMwMDg2Q0M7IiBkPSJNNTExLjk5OCwxMjcuMjUxVjI3MC45M0gxODUuMzgxTDE1OC45NDYsNjIuMDgxbC01Ljc1OC00NS41NDFoMjQ4LjEwOQ0KCWMzNi43NzgsMCw2OS4zNjksMTcuOTQ0LDg5LjQ5LDQ1LjU0MUM1MDQuMTM2LDgwLjM3LDUxMS45OTgsMTAyLjg4OSw1MTEuOTk4LDEyNy4yNTF6Ii8+DQo8cGF0aCBzdHlsZT0iZmlsbDojNDE0NTUxOyIgZD0iTTI2My44ODksMTI3LjI1MVYyNzAuOTNINDIuNDc3VjEyNy4yNTFjMC02MS4xNCw0OS41NzItMTEwLjcxMSwxMTAuNzExLTExMC43MTENCgljNDEuNDU4LDAsNzcuNTg3LDIyLjc5MSw5Ni41NTcsNTYuNTM0QzI1OC43NDksODkuMDgxLDI2My44ODksMTA3LjU2OSwyNjMuODg5LDEyNy4yNTF6Ii8+DQo8cGF0aCBzdHlsZT0iZmlsbDojMDI3QUFGOyIgZD0iTTMyMC45NzIsMTYuNTQ0aC05NS43ODhjNjkuNTUxLDI0LjE5MSw1OC41MjUsODcuNjksNTcuNjEsMTczLjA4Ng0KCWMtMC4wNTksNS40OTMsNC4zOTIsOS45NzksOS44ODUsOS45NzloNDAuMzExTDMyMC45NzIsMTYuNTQ0Ii8+DQo8cGF0aCBzdHlsZT0iZmlsbDojQzExMDFEOyIgZD0iTTMwNi4zNywyMDAuMjY0aDE3LjY5MWM0LjY1NSwwLDguNDI4LTMuNzczLDguNDI4LTguNDI4Vjk0LjY3MmMwLTQuNjU1LDMuNzczLTguNDI4LDguNDI4LTguNDI4DQoJaDUyLjIzN2M0LjY1NSwwLDguNDI4LTMuNzczLDguNDI4LTguNDI4VjI0Ljk2OGMwLTQuNjU1LTMuNzczLTguNDI4LTguNDI4LTguNDI4SDMzMC43MWMtMTguMDk3LDAtMzIuNzY3LDE0LjY3LTMyLjc2NywzMi43NjcNCgl2MTQyLjUyOUMyOTcuOTQyLDE5Ni40OTEsMzAxLjcxNSwyMDAuMjY0LDMwNi4zNywyMDAuMjY0eiIvPg0KPHBhdGggc3R5bGU9ImZpbGw6I0MwQzJEMDsiIGQ9Ik0yNjMuODg5LDEyNy4yNTF2MTQzLjY1OEg5Ni45NDhWNzMuMDczaDE1Mi43OThDMjU4Ljc0OSw4OS4wODEsMjYzLjg4OSwxMDcuNTY5LDI2My44ODksMTI3LjI1MXoiLz4NCjxwb2x5Z29uIHN0eWxlPSJmaWxsOiNEMEQ3REQ7IiBwb2ludHM9IjI2My44ODksMTcyLjAwNyAyNjMuODg5LDI3MC45MyA5Ni45NjksMjcwLjkzIDk2Ljk5LDI3MC45MDkgMjA1Ljk2MywxNzIuMDE3IA0KCTIwNS45NzQsMTcyLjAwNyAiLz4NCjxwYXRoIHN0eWxlPSJmaWxsOiNFNkVCRUY7IiBkPSJNMjYzLjg4OSwxMjcuMjUxdjcxLjc4N2wtMTQuMDgxLDEyLjc4M2wtNDMuODQ1LTM5LjgwNEw5Ni45NDgsNzMuMDczaDE1Mi43OTgNCglDMjU4Ljc0OSw4OS4wODEsMjYzLjg4OSwxMDcuNTY5LDI2My44ODksMTI3LjI1MXoiLz4NCjxwYXRoIHN0eWxlPSJmaWxsOiNDMEMyRDA7IiBkPSJNMjYzLjg4OSwxMjcuMjUxVjI3MC45M2gtNjcuMDAzVjEyNy4yNTFjMC0xOS42ODItNS4xNC0zOC4xNzEtMTQuMTQ0LTU0LjE3OGg2Ny4wMDMNCglDMjU4Ljc0OSw4OS4wODEsMjYzLjg4OSwxMDcuNTY5LDI2My44ODksMTI3LjI1MXoiLz4NCjxnPg0KPC9nPg0KPGc+DQo8L2c+DQo8Zz4NCjwvZz4NCjxnPg0KPC9nPg0KPGc+DQo8L2c+DQo8Zz4NCjwvZz4NCjxnPg0KPC9nPg0KPGc+DQo8L2c+DQo8Zz4NCjwvZz4NCjxnPg0KPC9nPg0KPGc+DQo8L2c+DQo8Zz4NCjwvZz4NCjxnPg0KPC9nPg0KPGc+DQo8L2c+DQo8Zz4NCjwvZz4NCjwvc3ZnPg0K",
      openPostBox: "data:image/svg+xml;base64,PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iaXNvLTg4NTktMSI/Pg0KPCEtLSBHZW5lcmF0b3I6IEFkb2JlIElsbHVzdHJhdG9yIDE5LjAuMCwgU1ZHIEV4cG9ydCBQbHVnLUluIC4gU1ZHIFZlcnNpb246IDYuMDAgQnVpbGQgMCkgIC0tPg0KPHN2ZyB2ZXJzaW9uPSIxLjEiIGlkPSJDYXBhXzEiIHhtbG5zPSJodHRwOi8vd3d3LnczLm9yZy8yMDAwL3N2ZyIgeG1sbnM6eGxpbms9Imh0dHA6Ly93d3cudzMub3JnLzE5OTkveGxpbmsiIHg9IjBweCIgeT0iMHB4Ig0KCSB2aWV3Qm94PSIwIDAgNTEyLjAwNSA1MTIuMDA1IiBzdHlsZT0iZW5hYmxlLWJhY2tncm91bmQ6bmV3IDAgMCA1MTIuMDA1IDUxMi4wMDU7IiB4bWw6c3BhY2U9InByZXNlcnZlIj4NCjxyZWN0IHg9IjIyNy43NzgiIHk9IjI4Ni42NDciIHN0eWxlPSJmaWxsOiM5NUFCQjA7IiB3aWR0aD0iODkuNTExIiBoZWlnaHQ9IjIwOC44MTgiLz4NCjxnPg0KCTxyZWN0IHg9IjIyNy43NzgiIHk9IjI4Ni42NDciIHN0eWxlPSJmaWxsOiM3ODg3OEM7IiB3aWR0aD0iMzkuMjU3IiBoZWlnaHQ9IjIwOC44MTgiLz4NCgk8cmVjdCB4PSIyMjcuNzc4IiB5PSIyODQuNTU0IiBzdHlsZT0iZmlsbDojNzg4NzhDOyIgd2lkdGg9Ijg5LjUxMSIgaGVpZ2h0PSI0NS42NDMiLz4NCjwvZz4NCjxyZWN0IHk9IjI3MC45MzMiIHN0eWxlPSJmaWxsOiNGRkNBM0I7IiB3aWR0aD0iNTEyLjAwNSIgaGVpZ2h0PSIyOC4yNjYiLz4NCjxyZWN0IHk9IjI3MC45MzMiIHN0eWxlPSJmaWxsOiNFNUIwMkM7IiB3aWR0aD0iMjIxLjQxMyIgaGVpZ2h0PSIyOC4yNjUiLz4NCjxwYXRoIHN0eWxlPSJmaWxsOiMwMDg2Q0M7IiBkPSJNNTExLjk5OCwxMjcuMjUxVjI3MC45M0gxODUuMzgxTDE1OC45NDYsNjIuMDgxbC01Ljc1OC00NS41NDFoMjQ4LjEwOQ0KCWMzNi43NzgsMCw2OS4zNjksMTcuOTQ0LDg5LjQ5LDQ1LjU0MUM1MDQuMTM2LDgwLjM3LDUxMS45OTgsMTAyLjg4OSw1MTEuOTk4LDEyNy4yNTF6Ii8+DQo8cGF0aCBzdHlsZT0iZmlsbDojNDE0NTUxOyIgZD0iTTE1My4xODcsMTYuNTQ0TDE1My4xODcsMTYuNTQ0Yy02MS4xNDEsMC0xMTAuNzA2LDQ5LjU2NC0xMTAuNzA2LDExMC43MDZ2MTQzLjY4MmgyMjEuNDExVjEyNy4yNQ0KCUMyNjMuODkzLDY2LjEwOCwyMTQuMzI4LDE2LjU0NCwxNTMuMTg3LDE2LjU0NHoiLz4NCjxwYXRoIHN0eWxlPSJmaWxsOiNDMTEwMUQ7IiBkPSJNMjk4LjQzOSwxNDAuMjV2MTYuMzk3YzAsNS4wMTIsNC4wNjMsOS4wNzUsOS4wNzUsOS4wNzVoOTUuODdjNS4wMTIsMCw5LjA3NSw0LjA2Myw5LjA3NSw5LjA3NQ0KCXY1Mi41MTNjMCw1LjAxMiw0LjA2Myw5LjA3NSw5LjA3NSw5LjA3NWg1MS41NTVjNS4wMTIsMCw5LjA3NS00LjA2Myw5LjA3NS05LjA3NXYtNjIuNzIxYzAtMTguNDU0LTE0Ljk2LTMzLjQxNC0zMy40MTQtMzMuNDE0DQoJSDMwNy41MTRDMzAyLjUwMywxMzEuMTc1LDI5OC40MzksMTM1LjIzOCwyOTguNDM5LDE0MC4yNXoiLz4NCjxnPg0KPC9nPg0KPGc+DQo8L2c+DQo8Zz4NCjwvZz4NCjxnPg0KPC9nPg0KPGc+DQo8L2c+DQo8Zz4NCjwvZz4NCjxnPg0KPC9nPg0KPGc+DQo8L2c+DQo8Zz4NCjwvZz4NCjxnPg0KPC9nPg0KPGc+DQo8L2c+DQo8Zz4NCjwvZz4NCjxnPg0KPC9nPg0KPGc+DQo8L2c+DQo8Zz4NCjwvZz4NCjwvc3ZnPg0K",
      userInifo: {},
    }
  },
  methods: {
    goToBookShelf() {
      this.$router.push({ path:"/main" })
    },
    goToRecommend() {
      if (this.pageName!="MainBook") {
        this.$router.push({ name: 'MainBook' })
      }
    },
    goToProfile() {
      this.$router.push({ name: 'Profile' })
    },
    goToMain() {
      this.$router.push({ name: 'MainPage' })
    },
    tempOverImg() {
      this.postBox = this.openPostBox
      console.log(this.postBox)
    },
    tempOutImg() {
      this.postBox = this.closePostBox
      console.log(this.postBox)
    },
    showInput() {
      this.checkInput = false
    },
    hideInput() {
      this.checkInput = true
    },
    checkLogin() {
      const token = localStorage.getItem('jwt')
      if (token) {
        this.isLogin = true
      }
    },
    Logout() {
      this.$store.dispatch('Logout')
      localStorage.removeItem('jwt')
      this.isLogin = false
    },
    postBoxClick() {
      alert('우체통 설치 중 ...🛠')
    },
  },
  watch: {
    $route(to) {
      this.pageName = to.name
      this.checkLogin()
    }
  },
  created() {
    this.checkLogin()
    this.userInfo = this.$store.getters.getUser
    console.log(this.userInfo)
  }
}
</script>

<style>
  #title-logo:hover {
    cursor: pointer;
  }
  /* .background {
    background-color: rgba(61, 16, 5, 0.1); 
    height: 100%;
    width: 100%;
  } */
  .background-nav {
    background-color: white;
    /* background-color: rgba(237, 234, 232, 1); */
    position: fixed;
    top: 0;
    width: 100%;
    display: flex;
    align-items: center;
    justify-content: space-around;
    padding-top: 0;
    padding-bottom: 0;
    z-index: 1000;
    /* 그림자 효과 */
    box-shadow: 0px 5px 10px -10px gray;
  }
/*여기입니다요 id가 이거래 .. 선택자로 찍어버렸어 약간 포크 */
  #__BVID__9 > ul {
    background-color: #5c463d;
    border-radius: 10px/ 10px;
    border: 0px;
    width: 10px;
  }
  #__BVID__9__BV_toggle_ { 
    border : 0;
    outline-style: none;
    text-decoration-style: none; 
    outline-offset: 0;
  }
  .animate__animated {
    --animate-duration: 1s;
  }
  #navInput {
    font-size: 12px;
    width: 200px;
  }
  #navInput[type="text"]:focus {
    border-color: rgb(218, 203, 0);
    box-shadow: 0 1px 1px rgba(0, 0, 0, 0.075) inset, 0 0 6px rgba(255, 195, 105, 0.6);
    outline: 0 none;
    font-size: 12px;
    width: 200px;
  }
  .dropdown-item:hover {
    background-color: #5c463d;
    font-weight: bold;
    color: rgba(255, 255, 255, 1);
  }
  .dropdown-item .a:hover {
    color: none;
  }
  .dropdown-menu {
    min-width: 100px;
    background-color: #5c463d;
  }
  .dropdown-menu .dropdown-menu-right .show > li {
    display: flex;
    justify-items: center;
    background-color: #3D1605;
    margin: 0;
  }
  .dropdown-menu .dropdown-menu-right .show > li > a:hover {
    background-color: red;
  }
  .dropdown-item {
    text-align: center;
    /* background-color: #3D1605; */
    color: rgba(255, 255, 255, 0.9);
  }
  .list-hr {
    margin-left: 15px;
    margin-right: 15px;
    margin-top: 5px;
    margin-bottom: 5px;
    /* background-color: white; */
  }
  .postbox:hover {
    cursor: pointer;
  }
  .temp-menu {
    background: linear-gradient(to right, rgba(255, 255, 255, 0) 50%, rgb(255, 240, 178) 50%);
    background-size: 200%;
    transition: .35s;
    color: black;
    position: relative;
    font-size: 18px;
    font-weight:300;
    padding: 0 10px;
    margin-left: 20px; 
    font-family: Candara,Calibri,Segoe,Segoe UI,Optima,Arial,sans-serif;
  }
  .temp-menu.router-link-active {
    font-weight: bold;
    background-color: rgb(255, 240, 178)
  }
  .temp-menu:hover {
    background-position: -100% 0;
    cursor: pointer;
    font-weight: bold;
    color: black;
  }
  .temp {
    display: flex;
  }
  /* .temp-hightlight {
    display: absolute;
  } */
</style>