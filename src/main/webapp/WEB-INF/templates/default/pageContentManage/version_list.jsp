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
<title>page_content_version_list</title>
	<script type="text/javascript">
	// 预览首页
	function previewIndex(id) {
		$.post("admin/common/PageContentManageAction!pushPreview.action",{versionId : id},function(re) {
			var result = re.split("❤");
			if ("success" == result[0]) {
				var open = window.open(result[1] + "?officialPageContnet=2");
				if (open.document.readyState=="complete") {
					// 清除预览缓存
					//setTimeout($.post("admin/common/PageContentManageAction!pushDestroyPreview.action",{versionId : id},function(re){
					//},"text"),15000);
				}
			} else {
				alert(re);
			}
		},"text");
	}
	
	//　启用版本
	function pushVersion(id) {
		$.post("admin/common/PageContentManageAction!pushVersion.action",{versionId : id},function(re){
			alert(re);
			window.location.reload();
		},"text");
	}
	
	// 删除版本
	function delVersion(id) {
		if (confirm("确定删除吗？")) {
			window.location.href = "admin/common/PageContentManageAction!delVersion.action?versionId=" + id;
		} else {
			return;
		}
	}
	</script>
</head>
<body>
	<div style="width: 100%; margin: 0 auto;">
		<div class="kzmb_table_3">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr class="kzmb_nm_3">
					<td><a name="id" class="sort">启用标示(版本序号)<img  alt="点击排序" src="images/down_sort.gif" border="0"/></a></td>
					<td><a name="versionName" class="sort">版本名称<img  alt="点击排序" src="images/down_sort.gif" border="0"/></a></td>
					<td><a name="readOnly" class="sort">只读<img  alt="点击排序" src="images/down_sort.gif" border="0"/></a></td>
					<td><a name="systemType" class="sort">系统类别<img  alt="点击排序" src="images/down_sort.gif" border="0"/></a></td>
					<td><a name="pushUrl" class="sort">推送地址<img  alt="点击排序" src="images/down_sort.gif" border="0"/></a></td>
					<td><a name="versionDescription" class="sort">版本说明<img  alt="点击排序" src="images/down_sort.gif" border="0"/></a></td>
					<td>操作</td>
				</tr>
				<s:iterator value="versionList">
					<tr class="kzmb_nr">
						<td>
							<s:property value="version.systemType"/>
							<s:if test="enableMarkup == @com.sjdf.platform.dictionary.bean.WhetherState@YES">
								${id}<input type="radio" name="${systemType}" checked="checked" readonly="readonly" />启用中
							</s:if>
							<s:else>
								${id}
							</s:else>
						</td>
						<td>${versionName}</td>
						<td>${readOnlyInfo }</td>
						<td>${systemTypeInfo }</td>
						<td>${pushUrl }</td>
						<td>${versionDescription }</td>
						<td>
							<a href="admin/common/PageContentManageAction!turnToIndex.action?versionId=${id }">查看树</a>
							<a href="admin/common/PageContentManageAction!preModifyVersion.action?versionId=${id }">修改</a>
							<s:if test="enableMarkup != @com.sjdf.platform.dictionary.bean.WhetherState@YES">
								<a onclick="previewIndex(${id})" href="javascript:void(0)">预览首页</a>
								<a onclick="pushVersion(${id})" href="javascript:void(0)">启用</a>
								<s:if test="readOnly != @com.sjdf.platform.dictionary.bean.WhetherState@YES">
									<a onclick="delVersion(${id})" href="javascript:void(0)">删除</a>
								</s:if>
							</s:if>
						</td>
					</tr>
				</s:iterator>
			</table>
			<my:page />
		</div>
	</div>
</body>
</html>