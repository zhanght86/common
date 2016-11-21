package com.sjdf.platform.log.logger;

import org.slf4j.LoggerFactory;

/**
 * 日志工厂
 * 2012-5-10 下午1:30:03
 *
 * @author laberwu
 */
public abstract class SJDFLoggerFactory {
    private static final String LOGGER_DB = "DB";

    /**
     * 通过class生成日志记录器
     *
     * @param clz class
     */
    public static SJDFLogger getSJDFLogger(Class<?> clz) {
        return new SJDFLogger(LoggerFactory.getLogger(clz), LoggerFactory.getLogger(LOGGER_DB));
    }
}
