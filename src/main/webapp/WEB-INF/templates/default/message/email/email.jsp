<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="my" uri="/sjdf-tags" %>
<!DOCTYPE html>
<html>
<head>
    <base href="${basePath}"/>
    <title>邮件管理</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="ueditor/themes/default/ueditor.css"/>
    <link rel="stylesheet" type="text/css" href="css/jquery-ui-1.8.16.custom.css"/>
    <link rel="stylesheet" type="text/css" href="css/reset.css"/>
    <link rel="stylesheet" type="text/css" href="css/style.css"/>
    <link rel="stylesheet" type="text/css" href="css/tools_css.css"/>
    <link rel="stylesheet" type="text/css" href="css/smsMail.css"/>
    <script type='text/javascript' src="${staticPath}/js/My97DatePicker/WdatePicker.js"></script>
    <script type='text/javascript' src="${staticPath}/js/jquery-1.7.1.min.js"></script>
    <script type='text/javascript' src="js/jquery-ui-1.8.16.custom.min.js"></script>
    <script type='text/javascript' src="${staticPath}/js/jquery.blockUI.js"></script>
    <script type='text/javascript' src="${staticPath}/js/blockui.js"></script>
    <script type="text/javascript" src="js/tree.js"></script>
    <script type='text/javascript' src="js/ketqi.js"></script>
    <script type="text/javascript" src="ueditor/editor_all.js"></script>
    <script type="text/javascript" src="ueditor/editor_config.js"></script>
    <script type="text/javascript" src="${staticPath}/js/layer/layer.min.js"></script>
    <script type="text/javascript" src="${staticPath}/js/layer/alert.js"></script>
    <script type="text/javascript">
        document.domain = "${topDomain}";
        $(function () {
            var messageType = "<s:property value="@com.sjdf.platform.dictionary.bean.common.MessageType@EMAIL"/>";
            var currentUI;
            var opts = {
                checkbox: false, expand: true,
                menu: {
                    "添加模板夹": function (ui) {
                        currentUI = ui;
                        var idx = ui.children("input").val();
                        $("#addTemplateDiv").load("admin/common/template!beforeAdd.action", {idx: idx}, function () {
                            $("#addTemplateForm").dialog({
                                autoOpen: true,
                                modal: true,
                                width : 460,
                                show: "explode",
                                hide: "explode",
                                buttons: {
                                    "确定": function () {
                                        var self = $(this);
                                        self.find('input[name="info.messageType"]').val(messageType);
                                        $.post("admin/common/template!add.action", self.serializeArray(), function (re) {
                                            if (/^[0-9]*$/.test(re)) {
                                                currentUI.addNode(re, self.find("input[name='info.name']").val(), null, function (li) {
                                                    //checked status
                                                    $(li).children("input").attr("checked", false);
                                                });
                                                self.dialog("close").remove();
                                            } else {
                                                jFail(re);
                                            }
                                        });
                                    },
                                    "取消": function () {
                                        $(this).dialog('close').remove();
                                    }
                                }
                            });
                        });
                    },

                    "修改模板夹": function (ui) {
                        currentUI = ui;
                        var idx = currentUI.children("input").val();
                        $("#editTemplateDiv").load("admin/common/template!edit.action", {idx: idx}, function (html) {
                            $("#editTemplateForm").dialog({
                                autoOpen: true,
                                modal: true,
                                width : 460,
                                show: "explode",
                                hide: "explode",
                                buttons: {
                                    "确定": function () {
                                        var self = $(this);
                                        $.post("admin/common/template!update.action", self.serializeArray(), function (re) {
                                            if (re == "success") {
                                                currentUI.editNode(null, self.find("input[name='info.name']").val());
                                                currentUI.children("input").attr("checked", false);
                                            } else {
                                                jFail(re);
                                            }
                                            self.dialog("close");
                                        });
                                    },
                                    "取消": function () {
                                        $(this).dialog("close");
                                    }
                                }
                            });
                        });
                    },

                    "删除模板夹": function (ui) {
                        if (confirm("确定删除此模板夹吗?")) {
                            currentUI = ui;
                            var idx = currentUI.children("input").val();
                            $.post("admin/common/template!delete.action", {idx: idx}, function (re) {
                                if (re == "success") {
                                    currentUI.remove();
                                } else {
                                    jFail(re);
                                }
                            });
                        }
                    }
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

            //启动tree插件
            $("#templateTree>ul:first").initTree(opts);

            $(".sendRadio").click(function () {
                if ($(this).attr("value") == "1") {
                    $(".messageTemplate").show();
                } else {
                    $(".messageTemplate").hide();
                }
            });

            var editor = new baidu.editor.ui.Editor({
                UEDITOR_HOME_URL: "${basePath}ueditor/",
                iframeCssUrl: "${basePath}ueditor/themes/default/iframe.css",
                textarea: 'email.content'
            });
            editor.render("content");

            $('#submitBtn').click(function () {
                blockUI();
                $("#hidden_content").val(editor.getContent()); //或者编辑器的值要写在这里或者上面，不能写在beforeSubmit事件里
                var addresses = $(window.frames["mainFrame"].document).find("#addressListTarget").val();
                $('#hidden_message_address').val(addresses);
                $.post('admin/common/message!saveOrUpdateEmail.action', $('#dataForm').serializeArray(), function (res) {
                    $.unblockUI();
                    if (res['bool']) {
                        jSuccess(res['message'], function () {
                            window.close();
                        });
                    } else {
                        jFail(res['message']);
                    }
                }, 'json');
            });

            $('#cancelBtn').click(function () {
                window.close();
            });

            //加载模板数据
            $("#templateTree li").live('dblclick', function (event) {
                var idx = $(this).children("input").val();
                event.stopPropagation();
                if(idx <= 0){
                    return;
                }

                $.post('admin/common/template!query.action', {idx:idx}, function (res) {
                    if(res['bool']){
                        var result = res['result'];
                        $('#email_title').val(result['title']);
                        editor.setContent(result['content']);
                    }
                }, 'json');
            });

            $('#addTemplateBtn').click(function () {
                var span = $('#templateTree span[class="selected"]');
                var text = span.html();
                var idx = span.parent().children("input").val();
                if (!idx) {
                    jFail("请选择待添加的目录");
                    return;
                }

                jConfirm("确认将模板保存到【" + text + "】下吗?", function () {
                    var title = $('#email_title').val();
                    $.post('admin/common/template!save.action', {"info.parent.id": idx, "info.messageType": messageType, "info.name": title, "info.title": title, "info.content": editor.getContent()}, function (res) {
                        if (res['bool']) {
                            span.parent().parent().addNode(res['message'], title, null, function (li) {
                                //checked status
                                $(li).children("input").attr("checked", false);
                            });
                        } else {
                            jFail(res['message']);
                        }
                    }, 'json');
                });
            });

            $('.add-btn').click(function(){
                var msg = '<div class="add-tr mb10"><input name="email.sendTimeList" class="Wdate s_text w250" onClick="WdatePicker({isShowClear:true,readOnly:true,dateFmt:\'yyyy-MM-dd HH:mm:ss\'});"> <a href="javascript:void(0);" class="del-btn">定时发送</a></div>';
                $(this).parent().parent().append(msg);
            });

            $('.del-btn').live('click',function(){
                $(this).parent().remove();
            });
        });

        function load() {
            $(window.frames["mainFrame"].document).find("#addressListTarget").val($('#hidden_message_address').val());
        }
    </script>
</head>
<body>
<div class="mc w95p mt20 pb20">
    <form id="dataForm" action="admin/common/message!saveOrUpdateEmail.action" method="post">
        <input type="hidden" name="email.id" value="${email.id}">
        <input type="hidden" id="hidden_message_address" name="email.address" value="<s:property value="email.address"/>">

        <s:if test="email != null">
            <div>
                <table class="send-tab t_bor" width="100%">
                    <tr>
                        <th width="12%">公司</th>
                        <td><s:property value="email.companyInfo"/></td>
                    </tr>
                    <tr>
                        <th>系统类型</th>
                        <td><s:property value="email.systemTypeInfo"/></td>
                    </tr>
                    <tr>
                        <th>所属用户</th>
                        <td><s:property value="email.userId"/>【<s:property value="email.nameInfo"/>】</td>
                    </tr>
                    <tr>
                        <th>邮件地址</th>
                        <td><s:property value="email.address"/></td>
                    </tr>
                    <tr>
                        <th>错误信息</th>
                        <td><s:property value="email.remark"/></td>
                    </tr>
                    <tr>
                        <th>发送状态</th>
                        <td>
                            <s:if test="email.status == @com.sjdf.platform.dictionary.bean.common.SendStatus@SEND_SUCCESS">
                                <s:property value="email.statusInfo"/>
                            </s:if>
                            <s:else>
                                <s:select name="email.status" list="sendStatusList" listKey="attr" listValue="name"/>
                            </s:else>
                        </td>
                    </tr>
                </table>
            </div>
        </s:if>

        <iframe frameborder="0" src="${tipMessageUrl}" id="mainFrame" name="mainFrame" width="100%" class="mt10" onload="javascript:load();"></iframe>
        <table width="100%" class="send-tab t_bor">
            <th width="12%">定时发送</th>
            <td width="88%">
                <div class="add-tr mb10">
                    <s:if test="email == null">
                        <input name="email.sendTimeList" class="Wdate s_text w250" onClick="WdatePicker({isShowClear:true,readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'});">
                        <a href="javascript:void(0);" class="add-btn">定时发送</a>
                    </s:if>
                    <s:else>
                        <input name="email.sendTime" value="<s:date name="email.sendTime" format="yyyy-MM-dd HH:mm:ss"/>" class="Wdate s_text w250" onClick="WdatePicker({isShowClear:true,readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'});">
                    </s:else>
                </div>
            </td>
        </table>

        <p class="sendTerm sendTit mt10 d_bor">
            <label><input type="radio" checked="checked" class="sendRadio" name="sendRadio" value="1"/>从模板进行选择</label>
            <label><input type="radio" name="sendRadio" class="sendRadio" value="0"/>手动输入</label>
        </p>

        <div class="sendTemplet clearfix w">
            <div id="templateTree" class="fl sendTree messageTemplate">
                <ul>
                    <my:tree nodeList="templateList"/>
                </ul>
            </div>
            <div class="fl sendTreeCon">
                <p>邮件标题</p>
                <input type="text" class="s_text" id="email_title" style="width: 97%" name="email.title" value="<s:property value="email.title"/>"/>

                <p>邮件内容</p>
                <input type="hidden" id="hidden_content" name="email.content"/>
                <script type="text/plain" id="content" style="width:98%;">${email.content}</script>

                <div class="tc mt10 messageTemplate"><a href="javascript:void(0);" id="addTemplateBtn" class="sea-btn">保存</a></div>
            </div>
        </div>
        <div class="tc mt20">
            <s:select cssClass="gy_select" list="userList" name="email.userId" listKey="userId" listValue="nameInfo" headerKey="" headerValue="===请选择==="/>
            <a href="javascript:void(0);" id="submitBtn" class="send-btn ml10">确认发送</a>
            <a href="javascript:void(0);" id="cancelBtn" class="send-btn ml10">取消</a>
        </div>
    </form>
</div>
<div id="addTemplateDiv" title="添加模板夹"></div>
<div id="editTemplateDiv" title="修改模板夹"></div>
</body>
</html>