<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="my" uri="/sjdf-tags" %>
<!DOCTYPE html>
<html>
<head>
    <script type='text/javascript' src="${staticPath}/js/My97DatePicker/WdatePicker.js"></script>
    <base href="${basePath}"/>
    <title>短信管理</title>
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
    <script type='text/javascript' src="${staticPath}/js/jquery.qtip-1.0.0-rc3.min.js"></script>
    <script type="text/javascript">
        document.domain = "${topDomain}";
        $(function () {
            <my:permission code="06011341" description="短信管理">
            $('#searchBtn').click(function () {
                $("#queryForm").attr('action', 'admin/common/message!sms.action').submit();
            });
            </my:permission>

            <my:permission code="06011342" description="历史短信查询">
            $('#searchHistoryBtn').click(function () {
                $("#queryForm").attr('action', 'admin/common/message!smsHistory.action').submit();
            });
            </my:permission>

            <my:permission code="06011343" description="短信编辑">
            $('#addBtn').click(function () {
                window.open('admin/common/message!editSms.action');
            });

            $('.kzmb_nr').dblclick(function () {
                window.open('admin/common/message!editSms.action?idx=' + $(this).attr('id'));
            });
            </my:permission>

            var $selectSingle = $(".selectSingle").click(function () {
                $selectedAll.attr("checked", ($selectSingle.length == $(".selectSingle:checked").length) ? true : false);
            });

            var $selectedAll = $('#selectedAll').click(function () {
                $selectSingle.attr("checked", this.checked);
            });

            <my:permission code="06011366" description="更新信息状态">
            $('#reSendBtn').click(function () {
                if ($(".selectSingle:checked").length == 0) {
                    jFail("请选择待操作的数据记录");
                    return;
                }
                jConfirm("确定【重新发送】吗?", function () {
                    $('#statusInput').val('<s:property value="@com.sjdf.platform.dictionary.bean.common.SendStatus@WAIT_SEND"/>');
                    $.post('admin/common/message!updateStatus.action', $('#statusForm').serializeArray(), function (res) {
                        if (res['bool']) {
                            jSuccess(res['message'], function () {
                                $('#searchBtn').trigger('click');
                            });
                        } else {
                            jFail(res['message']);
                        }
                    }, 'json');
                });
            });

            $('#manualBtn').click(function () {
                if ($(".selectSingle:checked").length == 0) {
                    jFail("请选择待操作的数据记录");
                    return;
                }
                jConfirm("确定【人工处理完毕】吗?", function () {
                    $('#statusInput').val('<s:property value="@com.sjdf.platform.dictionary.bean.common.SendStatus@MANUAL_HANDLE_COMPLETE"/>');
                    $.post('admin/common/message!updateStatus.action', $('#statusForm').serializeArray(), function (res) {
                        if (res['bool']) {
                            jSuccess(res['message'], function () {
                                $('#searchBtn').trigger('click');
                            });
                        } else {
                            jFail(res['message']);
                        }
                    }, 'json');
                });
            });
            </my:permission>

            //提示框
            $('.tips').each(function() {
                $(this).qtip({
                    content : $('<div></div>').text($(this).attr('desc')).html(), // Use the tooltip attribute of the element for the content
                    style : {
                        padding : 5,
                        tip : "leftMiddle", // Give it a speech bubble tip with automatic corner detection
                        name : 'cream' //'dark', 'cream', 'green', 'red', 'light', 'blue'
                    },
                    position : {
                        corner : {
                            target : 'rightMiddle', // ...and opposite corner
                            tooltip : 'leftMiddle'
                        }
                    },
                    hide: {
                        fixed: true
                    },
                    show : 'mouseover'
                });
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

        .sea-btn {
            display: inline-block;
            background-color: #f5f5f5;
            border: 1px solid #ccc;
            height: 26px;
            line-height: 26px;
            padding: 0 20px;
            margin-right: 10px;
            text-shadow: 1px 1px 0px #fff;
        }
    </style>
</head>
<body>
<form id="queryForm" method="post" style="border: #cccccc solid 1px; width: 99%; height: 100%; margin: 0 auto; margin-top: 10px; margin-bottom: 10px;">
    <table width="100%">
        <tr>
            <th align="right">公司:</th>
            <td><s:select cssStyle="width: 173px;" name="sms.company" list="companyClassList" listKey="attr" listValue="name" headerKey="0" headerValue="===请选择==="/></td>
            <th align="right">系统类型:</th>
            <td><s:select cssStyle="width: 173px;" name="sms.systemType" list="systemTypeList" listKey="attr" listValue="name" headerKey="0" headerValue="===请选择==="/></td>
            <th align="right">所属用户:</th>
            <td><input type="text" name="sms.userId" value="<s:property value="sms.userId"/>"/></td>
            <th align="right">从:</th>
            <td><input name="sms.createTime" value="<s:property value="sms.createTimeInfo"/>" class="Wdate" readonly="readonly" onclick="WdatePicker({isShowClear:true,readOnly:true,startDate:'%y-%M-%d 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss'});"/></td>
        </tr>
        <tr>
            <th align="right">手机号码:</th>
            <td><input type="text" name="sms.address" value="<s:property value="sms.address"/>"/></td>
            <th align="right">短信内容:</th>
            <td><input type="text" name="sms.content" value="<s:property value="sms.content"/>"/></td>
            <th align="right">状态:</th>
            <td><s:select cssStyle="width: 173px;" name="sms.status" list="sendStatusList" listKey="attr" listValue="name" headerKey="0" headerValue="===请选择==="/></td>
            <th align="right">到:</th>
            <td><input name="sms.updateTime" value="<s:property value="sms.updateTimeInfo"/>" class="Wdate" readonly="readonly" onclick="WdatePicker({isShowClear:true,readOnly:true,startDate:'%y-%M-%d 23:59:59',dateFmt:'yyyy-MM-dd HH:mm:ss'});"/></td>
        </tr>
        <tr>
            <td colspan="8" align="center">
                <my:permission code="06011341" description="短信管理">
                    <a href="javascript:void(0);" id="searchBtn" class="sea-btn">开始搜索</a>
                </my:permission>
                <my:permission code="06011366" description="更新信息状态">
                    <a href="javascript:void(0);" id="reSendBtn" class="sea-btn">重新发送</a>
                    <a href="javascript:void(0);" id="manualBtn" class="sea-btn">人工处理完毕</a>
                </my:permission>
                <my:permission code="06011342" description="历史短信查询">
                    <a href="javascript:void(0);" id="searchHistoryBtn" class="sea-btn">历史搜索</a>
                </my:permission>
                <my:permission code="06011343" description="短信编辑">
                    <a href="javascript:void(0);" id="addBtn" class="sea-btn">添加短信</a>
                </my:permission>
            </td>
        </tr>
    </table>
</form>
<div class="queryForm_kzmb_table_3">
    <form id="statusForm" method="post">
        <input type="hidden" name="idx" value="<s:property value="@com.sjdf.platform.dictionary.bean.common.MessageType@SMS"/>">
        <input type="hidden" name="status" id="statusInput">
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr class="kzmb_nm_3">
                <td><input type="checkbox" id="selectedAll"></td>
                <td width="80"><a name="company" class="sort">公司<img alt="点击排序" src="images/down_sort.gif" border="0"/></a></td>
                <td width="80"><a name="systemType" class="sort">系统类型<img alt="点击排序" src="images/down_sort.gif" border="0"/></a></td>
                <td width="80"><a name="userId" class="sort">所属用户<img alt="点击排序" src="images/down_sort.gif" border="0"/></a></td>
                <td width="200">手机号码</td>
                <td>短信内容</td>
                <td width="120">发送时间</td>
                <td width="80"><a name="status" class="sort">发送状态<img alt="点击排序" src="images/down_sort.gif" border="0"/></a></td>
                <td width="80">发送用户</td>
                <td>发送接口</td>
                <td>错误信息</td>
            </tr>
            <s:iterator value="smsList">
                <tr id="${id}" class="kzmb_nr">
                    <td><input type="checkbox" name="ids" value="${id}" class="selectSingle"></td>
                    <td>${companyInfo}</td>
                    <td>${systemTypeInfo}</td>
                    <td>${userId}</td>
                    <td><s:property value="addressInfo"/></td>
                    <td class="tips" desc="<s:property value="content"/>"><s:property value="contentInfo"/></td>
                    <td>${sendTimeInfo}</td>
                    <td>${statusInfo}</td>
                    <td><s:property value="updateUser"/></td>
                    <td><s:property value="sendApiName"/></td>
                    <td><s:property value="remarkInfo"/></td>
                </tr>
            </s:iterator>
        </table>
    </form>
</div>
<my:page/>
<script type='text/javascript' src="${staticPath}/js/adapter-height.js"></script>
</body>
</html>