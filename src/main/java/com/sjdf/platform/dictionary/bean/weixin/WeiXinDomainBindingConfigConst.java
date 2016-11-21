package com.sjdf.platform.dictionary.bean.weixin;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.Dictionary;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author hunk
 * @category 微信域名绑定配置常量
 * @Create 2014-4-4
 */
@Entity
@DiscriminatorValue("WEIXIN_DOMAIN_BINDING_CONFIG_CONST")
@BeanName(name = "微信域名绑定配置常量")
public class WeiXinDomainBindingConfigConst extends Dictionary {

    private static final long serialVersionUID = 51374562693319462L;

    /** 配置worker */
    @BeanAttrInfo(value = "\tJkMount /* ajp13_worker_56002\n", cnName = "配置worker")
    public static final long CONFIG_WORKER = 1L;

    /** 配置文件路径 */
    @BeanAttrInfo(value = "/etc/httpd/domain/binding.conf", cnName = "配置文件路径")
    public static final long CONFIG_FILE_PATH = 2L;

    /** 重载配置文件命令 */
    @BeanAttrInfo(value = "sudo /etc/init.d/httpd reload", cnName = "重载配置文件命令")
    public static final long CONFIG_RELOAD_EXECUTE_CMD = 3L;
}
