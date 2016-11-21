<%@page import="com.sjdf.platform.common.utils.DateUtils" %>
<%@page import="com.sjdf.platform.common.utils.MD5" %>
<%@page import="com.sjdf.platform.dictionary.bean.RemoteAccess" %>
<%@page import="com.sjdf.platform.dictionary.bean.SystemType" %>
<%@page import="com.sjdf.platform.dictionary.cache.ConfigManager" %>
<%@page import="java.util.Date" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <base href="${basePath}"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>首页</title>
    <link rel="stylesheet" type="text/css" href="css/jquery-ui-1.8.16.custom.css"/>
    <script type='text/javascript' src="js/jquery-1.6.4.min.js"></script>
    <script type='text/javascript' src="js/jquery-ui-1.8.16.custom.min.js"></script>
    <script type="text/javascript">
        function monitor() {
            $.get('admin/common/message!monitor.action', {}, function (res) {
                if (res['bool']) {
                    var body = '<tr><th>userId</th><th>buffer size</th><th>cursor</th><th>remaining capacity</th></tr>';
                    var result = res['result'];
                    if (result) {
                        for (var i = 0; i < result.length; i++) {
                            body += "<tr>";
                            body += "<td>" + result[i]['userId'] + "</td>";
                            body += "<td>" + result[i]['bufferSize'] + "</td>";
                            body += "<td>" + result[i]['cursor'] + "</td>";
                            body += "<td>" + result[i]['remainingCapacity'] + "</td>";
                            body += "</tr>";
                        }
                    }
                    $('#message_monitor').empty().html(body);
                }
            }, 'json');
        }

        $(function () {
            setInterval("monitor()", 1000);//1000为1秒钟
        });
    </script>
    <%
        String vertime = DateUtils.formatDateTimestamp(new Date());
        String connpwd = ConfigManager.getInstance().getValue(RemoteAccess.class, RemoteAccess.CONNECTION_PASSWORD);
        String name = "ketqi";
        String vercode = MD5.md5(connpwd + vertime);
        String checkSum = MD5.md5(connpwd + "18459461" + vertime);
        String messageTemplate = MD5.md5(connpwd + "18459461" + name + vertime);
    %>
</head>
<body>
<a title="<%=connpwd%>" target="_blank" href="/api/common/ExpressAction!print.action?type=yto&vertime=<%=vertime%>&vercode=<%=vercode%>">圆通</a>
<a target="_blank" href="/api/common/ExpressAction!print.action?type=ems&vertime=<%=vertime%>&vercode=<%=vercode%>">EMS</a>
<a target="_blank" href="/api/common/ExpressAction!print.action?type=sf&vertime=<%=vertime%>&vercode=<%=vercode%>">顺丰</a>
<a target="_blank" href="/api/common/ExpressAction!print.action?type=ordinary&vertime=<%=vertime%>&vercode=<%=vercode%>">普通平信</a>
<br/>
<br/>
<a target="_blank" href="/admin/common/PrintAction!list.action">打印地址管理</a>
<a target="_blank" href="/admin/common/PrintAction!indirectPrint.action">快递单号打印</a>
<br/>
<br/>
<a target="_blank" href="/admin/common/DictionaryAction!list.action">配置库</a>
<a target="_blank" href="/admin/common/DictionaryAction!refresh.action">刷新缓存</a>
<a target="_blank" href="/api/common/DictionaryAction!initClient.action">配置库接口</a>
<br/>
<br/>
<a target="_blank" href="api/common/TemplateAction!get.action">模板库</a>
<br/>
<br/>
<a target="_blank" href="/admin/common/MessageTemplateAction!list.action">邮件或短信模板</a>
<br/>
<br/>
<a target="_blank" href="/admin/common/LogAction!log.action">日志</a>&nbsp;&nbsp;&nbsp;&nbsp;
<a target="_blank" href="/admin/common/LogAction!search.action">日志搜索</a>
<br/>
<br/>
<a target="_blank" href="/admin/common/LocationAction!list.action?location.province.code=0">地理位置信息</a>
<br/>
<br/>
<a target="_blank" href="/admin/common/NotifyTrackAction!list.action">通知跟踪管理</a>
<br/>
<br/>
<a target="_blank" href="/admin/common/DisplayInfoAction!displayInfoList.action?user=123456">展示信息管理</a>
<br/>
<br/>
<a target="_blank" href="/sendmsgmanagementindex.jsp">发送信息管理</a>
<br/>
<br/>
<a target="_blank" href="/admin/common/AttachmentAction!showAttachmentList.action?user=123456">附件库管理</a>
<br/>
<br/>
<a target="_blank" href="/admin/common/PageContentManageAction!list4Version.action">页面内容管理</a>
<br/>
<br/>
<a target="_blank" title="<%=connpwd%>" href="http://client.51web.com/api/AuthenticationValidAction!api.action?timestamp=<%=vertime%>&checkSum=<%=vercode%>&useName=18459461&password=<%=MD5.md5("wangzhengwei")%>">主站验证接口测试</a>
<br/>
<a target="_blank" href="http://beian.51web.com/ERS/apiAction!getAuthentication.action?timeStamp=<%=vertime%>&checkSum=<%=vercode%>&useName=frank_passion&password=frankliu0203">备案验证接口测试</a>
<br/>
<a target="_blank" href="/user/common/AssociateAction!index.action?associate.currentSystemType=<%=SystemType.EISS%>&associate.currentUser=18459461&vertime=<%=vertime%>&vercode=<%=checkSum%>" id="associate" target="_blank">主站关联备案</a>
<br/>
<br/>
<a target="_blank" href="/admin/common/platform/PermissionAction!list.action">权限信息管理</a>
<a target="_blank" href="/admin/common/platform/PermissionNodeAction!list.action">权限树管理</a>
<a target="_blank" href="/admin/common/platform/RoleAction!list.action">角色树管理</a>
<a target="_blank" href="/admin/common/platform/UserAction!list.action">用户树管理</a>
<a target="_blank" href="/login.jsp">用户登录</a>
<a target="_blank" href="/other/common/platform/LoginAction!logout.action">退出登陆</a>
<br/>
<br/>
<a target="_blank" href="/admin/common/AutoTaskAction!index.action">所有自动任务类别列表</a><br/>
<br/>
<br/>
<a target="_blank" href="/admin/common/config!list.action">接口配置列表</a><br/>
<a target="_blank" href="/admin/common/apiAuth!list.action">信息接口授权管理</a><br/>
<a target="_blank" href="/admin/common/message!sms.action">短信</a><br/>
<a target="_blank" href="/admin/common/message!email.action">邮件</a><br/>
<table id="message_monitor" border="1" title="邮件短信发送队列监控"></table>
<br/>
</body>
</html>