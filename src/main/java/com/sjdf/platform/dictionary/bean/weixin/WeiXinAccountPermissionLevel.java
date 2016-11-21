package com.sjdf.platform.dictionary.bean.weixin;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.Dictionary;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author hunk
 * @category 微信公众账号权限级别
 * @Create 2014-04-10
 */
@Entity
@DiscriminatorValue("WEI_XIN_ACCOUNT_PERMISSION_LEVEL")
@BeanName(name = "微信公众账号权限级别")
public class WeiXinAccountPermissionLevel extends Dictionary {

    private static final long serialVersionUID = -3596320355512701996L;

    /** 所有者权限 */
    @BeanAttrInfo(value = "1", cnName = "所有者权限")
    public static final long OWNER = 1L;

    /** 管理者权限 */
    @BeanAttrInfo(value = "2", cnName = "管理者权限")
    public static final long MANAGEMENT = 2L;

    /** 授权管理权限 */
    @BeanAttrInfo(value = "3", cnName = "授权管理权限")
    public static final long AUTHORIZED_MANAGEMENT = 3L;
}
