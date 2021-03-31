<template>
  <div>
    <h5 
      class="job-subtitle font-nanumpen"
    >
      현재 혹은 희망하시는 직종을 선택해주세요.
    </h5>

    <div 
      v-for="(option, idx) in options" 
      :key="idx" 
      class="custom-control font-nanumpen"
    >
      <Checkbox :option="option" @serveData="catchData"/>
    </div>

    <!-- 중복선택 가능하게 바꾸려면 checkedValues[0]을 checkedValues로 바꿔주세요 -->
    <b-button 
      @click="submitUserJob(checkedValues[0])"
      @keydown.enter="submitUserJob(checkedValues[0])"
      block
      class="btn-success job-btn login-button font-nanumpen"
    >
      다음으로 넘어가기
    </b-button>
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
      this.options[option.value].isChecked = option.isChecked
      if (option.isChecked) {
        if (this.checkedValues.length > 0) {
          // 중복선택 가능하게 바꾸려면 바로위의 if문을 제거하고 else문에 있는 명령어만 사용!
          alert("하나만 선택해 주세요 :(")
          this.options[option.value].isChecked = false
        } else {
          this.checkedValues.push(option.text)
        }
      } else {
        const idx = this.checkedValues.indexOf(option.text)
        this.checkedValues.splice(idx, 1)
      }
    },
    submitUserJob(Job) {
      this.$store.dispatch('SubmitUserJob', Job)
      this.goToSubmitPic()
    },
    goToSubmitPic() {
      console.log('담겼나?', this.$store.getters.getUser)
      this.$router.push({ name: 'SubmitPic'})
    }
  },
  watch: {
  },
}
</script>

<style>
  .job-subtitle {
    margin-top: 5px; 
    margin-bottom: 10px
  }
  .custom-control {
    display: inline-block;
    width: 130px;
    line-height: 25px;
    margin-top: 7px;
    margin-bottom: 20px;
    padding-left: 15px;
  }
  .job-btn {
    margin-top: 10px;
  }
</style>