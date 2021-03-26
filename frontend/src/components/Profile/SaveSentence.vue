<template>
  <div class="temp">
    <div class="sentence-item" v-for="item in showItems" :key="item.index">
      <img class="sentence-img" width="340px" height="250px" :src="item.src" alt="">
      <div class="dimmed">
        <!-- text최소 길이, 최대 길이 정해주기 -->
        <div class="sentence-text">
          <div>{{item.text}}</div>
          <div style="margin-top: 10px; font-weight: bold;">"{{item.book}}"</div>
        </div>
        <!-- <div class="sentence-book">{{item.book}}</div> -->
      </div>
    </div>
    <div class="more">
      <img 
        @click="more()"
        v-show="moreBtn"
        class="more-icon" 
        width="30px;" 
        src="data:image/svg+xml;base64,PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iaXNvLTg4NTktMSI/Pg0KPCEtLSBHZW5lcmF0b3I6IEFkb2JlIElsbHVzdHJhdG9yIDE5LjAuMCwgU1ZHIEV4cG9ydCBQbHVnLUluIC4gU1ZHIFZlcnNpb246IDYuMDAgQnVpbGQgMCkgIC0tPg0KPHN2ZyB2ZXJzaW9uPSIxLjEiIGlkPSJMYXllcl8xIiB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHhtbG5zOnhsaW5rPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5L3hsaW5rIiB4PSIwcHgiIHk9IjBweCINCgkgdmlld0JveD0iMCAwIDUxMiA1MTIiIHN0eWxlPSJlbmFibGUtYmFja2dyb3VuZDpuZXcgMCAwIDUxMiA1MTI7IiB4bWw6c3BhY2U9InByZXNlcnZlIj4NCjxnPg0KCTxnPg0KCQk8cGF0aCBkPSJNMjU2LDBDMTE0LjUxLDAsMCwxMTQuNDk3LDAsMjU2YzAsMTQxLjQ5LDExNC40OTcsMjU2LDI1NiwyNTZjMTQxLjQ5LDAsMjU2LTExNC40OTcsMjU2LTI1NkM1MTIsMTE0LjUxLDM5Ny41MDMsMCwyNTYsMHoNCgkJCSBNMjU2LDQ3Ny44NjdjLTEyMi4zMzcsMC0yMjEuODY3LTk5LjUyOS0yMjEuODY3LTIyMS44NjdTMTMzLjY2MywzNC4xMzMsMjU2LDM0LjEzM1M0NzcuODY3LDEzMy42NjMsNDc3Ljg2NywyNTYNCgkJCVMzNzguMzM3LDQ3Ny44NjcsMjU2LDQ3Ny44Njd6Ii8+DQoJPC9nPg0KPC9nPg0KPGc+DQoJPGc+DQoJCTxwYXRoIGQ9Ik0zNzEuMzQ1LDIzOC45MzNoLTk4LjI3OHYtOTguMjc4YzAtOS40MjUtNy42NDEtMTcuMDY3LTE3LjA2Ny0xNy4wNjdzLTE3LjA2Nyw3LjY0MS0xNy4wNjcsMTcuMDY3djk4LjI3OGgtOTguMjc4DQoJCQljLTkuNDI1LDAtMTcuMDY3LDcuNjQxLTE3LjA2NywxNy4wNjdzNy42NDEsMTcuMDY3LDE3LjA2NywxNy4wNjdoOTguMjc4djk4LjI3OGMwLDkuNDI1LDcuNjQxLDE3LjA2NywxNy4wNjcsMTcuMDY3DQoJCQlzMTcuMDY3LTcuNjQxLDE3LjA2Ny0xNy4wNjd2LTk4LjI3OGg5OC4yNzhjOS40MjUsMCwxNy4wNjctNy42NDEsMTcuMDY3LTE3LjA2N1MzODAuNzcsMjM4LjkzMywzNzEuMzQ1LDIzOC45MzN6Ii8+DQoJPC9nPg0KPC9nPg0KPGc+DQo8L2c+DQo8Zz4NCjwvZz4NCjxnPg0KPC9nPg0KPGc+DQo8L2c+DQo8Zz4NCjwvZz4NCjxnPg0KPC9nPg0KPGc+DQo8L2c+DQo8Zz4NCjwvZz4NCjxnPg0KPC9nPg0KPGc+DQo8L2c+DQo8Zz4NCjwvZz4NCjxnPg0KPC9nPg0KPGc+DQo8L2c+DQo8Zz4NCjwvZz4NCjxnPg0KPC9nPg0KPC9zdmc+DQo=" 
      />
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      // 처음에 3개만 받아오기
      showItems: [
        {
          src: 'https://cdn.pixabay.com/photo/2021/03/16/15/30/beach-6100209_960_720.jpg',
          text: '내가 종일 열심히 일하는 것은 책 읽을 시간을 내기 위해서다1.',
          book: '책 이름1',
        },
        {
          src: 'https://cdn.pixabay.com/photo/2021/03/03/07/09/background-6064645_960_720.jpg',
          text: '책 이름2',
          book: '책 이름2',
        },
        {
          src: 'https://cdn.pixabay.com/photo/2021/01/30/18/07/salzburg-5964812_960_720.jpg',
          text: '내가 종일 열심히 일하는 것은 책 읽을 시간을 내기 위해서다3.',
          book: '책 이름3',
        },
      ],
      // 다 받아온거
      defaultItems: [
        {
          src: 'https://cdn.pixabay.com/photo/2021/03/16/15/30/beach-6100209_960_720.jpg',
          text: '내가 종일 열심히 일하는 것은 책 읽을 시간을 내기 위해서다1.',
          book: '책 이름1',
        },
        {
          src: 'https://cdn.pixabay.com/photo/2021/03/03/07/09/background-6064645_960_720.jpg',
          text: '책 이름2',
          book: '책 이름2',
        },
        {
          src: 'https://cdn.pixabay.com/photo/2021/01/30/18/07/salzburg-5964812_960_720.jpg',
          text: '내가 종일 열심히 일하는 것은 책 읽을 시간을 내기 위해서다3.',
          book: '책 이름3',
        },
        {
          src: 'https://cdn.pixabay.com/photo/2021/03/16/15/30/beach-6100209_960_720.jpg',
          text: '내가 종일 열심히 일하는 것은 책 읽을 시간을 내기 위해서다1.',
          book: '책 이름1',
        },
        {
          src: 'https://cdn.pixabay.com/photo/2021/03/03/07/09/background-6064645_960_720.jpg',
          text: '책 이름2',
          book: '책 이름2',
        },
        {
          src: 'https://cdn.pixabay.com/photo/2021/01/30/18/07/salzburg-5964812_960_720.jpg',
          text: '내가 종일 열심히 일하는 것은 책 읽을 시간을 내기 위해서다3.',
          book: '책 이름3',
        },
        {
          src: 'https://cdn.pixabay.com/photo/2021/03/16/15/30/beach-6100209_960_720.jpg',
          text: '내가 종일 열심히 일하는 것은 책 읽을 시간을 내기 위해서다1.',
          book: '책 이름1',
        },
        {
          src: 'https://cdn.pixabay.com/photo/2021/03/03/07/09/background-6064645_960_720.jpg',
          text: '내가 종일 열심히 일하는 것은 책 읽을 시간을 내기 위해서다2.',
          book: '책 이름2',
        },
        {
          src: 'https://cdn.pixabay.com/photo/2021/01/30/18/07/salzburg-5964812_960_720.jpg',
          text: '내가 종일 열심히 일하는 것은 책 읽을 시간을 내기 위해서다3.',
          book: '책 이름3',
        },
        {
          src: 'https://cdn.pixabay.com/photo/2021/03/16/15/30/beach-6100209_960_720.jpg',
          text: '내가 종일 열심히 일하는 것은 책 읽을 시간을 내기 위해서다1.',
          book: '책 이름1',
        },
        {
          src: 'https://cdn.pixabay.com/photo/2021/03/03/07/09/background-6064645_960_720.jpg',
          text: '책 이름2',
          book: '책 이름2',
        },
        {
          src: 'https://cdn.pixabay.com/photo/2021/01/30/18/07/salzburg-5964812_960_720.jpg',
          text: '내가 종일 열심히 일하는 것은 책 읽을 시간을 내기 위해서다3.',
          book: '책 이름3',
        },
        {
          src: 'https://cdn.pixabay.com/photo/2021/03/16/15/30/beach-6100209_960_720.jpg',
          text: '내가 종일 열심히 일하는 것은 책 읽을 시간을 내기 위해서다1.',
          book: '책 이름1',
        },
        {
          src: 'https://cdn.pixabay.com/photo/2021/03/03/07/09/background-6064645_960_720.jpg',
          text: '내가 종일 열심히 일하는 것은 책 읽을 시간을 내기 위해서다2.',
          book: '책 이름2',
        },
        {
          src: 'https://cdn.pixabay.com/photo/2021/01/30/18/07/salzburg-5964812_960_720.jpg',
          text: '내가 종일 열심히 일하는 것은 책 읽을 시간을 내기 위해서다3.',
          book: '책 이름3',
        },
      ],
      moreBtn: true,
    }
  },
  methods: {
    more() {
      this.showItems = this.defaultItems
      this.moreBtn = false
    },
  },
}
</script>

