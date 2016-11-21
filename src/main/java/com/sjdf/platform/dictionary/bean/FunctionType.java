package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * 日志记录时, 功能小类
 * Create at 2012-04-28
 * 监控:4000+
 * 公司管理系统:5000+
 * 业务公共平台:6000+
 *
 * @author 王正伟
 */
@Entity
@DiscriminatorValue("FUNCTION_TYPE")
@BeanName(name = "功能小类")
public class FunctionType extends Dictionary {
    private static final long serialVersionUID = -2659822814203199658L;
    /** 购买 */
    @BeanAttrInfo(value = "1", cnName = "购买")
    public static final long BUY = 1;

    /** 续费 */
    @BeanAttrInfo(value = "2", cnName = "续费")
    public static final long RENEW = 2;

    /** 升级 */
    @BeanAttrInfo(value = "3", cnName = "升级")
    public static final long UPGRADE = 3;

    /** 产品转移 */
    @BeanAttrInfo(value = "4", cnName = "产品转移")
    public static final long PRODUCT_TRANSFER = 4;

    /** 会员备注 */
    @BeanAttrInfo(value = "5", cnName = "会员备注")
    public static final long MEMBER_NOTES = 5;

    /** 自定义产品名称 */
    @BeanAttrInfo(value = "6", cnName = "自定义产品名称")
    public static final long DIY_PRODUCT = 6;

    /** 白名单管理 */
    @BeanAttrInfo(value = "7", cnName = "白名单管理")
    public static final long CONPANEL_CHOST_DOMAIN_BINDING = 7;

    /** 免备案操作 */
    @BeanAttrInfo(value = "8", cnName = "免备案操作")
    public static final long URL_TRANSFER = 8;

    /** 客户信息管理 */
    @BeanAttrInfo(value = "9", cnName = "客户信息管理")
    public static final long CUSTOMER_INFO_MANAGE = 9;

    /** 产品密码管理 */
    @BeanAttrInfo(value = "10", cnName = "产品密码管理")
    public static final long CONPANEL_CHOST_MODIFY_PASSWORD = 10;

    /** 开关机 */
    @BeanAttrInfo(value = "11", cnName = "开关机")
    public static final long CONPANEL_CHOST_STATUS_CONTROL = 11;

    /** 系统重装 */
    @BeanAttrInfo(value = "12", cnName = "系统重装")
    public static final long CONPANEL_CHOST_RELOAD = 12;

    /** 快照恢复 */
    @BeanAttrInfo(value = "13", cnName = "快照恢复")
    public static final long CONPANEL_CHOST_SNAPSHOT_RECOVER = 13;

    /** 云主机产品管理平台登录 */
    @BeanAttrInfo(value = "14", cnName = "云主机产品管理平台登录")
    public static final long CHOST_LOGIN = 14;

    /** 产品分类设置 */
    @BeanAttrInfo(value = "15", cnName = "产品分类设置")
    public static final long PROCLS_SET = 15;

    /** 产品参数设置 */
    @BeanAttrInfo(value = "16", cnName = "产品参数设置")
    public static final long PRODUCT_SET = 16;

    /** 子IP管理 */
    @BeanAttrInfo(value = "17", cnName = "子IP管理")
    public static final long SUB_IP = 17;

    /** 操作系统配置 */
    @BeanAttrInfo(value = "18", cnName = "操作系统配置")
    public static final long SYS_SET = 18;

    /** 后台白名单管理 */
    @BeanAttrInfo(value = "19", cnName = "后台白名单管理")
    public static final long SYS_WHITE_LIST_MANAGE = 19;

    /** 云主机信息编辑 */
    @BeanAttrInfo(value = "20", cnName = "云主机信息编辑")
    public static final long CHOST_INFO = 20;

    /** 云主机删除 */
    @BeanAttrInfo(value = "21", cnName = "云主机删除")
    public static final long CHOST_DELETE = 21;

    /** 云主机状态设置 */
    @BeanAttrInfo(value = "22", cnName = "云主机状态设置")
    public static final long CHOST_STATUS_SET = 22;

    /** 云主机迁移 */
    @BeanAttrInfo(value = "23", cnName = "云主机迁移")
    public static final long CHOST_TRANSFER = 23;

    /** 云主机开设API */
    @BeanAttrInfo(value = "24", cnName = "云主机开设API")
    public static final long API_CHOST_CREATE = 24;

    /** 云主机备份FTP开设API */
    @BeanAttrInfo(value = "25", cnName = "云主机备份FTP开设API")
    public static final long API_CHOST_FTP_CREATE = 25;

    /** 云主机启动API */
    @BeanAttrInfo(value = "26", cnName = "云主机启动API")
    public static final long API_CHOST_START = 26;

    /** 云主机停止API */
    @BeanAttrInfo(value = "27", cnName = "云主机停止API")
    public static final long API_CHOST_STOP = 27;

    /** 云主机强制关机API */
    @BeanAttrInfo(value = "28", cnName = "云主机强制关机API")
    public static final long API_CHOST_POWEROFF = 28;

    /** 云主机状态检测API */
    @BeanAttrInfo(value = "29", cnName = "云主机状态检测API")
    public static final long API_CHOST_DETECT = 29;

    /** 云主机参数设置API */
    @BeanAttrInfo(value = "30", cnName = "云主机参数设置API")
    public static final long API_CHOST_SETEVN = 30;

    /** 云主机迁移API */
    @BeanAttrInfo(value = "31", cnName = "云主机迁移API")
    public static final long API_CHOST_TRANSFER = 31;

    /** 云主机升级迁移API */
    @BeanAttrInfo(value = "32", cnName = "云主机升级迁移API")
    public static final long API_CHOST_UPGRADE_TRANSFER = 32;

    /** 云主机删除API */
    @BeanAttrInfo(value = "33", cnName = "云主机删除API")
    public static final long API_CHOST_DELETE = 33;

    /** 云主机备份FTP删除API */
    @BeanAttrInfo(value = "34", cnName = "云主机备份FTP删除API")
    public static final long API_CHOST_FTP_DELETE = 34;

    /** 云主机系统备份删除 API */
    @BeanAttrInfo(value = "35", cnName = "云主机系统备份删除API")
    public static final long API_CHOST_SYS_BACKUP_DELETE = 35;

    /** 云主机暂停 API */
    @BeanAttrInfo(value = "36", cnName = "云主机暂停API")
    public static final long API_CHOST_SUSPEND = 36;

