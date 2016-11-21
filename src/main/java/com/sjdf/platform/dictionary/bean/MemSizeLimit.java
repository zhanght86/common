package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * 2012-06-02
 *
 * @author 黄远昌
 * @category 内存大小限制
 */
@Entity
@DiscriminatorValue("MEM_SIZE_LIMIT")
@BeanName(name = "内存大小限制")
public class MemSizeLimit extends Dictionary {
    private static final long serialVersionUID = 7428287241522989552L;
    /** 内存大小限制 */
    @BeanAttrInfo(value = "1024", cnName = "内存大小")
    public static final long MEM_SIZE_LIMIT = 1;
}
