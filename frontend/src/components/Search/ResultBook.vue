<template>
  <div>
    <h2><strong>검색 결과 - BOOK</strong></h2>
    <div v-if="item.titleData==null&&item.authorData==null&&item.contentsData==null" style="margin-bottom:5px;">
      <h4 style="text-align: center; ">😥검색된 결과가 없습니다.</h4>
    </div>
    <div v-if="item.titleData!=null||item.authorData!=null||item.contentsData!=null">
      <br><h4>도서명 검색 결과</h4><br>
      <div v-if="item.titleData!=null">
       {{item.titleData}}
      </div>
      <div v-if="item.titleData==null">
        <h5 style="text-align: center; ">😥검색된 결과가 없습니다.</h5>
      </div>
      <br><br>
      <h4>작가명 검색 결과</h4><br>
      <div v-if="item.authorData.length>0">
        <book-detail 
          v-for="(book,index) in item.authorData" 
          v-bind:book="book" 
          v-bind:key="book.book_title"
          v-bind:index = "index"
        ></book-detail>
      </div>
      <div v-if="item.authorData==null">
        <h5 style="text-align: center; ">😥검색된 결과가 없습니다.</h5>
      </div>
      <br><br>
      <h4>내용 검색 결과</h4><br>
      <div v-if="item.contentsData!=null">
        {{item.contentsData}}
      </div>
      <div v-if="item.contentsData==null">
        <h5 style="text-align: center; ">😥검색된 결과가 없습니다.</h5>
      </div>
      
    </div>
  </div>
</template>

<script>
import BookDetail from './BookDetail.vue'
export default {
  components: { BookDetail },
  props: {
    item: null,
  },
  data() {
    return {
      authorData : null,
      titleData : null,
      contentsData : null
    }
  },
  computed(){
    // this.authorData = item.authorData;
    // this.titleData = item.titleData;
    // this.contentsData = item.contentsData;
  }
}
</script>

<style>

</style>