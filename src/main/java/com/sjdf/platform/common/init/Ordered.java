package com.sjdf.platform.common.init;

/**
 * Create at 2013年12月9日 上午11:52:19
 * 初始化顺序,值越大越先初始化
 *
 * @author KETQI
 */
public interface Ordered {
    /**
     * 初始化顺序,值越大越先初始化
     *
     * @return 初始化顺序
     */
    int getOrder();
}
