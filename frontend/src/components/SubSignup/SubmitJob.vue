<template>
  <div>
    <h5 style="margin-top: -5px; margin-bottom: 18px">현재 혹은 희망하시는 직종을 선택해주세요.</h5>
    <div v-for="(option, idx) in options" :key="idx" class="custom-control">
      <Checkbox :option="option" @serveData="catchData"/>
    </div>
    <router-link :to="{ name: 'SubmitPic' }" class="btn btn-success btn-block job-btn">다음으로 넘어가기</router-link>
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
        progress: 2,
        size: "back-md"
      },
      // options에 리스트 형태로 분류목록을 담아주면 됩니다.
      // text는 보여지는 부분, value는 동작 단위의 id값입니다.(같은 id는 같이 클릭됨)
      options: [
          { text: '경영/사무', value: '0', isChecked: false},
          { text: '영업/상담', value: '1', isChecked: false},
          { text: '생산/제조', value: '2', isChecked: false},
          { text: 'IT/개발', value: '3', isChecked: false},
          { text: '전문직', value: '4', isChecked: false},
          { text: '교육', value: '5', isChecked: false},
          { text: '미디어', value: '6', isChecked: false},
          { text: '건설', value: '7', isChecked: false},
          { text: '디자인', value: '8', isChecked: false},
      ],
      checkedValues: [ ],
    }
  },
  methods: {
    servePageInfo() {
      return this.pageData
    },
    catchData(option) {
      console.log(option)
      this.options[option.value].isChecked = option.isChecked
      console.log(this.options)
      if (option.isChecked) {
        this.checkedValues.push(option.text)
      } else {
        const idx = this.checkedValues.indexOf(option.text)
        this.checkedValues.splice(idx, 1)
      }
      console.log(this.checkedValues)
    }
  },
  watch: {
  },
}
</script>

<style>
  .custom-control {
    display: inline-block;
    width: 117px;
    line-height: 25px;
    margin-bottom: 25px;
  }
  .describeP {
    color: #5a9b00;
    font-size: 13px;
    margin-top: -5px;
  }
  .checkPref {
    display: none;
  }
  .check {
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
  .checked {
    height: 17px;
    width: 17px;
    border: 0px solid;
    border-color: rgb(145, 145, 145);
    border-radius: 5px/ 5px;
    margin-left: 0px;
    margin-bottom: 0px;
  }
  .realLabel {
    margin-left: 5px;
  }
  .checkvalues {
    margin-bottom: 10px;
  }
  .job-btn {
    margin-top: -11px;
  }
</style>