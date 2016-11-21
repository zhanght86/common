package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Create at 2012-7-3
 *
 * @author 邱忠福
 * @category 白名单
 */
@Entity
@DiscriminatorValue("WHITE_LIST")
@BeanName(name = "白名单")
public class WhiteList extends Dictionary {
    private static final long serialVersionUID = -3600977106609551997L;
    /** 存在于白名单中 */
    @BeanAttrInfo(value = "1", cnName = "是")
    public static final long EXIST = 1;
    /** 不存在于白名单中 */
    @BeanAttrInfo(value = "2", cnName = "否")
    public static final long NOT_EXIST = 2;

}
