<template>
  <div id="community">
    <section id="monthly-recommend-book">
      <header class="title">이달의 추천 책</header>
      <hr>
      <div>
        <!-- <Book></Book> -->
      </div>
    </section>
    <section id="sentence-timeline">
      <header class="title">실시간 수집된 문장</header>
      <hr>
      <!-- <community-item :item="showItems[0]"></community-item> -->
      <div id="itemContainer">
        <community-item v-for="item in showItems" :key="item.index" :item="item"></community-item>
      </div>
    </section>
  </div>
</template>

<script>
// import Book from '@/components/Main/Book.vue'
import CommunityItem from '../components/Community/CommunityItem.vue'

export default {
  components: {
    // Book,
    CommunityItem,
  },
  data() {
    return {
      showItems: [],
    }
  },
  methods: {

  },
  created() {
    this.$axios.get(`${this.$store.getters.getServer}/highlight`)
    .then(res => {
      console.log(res.data.data, 'res')
      this.showItems = res.data.data
    })
    .catch(err => {
      console.log(err)
    })
  },
}
</script>

<style>
#community .title {
  font-size: 30px;
  font-weight: bold;
}
#community hr {
  margin: 10px 0;
}
</style>