    /** 云主机恢复API */
    @BeanAttrInfo(value = "37", cnName = "云主机恢复API")
    public static final long API_CHOST_RESUME = 37;

    /** 云主机重启API */
    @BeanAttrInfo(value = "38", cnName = "云主机重启API")
    public static final long API_CHOST_REBOOT = 38;

    /** 云主机重装API */
    @BeanAttrInfo(value = "39", cnName = "云主机重装API")
    public static final long API_CHOST_REINSTALL = 39;

    /** 云主机备份FTP使用情况获取API */
    @BeanAttrInfo(value = "40", cnName = "云主机备份FTP使用情况获取API")
    public static final long API_CHOST_FTP_GETQUOTA = 40;

    /** 云主机修改备份FTP空间配额API */
    @BeanAttrInfo(value = "41", cnName = "云主机修改备份FTP空间配额")
    public static final long API_CHOST_MODIFY_FTP_GETQUOTA = 41;

    /** 云主机修改备份FTP密码API */
    @BeanAttrInfo(value = "42", cnName = "云主机修改备份FTP密码API")
    public static final long API_CHOST_MODIFY_FTP_PASSWORD = 42;

    /** 云主机获取远程管理桌面端口和密码API */
    @BeanAttrInfo(value = "43", cnName = "云主机获取远程管理桌面端口和密码API")
    public static final long API_CHOST_GET_VNCPASSWORD = 43;

    /** 云主机获取快照列表API */
    @BeanAttrInfo(value = "44", cnName = "云主机获取快照列表API")
    public static final long API_CHOST_GET_SANPSHOTLIST = 44;

    /** 云主机制作并备份快照API */
    @BeanAttrInfo(value = "45", cnName = "云主机制作并备份快照API")
    public static final long API_CHOST_CREATE_SANPSHOT = 45;

    /** 云主机设置快照保留次数API */
    @BeanAttrInfo(value = "46", cnName = "云主机设置快照保留次数API")
    public static final long API_CHOST_SET_SANPSHOTNUM = 46;

    /** 云主机恢复快照备份API */
    @BeanAttrInfo(value = "47", cnName = "云主机恢复快照备份API")
    public static final long API_CHOST_RESTORE_SANPSHOT = 47;

    /** 云主机确认恢复快照备份API */
    @BeanAttrInfo(value = "48", cnName = "云主机确认恢复快照备份API")
    public static final long API_CHOST_CONFIRM_RESTORE_SANPSHOT = 48;

    /** 云主机撤销恢复快照备份API */
    @BeanAttrInfo(value = "49", cnName = "云主机撤销恢复快照备份API")
    public static final long API_CHOST_CANCEL_RESTORE_SANPSHOT = 49;

    /** 白名单管理API */
    @BeanAttrInfo(value = "50", cnName = "白名单管理API")
    public static final long API_WHITE_LIST_MANAGE = 50;

    /** 云主机远程管理桌面 */
    @BeanAttrInfo(value = "51", cnName = "云主机远程管理桌面")
    public static final long CONPANEL_CHOST_WEB_REMOTE = 51;

    /** 自定义域名个数 */
    @BeanAttrInfo(value = "52", cnName = "自定义域名个数")
    public static final long DIY_DOMAIN_NUM = 52;

    /** 自定义产品参数 */
    @BeanAttrInfo(value = "53", cnName = "自定义产品参数")
    public static final long DIY_PRODUCT_PARA = 53;

    /** 云主机带宽设置 */
    @BeanAttrInfo(value = "54", cnName = "云主机带宽设置")
    public static final long CHOST_BANDWIDTH_SET = 54;

    /** 云主机带宽类型设置 */
    @BeanAttrInfo(value = "55", cnName = "云主机带宽类型设置")
    public static final long CHOST_BANDWIDTH_TYPE_SET = 55;

    /** 云主机CPU配额设置 */
    @BeanAttrInfo(value = "56", cnName = "云主机CPU配额设置")
    public static final long CHOST_CPU_QUOTA_SET = 56;

    /** 云主机CPU配额设置API */
    @BeanAttrInfo(value = "57", cnName = "云主机CPU配额设置API")
    public static final long API_CHOST_CPU_QUOTA_SET = 57;

    /** 重新开设 */
    @BeanAttrInfo(value = "58", cnName = "重新开设")
    public static final long RECREATE = 58;

    /** 管理员备注 */
    @BeanAttrInfo(value = "59", cnName = "管理员备注")
    public static final long ADMIN_NOTES = 59;

    /** 重置密码 */
    @BeanAttrInfo(value = "60", cnName = "重置密码")
    public static final long RESET_PASSWORD = 60;

    /** 从备份恢复已过期云主机API */
    @BeanAttrInfo(value = "61", cnName = "从备份恢复已过期云主机API")
    public static final long API_CHOST_RESTORE_FROM_BACKUP = 61;

    /** 绑定主服务器指定端口至共享ip云主机内部端口API */
    @BeanAttrInfo(value = "62", cnName = "绑定主服务器指定端口至共享ip云主机内部端口API")
    public static final long API_CHOST_BIND_SERVER_PORT_TO_SHARED_CHOST_PROT = 62;

    /** 云主机端口绑定 */
    @BeanAttrInfo(value = "63", cnName = "云主机端口绑定")
    public static final long CHOST_PORT_BINDINGS = 63;

    /** 解除绑定主服务器指定端口至共享ip云主机内部端口API */
    @BeanAttrInfo(value = "64", cnName = "解除绑定主服务器指定端口至共享ip云主机内部端口API")
    public static final long API_CHOST_UNBIND_SERVER_PORT_TO_SHARED_CHOST_PROT = 64;

    /** 备案系统ISPID生成 */
    @BeanAttrInfo(value = "65", cnName = "备案系统ISPID生成")
    public static final long RECORD_SYSTEM_ISPID_GENERATE = 65;

    /** 云主机IP设置 */
    @BeanAttrInfo(value = "66", cnName = "云主机IP设置")
    public static final long CHOST_IP_SET = 66;

    /** 修改域名DNS */
    @BeanAttrInfo(value = "67", cnName = "修改域名DNS")
    public static final long DOMAIN_DNS_MODIFY = 67;

    /** 修改白名单模式 */
    @BeanAttrInfo(value = "68", cnName = "修改白名单模式")
    public static final long WHITELIST_MODE_MODIFY = 68;

    /** 域名赎回 */
    @BeanAttrInfo(value = "69", cnName = "域名赎回")
    public static final long DOMAIN_RANSOM = 69;

