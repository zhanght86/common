package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author laberwu
 * @category 是否需要收集白名单确认信息
 * @ClassName NeedRecordWhiteListInfo
 * @Created 2012 2012-8-22 下午6:46:23
 */
@Entity
@DiscriminatorValue("NEED_RECORD_WHITE_LIST_INFO")
@BeanName(name = "是否需要收集白名单确认信息")
public class NeedRecordWhiteListInfo extends Dictionary {

    /**
     * @category
     * @field long serialVersionUID
     * @created 2012 2012-8-22 下午6:47:23
     */
    private static final long serialVersionUID = -691198449432036304L;
    /** 需要收集白名单确认信息 */
    @BeanAttrInfo(value = "1", cnName = "是否需要收集白名单确认信息 ")
    public static final long NEED_RECORD_WHITE_LIST_INFO = 1;

}
