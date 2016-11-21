package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * 2015-09-18
 *
 * @author wangpeng
 * @category IP宿主类型
 */
@Entity
@DiscriminatorValue("IP_HOST_CLASS")
@BeanName(name = "IP宿主类型")
public class IPHostClass extends Dictionary {

    private static final long serialVersionUID = -2653750530970480720L;

    @BeanAttrInfo(cnName = "服务器", enName = "server")
    public static final long SERVER = 1;

    @BeanAttrInfo(cnName = "子IP", enName = "subIP")
    public static final long SUB_IP = 2;
}
