package com.sjdf.platform.dictionary.bean.eiss;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.Dictionary;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("RECOMMEND_CODE_CONFIG")
@BeanName(name = "推荐码参数配置")
public class ReCommendCodeConfig extends Dictionary {
    private static final long serialVersionUID = 2568442778133447964L;

    /** 推荐码的缺省有效期限 */
    @BeanAttrInfo(value = "30", cnName = "推荐码的缺省有效期限")
    public static final long VALIDITY_DAYS = 1;

    /** 有效期限自动延长长度 */
    @BeanAttrInfo(value = "30", cnName = "有效期限自动延长长度")
    public static final long AUTO_DELAY_DAYS = 2;

    /** 有效期限最长延长长度 */
    @BeanAttrInfo(value = "90", cnName = "有效期限最长延长长度")
    public static final long MAX_DAYS = 3;

    /** 前缀 */
    @BeanAttrInfo(value = "51web", cnName = "推荐码前缀")
    public static final long CODE_PREFIX = 4;


}
