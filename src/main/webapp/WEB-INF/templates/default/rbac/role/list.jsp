<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="my" uri="/sjdf-tags" %>
<!DOCTYPE html>
<html>
<head>
	<base href="${basePath}"/>
	<title>角色管理</title>
	<%@include file="../../share/head.jsp" %>
	<script type="text/javascript">
		$(function () {
			var currentUI;
			var opts = {checkbox: true, expand: true,
				menu: {
					<my:permission code="06010606">
					"添加角色": function (ui) {
						currentUI = ui;
						var idx = ui.children("input").val();
						$("#addRoleDiv").load("admin/common/platform/RoleAction!beforeAdd.action", {idx: idx}, function () {
							$("#addRoleForm").dialog({
								autoOpen: true,
								modal: true,
								show: "explode",
								hide: "explode",
								buttons: {
									"取消": function () {
										$(this).dialog('close').remove();
									}
									<my:permission code="010602">, "确定": function () {
										var self = $(this);
										$.post("admin/common/platform/RoleAction!add.action", self.serializeArray(), function (re) {
											if (/^[0-9]*$/.test(re)) {
												currentUI.addNode(re, self.find("input[name='role.name']").val(), null, function (li) {
													//checked status
													$(li).children("input").attr("checked", false);
												});
												self.dialog("close").remove();
											} else {
												alert(re);
											}
										});
									}
									</my:permission>
								}
							});
						});
					},
					</my:permission>

					<my:permission code="06010604">
					"修改角色": function (ui) {
						currentUI = ui;
						var idx = currentUI.children("input").val();
						$("#editRoleDiv").load("admin/common/platform/RoleAction!edit.action", {idx: idx}, function (html) {
							$("#editRoleForm").dialog({
								autoOpen: true,
								modal: true,
								show: "explode",
								hide: "explode",
								buttons: {
									"取消": function () {
										$(this).dialog("close");
									}
									<my:permission code="010605">, "确定": function () {
										var self = $(this);
										$.post("admin/common/platform/RoleAction!update.action", self.serializeArray(), function (re) {
											if (re == "success") {
												currentUI.editNode(null, self.find("input[name='role.name']").val());
												currentUI.children("input").attr("checked", false);
											} else {
												alert(re);
											}
											self.dialog("close");
										});
									}
									</my:permission>
								}
							});
						});
					},
					</my:permission>

					<my:permission code="06010603">
					"删除角色": function (ui) {
						if (confirm("确定删除此角色吗?")) {
							currentUI = ui;
							var idx = currentUI.children("input").val();
							$.post("admin/common/platform/RoleAction!delete.action", {idx: idx}, function (re) {
								if (re == "success") {
									currentUI.remove();
								} else {
									alert(re);
								}
							});
						}
					},
					</my:permission>

					<my:permission code="06010901">
					"分配权限": function (ul) {
						var idx = ul.children("input").val();
						var iframe = $("#rolexpermissionIframe");
						iframe.attr("src", "admin/common/platform/rolexpermission!beforeGrant.action?idx=" + idx + "&_random=" + Math.random());
						iframe.load(function () {
							iframe.dialog("open");
						});
					},
					</my:permission>

					<my:permission code="06010607">
					"查看信息": function (ui) {
						var idx = ui.children("input").val();
						$("#roleInforDiv").load("admin/common/platform/RoleAction!show.action", {idx: idx}, "html");
					}
					</my:permission>
				},
				licall: function (li, opts) {
					li = $(li);
					opts.menu && li.menu(opts.menu);
					li.children("span").click(function () {
						$(this).liSpanClick();
					});
					li.children("input:checkbox").attr("disabled", true);
				}
			};

			<my:permission code="06010601">
			//启动tree插件
			$("#roleTree>ul:first").initTree(opts);
			</my:permission>

			//分配权限
			<my:permission code="06010902">
			$("#rolexpermissionIframe").dialog({
				width: 460,
				height: 460,
				autoOpen: false,
				modal: true,
				show: "explode",
				hide: "explode",
				buttons: {
					"取消": function () {
						$(this).dialog("close");
					},
					"确定": function () {
						var self = $(this);
						var iframe = self[0];
						var form = window.frames["rolexpermissionIframe"].document.getElementById("rolexpermissionForm");
						if (!form) {
							self.dialog("close");
							return;
						}
						form = $(form);
						$.post("admin/common/platform/rolexpermission!grant.action", form.serializeArray(), function (re) {
							$("#message").html(re["result"]);
							self.dialog("close");
						});
					}
				}
			});
			</my:permission>
		});
	</script>
</head>
<body>
<div id="roleInforDiv" style="width:50%;float:right;"></div>
<my:permission code="06010601">
	<div id="roleTree" style="width:50%;">
		<p>角色树</p>
		<ul>
			<my:tree nodeList="roleList"/>
		</ul>
	</div>
</my:permission>
<div id="addRoleDiv" title="添加角色"></div>
<div id="editRoleDiv" title="修改角色"></div>
<my:permission code="06010901">
	<iframe id="rolexpermissionIframe" name="rolexpermissionIframe" frameborder="0" width="450px" title="分配权限"></iframe>
</my:permission>
</body>
</html>