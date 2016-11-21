<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="${basePath }" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="javascript" src="js/jquery-1.6.4.min.js"></script>
<title>普通平信</title>
<s:if test="flag == 3">
	<script language="javascript">
		$(function() {
			window.print();
			var browserName = navigator.appName;
			if (browserName == "Netscape") {
				window.open('', '_parent', '');
				window.close();
			} else if (browserName == "Microsoft Internet Explorer") {
				window.opener = null;
				window.close();
			}
		});
	</script>
	<style type="text/css">
input,textarea {
	font: 13pt Arial, Helvetica, sans-serif;
	background: none;
	border: none;
}
</style>
</s:if>
<s:else>
	<style type="text/css">
.xf_bg {
	background: url(images/express/xf.gif) top left no-repeat;
}
</style>
</s:else>
<style type="text/css">
body {
	padding: 0px;
	margin: 0px;
}

.xf_bg {
	width: 843px;
	height: 385px;
	margin: 0px;
	padding: 0px;
}

.xf_dz {
	width: 345px;
	height: 135px;
	float: left;
	margin: 82px 0 0 45px;
	display: inline;
	overflow: hidden;
}

textarea {
	width: 335px;
	height: 80px;
	margin: 0px;
	background: none;
	border: none;
	display: inline;
	overflow: hidden;
	background: none;
	text-align: center;
}

.name {
	width: 320px;
	height: 25px;
	padding-right: 15px;
	text-align: center;
	float: right;
}

.gs_dz {
	width: 260px;
	height: 90px;
	float: left;
	margin: 60px 0 0 500px;
	display: inline;
	background: #fff;
}

.gs_dz ul {
	margin: 0px;
	padding: 0px;
}

.gs_dz ul li {
	font: 12px/18px "宋体";
	text-align: left;
	list-style-type: none;
}
</style>
<script language="javascript">
	$(function() {
		$("button").click(function() {
			if (confirm("确定打印吗?")) {
				$("form").submit();
			}
		});
	});
</script>

</head>
<body>
	<form action="api/common/ExpressAction!print.action?type=ordinary&flag=3&${vercodeAndCertime}" method="post">
		<div class="xf_bg">
			<div class="xf_dz">
				<input name="toAddressVo.post" value="<s:property value='toAddressVo.post'/>"><br />
				<textarea class="address" name="toAddressVo.address"><s:property value='toAddressVo.address'/></textarea>
				<input class="name" name="toAddressVo.name" value="<s:property value='toAddressVo.name'/>">
			</div>
			<div class="gs_dz">
				<ul>
					<li>地址：成都市高新区天府三街218号峰汇中心2栋-1506</li>
					<li>电话：028-82001809</li>
					<li>传真：028-61554799</li>
					<li>邮编：610041</li>
					<li>E-mail：service@51web.com</li>
				</ul>
			</div>
		</div>
		<s:if test="flag != 3">
			<button>打印</button>
		</s:if>
	</form>
</body>
</html>