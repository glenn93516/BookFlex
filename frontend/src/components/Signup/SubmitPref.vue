<template>
  <div>
    <p class="notice-message">선호 또는 관심있는 장르를 선택해 주세요.</p>
    <div v-for="(option, idx) in options" :key="idx" class="custom-control">
      <Checkbox :option="option" @serve-data="catchData"/>
    </div>
    <!-- <div class="checkvalues">
      <span v-for="(checkValue, idx) in checkedValues" :key="idx" class="badge badge-pill" :class="checkValue.class">{{ checkValue.text }}</span>
    </div> -->
    <b-button
      block
      class="btn btn-success login-button" 
      :class="{ disabled : isActive }"
      @click="goToComplete"
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
        progress: 3,
        size: "back-md",
      },
      isActive: true,
      // options에 리스트 형태로 분류목록을 담아주면 됩니다.
      // text는 보여지는 부분, value는 동작 단위의 id값입니다.(같은 id는 같이 클릭됨)
      options: [
          { text: '취미/여가', value: '0', isChecked: false, class: "badge-primary"},
          { text: '경제/사회', value: '1', isChecked: false, class: "badge-secondary"},
          { text: '인문/예술', value: '2', isChecked: false, class: "badge-success"},
          { text: '문학/고전', value: '3', isChecked: false, class: "badge-danger"},
          { text: '전문/수험서', value: '4', isChecked: false, class: "badge-warning"},
          { text: 'IT/과학', value: '5', isChecked: false, class: "badge-info"},
          { text: '유아/어린이', value: '6', isChecked: false, class: "badge-light"},
          { text: '청소년/만화', value: '7', isChecked: false, class: "badge-dark"},
          { text: '자기계발', value: '8', isChecked: false, class: "badge-success"},
      ],
      checkedValues: [],
      checkedGenres: [],
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
        this.checkedValues.splice(idx, 1)
      }
      console.log(this.checkedValues)
    },
    goToComplete() {
      this.changeGenreState()
      // this.$store.commit('SubmitPref', this.checkedGenres)
      // this.$router.push({ name: "SignupComplete" })
    },
    // 모여있는 장르데이터를 분해해서 넣어주기
    changeGenreState() {
      for (let option of this.checkedValues) {
        console.log(option)
        if (option.value == 0) {
          this.checkedGenres.push(
            {"genreId": 1, "genreName": "가정/요리/뷰티"},
            {"genreId": 2, "genreName": "건강/취미/레저"},
            {"genreId": 14, "genreName": "여행"},
            {"genreId": 22, "genreName": "잡지"},
          )
        } else if (option.value == 1) {
          this.checkedGenres.push(
            {"genreId": 3, "genreName": "경제경영"},
            {"genreId": 9, "genreName": "사회과학"},
          )
        } else if (option.value == 2) {
          this.checkedGenres.push(
            {"genreId": 19, "genreName": "인문학"},
            {"genreId": 23, "genreName": "종교/역학"},
            {"genreId": 16, "genreName": "예술/대중문화"},
            {"genreId": 15, "genreName": "역사"},
          )
        } else if (option.value == 3) {
          this.checkedGenres.push(
            {"genreId": 4, "genreName": "고전"},
            {"genreId": 10, "genreName": "소설/시/희곡"},
            {"genreId": 13, "genreName": "에세이"},
            {"genreId": 21, "genreName": "장르소설"},
          )
        } else if (option.value == 4) {
          this.checkedGenres.push(
            {"genreId": 17, "genreName": "외국어"},
            {"genreId": 6, "genreName": "대학교재/전문서적"},
            {"genreId": 11, "genreName": "수험서/자격증"},
            {"genreId": 27, "genreName": "초등학교참고서"},
            {"genreId": 28, "genreName": "중학교참고서"},
            {"genreId": 29, "genreName": "고등학교참고서"},
          )
        } else if (option.value == 5) {
          this.checkedGenres.push(
            {"genreId": 5, "genreName": "과학"},
            {"genreId": 26, "genreName": "컴퓨터/모바일"},
          )
        } else if (option.value == 6) {
          this.checkedGenres.push(
            {"genreId": 18, "genreName": "유아"},
            {"genreId": 12, "genreName": "어린이"},
            {"genreId": 24, "genreName": "좋은부모"},
          )
        } else if (option.value == 7) {
          this.checkedGenres.push(
            {"genreId": 7, "genreName": "만화"},
            {"genreId": 25, "genreName": "청소년"},
          )
        } else if (option.value == 8) {
          this.checkedGenres.push(
            {"genreId": 20, "genreName": "자기계발"},
          )
        }
      }
      console.log(this.checkedGenres)
      this.$store.commit('SubmitPref', this.checkedGenres)
      this.$router.push({ name: "SignupComplete" })
    }
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
  .custom-control {
    display: inline-block;
    width: 130px;
    line-height: 25px;
    margin-top: 7px;
    margin-bottom: 20px;
    padding-left: 15px;
  }
  .checkvalues {
    margin-bottom: 5px;
    margin-top: 0px;
  }
</style>