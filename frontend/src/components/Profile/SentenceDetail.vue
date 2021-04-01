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
        :src="item.hightlightCover" 
        alt=""
        style="margin-top: 9px;"
      >
      <div class="detail-dimmed">
        <!-- text최소 길이, 최대 길이 정해주기 -->
        <div class="detail-sentence-text">
          <div style="font-size: 23px;">{{item.highlightContent}}</div>
          <div style="font-size: 20px; margin-top: 10px; font-weight: bold;">"{{item.bookName}}"</div>
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
          @click="heart=false"
          v-if="heart"
          class="heart hvr-buzz">
        </div>
        <div 
          style="margin-left: -40px; margin-top: -30px;"
          @click="heart=true"
          v-else
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
          :style="{color: '#FF0000'}"
          v-if="isEditor"
        />
        <font-awesome-icon 
          size="2x"
          class="text-danger mouse-pointer ml-2"
          @mouseover="deleteIcon = ['fas', 'trash-alt']"
          @mouseleave="deleteIcon = ['far', 'trash-alt']"
          :icon="deleteIcon"
          :style="{color: '#FF0000'}"
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
      likeNum: 100,
      isEditor: false,
    }
  },
  // 지금은 프로필이라서 이렇게 해도 되지만, community의 경우 한 개씩 반복해서 확인해줘야함
  mounted() {
    if (this.$store.getters.getUser.userNickname === this.$route.params.userName) {
      this.isEditor = true
    } else {
      this.isEditor = false
    }
  },
  methods: {
    closeModal() {
      this.$emit('close-modal')
    }
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
    background-color: rgba(0, 0, 0, 0.3);
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
  .no-heart:hover {
    background-position: -2800px 0;
    /* animation: fave-heart 1s steps(28) infinite; */
    transition: background 1s steps(28);
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