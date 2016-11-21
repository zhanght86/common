package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Create at 2013-3-12
 *
 * @author 王正伟
 * @category 域名提供商域名标准状态
 */
@Entity
@DiscriminatorValue("DOMAIN_STANDARD_STATUS")
@BeanName(name = "域名标准状态")
public class DomainStandardStatus extends Dictionary {
    private static final long serialVersionUID = 3239935815875337956L;

    @BeanAttrInfo(value = "OK", cnName = "正常")
    public static final long OK = 1;

    @BeanAttrInfo(value = "SERVERHOLD", cnName = "停止解析")
    public static final long SERVER_HOLD = 2;

    @BeanAttrInfo(value = "SERVERRENEWPROHIBITED", cnName = "禁止续费")
    public static final long SERVER_RENEW_PROHIBITED = 3;

    @BeanAttrInfo(value = "SERVERTRANSFERPROHIBITED", cnName = "禁止转移")
    public static final long SERVER_TRANSFER_PROHIBITED = 4;

    @BeanAttrInfo(value = "SERVERUPDATEPROHIBITED", cnName = "禁止更新")
    public static final long SERVER_UPDATE_PROHIBITED = 5;

    @BeanAttrInfo(value = "SERVERDELETEPROHIBITED", cnName = "禁止删除")
    public static final long SERVER_DELETE_PROHIBITED = 6;

    @BeanAttrInfo(value = "INACTIVE", cnName = "非激活状态")
    public static final long INACTIVE = 7;

    @BeanAttrInfo(value = "PENDINGCREATE", cnName = "即将创建")
    public static final long PENDING_CREATE = 8;

    @BeanAttrInfo(value = "PENDINGDELETE", cnName = "即将删除")
    public static final long PENDING_DELETE = 9;

    @BeanAttrInfo(value = "PENDINGRENEW", cnName = "即将续费")
    public static final long PENDING_RENEW = 10;

    @BeanAttrInfo(value = "PENDINGTRANSFER", cnName = "即将转移")
    public static final long PENDING_TRANSFER = 11;

    @BeanAttrInfo(value = "PENDINGUPDATE", cnName = "即将更新")
    public static final long PENDING_UPDATE = 12;

    @BeanAttrInfo(value = "CLIENTHOLD", cnName = "停止解析")
    public static final long CLIENT_HOLD = 13;

    @BeanAttrInfo(value = "CLIENTTRANSFERPROHIBITED", cnName = "禁止转移")
    public static final long CLIENT_TRANSFER_PROHIBITED = 14;

    @BeanAttrInfo(value = "CLIENTUPDATEPROHIBITED", cnName = "禁止更新")
    public static final long CLIENT_UPDATE_PROHIBITED = 15;

    @BeanAttrInfo(value = "CLIENTRENEWPROHIBITED", cnName = "禁止续费")
    public static final long CLIENT_RENEW_PROHIBITED = 16;

    @BeanAttrInfo(value = "CLIENTDELETEPROHIBITED", cnName = "禁止删除")
    public static final long CLIENT_DELETE_PROHIBITED = 17;
}
