<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="my" uri="/sjdf-tags" %>
<!DOCTYPE html>
<html>
<head>
	<base href="${basePath}"/>
	<title>用户角色分配</title>
	<%@include file="../../../share/head.jsp" %>
	<script type="text/javascript">
		$(function() {
			var optConfig = {
				checkbox:true,
				expand:true,
				licall:function(li) {
					$(li).children("input:checkbox").click(function() {
						var self = $(this);
						var checked = self.is(":checked");
						self.parents("ul.tree").find("input:checkbox[checked=true]").prop("checked", false).attr("name", "ids");
						if(checked){
							self.attr("name", "roleId").parents("li").children("input:checkbox").prop("checked", true);
						}
					});
				}
			};
			var tree = $("#roleTree>ul:first");
			tree.initTree(optConfig);
			tree.find("input:checkbox[checked=true]").attr("name", "roleId");
		});
	</script>
</head>
<body>
用户角色分配
<form action="" id="userxroleForm">
	<div id="roleTree">
		<ul>
			<my:tree nodeList="roleList" expand="true"/>
		</ul>
	</div>
	<input type="hidden" name="idx" value="<s:property value="idx"/>">
</form>
</body>
</html>