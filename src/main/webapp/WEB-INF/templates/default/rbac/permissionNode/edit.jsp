<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<form id="editPermissionNodeForm" method="post" title="修改权限结点">
	<label for="editPermissionNodeName">名称：</label>
	<input type="text" id="editPermissionNodeName" name="permissionNode.name" maxlength="50" value="<s:property value="permissionNode.name"/>" style="width: 280px;"/><br>
	<input type="hidden" name="permissionNode.id" value="<s:property value="permissionNode.id"/>">
</form>
