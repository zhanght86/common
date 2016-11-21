<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<base href="${basePath}" />
<meta charset="UTF-8">
<title>错误页面</title>
<link href="css/default.css" rel="stylesheet" type="text/css" />
<link href="/css/default.css" rel="stylesheet" type="text/css" />
<style type="text/css">
<!--
#MessageBox {
	width: 600px;
	margin-top: 15%;
	margin-right: auto;
	margin-bottom: auto;
	margin-left: auto;
}

#MessageBox-Info {
	font-weight: bold;
	border-color:#B5D4FE;
	border-top:2px solid;
	border-bottom:1px solid;
	padding:5px 15px 5px 45px;
	text-align: left;
}
#MessageBox-Return {
	margin-top: 6px;
	text-align: right;
}

#MessageBox-Return a:link,#MessageBox-Return a:visited {
	color: #0000FF;
	background-color:transparent;
	text-decoration: none;
}
#MessageBox-Return a:hover,#MessageBox-Return a:active {
	color: #FF0000;
	background-color: transparent;
	text-decoration: underline;
}
-->
</style>
</head>
<body>
	<div id="MessageBox">
		<div id="MessageBox-Info"><s:property value="tipMessage"/></div>
		<div id="MessageBox-Return">
			<!-- <a href="javascript:window.top.opener='';window.close();">关闭</a> -->
		</div>
	</div>
</body>
</html>