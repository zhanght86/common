package com.sjdf.platform.dictionary.bean.ers;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.Dictionary;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author Hunk
 * @category 备案相关可配置常量
 */
@Entity
@DiscriminatorValue("ERS_CONST")
@BeanName(name = "备案相关可配置常量")
public class ErsConst extends Dictionary {
    private static final long serialVersionUID = -964151046540509915L;

    /** 是否清理变更备案历史数据 */
    @BeanAttrInfo(value = "2", cnName = "是否清理变更备案历史数据")
    public static final long WHETHER_CLEAR_HISTORY_DATA = 1;

    /** 清理多久前的变更备案数据（单位：月） */
    @BeanAttrInfo(value = "6", cnName = "清理多久前的变更备案数据（单位：月）")
    public static final long BGBA_ZJXGSJ_JS = 2;

    /** 超管用户ID */
    @BeanAttrInfo(value = "2", cnName = "超管用户ID")
    public static final long ADMIN_YH_ID = 3;

    /** 是否需要验证产品 */
    @BeanAttrInfo(value = "1", cnName = "是否需要验证产品（1：是；2：否）")
    public static final long IS_VALIDATE_PRODUCT = 4;

    /** 是否网域网备案 */
    @BeanAttrInfo(value = "2", cnName = "是否网域网备案（1：是；2：否）")
    public static final long IS_7X24_RECORD = 5;

    /** 不进行备案接入检查的用户ID */
    @BeanAttrInfo(value = "", cnName = "不进行备案接入检查的用户ID（说明：设置为空或者无效时，为所有对象）")
    public static final long NOT_ACCESS_YH_ID = 6;
}
