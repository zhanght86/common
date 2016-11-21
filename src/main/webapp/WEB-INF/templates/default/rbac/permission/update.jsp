<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<tr class="content kzmb_nr" id="<s:property value="permission.id"/>">
	<td><s:property value="permission.systemTypeInfo"/></td>
	<td><s:property value="permission.code"/></td>
	<td><s:property value="permission.className"/></td>
	<td><s:property value="permission.method"/></td>
	<td><s:property value="permission.name"/></td>
	<td><s:property value="permission.url"/></td>
	<td><s:property value="permission.isMenuInfo"/></td>
	<td><s:property value="permission.orderBy"/></td>
	<td>
		<s:if test="permission.supportedPermission !=null">
			<s:property value="permission.supportedPermission.name"/>(<s:property value="permission.supportedPermission.code"/>)
		</s:if>
	</td>
</tr>
