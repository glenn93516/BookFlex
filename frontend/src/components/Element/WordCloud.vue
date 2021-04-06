<template>
  <div>
    <div v-if="isEmpty">
      <vue-word-cloud
        style="
          height: 550px;
          width: 450px;
          margin-left: 30px;
          cursor: pointer;
        "
        :words="words"
        :color="color"
        font-family="Anton"
        font-weight="Bold"
        :font-size-ratio="Ratio"
        @click="wordClickHandler(word)"
        :animation-overlap="property.overlap"
        :animation-easing="property.easing"
        :animation-duration="property.duration"
        :rotation="rotation"
      >
        <!-- 텍스트(커스텀 가능) -->
        <template v-slot="props">
          <div 
            :id="props.text" 
            class="cloud_text"
            @click="$emit('open-modal', props.text)"  
          >
            {{ props.text }}
          </div>
          <!-- 마우스 호버이벤트 -->
          <b-tooltip :target="props.text">
            {{ props.text }}
            <br>
            출현 빈도 : <strong>{{ props.weight }}</strong>
          </b-tooltip>
        </template>
      </vue-word-cloud>
    </div>

    <!-- 리뷰 데이터 부재시 보여줄 화면 -->
    <div 
      v-else 
      style="height: 275px; width: 450px; z-index: 10; text-align: center;"
    >
      <h2 style="margin-top: 300px; margin-bottom: 0px; font-weight: bold; color: grey; margin-left: 50px;">
        리뷰 데이터 수집중 :)
      </h2>
    </div>
  </div>
</template>

<script>
import VueWordCloud from 'vuewordcloud';
let Chance = require('chance')
let chance = new Chance();

export default {
  name: "WordCloud",
  props: {
    isbn: String,
  },
  components: {
    VueWordCloud
  },
  data() {
    return {
      isEmpty: "",
      words: [],
      property: {
        overlap: 2,
        easing: "ease",
        duration: 500,
      },
      Ratio: 5,
      colorItemIndex: undefined,
      colorItems: [
        // 원하는 컬러 조합 추가 or 제거 하세요. 
        ['#FEDE00', '#B4F8C8', '#6AB8EE'],
        ['#4C5270', '#F652A0', '#36EEE0', '#BCECE0'],
        ['#ffd077', '#3bc4c7', '#3a9eea', '#ff4e69', '#461e47'],
        ['#DB1F48', '#004369', '#01949A', '#E5DDC8'],
        ['#E7DED9', '#13292A', '#988780']
      ],
      rotationItemIndex: undefined,
      rotationItems: [
        {
          value: 0,
        },
        {
          value: function() {
            let chance = new Chance();
            return chance.pickone([0, 3/4]);
          },
        },
        {
          value: function() {
            let chance = new Chance();
            return chance.pickone([0, 1/8, 3/4, 7/8]);
          },
        },
      ],
      snackbarText: '',
      snackbarVisible: false,
    }
  },
  computed: {
    // colorItem에 있는 칼라를 랜덤으로 추출해서 바인딩
    color() {
      const colors = this.colorItems[this.colorItemIndex]
      return function() {
        return chance.pickone(colors)
      }
    },
    // rotationItem에 있는 각도를 랜덤으로 추출해서 바인딩
    rotation() {
      const item = this.rotationItems[this.rotationItemIndex];
      return item.value;
    }
  },
  created() {
    this.getWordCloud(this.isbn)
    // 칼라 조합 랜덤 선택
    this.colorItemIndex = chance.integer({min: 0, max: this.colorItems.length - 1});
    // 단어 구성 형태 랜덤 선택(십자, 원형)
    this.rotationItemIndex = chance.integer({min: 0, max: this.rotationItems.length - 1});
  },
  methods: {
    // 단어 클릭 이벤트 메서드
    onWordClick: function(word) {
      this.snackbarVisible = true;
      this.snackbarText = word[0];
    },
    // 단어 가져오기 [단어, 출현빈도]
    getWordCloud(isbn) {
      this.$axios.get(`${this.$store.getters.getServer}/book/${isbn}/wordcloud`)
      .then(res => {
        // console.log(res.data.data)
        this.words = res.data.data.slice(0, 30)
        this.isEmpty = res.data.data.length
        // console.log(this.isEmpty)
      })
      .catch(err => {
        console.error(err)
      })
    },
  }
}
</script>

<style>
  .cloud_text {
    text-shadow: 1px 1px 2px rgba(0, 0, 0, 0.4);
    transition: 0.5s;
  }
  .cloud_text:hover {
    -webkit-transform: scale(1.1,1.1);
    -moz-transform: scale(1.1,1.1);
    -o-transform: scale(1.1,1.1);
    -ms-transform: scale(1.1,1.1);
    transform: scale(1.1,1.1);
  }
</style>