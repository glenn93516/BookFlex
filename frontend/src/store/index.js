import Vue from "vue"
import Vuex from "vuex"
import axios from "axios"

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
      genres: [],
    },
    user: {
      userEmail: "",
      userNickname: "",
      userPassword: "",
      genres: [],
      userProfileImg: "",
      userProfileImgFile: "", // file ?
      userBirth: "",
      userGender: "",
      userJob: "",
      // 입력 X
      userId: Number,
      userRole: "",
    },
    selectedBook: "",
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
    },
    getSignupInfo(state) {
      return state.signupInfo
    },
    getSelectedBook(state) {
      console.log('이거 되는겨?1')
      return state.selectedBook
    }
  },
  mutations: {
    Login (state, payload) {
      state.accessToken = payload
    },
    GetUserInfo(state, payload) {
      state.user.userId = payload.userId
      state.user.userEmail = payload.userEmail
      state.user.userNickname = payload.userNickname
      state.user.userProfileImg = payload.userProfileImg
      state.user.genres = payload.genres
      state.user.userBirth = payload.userBirth
      state.user.userGender = payload.userGender
      state.user.userJob = payload.userJob
      state.user.userRole = payload.userRole
    },
    SetEmail(state, payload) {
      state.signupInfo.userEmail = payload.userEmail
    },
    SetSignupInfo(state, payload) {
      state.signupInfo.userNickname = payload.userNickname
      state.signupInfo.userPassword = payload.userPassword
    },
    SubmitUserGender(state, payload) {
      state.user.userGender = payload
    },
    SubmitUserBirth(state, payload) {
      state.user.userBirth = payload
    },
    SubmitUserJob(state, payload) {
      state.user.userJob = payload
    },
    SubmitUserPic(state, payload) {
      state.user.userProfileImgFile = payload
    },
    UpdateUserInfo(state, payload) {
      state.user.userEmail = payload.userEmail
      state.user.userNickname = payload.userNickname
      state.user.userBirth = payload.userBirth
      state.user.userGender = payload.userGender
      state.user.userJob = payload.userJob
    },
    SubmitPref(state, payload) {
      state.signupInfo.genres = payload
      state.user.genres = payload
      console.log(payload)
      console.log(state.user.genres)
    },
    Logout(state) {
      state.accessToken = ""
    },
    SelectBook(state, payload) {
      console.log('이거 되는겨?2')
      state.selectedBook = payload
    }
  },
  actions: {
    Login (context, user) {
      return axios.post(`${SERVER_URL}/user/login`, {
          "userEmail": user.userEmail,
          "userPassword": user.userPassword
        })
        .then(res => {
          // 로그인이 됐을 때
          if (res.data != "undefined") {
            axios.defaults.headers.common[
              "Authorization"
            ] = `Bearer ${res.data['data']}`
          }
          localStorage.setItem('jwt', `Bearer ${res.data.data}`)
          context.commit("Login", res.data.data)
          console.log(`Bearer ${res.data.data}`)
        })
        .catch(err => {
          console.log(err)
        })
    },
    GetUserInfo (context) {
      return axios.get(`${SERVER_URL}/user`)
      .then(res => {
        context.commit("GetUserInfo", res.data.data)
      })
      .catch(err => {
        console.log(err)
      })
    },
    SubmitUserGender(context, gender) {
      context.commit("SubmitUserGender", gender)
    },
    SubmitUserBirth(context, birthday) {
      context.commit("SubmitUserBirth", birthday)
    },
    SubmitUserJob(context, Job) {
      context.commit("SubmitUserJob", Job)
    },
    SubmitUserPic(context, Img) {
      context.commit("SubmitUserPic", Img)
    },
    UpdateUserInfo(context, User) {
      // let FormData = require('form-data')
      let data = new FormData()
      data.append('userProfileImgFile', User.userProfileImgFile)
      data.append('userEmail', User.userEmail)
      data.append('userNickname', User.userNickname)
      data.append('userBirth', User.userBirth)
      data.append('userGender', User.userGender)
      data.append('userJob', User.userJob)
      console.log('@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@')
      for (let val of data.values()) {
        console.log(val)
      }
      console.log(typeof(data), '타입')
      console.log('@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@')
      console.log('액션스 user', User)

      axios.put(`${SERVER_URL}/user`, data)
      .then(res => {
        console.log(res)
        context.commit('UpdateUserInfo', User)
      })
      .catch(err => {
        console.error(err)
      })
    },
    SetEmail(context, Email) {
      context.commit("SetEmail", Email)
    },
    SetSignupInfo(context, user) {
      context.commit('SetSignupInfo', user)
    },
    Logout(context) {
      context.commit('Logout')
    },
    
  }
})