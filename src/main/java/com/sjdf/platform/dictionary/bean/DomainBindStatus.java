package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Create at 2013-9-9
 *
 * @author 许长贵
 * @category 域名绑定状态
 */
@Entity
@DiscriminatorValue("DOMAIN_BIND_STATUS")
@BeanName(name = "域名绑定状态")
public class DomainBindStatus extends Dictionary {
    private static final long serialVersionUID = -1839884639994303823L;

    @BeanAttrInfo(cnName = "绑定正常")
    public static final long BIND_SUCCESS = 0;

    @BeanAttrInfo(cnName = "已解除绑定")
    public static final long BIND_REMOVE = 1;

    @BeanAttrInfo(cnName = "管理员停止")
    public static final long ADMIN_STOP = 2;

    @BeanAttrInfo(cnName = "备案信息不真实-可恢复")
    public static final long RECORD_UNREAL_RECOVERABLE = 3;

    @BeanAttrInfo(cnName = "备案资料附件不全")
    public static final long RECORD_DATA_ERROR = 4;

    @BeanAttrInfo(cnName = "备案信息不真实-不可恢复")
    public static final long RECORD_UNREAL_UNRECOVERABLE = 5;

}
