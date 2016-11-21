package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author frank
 * @category 审核结果代码
 */
@Entity
@DiscriminatorValue("VERIFY_RESULT_CODE")
@BeanName(name = "审核结果代码")
public class VerifyResultCode extends Dictionary {

    private static final long serialVersionUID = 2882163375429724179L;

    /** 审核通过 */
    @BeanAttrInfo(value = "0", orderBy = 0, cnName = "审核通过")
    public static final long VERIFY_YES = 0;

    /** 审核未通过 */
    @BeanAttrInfo(value = "1", orderBy = 1, cnName = "审核未通过")
    public static final long VERIFY_NO = 1;

    /** 未审核 */
    @BeanAttrInfo(value = "2", orderBy = 2, cnName = "未审核")
    public static final long NOT_VERIFY = 2;

}
