<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>page_content_copy_version</title>
<style type="text/css">
table tr td span {
	color: #ff6701;
}
table tr th{text-align: right; font-weight: normal; padding-right:2px;}
</style>
</head>
<body>
	<form id="postForm" action="admin/common/PageContentManageAction!copyVersion.action" method="post">
		<table>
			<tr>
				<th>版本名称:</th>
				<td>
					<s:textfield name="versionManageBean.versionName"></s:textfield>
					<s:hidden name="versionId"></s:hidden>
				</td>
			</tr>
			<tr>
				<th>版本说明</th>
				<td>
					<s:textarea name="versionManageBean.versionDescription"></s:textarea>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>