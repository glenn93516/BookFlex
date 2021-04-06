<template>
  <div>
    <div :class="size" class="sent-container">
      <!-- header -->
      <header>
        <div class="sent-header">
          <h1 
            class="sent-h1"
          >
            보낸 쪽지함
          </h1>

          <b-button
            class="btn btn-info"
            type="submit"
            style="border-radius:25px"
            @click="goToReceived"
          >
            받은 쪽지함
          </b-button>
        </div>
      </header>
      
      <!-- 보낸메세지 리스트 -->
      <!-- body -->
      <body class="sent-body">
        <hr class="my-hr2">
        <div v-for='(sentMessage, idx) in sentMessages' :key="idx">

          <div class="sent-message">
            <div class="mouse-pointer" style="padding-right: 10px;">
              <b-avatar :id="sentMessage.sendmsgDate" size="40px" :src="sentMessage.receiverProfileImg"></b-avatar>

              <b-tooltip :target="sentMessage.sendmsgDate" triggers="hover">
                {{ sentMessage.receiverNickname }}
              </b-tooltip>
            </div>
            <div class="mouse-pointer sent-content"
              @click="goToReadSent(sentMessage.sendmsgId)"
            > 
            {{ sentMessage.sendmsgContent }}
            </div>
            <div class="sent-date">
              <p>{{ sentMessage.sendmsgDate }}</p>
            </div>
          </div>

          <hr>
        </div>
      </body>


      <!-- footer -->
      <footer class="sent-footer">
        <img 
          width="70px" 
          class="mouse-pointer ml-2"
          src="data:image/svg+xml;base64,PHN2ZyBpZD0iQ2FwYV8xIiBlbmFibGUtYmFja2dyb3VuZD0ibmV3IDAgMCA0ODMuMjUxIDQ4My4yNTEiIGhlaWdodD0iNTEyIiB2aWV3Qm94PSIwIDAgNDgzLjI1MSA0ODMuMjUxIiB3aWR0aD0iNTEyIiB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciPjxnPjxnIGlkPSJpY29uXzYyXyI+PHBhdGggZD0ibTQ3NS4zNjcgMTk1LjA5NXYyNjQuNTgyYy4wNDkgNi42LTIuNzQyIDEyLjkwMy03LjY2MSAxNy4zMDNsLS42OTUuNjk1Yy00LjI0MyAzLjYxNy05LjY0MSA1LjU5NS0xNS4yMTcgNS41NzVoLTQyMC4zMzFjLTUuNTM2LS4wMDEtMTAuODktMS45NzgtMTUuMDk5LTUuNTc1LS4yODctLjE2OS0uNTI2LS40MDgtLjY5NS0uNjk1LTQuOTU2LTQuMzg1LTcuNzkxLTEwLjY4Ni03Ljc4NS0xNy4zMDN2LTI2NC41ODJjLS4wNTUtNS44ODIgMi4xOTYtMTEuNTUyIDYuMjcxLTE1Ljc5NCAxLjY2MS0xLjk2MSAzLjY1Ny0zLjYxMyA1Ljg5My00Ljg4IDMuNDg5LTEuOTA5IDcuNDAzLTIuOTA4IDExLjM4LTIuOTA1aDQyMC4zNjFjNi41OTguMTE5IDEyLjg3MyAyLjg3OSAxNy40MjEgNy42NjEgMy45NTIgNC4zNjEgNi4xNDcgMTAuMDMzIDYuMTU3IDE1LjkxOHoiIGZpbGw9IiNmNmI4NjMiLz48cGF0aCBkPSJtNDY5LjIzMiAxNzkuMTU0Yy0xLjY1NiAyLjA0NS0yMjcuNDgzIDE4MS42MzktMjI3LjQ4MyAxODEuNjM5bC0yMjcuNjA3LTE4MS40MzkuMS0uMDk0di0uMWwyMTQuNjE5LTE3NC41OTFjNy40OS02LjA5MiAxOC4yMjgtNi4wOTIgMjUuNzE5IDB6IiBmaWxsPSIjZWFhMTRlIi8+PHBhdGggZD0ibTQyMS4yMTkgNTcuNDE1djE2MC40MDVsLTE3OS40OTMgMTQyLjk3My0xNzkuNTk0LTE0My4xNjd2LTE2MC4yMTF6IiBmaWxsPSIjZWVlZmVlIi8+PHBhdGggZD0ibTQ2Ny42NzcgNDc2Ljk2OGMtLjIwMi4yNTItLjQzMi40ODEtLjY4NC42ODRsLTIwMi4xODktMTM0Ljg3NSAxOC41MDUtMTQuOTA0eiIgZmlsbD0iI2VhYTE0ZSIvPjxwYXRoIGQ9Im0yMTguNTQxIDM0Mi42NzctMjAyLjE4OSAxMzQuOTU3Yy0uMjk3LS4xNDYtLjUzOC0uMzg2LS42ODQtLjY4NGwxODQuMjczLTE0OS4yNzN6IiBmaWxsPSIjZWFhMTRlIi8+PGcgZmlsbD0iI2RiZDhkZCI+PHBhdGggZD0ibTEwMS45MTIgOTkuNTE3aDI3OS40MjF2MTIuMTIzaC0yNzkuNDIxeiIvPjxwYXRoIGQ9Im0xMDEuOTEyIDE0Ni4yNzVoMjc5LjQyMXYxMi4xMjNoLTI3OS40MjF6Ii8+PHBhdGggZD0ibTEwMS45MTIgMTkzLjAzOWgyNzkuNDIxdjEyLjEyM2gtMjc5LjQyMXoiLz48cGF0aCBkPSJtMTQ4Ljg0MSAyMzkuNzk2aDE4NS41N3YxMi4xMjNoLTE4NS41N3oiLz48L2c+PC9nPjwvZz48L3N2Zz4=" 
          @click="goToWrite"
        />
      </footer>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      size: "back-lg",
      sentMessages: [],
    }
  },
  created() {
    this.getSentMessage()
  },
  watch: {
  },
  methods: {
    getSentMessage(){
      const headers = {
        "Authorization": localStorage.getItem('jwt')
      }
      this.$axios.get(`${this.$store.getters.getServer}/sendmsg`,{headers})
      .then(res => {
        console.log(res.data)
        this.sentMessages = res.data.data
      })
      .catch(err => {
        console.error(err)
      })
    },
    goToReceived() {
      this.$router.push({ name: "Received" })
    },
    goToWrite() {
      this.$router.push({ name: "Write" })
    },
    goToReadSent(sendmsgId) {
      this.$router.push({ path: `readSent/${sendmsgId}` })
    }
  }
}
</script>

<style>
  .sent-container {
    display: flex; 
    flex-direction: column;
    justify-content:space-between; 
  }

  .sent-header {
    display: flex; 
    justify-content: space-between; 
    align-items: center;
  }

  .sent-body {
    height: 300px;
    background-color: rgb(246, 246, 245);
  }

  .sent-message {
    padding-top: 10px;
    padding-bottom: 10px;
    height: 60px;
    display: flex; 
    justify-content: space-between;
    align-items: center;
  }
  .sent-content {
    width: 380px;
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
  }
  .sent-date {
    font-size: 10px;
    display: flex;
    flex-direction: column;
    justify-content: space-between;
    align-items: center;
  }
  
  .sent-footer {
    display: flex; 
    flex-direction: row-reverse;
  }

  .sent-h1 {
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
</style>