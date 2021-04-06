<template>
  <div id="communityItem" style=" border-bottom: solid 1px rgba(33, 37, 41, 0.2); margin : 0 300px">
    <header class="header">
      <b-avatar :src="item.userProfileImg" class="avatar mouse-pointer" variant="white"></b-avatar>
      <span class="usernickname mouse-pointer" @click="goProfile()">
        {{item.userNickname}}
      </span>
    </header>
    <div style="position: relative;" >
      <img 
        class="sentence-img" 
        v-if="item.highlightCover"
        :src="item.highlightCover" 
        alt=""
        style="
          position: relative;
        "
      >
      <img 
        class="sentence-img" 
        v-else
        src="@/assets/waterprint_back.jpg" 
        alt=""
        style="
          position: relative;
        "
      >
      <div class="detail-sentence-text">
        <div class="sentence-text">{{item.highlightContent}}</div>
        <div class="sentence-book-title mouse-pointer" @click="clickBook(item.bookIsbn)">📖 {{item.bookTitle}}-{{item.highlightPage}}p</div>
      </div>
    </div>
    <div class="detail">
      <div class="detail-sentence-date">
        {{item.createdDate.slice(0, 10)}}
      </div>
      <!-- 하트랑 수정, 삭제 들어가는 곳 -->
      <div class="footer">
        <div>
          <div 
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
        <div class="footer-icon" style="font-size: 13px;">
          <font-awesome-icon 
            size="2x"
            class="text-primary mouse-pointer"
            @click="modifySentence()"
            @mouseover="editIcon = ['fas', 'edit']"
            @mouseleave="editIcon = ['far', 'edit']"
            :icon="editIcon"
            v-if="isEditor"
          />
          <font-awesome-icon 
            size="2x"
            class="text-danger mouse-pointer ml-2"
            @click="deleteSentence()"
            @mouseover="deleteIcon = ['fas', 'trash-alt']"
            @mouseleave="deleteIcon = ['far', 'trash-alt']"
            :icon="deleteIcon"
            v-if="isEditor"
          />
        </div>
      </div>
    </div>
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
      writterInfo: null,
    }
  },
  mounted() {
    // console.log(this.item, 'item')
    let userId = this.$store.getters.getUser.userId
    if (this.item.userId === userId) {
      this.isEditor = true
    } else {
      this.isEditor = false
    }
    this.getLikeStatus()
  },
  methods: {
    goProfile() {
      this.$router.push({ name: 'Profile', params: { userName: this.item.userNickname }})
    },
    getLikeStatus() {
      const token = localStorage.getItem('jwt')
      const headers = {
        'Authorization': token
      }
      this.$axios.get(`${this.$store.getters.getServer}/highlight/${this.item.highlightId}`, {headers})
      .then(res => {
        this.likeNum = res.data.data.goodCount
        this.likeStatus = res.data.data.userGood
        this.writterInfo = res.data.data
      })
    },
    clickBook(isbn) {
      this.$router.push({ name: 'BookDetail', params: { bookIsbn: isbn }})
    },
    addLike() {
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
    deleteSentence() {
      const token = localStorage.getItem('jwt')
      const headers = {
        "Authorization": token
      }
      console.log(this.item, '여기는 아이템')
      this.$axios.delete(`${this.$store.getters.getServer}/highlight/${this.item.highlightId}`, {}, {headers})
      .then(res => {
        console.log(res, '삭제 성공')
      })
      .catch(err => {
        console.log(err, '삭제 실패')
      })
    },
    modifySentence() {

    },
  }
}
</script>

<style scoped>
  #communityItem {
    text-align: center;
    padding-top: 15px;
  }
  #communityItem .header {
    width: 465px;
    margin: 5px auto;
    text-align: start;
    display: flex;
    align-items: center;
  }
  #communityItem .header .avatar {
    border: rgba(33, 37, 41, 0.5) 1px solid;
    margin: 0 10px;
  } 
  #communityItem .usernickname {
    font-size: 20px;
    font-weight: bold;
  }
  #communityItem .sentence-img {
    width: 465px;
    height: 300px;
    margin-top: 9px;
    border-radius: 5px 5px 0 0;
  }
  #communityItem .sentence-text {
    font-size: 20px;
  }
  #communityItem .sentence-book-title {
    font-size: 18px; 
    margin-top: 10px; 
    margin-bottom: 20px;
    font-weight: bold;
  }
  #communityItem .footer {
    padding: 10px 0;
    margin: 0 auto;
    width: 465px;
    display: flex; 
    justify-content: space-between; 
    border: none;
  }
  #communityItem .footer .heart {
    margin-left: -40px;
    margin-top: -30px;
  }
  .like-num {
    margin-top: -63px;
    margin-left: 30px;
  }
  .footer-icon {
    margin: 0;
  }
  #communityItem .detail {
    width: 465px;
    border-radius: 5px 5px 0 0;
    color: black;
    margin: 0 auto;
    padding-bottom: 10px;
  }
  #communityItem .detail-sentence-text {
    position: absolute;
    width: 465px;
    text-align: center;
    margin-bottom: 10px;
    padding-bottom: 10px;
    top : 0;
    left: 22px; 
    width:465px; 
    height : 300px; 
    padding-top:100px;
    margin: 9px 0;
    background-color: rgba(255, 255, 255, 0.5);
    border-radius: 5px 5px 0 0;
  }
  #communityItem .detail-sentence-date {
    /* position: absolute; */
    height: 30px;
    right: 0;
    text-align: end;
    padding-right: 20px;
    color: white;
    background-color: rgba(0, 0, 0, 0.8);
    border-radius: 0 0 5px 5px;
  }

  .no-heart {
    width: 100px;
    height: 100px;
    background: url(https://cssanimation.rocks/images/posts/steps/heart.png) no-repeat;
    background-position: 0 0;
    cursor: pointer;
  }
  .no-heart:hover {
    background-position: -2800px 0;
    transition: background 1s steps(28);
  }
  .heart {
    width: 100px;
    height: 100px;
    background: url(https://cssanimation.rocks/images/posts/steps/heart.png) no-repeat;
    background-position: -2800px 0;
    cursor: pointer;
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