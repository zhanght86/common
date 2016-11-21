package com.sjdf.platform.dictionary.bean.sjlx;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.Dictionary;
import com.sjdf.platform.dictionary.bean.ValidMark;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("SJLX_SERVICE_STATUS")
@BeanName(name = "sjlx-服务状态")
public class SjlxServiceStatus extends Dictionary {
    private static final long serialVersionUID = -568336230472747969L;

    /** 正常 */
    @BeanAttrInfo(cnName = "正常")
    public static final long NORMAL = ValidMark.VALID;

    /** 即将到期（1个月以内） */
    @BeanAttrInfo(cnName = "即将到期（1个月以内）")
    public static final long BECOMING_DUE = 3;

    /** 服务结束 */
    @BeanAttrInfo(cnName = "服务结束")
    public static final long SERVICE_END = ValidMark.INVALID;

    /** 终止合作 */
    @BeanAttrInfo(cnName = "终止合作")
    public static final long STOP_COOPERATION = 4;
}
