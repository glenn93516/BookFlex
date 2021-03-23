<template>
  <div>
    <!-- 프로필 헤더(사진, 이름, 한줄, 편집 버튼, 직업) -->
    <div id="profileHeader" class="profile-header">
      <!-- 프로필사진 -->
      <b-avatar 
        src="https://placekitten.com/300/300" 
        size="10rem"
        class="profile-left"
      >
      </b-avatar>
      <!-- 이름 -->
      <div class="profile-right">
        <h1 class="profile-name">
          김싸피
        </h1>
        <p class="profile-singleline">
          {{ profileSingleLine }}
          내가 종일 열심히 일하는 것은 책 읽을 시간을 내기 위해서다. 
          나는 다른 사람의 생각 속에서 사는 것이 정말로 좋다. 종이 위에서 살아나는 사람들과 만나서 느끼는 유대감은 나를 전율케 한다. 
        </p>
      </div>
    </div>
    <!-- 선호장르, 문장수집, 읽은 책, 위시리스트 -->
    <nav></nav>
    <!-- 갈아끼울 컴포넌트 (로그인처럼) -->
  </div>
</template>

<script>
export default {
  props: {},
  data() {
    return {
      profileSingleLine: "내가 종일 열심히 일하는 것은 책 읽을 시간을 내기 위해서다.",
    }
  },
}
</script>

<style>
  @font-face {
    font-family: 'NanumBarunpen';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_two@1.0/NanumBarunpen.woff') format('woff');
    font-weight: normal;
    font-style: normal;
  }
  * {
    font-family: 'NanumBarunpen', sans-serif;
    margin: 0;
    padding: 0;
  }
  /* reset */
  h1, p {
    margin: 0;
    padding: 0;
  }
  .profile-header {
    margin-top: 60px;
    display: flex;
    align-items: center;
    border-bottom: 1px solid black;
  }
  .profile-left {
    display: inline-block;
    margin: 30px 30px 30px 0px;
  }
  .profile-right {
    display: inline-block;
    margin: 30px;
  }
  .profile-name {
    font-size: 50px;
    font-weight: bold;
  }
  .profile-singleline {
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
    width: 750px;
    margin-top: 20px;
  }
</style>