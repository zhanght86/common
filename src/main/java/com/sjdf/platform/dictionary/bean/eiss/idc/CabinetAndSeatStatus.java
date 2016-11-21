package com.sjdf.platform.dictionary.bean.eiss.idc;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.Dictionary;

/**
 * 机柜和机位状态配置
 * @date 2016年3月8日下午3:33:46
 * @author wangpeng *
 */
@Entity
@DiscriminatorValue("CABINET_AND_SEAT_STATUS")
@BeanName(name = "IDC机柜和机位状态")
public class CabinetAndSeatStatus extends Dictionary{

    private static final long serialVersionUID = -1185172579085003831L;

    @BeanAttrInfo(value="1" ,cnName = "空闲" ,enName = "free")
    public static final long FREE = 1;

    @BeanAttrInfo(value="2" ,cnName = "预定" ,enName = "booked")
    public static final long BOOKED = 2;

    @BeanAttrInfo(value="3" ,cnName = "使用" ,enName = "used")
    public static final long USED = 3;
}
