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
    },
    getSignupInfo(state) {
      return state.signupInfo
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
      // state.user.userGenres = payload.userGenres
      state.user.userBirth = payload.userBirth
      state.user.userGender = payload.userGender
      state.user.userJob = payload.userJob
      state.user.userRole = payload.userRole
    },
    setEmail(state, payload) {
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
          context.commit("Login", res.data.data)
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
      console.log('액션스 user', User)
      axios.put(`${SERVER_URL}/user`, {
        userEmail: User.userEmail,
        userNickname: User.userNickname,
        userPassword: User.userPassword,
        userProfileImgFile: User.userProfileImgFile,
        userBirth: User.userBirth,
        userGender: User.userGender,
        userJob: User.userJob,
      })
      .then(res => {
        console.log(res)
        context.commit('UpdateUserInfo', User)
      })
      .catch(err => {
        console.error(err)
      })
    },
    SetEmail(context, Email) {
      context.commit("setEmail", Email)
    },
    SetSignupInfo(context, user) {
      context.commit('SetSignupInfo', user)
    }
  }
})