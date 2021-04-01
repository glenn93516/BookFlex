<template>
  <div>
    <div :class="size" class="received-container">
      <!-- header -->
      <header>
        <div class="received-header">
          <h1 
            class="received-h1"
          >
            받은 쪽지함
          </h1>

          <b-button
            class="btn btn-info"
            type="submit"
            style="border-radius:25px"
            @click="goToSent"
          >
            보낸 쪽지함
          </b-button>
        </div>
      </header>
      
      <!-- 받은메세지 리스트 -->
      <!-- body -->
      <body class="received-body">
        <hr class="my-hr2">
        <div v-for='(receivedMessage, idx) in receivedMessages' :key="idx">

          <div class="received-message">
            <div class="mouse-pointer" style="padding-right: 10px;">
              <b-avatar size="40px" src="https://placekitten.com/300/300"></b-avatar>
            </div>
            <div 
              class="mouse-pointer received-content"
              @click="goToReadReceived(receivedMessage.receivemsgId)"
            >
              {{receivedMessage.receivemsgContent }}
            </div>
            <div class="received-date">
              <p>{{ receivedMessage.receivemsgDate }}</p>
              <img v-if="receivedMessage.receivemsgRead  === '1'"
                width="20px"
                src="data:image/svg+xml;base64,PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iaXNvLTg4NTktMSI/Pg0KPCEtLSBHZW5lcmF0b3I6IEFkb2JlIElsbHVzdHJhdG9yIDE5LjAuMCwgU1ZHIEV4cG9ydCBQbHVnLUluIC4gU1ZHIFZlcnNpb246IDYuMDAgQnVpbGQgMCkgIC0tPg0KPHN2ZyB2ZXJzaW9uPSIxLjEiIGlkPSJMYXllcl8xIiB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHhtbG5zOnhsaW5rPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5L3hsaW5rIiB4PSIwcHgiIHk9IjBweCINCgkgdmlld0JveD0iMCAwIDUxMiA1MTIiIHN0eWxlPSJlbmFibGUtYmFja2dyb3VuZDpuZXcgMCAwIDUxMiA1MTI7IiB4bWw6c3BhY2U9InByZXNlcnZlIj4NCjxwYXRoIHN0eWxlPSJmaWxsOiNBNUVCNzg7IiBkPSJNNDMzLjEzOSw2Ny4xMDhMMjAxLjI5NCwyOTguOTUzYy02LjI0OSw2LjI0OS0xNi4zODEsNi4yNDktMjIuNjMsMEw3OC44NjEsMTk5LjE1TDAsMjc4LjAxMQ0KCWwxNTAuNTQ3LDE1MC41NDljMTAuNDU4LDEwLjQ1OCwyNC42NDIsMTYuMzMzLDM5LjQzMSwxNi4zMzNsMCwwYzE0Ljc4OCwwLDI4Ljk3My01Ljg3NiwzOS40My0xNi4zMzNMNTEyLDE0NS45NjhMNDMzLjEzOSw2Ny4xMDh6Ig0KCS8+DQo8ZyBzdHlsZT0ib3BhY2l0eTowLjE7Ij4NCgk8cGF0aCBkPSJNNDg1LjkyMSwxMTkuODg4TDE4Ny41OSw0MTguMjJjLTguMjU0LDguMjUzLTE4LjYzMywxMy44ODItMjkuODQ3LDE2LjM5MWM5LjM2Myw2LjYzNSwyMC42MDgsMTAuMjgsMzIuMjM1LDEwLjI4bDAsMA0KCQljMTQuNzg4LDAsMjguOTczLTUuODc2LDM5LjQzLTE2LjMzM0w1MTIsMTQ1Ljk2Nkw0ODUuOTIxLDExOS44ODh6Ii8+DQo8L2c+DQo8Zz4NCjwvZz4NCjxnPg0KPC9nPg0KPGc+DQo8L2c+DQo8Zz4NCjwvZz4NCjxnPg0KPC9nPg0KPGc+DQo8L2c+DQo8Zz4NCjwvZz4NCjxnPg0KPC9nPg0KPGc+DQo8L2c+DQo8Zz4NCjwvZz4NCjxnPg0KPC9nPg0KPGc+DQo8L2c+DQo8Zz4NCjwvZz4NCjxnPg0KPC9nPg0KPGc+DQo8L2c+DQo8L3N2Zz4NCg==" 
              />
            </div>
          </div>

          <hr>
        </div>
      </body>


      <!-- footer -->
      <footer class="received-footer">
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
      receivedMessages: [],
    }
  },
  created() {
    this.getReceivedMessage()
  },
  watch: {
  },
  methods: {
    getReceivedMessage(){
      const headers = {
        "Authorization": localStorage.getItem('jwt')
      }
      this.$axios.get(`${this.$store.getters.getServer}/receivemsg`,{headers})
      .then(res => {
        console.log(res.data)
        this.receivedMessages = res.data.data
      })
      .catch(err => {
        console.error(err)
      })
    },
    goToSent() {
      this.$router.push({ name: "Sent" })
    },
    goToWrite() {
      this.$router.push({ name: "Write" })
    },
    goToReadReceived(receivemsgId) {
      this.$router.push({ path: `readReceived/${receivemsgId}` })
    }
  }
}
</script>

<style>
  .received-container {
    display: flex; 
    flex-direction: column;
    justify-content:space-between; 
  }

  .received-header {
    display: flex; 
    justify-content: space-between; 
    align-items: center;
  }

  .received-body {
    height: 300px;
    background-color: rgb(246, 246, 245);
  }

  .received-message {
    padding-top: 10px;
    padding-bottom: 10px;
    height: 60px;
    display: flex; 
    justify-content: space-between;
    align-items: center;
  }
  .received-content {
    width: 380px;
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
  }
  .received-date {
    width: 100px;
    font-size: 10px;
    display: flex;
    justify-content: space-between;
    align-items: center;
  }
  
  .received-footer {
    display: flex; 
    flex-direction: row-reverse;
  }

  .received-h1 {
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