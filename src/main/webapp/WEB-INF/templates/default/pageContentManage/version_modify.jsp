<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="my" uri="/sjdf-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<base href="${basePath}" />
<link rel="stylesheet" type="text/css" href="css/admin_css.css"></link>
<link rel="stylesheet" type="text/css" href="css/style_zc.css"></link>
<script type='text/javascript' src='js/jquery-1.6.4.min.js'></script>
<title>page_content_modify_version</title>
<script type="text/javascript">
	function returnIndex() {
		window.location.href = "admin/common/PageContentManageAction!list4Version.action";
	}
</script>
</head>
<body>
	<div style="width: 100%; margin: 0 auto;">
		<div class="kzmb_table_3">
			<form action="admin/common/PageContentManageAction!modifyVersion.action" method="post">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr class="kzmb_nm_3">
						<td>版本序号</td>
						<td><s:property value="versionManageBean.id" /></td>
						<td>启用标示</td>
						<td><s:property value="versionManageBean.readOnlyInfo" /></td>
					</tr>
					<tr class="kzmb_nm_3">
						<td>只读</td>
						<td><s:property value="versionManageBean.enableMarkupInfo" /></td>
						<td>系统类别</td>
						<td><s:property value="versionManageBean.systemTypeInfo" /></td>
					</tr>
					<tr class="kzmb_nm_3">
						<td>版本名称</td>
						<td>
							<s:if test="versionManageBean.readOnly == @com.sjdf.platform.dictionary.bean.WhetherState@YES">
								<s:property value="versionManageBean.versionName" />
							</s:if>
							<s:else>
								<s:textfield name="versionManageBean.versionName"></s:textfield>
							</s:else>
						</td>
						<td>版本说明</td>
						<td>
							<s:if test="versionManageBean.readOnly == @com.sjdf.platform.dictionary.bean.WhetherState@YES">
								<s:textarea readonly="true" name="versionManageBean.versionDescription"></s:textarea>
							</s:if>
							<s:else>
								<s:textarea name="versionManageBean.versionDescription"></s:textarea>
							</s:else>
						</td>
					</tr>
					<tr class="kzmb_nm_3">
						<td>推送地址</td>
						<td>
							<s:textarea name="versionManageBean.pushUrl"></s:textarea>
						</td>
						<td>预览首页地址</td>
						<td>
							<s:textarea name="versionManageBean.previewUrl"></s:textarea>
						</td>
					</tr>
					
					<tr class="kzmb_nm_3">
						<td colspan="4">
							<s:hidden name="versionManageBean.id"></s:hidden>
							<s:submit value="修改"></s:submit>
							<input onclick="returnIndex();" type="button" value="返回" />
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>