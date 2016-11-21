<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<form id="dataForm">
	<input type="hidden" name="addressVo.id" value="${addressVo.id}"/>
	收&nbsp;件&nbsp;人:<input type="text" style="width:200px" name="addressVo.name" value="<s:property value='addressVo.name'/>" /><br/>
	单位名称:<input type="text" style="width:200px;margin-top:5px;margin-bottom:5px" name="addressVo.companyName" value="<s:property value='addressVo.companyName'/>" /><br/>
	邮寄地址:<textarea style="width:200px;height:50px" name="addressVo.address"><s:property value='addressVo.address'/></textarea>
</form>