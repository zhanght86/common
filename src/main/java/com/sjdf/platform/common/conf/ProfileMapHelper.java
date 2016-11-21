package com.sjdf.platform.common.conf;

/**
 * 配置文件Key的映射
 * Create at 2012-4-23
 *
 * @author 王正伟
 */
public abstract class ProfileMapHelper {
    /** 访问common平台的域名 */
    public static final String PLATFORM_COMMON_51WEB_COM_DOMAIN = "platform.common.51web.com.domain";
    /** 配置库初始化路径 */
    public static final String INIT_CONFIG_MANAGER_URL = "init.config.manager.url";

    /** 系统邮件服务器主机名 */
    public static final String SYSTEM_EMAIL_HOST = "system.email.host";

    /** 系统邮件服务器地址 */
    public static final String SYSTEM_EMAIL_ADDRESS = "system.email.address";

    /** 系统邮件发送方姓名或机构 */
    public static final String SYSTEM_EMAIL_FROM_NAME = "system.email.from.name";

    /** 系统邮件服务器用户名 */
    public static final String SYSTEM_EMAIL_USERNAME = "system.email.username";

    /** 系统邮件服务器密码 */
    public static final String SYSTEM_EMAIL_PWD = "system.email.password";

    /** config of menu definition */
    public static final String CONFIG_MENU = "config.menu";

    /** 消息扩展顶级域名 */
    public static final String MESSAGE_EXTERNAL_TOP_DOMAIN = "message.external.top.domain";

    /** 获取会员手机号码或者邮件地址 */
    public static final String MESSAGE_EXTERNAL_API_URL = "message.external.api.url";
}
