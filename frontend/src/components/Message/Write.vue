<template>
  <div>
    <div :class="size" class="write-container">
      <!-- header -->
      <header>
        <div class="write-header">
          <b-button
            class="btn btn-success"
            type="submit"
            :disabled="disableBtn"
          >
            뒤로
          </b-button>

          <h1 
            class="write-h1"
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
          <b-form-tags
            v-model="value"
            tag-variant="success"
            tag-pills
            size="md"
            style="border:none; 
            width:100%;"
            placeholder=""
          ></b-form-tags>
        </div>
        
        <div>
          <b-form-textarea
            id="textarea-rows"
            rows="11"
            placeholder=""
            class="write-content"
          ></b-form-textarea>
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
        >전송하기</b-button>
      </footer>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      size: "back-lg",
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
</style>