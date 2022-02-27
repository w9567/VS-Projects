<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<link rel="stylesheet" type="text/css" href="css/login.css" />
<link rel="stylesheet" href="css/head.css" />
<style>
   .error{
   color:red;
   }
</style>
<script src="js/jquery-1.7.2.min.js"></script>
<script src="js/jquery.validate.min.js"></script>
<script src="js/index.js" type="text/javascript" charset="utf-8"></script>

<script type="text/javascript" charset="utf-8">


$(function(){
	$("#myform").validate({
		//规则
		rules:{
			"email":{
				//是否为必填字段
				"required":true,
				"email":true
			}
		},
		//如果违反了规则应该怎么办
		messages:{
			"email":{
				"required":"邮箱不能为空",
				"email":"请输入正确的邮箱"
			}
		}
	})
})




$(function()
	{
		$("#username").blur(function(){
			var usernameInput = $(this).val();
			//alert(usernameInput);
			$.post(
				//action的地址
				"${pageContext.request.contextPath}/UserAction_checkUsername",
				//提交的数据
				{"username":usernameInput},
				//回调函数
				function(data)
				{
					var success = data.success;
					//可以添加
					if(success)
					{
						$("#usernameInfo").css("color","green");
						$("#usernameInfo").html("用户名可以使用");
					}
					//不能添加
					else
					{
						$("#usernameInfo").css("color","red");
						$("#usernameInfo").html("用户名已经被注册！！");
					}
					//alert(data.success);
				},
				//数据的格式
				"json"
			)
			
			
		})
	})







</script>



</head>

<body style="margin: -2px">
	<iframe src="head.jsp" scrolling="no" width="100%" height="110px"></iframe>
	<section class="sec">
		<form  id="myform" action="${pageContext.request.contextPath }/UserAction_register" method="post">
			<div class="register-box">

				<label  for="username" class="username_label"> 用 户 名 <input  id="username"  maxlength="20" name="username" type="text" placeholder="您的用户名和登录名" />
				</label>
				<div id="usernameInfo"  class="tips"></div>
			</div>
			<div class="register-box">
				<label for="username" class="other_label"> 设 置 密 码 <input maxlength="20" type="password" name="password" placeholder="建议至少使用两种字符组合" />
				</label>
				<div class="tips"></div>
			</div>
			<div class="register-box">
				<label for="username" class="other_label"> 确 认 密 码 <input maxlength="20" type="password" placeholder="请再次输入密码" />
				</label>
				<div class="tips"></div>
			</div>

			<div class="register-box">
				<label for="username" class="username_label"> 真实姓名 <input maxlength="20" name="name" type="text" placeholder="您的真实姓名" />
				</label>
				<div class="tips"></div>
			</div>
			<div class="register-box">
				<label for="username" class="username_label"> 邮箱 <input maxlength="50" name="email" type="text" placeholder="您的邮箱" />
				</label>
				<div class="tips"><label class="error" for="email" style="display: none;padding-left: 0px">请输入正确的邮箱</label></div>
			</div>
			<div class="register-box">
				<label for="username" class="username_label"> 手机号 <input maxlength="20" name="telephone" type="text" placeholder="您的手机号" />
				</label>
				<div class="tips"></div>
			</div>
			<div class="arguement">
				<input type="checkbox" id="xieyi" /> 阅读并同意 <a href="javascript:void(0)">《错题用户注册协议》</a> <a href="login.html">已有账号,立即登录</a>
				<div class="tips"></div>
			</div>
			<div class="submit_btn">
				<button type="submit" id="submit_btn" style="background:#5f3ad0">立 即 注 册</button>
			</div>
		</form>
	</section>
	
</body>
</html>
