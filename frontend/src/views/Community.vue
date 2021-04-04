<template>
  <div id="community">
    <section id="monthly-recommend-book">
      <header class="title">이달의 추천 책</header>
      <hr><br>
      <div style="display : flex; justify-content:center;" >
        
        <!-- <Book></Book> -->
        <div style="margin-left : 30px; margin-right:30px; justify-content:center;">
          <h6 style="text-align: center;"><strong>유저들이 가장 많이 읽은 도서 </strong></h6><br>
          <community-book  v-bind:book="readBook" style=" margin:0 auto;"></community-book>  
        </div>
        <div style="margin-left : 30px; margin-right:30px; justify-content:center;">
          <h6 style="text-align: center;"><strong>좋아요가 가장 많은 문장의 도서 </strong></h6><br>
          <community-book  v-bind:book="goodBook" style=" margin:0 auto;"></community-book>  
        </div>
        <div style="margin-left : 30px; margin-right:30px; justify-content:center;">
          <h6 style="text-align: center;"><strong>위시리스트에 가장 많이 담긴 도서 </strong></h6><br>
          <community-book  v-bind:book="wishlistBook" style=" margin:0 auto;"></community-book>  
        </div>
        <div style="margin-left : 30px; margin-right:30px; justify-content:center;">
          <h6 style="text-align: center;"><strong>문장 수집이 가장 많이 된 도서</strong></h6><br>
          <community-book style=" margin:0 auto;" v-bind:book="highlightBook" ></community-book>  
        </div>
        
      </div>
    </section>
    <br>
    <br>
    <section id="sentence-timeline">
      <header class="title">실시간 수집된 문장</header>
      <hr>
      <!-- <community-item :item="showItems[0]"></community-item> -->
      <div id="itemContainer">
        <community-item v-for="item in showItems" :key="item.index" :item="item"></community-item>
      </div>
    </section>
  </div>
</template>

<script>
// import Book from '@/components/Main/Book.vue'
import CommunityItem from '../components/Community/CommunityItem.vue'
import CommunityBook from '../components/Search/SearchBookDetail.vue'


