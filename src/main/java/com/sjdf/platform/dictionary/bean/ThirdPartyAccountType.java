package com.sjdf.platform.dictionary.bean;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

/**
 * create in 2016年7月25日
 * @category 第三方账号类型
 * @author wangpeng
 */
@Entity
@DiscriminatorValue("THIRD_PARTY_ACCOUNT_TYPE")
@BeanName(name = "第三方账号类型")
public class ThirdPartyAccountType extends Dictionary{

    private static final long serialVersionUID = -4216075092367131141L;

    @BeanAttrInfo(cnName = "QQ", enName = "QQ")
    public static final long QQ = 1L;

    @BeanAttrInfo(cnName = "微信", enName = "wechat")
    public static final long WECHAT = 2L;

    @BeanAttrInfo(cnName = "支付宝", enName = "alipay")
    public static final long ALIPAY = 3L;
}
