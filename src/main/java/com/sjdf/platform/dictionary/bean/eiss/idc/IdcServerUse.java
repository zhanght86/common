package com.sjdf.platform.dictionary.bean.eiss.idc;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.Dictionary;

/**
 * 服务器用途
 * @date 2016年3月7日下午2:42:40
 * @author wangpeng *
 */
@Entity
@DiscriminatorValue("IDC_SERVER_USE")
@BeanName(name = "IDC服务器用途")
public class IdcServerUse extends Dictionary{

    private static final long serialVersionUID = -1176796442370507449L;

    @BeanAttrInfo(value="1", cnName="网站", enName="website")
    public static final long WEBSITE = 1;

    @BeanAttrInfo(value="2", cnName="其他", enName="other")
    public static final long other = 2;
}
