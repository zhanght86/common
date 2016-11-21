package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * 操作动作
 * Create at 2012-04-28
 *
 * @author 王正伟
 */
@Entity
@DiscriminatorValue("OPERATOR_ACTION")
@BeanName(name = "操作动作")
public class OperatorAction extends Dictionary {
    private static final long serialVersionUID = -2659822814203199658L;

    /** 添加 */
    @BeanAttrInfo(value = "add", cnName = "添加")
    public static final long ADD = 1;

    /** 修改 */
    @BeanAttrInfo(value = "modify", cnName = "修改")
    public static final long MODIFY = 2;

    /** 删除 */
    @BeanAttrInfo(value = "delete", cnName = "删除")
    public static final long DELETE = 3;

    /** 查询 */
    @BeanAttrInfo(value = "query", cnName = "查询")
    public static final long QUERY = 4;

    /** 域名解析 */
    @BeanAttrInfo(value = "domain_resolve", cnName = "域名解析")
    public static final long DOMAIN_RESOLVE = 5;

    /** 域名转发 */
    @BeanAttrInfo(value = "domain_transfer", cnName = "域名转发")
    public static final long DOMAIN_TRANSFER = 6;

    /** 执行shell命令 */
    @BeanAttrInfo(value = "exec_shell", cnName = "执行shell命令")
    public static final long EXEC_SHELL = 7;

    /** 其他 */
    @BeanAttrInfo(value = "other", cnName = "其他")
    public static final long OTHER = 8;

    /** 个人cn域名,value表示所需附件类型 */
    @BeanAttrInfo(value = "30", cnName = "个人cn域名")
    public static final long DOMAIN_PERSONAL_CN = 9;

    /** 个人企业cn域名,value表示所需附件类型 */
    @BeanAttrInfo(value = "30,31", cnName = "个人企业cn域名")
    public static final long DOMAIN_ENTERPRISE_CN = 10;

    /** 政府cn域名,value表示所需附件类型 */
    @BeanAttrInfo(value = "30,31,32", cnName = "政府cn域名")
    public static final long DOMAIN_GOV_CN = 11;

    /** 注册商转移,value表示所需附件类型 */
    @BeanAttrInfo(value = "30,33", cnName = "注册商转移")
    public static final long DOMAIN_REGISTER_TRANSFER = 12;

    /** 代理间转移,value表示所需附件类型 */
    @BeanAttrInfo(value = "30,34", cnName = "代理间转移")
    public static final long DOMAIN_AGENT_TRANSFER = 13;

    /** 域名过户,value表示所需附件类型 */
    @BeanAttrInfo(value = "35,36,37,38", cnName = "域名过户")
    public static final long DOMAIN_TRANSFER_OWNER = 14;

    /** 工单跟踪 */
    @BeanAttrInfo(cnName = "工单跟踪")
    public static final long WORKORDER_TRACK = 15;

    /** 域名注册 */
    @BeanAttrInfo(cnName = "域名注册")
    public static final long DOMAIN_REGISTRAR = 16;

    /** 域名转出 */
    @BeanAttrInfo(cnName = "域名转出")
    public static final long DOMAIN_TRANSFER_OUT = 17;

    /** 登录 */
    @BeanAttrInfo(cnName = "登录")
    public static final long LOGIN = 18;

    @BeanAttrInfo(cnName = "找回密码")
    public static final long FIND_PWD = 19;
}
