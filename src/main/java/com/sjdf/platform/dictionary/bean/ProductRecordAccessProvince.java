package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author laberwu
 * @category 产品备案接入检查-短信邮件通知省份
 * @ClassName ProductRecordAccessProvince
 * @Created 2013 2013-2-21 下午5:18:12
 */
@Entity
@DiscriminatorValue("PRODUCT_RECORD_ACCESS_PROVINCE")
@BeanName(name = "产品备案接入检查-短信邮件通知省份")
public class ProductRecordAccessProvince extends Dictionary {

    /**
     * @category
     * @field long serialVersionUID
     * @created 2013 2013-2-21 下午5:19:54
     */
    private static final long serialVersionUID = 4101940383526810960L;

    @BeanAttrInfo(value = "330000", cnName = "浙江省")
    public static final long ZHE_JIANG = 1;

}
