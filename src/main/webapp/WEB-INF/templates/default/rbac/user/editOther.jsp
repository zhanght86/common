<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<label  class="input">用户名：</label><input type="text" name="user.username" maxlength="50" disabled="disabled" value="<s:property value="user.username"/>"><br>
<label class="input">真实姓名：</label><input type="text" name="user.name" maxlength="50" value="<s:property value="user.name"/>"><br>
<input type="hidden" name="user.id" value="<s:property value="user.id"/>">
