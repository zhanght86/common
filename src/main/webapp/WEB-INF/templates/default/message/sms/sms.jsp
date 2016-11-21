<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="my" uri="/sjdf-tags" %>
<!DOCTYPE html>
<html>
<head>
    <base href="${basePath}"/>
    <title>短信管理</title>
    <meta charset="UTF-8">
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
    <script type="text/javascript" src="${staticPath}/js/layer/layer.min.js"></script>
    <script type="text/javascript" src="${staticPath}/js/layer/alert.js"></script>
    <script type="text/javascript" src="${staticPath}/js/ajaxfileupload.js"></script>
    <script type="text/javascript">
        document.domain = "${topDomain}";
        function load() {
            $(window.frames["mainFrame"].document).find("#addressListTarget").val("${sms.address}");
        }

        var isLongSms = false;
        //短信计数
        function smsTip(tt, isOnlyCounter) {
            var size = tt.val().length;
            $("#smsTip").html("当前已输入<span class='red'>" + size + "</span>个字符");
            if (isOnlyCounter) {
                return;
            }

            if (isLongSms && size > 128) {
                $("<div>已经超过短信发送字符限制（128个字符），请确认和修改。</div>").dialog({
                    autoOpen: true,
                    modal: true,
                    width: 460,
                    show: "explode",
                    hide: "explode",
                    buttons: {
                        "返回修改": function () {
                            $(this).dialog('close').remove();
                        }
                    }
                });
                return false;
            } else if (!isLongSms && size > 64) {
                $("<div>已经超过单条短信限制（64个字符），请确认是否要发送长短信？</div>").dialog({
                    autoOpen: true,
                    modal: true,
                    width: 460,
                    show: "explode",
                    hide: "explode",
                    buttons: {
                        "确认使用长短信": function () {
                            isLongSms = true;
                            $(this).dialog('close').remove();
                        },
                        "返回修改": function () {
                            $(this).dialog('close').remove();
                        }
                    }
                });
                return false;
            }
            return true;
        }

        $(function () {
            <my:permission code="06011345" description="短信添加或更新">
            $('#submitBtn').click(function () {
                //excel方式发送短信
                if($('.sendRadio:checked').val() == "2"){
                    var params = '{';
                    var index = 0;
                    $('input[name="sms.sendTimeList"]').each(function(){
                        params += '"sms.sendTimeList[' + index + ']":"' + $(this).val() + '",';
                        index = index + 1;
                    });
                    params += '"sms.userId":"'+$('#sms_userId :selected').val() + '"}';

                    $.ajaxFileUpload({
                        url: 'admin/common/message!saveOrUpdateSms.action',
                        secureuri: false,
                        fileElementId: 'xls_file',
                        dataType: "json",
                        data: eval('(' + params + ')'),
                        type: 'post',
                        success: function (data, status) {
                            if(data) {
                                if (data['bool']) {
                                    jSuccess("操作成功", function () {
                                        window.close();
                                    });
                                } else {
                                    jFail(data['message']);
                                }
                            }
                        },
                        error: function (da, status, e) {
                            jSuccess("操作成功", function () {
                                window.close();
                            });
                        }
                    });

                    return;
                }

                //常用方式发送短信
                if (!smsTip($("#sms_content"), false)) {
                    return;
                }

                var addresses = $(window.frames["mainFrame"].document).find("#addressListTarget").val();
                $('#hidden_message_address').val(addresses);
                blockUI();
                $.post('admin/common/message!saveOrUpdateSms.action', $('#dataForm').serializeArray(), function (res) {
                    $.unblockUI();
                    if (res['bool']) {
                        jSuccess("操作成功", function () {
                            window.close();
                        });
                    } else {
                        jFail(res['message']);
                    }
                }, 'json');
            });
            </my:permission>

            var messageType = "<s:property value="@com.sjdf.platform.dictionary.bean.common.MessageType@SMS"/>";
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

            var $sms_excel_template_div = $('#sms_excel_template_div').hide();
            var $sms_content_div = $('#sms_content_div');
            $(".sendRadio").click(function () {
                var value = $(this).val();
                if (value == "2") {
                    $sms_excel_template_div.show();
                    $sms_content_div.hide();
                } else {
                    $sms_content_div.show();
                    $sms_excel_template_div.hide();
                    if (value == "1") {
                        $(".messageTemplate").show();
                    } else if (value == "0") {
                        $(".messageTemplate").hide();
                    }
                }
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
                        $('#sms_content').val(result['content']);
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
                    $.post('admin/common/template!save.action', {"info.parent.id": idx,"info.messageType": messageType, "info.name": title, "info.title": title, "info.content": $('#sms_content').val()}, function (res) {
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

            $('#cancelBtn').click(function () {
                window.close();
            });

            var sms = $("#sms_content").live('blur', function () {
                smsTip($(this), false);
            }).live("keyup", function () {
                smsTip($(this), true);
            });
            smsTip(sms);

            $('.add-btn').click(function () {
                var msg = '<div class="add-tr mb10"><input name="sms.sendTimeList" class="Wdate s_text w250" onClick="WdatePicker({isShowClear:true,readOnly:true,dateFmt:\'yyyy-MM-dd HH:mm:ss\'});"> <a href="javascript:void(0);" class="del-btn">定时发送</a></div>';
                $(this).parent().parent().append(msg);
            });

            $('.del-btn').live('click', function () {
                $(this).parent().remove();
            });
        });
    </script>
</head>
<body>
<div class="mc w95p mt20 pb20">
    <form id="dataForm" method="post" enctype="multipart/form-data">
        <input type="hidden" name="sms.id" value="${sms.id}">
        <input type="hidden" id="hidden_message_address" name="sms.address">

        <s:if test="sms != null">
            <div>
                <table class="send-tab t_bor" width="100%">
                    <tr>
                        <th width="12%">公司</th>
                        <td><s:property value="sms.companyInfo"/></td>
                    </tr>
                    <tr>
                        <th>系统类型</th>
                        <td><s:property value="sms.systemTypeInfo"/></td>
                    </tr>
                    <tr>
                        <th>所属用户</th>
                        <td><s:property value="sms.userId"/>【<s:property value="sms.nameInfo"/>】</td>
                    </tr>
                    <tr>
                        <th>手机号码</th>
                        <td><s:property value="sms.address"/></td>
                    </tr>
                    <tr>
                        <th>错误信息</th>
                        <td><s:property value="sms.remark"/></td>
                    </tr>
                    <tr>
                        <th>发送状态</th>
                        <td>
                            <s:if test="sms.status == @com.sjdf.platform.dictionary.bean.common.SendStatus@SEND_SUCCESS">
                                <s:property value="sms.statusInfo"/>
                            </s:if>
                            <s:else>
                                <s:select cssStyle="width: 173px;" name="sms.status" list="sendStatusList" listKey="attr" listValue="name"/>
                            </s:else>
                        </td>
                    </tr>
                </table>
            </div>
        </s:if>

        <iframe frameborder="0" src="${tipMessageUrl}" id="mainFrame" name="mainFrame" width="100%" onload="javascript:load();"></iframe>
        <table width="100%" class="send-tab t_bor">
            <th width="12%">定时发送</th>
            <td width="88%">
                <div class="add-tr mb10">
                    <s:if test="sms == null">
                        <input name="sms.sendTimeList" class="Wdate s_text w250" onClick="WdatePicker({isShowClear:true,readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'});">
                        <a href="javascript:void(0);" class="add-btn">定时发送</a>
                    </s:if>
                    <s:else>
                        <input name="sms.sendTime" value="<s:date name="sms.sendTime" format="yyyy-MM-dd HH:mm:ss"/>" class="Wdate s_text w250" onClick="WdatePicker({isShowClear:true,readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'});">
                    </s:else>
                </div>
            </td>
        </table>

        <p class="sendTerm sendTit mt10 d_bor">
            <label><input type="radio" checked="checked" class="sendRadio" name="sendRadio" value="1"/>从模板进行选择</label>
            <label><input type="radio" name="sendRadio" class="sendRadio" value="0"/>手动输入</label>
            <label><input type="radio" name="sendRadio" class="sendRadio" value="2"/>Excel导入</label>
        </p>

        <div id="sms_content_div" class="sendTemplet clearfix w">
            <div id="templateTree" class="fl sendTree messageTemplate">
                <ul>
                    <my:tree nodeList="templateList"/>
                </ul>
            </div>
            <div class="fl sendTreeCon">
                <div class="messageTemplate">
                    <p>标题</p>
                    <input type="text" class="s_text" id="email_title" style="width: 97%"/>
                </div>

                <p>内容</p>
                <textarea id="sms_content" name="sms.content" class="fl" cols="120" rows="10"><s:property value="sms.content"/></textarea>
                <div class="fl ml10 mt10">
                    <p>单条短信字符限制：64个字符</p>

                    <p>多条短信字符限制：128个字符</p>

                    <p id="smsTip"></p>
                </div>
                <div class="clear"></div>
                <div class="tc mt10 messageTemplate"><a href="javascript:void(0);" id="addTemplateBtn" class="sea-btn">保存</a></div>
            </div>
        </div>
        <div id="sms_excel_template_div" class="sendTemplet clearfix w p10">
            <a href="${staticPath}/template/sms_send_template.xls" class="sea-btn ml10">Excel模板下载</a>
            <input type="file" id="xls_file" name="xls">
        </div>

        <div class="tc mt20">
            <s:select cssClass="gy_select" list="userList" name="sms.userId" listKey="userId" listValue="nameInfo" headerKey="" headerValue="===请选择==="/>
            <my:permission code="06011345" description="短信添加或更新">
                <a href="javascript:void(0);" id="submitBtn" class="send-btn ml10">确认发送</a>
            </my:permission>
            <a href="javascript:void(0);" id="cancelBtn" class="send-btn ml10">取消</a>
        </div>
    </form>
</div>
<div id="addTemplateDiv" title="添加模板夹"></div>
<div id="editTemplateDiv" title="修改模板夹"></div>
</body>
</html>