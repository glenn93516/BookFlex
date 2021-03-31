<template>
  <div>
    <full-page ref="fullpage" id="fullpage">
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
        <!-- <img width="600px;" style="position: absolute; top: 10vh; left: 30vw;" src="https://fontmeme.com/permalink/210331/cec3a003d7c1181272a1055dbb91e6b1.png" alt="neoneon-font" border="0"> -->
        <div class="scroll-link">
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
      </div>
      
      <div id="1" class="section first-section">
        <p>
          여기는 1
        </p>
      </div>
      <div id="2" class="section second-section">
        <p>
          여기는 2
        </p>
      </div>
      <div id="3" class="section third-section">
        <p>
          여기는 3
        </p>
      </div>
    </full-page>
  </div>
</template>

<script>
  export default {
    data() {
      return{
        bigLetter: "",
        // options: {
          // licenseKey: 'YOUR_KEY_HEERE',
          // menu: '#menu',
          // anchors: ['0', '1', '2', '3'],
          // sectionsColor: ['#000000', '#000000', '#41b883', '#ff5f45', '#41b883']
        // },
      }
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
    }
  }
</script>

<style>
@import url('https://fonts.googleapis.com/css2?family=Arvo:wght@700&family=Open+Sans:wght@800&family=Roboto:wght@900&display=swap');
  .font-main {
    /* font-family: 'Arvo', serif; */
    font-family: 'Open Sans', sans-serif;
    /* font-family: 'Roboto', sans-serif; */
  }
  .first-main {
    background-color: black;
    text-align: center;
    height: 100vh;
  }
  .first-main-logo {
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
  .main-small-font {
    color: rgba(255, 255, 255, 0.8);
  }
  .main-logo:hover {
    cursor: pointer;
    /* opacity: 0.9; */
  }
  .big-letter {
    color: #C21807;
    /* color: #B80F0A; */
  }
  /* 가운데서 좌우로 펼쳐지는 라인 애니메이션 */
  .spread-underline {
    text-decoration: none;
    display: inline-block;
    padding: 15px 0;
    position: relative;
  }
  .spread-underline:after {
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
  .spread-underline:hover:after {
    width: 100%;
    left: 0;
  }
  .scroll-link {
    position: absolute;
    bottom: 1rem;
    left: 50%;
    transform: translateX(-50%);
  }
  .mouse {
    max-width: 2.5rem;
    width: 100%;
    height: auto;
  }
  .scroll {
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
  .first-section {
    background-color: grey;
    height: 100vh;
  }
  .second-section {
    background-color: orange;
    height: 100vh;
  }
  .third-section {
    background-color: yellow;
    height: 100vh;
  }
</style>