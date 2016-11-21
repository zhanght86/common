<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<form id="editRoleForm" title="修改角色">
	<label>角色名：</label><input type="text" name="role.name" maxlength="50" value="<s:property value="role.name"/>"><br>
	<input type="hidden" name="role.id" value="<s:property value="role.id"/>">
</form>
