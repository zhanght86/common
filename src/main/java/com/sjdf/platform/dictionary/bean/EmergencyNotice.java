package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * 2013-12-05
 *
 * @author 黄远昌
 * @category 紧急通知
 */
@Entity
@DiscriminatorValue("EMERGENCY_NOTICE")
@BeanName(name = "紧急通知")
public class EmergencyNotice extends Dictionary {
    private static final long serialVersionUID = 6732517507059638770L;
    /** 主站紧急通知 */
    @BeanAttrInfo(value = "请配置通知内容", cnName = "主站紧急通知")
    public static final long HOME_EMERGENCY_NOTICE = 1;
}
