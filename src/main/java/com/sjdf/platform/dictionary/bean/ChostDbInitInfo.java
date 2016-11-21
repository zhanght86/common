package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * create at 2012-10-11
 *
 * @author 黄远昌
 * @category 云主机管理助手数据库初始信息
 */
@Entity
@DiscriminatorValue("CHOST_DB_INIT_INFO")
@BeanName(name = "云主机管理助手数据库初始信息")
public class ChostDbInitInfo extends Dictionary {
    private static final long serialVersionUID = -6574820186553808577L;
    /** 管理助手MySQL默认帐户 */
    @BeanAttrInfo(value = "root", cnName = "管理助手MySQL默认帐户")
    public static final long MYSQL_ACCOUNT = 1;
    /** 管理助手MySQL初始密码 */
    @BeanAttrInfo(value = "iDoaSrTKM%T5iI4!", cnName = "管理助手MySQL初始密码")
    public static final long MYSQL_PASSWORD = 2;
    /** 管理助手MSSQL默认帐户 */
    @BeanAttrInfo(value = "sa", cnName = "管理助手MSSQL默认帐户")
    public static final long MSSQL_ACCOUNT = 3;
    /** 管理助手MSSQL初始密码 */
    @BeanAttrInfo(value = "mFF9%##iDD8p*Uc@", cnName = "管理助手MSSQL初始密码")
    public static final long MSSQL_PASSWORD = 4;
    /** WDCP_MySQL默认帐户 */
    @BeanAttrInfo(value = "root", cnName = "WDCP_MySQL默认帐户")
    public static final long WDCP_MYSQL_ACCOUNT = 5;
    /** WDCP_MySQL初始密码 */
    @BeanAttrInfo(value = "wdlinux.cn", cnName = "WDCP_MySQL初始密码")
    public static final long WDCP_MYSQL_PASSWORD = 6;
    /** WDCP_MSSQL默认帐户 */
    @BeanAttrInfo(value = "", cnName = "WDCP_MSSQL默认帐户")
    public static final long WDCP_MSSQL_ACCOUNT = 7;
    /** WDCP_MSSQL初始密码 */
    @BeanAttrInfo(value = "", cnName = "WDCP_MSSQL初始密码")
    public static final long WDCP_MSSQL_PASSWORD = 8;
}
