package com.sjdf.platform.dictionary.bean.eiss.idc;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.Dictionary;

/**
 * IDC产品状态
 * @date 2016年3月7日上午10:57:56
 * @author wangpeng
 */
@Entity
@DiscriminatorValue("IDC_PRODUCT_STATUS")
@BeanName(name = "IDC产品状态")
public class IdcProductStatus extends Dictionary{

    private static final long serialVersionUID = 972115073124454935L;

    @BeanAttrInfo(value="1", cnName="处理中", enName="processing")
    public static final long PROCESSING = 1;

    @BeanAttrInfo(value="3", cnName="正常", enName="normal")
    public static final long NORMAL = 3;

    @BeanAttrInfo(value="4", cnName="到期", enName="expired")
    public static final long EXPIRED = 4;

    @BeanAttrInfo(value="5", cnName="停机", enName="Stopped")
    public static final long STOPPED = 5;

    @BeanAttrInfo(value="6", cnName="已下架", enName="has off shelf")
    public static final long HAS_OFF_SHELF = 6;

    @BeanAttrInfo(value="6", cnName="待用", enName="has off shelf")
    public static final long STANDBY = 10;
}
