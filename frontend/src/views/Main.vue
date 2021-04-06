<template>
  <div id="main" :style="mainStyle">

    <div class="main-first-page">
      <!-- 첫번째 페이지 -->
      <div id="0" class="first-main" style="background-color: black;">
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
      <div class="book-info-black"></div>
      <div id="bookInfo">
        <div class="book-info-background">
          <div id="book-number" style="width: 100vw;">
            <div data-aos="fade-right" class="text-center">
              <span class="white info-subtitle-size">도서</span>
              <span class="yellow info-title-size info-number">88,416</span>
              <span class="white info-subtitle-size">권</span>
            </div>
          </div>
          <div id="review-number" style="width: 100vw;">
            <div data-aos="fade-left" class="white info-subtitle-size">
              <span class="white info-subtitle-size">리뷰</span>
              <span class="salmon info-title-size info-number">632,017</span>
              <span class="white info-subtitle-size">개</span>
            </div>
          </div>
        </div>
      </div>
      <div class="book-info-black"></div>

      
      <!-- 스크롤 페이지 -->
      <div id="scrollPage">
        <div class="article">
          <div id="firstSection" class="section first-section">
            <div data-aos="fade-down" class="white section-title">추천 시스템</div>
            <Book class="mouse-pointer recommend-book" data-aos="zoom-in-down" id="book" :book="nowBookObj"></Book>
            <div class="white text-center">
              <div data-aos="fade-right">
                <span class="highlight-size-xs">Fasttext</span>
                <span>를 기반으로 </span> 
                <span class="highlight-size-xxxs">앙상블팀</span>
                <span>이</span>
              </div>
              <div data-aos="fade-left">
                <span>직접 </span>
                <span class="highlight-size-xxxs">한국어</span>
                <span>로 </span>
                <span class="highlight-size-xxxs">사전 학습</span>
                <span>시킨 </span>
                <span>모델 활용</span>
              </div>
            </div>
          </div>
          <div id="secondSection" class="section middle-section">
            <div data-aos="fade-down" class="white section-title">지도 학습</div>
            <div class="white text-center bert-info">
              <span data-aos="zoom-in" class="highlight-size-lg">BERT</span>
              <span data-aos="zoom-in" class="default-size">모델</span>
            </div>
            <div data-aos="fade-up" class="white text-center default-size">감정분석</div>
            <div data-aos="zoom-out-up" class="white text-center highlight-size-lg">90.09%</div>
            <div data-aos="fade-up" class="white text-center default-size">정확도</div>
            <br class="section-br">
            <div>
              <p data-aos="flip-left" class="white text-center info-size">인간의 직관을 넘어선 정밀한 성능의 감정분석 알고리즘</p>
              <p data-aos="zoom-in" class="white text-center small-size">(인간의 직관 88% 기준)</p>
            </div>
          </div>
          <div id="thirdSection" class="section">
            <div data-aos="fade-down" class="white section-title">비지도 학습</div>
            <div class="white bert-info">
              <div data-aos="fade-right">
                <span style="font-size: 50px; font-weight: bold;">LDA</span>를 사용한 <span style="font-size: 27px; font-weight: bold;">리뷰 토픽 모델링</span>
              </div>
              <div data-aos="fade-left" style="font-size: 13px;">
                <span style="font-size: 25px; font-weight: bold;">Coherence</span>와 
                <span style="font-size: 25px; font-weight: bold;">Perplexity</span> 테스트를 통해
                <div>
                  추출한 <span style="font-size: 23px; font-weight: bold;">최적</span>의 토픽 수, 
                  <span style="font-size: 25px; font-weight: bold;">17</span>개
                </div> 
              </div>
              <div data-aos="fade-right" style="font-size: 13px;">
                 <span style="font-size: 25px; font-weight: bold;">Passes</span> 테스트를 통해 얻은 <span style="font-size: 23px; font-weight: bold;">최적</span>의 훈련 수, <span style="font-size: 25px; font-weight: bold;">8</span>회
              </div>
              <div data-aos="fade-right">
                한 눈에 볼 수 있는 독자들의 리뷰 분석
              </div>
              <br>
              <div data-aos="fade-left" style="text-align: center; position: relative;">
                <img width="100%" height="220px" src="@/assets/bizido.png" alt="" class="bizido-img">
                <div class="bizido-img-dimmed"></div>
              </div>
            </div>
          </div>
        </div>
        <div style="height: 20vh;">
        </div>
        <div style="height: 110vh;">
          <div style="height: 50vh; padding: 3vh 2vw; display: flex; justify-content: space-around; align-items: center;">
            <div>
              <h1 data-aos="fade-right" style="font-weight: bold;">문장 수집</h1>
              <hr style="margin: 10px 0;">
              <p data-aos="fade-left" style="width: 400px;">
                나만의 문장 수집 기능을 통한 어쩌구 저쩌구 
                나만의 문장 수집 기능을 통한 어쩌구 저쩌구 
                나만의 문장 수집 기능을 통한 어쩌구 저쩌구 
                나만의 문장 수집 기능을 통한 어쩌구 저쩌구 
                나만의 문장 수집 기능을 통한 어쩌구 저쩌구 
                나만의 문장 수집 기능을 통한 어쩌구 저쩌구 
              </p>
            </div>
            <img data-aos="fade-down" data-aos-duration="1000" style="border-radius: 10px; box-shadow: 5px 5px 10px -5px grey;" width="575px" height="90%" src="@/assets/highlightpage.png" alt="">
          </div>
          <div style="height: 50vh; margin-top: 5vh; padding: 3vh 2vw; display: flex; justify-content: space-around; align-items: center;">
            <div>
              <h1 data-aos="fade-right" style="font-weight: bold;">나만의 서재</h1>
              <hr style="margin: 10px 0;">
              <p data-aos="fade-left" style="width: 400px;">
                나만의 문장 수집 기능을 통한 어쩌구 저쩌구 
                나만의 문장 수집 기능을 통한 어쩌구 저쩌구 
                나만의 문장 수집 기능을 통한 어쩌구 저쩌구 
                나만의 문장 수집 기능을 통한 어쩌구 저쩌구 
                나만의 문장 수집 기능을 통한 어쩌구 저쩌구 
                나만의 문장 수집 기능을 통한 어쩌구 저쩌구 
              </p>
            </div>
            <img data-aos="fade-down" style="border-radius: 10px; box-shadow: 5px 5px 10px -5px grey;" width="575px" height="90%" src="@/assets/highlightpage.png" alt="">
          </div>
        </div>
        <div style="height: 110vh;">
          <div style="height: 50vh; margin-top: 5vh; padding: 3vh 2vw; display: flex; justify-content: space-around; align-items: center;">
            <div>
              <h1 data-aos="fade-right" style="font-weight: bold;">맞춤형 추천</h1>
              <hr style="margin: 10px 0;">
              <p data-aos="fade-left" style="width: 400px;">
                나만의 문장 수집 기능을 통한 어쩌구 저쩌구 
                나만의 문장 수집 기능을 통한 어쩌구 저쩌구 
                나만의 문장 수집 기능을 통한 어쩌구 저쩌구 
                나만의 문장 수집 기능을 통한 어쩌구 저쩌구 
                나만의 문장 수집 기능을 통한 어쩌구 저쩌구 
                나만의 문장 수집 기능을 통한 어쩌구 저쩌구 
              </p>
            </div>
            <img data-aos="fade-down" style="border-radius: 10px; box-shadow: 5px 5px 10px -5px grey;" width="575px" height="90%" src="@/assets/highlightpage.png" alt="">
          </div>
          <div style="height: 50vh; margin-top: 5vh; padding: 3vh 2vw; display: flex; justify-content: space-around; align-items: center;">
            <div>
              <h1 data-aos="fade-right" style="font-weight: bold;">커뮤니티</h1>
              <hr style="margin: 10px 0;">
              <p data-aos="fade-left" style="width: 400px;">
                나만의 문장 수집 기능을 통한 어쩌구 저쩌구 
                나만의 문장 수집 기능을 통한 어쩌구 저쩌구 
                나만의 문장 수집 기능을 통한 어쩌구 저쩌구 
                나만의 문장 수집 기능을 통한 어쩌구 저쩌구 
                나만의 문장 수집 기능을 통한 어쩌구 저쩌구 
                나만의 문장 수집 기능을 통한 어쩌구 저쩌구 
              </p>
            </div>
            <img data-aos="fade-down" style="border-radius: 10px; box-shadow: 5px 5px 10px -5px grey;" width="575px" height="90%" src="@/assets/highlightpage.png" alt="">
          </div>
        </div>

      </div>
      <div id="endScroll" 
        style="
          height: 100vh;
          background-color: rgba(235, 234, 232, 1);
        "
      >
      </div>
      <!-- <b-button>여기로</b-button> -->
      <div style="width: 100vw; height: 10vh; justify-content: center; text-align: center; align-items: center;">
        <button class="go-main-btn" @click="goMain()">북플렉스로 이동</button>
      </div>
      <div style="height: 6vh; width: 100vw;background-color: rgba(235, 234, 232, 1);">
        <p class="footer-content">
          Copyright © 앙상블. All Rights Reserved.
        </p>
      </div>
    </div>
  </div>
