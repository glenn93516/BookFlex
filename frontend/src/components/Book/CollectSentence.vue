<template>
  <div>
    <div class="head">
      <h2 v-if="!mode">문장담기</h2>
      <h2 v-else-if="mode">문장 수정하기</h2>
    </div>
    <!-- <img class="collect-img" :src="book.book_cover" alt=""> -->
    <div>
      <b-card no-body class="overflow-hidden" style="max-width: 540px;">
        <b-row no-gutters>
          <b-col md="6">
            <b-card-img :src="book.book_cover" alt="Image" class="rounded-0"></b-card-img>
          </b-col>
          <b-col md="6">
            <b-card-body :title="book.book_title">
              <h5>{{ book.book_author }}</h5>
              <h6>{{ book.book_date }}</h6>
              <b-card-text :text="book.book_contents">
                {{ book.book_contents }}
              </b-card-text>
            </b-card-body>
          </b-col>
        </b-row>
      </b-card>
      <!-- 사진 -->
      <h4>사진 업로드</h4>
      <b-form-file
        v-model="file"
        :state="Boolean(file)"
        placeholder="Choose a file or drop it here..."
        drop-placeholder="Drop file here..."
      ></b-form-file>
      <div class="mt-3">Selected file: {{ file ? file.name : '' }}</div>
      



      <button @click="collectSentence">문장 담기</button>

    </div>
  </div>
</template>
<script>
// props로 인자를 받아 Create(0), Update(1)역할을 결정한다.
export default {
  props: {
    mode: Number,
    book: Object,
  },
  components: {
  },
  data() {
    return {
      file: null,
    }
  },
  created() {
  }, 
  methods: {
    collectSentence() {
      // 토큰, isbn, 문장 수집 내용(text), 이미지 파일, 책 페이지, 공개 여부
      // const token = localStorage.getItem('jwt')
      // const isbn = this.book.book_isbn
      // const text = 

    }
  },
}
</script>

<style>
  .head {
    font-weight: bold;
  }
  .collect-img {
    
  }
</style>