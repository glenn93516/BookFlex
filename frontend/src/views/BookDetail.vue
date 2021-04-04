<template>
  <div>
    <div class="bookdetail-box">
      <router-view></router-view>
    </div>
    <div style="width: 1100px;">
      <div class="mt-3" style="display: flex; justify-content: center;">
        <b-pagination-nav 
          pills 
          :pages="pages" 
          use-router 
          class="detail_pagenation"
        >
          <!-- <template #first-text>first</template>
          <template #last-text>last</template>
          <template #page="{ }">


          </template> -->
        </b-pagination-nav>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: {
  },
  data() {
    return {
      pages: [
        { link: { name: 'FirstPage' }, text: '1' },
        { link: { name: 'SecondPage' }, text: '2' },
        { link: { name: 'ThirdPage' }, text: '3' },
      ],
    }
  },
  methods: {
    linkGen(pageNum) {
      console.log(pageNum)
    }
  },
  created() {
    console.log(this.book)
  }
}
</script>

<style>
  .bookdetail-box {
    background: white; 
    width: 1100px; 
    height: 700px;
    box-shadow: 0px 5px 10px -10px gray;
  }
 .detail_pagenation {
   background-color: rgba(0, 0, 0, 0);
 }
</style>