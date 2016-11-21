<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="my" uri="/sjdf-tags" %>
<!DOCTYPE html>
<html>
<head>
    <script type='text/javascript' src="${staticPath}/js/My97DatePicker/WdatePicker.js"></script>
    <base href="${basePath}"/>
    <title>信息接口配置管理</title>
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
                $("#queryForm").attr('action', 'admin/common/config!list.action').submit();
            });

            <my:permission code="06011302" description="信息发送接口编辑">
                $('#addBtn').click(function () {
                $('#postDiv').empty().load('admin/common/config!edit.action', {}, function () {
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
                            <my:permission code="06011303" description="信息发送接口添加或更新">
                            "添加": function () {
                                blockUI();
                                $.post("admin/common/config!post.action", dataForm.serializeArray(), function (re) {
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
                var idx = $(this).attr('id');
                $('#postDiv').empty().load('admin/common/config!edit.action', {idx: idx}, function () {
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
                            <my:permission code="06011303" description="信息发送接口添加或更新">
                            "更新": function () {
                                blockUI();
                                $.post("admin/common/config!post.action", dataForm.serializeArray(), function (re) {
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
                                $.post("admin/common/config!post.action", dataForm.serializeArray(), function (re) {
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
                            <my:permission code="06011304" description="信息发送接口删除">
                            "删除": function () {
                                blockUI();
                                $.post("admin/common/config!del.action", {idx: idx}, function (re) {
                                    $.unblockUI();
                                    if (re['bool']) {
                                        jSuccess("删除成功", function () {
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

            $('#messageTypeSelect').live('change', function () {
                var option = '<option value="">===请选择===</option>';
                $.post('admin/common/config!sendApiImpl.action', {idx: $(this).val()}, function (res) {
                    var result = res['result'];
                    for (var key in result) {
                        option += '<option value="' + key + '">' + result[key] + '</option>';
                    }
                    $('#sendApiImplSelect').empty().append(option);
                }, 'json');
            });
        });
    </script>
    <style type="text/css">
        .queryForm_kzmb_table_3 {
            width: 99%;
            height: 100%;
            overflow: auto;
            margin: 0 auto;
        }
    </style>
</head>
<body>
<form id="queryForm" method="post" style="border: #cccccc solid 1px; width: 99%; height: 100%; margin: 0 auto; margin-top: 10px; margin-bottom: 10px;">
    <table width="100%">
        <tr>
            <th align="right">名称:</th>
            <td><input type="text" name="config.name" value="<s:property value="config.name"/>"/></td>
            <th align="right">消息类型:</th>
            <td><s:select cssStyle="width: 173px;" name="config.messageType" list="messageTypeList" listKey="attr" listValue="name" headerKey="0" headerValue="===请选择==="/></td>
            <th align="right">有效状态:</th>
            <td><s:select cssStyle="width: 173px;" name="config.valid" list="validMarkList" listKey="attr" listValue="name" headerKey="0" headerValue="===请选择==="/></td>
            <th align="right">发送接口:</th>
            <td><s:select cssStyle="width: 173px;" name="config.sendApiImpl" list="messageApiImplMap" listKey="key" listValue="value" headerKey="0" headerValue="===请选择==="/></td>
        </tr>
        <tr>
            <td colspan="8" align="center">
                <button type="button" id="searchBtn">搜索</button>
                <my:permission code="06011302" description="信息发送接口编辑">
                    <button type="button" id="addBtn">添加</button>
                </my:permission>
            </td>
        </tr>
    </table>
</form>
<div class="queryForm_kzmb_table_3">
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr class="kzmb_nm_3">
            <td width="140">名称</td>
            <td width="80"><a name="messageType" class="sort">消息类型<img alt="点击排序" src="images/down_sort.gif" border="0"/></a></td>
            <td width="140"><a name="sendApiImpl" class="sort">发送接口<img alt="点击排序" src="images/down_sort.gif" border="0"/></a></td>
            <td width="80"><a name="valid" class="sort">有效状态<img alt="点击排序" src="images/down_sort.gif" border="0"/></a></td>
            <td width="120">用户名</td>
            <td width="130">密码</td>
            <td>主机地址</td>
            <td>源地址</td>
            <td>个人名称</td>
            <td>操作人</td>
            <td width="120">操作时间</td>
        </tr>
        <s:iterator value="configList">
            <tr id="${id}" class="kzmb_nr">
                <td><s:property value="name"/></td>
                <td>${messageTypeInfo}</td>
                <td>${sendApiImplInfo}</td>
                <td>${validInfo}</td>
                <td><s:property value="userName"/></td>
                <td><s:property value="pwd"/></td>
                <td><s:property value="hostUrl"/></td>
                <td><s:property value="sourceAddress"/></td>
                <td><s:property value="personalName"/></td>
                <td><s:property value="updateUser"/></td>
                <td><s:property value="updateTimeInfo"/></td>
            </tr>
        </s:iterator>
    </table>
</div>
<my:page/>
<div id="postDiv"></div>
</body>
</html>