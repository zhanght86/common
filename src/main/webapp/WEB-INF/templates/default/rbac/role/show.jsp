<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
角色名:<br/>
&nbsp;&nbsp;&nbsp;&nbsp;<s:property value="role.name"/><br/>

<s:if test="role.permissionNodeTreeList != null && role.permissionNodeTreeList.size() > 0">
	权限组:<br/>
	<s:iterator value="role.permissionNodeTreeList">
		&nbsp;&nbsp;&nbsp;&nbsp;<s:property value="name"/><br/>
	</s:iterator>
</s:if>

<s:if test="role.permissionList != null && role.permissionList.size() > 0">
	权限:<br/>
	<s:iterator value="role.permissionList">
		&nbsp;&nbsp;&nbsp;&nbsp;<s:property value="name"/><br/>
	</s:iterator>
</s:if>
