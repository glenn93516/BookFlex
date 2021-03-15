import Vue from "vue"
import Vuex from "vuex"
// import axios from "axios"

Vue.use(Vuex)

const SERVER_URL = process.env.VUE_APP_SERVER_URL

export default new Vuex.Store({
  state: {
    server: SERVER_URL,
    accessToken: "",
    signupInfo: {
      userEmail: "",
      userPassword: "",
      userNickname: "",
      // userGenres: [],
    },
    user: {
      userEmail: "",
      userNickname: "",
      userPassword: "",
      // userGenres: [],
      userProfileImg: "",
      userProfileImgFile: "", // file ?
      userBirth: "",
      userGender: "",
      userJob: "",
      // 입력 X
      userId: Number,
      userRole: "",
    }
  },
  getters: {
    getServer(state) {
      return state.server
    },
    getAccessToken(state) {
      return state.accessToken
    },
    getUser(state) {
      return state.user
    },
    getEmail(state) {
      return state.signupInfo.userEmail
    }
  },
  mutations: {
    Login (state, payload) {
      state.accessToken = payload
    },
    getUserInfo(state, payload) {
      state.user.userId = payload.userId
      state.user.userNickname = payload.userNickname
      state.user.userEmail = payload.userEmail
      state.user.userProfileImg = payload.userProfileImg
    },
    setEmail(state, payload) {
      state.signupInfo.userEmail = payload.userEmail
    },
    setSignupInfo(state, payload) {
      state.signupInfo.userNickname = payload.userNickname
      state.signupInfo.userPassword = payload.userPassword
    },
  },
  actions: {
    Login (context, user) {
      console.log(user)
      console.log(SERVER_URL)
      return this.$axios
        .post(`${SERVER_URL}/user/login`, {
          "userEmail": user.userEmail,
          "userPassword": user.userPassword
        })
        .then(res => {
          // 로그인이 됐을 때
          if (res.data != "undefined") {
            console.log('bearer res.data.data')
            console.log(`Bearer ${res.data.data}`)
            this.$axios.defaults.headers.common[
              "Authorization"
            ] = `Bearer ${res.data['data']}`
          }
          context.commit("Login", res.data.data)
        })
        .catch(err => {
          console.log(err)
        })
    },
    getUserInfo (context) {
      console.log('getUserInfo----')
      console.log(this.$axios.defaults.headers)
      return this.$axios
      .get(`${SERVER_URL}/user`)
      .then(res => {
        console.log(res)
        context.commit("getUserInfo", res.data.data)
      })
      .catch(err => {
        console.log(err)
      })
    }
  }
})