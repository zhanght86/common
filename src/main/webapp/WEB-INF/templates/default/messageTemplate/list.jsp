<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="my" uri="/sjdf-tags" %>
<!DOCTYPE html>
<html>
<head>
    <base href="${basePath}"/>
    <title>邮件短信模板管理</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="css/jquery-ui-1.8.16.custom.css"/>
    <link rel="stylesheet" type="text/css" href="css/tools_css.css"/>
    <script type='text/javascript' src="js/jquery-1.6.4.min.js"></script>
    <script type='text/javascript' src="js/jquery-ui-1.8.16.custom.min.js"></script>
    <script type='text/javascript' src="js/jquery.blockUI.js"></script>
    <script type='text/javascript' src="js/blockui.js"></script>
    <script type='text/javascript' src="js/jquery.qtip-1.0.0-rc3.min.js"></script>
    <script type="text/javascript">
        $(function () {
            document.domain = "${topDomain}";
            var error = "${error}";
            if (error != "") {
                alert(error);
            }

            //查询
            $("select").change(function () {
                $("#queryForm").submit();
            });

            //添加
            $("#add").click(function () {
                var systemType = $("#systemType option:selected").val();
                if (!systemType) {
                    alert("请选择系统类型");
                    return;
                }
                window.self.location = "admin/common/MessageTemplateAction!preAdd.action?template.systemType=" + systemType;
            });
            //修改
            $(".edit").click(function () {
                var idx = $(this).parent("td").parent("tr").attr("id");
                window.self.location = "admin/common/MessageTemplateAction!edit.action?idx=" + idx;
            });
            //删除
            $(".del").click(function () {
                if (confirm("确定删除?")) {
                    var idx = $(this).parent("td").parent("tr").attr("id");
                    window.self.location = "admin/common/MessageTemplateAction!del.action?idx=" + idx;
                }
            });

            //提示框
            $('td.tip').each(function () {
                $(this).qtip({
                    content: $(this).attr('tip'), // Use the tooltip attribute of the element for the content
                    style: {
                        padding: 5,
                        tip: "leftMiddle", // Give it a speech bubble tip with automatic corner detection
                        name: 'dark' //'dark', 'cream', 'green', 'red', 'light', 'blue'
                    },
                    position: {
                        corner: {
                            target: 'rightMiddle', // ...and opposite corner
                            tooltip: 'leftMiddle'
                        }
                    },
                    hide: {
                        fixed: true
                    },
                    show: 'mouseover'
                });
            });
        });
    </script>
</head>
<body>
<form id="queryForm" action="admin/common/MessageTemplateAction!list.action" method="post" style="width: 99%;height: 100%;margin: 0 auto;margin-top: 10px;margin-bottom: 10px;">
    <my:filterSelect id="systemType" name="template.systemType" list="systemTypeList" listKey="attr" listValue="name" headerKey="" headerValue="=====请选择系统类型====="/>
    <my:filterSelect id="templateType" name="template.templateType" list="templateTypeList" listKey="attr" listValue="name" headerKey="" headerValue="=====请选择模板标识====="/>
    邮件有效状态:<s:select name="template.emailValid" list="validMarkList" listKey="attr" listValue="name" headerKey="" headerValue="==请选择=="/>
    短信有效状态:<s:select name="template.smsValid" list="validMarkList" listKey="attr" listValue="name" headerKey="" headerValue="==请选择=="/>
    邮件标题:<input type="text" name="template.title" value="${template.title}"/>
    短信:<input type="text" name="template.sms" value="${template.sms}"/>
    邮件:<input type="text" name="template.email" value="${template.email}"/>
    <button>搜索</button>
    <button type="button" id="add">添加</button>
</form>
<div class="kzmb_table_3">
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr class="kzmb_nm_3">
            <td><a name="systemType" class="sort">系统类型<img alt="点击排序" src="images/down_sort.gif" border="0"/></a></td>
            <td><a name="templateType" class="sort">模板标识<img alt="点击排序" src="images/down_sort.gif" border="0"/></a></td>
            <td><a name="title" class="sort">邮件标题<img alt="点击排序" src="images/down_sort.gif" border="0"/></a></td>
            <td>短信</td>
            <td><a name="emailValid" class="sort">邮件有效状态<img alt="点击排序" src="images/down_sort.gif" border="0"/></a></td>
            <td><a name="smsValid" class="sort">短信有效状态<img alt="点击排序" src="images/down_sort.gif" border="0"/></a></td>
            <td>操作</td>
        </tr>
        <s:iterator value="templateList">
            <tr id="${id}" class="kzmb_nr">
                <td>${systemTypeInfo}</td>
                <td>${templateTypeInfo}</td>
                <td class="tip" tip='${email}'>${title}</td>
                <td>${sms}</td>
                <td>${emailValidInfo}</td>
                <td>${smsValidInfo}</td>
                <td>
                    <button type="button" class="edit">修改</button>
                    <button type="button" class="del">删除</button>
                </td>
            </tr>
        </s:iterator>
    </table>
</div>
<my:page/>
<script type='text/javascript' src="${staticPath}/js/adapter-height.js"></script>
</body>
</html>