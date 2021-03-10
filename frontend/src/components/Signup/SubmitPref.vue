<template>
  <div>
    <router-link :to="{ name: 'SignupComplete' }" class="btn btn-primary btn-block">다음으로 넘어가기(임시)</router-link>
  </div>
</template>

<script>
export default {
  data() {
    return {
      progressNum: 3
    }
  },
  methods: {
    serveProgressData() {
      console.log("여기는 선호장르")
      return this.progressNum
    },
  }
}
</script>

<style>

</style>