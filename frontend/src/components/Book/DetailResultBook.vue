<template>
  <div>
    <div v-if="item.titleData.length==0&&item.authorData.length==0&&item.contentsData.length==0">
      <h4 style="text-align: center; margin-top: 30px;">검색 결과가 없습니다.</h4>
    </div>
    <div v-if="item.titleData.length > 0||item.authorData.length > 0||item.contentsData.length > 0">
      
      <h5 class="mb-2"> 도서명</h5>
      <div v-if="item.titleData!=null">
        <div style="display : flex">
          <book-detail 
            v-for="(book, idx) in titleList" 
            :book="book" 
            :key="idx"
            @setIsbn="setIsbn(book.book_isbn)"
          ></book-detail>
        </div>
        <div v-if="item.titleData.length > 0" class="d-flex justify-content-center">
          <b-pagination
            pills
            v-model="titleCurrentPage"
            :total-rows="titleTotalRows"
            :per-page="perPage"
          >
            <template #first-text class="bg-success"><span class="text-success bg-success">처음</span></template>
            <template #last-text class="bg-success"><span class="text-success bg-success">끝</span></template>
            <template #prev-text>이전</template>
            <template #next-text>다음</template>
          </b-pagination>
        </div>
      </div>
      <div v-if="item.titleData.length===0">
        <h5 style="text-align: center; ">검색 결과가 없습니다.</h5>
      </div>
      
      <h5 class="mb-2"> 작가명</h5>
      <div v-if="item.authorData!=null">
        <div style="display : flex">
          <book-detail 
            v-for="(book,idx) in authorList" 
            :book="book" 
            :key="idx"
            @setIsbn="setIsbn(book.book_isbn)"
          ></book-detail>
        </div>
        <div v-if="authorTotalRows > 0" class="d-flex justify-content-center">
          <b-pagination
            pills
            v-model="authorCurrentPage"
            :total-rows="authorTotalRows"
            :per-page="perPage"
          >
            <template #first-text class="bg-success"><span class="text-success bg-success">처음</span></template>
            <template #last-text class="bg-success"><span class="text-success bg-success">끝</span></template>
            <template #prev-text>이전</template>
            <template #next-text>다음</template>
          </b-pagination>
        </div>
      </div>
      <div v-if="item.authorData.length==0">
        <h5 style="text-align: center; ">검색 결과가 없습니다.</h5>
      </div>
      
      <h5 class="mb-2"> 도서 설명</h5>
      <div v-if="item.contentsData!=null">
        <div style="display : flex">
          <book-detail 
            v-for="(book,idx) in contentsList" 
            :book="book" 
            :key="idx"
            @setIsbn="setIsbn(book.book_isbn)"
          ></book-detail>
        </div>
        <div v-if="contentsTotalRows > 0" class="d-flex justify-content-center">
          <b-pagination
            pills
            v-model="contentsCurrentPage"
            :total-rows="contentsTotalRows"
            :per-page="perPage"
          >
            <template #first-text class="bg-success"><span class="text-success bg-success">처음</span></template>
            <template #last-text class="bg-success"><span class="text-success bg-success">끝</span></template>
            <template #prev-text>이전</template>
            <template #next-text>다음</template>
          </b-pagination>
        </div>
      </div>
      <div v-if="item.contentsData.length==0">
        <h5 style="text-align: center; ">검색 결과가 없습니다.</h5>
      </div>
      
    </div>
  </div>
</template>

<script>
import BookDetail from './DetailBookImg.vue'

export default {
  components: { 
    BookDetail,
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
      perPage: 5,
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
  methods: {
    setIsbn(isbn) {
      this.$emit("setIsbn", isbn)
    },
  }
}
</script>

<style scope>
.pagination li button {
  color: black;
  background-color: rgba(0, 0, 0, 0);
  border: 0px;
}
.page-item.active .page-link {
  background-color: rgb(192, 180, 165);
  color: white;
  border-color: rgb(192, 180, 165);
}
</style>