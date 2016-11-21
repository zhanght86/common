<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<form id="addRoleForm" title="添加角色">
	<label>角色名称:</label><input type="text" name="role.name" class="required"><br/>
	<input type="hidden" name="role.parent.id" value="<s:property value="role.id"/>">
</form>
