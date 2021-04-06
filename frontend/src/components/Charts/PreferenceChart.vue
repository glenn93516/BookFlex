<template>
  <figure class="highcharts-figure">
    <div id="preferenceChart"></div>
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
      userInfo: null,
    }
  },
  created() {
  },
  mounted() {
    this.$axios.get(`${this.$store.getters.getServer}/user/${this.$route.params.userName}/statistics`)
    .then(res => {
      this.userInfo = res.data
      this.makeChart()
    })
    .catch(err => {
      console.log(err, 'err')
    })
  },
  methods: {
    makeChart() {
      Highcharts.chart('preferenceChart', {
        legend: {
          align: 'top',
          verticalAlign: 'top',
          layout: 'vertical',
          x: 0, 
          y: 0,
        },
        chart: {
          type: 'packedbubble',
          height: '100%',
          backgroundColor: '#EDEAE8',

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
              verticalAlign: 'top',
              enabled: true,
              format: '{point.name}',
              filter: {
                // y value가 10이상인 것만 보여주겠다 (인덱스 이름)
                property: 'y',
                operator: '>',
                value: 20
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
          name: this.userInfo[0].category_name,
          data: [{
            name: this.userInfo[0].genres[0].genre_name,
            value: this.userInfo[0].genres[0].value
          }, {
            name: this.userInfo[0].genres[1].genre_name,
            value: this.userInfo[0].genres[1].value
          },
          {
            name: this.userInfo[0].genres[2].genre_name,
            value: this.userInfo[0].genres[2].value
          },
          {
            name: this.userInfo[0].genres[3].genre_name,
            value: this.userInfo[0].genres[3].value
          }]
        }, {
          name: this.userInfo[1].category_name,
          data: [{
            name: this.userInfo[1].genres[0].genre_name,
            value: this.userInfo[1].genres[0].value
          },
          {
            name: this.userInfo[1].genres[1].genre_name,
            value: this.userInfo[1].genres[1].value
          },
          {
            name: this.userInfo[1].genres[2].genre_name,
            value: this.userInfo[1].genres[2].value
          },
          {
            name: this.userInfo[1].genres[3].genre_name,
            value: this.userInfo[1].genres[3].value
          }]
        }, {
          name: this.userInfo[2].category_name,
          data: [{
            name: this.userInfo[2].genres[0].genre_name,
            value: this.userInfo[2].genres[0].value
          },
          {
            name: this.userInfo[2].genres[1].genre_name,
            value: this.userInfo[2].genres[1].value
          },
          {
            name: this.userInfo[2].genres[2].genre_name,
            value: this.userInfo[2].genres[2].value
          }]
        }, {
          name: this.userInfo[3].category_name,
          data: [{
            name: this.userInfo[3].genres[0].genre_name,
            value: this.userInfo[3].genres[0].value
          },
          {
            name: this.userInfo[3].genres[1].genre_name,
            value: this.userInfo[3].genres[1].value
          }]
        }, {
          name: this.userInfo[4].category_name,
          data: [{
            name: this.userInfo[4].genres[0].genre_name,
            value: this.userInfo[4].genres[0].value
          },
          {
            name: this.userInfo[4].genres[1].genre_name,
            value: this.userInfo[4].genres[1].value
          },
          {
            name: this.userInfo[4].genres[2].genre_name,
            value: this.userInfo[4].genres[2].value
          },
          {
            name: this.userInfo[4].genres[3].genre_name,
            value: this.userInfo[4].genres[3].value
          },
          {
            name: this.userInfo[4].genres[4].genre_name,
            value: this.userInfo[4].genres[4].value
          },
          {
            name: this.userInfo[4].genres[5].genre_name,
            value: this.userInfo[4].genres[5].value
          }]
        }, {
          name: this.userInfo[5].category_name,
          data: [{
            name: this.userInfo[5].genres[0].genre_name,
            value: this.userInfo[5].genres[0].value
          },
          {
            name: this.userInfo[5].genres[1].genre_name,
            value: this.userInfo[5].genres[1].value
          }]
        }, {
          name: this.userInfo[6].category_name,
          data: [{
            name: this.userInfo[6].genres[0].genre_name,
            value: this.userInfo[6].genres[0].value
          },
          {
            name: this.userInfo[6].genres[1].genre_name,
            value: this.userInfo[6].genres[1].value
          },
          {
            name: this.userInfo[6].genres[2].genre_name,
            value: this.userInfo[6].genres[2].value
          },
          {
            name: this.userInfo[6].genres[3].genre_name,
            value: this.userInfo[6].genres[3].value
          }]
        }, {
          name: this.userInfo[7].category_name,
          data: [{
            name: this.userInfo[7].genres[0].genre_name,
            value: this.userInfo[7].genres[0].value
          }]
        }, {
          name: this.userInfo[8].category_name,
          data: [{
            name: this.userInfo[8].genres[0].genre_name,
            value: this.userInfo[8].genres[0].value
          },
          {
            name: this.userInfo[8].genres[1].genre_name,
            value: this.userInfo[8].genres[1].value
          }]
        }]
      });
    }
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