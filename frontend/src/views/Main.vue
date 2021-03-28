<template>
  <div>
    <div 
      style="
        background-color: black; 
        text-align:center;
        height: 100vh;
      "
    >
      <img src="https://i.pinimg.com/originals/3d/31/6b/3d316ba5a416f68c8627de6be9f97b68.gif" alt="">
      <div style="
      position: absolute;
      top: 50vh;
      width: 100%;
      z-index: 10;
      color: white;
      text-align: center;
      font-size: 80px;
      font-weight: bold;
      letter-spacing: 5px;
      ">
        <span class="main-logo spread-underline" @click="goMain()" @mouseover="bigLetterChange()" @mouseleave="bigLetterToWhite()">
          <strong :class="bigLetter">B</strong>
          <span style="color: rgba(255, 255, 255, 0.7)">ook </span> 
          <strong :class="bigLetter">F</strong>
          <span style="color: rgba(255, 255, 255, 0.7)">lex</span>
        </span>
      </div>
      <div
       style="
        position: fixed;
        right: 5vw; 
        top: 40vh;
      ">
        <!-- 꽉찬거 -->
        <img @click="goPage()" width="20px" :src="activeCircle" />
        <br>
        <!-- 빈 거 -->
        <img @click="goPage(1)" width="20px" :src="inactiveCircle" />
        <br>
        <!-- 빈 거 -->
        <img @click="goPage(2)" width="20px" :src="inactiveCircle" />
        <br>
        <!-- 빈 거 -->
        <img @click="goPage(3)" width="20px" :src="inactiveCircle" />
      </div>  
      <a class="scroll-link" href="#1" v-smooth-scroll>
        <svg width="40px" class="mouse" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 76 130" preserveAspectRatio="xMidYMid meet">
          <g fill="none" fill-rule="evenodd">
            <rect width="70" height="118" x="1.5" y="1.5" stroke="#FFF" stroke-width="3" rx="36"/>
            <circle class="scroll" cx="36.5" cy="31.5" r="4.5" fill="#FFF"/>
          </g>
        </svg>
      </a>
    </div>
    
    <div id="1" style="background-color: red; height: 100vh;">
      <p style="position: relative; top: 50vh; text-align: center;">
        여기는 
      </p>
    </div>
    <div id="2" style="background-color: orange; height: 100vh;">
      <p style="position: relative; top: 50vh; text-align: center;">
        여기는 
      </p>
    </div>
    <div id="3" style="background-color: yellow; height: 100vh;">
      <p style="position: relative; top: 50vh; text-align: center;">
        여기는 
      </p>
    </div>
  </div>
</template>

