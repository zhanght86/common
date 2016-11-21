	
	var floatChatObj = document.getElementById("floatChat");
	floatChatObj.style.top = (window.screen.availHeight * 0.30) + "px";
	var lastScrollYChat = 0;
	
	function floatDiv() {
		var diffY = null;
		if (document.documentElement && document.documentElement.scrollTop) {
			diffY = document.documentElement.scrollTop;
		} else if (document.body) {
			diffY = document.body.scrollTop;
		}
		var percent = 0.1 * (diffY - lastScrollYChat);
		if (percent > 0) {
			percent = Math.ceil(percent);
		} else {
			percent = Math.floor(percent);
		}
		var styleTop = parseInt(document.getElementById("floatChat").style.top) + percent + "px";
		document.getElementById("floatChat").style.top = styleTop;
		lastScrollYChat = lastScrollYChat + percent;
	}

	function chatCancel() {
		document.getElementById('floatChat').style.display = 'none';
	}