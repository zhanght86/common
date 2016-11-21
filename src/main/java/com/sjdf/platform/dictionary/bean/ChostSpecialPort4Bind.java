package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Create at 2013-1-4
 *
 * @author 邱忠福
 * @category 绑定至云主机的目标端口号
 */
@Entity
@DiscriminatorValue("CHOST_SPECIAL_PORT_FOR_BIND")
@BeanName(name = "绑定至云主机的目标端口号")
public class ChostSpecialPort4Bind extends Dictionary {

    private static final long serialVersionUID = -3741924312517816222L;

    /** 系统类型为Windows的云主机端口号 */
    @BeanAttrInfo(value = "21;1433;3306", cnName = "系统类型为Windows的云主机端口号")
    public static final long WINDOWS = 1;

    /** 系统类型为Linux的云主机端口号 */
    @BeanAttrInfo(value = "21;3306;8080", cnName = "系统类型为Linux的云主机端口号")
    public static final long LINUX = 2;
}
