<template>
  <div>
    <div :class="size" class="write-container">
      <!-- header -->
      <header>
        <div class="write-header">
          <img 
            class="mouse-pointer"
            width="25px"
            @mouseleave="backIcon='data:image/svg+xml;base64,PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iaXNvLTg4NTktMSI/Pg0KPCEtLSBHZW5lcmF0b3I6IEFkb2JlIElsbHVzdHJhdG9yIDE5LjAuMCwgU1ZHIEV4cG9ydCBQbHVnLUluIC4gU1ZHIFZlcnNpb246IDYuMDAgQnVpbGQgMCkgIC0tPg0KPHN2ZyB2ZXJzaW9uPSIxLjEiIGlkPSJMYXllcl8xIiB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHhtbG5zOnhsaW5rPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5L3hsaW5rIiB4PSIwcHgiIHk9IjBweCINCgkgdmlld0JveD0iMCAwIDUxMiA1MTIiIHN0eWxlPSJlbmFibGUtYmFja2dyb3VuZDpuZXcgMCAwIDUxMiA1MTI7IiB4bWw6c3BhY2U9InByZXNlcnZlIj4NCjxnPg0KCTxnPg0KCQk8Zz4NCgkJCTxwYXRoIGQ9Ik0yNTYsMEMxMTQuNjE4LDAsMCwxMTQuNjE4LDAsMjU2czExNC42MTgsMjU2LDI1NiwyNTZzMjU2LTExNC42MTgsMjU2LTI1NlMzOTcuMzgyLDAsMjU2LDB6IE0yNTYsNDY5LjMzMw0KCQkJCWMtMTE3LjgxOCwwLTIxMy4zMzMtOTUuNTE1LTIxMy4zMzMtMjEzLjMzM1MxMzguMTgyLDQyLjY2NywyNTYsNDIuNjY3UzQ2OS4zMzMsMTM4LjE4Miw0NjkuMzMzLDI1NlMzNzMuODE4LDQ2OS4zMzMsMjU2LDQ2OS4zMzMNCgkJCQl6Ii8+DQoJCQk8cGF0aCBkPSJNMzEzLjc1MiwxMzQuMjQ4Yy04LjMzMS04LjMzMS0yMS44MzktOC4zMzEtMzAuMTcsMEwxNzYuOTE1LDI0MC45MTVjLTguMzMxLDguMzMxLTguMzMxLDIxLjgzOSwwLDMwLjE3bDEwNi42NjcsMTA2LjY2Nw0KCQkJCWM4LjMzMSw4LjMzMSwyMS44MzksOC4zMzEsMzAuMTcsMGM4LjMzMS04LjMzMSw4LjMzMS0yMS44MzksMC0zMC4xN0wyMjIuMTcsMjU2bDkxLjU4Mi05MS41ODINCgkJCQlDMzIyLjA4MywxNTYuMDg3LDMyMi4wODMsMTQyLjU4LDMxMy43NTIsMTM0LjI0OHoiLz4NCgkJPC9nPg0KCTwvZz4NCjwvZz4NCjxnPg0KPC9nPg0KPGc+DQo8L2c+DQo8Zz4NCjwvZz4NCjxnPg0KPC9nPg0KPGc+DQo8L2c+DQo8Zz4NCjwvZz4NCjxnPg0KPC9nPg0KPGc+DQo8L2c+DQo8Zz4NCjwvZz4NCjxnPg0KPC9nPg0KPGc+DQo8L2c+DQo8Zz4NCjwvZz4NCjxnPg0KPC9nPg0KPGc+DQo8L2c+DQo8Zz4NCjwvZz4NCjwvc3ZnPg0K'"
            @mouseover="backIcon='data:image/png;base64,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'"
            :src="backIcon"
            @click="goToReceived"
          />
          <h1 
            class="write-h1 ml-2"
          >
            쪽지 쓰기
          </h1>
        </div>
      </header>
      
      <!-- 받은메세지 리스트 -->
      <!-- body -->
      <body class="write-body">
        <hr class="my-hr2">

        <div class="write-receiver">
          <span style="width:100px;">받는 사람:</span>
          <div class='tag-input'>
            <div v-for='(tag, idx) in tags' :key='idx' class='tag-input__tag'>
              <span @click='removeTag(index)'>x</span>
              {{ tag.userNickname }}
            </div>
            <input 
              type='text' 
              placeholder="Enter" 
              class='tag-input__text' 
              @keydown.enter='addTag' 
              @keydown.188='addTag'
              @keydown.delete='removeLastTag'
            />
          </div>
        </div>
        
        <div>
          <b-form-textarea
            id="textarea-rows"
            rows="11"
            placeholder=""
            class="write-content"
            v-model="content"
          ></b-form-textarea>
        </div>
      </body>

      <!-- footer -->
      <footer>
        <b-button 
          block
          class="mt-3 btn-warning write-button" 
          @click="sendMessages"
        >
          <img 
            width="20px" 
            src="data:image/svg+xml;base64,PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iaXNvLTg4NTktMSI/Pg0KPCEtLSBHZW5lcmF0b3I6IEFkb2JlIElsbHVzdHJhdG9yIDE5LjAuMCwgU1ZHIEV4cG9ydCBQbHVnLUluIC4gU1ZHIFZlcnNpb246IDYuMDAgQnVpbGQgMCkgIC0tPg0KPHN2ZyB2ZXJzaW9uPSIxLjEiIGlkPSJDYXBhXzEiIHhtbG5zPSJodHRwOi8vd3d3LnczLm9yZy8yMDAwL3N2ZyIgeG1sbnM6eGxpbms9Imh0dHA6Ly93d3cudzMub3JnLzE5OTkveGxpbmsiIHg9IjBweCIgeT0iMHB4Ig0KCSB2aWV3Qm94PSIwIDAgNTEyLjAwMiA1MTIuMDAyIiBzdHlsZT0iZW5hYmxlLWJhY2tncm91bmQ6bmV3IDAgMCA1MTIuMDAyIDUxMi4wMDI7IiB4bWw6c3BhY2U9InByZXNlcnZlIj4NCjxnPg0KCTxwYXRoIHN0eWxlPSJmaWxsOiNGRkJENTE7IiBkPSJNNDk1LjA0MSw5MS42MjhjNC4wNTIsMy45NTIsNi4zMjMsOS4zOCw2LjI5MywxNS4wNHYyOTguNjY3YzAuMDMsNS42Ni0yLjI0MSwxMS4wODgtNi4yOTMsMTUuMDQNCgkJbC00Ny4wNC00Ny4wNEwzMTAuMTg4LDIzNS41MjFsMTgwLjQ4LTEzOS41Mkw0OTUuMDQxLDkxLjYyOHoiLz4NCgk8cGF0aCBzdHlsZT0iZmlsbDojRkZCRDUxOyIgZD0iTTY0LjAwMSwzNzMuMzM0bC00Ni45MzMsNDYuOTMzbC0wLjEwNywwLjEwN2MtNC4wNTItMy45NTItNi4zMjMtOS4zOC02LjI5My0xNS4wNFYxMDYuNjY4DQoJCWMtMC4wMjctNC4yNTcsMS4yMzgtOC40MjMsMy42MjctMTEuOTQ3bDcuMDQsMS4yOGwxODAuNDgsMTM5LjUyTDY0LjAwMSwzNzMuMzM0eiIvPg0KPC9nPg0KPGc+DQoJPHBhdGggc3R5bGU9ImZpbGw6I0ZGRTQ1MTsiIGQ9Ik0xNy4wNjgsNDIwLjI2OGw0Ni45MzMtNDYuOTMzbDEzNy44MTMtMTM3LjgxM2w1NC4xODcsNDEuODEzbDU0LjE4Ny00MS44MTNsMTM3LjgxMywxMzcuODEzDQoJCWw0Ny4wNCw0Ny4wNGMtMy45NTIsNC4wNTItOS4zOCw2LjMyMy0xNS4wNCw2LjI5M2gtNDQ4Yy01LjY2LDAuMDMtMTEuMDg4LTIuMjQxLTE1LjA0LTYuMjkzTDE3LjA2OCw0MjAuMjY4eiIvPg0KCTxwYXRoIHN0eWxlPSJmaWxsOiNGRkU0NTE7IiBkPSJNNDgwLjAwMSw4NS4zMzRjNS42Ni0wLjAzLDExLjA4OCwyLjI0MiwxNS4wNCw2LjI5M2wtNC4zNzMsNC4zNzNsLTE4MC40OCwxMzkuNTJsLTU0LjE4Nyw0MS44MTMNCgkJbC01NC4xODctNDEuODEzTDIxLjMzNCw5Ni4wMDFsLTcuMDQtMS4yOGMzLjk4LTUuODcyLDEwLjYxMy05LjM4OCwxNy43MDctOS4zODdINDgwLjAwMXoiLz4NCjwvZz4NCjxwYXRoIHN0eWxlPSJmaWxsOiM0QzVCNjY7IiBkPSJNNTAyLjY3OCw4NC4xODJjLTUuOTUxLTYuMTIzLTE0LjEzOS05LjU1OC0yMi42NzctOS41MTVoLTQ0OA0KCWMtMTAuNjIzLTAuMDA5LTIwLjU1OCw1LjI1NC0yNi41MTcsMTQuMDQ4Yy0zLjYwMiw1LjI5MS01LjUxNCwxMS41NTEtNS40ODMsMTcuOTUydjI5OC42NjdjLTAuMDY5LDguNDQ5LDMuMjk2LDE2LjU2NCw5LjMyMywyMi40ODUNCgljNS45NTEsNi4xMjMsMTQuMTM5LDkuNTU4LDIyLjY3Nyw5LjUxNWg0NDhjMTcuNjMyLTAuMDk5LDMxLjkwMS0xNC4zNjgsMzItMzJWMTA2LjY2OEM1MTIuMDcsOTguMjE5LDUwOC43MDUsOTAuMTA0LDUwMi42NzgsODQuMTgyDQoJeiBNMjU2LjAwMSwyNjMuODYyTDM4Ljc3NCw5Ni4wMDFoNDM0LjQ1M0wyNTYuMDAxLDI2My44NjJ6IE00ODcuNDA0LDQxMi45MjljLTEuOTQ2LDEuOTktNC42MiwzLjEtNy40MDMsMy4wNzJoLTQ0OA0KCWMtMi44NzcsMC4wMDctNS42MjktMS4xNzQtNy42MDUtMy4yNjRjLTEuOTc4LTEuOTUzLTMuMDgyLTQuNjIzLTMuMDYxLTcuNDAzVjEwOS40NzNsMTY0LjQwNSwxMjcuMDRMNTYuNDYsMzY1Ljc5M2wxNS4wODMsMTUuMDgzDQoJbDEzMS4yLTEzMS4ybDQ2LjczMSwzNi4xMTdjMy44NCwyLjk2NCw5LjE5NSwyLjk2NCwxMy4wMzUsMGw0Ni43MzEtMzYuMTE3bDEzMS4yLDEzMS4ybDE1LjA4My0xNS4wODNsLTEyOS4yOC0xMjkuMjgNCglsMTY0LjQyNy0xMjcuMDR2Mjk1Ljg2MUM0OTAuNjcyLDQwOC4yMDcsNDg5LjQ5MSw0MTAuOTU1LDQ4Ny40MDQsNDEyLjkyOUw0ODcuNDA0LDQxMi45Mjl6Ii8+DQo8Zz4NCjwvZz4NCjxnPg0KPC9nPg0KPGc+DQo8L2c+DQo8Zz4NCjwvZz4NCjxnPg0KPC9nPg0KPGc+DQo8L2c+DQo8Zz4NCjwvZz4NCjxnPg0KPC9nPg0KPGc+DQo8L2c+DQo8Zz4NCjwvZz4NCjxnPg0KPC9nPg0KPGc+DQo8L2c+DQo8Zz4NCjwvZz4NCjxnPg0KPC9nPg0KPGc+DQo8L2c+DQo8L3N2Zz4NCg==" 
          />
          전송하기
        </b-button>

        <modal v-if="showModal">
          <div class="modal-mask">
            <div class="modal-wrapper">
              <div class="modal-container">

                <div class="modal_header" >
                  <b-button 
                    variant="light"
                    @click="showModal = false"
                  >
                    X
                  </b-button>
                </div>

                <div class="modal_body">
                  <div style="font-size:30px; font-weight: bold;">존재하지 않는 닉네임입니다!</div>
                  <div style="color:red;">다시 한 번 확인해봐주시기 바랍니다.</div>
                </div>

                <div class="modal_footer">
                  <b-button 
                    class="modal-button" 
                    variant="primary"
                  >
                  확인
                  </b-button>
                  <b-button 
                    class="modal-button"
                    variant="danger"
                    @click="showModal = false"
                    >
                    닫기
                    </b-button>
                </div>

              </div>
            </div>
          </div>
        </modal>
      </footer>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      showModal: false,
      backIcon: "data:image/svg+xml;base64,PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iaXNvLTg4NTktMSI/Pg0KPCEtLSBHZW5lcmF0b3I6IEFkb2JlIElsbHVzdHJhdG9yIDE5LjAuMCwgU1ZHIEV4cG9ydCBQbHVnLUluIC4gU1ZHIFZlcnNpb246IDYuMDAgQnVpbGQgMCkgIC0tPg0KPHN2ZyB2ZXJzaW9uPSIxLjEiIGlkPSJMYXllcl8xIiB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHhtbG5zOnhsaW5rPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5L3hsaW5rIiB4PSIwcHgiIHk9IjBweCINCgkgdmlld0JveD0iMCAwIDUxMiA1MTIiIHN0eWxlPSJlbmFibGUtYmFja2dyb3VuZDpuZXcgMCAwIDUxMiA1MTI7IiB4bWw6c3BhY2U9InByZXNlcnZlIj4NCjxnPg0KCTxnPg0KCQk8Zz4NCgkJCTxwYXRoIGQ9Ik0yNTYsMEMxMTQuNjE4LDAsMCwxMTQuNjE4LDAsMjU2czExNC42MTgsMjU2LDI1NiwyNTZzMjU2LTExNC42MTgsMjU2LTI1NlMzOTcuMzgyLDAsMjU2LDB6IE0yNTYsNDY5LjMzMw0KCQkJCWMtMTE3LjgxOCwwLTIxMy4zMzMtOTUuNTE1LTIxMy4zMzMtMjEzLjMzM1MxMzguMTgyLDQyLjY2NywyNTYsNDIuNjY3UzQ2OS4zMzMsMTM4LjE4Miw0NjkuMzMzLDI1NlMzNzMuODE4LDQ2OS4zMzMsMjU2LDQ2OS4zMzMNCgkJCQl6Ii8+DQoJCQk8cGF0aCBkPSJNMzEzLjc1MiwxMzQuMjQ4Yy04LjMzMS04LjMzMS0yMS44MzktOC4zMzEtMzAuMTcsMEwxNzYuOTE1LDI0MC45MTVjLTguMzMxLDguMzMxLTguMzMxLDIxLjgzOSwwLDMwLjE3bDEwNi42NjcsMTA2LjY2Nw0KCQkJCWM4LjMzMSw4LjMzMSwyMS44MzksOC4zMzEsMzAuMTcsMGM4LjMzMS04LjMzMSw4LjMzMS0yMS44MzksMC0zMC4xN0wyMjIuMTcsMjU2bDkxLjU4Mi05MS41ODINCgkJCQlDMzIyLjA4MywxNTYuMDg3LDMyMi4wODMsMTQyLjU4LDMxMy43NTIsMTM0LjI0OHoiLz4NCgkJPC9nPg0KCTwvZz4NCjwvZz4NCjxnPg0KPC9nPg0KPGc+DQo8L2c+DQo8Zz4NCjwvZz4NCjxnPg0KPC9nPg0KPGc+DQo8L2c+DQo8Zz4NCjwvZz4NCjxnPg0KPC9nPg0KPGc+DQo8L2c+DQo8Zz4NCjwvZz4NCjxnPg0KPC9nPg0KPGc+DQo8L2c+DQo8Zz4NCjwvZz4NCjxnPg0KPC9nPg0KPGc+DQo8L2c+DQo8Zz4NCjwvZz4NCjwvc3ZnPg0K",
      size: "back-lg",
      content: "",
      tags: [],
    }
  },
  created() {
  },
  watch: {
  },
  methods: {
    sendMessages() {
      const headers = {
        "Authorization": localStorage.getItem('jwt')
      }
      this.tags.forEach(e => {
        const message = {
          "receiverId": e.userId,
          // "receiverNickname": "string",
          "senderId": this.$store.getters.getUser.userId,
          "sendmsgContent": this.content,
        }
        console.log(message)
        this.$axios.post(`${this.$store.getters.getServer}/sendmsg`,message,{headers})
      })

      this.goToSent()
    },
    addTag (event) {
      event.preventDefault()
      const nickname = event.target.value.trim()
      if (nickname.length > 0) {
        
        const token = localStorage.getItem('jwt')
      
        this.$axios.get(`${this.$store.getters.getServer}/user/${nickname}`,{},token)
        .then(res => {
          console.log(res.data)
          this.tags.push(res.data.data)
        })
        .catch(err => {
          console.error(err)
          this.showModal = true
        })
        
        event.target.value = ''
      }
    },
    removeTag (index) {
      this.tags.splice(index, 1)
    },
    removeLastTag(event) {
      if (event.target.value.length === 0) {
        this.removeTag(this.tags.length - 1)
      }
    },
    goToReceived() {
      this.$router.push({ name: "Received" })
    },
    goToSent() {
      this.$router.push({ name: "Sent" })
    },
  }
}
</script>

