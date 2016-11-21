package com.sjdf.platform.dictionary.bean.eiss;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.Dictionary;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * 软件类型
 * User: ketqi
 * Date: 2015-01-16 09:27
 */
@Entity
@DiscriminatorValue("SOFTWARE_TYPE")
@BeanName(name = "软件类型")
public class SoftwareType extends Dictionary {
    private static final long serialVersionUID = 6836570052875031926L;

    @BeanAttrInfo(cnName = "齐齐挂机助手")
    public static final long HANGUP_HELPER = 1L;
}
