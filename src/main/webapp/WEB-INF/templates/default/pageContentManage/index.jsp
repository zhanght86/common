<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<base href="${basePath}" />
<title>page_content_frame</title>
<style>
.kzmTop{width:100%;height:60px;line-height:60px;background:#f5f5f5;margin-bottom:0px;border-bottom:#3C6E31 solid 1px;font-size:20px;padding-left:0px;}

</style>
</head>
<body>
	<!-- 头部开始 -->
	<div id="topDiv" class="kzmTop">
		<%@include file="top.jsp" %>
	</div>
	<!-- 主页面 -->
	<div id="mainDiv" style="width: 100%;height:auto;border-top:1px #3C6E31 solid">
		<!-- 左边树形显示 -->
		<div id="leftDiv" style="width: 260px;;float: left;">
			<%@include file="left.jsp" %>
		</div>
		<!-- 右边内容变化 11-->
		<div id="rightDiv" style="width: 80%;float: left;margin-left:1px">
			<iframe width="100%" height="1800px;" scrolling="no" id="win" name="rightFrame" frameborder="0"  src="admin/common/PageContentManageAction!list4Node.action?contentManageBean.versionId=${versionId}"></iframe>
		</div>
	</div>
</body>
</html>