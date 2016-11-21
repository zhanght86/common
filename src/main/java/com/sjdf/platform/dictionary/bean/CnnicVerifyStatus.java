package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Create at 2013年10月28日 下午3:43:01
 *
 * @author KETQI
 * @category cnnic审核结果状态(value禁止修改)
 */
@Entity
@DiscriminatorValue("CNNIC_VERIFY_STATUS")
@BeanName(name = "cnnic审核结果状态")
public class CnnicVerifyStatus extends Dictionary {
    private static final long serialVersionUID = 6854815540805540970L;

    /** 审核结果状态:审核中 */
    @BeanAttrInfo(value = "auditing", cnName = "审核中")
    public static final long AUDITING = 1;

    /** 审核结果状态:审核通过 */
    @BeanAttrInfo(value = "pass", cnName = "审核通过")
    public static final long PASS = 2;

    /** 审核结果状态:审核未通过 */
    @BeanAttrInfo(value = "unpass", cnName = "审核未通过")
    public static final long UNPASS = 3;
}
