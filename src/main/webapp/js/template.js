function loadTemplate(idx, callback) {
	$.post("other/common/platform/DataTemplateAction!get.action", {idx: idx}, function (re) {
		if (re["bool"]) {
			if (callback && typeof(callback) === "function") {
				callback(re["result"]);
			}
		} else {
			alert(re["message"]);
		}
	},"json");
}