    /** 修改注册信息 */
    @BeanAttrInfo(value = "70", cnName = "修改注册信息")
    public static final long DOMAIN_MODIFY_CONTACT = 70;

    /** 修改产品密码 */
    @BeanAttrInfo(value = "71", cnName = "修改产品密码")
    public static final long DOMAIN_MODIFY_PASSWORD = 71;

    /** 域名解析 */
    @BeanAttrInfo(value = "72", cnName = "域名解析")
    public static final long DOMAIN_DNS_REC = 72;

    /** 域名订单 */
    @BeanAttrInfo(value = "73", cnName = "域名订单")
    public static final long DOMAIN_ORDER = 73;

    /** 域名API注册 */
    @BeanAttrInfo(value = "74", cnName = "域名API注册")
    public static final long API_DOMAIN_REGISTER = 74;

    /** 域名会员间转移 */
    @BeanAttrInfo(value = "75", cnName = "域名会员间转移")
    public static final long DOMAIN_USER_TRANSFER = 75;

    /** 域名转出 */
    @BeanAttrInfo(value = "76", cnName = "域名转出")
    public static final long DOMAIN_TRANSFER_OUT = 76;

    /** 域名过户 */
    @BeanAttrInfo(value = "77", cnName = "域名过户")
    public static final long DOMAIN_TRANSFER_OWNER = 77;

    /** 域名转入 */
    @BeanAttrInfo(value = "78", cnName = "域名转入")
    public static final long DOMAIN_TRANSFER_IN = 78;

    /** 附件上传 */
    @BeanAttrInfo(value = "79", cnName = "附件上传")
    public static final long ATTACHMENT_UPLOAD = 79;

    /** DNS转入 */
    @BeanAttrInfo(value = "80", cnName = "DNS转入")
    public static final long DNS_TRANSFER_IN = 80;

    /** 域名后台参数设置 */
    @BeanAttrInfo(value = "81", cnName = "域名后台参数设置")
    public static final long DOMAIN_BACKSTAGE_SET = 81;

    /** CDN启用或禁用 */
    @BeanAttrInfo(value = "82", cnName = "CDN启用或禁用")
    public static final long CDN_SWITCH = 82;

    /** CDN刷新 */
    @BeanAttrInfo(value = "83", cnName = "CDN刷新")
    public static final long CDN_REFRESH = 83;

    /** 获取验证码 */
    @BeanAttrInfo(value = "84", cnName = "获取验证码")
    public static final long GET_VERIFICATION_CODE = 84;

    /** 用户找回密码 */
    @BeanAttrInfo(value = "85", cnName = "用户找回密码")
    public static final long MEMBER_FORGET_PASSWORD = 85;

    /** 授权协议 */
    @BeanAttrInfo(value = "86", cnName = "授权协议")
    public static final long AGREEMENT_LICENSE = 86;

    /** 网卡禁用 */
    @BeanAttrInfo(value = "87", cnName = "网卡禁用")
    public static final long DISABLE_NIC = 87;

    /** 网卡恢复 */
    @BeanAttrInfo(value = "88", cnName = "网卡恢复")
    public static final long RESTORE_NIC = 88;

    /** 身份模拟 */
    @BeanAttrInfo(value = "89", cnName = "身份模拟")
    public static final long ANALOG_IDENTITY = 89;

    /** 系统登录 */
    @BeanAttrInfo(value = "90", cnName = "系统登录")
    public static final long SYSTEM_LOGIN = 90;

    /** 后台黑名单管理 */
    @BeanAttrInfo(value = "91", cnName = "后台黑名单管理")
    public static final long SYS_BLACK_LIST_MANAGE = 91;

    /** 网站扫描 */
    @BeanAttrInfo(value = "92", cnName = "网站扫描")
    public static final long WEB_SITE_SCAN = 92;

    /** 添加网站扫描任务 */
    @BeanAttrInfo(value = "93", cnName = "添加网站扫描任务")
    public static final long WEB_SITE_SCAN_ADD_TASK = 93;

    /** 设置数据库状态 */
    @BeanAttrInfo(value = "94", cnName = "设置数据库状态")
    public static final long DB_SET_STATUS = 94;

    /** 修改数据库密码 */
    @BeanAttrInfo(value = "95", cnName = "修改数据库密码")
    public static final long DB_MODIFY_PWD = 95;

    /** 清理数据库事务日志 */
    @BeanAttrInfo(value = "96", cnName = "清理数据库事务日志")
    public static final long CLEAN_TRANS_LOG = 96;

    /** 数据备份 */
    @BeanAttrInfo(value = "97", cnName = "数据备份")
    public static final long DB_BACKUP = 97;

    /** 数据还原 */
    @BeanAttrInfo(value = "98", cnName = "数据还原")
    public static final long DB_RECOVERY = 98;

    /** 删除备份文件 */
    @BeanAttrInfo(value = "99", cnName = "删除备份文件")
    public static final long DB_DEL_BACKUP_FILES = 99;

    /** 清空数据库 */
    @BeanAttrInfo(value = "100", cnName = "清空数据库")
    public static final long DB_CLEAR = 100;

    /** 同步会员数据 */
    @BeanAttrInfo(value = "101", cnName = "同步会员数据")
    public static final long SYNC_MEMBER_DATA = 101;

    /** 同步基础数据 */
    @BeanAttrInfo(value = "102", cnName = "同步基础数据")
    public static final long SYNC_BASE_DATA = 102;

    /** ADSL管理 */
    @BeanAttrInfo(value = "103", cnName = "ADSL管理")
    public static final long CHOST_ADSL = 103;

    /** 消息管理 */
    @BeanAttrInfo(value = "104", cnName = "消息管理")
    public static final long COMMON_MESSAGE_MANAGE = 104;

    @BeanAttrInfo(value = "105", cnName = "云主机模板管理API")
    public static final long CHOST_TEMPLATE_MANAGE_API = 105;

    /** 会员注册 */
    @BeanAttrInfo(value = "107", cnName = "会员注册")
    public static final long MEMBER_REGISTER = 107;

    /** 会员登录 */
    @BeanAttrInfo(value = "108", cnName = "会员登录")
    public static final long MEMBER_LOGIN = 108;

    /** 退出登录 */
    @BeanAttrInfo(value = "109", cnName = "退出登录")
    public static final long MEMBER_LOGOUT = 109;

    /** 找回密码 */
    @BeanAttrInfo(value = "110", cnName = "找回密码")
    public static final long MEMBER_FIND_PWD = 110;

    /** 修改密码 */
    @BeanAttrInfo(value = "111", cnName = "修改密码")
    public static final long MEMBER_UPDATE_PWD = 111;

