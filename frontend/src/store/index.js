import Vue from "vue"
import Vuex from "vuex"
import axios from "axios"

Vue.use(Vuex)

const SERVER_URL = process.env.VUE_APP_SERVER_URL

export default new Vuex.Store({
  state: {
    server: SERVER_URL,
    accessToken: "",
    user: {
      userId: Number,
      userEmail: "",
      userNickname: "",
      userProfileImg: "",
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
    }
  },
  actions: {
    Login (context, user) {
      console.log(user)
      console.log(SERVER_URL)
      return axios
        .post(`${SERVER_URL}/user/login`, {
          "userEmail": user.userEmail,
          "userPassword": user.userPassword
        })
        .then(res => {
          // 로그인이 됐을 때
          if (res.data != "undefined") {
            console.log('bearer res.data.data')
            console.log(`Bearer ${res.data.data}`)
            axios.defaults.headers.common[
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
      console.log(axios.defaults.headers)
      return axios
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