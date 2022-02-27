<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="/struts-tags" prefix="s" %>
<!doctype html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>后台登录</title>
        <meta name="renderer" content="webkit|ie-comp|ie-stand">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
        <meta http-equiv="Cache-Control" content="no-siteapp" />

        <link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />
        <link rel="stylesheet" href="./css/font.css">
        <link rel="stylesheet" href="./css/xadmin.css">
        <script type="text/javascript" src="./js/echarts.js"></script>
    </head>
    <body>
        <div class="x-body">
            <blockquote class="layui-elem-quote">
              特别声明：ECharts，一个纯 Javascript 的图表库，可以流畅的运行在 PC 和移动设备上，兼容当前绝大部分浏览器（IE8/9/10/11，Chrome，Firefox，Safari等），底层依赖轻量级的 Canvas 类库 ZRender，提供直观，生动，可交互，可高度个性化定制的数据可视化图表。如需使用，详细了解可以访问官网 <a href="https://echarts.baidu.com/" style="color:red">ECharts</a>。 本人不承担任何版权问题。
             </blockquote>
           
             
              
               
            <!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
            <div id="main" style="width: 100%;height:500px;"></div>
            <blockquote class="layui-elem-quote">
                     注意：本项目的Echarts图表库由cdn引入，需要在线才能正常使用。
           </blockquote>
        </div>
     	
        <script type="text/javascript">
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('main'));

        // 指定图表的配置项和数据
        var option = {
        	    tooltip: {
        	        trigger: 'item',
        	        formatter: "{a} <br/>{b}: {c} ({d}%)"
        	    },
        	    legend: {
        	        orient: 'vertical',
        	        x: 'left',
        	        data:['用户','帖子','点赞','回复','激活用户','未激活用户','完结帖子','未完结帖子','点赞','回复']
        	    },
        	    series: [
        	        {
        	            name:'访问来源',
        	            type:'pie',
        	            selectedMode: 'single',
        	            radius: [0, '30%'],

        	            label: {
        	                normal: {
        	                    position: 'inner'
        	                }
        	            },
        	            labelLine: {
        	                normal: {
        	                    show: false
        	                }
        	            },
        	            data:[
        	                {value:<s:property value="#userCount"/>, name:'用户', selected:true},
        	                {value:<s:property value="#pasteCount"/>, name:'帖子'},
        	                {value:<s:property value="#praiseCount"/>, name:'点赞'},
        	                {value:<s:property value="#answerCount"/>, name:'回复'}
        	            ]
        	        },
        	        {
        	            name:'访问来源',
        	            type:'pie',
        	            radius: ['40%', '55%'],
        	            label: {
        	                normal: {
        	                    formatter: '{a|{a}}{abg|}\n{hr|}\n  {b|{b}：}{c}  {per|{d}%}  ',
        	                    backgroundColor: '#eee',
        	                    borderColor: '#aaa',
        	                    borderWidth: 1,
        	                    borderRadius: 4,
        	                    // shadowBlur:3,
        	                    // shadowOffsetX: 2,
        	                    // shadowOffsetY: 2,
        	                    // shadowColor: '#999',
        	                    // padding: [0, 7],
        	                    rich: {
        	                        a: {
        	                            color: '#999',
        	                            lineHeight: 22,
        	                            align: 'center'
        	                        },
        	                        // abg: {
        	                        //     backgroundColor: '#333',
        	                        //     width: '100%',
        	                        //     align: 'right',
        	                        //     height: 22,
        	                        //     borderRadius: [4, 4, 0, 0]
        	                        // },
        	                        hr: {
        	                            borderColor: '#aaa',
        	                            width: '100%',
        	                            borderWidth: 0.5,
        	                            height: 0
        	                        },
        	                        b: {
        	                            fontSize: 16,
        	                            lineHeight: 33
        	                        },
        	                        per: {
        	                            color: '#eee',
        	                            backgroundColor: '#334455',
        	                            padding: [2, 4],
        	                            borderRadius: 2
        	                        }
        	                    }
        	                }
        	            },
        	            data:[
        	            	//激活未激活
        	                {value:<s:property value="#activeUserCount"/>, name:'激活用户'},
        	                {value:<s:property value="#normalUserCount"/>, name:'未激活用户'},
        	                //完结未完结
        	                {value:<s:property value="#overPasteCount"/>, name:'完结帖子'},
        	                {value:<s:property value="#normalPasteCount"/>, name:'未完结帖子'},
        	                
        	                {value:<s:property value="#praiseCount"/>, name:'点赞'},
            	            {value:<s:property value="#answerCount"/>, name:'回复'}
        	            ]
        	        }
        	    ]
        	};

        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
    </script>
    <script>
        var _hmt = _hmt || [];
        (function() {
          var hm = document.createElement("script");
          hm.src = "https://hm.baidu.com/hm.js?b393d153aeb26b46e9431fabaf0f6190";
          var s = document.getElementsByTagName("script")[0]; 
          s.parentNode.insertBefore(hm, s);
        })();
        </script>
    </body>
</html>