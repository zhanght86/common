<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="my" uri="/sjdf-tags"%>
<!DOCTYPE html>
<html>
<head>
<base href="${basePath}" />
<title>权限树管理</title>
<%@include file="../../share/head.jsp"%>
<script type="text/javascript">
		$(function () {
			var currentUI;
			var opts = {checkbox: false, expand: true,
				menu: {
					<my:permission code="06010502">
					"添加权限结点": function (ui) {
						currentUI = ui;
						var idx = ui.children("input").val();
						var div = $("#addPermissionNodeDiv");
						$("#addPermissionNodeForm input[name='permissionNode.parent.id']").val(idx);
						div.dialog("open");
					},
					</my:permission>

					<my:permission code="06010503">
					"修改权限结点": function (ui) {
						currentUI = ui;
						var idx = currentUI.children("input").val();
						$("#editPermissionNodeDiv").load("admin/common/platform/PermissionNodeAction!edit.action", {idx: idx}, function (html) {
							$("#editPermissionNodeForm").dialog({
								width: 460,
								autoOpen: true,
								modal: true,
								show: "explode",
								hide: "explode",
								buttons: {
									"取消": function () {
										$(this).dialog("close").remove();
									}
									<my:permission code="010504">
									,"确定": function () {
										var self = $(this);
										$.post("admin/common/platform/PermissionNodeAction!update.action", self.serializeArray(), function (re) {
											if (re == "success") {
												currentUI.editNode(null, self.find("input[name='permissionNode.name']").val());
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

					<my:permission code="06010505">
					"删除权限结点": function (ui) {
						if (confirm("确定删除此权限节点吗?")) {
							currentUI = ui;
							var idx = currentUI.children("input").val();
							$.post("admin/common/platform/PermissionNodeAction!delete.action", {idx: idx}, function (re) {
								if (re == "success") {
									currentUI.remove();
								} else {
									alert(re);
								}
							});
						}
					}
					</my:permission>
				},
				pmenu: {
					<my:permission code="06010802">
					"移除": function (ui) {
						var idx = ui.children("input").val();
						$.post("admin/common/platform/GrantPermissionAction!remove.action", {idx: idx}, function () {
							ui.find("span.selected").removeClass("selected");
							$("#permissionListDiv ul.list").append(ui.remove().draggable(dragConfig));
						});
					}
					</my:permission>
				},
				licall: function (li, opt) {
					var self = $(li);
					if (self.children("input").attr("name") == "ids") {
						opt.defaultLicall(li, opt);
						//使权限可往tree上拖
						self.droppable(droppableConfig);
					}
					//root结点可以点击和右键事件
					else if (self.children("input").attr("name") == "root") {
						opt.defaultLicall(li, opt);
					}
					//这是权限结点
					else {
						self.menu(opts.pmenu);
						self.children("span").click(function () {
							$(this).liSpanClick();
						});
					}
				}
			};

			//添加权限节点
			<my:permission code="06010502">
			$("#addPermissionNodeDiv").dialog({
				width: 460,
				autoOpen: false,
				modal: true,
				show: "explode",
				hide: "explode",
				buttons: {
					"取消": function () {
						$(this).dialog('close');
					},
					"确定": function () {
						var form = $("#addPermissionNodeForm");
						var self = $(this);
						$.post("admin/common/platform/PermissionNodeAction!add.action", form.serializeArray(), function (html) {
							if (html.indexOf("<li>") > 0) {
								currentUI.addNodeList(html, null, function (htm) {
									htm.droppable(droppableConfig);
								});
								self.dialog("close");
								form[0].reset();
							} else {
								alert(html);
							}
						}, "html");
					}
				}
			});
			</my:permission>

			//使权限可选
			$("#permissionListDiv ul.list li").live("click", function (e) {
				e = e || window.event;
				if (e.ctrlKey) {
					$(this).toggleClass("selected");
				} else {
					$(this).parents("ul:first li").removeClass("selected");
					$(this).addClass("selected");
				}
			});


			var droppableConfig = {
				over: function () {
					$(this).children("span").addClass("selected");
				},
				out: function () {
					$(this).children("span").removeClass("selected");
				},
				<my:permission code="06010801">
				drop: function () {
					var idx = $(this).children("input").val();
					if (!idx || idx == "0") {
						alert("不能分配给根结点");
						return;
					}
					var ids = [];
					var x = $("#permissionListDiv li.selected");
					x.each(function () {
						var v = $(this).children("input").val();
						ids.push(v);
					});

					var data = {idx: idx, ids: ids};
					//准备grant
					var self = $(this);
					$.post("admin/common/platform/GrantPermissionAction!grant.action", $.param(data, true), function () {
						//取消各个绑定信息
						x.each(function () {
							$(this).unbind(".draggable").removeClass("ui-draggable ui-draggable-dragging ui-draggable-disabled selected").css({top: 0, left: 0});
						});
						self.addNodeList(x, {menu: false});
					});
				},
				</my:permission>
				greedy: true
			};

			<my:permission code="06010801">
			//启动拖动事件
			var dragConfig = {
				drag: function (event, ui) {
					//使自己也被选中,以便drop时正确计算
					$(this).addClass("selected");
					var select = $("#permissionListDiv li.selected");
					var offset = ui.position;
					select.each(function () {
						$(this).css({top: offset.top, left: offset.left})
					});
				},
				revert: function (dropped) {
					var draggable = $(this).data("draggable");
					if (!dropped) {
						var select = $("#permissionListDiv li.selected");
						select.animate(draggable.originalPosition, parseInt(draggable.options.revertDuration, 10));
					}
					return false;
				}
			};
			$("#permissionListDiv>ul:first").initTree({checkbox: false, expand: true});
			$("#permissionListDiv ul.list li").draggable(dragConfig);
			</my:permission>

			<my:permission code="06010501">
			//启动tree插件
			$("#permissionNodeTree>ul:first").initTree(opts);
			</my:permission>
		});
	</script>
</head>
<body>
	<my:permission code="06010501">
		<div id="permissionListDiv" style="width: 50%; float: right;">
			<b>权限值列表</b>
			<ul>
				<s:iterator value="permissionMap">
					<li><span><s:property value="key.name"/></span>
						<ul class="list">
							<s:iterator value="value">
								<li><input type="checkbox" value="<s:property value="id"/>" style="display: none;" /> <span><s:property value="name" /></span></li>
							</s:iterator>
						</ul>
					</li>
				</s:iterator>
			</ul>
		</div>
		<div id="permissionNodeTree" style="width: 50%;">
			<p>权限树</p>
			<ul>
				<my:tree nodeList="treeNodeList" />
			</ul>
		</div>

		<my:permission code="06010502">
			<div id="addPermissionNodeDiv" title="添加权限结点">
				<form id="addPermissionNodeForm">
					<label for="addPermissionNodeName" style="float: left">名称:</label> <input type="text" id="addPermissionNodeName" name="permissionNode.name" class="required" style="width: 280px;" /><br /> <input type="hidden" name="permissionNode.parent.id" value="<s:property value="role.id"/>">
				</form>
			</div>
		</my:permission>

		<my:permission code="06010503">
			<div id="editPermissionNodeDiv" title="修改权限结点"></div>
		</my:permission>
	</my:permission>
</body>
</html>