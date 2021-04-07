<template>
  <div class="background" style="margin-top: 100px;">
    <b-navbar 
      variant="faded" 
      type="light" 
      class="background-nav"
      style="min-width: 1060px;"
    >
      <b-navbar-brand
        @click="goToMain"
      >
        <div style="width: 300px;">
          <img
            id="title-logo" 
            src="@/assets/hansam.png" 
            alt="book logo" 
            height="35px"
            class="hvr-grow"
          >
        </div>
      </b-navbar-brand>
      <div style="width: 500px; display: flex; justify-content: center;">
        <router-link :to="{ name: 'MainBook' }" class="temp-menu">
          RECOMMEND
        </router-link>
        <router-link :to="{ name: 'Community' }" class="temp-menu">
          COMMUNITY
        </router-link>
        <router-link :to="{ name: 'Search' }" class="temp-menu">
          SEARCH
        </router-link>
      </div>
      <b-navbar-nav style="display: flex; align-items: center; width: 300px; justify-content: flex-end;">
        <!-- 검색 -->
        <b-nav-item>
          <img class="hvr-rotate" @click="showInput" v-if="checkInput" style="width: 1.5rem;" src="data:image/svg+xml;base64,PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iaXNvLTg4NTktMSI/Pg0KPCEtLSBHZW5lcmF0b3I6IEFkb2JlIElsbHVzdHJhdG9yIDE5LjAuMCwgU1ZHIEV4cG9ydCBQbHVnLUluIC4gU1ZHIFZlcnNpb246IDYuMDAgQnVpbGQgMCkgIC0tPg0KPHN2ZyB2ZXJzaW9uPSIxLjEiIGlkPSJMYXllcl8xIiB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHhtbG5zOnhsaW5rPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5L3hsaW5rIiB4PSIwcHgiIHk9IjBweCINCgkgdmlld0JveD0iMCAwIDUxMS45OTkgNTExLjk5OSIgc3R5bGU9ImVuYWJsZS1iYWNrZ3JvdW5kOm5ldyAwIDAgNTExLjk5OSA1MTEuOTk5OyIgeG1sOnNwYWNlPSJwcmVzZXJ2ZSI+DQo8Zz4NCgk8Zz4NCgkJPGc+DQoJCQk8cGF0aCBkPSJNNTAxLjAxMSw0NDcuOTAyTDM0Ny41NCwyOTQuNDMxYzIwLjAwOS0zMC4wNDYsMzEuNjg5LTY2LjA5MywzMS42ODktMTA0LjgyMUMzNzkuMjI5LDg1LjA1OSwyOTQuMTcsMCwxODkuNjE4LDANCgkJCQlTMC4wMDgsODUuMDU5LDAuMDA4LDE4OS42MXM4NS4wNTksMTg5LjYxLDE4OS42MSwxODkuNjFjMzguNzI4LDAsNzQuNzc0LTExLjY4LDEwNC44MjEtMzEuNjg5TDQ0Ny45MSw1MDEuMDAyDQoJCQkJYzcuMDkyLDcuMDkyLDE2LjUyMSwxMC45OTcsMjYuNTUxLDEwLjk5N2MxMC4wMywwLDE5LjQ1OC0zLjkwNSwyNi41NS0xMC45OThDNTE1LjY1LDQ4Ni4zNjMsNTE1LjY1LDQ2Mi41NDIsNTAxLjAxMSw0NDcuOTAyeg0KCQkJCSBNMTg5LjYxOCwzNTkuMjJjLTkzLjUyMywwLTE2OS42MS03Ni4wODYtMTY5LjYxLTE2OS42MUMyMC4wMDgsOTYuMDg3LDk2LjA5NSwyMCwxODkuNjE4LDIwczE2OS42MSw3Ni4wODcsMTY5LjYxLDE2OS42MQ0KCQkJCVMyODMuMTQxLDM1OS4yMiwxODkuNjE4LDM1OS4yMnogTTQ4Ni44NjgsNDg2Ljg2Yy0zLjMxNCwzLjMxNC03LjcyMSw1LjE0LTEyLjQwNyw1LjE0Yy00LjY4OCwwLTkuMDk0LTEuODI1LTEyLjQwOC01LjE0DQoJCQkJTDMxMC42NDgsMzM1LjQ1NWM5LjAxMy03LjQ5MiwxNy4zMjMtMTUuODAyLDI0LjgxNS0yNC44MTVsMTUxLjQwNSwxNTEuNDA0QzQ5My43MDksNDY4Ljg4Nyw0OTMuNzA5LDQ4MC4wMTksNDg2Ljg2OCw0ODYuODZ6Ii8+DQoJCQk8cGF0aCBkPSJNMTg5LjYxOSw0NS4xNzdjLTc5LjY0MSwwLTE0NC40MzMsNjQuNzkyLTE0NC40MzMsMTQ0LjQzM2MwLDUuNTIyLDQuNDc4LDEwLDEwLDEwYzUuNTIyLDAsMTAtNC40NzcsMTAtMTANCgkJCQljMC02OC42MTMsNTUuODIxLTEyNC40MzMsMTI0LjQzMy0xMjQuNDMzYzUuNTIyLDAsMTAtNC40NzgsMTAtMTBTMTk1LjE0MSw0NS4xNzcsMTg5LjYxOSw0NS4xNzd6Ii8+DQoJCQk8cGF0aCBkPSJNMzIwLjAyOSwxMjcuNDU3Yy0xNC4yMjMtMjkuNzktMzguNDYxLTU0LjAyOS02OC4yNS02OC4yNTNjLTQuOTg0LTIuMzgxLTEwLjk1NC0wLjI2OC0xMy4zMzMsNC43MTYNCgkJCQljLTIuMzc5LDQuOTgzLTAuMjY4LDEwLjk1Myw0LjcxNiwxMy4zMzNjMjUuNjcyLDEyLjI1OCw0Ni41NjEsMzMuMTQ3LDU4LjgxOCw1OC44MjFjMS43MTYsMy41OTMsNS4yOTgsNS42OTMsOS4wMzEsNS42OTMNCgkJCQljMS40NDQsMCwyLjkxMS0wLjMxNCw0LjMwMi0wLjk3N0MzMjAuMjk3LDEzOC40MSwzMjIuNDA4LDEzMi40NDEsMzIwLjAyOSwxMjcuNDU3eiIvPg0KCQk8L2c+DQoJPC9nPg0KPC9nPg0KPGc+DQo8L2c+DQo8Zz4NCjwvZz4NCjxnPg0KPC9nPg0KPGc+DQo8L2c+DQo8Zz4NCjwvZz4NCjxnPg0KPC9nPg0KPGc+DQo8L2c+DQo8Zz4NCjwvZz4NCjxnPg0KPC9nPg0KPGc+DQo8L2c+DQo8Zz4NCjwvZz4NCjxnPg0KPC9nPg0KPGc+DQo8L2c+DQo8Zz4NCjwvZz4NCjxnPg0KPC9nPg0KPC9zdmc+DQo=" />
          <b-form-input 
            id="navInput"
            v-else 
            style="display: absolute; left: 0; height: 40px; margin-top: 1px; border-radius: 20px / 20px;" 
            class="formcolor animate__animated animate__headShake"
            placeholder="검색어를 입력하세요."
            type="text"
            v-model="text"
            @keypress.enter="hideInput"
            @blur="showInput"
          ></b-form-input>
        </b-nav-item>
        <!-- 마이페이지 -->
        <b-nav-item-dropdown right>
          <template #button-content>
            <b-avatar size="2.5rem" :src="userInfo.userProfileImg" variant="white"></b-avatar>
          </template>

          <b-dropdown-item
            v-if="isLogin"
            @click="goToProfile"
            class="main-dropdown-item"
          >
            <span class="main-dropdown-text">나의 서재</span>
            <hr v-if="isLogin" class="list-hr">
          </b-dropdown-item>
          
          <b-dropdown-item
            v-if="!isLogin" 
            href="/login" 
            class="main-dropdown-item"
          >
            <span class="main-dropdown-text">로그인</span>
            <hr class="list-hr">
          </b-dropdown-item>

          <b-dropdown-item
            v-if="isLogin" 
            @click="Logout"
            class="main-dropdown-item"
          >
            <span class="main-dropdown-text">로그아웃</span>
          </b-dropdown-item>

          <b-dropdown-item 
            v-if="!isLogin"
            href="/signup/1"
            class="main-dropdown-item" 
          >
            <span class="main-dropdown-text">회원가입</span>
          </b-dropdown-item>
        </b-nav-item-dropdown>
      </b-navbar-nav>
    </b-navbar>
    <!-- 메인페이지 입니다 :) -->
    <section class="container">
      <router-view></router-view>
    </section>
    <aside>
      <img
        v-if="this.$route.path !== '/login' && !this.re.test(this.$route.path)"
        class="postbox"
        :src="postBox"
        @mouseover="tempOverImg()"
        @mouseout="tempOutImg()"
        @click="postBoxClick" 
      />
    </aside>
    <footer class="footer-content">
      Copyright © 앙상블. All Rights Reserved.
    </footer>
  </div>
