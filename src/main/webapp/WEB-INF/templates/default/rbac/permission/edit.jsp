<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<form id="dataForm" title="修改权限">
	<label for="permissionName">权限名称：</label>
	<input type="text" id="permissionName" name="permission.name" value="<s:property value="permission.name"/>" class="required" maxlength="50" style="width: 280px;"/><br>
	<input type="hidden" value="<s:property value="permission.id"/>" name="permission.id"/>
</form>
