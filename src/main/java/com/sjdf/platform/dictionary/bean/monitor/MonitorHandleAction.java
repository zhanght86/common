package com.sjdf.platform.dictionary.bean.monitor;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.Dictionary;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * 监控结果处理动作
 * User: ketqi
 * Date: 2015-04-30 10:58
 */
@Entity
@DiscriminatorValue("MONITOR_HANDLE_ACTION")
@BeanName(name = "监控结果处理动作")
public class MonitorHandleAction extends Dictionary {
    private static final long serialVersionUID = 3539593629043374983L;

    /** 更改域名解记录 */
    @BeanAttrInfo(cnName = "更改域名解析记录")
    public static final long MODIFY_DOMAIN_PARSE = 1;
}
