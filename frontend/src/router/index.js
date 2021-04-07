import Vue from 'vue';
import VueRouter from 'vue-router';

Vue.use(VueRouter);

const routes = [
  {
    path: '/',
    name: 'MainPage',
    component: () => import('../views/Main.vue'),
  },
  {
    path: '/',
    name: 'Background',
    component: () => import(/* webpackChunkName: "about" */ '../views/BackgroundNav.vue'),
    children: [
      {
        path: '/main',
        name: 'MainBook',
        component: () => import('../views/MainBook.vue'),
      },
      {
        path: '/login',
        name: 'Login',
        component: () => import('../components/Login/Login.vue'),
        beforeEnter: (to, from, next) => {
          if (localStorage.getItem('jwt')) {
            next({name: 'MainBook'})
            alert('잘못된 접근입니다.')
          } else {
            next()
          }
        }
      },
      {
        path: '/signup',
        name: 'Signup',
        component: () => import('../components/Signup/Signup.vue'),
        beforeEnter: (to, from, next) => {
          if (localStorage.getItem('jwt')) {
            next({name: 'MainBook'})
            alert('잘못된 접근입니다.')
          } else {
            next()
          }
        },
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
            beforeEnter: (to, from, next) => {
              if (from.name==='SubmitEmail') {
                next()
              } else {
                next({name: 'MainBook'})
                alert('잘못된 접근입니다.')
              }
            }
          },
          {
            path: '3',
            name: 'InputName',
            component: () => import('../components/Signup/InputName.vue'),
            beforeEnter: (to, from, next) => {
              if (from.name==='CheckEmail') {
                next()
              } else {
                next({name: 'MainBook'})
                alert('잘못된 접근입니다.')
              }
            }
          },
          {
            path: '4',
            name: 'SubmitPref',
            component: () => import('../components/Signup/SubmitPref.vue'),
            beforeEnter: (to, from, next) => {
              if (from.name==='InputName') {
                next()
              } else {
                next({name: 'MainBook'})
                alert('잘못된 접근입니다.')
              }
            }
          },
          {
            path: '5',
            name: 'SignupComplete',
            component: () => import('../components/Signup/SignupComplete.vue'),
            beforeEnter: (to, from, next) => {
              if (from.name==='SubmitPref') {
                next()
              } else {
                next({name: 'MainBook'})
                alert('잘못된 접근입니다.')
              }
            }
          },
        ],
      },
      {
        path: '/subsignup',
        name: 'SubSignup',
        component: () => import('../components/SubSignup/SubSignup.vue'),
        children: [
          {
            path: '1',
            name: 'SubmitGender',
            component: () => import('../components/SubSignup/SubmitGender.vue'),
            beforeEnter: (to, from, next) => {
              if (from.name==='SignupComplete' || from.name==='SubmitBirth') {
                next()
              } else {
                next({name: 'MainBook'})
                alert('잘못된 접근입니다.')
              }
            }
          },
          {
            path: '2',
            name: 'SubmitBirth',
            component: () => import('../components/SubSignup/SubmitBirth.vue'),
            beforeEnter: (to, from, next) => {
              if (from.name==='SubmitGender' || from.name==='SubmitJob') {
                next()
              } else {
                next({name: 'MainBook'})
                alert('잘못된 접근입니다.')
              }
            }
          },
          {
            path: '3',
            name: 'SubmitJob',
            component: () => import('../components/SubSignup/SubmitJob.vue'),
            beforeEnter: (to, from, next) => {
              if (from.name==='SubmitBirth' || from.name==='SubmitPic') {
                next()
              } else {
                next({name: 'MainBook'})
                alert('잘못된 접근입니다.')
              }
            }
          },
          {
            path: '4',
            name: 'SubmitPic',
            component: () => import('../components/SubSignup/SubmitPic.vue'),
            beforeEnter: (to, from, next) => {
              if (from.name==='SubmitJob') {
                next()
              } else {
                next({name: 'MainBook'})
                alert('잘못된 접근입니다.')
              }
            }
          },
          {
            path: '5',
            name: 'SubSignComplete',
            component: () => import('../components/SubSignup/SubSignComplete.vue'),
            beforeEnter: (to, from, next) => {
              if (from.name==='SubmitPic') {
                next()
              } else {
                next({name: 'MainBook'})
                alert('잘못된 접근입니다.')
              }
            }
          },
        ],
      },
      {
        path:'/profile/:userName',
        name: 'Profile',
        component: () => import('../views/Profile.vue'),
        redirect: '/profile/:userName/genre',
        beforeEnter: (to, from, next) => {
          if (localStorage.getItem('jwt')) {
            next()
          } else {
            next({name: 'Login'})
            alert('로그인이 필요한 서비스입니다.')
          }
        },
        props: true,
        children: [
          {
            path: 'genre',
            name: 'PreferenceGenre',
            component: () => import('../components/Profile/PreferenceGenre.vue'),
          },
          {
            path: 'sentence',
            name: 'SaveSentence',
            component: () => import('../components/Profile/SaveSentence.vue'),
          },
          {
            path: 'books',
            name: 'ReadBooks',
            component: () => import('../components/Profile/ReadBooks.vue'),
          },
          {
            path: 'liked',
            name: 'LikeSentence',
            component: () => import('../components/Profile/LikeSentence.vue'),
          },
          {
            path: 'wishlist',
            name: 'WishList',
            component: () => import('../components/Profile/WishList.vue'),
          },
        ],
      },
      {
        path: '/search/:word?',
        name: 'Search',
        component: () => import('../views/Search.vue'),
        children: [],
      },
      {
        path: '/message',
        name: 'Message',
        component: () => import('../components/Message/Message.vue'),
        redirect: '/message/received',
        beforeEnter: (to, from, next) => {
          if (localStorage.getItem('jwt')) {
            next()
          } else {
            next({name: 'Login'})
            alert('로그인이 필요한 서비스입니다.')
          }
        },
        children: [
          {
            path: 'received',
            name: 'Received',
            component: () => import('../components/Message/Received.vue'),
          },
          {
            path: 'sent',
            name: 'Sent',
            component: () => import('../components/Message/Sent.vue'),
          },
          {
            path: 'write',
            name: 'Write',
            component: () => import('../components/Message/Write.vue'),
          },
          {
            path: 'readSent/:id',
            name: 'ReadSent',
            component: () => import('../components/Message/ReadSent.vue'),
          },
          {
            path: 'readReceived/:id',
            name: 'ReadReceived',
            component: () => import('../components/Message/ReadReceived.vue'),
          },
        ],
      },
      {
        path: '/book/:bookIsbn',
        name: 'BookDetail',
        component: () => import('../views/BookDetail.vue'),
        redirect: '/book/:bookIsbn/firstpage',
        props: true,
        children: [
          {
            path: 'firstpage',
            name: 'FirstPage',
            component: () => import('../components/Book/FirstPage.vue'),
            props: true,
          },
          {
            path: 'secondpage',
            name: 'SecondPage',
            component: () => import('../components/Book/SecondPage.vue'),
            props: true,
          },
          {
            path: 'thirdpage',
            name: 'ThirdPage',
            component: () => import('../components/Book/ThirdPage.vue'),
            props: true,
          },
        ],
      },
      {
        path: 'password',
        name: 'Password',
        component: () => import('../components/Password/Password.vue'),
        redirect: 'password/submitEmail',
        beforeEnter: (to, from, next) => {
          if (localStorage.getItem('jwt')) {
            next({name: 'MainBook'})
            alert('잘못된 접근입니다.')
          } else {
            next()
          }
        },
        children: [
          {
            path: 'submitEmail',
            name: 'PasswordSubmitEmail',
            component: () => import('../components/Password/SubmitEmail.vue'),
          },
          {
            path: 'checkEmail',
            name: 'PasswordCheckEmail',
            component: () => import('../components/Password/CheckEmail.vue'),
          },
          {
            path: 'reset',
            name: 'PasswordReset',
            component: () => import('../components/Password/Reset.vue'),
          },
        ],
      },
      {
        path: '/community',
        name: 'Community',
        component: () => import('../views/Community.vue'),
      },
    ],
  },
  {
    path: '*',
    name: 'NotFound',
    component: () => import('../views/NotFound.vue'),
  },
];

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes,
});

export default router;
