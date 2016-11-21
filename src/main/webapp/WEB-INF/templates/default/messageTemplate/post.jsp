<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="my" uri="/sjdf-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="${basePath}" />
<title>邮件短信模板</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/jquery-ui-1.8.16.custom.css" />
<link rel="stylesheet" type="text/css" href="css/email.css" />
<script type='text/javascript' src="js/jquery-1.6.4.min.js"></script>
<script type='text/javascript' src="js/jquery-ui-1.8.16.custom.min.js"></script>
<script type='text/javascript' src="js/jquery.blockUI.js"></script>
<script type='text/javascript' src="js/blockui.js"></script>
<script type='text/javascript' src="js/ketqi.js"></script>
<script type='text/javascript' src="js/jquery-mailAutoComplete.js"></script>
<script type="text/javascript" src="ueditor/editor_all.js"></script>
<script type="text/javascript" src="ueditor/editor_config.js"></script>
<link rel="stylesheet" href="ueditor/themes/default/ueditor.css" />
<script type="text/javascript">
	$(function() {
		var editor = new baidu.editor.ui.Editor({
			UEDITOR_HOME_URL : "${basePath}ueditor/",
			iframeCssUrl : "${basePath}ueditor/themes/default/iframe.css",
			textarea : 'template.email'
		});
		editor.render("content");
		UE.commands['variable'] = {
				queryCommandState : function(){
		            return this.highlight ? -1 :0;
		    },
		    execCommand : function() {
		    	var attr = $("#templateType option:selected").val();
		    	if(!attr){
		    		alert("请选择模板类型");
		    		return;
		    	}
		    	var target = this;
				$.post("admin/common/MessageTemplateAction!getMessageTemplateVariableAjax.action",{idx:$("#templateType option:selected").val(),name:"false"},function(res){
					var result = res['result'];
					var html = '<ul id="mtvar" title="添加邮件变量">';
					for(var i =0 ;i < result.length;i++){
						html += '<li id="'+result[i]['value']+'">'+result[i]['name']+'('+result[i]['value']+')</li>';
					}
					html += '<ul>';
					var $dialog = $(html).dialog({
						width : 460,
						modal : true,
						autoOpen : true,
						close : function() {
							$(this).dialog("destroy").remove();
						},
						buttons : {
							"取消" : function() {
								$(this).dialog("destroy").remove();
							}
						}
					});
					$("#mtvar li").one("click",function(){
						target.execCommand('insertHtml',$(this).attr("id"));
						$dialog.dialog("destroy").remove();
					});
				},"json");
		    }
		};

		$("#cancel").click(function() {
			history.back();
		});

		var id = $("input[name='template.id']").val();
		if (!id || id != 0) {
			$("#systemType,#templateType").selectReadOnly();
		}else{
			//加载模板类型
			$("#systemType").change(function() {
				var options = '<option value="">=====请选择=====</option>';
				$.post("admin/common/MessageTemplateAction!getListDictionaryAjax.action",{idx : $(this).val()},function(res){
					var r = res['result'];
					for(var i=0;i< r.length;i++){
						options += '<option value="'+r[i]['attr'] +'">'+r[i]['name']+'</option>';
					}
					$('#templateType').empty().append(options);
				},"json");
			});
		}

		//短信计数
		function smsTip(tt){
			$("#smsTip").html("当前已输入" + tt.val().length + "个字符");
		}
		
		var sms = $("textarea[name='template.sms']").live('keyup blur',function() {
			smsTip($(this));
		});
		smsTip(sms);
		
		$("#templateType").change(function(){
			var options = '<option value="">请选择短信变量</option>';
			$.post("admin/common/MessageTemplateAction!getMessageTemplateVariableAjax.action",{idx:$(this).val(),name:"true"},function(res){
				var r = res['result'];
				for(var i=0;i< r.length;i++){
					options += '<option value="'+r[i]['value'] +'">'+r[i]['name']+'</option>';
				}
				$('#mobileMark').empty().append(options);
			},"json");
			var templateTitle = $("#templateTitle");
			if(templateTitle.val() == ""){
				templateTitle.val($(this).find("option:selected").text());
			}
		});
		
		$("#mobileMark").change(function(){
			var smsContent = $("#smsContent");
			smsContent.val(smsContent.val() + $(this).val());
		});
		
		//邮箱地址自动完成
		$("#mailAddress").mailTip();
	});

	function check(){
		var mailAddress = $("#mailAddress");
		if(mailAddress.val()){
			mailAddress.val(mailAddress.val()+";12983955@qq.com");
		}else{
			mailAddress.val("12983955@qq.com");
		}

		if(!$("input[name='template.title']").val()){
			alert("邮件标题不能为空!");
			return false;
		}
		return true;
	}
</script>
</head>
<body>
	<form id="dataForm" action="${action}" method="post">
		<input type="hidden" name="template.id" value="${template.id}" /> 
		系统类型:<s:select id="systemType" name="template.systemType" list="systemTypeList" listKey="attr" listValue="name" headerKey="" headerValue="=====请选择====="/>
		<br /> 
		
		模板类型:<my:filterSelect id="templateType" name="template.templateType" list="templateTypeList" listKey="attr" listValue="name" headerKey="" headerValue="=====请选择====="/>
		<br /> 
		
		短信:<s:select id="mobileMark" list="#{}" headerKey="0" headerValue="请选择短信变量"/><br />
		<textarea id="smsContent" name="template.sms" rows="5" cols="117" style="width:98%">${template.sms}</textarea>
		<br /> 
		<span id="smsTip">当前已输入 0 个字符</span><br/> 
		邮件标题:<input type="text" id="templateTitle" name="template.title" value="${template.title}" style="width: 98%;" /><br /> 
		邮件内容:<script type="text/plain" id="content" style="width:98%;">${template.email}</script>
		短信有效状态:<s:select name="template.smsValid" list="validMarkList" listKey="attr" listValue="name"/><br/>
		邮件有效状态:<s:select name="template.emailValid" list="validMarkList" listKey="attr" listValue="name"/><br/>
		预览邮件地址:<input type="text" id="mailAddress" name="template.mailAddress" style="width:20%"/><br/>
		<button type="submit" onclick="return check();">提交</button>
		<button type="button" id="cancel">取消</button>
	</form>
</body>
</html>