    /** 验证会员信息 */
    @BeanAttrInfo(value = "112", cnName = "验证会员信息")
    public static final long MEMBER_VERIFY = 112;

    /** 设置密保 */
    @BeanAttrInfo(value = "113", cnName = "设置密保")
    public static final long MEMBER_SET_SECURITY = 113;

    /** 会员导入 */
    @BeanAttrInfo(value = "114", cnName = "会员导入")
    public static final long MEMBER_IMPORT = 114;

    @BeanAttrInfo(value = "115", cnName = "自助退款")
    public static final long MEMBER_REFUND = 115;

    @BeanAttrInfo(value = "116", cnName = "退款到支付宝")
    public static final long MEMBER_REFUND_TO_ALIPAY = 116;

    @BeanAttrInfo(value = "117", cnName = "IP管理")
    public static final long IP_MANAGER = 117;

    @BeanAttrInfo(value = "118", cnName = "下载过期域名数据")
    public static final long DOWNLOAD_EXPIRED_DOMAIN = 118;

    @BeanAttrInfo(value = "119", cnName = "会员转移并续费")
    public static final long UPDATE_USER_AND_RENEW = 119;

    /** 微信授权设置 */
    @BeanAttrInfo(value = "901", cnName = "微信授权设置")
    public static final long WEI_XIN_APP_SET = 901;

    /** 微信公众账号更新 */
    @BeanAttrInfo(value = "902", cnName = "微信公众账号更新")
    public static final long WEI_XIN_ACCOUNT_UPDATE = 902;

    /** 微信公众账号授权用户管理 */
    @BeanAttrInfo(value = "903", cnName = "微信公众账号授权用户管理")
    public static final long WEI_XIN_ACCOUNT_AUTHORIZE = 903;

    /** 微信公众账号取消授权用户管理 */
    @BeanAttrInfo(value = "904", cnName = "微信公众账号取消授权用户管理")
    public static final long WEI_XIN_ACCOUNT_DEAUTHORIZE = 904;

    /** 微信公众账号微页面设置 */
    @BeanAttrInfo(value = "905", cnName = "微信公众账号微页面设置")
    public static final long WEI_XIN_MICRO_PAGE_SET = 905L;

    /** 微信过期处理 */
    @BeanAttrInfo(value = "906", cnName = "微信过期处理")
    public static final long WEI_XIN_ACCOUNT_EXPIRED = 906;

    /** 微信系统参数设置 */
    @BeanAttrInfo(value = "907", cnName = "微信系统参数设置")
    public static final long WEI_XIN_SYSTEM_PARAM_SET = 907;

    /** 微信会员信息管理 */
    @BeanAttrInfo(value = "908", cnName = "微信会员信息管理")
    public static final long WEI_XIN_USER_INFO = 908;

    /** 微信模板设置 */
    @BeanAttrInfo(value = "909", cnName = "微信模板设置")
    public static final long WEI_XIN_TEMPLATE_SET = 909;

    /** 公众账号月请求数清空自动任务 */
    @BeanAttrInfo(value = "910", cnName = "公众账号月请求数清空自动任务")
    public static final long WEI_XIN_ACCOUNT_REQUESTS_CLEAR = 910;

    /** 微信公众平台登录Session清空自动任务 */
    @BeanAttrInfo(value = "911", cnName = "微信公众平台登录Session清空自动任务")
    public static final long WEI_XIN_LOGIN_SESSION_CLEAR = 911;

    @BeanAttrInfo(value = "912", cnName = "微信公众平台保存文本回复")
    public static final long WEI_XIN_SAVE_TEXT_REPLY = 912;

    @BeanAttrInfo(value = "913", cnName = "微信公众平台保存图文回复")
    public static final long WEI_XIN_SAVE_NEWS_REPLY = 913;

    @BeanAttrInfo(value = "914", cnName = "微信公众平台保存默认设置")
    public static final long WEI_XIN_SAVE_DEFAULT_SET = 914;

    @BeanAttrInfo(value = "915", cnName = "微信公众平台保存回复")
    public static final long WEI_XIN_SAVE_REPLY = 915;

    @BeanAttrInfo(value = "916", cnName = "微信公众平台修改回复")
    public static final long WEI_XIN_UPDATE_REPLY = 916;

    @BeanAttrInfo(value = "917", cnName = "微信公众平台删除回复")
    public static final long WEI_XIN_DELETE_REPLY = 917;

    @BeanAttrInfo(value = "918", cnName = "微信公众平台批量删除回复")
    public static final long WEI_XIN_BATCH_DELETE_REPLY = 918;

    @BeanAttrInfo(value = "919", cnName = "微信公众平台保存菜单")
    public static final long WEI_XIN_SAVE_MENU = 919;

    @BeanAttrInfo(value = "920", cnName = "微信公众平台发布菜单")
    public static final long WEI_XIN_PUBLISH_MENU = 920;

    @BeanAttrInfo(value = "921", cnName = "微信公众平台停用菜单")
    public static final long WEI_XIN_STOP_MENU = 921;

    @BeanAttrInfo(value = "922", cnName = "微信公众平台保存活动")
    public static final long WEI_XIN_SAVE_ACTIVITY = 922;

    @BeanAttrInfo(value = "923", cnName = "微信公众平台删除活动")
    public static final long WEI_XIN_DELETE_ACTIVITY = 923;

    @BeanAttrInfo(value = "924", cnName = "微信公众平台微官网响应设置")
    public static final long WEI_XIN_MICRO_PAGE_REPLAY = 924;

    /** 当天用户参与活动的次数清零自动任务 */
    @BeanAttrInfo(value = "925", cnName = "当天用户参与活动的次数清零自动任务")
    public static final long WEI_XIN_ACTIVITY_CAMPAIGN_TIMES_CLEAR = 925;
    /** 活动定时开始自动任务 */
    @BeanAttrInfo(value = "926", cnName = "活动定时开始自动任务")
    public static final long WEI_XIN_ACTIVITY_AUTO_START = 926;
    @BeanAttrInfo(cnName = "虚拟主机迁移")
    public static final long VHOST_MIGRATE = 1000;

    @BeanAttrInfo(value = "1001", cnName = "产品停放")
    public static final long PRODUCT_PARK = 1001;

    @BeanAttrInfo(value = "1002", cnName = "批量续费")
    public static final long BATCH_RENEW = 1002;

    @BeanAttrInfo(cnName = "工单评价")
    public static final long WORK_ORDER_EVALUATE = 1003;