</template>

<script>
import AOS from 'aos';
import "aos/dist/aos.css";

import Book from '@/components/Main/Book.vue'

  export default {
    components: {
      Book,
    },
    created() {
      AOS.init()
      window.addEventListener('scroll', this.handleScroll)
    },
    mounted() {
      this.randomPickBook()
    },
    data() {
      return{
        bigLetter: "",
        nowTop: true,
        mainStyle: {
          backgroundImage: 'url(https://cdn.pixabay.com/photo/2017/08/03/20/00/booksm-collection-2578237_960_720.jpg)', 
          height: '250vh', 
          width: '100%', 
          border: '1px solid red',
          backgroundAttachment: 'fixed',
        },
        booklists: [
          '머리부터 천천히',
          '우리의 사람들',
          '그럼 무얼 부르지',
          '도시의 시간',
          '인터내셔널의 밤',
          '모든 저녁이 저물 때',
          '저녁의 구애',
          '저녁의 게임',
          '아침의 노래 저녁의 시',
          '상냥한 폭력의 시대',
          '여름의 맛',
          '그믐, 또는 당신이 세계를 기억하는 방식',
          '국경시장',
        ],
        nowBookObj: {},
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
      },
      randomPickBook() {
        const num = Math.floor(Math.random() * (this.booklists.length))
        
        this.$axios.get(`${this.$store.getters.getServer}/book`, {params: {
          search: 'title',
          word: this.booklists[num]
        }})
        .then(res => {
          this.nowBookObj = res.data.data[0]
          // console.log(res.data.data[0], 'res.data.data[0]')
        })
        .catch(err => {
          console.log(err)
        })
      },
    }
  }
