<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="${basePath }" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>圆通速递.物流</title>
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
        .yt_bg {
            background: url(images/express/yto.gif) top left no-repeat;
        }
    </style>
</s:else>

<style type="text/css">
body {
    padding:0px;
    margin:0px;
}
input {
    font:11pt Arial, Helvetica, sans-serif;
    margin:0px;
    padding:0;
    height:22px;
    height:16px;
    display:inline;
    overflow:hidden;
}
textarea {
    font:11pt Arial, Helvetica, sans-serif;
    display:inline;
    overflow:hidden;
}
.yt_bg {
    width:813px;
    height:447px;
    margin:0px;
    padding:0px;
}
.yt_l {
    width:320px;
    height:auto;
    float:left;
    margin:55px 0 0 0px;
}
.yt_jjr {
    width:95px;
    height:20px;
    float:left;
    margin-left:35px;
    display:inline;
    overflow:hidden;
}
.yt_fjd {
    width:100px;
    height:20px;
    float:left;
    margin-left:45px;
    display:inline;
    overflow:hidden;
}
.yt_dwmc {
    width:265px;
    height:20px;
    margin:12px 0 0 50px;
    float:left;
    display:inline;
    overflow:hidden;
}
.yt_dz, .yt_sjdz {
    width:305px;
    float:left;
    height:45px;
    margin:35px 0 0 0px;
}
.yt_sjdz {
    margin:45px 0 0 20px;
}
.yt_sj, .yt_dh {
    width:100px;
    height:20px;
    float:left;
    display:inline;
    overflow:hidden;
}
.yt_sj {
    margin:3px 0 0 20px;
}
.yt_dh {
    width:110px;
    margin:3px 0 0 30px;
}
.yt_pm {
    width:195px;
    height:20px;
    float:left;
    margin-top:45px;
    display:inline;
    overflow:hidden;
}
.yt_sl {
    width:115px;
    height:20px;
    float:left;
    margin:45px 0 0 10px;
    display:inline;
    overflow:hidden;
}
.yt_qm {
    width:100px;
    height:20px;
    float:left;
    margin:50px 0 0 55px;
    display:inline;
    overflow:hidden;
}
.yt_rq {
    width:150px;
    height:20px;
    float:left;
    margin:50px 0 0 210px;
    display:inline;
    overflow:hidden;
}
.yt_r {
    width:340px;
    height:auto;
    margin:80px 0 0 50px;
    float:left;
}
.yt_sjr {
    width:180px;
    height:22px;
    margin-left:80px;
    float:left;
    display:inline;
    overflow:hidden;
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
    <form action="api/common/ExpressAction!print.action?type=yto&flag=3&${vercodeAndCertime}" method="post">
        <div class="yt_bg">
            <div class="yt_l">
                <div class="yt_jjr">
                    <input name="fromAddressVo.name" type="text" style="width:90px" value="<s:property value='fromAddressVo.name'/>">
                </div>
                <div class="yt_fjd">
                    <input name="fromAddressVo.originalOffic" type="text" style="width:90px" value="<s:property value='fromAddressVo.originalOffic'/>">
                </div>
                <div class="yt_dwmc">
                    <input name="fromAddressVo.companyName" type="text" style="width:260px" value="<s:property value='fromAddressVo.companyName'/>">
                </div>
                <div class="yt_dz">
                    <textarea name="fromAddressVo.address" style="width:310px" rows="2"><s:property value='fromAddressVo.address'/></textarea>
                </div>
                <div class="yt_sj">
                    <input name="fromAddressVo.mobilePhone" type="text" style="width:100px" value="<s:property value='fromAddressVo.mobilePhone'/>">
                </div>
                <div class="yt_dh">
                    <input name="fromAddressVo.phone" type="text" style="width:110px" value="<s:property value='fromAddressVo.phone'/>">
                </div>
                <div class="yt_pm">
                    <input name="fromAddressVo.contentName" type="text" style="width:190px" value="<s:property value='fromAddressVo.contentName'/>">
                </div>
                <div class="yt_sl">
                    <input name="fromAddressVo.amount" type="text" style="width:110px" value="<s:property value='fromAddressVo.amount'/>">
                </div>
                <div class="yt_qm">
                    <input type="text" style="width:90px" value="<s:property value='fromAddressVo.name'/>">
                </div>
                <div class="yt_rq">
                    <input name="fromAddressVo.date" type="text" style="width:145px" value="<s:property value='fromAddressVo.date'/>">
                </div>
            </div>
            <div class="yt_r">
                <div class="yt_sjr">
                    <input name="toAddressVo.name" type="text" value="<s:property value='toAddressVo.name'/>">
                </div>
                <div class="yt_sjdz">
                    <textarea name="toAddressVo.address" style="width:310px" rows="2"><s:property value='toAddressVo.address'/>  <s:if test="flag != 2"><s:property value='toAddressVo.phone'/></s:if></textarea>
                </div>
            </div>
        </div>
        <s:if test="flag != 3">
        <button>打印</button>
    </s:if>
    </form>
</body>
</html>