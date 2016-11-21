package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Create at 2012-09-14
 *
 * @author 陈绍刚
 * @category 升级手续费
 */
@Entity
@DiscriminatorValue("UPGRADE_FEE")
@BeanName(name = "升级手续费")
public class UpgradeFee extends Dictionary {
    private static final long serialVersionUID = 2941826606229590435L;

    /** 云主机更换机房手续费 */
    @BeanAttrInfo(value = "30", cnName = "云主机更换机房手续费")
    public static final long CHOST_GRADE_DIFF_IDC_FEE = 1;
    /** 云主机不更换机房手续费 */
    @BeanAttrInfo(value = "10", cnName = "云主机不更换机房手续费")
    public static final long CHOST_GRADE_SAME_IDC_FEE = 2;
    /** VPS升级到云主机手续费 */
    @BeanAttrInfo(value = "0", cnName = "VPS升级到云主机手续费")
    public static final long CHOST_GRADE_VPS_TO_CHOST = 3;
}
