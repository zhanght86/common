package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * create at 2012-08-14
 *
 * @author 陈绍刚
 * @category 云主机FTP端口配置
 */
@Entity
@DiscriminatorValue("CHOST_FTP_PORT")
@BeanName(name = "云主机FTP端口配置")
public class ChostFtpPort extends Dictionary {

    private static final long serialVersionUID = 3905662418743593332L;

    /** 云主机FTP端口 */
    @BeanAttrInfo(value = "51021", cnName = "云主机FTP端口")
    public static final long PORT = 1;

}
