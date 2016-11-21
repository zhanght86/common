$(document).ready(function() {

	var checkAllObj = $("#CheckBox_CheckAll"); // 获取【全选所有记录】对象
	var checkThisPageObj = $("#CheckBox_CheckThisPage"); // 获取【全选本页】对象
	var checkDataObj = $(".CheckBox_CheckData"); // 获取本页所有数据行的复选框
	
	var pageCheckIdHidden = $("#PageCheckIdHidden").val();//页面数据复选框ID的传输数据名称
	var PageFormTag = $("#PageFormTag");//form表单
	var PageOrderByHidden = $("#PageOrderByHidden");//排序order by的输入框
	var PageOrderTypeHidden = $("#PageOrderTypeHidden");//排序order by的类型输入框
	var PageFirstLinkTag = $("#PageFirstLinkTag");//首页a标签
	var PagePreviousLinkTag = $("#PagePreviousLinkTag");//上一页a标签
	var PageNextLinkTag = $("#PageNextLinkTag");//下一页a标签
	var PageLastLinkTag = $("#PageLastLinkTag");//尾页a标签
	var PageCurrentPageTag = $("#PageCurrentPageTag");//page的当前页
	
	// 根据URL地址获取checkId的值，以数组形式返回
	function getCheckIdArray(url) {
		var checkId = null;
		var idx = url.indexOf("?");
		if (idx != -1) {
			var requestUrl = url.substr(idx + 1);
			var requestUrls = requestUrl.split("&");
			for(var i = 0; i < requestUrls.length; i++) {
				var keyValue = requestUrls[i];
				if (keyValue.length > 13 && keyValue.substr(0,13) == pageCheckIdHidden + '=') {
					checkId = decodeURIComponent(keyValue.substr(13));
				}
			}
		}
		if (checkId == null) {
			return null;
		} else {
			return checkId.split("|");
		}
	}
	
	// 根据当前页面的选择情况生成新的URL地址
	function makeNewUrl(url) {

		var newUrl = "";
		var newCheckId = "";
		
		if (checkAllObj.attr("checked")) {
			newCheckId = "ALL";
		} else {
			// 获取之前的checkId
			var oldCheckIdArray = getCheckIdArray(url);
			if(oldCheckIdArray != null) {
				for(var i = 0; i < oldCheckIdArray.length; i++) {
					var found = false;
					checkDataObj.each(function() {
						if ($(this).val() == oldCheckIdArray[i]) {
							found = true;
						}
					});
					if (oldCheckIdArray[i] == "ALL") {
						found = true;
					}
					if (!found) {
						newCheckId += oldCheckIdArray[i] + "|";
					}
				}
			}

			// 获取当前页面选择的checkId
			$(".CheckBox_CheckData:checked").each(function() {
				newCheckId += $(this).val() + "|";
			});

			if (newCheckId.length > 1) {
				newCheckId = newCheckId.substring(0,newCheckId.length - 1);
			}
		}

		// 如果以前有【page.checkId=】则替换为新的，如果没有则生成一个新的
		var regExp = new RegExp(pageCheckIdHidden + "=[0-9((%7C)|(\|)|(ALL))]+");
		var hasCheckId = url.match(regExp);
		if (hasCheckId == null) {
			if (newCheckId != "") {
				var idx = url.indexOf("?");
				if (idx != -1) {
					newUrl = url + "&" + pageCheckIdHidden + "=" + newCheckId;
				} else {
					newUrl = url + "?" + pageCheckIdHidden + "=" + newCheckId;
				}
			}
		} else {
			if (newCheckId == "") {
				newUrl = url;
				newUrl = newUrl.replace(new RegExp("&" + pageCheckIdHidden + "=[0-9((%7C)|(\|)|(ALL))]+"), "");
				newUrl = newUrl.replace(new RegExp(pageCheckIdHidden + "=[0-9((%7C)|(\|)|(ALL))]+&"), "");
				newUrl = newUrl.replace(regExp, "");
				if (newUrl.length > 1) {
					if (newUrl.substr(newUrl.length - 1) == "?") {
						newUrl = newUrl.substr(0,newUrl.length - 1);
					}
				}
			} else {
				newUrl = url.replace(regExp, pageCheckIdHidden + "=" + newCheckId);
			}
		}
		
		return newUrl;
	}
	
	
	// 替换【表单】【首页】【上一页】【下一页】【尾页】的URL地址
	function buildUrl() {
		var PageFormActionUrl = PageFormTag.attr("action");
		var PageFirstUrl = PageFirstLinkTag.attr("href");
		var PagePreviousUrl = PagePreviousLinkTag.attr("href");
		var PageNextUrl = PageNextLinkTag.attr("href");
		var PageLastUrl = PageLastLinkTag.attr("href");

		PageFormTag.attr("action",makeNewUrl(PageFormActionUrl));
		PageFirstLinkTag.attr("href",makeNewUrl(PageFirstUrl));
		PagePreviousLinkTag.attr("href",makeNewUrl(PagePreviousUrl));
		PageNextLinkTag.attr("href",makeNewUrl(PageNextUrl));
		PageLastLinkTag.attr("href",makeNewUrl(PageLastUrl));
	}

	// 绑定【全选所有记录】复选框
	checkAllObj.change(function() {
		checkAllObjChange();
	});
	
	// 【全选所有记录】复选框事件
	function checkAllObjChange() {
		if ($("#CheckBox_CheckAll:checked").length == 1) {
			checkThisPageObj.attr("checked","checked");
			checkDataObj.each(function(){
				$(this).attr("checked","checked");
			});
		} else {
			checkThisPageObj.removeAttr("checked");
			checkDataObj.each(function(){
				$(this).removeAttr("checked");
			});
		}
		buildUrl();
	}

	// 绑定全选本页复选框
	checkThisPageObj.change(function() {
		checkAllObj.removeAttr("checked");
		if ($("#CheckBox_CheckThisPage:checked").length == 1) {
			checkDataObj.each(function(){
				$(this).attr("checked","checked");
			});
		} else {
			checkDataObj.each(function(){
				$(this).removeAttr("checked");
			});
		}
		buildUrl();
	});
	
	// 绑定本页数据行复选框
	checkDataObj.change(function() {
		checkDataObjChange();
	});
	
	// 本页数据行复选框事件
	function checkDataObjChange() {
		checkAllObj.removeAttr("checked");
		allChecked = true;
		var all = checkDataObj.length;
		var temp = $(".CheckBox_CheckData:checked").length;
		allChecked = (all == temp);
		if (allChecked) {
			checkThisPageObj.attr("checked","checked");
		} else {
			checkThisPageObj.removeAttr("checked");
		}
		buildUrl();
	}
	
	// 在加载完页面后，将根据URL里的checkId来选中复选框
	var checkIdArray = getCheckIdArray(PageFormTag.attr("action"));
	if (checkIdArray != null) {
		if (checkIdArray == "ALL") {
			checkAllObj.attr("checked","checked");
			checkAllObjChange();
		} else {
			for(var i = 0; i < checkIdArray.length; i++) {
				checkDataObj.each(function() {
					if ($(this).val() == checkIdArray[i]) {
						$(this).attr("checked","checked");
					}
				});
			}
			checkDataObjChange();
		}
	}
	
	// 初始化排序图标
	var orderByName = PageOrderByHidden.val();
	var orderTpyeName = PageOrderTypeHidden.val();
	if (orderByName != "") {
		var a = $(document).find("a[name='" + orderByName + "']");
		if(a){
			var img = a.children("img");
			if(img){
				var src = img.attr("src");
				if(src){
					var path = src.substring(0,src.lastIndexOf("/")+1);
					if(orderTpyeName == "asc") {
						path += "up_sort.gif";
					} else {
						path += "down_sort.gif";
					}
					img.attr("src",path);
				}
			}
		}
	}
	$(".sort").attr("href","javascript:void(0);");
	// 排序
	$(".sort").click(function() {
		var orderType = orderTpyeName;
		var orderBy = $(this).attr("name");
		if(orderBy != orderByName) {
			orderType = "asc";
		} else {
			if(orderType == "asc") {
				orderType = "desc";
			} else {
				orderType = "asc";
			}
		}

		PageCurrentPageTag.val(1);
		PageOrderByHidden.val(orderBy);
		PageOrderTypeHidden.val(orderType);
		PageFormTag.submit();
	});
	
	//每页显示条数失去焦点提交表单
	$("#PageSizeInput").blur(function(){
		PageFormTag.submit();
	});
});

//提交到指定的url
function doCustomSubmit(url){
	var length = $("#PageFormActionURLHidden").val().length;//当前page的form表单action
	var form = $("#PageFormTag");
	var oldUrl = form.attr("action");
	oldUrl = form.attr("action").substring(length,oldUrl.length);
	form.attr("action",url + oldUrl);
	form.submit();
}