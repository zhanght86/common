package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Create at 2012-8-17
 *
 * @author 邱忠福
 * @category 服务器状态
 */
@Entity
@DiscriminatorValue("SERVER_STATUS")
@BeanName(name = "服务器状态")
public class ServerStatus extends Dictionary {
    private static final long serialVersionUID = -2524643511950603625L;

    /** 宕机 */
    @BeanAttrInfo(value = "1", cnName = "宕机")
    public static final long DOWN = 1;

    /** 未宕机 */
    @BeanAttrInfo(value = "2", cnName = "未宕机")
    public static final long NOT_DOWN = 2;
}
