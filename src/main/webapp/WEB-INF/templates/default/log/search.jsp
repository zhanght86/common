<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="my" uri="/sjdf-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script type='text/javascript' src='<%=request.getContextPath()%>/js/My97DatePicker/WdatePicker.js'></script>
<base href="${basePath}"/>
<script type='text/javascript' src='js/jquery-1.6.4.min.js'></script>
<link rel="stylesheet" type="text/css" href="css/admin_css.css"></link>
<link rel="stylesheet" type="text/css" href="css/style_zc.css"></link>
<title>日志管理搜索</title>
<script type="text/javascript">
$(function(){
	$("#functionClass").change(function(){
		
	});
});
</script>
</head>
<body>
	<div style="width: 900px; margin: 0 auto;">
		<div class="kzmb_table_3">
			<form action="admin/common/LogAction!list.action" method="post">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr class="kzmb_nm">
						<td class="mn_tit">系统类型:&nbsp;</td>
						<td width="160" class="inpt_text vh_wz">
							<s:select list="systemTypeList" name="log.systemType" listKey="attr" listValue="name" headerKey="" headerValue="===请选择==="/>
						</td>
						<td class="mn_tit">子系统类型: &nbsp;</td>
						<td width="160" class="inpt_text vh_wz">
							<s:select list="subsystemTypeList" name="log.subsystemType" listKey="attr" listValue="name" headerKey="" headerValue="===请选择==="/>
						</td>
					</tr>
					<tr class="kzmb_nm">
						<td class="mn_tit">功能大类:&nbsp;</td>
						<td class="inpt_text vh_wz">
							<s:select id="functionClass" list="functionClassList" name="log.functionClass" listKey="attr" listValue="name" headerKey="" headerValue="===请选择==="/></td>
						<td class="mn_tit">功能小类:&nbsp;</td>
						<td class="inpt_text vh_wz">
							<my:filterSelect id="log_functionType" list="functionTypeList" name="log.functionType" listKey="attr" listValue="name" headerKey="" headerValue="===请选择==="/>
						</td>
					</tr>
					<tr class="kzmb_nm">
						<td class="mn_tit">操作动作:&nbsp;</td>
						<td class="inpt_text vh_wz">
							<s:select list="operatorActionList" name="log.operatorAction" listKey="attr" listValue="name" headerKey="" headerValue="===请选择==="/>
						</td>
						<td class="mn_tit">日志类型:&nbsp;</td>
						<td class="inpt_text vh_wz">
							<s:select list="logTypeList" name="log.logType" listKey="attr" listValue="name" headerKey="" headerValue="===请选择==="/>
						</td>
					</tr>
					<tr class="kzmb_nm">
					  <td class="mn_tit">操作人:</td>
					  <td class="inpt_text vh_wz"><s:textfield name="log.loginUser" /></td>
					  <td class="mn_tit">资源ID:&nbsp;</td>
					  <td class="inpt_text vh_wz"><s:textfield name="log.resourceId" /></td>
				  </tr>
					<tr class="kzmb_nm">
						<td class="mn_tit">操作人ip地址:</td>
						<td class="inpt_text vh_wz"><s:textfield name="log.ipAddress" /></td>
						<td class="mn_tit">操作开始时间:</td>
						<td class="inpt_text vh_wz">
							<input name="log.beginTime" value='<s:date name="log.beginTime" format="yyyy-MM-dd HH:mm:ss"/>' class="Wdate" onclick="javascript:WdatePicker({isShowClear:true,readOnly:true});"/></td>
					</tr>
					<tr class="kzmb_nm">
						<td class="mn_tit">操作内容:&nbsp;</td>
						<td class="inpt_text vh_wz"><s:textfield name="log.operationalContent"/></td>
						<td class="mn_tit">操作结束时间:</td>
						<td class="inpt_text vh_wz">
							<input name="log.endTime" value='<s:date name="log.endTime" format="yyyy-MM-dd HH:mm:ss"/>' class="Wdate" onclick="javascript:WdatePicker({isShowClear:true,readOnly:true});"/></td>
					</tr>
					<tr class="kzmb_nm">
						<td class="mn_tit">结果信息:&nbsp;</td>
						<td class="inpt_text vh_wz"><s:textfield name="log.errorInfo"/></td>
						<td class="mn_tit">每页显示条数:&nbsp;</td>
						<td  class="inpt_text vh_wz"><s:textfield name="page.pageSize"/></td>
					</tr>
					<tr class="kzmb_nm">
						<td colspan="4"><div align="center">
								<input type="image" src="images/jqss_btn.jpg" />
							</div></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>
