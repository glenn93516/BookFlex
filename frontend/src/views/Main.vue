<template>
  <div id="main">
    <div style="position: absolute; top: 0;">
      <!-- 첫번째 페이지 -->
      <div id="0" class="section first-main">
        <img src="https://i.pinimg.com/originals/3d/31/6b/3d316ba5a416f68c8627de6be9f97b68.gif" alt="">
        <div class="first-main-logo">
          <span class="main-logo spread-underline font-jeonnam" @click="goMain()" @mouseover="bigLetterChange()" @mouseleave="bigLetterToWhite()">
            <strong :class="bigLetter">B</strong>
            <span class="main-small-font">ook </span> 
            <strong :class="bigLetter">F</strong>
            <span class="main-small-font">lex</span>
          </span>
        </div>
        <transition>
          <div class="scroll-link" v-show="nowTop">
            <svg width="40px" class="mouse" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 76 130" preserveAspectRatio="xMidYMid meet">
              <g fill="none" fill-rule="evenodd">
                <rect width="70" height="118" x="1.5" y="1.5" stroke="#FFF" stroke-width="3" rx="36"/>
                <circle class="scroll" cx="36.5" cy="31.5" r="4.5" fill="#FFF"/>
              </g>
            </svg>
            <div style="color: white;" class="font-jeonnam">
              scroll down
            </div>
          </div>
        </transition>
      </div>

      <div>
        <div style="height: 300px; width: 100vw; border: 1px solid yellow;">
          <div id="book-number">
            <div data-aos="fade-right" class="text-center">
              <span class="white info-subtitle-size">도서</span>
              <span class="yellow info-title-size" style="margin: 0 15px;">88,416</span>
              <span class="white info-subtitle-size">권</span>
            </div>
          </div>
          <div id="review-number">
            <div data-aos="fade-left" class="white info-subtitle-size">
              <span class="white info-subtitle-size">리뷰</span>
              <span class="salmon info-title-size" style="margin: 0 15px;">632,017</span>
              <span class="white info-subtitle-size">개</span>
            </div>
          </div>
        </div>
      </div>
      
      <!-- 스크롤 페이지 -->
      <div id="scrollPage" 
        style="
          background: linear-gradient(to bottom, black, rgba(235, 234, 232, 1));
          height: 400vh;
          position: relative;
          top: 300px;
        "
      >
        <!-- <div style="height: 100vh; width: 100vw; border: 1px solid yellow;">
          <div id="book-number">
            <div data-aos="fade-right" class="text-center">
              <span class="white info-subtitle-size">도서</span>
              <span class="yellow info-title-size" style="margin: 0 15px;">88,416</span>
              <span class="white info-subtitle-size">권</span>
            </div>
          </div>
          <div id="review-number">
            <div data-aos="fade-left" class="white info-subtitle-size">
              <span class="white info-subtitle-size">리뷰</span>
              <span class="salmon info-title-size" style="margin: 0 15px;">632,017</span>
              <span class="white info-subtitle-size">개</span>
            </div>
          </div>
        </div> -->

        <div style="height: 100vh; border: 1px solid pink;">

        </div>
        <div style="height: 100vh;">

        </div>
        <div style="height: 100vh;">

        </div>

      </div>
      <div id="endScroll" 
        style="
          height: 100vh;
          border: 5px orange solid;
        "
      >
      <b-button @click="goMain()">여기</b-button>
      </div>
    </div>
  </div>
</template>

<script>
document.querySelector('body').style.backgroundImage = "url('@/assets/librarycolumn3.jpg')"
document.querySelector('body').style.backgroundColor = "blue"

