<template>
  <div>
    <input type="checkbox" :id="option.id" class="checkPref">
    <label :for="option.value" :class="{ check: !option.isChecked, checked: option.isChecked }" @click="changeCheck(option.isChecked)"></label>
    <label :for="option.value" class="realLabel">{{ option.text }}</label>
  </div>
</template>

<script>
export default {
  props: {
    option: Object,
  },
  data() {
    return {
    }
  },
  methods: {
    changeCheck(Check) {
      if (Check) {
        this.option.isChecked = false
      } else {
        this.option.isChecked = true
      }
      this.serveData()
    },
    serveData() {
      console.log('this.option', this.option)
      this.$emit("serveData", this.option)
    }
  }
}
</script>

<style>

</style>