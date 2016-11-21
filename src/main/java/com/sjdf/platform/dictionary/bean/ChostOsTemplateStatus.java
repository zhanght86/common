package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * 2015-08-19
 *
 * @author wangpeng
 * @category 云主机操作系统模板状态
 */
@Entity
@DiscriminatorValue("CHOST_OS_TEMPLATE_STATUS")
@BeanName(name = "云主机操作系统模板状态")
public class ChostOsTemplateStatus extends Dictionary {

    private static final long serialVersionUID = -6049555606318078699L;

    @BeanAttrInfo(value = "0", cnName = "创建成功")
    public static final long SUCCESS = 0;

    @BeanAttrInfo(value = "1", cnName = "创建中")
    public static final long CREATING = 1;

    @BeanAttrInfo(value = "2", cnName = "创建失败")
    public static final long FAILURE = 2;
}
