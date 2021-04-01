<template>
  <div>
    <header 
      style="
        display: flex;
        justify-content: flex-end;
        font-size: 15px;
      "
    >
      <font-awesome-icon 
        @click="closeModal()" 
        size="2x" 
        class="mouse-pointer text-secondary" 
        @mouseover="closeIcon = ['fas', 'times-circle']" 
        @mouseleave="closeIcon = ['far', 'times-circle']" 
        :icon="closeIcon" 
      />
    </header>
    <div>
      <img 
        class="sentence-img" 
        width="465px" 
        height="300px" 
        v-if="item.highlightCover"
        :src="item.highlightCover" 
        alt=""
        style="margin-top: 9px;"
      >
      <img 
        class="sentence-img" 
        width="465px" 
        height="300px" 
        v-else
        src="@/assets/waterprint_back.jpg" 
        alt=""
        style="margin-top: 9px;"
      >
      
      <div class="detail-dimmed">
        <!-- text최소 길이, 최대 길이 정해주기 -->
        <div class="detail-sentence-text">
          <div style="font-size: 23px;">{{item.highlightContent}}</div>
          <div style="font-size: 20px; margin-top: 10px; font-weight: bold;">"{{item.bookTitle}}"</div>
        </div>
        <div class="detail-sentence-date">
          {{item.createdDate}}
        </div>
      </div>
    </div>
    <footer class="modal-footer">
      <div>
        <div 
          style="margin-left: -40px; margin-top: -30px;"
          @click="delLike()"
          v-if="likeStatus"
          class="heart hvr-buzz">
        </div>
        <div 
          style="margin-left: -40px; margin-top: -30px;"
          @click="addLike()"
          v-if="!likeStatus"
          class="no-heart">
        </div>
        <div class="like-num">{{likeNum}}</div>
      </div>
      <!-- 여기는 하트랑 아이콘이 들어갈 자리 -->
      <div class="footer-icon" style="font-size: 13px;">
        <font-awesome-icon 
          size="2x"
          class="text-primary mouse-pointer"
          @mouseover="editIcon = ['fas', 'edit']"
          @mouseleave="editIcon = ['far', 'edit']"
          :icon="editIcon"
          v-if="isEditor"
        />
        <font-awesome-icon 
          size="2x"
          class="text-danger mouse-pointer ml-2"
          @mouseover="deleteIcon = ['fas', 'trash-alt']"
          @mouseleave="deleteIcon = ['far', 'trash-alt']"
          :icon="deleteIcon"
          v-if="isEditor"
        />
      </div>
    </footer>
  </div>
</template>

<script>
export default {
  props: {
    item: Object,
  },
  data() {
    return {
      closeIcon: ['far', 'times-circle'],
      editIcon: ['far', 'edit'],
      deleteIcon: ['far', 'trash-alt'],
      hoverClose: false,
      heart: false,
      likeNum: 0,
      isEditor: false,
      likeStatus: null,
    }
  },
  // 지금은 프로필이라서 이렇게 해도 되지만, community의 경우 한 개씩 반복해서 확인해줘야함
  mounted() {
    if (this.$store.getters.getUser.userNickname === this.$route.params.userName) {
      this.isEditor = true
    } else {
      this.isEditor = false
    }
    this.getLikeStatus()
  },
  methods: {
    getLikeStatus() {
      const token = localStorage.getItem('jwt')
      const headers = {
        'Authorization': token
      }
      this.$axios.get(`${this.$store.getters.getServer}/highlight/${this.item.highlightId}`, {headers})
      .then(res => {
        this.likeNum = res.data.data.goodCount
        this.likeStatus = res.data.data.userGood
        console.log(res.data.data, '처음에 들어오는 데이터')
        console.log(res.data.data.userGood, '처음에 들어오는 데이터 userGood')
      })
    },
    closeModal() {
      this.$emit('close-modal')
    },
    addLike() {
      console.log('addLike')
      // 만약 로그인 안한 유저라면 addLike못함
      const token = localStorage.getItem('jwt')
      const headers = {
        "Authorization": token
      }
      if (token) {
        this.$axios.post(`${this.$store.getters.getServer}/highlight/${this.item.highlightId}/good`, {}, {headers})
        .then(res => {
          if (res.data.success) {
            this.likeStatus = true
            this.likeNum += 1
          }
        })
        .catch(err => {
          console.log(err)
        })
      }
    },
    delLike() {
      const token = localStorage.getItem('jwt')
      const headers = {
        "Authorization": token
      }
      if (token) {
        this.$axios.post(`${this.$store.getters.getServer}/highlight/${this.item.highlightId}/good`, {}, {headers})
        .then(res => {
          if (res.data.success) {
            this.likeStatus = false
            this.likeNum -= 1
          }
        })
        .catch(err => {
          console.log(err)
        })
      }
    },
  }
}
</script>

<style>
  .modal-footer {
    /* background-color: blue; */
    display: flex; 
    justify-content: space-between; 
  }
  .like-num {
    margin-top: -63px;
    margin-left: 30px;
  }
  .footer-icon {
    margin: 0;
  }
  .detail-dimmed {
    position: absolute;
    width: 465px;
    border-radius: 10px;
    height: 300px;
    top: 54px;
    background-color: rgba(0, 0, 0, 0.5);
    color: white;
    text-align: center;
  }
  .detail-sentence-text {
    display: absolute;
    width: 465px;
    height: 270px;
    padding: 100px 10px 0 10px;
    /* display: flex; */
    /* align-items: center; */
    /* justify-content: center; */
  }
  .detail-sentence-date {
    display: absolute;
    height: 30px;
    right: 0;
    /* background-color: red; */
    text-align: end;
    padding-right: 20px;
    /* display: flex; */
    /* align-items: center; */
    /* justify-content: center; */
  }

  .no-heart {
    width: 100px;
    height: 100px;
    /* position: relative; */
    /* transform: translate(-50%, -50%); */
    background: url(https://cssanimation.rocks/images/posts/steps/heart.png) no-repeat;
    background-position: 0 0;
    cursor: pointer;
    /* animation: fave-heart 1s step-end infinite; */
    /* animation: fave-heart 1s steps(28); */
  }
  .no-heart:hover {
    background-position: -2800px 0;
    /* animation: fave-heart 1s steps(28) infinite; */
    transition: background 1s steps(28);
  }
  .heart {
    width: 100px;
    height: 100px;
    /* position: relative; */
    /* transform: translate(-50%, -50%); */
    background: url(https://cssanimation.rocks/images/posts/steps/heart.png) no-repeat;
    background-position: -2800px 0;
    cursor: pointer;
    /* transition: background 1s steps(28); */
    /* animation: fave-heart 1s step-start infinite; */
  }
  @keyframes fave-heart {
    0% {
      background-position: 0 0;
    }
    100% {
      background-position: -2800px 0;
    }
  }

</style>