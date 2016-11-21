package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Create at 2012-6-20
 *
 * @author 邱忠福
 * @category 备案状态
 */
@Entity
@DiscriminatorValue("RECORD_STATUS")
@BeanName(name = "备案状态")
public class RecordStatus extends Dictionary {
    private static final long serialVersionUID = -5020868012955548194L;
    /** 未备案 */
    @BeanAttrInfo(value = "1", cnName = "未备案")
    public static final long NONE_RECORD = 1;
    /** 已备案 */
    @BeanAttrInfo(value = "2", cnName = "已备案")
    public static final long ALREADY_RECORD = 2;
    /** 待检测 */
    @BeanAttrInfo(value = "4", cnName = "待检测")
    public static final long PENDING_CHECK_RECORD = 4;
}
