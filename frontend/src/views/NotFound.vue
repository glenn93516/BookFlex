<template>
  <div id='oopss'>
    <div id='error-text'>
      <img src="https://cdn.rawgit.com/ahmedhosna95/upload/1731955f/sad404.svg" alt="404">
      <span>404 PAGE</span>
      <p class="p-a" style="font-family: 'S-CoreDream-2ExtraLight'">
          .존재하지 않는 페이지 입니다</p>
      <a href='/' class="back">메인 페이지로 이동하기</a>
    </div>
    <footer>
      <p class="footer-content">
        Copyright © 앙상블. All Rights Reserved.
      </p>
    </footer>
  </div>
</template>

<script>
export default {

}
</script>

<style>
#oopss {
  background-color: rgba(237, 234, 232, 1);
  /* background: linear-gradient(-45deg, #fff300, #efe400); */
  position: fixed;
  left: 0px;
  top: 0;
  width: 100%;
  height: 100%;
  line-height: 1.5em;
  z-index: 9999;
}
#oopss #error-text {
  font-size: 40px;
  display: flex;
  flex-direction: column;
  align-items: center;
  font-family: 'NanumBarunpen', sans-serif;
  color: #000;
  direction: rtl;
}
#oopss #error-text img {
  margin: 85px auto 20px;
  height: 342px;
}
#oopss #error-text span {
  position: relative;
  font-size: 3.3em;
  font-weight: 900;
  margin-bottom: 50px;
}
#oopss #error-text p.p-a {
  font-size: 19px;
  margin: 20px 0 0 0;
}
#oopss #error-text .back {
  background: #fff;
  color: #000;
  font-size: 30px;
  text-decoration: none;
  margin: 1em auto 0;
  padding: .7em 2em;
  border-radius: 500px;
  box-shadow: 0 20px 70px 4px rgba(0, 0, 0, 0.1), inset 7px 33px 0 0px rgba(61, 16, 5, 0.3);
  font-weight: 900;
  transition: all 300ms ease;
}
#oopss #error-text .back:hover {
  -webkit-transform: translateY(-13px);
          transform: translateY(-13px);
  box-shadow: 0 35px 90px 4px rgba(0, 0, 0, 0.3), inset 0px 0 0 3px #000;
}
.footer-content {
  font-family: 'NanumBarunpen', sans-serif;
  font-size: 10px;
  text-align: center;
  margin-top: 30px;
  margin-bottom: 30px;
}
</style>