<template>
  <div style="display: flex; align-items: center;">
    <input 
      type="checkbox" 
      :id="option.id" 
      class="checkPref"
    >
    <label 
      :for="option.value" 
      :class="{ check_Label: !option.isChecked, checked_Label: option.isChecked }" 
      class="mouse-pointer"
      @click="changeCheck(option.isChecked)"
      style="display: flex; justify-content: center; align-items: center;"
    >
      <img 
        v-if="option.isChecked"
        class="checkImg"
        src="data:image/svg+xml;base64,PHN2ZyBpZD0iQ2FwYV8xIiBlbmFibGUtYmFja2dyb3VuZD0ibmV3IDAgMCA1MTUuNTU2IDUxNS41NTYiIGhlaWdodD0iNTEyIiB2aWV3Qm94PSIwIDAgNTE1LjU1NiA1MTUuNTU2IiB3aWR0aD0iNTEyIiB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciPjxwYXRoIGQ9Im0wIDI3NC4yMjYgMTc2LjU0OSAxNzYuODg2IDMzOS4wMDctMzM4LjY3Mi00OC42Ny00Ny45OTctMjkwLjMzNyAyOTAtMTI4LjU1My0xMjguNTUyeiIvPjwvc3ZnPg==" 
      />
    </label>

    <label 
      :for="option.value" 
      class="realLabel"
    >
      {{ option.text }}
    </label>
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
      // console.log('this.option', this.option)
      this.$emit("serve-data", this.option)
    }
  }
}
</script>

<style>
  .checkPref {
    display: none;
  }
  .check_Label {
  height: 17px;
  width: 17px;
  background-color: white;
  border: 1px solid;
  border-color: rgb(121, 121, 121);
  border-radius: 20px/ 20px;
  margin-left: 0px;
  margin-bottom: 0px;
  margin-top: 0px;
  }
  .checked_Label {
    height: 17px;
    width: 17px;
    border: 0px solid;
    border-color: rgb(145, 145, 145);
    border-radius: 5px/ 5px;
    margin-left: 0px;
    margin-bottom: 0px;
  }
  .checkImg {
    height: 17px; 
    width: 17px; 
    /* margin-bottom: 10px;  */
  }
  .realLabel {
    margin-left: 5px;
    margin-bottom: 0;
    font-size: 17px;
  }
</style>