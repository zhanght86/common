package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Create at 2012-06-06
 *
 * @author 黄远昌
 * @category 虚拟局域网
 */

@Entity
@DiscriminatorValue("VLAN")
@BeanName(name = "虚拟局域网")
public class Vlan extends Dictionary {
    private static final long serialVersionUID = -7650715492384077291L;

    /** 德阳-虚拟局域网40 */
    @BeanAttrInfo(value = "dy_vlan40", cnName = "德阳-虚拟局域网40")
    public static final long DY_VLAN40 = 1;

    /** 德阳-虚拟局域网50 */
    @BeanAttrInfo(value = "dy_vlan50", cnName = "德阳-虚拟局域网50")
    public static final long DY_VLAN50 = 2;

    /** 德阳-虚拟局域网60 */
    @BeanAttrInfo(value = "dy_vlan60", cnName = "德阳-虚拟局域网60")
    public static final long DY_VLAN60 = 3;

    /** 绵阳-虚拟局域网116 */
    @BeanAttrInfo(value = "my_vlan116", cnName = "绵阳-虚拟局域网116")
    public static final long MY_VLAN116 = 4;

    /** 绵阳-虚拟局域网138 */
    @BeanAttrInfo(value = "my_vlan138", cnName = "绵阳-虚拟局域网138")
    public static final long MY_VLAN138 = 5;

    /** 绵阳-虚拟局域网154 */
    @BeanAttrInfo(value = "my_vlan154", cnName = "绵阳-虚拟局域网154")
    public static final long MY_VLAN154 = 6;

    /** 绵阳-虚拟局域网160 */
    @BeanAttrInfo(value = "my_vlan160", cnName = "绵阳-虚拟局域网160")
    public static final long MY_VLAN160 = 7;

    /** 绵阳-虚拟局域网178 */
    @BeanAttrInfo(value = "my_vlan178", cnName = "绵阳-虚拟局域网178")
    public static final long MY_VLAN178 = 8;
}
