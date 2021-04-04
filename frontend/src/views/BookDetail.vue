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
          <!-- <template #first-text class="bg-success"><span class="text-success bg-success">처음</span></template>
          <template #prev-text>이전</template>
          <template #next-text>다음</template> -->
          <template #page="{  }">


          </template>
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
 .pagination > li:first-child {
   display: none;
 }
  .pagination > li:last-child {
   display: none;
 }
 .pagination > li:nth-child(2) > a {
  display: none;
 }
.pagination .disabled {
  display: none;
 }
.pagination > li:nth-child(3) > a,
.pagination > li:nth-child(4) > a,
.pagination > li:nth-child(5) > a 
{
  font-size: 20px;
  color: rgb(0, 0, 0);
  background-color: rgba(0, 0, 0, 0);
  border: 0px;
 }
.pagination > li:nth-child(3) > a:visited,
.pagination > li:nth-child(4) > a:visited,
.pagination > li:nth-child(5) > a:visited
{
  font-size: 20px;
  color: rgb(0, 0, 0);
  background-color: rgb(192, 180, 165);
  border: 0px;
 }
.pagination > li:nth-child(6) > a {
  display: none;
 }
</style>