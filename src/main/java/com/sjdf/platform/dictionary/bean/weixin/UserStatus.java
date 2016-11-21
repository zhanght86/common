package com.sjdf.platform.dictionary.bean.weixin;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.Dictionary;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author 王鸣忠
 * @category 用户状态
 * @create 2014年4月14日17:10:11
 */
@Entity
@DiscriminatorValue("USER_STATUS")
@BeanName(name = "用户状态")
public class UserStatus extends Dictionary {

    private static final long serialVersionUID = 2965486082897988160L;

    /** 正常 */
    @BeanAttrInfo(value = "1", cnName = "正常")
    public static final long UNLOCK = 1;

    /** 锁定 */
    @BeanAttrInfo(value = "2", cnName = "锁定")
    public static final long LOCK = 2;
}
