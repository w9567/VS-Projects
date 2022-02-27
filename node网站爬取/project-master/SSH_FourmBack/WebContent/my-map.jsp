<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="./js/echarts.js"></script>
</head>
<body>

	<!-- 准备地方 （准备DOM）-->
	<div id="main" style="width: 100%;height: 400px"></div>
	
	<script type="text/javascript">
		//初始化echarts
		var myChart=echarts.init(document.getElementById('main'));
		
		var option = {
			    angleAxis: {
			    },
			    radiusAxis: {
			        type: 'category',
			        data: ['周一', '周二', '周三', '周四'],
			        z: 10
			    },
			    polar: {
			    },
			    series: [{
			        type: 'bar',
			        data: [1, 2, 3, 4],
			        coordinateSystem: 'polar',
			        name: 'A',
			        stack: 'a'
			    }, {
			        type: 'bar',
			        data: [2, 4, 6, 8],
			        coordinateSystem: 'polar',
			        name: 'B',
			        stack: 'a'
			    }, {
			        type: 'bar',
			        data: [1, 2, 3, 4],
			        coordinateSystem: 'polar',
			        name: 'C',
			        stack: 'a'
			    }],
			    legend: {
			        show: true,
			        data: ['A', 'B', 'C']
			    }
			};

		
		/*var option = {
				//标题
			    title: {
			        text: '折线图堆叠'
			    },
			    //提示
			    tooltip: {
			        trigger: 'axis'
			    },
			    //说明
			    legend: {
			        data:['邮件营销','联盟广告','视频广告','直接访问','搜索引擎']
			    },
			    //格子
			    grid: {
			        left: '3%',
			        right: '4%',
			        bottom: '3%',
			        containLabel: true
			    },
			    //工具箱
			    toolbox: {
			        feature: {
			            saveAsImage: {}
			        }
			    },
			    //x轴
			    xAxis: {
			        type: 'category',
			        boundaryGap: false,
			        data: ['周一','周二','周三','周四','周五','周六','周日']
			    },
			    //y轴
			    yAxis: {
			        type: 'value'
			    },
			    //数据序列
			    series: [
			        {
			            name:'邮件营销',
			            type:'line',
			            stack: '总量',
			            data:[120, 132, 101, 134, 90, 230, 210]
			        },
			        {
			            name:'联盟广告',
			            type:'line',
			            stack: '总量',
			            data:[220, 182, 191, 234, 290, 330, 310]
			        },
			        {
			            name:'视频广告',
			            type:'line',
			            stack: '总量',
			            data:[150, 232, 201, 154, 190, 330, 410]
			        },
			        {
			            name:'直接访问',
			            type:'line',
			            stack: '总量',
			            data:[320, 332, 301, 334, 390, 330, 320]
			        },
			        {
			            name:'搜索引擎',
			            type:'line',
			            stack: '总量',
			            data:[820, 932, 901, 934, 1290, 1330, 1320]
			        }
			    ]
			};*/
			//图表的显示
			myChart.setOption(option);
	</script>

</body>
</html>