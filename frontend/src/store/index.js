import Vue from "vue"
import Vuex from "vuex"

Vue.use(Vuex)

const SERVER_URL = process.env.VUE_APP_SERVER

export default new Vuex.Store({
  state: {
    server: SERVER_URL,
  },
  getters: {
    getServer(state) {
      return state.server
    }
  },
  mutations: {

  },
  actions: {
    
  }
})