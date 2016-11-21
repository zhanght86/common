package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Create at 2012-05-22
 *
 * @author 王正伟
 * @category 服务器分类
 */
@Entity
@DiscriminatorValue("SERVER_CLASS")
@BeanName(name = "服务器分类")
public class ServerClass extends Dictionary {
    private static final long serialVersionUID = -6941727372910520143L;

    /** 虚拟主机、数据库、邮局 */
    @BeanAttrInfo(value = "1", cnName = "虚拟主机、数据库、邮局")
    public static final long VHOST_DATABASE_MAIL = 1;

    /** VPS */
    @BeanAttrInfo(value = "2", cnName = "VPS")
    public static final long VPS = 2;

    /** IDC */
    @BeanAttrInfo(value = "3", cnName = "IDC")
    public static final long IDC = 3;

    /** 内部使用 */
    @BeanAttrInfo(value = "4", cnName = "内部使用")
    public static final long ERNAL = 4;

    /** 云主机 */
    @BeanAttrInfo(value = "5", cnName = "云主机主服务器")
    public static final long CHOST = 5;

    /** CDN */
    @BeanAttrInfo(value = "6", cnName = "CDN")
    public static final long CDN = 6;

    /** DNS */
    @BeanAttrInfo(value = "7", cnName = "DNS")
    public static final long DNS = 7;

    /** MAIL */
    @BeanAttrInfo(value = "8", cnName = "邮局")
    public static final long MAIL = 8;

    /** DB */
    @BeanAttrInfo(value = "9", cnName = "数据库")
    public static final long DB = 9;

    // 虚拟主机主服务器和远程卡为课题2789新增分类

    @BeanAttrInfo(value = "10", cnName = "虚拟主机主服务器")
    public static final long VHOST_PHYSICAL_SERVER = 10;

    @BeanAttrInfo(value = "11", cnName = "远程卡")
    public static final long REMOTE_CARD = 11;
}
