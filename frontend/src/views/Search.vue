<template>
  <div id='mainDiv'>
    <div id="searchDiv" style="display:flex;">
      <b-form-input 
        id="input" 
        style=" left: 0; height: 40px; margin-top: 1px; border-radius: 20px / 20px; float:left;" 
        class="formcolor animate__animated animate__headShake"
        placeholder="검색어를 입력하세요."
        type="text"
        v-model="text"
        @keypress.enter="searchClick"
      ></b-form-input><!-- @keypress.enter="hideInput" -->
      <img height="35px" class="hvr-rotate" @click="searchClick" width="50" style="margin-top: 1px; float: right;" src="data:image/svg+xml;base64,PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iaXNvLTg4NTktMSI/Pg0KPCEtLSBHZW5lcmF0b3I6IEFkb2JlIElsbHVzdHJhdG9yIDE5LjAuMCwgU1ZHIEV4cG9ydCBQbHVnLUluIC4gU1ZHIFZlcnNpb246IDYuMDAgQnVpbGQgMCkgIC0tPg0KPHN2ZyB2ZXJzaW9uPSIxLjEiIGlkPSJMYXllcl8xIiB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHhtbG5zOnhsaW5rPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5L3hsaW5rIiB4PSIwcHgiIHk9IjBweCINCgkgdmlld0JveD0iMCAwIDUxMS45OTkgNTExLjk5OSIgc3R5bGU9ImVuYWJsZS1iYWNrZ3JvdW5kOm5ldyAwIDAgNTExLjk5OSA1MTEuOTk5OyIgeG1sOnNwYWNlPSJwcmVzZXJ2ZSI+DQo8Zz4NCgk8Zz4NCgkJPGc+DQoJCQk8cGF0aCBkPSJNNTAxLjAxMSw0NDcuOTAyTDM0Ny41NCwyOTQuNDMxYzIwLjAwOS0zMC4wNDYsMzEuNjg5LTY2LjA5MywzMS42ODktMTA0LjgyMUMzNzkuMjI5LDg1LjA1OSwyOTQuMTcsMCwxODkuNjE4LDANCgkJCQlTMC4wMDgsODUuMDU5LDAuMDA4LDE4OS42MXM4NS4wNTksMTg5LjYxLDE4OS42MSwxODkuNjFjMzguNzI4LDAsNzQuNzc0LTExLjY4LDEwNC44MjEtMzEuNjg5TDQ0Ny45MSw1MDEuMDAyDQoJCQkJYzcuMDkyLDcuMDkyLDE2LjUyMSwxMC45OTcsMjYuNTUxLDEwLjk5N2MxMC4wMywwLDE5LjQ1OC0zLjkwNSwyNi41NS0xMC45OThDNTE1LjY1LDQ4Ni4zNjMsNTE1LjY1LDQ2Mi41NDIsNTAxLjAxMSw0NDcuOTAyeg0KCQkJCSBNMTg5LjYxOCwzNTkuMjJjLTkzLjUyMywwLTE2OS42MS03Ni4wODYtMTY5LjYxLTE2OS42MUMyMC4wMDgsOTYuMDg3LDk2LjA5NSwyMCwxODkuNjE4LDIwczE2OS42MSw3Ni4wODcsMTY5LjYxLDE2OS42MQ0KCQkJCVMyODMuMTQxLDM1OS4yMiwxODkuNjE4LDM1OS4yMnogTTQ4Ni44NjgsNDg2Ljg2Yy0zLjMxNCwzLjMxNC03LjcyMSw1LjE0LTEyLjQwNyw1LjE0Yy00LjY4OCwwLTkuMDk0LTEuODI1LTEyLjQwOC01LjE0DQoJCQkJTDMxMC42NDgsMzM1LjQ1NWM5LjAxMy03LjQ5MiwxNy4zMjMtMTUuODAyLDI0LjgxNS0yNC44MTVsMTUxLjQwNSwxNTEuNDA0QzQ5My43MDksNDY4Ljg4Nyw0OTMuNzA5LDQ4MC4wMTksNDg2Ljg2OCw0ODYuODZ6Ii8+DQoJCQk8cGF0aCBkPSJNMTg5LjYxOSw0NS4xNzdjLTc5LjY0MSwwLTE0NC40MzMsNjQuNzkyLTE0NC40MzMsMTQ0LjQzM2MwLDUuNTIyLDQuNDc4LDEwLDEwLDEwYzUuNTIyLDAsMTAtNC40NzcsMTAtMTANCgkJCQljMC02OC42MTMsNTUuODIxLTEyNC40MzMsMTI0LjQzMy0xMjQuNDMzYzUuNTIyLDAsMTAtNC40NzgsMTAtMTBTMTk1LjE0MSw0NS4xNzcsMTg5LjYxOSw0NS4xNzd6Ii8+DQoJCQk8cGF0aCBkPSJNMzIwLjAyOSwxMjcuNDU3Yy0xNC4yMjMtMjkuNzktMzguNDYxLTU0LjAyOS02OC4yNS02OC4yNTNjLTQuOTg0LTIuMzgxLTEwLjk1NC0wLjI2OC0xMy4zMzMsNC43MTYNCgkJCQljLTIuMzc5LDQuOTgzLTAuMjY4LDEwLjk1Myw0LjcxNiwxMy4zMzNjMjUuNjcyLDEyLjI1OCw0Ni41NjEsMzMuMTQ3LDU4LjgxOCw1OC44MjFjMS43MTYsMy41OTMsNS4yOTgsNS42OTMsOS4wMzEsNS42OTMNCgkJCQljMS40NDQsMCwyLjkxMS0wLjMxNCw0LjMwMi0wLjk3N0MzMjAuMjk3LDEzOC40MSwzMjIuNDA4LDEzMi40NDEsMzIwLjAyOSwxMjcuNDU3eiIvPg0KCQk8L2c+DQoJPC9nPg0KPC9nPg0KPGc+DQo8L2c+DQo8Zz4NCjwvZz4NCjxnPg0KPC9nPg0KPGc+DQo8L2c+DQo8Zz4NCjwvZz4NCjxnPg0KPC9nPg0KPGc+DQo8L2c+DQo8Zz4NCjwvZz4NCjxnPg0KPC9nPg0KPGc+DQo8L2c+DQo8Zz4NCjwvZz4NCjxnPg0KPC9nPg0KPGc+DQo8L2c+DQo8Zz4NCjwvZz4NCjxnPg0KPC9nPg0KPC9zdmc+DQo=" />
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
      // console.log('route : '+this.$route.params.word);
      this.text = this.$route.params.word;
      this.searchClick();
    }
  },
  watch: {
    $route() {
      // console.log('watch : '+this.$route.params.word);
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
      // console.log(this.form)
      this.$axios.get(`${this.$store.getters.getServer}/book`,{ params: form })
        .then(res => {
          this.bookData.titleData = res.data.data;
          // console.log('this.bookData.titleData  >> ',this.bookData.titleData)
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
      // console.log(this.form)
      this.$axios.get(`${this.$store.getters.getServer}/book`,{ params: this.form })
        .then(res => {
          this.bookData.authorData = res.data.data;
          // console.log('this.bookData.authorData  >> ',res.data.data)
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
      // console.log(this.form)
      this.$axios.get(`${this.$store.getters.getServer}/book`,{ params: this.form })
        .then(res => {
          this.bookData.contentsData = res.data.data;
          // console.log('this.bookData.contentsData >> ',res.data.data)
        })
        .catch(err => {
          console.error(err)
        })
    },
    searchUser(){
      var temp = null;
      this.$axios.get(`${this.$store.getters.getServer}/user/${this.text}`)
        .then(res => {
          // console.log('this.Userdata >> ',res.data.data);
          this.userData = res.data.data;
          temp = res.data.data;
        })
        .catch(() => {
          this.userData = null;
          console.log("없는 유저입니다.")
          // console.error(err);
        })
      if(temp==null){
        this.userData = null;
      }
    }
  }
}
</script>

<style>
  .profile-nav {
    font-family: 'S-CoreDream-2ExtraLight', sans-serif;
  }
  /* reset */
  h1, p {
    margin: 0;
    padding: 0;
  }
  #navInput {
    font-size: 12px;
    width: 200px;
  }
  #navInput[type="text"]:focus {
    border-color: rgb(218, 203, 0);
    box-shadow: 0 1px 1px rgba(0, 0, 0, 0.075) inset, 0 0 6px rgba(255, 195, 105, 0.6);
    outline: 0 none;
    font-size: 12px;
    width: 200px;
  }

  .more {
    display: flex;
    justify-content: center;
    align-items: center;
  }
  .more-word {
    display: block;
  }
  .more-icon {
    display: block;
  }
  .more-icon:hover {
    width: 40px;
    cursor: pointer;
  }
</style>