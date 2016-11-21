package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Create at 2012-12-25
 *
 * @author 邱忠福
 * @category 端口
 */
@Entity
@DiscriminatorValue("PORT")
@BeanName(name = "端口")
public class Port extends Dictionary {

    private static final long serialVersionUID = -4579614364100096759L;

    /** 开始端口号 */
    @BeanAttrInfo(value = "50000", cnName = "开始端口号")
    public static final long PORT_START = 1;

    /** 结束端口号 */
    @BeanAttrInfo(value = "55000", cnName = "结束端口号")
    public static final long PORT_STOP = 2;

    /** 保留端口号 */
    @BeanAttrInfo(value = "51022;51080;51081;51088;51389", cnName = "保留端口号")
    public static final long PORT_RETAIN = 3;

    /** 保留端口区 */
    @BeanAttrInfo(value = "51020-51039;51080-51099;51380-51399", cnName = "保留端口区")
    public static final long PORT_RETAIN_AREA = 4;

    /** 每台云主机预留端口数 */
    @BeanAttrInfo(value = "20", cnName = "每台云主机预留端口数")
    public static final long PORT_NUM_FOR_PER_CHOST = 5;

}
