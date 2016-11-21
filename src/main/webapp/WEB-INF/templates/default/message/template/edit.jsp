<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<form id="editTemplateForm" title="修改模板">
	<label>模板名称：</label><input style="width: 350px;" type="text" name="info.name" value="<s:property value="info.name"/>"><br>
	<input type="hidden" name="info.id" value="<s:property value="info.id"/>">
</form>
