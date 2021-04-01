<template>
  <div id='mainDiv'>
    <search-bar> </search-bar>
  </div>
</template>

<script>
import searchBar from '../components/Search/SearchBar.vue'

export default {
  props: {
  },
  components: {
    searchBar,
  },
  data() {
    return {
      test : '메롱'
    }
  },
  methods: {
    searchClick(){
      alert('클릭')
    }
  },
  mounted() {
  }
}
</script>

<style>
  * {
    font-family: 'NanumBarunpen', sans-serif;
    margin: 0;
    padding: 0;
  }
  .profile-nav {
    font-family: 'S-CoreDream-2ExtraLight', sans-serif;
  }
  /* reset */
  h1, p {
    margin: 0;
    padding: 0;
  }
  #navInput {
    font-size: 12px;
    width: 200px;
  }
  #navInput[type="text"]:focus {
    border-color: rgb(218, 203, 0);
    box-shadow: 0 1px 1px rgba(0, 0, 0, 0.075) inset, 0 0 6px rgba(255, 195, 105, 0.6);
    outline: 0 none;
    font-size: 12px;
    width: 200px;
  }

  .more {
    display: flex;
    justify-content: center;
    align-items: center;
  }
  .more-word {
    display: block;
  }
  .more-icon {
    display: block;
  }
  .more-icon:hover {
    width: 40px;
    cursor: pointer;
  }
</style>