package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Create 2014-06-06
 *
 * @author Hunk
 * @category Windows虚拟主机默认web文件
 */
@Entity
@DiscriminatorValue("VHOST_WINDOWS_DEFAULT_WEB_FILE")
@BeanName(name = "Windows虚拟主机默认web文件")
public class VhostWindowsDefaultWebFile extends Dictionary {

    private static final long serialVersionUID = 5176251468791295082L;

    @BeanAttrInfo(value = "\\web\\cert\\", cnName = "CERT目录")
    public static final long WEB_CERT = 1;

    @BeanAttrInfo(value = "\\web\\index.htm", cnName = "INDEX文件")
    public static final long WEB_INDEX = 2;
}
