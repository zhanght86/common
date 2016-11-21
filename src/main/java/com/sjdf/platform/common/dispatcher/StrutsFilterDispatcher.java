package com.sjdf.platform.common.dispatcher;

import com.sjdf.platform.dictionary.bean.SystemType;

/**
 * Create at 2013年04月16日 下午09:20:50
 * eiss common平台初始化
 *
 * @author KETQI
 */
public class StrutsFilterDispatcher extends PlatformFilterDispatcher {
    @Override
    protected void beforeInit() {
        // 设置当前系统类型
        System.setProperty(SystemType.CURRENT_SYSTEM_TYPE, String.valueOf(SystemType.EISS_COMMON));
    }
}
