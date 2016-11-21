package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author sjdf
 * @category 自动任务触发标识
 */
@Entity
@DiscriminatorValue("AUTO_TASK_TRIGGER")
@BeanName(name = "自动任务触发标识")
public class AutoTaskTrigger extends Dictionary {

    /**
     * 序列化
     */
    private static final long serialVersionUID = -198037656521821197L;

    /** 产品备案接入检查--是我司产品但备案不在我司的域名检查 */
    @BeanAttrInfo(value = "1", orderBy = 1, cnName = "产品备案接入检查--是我司产品但备案不在我司的域名检查")
    public static final long PRODUCT_RECORD_ACCESS_OUR_DOMAIN_NOT_RECORD = 1;

    /** 产品备案接入检查--是我司产品但备案不在我司的域名检查--停止产品 */
    @BeanAttrInfo(value = "2", orderBy = 2, cnName = "产品备案接入检查--是我司产品但备案不在我司的域名检查--停止产品")
    public static final long PRODUCT_RECORD_ACCESS_OUR_DOMAIN_NOT_RECORD_STOP_PRODUCT = 2;
}
