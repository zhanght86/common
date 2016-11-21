package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * 2015-11-10
 *
 * @author wangpeng
 * @category 网居平台专有配置信息
 */
@Entity
@DiscriminatorValue("E8_PLATFORM_DIC")
@BeanName(name = "网居平台专有配置信息")
public class E8PlatformDic extends Dictionary {

    private static final long serialVersionUID = 9213546778127069835L;

    /**
     * @category 用户申请退款出账后调整到的级别
     */
    @BeanAttrInfo(value = "19", cnName = "普通会员")
    public static final long AFTER_USER_REFUND_MODIFIED_LEVEL = 1;
}
