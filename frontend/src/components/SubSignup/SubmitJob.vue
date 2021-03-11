<template>
  <div>
    <br>
    <h4>현재 혹은 희망하시는 직종을 선택해주세요.</h4>
    <div v-for="(option, idx) in options" :key="idx" class="custom-control">
      <Checkbox :option="option" @serveData="catchData"/>
    </div>
    <div class="checkvalues">
      선택된 항목: {{ checkedValues }}
    </div>
    <router-link :to="{ name: 'SubmitPic' }" class="btn btn-primary btn-block">다음으로 넘어가기(임시)</router-link>
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
      progressNum: 2,
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
    serveProgressData() {
      console.log("여기는 나의 직업")
      return this.progressNum
    },
    catchData(option) {
      console.log(option)
      this.options[option.value].isChecked = option.isChecked
      console.log(this.options)
    }
  },
  watch: {
    options: function () {
      let checkarr = []
      for (var option in this.options) {
        if (option.isChecked) {
          checkarr.push(option.text)
        }
      }
      console.log('-----------', checkarr)
      this.checkedValues = checkarr
    }
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
    height: 15px;
    width: 15px;
    background-color: white;
    border: solid;
    border-color: rgb(121, 121, 121);
    border-radius: 5px/ 5px;
    margin-left: 5px;
    margin-bottom: 0px;
  }
  .checked {
    height: 15px;
    width: 15px;
    background-color: rgb(138, 255, 92);
    border: solid;
    border-color: rgb(145, 145, 145);
    border-radius: 5px/ 5px;
    margin-left: 5px;
    margin-bottom: 0px;
  }
  .realLabel {
    margin-left: 5px;
  }
  .checkvalues {
    margin-top: -5px;
    margin-bottom: 10px;
  }
</style>