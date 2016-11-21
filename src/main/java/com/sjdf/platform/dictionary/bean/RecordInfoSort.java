package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author frank
 * @date 2012-9-5 上午9:38:35
 * @category 备案信息分类
 */
@Entity
@DiscriminatorValue("RECORD_INFO_SORT")
@BeanName(name = "备案信息分类")
public class RecordInfoSort extends Dictionary {

    private static final long serialVersionUID = 2882157632658724007L;

    /** 备案信息-主体 */
    @BeanAttrInfo(orderBy = 1, cnName = "备案信息-主体")
    public static final long MAIN_PART = 1;

    /** 备案信息-网站 */
    @BeanAttrInfo(orderBy = 2, cnName = "备案信息-网站")
    public static final long WEB = 2;

}
