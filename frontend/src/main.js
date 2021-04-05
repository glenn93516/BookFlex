import Vue from 'vue'
import App from './App.vue'
import axios from 'axios'
import router from './router'
import store from './store'
import { BootstrapVue, BootstrapVueIcons } from 'bootstrap-vue'

import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import '../public/reset.css'

import { library } from '@fortawesome/fontawesome-svg-core'
import { faTimesCircle as fasTimesCircle } from '@fortawesome/free-solid-svg-icons'
import { faTimesCircle as farTimesCircle } from '@fortawesome/free-regular-svg-icons'
import { faEdit as fasEdit } from '@fortawesome/free-solid-svg-icons'
import { faEdit as farEdit } from '@fortawesome/free-regular-svg-icons'
import { faTrashAlt as fasTrashAlt } from '@fortawesome/free-solid-svg-icons'
import { faTrashAlt as farTrashAlt } from '@fortawesome/free-regular-svg-icons'
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome'

library.add(
  fasTimesCircle,
  farTimesCircle,
  fasEdit,
  farEdit,
  fasTrashAlt,
  farTrashAlt,
)

Vue.component('font-awesome-icon', FontAwesomeIcon)

Vue.config.productionTip = false

Vue.use(BootstrapVue)
Vue.use(BootstrapVueIcons)

Vue.config.productionTip = false
Vue.prototype.$axios=axios

new Vue({
  router,
  store,
  render: h => h(App),
}).$mount('#app')
