function blockUI(){
	$.blockUI({
		message : '<br/>正在执行操作,请稍候....<br/><br/><img src="http://spihome.cdnhost.cn/images/busy.gif" /><br/>&nbsp;'
	});
}

function unblockUI(){
	$.unblockUI();
}

$(function(){
	$.unblockUI();

	$("form").submit(function(){
		blockUI();
	});
	$("#refresh").click(function(){
		blockUI();
	});
});