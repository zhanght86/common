<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="my" uri="/sjdf-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <base href="${basePath}"/>
        <title>微信管理</title>
        <meta charset="UTF-8">
        <link rel="stylesheet" type="text/css" href="ueditor/themes/default/ueditor.css"/>
        <link rel="stylesheet" type="text/css" href="css/jquery-ui-1.8.16.custom.css"/>
        <link rel="stylesheet" type="text/css" href="css/reset.css"/>
        <link rel="stylesheet" type="text/css" href="css/style.css"/>
        <link rel="stylesheet" type="text/css" href="css/tools_css.css"/>
        <link rel="stylesheet" type="text/css" href="css/smsMail.css"/>
        <script type='text/javascript' src="${staticPath}/js/My97DatePicker/WdatePicker.js"></script>
        <script type='text/javascript' src="${staticPath}/js/jquery-1.7.1.min.js"></script>
        <script type='text/javascript' src="${staticPath}/js/jquery-ui-1.8.16.custom.min.js"></script>
        <script type='text/javascript' src="${staticPath}/js/jquery.blockUI.js"></script>
        <script type='text/javascript' src="${staticPath}/js/blockui.js"></script>
        <script type='text/javascript' src="js/ketqi.js"></script>
        <script type="text/javascript" src="ueditor/editor_all.js"></script>
        <script type="text/javascript" src="ueditor/editor_config.js"></script>
        <script type="text/javascript" src="${staticPath}/js/layer/layer.min.js"></script>
        <script type="text/javascript" src="${staticPath}/js/layer/alert.js"></script>
        <script type="text/javascript">
            //同主域，不同子域跨域，设置相同的document.domian就可以解决
            document.domain = "${topDomain}";
            $(function () {
                $("input[name='wechat.isToAll']").change(function(){
                    $(window.frames["mainFrame"].document).find("#addressListTarget").attr("disabled",$(this).prop('checked'));
                });
                $(".sendRadio").click(function () {
                    if ($(this).attr("value") == "text") {
                        $("#textMessage").show();
                        $("#newsMessage").hide();
                    } else {
                        $("#textMessage").hide();
                        $("#newsMessage").show();
                    }
                });
                $('#submitBtn').click(function () {
                    var massType = $(".sendRadio:checked").val();
                    if ("text" == massType) {
                        if (!$("#wechat_content").val() || $("#wechat_content").val() == '') {
                            jFail("请输入要群发的文本内容");
                            return;
                        }
                    } else {
                        $("input[name='wechat.title']").val($("select[name='wechat.mediaId'] option:selected").text());
                        if (!$("#wchat_mediaId").val() || $("#wchat_mediaId").val() == '') {
                            jFail("请选择要群发的图文消息");
                            return;
                        }
                    }
                    var addresses = $(window.frames["mainFrame"].document).find("#addressListTarget").val();
                    $('#hidden_message_address').val(addresses);
                    blockUI();
                    $.post('admin/common/message!saveOrUpdateWechat.action', $('#dataForm').serializeArray(), function (res) {
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
            });
    
            function load() {
                $(window.frames["mainFrame"].document).find("#addressListTarget").val($('#hidden_message_address').val()).attr("disabled",true);
            }
        </script>
    </head>
    <body>
        <div class="mc w95p mt20 pb20">
            <form id="dataForm" action="admin/common/message!saveOrUpdateWechat.action" method="post">
                <input type="hidden" name="wechat.id" value="${wechat.id}">
                <input type="hidden" id="hidden_message_address" name="wechat.address" value="<s:property value="wechat.address"/>">
                <s:if test="wechat != null">
                    <div>
                        <table class="send-tab t_bor" width="100%">
                            <tr>
                                <th width="12%">公司</th>
                                <td><s:property value="wechat.companyInfo"/></td>
                            </tr>
                            <tr>
                                <th>系统类型</th>
                                <td><s:property value="wechat.systemTypeInfo"/></td>
                            </tr>
                            <tr>
                                <th>微信OpenId</th>
                                <td><s:property value="wechat.address"/></td>
                            </tr>
                            <tr>
                                <th>错误信息</th>
                                <td><s:property value="wechat.remark"/></td>
                            </tr>
                            <tr>
                                <th>发送状态</th>
                                <td>
                                    <s:if test="wechat.status == @com.sjdf.platform.dictionary.bean.common.SendStatus@SEND_SUCCESS">
                                        <s:property value="wechat.statusInfo"/>
                                    </s:if>
                                    <s:else>
                                        <s:select name="wechat.status" list="sendStatusList" listKey="attr" listValue="name"/>
                                    </s:else>
                                </td>
                            </tr>
                        </table>
                    </div>
                </s:if>
                <!-- iframe加载common平台的地址抽取页面 -->
                <iframe frameborder="0" src="${tipMessageUrl}" id="mainFrame" name="mainFrame" width="100%" class="mt10" onload="javascript:load();"></iframe>
                <p class="sendTerm sendTit mt10 d_bor">
                    <label><input type="checkbox" name="wechat.isToAll" value="true" checked="checked"/>群发所有用户</label>
                    <label><input type="radio" name="wechat.massType" class="sendRadio" value="<s:property value='@com.sjdf.platform.message.constant.MassMessageType@NEWS'/>" checked="checked"/>群发图文消息</label>
                    <label><input type="radio" name="wechat.massType" class="sendRadio" value="<s:property value='@com.sjdf.platform.message.constant.MassMessageType@TEXT'/>"/>群发文本消息</label>
                </p>
                <div class="sendTemplet clearfix w">
                    <div class="fl sendTreeCon" id="newsMessage">
                        <s:hidden name="wechat.title"/>
                        <s:select id="wchat_mediaId" list="materialList" name="wechat.mediaId" listKey="mediaId" listValue="title" headerKey="" headerValue="---请选择要发送的图文---"/>
                    </div>
                    <div class="fl sendTreeCon" id="textMessage" style="display:none;">
                        <p>文本内容</p>
                        <textarea id="wechat_content" name="wechat.content" class="fl" cols="120" rows="10"><s:property value="wechat.content"/></textarea>
                    </div>
                </div>
                <div class="tc mt20">
                    <a href="javascript:void(0);" id="submitBtn" class="send-btn ml10">确认发送</a>
                    <a href="javascript:void(0);" id="cancelBtn" class="send-btn ml10">取消</a>
                </div>
            </form>
        </div>
    </body>
</html>