package com.sjdf.platform.dictionary.bean.ers;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.Dictionary;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author sjdf
 * @category 备案系统角色权限
 */
@Entity
@DiscriminatorValue("ERS_ROLE_AUTHORITY")
@BeanName(name = "备案系统_角色权限")
public class ErsRoleAuthority extends Dictionary {

    /**
     * 序列化
     */
    private static final long serialVersionUID = -7982691831442448947L;

    /** 普通会员 */
    @BeanAttrInfo(value = "1", cnName = "普通会员")
    public static final long ORDINARY_MEMBER = 1L;

    /** 代理管理员 */
    @BeanAttrInfo(value = "2", cnName = "代理管理员")
    public static final long AGENT_MANAGER = 2L;

    /** 超级管理员 */
    @BeanAttrInfo(value = "3", cnName = "超级管理员")
    public static final long SUPER_MANAGER = 3L;

    /** 备案审核人员 */
    @BeanAttrInfo(value = "5", cnName = "备案审核人员")
    public static final long RECORD_REVIEWER = 5L;

    /** 特权人员 */
    @BeanAttrInfo(value = "6", cnName = "特权人员")
    public static final long PRIVILEGE = 6L;

}
