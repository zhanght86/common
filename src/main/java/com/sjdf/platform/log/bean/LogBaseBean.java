package com.sjdf.platform.log.bean;

import com.sjdf.platform.CommonPlatformConstant;
import com.sjdf.platform.common.bean.BaseBean;
import com.sjdf.platform.common.helper.ResourceBundleHelper;
import com.sjdf.platform.common.utils.DateUtils;
import com.sjdf.platform.common.utils.Page;
import com.sjdf.platform.common.utils.PlatformUtils;
import com.sjdf.platform.dictionary.bean.*;
import com.sjdf.platform.dictionary.cache.ConfigManager;
import com.sjdf.platform.log.logger.SJDFLogger;
import com.sjdf.platform.log.logger.SJDFLoggerFactory;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.hibernate.annotations.Index;

import javax.persistence.*;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 日志
 * User: ketqi
 * Date: 2015-09-16 16:37
 */
@MappedSuperclass
public class LogBaseBean extends BaseBean implements Cloneable {
    private static final long serialVersionUID = 2727375707363573239L;
    protected static final SJDFLogger LOGGER = SJDFLoggerFactory.getSJDFLogger(LogBaseBean.class);

    /**
     * 日志公共信息（系统id，登陆者，ip，公司名）
     */
    @Embedded
    private LogUser logUser;
    /**
     * 子系统类别：自动任务，维护工具，控制面板，管理中心，业务后台，API（对外访问）
     *
     * @see com.sjdf.platform.dictionary.bean.SubsystemType
     */
    @Index(name = "subsystemTypeIndex")
    private long subsystemType;
    /**
     * 操作目标所属分类（功能大类）;虚拟主机;数据库;邮局;域名;VPS;IDC;等等
     *
     * @see com.sjdf.platform.dictionary.bean.FunctionClass
     */
    @Index(name = "functionClassIndex")
    private long functionClass;
    /**
     * 功能名称（功能小类）:1.购买；2.续费；3.升级;等等
     *
     * @see com.sjdf.platform.dictionary.bean.FunctionType
     */
    @Index(name = "functionTypeIndex")
    private long functionType;
    /**
     * 操作类别(操作动作);1:添加,2:修改;3:删除;4:其他
     *
     * @see com.sjdf.platform.dictionary.bean.OperatorAction
     */
    @Index(name = "operatorActionIndex")
    private long operatorAction;
    /** 操作目标即资源（资源id） */
    @Index(name = "resourceIdIndex")
    private String resourceId;
    /** 操作过程 （操作内容） */
    @Lob
    @Column(columnDefinition = "longtext")
    private String operationalContent;
    /** 操作后的结果信息（错误原因） */
    @Lob
    @Column(columnDefinition = "longtext")
    private String errorInfo;
    /**
     * 日志类型;1:操作日志;2:错误(异常)日志
     *
     * @see com.sjdf.platform.dictionary.bean.LogType
     */
    @Index(name = "logTypeIndex")
    private long logType;
    /**
     * 显示类型;0：默认，1：管理中心,2:管理中心和控制面板
     *
     * @see com.sjdf.platform.dictionary.bean.LogShowType
     */
    @Index(name = "showTypeIndex")
    private long showType;
    /** 处理人 */
    private String handler;
    /** 源产品id */
    private long sourceProductId;
    /** 源服务器id */
    private long sourceServerId;
    /** 目标服务器id */
    private long targetServerId;
    /** 源产品名称 */
    private String srcProductName;
    /** 目标产品名称 */
    private String targetProductName;
    /**
     * 状态
     *
     * @see com.sjdf.platform.dictionary.bean.ExecuteStatus
     */
    @Column(name = "l_status")
    private long status;
    /**
     * 是否短信邮件通知
     *
     * @see com.sjdf.platform.dictionary.bean.WhetherState
     */
    private long notify;
    /**
     * IP类型(独立IP, 共享IP)
     *
     * @see com.sjdf.platform.dictionary.bean.ChostIPType
     */
    private long ipType;
    /**
     * 类型(自动, 手动)
     *
     * @see com.sjdf.platform.dictionary.bean.OperatorType
     */
    private long type;
    /**
     * 步骤
     *
     * @see com.sjdf.platform.dictionary.bean.ExecuteStep
     */
    private long step;
    /** 计数器 */
    private int counter;
    /**
     * 是否状态
     *
     * @see com.sjdf.platform.dictionary.bean.WhetherState
     */
    private long whether;
    /** 消息 */
    private String message;

