<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<li>
	<input type="checkbox" name="ids" value="<s:property value="permissionNode.id"/>"/>
	<span><s:property value="permissionNode.name"/></span>
</li>
