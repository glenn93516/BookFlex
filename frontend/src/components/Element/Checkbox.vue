<template>
  <div>
    <input type="checkbox" :id="option.id" class="checkPref">
    <label :for="option.value" :class="{ check: !option.isChecked, checked: option.isChecked }" @click="changeCheck(option.isChecked)">
      <img v-if="option.isChecked" style="height: 20px; width: 20px; margin-bottom: 10px;" src="data:image/svg+xml;base64,PHN2ZyBpZD0iQ2FwYV8xIiBlbmFibGUtYmFja2dyb3VuZD0ibmV3IDAgMCA1MTUuNTU2IDUxNS41NTYiIGhlaWdodD0iNTEyIiB2aWV3Qm94PSIwIDAgNTE1LjU1NiA1MTUuNTU2IiB3aWR0aD0iNTEyIiB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciPjxwYXRoIGQ9Im0wIDI3NC4yMjYgMTc2LjU0OSAxNzYuODg2IDMzOS4wMDctMzM4LjY3Mi00OC42Ny00Ny45OTctMjkwLjMzNyAyOTAtMTI4LjU1My0xMjguNTUyeiIvPjwvc3ZnPg==" />
    </label>
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