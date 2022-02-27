<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<link rel="stylesheet" type="text/css" href="css/index.css">
<link rel="stylesheet" href="res/css/global.css">
</head>

<body style="margin: -2px">
	<iframe src="head.jsp" scrolling="no" width="100%" height="110px"></iframe>
	<div class="dvContent">
		<div class="dvquesleft">

			<div class="dvqstitle">
				<image class="imgbean" src="images/bean.jpg"> <span class="qsTitle">问答</span> <span class="back">
					<ab href="">《返回上一页</a>
				</span>
			</div>
			<div class="dvtabhead">
				<div class="tabheads tabcurrent">全部问题</div>
				<div class="tabheads">我的问题</div>
				<!-- <div class="tabheads">关注问题</div>
				<div class="tabheads">问题标签</div> -->
			</div>
			<div class="tabContent">
				<div class="dvtags">
					<a class="curenttag">待解决</a>
					
				</div>
				<div class="tab">
					<s:iterator value="#pastePageBean.list" var="paste">
						<div class="dvques">
							<div class="quesCount">
								<div class="count">
									<s:property value="#paste.ansnum" />
								</div>
								<div class="ques">回答数</div>
							</div>
							<div class="quesContent">
								<div class="quesTitle">
									<s:property value="#paste.offer" />
									<image src="images/bean.jpg" class="bean"> <span class="spanques">
										
										<a href="${pageContext.request.contextPath }/PasteAction_getDetail?pasteid=<s:property value="#paste.id"/>">
											<s:property value="#paste.title" />
										</a>
									</span>
								</div>
								<div class="qContent" style="width: 630px; height: 34px; overflow: hidden; white-space: normal; text-overflow: ellipsis;">
									<p>
										<s:property value="#paste.content" />
									</p>
								</div>
								<div class="tags">
									<span class="tag">excel</span>
									<span class="tag">程序</span>
								</div>
								<div class="quesUser" style="margin-top: 20px; margin-right: 20px">
									<image src="${pageContext.request.contextPath }/<s:property value="#paste.user.image"/>" class="imguser" />

									<div class="userName">
										<s:property value="#paste.user.username" />
										<div class="liulan">
											浏览(
											<s:property value="#paste.glanceover" />
											)
											<s:property value="#paste.createtime" />
										</div>
									</div>

								</div>
							</div>
						</div>
					</s:iterator>
					<div style="text-align: center ">
						<div class="laypage-main">

							<a href="${pageContext.request.contextPath }/GetDataAction_getData?currentPage=<s:property value="#pastePageBean.currentPage-1"/>" class="laypage-next">上一页</a>
							<a href="${pageContext.request.contextPath }/GetDataAction_getData?currentPage=1" class="laypage-last" title="尾页">首页</a>
							<s:if test="#pastePageBean.currentPage-3>0">
								<span>…</span>
							</s:if>
							<s:if test="#pastePageBean.currentPage-2>0">
								<a href="${pageContext.request.contextPath }/GetDataAction_getData?currentPage=<s:property value="#pastePageBean.currentPage-2"/>">
									<s:property value="#pastePageBean.currentPage-2" />
								</a>
							</s:if>
							<s:if test="#pastePageBean.currentPage-1>0">
								<a href="${pageContext.request.contextPath }/GetDataAction_getData?currentPage=<s:property value="#pastePageBean.currentPage-1"/>">
									<s:property value="#pastePageBean.currentPage-1" />
								</a>
							</s:if>

							<!-- 当前页 -->
							<span class="laypage-curr"style="background-color:#5f3ad0">
								<s:property value="#pastePageBean.currentPage" />
							</span>

							<s:if test="#pastePageBean.currentPage<#pastePageBean.totalPage">
								<a href="${pageContext.request.contextPath }/GetDataAction_getData?currentPage=<s:property value="#pastePageBean.currentPage+1"/>">
									<s:property value="#pastePageBean.currentPage+1" />
								</a>
							</s:if>
							<s:if test="#pastePageBean.currentPage+1<#pastePageBean.totalPage">
								<a href="${pageContext.request.contextPath }/GetDataAction_getData?currentPage=<s:property value="#pastePageBean.currentPage+2"/>">
									<s:property value="#pastePageBean.currentPage+2" />
								</a>
							</s:if>
							<s:if test="#pastePageBean.currentPage+2<#pastePageBean.totalPage">
								<span>…</span>
							</s:if>
							<a href="${pageContext.request.contextPath }/GetDataAction_getData?currentPage=<s:property value="#pastePageBean.totalPage"/>" class="laypage-last" title="尾页">尾页</a>

							<a href="${pageContext.request.contextPath }/GetDataAction_getData?currentPage=<s:property value="#pastePageBean.currentPage+1"/>" class="laypage-next">下一页</a>
						</div>
					</div>
				</div>
				
				
				<div class="tab hidden">
				
				<s:iterator value="#pastePageBean.list" var="paste">
						<div class="dvques">
							<div class="quesCount">
								<div class="count">
									<s:property value="#paste.ansnum" />
								</div>
								<div class="ques">回答数</div>
							</div>
							<div class="quesContent">
								<div class="quesTitle">
									<s:property value="#paste.offer" />
									<image src="images/bean.jpg" class="bean"> <span class="spanques">
										
										<a href="${pageContext.request.contextPath }/PasteAction_getDetail?pasteid=<s:property value="#paste.id"/>">
											<s:property value="#paste.title" />
										</a>
									</span>
								</div>
								<div class="qContent" style="width: 630px; height: 34px; overflow: hidden; white-space: normal; text-overflow: ellipsis;">
									<p>
										<s:property value="#paste.content" />
									</p>
								</div>
								<div class="tags">
									<span class="tag">excel</span>
									<span class="tag">程序</span>
								</div>
								<div class="quesUser" style="margin-top: 20px; margin-right: 20px">
									<image src="${pageContext.request.contextPath }/<s:property value="#paste.user.image"/>" class="imguser" />

									<div class="userName">
										<s:property value="#paste.user.username" />
										<div class="liulan">
											浏览(
											<s:property value="#paste.glanceover" />
											)
											<s:property value="#paste.createtime" />
										</div>
									</div>

								</div>
							</div>
						</div>
					</s:iterator>
					<div style="text-align: center ">
						<div class="laypage-main">

							<a href="${pageContext.request.contextPath }/GetDataAction_getData?currentPage=<s:property value="#pastePageBean.currentPage-1"/>" class="laypage-next">上一页</a>
							<a href="${pageContext.request.contextPath }/GetDataAction_getData?currentPage=1" class="laypage-last" title="尾页">首页</a>
							<s:if test="#pastePageBean.currentPage-3>0">
								<span>…</span>
							</s:if>
							<s:if test="#pastePageBean.currentPage-2>0">
								<a href="${pageContext.request.contextPath }/GetDataAction_getData?currentPage=<s:property value="#pastePageBean.currentPage-2"/>">
									<s:property value="#pastePageBean.currentPage-2" />
								</a>
							</s:if>
							<s:if test="#pastePageBean.currentPage-1>0">
								<a href="${pageContext.request.contextPath }/GetDataAction_getData?currentPage=<s:property value="#pastePageBean.currentPage-1"/>">
									<s:property value="#pastePageBean.currentPage-1" />
								</a>
							</s:if>

							<!-- 当前页 -->
							<span class="laypage-curr"style="background-color:#5f3ad0">
								<s:property value="#pastePageBean.currentPage" />
							</span>

							<s:if test="#pastePageBean.currentPage<#pastePageBean.totalPage">
								<a href="${pageContext.request.contextPath }/GetDataAction_getData?currentPage=<s:property value="#pastePageBean.currentPage+1"/>">
									<s:property value="#pastePageBean.currentPage+1" />
								</a>
							</s:if>
							<s:if test="#pastePageBean.currentPage+1<#pastePageBean.totalPage">
								<a href="${pageContext.request.contextPath }/GetDataAction_getData?currentPage=<s:property value="#pastePageBean.currentPage+2"/>">
									<s:property value="#pastePageBean.currentPage+2" />
								</a>
							</s:if>
							<s:if test="#pastePageBean.currentPage+2<#pastePageBean.totalPage">
								<span>…</span>
							</s:if>
							<a href="${pageContext.request.contextPath }/GetDataAction_getData?currentPage=<s:property value="#pastePageBean.totalPage"/>" class="laypage-last" title="尾页">尾页</a>

							<a href="${pageContext.request.contextPath }/GetDataAction_getData?currentPage=<s:property value="#pastePageBean.currentPage+1"/>" class="laypage-next">下一页</a>
						</div>
					</div>
				
				
				
				
				
				
				
				
				</div>
				
			</div>
		</div>
		<div class="dvquesright">
			<div>
				<buton class="btnques" onclick="location.href='add.jsp'">提个问题</buton>
			</div>



			<div class="dvorder">

				<dl class="fly-panel fly-list-one">
					<dt class="fly-panel-title">最近热帖</dt>
					<s:iterator value="#glanceoverPageBean.list" var="paste">
						<dd>
							<a href="${pageContext.request.contextPath }/PasteAction_getDetail?pasteid=<s:property value="#paste.id"/>">
								<s:property value="#paste.title" />
							</a>
							<span>
								<i class="iconfont">&#xe60b;</i>
								<s:property value="#paste.glanceover" />
							</span>
						</dd>
					</s:iterator>
				</dl>


				<dl class="fly-panel fly-list-one">
					<dt class="fly-panel-title">近期热议</dt>
					<s:iterator value="#ansnumPageBean.list" var="paste">
						<dd>
							<a href="${pageContext.request.contextPath }/PasteAction_getDetail?pasteid=<s:property value="#paste.id"/>">
								<s:property value="#paste.title" />
							</a>
							<span>
								<i class="iconfont">&#xe60c;</i>
								<s:property value="#paste.ansnum" />
							</span>
						</dd>
					</s:iterator>
				</dl>

				<div class="orderTitle">学生排行榜</div>

				<s:iterator value="#userPageBean.list" var="user">
					<div class="users">

						<image class="userface" src="${pageContext.request.contextPath }/<s:property value="#user.image"/>" />
						<div class="dvuser">
							<div class="userTitle">
								<s:property value="#user.username" />
							</div>
							<div class="userdeital">
								大牛
								<s:property value="#user.level" />
								级 豆:
								<s:property value="#user.coin" />
							</div>
						</div>
					</div>
				</s:iterator>


			</div>

		</div>

	</div>
	<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
	<script type="text/javascript">
		$(function() {

			$(".tabheads").click(
					function() {
						$(".tabheads").removeClass("tabcurrent").eq(
								$(this).index()).addClass("tabcurrent");
						$(".tab").hide().eq($(this).index()).show();
					});
		});
	</script>

</body>
</html>


