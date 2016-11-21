package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * 2012-09-01
 *
 * @author 黄远昌
 * @category 日志级别
 */
@Entity
@DiscriminatorValue("LOG_LEVEL")
@BeanName(name = "日志级别")
public class LogLevel extends Dictionary {
    private static final long serialVersionUID = 312375528830282819L;
    /** 超管级别 */
    @BeanAttrInfo(value = "1", cnName = "超管级别")
    public static final long SUPER_LEVEL = 1;

    /** 会员级别 */
    @BeanAttrInfo(value = "2", cnName = "会员级别")
    public static final long MEMBER_LEVEL = 2;

    /** 用户级别 */
    @BeanAttrInfo(value = "3", cnName = "用户级别")
    public static final long USER_LEVEL = 3;
}
