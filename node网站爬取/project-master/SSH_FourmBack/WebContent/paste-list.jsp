<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>泉师释疑</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
<link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />
<link rel="stylesheet" href="./css/font.css">
<link rel="stylesheet" href="./css/xadmin.css">
<script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript" src="./lib/layui/layui.js" charset="utf-8"></script>
<script type="text/javascript" src="./js/xadmin.js"></script>
<!-- 让IE8/9支持媒体查询，从而兼容栅格 -->
<!--[if lt IE 9]>
      <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
      <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body class="layui-anim layui-anim-up">
	<div class="x-nav">
		<span class="layui-breadcrumb">
			<a href="">首页</a>
			<a href="">演示</a>
			<a>
				<cite>导航元素</cite>
			</a>
		</span>
		<a class="layui-btn layui-btn-small" style="line-height: 1.6em; margin-top: 3px; float: right" href="javascript:location.replace(location.href);" title="刷新">
			<i class="layui-icon" style="line-height: 30px">ဂ</i>
		</a>
	</div>
	<div class="x-body">
		<div class="layui-row">
			<form class="layui-form layui-col-md12 x-so" action="${pageContext.request.contextPath }/PasteAction_getPageBeanByCondition">
				<input type="hidden" name="solve" value="<s:property value="solve"/>">
				<input type="hidden" name="isDelete" value="<s:property value="isDelete"/>">
				<input class="layui-input" placeholder="开始日" name="start" id="start" value="<s:property value="start"/>">
				<input class="layui-input" placeholder="截止日" name="end" id="end" value="<s:property value="end"/>">
				<input type="text" name="title" placeholder="请输入标题" autocomplete="off" class="layui-input" value="<s:property value="title"/>">
				<button class="layui-btn" lay-submit="" lay-filter="sreach">
					<i class="layui-icon">&#xe615;</i>
				</button>
			</form>
		</div>
		<xblock> <s:if test="isDelete==0">
			<button class="layui-btn layui-btn-danger" onclick="delAll()">
				<i class="layui-icon"></i>
				批量删除
			</button>
		</s:if> <s:else>
			<button class="layui-btn layui-btn-danger" onclick="resAll()">
				<i class="layui-icon"></i>
				批量还原
			</button>
		</s:else>
		<button class="layui-btn" onclick="x_admin_show('添加用户','./user-add.jsp',600,400)">
			<i class="layui-icon"></i>
			添加
		</button>
		<span class="x-right" style="line-height: 40px">
			共有数据：
			<s:property value="#pastePageBean.totalCount" />
			条
		</span> </xblock>
		<table class="layui-table">
			<thead>
				<tr>
					<th>
						<div class="layui-unselect header layui-form-checkbox" lay-skin="primary">
							<i class="layui-icon">&#xe605;</i>
						</div>
					</th>
					<th>ID</th>
					<th>标题</th>
					<th>内容</th>
					<th>悬赏</th>
					<th>回复数</th>
					<th>创建时间</th>
					<th>浏览数</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="#pastePageBean.list" var="paste" status="status">
					<tr>
						<td>
							<div class="layui-unselect layui-form-checkbox" lay-skin="primary" data-id='2'>
								<i class="layui-icon">&#xe605;</i>
								<input type="hidden" name="id" value="<s:property value="#paste.id"/>">

							</div>
						</td>
						<td>
							<s:property value="#status.index+1" />
						</td>
						<td>
							<s:property value="#paste.title" />
						</td>
						<td>
							<s:property value="#paste.content" />
						</td>
						<td>
							<s:property value="#paste.offer" />
						</td>
						<td>
							<s:property value="#paste.ansnum" />
						</td>
						<td>
							<s:property value="#paste.createtime" />
						</td>
						
							<td class="td-status">
								<s:property value="#paste.glanceover" />
							</td>
						
					
						<td class="td-manage">
							<a onclick="user_stop(this,'<s:property value="#user.id"/>')" href="javascript:;" title="启用">
								<i class="layui-icon">&#xe601;</i>
							</a>
							<a title="编辑" onclick="x_admin_show('编辑','${pageContext.request.contextPath}/PasteAction_toEditPaste?id=<s:property value="#paste.id"/>',600,400)" href="javascript:;">
								<i class="layui-icon">&#xe642;</i>
							</a>
							
							<s:if test="isDelete==0">
								<a title="删除" onclick="paste_del(this,'<s:property value="#paste.id"/>')" href="javascript:;">
									<i class="layui-icon">&#xe640;</i>
								</a>
							</s:if>
							<s:else>
								<a title="还原" onclick="paste_res(this,'<s:property value="#paste.id"/>')" href="javascript:;">
									<i class="layui-icon">&#xe6aa;</i>
								</a>
							</s:else>
						</td>
					</tr>
				</s:iterator>
			</tbody>
		</table>
		<div class="page">
			<div>
				<a class="prev" href="${pageContext.request.contextPath }/PasteAction_getPageBeanByCondition?currentPage=<s:property value="#pastePageBean.currentPage-1"/>&start=<s:property value="start"/>&end=<s:property value="end"/>&title=<s:property value="title"/>&isDelete=<s:property value="isDelete"/>&solve=<s:property value="solve"/> ">&lt;&lt;</a>
				<s:if test="#pastePageBean.currentPage-2>0">
					<a class="num" href="${pageContext.request.contextPath }/PasteAction_getPageBeanByCondition?currentPage=<s:property value="#pastePageBean.currentPage-2"/>&start=<s:property value="start"/>&end=<s:property value="end"/>&title=<s:property value="title"/>&isDelete=<s:property value="isDelete"/>&solve=<s:property value="solve"/> ">
						<s:property value="#pastePageBean.currentPage-2" />
					</a>
				</s:if>
				<s:if test="#pastePageBean.currentPage-1>0">
					<a class="num" href="${pageContext.request.contextPath }/PasteAction_getPageBeanByCondition?currentPage=<s:property value="#pastePageBean.currentPage-1"/>&start=<s:property value="start"/>&end=<s:property value="end"/>&title=<s:property value="title"/>&isDelete=<s:property value="isDelete"/>&solve=<s:property value="solve"/> ">
						<s:property value="#pastePageBean.currentPage-1" />
					</a>
				</s:if>
				<span class="current">
					<s:property value="#pastePageBean.currentPage" />
				</span>
				<s:if test="#pastePageBean.currentPage+1<=#pastePageBean.totalPage">
					<a class="num" href="${pageContext.request.contextPath }/PasteAction_getPageBeanByCondition?currentPage=<s:property value="#pastePageBean.currentPage+1"/>&start=<s:property value="start"/>&end=<s:property value="end"/>&title=<s:property value="title"/>&isDelete=<s:property value="isDelete"/>&solve=<s:property value="solve"/> ">
						<s:property value="#pastePageBean.currentPage+1" />
					</a>
				</s:if>
				<s:if test="#pastePageBean.currentPage+2<=#pastePageBean.totalPage">
					<a class="num" href="${pageContext.request.contextPath }/PasteAction_getPageBeanByCondition?currentPage=<s:property value="#pastePageBean.currentPage+2"/>&start=<s:property value="start"/>&end=<s:property value="end"/>&title=<s:property value="title"/>&isDelete=<s:property value="isDelete"/>&solve=<s:property value="solve"/> ">
						<s:property value="#pastePageBean.currentPage+2" />
					</a>
				</s:if>
				<a class="num" href="${pageContext.request.contextPath }/PasteAction_getPageBeanByCondition?currentPage=<s:property value="#pastePageBean.totalPage"/>&start=<s:property value="start"/>&end=<s:property value="end"/>&title=<s:property value="title"/>&isDelete=<s:property value="isDelete"/>&solve=<s:property value="solve"/> ">尾页</a>
				<a class="next" href="${pageContext.request.contextPath }/PasteAction_getPageBeanByCondition?currentPage=<s:property value="#pastePageBean.currentPage+1"/>&start=<s:property value="start"/>&end=<s:property value="end"/>&title=<s:property value="title"/>&isDelete=<s:property value="isDelete"/>&solve=<s:property value="solve"/> ">&gt;&gt;</a>
			</div>
		</div>

	</div>
	<script>
		layui.use('laydate', function() {
			var laydate = layui.laydate;

			//执行一个laydate实例
			laydate.render({
				elem : '#start' //指定元素
			});

			//执行一个laydate实例
			laydate.render({
				elem : '#end' //指定元素
			});
		});

		/*用户-停用*/
		function user_stop(obj, userid) {
			//alert(id);
			//history.go(0)
			layer
					.confirm(
							'确认要改变状态吗？',
							function(index) {
								//ajax
								$
										.post(
												//action的地址
												"${pageContext.request.contextPath}/UserAction_changeUserState",
												//提交的数据
												{
													"userid" : userid
												},
												//回调函数
												function(data) {
													window.location.reload();
												},
												//数据格式
												"json")

							});
		}

		/*用户-删除*/
		function paste_del(obj, pasteid) {
			layer.confirm('确认要删除吗？', function(index) {
				//发异步删除数据
				$.post(
				//action的地址
				"${pageContext.request.contextPath}/PasteAction_deletePaste",
				//提交的数据
				{
					"pasteid" : pasteid
				},
				//回调函数
				function(data) {
					window.location.reload();
				},
				//数据格式
				"json")
			});
		}

		//还原用户
		function paste_res(obj, pasteid) {
			layer.confirm('确认要还原吗？', function(index) {
				//发异步删除数据
				$.post(
				//action的地址
				"${pageContext.request.contextPath}/PasteAction_resPaste",
				//提交的数据
				{
					"pasteid" : pasteid
				},
				//回调函数
				function(data) {
					window.location.reload();
				},
				//数据格式
				"json")
			});
		}
		
		//批量还原
		function resAll(argument) {
			var data = new Array();
			var i = 0;
			//拿到所有选中的id
			$(".layui-form-checked input[name='id']").each(function() {
				//alert($(this).val());
				data[i++] = $(this).val();
			})

			$.post(
				//action的地址
				"${pageContext.request.contextPath}/PasteAction_resPasteList",
				//传递的数据
				{
					"deleteids" : data.toString()
				},
				//回调函数
				function(data) {
					window.location.reload();
				},
				//数据格式
				"json"
	
				)

			//var data = tableCheck.getData();

			//layer.confirm('确认要删除吗？' + data, function(index) {
			//捉到所有被选中的，发异步进行删除
			//layer.msg('删除成功', {
			//	icon : 1
			//});
			//	$(".layui-form-checked").not('.header').parents('tr').remove();
			//});
		}
		
		
		//批量删除
		function delAll(argument) {
			var data = new Array();
			var i = 0;
			//拿到所有选中的id
			$(".layui-form-checked input[name='id']").each(function() {
				//alert($(this).val());
				data[i++] = $(this).val();
			})
		
			$.post(
			//action的地址
			"${pageContext.request.contextPath}/PasteAction_deletePasteList",
			//传递的数据
			{
				"deleteids" : data.toString()
			},
			//回调函数
			function(data) {
				//刷新页面
				window.location.reload();
			},
			//数据格式
			"json"

			)

			//var data = tableCheck.getData();

			//layer.confirm('确认要删除吗？' + data, function(index) {
			//捉到所有被选中的，发异步进行删除
			//layer.msg('删除成功', {
			//	icon : 1
			//});
			//	$(".layui-form-checked").not('.header').parents('tr').remove();
			//});
		}
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