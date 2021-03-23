<template>
  <div>
    <br>
    <p class="describeP">선호 또는 관심있는 장르를 선택해 주세요.</p>
    <div v-for="(option, idx) in options" :key="idx" class="custom-control">
      <Checkbox :option="option" @serveData="catchData"/>
    </div>
    <div class="checkvalues">
      선택항목: 
      <span v-for="(checkValue, idx) in checkedValues" :key="idx" class="badge badge-pill" :class="checkValue.class">{{ checkValue.text }}</span>
    </div>
    <router-link :to="{ name: 'SignupComplete' }" class="btn btn-success btn-block" :class="{ disabled : isActive }">다음으로 넘어가기</router-link>
  </div>
</template>

<script>
import Checkbox from '@/components/Element/Checkbox.vue'

export default {
  components: {
    Checkbox,
  },
  data() {
    return {
      pageData: {
        progress: 3,
        size: "back-md",
      },
      isActive: true,
      // options에 리스트 형태로 분류목록을 담아주면 됩니다.
      // text는 보여지는 부분, value는 동작 단위의 id값입니다.(같은 id는 같이 클릭됨)
      options: [
          { text: '역사', value: '0', isChecked: false, class: "badge-primary"},
          { text: '철학', value: '1', isChecked: false, class: "badge-secondary"},
          { text: '종교', value: '2', isChecked: false, class: "badge-success"},
          { text: '사회과학', value: '3', isChecked: false, class: "badge-danger"},
          { text: '순수과학', value: '4', isChecked: false, class: "badge-warning"},
          { text: '기술과학', value: '5', isChecked: false, class: "badge-info"},
          { text: '예술', value: '6', isChecked: false, class: "badge-light"},
          { text: '언어', value: '7', isChecked: false, class: "badge-dark"},
          { text: '문학', value: '8', isChecked: false, class: "badge-success"},
      ],
      checkedValues: [ ],
    }
  },
  methods: {
    servePageInfo() {
      console.log("여기는 선호장르")
      return this.pageData
    },
    // 사용자가 선택한 항목의 체크표시
    // 선택한 항목을 리스트에 담아서 보여주기
    catchData(option) {
      this.options[option.value].isChecked = option.isChecked
      if (option.isChecked) {
        this.checkedValues.push(option)
      } else {
        const idx = this.checkedValues.indexOf(option)
        console.log(this.checkedValues)
        console.log(option)
        console.log(idx)
        this.checkedValues.splice(idx, 1)
      }
      console.log(this.checkedValues)
    },
  },
  watch: {
    checkedValues: function (newValue) {
      if (newValue.length) {
        this.isActive = false
      } else {
        this.isActive = true
      }
    }
  },
}
</script>

<style>
  .describeP {
    color: #5a9b00;
    font-size: 14px;
    margin-top: -5px;
  }
  .custom-control {
    display: inline-block;
    width: 117px;
    line-height: 25px;
    margin-bottom: 25px;
  }
  .checkvalues {
    margin-bottom: 10px;
    margin-top: -10px;
  }
</style>