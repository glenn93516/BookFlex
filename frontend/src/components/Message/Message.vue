<template>
  <div class="message-main">
    <div>
      <div id="message-background">
        <router-view ref="message"></router-view>
      </div>

    </div>
  </div>
</template>

<script>
export default {
  components: {
  },
  data() {
    return {
    }
  },
}
</script>

<style>
  .message-main {
    display: flex; 
    justify-content: center;
  }
  #message-background {
    margin-top: 15vh;
    /* margin-bottom: 15vh; */
    width: 550px;
    /* height: 400px; */
    background-color: white;
    border-radius: 30px / 30px;
    margin-right: 0px;
    box-shadow: 0px 3px 6px rgba(0, 0, 0, 0.16);
    padding-top: 30px;
    padding-bottom: 30px;
    padding-left: 40px;
    padding-right: 40px;
    background-color: rgb(246, 246, 245);
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