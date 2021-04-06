<template>
  <div>

    <div class="second-left-small row justify-content-center">
      <div v-if="bookSentiment.positive">
        
        <div style="padding: 20px; border: 1px solid grey; border-radius:20px">
          <font-awesome-icon :icon="['far', 'thumbs-up']" size="1x" style="color:blue" />
          <br>
          <hr>
          <div style="width:400px; height:200px; overflow: auto; color:blue;">
            <div v-for="(review, idx) in bookSentiment.positive.reviews" :key="idx">
              <p>"{{ review }}"</p>
              <br><hr><br>
            </div>
          </div>
        </div>
        
        <br>

        <div style="padding: 20px; border: 1px solid grey; border-radius:20px">
          <font-awesome-icon :icon="['far', 'thumbs-down']" size="1x" style="color:red" />
          <div style="width:400px; height:200px; overflow: auto; color:red;">
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