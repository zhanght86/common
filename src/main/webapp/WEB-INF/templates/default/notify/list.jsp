<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="my" uri="/sjdf-tags" %>
<!DOCTYPE html>
<html>
<head>
<base href="${basePath}" />
<title>通知跟踪列表</title>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="css/jquery-ui-1.8.16.custom.css" />
<link rel="stylesheet" type="text/css" href="css/tools_css.css" />
<link rel="stylesheet" type="text/css" href="css/sfilter.css" />
<script type='text/javascript' src="js/jquery-1.6.4.min.js"></script>
<script type='text/javascript' src="js/jquery-ui-1.8.16.custom.min.js"></script>
<script type='text/javascript' src="js/jquery.blockUI.js"></script>
<script type='text/javascript' src="js/blockui.js"></script>
<script type='text/javascript' src="js/sfilter.js"></script>
<style type="text/css">
.handle {
	cursor: pointer;
}
</style>
<script type="text/javascript">
	$(function() {
		var tipMessage = "${tipMessage}";
		if(tipMessage != ""){
			alert(tipMessage);
		}
		
		$("#reNotify").click(function(){
			if (confirm("确定重新同步数据吗?")) {
				doCustomSubmit("admin/common/NotifyTrackAction!handle.action");
			}
		});
	});
</script>
</head>
<body>
	<span style="margin:7px 0 0 7px; float:left">
		<form action="admin/common/NotifyTrackAction!list.action" method="post">
			通知类型:<s:select name="track.mec" list="mecList" listKey="attr" listValue="name" headerKey="" headerValue="====请选择===="></s:select>
			操作动作:<s:select name="track.operateType" list="operateTypeList" listKey="attr" listValue="name" headerKey="" headerValue="====请选择===="></s:select>
			执行状态:<s:select name="track.status" list="executeStatusList" listKey="attr" listValue="name" headerKey="" headerValue="====请选择===="></s:select>
			url:<input type="text" name="track.url" value="${track.url}"/>
			数据或原因:<input type="text" name="track.data" value="${track.data}"/>
			跟踪编号:<input type="text" name="track.id" value="${track.id == 0 ? '' : track.id}"/>
			上级:<input type="text" name="track.parent.id" value="${track.parent.id == 0 ? '' : track.parent.id}"/>
			<button type="submit">搜索</button>
		</form>
	</span>
	<div class="kzmb_table_3" style="float:left;padding-left:7px;">
		<div>
			<input type="checkbox" id="CheckBox_CheckAll" value="1" /><font color="#0033FF">全选所有记录</font>
			<button type="button" id="reNotify">重新同步</button>
		</div>
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr class="kzmb_nm_3">
				<td><input type="checkbox" id="CheckBox_CheckThisPage"/></td>
				<td>跟踪编号</td>
				<td>通知类型</td>
				<td>操作动作</td>
				<td>执行状态</td>
				<td>操作时间</td>
				<td>url</td>
				<td>数据或原因</td>
			</tr>
			<s:iterator value="trackList">
				<tr class="content kzmb_nr" id="${id}">
					<td>
						<s:if test="@com.sjdf.platform.dictionary.bean.ExecuteStatus@FAIL == status">
							<input class="CheckBox_CheckData" type="checkbox" value="${id}" />
						</s:if>
						<s:else>&nbsp;</s:else>
					</td>
					<td>${id}<s:if test="parent != null">(${parent.id})</s:if></td>
					<td>${mecInfo}</td>
					<td>${operateTypeInfo}</td>
					<td>${statusInfo}</td>
					<td>${createTimeInfo}</td>
					<td>${url}</td>
					<td>
						<s:if test="parent != null"><s:property value="data"/></s:if>
					</td>
				</tr>
			</s:iterator>
		</table>
		<my:page/>
	</div>
</body>
</html>
