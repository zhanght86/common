package com.sjdf.platform.dictionary.bean.eiss;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.Dictionary;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Create at 2014年5月4日 下午5:58:37
 *
 * @author KETQI
 *         工单状态(待受理，已派单，处理中，已回复，已解决，已评价)
 */
@Entity
@DiscriminatorValue("WORK_ORDER_STATUS")
@BeanName(name = "工单状态")
public class WorkOrderStatus extends Dictionary {
    private static final long serialVersionUID = 2278469409263024444L;

    /** 待受理 */
    @BeanAttrInfo(cnName = "待受理")
    public static final long WAIT_HANDLE = 1;

    /** 待指派 */
    @BeanAttrInfo(cnName = "待指派")
    public static final long WATE_ASSIGEN = 2;

    /** 已派单 */
    @BeanAttrInfo(cnName = "已派单", enName = "已受理")
    public static final long APPOINTED = 5;

    /** 处理中 */
    @BeanAttrInfo(cnName = "处理中")
    public static final long HANDING = 10;

    /** 未回复 */
    @BeanAttrInfo(cnName = "未回复")
    public static final long NOTREPLY = 13;

    /** 已回复 */
    @BeanAttrInfo(cnName = "已回复", enName = "待评价")
    public static final long REPLIED = 15;
    /** 智能回复 */
    @BeanAttrInfo(cnName = "智能回复")
    public static final long INTELLIGENT_RESPONSE = 17;

    /** 已解决 */
    @BeanAttrInfo(cnName = "已解决")
    public static final long HANDLED_COMPLETE = 20;

    /** 已评价 */
    @BeanAttrInfo(cnName = "已评价")
    public static final long EVALUATED = 25;
}
