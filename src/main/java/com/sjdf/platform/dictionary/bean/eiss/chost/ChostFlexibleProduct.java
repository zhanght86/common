package com.sjdf.platform.dictionary.bean.eiss.chost;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.Dictionary;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author sjdf
 * @category 云主机弹性购买产品设置
 */
@Entity
@DiscriminatorValue("CHOST_FLEXIBLE_PRODUCT")
@BeanName(name = "云主机弹性购买产品设置")
public class ChostFlexibleProduct extends Dictionary {

    /**
     * serial
     */
    private static final long serialVersionUID = 1421985401051784375L;

    public static final String CPU_FLAG = "cpu";
    public static final String MEMORY_FLAG = "memory";
    public static final String DISK_FLAG = "disk";
    public static final String BANDWIDTH_FLAG = "bandwidth";
    public static final String IDC_FLAG = "idcId";
    public static final String IP_FLAG = "ip";
    public static final String DOMAIN_FLAG = "domain";
    public static final String PRICE_TYPE_FLAG = "priceType";
    public static final String PRICE_UNIT_FLAG = "priceUnit";

    /** 经济型 */
    @BeanAttrInfo(value = "{cpu:1,memory:2,disk:100,bandwidth:5,idcId:14,priceId:159029,couponId:0,num:1,domain:0,ip:0,priceType:6,priceUnit:1}", orderBy = 1, cnName = "经济型")
    public static final long ECONOMY = 1;

    /** 商务型 */
    @BeanAttrInfo(value = "{cpu:2,memory:8,disk:300,bandwidth:20,idcId:14,priceId:159029,couponId:0,num:1,domain:0,ip:0,priceType:6,priceUnit:1}", orderBy = 2, cnName = "商务型")
    public static final long BUSINESS = 2;

    /** 尊贵型 */
    @BeanAttrInfo(value = "{cpu:8,memory:32,disk:500,bandwidth:80,idcId:14,priceId:159029,couponId:0,num:1,domain:0,ip:0,priceType:6,priceUnit:1}", orderBy = 2, cnName = "尊贵型")
    public static final long DLX = 3;

}
