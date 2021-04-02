<template>
  <div>
    <div class="bookdetail-box">
      <router-view></router-view>
    </div>
    <div style="display: flex; justify-content: center;">
      <div class="mt-3">
        <b-pagination-nav pills :pages="pages" use-router :book="book"></b-pagination-nav>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    book: Object,
  },
  data() {
    return {
      pages: [
        { link: { name: 'FirstPage' }, text: '1' },
        { link: { name: 'SecondPage' }, text: '2' },
        { link: { name: 'ThirdPage' }, text: '3' },
        { link: { name: 'FourthPage' }, text: '4' },
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
</style>