</script>

<style>
@import url('https://fonts.googleapis.com/css2?family=Arvo:wght@700&family=Open+Sans:wght@800&family=Roboto:wght@900&display=swap');
  #main .main-first-page {
    position: absolute;
    top: 0;
    left: 0;
    width: 100vw;
  }
  #main .font-main {
    font-family: 'Open Sans', sans-serif;
  }
  #main .first-main {
    position: relative;
    background-color: black;
    text-align: center;
    height: 100vh;
    width: 100vw;
  }
  #main .first-main-logo {
    position: absolute;
    bottom: 30vh;
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
    position: absolute;
    /* margin-top: 10vh; */
    bottom: 5vh;
    margin-left: 50vw;
    bottom: 1rem;
    width: 100%;
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
    color: #fed049;
  }
  .salmon {
    color: #f39189;
  }
  
  #main .book-info-black {
    width: 100vw; 
    height: 20vh; 
    background-color: black;
  }
  #main .book-info-background {
    height: 22vw; 
    width: 100vw; 
    background-color: rgba(0, 0, 0, 0.57);
  }
  #main .info-number {
    margin: 0 15px;
  }
  #main .info-title-size {
    font-size: 110px;
    font-weight: bold;
    width: 100%;
    text-align: center;
  }
  #main .info-subtitle-size {
    font-size: 50px;
    width: 100%;
    text-align: center;
  }
  #main .text-center {
    text-align: center;
  }

  #scrollPage {
    background: linear-gradient(to bottom, black, rgba(235, 234, 232, 1));
    height: 250vh;
    position: relative;
    top: 0;
  }
  #main .article {
    padding-top: 6vh;
    height: 100vh;
    width: 100vw;
    display: flex;
    justify-content: left;
  }
  #main .section {
    width: 30vw;
    background-color: rgba(0, 0, 0, 0.5);
    height: 80vh;
    margin: 5vh 0;
    border-radius: 10px;
    padding: 3vh 3vw;
  }
  #main .first-section {
    margin: 5vh 0 2vw 5vh;
  }
  #main .middle-section {
    margin: 5vh 2vw;
  }
  #main .section-title {
    font-size: 20px;
  }
  #main .highlight-size-lg {
    font-size: 90px;
    font-weight: bold;
  }
  #main .highlight-size-md {
    font-size: 80px;
    font-weight: bold;
  }
  #main .highlight-size-sm {
    font-size: 60px;
    font-weight: bold;
  }
  #main .highlight-size-xs {
    font-size: 50px;
    font-weight: bold;
  }
  #main .highlight-size-xxs {
    font-size: 40px;
    font-weight: bold;
  }
  #main .default-size {
    font-size: 18px;
  }
  #main .info-size {
    font-size: 15px;
  }
  #main .small-size {
    font-size: 12px;
  }
  #main .section-br {
    margin: 10px 0;
  }
  #main .highlight-size-xxxs {
    font-size: 20px;
    font-weight: bold;
  }

  #book .book {
    text-align: center;
    justify-content: center;
    margin: auto;
  }
  #book .book-ul .book-readcheck {
    display: none;
  }
  #book .book-ul .book-wishlist {
    display: none;
  }
  #book .book-ul .book-moreInf {
    margin: 80px 0;
  }
  #main .recommend-book {
    margin: 80px 0;
  }

  #main .bert-info {
    padding-top: 8vh;
  }

  #main .bizido-img {
    border-radius: 4px;
  }
  #main .bizido-img-dimmed {
    border-radius: 4px; 
    position: absolute; 
    background-color: rgba(0, 0, 0, 0.05); 
    width: 100%; 
    height: 220px; 
    top: 0;
  }

  #main .go-main-btn{
    background:#1AAB8A;
    color:#fff;
    border:none;
    position:relative;
    height:60px;
    font-size:1.6em;
    padding:0 2em;
    cursor:pointer;
    transition:800ms ease all;
    outline:none;
  }
  #main .go-main-btn:hover{
    background:rgba(235, 234, 232, 1);
    color:#1AAB8A;
  }
  #main .go-main-btn:before {
    content:'';
    position:absolute;
    top:0;
    right:0;
    height:2px;
    width:0;
    background: #1AAB8A;
    transition:400ms ease all;
  }
  #main .go-main-btn:after {
    content:'';
    position:absolute;
    top:0;
    right:0;
    height:2px;
    width:0;
    background: #1AAB8A;
    transition:400ms ease all;
  }
  #main .go-main-btn:after{
    right:inherit;
    top:inherit;
    left:0;
    bottom:0;
  }
  #main .go-main-btn:hover:before {
    width:100%;
    transition:800ms ease all;
  }
  #main .go-main-btn:hover:after {
    width:100%;
    transition:800ms ease all;
  }

</style>