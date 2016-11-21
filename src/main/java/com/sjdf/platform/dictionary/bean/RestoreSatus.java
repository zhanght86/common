package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * create at 2012-08-09
 *
 * @author 陈绍刚
 * @category 恢复状态
 */

@Entity
@DiscriminatorValue("RESTORE_SATUS")
@BeanName(name = "恢复状态")
public class RestoreSatus extends Dictionary {
    private static final long serialVersionUID = 8785222815324777295L;

    /** 恢复中 */
    @BeanAttrInfo(value = "restoring", cnName = "恢复中")
    public static final long RESTORING = 1;
    /** 恢复成功 */
    @BeanAttrInfo(value = "success", cnName = "恢复成功")
    public static final long SUCCESS = 2;
    /** 恢复失败 */
    @BeanAttrInfo(value = "fail", cnName = "恢复失败")
    public static final long FAIL = 3;
    /** 执行中 */
    @BeanAttrInfo(value = "running", cnName = "执行中")
    public static final long RUNNING = 4;
}