<style>
  .temp {
    display: flex;
  }
  .sentence-item {
    display: inline-block;
    position: relative;
    margin-left: 45px;
    margin-bottom: 30px;
  }
  .sentence-img {
    border-radius: 10px;
  }
  .temp div:nth-child(3n+1) {
    margin-left: 0;
  }
  .dimmed {
    border-radius: 10px;
    position: absolute;
    width: 340px;
    height: 100%;
    top: 0;
    bottom: 0;
    background-color: rgba(255, 255, 255, 0.5);
    text-align: center;
  }
  .sentence-item:hover .dimmed {
    border-radius: 10px;
    background-color: rgba(0, 0, 0, 0.5);
    color: white;
    cursor: pointer;
  }
  .sentence-text {
    /* background-color: rgba(255, 0, 0, 0.5); */
    margin-top: 80px;
    padding-left: 60px;
    width: 280px;
    height: 150px;
    /* display: flex; */
    align-content: center;
    justify-items: center;
    text-align: center;
  }
  /* .sentence-book {
    position: absolute; 
    width: 300px;
    padding-left: 45px;
  } */
  /* .sentence-item {
    display: absolute;
    z-index: 1;
    color: red;
  } */
  /* .sentence-item:hover {
    cursor: pointer;
    background-color: black;
  }
  .more {
    display: flex;
    justify-content: center;
    align-items: center;
  }
  .more-line {
    display: absolute;
    width: 300px;
    left: 100px;
  }
  .more-word {
    display: absolute;
    left: 10px;
  } */
</style>