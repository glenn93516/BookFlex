<template>
  <div id="community">
    <section id="monthly-recommend-book">
      <header class="title">이달의 추천 책</header>
      <hr>
      <div>
        <book></book>
      </div>
    </section>
    <section id="sentence-timeline">
      <header class="title">실시간 수집된 문장</header>
      <hr>
    </section>
  </div>
</template>

<script>
// import Book from '@/components/Main/Book.vue'

export default {
  components: {
    // Book,
  },
  data() {
    return {}
  },
  methods: {

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