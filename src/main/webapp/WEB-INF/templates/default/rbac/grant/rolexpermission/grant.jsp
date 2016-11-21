<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="my" uri="/sjdf-tags" %>
<!DOCTYPE html>
<html>
<head>
	<base href="${basePath}"/>
	<title>角色分配权限</title>
	<%@include file="../../../share/head.jsp" %>
	<script type="text/javascript">
		//<![CDATA[
		$(function() {
			var optConfig = {
				checkbox:true,
				expand:true,
				licall:function(li) {
					li = $(li);
					li.children("input:checkbox").click(function() {
						var self = $(this);
						self.liCheckboxClick();
					});
				}
			};
			$("#permissionTree>ul:first").initTree(optConfig);
		});
		//]]>
	</script>
</head>
<body>
角色分配权限
<form id="rolexpermissionForm">
	<div id="permissionTree">
		<ul>
			<my:tree nodeList="permissionList"/>
		</ul>
	</div>
	<input type="hidden" name="idx" value="<s:property value="idx"/>">
</form>
</body>
</html>