    @BeanAttrInfo(cnName = "工单指派")
    public static final long WORK_ORDER_DESIGNATE = 1004;

    @BeanAttrInfo(cnName = "工单转移")
    public static final long WORK_ORDER_TRANSFER = 1006;

    @BeanAttrInfo(cnName = "服务器项目管理")
    public static final long WORK_ORDER_SERVICE_ITEM_MANAGER = 1007;

    @BeanAttrInfo(cnName = "公共常用回复管理")
    public static final long WORK_ORDER_COMMON_REPLY = 1008;

    @BeanAttrInfo(cnName = "工单基础数据映射管理")
    public static final long WORK_ORDER_MAPPING = 1009;

    @BeanAttrInfo(cnName = "工单处理流程管理")
    public static final long WORK_ORDER_PROCESS = 1010;

    @BeanAttrInfo(cnName = "突发事件管理")
    public static final long WORK_ORDER_SERVER_EMERGENCY = 1011;

    @BeanAttrInfo(cnName = "工单编辑")
    public static final long WORK_ORDER_EDIT = 1012;

    @BeanAttrInfo(cnName = "工单自动回复内容管理")
    public static final long WORK_ORDER_TEMPLATE_CONTENT = 1013;

    @BeanAttrInfo(cnName = "超管接受工单设置")
    public static final long WORK_ORDER_ACCEPT_SET = 1014;

    @BeanAttrInfo(value = "1104", cnName = "修改产品过期时间")
    public static final long UPDATE_EXPIREDATE = 1104;

    @BeanAttrInfo(cnName = "财务-支付类型管理")
    public static final long FINANCE_REMITTANCE = 2000;

    @BeanAttrInfo(cnName = "财务-支付接口设置")
    public static final long FINANCE_REMITTANCE_API_SET = 2001;

    @BeanAttrInfo(cnName = "财务-财务流水")
    public static final long FINANCE_WATER = 2002;

    @BeanAttrInfo(cnName = "财务-入款流水")
    public static final long FINANCE_DEPOSIT_WATER = 2003;

    @BeanAttrInfo(cnName = "财务-入款流水冻结")
    public static final long FINANCE_DEPOSIT_WATER_SETTLE = 2004;

    @BeanAttrInfo(cnName = "财务-发票管理")
    public static final long FINANCE_INVOICE = 2005;

    @BeanAttrInfo(cnName = "财务-E宝管理")
    public static final long FINANCE_E_BABY = 2006;

    @BeanAttrInfo(cnName = "财务-卡密")
    public static final long FINANCE_CARD_SECRET = 2007;

    @BeanAttrInfo(cnName = "财务-价格管理")
    public static final long FINANCE_PRIVCE_MANAGE = 2008;

    @BeanAttrInfo(cnName = "财务-超管管理")
    public static final long FINANCE_MANAGER_MANAGE = 2009;

    @BeanAttrInfo(cnName = "删除数据库")
    public static final long DEL_DATABASE = 3001;

    @BeanAttrInfo(cnName = "修改附属")
    public static final long MODIFY_PARRENT = 3002;

    @BeanAttrInfo(cnName = "设置数据库大小")
    public static final long SET_DATABASE_SIZE = 3003;

    @BeanAttrInfo(cnName = "增加子目录绑定")
    public static final long ADD_SUBDIR_BINGDINGS = 3004;

    @BeanAttrInfo(cnName = "删除子目录绑定")
    public static final long DEL_SUBDIR_BINDINGS = 3005;

    @BeanAttrInfo(cnName = "子目录资源分配")
    public static final long SUBDIR_RESOURCE_ALLOCATION = 3006;

    @BeanAttrInfo(cnName = "设置子目录WEB状态")
    public static final long SET_SUBDIR_WEB_STATUS = 3007;

    @BeanAttrInfo(cnName = "子目录重定向")
    public static final long SUBDIR_REDIRECT = 3008;

    @BeanAttrInfo(cnName = "子目录执行权限")
    public static final long SUBDIR_PERMISSIONS = 3009;

    @BeanAttrInfo(cnName = "设置数据库状态")
    public static final long SET_DATABASE_STATUS = 3010;

    @BeanAttrInfo(cnName = "修改数据库密码")
    public static final long MODIFY_DATABASE_PASSWORD = 3011;

    @BeanAttrInfo(cnName = "清理事务日志")
    public static final long CLEAR_TRANSACTION_LOG = 3012;

    @BeanAttrInfo(cnName = "数据库备份/恢复")
    public static final long SET_DATABASE_INFO = 3013;

    @BeanAttrInfo(cnName = "清空数据库")
    public static final long CLEAR_DATABASE = 3014;

    @BeanAttrInfo(cnName = "数据库ip访问策略")
    public static final long SET_DATABASE_IP_POLICY = 3015;

    @BeanAttrInfo(cnName = "设置邮局状态")
    public static final long SET_MAIL_STATUS = 3016;

    @BeanAttrInfo(cnName = "设置邮局密码")
    public static final long SET_MAIL_PASSWORD = 3017;

    @BeanAttrInfo(cnName = "更换邮局域名")
    public static final long MODIFY_MAIL_DOMAIN = 3018;

    @BeanAttrInfo(cnName = "系统重装")
    public static final long SYSTEM_REINSTALL = 3019;

    @BeanAttrInfo(cnName = "数据备份")
    public static final long DATA_BACKUP = 3020;

    @BeanAttrInfo(cnName = "创建WEB站点")
    public static final long CREATE_SITE = 3021;

    @BeanAttrInfo(cnName = "创建MYSQL")
    public static final long CREATE_MYSQL = 3022;

    @BeanAttrInfo(cnName = "创建MSSQL")
    public static final long CREATE_MSSQL = 3023;

    @BeanAttrInfo(cnName = "服务器列表")
    public static final long ET_SERVER_LIST = 3024;

    @BeanAttrInfo(cnName = "机房设置")
    public static final long SET_IDC = 3025;

    @BeanAttrInfo(cnName = "价格设置")
    public static final long SET_PRICE = 3026;

    @BeanAttrInfo(cnName = "托管商设置")
    public static final long ET_SERVICE = 3027;

    @BeanAttrInfo(cnName = "产品参数设置")
    public static final long SET_PRODUCT_PARAMETER = 3028;

    @BeanAttrInfo(cnName = "产品分类设置")
    public static final long SET_PRODUCT_CLASS = 3029;

    @BeanAttrInfo(cnName = "子IP管理")
    public static final long SET_SUBIP = 3030;

