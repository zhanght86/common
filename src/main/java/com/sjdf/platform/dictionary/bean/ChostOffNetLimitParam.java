package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Create at 2013年11月26日 下午17:55:24
 *
 * @author 黄远昌
 * @category 云主机断网限制参数
 */
@Entity
@DiscriminatorValue("CHOST_OFFNET_LIMIT_PARAM")
@BeanName(name = "云主机断网限制参数")
public class ChostOffNetLimitParam extends Dictionary {

    private static final long serialVersionUID = 816847196262287851L;

    @BeanAttrInfo(value = "2", orderBy = 1, cnName = "超服务器实际带宽次数")
    public static final long TIMES = 1;

    @BeanAttrInfo(value = "2", orderBy = 2, cnName = "超服务器实际带宽倍数")
    public static final long MULTIPLE = 2;

    @BeanAttrInfo(value = "0", orderBy = 3, cnName = "发包率断网开关(0:关闭,1:打开)")
    public static final long PACKET_SWITCH = 3;

    @BeanAttrInfo(value = "2000", orderBy = 4, cnName = "发包率阀值")
    public static final long PACKET_RATE = 4;

    @BeanAttrInfo(value = "1", orderBy = 5, cnName = "超发包率阀值次数")
    public static final long PACKET_TIMES = 5;
}
