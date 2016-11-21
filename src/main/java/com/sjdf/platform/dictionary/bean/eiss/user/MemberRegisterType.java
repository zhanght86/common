package com.sjdf.platform.dictionary.bean.eiss.user;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.Dictionary;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * User: ketqi
 * Date: 2015-10-15 17:02
 */
@Entity
@DiscriminatorValue("MEMBER_REGISTER_TYPE")
@BeanName(name = "会员注册类别")
public class MemberRegisterType extends Dictionary {
    private static final long serialVersionUID = -8749044415446629419L;

    @BeanAttrInfo(cnName = "注册")
    public static final long REGISTER = 1;

    @BeanAttrInfo(cnName = "导入")
    public static final long IMPORT = 5;
}
