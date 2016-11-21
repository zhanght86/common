<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="my" uri="/sjdf-tags" %>
<!DOCTYPE html>
<html>
<head>
    <script type='text/javascript' src="${staticPath}/js/My97DatePicker/WdatePicker.js"></script>
    <base href="${basePath}"/>
    <title>邮件管理</title>
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
        document.domain = "${topDomain}";
        $(function () {
            <my:permission code="06011361" description="邮件管理">
            $('#searchBtn').click(function () {
                $("#queryForm").attr('action', 'admin/common/message!email.action').submit();
            });
            </my:permission>

            <my:permission code = "06011362" description="历史邮件查询">
            $('#searchHistoryBtn').click(function () {
                $("#queryForm").attr('action', 'admin/common/message!emailHistory.action').submit();
            });
            </my:permission>

            <my:permission code = "06011364" description="历史邮件详情">
            $('.kzmb_nr').dblclick(function () {
                $('#infoDiv').empty().load('admin/common/message!infoEmail.action', {idx: $(this).attr('id')}, function () {
                    var contentDiv = $('#contentDiv');
                    contentDiv.dialog({
                        title: "邮件详情",
                        width: 920,
                        modal: true,
                        autoOpen: true,
                        show: "explode",
                        hide: "explode",
                        close: function () {
                            contentDiv.dialog("destroy").remove();
                        },
                        buttons: {
                            "关闭": function () {
                                contentDiv.dialog("close").remove();
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

        .info_table {
            border-top: 1px solid #ccc;
            border-left: 1px solid #ccc;
        }

        .info_table th, .info_table td {
            height: 26px;
            border-right: 1px solid #ccc;
            border-bottom: 1px solid #ccc;
        }
    </style>
</head>
<body>
<form id="queryForm" method="post" style="border: #cccccc solid 1px; width: 99%; height: 100%; margin: 0 auto; margin-top: 10px; margin-bottom: 10px;">
    <table width="100%">
        <tr>
            <th align="right">公司:</th>
            <td><s:select cssStyle="width: 173px;" name="email.company" list="companyClassList" listKey="attr" listValue="name" headerKey="0" headerValue="===请选择==="/></td>
            <th align="right">系统类型:</th>
            <td><s:select cssStyle="width: 173px;" name="email.systemType" list="systemTypeList" listKey="attr" listValue="name" headerKey="0" headerValue="===请选择==="/></td>
            <th align="right">所属用户:</th>
            <td><input type="text" name="email.userId" value="<s:property value="email.userId"/>"/></td>
            <th align="right">从:</th>
            <td><input name="email.createTime" value="<s:property value="email.createTimeInfo"/>" class="Wdate" readonly="readonly" onclick="WdatePicker({isShowClear:true,readOnly:true,startDate:'%y-%M-%d 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss'});"/></td>
        </tr>
        <tr>
            <th align="right">邮件地址:</th>
            <td><input type="text" name="email.address" value="<s:property value="email.address"/>"/></td>
            <th align="right">邮件标题:</th>
            <td><input type="text" name="email.title" value="<s:property value="email.title"/>"/></td>
            <th align="right">状态:</th>
            <td><s:select cssStyle="width: 173px;" name="email.status" list="sendStatusList" listKey="attr" listValue="name" headerKey="0" headerValue="===请选择==="/></td>
            <th align="right">到:</th>
            <td><input name="email.updateTime" value="<s:property value="email.updateTimeInfo"/>" class="Wdate" readonly="readonly" onclick="WdatePicker({isShowClear:true,readOnly:true,startDate:'%y-%M-%d 23:59:59',dateFmt:'yyyy-MM-dd HH:mm:ss'});"/></td>
        </tr>
        <tr>
            <th align="right">邮件内容:</th>
            <td><input type="text" name="email.content" value="<s:property value="email.content"/>"/></td>
            <td colspan="6" align="center">
                <my:permission code="06011361" description="邮件管理">
                    <a href="javascript:void(0);" id="searchBtn" class="sea-btn">开始搜索</a>
                </my:permission>
                <my:permission code="06011362" description="历史邮件查询">
                    <a href="javascript:void(0);" id="searchHistoryBtn" class="sea-btn">历史搜索</a>
                </my:permission>
            </td>
        </tr>
    </table>
</form>
<div class="queryForm_kzmb_table_3">
    <form id="statusForm" method="post">
        <input type="hidden" name="idx" value="<s:property value="@com.sjdf.platform.dictionary.bean.common.MessageType@EMAIL"/>">
        <input type="hidden" name="status" id="statusInput">
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr class="kzmb_nm_3">
                <td width="80"><a name="company" class="sort">公司<img alt="点击排序" src="images/down_sort.gif" border="0"/></a></td>
                <td width="80"><a name="systemType" class="sort">系统类型<img alt="点击排序" src="images/down_sort.gif" border="0"/></a></td>
                <td width="80"><a name="userId" class="sort">所属用户<img alt="点击排序" src="images/down_sort.gif" border="0"/></a></td>
                <td width="200">邮件地址</td>
                <td>邮件标题</td>
                <td>邮件内容</td>
                <td width="120">发送时间</td>
                <td width="80"><a name="status" class="sort">发送状态<img alt="点击排序" src="images/down_sort.gif" border="0"/></a></td>
                <td width="80">发送用户</td>
                <td>发送接口</td>
                <td>错误信息</td>
            </tr>
            <s:iterator value="emailList">
                <tr id="${id}" class="kzmb_nr">
                    <td>${companyInfo}</td>
                    <td>${systemTypeInfo}</td>
                    <td>${userId}</td>
                    <td><s:property value="addressInfo"/></td>
                    <td><s:property value="titleInfo"/></td>
                    <td><s:property value="contentInfo"/></td>
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
<div id="infoDiv"></div>
<script type='text/javascript' src="${staticPath}/js/adapter-height.js"></script>
</body>
</html>