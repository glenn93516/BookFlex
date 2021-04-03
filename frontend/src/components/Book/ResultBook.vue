<template>
  <div>
    <h2><strong>검색 결과 - BOOK</strong></h2>
    <div v-if="item.titleData.length==0&&item.authorData.length==0&&item.contentsData.length==0" style="margin-bottom:5px;">
      <h4 style="text-align: center; ">😥검색된 결과가 없습니다.</h4>
    </div>
    <div v-if="item.titleData.length > 0||item.authorData.length > 0||item.contentsData.length > 0">
      <br><h4> #도서명</h4><br>
      <div v-if="item.titleData!=null" style="display : flex">
        <book-detail 
          v-for="(book,index) in item.titleData" 
          v-bind:book="book" 
          v-bind:key="book.book_title"
          v-bind:index='index'
        ></book-detail>
      </div>
      <div v-if="item.titleData.length==0">
        <h5 style="text-align: center; ">😥검색된 결과가 없습니다.</h5>
      </div>
      <br><br>
      <h4> #작가명</h4><br>
      <div v-if="item.authorData!=null" style="display : flex">
        <book-detail 
          v-for="(book,index) in item.authorData" 
          v-bind:book="book" 
          v-bind:key="book.book_title"
          v-bind:index='index'
        ></book-detail>
      </div>
      <div v-if="item.authorData.length==0">
        <h5 style="text-align: center; ">😥검색된 결과가 없습니다.</h5>
      </div>
      <br><br>
      <h4> #도서 설명</h4><br>
      <div v-if="item.contentsData!=null" style="display : flex">
        <book-detail 
          v-for="(book,index) in item.contentsData" 
          v-bind:book="book" 
          v-bind:key="book.book_title"
          v-bind:index='index'
        ></book-detail>
      </div>
      <div v-if="item.contentsData.length==0">
        <h5 style="text-align: center; ">😥검색된 결과가 없습니다.</h5>
      </div>
      
    </div>
  </div>
</template>

<script>
import BookDetail from './BookDetail.vue'

export default {
  components: { 
    BookDetail,

  },
  props: {
    item: {
      titleData : [],
      authorData : [],
      contentsData : [],
    },
  },
  data() {
    return {
    }
  },
  computed(){

  },
  methods: {
  },
}
</script>

<style>

</style>