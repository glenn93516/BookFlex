<template>
  <figure class="highcharts-figure">
    <div id="container"></div>
  </figure>
</template>

<script src="https://code.highcharts.com/highcharts.js"></script>
<script src="https://code.highcharts.com/highcharts-more.js"></script>
<script src="https://code.highcharts.com/modules/exporting.js"></script>
<script src="https://code.highcharts.com/modules/accessibility.js"></script>
<script>

export default {
  data() {
    return {

    }
  },
  mounted() {
    Highcharts.chart('container', {
      caption: {
        // align: center,
        // 왜 위로 안가지 @!@!@!@
        verticalAlign: top,
        floating: top,
      },
      chart: {
        type: 'packedbubble',
        height: '100%',
        backgroundColor: '#EDEAE8'
      },
      title: {
        text: null
      },
      tooltip: {
        followPointer: true,
        // useHTML: true,
        pointFormat: '<b>{point.name}:</b> {point.value}'
      },
      plotOptions: {
        packedbubble: {
          // zMin은 0, zMax는 100, minSize는 30%, maxSize는 120%
          minSize: '15%',
          maxSize: '130%',
          zMin: 0,
          zMax: 100,
          layoutAlgorithm: {
            splitSeries: false,
            gravitationalConstant: 0.02
          },
          dataLabels: {
            enabled: true,
            format: '{point.name}',
            filter: {
              // y value가 10이상인 것만 보여주겠다 (인덱스 이름)
              property: 'y',
              operator: '>',
              value: 10
            },
            style: {
              color: 'black',
              textOutline: 'none',
              fontWeight: 'normal'
            }
          }
        }
      },
      series: [{
        name: '취미/여가',
        data: [{
          name: '가정/요리/뷰티',
          value: 76.1
        }, {
          name: '건강/취미/레저',
          value: 20.7
        },
        {
          name: "여행",
          value: 9.2
        },
        {
          name: "잡지",
          value: 11.7
        }]
      }, {
        name: '문학',
        data: [{
          name: "고전",
          value: 8.2
        },
        {
          name: "소설/시/희곡",
          value: 9.2
        },
        {
          name: "에세이",
          value: 13.1
        },
        {
          name: "장르소설",
          value: 14.1
        }]
      }, {
        name: '유아/어린이',
        data: [{
          name: "유아",
          value: 40.4
        },
        {
          name: "어린이",
          value: 34.1
        },
        {
          name: "좋은부모",
          value: 7.1
        }]
      }, {
        name: '경제/사회',
        data: [{
          name: "경제경영",
          value: 7.6
        },
        {
          name: "사회과학",
          value: 8.4
        }]
      }, {
        name: '수험서/전공서적',
        data: [{
          name: "대학교재/전문서적",
          value: 7.2
        },
        {
          name: "수험서/자격증",
          value: 8.1
        },
        {
          name: "외국어",
          value: 17.8
        },
        {
          name: "초등학교참고서",
          value: 34
        },
        {
          name: "중학교참고서",
          value: 43
        },
        {
          name: "고등학교참고서",
          value: 7.6
        }]
      }, {
        name: '청소년/만화',
        data: [{
          name: "만화",
          value: 6.5
        },
        {
          name: "청소년",
          value: 6.5
        }]
      }, {
        name: '인문/예술/역사',
        data: [{
          name: "인문학",
          value: 6.5
        },
        {
          name: "종교/역학",
          value: 6.5
        },
        {
          name: "예술/대중문화",
          value: 7.4
        },
        {
          name: "역사",
          value: 7.4
        }]
      }, {
        name: '자기계발',
        data: [{
          name: "자기계발",
          value: 6.5
        }]
      }, {
        name: 'IT/과학',
        data: [{
          name: "과학",
          value: 6.5
        },
        {
          name: "컴퓨터/모바일",
          value: 6.5
        }]
      }]
    });
  },
}
</script>

<style>
.highcharts-figure, .highcharts-data-table table {
  min-width: 320px; 
  max-width: 800px;
  margin: 1em auto;
}

.highcharts-data-table table {
	font-family: Verdana, sans-serif;
	border-collapse: collapse;
	border: 1px solid #EBEBEB;
	margin: 10px auto;
	text-align: center;
	width: 100%;
	max-width: 500px;
}
.highcharts-data-table caption {
  padding: 1em 0;
  font-size: 1.2em;
  color: #555;
}
.highcharts-data-table th {
	font-weight: 600;
  padding: 0.5em;
}
.highcharts-data-table td, .highcharts-data-table th, .highcharts-data-table caption {
  padding: 0.5em;
}
.highcharts-data-table thead tr, .highcharts-data-table tr:nth-child(even) {
  background: #f8f8f8;
}
.highcharts-data-table tr:hover {
  background: #f1f7ff;
}
</style>