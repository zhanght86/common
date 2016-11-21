package com.sjdf.platform.dictionary.bean.eiss.idc;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.Dictionary;

/**
 * IDC设备状态
 * @date 2016年5月11日下午10:12:46
 * @author wangpeng
 */
@Entity
@DiscriminatorValue("EQUIPMENT_STATUS")
@BeanName(name = "IDC设备状态")
public class EquipmentStatus extends Dictionary{

    private static final long serialVersionUID = -1102408315855236486L;

    @BeanAttrInfo(cnName = "空机箱")
    public static final long EMPTY = 1;

    @BeanAttrInfo(cnName = "未使用")
    public static final long UNUSED = 2;

    @BeanAttrInfo(cnName = "已使用")
    public static final long USED = 3;
}
