package com.sjdf.platform.common.filter;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.ExceptionHolder;
import com.sjdf.platform.log.logger.SJDFLogger;
import com.sjdf.platform.log.logger.SJDFLoggerFactory;

/**
 * 异常拦截器
 * User: ketqi
 * Date: 2015-12-28 14:15
 */
public class CommonExceptionMappingInterceptor extends com.opensymphony.xwork2.interceptor.ExceptionMappingInterceptor {
    private static final SJDFLogger LOGGER = SJDFLoggerFactory.getSJDFLogger(CommonExceptionMappingInterceptor.class);
    /** 是否打印异常栈 */
    private boolean printStack = false;

    @Override
    protected void publishException(ActionInvocation invocation, ExceptionHolder exceptionHolder) {
        super.publishException(invocation, exceptionHolder);

        // 打印异常
        if (printStack) {
            exceptionHolder.getException().printStackTrace();
        }

        // 记录异常日志
        LOGGER.error("Exception Mapping Interceptor", exceptionHolder.getException());
    }

    public boolean isPrintStack() {
        return printStack;
    }

    public void setPrintStack(boolean printStack) {
        this.printStack = printStack;
    }
}
