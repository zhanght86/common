package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Create at 2012-08-02
 * <p/>
 * 硬件防火墙列表
 * <p/>
 * enName 表示防火墙访问的URL地址，格式：【http://IP地址:端口/】
 * <p/>
 * value 里储存登录防火墙的用户名和密码，中间用“|”符号间隔，用户名必须在前，格式：【用户名|密码】
 *
 * @author Chen Mohan
 */
@Entity
@DiscriminatorValue("FIREWALL_LIST")
@BeanName(name = "硬件防火墙列表")
public class FirewallList extends Dictionary {

    private static final long serialVersionUID = 3033758692963261599L;

    /** 绵阳电信-金盾防火墙 */
    @BeanAttrInfo(cnName = "绵阳电信-金盾防火墙", value = "51web|51webocnc.com.!@#", enName = "http://125.65.112.239:28099/")
    public static final long MIAN_YANG_DIAN_XIN_JIN_DUN_FANG_HUO_QIANG = 1;

    /** 德阳电信-金盾防火墙 */
    @BeanAttrInfo(cnName = "德阳电信-金盾防火墙", value = "51web|51webocnc.com.!@#", enName = "http://118.123.106.6:28099/")
    public static final long DE_YANG_DIAN_XIN_JIN_DUN_FANG_HUO_QIANG = 2;

    /** 北京双线-金盾防火墙 */
    @BeanAttrInfo(cnName = "北京双线-金盾防火墙", value = "51web|51webocnc.com.!@#", enName = "http://221.123.152.238:28099/")
    public static final long BEI_JING_SHUANG_XIAN_JIN_DUN_FANG_HUO_QIANG = 3;
}
