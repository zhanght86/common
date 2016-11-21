<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="${basePath}" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>EMS 国内特快专递邮件详情单</title>
<script language="javascript" src="js/jquery-1.6.4.min.js"></script>
<style type="text/css">
body {margin:0; padding:0; height:auto;overflow:hidden;zoom:1;font-family:"微软雅黑",Tahoma, Geneva, sans-serif;overflow:hidden;font-size:16px;} 
input,button,select,textarea{outline:none;font-size:18px;resize:none;}
input{font-family:"微软雅黑",Tahoma, Geneva, sans-serif;overflow:hidden;}
.ems_01 input{width:100px;height:18px;padding-top:5px;position:absolute;top:45px;left:0px;background:none;border:0px;}
.ems_02 input{width:160px;height:18px;padding-top:5px;position:absolute;top:45px;left:130px;background:none;border:0px;}
.ems_03 input{width:300px;height:22px;padding-top:5px;position:absolute;top:90px;left:0px;background:none;border:0px;}
.ems_04 textarea{width:360px;height:36px;padding-top:0px;position:absolute;top:122px;left:0px;background:none;border:0px;}
.ems_05 input{width:100px;height:18px;padding-top:5px;position:absolute;top:200px;left:0px;background:none;border:0px;}
.ems_06 input{width:160px;height:18px;padding-top:5px;position:absolute;top:200px;left:130px;background:none;border:0px;}
.ems_07 input{width:165px;height:18px;padding-top:5px;position:absolute;top:230px;left:0px;background:none;border:0px;}
.ems_08 textarea{width:360px;height:46px;padding-top:0px;position:absolute;top:280px;left:0px;background:none;border:0px;}
.ems_bottom{width:876px;height:40px;margin:20px 0px;text-align:center;}
.ems_bottom button{width:70px;height:26px}
</style>

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
    .ems_box{width:876px;height:486px;overflow:hidden;background:url(images/express/ems.gif) no-repeat;margin:10px 0px;position:relative;}
    </style>
</s:else>

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
    <form action="api/common/ExpressAction!print.action?type=ems&flag=3&${vercodeAndCertime}" method="post">
        <div class="ems_box">
            <div class="ems_01">
                <input name="fromAddressVo.name" type="text" size="7" value="<s:property value='fromAddressVo.name'/>"/>
            </div>
            <div class="ems_02">
                <input name="fromAddressVo.mobilePhone" type="text" size="15" value="<s:property value='fromAddressVo.mobilePhone'/>">
            </div>
            <div class="ems_03">
                <input name="fromAddressVo.companyName" type="text" size="44" value="<s:property value='fromAddressVo.companyName'/>">
            </div>
            <div class="ems_04">
                <textarea name="fromAddressVo.address" cols="38" rows="3"><s:property value='fromAddressVo.address'/>  <s:if test="flag != 2"><s:property value='fromAddressVo.phone'/></s:if></textarea>
            </div>
            <div class="ems_05">
                <input type="text" name="toAddressVo.name" size="10" value="<s:property value='toAddressVo.name'/>">
            </div>
            <div class="ems_06">
                <input type="text" name="toAddressVo.phone" value="<s:property value='toAddressVo.phone'/>" />
            </div>
            <div class="ems_07">
                <input type="text" name="toAddressVo.companyName" value="<s:property value='toAddressVo.companyName'/>" />
            </div>
            <div class="ems_08">
                <textarea name="toAddressVo.address" cols="38" rows="3"><s:property value='toAddressVo.address'/></textarea>
            </div>
        </div>
        <div class="ems_bottom">
            <s:if test="flag != 3">
                <button>打印</button>
            </s:if>
        </div>
    </form>
</body>
</html>