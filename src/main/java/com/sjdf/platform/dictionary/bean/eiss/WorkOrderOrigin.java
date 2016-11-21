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
 *         工单来源
 */
@Entity
@DiscriminatorValue("WORK_ORDER_ORIGIN")
@BeanName(name = "工单来源")
public class WorkOrderOrigin extends Dictionary {
    private static final long serialVersionUID = 5280580561303586436L;


    /** 管理中心 */
    @BeanAttrInfo(cnName = "管理中心")
    public static final long MANAGER_CENTER = 1;

    /** 控制面板 */
    @BeanAttrInfo(cnName = "控制面板")
    public static final long CONPANEL = 5;

    /** 代理平台 */
    @BeanAttrInfo(cnName = "代理平台")
    public static final long PROXY_PLAFORM = 10;

}
