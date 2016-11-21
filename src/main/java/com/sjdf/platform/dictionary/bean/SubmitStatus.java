package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Create at 2013年8月8日 下午5:20:30
 *
 * @author KETQI
 * @category 提交状态
 */
@Entity
@DiscriminatorValue("SUBMIT_STATUS")
@BeanName(name = "提交状态")
public class SubmitStatus extends Dictionary {

    private static final long serialVersionUID = 1L;

    /** 已提交 */
    @BeanAttrInfo(value = "1", cnName = "已提交")
    public static final long YES = 1;

    /** 未提交 */
    @BeanAttrInfo(value = "2", cnName = "未提交")
    public static final long NO = 2;

}
