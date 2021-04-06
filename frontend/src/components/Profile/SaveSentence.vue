<template>
  <div class="temp" style="margin-top: 20px;">
    <div 
      class="sentence-item" 
      v-for="item in showItems" 
      :key="item.index" 
    >
      <!-- <img class="sentence-img" width="340px" height="250px" src="@/assets/waterprint_back.jpg" alt="bookImg"> -->
      <img v-if="item.highlightCover" class="sentence-img" width="340px" height="250px" :src="item.highlightCover" alt="bookImg">
      <img v-else class="sentence-img" width="340px" height="250px" src='@/assets/waterprint_back.jpg' alt="bookImg">
      <div @click="sentenceDetail(item)" class="dimmed">
        <!-- text최소 길이, 최대 길이 정해주기 -->
        <div class="sentence-text">
          <div 
            style="
              font-size: 20px; 
              height: 90px; 
              overflow: hidden; 
              text-overflow: ellipsis; 
              display: -webkit-box; 
              -webkit-line-clamp: 3;
              -webkit-box-orient: vertical; 
              word-wrap:break-word; 
            "
          >{{item.highlightContent}}</div>
          <div style="margin-top: 10px; font-weight: bold; font-size: 18px;">📖{{item.bookTitle}}-{{item.highlightPage}}p</div>
        </div>
        <!-- <div class="sentence-book">{{item.book}}</div> -->
      </div>
    </div>
    <b-modal v-model="showDetail" centered hide-footer hide-header hide-backdrop>
    <!-- <b-modal v-model="showDetail" centered hide-footer hide-header> -->
      <sentence-detail :item="nowItem" @close-modal="showDetail=false"></sentence-detail>
    </b-modal>
    <div class="more">
      <img 
        @click="more()"
        v-if="moreBtn"
        class="more-icon hvr-grow" 
        width="30px;" 
        src="data:image/svg+xml;base64,PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iaXNvLTg4NTktMSI/Pg0KPCEtLSBHZW5lcmF0b3I6IEFkb2JlIElsbHVzdHJhdG9yIDE5LjAuMCwgU1ZHIEV4cG9ydCBQbHVnLUluIC4gU1ZHIFZlcnNpb246IDYuMDAgQnVpbGQgMCkgIC0tPg0KPHN2ZyB2ZXJzaW9uPSIxLjEiIGlkPSJMYXllcl8xIiB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHhtbG5zOnhsaW5rPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5L3hsaW5rIiB4PSIwcHgiIHk9IjBweCINCgkgdmlld0JveD0iMCAwIDUxMiA1MTIiIHN0eWxlPSJlbmFibGUtYmFja2dyb3VuZDpuZXcgMCAwIDUxMiA1MTI7IiB4bWw6c3BhY2U9InByZXNlcnZlIj4NCjxnPg0KCTxnPg0KCQk8cGF0aCBkPSJNMjU2LDBDMTE0LjUxLDAsMCwxMTQuNDk3LDAsMjU2YzAsMTQxLjQ5LDExNC40OTcsMjU2LDI1NiwyNTZjMTQxLjQ5LDAsMjU2LTExNC40OTcsMjU2LTI1NkM1MTIsMTE0LjUxLDM5Ny41MDMsMCwyNTYsMHoNCgkJCSBNMjU2LDQ3Ny44NjdjLTEyMi4zMzcsMC0yMjEuODY3LTk5LjUyOS0yMjEuODY3LTIyMS44NjdTMTMzLjY2MywzNC4xMzMsMjU2LDM0LjEzM1M0NzcuODY3LDEzMy42NjMsNDc3Ljg2NywyNTYNCgkJCVMzNzguMzM3LDQ3Ny44NjcsMjU2LDQ3Ny44Njd6Ii8+DQoJPC9nPg0KPC9nPg0KPGc+DQoJPGc+DQoJCTxwYXRoIGQ9Ik0zNzEuMzQ1LDIzOC45MzNoLTk4LjI3OHYtOTguMjc4YzAtOS40MjUtNy42NDEtMTcuMDY3LTE3LjA2Ny0xNy4wNjdzLTE3LjA2Nyw3LjY0MS0xNy4wNjcsMTcuMDY3djk4LjI3OGgtOTguMjc4DQoJCQljLTkuNDI1LDAtMTcuMDY3LDcuNjQxLTE3LjA2NywxNy4wNjdzNy42NDEsMTcuMDY3LDE3LjA2NywxNy4wNjdoOTguMjc4djk4LjI3OGMwLDkuNDI1LDcuNjQxLDE3LjA2NywxNy4wNjcsMTcuMDY3DQoJCQlzMTcuMDY3LTcuNjQxLDE3LjA2Ny0xNy4wNjd2LTk4LjI3OGg5OC4yNzhjOS40MjUsMCwxNy4wNjctNy42NDEsMTcuMDY3LTE3LjA2N1MzODAuNzcsMjM4LjkzMywzNzEuMzQ1LDIzOC45MzN6Ii8+DQoJPC9nPg0KPC9nPg0KPGc+DQo8L2c+DQo8Zz4NCjwvZz4NCjxnPg0KPC9nPg0KPGc+DQo8L2c+DQo8Zz4NCjwvZz4NCjxnPg0KPC9nPg0KPGc+DQo8L2c+DQo8Zz4NCjwvZz4NCjxnPg0KPC9nPg0KPGc+DQo8L2c+DQo8Zz4NCjwvZz4NCjxnPg0KPC9nPg0KPGc+DQo8L2c+DQo8Zz4NCjwvZz4NCjxnPg0KPC9nPg0KPC9zdmc+DQo=" 
      />
    </div>
  </div>
</template>

<script>
import SentenceDetail from './SentenceDetail.vue'

export default {
  components: {
    SentenceDetail,
  },
  mounted() {
    console.log(this.$route.params.userName, 'savesentence')
    // 문장 요청 보내기
    // 지금은 숫자로 요청해야함
    this.$axios.get(`${this.$store.getters.getServer}/user/${this.$route.params.userName}/highlight`)
    .then(res => {
      console.log(res.data.data, '이거 115')
      this.defaultItems = res.data.data
      this.showItems = this.defaultItems.slice(0, 6)
      if (this.defaultItems.length > 6) {
        this.moreBtn = true
      } else {
        this.moreBtn = false
      }
    })
  },
  data() {
    return {
      showItems: [],
      defaultItems: [],
      moreBtn: false,
      showDetail: false,
      userInfo: {},
      nowItem: "",
      nowBookTitle: "",
    }
  },
  methods: {
    more() {
      this.showItems = this.defaultItems
      this.moreBtn = false
    },
    sentenceDetail(item) {
      this.showDetail = true
      this.nowItem = item
      // 여기서 자기껀지 아닌지 확인해주자.
      // 현재 highlight의 작성자와 현재 로그인된 계정 사람이 같은지
    },
  },
}
</script>

<style scoped>
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
    transition-duration: 0.3s;
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
    transition-duration: 0.3s;
    border-radius: 10px;
    background-color: rgba(0, 0, 0, 0.5);
    color: white;
    cursor: pointer;
  }
  .sentence-text {
    margin-top: 80px;
    padding-left: 60px;
    width: 280px;
    height: 150px;
    align-content: center;
    justify-items: center;
    text-align: center;
  }
  .more {
    display: flex;
    justify-content: center;
    align-items: center;
  }
  .more-icon:hover {
    cursor: pointer;
  }
  .modal-content {
    border: none;
  }
</style>