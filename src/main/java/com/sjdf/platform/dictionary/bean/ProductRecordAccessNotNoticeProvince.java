package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author Hunk
 * @category 产品备案接入检查-短信邮件不通知通知的省份
 * @ClassName ProductRecordAccessNotNoticeProvince
 */
@Entity
@DiscriminatorValue("PRODUCT_RECORD_ACCESS_NOT_NOTICE_PROVINCE")
@BeanName(name = "产品备案接入检查-短信邮件不通知的省份")
public class ProductRecordAccessNotNoticeProvince extends Dictionary {

    private static final long serialVersionUID = 1029287922904004029L;

    @BeanAttrInfo(value = "370000", cnName = "山东省")
    public static final long SHAN_DONG = 1;

}
