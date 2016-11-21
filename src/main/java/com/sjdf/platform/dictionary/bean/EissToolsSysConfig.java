package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author sjdf
 * @category web工具系统配置
 */
@Entity
@DiscriminatorValue("EISS_TOOLS_SYS_CONFIG")
@BeanName(name = "web工具系统配置")
public class EissToolsSysConfig extends Dictionary {

    /**
     * 序列化ID
     */
    private static final long serialVersionUID = -7158436857225826562L;

    /** 备案清理自动任务运行省份： */
    @BeanAttrInfo(value = "", orderBy = 1, cnName = "备案清理自动任务运行省份")
    public static final long AUTO_TASK_RECORD_CLEAN_PROVINCE = 1;

    /** 备案清理自动任务停止产品状态(1:可恢复;2：不可恢复)： */
    @BeanAttrInfo(value = "1", orderBy = 2, cnName = "备案清理自动任务停止产品状态(1:可恢复;2：不可恢复)")
    public static final long AUTO_TASK_RECORD_CLEAN_STOP_PRODUCT_STATUS = 2;

}
