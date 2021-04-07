<template>
  <div>

    <div class="second-left-small row justify-content-center">
      <div v-if="bookSentiment.positive">
        
        <h3 class="second-title">
          좋아요 
          <font-awesome-icon :icon="['far', 'thumbs-up']" size="sm" class="blue hvr-grow mouse-pointer"/>
        </h3>
        <div class="title-box" style="border: 1px solid #2196f3; box-shadow: 5px 5px 10px -5px #2196f3;">
          <div 
            style="
              width:400px; 
              height:200px; 
              overflow: auto;
              padding: 0.3rem;
            "
          >
            <div v-for="(review, idx) in bookSentiment.positive.reviews" :key="idx">
              <p>"{{ review }}"</p>
              <br><hr><br>
            </div>
          </div>
        </div>
        <h3 class="second-title" style="margin-top: 1.3rem;">
          싫어요 
          <font-awesome-icon :icon="['far', 'thumbs-down']" size="sm" class="red hvr-grow mouse-pointer" />
        </h3>
        <div class="title-box" style="border: 1px solid #f44336; box-shadow: 5px 5px 10px -5px #f44336;">
          <div style="
            width:400px; 
            height:200px; 
            overflow: auto;
            padding: 0.3rem;
          ">
            <div v-for="(review, idx) in bookSentiment.negative.reviews" :key="idx">
              <p>"{{ review }}"</p>
              <br><hr><br>
            </div>
          </div>
        </div>

      </div>
      <div v-else style="height:300px; display:flex; justify-content:center; align-items:center;">
        <h5 style="text-align: center;">리뷰 데이터 수집중</h5>
      </div>
    </div>

  </div>
</template>

<script>

  export default {
    props: {
      bookSentiment: {
        type: Object,
      },
    },
  }
</script>

<style scoped>
  .red {
    color: #f44336;
  }
  .blue {
    color: #2196f3;
  }
  .second-title {
    font-weight: bold;
  }
  .title-box {
    margin: 0.5rem 1rem; 
    padding: 20px; 
    border-radius:20px;
    box-shadow: 5px 5px 10px -5px grey;
  }
</style>