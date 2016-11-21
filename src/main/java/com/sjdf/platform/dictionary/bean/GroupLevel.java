package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author laberwu
 * @category 组别级别
 * @ClassName GroupLevel
 * @Created 2012 2012-8-30 上午9:40:12
 */
@Entity
@DiscriminatorValue("GROUP_LEVEL")
@BeanName(name = "组别级别")
public class GroupLevel extends Dictionary {

    /**
     * @category
     * @field long serialVersionUID
     * @created 2012 2012-8-30 上午9:41:34
     */
    private static final long serialVersionUID = -5500523758057337482L;

    /** 代理 */
    @BeanAttrInfo(value = "1", cnName = "代理")
    public static final long AGENT = 1;

    /** 非代理 */
    @BeanAttrInfo(value = "0", cnName = "非代理")
    public static final long NOT_AGENT = 0;
}
