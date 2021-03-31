<template>
  <div>
    <div :class="size" class="read-container">
      <!-- header -->
      <header>
        <div class="read-header">
          <div>
            <b-button
              class="btn btn-success"
              type="submit"
              :disabled="disableBtn"
            >
              뒤로
            </b-button>

            <h1 
              class="read-h1"
            >
              받은 쪽지
            </h1>
          </div>

          <b-button
            class="btn btn-success"
            type="submit"
            :disabled="disableBtn"
          >
            쓰래기통
          </b-button>
        </div>
      </header>
      
      <!-- 받은메세지 리스트 -->
      <!-- body -->
      <body class="read-body">
        <hr class="my-hr2">

        <div class="read-sender">
          <p>보낸 사람: 앙상블</p>
          <p>보낸 일시: 21.03.04</p>
        </div>
        
        <div class="read-content">
          abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz
        </div>
      </body>

      <!-- footer -->
      <footer>
        <b-button 
        block
        class="mt-3 btn-success" 
        style="
          border-radius: 15px / 15px; 
          font-weight: bold; 
          font-size: 1.2rem;
          color: black;
          border: 0px;
          background-color:rgba(255,162,0,0.3);"
        >답장하기</b-button>
      </footer>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      size: "back-lg",
      title: "",
      content: "",
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
  .read-container {
    display: flex; 
    flex-direction: column;
    justify-content:space-between;
  }
  .read-header {
    display: flex;
    justify-content:space-between;
    align-items: center;
  }
  .read-body {
    height: 300px;
    background-color: rgb(246, 246, 245);
  }
  .read-sender {
    margin-top: 5px;
    padding: 5px 15px;
    margin: 15px 0px;
  }
  .read-content {
    padding: 5px 15px;
    margin: 15px 0px;
    width:100%;
    height: 250px;
    background-color: white;
    border-radius: 20px;
    word-break:break-all;
  }

  .read-footer {
    display: flex; 
    flex-direction: row-reverse;
  }

  .read-h1 {
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