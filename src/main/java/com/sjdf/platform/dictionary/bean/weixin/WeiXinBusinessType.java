package com.sjdf.platform.dictionary.bean.weixin;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.Dictionary;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author wangpeng
 * @category 微信业务模块类型
 * @date 2014-04-09
 */
@Entity
@DiscriminatorValue("WEI_XIN_BUSINESS_TYPE")
@BeanName(name = "业务模块类型")
public class WeiXinBusinessType extends Dictionary {

    private static final long serialVersionUID = 4600948807597011708L;

    @BeanAttrInfo(value = "1", cnName = "微官网")
    public static final long OFFICIAL_WEBSITE = 1L;
}
