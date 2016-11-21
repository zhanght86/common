package com.sjdf.platform.log.logger;

import com.sjdf.platform.CommonPlatformConstant;
import com.sjdf.platform.common.utils.DateUtils;
import com.sjdf.platform.common.utils.PlatformUtils;
import com.sjdf.platform.dictionary.bean.LogType;
import com.sjdf.platform.dictionary.bean.OperatorFunction;
import com.sjdf.platform.dictionary.cache.ConfigManager;
import com.sjdf.platform.log.bean.LogBean;
import com.sjdf.platform.log.bean.LogUser;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.MDC;

import javax.servlet.http.HttpServletRequest;

/**
 * 2012-5-10 下午1:31:36
 * 日志记录器
 * <pre>
 * 在配置库【操作函数】中添加配置
 * 属性为：package.className.functionName
 * 引用:选择功能小类
 * 系统类型
 * 子系统类型
 * 功能大分类
 * 操作动作
 * 以上属性为必填
 * </pre>
 *
 * @author laberwu
 */
public class SJDFLogger {
    /** 普通日志记录器 */
    private Logger logger = null;
    /** DB日志记录器（记录到数据库中） */
    private Logger logger4Db = null;

    /** 构造函数，用于两个日志记录器的生成 */
    SJDFLogger(Logger logger, Logger logger4DB) {
        this.logger = logger;
        this.logger4Db = logger4DB;
    }

    public void debug(String string) {
        logger.debug(string);
    }

    public void debug(String string, Object... objs) {
        logger.debug(string, objs);
    }

    public void info(String string) {
        logger.info(string);
    }

    public void info(String string, Object... objs) {
        logger.debug(string, objs);
    }

    public void error(String string) {
        logger.error(string);
    }

    public void error(String string, Object... objs) {
        logger.error(string, objs);
    }

    public void error(String string, Throwable e) {
        logger.error(string, e);
    }

    /**
     * 记录日志对象持久化到数据库，用于操作日志
     *
     * @param logBean 日志记录
     */
    public void infoDB(LogBean logBean) {
        // 加入级别
        logBean.setLogType(LogType.INFO);
        // 如果logBean中没有logUser对象 加入logUser对象
        if (logBean.getLogUser() == null) {
            LogUser logUser = getLogUser();
            logBean.setLogUser(logUser);
        }
        // 通过完整类名和方法名确认操作信息
        setOperationAttribute(logBean);

        // 操作日志为空，直接返回不记录日志
        if (!PlatformUtils.hasText(logBean.getOperationalContent())) {
            return;
        }

        // 判断logBean设置是否完整，如果不完整，跑出异常
        logBean.validateAttribute();
        // 进行mdc存放值进行处理
        setMDCValue(logBean);
        // 记录日志
        logger4Db.info("LogBean：" + logBean);
    }

    /**
     * 记录日志对象持久化到数据库，用于错误
     *
     * @param logBean 日志记录
     */
    public void errorDB(LogBean logBean) {
        // 加入级别
        logBean.setLogType(LogType.ERROR);
        // 加入logUser对象
        if (logBean.getLogUser() == null) {
            LogUser logUser = getLogUser();
            logBean.setLogUser(logUser);
        }

        setOperationAttribute(logBean);

        // 操作日志为空，直接返回不记录日志
        if (!PlatformUtils.hasText(logBean.getOperationalContent())) {
            return;
        }

        // 判断logBean设置是否完整，如果不完整，跑出异常
        logBean.validateAttribute();
        // 进行mdc存放值进行处理
        setMDCValue(logBean);
        logger4Db.error("LogBean：" + logBean);
    }

    /** 得到logUser对象 */
    private LogUser getLogUser() {
        LogUser logUser = new LogUser();
        HttpServletRequest request;
        try {
            request = ServletActionContext.getRequest();
        } catch (Exception e) {
            // 未能从web环境中取值，返回测试数据
            return logUser;
        }

        if (request != null) {
            logUser = (LogUser) request.getSession().getAttribute(LogUser.KEY);
            // 从session中获取不到logUser，显示异常信息
            if (logUser == null) {
                throw new IllegalArgumentException("the argument[logUser] must be set by session!!!");
            }
        }

        return logUser;
    }

