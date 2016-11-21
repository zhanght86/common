<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<form id="dataForm" method="post" title="地理位置信息编辑">
	<input type="hidden" name="location.id" value="${location.id}" /> 
	省份:<s:select id="province" name="location.province.code" list="provinceList" listKey="code" listValue="cnName" headerKey="" headerValue="======请选择省份======"/><br />
	城市:<s:select id="city" name="location.city.code" list="cityList" listKey="code" listValue="cnName" headerKey="" headerValue="======请选择城市======"/><br />
	代码:<input type="text" name="location.code" value="${location.code}"/><br /> 
	中文名称:<input type="text" name="location.cnName" value="${location.cnName}"/><br />
	英文名称:<input type="text" name="location.enName" value="${location.enName}"/><br />
	简称:<input type="text" name="location.abbreviation" value="${location.abbreviation}"/><br /> 
	邮编:<input type="text" name="location.postal" value="${location.postal}"/><br /> 
	区号:<input type="text" name="location.areaCode" value="${location.areaCode}"/><br /> 
</form>