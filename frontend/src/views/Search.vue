<template>
  <div id='mainDiv'>
    <div id="searchDiv" style="display:flex; align-items: center;">
      <b-form-input 
        id="input" 
        style=
        " 
          left: 0; 
          border-radius: 20px / 20px; 
          float:left;
          margin: 0 2rem;
          box-shadow: 5px 5px 10px -5px grey;
        " 
        class="formcolor animate__animated animate__headShake"
        placeholder="검색어를 입력하세요."
        type="text"
        v-model="text"
        @keypress.enter="searchClick"
        size="lg"
      ></b-form-input>
      <span class="search-btn" @click="searchClick">검색</span>
    </div>
    <br>
    <br>
    <!-- <recommend-word></recommend-word><br><br> -->
    <!-- <search-bar> </search-bar> -->
    <result-user id='user' v-bind:userData="userData"></result-user><br><br>
    <result-book id='book' v-bind:item="bookData"></result-book>
  </div>
</template>

<script>
import resultUser from '../components/Search/ResultUser.vue'
import resultBook from '../components/Search/ResultBook.vue'
// import recommendWord from '../components/Search/RecommendWord.vue'


export default {
  props: {
  },
  components: {
    resultUser,
    resultBook,
    // recommendWord,
  },
  data() {
    return {
      text : '', //SEARCH에 들어온 상태에서 Nav검색 이용 불가
      bookData : {
        titleData : [],
        authorData : [],
        contentsData : [],
      },
      userData : null,
    }
  },
  created() {
    if(this.$route.params.word==null){
      this.text = ''
    }else{
      this.text = this.$route.params.word;
      this.searchClick();
    }
  },
  watch: {
    $route() {
      this.text = this.$route.params.word;
      this.searchClick();
    }
  },
  methods: {
    searchClick(){
      if(this.text.trim()==''){
        alert('검색어를 입력하세요.')
      }else{
        this.searchTitle();
        this.searchAuthor();
        this.searchContents();
        this.searchUser();
      }
    },
    searchTitle(){
      const form = {
        search : 'title',
        word : this.text
      };
      this.$axios.get(`${this.$store.getters.getServer}/book`,{ params: form })
        .then(res => {
          this.bookData.titleData = res.data.data;
        })
        .catch(err => {
          console.error(err)
        })
    },
    searchAuthor(){
      this.form = {
        search : 'author',
        word : this.text
      };
      this.$axios.get(`${this.$store.getters.getServer}/book`,{ params: this.form })
        .then(res => {
          this.bookData.authorData = res.data.data;
        })
        .catch(err => {
          console.error(err)
        })
    },
    searchContents(){
      this.form = {
        search : 'contents',
        word : this.text
      };
      this.$axios.get(`${this.$store.getters.getServer}/book`,{ params: this.form })
        .then(res => {
          this.bookData.contentsData = res.data.data;
        })
        .catch(err => {
          console.error(err)
        })
    },
    searchUser(){
      var temp = null;
      this.$axios.get(`${this.$store.getters.getServer}/user/${this.text}`)
        .then(res => {
          this.userData = res.data.data;
          temp = res.data.data;
        })
        .catch(() => {
          this.userData = null;
        })
      if(temp==null){
        this.userData = null;
      }
    }
  }
}
</script>

<style>
  .search-btn {
    width: 5rem;
    height: 3rem;
    border-radius: 25px;
    box-shadow: 1px 4px 0 rgb(0, 0, 0, 0.5);
    display: flex;
    justify-content: center;
    align-items: center;
    cursor: pointer;
    border: 1px solid rgba(0, 0, 0, 0.5);
    background-color: rgba(255, 255, 255, 0.5);
    font-size: 1.3rem;
  }
  .search-btn:hover {
    background-color: rgb(127, 102, 91);
    font-weight: bold;
    color: white;
  }
  .search-btn:active {
    box-shadow: 1px 1px 0 rgb(0,0,0,0.5);
    position: relative;
    top: 2px;
  }
</style>