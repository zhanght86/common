<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="my" uri="/sjdf-tags" %>
<!DOCTYPE html>
<html>
<head>
<base href="${basePath}"/>
<title>用户管理</title>
<%@include file="../../share/head.jsp" %>
<script type="text/javascript">
$(function () {
	var infoTable = $("#InfoTable");
	infoTable.operational();

	<my:permission code="06010702" description="添加用户">
	//添加用户按钮
	$("#add").click(function () {
		$('#addUserForm').dialog('open');
	});

	//添加用户
	$("#addUserForm").dialog({
		width : 460,
		autoOpen: false,
		modal: true,
		show: "explode",
		hide: "explode",
		buttons: {
			"取消": function () {
				$(this).dialog('close');
			},
			"确定": function () {
				//validate form then submit and callback
				if ($('#addUserForm').validate().form()) {
					var self = $(this);
					$.post("admin/common/platform/UserAction!add.action", $('#addUserForm').serializeArray(), function (html) {
						infoTable.find("tbody").append(html);
						self.dialog("close");
						self[0].reset();
					}, "html");
				}
			}
		}
	});
	</my:permission>

	<my:permission code="06010708" description="修改下级用户信息">
	//修改用户按钮
	$("#edit").click(function () {
		var tr = $('#InfoTable tr.selected');
		if (tr.length == 1) {
			var id = tr.attr("id");
			var form = $('#editUserForm');
			form.load("admin/common/platform/UserAction!editOther.action", {idx: id}, function () {
				form.dialog("open");
			});
		}
		else alert("每次仅能选中一项进行修改");
	});

	//修改用户
	$("#editUserForm").dialog({
		autoOpen: false,
		modal: true,
		show: "explode",
		hide: "explode",
		height: 200,
		buttons: {
			"取消": function () {
				$(this).dialog('close');
			}
			<my:permission code="06010709">, "确定": function () {
				//validate form then submit and callback
				if ($('#editUserForm').validate().form()) {
					//update data here
					var tr = $("#InfoTable tr.selected");
					var self = $(this);
					$.post("admin/common/platform/UserAction!updateOther.action", self.serializeArray(), function (html) {
						tr.replaceWith(html);
						self.dialog("close").empty();
					});
				}
			}
			</my:permission>
		}
	});
	</my:permission>

	<my:permission code="06010705" description="修改下级用户密码">
	//修改密码按钮
	$("#editPassword").click(function () {
		var tr = $("#InfoTable tr.selected");
		if (tr.length != 1) {
			alert("请选择一个用户进行修改");
			return;
		}
		var idx = tr.attr("id");
		$("#editPasswordForm input[name='user.id']").val(idx);
		$("#editPasswordForm").dialog("open");
	});

	//修改用户
	$("#editPasswordForm").dialog({
		autoOpen: false,
		modal: true,
		show: "explode",
		hide: "explode",
		buttons: {
			"取消": function () {
				$(this).dialog("close");
			},
			"确定": function () {
				var form = $(this);
				if (form.validate().form()) {
					$.post("admin/common/platform/UserAction!changeOtherPassword.action", form.serializeArray(), function () {
						form[0].reset();
						form.dialog("close");
					});
				}
			}
		}
	});
	</my:permission>

	<my:permission code="06010712" description="启用,禁用用户">
	//启用/禁用用户
	$("#forbidTag").click(function () {
		var tr = $("#InfoTable tr.selected");
		if (tr.length == 1) {
			var idx = tr.attr("id");
			var td = $("#forbid" + idx);
			$("<p title='启用/禁用'>确定要启用/禁用此用户吗?</p>").dialog({
				modal: true, autoOpen: true,
				buttons: {
					"取消": function () {
						$(this).dialog('close');
					},
					"确定": function () {
						var self = $(this);
						$.post("admin/common/platform/UserAction!forbid.action", {idx: idx}, function (re) {
							if (re["bool"]) {
								self.dialog("close");
								td.html(re["message"]);
							} else {
								self.html(re["message"]);
							}
						}, "json");
					}}});
		} else {
			alert("请选择一行进行操作");
		}
	});
	</my:permission>

	<my:permission code="06010703" description="删除用户">
	//删除用户
	$('#delete').click(function () {
		var tr = $('#InfoTable tr.selected');
		if (tr.length == 1) {
			//delete data from database
			$("<p title='删除信息'>确定要删除此用户吗?</p>").dialog({
				modal: true, autoOpen: true,
				buttons: {
					"取消": function () {
						$(this).dialog('close');
					},
					"删除": function () {
						var self = $(this);
						$.post("admin/common/platform/UserAction!delete.action", {idx: tr.attr("id")}, function (re) {
							if (re == "success") {
								tr.remove();
								self.dialog("close");
							} else {
								self.html(re);
							}
						});
					}}});
		} else {
			alert("每次仅能选中一项进行删除");
		}
	});
	</my:permission>

	<my:permission code="06011001" description="用户分配角色-数据准备">
	//userxrole dialog
	$("#userxroleIframe").dialog({
		modal: true,
		autoOpen: false,
		show: "explode",
		hide: "explode",
		height: 350,
		buttons: {
			"取消": function () {
				$(this).dialog("close");
			}
			<my:permission code="06011002" description="用户分配角色">, "确定": function () {
				var self = $(this);
				var form = window.frames["userxroleIframe"].document.getElementById("userxroleForm");
				if (!form) {
					self.dialog("close");
					return;
				}
				form = $(form);
				var tr = $("#InfoTable tr.selected");
				var roleTd = $("#roleName" + tr.attr("id"));
				$.post("admin/common/platform/userXrole!grant.action", form.serializeArray(), function (re) {
					roleTd.html(re);
					self.dialog("close");
				});
			}
			</my:permission>
		}
	});

	$("#userxrole").click(function () {
		var tr = $("#InfoTable tr.selected");
		if (tr.length == 1) {
			var idx = tr.attr("id");
			var frame = $("#userxroleIframe");
			frame.attr("src", "admin/common/platform/userXrole!beforeGrant.action?idx=" + idx + "&_random=" + Math.random());
			frame.dialog("open");
		} else {
			alert("每次仅能选中一项进行操作");
		}
	});
	</my:permission>

	$("input").tip();//input hover tip
	$("td").tip({tipClass: "linktip", stay: 60000, left: -180});
});
</script>
</head>
<body>
<my:permission code="06010701">
	<div id="toolbar" class="space">
		<my:permission code="06010702">
			<a id="add"><span class="ui-icon ui-icon-plus"></span>添加用户</a>
		</my:permission>

		<my:permission code="06010708">
			<a id="edit"><span class="ui-icon ui-icon-pencil"></span>修改信息</a>
		</my:permission>

		<my:permission code="06010704">
			<a id="editPassword"><span class="ui-icon ui-icon-pencil"></span>修改密码</a>
		</my:permission>

		<a id="forbidTag"><span class="ui-icon ui-icon-pencil"></span>启用/禁用</a>

		<my:permission code="06010703">
			<a id="delete"><span class="ui-icon ui-icon-trash"></span>删除用户</a>
		</my:permission>
		<span class="ui-icon ui-icon-grip-dotted-vertical"></span>

		<my:permission code="06011001">
			<a id="userxrole">分配角色</a>
		</my:permission>
	</div>
	<table id="InfoTable" class="manage-table space">
		<thead>
		<tr>
			<th>系统类型</th>
			<th>用户名</th>
			<th>真实姓名</th>
			<th>是否禁用</th>
			<th>角色</th>
		</tr>
		</thead>
		<tbody>
		<s:iterator value="userList" id="u">
			<tr id="<s:property value="id"/>">
				<td><s:property value="systemTypeInfo"/></td>
				<td><s:property value="username"/></td>
				<td><s:property value="name"/></td>
				<td id="forbid<s:property value="id"/>"><s:property value="forbidInfor"/></td>
				<td id="roleName<s:property value="id"/>"><s:property value="role.name"/></td>
			</tr>
		</s:iterator>
		</tbody>
	</table>
	<my:page/>

	<my:permission code="06010702">
		<form action="" name="addItem" id="addUserForm" title="添加用户">
			<label class="input">系统类型：</label>
			<s:select name="user.systemType" value="@com.sjdf.platform.dictionary.bean.SystemType@EISS_COMMON" list="systemTypeList" listKey="attr" listValue="name"/><br/>
			<label class="input">用户名：</label>
			<input type="text" name="user.username" id="user.username" class="required" maxlength="50" minlength="6" tip="至少六位"><br>
			<label class="input">密码：</label>
			<input type="password" name="user.password" id="password" class="required" maxlength="50" minlength="6" tip="至少六位"><br>
			<label class="input">确认密码：</label>
			<input type="password" name="repassword" id="user.repassword" class="required" equalTo="#password" maxlength="50" minlength="6"><br>
			<label class="input">真实姓名：</label>
			<input type="text" name="user.name" id="user.name" class="required" maxlength="50"><br>
			<label class="input">是否禁用：</label>
			<input name="user.forbidFlag" type="radio" value='<s:property value="@com.sjdf.platform.dictionary.bean.WhetherState@YES"/>'>是&nbsp;
			<input name="user.forbidFlag" type="radio" value='<s:property value="@com.sjdf.platform.dictionary.bean.WhetherState@NO"/>' checked="checked">否<br>
			<label class="input">能否创建用户：</label>
			<input name="user.createFlag" type="radio" value='<s:property value="@com.sjdf.platform.dictionary.bean.WhetherState@YES"/>' checked="checked">是&nbsp;
			<input name="user.createFlag" type="radio" value='<s:property value="@com.sjdf.platform.dictionary.bean.WhetherState@NO"/>'>否<br>
		</form>
	</my:permission>
	<form action="" name="editItem" id="editUserForm" title="修改用户"></form>

	<form action="" id="editPasswordForm" title="修改密码">
		<input type="hidden" name="user.id">
		<label class="input">新密码:</label>
		<input type="password" name="user.password" id="passwordEdit" class="required" maxlength="20" minlength=6 tip="至少六位">
		<label class="input">确认密码:</label>
		<input type="password" name="repassword" id="repassword" equalTo="#passwordEdit" class="required" maxlength="20" minlength=6 tip="至少六位">
	</form>

	<iframe id='userxroleIframe' name="userxroleIframe" width='300' frameborder='0' title='分配角色'></iframe>
</my:permission>
</body>
</html>