    @Transient
    private transient boolean isDBLog = true;
    /**
     * 临时操作内容
     */
    @Transient
    private transient String tmpOperationalContent;
    /**
     * 临时错误原因
     */
    @Transient
    private transient String tmpErrorInfo;

    public LogBaseBean() {
    }

    public LogBaseBean(boolean isDBLog) {
        this.isDBLog = isDBLog;
    }

    public LogBaseBean(long subsystemType, String resourceId, String operationalContent, String errorInfo) {
        this.subsystemType = subsystemType;
        this.resourceId = resourceId;
        this.operationalContent = operationalContent;
        this.errorInfo = errorInfo;
    }

    public String setOperateContent(String superOperateInfo, String memberOperateInfo, String userOperateInfo) {
        this.operationalContent = wrapOperateContent(superOperateInfo, memberOperateInfo, userOperateInfo);
        return this.operationalContent;
    }

    public String getSuperOperateContent() {
        return getOperateContent(LogLevel.SUPER_LEVEL, operationalContent);
    }

    public String getMemberOperateContent() {
        return getOperateContent(LogLevel.MEMBER_LEVEL, operationalContent);
    }

    public String getUserOperateContent() {
        return getOperateContent(LogLevel.USER_LEVEL, operationalContent);
    }

    public boolean isDBLog() {
        return isDBLog;
    }

    public void setDBLog(boolean isDBLog) {
        this.isDBLog = isDBLog;
    }

    public String getFunctionClassInfo() {
        return ConfigManager.getInstance().getName(FunctionClass.class, functionClass);
    }

    public String getFunctionTypeInfo() {
        return ConfigManager.getInstance().getName(FunctionType.class, functionType);
    }

    public String getOperatorActionInfo() {
        return ConfigManager.getInstance().getName(OperatorAction.class, operatorAction);
    }

    public String getSubsystemTypeInfo() {
        return ConfigManager.getInstance().getName(SubsystemType.class, subsystemType);
    }

    public String getLogTypeInfo() {
        return ConfigManager.getInstance().getName(LogType.class, logType);
    }

    public String getStatusInfo() {
        return ConfigManager.getInstance().getName(ExecuteStatus.class, status);
    }

    public String getNotifyInfo() {
        return ConfigManager.getInstance().getName(WhetherState.class, notify);
    }

    public String getIpTypeInfo() {
        return ConfigManager.getInstance().getName(ChostIPType.class, ipType);
    }

    public String getTypeInfo() {
        return ConfigManager.getInstance().getName(OperatorType.class, type);
    }

    public String getWhetherInfo() {
        return ConfigManager.getInstance().getName(WhetherState.class, whether);
    }

    public String getStepInfo() {
        return ConfigManager.getInstance().getName(ExecuteStep.class, step);
    }

    public void appendMessage(String message) {
        if (!PlatformUtils.hasText(message)) {
            return;
        }

        if (PlatformUtils.hasText(this.message)) {
            if (!this.message.contains(message)) {
                this.message += message;
            }
        } else {
            this.message = message;
        }
    }

    /**
     * @param t 异常
     * @return String 异常信息
     */
    public static String getTrace(Throwable t) {
        return PlatformUtils.getStackTrace(t);
    }

    public void appendContent(String content, String separator) {
        if (!PlatformUtils.hasText(content)) {
            return;
        }

        if (PlatformUtils.hasText(operationalContent)) {
            operationalContent += (content + separator);
        } else {
            operationalContent = content + separator;
        }
    }

    public void appendErrorInfo(String error, String separator) {
        if (!PlatformUtils.hasText(error)) {
            return;
        }

        if (PlatformUtils.hasText(errorInfo)) {
            errorInfo += (error + separator);
        } else {
            errorInfo = error + separator;
        }
    }

