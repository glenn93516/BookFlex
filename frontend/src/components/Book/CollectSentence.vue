<template>
  <div >
    <div class="head mt-4 pl-5" style="text-align: center;">
      <h2 v-if="!mode">문장담기</h2>
      <h2 v-else-if="mode">문장 수정하기</h2>
    </div>
    <hr class="mt-3 mb-0 mr-3 ml-3" style="background-color: grey;">
    <br>
    <!-- <img class="collect-img" :src="book.book_cover" alt=""> -->
    <div>
      <div style="float: left;" class="col-6">
        <div class="card bg-dark text-white">
          <img :src="book.book_cover" class="card-img collect_img" :alt="book.book_title">
          <div class="card-img-overlay collect_card">
            <img style="width: 40px; height: 40px; float: right;" src="data:image/svg+xml;base64,PHN2ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHZlcnNpb249IjEuMSIgeG1sbnM6eGxpbms9Imh0dHA6Ly93d3cudzMub3JnLzE5OTkveGxpbmsiIHhtbG5zOnN2Z2pzPSJodHRwOi8vc3ZnanMuY29tL3N2Z2pzIiB3aWR0aD0iNTEyIiBoZWlnaHQ9IjUxMiIgeD0iMCIgeT0iMCIgdmlld0JveD0iMCAwIDUxMiA1MTIiIHN0eWxlPSJlbmFibGUtYmFja2dyb3VuZDpuZXcgMCAwIDUxMiA1MTIiIHhtbDpzcGFjZT0icHJlc2VydmUiIGNsYXNzPSIiPjxnPgo8bGluZWFyR3JhZGllbnQgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIiBpZD0iU1ZHSURfMV8iIGdyYWRpZW50VW5pdHM9InVzZXJTcGFjZU9uVXNlIiB4MT0iMzYiIHkxPSIyNTgiIHgyPSI0NzYiIHkyPSIyNTgiIGdyYWRpZW50VHJhbnNmb3JtPSJtYXRyaXgoMSAwIDAgLTEgMCA1MTQpIj4KCTxzdG9wIG9mZnNldD0iMCIgc3R5bGU9InN0b3AtY29sb3I6IzgwRDhGRiI+PC9zdG9wPgoJPHN0b3Agb2Zmc2V0PSIxIiBzdHlsZT0ic3RvcC1jb2xvcjojRUE4MEZDIj48L3N0b3A+CjwvbGluZWFyR3JhZGllbnQ+CjxwYXRoIHhtbG5zPSJodHRwOi8vd3d3LnczLm9yZy8yMDAwL3N2ZyIgc3R5bGU9ImZpbGw6dXJsKCNTVkdJRF8xXyk7IiBkPSJNMjU2LDUxMmMtNi42MywwLTEyLjgyOS0zLjI4NS0xNi41NTEtOC43NzFsLTUwLjAzMi03My43NDcgIEMxMDEuMzMzLDQwMS40OTksMzYsMzE5LjEwNCwzNiwyMTkuODczQzM2LDk4LjM0OCwxMzQuMzk4LDAsMjU2LDBjMTIxLjUyMywwLDIyMCw5OC4yNzEsMjIwLDIxOS44NzMgIGMwLDk5LjE4OC02NS4yOTQsMTgxLjYxMy0xNTMuNDE3LDIwOS42MDlsLTUwLjAzMiw3My43NDdDMjY4LjgyOSw1MDguNzE1LDI2Mi42Myw1MTIsMjU2LDUxMnogTTI1Niw0MCAgYy05OS4yNTIsMC0xODAsODAuNjktMTgwLDE3OS44NzNjMCw4My4zMDgsNTYuMzc3LDE1Mi4yNjIsMTMxLjM1NSwxNzMuMjI3YzQuNTU1LDEuMjczLDguNTEsNC4xMiwxMS4xNjYsOC4wMzNMMjU2LDQ1Ni4zNzYgIGwzNy40OC01NS4yNDRjMi42NTUtMy45MTMsNi42MS02Ljc1OSwxMS4xNjUtOC4wMzNDMzc5LjU0LDM3Mi4xNTgsNDM2LDMwMy4yNjQsNDM2LDIxOS44NzNDNDM2LDEyMC42OSwzNTUuMjUyLDQwLDI1Niw0MHogICBNMjc4Ljg5NCwxMzYuNDI2YzAsMTAuNzM5LTEwLjE3NCwyMC4zNDgtMjIuODkyLDIwLjM0OGMtMTMsMC0yMy4xNzQtOS42MDktMjMuMTc0LTIwLjM0OHMxMC4xNzQtMjAuMDY1LDIzLjE3NC0yMC4wNjUgIEMyNjguNzE5LDExNi4zNjEsMjc4Ljg5NCwxMjUuNjg4LDI3OC44OTQsMTM2LjQyNnogTTI3OC4wNDUsMTk2LjA1N1YzMDMuNzNjMCw2Ljc4Mi05Ljg5MSwxMy41NjUtMjIuMDQzLDEzLjU2NSAgYy0xMi43MTcsMC0yMS43NjEtNi43ODMtMjEuNzYxLTEzLjU2NVYxOTYuMDU3YzAtNy45MTMsOS4wNDMtMTMuMjgzLDIxLjc2MS0xMy4yODMgIEMyNjguMTU0LDE4Mi43NzQsMjc4LjA0NSwxODguMTQ0LDI3OC4wNDUsMTk2LjA1N3oiIGZpbGw9IiI+PC9wYXRoPgo8ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciPgo8L2c+CjxnIHhtbG5zPSJodHRwOi8vd3d3LnczLm9yZy8yMDAwL3N2ZyI+CjwvZz4KPGcgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIj4KPC9nPgo8ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciPgo8L2c+CjxnIHhtbG5zPSJodHRwOi8vd3d3LnczLm9yZy8yMDAwL3N2ZyI+CjwvZz4KPGcgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIj4KPC9nPgo8ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciPgo8L2c+CjxnIHhtbG5zPSJodHRwOi8vd3d3LnczLm9yZy8yMDAwL3N2ZyI+CjwvZz4KPGcgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIj4KPC9nPgo8ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciPgo8L2c+CjxnIHhtbG5zPSJodHRwOi8vd3d3LnczLm9yZy8yMDAwL3N2ZyI+CjwvZz4KPGcgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIj4KPC9nPgo8ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciPgo8L2c+CjxnIHhtbG5zPSJodHRwOi8vd3d3LnczLm9yZy8yMDAwL3N2ZyI+CjwvZz4KPGcgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIj4KPC9nPgo8L2c+PC9zdmc+" />
            <h2 class="card-title">{{ book.book_title }}</h2>
            <br>
            <p class="card-text">{{ book.book_author }}</p>
            <br>
            <p class="card-text">{{ book.book_publisher }}</p>
          </div>
        </div>
      </div>
      <!-- 사진 -->
      <div style="float: right;" class="col-6">
        <h4>문장을 모아보세요!</h4>
        <hr class="mt-2 mb-2" style="background-color: white;">
        <p style="font-size: 18px;">
          책을 읽으셨다면 마음에 드는 문장 한줄을 
          <br>
          기억속에 남겨보세요.
          <br>
          문장을 모으고 기록하고 공유해보세요.
          <br>
          아름다운 카드와 글귀로 책을 기억속에 간직한다면
          <br>
          책을 읽었던 시간이 더 가치있게 보관될 수 있을거에요.
        </p>

        <label for="textarea" class="mb-0" style="margin-top: 80px;">문장 기록하기</label>
        <hr class="mt-0 mb-2" style="background-color: white;">
        <b-form-textarea
          id="textarea"
          v-model="text"
          placeholder="간직할 문장을 이곳에 담아주세요"
          rows="3"
          max-rows="6"
        ></b-form-textarea>
        
        <label for="sentence-img" class="mb-0 mt-3">사진 선택하기</label>
        <hr class="mt-0 mb-2" style="background-color: white;">
        <b-form-file
          id="sentence-img"
          v-model="file"
          :state="Boolean(file)"
          placeholder="파일을 선택하거나 끌어서 넣어주세요"
          drop-placeholder="사진 담을 곳"
        >
        <b-button type="submit" for="sentence-img">사진 찾기</b-button>
        </b-form-file>
          <div class="mt-3" style="display: inline-block; margin-right: 50px;">
            <label for="sentencePage" class="mt-3" style="font-size: 18px; margin-right: 5px; width: 70px;">페이지 : </label>
            <input id="sentencePage" class="form-control" type="number" min="0" v-model="sentencePage">
          </div>
          <b-form-radio-group
            v-model="selected"
            :options="options"
            value-field="item"
            text-field="name"
            disabled-field="notEnabled"
            style="margin-top: 20px; display: inline-block; font-size: 18px;"
          ></b-form-radio-group>
        <div>
          <b-button class="mt-4 collect_btn col-12" @click="collectSentence">문장 담기</b-button>
        </div>
      </div>
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
      text: "",
      sentencePage: 0,
      options: [
        { item: 1, name: "공개" },
        { item: 0, name: "비공개" }
      ],
      selected: 1,
    }
  },
  created() {
    // console.log(this.book)
  }, 
  methods: {
    collectSentence() {
      // 토큰, isbn, 문장 수집 내용(text), 이미지 파일, 책 페이지, 공개 여부
      if (!this.file || !this.text || !this.sentencePage) {
        alert("카드를 채워주세요!!")
      } else {
        const token = localStorage.getItem('jwt')
        const headers = {
          "Authorization": token
        }
        let data = new FormData()
        data.append('bookIsbn', this.book.book_isbn)
        data.append('highlightContent', this.text)
        data.append('highlightCover', this.file)
        data.append('highlightPage', this.sentencePage)
        data.append('isPublic', this.selected)
        this.$axios.post(`${this.$store.getters.getServer}/highlight`, data, 
        {
          headers
        })
        .then(() => {
          // console.log(res)
          alert('문장이 잘 담겼어요 :)')
          this.text = ""
          this.sentencePage = 0
          this.file = null
          this.$emit('close-modal')
        })
        .catch(err => {
          console.error(err)
        })
      }
    },
  },
}
</script>

<style>
  .head {
    font-weight: bold;
  }
  .collect_img {
    height: 600px;
    border: 0px;
  }
  .collect_card {
    color: rgba(0, 0, 0, 0);
    background-color: rgba(0, 0, 0, 0);
    padding-top: 30px;
  }
  .collect_card:hover {
    color: white;
    background-color: rgba(0, 0, 0, 0.5);
    transition: 0.2s;
  }
  .collect_btn {
    background-color: rgb(255, 230, 194);
    color: rgb(77, 64, 42);
    font-weight: bold;
    border: 0px;
    height: 45px;
  }
  .collect_btn:hover {
    background-color: rgb(134, 134, 134);
  }
  #sentencePage {
    display: inline-block;
    width: 100px;
  }
</style>