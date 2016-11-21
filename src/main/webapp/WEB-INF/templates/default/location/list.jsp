<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="my" uri="/sjdf-tags"%>
<!DOCTYPE html>
<html>
<head>
<base href="${basePath}" />
<title>地理位置信息管理</title>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="css/jquery-ui-1.8.16.custom.css" />
<link rel="stylesheet" type="text/css" href="css/tools_css.css" />
<script type='text/javascript' src="js/jquery-1.6.4.min.js"></script>
<script type='text/javascript' src="js/jquery-ui-1.8.16.custom.min.js"></script>
<script type='text/javascript' src="js/jquery.blockUI.js"></script>
<script type='text/javascript' src="js/blockui.js"></script>
<script type="text/javascript">
	$(function() {
		var queryForm = $("#queryForm");
		//查询
		$("select").change(function() {
			$("#queryForm").submit();
		});

		//添加
		$("#add").click(function() {
			$("#postDiv").empty().load("admin/common/LocationAction!edit.action", queryForm.serializeArray(), function() {
				$("#dataForm").dialog({
					width : 460,
					modal : true,
					autoOpen : true,
					show : "explode",
					hide : "explode",
					close : function() {
						$(this).dialog("destroy").remove();
					},
					buttons : {
						"提交" : function() {
							$.post("admin/common/LocationAction!add.action", $(this).serializeArray(), function(re) {
								if (re["status"] == 200) {
									window.self.location = re["message"];
								} else {
									alert(re["message"]);
								}
							}, "json");
						},
						"取消" : function() {
							$(this).dialog("close").remove();
						}
					}
				});
			});
		});

		//修改
		$(".kzmb_nr").dblclick(function() {
			$("#postDiv").empty().load("admin/common/LocationAction!edit.action", {
				idx : $(this).attr("id")
			}, function() {
				$("#dataForm").dialog({
					width : 460,
					modal : true,
					autoOpen : true,
					show : "explode",
					hide : "explode",
					close : function() {
						$(this).dialog("destroy").remove();
					},
					buttons : {
						"提交" : function() {
							$.post("admin/common/LocationAction!update.action", $(this).serializeArray(), function(re) {
								if (re["status"] == 200) {
									window.self.location = re["message"];
								} else {
									alert(re["message"]);
								}
							}, "json");
						},
						"取消" : function() {
							$(this).dialog("close").remove();
						}
					}
				});
			});
		});

		//删除
		$(".del").click(function() {
			if (confirm("确定删除?")) {
				var tr = $(this).parent().parent();
				$.post("admin/common/LocationAction!del.action", {
					idx : tr.attr("id")
				}, function(re) {
					if (re["status"] == 200) {
						tr.remove();
					} else {
						alert(re["message"]);
					}
				}, "json");
			}
		});

		//级连城市
		$("#province").live("change", function() {
			var provinceCode = $(this).find("option:selected").val();
			var options = '<option value="">=====请选择=====</option>';
			if (provinceCode != "") {
				$.post("admin/common/LocationAction!getCityListAjax.action",{vercode : provinceCode},function(res){
					var r = res['result'];
					for(var i=0;i< r.length;i++){
						options += '<option value="'+r[i]['code'] +'">'+r[i]['cnName']+'</option>';
					}
					$('#city').empty().append(options);
				},"json");
			}
		});
	});
</script>
<style type="text/css">
.queryFormInput {
	width: 184px;
}

.queryForm_kzmb_table_3 {
	border: #cccccc solid 1px;
	width: 99%;
	height: 100%;
	overflow: auto;
	margin: 0 auto;
	margin-top: 10px;
	margin-bottom: 10px;
}
</style>
</head>
<body>
	<form id="queryForm" action="admin/common/LocationAction!list.action" method="post" style="border: #cccccc solid 1px; width: 99%; height: 100%; margin: 0 auto; margin-top: 10px; margin-bottom: 10px;">
		<table>
			<tr>
				<td>省份:</td>
				<td><my:filterSelect id="provinceSelectId" name="location.province.code" list="provinceList" listKey="code" listValue="cnName" headerKey="0" headerValue="======请选择省份======"/></td>
				<td>城市:</td>
				<td><my:filterSelect id="citySelectId" name="location.city.code" list="cityList" listKey="code" listValue="cnName" headerKey="" headerValue="======请选择城市======"/></td>
				<td>代码:</td>
				<td><input type="text" name="location.code" value="${location.code}" class="queryFormInput" /></td>
				<td>中文名称:</td>
				<td><input type="text" name="location.cnName" value="${location.cnName}" class="queryFormInput" /></td>
			</tr>
			<tr>
				<td>简称:</td>
				<td><input type="text" name="location.abbreviation" value="${location.abbreviation}" class="queryFormInput" /></td>
				<td>邮编:</td>
				<td><input type="text" name="location.postal" value="${location.postal}" class="queryFormInput" /></td>
				<td>区号:</td>
				<td><input type="text" name="location.areaCode" value="${location.areaCode}" class="queryFormInput" /><br /></td>
				<td>英文名称:</td>
				<td><input type="text" name="location.enName" value="${location.enName}" class="queryFormInput" /><br /></td>
			</tr>
			<tr>
				<td colspan="4" align="right"><button>搜索</button></td>
				<td colspan="4" align="left"><button type="button" id="add">添加</button></td>
			</tr>
		</table>
	</form>
	<div class="kzmb_table_3">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr class="kzmb_nm_3">
				<td>省份</td>
				<td>城市</td>
				<td><a name="code" class="sort">代码<img alt="点击排序" src="images/down_sort.gif" border="0" /></a></td>
				<td><a name="cnName" class="sort">中文名称<img alt="点击排序" src="images/down_sort.gif" border="0" /></a></td>
				<td><a name="enName" class="sort">英文名称<img alt="点击排序" src="images/down_sort.gif" border="0" /></a></td>
				<td><a name="abbreviation" class="sort">简称<img alt="点击排序" src="images/down_sort.gif" border="0" /></a></td>
				<td><a name="postal" class="sort">邮编<img alt="点击排序" src="images/down_sort.gif" border="0" /></a></td>
				<td><a name="areaCode" class="sort">区号<img alt="点击排序" src="images/down_sort.gif" border="0" /></a></td>
				<td>操作</td>
			</tr>
			<s:iterator value="locationList">
				<tr id="${id}" class="kzmb_nr">
					<td>${province.cnName}</td>
					<td>${city.cnName}</td>
					<td>${code}</td>
					<td>${cnName}</td>
					<td>${enName}</td>
					<td>${abbreviation}</td>
					<td>${postal}</td>
					<td>${areaCode}</td>
					<td>
						<button type="button" class="del">删除</button>
					</td>
				</tr>
			</s:iterator>
		</table>
	</div>
	<my:page />
	<div id="postDiv"></div>
</body>
</html>