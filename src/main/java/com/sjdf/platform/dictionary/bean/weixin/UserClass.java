package com.sjdf.platform.dictionary.bean.weixin;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.Dictionary;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author 王鸣忠
 * @category 微信平台用户类型
 * @Create 2014-4-4
 */
@Entity
@DiscriminatorValue("USER_CLASS")
@BeanName(name = "微信平台用户类型")
public class UserClass extends Dictionary {

    private static final long serialVersionUID = -4853200133561402457L;

    /** 微信用户 */
    @BeanAttrInfo(value = "1", cnName = "微信用户")
    public static final long WEIXIN_USER = 1L;

    /** 主站用户 */
    @BeanAttrInfo(value = "2", cnName = "主站用户")
    public static final long MASTER_USER = 2L;
}
