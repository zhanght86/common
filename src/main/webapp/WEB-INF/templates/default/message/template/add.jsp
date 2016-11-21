<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<form id="addTemplateForm" title="添加模板">
	<label>模板名称:</label><input style="width: 350px;" type="text" name="info.name" class="required"><br/>
	<input type="hidden" name="info.parent.id" value="<s:property value="info.id"/>">
	<input type="hidden" name="info.messageType" value="<s:property value="info.messageType"/>">
</form>
