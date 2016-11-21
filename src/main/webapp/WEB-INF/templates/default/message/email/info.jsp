<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="my" uri="/sjdf-tags" %>
<div id="contentDiv">
    <table width="100%" class="info_table" border="0" cellspacing="0" cellpadding="0">
        <tr>
            <th width="80px" align="right">合作伙伴:</th>
            <td><s:property value="email.companyInfo"/></td>
        </tr>
        <tr>
            <th align="right">系统类型:</th>
            <td><s:property value="email.systemTypeInfo"/></td>
        </tr>
        <tr>
            <th align="right">所属用户:</th>
            <td><s:property value="email.userId"/>【<s:property value="email.nameInfo"/>】</td>
        </tr>
        <tr>
            <th align="right">发送状态:</th>
            <td><s:property value="email.statusInfo"/>【<s:date name="email.sendTime" format="yyyy-MM-dd HH:mm:ss"/>】</td>
        </tr>
        <tr>
            <th align="right">邮件地址:</th>
            <td><s:property value="email.address"/></td>
        </tr>
        <tr>
            <th align="right">标题:</th>
            <td><s:property value="email.title"/></td>
        </tr>
        <tr>
            <th align="right">邮件内容:</th>
            <td>${email.content}</td>
        </tr>
        <tr>
            <th align="right">错误信息:</th>
            <td><s:property value="email.remark"/></td>
        </tr>
    </table>
</div>