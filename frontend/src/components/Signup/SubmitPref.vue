<template>
  <div>
    <br>
    <p class="describeP">선호 또는 관심있는 장르를 선택해 주세요.</p>
    <!-- <h5 style="font-weight: bold;">현재 혹은 희망하는 직종을 선택해주세요</h5> -->
        <!-- <input type="checkbox" v-for="(option, idx) in options" :key="idx" :id="option.id" class="checkPref">
        <label v-for="(option, idx) in options" :key="idx" :for="option.id" :class="{ check: option.isChecked, checked: !option.isChecked }" @click="changeCheck(option.isChecked, idx)"></label> -->
    <br>
    <input type="checkbox" v-for="(option, idx) in options" :key="idx" :id="option.id" class="checkPref">
    <label :for="option.id" :class="{ check: option.isChecked, checked: !option.isChecked }" @click="changeCheck(option.isChecked, idx)"></label>
    <br>
    <router-link :to="{ name: 'SignupComplete' }" class="btn btn-primary btn-block">다음으로 넘어가기(임시)</router-link>
  </div>
</template>

<script>
export default {
  data() {
    return {
      progressNum: 3,
      // options에 리스트 형태로 분류목록을 담아주면 됩니다.
      // text는 보여지는 부분, value는 동작 단위의 id값입니다.(같은 id는 같이 클릭됨)
      options: [
          { text: '철학', value: '1', id: 'checkbox-group-1_BV_option_0', isChecked: true},
          { text: '종교', value: '2', id: 'checkbox-group-1_BV_option_1', isChecked: true},
          { text: '사회과학', value: '3', id: 'checkbox-group-1_BV_option_2', isChecked: true},
          { text: '순수과학', value: '4', id: 'checkbox-group-1_BV_option_3', isChecked: true},
          { text: '기술과학', value: '5', id: 'checkbox-group-1_BV_option_4', isChecked: true},
          { text: '예술', value: '6', id: 'checkbox-group-1_BV_option_5', isChecked: true},
          { text: '언어', value: '7', id: 'checkbox-group-1_BV_option_6', isChecked: true},
          { text: '문학', value: '8', id: 'checkbox-group-1_BV_option_7', isChecked: true},
          { text: '역사', value: '9', id: 'checkbox-group-1_BV_option_8', isChecked: true},
      ],
      isChecked: true,
    }
  },
  methods: {
    serveProgressData() {
      console.log("여기는 선호장르")
      return this.progressNum
    },
    changeCheck(Check, i) {
      if (Check) {
        this.options[i].isChecked = false
      } else {
        this.options[i].isChecked = true
      }
    }
  }
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
    font-size: 12px;
  }
  .checkPref {
    /* display: none; */
  }
  .check {
    height: 20px;
    width: 20px;
    background-color: white;
    border: solid;
    border-color: rgb(121, 121, 121);
    border-radius: 5px/ 5px;
    margin-left: 5px;
  }
  .checked {
    height: 20px;
    width: 20px;
    background-color: rgb(138, 255, 92);
    border: solid;
    border-color: rgb(145, 145, 145);
    border-radius: 5px/ 5px;
    margin-left: 5px;
  }

</style>