package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Create 2014-06-06
 *
 * @author Hunk
 * @category Linux虚拟主机默认web文件
 */
@Entity
@DiscriminatorValue("VHOST_LINUX_DEFAULT_WEB_FILE")
@BeanName(name = "Linux虚拟主机默认web文件")
public class VhostLinuxDefaultWebFile extends Dictionary {

    private static final long serialVersionUID = 3811912981370995411L;

    @BeanAttrInfo(value = "/wwwroot/images/", cnName = "IMAGES目录")
    public static final long WEB_IMAGES = 1;

    @BeanAttrInfo(value = "/wwwroot/cgi-bin/", cnName = "CGI-BIN目录")
    public static final long WEB_CGI_BIN = 2;

    @BeanAttrInfo(value = "/wwwroot/cert/", cnName = "CERT目录")
    public static final long WEB_CERT = 3;

    @BeanAttrInfo(value = "/wwwroot/index.htm", cnName = "INDEX文件")
    public static final long WEB_INDEX = 4;
}
