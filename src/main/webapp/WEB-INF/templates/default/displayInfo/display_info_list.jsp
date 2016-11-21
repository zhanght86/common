<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="my" uri="/sjdf-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="${basePath}" />
<title>信息查询</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/jquery-ui-1.8.16.custom.css" />
<link rel="stylesheet" type="text/css" href="css/tools_css.css" />
<link rel="stylesheet" type="text/css" href="css/sfilter.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/tip-yellow/tip-yellow.css" type="text/css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/tip-yellowsimple/tip-yellowsimple.css" type="text/css" />
<script type='text/javascript' src="js/jquery-1.6.4.min.js"></script>
<script type='text/javascript' src="js/jquery-ui-1.8.16.custom.min.js"></script>
<script type='text/javascript' src="${staticPath}/js/jquery.blockUI.js"></script>
<script type='text/javascript' src="${staticPath}/js/blockui.js"></script>
<script type='text/javascript' src='<%=request.getContextPath()%>/js/My97DatePicker/WdatePicker.js'></script>
<script type='text/javascript' src="js/display-info.js"></script>
<script type='text/javascript' src="js/sfilter.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/css/jquery.poshytip.js"></script>
<script type='text/javascript'>
	document.domain = "${topDomain}";
	function submitConditon(tempUrl){
		var ownerTypeInfoValue = beforeSubmit();
		$("#ownerTypeInfo").val(ownerTypeInfoValue);
		$("#searchForm").attr("action",tempUrl);
		$("#searchForm").submit();
	}
	$(function(){
		// 下拉框输入搜索
		$("#displayInfoTypeMap").sfilter();
		$("#clazz0").sfilter();
		
		$("#add").click(function(){
			submitConditon("admin/common/DisplayInfoAction!preAdd.action");
		});
		$('.demo-tip-yellow').poshytip();
		//查询选定的配置数据
		$("#clazz").change(function() {
			var clazz = $(this).find("option:selected").val();
			window.self.location = list + "?dictionary.clazz=" + clazz;
		});
		$("tr.content").dblclick(function() {
			$("#selectId").val($(this).attr("id"));
			submitConditon("admin/common/DisplayInfoAction!getDisplayInfoById.action");
		});
		$("#search").click(function(){
			submitConditon("admin/common/DisplayInfoAction!displayInfoList.action");
		});
	});
</script>
<style type="text/css">
	#main{
		width:1024px;
		margin: auto;
	}
	.buttonClass{
		margin: 0 0 0 6px;
		width:60px;
		height:25px;
	}
	.searchButton{
		width: 65px;
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
		width: 1024px;
	}
	#buttonDiv{
		margin: 5px 0 5px 0px;
	}
	.a_hover:hover{
		color:red;
		text-decoration: underline;
	}
