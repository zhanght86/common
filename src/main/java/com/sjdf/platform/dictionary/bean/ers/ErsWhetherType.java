package com.sjdf.platform.dictionary.bean.ers;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.Dictionary;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author sjdf
 * @category 备案系统是否
 */
@Entity
@DiscriminatorValue("ERS_WHETHER_TYPE")
@BeanName(name = "备案系统是否")
public class ErsWhetherType extends Dictionary {

    /**
     * 序列化
     */
    private static final long serialVersionUID = 3592258801511693833L;

    /** 否 */
    @BeanAttrInfo(value = "0", cnName = "否")
    public static final long NO = 0L;

    /** 是 */
    @BeanAttrInfo(value = "1", cnName = "是")
    public static final long YES = 1L;

}
