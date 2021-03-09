import Vue from 'vue'
import VueRouter from 'vue-router'
// import Home from '../views/Home.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Main',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/BackgroundNav.vue'),
    children: [
      {
        path:'/login',
        name: 'Login',
        component: () => import('../components/Login/Login.vue')
      },
      {
        path:'/signup',
        name: 'Signup',
        component: () => import('../components/Signup/Signup.vue'),
        children: [
          {
            path: '1',
            name: 'SubmitEmail',
            component: () => import('../components/Signup/SubmitEmail.vue'),
          },
          {
            path: '2',
            name: 'CheckEmail',
            component: () => import('../components/Signup/CheckEmail.vue'),
          },
          {
            path: '3',
            name: 'InputName',
            component: () => import('../components/Signup/InputName.vue'),
          },
          {
            path: '4',
            name: 'SubmitPref',
            component: () => import('../components/Signup/SubmitPref.vue'),
          },
        ]
      },
    ]
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})
// router guard로 이메일 인증 건너뛰기 막아줘야함

export default router
