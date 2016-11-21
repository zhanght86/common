package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Create at 2012-7-4
 *
 * @author 邱忠福
 * @category 备案接入
 */
@Entity
@DiscriminatorValue("RECORD_OR_JOIN")
@BeanName(name = "备案接入")
public class RecordOrJoin extends Dictionary {
    private static final long serialVersionUID = -5345676888742336676L;
    /** 需要备案 */
    @BeanAttrInfo(value = "1", cnName = "备案")
    public static final long RECORD = 1;
    /** 需要接入 */
    @BeanAttrInfo(value = "2", cnName = "新增接入")
    public static final long JOIN = 2;
    /** 已在我司备案 */
    @BeanAttrInfo(value = "3", cnName = "正常")
    public static final long YES = 3;

}
