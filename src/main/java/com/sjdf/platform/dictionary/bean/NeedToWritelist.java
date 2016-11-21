package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Create at 2012-7-3
 *
 * @author 邱忠福
 * @category 是否需要添加到白名单
 */
@Entity
@DiscriminatorValue("NEED_TO_WRITE_LIST")
@BeanName(name = "是否需要添加到白名单")
public class NeedToWritelist extends Dictionary {
    private static final long serialVersionUID = -4551923027577509439L;
    /** 需要添加到白名单中 */
    @BeanAttrInfo(value = "1", cnName = "是")
    public static final long YES = 1;
    /** 不需要添加到白名单中 */
    @BeanAttrInfo(value = "2", cnName = "否")
    public static final long NO = 2;
}