    @BeanAttrInfo(cnName = "VPS用户操作系统配置")
    public static final long SET_VPS_SYSTEM = 3031;

    @BeanAttrInfo(cnName = "设置带宽限制")
    public static final long SET_BANDWIDTH = 3032;

    @BeanAttrInfo(cnName = "设置并发连接")
    public static final long SET_CONNECTIONS = 3033;

    @BeanAttrInfo(cnName = "删除站点")
    public static final long DEL_SITE = 3034;

    @BeanAttrInfo(cnName = "设定脚本映射")
    public static final long SET_SCRIPT_MAP = 3035;

    @BeanAttrInfo(cnName = "更换服务器")
    public static final long MODIFY_SERVER = 3036;

    @BeanAttrInfo(cnName = "回收应用程序池")
    public static final long RECOVER_APPLICATION = 3037;

    @BeanAttrInfo(cnName = "设置子目录绑定数量")
    public static final long SET_SUBDIR_NUM = 3038;

    @BeanAttrInfo(cnName = "设置域名绑定个数")
    public static final long SET_BINDINGS_DOMAIN_NUM = 3039;

    @BeanAttrInfo(cnName = "设置流量限制")
    public static final long SET_FLOW_LIMIT = 3040;

    @BeanAttrInfo(cnName = "重新计算磁盘配额")
    public static final long GET_DISK_QUOTA = 3041;

    @BeanAttrInfo(cnName = "产品更换")
    public static final long MODIFY_PRODUCT = 3042;

    @BeanAttrInfo(cnName = "调整产品时间")
    public static final long SET_PRODUCT_TIME = 3043;

    @BeanAttrInfo(cnName = "设置邮局大小")
    public static final long SET_MAIL_SIZE = 3044;

    @BeanAttrInfo(cnName = "设置邮局用户数")
    public static final long SET_MAIL_USER = 3045;

    @BeanAttrInfo(cnName = "删除邮局")
    public static final long DEL_MAIL = 3046;

    @BeanAttrInfo(cnName = "VPS信息编辑")
    public static final long SET_VPS_INFO = 3047;

    @BeanAttrInfo(cnName = "设置VPS状态")
    public static final long SET_VPS_STATUS = 3048;

    @BeanAttrInfo(cnName = "网站标题自动替换")
    public static final long MODIFY_SITE_TITLE = 3049;

    @BeanAttrInfo(cnName = "批量开设站点")
    public static final long BATCH_CREATE_SITE = 3050;

    @BeanAttrInfo(cnName = "批量处理工具")
    public static final long BATCH_SET_SITE = 3051;

    @BeanAttrInfo(cnName = "批量数据库开设")
    public static final long BATCH_CREATE_DATABASE = 3052;

    @BeanAttrInfo(cnName = "站点文件夹清理")
    public static final long FOLDER_CLEAR = 3053;

    @BeanAttrInfo(cnName = "IIS清理工具")
    public static final long CLEAR_IIS = 3054;

    @BeanAttrInfo(cnName = "ftp清理")
    public static final long CLEAR_FTP = 3055;

    @BeanAttrInfo(cnName = "计算机账号清理")
    public static final long CLEAR_ACCOUNT = 3056;

    @BeanAttrInfo(cnName = "vps到期通知")
    public static final long VPS_EXPIRATION_NOTICE = 3057;

    @BeanAttrInfo(cnName = "IDC管理-添加")
    public static final long IDC_ADD = 3058;

    @BeanAttrInfo(cnName = "IDC管理-修改")
    public static final long IDC_MODIFY = 3059;

    @BeanAttrInfo(cnName = "过期产品停止")
    public static final long EXPIRE_PRODUCT_STOP = 3060;

    @BeanAttrInfo(cnName = "赠送数据库过期停止")
    public static final long EXPIRE_GIFT_DATEBASE_STOP = 3061;

    @BeanAttrInfo(cnName = "超流量停止")
    public static final long FLOW_STOP = 3062;

    @BeanAttrInfo(cnName = "超流量恢复")
    public static final long FLOW_OPEN = 3063;

    @BeanAttrInfo(cnName = "解除绑定")
    public static final long REMOVE_BINDINGS = 3064;

    @BeanAttrInfo(cnName = "恢复绑定")
    public static final long RESUME_BINDINGS = 3065;

    @BeanAttrInfo(cnName = "流量收集")
    public static final long FLOW_COLLECTION = 3066;

    @BeanAttrInfo(cnName = "更新备案状态")
    public static final long UPDATE_RECORD_STATUS = 3067;

    @BeanAttrInfo(cnName = "过期删除数据")
    public static final long DEL_EXPIRE_PRODUCT = 3068;

    @BeanAttrInfo(cnName = "邮局过期停止")
    public static final long EXPIRE_MAIL_STOP = 3069;

    @BeanAttrInfo(cnName = "邮局过期删除数据")
    public static final long EL_EXPIRE_MAIL = 3070;

    @BeanAttrInfo(cnName = "cpu与流量统计")
    public static final long CPU_AND_FLOW_RECORD = 3071;

    @BeanAttrInfo(cnName = "备案检查解除与恢复")
    public static final long CHECK_RECORD = 3072;

    @BeanAttrInfo(cnName = "数据备份")
    public static final long BACKUP_PRODUCT_DATA = 3073;

    @BeanAttrInfo(cnName = "VPS带宽限制")
    public static final long VPS_BANDWIDTH_LIMIT = 3074;

    @BeanAttrInfo(cnName = "自动备份VPS的用户硬盘数据")
    public static final long BACKUP_VPS_DISK_DATA = 3075;

    @BeanAttrInfo(cnName = "cpu使用率收集")
    public static final long CPU_COLLECTION = 3076;

    @BeanAttrInfo(cnName = "同步网站模板小分类和模板设置")
    public static final long UPDATE_SITE_TEMPLATE = 3077;

    @BeanAttrInfo(cnName = "开设赠送产品")
    public static final long CREATE_GIFT_PRODUCT = 3078;

    @BeanAttrInfo(cnName = "网站与备案信息")
    public static final long RECORDSITECACHE = 3079;

    @BeanAttrInfo(cnName = "日志备份")
    public static final long BACKUP_LOG_DATA = 3080;

    @BeanAttrInfo(cnName = "修复FTP")
    public static final long BATCH_REPAIR_FTP = 3081;

    @BeanAttrInfo(cnName = "批量修复权限")
    public static final long BATCH_SET_ACCESS = 3082;

