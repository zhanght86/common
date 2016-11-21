package com.sjdf.platform.dictionary.bean.sjlx;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.Dictionary;

/**
 * create in 2016年8月23日
 * @category 流程分类配置库
 * @author wangpeng
 */
@Entity
@DiscriminatorValue("SJLX_PROCESS_TYPE")
@BeanName(name = "sjlx-流程分类")
public class SjlxProcessType extends Dictionary {

    private static final long serialVersionUID = 120258225746062779L;

    @BeanAttrInfo(cnName = "默认流程分类")
    public static final long DEFAULT_TYPE = 1L;
}
