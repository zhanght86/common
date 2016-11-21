<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="my" uri="/sjdf-tags"%>
<!DOCTYPE html>
<html>
<head>
<base href="${basePath }" />
<title>快递单号打印</title>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="css/tools_css.css" />
<script type='text/javascript' src="js/jquery-1.6.4.min.js"></script>
<script type="text/javascript">
	$(function() {
		var dataForm = $("#dataForm");
		var flag = $("#flag");
		
		$("#direct,#preview").click(function(){
			if($(this).attr("id") == "direct"){
				flag.val(3);
			}else{
				flag.val(1);
			}
			
			if($("#type option:selected").val() == "0"){
				alert("快递类型不能为空");
				return false;	
			}
			
			if($("#toAddressVoId option:selected").val() == "0"){
				alert("单位名称不能为空");
				return false;
			}
			
			dataForm.submit();
		});
	});
</script>
<style type="text/css">
#dataForm{
	border: 1 solid gray;
	position: absolute;
	top: 50%;
	left: 50%;
	height: 200px; 
	margin-top: -100px;
	width: 400px; 
	margin-left: -200px;
}
</style>
</head>
<body>
	<form id="dataForm" target="_blank" action="api/common/ExpressAction!print.action">
		<input type="hidden" name="vertime" value="${vertime}"/>
		<input type="hidden" name="vercode" value="${vercode}"/>
		<input type="hidden" id="flag" name="flag"/>
		快递类型:<s:select name="type" id="type" list="expressTypeList" listKey="value" listValue="name" headerKey="0" headerValue="=====请选择=====" cssStyle="width:170px;margin-top:5px"/><br/>
		单位名称:<my:filterSelect name="toAddressVo.id" id="toAddressVoId" list="addressList" listKey="id" listValue="companyName" headerKey="0" headerValue="=====请选择=====" cssStyle="width:170px;margin-top:5px"/><br/>
		<button id="direct">直接打印</button>
		<button id="preview">打印预览</button>
		<a href="admin/common/PrintAction!list.action">添加打印地址</a>
	</form>
</body>
</html>