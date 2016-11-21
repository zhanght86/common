package com.sjdf.platform.common.init;

import org.apache.struts2.dispatcher.Dispatcher;

import javax.servlet.ServletContext;

/**
 * Create at 2013年12月9日 上午11:51:37
 * 由struts2负责进行系统初始化的
 *
 * @author KETQI
 */
public interface StrutsStartupInitable extends Ordered {
    /**
     * 初始化信息
     *
     * @param servletContext 上下文
     * @param dispatcher     struts分发器
     */
    void init(ServletContext servletContext, Dispatcher dispatcher);

    /**
     * 系统类型,标识该初始化功能在该系统下使用
     *
     * @return 系统类型
     * @see com.sjdf.platform.dictionary.bean.SystemType
     */
    long systemType();
}
