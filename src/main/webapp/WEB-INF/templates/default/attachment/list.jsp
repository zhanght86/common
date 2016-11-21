<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="my" uri="/sjdf-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="${basePath}" />
<title>附件库信息</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/jquery-ui-1.8.16.custom.css" />
<link rel="stylesheet" type="text/css" href="css/tools_css.css" />
<link rel="stylesheet" type="text/css" href="css/sfilter.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/tip-yellow/tip-yellow.css" type="text/css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/tip-yellowsimple/tip-yellowsimple.css" type="text/css" />
<script type='text/javascript' src="js/jquery-1.6.4.min.js"></script>
<script type='text/javascript' src="js/jquery-ui-1.8.16.custom.min.js"></script>
<script type='text/javascript' src="js/jquery.blockUI.js"></script>
<script type='text/javascript' src="js/blockui.js"></script>
<script type='text/javascript' src='<%=request.getContextPath()%>/js/My97DatePicker/WdatePicker.js'></script>
<script type='text/javascript' src="js/display-info.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/css/jquery.poshytip.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.jqzoom-core.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/jquery.jqzoom.css">
<script type='text/javascript'>
	$(document).ready(function() {
		$('.jqzoom').jqzoom({
			zoomType: 'standard',//标准类型其它值为：drag|innerzoom|reverse|standard
			lens:true,//鼠标上的框
			preloadImages: true,
			alwaysOn:false,//是否总是显示
			zoomHeight:240
		});
		$("#search").click(function(){
			$("#searchForm").attr("action","admin/common/AttachmentAction!showAttachmentList.action");
			$("#searchForm").submit();;
		});
		//$("#add").click(function(){
		//	window.location = "admin/common/AttachmentAction!showAttachment.action";
		//});
	});
</script>
<style type="text/css">
	#main{
		width:100%;
		margin: auto;
	}
	.buttonClass{
		margin: 0 0 0 6px;
		width:60px;
		height:25px;
	}
	.searchButton{
		width: 165px;
		height: 25px;
		background-color: buttonhighlight;
		margin-left: 5px; 
	}
	.searchButton:hover{
		background-color: gray;
	}
	.footer{
		font-size: 15px;
	}
	#editTable{
		width: 100%;
	}
	#buttonDiv{
		margin: 5px 0 5px 0px;
	}
	.a_hover:hover{
		color:red;
		text-decoration: underline;
	}
	.operateHref {
		color:blue;
	}
	.operateHref:hover {
		color:red;
		text-decoration: underline;
	}
</style>
</head>
<body>
	<div id="main">
		<div id="buttonDiv">
			<label class="class_p">信息查询</label>
		</div>
		<div class="header">
			<form id="searchForm" action="#" method="post">
				<table id="editTable">
					<tr>
						<td align="right"><label class="class_p">附件用途:</label></td>
						<td><s:select id="attachmentUseCodeMap" style="width:196px;" list="attachmentUseCodeMap" listKey="key" listValue="value" headerKey="0" headerValue="=====请选择=====" name="attachmentHouseVo.attachmentUseCode"></s:select></td>
						<td align="right"><label class="class_p">附件类别:</label></td>
						<td><s:select id="attachmentUseTypeMap" style="width:196px;" list="attachmentUseTypeMap" listKey="key" listValue="value" headerKey="0" headerValue="=====请选择=====" name="attachmentHouseVo.attachmentUseType"></s:select></td>
						<td>附件大小(始)：</td>
						<td><s:textfield style="width:196px;" name="attachmentHouseVo.startSize" /></td>
						<td>更新时间(始)：</td>
						<td><s:textfield  class="Wdate" style="width:196px;" name="attachmentHouseVo.beginDate"  onClick="WdatePicker({isShowClear:false,readOnly:true,dateFmt:'yyyy-MM-dd'})" /></td>
					</tr>
					<tr>
						<td align="right"><label class="class_p">所属系统:</label></td>
						<td><s:select id="systemTypeMap" style="width:196px;" list="systemTypeMap" listKey="key" listValue="value" headerKey="0" headerValue="=====请选择=====" name="attachmentHouseVo.systemType"></s:select></td>
						<td align="right"><label class="class_p">附件格式:</label></td>
						<td><s:select id="attachmentFormatMap" style="width:196px;" list="attachmentFormatMap" listKey="key" listValue="value" headerKey="0" headerValue="=====请选择=====" name="attachmentHouseVo.format"></s:select></td>
						<td>附件大小(末)：</td>
						<td><s:textfield style="width:196px;" name="attachmentHouseVo.endSize" /></td>
						<td>更新时间(末)：</td>
						<td><s:textfield  class="Wdate" style="width:196px;" name="attachmentHouseVo.endDate"  onClick="WdatePicker({isShowClear:false,readOnly:true,dateFmt:'yyyy-MM-dd'})" /></td>
					</tr>
					<tr>
						<td colspan="8" align="center">
							<input type="button"  id="search" class="searchButton" value="搜索"/>
						</td>
					</tr>
				</table>
			</form>
		</div>
		<div id="buttonDiv">
			<label class="class_p">信息列表</label>
		</div>
		<div class="kzmb_table_3" style="float:left;padding-left:7px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr class="kzmb_nm_3">
					<td width="308px;">附件</td>
					<td>附件信息</td>	
					<td width="5%">操作</td>
				</tr>
				<s:iterator var="attachmentBean" value="sourceAttachmentList">
					<tr class="content kzmb_nr" id="${id}">
						<td>
							<s:if test="#attachmentBean.path != null && !''.equals(#attachmentBean.path)">
								<a href="${ attachmentBean.path}" class="jqzoom" title="附件"><img src="${ attachmentBean.resizePath}"/></a>
							</s:if>
						</td>
						<td>
							<div>
								<ul>
									<li>附件用途：<s:property value="attachmentUseCodeMap[#attachmentBean.attachmentUseCode]" /></li>
									<li>附件类别：<s:property value="attachmentUseTypeMap[#attachmentBean.attachmentUseType]" /></li>
									<li>附件标识：${ attachmentBean.attachmentSpecialMark}</li>
									<li>所属系统：<s:property value="systemTypeMap[#attachmentBean.systemType]" /></li>
									<li>附件大小：${ attachmentBean.size}</li>
									<li>附件格式：<s:property value="attachmentFormatMap[#attachmentBean.format]" /></li>
									<li>创建日期：${ attachmentBean.createTime}</li>
									<li>更新日期：${ attachmentBean.updateTime}</li>
								</ul>
							</div>
						</td>
						<td>
						<a href="admin/common/AttachmentAction!downloadAttachment.action?attachmentHouseVo.id=${ attachmentBean.id}" class="operateHref" target="_blank">下载</a><br />
						<a href="admin/common/AttachmentAction!showAttachment.action?attachmentHouseVo.id=${ attachmentBean.id}" class="operateHref">修改</a><br />
						<a href="admin/common/AttachmentAction!delAttachment.action?attachmentHouseVo.id=${ attachmentBean.id}" class="operateHref">删除</a>
						</td>
					</tr>
				</s:iterator>
			</table>
		</div>
		<div id="dialogDiv"></div>
		<div class="footer">
			<my:page/>
		</div>
	</div>
</body>
</html>