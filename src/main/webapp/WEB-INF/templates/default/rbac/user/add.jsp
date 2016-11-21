<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<tr id="<s:property value="user.id"/>">
	<td><s:property value="user.systemTypeInfo"/></td>
	<td><s:property value="user.username"/></td>
	<td><s:property value="user.name"/></td>
	<td id="forbid<s:property value="user.id"/>"><s:property value="user.forbidInfor"/></td>
	<td id="roleName<s:property value="user.id"/>"><s:property value="user.role.name"/></td>
</tr>
