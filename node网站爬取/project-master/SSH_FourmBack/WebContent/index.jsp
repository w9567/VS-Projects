<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ taglib uri="/struts-tags" prefix="s" %>
<!doctype html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>泉师释疑后台</title>
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
<meta http-equiv="Cache-Control" content="no-siteapp" />

<link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />
<link rel="stylesheet" href="./css/font.css">
<link rel="stylesheet" href="./css/xadmin.css">
<script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<script src="./lib/layui/layui.js" charset="utf-8"></script>
<script type="text/javascript" src="./js/xadmin.js"></script>

</head>
<body>
	<!-- 顶部开始 -->
	<div class="container">
		<div class="logo">
			<a href="./index.jsp">泉师释疑</a>
		</div>
		<div class="left_open">
			<i title="展开左侧栏" class="iconfont">&#xe699;</i>
		</div>
		<ul class="layui-nav left fast-add" lay-filter="">
			<li class="layui-nav-item">
				<a href="javascript:;">+新增</a>
				<dl class="layui-nav-child">
					<!-- 二级菜单 -->
					<dd>
						<a onclick="x_admin_show('资讯','http://www.baidu.com')">
							<i class="iconfont">&#xe6a2;</i>
							资讯
						</a>
					</dd>
					<dd>
						<a onclick="x_admin_show('图片','http://www.baidu.com')">
							<i class="iconfont">&#xe6a8;</i>
							图片
						</a>
					</dd>
					<dd>
						<a onclick="x_admin_show('用户','http://www.baidu.com')">
							<i class="iconfont">&#xe6b8;</i>
							用户
						</a>
					</dd>
				</dl>
			</li>
		</ul>
		<ul class="layui-nav right" lay-filter="">
			<li class="layui-nav-item">
				<a href="javascript:;">admin</a>
				<dl class="layui-nav-child">
					<!-- 二级菜单 -->
					<dd>
						<a onclick="x_admin_show('个人信息','http://www.baidu.com')">个人信息</a>
					</dd>
					<dd>
						<a onclick="x_admin_show('切换帐号','http://www.baidu.com')">切换帐号</a>
					</dd>
					<dd>
						<a href="./login.jsp">退出</a>
					</dd>
				</dl>
			</li>
			<li class="layui-nav-item to-index">
				<a href="/">前台首页</a>
			</li>
		</ul>

	</div>
	<!-- 顶部结束 -->
	<!-- 中部开始 -->
	<!-- 左侧菜单开始 -->
	<div class="left-nav">
		<div id="side-nav">
			<ul id="nav">
				<li>
					<a href="javascript:;">
						<i class="iconfont">&#xe6b8;</i>
						<cite>用户管理</cite>
						<i class="iconfont nav_right">&#xe697;</i>
					</a>
					<ul class="sub-menu">
						<li>
							<a _href="${pageContext.request.contextPath }/UserAction_getAllUser?isDelete=0">
								<i class="iconfont">&#xe6a7;</i>
								<cite>用户列表</cite>

							</a>
						</li>
						<li>
							<a _href="${pageContext.request.contextPath }/UserAction_getAllUser?isDelete=1">
								<i class="iconfont">&#xe6a7;</i>
								<cite>用户删除</cite>

							</a>
						</li>
					</ul>
				</li>
				<li>
					<a href="javascript:;">
						<i class="iconfont">&#xe6b5;</i>
						<cite>帖子管理</cite>
						<i class="iconfont nav_right">&#xe697;</i>
					</a>
					<ul class="sub-menu">
						
						<li>
							<a href="javascript:;">
								<i class="iconfont">&#xe70b;</i>
								<cite>帖子列表</cite>
								<i class="iconfont nav_right">&#xe697;</i>
							</a>
							<ul class="sub-menu">
								<li>
									<a _href="${pageContext.request.contextPath }/PasteAction_getSolvePaste?isDelete=0&solve=1">
										<i class="iconfont">&#xe6a7;</i>
										<cite>已完结</cite>

									</a>
								</li>
								<li>
									<a _href="${pageContext.request.contextPath }/PasteAction_getSolvePaste?isDelete=0&solve=0">
										<i class="iconfont">&#xe6a7;</i>
										<cite>未完结</cite>

									</a>
								</li>
					

							</ul>
						</li>
						<li>
							<a _href="${pageContext.request.contextPath }/PasteAction_getDeletePaste?isDelete=1">
								<i class="iconfont">&#xe6a7;</i>
								<cite>帖子删除</cite>

							</a>
						</li>
					</ul>
				</li>
				<li>
					<a href="javascript:;">
						<i class="iconfont">&#xe6ce;</i>
						<cite>系统统计</cite>
						<i class="iconfont nav_right">&#xe697;</i>
					</a>
					<ul class="sub-menu">
						<li>
							<a _href="${pageContext.request.contextPath }/GetDataAction_getAllData">
								<i class="iconfont">&#xe6a7;</i>
								<cite>总体数量显示</cite>
							</a>
						</li>
						<li>
							<a _href="${pageContext.request.contextPath }/GetDataAction_getDetailAllData">
								<i class="iconfont">&#xe6a7;</i>
								<cite>总体数量详细显示</cite>
							</a>
						</li>
						<li>
							<a _href="${pageContext.request.contextPath }/GetDataAction_getRadarData">
								<i class="iconfont">&#xe6a7;</i>
								<cite>总体数量雷达图</cite>
							</a>
						</li>
						<li>
							<a _href="${pageContext.request.contextPath }/GetDataAction_getDetailRadarData">
								<i class="iconfont">&#xe6a7;</i>
								<cite>帖子大比拼</cite>
							</a>
						</li>
						<li>
							<a _href="${pageContext.request.contextPath }/GetDataAction_getAWeekUserData">
								<i class="iconfont">&#xe6a7;</i>
								<cite>每周用户统计</cite>
							</a>
						</li>
						<li>
							<a _href="${pageContext.request.contextPath }/GetDataAction_getAWeekDetailData">
								<i class="iconfont">&#xe6a7;</i>
								<cite>每周数量统计</cite>
							</a>
						</li>
						
					</ul>
				</li>
				<li>
					<a href="javascript:;">
						<i class="iconfont">&#xe6b4;</i>
						<cite>图标字体</cite>
						<i class="iconfont nav_right">&#xe697;</i>
					</a>
					<ul class="sub-menu">
						<li>
							<a _href="unicode.html">
								<i class="iconfont">&#xe6a7;</i>
								<cite>图标对应字体</cite>
							</a>
						</li>
					</ul>
				</li>
			</ul>
		</div>
	</div>
	<!-- <div class="x-slide_left"></div> -->
	<!-- 左侧菜单结束 -->
	<!-- 右侧主体开始 -->
	<div class="page-content">
		<div class="layui-tab tab" lay-filter="xbs_tab" lay-allowclose="false">
			<ul class="layui-tab-title">
				<li class="home">
					<i class="layui-icon">&#xe68e;</i>
					我的桌面
				</li>
			</ul>
			<div class="layui-tab-content">
				<div class="layui-tab-item layui-show">
					<iframe src="${pageContext.request.contextPath }/GetDataAction_getData" frameborder="0" scrolling="yes" class="x-iframe"></iframe>
				</div>
			</div>
		</div>
	</div>
	<div class="page-content-bg"></div>
	<!-- 右侧主体结束 -->
	<!-- 中部结束 -->
	<!-- 底部开始 -->
	<div class="footer">
		<div class="copyright">Copyright ©2019 jiguiyan</div>
	</div>
	<!-- 底部结束 -->
	<script>
		//百度统计可去掉
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