    @BeanAttrInfo(cnName = "维护工具,受控端自动更新")
    public static final long TOOLS_CLIENT_AUTO_UPDATE = 3083;

    @BeanAttrInfo(cnName = "维护工具,计算磁盘配额")
    public static final long TOOLS_COUNT_SITE_DISK_QUOTA = 3084;

    @BeanAttrInfo(cnName = "维护工具,站点运行状况检查")
    public static final long TOOLS_SCAN_WEB_SITE = 3085;

    @BeanAttrInfo(cnName = "内容替换")
    public static final long CONTEXT_REPLACE = 3086;

    @BeanAttrInfo(cnName = "vps库存统计")
    public static final long VPS_STORE_COUNT = 3087;

    @BeanAttrInfo(cnName = "会员信息修改")
    public static final long MEMBER_INFO_CHANGE = 3088;

    @BeanAttrInfo(cnName = "域名绑定")
    public static final long BINDING_DOMAIN = 3089;

    @BeanAttrInfo(cnName = "修改FTP状态")
    public static final long MODIFY_FTP_STATUS = 3090;

    @BeanAttrInfo(cnName = " 修改WEB状态")
    public static final long MODIFY_WEB_STATUS = 3091;

    @BeanAttrInfo(cnName = "数据还原")
    public static final long SET_DATA_RESTORE = 3092;

    @BeanAttrInfo(cnName = "设置默认首页")
    public static final long SET_DEFAULT_HOME = 3093;

    @BeanAttrInfo(cnName = "文件保护")
    public static final long SET_FILE_PROTECTION = 3094;

    @BeanAttrInfo(cnName = "切换ASP.NET版本")
    public static final long SWITCH_NET_VERSION = 3095;

    @BeanAttrInfo(cnName = "建站模板")
    public static final long SELECT_WEB_TEMPLATE = 3096;

    @BeanAttrInfo(cnName = "MIME设置")
    public static final long SET_MIME = 3097;

    @BeanAttrInfo(cnName = "文件压缩")
    public static final long FILE_COMPRESSION = 3098;

    @BeanAttrInfo(cnName = "文件解压")
    public static final long FILE_DECOMPRESSION = 3099;

    @BeanAttrInfo(cnName = "网站搬家")
    public static final long SITE_MOVE = 3100;

    @BeanAttrInfo(cnName = "FOS设置")
    public static final long SET_FSO = 3101;

    @BeanAttrInfo(cnName = "设置WEB拒绝IP")
    public static final long SET_REFUSED_IP = 3102;

    @BeanAttrInfo(cnName = "设置目录执行权限")
    public static final long SET_DIR_PERMISSIONS = 3103;

    @BeanAttrInfo(cnName = "设置写入权限")
    public static final long SET_WRITE_PERMISSIONS = 3104;

    @BeanAttrInfo(cnName = "站点重定向")
    public static final long SET_SITE_REDIRECT = 3105;

    @BeanAttrInfo(cnName = "网站日志提取")
    public static final long GET_SITE_LOG = 3106;

    @BeanAttrInfo(cnName = "修改FTP密码")
    public static final long MODIFY_FTP_PASSWORD = 3107;

    @BeanAttrInfo(cnName = "转移")
    public static final long SHIFT = 3108;

    @BeanAttrInfo(cnName = "转移")
    public static final long NOTES = 3109;

    @BeanAttrInfo(cnName = "切换PHP版本")
    public static final long SWITCH_PHP_VERSION = 3110;

    @BeanAttrInfo(cnName = "脚本错误响应设置")
    public static final long SET_ERROR_SCRIPT_RESPONSE = 3111;

    @BeanAttrInfo(cnName = "自定义错误页面")
    public static final long SET_ERROR_PAGE = 3112;

    @BeanAttrInfo(cnName = "空间使用报告")
    public static final long SITE_USAGE_REPORT = 3113;

    @BeanAttrInfo(cnName = "清空目录")
    public static final long CLEAR_DIR = 3114;

    @BeanAttrInfo(cnName = "攻击代理自动化")
    public static final long ATTACK_PROXY_AUTO = 3115;

    @BeanAttrInfo(cnName = "客户信息管理-会员跟单频率设定")
    public static final long MEMBER_TRACK_FREQUENCY = 3116;

    @BeanAttrInfo(cnName = "客户信息管理-会员联络内容设定")
    public static final long MEMBER_TRACK_CONTENT = 3117;

    @BeanAttrInfo(cnName = "客户信息管理-会员专员流转")
    public static final long MEMBER_TRANSFER = 3118;

    @BeanAttrInfo(cnName = "客户信息管理-联系人管理")
    public static final long MEMBER_MANAGER_CONTACT = 3119;

    @BeanAttrInfo(cnName = "客户信息管理-会员级别")
    public static final long MEMBER_USER_LEVEL = 3120;

    @BeanAttrInfo(cnName = "客户信息管理-重置密保答案")
    public static final long MEMBER_RESET_SAFETY_ANSWER = 3121;

    @BeanAttrInfo(cnName = "IDC产品管理")
    public static final long IDC_PRODUCT_MANAGER = 3122;

    @BeanAttrInfo(cnName = "建站超市管理")
    public static final long VHOST_SITE_SUPPERMARKET_MANAGER = 3123;

    @BeanAttrInfo(cnName = "链接管理")
    public static final long EXTERNAL_LINK_MANAGER = 3124;

    @BeanAttrInfo(cnName = "菜单管理")
    public static final long EISS_MENU_MANAGER = 3125;

    @BeanAttrInfo(cnName = "重置站点权限")
    public static final long RESET_SITE_PERMISSION = 3126;

    @BeanAttrInfo(cnName = "合同续签")
    public static final long IDC_RENEW_CONTRACT = 3127;

    @BeanAttrInfo(cnName = "ICP管理")
    public static final long IDC_ICP_MANAGER = 3128;

    @BeanAttrInfo(cnName = "IDC订单管理")
    public static final long IDC_ORDER_MANAGER = 3129;

    @BeanAttrInfo(cnName = "IDC资源管理")
    public static final long IDC_RES_MANAGER = 3130;

    @BeanAttrInfo(cnName = "客户信息管理-会员跟单记录")
    public static final long MEMBER_TRACK_RECORD = 3131;

    @BeanAttrInfo(cnName = "修改状态")
    public static final long COMMON_MODIFY_STATUS = 3132;

    @BeanAttrInfo(cnName = "客户信息管理-代理考核金额")
    public static final long MEMBER_RESET_PROXY_CHECK_MONEY = 3133;

