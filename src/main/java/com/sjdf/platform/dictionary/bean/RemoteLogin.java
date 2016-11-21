package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Create at 2012-06-05
 *
 * @author 黄远昌
 * @category 远程登录配置信息
 */
@Entity
@DiscriminatorValue("REMOTE_LOGIN")
@BeanName(name = "远程登录配置信息")
public class RemoteLogin extends Dictionary {
    private static final long serialVersionUID = -7111372579207850193L;
    /** IP */
    @BeanAttrInfo(value = "118.123.12.65", cnName = "IP")
    public static final long IP = 1;
    /** 用户名 */
    @BeanAttrInfo(value = "root", cnName = "用户名")
    public static final long USER = 2;
    /** 密码 */
    @BeanAttrInfo(value = "##nopass,.,", cnName = "密码")
    public static final long PASSWORD = 3;
}
