<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="my" uri="/sjdf-tags" %>
<!DOCTYPE html>
<html>
<head>
<base href="${basePath}" />
<meta charset="UTF-8">
<title>配置库编辑</title>
<style type="text/css">
table tr td span {
	color: #ff6701;
}
table tr th{text-align: right; font-weight: normal; padding-right:2px;}
</style>
</head>
<body>
	<form id="postForm">
		<table>
			<tr>
				<th>类别:</th>
				<td colspan="2"><input type="hidden" name="dictionary.id" value="${dictionary.id}" /> <my:filterSelect id="clazzName" disabled="%{dictionary.id != 0}" name="dictionary.clazz" list="clazzNameMap" listKey="key" listValue="value" headerKey="" headerValue="=====请选择====="/><span>*</span></td>
			</tr>
			<tr>
				<th>属性:</th>
				<td colspan="2"><input type="text" <s:if test="dictionary.id != 0">readonly="readonly" style="background:#ebebe4"</s:if> name="dictionary.attr" value="${dictionary.attr}" /><span>*</span></td>
			</tr>
			<tr>
				<th>值:</th>
				<td colspan="2"><textarea rows="5" cols="42" name="dictionary.value">${dictionary.value}</textarea></td>
			</tr>
			<tr>
				<th>引用:</th>
				<td><my:filterSelect cssStyle="width:155px;" id="refClass" list="clazzNameMap" listKey="key" listValue="value" headerKey="" headerValue="=====请选择=====" name="dictionary.refClazz"/></td>
				<td><s:select cssStyle="width:155px;" id="refList" list="refDictionaryList" listKey="id" listValue="name" headerKey="0" headerValue="=====请选择=====" name="dictionary.ref.id"/></td>
			</tr>
			<tr>
				<th>系统类型:</th>
				<td><s:select cssStyle="width:155px;" list="systemTypeList" listKey="id" listValue="name" headerKey="0" headerValue="=====请选择=====" name="dictionary.systemType.id"/></td>
			</tr>
			<tr>
				<th>子系统类型:</th>
				<td><s:select cssStyle="width:155px;" list="subsystemTypeList" listKey="id" listValue="name" headerKey="0" headerValue="=====请选择=====" name="dictionary.subsystem.id"/></td>
			</tr>
			<tr>
				<th>功能大分类:</th>
				<td><s:select cssStyle="width:155px;" list="functionTypeList" listKey="id" listValue="name" headerKey="0" headerValue="=====请选择=====" name="dictionary.functionClass.id"/></td>
			</tr>
			<tr>
				<th>操作动作:</th>
				<td><s:select cssStyle="width:155px;" list="operatorTypeList" listKey="id" listValue="name" headerKey="0" headerValue="=====请选择=====" name="dictionary.operateAction.id"/></td>
			</tr>
			<tr>
				<th>名称(en):</th>
				<td colspan="2"><input type="text" name="dictionary.enName" value="${dictionary.enName}" /></td>
			</tr>
			<tr>
				<th>名称(cn):</th>
				<td colspan="2"><input type="text" name="dictionary.cnName" value="${dictionary.cnName}" /></td>
			</tr>
			<tr>
				<th>显示顺序:</th>
				<td colspan="2"><input type="text" name="dictionary.orderBy" value="${dictionary.orderBy}" /></td>
			</tr>
			<tr>
				<th>名称语言类别:</th>
				<td colspan="2"><s:select name="dictionary.langType" list="langTypeList" listKey="attr" listValue="cnName"/></td>
			</tr>
			<tr>
				<th>有效标记:</th>
				<td colspan="2"><s:select name="dictionary.valid" list="#{2:'有效',1:'无效'}" listKey="key" listValue="value"/></td>
			</tr>
		</table>
	</form>
</body>
</html>