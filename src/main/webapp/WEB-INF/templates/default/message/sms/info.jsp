<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="my" uri="/sjdf-tags" %>
<div id="contentDiv">
    <table width="100%" class="info_table" border="0" cellspacing="0" cellpadding="0">
        <tr>
            <th width="80px" align="right">公司:</th>
            <td><s:property value="sms.companyInfo"/></td>
        </tr>
        <tr>
            <th align="right">系统类型:</th>
            <td><s:property value="sms.systemTypeInfo"/></td>
        </tr>
        <tr>
            <th align="right">所属用户:</th>
            <td><s:property value="sms.userId"/>【<s:property value="sms.nameInfo"/>】</td>
        </tr>
        <tr>
            <th align="right">发送状态:</th>
            <td>
                <s:property value="sms.statusInfo"/>【<s:date name="sms.sendTime" format="yyyy-MM-dd HH:mm:ss"/>】
            </td>
        </tr>
        <tr>
            <th align="right">手机号码:</th>
            <td><s:property value="sms.address"/></td>
        </tr>
        <tr>
            <th align="right">短息内容:</th>
            <td><s:property value="sms.content"/></td>
        </tr>
        <tr>
            <th align="right">错误信息:</th>
            <td><s:property value="sms.remark"/></td>
        </tr>
    </table>
</div>