    /**
     * 验证参数属性是否设值
     */
    public void validateAttribute() {
        // 验证logUser对象
        if (logUser == null) {
            throw new IllegalArgumentException("the argument[logUser] must be set!!!");
        }
        logUser.validateAttribute();
        if (subsystemType <= 0) {
            throw new IllegalArgumentException("the argument[subsystemType] must be set!!!");
        }
        if (resourceId == null || resourceId.isEmpty()) {
            throw new IllegalArgumentException("the argument[resourceId] must be set!!!");
        }
        if (logType <= 0) {
            throw new IllegalArgumentException("the argument[logType] must be set!!!");
        }
        if (functionClass <= 0) {
            throw new IllegalArgumentException("the argument[functionClass] must be set!!!");
        }
        if (functionType <= 0) {
            throw new IllegalArgumentException("the argument[funtionType] must be set!!!");
        }
        if (operatorAction <= 0) {
            throw new IllegalArgumentException("the argument[operatorAction] must be set!!!");
        }
    }

    /**
     * @param functionClass  功能大类
     * @param functionType   功能小类
     * @param operatorAction 操作动作
     *                       设置操作属性
     */
    public void setOperationAttribute(long functionClass, long functionType, long operatorAction) {
        this.functionClass = functionClass;
        this.functionType = functionType;
        this.operatorAction = operatorAction;
    }

    /**
     * @param subsystemType      子系统类别
     * @param resourceId         资源id
     * @param operationalContent 操作内容
     * @param errorInfo          错误信息 可以为空
     */
    public void setVariableAttribute(long subsystemType, String resourceId, String operationalContent, String errorInfo) {
        this.subsystemType = subsystemType;
        this.resourceId = resourceId;
        this.operationalContent = operationalContent;
        this.errorInfo = errorInfo;
    }

    /**
     * @param subsystemType      子系统类别
     * @param resourceId         资源id
     * @param operationalContent 操作内容
     */
    public void setVariableAttribute(int subsystemType, String resourceId, String operationalContent) {
        this.subsystemType = subsystemType;
        this.resourceId = resourceId;
        this.operationalContent = operationalContent;
    }

