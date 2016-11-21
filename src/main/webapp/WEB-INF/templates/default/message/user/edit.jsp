<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="my" uri="/sjdf-tags" %>
<form id="dataForm" method="post">
    <input type="hidden" id="configId" name="user.id" value="${user.id}">
    <table width="100%">
        <tr>
            <th width="60" align="right">昵称:</th>
            <td><input type="text" name="user.name" value="<s:property value="user.name"/>"/></td>
        </tr>
        <tr>
            <th align="right">授权名称:</th>
            <td><input type="text" name="user.userId" value="<s:property value="user.userId"/>"/></td>
        </tr>
        <tr>
            <th align="right">授权密码:</th>
            <td><input type="text" name="user.connPwd" value="<s:property value="user.connPwd"/>"/></td>
        </tr>
        <tr>
            <th align="right">合作伙伴:</th>
            <td><s:select cssStyle="width: 182px;" name="user.company" list="companyClassList" listKey="attr" listValue="name" headerKey="0" headerValue="===请选择==="/></td>
        </tr>
        <tr>
            <th align="right">系统类型:</th>
            <td><s:select cssStyle="width: 182px;" name="user.systemType" list="systemTypeList" listKey="attr" listValue="name" headerKey="0" headerValue="===请选择==="/></td>
        </tr>
        <tr>
            <th align="right">有效状态:</th>
            <td><s:select cssStyle="width: 182px;" name="user.valid" list="validMarkList" listKey="attr" listValue="name"/></td>
        </tr>
        <tr>
            <th align="right">备注:</th>
            <td><input type="text" name="user.remark" style="width: 250px;" value="<s:property value="user.remark"/>"/></td>
        </tr>
        <tr>
            <th align="right">短信接口:</th>
            <td><s:checkboxlist name="user.smsApiList" list="smsConfigList" listKey="id" listValue="name"/>
        </tr>
        <tr>
            <th align="right">邮件接口:</th>
            <td><s:checkboxlist template="linecheckboxlist.ftl" name="user.emailApiList" list="emailConfigList" listKey="id" listValue="name"/></td>
        </tr>
    </table>
</form>