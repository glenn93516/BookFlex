<template>
  <div>
    <div :class="size" class="sent-container">
      <!-- header -->
      <header>
        <div class="sent-header">
          <h1 
            class="sent-h1"
          >
            받은 쪽지함
          </h1>

          <b-button
            class="btn btn-success"
            type="submit"
            style="border-radius:25px"
            :disabled="disableBtn"
          >
            보낸 쪽지함
          </b-button>
        </div>
      </header>
      
      <!-- 받은메세지 리스트 -->
      <!-- body -->
      <body class="sent-body">
        <hr class="my-hr2">

        <div class="sent-message">
          <div>
            <b-avatar size="2.5rem" src="https://placekitten.com/300/300"></b-avatar>
            <span> 안녕하세요 ~! 이 책 괜찮나요 ?</span>
          </div>

          <div class="sent-date">
            <p>21.03.04</p>
            <p>체크</p>
          </div>
        </div>
        <hr>
        
      </body>

      <!-- footer -->
      <footer class="sent-footer">
        <b-button
          class="btn btn-success"
          type="submit"
          :disabled="disableBtn"
        >
          쪽지 쓰기
        </b-button>
      </footer>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      size: "back-lg",
    }
  },
  created() {
  },
  watch: {
  },
  methods: {
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
  }
  .sent-date{
    font-size: 12px;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-content: center;
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