</style>
</head>
<body>
	<div id="main">
		<div>
			<label class="class_p">信息查询</label>
		</div>
		<div class="header">
			<form id="searchForm" action="#" method="post">
				<s:hidden id="ownerTypeInfo" name="searchVo.displayInfoOwnerType"/>
				<s:hidden id="user" name="user"/>
				<s:hidden id="selectId" name="displayInfoBean.id"/>
				<table id="editTable">
					<tr>
						<td align="right"><label class="class_p">信息类别:</label></td>
						<td><s:select id="displayInfoTypeMap" style="width:196px;" list="displayInfoTypeMap" listKey="key" listValue="value" headerKey="0" headerValue="=====请选择=====" name="searchVo.displayInfoInfoType"/></td>
						<td align="right"><label class="class_p">更新时间(开始):</label></td>
						<td><s:textfield  class="Wdate" style="width:196px;" name="searchVo.beginOfupdateDate"  onClick="WdatePicker({isShowClear:false,readOnly:true})" />
					</tr>
					<tr>
						<td align="right"><label class="class_p">信息内容:</label></td>
						<td><s:textfield name="searchVo.displayInfoContent" style="width:196px;" id="content"/></td>
						<td align="right"><label class="class_p">更新时间(结束):</label></td>
						<td><s:textfield  class="Wdate" style="width:196px;" name="searchVo.endOfupdateDate"  onClick="WdatePicker({isShowClear:false,readOnly:true})" />
					</tr>
					<s:if test="displayInfoBean.ownerType != null && !''.equals(displayInfoBean.ownerType)">
						<s:iterator value="displayInfoBean.ownerTypeMap" var="ownerType" status="status">
							<tr id="ownerType${status.index }">
								<td align="right">所属类别:</td>
								<td>
									<s:select id="clazz%{#status.index}" name="dictionary.clazz" list="clazzNameMap" listKey="key" listValue="value" headerKey="0" headerValue="====请选择====" value="#ownerType.key" onchange="ownerTypeChange(%{#status.index});"/>
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
							<td align="right">所属类别:</td>
							<td>
								<s:select id="clazz0" name="dictionary.clazz" list="clazzNameMap" listKey="key" listValue="value" headerKey="0" headerValue="====请选择====" onchange="ownerTypeChange(0);"/>
								<input id="createOwnerType0" type="button" value="添加" onclick="createOwnerType(0);"/>
							</td>
						</tr>
					</s:else>
				</table>
			</form>
		</div>
		<div id="buttonDiv">
			<label class="class_p">信息列表</label><input type="button"  id="search" class="searchButton" value="搜索"/><button type="button" id="add" class="searchButton">添加</button>
		</div>
		<div class="kzmb_table_3" style="float:left;padding-left:7px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr class="kzmb_nm_3">
					<td><a name="infoType" class="sort">信息类别<img  alt="点击排序" src="images/down_sort.gif" border="0"/></a></td>
					<td><a name="ownerType" class="sort">所属类别<img  alt="点击排序" src="images/down_sort.gif" border="0"/></a></td>	
					<td><a name="content" class="sort">信息内容<img  alt="点击排序" src="images/down_sort.gif" border="0"/></a></td>
					<td><a name="content" class="sort">最近操作人<img  alt="点击排序" src="images/down_sort.gif" border="0"/></a></td>
					<td><a name="createTime" class="sort">创建时间<img  alt="点击排序" src="images/down_sort.gif" border="0"/></a></td>
					<td><a name="updateTime" class="sort">更新时间<img  alt="点击排序" src="images/down_sort.gif" border="0"/></a></td>
					<td>操作</td>
				</tr>
				<s:iterator var="tempDisplayInfoBean" value="displayInfoBeanList">
					<tr class="content kzmb_nr" id="${id}">
						<td>${tempDisplayInfoBean.infoTypeString}</td>
						
						<s:set name="tempOwnerTypeString"  value="#tempDisplayInfoBean.ownerTypeString"></s:set>
						<td class="demo-tip-yellow" title='${tempDisplayInfoBean.ownerTypeString}'>
							<s:if test="#tempOwnerTypeString.length()>15">
								<s:property value="#tempOwnerTypeString.substring(0,15)+'...'" />
							</s:if>
							<s:else>
								<s:property value="#tempOwnerTypeString" />
							</s:else>
						</td>
						<s:set name="tempContent" value="#tempDisplayInfoBean.content"></s:set>
						<td class="demo-tip-yellow" title='${tempDisplayInfoBean.content}'>
							<s:if test="#tempContent.length()>25">
								<s:property value="#tempContent.substring(3,25)+'...'" />
							</s:if>
							<s:else>
								<s:property value="#tempContent" />
							</s:else></td>
						<td>${tempDisplayInfoBean.updateUser }</td>
						<td><s:date name="#tempDisplayInfoBean.createTime" format="yyyy-MM-dd HH:mm:ss" /></td>
						<td><s:date name="#tempDisplayInfoBean.updateTime" format="yyyy-MM-dd HH:mm:ss" /></td>
						<td><a href="admin/common/DisplayInfoAction!deleteDisplayInfo.action?displayInfoBean.id=${id}" class="a_hover">删除</a></td>
					</tr>
				</s:iterator>
			</table>
		</div>
		<div id="dialogDiv"></div>
		<div class="footer">
			<my:page/>
		</div>
	</div>
	<script type='text/javascript' src="${staticPath}/js/adapter-height.js"></script>
</body>
</html>