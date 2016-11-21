/**
 * 
 * 2015-11-23
 * @author wangpeng
 * @category 验证码标签使用到的js
 * 
 */

// 获取验证码请求的路径
var verCodeUrl = "/user/product/memberInfo!getVerifyCode.action";
// 提示信息
var tips = {
        "mobile.format.error" : "请输入正确的手机号码",
        "email.format.error"  : "请输入正确的邮箱账号",
        "validate.success"    : "验证成功"
    };
$(function(){
    // 获取验证码
    $(".getVerCodeButton").on("click",function(){
        var $this = $(this);
        var $sendType = $this.prev().find("#sendType");
        var $sendAddress = $this.prev().find("#sendAddress");
        var $userName = $this.prev().find("#userName");
        var $resourceType = $this.prev().find("#resourceType");
        var $resource = $this.prev().find("#resource");
        var resource = $resource.val();
        if(resource == ""){
            var callback = $this.attr('resourceCallback');
            if(callback){
                resource = eval(callback);
            }
        }
        if(resource == "" || resource == null){
            return false;
        }

        if ("sms" == $sendType.val()) {
            if (!$sendAddress.checkMobile()) {
                jFail(tips["mobile.format.error"]);
                return false;
            }
        } else {
            if (!$sendAddress.checkEmail()) {
                jFail(tips["email.format.error"]);
                return false;
            }
        }
        var param = {
                "member.userName"      : $userName.val(),
                "memberVo.sendType"    : $sendType.val(),
                "memberVo.sendAddress" : $sendAddress.val(),
                "resourceType"         : $resourceType.val(),
                "resource"             : resource
            };
        $.post(verCodeUrl,param,function(data){
            if (data["bool"]) {
                $this.next("strong").removeClass("none");
                $sendAddress.attr("disabled",true);
                window.timerObj = $this;
                window.intervalTime = 300;
                window.sendType = $sendType.val();
                Timer();
            } else {
                $this.next("strong").addClass("none");
                jFail(data["message"]);
            }
        },'json');
    });
});

/**
 * 发送验证码时读秒用
 */
function Timer() {
    if (window.intervalTime <= 0) {
        window.timerObj.val("获取验证码").attr("disabled", false).attr("disabled", false);
        if (window.addreObj) {
            window.addreObj.removeAttr("disabled");
        }
        return;
    } else {
        window.timerObj.val(--window.intervalTime + "秒后重新发送验证码").attr("disabled", true);
        if (window.addreObj) {
            window.addreObj.attr("disabled","disabled");
        }
    }
    setTimeout("Timer()", 1000);
}