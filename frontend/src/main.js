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
import { faThumbsDown } from '@fortawesome/free-regular-svg-icons'
import { faThumbsUp } from '@fortawesome/free-regular-svg-icons'
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome'

// import * as am4core from '@amcharts/amcharts4/core';
// import * as am4charts from '@amcharts/amcharts4/charts';
// import '@amcharts/amcharts4/charts';

library.add(
  fasTimesCircle,
  farTimesCircle,
  fasEdit,
  farEdit,
  fasTrashAlt,
  farTrashAlt,
  faThumbsDown,
  faThumbsUp,
)

Vue.component('font-awesome-icon', FontAwesomeIcon)

Vue.config.productionTip = false

// import 'fulllpage.js/vendors/scrolloverflow' // Optional. When using scrollOverflow: true
// import './fullpage.scrollHorizontally.min' // Optional. When using fullpage extensions
import VueFullPage from 'vue-fullpage.js'

Vue.use(BootstrapVue)
Vue.use(BootstrapVueIcons)
Vue.use(VueFullPage)

Vue.config.productionTip = false
Vue.prototype.$axios=axios


new Vue({
  router,
  store,
  render: h => h(App),
}).$mount('#app')
