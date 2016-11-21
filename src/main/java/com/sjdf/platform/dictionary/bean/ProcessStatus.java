package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * 2012-12-14
 *
 * @author 黄远昌
 * @category 处理状态
 */
@Entity
@DiscriminatorValue("PROCESS_STATUS")
@BeanName(name = "处理状态")
public class ProcessStatus extends Dictionary {
    private static final long serialVersionUID = 977428908826345374L;
    /** 未处理 */
    @BeanAttrInfo(value = "1", cnName = "未处理")
    public static final long UNPROCESS = 1;
    /** 处理中 */
    @BeanAttrInfo(value = "0", cnName = "处理中")
    public static final long PROCESSING = 0;
    /** 已处理 */
    @BeanAttrInfo(value = "2", cnName = "已处理")
    public static final long PROCESSED = 2;
    /** 待审核 */
    @BeanAttrInfo(cnName = "待审核")
    public static final long PENDING_AUDIT = 3;
}
