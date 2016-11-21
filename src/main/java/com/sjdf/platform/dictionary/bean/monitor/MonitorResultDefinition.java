package com.sjdf.platform.dictionary.bean.monitor;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.Dictionary;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * 监控结果定义
 * User: ketqi
 * Date: 2015-04-30 10:14
 */
@Entity
@DiscriminatorValue("MONITOR_RESULT_DEFINITION")
@BeanName(name = "监控结果定义")
public class MonitorResultDefinition extends Dictionary {
    private static final long serialVersionUID = 3618111653264221809L;

    /** HTTP 请求响应状态 */
    @BeanAttrInfo(cnName = "HTTP请求响应状态")
    public static final long HTTP_RESPONSE_STATUS = 1;

    /** HTTP 请求响应结果 */
    @BeanAttrInfo(cnName = "HTTP请求响应结果")
    public static final long HTTP_RESPONSE_RESULT = 5;
}