<script>
  export default {
    data() {
      return{
        prev_y: "",
        bigLetter: "",
        nowId: "",
        activeCircle: "data:image/svg+xml;base64,PHN2ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHZlcnNpb249IjEuMSIgeG1sbnM6eGxpbms9Imh0dHA6Ly93d3cudzMub3JnLzE5OTkveGxpbmsiIHhtbG5zOnN2Z2pzPSJodHRwOi8vc3ZnanMuY29tL3N2Z2pzIiB3aWR0aD0iNTEyIiBoZWlnaHQ9IjUxMiIgeD0iMCIgeT0iMCIgdmlld0JveD0iMCAwIDUxMiA1MTIiIHN0eWxlPSJlbmFibGUtYmFja2dyb3VuZDpuZXcgMCAwIDUxMiA1MTIiIHhtbDpzcGFjZT0icHJlc2VydmUiIGNsYXNzPSIiPjxnPgo8ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciPgoJPGc+CgkJPHBhdGggZD0iTTI1NiwwQzExNS4zOSwwLDAsMTE1LjM5LDAsMjU2czExNS4zOSwyNTYsMjU2LDI1NnMyNTYtMTE1LjM5LDI1Ni0yNTZTMzk2LjYxLDAsMjU2LDB6IiBmaWxsPSIjZmZmZmZmIiBkYXRhLW9yaWdpbmFsPSIjMDAwMDAwIiBzdHlsZT0iIiBjbGFzcz0iIj48L3BhdGg+Cgk8L2c+CjwvZz4KPGcgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIj4KPC9nPgo8ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciPgo8L2c+CjxnIHhtbG5zPSJodHRwOi8vd3d3LnczLm9yZy8yMDAwL3N2ZyI+CjwvZz4KPGcgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIj4KPC9nPgo8ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciPgo8L2c+CjxnIHhtbG5zPSJodHRwOi8vd3d3LnczLm9yZy8yMDAwL3N2ZyI+CjwvZz4KPGcgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIj4KPC9nPgo8ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciPgo8L2c+CjxnIHhtbG5zPSJodHRwOi8vd3d3LnczLm9yZy8yMDAwL3N2ZyI+CjwvZz4KPGcgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIj4KPC9nPgo8ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciPgo8L2c+CjxnIHhtbG5zPSJodHRwOi8vd3d3LnczLm9yZy8yMDAwL3N2ZyI+CjwvZz4KPGcgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIj4KPC9nPgo8ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciPgo8L2c+CjxnIHhtbG5zPSJodHRwOi8vd3d3LnczLm9yZy8yMDAwL3N2ZyI+CjwvZz4KPC9nPjwvc3ZnPg==",
        inactiveCircle: "data:image/svg+xml;base64,PHN2ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHZlcnNpb249IjEuMSIgeG1sbnM6eGxpbms9Imh0dHA6Ly93d3cudzMub3JnLzE5OTkveGxpbmsiIHhtbG5zOnN2Z2pzPSJodHRwOi8vc3ZnanMuY29tL3N2Z2pzIiB3aWR0aD0iNTEyIiBoZWlnaHQ9IjUxMiIgeD0iMCIgeT0iMCIgdmlld0JveD0iMCAwIDM0Ljc2IDM0Ljc2IiBzdHlsZT0iZW5hYmxlLWJhY2tncm91bmQ6bmV3IDAgMCA1MTIgNTEyIiB4bWw6c3BhY2U9InByZXNlcnZlIiBjbGFzcz0iIj48Zz4KPGcgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIj4KCTxnPgoJCTxwYXRoIGQ9Ik0xNy4zOCwzNC43NmMtOS41ODMsMC0xNy4zNzktNy43OTYtMTcuMzc5LTE3LjM4UzcuNzk3LDAsMTcuMzgsMHMxNy4zNzksNy43OTYsMTcuMzc5LDE3LjM4UzI2Ljk2MywzNC43NiwxNy4zOCwzNC43NnogICAgIE0xNy4zOCw2LjQ5NWMtNi4wMDIsMC0xMC44ODUsNC44ODItMTAuODg1LDEwLjg4NXM0Ljg4MywxMC44ODUsMTAuODg1LDEwLjg4NXMxMC44ODUtNC44ODIsMTAuODg1LTEwLjg4NSAgICBTMjMuMzgyLDYuNDk1LDE3LjM4LDYuNDk1eiIgZmlsbD0iI2ZmZmZmZiIgZGF0YS1vcmlnaW5hbD0iIzAwMDAwMCIgc3R5bGU9IiIgY2xhc3M9IiI+PC9wYXRoPgoJPC9nPgo8L2c+CjxnIHhtbG5zPSJodHRwOi8vd3d3LnczLm9yZy8yMDAwL3N2ZyI+CjwvZz4KPGcgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIj4KPC9nPgo8ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciPgo8L2c+CjxnIHhtbG5zPSJodHRwOi8vd3d3LnczLm9yZy8yMDAwL3N2ZyI+CjwvZz4KPGcgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIj4KPC9nPgo8ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciPgo8L2c+CjxnIHhtbG5zPSJodHRwOi8vd3d3LnczLm9yZy8yMDAwL3N2ZyI+CjwvZz4KPGcgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIj4KPC9nPgo8ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciPgo8L2c+CjxnIHhtbG5zPSJodHRwOi8vd3d3LnczLm9yZy8yMDAwL3N2ZyI+CjwvZz4KPGcgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIj4KPC9nPgo8ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciPgo8L2c+CjxnIHhtbG5zPSJodHRwOi8vd3d3LnczLm9yZy8yMDAwL3N2ZyI+CjwvZz4KPGcgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIj4KPC9nPgo8ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciPgo8L2c+CjwvZz48L3N2Zz4="
      }
    },
    created() {
      window.addEventListener('scroll', this.handleScroll)
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
      goPage(num) {
        if (num === undefined) {
          window.scrollTo(0, 0)
        } else {
          document.getElementById(`${num}`).scrollIntoView();
        }
      },
      // handleScroll(event) {
      //   console.log(event)
      //   let _documentY = event.srcElement.scrollingElement.scrollTop;
      //   const first = document.getElementById('1').offsetTop;
      //   const second = document.getElementById('2').offsetTop;
      //   const third = document.getElementById('3').offsetTop;

      //   if (_documentY < first) {
      //     window.scrollTo(0, 0)
      //   } else {
      //     if (_documentY >= first && _documentY < second) {
      //       window.scrollTo(0, first)
      //     } else {
      //       if (_documentY >= second && _documentY < third) {
      //         window.scrollTo(0, second)
      //       } else {
      //         if (_documentY >= third) {
      //           window.scrollTo(0, third)
      //         }
      //       }
      //     }
      //   }
      //   // const first = 0;
      //   // let second = document.getElementById('1').scrollIntoView();
      //   // console.log(second)
        
      //   // window.scrollTo(second)
      // },
      // handleScroll(event) {
      //   let _documentY = event.srcElement.scrollingElement.scrollTop;
      //   if (_documentY > this.prev_y) {
      //     console.log(_documentY)
      //     // 스크롤 한 번 내릴 때 100만큼 이동
      //     console.log(this.prev_y)
      //     // console.log(event.srcElement.location.hash)
      //     // 처음에는 hash값 존재 안함
      //     console.log('아래로')
      //     this.animteScrollTo('#1')


      //     // if (event.srcElement.location.hash === "") {
      //     //   // document.location = "#1"
      //     //   document.getElementById('1').scrollIntoView()
      //     //   location.hash = "#1"
      //     //   // this.nowId = 1
      //     // } else {
      //     //   let nowId = parseInt(event.srcElement.location.hash[1]) + 1
      //     //   if (nowId !== "" && nowId < 4) {
      //     //     console.log(nowId)
      //     //     document.getElementById(nowId).scrollIntoView()
      //     //     location.hash = "#" + nowId
      //     //   }
      //     // }


      //     // if (event.srcElement.location.hash) {
      //     //   location.hash = "#" + (event.srcElement.location.hash + 1)
      //     // } else {
      //     //   location.hash = "#" + 1
      //     // }
      //   } else {
      //     console.log('위로')
      //   }
      //   this.prev_y = _documentY
      //   // let _direction = _documentY - window.document.scrollingElement.scrollTop >= 0 ? 1 : -1;
      //   // let _direction = _documentY - window.__scrollPosition >= 0 ? 1 : -1;
      //   // console.log(_direction)
      //   // if (_direction === 1) {
      //   //   console.log('위로')
      //   // } else {
      //   //   console.log('아래로')
      //   // }
      //   // console.log('찍히나')
      //   // console.log(Event)
      // },
      animteScrollTo(_selector, _duration, _adjust) {
        const targetEle = document.querySelector(_selector);
        if (!targetEle) return;

        // - Get current &amp; target positions
        const scrollEle = document.documentElement || window.scrollingElement,
        currentY = scrollEle.scrollTop,
        targetY = targetEle.offsetTop - (_adjust || 0);
        animateScrollTo(currentY, targetY, _duration);

        // - Animate and scroll to target position
        function animateScrollTo(_startY, _endY, _duration) {
          _duration = _duration ? _duration : 600;
          const unitY = (targetY - currentY) / _duration;
          const startTime = new Date().getTime();
          const endTime = new Date().getTime() + _duration;

          const scrollTo = function() {
            let now = new Date().getTime();
            let passed = now - startTime;
            if (now <= endTime) {
              scrollEle.scrollTop = currentY + (unitY * passed);
              requestAnimationFrame(scrollTo);
            }
            else {
              console.log('End off.')
            }
          };
          requestAnimationFrame(scrollTo);
        }
      }
    }
  }
</script>

<style>
  .main-logo:hover {
    cursor: pointer;
    opacity: 0.7;
    /* 밑줄 긋는 효과 추가 */
  }
  .big-letter {
    color: red;
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
    /* bottom: 0; */
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
    bottom: 3rem;
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
</style>