    /**
     * 通过配置库查询得到操作函数并设置
     *
     * @param logBean 日志记录
     */
    private void setOperationAttribute(LogBean logBean) {
        // 通过配置库得到操作信息
        // 得到完整类名，方法名，用来设置功能大类，小类机操作动作(注意：如果上面的值 已经存在，说明已经认为设置，则这里不用设置)
        StackTraceElement[] stack = (new Throwable()).getStackTrace();
        String clz;
        String method;
        int length = stack.length;
        if (length > CommonPlatformConstant.LENGTH_2) {
            clz = stack[CommonPlatformConstant.LENGTH_2].getClassName();
            method = stack[CommonPlatformConstant.LENGTH_2].getMethodName();
        } else {
            clz = stack[CommonPlatformConstant.LENGTH_2].getClassName();
            method = stack[CommonPlatformConstant.LENGTH_2].getMethodName();
        }

        OperatorFunction dictionary = ConfigManager.getInstance().getDictionaryByVal(OperatorFunction.class, clz + "." + method);
        if (dictionary != null) {
            // 如果操作类型值为空，则自动设置
            if (dictionary.getFunctionClass() != null) {
                logBean.setFunctionClass(dictionary.getFunctionClass().getAttr());
            }
            if (dictionary.getRef() != null) {
                logBean.setFunctionType(dictionary.getRef().getAttr());
            }
            if (dictionary.getOperateAction() != null) {
                logBean.setOperatorAction(dictionary.getOperateAction().getAttr());
            }
            if (dictionary.getSubsystem() != null) {
                logBean.setSubsystemType(dictionary.getSubsystem().getAttr());
            }
        }
    }

    private void setMDCValue(LogBean logBean) {
        // 这里的值应该是根据配置库中查询出来后的结果
        MDC.put("createTime", DateUtils.formatDate(logBean.getCreateTime()));
        MDC.put("createUser", logBean.getCreateUser());
        MDC.put("updateTime", DateUtils.formatDateTime(logBean.getUpdateTime()));
        MDC.put("updateUser", logBean.getUpdateUser());

        MDC.put("id", String.valueOf(logBean.getId()));
        MDC.put("sourceProductId", String.valueOf(logBean.getSourceProductId()));
        MDC.put("sourceServerId", String.valueOf(logBean.getSourceServerId()));
        MDC.put("targetServerId", String.valueOf(logBean.getTargetServerId()));
        MDC.put("srcProductName", logBean.getSrcProductName());
        MDC.put("targetProductName", logBean.getTargetProductName());
        MDC.put("status", String.valueOf(logBean.getStatus()));
        MDC.put("notify", String.valueOf(logBean.getNotify()));
        MDC.put("ipType", String.valueOf(logBean.getIpType()));
        MDC.put("type", String.valueOf(logBean.getType()));
        MDC.put("step", String.valueOf(logBean.getStep()));
        MDC.put("counter", String.valueOf(logBean.getCounter()));
        MDC.put("whether", String.valueOf(logBean.getWhether()));
        MDC.put("message", logBean.getMessage());

        // 存放logUser对象的值
        MDC.put("loginUser.systemType", String.valueOf(logBean.getLogUser().getSystemType()));
        MDC.put("loginUser.loginUser", logBean.getLogUser().getLoginUser());
        MDC.put("loginUser.company", logBean.getLogUser().getCompany());
        MDC.put("loginUser.ipAddress", logBean.getLogUser().getIpAddress());

        // 存放操作LogFunction的值
        MDC.put("functionClass", String.valueOf(logBean.getFunctionClass()));
        MDC.put("functionType", String.valueOf(logBean.getFunctionType()));
        MDC.put("operatorAction", String.valueOf(logBean.getOperatorAction()));

        // 其他值
        MDC.put("subsystemType", String.valueOf(logBean.getSubsystemType()));
        MDC.put("resourceId", logBean.getResourceId());
        MDC.put("operationalContent", logBean.getOperationalContent());
        MDC.put("logType", String.valueOf(logBean.getLogType()));
        MDC.put("errorInfo", logBean.getErrorInfo());
        MDC.put("showType", String.valueOf(logBean.getShowType()));
    }
}
