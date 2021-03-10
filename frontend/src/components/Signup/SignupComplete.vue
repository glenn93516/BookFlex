<template>
  <div>
    <h1 style="color: black;">회원가입 완료!</h1>
    <router-link :to="{ name: 'Login'}" >회원가입 완료</router-link>
  </div>
</template>

<script>
export default {
  data() {
    return {
      progress: 4,
    }
  },
  methods: {
    serveProgressData() {
      console.log('여기는 서브밋')
      return this.progress
    }
  }
}
</script>

<style>

</style>