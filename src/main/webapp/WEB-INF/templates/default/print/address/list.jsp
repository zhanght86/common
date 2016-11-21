<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="my" uri="/sjdf-tags"%>
<!DOCTYPE html>
<html>
<head>
<base href="${basePath }" />
<title>打印地址管理</title>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="css/jquery-ui-1.8.16.custom.css" />
<link rel="stylesheet" type="text/css" href="css/tools_css.css" />
<script type='text/javascript' src="js/jquery-1.6.4.min.js"></script>
<script type='text/javascript' src="js/jquery-ui-1.8.16.custom.min.js"></script>
<script type='text/javascript' src="js/jquery.blockUI.js"></script>
<script type='text/javascript' src="js/blockui.js"></script>
<script type='text/javascript' src="js/jquery.qtip-1.0.0-rc3.min.js"></script>
<script type="text/javascript">
	$(function() {
		var queryForm = $("#queryForm");
		//添加
		$("#add").click(function() {
			$("#postFormDiv").load("admin/common/PrintAction!edit.action", {}, function() {
				$("#dataForm").dialog({
					title : "添加",
					modal : true,
					autoOpen : true,
					show : "explode",
					hide : "explode",
					close : function() {
						$(this).dialog("destroy").remove();
					},
					buttons : {
						"添加" : function() {
							$.post("admin/common/PrintAction!add.action", $(this).serializeArray(), function(re) {
								if (re == "success") {
									queryForm.submit();
								} else {
									alert(re);
								}
							}, "text");
						},
						"取消" : function() {
							$(this).dialog("close").remove();
						}
					}
				});
			});
		});
		
		//修改
		$(".kzmb_nr").dblclick(function(){
			$("#postFormDiv").load("admin/common/PrintAction!edit.action", {idx:$(this).attr("id")}, function() {
				$("#dataForm").dialog({
					title : "修改",
					modal : true,
					autoOpen : true,
					show : "explode",
					hide : "explode",
					close : function() {
						$(this).dialog("destroy").remove();
					},
					buttons : {
						"修改" : function() {
							$.post("admin/common/PrintAction!update.action", $(this).serializeArray(), function(re) {
								if (re == "success") {
									queryForm.submit();
								} else {
									alert(re);
								}
							}, "text");
						},
						"取消" : function() {
							$(this).dialog("close").remove();
						}
					}
				});
			});
		});
		
		//删除
		$(".del").click(function(){
			if(confirm("确定要删除吗?")){
				var tr = $(this).parent().parent();
				$.post("admin/common/PrintAction!del.action",{idx:tr.attr("id")},function(re){
					if(re == "success"){
						queryForm.submit();
					}else{
						alert(re);
					}
				});
			}
		});
	});
</script>
</head>
<body>
	<div class="kzmb_table_3">
		<form id="queryForm" action="admin/common/PrintAction!list.action" method="post">
			收寄件人:<input type="text" name="addressVo.name" value="${addressVo.name}" />
			单位名称:<input type="text" name="addressVo.companyName" value="${addressVo.companyName}" /> 
			邮寄地址:<input type="text" name="addressVo.address" value="${addressVo.address}" />
			<button>搜索</button>
			<button type="button" id="add">添加</button>
			<a href="admin/common/PrintAction!indirectPrint.action">快递单号打印</a>
		</form>
	</div>
	<div class="kzmb_table_3" style="float:left;padding-left:7px;">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr class="kzmb_nm_3">
				<td>收件人</td>
				<td>单位名称</td>
				<td>地址</td>
				<td>操作</td>
			</tr>
			<s:iterator value="addressList">
				<tr id="${id}" class="kzmb_nr">
					<td>${name}</td>
					<td>${companyName}</td>
					<td>${address}</td>
					<td>
						<button type="button" class="del">删除</button>
					</td>
				</tr>
			</s:iterator>
		</table>
		<my:page />
	</div>
	<div id="postFormDiv"></div>
</body>
</html>