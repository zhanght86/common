package com.sjdf.platform.dictionary.bean.eiss;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.Dictionary;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author Hunk
 * @category 空间问题检查
 */
@Entity
@DiscriminatorValue("VHOST_WEB_CHECK")
@BeanName(name = "空间问题检查")
public class VhostWebCheck extends Dictionary {

    /**
     * serial
     */
    private static final long serialVersionUID = 4217188431966948465L;

    /** 检查有结果显示信息 */
    @BeanAttrInfo(value = "请确认如下域名的解析IP是否和【空间IP】一致，如果不一致，请修改解析到【空间别名地址】。", cnName = "检查有结果显示信息")
    public static final long CHECK_RESULT_TIP = 1L;

    /** 检查没有结果显示信息 */
    @BeanAttrInfo(value = "您的问题请到<a href=\"http://client.51web.com/workorder/home/\" target=\"_blank\"\\>工单系统</a>中提交问题，技术工程师会为你处理并回复的（一般情况1小时）。", cnName = "检查没有结果显示信息")
    public static final long CHECK_NO_RESULT_TIP = 2L;
}
