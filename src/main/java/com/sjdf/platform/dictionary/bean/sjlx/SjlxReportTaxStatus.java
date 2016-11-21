package com.sjdf.platform.dictionary.bean.sjlx;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.Dictionary;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * 报税状态
 * User: ketqi
 * Date: 2015-06-08 14:51
 */
@Entity
@DiscriminatorValue("SJLX_REPORT_TAX_STATUS")
@BeanName(name = "sjlx-报税状态")
public class SjlxReportTaxStatus extends Dictionary {
    private static final long serialVersionUID = -6023860836141796475L;

    /** 已报税 */
    @BeanAttrInfo(cnName = "已报税")
    public static final long YES = 1;
    /** 未报税 */
    @BeanAttrInfo(cnName = "未报税")
    public static final long NO = 2;
}
