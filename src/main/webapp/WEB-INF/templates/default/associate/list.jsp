<%@page import="com.sjdf.platform.dictionary.bean.RemoteAccess"%>
<%@page import="com.sjdf.platform.dictionary.cache.ConfigManager"%>
<%@page import="com.sjdf.platform.dictionary.bean.SystemType"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="my" uri="/sjdf-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="${basePath}" />
<title>编辑关联信息</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/tools_css.css" />
<script type='text/javascript' src="js/jquery-1.6.4.min.js"></script>
<script type="text/javascript">
$(function(){
	var errorMsg = "${errorMsg}";
	if(errorMsg!=null && errorMsg != ""){
		alert(errorMsg);	
	}
	
	$("#commonSystemTypeList").change(function(){
		var associateUserLabel = $("#associateUserLabel");
		var systemType = "<%=SystemType.EISS%>";
		if($(this).val() == systemType){
			associateUserLabel.html("用户编号：");
		}else{
			associateUserLabel.html("用户名称：");
		}
	});
	
	$("#associate").click(function(){
		var form = $("#associateDataForm"); 
		$.post(form.attr("action"),form.serializeArray(),function(re){
			if(re.indexOf("status:1") != -1){
				$("#password").remove();
				form.attr("action","user/common/AssociateAction!list.action").submit();
			}else{
				alert(re);
			}
		});
	});
	
	$(".cancel").click(function(){
		var id = $(this).parent().parent().attr("id");
		window.self.location = "user/common/AssociateAction!cancel.action?idx="+id;
	});
});
</script>
<style type="text/css">
#associateDataForm div{
width: 100%;
line-height:25px;
float: left;
}
#associateDataForm div span{
width:5%;
float: left;
text-align: right;
}
#associateDataForm .input{
margin-left:5%;
}
.point {
	margin:7px 0 7px 13px;
	float:left;
	width:98%;
	height:auto;
	display:inline;
}
.point span {
	width:110px;
	height:28px;
	float:left;
	font:bold 15px/28px "雅黑宋体", "宋体";
	color:#1b8ecb;
	border-bottom:2px solid #1b8ecb;
}
.point_text {
	width:98%;
	padding:10px 15px;
	float:left;
	font:12px/20px 宋体;
	color:#444;
	border-top:1px solid #ececec;
}
</style>
</head>
<body>
	<div class="point">
		<span>功能说明：</span>
		<div class="point_text">
			1.本公司信息库支持跨会员、跨系统通过用户关联的方式来调取不包含财务和产品外的其他信息或者附件。如果被您关联的用户不修改会员密码，则该关联关系一直有效，如被关联的用户修改了会员密码，则需要重新关联该用户。<br/>
			2.在本公司系统需要填写相关信息或者上传附件时，系统会优先在会员和关联会员的信息库中调取数据来使用，以减少客户填写和上传的时间。<br/>
			3.如果被关联用户修改了会员密码以后，关联关系自动终止，关联是否还有效，可以在下面的关联列表查看。一般情况下无须关注关联的有效性，在具体触发到对应信息的时候，系统会提示你进行用户关联。<br/>
			4.从关联用户信息库调取并使用过的信息，不会因为后期关联关系的无效而失去。<br/>
			5.如果您不需要继续关联某用户，可以在下面的关联用户列表中的操作中做：取消关联  则关联关系被终止。<br/>
			6.从信息库调取附件信息，已经被审核通过的附件一般不再显示，更不支持修改；未审核的附件会被调取并显示但是支持覆盖上传，覆盖上传的信息仅在引用方保存，覆盖上传不会溯及被引用方。审核未被通过的附件不会被引用。<br/>
			因附件调取和审核之间的时间差异，故信息库会存在多份审核标识不同的附件。系统会优先调用审核通过的附件、最新上传的附件。<br/>
		</div>
	</div>
	<br/>
	<form id="associateDataForm" method="post" action="<%=ConfigManager.getInstance().getValue(RemoteAccess.class,RemoteAccess.ASSOCIATE_SAVE_OR_UPDATE_URL)%>" title="关联信息编辑">
		<input type="hidden" name="vercode" value="${vercodeInfo}"/>
		<input type="hidden" name="vertime" value="${vertime}"/>
		<input type="hidden" name="associate.currentSystemType" value="${associate.currentSystemType}"/>
		<input type="hidden" name="associate.currentUser" value="${associate.currentUser}"/>
		<div><span>关联系统：</span><s:select id="commonSystemTypeList" list="systemTypeList" name="associate.associateSystemType" listKey="attr" listValue="name" style="width:159px;"></s:select></div>
		<div><span id="associateUserLabel">用户编号：</span><input type="text" style="width:155px" name="associate.associateUser" value="${associate.associateUser}"/></div>
		<div><span>密&nbsp;&nbsp;&nbsp;&nbsp;码：</span><input id="password" type="password" style="width:155px" name="associate.associatePwd"/></div>
		<div><input type="button" class="input" id="associate" value="关联"/><input class="input" type="reset" value="重置"/></div>
	</form>
	
	<div class="kzmb_table_3" style="float:left;padding-left:7px;">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr class="kzmb_nm_3">
				<td><a name="associateSystemType" class="sort">关联目标系统<img  alt="点击排序" src="images/down_sort.gif" border="0"/></a></td>
				<td><a name="associateUser" class="sort">用户名称<img  alt="点击排序" src="images/down_sort.gif" border="0"/></a></td>
				<td><a name="valid" class="sort">状态<img  alt="点击排序" src="images/down_sort.gif" border="0"/></a></td>
				<td><a name="createTime" class="sort">关联时间<img  alt="点击排序" src="images/down_sort.gif" border="0"/></a></td>
				<td><a name="cancelTime" class="sort">取消关联时间<img  alt="点击排序" src="images/down_sort.gif" border="0"/></a></td>	
				<td><a name="ipAddress" class="sort">操作人IP<img  alt="点击排序" src="images/down_sort.gif" border="0"/></a></td>
				<td><a name="invalidTime" class="sort">失效检测时间<img  alt="点击排序" src="images/down_sort.gif" border="0"/></a></td>
				<td>操作</td>
			</tr>
			<s:iterator value="associateList">
				<tr class="content kzmb_nr" id="${id}" title="${remark}">
					<td>${associateSystemTypeInfo}&nbsp;</td>
					<td>${associateUser}&nbsp;</td>
					<td>
						<s:if test="valid == @com.sjdf.platform.dictionary.bean.ValidMark@VALID">
							<img src="images/valid.gif"/>
						</s:if>
						<s:else>
							<img src="images/invalid.gif"/>
						</s:else>&nbsp;
					</td>
					<td><s:date name="createTime" format="yyyy-MM-dd HH:mm:ss"/>&nbsp;</td>
					<td><s:date name="cancelTime" format="yyyy-MM-dd HH:mm:ss"/>&nbsp;</td>	
					<td>${ipAddress}&nbsp;</td>
					<td><s:date name="invalidTime" format="yyyy-MM-dd HH:mm:ss"/>&nbsp;</td>
					<td>
						<s:if test="valid == @com.sjdf.platform.dictionary.bean.ValidMark@VALID">
							<input type="button" class="cancel" value="取消关联"/>
						</s:if>&nbsp;
					</td>
				</tr>
			</s:iterator>
		</table>
		<my:page/>
	</div>
</body>
</html>
