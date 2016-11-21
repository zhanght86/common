package com.sjdf.platform.dictionary.bean.eiss.idc;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.Dictionary;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * User: ketqi
 * Date: 2016-06-14 17:46
 */
@Entity
@DiscriminatorValue("IDC_GLOBAL_CONFIG")
@BeanName(name = "IDC全局配置")
public class IdcGlobalConfig extends Dictionary {
    private static final long serialVersionUID = 3880228213169022139L;

    @BeanAttrInfo(value = "7", cnName = "到期下架延长时间(天)")
    public static final long EXPIRE_DOWN_DELAY = 1;
}
