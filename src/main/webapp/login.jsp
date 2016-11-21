<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%
	StringBuilder basePath = new StringBuilder();
	basePath.append(request.getScheme());
	basePath.append("://");
	basePath.append(request.getServerName());
	basePath.append(":");
	basePath.append(request.getServerPort());
	basePath.append(request.getContextPath());
	basePath.append("/");
%>
<base href="<%=basePath.toString()%>" />
<meta charset="UTF-8">
<title>用户登录</title>
<script type="text/javascript" src="js/jquery-1.6.4.min.js"></script>
<script type='text/javascript' src="js/jquery.validate.min.js"></script>
<script type="text/javascript">
	$(function() {
		var tipMessage = "${tipMessage}";
		if (tipMessage != "") {
			alert(tipMessage);
		}

		$("form").validate();

		$("#verifyCode").click(function() {
			$(this).attr("src", "image.jsp?d=" + new Date().getTime());
		});
	});
</script>
<style type="text/css">
html,form,body,ul,ol,dl,img,li,* {
	margin: 0;
	padding: 0;
	list-style-type: none;
}

img {
	display: block;
	margin: 0 auto;
	border: 0px;
}

label {
	width: 10em;
	float: left;
}

label.error {
	float: none;
	color: red;
	padding-left: .5em;
	vertical-align: top;
}
.content {
	width: 750px;
	height: auto;
	margin: 0 auto;
}

.logo {
	width: 287px;
	height: 48px;
	margin: 63px 0 23px 0;
	float: left;
}

.domain_nr {
	width: 750px;
	height: 336px;
	float: left;
}

.d_bottomqh {
	width: 700px;
	margin: 25px 34px 0 34px;
	height: 210px;
	float: left;
	font-family: "宋体";
	font-size: 12px;
	display: inline;
	overflow: hidden;
}

.ymz_pic {
	float: left;
	margin: 0 10px;
	line-height: 33px;
}
input {
	width:330px;
	height:35px;
	font:14px/33px "宋体";
	border:1px solid;
	border-color:#aaa #ddd #ddd #aaa;
}
.input {
	width:120px;
	float:left;
}
ul li {
	width:750px;
	float:left;
	margin:9px 0;
	font:bold 14px/36px "雅黑宋体";
}
ul li label {
	width:120px;
	padding-right:12px;
	float:left;
	text-align:right;
}
.buttom_pic {
	width:607px;
	height:35px;
	clear: both;
	float:left;
	margin-top:15px;
	text-align:center;
}
.buttom {
	width:102px;
	height:35px;
	border:0px;
	cursor:pointer;
}
</style>
</head>
<body>
	<div class="content">
		<div class="logo"></div>
		<div class="domain_nr">
			<div class="d_bottomqh">
				<form action="other/common/platform/LoginAction!login.action" method="post">
					<ul>
						<li><label for="username">用户名:</label><input type="text" id="username" name="user.username" class="required" title="用户名不能为空" /></li>
						<li><label for="password">密码:</label><input type="password" id="password" name="user.password" class="required" title="密码不能为空" /></li>
						<li><label for="code">验证码:</label><input type="text" id="code" name="code" class="required input" title="验证码不能为空" /><img class="ymz_pic" id="verifyCode" src="image.jsp" title="点击刷新" style="cursor: pointer;" /></li>
					</ul>
					<div class="buttom_pic">
						<input type="submit" value="登录" class="buttom"/>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>