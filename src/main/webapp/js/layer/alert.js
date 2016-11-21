/**
 * 成功提示信息弹出框
 */
function jSuccess(msg, callback) {
    var al = layer.open({
        icon:1,
        closeBtn: false,
        title: "成功提示信息",
        content:msg,
        shade: [0.5, '#000', true],
        area: ['310px', 'auto'],
        border: [5, 0.3, '#000', true],
        btn: ['确定'],
        yes: function (res) {
            layer.close(al);
            if (callback) {
                callback(res);
            }
        }
    });
}

/**
 * 失败提示信息弹出框
 */
function jFail(msg, callback) {
    var al = layer.open({
        closeBtn: false,
        title: "失败提示信息",
        shade: [0.5, '#000', true],
        area: ['310px', 'auto'],
        border: [5, 0.3, '#000', true],
        content: msg,
        icon: 2,
        btn: ['确定'],
        yes: function (res) {
            layer.close(al);
            if (callback) {
                callback(res);
            }
        }
    });
}

/**
 * 警告信息弹出框
 */
function jWarn(msg, callback) {
    var al = layer.open({
        closeBtn: false,
        title: "警告信息",
        shade: [0.5, '#000', true],
        area: ['310px', 'auto'],
        border: [5, 0.3, '#000', true],
        content: msg,
        icon: 0,
        btn: ['确定'],
        yes: function (res) {
            layer.close(al);
            if (callback) {
                callback(res);
            }
        }
    });
}

/**
 *弹出框
 */
function jAlert(successMsg, failMsg, warnMsg, callback) {
    if (successMsg != null && successMsg != '') {
        jSuccess(successMsg, callback);
    } else if (failMsg != null && failMsg != '') {
        jFail(failMsg, callback);
    } else if (warnMsg != null && warnMsg != '') {
        jWarn(warnMsg, callback);
    }
}

/**
 * 确认框
 */
function jConfirm(msg, yesCallback, noCallback) {
    var al = layer.open({
        closeBtn: false,
        title: "确认框",
        shade: [0.5, '#000', true],
        area: ['310px', 'auto'],
        border: [5, 0.3, '#000', true],
        content: msg,
        icon: 3,
        btn: ['确定', '取消'],
        yes: function (res) {
            layer.close(al);
            if (yesCallback) {
                yesCallback(res);
            }
        },
        no: function (res) {
            layer.close(al);
            if (noCallback) {
                noCallback(res);
            }
        }
    });
}

/**
 * 确认框
 */
function jConfirm1(msg, yesButton, yesCallback, noCallback) {
    var al = layer.open({
        closeBtn: false,
        title: "确认框",
        shade: [0.5, '#000', true],
        area: ['310px', 'auto'],
        border: [5, 0.3, '#000', true],
        content: msg,
        icon: 3,
        btn: [yesButton, '取消'],
        yes: function (res) {
            layer.close(al);
            if (yesCallback) {
                yesCallback(res);
            }
        },
        no: function (res) {
            layer.close(al);
            if (noCallback) {
                noCallback(res);
            }
        }
    });
}

function setHeight(msg) {
    if (msg) {
        var line = Math.ceil(msg.replace(/[^\x00-\xff]/g, "00").length / 32);
        $('.xubox_main').height(line * 26 + 100);
    }
    $('.xubox_border').height("auto").width("auto");
}

/**字符串转换*/
function jStrConvert(msg) {
    return $('<div></div>').html(msg).text();
}

/**无符号整数验证*/
function jValidateUnsignedInteger() {
    var str = "";
    if(arguments.length > 0){
        str = arguments[0];
    }
    var bool = false;
    if(arguments.length > 1){
        bool = arguments[1];
    }

    if (bool && (!str || str == null || str == "")) {
        return false;
    }

    if (str && str != null && str != "") {
        if (!/^\d*$/.test(str)) {
            return false;
        }
    }
    return true;
}

/**无符号整数验证*/
function jValidateUnsignedLong() {
    var str = "";
    if(arguments.length > 0){
        str = arguments[0];
    }
    var bool = false;
    if(arguments.length > 1){
        bool = arguments[1];
    }

    if (bool && (!str || str == null || str == "")) {
        return false;
    }

    if (str && str != null && str != "") {
        if (!/^\d{1,9}$/.test(str)) {
            return false;
        }
    }
    return true;
}
