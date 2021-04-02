import Vue from 'vue';
import VueRouter from 'vue-router';
// import Home from '../views/Home.vue'

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
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
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
      },
      {
        path: '/signup',
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
          {
            path: '5',
            name: 'SignupComplete',
            component: () => import('../components/Signup/SignupComplete.vue'),
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
          },
          {
            path: '2',
            name: 'SubmitBirth',
            component: () => import('../components/SubSignup/SubmitBirth.vue'),
          },
          {
            path: '3',
            name: 'SubmitJob',
            component: () => import('../components/SubSignup/SubmitJob.vue'),
          },
          {
            path: '4',
            name: 'SubmitPic',
            component: () => import('../components/SubSignup/SubmitPic.vue'),
          },
          {
            path: '5',
            name: 'SubSignComplete',
            component: () => import('../components/SubSignup/SubSignComplete.vue'),
          },
        ],
      },
      {
        path:'/:userName',
        name: 'Profile',
        component: () => import('../views/Profile.vue'),
        redirect: '/:userName/genre',
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
        ]
      },
      {
        path: '/bookdetail',
        name: 'BookDetail',
        component: () => import('../views/BookDetail.vue'),
        redirect: '/bookdetail/firstpage',
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
          {
            path: 'fourthpage',
            name: 'FourthPage',
            component: () => import('../components/Book/FourthPage.vue'),
            props: true,
          },
        ]
      },
      {
        path: 'password',
        name: 'Password',
        component: () => import('../components/Password/Password.vue'),
        redirect: 'password/submitEmail',
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
        ]
      }
    ]
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
// router guard로 이메일 인증 건너뛰기 막아줘야함

export default router;
