<template>
  <div>
    <div 
      style="
        background-color: black; 
        text-align:center;
        width: 100vw;
        height: 100vh;
      "
    >
      <img src="https://i.pinimg.com/originals/3d/31/6b/3d316ba5a416f68c8627de6be9f97b68.gif" alt="">
    </div>
    <div style="
      position: absolute;
      top: 50vh;
      height: 100vh;
      width: 100%;
      z-index: 10;
      color: white;
      text-align: center;
      font-size: 80px;
      font-weight: bold;
      letter-spacing: 5px;
    ">
      <strong>B</strong><span style="color: rgba(255, 255, 255, 0.7)">ook</span> <strong>F</strong><span style="color: rgba(255, 255, 255, 0.7)">lex</span>
      <br>
      <div style="margin-top: 100px;">
        <a class="scroll-link" href="#here" v-smooth-scroll>
          <svg width="40px" class="mouse" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 76 130" preserveAspectRatio="xMidYMid meet">
            <g fill="none" fill-rule="evenodd">
              <rect width="70" height="118" x="1.5" y="1.5" stroke="#FFF" stroke-width="3" rx="36"/>
              <circle class="scroll" cx="36.5" cy="31.5" r="4.5" fill="#FFF"/>
            </g>
          </svg>
        </a>
      </div>
    </div>
    <div id="here" style="background-color: red; height: 100vh;">
      <p style="position: relative; top: 50vh; text-align: center;">
        여기는 
      </p>
    </div>
  </div>
</template>

<script>
</script>

<style>
  .scroll-link {
    position: absolute;
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