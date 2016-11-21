package com.sjdf.platform.log.logger;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.UnsynchronizedAppenderBase;
import com.sjdf.platform.log.bean.LogBean;
import com.sjdf.platform.log.bean.LogUser;
import org.slf4j.MDC;

import java.util.Date;

/**
 * 数据库日志适配器
 * Create at 2013年8月8日 下午5:22:20
 *
 * @author KETQI
 */
public class DBAppender extends UnsynchronizedAppenderBase<ILoggingEvent> {
    @Override
    public void append(ILoggingEvent eventObject) {
        LogUser logUser = new LogUser();
        logUser.setCompany(MDC.get("loginUser.company"));
        logUser.setIpAddress(MDC.get("loginUser.ipAddress"));
        logUser.setLoginUser(MDC.get("loginUser.loginUser"));
        logUser.setSystemType(Integer.parseInt(MDC.get("loginUser.systemType")));

        LogBean log = new LogBean();
        log.setLogUser(logUser);
        log.setId(Long.parseLong(MDC.get("id")));
        log.setSourceProductId(Long.parseLong(MDC.get("sourceProductId")));
        log.setSourceServerId(Long.parseLong(MDC.get("sourceServerId")));
        log.setTargetServerId(Long.parseLong(MDC.get("targetServerId")));
        log.setSrcProductName(MDC.get("srcProductName"));
        log.setTargetProductName(MDC.get("targetProductName"));
        log.setStatus(Long.parseLong(MDC.get("status")));
        log.setNotify(Long.parseLong(MDC.get("notify")));
        log.setIpType(Long.parseLong(MDC.get("ipType")));
        log.setType(Long.parseLong(MDC.get("type")));
        log.setStep(Long.parseLong(MDC.get("step")));
        log.setCounter(Integer.parseInt(MDC.get("counter")));
        log.setWhether(Long.parseLong(MDC.get("whether")));
        log.setMessage(MDC.get("message"));
        log.setCreateTime(new Date());
        log.setUpdateTime(new Date());
        log.setCreateUser(MDC.get("createUser") == null ? "system" : MDC.get("createUser"));
        log.setUpdateUser(MDC.get("updateUser") == null ? "system" : MDC.get("updateUser"));
        log.setErrorInfo(MDC.get("errorInfo"));
        log.setFunctionClass(Integer.parseInt(MDC.get("functionClass")));
        log.setFunctionType(Integer.parseInt(MDC.get("functionType")));
        log.setLogType(Integer.parseInt(MDC.get("logType")));
        log.setOperationalContent(MDC.get("operationalContent"));
        log.setOperatorAction(Integer.parseInt(MDC.get("operatorAction")));
        log.setResourceId(MDC.get("resourceId"));
        log.setSubsystemType(Integer.parseInt(MDC.get("subsystemType")));
        log.setShowType(Integer.parseInt(MDC.get("showType")));
        log.setHandler(MDC.get("handler"));

        LogHelper.getInstance().saveOrUpdate(log);
    }
}