export default {
  components: {
    // Book,
    CommunityItem,
    CommunityBook
  },
  data() {
    return {
      showItems: [],
      readBook : [],
      highlightBook : [],
      goodBook : [],
      wishlistBook : [],
      first: "data:image/svg+xml;base64,PHN2ZyBpZD0iQ2FwYV8xIiBlbmFibGUtYmFja2dyb3VuZD0ibmV3IDAgMCA1MTIgNTEyIiBoZWlnaHQ9IjUxMiIgdmlld0JveD0iMCAwIDUxMiA1MTIiIHdpZHRoPSI1MTIiIHhtbG5zPSJodHRwOi8vd3d3LnczLm9yZy8yMDAwL3N2ZyI+PGc+PGc+PHBhdGggZD0ibTE1NS4wMjggMzU1LjExNC0yMC4yOTguOTktMzkuMzU0IDExMy4wNjZjLTEuMjU0IDMuNjAxIDEuNjY0IDcuMjg0IDUuNDU3IDYuODg2bDU1LjE2My01Ljc3NmMxLjU0MS0uMTYxIDMuMDczLjM3MiA0LjE4MSAxLjQ1NWwzOS42NTUgMzguNzc5YzIuNzI2IDIuNjY2IDcuMyAxLjU5MSA4LjU1My0yLjAxbDM5LjYxMS0xMTMuODA1LTExLjY4LTExLjMzNnoiIGZpbGw9IiNmZjQ3NTUiLz48cGF0aCBkPSJtMjQ3Ljk5NiAzOTQuNjk5IDguMDA0LTIyLjk5NS0xMTMuMjktMzguNTI2LTcuOTggMjIuOTI3YzMyLjE3IDIyLjk1NSA3MS4xMjIgMzcuMDA3IDExMy4yNjYgMzguNTk0eiIgZmlsbD0iI2ZjMmIzYSIvPjxwYXRoIGQ9Im0zNTYuOTcyIDM1NS4xMTQgMjAuMjk4Ljk5IDM5LjM1NCAxMTMuMDY2YzEuMjU0IDMuNjAxLTEuNjY0IDcuMjg0LTUuNDU3IDYuODg2bC01NS4xNjMtNS43NzZjLTEuNTQxLS4xNjEtMy4wNzMuMzcyLTQuMTggMS40NTVsLTM5LjY1NSAzOC43NzljLTIuNzI2IDIuNjY2LTcuMyAxLjU5MS04LjU1My0yLjAxbC0zOS42MTEtMTEzLjgwNSAxMS42OC0xMS4zMzZ6IiBmaWxsPSIjZmY0NzU1Ii8+PHBhdGggZD0ibTI2NC4wMDQgMzk0LjY5OS04LjAwNC0yMi45OTUgMTEzLjI5LTM4LjUyNiA3Ljk4IDIyLjkyN2MtMzIuMTcgMjIuOTU1LTcxLjEyMiAzNy4wMDctMTEzLjI2NiAzOC41OTR6IiBmaWxsPSIjZmMyYjNhIi8+PHBhdGggZD0ibTI1NiAzNjAuMTE2Yy0xODUuODUyIDAtMTg1Ljc3NC0xODAuNTI5LTE4NS42Mi0xODMuNjMyIDQuODc5LTk4LjI5IDg2LjExOC0xNzYuNDg0IDE4NS42Mi0xNzYuNDg0czE4MC43NDEgNzguMTk0IDE4NS42MiAxNzYuNDg1Yy4xNTQgMy4xMDMuMjMyIDE4My42MzEtMTg1LjYyIDE4My42MzF6IiBmaWxsPSIjZmZlMjdhIi8+PHBhdGggZD0ibTI1NiAzNTIuOTY5Yy05OS41MDIgMC0xODAuNzM4LTc4LjE5NC0xODUuNjE4LTE3Ni40ODUtLjE1NCAzLjEwMy0uMjM0IDYuMjI2LS4yMzQgOS4zNjcgMCAxMDIuNjQzIDgzLjIwOSAxODUuODUyIDE4NS44NTIgMTg1Ljg1MnMxODUuODUyLTgzLjIwOSAxODUuODUyLTE4NS44NTJjMC0zLjE0Mi0uMDgtNi4yNjQtLjIzNC05LjM2Ny00Ljg4IDk4LjI5MS04Ni4xMTYgMTc2LjQ4NS0xODUuNjE4IDE3Ni40ODV6IiBmaWxsPSIjZjljZjU4Ii8+PHBhdGggZD0ibTI1NiAzMjAuMTI2Yy0xNDYuMTE3IDAtMTQ2LjMxMy0xNDMuNjgyLTE0Ni4xMTctMTQ2Ljc4IDQuODMxLTc2LjQ5NiA2OC40MDMtMTM3LjA0NCAxNDYuMTE3LTEzNy4wNDRzMTQxLjI4NiA2MC41NDggMTQ2LjExNyAxMzcuMDQ0Yy4xOTYgMy4wOTcgMCAxNDYuNzgtMTQ2LjExNyAxNDYuNzh6IiBmaWxsPSIjZmZiYTU3Ii8+PHBhdGggZD0ibTI1NiAzMTMuNTI5Yy03Ny43MTMgMC0xNDEuMjc0LTYwLjU1LTE0Ni4xMDUtMTM3LjA0NS0uMTk2IDMuMDk4LS4zMDcgNi4yMi0uMzA3IDkuMzY3IDAgODAuODYxIDY1LjU1MSAxNDYuNDEyIDE0Ni40MTIgMTQ2LjQxMnMxNDYuNDEyLTY1LjU1MSAxNDYuNDEyLTE0Ni40MTJjMC0zLjE0OC0uMTExLTYuMjY5LS4zMDctOS4zNjctNC44MzEgNzYuNDk1LTY4LjM5MiAxMzcuMDQ1LTE0Ni4xMDUgMTM3LjA0NXoiIGZpbGw9IiNmZmFjM2UiLz48cGF0aCBkPSJtMjY0LjM1IDI3Mi44MmMtMTAuMTY1IDAtMTguNDA2LTguMjQtMTguNDA2LTE4LjQwNnYtOS43MzUtOTMuNDE2cy0xMS4wMTMgMTEuMDQ1LTI0LjE5IDQuNDE4Yy0xMC4yNzQtNS4xNjctMTAuMjc0LTE4LjQ2Ni0xMC4yNzQtMTguNDY2bDIyLjY5My0zOC4zMDZjMy4zMTQtNS41OTQgOS4zMzQtOS4wMjUgMTUuODM2LTkuMDI1aDE0LjM0MWMxMC4xNjUgMCAxOC40MDYgOC4yNCAxOC40MDYgMTguNDA2djEzNi4zODkgOS43MzVjMCAxMC4xNjYtOC4yNDEgMTguNDA2LTE4LjQwNiAxOC40MDZ6IiBmaWxsPSIjZmZlMjdhIi8+PGcgZmlsbD0iI2Y5Y2Y1OCI+PHBhdGggZD0ibTI0My40MTUgMTM2LjhjLTQuODg1IDguMjQ3LTE1LjI3MiAxMS44MjMtMjMuOTM1IDcuNzE4LTMuNTA2LTEuNjYxLTYuMjA5LTQuMjQyLTguMDA2LTcuMjktNS4xMTEgOC42NDYtMi4zNDQgMTkuNzY5IDYuMTY2IDI1LjAyNCA4Ljc3NiA1LjQyIDIwLjUwMyAyLjE4NCAyNS43NTktNi42OTFsMi41NDYtNC4yOTl2LTEzLjc3MWMwLTEuMzgyLTEuODI1LTEuODgxLTIuNTMtLjY5MXoiLz48cGF0aCBkPSJtMjY0LjM1IDI2My4wODVjLTEwLjE2NSAwLTE4LjQwNi04LjI0LTE4LjQwNi0xOC40MDZ2MTguNzM1YzAgMTAuMTY2IDguMjQxIDE4LjQwNiAxOC40MDYgMTguNDA2czE4LjQwNi04LjI0IDE4LjQwNi0xOC40MDZ2LTE4LjczNWMwIDEwLjE2Ni04LjI0MSAxOC40MDYtMTguNDA2IDE4LjQwNnoiLz48L2c+PC9nPjwvZz48L3N2Zz4=",
    }
  },
  created() {
    this.getHighlightList();
    this.getReadBook();
    this.getGoodBook();
    this.getWishListBook();
    this.getHighLightBook();
  },
  methods: {
    getHighlightList(){
      this.$axios.get(`${this.$store.getters.getServer}/highlight`)
      .then(res => {
        // console.log(res.data.data, 'res')
        this.showItems = res.data.data
      })
      .catch(err => {
        console.log(err)
      })      
    },
    getReadBook(){
      this.$axios.get(`${this.$store.getters.getServer}/community/read`)
      .then(res => {
        this.readBook = res.data.data[0]
      })
      .catch(err => {
        console.log(err)
      })

    },
    getGoodBook(){
      this.$axios.get(`${this.$store.getters.getServer}/community/good`)
      .then(res => {
        this.goodBook = res.data.data[0]
      })
      .catch(err => {
        console.log(err)
      })
    },
    getWishListBook(){
      this.$axios.get(`${this.$store.getters.getServer}/community/wishlist`)
      .then(res => {
        this.wishlistBook = res.data.data[0]
      })
      .catch(err => {
        console.log(err)
      })

    },
    getHighLightBook(){
      this.$axios.get(`${this.$store.getters.getServer}/community/highlight`)
      .then(res => {
        this.highlightBook = res.data.data[0]
      })
      .catch(err => {
        console.log(err)
      })

    }
  },

  
}
</script>

<style>
#community .title {
  font-size: 30px;
  font-weight: bold;
}
#community hr {
  margin: 10px 0;
}
</style>