<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Enumeration" %>
<%@ page import="java.util.List" %>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.util.regex.Pattern" %>
<%@ page import="java.util.regex.Matcher" %>
<%@ page import="com.sjdf.platform.dictionary.cache.ConfigManager" %>
<%@ page import="com.sjdf.platform.dictionary.bean.Dictionary" %>
<%@ page import="com.sjdf.platform.dictionary.bean.Notify" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!-- 通知（开始） -->
<%
	StringBuilder js = new StringBuilder();
	StringBuilder html = new StringBuilder();
	ConfigManager configManager = ConfigManager.getInstance();
	List<? extends Dictionary> notifyList = configManager.getDictionary(Notify.class);
	for (Dictionary dictionary : notifyList) {
		String func = null;
		String api = null;
		String url = null;
		String value = dictionary.getValue();
		String[] values = value.split("\\|");
		for (String v : values) {
			if (v.startsWith("FUNC:")) {
				func = v.substring(5, v.length());
			}
			if (v.startsWith("API:")) {
				api = v.substring(4, v.length());
			}
			if (v.startsWith("URL:")) {
				url = v.substring(4, v.length());
			}
		}

		Pattern pattern = Pattern.compile("\\$\\{session\\.([a-z0-9A-Z-_]+)\\}");
		Matcher m = pattern.matcher(api);
		while (m.find()) {
			String attr = "";
			Object sessionAttr = session.getAttribute(m.group(1));
			if (sessionAttr != null) {
				attr = URLEncoder.encode(sessionAttr.toString(), "utf-8");
			}
			api = api.replace(m.group(), attr);
			url = url.replace(m.group(), attr);
		}
		
		out.println("<script type=\"text/javascript\" src=\""+api+"\"></script>");
		html.append("<div id=\\\"div_");
		html.append(func);
		html.append("\\\" style=\\\"display:none;\\\"><a href=\\\"");
		html.append(url);
		html.append("\\\" target=\\\"_blank\\\"><font color=\\\"#FF0000\\\">");
		html.append(dictionary.getName());
		html.append("</font></a></font></div>");
		
		js.append("if (");
		js.append(func);
		js.append("()) {document.getElementById('floatChat').style.display = '';document.getElementById('div_");
		js.append(func);
		js.append("').style.display = '';}\r\n");
	}
%>
<script type="text/javascript">
	var divHtml = "<div style=\"display:none;POSITION: absolute;right: 3%; top:800px;z-index:999;\" id=\"floatChat\">";
		divHtml += "<div style=\"width:200px;border:#ff9933 1px solid; background-color:#ffffe5; padding:10px; line-height:20px; font-size:12px; height:100%; text-align:left;\">";
		divHtml += "<font color=\"#009900\" onClick=\"chatCancel()\" style=\"cursor:pointer; float:right;\">[关闭]</font><br />";
		divHtml += "<%=html%>";
		divHtml += "</div></div>";
	document.write(divHtml);
	var floatChatObj = document.getElementById("floatChat");
	floatChatObj.style.top = (window.screen.availHeight * 0.30) + "px";
	lastScrollYChat = 0;
	function floatDiv() {
		var diffY = null;
		if (document.documentElement && document.documentElement.scrollTop) {
			diffY = document.documentElement.scrollTop;
		} else if (document.body) {
			diffY = document.body.scrollTop;
		}
		percent = .1 * (diffY - lastScrollYChat);
		if (percent > 0) {
			percent = Math.ceil(percent);
		} else {
			percent = Math.floor(percent);
		}
		document.getElementById("floatChat").style.top = parseInt(document.getElementById("floatChat").style.top) + percent + "px";
		lastScrollYChat = lastScrollYChat + percent;
	}

	function chatCancel() {
		document.getElementById('floatChat').style.display = 'none';
	}

	<%=js%>
</script>
<!-- 通知（结束） -->