<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="my" uri="/sjdf-tags" %>
<!DOCTYPE html>
<html>
<head>
    <base href="${basePath}"/>
    <title>权限信息管理</title>
    <meta charset="UTF-8">
    <%@include file="../../share/head.jsp" %>
    <script type="text/javascript">
        $(function () {
            //修改
            <my:permission code="06010402">
            $(".kzmb_nr").live("dblclick", function () {
                var $this = $(this);
                $("#editPermissionDiv").empty().load("admin/common/platform/PermissionAction!edit.action", {
                    idx: $this.attr("id")
                }, function () {
                    var dataForm = $("#dataForm").dialog({
                        width: 460,
                        modal: true,
                        autoOpen: true,
                        show: "explode",
                        hide: "explode",
                        close: function (event, ui) {
                            dataForm.dialog("destroy").remove();
                        },
                        buttons: {
                            "取消": function () {
                                dataForm.dialog("close").remove();
                            }
                            <my:permission code="010403">
                            , "提交": function () {
                                $.post("admin/common/platform/PermissionAction!update.action", $(this).serializeArray(), function (re) {
                                    $this.replaceWith(re);
                                    dataForm.dialog("close").remove();
                                }, "html");
                            }
                            </my:permission>
                        }
                    });
                });
            });
            </my:permission>
        })
    </script>
    <style type="text/css">
        .queryForm_kzmb_table_3 {
            width: 99%;
            height: 100%;
            overflow: auto;
            margin: 0 auto;
            margin-top: 10px;
            margin-bottom: 10px;
        }

        .kzmb_nr {
            cursor: pointer;
        }
    </style>
</head>
<body>
<my:permission code="06010401">
    <div class="queryForm_kzmb_table_3">
        <form id="queryForm" action="admin/common/platform/PermissionAction!list.action" method="post">
            系统类型:<s:select name="permission.systemType" list="systemTypeList" listKey="attr" listValue="name" headerKey="0" headerValue="==请选择=="/>
            代码:<input type="text" style="width: 160px" name="permission.code" value='<s:property value="permission.code"/>'/>
            类名:<input type="text" style="width: 160px" name="permission.className" value='<s:property value="permission.className"/>'/>
            方法名:<input type="text" style="width: 160px" name="permission.method" value='<s:property value="permission.method"/>'/>
            权限名:<input type="text" style="width: 160px" name="permission.name" value='<s:property value="permission.name"/>'/>
            是否是菜单:<s:select name="permission.isMenu" list="whetherStateList" listKey="attr" listValue="name" headerKey="0" headerValue="==请选择=="/>
            <button>搜索</button>
        </form>
    </div>
    <div class="kzmb_table_3" style="float:left;padding-left:7px;">
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr class="kzmb_nm_3">
                <td>系统类型</td>
                <td>代码</td>
                <td>类名</td>
                <td>方法名</td>
                <td>权限名</td>
                <td>请求路径</td>
                <td>菜单?</td>
                <td>顺序</td>
                <td>依附权限</td>
            </tr>
            <tbody>
            <s:iterator value="permissionList">
                <tr class="content kzmb_nr" id="<s:property value="id"/>">
                    <td><s:property value="systemTypeInfo"/></td>
                    <td><s:property value="code"/></td>
                    <td><s:property value="className"/></td>
                    <td><s:property value="method"/></td>
                    <td><s:property value="name"/></td>
                    <td><s:property value="url"/></td>
                    <td><s:property value="isMenuInfo"/></td>
                    <td><s:property value="orderBy"/></td>
                    <td>
                        <s:if test="supportedPermission !=null">
                            <s:property value="supportedPermission.name"/>(<s:property value="supportedPermission.code"/>)
                        </s:if>
                    </td>
                </tr>
            </s:iterator>
            </tbody>
        </table>
    </div>
    <my:page/>
    <div id="editPermissionDiv" title="修改权限"></div>
</my:permission>
</body>
</html>