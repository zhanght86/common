package com.sjdf.platform.log.vo;

import com.sjdf.platform.common.utils.DateUtils;
import com.sjdf.platform.common.utils.PlatformUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 日志搜索vo
 *
 * @author 王正伟
 */
public class LogVo {
    // 系统类型
    private long systemType;
    // 操作人(登录账号)
    private String loginUser;
    // 操作人中文姓名（单位名称）
    private String company;
    // 操作人ip地址
    private String ipAddress;
    // 子系统类别：自动任务，维护工具，控制面板，管理中心，业务后台，API（对外访问）
    private long subsystemType;
    // 操作目标所属分类（功能大类）;虚拟主机;数据库;邮局;域名;VPS;IDC;等等
    private long functionClass;
    // 功能名称（功能小类）:1.购买；2.续费；3.升级;等等
    private long functionType;
    // 操作类别(操作动作);1:添加,2:修改;3:删除;4:其他
    private long operatorAction;
    // 操作目标即资源（资源id）
    private String resourceId;
    // 操作过程 (操作内容)
    private String operationalContent;
    // 操作后的结果信息（错误原因）
    private String errorInfo;
    // 日志类型;1:操作日志;2:错误(异常)日志
    private long logType;
    // 开始时间
    private Date beginTime;
    // 结束时间
    private Date endTime;
    //功能名称（功能小类）功能小分类列表
    private List<Long> functionTypeList;
    //操作目标即资源（资源id）列表
    private List<String> resourceIdList;

    public Map<String, String> wrapPostData() {
        return wrapPostData("log");
    }

    public Map<String, String> wrapPostData(String prefix) {
        String logPrefix = prefix;
        if (PlatformUtils.hasText(prefix)) {
            logPrefix = logPrefix + ".";
        } else {
            logPrefix = "";
        }

        Map<String, String> postData = new HashMap<>();
        // 系统类型
        if (systemType > 0) {
            postData.put(logPrefix + "systemType", String.valueOf(systemType));
        }
        // 操作人(登录账号)
        if (PlatformUtils.hasText(loginUser)) {
            postData.put(logPrefix + "loginUser", loginUser);
        }
        // 操作人中文姓名（单位名称）
        if (PlatformUtils.hasText(company)) {
            postData.put(logPrefix + "company", company);
        }
        // 操作人ip地址
        if (PlatformUtils.hasText(ipAddress)) {
            postData.put(logPrefix + "ipAddress", ipAddress);
        }
        // 子系统类别：自动任务，维护工具，控制面板，管理中心，业务后台，API（对外访问）
        if (subsystemType > 0) {
            postData.put(logPrefix + "subsystemType", String.valueOf(subsystemType));
        }
        // 操作目标所属分类（功能大类）;虚拟主机;数据库;邮局;域名;VPS;IDC;等等
        if (functionClass > 0) {
            postData.put(logPrefix + "functionClass", String.valueOf(functionClass));
        }
        // 功能名称（功能小类）:1.购买；2.续费；3.升级;等等
        if (functionType > 0) {
            postData.put(logPrefix + "functionType", String.valueOf(functionType));
        }
        // 操作类别(操作动作);1:添加,2:修改;3:删除;4:其他
        if (operatorAction > 0) {
            postData.put(logPrefix + "operatorAction", String.valueOf(operatorAction));
        }
        // 操作目标即资源（资源id）
        if (PlatformUtils.hasText(resourceId)) {
            postData.put(logPrefix + "resourceId", resourceId);
        }
        // 操作过程 (操作内容)
        if (PlatformUtils.hasText(operationalContent)) {
            postData.put(logPrefix + "operationalContent", operationalContent);
        }
        // 操作后的结果信息（错误原因）
        if (PlatformUtils.hasText(errorInfo)) {
            postData.put(logPrefix + "errorInfo", errorInfo);
        }
        // 日志类型;1:操作日志;2:错误(异常)日志
        if (logType > 0) {
            postData.put(logPrefix + "logType", String.valueOf(logType));
        }
        // 开始时间
        if (beginTime != null) {
            postData.put(logPrefix + "beginTime", DateUtils.formatDateTime(beginTime));
        }
        // 结束时间
        if (endTime != null) {
            postData.put(logPrefix + "endTime", DateUtils.formatDateTime(endTime));
        }
        //功能名称（功能小类）功能小分类列表
        if (functionTypeList != null && !functionTypeList.isEmpty()) {
            for (int i = 0; i < functionTypeList.size(); i++) {
                postData.put(logPrefix + "functionTypeList[" + i + "]", String.valueOf(functionTypeList.get(i)));
            }
        }
        //操作目标即资源（资源id）列表
        if (resourceIdList != null && !resourceIdList.isEmpty()) {
            for (int i = 0; i < resourceIdList.size(); i++) {
                postData.put(logPrefix + "resourceIdList[" + i + "]", resourceIdList.get(i));
            }
        }
        return postData;
    }


    public long getSystemType() {
        return systemType;
    }

    public void setSystemType(long systemType) {
        this.systemType = systemType;
    }

    public String getLoginUser() {
        return loginUser;
    }

    public void setLoginUser(String loginUser) {
        this.loginUser = loginUser;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public long getSubsystemType() {
        return subsystemType;
    }

    public void setSubsystemType(long subsystemType) {
        this.subsystemType = subsystemType;
    }

    public long getFunctionClass() {
        return functionClass;
    }

    public void setFunctionClass(long functionClass) {
        this.functionClass = functionClass;
    }

    public long getFunctionType() {
        return functionType;
    }

    public void setFunctionType(long functionType) {
        this.functionType = functionType;
    }

    public long getOperatorAction() {
        return operatorAction;
    }

    public void setOperatorAction(long operatorAction) {
        this.operatorAction = operatorAction;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public String getOperationalContent() {
        return operationalContent;
    }

    public void setOperationalContent(String operationalContent) {
        this.operationalContent = operationalContent;
    }

    public String getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(String errorInfo) {
        this.errorInfo = errorInfo;
    }

    public long getLogType() {
        return logType;
    }

    public void setLogType(long logType) {
        this.logType = logType;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public List<Long> getFunctionTypeList() {
        return functionTypeList;
    }

    public void setFunctionTypeList(List<Long> functionTypeList) {
        this.functionTypeList = functionTypeList;
    }

    public List<String> getResourceIdList() {
        return resourceIdList;
    }

    public void setResourceIdList(List<String> resourceIdList) {
        this.resourceIdList = resourceIdList;
    }
}
