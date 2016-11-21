<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="${basePath}" />
<title>附件信息编辑</title>
<script type='text/javascript' src="js/jquery-1.6.4.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/ueditor/editor_config.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/ueditor/editor_all.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/ueditor/themes/default/ueditor.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.jqzoom-core.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/jquery.jqzoom.css">
<style type="text/css">
#main{
	width:800px;
	margin: auto;
}
table tr td span {
	color: #ff6701;
}
.tableLabelClass{
	text-align: right;
}
div p{
	background-color: appworkspace;
}
span p{
	background-color: white;
	color: red;
}
.searchButton{
	width: 65px;
	height: 30px;
	background-color: buttonhighlight;
}
.searchButton:hover{
	background-color: gray;
}
#editor{
	width:800px;
}
#associate{
	width:800px;
}
#southDiv{
	widht:800px;
	float: left;
	padding-top:10px; 
}
</style>
<script type="text/javascript">
	$(function(){
		$('.jqzoom').jqzoom({
			zoomType: 'standard',//标准类型其它值为：drag|innerzoom|reverse|standard
			lens:true,//鼠标上的框
			preloadImages: true,
			alwaysOn:false,//是否总是显示
			zoomHeight:240
		});
		
		//返回按钮的链接
		$("#backButton").click(function(){
			history.back();
		});
		//提交之前的数据初始化
		$("#saveButton").click(function(){
			$("#attachmentInfoForm").attr("action","admin/common/AttachmentAction!modifyAttachment.action");
			$("#attachmentInfoForm").submit();
		});
	});
</script>
</head>
<body>
	<div id="main">
		<s:if test="errorInfo != null && !''.equals(errorInfo)">
			<span><p>提示信息：${errorInfo }</p></span>
		</s:if>
		<s:form id="attachmentInfoForm" method="post" enctype="multipart/form-data">
		<s:hidden id="attachmentHouseVoId" name="attachmentHouseVo.id"></s:hidden>
		<div id="northDiv">
			<div id="associate">
				<p>附件信息</p>
				<table id="editTable">
					<tr>
						<td>附件所属用途:</td>
						<td><s:select style="width:196px;" id="attachmentUseCode" name="attachmentHouseVo.attachmentUseCode" list="attachmentUseCodeMap" listKey="key" listValue="value"  headerKey="0" headerValue="=====请选择=====" ></s:select>
						</td>
					</tr>
					<tr>
						<td>附件所属类别:</td>
						<td><s:select style="width:196px;" id="attachmentUseType" name="attachmentHouseVo.attachmentUseType" list="attachmentUseTypeMap" listKey="key" listValue="value" headerKey="0" headerValue="=====请选择=====" ></s:select>
						</td>
					</tr>
					<tr>
						<td>附件所属系统:</td>
						<td><s:select style="width:196px;" id="systemType" name="attachmentHouseVo.systemType" list="systemTypeMap" listKey="key" listValue="value" headerKey="0" headerValue="=====请选择=====" ></s:select>
						</td>
					</tr>
					<tr>
						<td>附件特别标识:</td>
						<td><s:textfield style="width:192px;" id="attachmentSpecialMark" name="attachmentHouseVo.attachmentSpecialMark" readOnly="true"></s:textfield></td>
					</tr>
					<tr>
						<td align="right">附件：</td>
						<td><a href="${ attachmentHouseVo.path}" class="jqzoom" title="附件"><img src="${ attachmentHouseVo.resizePath}"/></a></td>
					</tr>
					<tr>
						<td align="right">上传：</td>
						<td><s:file name="upload"></s:file></td>
					</tr>
				</table>
			</div>
		</div>
		<div id="southDiv">
			<input type="submit" id="saveButton" class="searchButton" value="保存" />
			<input type="button" id="backButton" class="searchButton" value="返回" />
		</div>
		</s:form>
	</div>
</body>
</html>