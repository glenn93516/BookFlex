import Vue from "vue"
import Vuex from "vuex"
import axios from "axios"
import router from '../router'

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
      state.user.userProfileImg = payload.userProfileImg
      state.user.userId = payload.userId
      state.user.userRole = payload.userRole
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
      axios.post(`${SERVER_URL}/user/login`, {
          "userEmail": user.userEmail,
          "userPassword": user.userPassword
        })
        .then(res => {
          // 로그인이 됐을 때
          const token =`Bearer ${res.data.data}`
          const data = {
            "token": token,
            "mode": user.mode
          }
          localStorage.setItem('jwt', token)
          context.commit("Login", token)
          context.dispatch('GetUserInfo', data)
        })
        .catch(err => {
          alert("아이디와 비밀번호를 확인해주세요.")
          console.log(err)
        })
    },
    GetUserInfo (context, data) {
      const headers = {
        'Authorization' : data.token
      }
      axios.get(`${SERVER_URL}/user`, {headers})
      .then(res => {
        context.commit("GetUserInfo", res.data.data)
        console.log(res.data.data)
        if (data.mode) {
          router.push({ name: "MainBook" })
        } 
      })
      .catch(err => {
        console.error(err)
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
      for (let val of data.values()) {
        console.log(val)
      }
      const token = localStorage.getItem('jwt')
      const headers = {
        "Authorization": token
      }
      axios.put(`${SERVER_URL}/user`, data, {headers})
      .then(res => {
        console.log(res)
        const data = {
          "token": localStorage.getItem('jwt'),
          "mode": 0
        }
        context.dispatch('GetUserInfo', data)
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