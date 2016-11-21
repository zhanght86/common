package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Create at 2012-07-20
 *
 * @author 黄远昌
 * @category 用户类型
 */
@Entity
@DiscriminatorValue("USER_TYPE")
@BeanName(name = "用户类型")
public class UserType extends Dictionary {
    private static final long serialVersionUID = 8053323371866311211L;
    /** 代理用户 */
    @BeanAttrInfo(value = "1", cnName = "代理用户")
    public static final long AGENT_USER = 1;
    /** 直接用户 */
    @BeanAttrInfo(value = "2", cnName = "直接用户")
    public static final long LOCAL_USER = 2;
}
