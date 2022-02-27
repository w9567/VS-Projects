<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>欢迎登录泉师释疑后台页面</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
<link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />
<link rel="stylesheet" href="./css/font.css">
<link rel="stylesheet" href="./css/xadmin.css">
</head>
<body>
	<div class="x-body layui-anim layui-anim-up">
		<blockquote class="layui-elem-quote">
			欢迎管理员：
			<span class="x-red">test</span>
			！当前时间:<s:property value="#computerInfo.time"/>
		</blockquote>
		<fieldset class="layui-elem-field">
			<legend>数据统计</legend>
			<div class="layui-field-box">
				<div class="layui-col-md12">
					<div class="layui-card">
						<div class="layui-card-body">
							<div class="layui-carousel x-admin-carousel x-admin-backlog" lay-anim="" lay-indicator="inside" lay-arrow="none" style="width: 100%; height: 90px;">
								<div carousel-item="">
									<ul class="layui-row layui-col-space10 layui-this">
										<li class="layui-col-xs2">
											<a href="javascript:;" class="x-admin-backlog-body">
												<h3>用户数</h3>
												<p>
													<cite> <s:property value="userCount"/> </cite>
												</p>
											</a>
										</li>
										<li class="layui-col-xs2">
											<a href="javascript:;" class="x-admin-backlog-body">
												<h3>帖子数</h3>
												<p>
													<cite><s:property value="pasteCount"/></cite>
												</p>
											</a>
										</li>
										<li class="layui-col-xs2">
											<a href="javascript:;" class="x-admin-backlog-body">
												<h3>回复数</h3>
												<p>
													<cite><s:property value="answerCount"/></cite>
												</p>
											</a>
										</li>
										<li class="layui-col-xs2">
											<a href="javascript:;" class="x-admin-backlog-body">
												<h3>点赞数</h3>
												<p>
													<cite><s:property value="praiseCount"/></cite>
												</p>
											</a>
										</li>
										
									</ul>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</fieldset>
		<fieldset class="layui-elem-field">
			<legend>系统通知</legend>
			<div class="layui-field-box">
				<table class="layui-table" lay-skin="line">
					<tbody>
						<tr>
							<td>
								<a class="x-a" href="https://www.cnblogs.com/jiguiyan/" target="_blank">新版泉师释疑发布了~~</a>
							</td>
						</tr>
						<tr>
							<td>
								<a class="x-a" href="/" target="_blank">交流qq:(xxxxx)</a>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</fieldset>
		<fieldset class="layui-elem-field">
			<legend>系统信息</legend>
			<div class="layui-field-box">
				<table class="layui-table">
					<tbody>
						<tr>
							<th>操作系统</th>
							<td><s:property value="#computerInfo.os"/></td>
						</tr>
						<tr>
							<th>ip地址</th>
							<td><s:property value="#computerInfo.ip"/></td>
						</tr>
						<tr>
							<th>计算机名称</th>
							<td><s:property value="#computerInfo.computerName"/></td>
						</tr>
						
						<tr>
							<th>Java版本</th>
							<td>JDK 1.8</td>
						</tr>
						<tr>
							<th>MYSQL版本</th>
							<td>5.7</td>
						</tr>
						<tr>
							<th>内存总量</th>
							<td><s:property value="#computerInfo.memTotal"/></td>
						</tr>
						<tr>
							<th>当前已使用内存</th>
							<td><s:property value="#computerInfo.memUse"/></td>
						</tr>
						<tr>
							<th>剩余内存</th>
							<td><s:property value="#computerInfo.memFree"/></td>
						</tr>
						
					</tbody>
				</table>
			</div>
		</fieldset>
		<fieldset class="layui-elem-field">
			<legend>开发团队</legend>
			<div class="layui-field-box">
				<table class="layui-table">
					<tbody>
						<tr>
							<th>版权所有</th>
							<td>
								xxxxxx
								<a href="https://www.cnblogs.com/jiguiyan/" class='x-a' target="_blank">访问官网</a>
							</td>
						</tr>
						<tr>
							<th>开发者</th>
							<td>xxxxx </td>
						</tr>
					</tbody>
				</table>
			</div>
		</fieldset>
		<blockquote class="layui-elem-quote layui-quote-nm">感谢layui,百度Echarts,jquery。</blockquote>
	</div>
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