<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="my" uri="/sjdf-tags" %>
<form id="dataForm" method="post">
    <input type="hidden" id="configId" name="config.id" value="${config.id}">
    <table width="100%">
        <tr>
            <th align="right">名称:</th>
            <td><input type="text" name="config.name" value="<s:property value="config.name"/>"/></td>
        </tr>
        <tr>
            <th align="right">消息类型:</th>
            <td><s:select id="messageTypeSelect" cssStyle="width: 182px;" name="config.messageType" list="messageTypeList" listKey="attr" listValue="name" headerKey="0" headerValue="===请选择==="/></td>
        </tr>
        <tr>
            <th align="right">有效状态:</th>
            <td><s:select cssStyle="width: 182px;" name="config.valid" list="validMarkList" listKey="attr" listValue="name"/></td>
        </tr>
        <tr>
            <th align="right">发送接口:</th>
            <td><s:select id="sendApiImplSelect" cssStyle="width: 182px;" name="config.sendApiImpl" list="messageApiImplMap" listKey="key" listValue="value" headerKey="" headerValue="===请选择==="/></td>
        </tr>
        <tr>
            <th align="right">用户名:</th>
            <td><input type="text" name="config.userName" value="<s:property value="config.userName"/>" style="width: 240px"/></td>
        </tr>
        <tr>
            <th align="right">密码:</th>
            <td><input type="text" name="config.pwd" value="<s:property value="config.pwd"/>" style="width: 240px"/></td>
        </tr>
        <tr>
            <th align="right">主机地址:</th>
            <td><input type="text" name="config.hostUrl" value="<s:property value="config.hostUrl"/>" style="width: 240px"/></td>
        </tr>
        <tr>
            <th align="right">源地址:</th>
            <td><input type="text" name="config.sourceAddress" value="<s:property value="config.sourceAddress"/>" style="width: 240px"/></td>
        </tr>
        <tr>
            <th align="right">个人名称:</th>
            <td><input type="text" name="config.personalName" value="<s:property value="config.personalName"/>" style="width: 240px"/></td>
        </tr>
    </table>
</form>