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
@DiscriminatorValue("BIND_STATUS")
@BeanName(name = "绑定状态")
public class BindStatus extends Dictionary {
    private static final long serialVersionUID = 3967366270676624048L;
    @BeanAttrInfo(cnName = "绑定正常")
    public static final long BIND_SUCCESS = 1;

    @BeanAttrInfo(cnName = "已解除绑定")
    public static final long BIND_REMOVE = 2;

    @BeanAttrInfo(cnName = "信产部停止")
    public static final long MII_STOP = 3;

    @BeanAttrInfo(cnName = "空壳网站")
    public static final long NONE_SHELL_WEBSITE = 4;

    @BeanAttrInfo(cnName = "不能备案")
    public static final long CANT_RECORD = 5;

    @BeanAttrInfo(cnName = "备案信息不真实-可恢复")
    public static final long RECORD_INFO_ERROR = 6;

    @BeanAttrInfo(cnName = "备案资料附件不全")
    public static final long RECORD_DATA_ERROR = 7;

    @BeanAttrInfo(cnName = "备案信息不真实-不可恢复")
    public static final long RECORD_INFO_ERROR_DISABLED = 8;
}
