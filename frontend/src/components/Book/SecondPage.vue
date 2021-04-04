<template>
  <!-- 리뷰 분석, 담긴 문장 페이지 -->
  <div class="row">
    <!-- 왼쪽 페이지: 비지도학습 키워드 분석 -->
    <div class="col-6 book_size" style="border-right: 1px solid rgba(121, 121, 121, 0.692); height: 700px;">
      <div 
        style="text-align: left; 
                  margin-top: 20px; 
                  margin-left: 20px;
                  margin-bottom: 50px;
                  color: rgba(100, 100, 100);
                  ">
        <h6 class="mr-1">도서 분류 분석</h6>
        <hr style="rgba(50, 50, 50); margin-bottom: 40px;">
      </div>
      <div style="margin-left: 20px; width: 450px; height: auto;">
        <figure class="highcharts-figure">
          <div id="container"></div>
          <p class="highcharts-description">
            Variable radius pie charts can be used to visualize a
            second dimension in a pie chart. In this chart, the more
            densely populated countries are drawn further out, while the
            slice width is determined by the size of the country.
          </p>
        </figure>
      </div>
    </div>

    <!-- 오른쪽 페이지: 지도학습 긍부정 분석 + 한줄문장 -->
    <div class="col-6 book_size">
      <div 
        style="text-align: right; 
                  margin-top: 20px; 
                  margin-right: 20px;
                  margin-bottom: 50px;
                  color: rgba(100, 100, 100);
                  ">
        <h6 class="mr-1">리뷰 감정 분석</h6>
        <hr style="rgba(50, 50, 50); margin-bottom: 40px;">
      </div>
      <div style="margin-right: 20px;">

      </div>

    </div>

  </div>
</template>

<script src="https://code.highcharts.com/highcharts.js"></script>
<script src="https://code.highcharts.com/modules/variable-pie.js"></script>
<script src="https://code.highcharts.com/modules/exporting.js"></script>
<script src="https://code.highcharts.com/modules/export-data.js"></script>
<script src="https://code.highcharts.com/modules/accessibility.js"></script>
<script>
export default {
  mounted() {
    Highcharts.chart('container', {
      chart: {
        type: 'variablepie'
      },
      title: {
        text: ''
      },
      tooltip: {
        headerFormat: '',
        pointFormat: '<span style="color:{point.color}">\u25CF</span> <b> {point.name}</b><br/>' +
          'Area (square km): <b>{point.y}</b><br/>' +
          'Population density (people per square km): <b>{point.z}</b><br/>'
      },
      series: [{
        minPointSize: 10,
        innerSize: '20%',
        zMin: 0,
        name: 'countries',
        data: [{
          name: 'Spain',
          y: 505370,
          z: 92.9
        }, {
          name: 'France',
          y: 551500,
          z: 118.7
        }, {
          name: 'Poland',
          y: 312685,
          z: 124.6
        }, {
          name: 'Czech Republic',
          y: 78867,
          z: 137.5
        }, {
          name: 'Italy',
          y: 301340,
          z: 201.8
        }, {
          name: 'Switzerland',
          y: 41277,
          z: 214.5
        }, {
          name: 'Germany',
          y: 357022,
          z: 235.6
        }]
      }]
    });
  }

}
</script>

<style>
#container {
 height: 300px;
}

.highcharts-figure, .highcharts-data-table table {
  min-width: 320px; 
  max-width: 700px;
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

.book_size{

}
</style>