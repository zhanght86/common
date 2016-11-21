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
<link rel="stylesheet" href="ueditor/themes/default/ueditor.css" />
<script type='text/javascript' src='js/jquery-1.6.4.min.js'></script>
<script type='text/javascript' src='js/ajaxfileupload.js'></script>
<script type="text/javascript" src="ueditor/editor_all.js"></script>
<script type="text/javascript" src="ueditor/editor_config.js"></script>
<title>页面内容信息</title>
<script type="text/javascript">
	jQuery.noConflict(); //解决jquery冲突问题
	
	function ajaxFileUpload() {
		var fileVal = jQuery("#file").attr("value");
		var fileValArr = fileVal.split(".");
		var fileSuffixes = fileValArr[fileValArr.length - 1];
		if (!(fileSuffixes == 'jpg' || fileSuffixes == 'gif')) {
			alert("请上传jpg文件！");
			return false;
		}
		jQuery("#loading").ajaxStart(function() {
			jQuery(this).show();
		})//开始上传文件时显示一个图片
		.ajaxComplete(function() {
			jQuery(this).hide();
		});//文件上传完成将图片隐藏起来
		jQuery.ajaxFileUpload({
			url : 'admin/common/PageContentManageAction!uploadImage.action',//用于文件上传的服务器端请求地址
			secureuri : false,//一般设置为false 这个为空ajaxfileupload中的iframe不显示
			fileElementId : 'file',//文件上传空间的id属性  <input type="file" id="file" name="file" />
			dataType : 'json',//返回值类型 一般设置为json
			data : {//加入的文本参数
				"databaseId" : "${contentManageBean.id}",
				"fileNameSuffixes" : fileSuffixes
			},
			success : function(data, status) //服务器成功响应处理函数
			{
				//这里放入返回成功后需要处理的响应data是返回的数据
				var imgPath = data.tmpPath;
				var imgAction = data.tmpAction;
				jQuery("#imgUrl").val(imgPath);
				jQuery("#imgShow").attr('src',imgAction);
			},
			error : function(data, status, e)//服务器响应失败处理函数
			{
				//服务器响应失败的处理信息。
			}
		});
		return false;
	}
	
	jQuery(function() {
		// 预览首页
		jQuery("#preview").click(function() {
			jQuery.post("admin/common/PageContentManageAction!pushPreview.action",{databaseId : "${contentManageBean.id}"},function(re) {
				var result = re.split("❤");
				if ("success" == result[0]) {
					alert(result[1]);
					var open = window.open(result[1] + "?officialPageContnet=2");
					if (open.document.readyState=="complete") {
						// 清除预览缓存
						//setTimeout(jQuery.post("admin/common/PageContentManageAction!pushDestroyPreview.action",{versionId : "${contentManageBean.versionId}"},function(re){
						//},"text"),15000);
					}
				} else {
					alert(re);
				}
			},"text");
		});
		
		// html编辑器
		var editor = new baidu.editor.ui.Editor({
			UEDITOR_HOME_URL : "${basePath}ueditor/",
			iframeCssUrl : "${basePath}ueditor/themes/default/iframe.css",
			textarea : 'contentManageBean.remark'
		});
		editor.render("remark");
	});
	
</script>
</head>
<body>
	<div style="width: 100%; margin: 0 auto;">
		<div class="kzmb_table_3">
			<form action="admin/common/PageContentManageAction!modify.action" method="post">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr class="kzmb_nm_3">
						<td>系统类别</td>
						<td><s:property value="versionManageBean.systemTypeInfo" /></td>
						<td>页面标示</td>
						<td><s:select list="pageMarkList" headerKey="0" headerValue="无"
								name="contentManageBean.pageMarkup" listKey="key"
								listValue="value"></s:select></td>
					</tr>
					<tr class="kzmb_nm_3">
						<td>链接地址</td>
						<td><s:textfield size="40" name="contentManageBean.linkUrl"></s:textfield></td>
						<td>预览地址</td>
						<td><s:textfield size="40" name="contentManageBean.previewUrl"></s:textfield></td>
					</tr>
					<tr class="kzmb_nm_3">
						<td>菜单名称</td>
						<td><s:textarea name="contentManageBean.menuName" rows="5" cols="50" ></s:textarea></td>
						<td>*排列顺序</td>
						<td><s:textfield size="40" name="contentManageBean.displaySort"></s:textfield></td>
					</tr>
					<tr class="kzmb_nm_3">
						<td>*是否新窗口打开</td>
						<td><s:select list="whetherList"
								name="contentManageBean.whetherNewWindow" listKey="key"
								listValue="value"></s:select>
						</td>
						
						<td>*是否显示</td>
						<td><s:select list="whetherList"
								name="contentManageBean.whetherDisplay" listKey="key"
								listValue="value"></s:select>
						</td>
					</tr>
					<tr class="kzmb_nm_3">
						<td>图片</td>
						<td>
							<input type="file" id="file" name="file" onchange="ajaxFileUpload();" />
							<s:hidden id="imgUrl" name="contentManageBean.imageUrl"></s:hidden>
						</td>
						<td colspan="2">
							<%-- <a href="javascript:void(0);"><img src="admin/common/PageContentManageAction!showImage.action?databaseId=${contentManageBean.id}" id="imgShow" width="232" height="137"/></a> --%>
							<a href="javascript:void(0);"><img src="${ contentManageBean.showImageUrl}?<%=Math.random() %>" id="imgShow" width="232" height="137"/></a>
						</td>
					</tr>
					<%-- <tr class="kzmb_nm_3">
						<td>内容</td>
						<td colspan="3" style="text-align: left;max-width: 400px;">、
							<script type="text/plain" id="remark" style="width:50%;">${contentManageBean.remark}</script>
							<s:textarea name="contentManageBean.remark" rows="5" cols="40" ></s:textarea>
						</td>
					</tr> --%>
					<tr class="kzmb_nm_3">
						<td>选中判定设置</td>
						<td colspan="3" style="text-align: left;">、
							<s:textarea name="contentManageBean.realRemark" rows="5" cols="40" ></s:textarea>
						</td>
					</tr>
					<tr class="kzmb_nm_3">
						<td colspan="4">
							<s:hidden name="contentManageBean.id"></s:hidden>
							<s:if test="versionManageBean.readOnly != @com.sjdf.platform.dictionary.bean.WhetherState@YES">
								<s:submit value="修改"></s:submit>
							</s:if>
							<a id="preview" href="javascript:void(0);">预览</a>
						</td>
					</tr>
				</table>
				内容：
				<br />
				<script type="text/plain" id="remark" style="width:100%;">${contentManageBean.remark}</script>
			</form>
		</div>
	</div>
</body>
</html>