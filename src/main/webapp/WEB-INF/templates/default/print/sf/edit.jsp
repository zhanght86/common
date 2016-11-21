<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="${basePath }" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>顺丰速运</title>
<script language="javascript" src="js/jquery-1.6.4.min.js"></script>

<s:if test="flag == 3">
    <style type="text/css">
        input,textarea {
            background: none;
            border: none;
        }
    </style>
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
</s:if>
<s:else>
    <style type="text/css">
        .sf_bg {
            background: url(images/express/sf.jpg) top left no-repeat;
        }
    </style>
</s:else>

<style type="text/css">
body {
    padding: 0px;
    margin: 0px;
}

input {
    font: 12pt Arial, Helvetica, sans-serif;
    margin: 0px;
    padding: 0;
    height: 16px;
    display: inline;
    overflow: hidden;
}

textarea {
    font: 12pt Arial, Helvetica, sans-serif;
    text-align: center;
    display: inline;
    overflow: hidden;
}

.sf_bg {
    width: 760px;
    height: 490px;
    margin: 0px;
    padding: 0px;
}

.sf_l {
    width: 400px;
    height: auto;
    float: left;
    margin: 90px 0 0 50px;
}

.sf_gs {
    width: 220px;
    height: 22px;
    float: left;
}

.sf_llr {
    width: 55px;
    float: left;
    margin-left: 42px;
    display: inline;
    overflow: hidden;
}

.sf_dz {
    width: 290px;
    height: 55px;
    margin-top: 14px;
    margin-left: 6px;
    float: left;
}

.sf_sjdz {
    width: 290px;
    height: 55px;
    margin-top: 10px;
    margin-left: 6px;
    float: left;
}

.sf_sjr {
    width: 125px;
    height: 22px;
    float: left;
    margin: 0 0 0 290px;
    display: inline;
    overflow: hidden;
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
    <form action="api/common/ExpressAction!print.action?type=sf&flag=3&${vercodeAndCertime}" method="post">
        <div class="sf_bg">
            <div class="sf_l">
                <div class="sf_gs">
                    <input name="fromAddressVo.companyName" type="text" style="width: 224px" value="<s:property value='fromAddressVo.companyName'/>">
                </div>
                <div class="sf_llr">
                    <input name="fromAddressVo.name" type="text" style="width: 50px" value="<s:property value='fromAddressVo.name'/>">
                </div>
                <div class="sf_dz">
                    <textarea name="fromAddressVo.address" style="width: 285px; height: 55px"><s:property value='fromAddressVo.address'/>  <s:if test="flag != 2"><s:property value='fromAddressVo.mobilePhone'/>      <s:property value='fromAddressVo.phone'/></s:if></textarea>
                </div>
                <div class="sf_sjr">
                    <input name="toAddressVo.name" type="text" style="width:120px" value="<s:property value='toAddressVo.name'/>">
                </div>
                <div class="sf_sjdz">
                    <textarea name="toAddressVo.address" style="width: 285px; height: 55px"><s:property value='toAddressVo.address'/>  <s:if test="flag != 2"><s:property value='toAddressVo.phone'/></s:if></textarea>
                </div>
            </div>
        </div>
        <s:if test="flag != 3">
            <button>打印</button>
        </s:if>
    </form>
</body>
</html>