import AOS from 'aos';
import "aos/dist/aos.css";

  export default {
    created() {
      AOS.init()
      window.addEventListener('scroll', this.handleScroll)
    },
    data() {
      return{
        bigLetter: "",
        nowTop: true,
      }
    },
    destroyed() {
      window.removeEventListener('scroll', this.handleScroll)
    },
    methods: {
      bigLetterChange() {
        this.bigLetter = "big-letter"     
      },
      bigLetterToWhite() {
        this.bigLetter = ""
      },
      goMain() {
        this.$router.push({ path: 'main' })
      },
      handleScroll(event) {
        // let scrollPageStart = document.getElementById('scrollPage').offsetTop;
        console.log(event, 'event')
        let _documentY = window.pageYOffset
        // let _documentY = event.srcElemnt.scrollingElement.scrollTop;
        if (_documentY > window.innerHeight/8) {
          this.nowTop = false
        } else {
          this.nowTop = true
        }
      }
    }
  }
</script>

<style>
@import url('https://fonts.googleapis.com/css2?family=Arvo:wght@700&family=Open+Sans:wght@800&family=Roboto:wght@900&display=swap');
  #main .font-main {
    font-family: 'Open Sans', sans-serif;
  }
  #main .first-main {
    background-color: black;
    text-align: center;
    height: 100vh;
    width: 100vw;
  }
  #main .first-main-logo {
    position: absolute;
    top: 50vh;
    width: 100%;
    z-index: 10;
    color: white;
    text-align: center;
    font-size: 100px;
    font-weight: bold;
    letter-spacing: 5px;
  }
  #main .main-small-font {
    color: rgba(255, 255, 255, 0.8);
  }
  #main .main-logo:hover {
    cursor: pointer;
    /* opacity: 0.9; */
  }
  #main .big-letter {
    color: #C21807;
    /* color: #B80F0A; */
  }
  /* 가운데서 좌우로 펼쳐지는 라인 애니메이션 */
  #main .spread-underline {
    text-decoration: none;
    display: inline-block;
    padding: 15px 0;
    position: relative;
  }
  #main .spread-underline:after {
    background: none repeat scroll 0 0 transparent;
    content: "";
    display: block;
    height: 2px;
    left: 50%;
    position: absolute;
    background: #ffb000;
    transition: width 0.3s ease 0s, left 0.3s ease 0s;
    width: 0;
  }
  #main .spread-underline:hover:after {
    width: 100%;
    left: 0;
  }
  #main .scroll-link {
    margin-top: 25vh;
    margin-left: 50vw;
    /* position: absolute; */
    bottom: 1rem;
    width: 100%;
    /* left: 50%; */
    /* text-align: center; */
    transform: translateX(-50%);
  }
  #main .mouse {
    max-width: 2.5rem;
    width: 100%;
    height: auto;
  }
  #main .scroll {
    animation-name: scroll;
    animation-duration: 1.5s;
    animation-timing-function: cubic-bezier(0.650, -0.550, 0.250, 1.500);
    animation-iteration-count: infinite;
    transform-origin: 50% 20.5px;
    will-change: transform, opacity;
    opacity: 1;
  }
  @keyframes scroll {
    0%, 20% {
      transform: translateY(0) scaleY(1);
    }
    100% {
      transform: translateY(36px) scaleY(2);
      opacity: 0;
    }
  }

  /* 1초 동안 투명도를 변화 */
  .v-enter-active, .v-leave-active {
    transition: opacity 0.5s;
  }
  /* 더 이상 보이게 되지 않았을 때의 투명도 */
  .v-enter, .v-leave-to {
    opacity: 0;
  }

  .white {
    color: white;
  }
  .yellow {
    color: yellow;
  }
  .salmon {
    color: salmon;
  }

  #book-number {
    /* padding: 0px 0 0 140px; */
  }
  #review-number {
    /* padding: 50px 0 0 60vw; */
  }
  .info-title-size {
    font-size: 120px;
    font-weight: bold;
    width: 100%;
    text-align: center;
  }
  .info-subtitle-size {
    font-size: 60px;
    width: 100%;
    text-align: center;
  }
  .text-center {
    text-align: center;
  }
</style>