<template>
  <div>
    <h2><strong>검색 결과 - BOOK</strong></h2>
    <div v-if="item.titleData.length==0&&item.authorData.length==0&&item.contentsData.length==0" style="margin-bottom:5px;">
      <h4 style="text-align: center; ">😥검색된 결과가 없습니다.</h4>
    </div>
    <div v-if="item.titleData.length > 0||item.authorData.length > 0||item.contentsData.length > 0">
      <br><h4> #도서명</h4><br>
      <div v-if="item.titleData!=null">
        <div style="display : flex" >
          <search-book-detail  
            v-for="(book,index) in titleList" 
            v-bind:book="book" 
            v-bind:key="book.book_title"
            v-bind:index='index'
          ></search-book-detail >
        </div><br>
        <div v-if="item.titleData.length > 0">
          <b-pagination
            pills
            v-model="titleCurrentPage"
            :total-rows="titleTotalRows"
            :per-page="7"
            align="center"
          ></b-pagination><!-- @page-click="pageClick" -->
        </div>
      </div>
      <div v-if="item.titleData.length===0">
        <h5 style="text-align: center; ">😥검색된 결과가 없습니다.</h5>
      </div>
      <br><br>
      <h4> #작가명</h4><br>
      <div v-if="item.authorData!=null">
        <div style="display : flex">
          <search-book-detail 
            v-for="(book,index) in authorList" 
            v-bind:book="book" 
            v-bind:key="book.book_title"
            v-bind:index='index'
          ></search-book-detail >
        </div><br>
        <div v-if="authorTotalRows > 0">
          <b-pagination
            pills
            v-model="authorCurrentPage"
            :total-rows="authorTotalRows"
            :per-page="7"
            align="center"
          ></b-pagination><!-- @page-click="pageClick" -->
        </div>
      </div>
      <div v-if="item.authorData.length==0">
        <h5 style="text-align: center; ">😥검색된 결과가 없습니다.</h5>
      </div>
      <br><br>
      <h4> #도서 설명</h4><br>
      <div v-if="item.contentsData!=null">
        <div style="display : flex">
          <search-book-detail  
            v-for="(book,index) in contentsList" 
            v-bind:book="book" 
            v-bind:key="book.book_title"
            v-bind:index='index'
          ></search-book-detail >
        </div><br>
        <div v-if="contentsTotalRows > 0">
          <b-pagination
            pills
            v-model="contentsCurrentPage"
            :total-rows="contentsTotalRows"
            :per-page="7"
            align="center"
          ></b-pagination><!-- @page-click="pageClick" -->
        </div>
      </div>
      <div v-if="item.contentsData.length==0">
        <h5 style="text-align: center; ">😥검색된 결과가 없습니다.</h5>
      </div>
      
    </div>
  </div>
</template>

<script>
import SearchBookDetail from './SearchBookDetail.vue'

export default {
  components: { 
    SearchBookDetail,

  },
  props: {
    item: {
      titleData : null,
      authorData : null,
      contentsData : null,
    },
  },
  data() {
    return {
      titlePage : 1,
      titleCurrentPage : 1,

      authorPage : 1,
      authorCurrentPage : 1,

      contentsPage : 1,
      contentsCurrentPage : 1,
    }
  },
  computed : {
    //도서명
    titleList: function() {
      if(this.item.titleData.length===0){
        return null;
      }
      return this.item.titleData.slice(7*(this.titleCurrentPage-1), 7*this.titleCurrentPage)
    },
    titleTotalRows : function() {
      return this.item.titleData.length;
    },
    //작가명
    authorList: function() {
      if(this.item.authorData.length===0){
        return null;
      }
      return this.item.authorData.slice(7*(this.authorCurrentPage-1), 7*this.authorCurrentPage)
    },
    authorTotalRows : function() {
      return this.item.authorData.length;
    },
    //도서 설명
    contentsList: function() {
      if(this.item.contentsData.length===0){
        return null;
      }
      return this.item.contentsData.slice(7*(this.contentsCurrentPage-1), 7*this.contentsCurrentPage)
    },
    contentsTotalRows : function() {
      return this.item.contentsData.length;
    }

  },
}
</script>

<style>

</style>