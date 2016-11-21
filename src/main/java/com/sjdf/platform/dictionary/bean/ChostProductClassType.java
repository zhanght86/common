package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.common.utils.PlatformUtils;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.cache.ConfigManager;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;

/**
 * create at 2012-06-15
 *
 * @author 陈绍刚
 * @category 云主机产品分类类型
 */
@Entity
@DiscriminatorValue("CHOST_PRODUCT_CLASS_TYPE")
@BeanName(name = "云主机产品分类类型 ")
public class ChostProductClassType extends Dictionary {
    private static final long serialVersionUID = -2495245880503687417L;
    /** 标准产品 */
    @BeanAttrInfo(value = "vhost,chost,mail", cnName = "标准产品")
    public static final long PRODUCT = 1L;
    /** CPU部件产品 */
    @BeanAttrInfo(value = "chost,idc,res", cnName = "CPU部件产品")
    public static final long CPU = 2L;
    /** 内存部件产品 */
    @BeanAttrInfo(value = "chost,idc,res", cnName = "内存部件产品")
    public static final long MEM = 3L;
    /** 硬盘部件产品 */
    @BeanAttrInfo(value = "chost,idc,res", cnName = "硬盘部件产品")
    public static final long DISK = 4L;
    /** 有效访问域名数部件产品 */
    @BeanAttrInfo(value = "chost", cnName = "有效访问域名数部件产品")
    public static final long BINDINGS = 5L;
    /** IP数部件产品 */
    @BeanAttrInfo(value = "vhost,chost,idc", cnName = "IP数部件产品")
    public static final long ZIIPS = 6L;
    /** 弹性购买产品 */
    @BeanAttrInfo(value = "chost,mail", cnName = "弹性购买产品")
    public static final long FLEXIBLE_BUY = 7L;
    /** 弹性带宽部件产品 */
    @BeanAttrInfo(value = "chost,idc", cnName = "弹性带宽部件产品")
    public static final long BANDWIDTH = 8L;
    /** 弹性IP部件产品 */
    @BeanAttrInfo(value = "chost", cnName = "弹性IP部件产品")
    public static final long FLEXIBLE_IP = 9L;
    /** 弹性域名部件产品 */
    @BeanAttrInfo(value = "chost", cnName = "弹性域名部件产品")
    public static final long FLEXIBLE_DOMAIN = 10L;
    /** 弹性CPU部件产品 */
    @BeanAttrInfo(value = "chost", cnName = "弹性CPU部件产品")
    public static final long FLEXIBLE_CPU = 11L;
    /** 弹性内存部件产品 */
    @BeanAttrInfo(value = "chost", cnName = "弹性内存部件产品")
    public static final long FLEXIBLE_MEM = 12L;
    /** 弹性硬盘部件产品 */
    @BeanAttrInfo(value = "chost", cnName = "弹性硬盘部件产品")
    public static final long FLEXIBLE_DISK = 13L;

    @BeanAttrInfo(value = "idc", cnName = "共享带宽")
    public static final long SHARE_BANDWIDTH = 20L;

    @BeanAttrInfo(value = "idc", cnName = "机柜")
    public static final long MACHINE_CABINET = 21L;

    @BeanAttrInfo(value = "idc", cnName = "电力")
    public static final long ELECTRICITY = 25L;

    @BeanAttrInfo(value = "idc", cnName = "机位")
    public static final long MACHINE_SEAT = 30L;

    @BeanAttrInfo(value = "idc", cnName = "操作系统")
    public static final long OS = 35L;

    @BeanAttrInfo(value = "idc", cnName = "增值业务")
    public static final long EXTEND_SERVICE = 40L;

    @BeanAttrInfo(value = "idc", cnName = "品牌")
    public static final long MACHINE_BRAND = 45L;

    @BeanAttrInfo(value = "idc,res", cnName = "型号")
    public static final long MACHINE_MODEL = 50L;

    @BeanAttrInfo(value = "idc", cnName = "raid")
    public static final long RAID = 55L;

    @BeanAttrInfo(value = "idc,res", cnName = "电源")
    public static final long POWER_SUPPLY = 60L;

    @BeanAttrInfo(value = "idc", cnName = "网卡")
    public static final long NIC = 65L;

    @BeanAttrInfo(value = "idc", cnName = "产权")
    public static final long PROPERTY = 70L;

    @BeanAttrInfo(value = "idc,res", cnName = "防火墙")
    public static final long FIREWALL = 75;

    @BeanAttrInfo(value = "idc,res", cnName = "交换机")
    public static final long SWITCH = 80;

    /**
     * 根据类型获取部件产品列表
     *
     * @param value 类型;财务系统PubProductClsBean子类中的DiscriminatorValue值
     * @return 部件产品列表
     */
    public static List<ChostProductClassType> getProductClassTypeList(String value) {
        List<ChostProductClassType> list = new ArrayList<>();
        if (!PlatformUtils.hasText(value)) {
            return list;
        }

        List<ChostProductClassType> allList = ConfigManager.getInstance().getDictionary(ChostProductClassType.class);
        String val;
        for (ChostProductClassType type : allList) {
            val = type.getValue();
            if (PlatformUtils.hasText(val) && val.contains(value)) {
                list.add(type);
            }
        }
        return list;
    }
}
