<template>
  <!-- 북 커버, 워드 클라우드 페이지 -->
  <div class="row">
    <!-- 북 표지 : 왼쪽 페이지 -->
    <div class="col-6 detail-cover-box">
        <div class="card bg-dark text-white detail-cover">
          <img :src="book.book_cover" :alt="book.book_title" class="detail-cover">
          <div class="card-img-overlay book_detail_data" style="z-index: 10; overflow: hidden;">
            <h1>{{ book.book_title }}</h1>
            <br>
            <h5 style="text-align: right; margin-bottom: 10px;">{{ book.book_author }}</h5>
            <h6 style="text-align: right;">{{ book.book_publisher }} | {{ book.book_date }}</h6>
            <br>
            <h6 v-html="book.book_description"></h6>
            <br>
          </div>
        </div>
    </div>

    <!-- 워드클라우드 : 오른쪽 페이지 -->
    <div class="col-6" style="">
      <div 
        style="text-align: right; 
                  margin-top: 20px; 
                  margin-right: 20px;
                  margin-bottom: 20px;
                  color: rgba(100, 100, 100);
                  ">
        <h6 class="mr-1">키워드 보기</h6>
        <hr style="rgba(50, 50, 50); margin-bottom: 40px;">
      </div>
      <div style="margin-bottom: 20px;">
        <WordCloud :isbn="isbn" @open-modal="openModal"/>
      </div>
      <div style="margin-right: 20px;">
        <b-button class="FirstPage_Btn" @click="checkRead">읽음</b-button>
        <b-button class="FirstPage_Btn" @click="addToWishlist">위시리스트</b-button>
        <img 
          style="width: 30px; 
                    height: 30px; 
                    float: right; 
                    cursor: pointer;
                    color: rgba(100, 100, 100);
                    opacity: 0.4;
                    margin-top: 10px;
                    " 
          src="data:image/svg+xml;base64,PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iaXNvLTg4NTktMSI/Pg0KPCEtLSBHZW5lcmF0b3I6IEFkb2JlIElsbHVzdHJhdG9yIDE5LjAuMCwgU1ZHIEV4cG9ydCBQbHVnLUluIC4gU1ZHIFZlcnNpb246IDYuMDAgQnVpbGQgMCkgIC0tPg0KPHN2ZyB2ZXJzaW9uPSIxLjEiIGlkPSJDYXBhXzEiIHhtbG5zPSJodHRwOi8vd3d3LnczLm9yZy8yMDAwL3N2ZyIgeG1sbnM6eGxpbms9Imh0dHA6Ly93d3cudzMub3JnLzE5OTkveGxpbmsiIHg9IjBweCIgeT0iMHB4Ig0KCSB2aWV3Qm94PSIwIDAgNTEyIDUxMiIgc3R5bGU9ImVuYWJsZS1iYWNrZ3JvdW5kOm5ldyAwIDAgNTEyIDUxMjsiIHhtbDpzcGFjZT0icHJlc2VydmUiPg0KPGc+DQoJPGc+DQoJCTxnPg0KCQkJPHBhdGggZD0iTTI1Ni4wMDIsMTgyLjc3NGMtMTIuNzE4LDAtMjEuNzYxLDUuMzctMjEuNzYxLDEzLjI4M1YzMDMuNzNjMCw2Ljc4Miw5LjA0NCwxMy41NjUsMjEuNzYxLDEzLjU2NQ0KCQkJCWMxMi4xNTIsMCwyMi4wNDMtNi43ODMsMjIuMDQzLTEzLjU2NVYxOTYuMDU3QzI3OC4wNDUsMTg4LjE0NCwyNjguMTU0LDE4Mi43NzQsMjU2LjAwMiwxODIuNzc0eiIvPg0KCQkJPHBhdGggZD0iTTI1Ni4wMDIsMTE2LjM2MWMtMTMsMC0yMy4xNzQsOS4zMjYtMjMuMTc0LDIwLjA2NWMwLDEwLjczOSwxMC4xNzQsMjAuMzQ4LDIzLjE3NCwyMC4zNDgNCgkJCQljMTIuNzE4LDAsMjIuODkyLTkuNjA5LDIyLjg5Mi0yMC4zNDhDMjc4Ljg5NCwxMjUuNjg4LDI2OC43MTksMTE2LjM2MSwyNTYuMDAyLDExNi4zNjF6Ii8+DQoJCQk8cGF0aCBkPSJNMjU2LDBDMTM0LjM5OCwwLDM2LDk4LjM0OCwzNiwyMTkuODczYzAsOTkuMjMxLDY1LjMzMywxODEuNjI2LDE1My40MTcsMjA5LjYwOWw1MC4wMzIsNzMuNzQ3DQoJCQkJQzI0My4xNzEsNTA4LjcxNSwyNDkuMzcsNTEyLDI1Niw1MTJzMTIuODI5LTMuMjg1LDE2LjU1MS04Ljc3MWw1MC4wMzItNzMuNzQ3QzQxMC43MDYsNDAxLjQ4Niw0NzYsMzE5LjA2MSw0NzYsMjE5Ljg3Mw0KCQkJCUM0NzYsOTguMjcxLDM3Ny41MjMsMCwyNTYsMHogTTMwNC42NDUsMzkzLjA5OWMtNC41NTUsMS4yNzQtOC41MSw0LjEyLTExLjE2NSw4LjAzM0wyNTYsNDU2LjM3NmwtMzcuNDc5LTU1LjI0Mw0KCQkJCWMtMi42NTYtMy45MTMtNi42MTEtNi43Ni0xMS4xNjYtOC4wMzNDMTMyLjM3NywzNzIuMTM1LDc2LDMwMy4xODEsNzYsMjE5Ljg3M0M3NiwxMjAuNjksMTU2Ljc0OCw0MCwyNTYsNDANCgkJCQlzMTgwLDgwLjY5LDE4MCwxNzkuODczQzQzNiwzMDMuMjY0LDM3OS41NCwzNzIuMTU4LDMwNC42NDUsMzkzLjA5OXoiLz4NCgkJPC9nPg0KCTwvZz4NCjwvZz4NCjxnPg0KPC9nPg0KPGc+DQo8L2c+DQo8Zz4NCjwvZz4NCjxnPg0KPC9nPg0KPGc+DQo8L2c+DQo8Zz4NCjwvZz4NCjxnPg0KPC9nPg0KPGc+DQo8L2c+DQo8Zz4NCjwvZz4NCjxnPg0KPC9nPg0KPGc+DQo8L2c+DQo8Zz4NCjwvZz4NCjxnPg0KPC9nPg0KPGc+DQo8L2c+DQo8Zz4NCjwvZz4NCjwvc3ZnPg0K" 
          title="북플렉스가 처음이신가요?" 
          v-b-popover.hover.top="informationText"
        />
      </div>
    </div>

    <div>
      <Modal v-show="isModalViewed" @close-modal="closeModal">
          <!-- 컨텐츠 컴포넌트 자리 -->
          <!-- 헤더 자리 -->
          <template #header>

          </template>
          <!-- 바디 자리 -->
          <template #body>
            <ReviewList v-if="step === 'checkReview'" :reviewList="reviewList" :reviewKeyword="reviewKeyword"/>   
            <CollectSentence v-if="step === 'collectSentence' " :book="book" :mode="0" @close-modal="closeModal"/>         
          </template>
      </Modal> 
    </div>
  </div>
