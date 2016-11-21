<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="my" uri="/sjdf-tags" %>
<!DOCTYPE html>
<html>
<head>
<base href="${basePath}" />
<title>信息内容编辑</title>
<script type='text/javascript' src="js/jquery-1.6.4.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/ueditor/editor_config.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/ueditor/editor_all.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/ueditor/themes/default/ueditor.css" />
<script type='text/javascript' src="js/display-info.js"></script>
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
	float: right;
	padding-top:10px; 
}
</style>
<script type="text/javascript">
	$(function(){
		//baidu的编辑器
		var editor = new baidu.editor.ui.Editor({
			UEDITOR_HOME_URL : "${basePath}ueditor/",
			iframeCssUrl : "${basePath}ueditor/themes/default/iframe.css",
			highlightJsUrl :"${basePath}ueditor/third-party/SyntaxHighlighter/shCore.js",
			highlightCssUrl :"${basePath}ueditor/third-party/SyntaxHighlighter/shCoreDefault.css",
			codeMirrorJsUrl :"${basePath}ueditor/third-party/codemirror2.15/codemirror.js",
			codeMirrorCssUrl :"${basePath}ueditor/third-party/codemirror2.15/codemirror.css",
			textarea : 'displayInfoBean.content'
		});
		editor.render("content");
		//返回按钮的链接
		$("#backButton").click(function(){
			$("#displayInfoForm").attr("action","admin/common/DisplayInfoAction!displayInfoList.action");
			$("#displayInfoForm").submit();
		});
		//提交之前的数据初始化
		$("#saveButton").click(function(){
			var ownerTypeInfoValue = beforeSubmit();
			$("#ownerTypeInfo").val(ownerTypeInfoValue);
		});
	});
</script>
</head>
<body>
	<div id="main">
		<s:if test="errorInfo != null && !''.equals(errorInfo)">
			<span><p>提示信息：${errorInfo }</p></span>
		</s:if>
		<s:form id="displayInfoForm" action="admin/common/DisplayInfoAction!saveDisplayInfo.action" method="post">
		<s:hidden id="displayInfoBeanId" name="displayInfoBean.id"></s:hidden>
		<s:hidden id="createTime" name="displayInfoBean.createTime"></s:hidden>
		<s:hidden name="searchVo.displayInfoContent"></s:hidden>
		<s:hidden name="searchVo.displayInfoInfoType"></s:hidden>
		<s:hidden name="searchVo.displayInfoOwnerType"></s:hidden>
		<s:hidden name="searchVo.beginOfupdateDate"></s:hidden>
		<s:hidden name="searchVo.endOfUpdateDate"></s:hidden>
		<div id="northDiv">
			<div id="associate">
				<p>信息关联</p>
				<table id="editTable">
					<tr>
						<td>信息类别:</td>
						<td><s:select id="displayInfoTypeMap" style="width:196px;" list="displayInfoTypeMap" listKey="key" listValue="value" headerKey="0" headerValue="=====请选择=====" name="displayInfoBean.infoType"></s:select>
						</td>
					</tr>
					<s:if test="displayInfoBean.ownerType != null && !''.equals(displayInfoBean.ownerType)">
						<s:iterator value="displayInfoBean.ownerTypeMap" var="ownerType" status="status">
							<tr id="ownerType${status.index }">
								<td>所属类别:</td>
								<td>
									<my:filterSelect id="clazz%{#status.index}" name="dictionary.clazz" list="clazzNameMap" listKey="key" listValue="value" headerKey="" headerValue="====请选择====" onchange="ownerTypeChange(%{#status.index});"></my:filterSelect>
									<s:if test="#status.last">
										<input id="createOwnerType${status.index }" type="button" value="添加" onclick="createOwnerType(${status.index });"/>
									</s:if>
								</td>
							</tr>
							<script type="text/javascript">
								ownerTypeChange(${status.index});
							</script>
							<s:iterator value="#ownerType.value" var="checkedNum" status="checkStatus">
								<script type="text/javascript">
								setDefaultChecked('${ownerType.key}${status.index }', ${checkedNum});
								</script>
							</s:iterator>
						</s:iterator>
					</s:if>
					<s:else>
						<tr id="ownerType0">
							<td>所属类别:</td>
							<td>
								<my:filterSelect id="clazz0" name="dictionary.clazz" list="clazzNameMap" listKey="key" listValue="value" headerKey="0" headerValue="====请选择====" onchange="ownerTypeChange(0);"></my:filterSelect>
								<input id="createOwnerType0" type="button" value="添加" onclick="createOwnerType(0);"/>
							</td>
						</tr>
					</s:else>
				</table>
			</div>
			<div id="editor">
				<p>信息内容</p>
				<span id="link_target"></span><br/>
				<script type="text/plain" id="content" style="width: 800px; float:left">${displayInfoBean.content}</script>
			</div>
		</div>
		<div id="southDiv">
			<input type="submit" id="saveButton" class="searchButton" value="保存" />
			<input type="button" id="backButton" class="searchButton" value="返回" />
		</div>
		<s:hidden id="ownerTypeInfo" name="displayInfoBean.ownerType"></s:hidden>
		<s:hidden id="createUser" name="displayInfoBean.createUser"></s:hidden>
		<s:hidden id="user" name="user"></s:hidden>
		</s:form>
	</div>
</body>
</html>