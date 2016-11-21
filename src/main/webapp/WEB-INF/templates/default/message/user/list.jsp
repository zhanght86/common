<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="my" uri="/sjdf-tags" %>
<!DOCTYPE html>
<html>
<head>
    <script type='text/javascript' src="${staticPath}/js/My97DatePicker/WdatePicker.js"></script>
    <base href="${basePath}"/>
    <title>信息接口授权管理</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="${staticPath}/css/jquery-ui-1.8.16.custom.css"/>
    <link rel="stylesheet" type="text/css" href="${staticPath}/css/tools_css.css"/>
    <link rel="stylesheet" type="text/css" href="${staticPath}/css/jquery-ui-1.8.16.custom.css"/>
    <script type='text/javascript' src="${staticPath}/js/jquery-1.7.1.min.js"></script>
    <script type='text/javascript' src="${staticPath}/js/jquery-ui-1.8.16.custom.min.js"></script>
    <script type="text/javascript" src="${staticPath}/js/layer/layer.min.js"></script>
    <script type="text/javascript" src="${staticPath}/js/layer/alert.js"></script>
    <script type='text/javascript' src="${staticPath}/js/jquery.blockUI.js"></script>
    <script type='text/javascript' src="${staticPath}/js/blockui.js"></script>
    <script type="text/javascript">
        $(function () {
            $('#searchBtn').click(function () {
                $("#queryForm").attr('action', 'admin/common/apiAuth!list.action').submit();
            });

            <my:permission code="06011322" description="信息接口授权编辑">
            $('#addBtn').click(function () {
                $('#postDiv').empty().load('admin/common/apiAuth!edit.action', {}, function () {
                    var dataForm = $('#dataForm');
                    dataForm.dialog({
                        title: "添加",
                        width: 460,
                        modal: true,
                        autoOpen: true,
                        show: "explode",
                        hide: "explode",
                        close: function () {
                            dataForm.dialog("destroy").remove();
                        },
                        buttons: {
                            <my:permission code="06011323" description="信息接口授权添加或更新">
                            "添加": function () {
                                blockUI();
                                $.post("admin/common/apiAuth!post.action", dataForm.serializeArray(), function (re) {
                                    $.unblockUI();
                                    if (re['bool']) {
                                        jSuccess("添加成功", function () {
                                            $('#searchBtn').trigger('click');
                                        });
                                    } else {
                                        jFail(re['message']);
                                    }
                                }, "json");
                            },
                            </my:permission>
                            "取消": function () {
                                dataForm.dialog("close").remove();
                            }
                        }
                    });
                });
            });

            $('.kzmb_nr').dblclick(function () {
                $('#postDiv').empty().load('admin/common/apiAuth!edit.action', {idx: $(this).attr('id')}, function () {
                    var dataForm = $('#dataForm');
                    dataForm.dialog({
                        title: "编辑",
                        width: 460,
                        modal: true,
                        autoOpen: true,
                        show: "explode",
                        hide: "explode",
                        close: function () {
                            dataForm.dialog("destroy").remove();
                        },
                        buttons: {
                            <my:permission code="06011323" description="信息接口授权添加或更新">
                            "更新": function () {
                                blockUI();
                                $.post("admin/common/apiAuth!post.action", dataForm.serializeArray(), function (re) {
                                    $.unblockUI();
                                    if (re['bool']) {
                                        jSuccess("更新成功", function () {
                                            $('#searchBtn').trigger('click');
                                        });
                                    } else {
                                        jFail(re['message']);
                                    }
                                }, "json");
                            },
                            "添加": function () {
                                blockUI();
                                $('#configId').val(0);
                                $.post("admin/common/apiAuth!post.action", dataForm.serializeArray(), function (re) {
                                    $.unblockUI();
                                    if (re['bool']) {
                                        jSuccess("添加成功", function () {
                                            $('#searchBtn').trigger('click');
                                        });
                                    } else {
                                        jFail(re['message']);
                                    }
                                }, "json");
                            },
                            </my:permission>
                            "取消": function () {
                                dataForm.dialog("close").remove();
                            }
                        }
                    });
                });
            });
            </my:permission>
        });
    </script>
    <style type="text/css">
        .queryForm_kzmb_table_3 {
            width: 99%;
            height: 100%;
            overflow: auto;
            margin: 0 auto;
        }

        .check-box-single-line {
            float: left;
            margin-right: 15px;
        }
    </style>
</head>
<body>
<form id="queryForm" method="post" style="border: #cccccc solid 1px; width: 99%; height: 100%; margin: 0 auto; margin-top: 10px; margin-bottom: 10px;">
    <table width="100%">
        <tr>
            <th align="right">合作伙伴:</th>
            <td><s:select cssStyle="width: 173px;" name="user.company" list="companyClassList" listKey="attr" listValue="name" headerKey="0" headerValue="===请选择==="/></td>
            <th align="right">系统类型:</th>
            <td><s:select cssStyle="width: 173px;" name="user.systemType" list="systemTypeList" listKey="attr" listValue="name" headerKey="0" headerValue="===请选择==="/></td>
            <th align="right">有效状态:</th>
            <td><s:select cssStyle="width: 173px;" name="user.valid" list="validMarkList" listKey="attr" listValue="name" headerKey="0" headerValue="===请选择==="/></td>
            <th align="right">短信接口:</th>
            <td><s:select cssStyle="width: 173px;" name="user.smsApi" list="smsConfigList" listKey="id" listValue="name" headerKey="" headerValue="===请选择==="/></td>
            <th align="right">邮件接口:</th>
            <td><s:select cssStyle="width: 173px;" name="user.emailApi" list="emailConfigList" listKey="id" listValue="name" headerKey="" headerValue="===请选择==="/></td>
        </tr>
        <tr>
            <td colspan="10" align="center">
                <button type="button" id="searchBtn">搜索</button>
                <my:permission code="06011322" description="信息接口授权编辑">
                    <button type="button" id="addBtn">添加</button>
                </my:permission>
            </td>
        </tr>
    </table>
</form>
<div class="queryForm_kzmb_table_3">
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr class="kzmb_nm_3">
            <td><a name="company" class="sort">合作伙伴<img alt="点击排序" src="images/down_sort.gif" border="0"/></a></td>
            <td><a name="systemType" class="sort">系统类型<img alt="点击排序" src="images/down_sort.gif" border="0"/></a></td>
            <td>用户名</td>
            <td>昵称</td>
            <td>密码</td>
            <td>短信接口</td>
            <td>邮件接口</td>
            <td><a name="valid" class="sort">有效状态<img alt="点击排序" src="images/down_sort.gif" border="0"/></a></td>
            <td>操作人</td>
            <td>操作时间</td>
            <td>备注</td>
        </tr>
        <s:iterator value="userList">
            <tr id="${id}" class="kzmb_nr">
                <td>${companyInfo}</td>
                <td>${systemTypeInfo}</td>
                <td><s:property value="userId"/></td>
                <td><s:property value="name"/></td>
                <td><s:property value="connPwd"/></td>
                <td><s:property value="smsApiInfo"/></td>
                <td><s:property value="emailApiInfo"/></td>
                <td>${validInfo}</td>
                <td><s:property value="updateUser"/></td>
                <td><s:property value="updateTimeInfo"/></td>
                <td><s:property value="remark"/></td>
            </tr>
        </s:iterator>
    </table>
</div>
<my:page/>
<div id="postDiv"></div>
</body>
</html>