<style>
  .write-container {
    display: flex; 
    flex-direction: column;
    justify-content:space-between;
  }
  .write-header {
    display: flex; 
    align-items: center;
  }
  .write-body {
    height: 300px;
    background-color: rgb(246, 246, 245);
  }
  .write-receiver {
    display: flex; 
    padding: 5px 15px;
    margin: 15px 0px;
    height: 50px;
    align-items: center;
    background-color: white;
    border-radius: 20px;
  }
  .write-content {
    border:none; 
    width:100%;
    margin: 15px 0px;
    border-radius: 20px;
  }
  .write-footer {
    display: flex; 
    flex-direction: row-reverse;
  }

  .write-button {
    display: flex; 
    justify-content: center;
    align-items: center;
    border-radius: 15px / 15px; 
    font-weight: bold; 
    font-size: 1.2rem;
    color: black;
    border: 0px;
    background-color:rgba(255,162,0,0.3);
  }

  .write-h1 {
    color: black;
    font-size: 1.5rem;
    font-weight: bold;
    display: inline-block;
  }

  .my-hr1 {
    border: 0;
    height: 1px;
    background: #ccc;
  }
  .my-hr2 {
    border: 0;
    height: 2px;
    background: #ccc;
  }
  .my-hr3 {
    border: 0;
    height: 3px;
    background: #ccc;
  }

  .back-lg {
    height: 500px;
  }
  .back-md {
    height: 400px;

  }
  .back-sm {
    height: 300px;
  }

  .tag-input {
    width: 100%;
    border: 1px solid #eee;
    font-size: 0.9em;
    height: 50px;
    box-sizing: border-box;
    padding: 0 10px;
    border: none;
  }

  .tag-input__tag {
    height: 30px;
    float: left;
    margin-right: 10px;
    background-color: #eee;
    margin-top: 10px;
    line-height: 30px;
    padding: 0 5px;
  }

  .tag-input__tag > span {
    cursor: pointer;
    opacity: 0.75;
  }

  .tag-input__text {
    border: none;
    outline: none;
    font-size: 0.9em;
    line-height: 50px;
    background: none;
  }

  .modal-mask {
    position: fixed;
    z-index: 9998;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background-color: rgba(0, 0, 0, .6);
    display: table;
    transition: opacity .3s ease;
  }
  .modal-wrapper {
    display: table-cell;
    vertical-align: middle;
  }
  .modal-container {
    width: 500px;
    height: 250px;
    margin: 0px auto;
    padding: 10px 20px 30px 20px;
    background-color: rgb(246, 246, 245);
    border-radius: 10px;
    transition: all .3s ease;
    
    display: flex;
    flex-direction: column;
    justify-content: space-between;
  }
  .modal_header {
    display: flex;
    flex-direction: row-reverse;
  }
  .modal_body {
    height: 100px;
    text-align: center;
    justify-content: center;
    align-items: center;
  }
  .modal_footer {
    display: flex;
    justify-content: space-around;
  }
  .modal-button {
    font-size: 20px;
    font-weight: bold;
    color: darkslategray;
    border-radius: 20px;
    width: 150px;
  }
</style>