</template>

<script>
export default {
  data() {
    return {
      re: /signup/ ,
      checkInput: true,
      isLogin: false,
      pageName: "",
      postBox: "data:image/svg+xml;base64,PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iaXNvLTg4NTktMSI/Pg0KPCEtLSBHZW5lcmF0b3I6IEFkb2JlIElsbHVzdHJhdG9yIDE5LjAuMCwgU1ZHIEV4cG9ydCBQbHVnLUluIC4gU1ZHIFZlcnNpb246IDYuMDAgQnVpbGQgMCkgIC0tPg0KPHN2ZyB2ZXJzaW9uPSIxLjEiIGlkPSJDYXBhXzEiIHhtbG5zPSJodHRwOi8vd3d3LnczLm9yZy8yMDAwL3N2ZyIgeG1sbnM6eGxpbms9Imh0dHA6Ly93d3cudzMub3JnLzE5OTkveGxpbmsiIHg9IjBweCIgeT0iMHB4Ig0KCSB2aWV3Qm94PSIwIDAgNTEyLjAwNSA1MTIuMDA1IiBzdHlsZT0iZW5hYmxlLWJhY2tncm91bmQ6bmV3IDAgMCA1MTIuMDA1IDUxMi4wMDU7IiB4bWw6c3BhY2U9InByZXNlcnZlIj4NCjxyZWN0IHg9IjIyNy43NzgiIHk9IjI4NC41NTQiIHN0eWxlPSJmaWxsOiM5NUFCQjA7IiB3aWR0aD0iODkuNTExIiBoZWlnaHQ9IjIxMC45MTIiLz4NCjxnPg0KCTxyZWN0IHg9IjIyNy43NzgiIHk9IjI4NC41NTQiIHN0eWxlPSJmaWxsOiM3ODg3OEM7IiB3aWR0aD0iODkuNTExIiBoZWlnaHQ9IjQ1LjY0MyIvPg0KCTxyZWN0IHg9IjIyNy43NzgiIHk9IjI4NC41NTQiIHN0eWxlPSJmaWxsOiM3ODg3OEM7IiB3aWR0aD0iMzkuMjU3IiBoZWlnaHQ9IjIxMC45MTIiLz4NCjwvZz4NCjxyZWN0IHk9IjI3MC45MzMiIHN0eWxlPSJmaWxsOiNGRkNBM0I7IiB3aWR0aD0iNTEyLjAwNSIgaGVpZ2h0PSIyOC4yNjYiLz4NCjxyZWN0IHk9IjI3MC45MzMiIHN0eWxlPSJmaWxsOiNFNUIwMkM7IiB3aWR0aD0iMjIxLjQxMyIgaGVpZ2h0PSIyOC4yNjUiLz4NCjxwYXRoIHN0eWxlPSJmaWxsOiMwMDg2Q0M7IiBkPSJNNTExLjk5OCwxMjcuMjUxVjI3MC45M0gxODUuMzgxTDE1OC45NDYsNjIuMDgxbC01Ljc1OC00NS41NDFoMjQ4LjEwOQ0KCWMzNi43NzgsMCw2OS4zNjksMTcuOTQ0LDg5LjQ5LDQ1LjU0MUM1MDQuMTM2LDgwLjM3LDUxMS45OTgsMTAyLjg4OSw1MTEuOTk4LDEyNy4yNTF6Ii8+DQo8Zz4NCgk8cGF0aCBzdHlsZT0iZmlsbDojMDI3QUFGOyIgZD0iTTE1My4xODcsMTYuNTQ0TDE1My4xODcsMTYuNTQ0Yy02MS4xNDEsMC0xMTAuNzA2LDQ5LjU2NC0xMTAuNzA2LDExMC43MDZ2MTQzLjY4MmgyMjEuNDExVjEyNy4yNQ0KCQlDMjYzLjg5Myw2Ni4xMDgsMjE0LjMyOCwxNi41NDQsMTUzLjE4NywxNi41NDR6Ii8+DQoJPHBhdGggc3R5bGU9ImZpbGw6IzAyN0FBRjsiIGQ9Ik0zMjAuOTcyLDE2LjU0NGgtOTUuNzg4YzY5LjU1MSwyNC4xOTEsNTguNTI1LDg3LjY5LDU3LjYxLDE3My4wODYNCgkJYy0wLjA1OSw1LjQ5Myw0LjM5Miw5Ljk3OSw5Ljg4NSw5Ljk3OWg0MC4zMTFMMzIwLjk3MiwxNi41NDQiLz4NCjwvZz4NCjxwYXRoIHN0eWxlPSJmaWxsOiNDMTEwMUQ7IiBkPSJNMzA0LjgxNCwyMDAuMjY0aDIwLjgwMmMzLjc5NSwwLDYuODcyLTMuMDc3LDYuODcyLTYuODcyVjkzLjExNmMwLTMuNzk1LDMuMDc3LTYuODcyLDYuODcyLTYuODcyDQoJaDU1LjM0OWMzLjc5NSwwLDYuODcyLTMuMDc3LDYuODcyLTYuODcyVjIzLjQxMmMwLTMuNzk1LTMuMDc3LTYuODcyLTYuODcyLTYuODcyaC02NS41NTZjLTE3LjIzNywwLTMxLjIxMiwxMy45NzQtMzEuMjEyLDMxLjIxMg0KCXYxNDUuNjQxQzI5Ny45NDIsMTk3LjE4NywzMDEuMDE5LDIwMC4yNjQsMzA0LjgxNCwyMDAuMjY0eiIvPg0KPGc+DQoJPHBhdGggc3R5bGU9ImZpbGw6IzMwMkQzNjsiIGQ9Ik0xNTMuMTg3LDExNi41OTVjLTE4Ljk1OCwwLTM0LjM4Mi0xNS40MjMtMzQuMzgyLTM0LjM4MnMxNS40MjMtMzQuMzgyLDM0LjM4Mi0zNC4zODINCgkJczM0LjM4MSwxNS40MjMsMzQuMzgxLDM0LjM4MlMxNzIuMTQ1LDExNi41OTUsMTUzLjE4NywxMTYuNTk1eiBNMTUzLjE4Nyw2My41MzVjLTEwLjI5OSwwLTE4LjY3OCw4LjM3OC0xOC42NzgsMTguNjc4DQoJCXM4LjM3OCwxOC42NzgsMTguNjc4LDE4LjY3OGMxMC4zLDAsMTguNjc3LTguMzc4LDE4LjY3Ny0xOC42NzhTMTYzLjQ4Niw2My41MzUsMTUzLjE4Nyw2My41MzV6Ii8+DQoJPHBhdGggc3R5bGU9ImZpbGw6IzMwMkQzNjsiIGQ9Ik0yMjAuMTksMTY5LjYzSDg2LjE4NGMtNC4zMzYsMC03Ljg1Mi0zLjUxNi03Ljg1Mi03Ljg1MmMwLTQuMzM2LDMuNTE2LTcuODUyLDcuODUyLTcuODUySDIyMC4xOQ0KCQljNC4zMzYsMCw3Ljg1MiwzLjUxNiw3Ljg1Miw3Ljg1MkMyMjguMDQyLDE2Ni4xMTUsMjI0LjUyNiwxNjkuNjMsMjIwLjE5LDE2OS42M3oiLz4NCgk8cGF0aCBzdHlsZT0iZmlsbDojMzAyRDM2OyIgZD0iTTE5Ny44NywyMTEuNTA3aC04OS4zNjdjLTQuMzM2LDAtNy44NTItMy41MTYtNy44NTItNy44NTJjMC00LjMzNiwzLjUxNi03Ljg1Miw3Ljg1Mi03Ljg1Mmg4OS4zNjcNCgkJYzQuMzM2LDAsNy44NTIsMy41MTYsNy44NTIsNy44NTJDMjA1LjcyMiwyMDcuOTkyLDIwMi4yMDcsMjExLjUwNywxOTcuODcsMjExLjUwN3oiLz4NCjwvZz4NCjxnPg0KPC9nPg0KPGc+DQo8L2c+DQo8Zz4NCjwvZz4NCjxnPg0KPC9nPg0KPGc+DQo8L2c+DQo8Zz4NCjwvZz4NCjxnPg0KPC9nPg0KPGc+DQo8L2c+DQo8Zz4NCjwvZz4NCjxnPg0KPC9nPg0KPGc+DQo8L2c+DQo8Zz4NCjwvZz4NCjxnPg0KPC9nPg0KPGc+DQo8L2c+DQo8Zz4NCjwvZz4NCjwvc3ZnPg0K",
      closePostBox: "data:image/svg+xml;base64,PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iaXNvLTg4NTktMSI/Pg0KPCEtLSBHZW5lcmF0b3I6IEFkb2JlIElsbHVzdHJhdG9yIDE5LjAuMCwgU1ZHIEV4cG9ydCBQbHVnLUluIC4gU1ZHIFZlcnNpb246IDYuMDAgQnVpbGQgMCkgIC0tPg0KPHN2ZyB2ZXJzaW9uPSIxLjEiIGlkPSJDYXBhXzEiIHhtbG5zPSJodHRwOi8vd3d3LnczLm9yZy8yMDAwL3N2ZyIgeG1sbnM6eGxpbms9Imh0dHA6Ly93d3cudzMub3JnLzE5OTkveGxpbmsiIHg9IjBweCIgeT0iMHB4Ig0KCSB2aWV3Qm94PSIwIDAgNTEyLjAwNSA1MTIuMDA1IiBzdHlsZT0iZW5hYmxlLWJhY2tncm91bmQ6bmV3IDAgMCA1MTIuMDA1IDUxMi4wMDU7IiB4bWw6c3BhY2U9InByZXNlcnZlIj4NCjxyZWN0IHg9IjIyNy43NzgiIHk9IjI4NC41NTQiIHN0eWxlPSJmaWxsOiM5NUFCQjA7IiB3aWR0aD0iODkuNTExIiBoZWlnaHQ9IjIxMC45MTIiLz4NCjxnPg0KCTxyZWN0IHg9IjIyNy43NzgiIHk9IjI4NC41NTQiIHN0eWxlPSJmaWxsOiM3ODg3OEM7IiB3aWR0aD0iODkuNTExIiBoZWlnaHQ9IjQ1LjY0MyIvPg0KCTxyZWN0IHg9IjIyNy43NzgiIHk9IjI4NC41NTQiIHN0eWxlPSJmaWxsOiM3ODg3OEM7IiB3aWR0aD0iMzkuMjU3IiBoZWlnaHQ9IjIxMC45MTIiLz4NCjwvZz4NCjxyZWN0IHk9IjI3MC45MzMiIHN0eWxlPSJmaWxsOiNGRkNBM0I7IiB3aWR0aD0iNTEyLjAwNSIgaGVpZ2h0PSIyOC4yNjYiLz4NCjxyZWN0IHk9IjI3MC45MzMiIHN0eWxlPSJmaWxsOiNFNUIwMkM7IiB3aWR0aD0iMjIxLjQxMyIgaGVpZ2h0PSIyOC4yNjUiLz4NCjxwYXRoIHN0eWxlPSJmaWxsOiMwMDg2Q0M7IiBkPSJNNTExLjk5OCwxMjcuMjUxVjI3MC45M0gxODUuMzgxTDE1OC45NDYsNjIuMDgxbC01Ljc1OC00NS41NDFoMjQ4LjEwOQ0KCWMzNi43NzgsMCw2OS4zNjksMTcuOTQ0LDg5LjQ5LDQ1LjU0MUM1MDQuMTM2LDgwLjM3LDUxMS45OTgsMTAyLjg4OSw1MTEuOTk4LDEyNy4yNTF6Ii8+DQo8Zz4NCgk8cGF0aCBzdHlsZT0iZmlsbDojMDI3QUFGOyIgZD0iTTE1My4xODcsMTYuNTQ0TDE1My4xODcsMTYuNTQ0Yy02MS4xNDEsMC0xMTAuNzA2LDQ5LjU2NC0xMTAuNzA2LDExMC43MDZ2MTQzLjY4MmgyMjEuNDExVjEyNy4yNQ0KCQlDMjYzLjg5Myw2Ni4xMDgsMjE0LjMyOCwxNi41NDQsMTUzLjE4NywxNi41NDR6Ii8+DQoJPHBhdGggc3R5bGU9ImZpbGw6IzAyN0FBRjsiIGQ9Ik0zMjAuOTcyLDE2LjU0NGgtOTUuNzg4YzY5LjU1MSwyNC4xOTEsNTguNTI1LDg3LjY5LDU3LjYxLDE3My4wODYNCgkJYy0wLjA1OSw1LjQ5Myw0LjM5Miw5Ljk3OSw5Ljg4NSw5Ljk3OWg0MC4zMTFMMzIwLjk3MiwxNi41NDQiLz4NCjwvZz4NCjxwYXRoIHN0eWxlPSJmaWxsOiNDMTEwMUQ7IiBkPSJNMzA0LjgxNCwyMDAuMjY0aDIwLjgwMmMzLjc5NSwwLDYuODcyLTMuMDc3LDYuODcyLTYuODcyVjkzLjExNmMwLTMuNzk1LDMuMDc3LTYuODcyLDYuODcyLTYuODcyDQoJaDU1LjM0OWMzLjc5NSwwLDYuODcyLTMuMDc3LDYuODcyLTYuODcyVjIzLjQxMmMwLTMuNzk1LTMuMDc3LTYuODcyLTYuODcyLTYuODcyaC02NS41NTZjLTE3LjIzNywwLTMxLjIxMiwxMy45NzQtMzEuMjEyLDMxLjIxMg0KCXYxNDUuNjQxQzI5Ny45NDIsMTk3LjE4NywzMDEuMDE5LDIwMC4yNjQsMzA0LjgxNCwyMDAuMjY0eiIvPg0KPGc+DQoJPHBhdGggc3R5bGU9ImZpbGw6IzMwMkQzNjsiIGQ9Ik0xNTMuMTg3LDExNi41OTVjLTE4Ljk1OCwwLTM0LjM4Mi0xNS40MjMtMzQuMzgyLTM0LjM4MnMxNS40MjMtMzQuMzgyLDM0LjM4Mi0zNC4zODINCgkJczM0LjM4MSwxNS40MjMsMzQuMzgxLDM0LjM4MlMxNzIuMTQ1LDExNi41OTUsMTUzLjE4NywxMTYuNTk1eiBNMTUzLjE4Nyw2My41MzVjLTEwLjI5OSwwLTE4LjY3OCw4LjM3OC0xOC42NzgsMTguNjc4DQoJCXM4LjM3OCwxOC42NzgsMTguNjc4LDE4LjY3OGMxMC4zLDAsMTguNjc3LTguMzc4LDE4LjY3Ny0xOC42NzhTMTYzLjQ4Niw2My41MzUsMTUzLjE4Nyw2My41MzV6Ii8+DQoJPHBhdGggc3R5bGU9ImZpbGw6IzMwMkQzNjsiIGQ9Ik0yMjAuMTksMTY5LjYzSDg2LjE4NGMtNC4zMzYsMC03Ljg1Mi0zLjUxNi03Ljg1Mi03Ljg1MmMwLTQuMzM2LDMuNTE2LTcuODUyLDcuODUyLTcuODUySDIyMC4xOQ0KCQljNC4zMzYsMCw3Ljg1MiwzLjUxNiw3Ljg1Miw3Ljg1MkMyMjguMDQyLDE2Ni4xMTUsMjI0LjUyNiwxNjkuNjMsMjIwLjE5LDE2OS42M3oiLz4NCgk8cGF0aCBzdHlsZT0iZmlsbDojMzAyRDM2OyIgZD0iTTE5Ny44NywyMTEuNTA3aC04OS4zNjdjLTQuMzM2LDAtNy44NTItMy41MTYtNy44NTItNy44NTJjMC00LjMzNiwzLjUxNi03Ljg1Miw3Ljg1Mi03Ljg1Mmg4OS4zNjcNCgkJYzQuMzM2LDAsNy44NTIsMy41MTYsNy44NTIsNy44NTJDMjA1LjcyMiwyMDcuOTkyLDIwMi4yMDcsMjExLjUwNywxOTcuODcsMjExLjUwN3oiLz4NCjwvZz4NCjxnPg0KPC9nPg0KPGc+DQo8L2c+DQo8Zz4NCjwvZz4NCjxnPg0KPC9nPg0KPGc+DQo8L2c+DQo8Zz4NCjwvZz4NCjxnPg0KPC9nPg0KPGc+DQo8L2c+DQo8Zz4NCjwvZz4NCjxnPg0KPC9nPg0KPGc+DQo8L2c+DQo8Zz4NCjwvZz4NCjxnPg0KPC9nPg0KPGc+DQo8L2c+DQo8Zz4NCjwvZz4NCjwvc3ZnPg0K",
      openPostBox: "data:image/svg+xml;base64,PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iaXNvLTg4NTktMSI/Pg0KPCEtLSBHZW5lcmF0b3I6IEFkb2JlIElsbHVzdHJhdG9yIDE5LjAuMCwgU1ZHIEV4cG9ydCBQbHVnLUluIC4gU1ZHIFZlcnNpb246IDYuMDAgQnVpbGQgMCkgIC0tPg0KPHN2ZyB2ZXJzaW9uPSIxLjEiIGlkPSJDYXBhXzEiIHhtbG5zPSJodHRwOi8vd3d3LnczLm9yZy8yMDAwL3N2ZyIgeG1sbnM6eGxpbms9Imh0dHA6Ly93d3cudzMub3JnLzE5OTkveGxpbmsiIHg9IjBweCIgeT0iMHB4Ig0KCSB2aWV3Qm94PSIwIDAgNTEyLjAwNSA1MTIuMDA1IiBzdHlsZT0iZW5hYmxlLWJhY2tncm91bmQ6bmV3IDAgMCA1MTIuMDA1IDUxMi4wMDU7IiB4bWw6c3BhY2U9InByZXNlcnZlIj4NCjxyZWN0IHg9IjIyNy43NzgiIHk9IjI4Ni42NDciIHN0eWxlPSJmaWxsOiM5NUFCQjA7IiB3aWR0aD0iODkuNTExIiBoZWlnaHQ9IjIwOC44MTgiLz4NCjxnPg0KCTxyZWN0IHg9IjIyNy43NzgiIHk9IjI4Ni42NDciIHN0eWxlPSJmaWxsOiM3ODg3OEM7IiB3aWR0aD0iMzkuMjU3IiBoZWlnaHQ9IjIwOC44MTgiLz4NCgk8cmVjdCB4PSIyMjcuNzc4IiB5PSIyODQuNTU0IiBzdHlsZT0iZmlsbDojNzg4NzhDOyIgd2lkdGg9Ijg5LjUxMSIgaGVpZ2h0PSI0NS42NDMiLz4NCjwvZz4NCjxyZWN0IHk9IjI3MC45MzMiIHN0eWxlPSJmaWxsOiNGRkNBM0I7IiB3aWR0aD0iNTEyLjAwNSIgaGVpZ2h0PSIyOC4yNjYiLz4NCjxyZWN0IHk9IjI3MC45MzMiIHN0eWxlPSJmaWxsOiNFNUIwMkM7IiB3aWR0aD0iMjIxLjQxMyIgaGVpZ2h0PSIyOC4yNjUiLz4NCjxwYXRoIHN0eWxlPSJmaWxsOiMwMDg2Q0M7IiBkPSJNNTExLjk5OCwxMjcuMjUxVjI3MC45M0gxODUuMzgxTDE1OC45NDYsNjIuMDgxbC01Ljc1OC00NS41NDFoMjQ4LjEwOQ0KCWMzNi43NzgsMCw2OS4zNjksMTcuOTQ0LDg5LjQ5LDQ1LjU0MUM1MDQuMTM2LDgwLjM3LDUxMS45OTgsMTAyLjg4OSw1MTEuOTk4LDEyNy4yNTF6Ii8+DQo8cGF0aCBzdHlsZT0iZmlsbDojNDE0NTUxOyIgZD0iTTE1My4xODcsMTYuNTQ0TDE1My4xODcsMTYuNTQ0Yy02MS4xNDEsMC0xMTAuNzA2LDQ5LjU2NC0xMTAuNzA2LDExMC43MDZ2MTQzLjY4MmgyMjEuNDExVjEyNy4yNQ0KCUMyNjMuODkzLDY2LjEwOCwyMTQuMzI4LDE2LjU0NCwxNTMuMTg3LDE2LjU0NHoiLz4NCjxwYXRoIHN0eWxlPSJmaWxsOiNDMTEwMUQ7IiBkPSJNMjk4LjQzOSwxNDAuMjV2MTYuMzk3YzAsNS4wMTIsNC4wNjMsOS4wNzUsOS4wNzUsOS4wNzVoOTUuODdjNS4wMTIsMCw5LjA3NSw0LjA2Myw5LjA3NSw5LjA3NQ0KCXY1Mi41MTNjMCw1LjAxMiw0LjA2Myw5LjA3NSw5LjA3NSw5LjA3NWg1MS41NTVjNS4wMTIsMCw5LjA3NS00LjA2Myw5LjA3NS05LjA3NXYtNjIuNzIxYzAtMTguNDU0LTE0Ljk2LTMzLjQxNC0zMy40MTQtMzMuNDE0DQoJSDMwNy41MTRDMzAyLjUwMywxMzEuMTc1LDI5OC40MzksMTM1LjIzOCwyOTguNDM5LDE0MC4yNXoiLz4NCjxnPg0KPC9nPg0KPGc+DQo8L2c+DQo8Zz4NCjwvZz4NCjxnPg0KPC9nPg0KPGc+DQo8L2c+DQo8Zz4NCjwvZz4NCjxnPg0KPC9nPg0KPGc+DQo8L2c+DQo8Zz4NCjwvZz4NCjxnPg0KPC9nPg0KPGc+DQo8L2c+DQo8Zz4NCjwvZz4NCjxnPg0KPC9nPg0KPGc+DQo8L2c+DQo8Zz4NCjwvZz4NCjwvc3ZnPg0K",
      openFullPostBox: "data:image/svg+xml;base64,PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iaXNvLTg4NTktMSI/Pg0KPCEtLSBHZW5lcmF0b3I6IEFkb2JlIElsbHVzdHJhdG9yIDE5LjAuMCwgU1ZHIEV4cG9ydCBQbHVnLUluIC4gU1ZHIFZlcnNpb246IDYuMDAgQnVpbGQgMCkgIC0tPg0KPHN2ZyB2ZXJzaW9uPSIxLjEiIGlkPSJDYXBhXzEiIHhtbG5zPSJodHRwOi8vd3d3LnczLm9yZy8yMDAwL3N2ZyIgeG1sbnM6eGxpbms9Imh0dHA6Ly93d3cudzMub3JnLzE5OTkveGxpbmsiIHg9IjBweCIgeT0iMHB4Ig0KCSB2aWV3Qm94PSIwIDAgNTEyLjAwNSA1MTIuMDA1IiBzdHlsZT0iZW5hYmxlLWJhY2tncm91bmQ6bmV3IDAgMCA1MTIuMDA1IDUxMi4wMDU7IiB4bWw6c3BhY2U9InByZXNlcnZlIj4NCjxyZWN0IHg9IjIyNy43NzgiIHk9IjI4Ny42OTQiIHN0eWxlPSJmaWxsOiM5NUFCQjA7IiB3aWR0aD0iODkuNTExIiBoZWlnaHQ9IjIwNy43NzEiLz4NCjxnPg0KCTxyZWN0IHg9IjIyNy43NzgiIHk9IjI4Ny42OTQiIHN0eWxlPSJmaWxsOiM3ODg3OEM7IiB3aWR0aD0iMzkuMjU3IiBoZWlnaHQ9IjIwNy43NzEiLz4NCgk8cmVjdCB4PSIyMjcuNzc4IiB5PSIyODQuNTU0IiBzdHlsZT0iZmlsbDojNzg4NzhDOyIgd2lkdGg9Ijg5LjUxMSIgaGVpZ2h0PSI0NS42NDMiLz4NCjwvZz4NCjxyZWN0IHk9IjI3MC45MzMiIHN0eWxlPSJmaWxsOiNGRkNBM0I7IiB3aWR0aD0iNTEyLjAwNSIgaGVpZ2h0PSIyOC4yNjYiLz4NCjxyZWN0IHk9IjI3MC45MzMiIHN0eWxlPSJmaWxsOiNFNUIwMkM7IiB3aWR0aD0iMjIxLjQxMyIgaGVpZ2h0PSIyOC4yNjUiLz4NCjxwYXRoIHN0eWxlPSJmaWxsOiMwMDg2Q0M7IiBkPSJNNTExLjk5OCwxMjcuMjUxVjI3MC45M0gxODUuMzgxTDE1OC45NDYsNjIuMDgxbC01Ljc1OC00NS41NDFoMjQ4LjEwOQ0KCWMzNi43NzgsMCw2OS4zNjksMTcuOTQ0LDg5LjQ5LDQ1LjU0MUM1MDQuMTM2LDgwLjM3LDUxMS45OTgsMTAyLjg4OSw1MTEuOTk4LDEyNy4yNTF6Ii8+DQo8cGF0aCBzdHlsZT0iZmlsbDojNDE0NTUxOyIgZD0iTTI2My44ODksMTI3LjI1MVYyNzAuOTNINDIuNDc3VjEyNy4yNTFjMC02MS4xNCw0OS41NzItMTEwLjcxMSwxMTAuNzExLTExMC43MTENCgljNDEuNDU4LDAsNzcuNTg3LDIyLjc5MSw5Ni41NTcsNTYuNTM0QzI1OC43NDksODkuMDgxLDI2My44ODksMTA3LjU2OSwyNjMuODg5LDEyNy4yNTF6Ii8+DQo8cGF0aCBzdHlsZT0iZmlsbDojMDI3QUFGOyIgZD0iTTMyMC45NzIsMTYuNTQ0aC05NS43ODhjNjkuNTUxLDI0LjE5MSw1OC41MjUsODcuNjksNTcuNjEsMTczLjA4Ng0KCWMtMC4wNTksNS40OTMsNC4zOTIsOS45NzksOS44ODUsOS45NzloNDAuMzExTDMyMC45NzIsMTYuNTQ0Ii8+DQo8cGF0aCBzdHlsZT0iZmlsbDojQzExMDFEOyIgZD0iTTMwNi4zNywyMDAuMjY0aDE3LjY5MWM0LjY1NSwwLDguNDI4LTMuNzczLDguNDI4LTguNDI4Vjk0LjY3MmMwLTQuNjU1LDMuNzczLTguNDI4LDguNDI4LTguNDI4DQoJaDUyLjIzN2M0LjY1NSwwLDguNDI4LTMuNzczLDguNDI4LTguNDI4VjI0Ljk2OGMwLTQuNjU1LTMuNzczLTguNDI4LTguNDI4LTguNDI4SDMzMC43MWMtMTguMDk3LDAtMzIuNzY3LDE0LjY3LTMyLjc2NywzMi43NjcNCgl2MTQyLjUyOUMyOTcuOTQyLDE5Ni40OTEsMzAxLjcxNSwyMDAuMjY0LDMwNi4zNywyMDAuMjY0eiIvPg0KPHBhdGggc3R5bGU9ImZpbGw6I0MwQzJEMDsiIGQ9Ik0yNjMuODg5LDEyNy4yNTF2MTQzLjY1OEg5Ni45NDhWNzMuMDczaDE1Mi43OThDMjU4Ljc0OSw4OS4wODEsMjYzLjg4OSwxMDcuNTY5LDI2My44ODksMTI3LjI1MXoiLz4NCjxwb2x5Z29uIHN0eWxlPSJmaWxsOiNEMEQ3REQ7IiBwb2ludHM9IjI2My44ODksMTcyLjAwNyAyNjMuODg5LDI3MC45MyA5Ni45NjksMjcwLjkzIDk2Ljk5LDI3MC45MDkgMjA1Ljk2MywxNzIuMDE3IA0KCTIwNS45NzQsMTcyLjAwNyAiLz4NCjxwYXRoIHN0eWxlPSJmaWxsOiNFNkVCRUY7IiBkPSJNMjYzLjg4OSwxMjcuMjUxdjcxLjc4N2wtMTQuMDgxLDEyLjc4M2wtNDMuODQ1LTM5LjgwNEw5Ni45NDgsNzMuMDczaDE1Mi43OTgNCglDMjU4Ljc0OSw4OS4wODEsMjYzLjg4OSwxMDcuNTY5LDI2My44ODksMTI3LjI1MXoiLz4NCjxwYXRoIHN0eWxlPSJmaWxsOiNDMEMyRDA7IiBkPSJNMjYzLjg4OSwxMjcuMjUxVjI3MC45M2gtNjcuMDAzVjEyNy4yNTFjMC0xOS42ODItNS4xNC0zOC4xNzEtMTQuMTQ0LTU0LjE3OGg2Ny4wMDMNCglDMjU4Ljc0OSw4OS4wODEsMjYzLjg4OSwxMDcuNTY5LDI2My44ODksMTI3LjI1MXoiLz4NCjxnPg0KPC9nPg0KPGc+DQo8L2c+DQo8Zz4NCjwvZz4NCjxnPg0KPC9nPg0KPGc+DQo8L2c+DQo8Zz4NCjwvZz4NCjxnPg0KPC9nPg0KPGc+DQo8L2c+DQo8Zz4NCjwvZz4NCjxnPg0KPC9nPg0KPGc+DQo8L2c+DQo8Zz4NCjwvZz4NCjxnPg0KPC9nPg0KPGc+DQo8L2c+DQo8Zz4NCjwvZz4NCjwvc3ZnPg0K",
      openEmptyPostBox: "data:image/svg+xml;base64,PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iaXNvLTg4NTktMSI/Pg0KPCEtLSBHZW5lcmF0b3I6IEFkb2JlIElsbHVzdHJhdG9yIDE5LjAuMCwgU1ZHIEV4cG9ydCBQbHVnLUluIC4gU1ZHIFZlcnNpb246IDYuMDAgQnVpbGQgMCkgIC0tPg0KPHN2ZyB2ZXJzaW9uPSIxLjEiIGlkPSJDYXBhXzEiIHhtbG5zPSJodHRwOi8vd3d3LnczLm9yZy8yMDAwL3N2ZyIgeG1sbnM6eGxpbms9Imh0dHA6Ly93d3cudzMub3JnLzE5OTkveGxpbmsiIHg9IjBweCIgeT0iMHB4Ig0KCSB2aWV3Qm94PSIwIDAgNTEyLjAwNSA1MTIuMDA1IiBzdHlsZT0iZW5hYmxlLWJhY2tncm91bmQ6bmV3IDAgMCA1MTIuMDA1IDUxMi4wMDU7IiB4bWw6c3BhY2U9InByZXNlcnZlIj4NCjxyZWN0IHg9IjIyNy43NzgiIHk9IjI4Ni42NDciIHN0eWxlPSJmaWxsOiM5NUFCQjA7IiB3aWR0aD0iODkuNTExIiBoZWlnaHQ9IjIwOC44MTgiLz4NCjxnPg0KCTxyZWN0IHg9IjIyNy43NzgiIHk9IjI4Ni42NDciIHN0eWxlPSJmaWxsOiM3ODg3OEM7IiB3aWR0aD0iMzkuMjU3IiBoZWlnaHQ9IjIwOC44MTgiLz4NCgk8cmVjdCB4PSIyMjcuNzc4IiB5PSIyODQuNTU0IiBzdHlsZT0iZmlsbDojNzg4NzhDOyIgd2lkdGg9Ijg5LjUxMSIgaGVpZ2h0PSI0NS42NDMiLz4NCjwvZz4NCjxyZWN0IHk9IjI3MC45MzMiIHN0eWxlPSJmaWxsOiNGRkNBM0I7IiB3aWR0aD0iNTEyLjAwNSIgaGVpZ2h0PSIyOC4yNjYiLz4NCjxyZWN0IHk9IjI3MC45MzMiIHN0eWxlPSJmaWxsOiNFNUIwMkM7IiB3aWR0aD0iMjIxLjQxMyIgaGVpZ2h0PSIyOC4yNjUiLz4NCjxwYXRoIHN0eWxlPSJmaWxsOiMwMDg2Q0M7IiBkPSJNNTExLjk5OCwxMjcuMjUxVjI3MC45M0gxODUuMzgxTDE1OC45NDYsNjIuMDgxbC01Ljc1OC00NS41NDFoMjQ4LjEwOQ0KCWMzNi43NzgsMCw2OS4zNjksMTcuOTQ0LDg5LjQ5LDQ1LjU0MUM1MDQuMTM2LDgwLjM3LDUxMS45OTgsMTAyLjg4OSw1MTEuOTk4LDEyNy4yNTF6Ii8+DQo8cGF0aCBzdHlsZT0iZmlsbDojNDE0NTUxOyIgZD0iTTE1My4xODcsMTYuNTQ0TDE1My4xODcsMTYuNTQ0Yy02MS4xNDEsMC0xMTAuNzA2LDQ5LjU2NC0xMTAuNzA2LDExMC43MDZ2MTQzLjY4MmgyMjEuNDExVjEyNy4yNQ0KCUMyNjMuODkzLDY2LjEwOCwyMTQuMzI4LDE2LjU0NCwxNTMuMTg3LDE2LjU0NHoiLz4NCjxwYXRoIHN0eWxlPSJmaWxsOiNDMTEwMUQ7IiBkPSJNMjk4LjQzOSwxNDAuMjV2MTYuMzk3YzAsNS4wMTIsNC4wNjMsOS4wNzUsOS4wNzUsOS4wNzVoOTUuODdjNS4wMTIsMCw5LjA3NSw0LjA2Myw5LjA3NSw5LjA3NQ0KCXY1Mi41MTNjMCw1LjAxMiw0LjA2Myw5LjA3NSw5LjA3NSw5LjA3NWg1MS41NTVjNS4wMTIsMCw5LjA3NS00LjA2Myw5LjA3NS05LjA3NXYtNjIuNzIxYzAtMTguNDU0LTE0Ljk2LTMzLjQxNC0zMy40MTQtMzMuNDE0DQoJSDMwNy41MTRDMzAyLjUwMywxMzEuMTc1LDI5OC40MzksMTM1LjIzOCwyOTguNDM5LDE0MC4yNXoiLz4NCjxnPg0KPC9nPg0KPGc+DQo8L2c+DQo8Zz4NCjwvZz4NCjxnPg0KPC9nPg0KPGc+DQo8L2c+DQo8Zz4NCjwvZz4NCjxnPg0KPC9nPg0KPGc+DQo8L2c+DQo8Zz4NCjwvZz4NCjxnPg0KPC9nPg0KPGc+DQo8L2c+DQo8Zz4NCjwvZz4NCjxnPg0KPC9nPg0KPGc+DQo8L2c+DQo8Zz4NCjwvZz4NCjwvc3ZnPg0K",
      userInfo: {},
      text:''
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
      this.$router.push({ name: 'Profile', params: {userName: this.userInfo.userNickname}})
    },
    goToMain() {
      this.$router.push({ name: 'MainPage' })
    },
    tempOverImg() {
      this.checkReceivedMessage()
      this.postBox = this.openPostBox
    },
    tempOutImg() {
      this.postBox = this.closePostBox
    },
    showInput() {
      this.checkInput = !this.checkInput
    },
    hideInput() {
      this.checkInput = true
      this.$router.push(`/search/${this.text}`);
      this.text=null
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
      this.userInfo = ""
      this.isLogin = false
      this.$router.push({ name: 'MainPage' })
    },
    postBoxClick() {
      this.$router.push({ name: 'Received' })
    },
    checkReceivedMessage(){
      const headers = {
        "Authorization": localStorage.getItem('jwt')
      }
      this.$axios.get(`${this.$store.getters.getServer}/receivemsg`,{headers})
      .then(res => {
        if(res.data.data.length) {
          this.openPostBox = this.openFullPostBox
        } else {
          this.openPostBox = this.openEmptyPostBox
        }
      })
      .catch(err => {
        console.error(err)
      })
    },
  },
  watch: {
    $route(to) {
      this.pageName = to.name
      this.checkLogin()
      this.userInfo = this.$store.getters.getUser
    },
    openPostBox(newOpenPostBox) {
      this.openPostBox = newOpenPostBox
      this.postBox = newOpenPostBox
    }
  },
  mounted() {
    this.checkLogin()
    const token = localStorage.getItem('jwt')
    const headers = {
      "Authorization" : token
    }
    if (token) {
      this.$axios.get(`${this.$store.getters.getServer}/user`, {headers})
      .then(res => {
        this.userInfo = res.data.data
        this.$store.commit('UpdateUserInfo', res.data.data)
      })
      .catch(err => {
        console.error(err)
      })
    } 
  }
}
</script>

<style>
  #title-logo:hover {
    cursor: pointer;
  }
  .background-nav {
    background-color: white;
    position: fixed;
    top: 0;
    width: 100%;
    display: flex;
    align-items: center;
    justify-content: space-around;
    padding-top: 0;
    padding-bottom: 0;
    z-index: 1000;
    box-shadow: 0px 5px 10px -10px gray;
  }
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
  .bi-person-fill {
    display: inline-block;
    width: 100%;
    height: 100%
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
    color: rgba(255, 255, 255, 0.9);
  }
  .main-dropdown-item {
    flex: inline-block; 
    width: 100px; 
    text-align: center; 
    justify-content: center; 
    margin: 0;
  }
  .main-dropdown-text {
    font-size: 0.8rem;
  }
  .list-hr {
    margin-left: 15px;
    margin-right: 15px;
    margin-top: 5px;
    margin-bottom: 5px;
  }
  .postbox {
    position: fixed;
    bottom: 0;
    right: 6rem;
    z-index: 2;
    width: 7rem;
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
    margin: 0 20px;
    /* margin-left: 20px;  */
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
</style>