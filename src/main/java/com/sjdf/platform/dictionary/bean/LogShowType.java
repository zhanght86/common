package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author laberwu
 * @category 日志显示类型
 * @ClassName LogShowType
 * @Created 2012 2012-10-16 下午3:05:13
 */
@Entity
@DiscriminatorValue("LOG_SHOW_TYPE")
@BeanName(name = "日志显示类型")
public class LogShowType extends Dictionary {

    /**
     * @category
     * @field long serialVersionUID
     * @created 2012 2012-10-16 下午3:05:51
     */
    private static final long serialVersionUID = -2464161667356234497L;

    /** 管理中心 */
    @BeanAttrInfo(value = "1", cnName = "管理中心可以看到超管日志")
    public static final long USER_TYPE = 1;

    /** 管理中心和控制面板 */
    @BeanAttrInfo(value = "2", cnName = "管理中心和控制面板可以看到超管日志")
    public static final long USER_AND_CONPANEL_TYPE = 2;
}