    //===========================监控系统=========================
    @BeanAttrInfo(cnName = "监控处理规则")
    public static final long MONITOR_HANDLE_RULE = 4000;

    @BeanAttrInfo(cnName = "监控节点")
    public static final long MONITOR_NODE = 4001;

    @BeanAttrInfo(cnName = "监控任务")
    public static final long MONITOR_TASK = 4002;


    //===========================世纪利信===============================
    @BeanAttrInfo(cnName = "世纪利信-会员管理", systemType = SystemType.SJLX_CMS)
    public static final long SJLX_CMS_MEMBER_MANAGE = 5015;

    @BeanAttrInfo(cnName = "世纪利信-提成管理", systemType = SystemType.SJLX_CMS)
    public static final long SJLX_CMS_BONUS_MANAGE = 5002;

    @BeanAttrInfo(cnName = "世纪利信-订单管理", systemType = SystemType.SJLX_CMS)
    public static final long SJLX_CMS_ORDER_MANAGE = 5004;

    @BeanAttrInfo(cnName = "世纪利信-资料分类设置", systemType = SystemType.SJLX_CMS)
    public static final long SJLX_CMS_ATTACHMENTEN_TYPE_MANAGE = 5005;

    @BeanAttrInfo(cnName = "世纪利信-产品管理", systemType = SystemType.SJLX_CMS)
    public static final long SJLX_CMS_PRODUCT_MANAGE = 5007;

    @BeanAttrInfo(cnName = "世纪利信-公司管理", systemType = SystemType.SJLX_CMS)
    public static final long SJLX_CMS_COMPANY_MANAGE = 5008;

    @BeanAttrInfo(cnName = "世纪利信-税务申报", systemType = SystemType.SJLX_CMS)
    public static final long SJLX_CMS_TAX_REPORT = 5009;

    @BeanAttrInfo(cnName = "世纪利信-发送验证码", systemType = SystemType.SJLX_CMS)
    public static final long SJLX_CMS_SEND_CODE = 5010;

    @BeanAttrInfo(cnName = "世纪利信-重置密码", systemType = SystemType.SJLX_CMS)
    public static final long SJLX_CMS_REST_PWD = 5011;

    @BeanAttrInfo(cnName = "世纪利信-修改密码", systemType = SystemType.SJLX_CMS)
    public static final long SJLX_CMS_MODIFY_PWD = 5012;

    @BeanAttrInfo(cnName = "世纪利信-会员注册", systemType = SystemType.SJLX_CMS)
    public static final long SJLX_CMS_MEMBER_REGISTER = 5013;

    @BeanAttrInfo(cnName = "世纪利信-资料管理", systemType = SystemType.SJLX_CMS)
    public static final long SJLX_CMS_ATTACHMENTEN_MANAGE = 5014;

    @BeanAttrInfo(cnName = "世纪利信-记账管理基础数据生成自动任务", systemType = SystemType.SJLX_CMS)
    public static final long SJLX_CMS_TAX_BASE_DATA_PRODUCE = 5015;

    @BeanAttrInfo(cnName = "添加企业", systemType = SystemType.SJLX_CMS)
    public static final long SJLX_CMS_COMPANY_ADD = 5020;

    @BeanAttrInfo(cnName = "修改企业", systemType = SystemType.SJLX_CMS)
    public static final long SJLX_CMS_COMPANY_MODIFY = 5021;

    @BeanAttrInfo(cnName = "添加企业核准申请", systemType = SystemType.SJLX_CMS)
    public static final long SJLX_CMS_COMPANY_ADD_HE = 5022;

    @BeanAttrInfo(cnName = "修改企业核准申请", systemType = SystemType.SJLX_CMS)
    public static final long SJLX_CMS_COMPANY_MODIFY_HE = 5023;

    @BeanAttrInfo(cnName = "会员状态修改", systemType = SystemType.SJLX_CMS)
    public static final long SJLX_MEMBER_STATUS_MODIFY = 5025;

    @BeanAttrInfo(cnName = "修改个人资料", systemType = SystemType.SJLX_CMS)
    public static final long SJLX_MEMBER_INFO_MODIFY = 5026;

    @BeanAttrInfo(cnName = "模板管理", systemType = SystemType.SJLX_CMS)
    public static final long SJLX_TEMPLATE_MANAGE = 5027;

    @BeanAttrInfo(cnName = "模板映射管理", systemType = SystemType.SJLX_CMS)
    public static final long SJLX_TEMPLATE_MAP_MANAGE = 5028;

    @BeanAttrInfo(cnName = "百度云加速")
    public static final long BAIDU_YUNJIASU = 5655;


    @BeanAttrInfo(cnName = "关联信息", systemType = SystemType.EISS_COMMON)
    public static final long COMMON_PLATFORM_ASSOCIATE = 60001;

    @BeanAttrInfo(cnName = "数据模板", systemType = SystemType.EISS_COMMON)
    public static final long COMMON_PLATFORM_TEMPLATE = 60002;

    @BeanAttrInfo(cnName = "配置库", systemType = SystemType.EISS_COMMON)
    public static final long COMMON_PLATFORM_DICTIONARY = 60003;

    @BeanAttrInfo(cnName = "邮件或短信模板", systemType = SystemType.EISS_COMMON)
    public static final long COMMON_PLATFORM_MESSAGE_TEMPLATE = 60004;

    @BeanAttrInfo(cnName = "页面内容管理", systemType = SystemType.EISS_COMMON)
    public static final long COMMON_PLATFORM_CONTENT_MANAGE = 60006;

    @BeanAttrInfo(cnName = "打印地址", systemType = SystemType.EISS_COMMON)
    public static final long COMMON_PLATFORM_PRINT_ADDRESS = 60007;

    @BeanAttrInfo(cnName = "权限", systemType = SystemType.EISS_COMMON)
    public static final long COMMON_PLATFORM_PERMISSION = 60008;

    @BeanAttrInfo(cnName = "角色", systemType = SystemType.EISS_COMMON)
    public static final long COMMON_PLATFORM_ROLE = 60009;

    @BeanAttrInfo(cnName = "用户", systemType = SystemType.EISS_COMMON)
    public static final long COMMON_PLATFORM_USER = 60010;

    /****************** 贷款审核系统 ****************/
    @BeanAttrInfo(cnName = "贷款申请资料")
    public static final long LOAN_APPLY_MATERIAL = 70000;

    /** 其他 */
    @BeanAttrInfo(value = "999", cnName = "其他")
    public static final long OTHER = 999;
}
