import { Doughnut } from 'vue-chartjs'

export default {
  extends: Doughnut,
  data () {
    return {
      options: {
        responsive: false,
        maintainAspectRatio: true,

        legend:{
          display:true, // 차트 위에 label 표시 여부
          labels: {
            fontSize: 20
          }
        },

        tooltips: {
          enabled: true // 차트에 마우스 올렷을때 데이터 표시 여부
        },

        // hover: {
        //   animationDuration: 0
        // },

        scales: {

          yAxes: [{
            // display: false, // y축 기준 표시 여부
            ticks: { // y축 기준 데이터 변경
              // beginAtZero:true,
              // min: 0, // y축 최소값
              // max: 100, // y축 최대값
              stepSize: 10, // y축 간격
              fontSize : 0, // 글자크기
            },
            gridLines: {
              display: false, // 차트 안에 가로선 표시 여부
              drawBorder: false
            }
          }],

          xAxes: [{
            display: false,
            gridLines:{
              display: false,
              brawBorder:false
            }
          }]

        }, // scales 끝
      }
    }
  },
  props: {
    chartData: {
      type: Object,
      default: null
    },
  },
  mounted () {
    this.renderChart(this.chartData, this.options)
  }
}