    /**
     * @param subsystemType      子系统类别
     * @param resourceId         资源id
     * @param operationalContent 操作内容
     * @param e                  异常堆栈信息
     */
    public void setVariableAttribute(int subsystemType, String resourceId, String operationalContent, Throwable e) {
        this.subsystemType = subsystemType;
        this.resourceId = resourceId;
        this.operationalContent = operationalContent;
        this.errorInfo = getTrace(e);
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

    public long getLogType() {
        return logType;
    }

    public void setLogType(long logType) {
        this.logType = logType;
    }

    public String getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(String errorInfo) {
        this.errorInfo = errorInfo;
    }

    public void setErrorInfo(Throwable e) {
        setErrorInfo(getTrace(e));
    }

    public LogUser getLogUser() {
        return logUser;
    }

    public void setLogUser(LogUser logUser) {
        this.logUser = logUser;
    }

    public long getOperatorAction() {
        return operatorAction;
    }

    public void setOperatorAction(long operatorAction) {
        this.operatorAction = operatorAction;
    }

    public long getShowType() {
        return showType;
    }

    public void setShowType(long showType) {
        this.showType = showType;
    }

    public String getHandler() {
        return handler;
    }

    public void setHandler(String handler) {
        this.handler = handler;
    }

    public long getSourceProductId() {
        return sourceProductId;
    }

    public void setSourceProductId(long sourceProductId) {
        this.sourceProductId = sourceProductId;
    }

    public long getSourceServerId() {
        return sourceServerId;
    }

    public void setSourceServerId(long sourceServerId) {
        this.sourceServerId = sourceServerId;
    }

    public long getTargetServerId() {
        return targetServerId;
    }

    public void setTargetServerId(long targetServerId) {
        this.targetServerId = targetServerId;
    }

    public String getSrcProductName() {
        return srcProductName;
    }

    public void setSrcProductName(String srcProductName) {
        this.srcProductName = srcProductName;
    }

    public String getTargetProductName() {
        return targetProductName;
    }

    public void setTargetProductName(String targetProductName) {
        this.targetProductName = targetProductName;
    }

    public long getStatus() {
        return status;
    }

    public void setStatus(long status) {
        this.status = status;
    }

    public long getNotify() {
        return notify;
    }

    public void setNotify(long notify) {
        this.notify = notify;
    }

    public long getIpType() {
        return ipType;
    }

    public void setIpType(long ipType) {
        this.ipType = ipType;
    }

    public long getType() {
        return type;
    }

    public void setType(long type) {
        this.type = type;
    }

    public long getStep() {
        return step;
    }

    public void setStep(long step) {
        this.step = step;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public long getWhether() {
        return whether;
    }

    public void setWhether(long whether) {
        this.whether = whether;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTmpOperationalContent() {
        return tmpOperationalContent;
    }

    public void setTmpOperationalContent(String tmpOperationalContent) {
        this.tmpOperationalContent = tmpOperationalContent;
        this.setOperationalContent(tmpOperationalContent);
    }

    public String getTmpErrorInfo() {
        return tmpErrorInfo;
    }

    public void setTmpErrorInfo(String tmpErrorInfo) {
        this.tmpErrorInfo = tmpErrorInfo;
        this.setErrorInfo(tmpErrorInfo);
    }

    public LogBaseBean clone() {
        LogBaseBean log = null;
        try {
            log = (LogBaseBean) super.clone();
        } catch (CloneNotSupportedException e) {
            logger.error(e.getMessage(), e);
        }
        return log;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("LogBean{");
        sb.append("id=").append(getId());
        sb.append(", createTime=").append(getCreateTimeInfo());
        sb.append(", createUser=").append(getCreateUser());
        sb.append(", updateTime=").append(getUpdateTimeInfo());
        sb.append(", updateUser=").append(getUpdateUser());
        sb.append(", logUser=").append(logUser);
        sb.append(", subsystemType=").append(subsystemType);
        sb.append(", functionClass=").append(functionClass);
        sb.append(", functionType=").append(functionType);
        sb.append(", operatorAction=").append(operatorAction);
        sb.append(", resourceId='").append(resourceId).append('\'');
        sb.append(", operationalContent='").append(operationalContent).append('\'');
        sb.append(", errorInfo='").append(errorInfo).append('\'');
        sb.append(", logType=").append(logType);
        sb.append(", showType=").append(showType);
        sb.append(", sourceProductId=").append(sourceProductId);
        sb.append(", sourceServerId=").append(sourceServerId);
        sb.append(", targetServerId=").append(targetServerId);
        sb.append(", status=").append(status);
        sb.append(", notify=").append(notify);
        sb.append(", ipType=").append(ipType);
        sb.append(", type=").append(type);
        sb.append(", step=").append(step);
        sb.append(", counter=").append(counter);
        sb.append(", whether=").append(whether);
        sb.append(", message=").append(message);
        sb.append('}');
        return sb.toString();
    }

    public static String toXml(List<LogBean> logList) {
        StringBuilder xml = new StringBuilder();
        xml.append("<logBeanList>");
        if (logList != null && !logList.isEmpty()) {
            for (LogBean logBean : logList) {
                xml.append(logBean.toXml());
            }
        }
        xml.append("</logBeanList>");

        return xml.toString();
    }

    public String toXml() {
        StringBuilder xml = new StringBuilder();
        xml.append("<logBean>");
        xml.append("<id><![CDATA[").append(getId()).append("]]></id>");
        xml.append("<functionClass><![CDATA[").append(getFunctionClass()).append("]]></functionClass>");
        xml.append("<logType><![CDATA[").append(getLogType()).append("]]></logType>");
        xml.append("<resourceId><![CDATA[").append(getResourceId()).append("]]></resourceId>");
        xml.append("<operationalContent><![CDATA[").append(getOperationalContent()).append("]]></operationalContent>");
        xml.append("<createTime><![CDATA[").append(getCreateTimeInfo()).append("]]></createTime>");
        xml.append("</logBean>");
        return xml.toString();
    }

    /**
     * @param xml
     * @return
     * @throws Exception
     * @category xmlToList
     */
    public static List<LogBean> parseXmlToList(String xml) throws Exception {
        List<LogBean> logList = new ArrayList<>();
        parseXmlToList(xml, logList, new Page());
        return logList;
    }

    public static void parseXmlToList(String xml, List<LogBean> logList, Page page) throws Exception {
        long returnCode = InterfaceRetCode.ERROR;
        String returnMsg = ResourceBundleHelper.getInstance().getText("common.message.fail");

        SAXReader saxReader = new SAXReader();
        try (InputStream is = new ByteArrayInputStream(xml.getBytes("UTF-8"))) {
            Document document = saxReader.read(is);
            Element root = (Element) document.selectSingleNode("/interface");
            @SuppressWarnings("unchecked")
            List<Element> eleList = root.elements();
            if (eleList != null && !eleList.isEmpty()) {
                for (Element element : eleList) {
                    if ("return_code".equals(element.getName())) {
                        returnCode = Long.parseLong(element.getText());
                    } else if ("return_msg".equals(element.getName())) {
                        returnMsg = element.getText();
                    } else if ("currentPage".equals(element.getName())) {
                        page.setCurrentPage(Integer.parseInt(element.getText()));
                    } else if ("pageSize".equals(element.getName())) {
                        page.setPageSize(Integer.parseInt(element.getText()));
                    } else if ("totalCount".equals(element.getName())) {
                        page.setTotalCount(Integer.parseInt(element.getText()));
                    } else if ("logBeanList".equals(element.getName())) {
                        @SuppressWarnings("unchecked")
                        List<Element> list = element.elements();
                        for (Element idcListEl : list) {
                            if ("logBean".equals(idcListEl.getName())) {
                                @SuppressWarnings("unchecked")
                                List<Element> endList = idcListEl.elements();
                                LogBean logBean = new LogBean();
                                for (Element idcEl : endList) {
                                    if ("id".equals(idcEl.getName())) {
                                        logBean.setId(Long.parseLong(idcEl.getText()));
                                    } else if ("functionClass".equals(idcEl.getName())) {
                                        logBean.setFunctionClass(Long.parseLong(idcEl.getText()));
                                    } else if ("logType".equals(idcEl.getName())) {
                                        logBean.setLogType(Long.parseLong(idcEl.getText()));
                                    } else if ("resourceId".equals(idcEl.getName())) {
                                        logBean.setResourceId(idcEl.getText());
                                    } else if ("operationalContent".equals(idcEl.getName())) {
                                        logBean.setOperationalContent(idcEl.getText());
                                    } else if ("createTime".equals(idcEl.getName())) {
                                        logBean.setCreateTime(DateUtils.parseDateTime(idcEl.getText()));
                                    }
                                }
                                logList.add(logBean);
                            }
                        }
                    }
                }
            }

            if (returnCode != InterfaceRetCode.SUCCESS) {
                throw new Exception("xml[" + xml + "]" + returnCode + returnMsg);
            }
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * 获取格式化操作内容
     *
     * @param superOperateInfo  超管可见操作内容
     * @param memberOperateInfo 会员可见操作内容
     * @param userOperateInfo   用户可见操作内容
     * @return 格式化操作内容
     */
    public static String wrapOperateContent(String superOperateInfo, String memberOperateInfo, String userOperateInfo) {
        StringBuilder builder = new StringBuilder(CommonPlatformConstant.LENGTH_1024);
        builder.append(LogLevel.SUPER_LEVEL).append(":{").append(superOperateInfo == null ? "" : superOperateInfo).append("};");
        builder.append(LogLevel.MEMBER_LEVEL).append(":{").append(memberOperateInfo == null ? "" : memberOperateInfo).append("};");
        builder.append(LogLevel.USER_LEVEL).append(":{").append(userOperateInfo == null ? "" : userOperateInfo).append("}");
        return builder.toString();
    }

    /**
     * 根据日志级别获取操作内容
     *
     * @param logLevel       日志级别
     * @param operateContent 操作内容
     * @return 操作内容
     * @see com.sjdf.platform.dictionary.bean.LogLevel
     */
    public static String getOperateContent(long logLevel, String operateContent) {
        if (operateContent == null) {
            return "";
        }

        String[] opeContentArrayStrings = null;
        if (operateContent.contains("};")) {
            opeContentArrayStrings = operateContent.split("};");
        } else if (operateContent.contains("}；")) {
            opeContentArrayStrings = operateContent.split("}；");
        }
        if (opeContentArrayStrings == null) {
            return "";
        }

        String opeContent = "";
        int length;
        long level;
        try {
            for (String opeContentArrayString : opeContentArrayStrings) {
                opeContent = opeContentArrayString;
                length = opeContent.indexOf(":");
                level = Long.valueOf(opeContent.substring(0, length));
                if (level == logLevel) {
                    if (opeContent.endsWith("}")) {
                        opeContent = opeContent.substring(length + CommonPlatformConstant.LENGTH_2, opeContent.length() - 1);
                    } else {
                        opeContent = opeContent.substring(length + CommonPlatformConstant.LENGTH_2, opeContent.length());
                    }
                    break;
                }
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return operateContent;
        }
        return opeContent;
    }
}
