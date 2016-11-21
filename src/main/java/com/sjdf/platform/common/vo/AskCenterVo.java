package com.sjdf.platform.common.vo;

/**
 * Create at 2012-09-07
 * 咨询反馈Vo
 *
 * @author ketqi
 */
public class AskCenterVo {
    //产品名称
    private String name;
    //订单号,标志该问题管理的产品;域名产品:域名名称
    private String orderNum;
    //问题大类
    private long askClass;
    //用户编号
    private long userId;
    //用户名称
    private String username;
    //登陆成功后跳转到哪个功能
    private String functionName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public long getAskClass() {
        return askClass;
    }

    public void setAskClass(long askClass) {
        this.askClass = askClass;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    @Override
    public String toString() {
        return "AskCenterVo [name=" + name + ", orderNum=" + orderNum + ", askClass=" + askClass + ", userId=" + userId + ", username=" + username + ",functionName=" + functionName + "]";
    }
}
