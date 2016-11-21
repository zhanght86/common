package com.sjdf.platform.dictionary.bean.eiss;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.Dictionary;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * User: Hunk
 * Date: 2016-06-12 13:53
 * 新网互联随心邮常量
 */
@Entity
@DiscriminatorValue("MAIL_CONSTANT")
@BeanName(name = "新网互联随心邮常量")
public class MailConstant extends Dictionary {
    private static final long serialVersionUID = -115274849097588660L;

    @BeanAttrInfo(value="http://gmaster.dns.com.cn/api_socket.php", cnName = "新网互联随心邮URL")
    public static final long URL = 1;

    @BeanAttrInfo(value="agent392", cnName = "新网互联邮局代理账号（使用云工坊的）")
    public static final long AGENT = 2;

    @BeanAttrInfo(value="b9734b21a2121b73737971aa7cc7afb4", cnName = "新网互联随心邮密码（使用云工坊的）")
    public static final long AGENT_PWD = 3;

    @BeanAttrInfo(value="webmail.amail.com.cn", cnName = "新网互联随心邮密码（未备案域名别名解析地址）")
    public static final long CNAME_ADDRESS = 4;
}
