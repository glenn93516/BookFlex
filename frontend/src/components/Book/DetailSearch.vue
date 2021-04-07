<template>
  <div id='mainDiv'>
    <div id="searchDiv" style="display:flex;">
      <b-form-input 
        id="input" 
        style=" left: 0; height: 40px; margin-right:10px; margin-top: 0px; margin-bottom: 5px; border-radius: 20px / 20px; float:left; width: 450px;" 
        class="formcolor animate__animated animate__headShake"
        placeholder="검색어를 입력하세요."
        type="text"
        v-model="text"
        @keypress.enter="searchClick"
      ></b-form-input>
      <span class="search-btn" @click="searchClick">검색</span>
    </div>
    <DetailResultBook id='book' v-bind:item="bookData" @setIsbn="setIsbn"></DetailResultBook>
  </div>
</template>

<script>
import DetailResultBook from './DetailResultBook.vue'


export default {
  props: {
  },
  components: {
    DetailResultBook,
  },
  data() {
    return {
      text : '', //SEARCH에 들어온 상태에서 Nav검색 이용 불가
      bookData : {
        titleData : [],
        authorData : [],
        contentsData : [],
      },
    }
  },
  computed() {
    // console.log(this.$rout.params.word);
    // if(this.$route.params.word!=undefined){
    //   // console.log('route : '+this.$route.params.word);
    //   this.text = this.$route.params.word;
    //   this.searchClick()
    // }
  },
  methods: {
    setIsbn(isbn) {
      // console.log("isbn>>",isbn)
      this.$emit("setIsbn", isbn)
    },
    searchClick(){
      if(this.text.trim==''){
        alert('검색어를 입력하세요.')
      }else{
        this.searchTitle();
        this.searchAuthor();
        this.searchContents();
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
  }
}
</script>

<style scoped>
  .search-btn {
    width: 3rem;
    height: 2rem;
    border-radius: 25px;
    box-shadow: 1px 4px 0 rgb(0, 0, 0, 0.5);
    display: flex;
    justify-content: center;
    align-items: center;
    cursor: pointer;
    border: 1px solid rgba(0, 0, 0, 0.5);
    background-color: rgba(255, 255, 255, 0.5);
    font-size: 0.9rem;
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