</template>

<script>
import WordCloud from '@/components/Element/WordCloud.vue'
import Modal from '@/components/Element/Modal.vue'
import ReviewList from '@/components/Book/ReviewList.vue'
import CollectSentence from '@/components/Book/CollectSentence.vue'

export default {
  props: {
  },
  components: {
    "WordCloud": WordCloud,
    "Modal": Modal,
    "ReviewList": ReviewList,
    "CollectSentence": CollectSentence,
  },
  data() {
    ReviewList
    return {
      book: Object,
      isbn: "",
      isModalViewed: false,
      reviewKeyword: "",
      reviewList: [],
      informationText: "1. 단어를 클릭해보세요. 단어를 포함한 리뷰를 보여드릴게요 \n 2. 책에 마우스를 가져가면 디테일한 정보를 확인할 수 있습니다.",
      step: "",
    }
  },
  created() {
    const isbn = this.$route.params.bookIsbn
    this.isbn = isbn
    // 책 정보 가져오는 함수
    this.getBookData(isbn)
    this.getReviewList(isbn)
  },
  methods: {
    getBookData(isbn) {
      this.$axios.get(`${this.$store.getters.getServer}/book/${isbn}`)
      .then(res => {
        // console.log(res.data.data)
        this.book = res.data.data
      })
      .catch(err => {
        console.error(err)
      })
    },
    closeModal() {
      this.isModalViewed = false
    },
    // 워드클라우드 단어 클릭시 연결
    openModal(keyword) {
      if (keyword != undefined) {
        this.reviewKeyword = keyword
        this.step = 'checkReview'
        this.isModalViewed = true
        console.log(keyword)
      } else {
        this.step = 'collectSentence'
        this.isModalViewed = true
      }
    },
    // 리뷰 리스트 불러오기
    getReviewList(isbn) {
      this.$axios.get(`${this.$store.getters.getServer}/book/${isbn}/review`)
      .then(res => {
        // console.log('리뷰리스트', res.data.data)
        this.reviewList = res.data.data
      })
      .catch(err => {
        console.error(err)
      })
    },
    // 읽음 표시
    checkRead() {
      const token = localStorage.getItem('jwt')
			const headers = {
				"Authorization": token,
			}
			if (token) {
				this.$axios.post(`${this.$store.getters.getServer}/bookshelf`, {
					"bookIsbn": this.book.book_isbn,
					"userId": this.$store.getters.getUser.userId
				},
				{
					headers,
				})
				.then(res => {
					alert('읽기 목록에 담겼습니다 :)')
					console.log(res)
					// 문장수집으로 이동
          this.openModal()
				})
				.catch(err => {
					console.error(err)
          alert('이미 읽으셨군요! 문장을 수집해보세요.')
          this.openModal()
				})
			} else {
				alert('로그인 후 이용하세요!')
			}
    },
    // 위시리스트에 담기
    addToWishlist() {
      const token = localStorage.getItem('jwt')
			const headers = {
				"Authorization": token
			}
			if (token) {
				this.$axios.post(`${this.$store.getters.getServer}/wishlist/${this.book.book_isbn}`, {}, 
					{
						headers
					})
				.then(res => {
					console.log(res)
					alert('위시리스트에 담겼습니다 :)')
				})
				.catch(err => {
					console.error(err)
				})
			} else {
				alert('로그인 후 이용하세요!')
			}
    }
  }
}
</script>

<style>
  .detail-cover-box {
    padding-top: 10px;
    padding-bottom: 10px;
    padding-right: 25px;
    padding-left: 25px;
    border-right: 1px solid rgba(121, 121, 121, 0.692);
  }
  .detail-cover {
    height: 680px;
    width: 530px;
    border: 0px;
  }
  .book_detail_data {
    color: rgba(0, 0, 0, 0);
    background-color: rgba(0, 0, 0, 0);
    padding-top: 30px;
    transition: 0.3s;
  }
  .book_detail_data:hover {
    color: white;
    background-color: rgba(0, 0, 0, 0.7);
    transition: 0.3s;
  }
  .FirstPage_Btn {
    margin-top: 5px;
    margin-right: 10px;
    background-color: white;
    border: 0px;
    color: black;
  }
  .FirstPage_Btn:hover {
    background-color: rgb(192, 180, 165);
    color: rgb(255, 255, 255);
  }
</style>