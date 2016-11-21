package com.sjdf.platform.dictionary.bean.eiss.idc;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.Dictionary;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * User: ketqi
 * Date: 2016-09-05 14:19
 */
@Entity
@DiscriminatorValue("IDC_BROADCAST_STATUS")
@BeanName(name = "IDC 广播状态")
public class IdcBroadcastStatus extends Dictionary {
    private static final long serialVersionUID = -3838546368929907086L;

    @BeanAttrInfo(cnName = "已广播")
    public static final long BROADCASTED = 1L;

    @BeanAttrInfo(cnName = "未广播")
    public static final long UN_BROADCAST = 5L;
}
