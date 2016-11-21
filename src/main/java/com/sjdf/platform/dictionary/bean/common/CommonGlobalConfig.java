package com.sjdf.platform.dictionary.bean.common;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.Dictionary;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * 公共平台常量设置
 * User: ketqi
 * Date: 2014-12-31 13:52
 */
@Entity
@DiscriminatorValue("COMMON_GLOBAL_CONFIG")
@BeanName(name = "公共平台常量设置")
public class CommonGlobalConfig extends Dictionary {
    private static final long serialVersionUID = 4417300184483223333L;

    /** 是否实时权限认证1:是;其他:不是 */
    @BeanAttrInfo(value = "1", cnName = "是否实时权限认证")
    public static final long REAL_TIME_RBAC_AUTH = 1L;

    /** 短信发送失败通知邮件地址列表 */
    @BeanAttrInfo(value = "sjdfowner@51web.com", cnName = "短信发送失败通知邮件地址列表")
    public static final long SMS_FAIL_NOTIFY_EMAIL_ADRESS = 2L;

    /** 日志文件备份路径 */
    @BeanAttrInfo(value = "/data/home/log/", cnName = "日志文件备份路径")
    public static final long LOG_STORE_LOCAL_FILE_PATH = 3L;

    /** 邮件文件备份路径 */
    @BeanAttrInfo(value = "/data/home/email/", cnName = "邮件文件备份路径")
    public static final long EMAIL_STORE_LOCAL_FILE_PATH = 4L;

    /** 短信文件备份路径 */
    @BeanAttrInfo(value = "/data/home/sms/", cnName = "短信文件备份路径")
    public static final long SMS_STORE_LOCAL_FILE_PATH = 5L;
}
