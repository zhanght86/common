package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Create at 2012-06-01
 *
 * @author 王正伟
 * @category 审核状态
 */
@Entity
@DiscriminatorValue("VERIFY_STATUS")
@BeanName(name = "审核状态")
public class VerifyStatus extends Dictionary {
    private static final long serialVersionUID = 5111238318605368673L;

    /** 待审核 */
    @BeanAttrInfo(value = "0", cnName = "待审核 ", enName = "wait verify")
    public static final long WAIT_VERIFY = 0;

    /** 已审核 ,审核通过 */
    @BeanAttrInfo(value = "1", cnName = "已审核 ", enName = "verified")
    public static final long VERIFIED = 1;

    /** 已提交 */
    @BeanAttrInfo(value = "2", cnName = "已提交", enName = "submited")
    public static final long SUBMITED = 2;

    /** 未通过 */
    @BeanAttrInfo(value = "3", cnName = "未通过", enName = "no pass")
    public static final long NO_PASS = 3;
}
