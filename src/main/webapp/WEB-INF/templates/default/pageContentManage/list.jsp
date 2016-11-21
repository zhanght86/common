<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="my" uri="/sjdf-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<base href="${basePath}"/>
<link rel="stylesheet" type="text/css" href="css/admin_css.css"></link>
<link rel="stylesheet" type="text/css" href="css/style_zc.css"></link>
<script type='text/javascript' src='js/jquery-1.6.4.min.js'></script>
<title>page_content_list</title>
	<script type="text/javascript">
	</script>
<style>
.td-width {width:200px;overflow:hidden;　word-break:break-all; word-wrap:break-word;}
</style>
</head>
<body>
	<div style="width: 100%; margin: 0 auto;">
		<div>
			<form action="admin/common/PageContentManageAction!sortList.action" method="post">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr class="kzmb_nm_3">
					<td colspan="5" style="text-align: left;">
						<s:hidden name="contentManageBean.id"></s:hidden>
						<s:hidden name="contentManageBean.versionId"></s:hidden>
						<s:hidden name="contentManageBean.parentId"></s:hidden>
						<s:submit value="保存排列排序"></s:submit>
					</td>
				</tr>
				<tr class="kzmb_nm_3" style="width:100%">
					<td><a name="id" class="sort">ID<img  alt="点击排序" src="images/down_sort.gif" border="0"/></a></td>
					<td style="overflow:hidden;max-width:200px;"><a name="menuName" class="sort">菜单名称<img  alt="点击排序" src="images/down_sort.gif" border="0"/></a></td>
					<td><a name="whetherDisplay" class="sort">显示<img  alt="点击排序" src="images/down_sort.gif" border="0"/></a></td>
					<td><a name="displaySort" class="sort">排列顺序<img  alt="点击排序" src="images/down_sort.gif" border="0"/></a></td>
					<td>操作</td>
				</tr>
				<s:iterator value="contentList" status="stat">
					<tr class="kzmb_nm_3">
						<td><s:property value="id" /></td>
						<td style="overflow:hidden;max-width:200px;"><s:property value="menuName" /></td>
						<td>
							<s:if test="whetherDisplay == @com.sjdf.platform.dictionary.bean.WhetherState@YES">
								是
							</s:if>
							<s:else>
								否
							</s:else>
						</td>
						<td>
							<s:textfield size="4" name="contentList[%{#stat.index}].displaySort"></s:textfield>
							<s:hidden name="contentList[%{#stat.index}].id" ></s:hidden>
						</td>
						<td>
							<a href="admin/common/PageContentManageAction!nodeInfo.action?databaseId=${id}">详细</a>
							<!-- <input type="button" value="删除" /> -->
						</td>
					</tr>
				</s:iterator>
			</table>
			</form>
			<my:page />
		</div>
	</div>
</body>
</html>