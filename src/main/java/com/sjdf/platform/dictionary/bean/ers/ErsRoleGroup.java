package com.sjdf.platform.dictionary.bean.ers;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.Dictionary;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author sjdf
 * @category 备案系统角色组别
 */
@Entity
@DiscriminatorValue("ERS_ROLE_GROUP")
@BeanName(name = "备案系统_角色组别")
public class ErsRoleGroup extends Dictionary {

    /**
     * 序列化
     */
    private static final long serialVersionUID = -7374182722709696771L;

    /** 普通会员 */
    @BeanAttrInfo(value = "1", cnName = "普通会员")
    public static final long ORDINARY_MEMBER = 1L;

    /** 代理管理员 */
    @BeanAttrInfo(value = "2", cnName = "代理管理员")
    public static final long AGENT_MANAGER = 2L;

    /** 超级管理员 */
    @BeanAttrInfo(value = "3", cnName = "超级管理员")
    public static final long SUPER_MANAGER = 3L;

}
