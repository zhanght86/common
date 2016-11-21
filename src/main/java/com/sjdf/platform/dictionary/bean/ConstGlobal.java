package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.CommonPlatformConstant;
import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.cache.ConfigManager;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Create at 2012-05-22
 * <p>
 * 公共常量
 *
 * @author 王正伟
 */
@Entity
@DiscriminatorValue("CONST_GLOBAL")
@BeanName(name = "公共常量")
public class ConstGlobal extends Dictionary {
    private static final long serialVersionUID = 6358479219478130599L;

    /** 主配置文件名 */
    @BeanAttrInfo(value = "main.conf", cnName = "主配置文件名")
    public static final long MAIN_CONF = 1;

    /** 连接密码 */
    @BeanAttrInfo(value = "&A-anB<'=R6eW_#N|,D93 g)4q?fskdW", cnName = "连接密码")
    public static final long CONNECT_PASSWORD = 2;

    /** 固定列表文件名 */
    @BeanAttrInfo(value = "FixedList.conf", cnName = "固定列表文件名")
    public static final long FIXED_LIST_FILE_NAME = 3;

    /** 默认首页 */
    @BeanAttrInfo(value = "index.htm,index.html,index.shtml,index.asp,index.php,index.aspx,defualt.htm,default.html,default.shtml,default.asp,default.php,default.aspx", cnName = "默认首页")
    public static final long DEFAULT_DOC = 4;

    /** 维护工具-任务类别-批量开设站点 */
    @BeanAttrInfo(value = "1", cnName = "维护工具-任务类别-批量开设站点")
    public static final long BATCH_CREATE_SITE = 5;

    /** 维护工具-任务类别-批量处理工具 */
    @BeanAttrInfo(value = "2", cnName = "维护工具-任务类别-批量处理工具")
    public static final long BATCH_DISPOSE = 6;

    /** 维护工具-任务类别-批量开设数据库 */
    @BeanAttrInfo(value = "3", cnName = "维护工具-任务类别-批量开设数据库")
    public static final long BATCH_CREATE_DATABASE = 7;

    /** 维护工具-任务类别-修复FTP */
    @BeanAttrInfo(value = "4", cnName = "维护工具-任务类别-修复FTP")
    public static final long BATCH_REPAIR_FTP = 8;

    /** 维护工具-任务类别-批量修复权限 */
    @BeanAttrInfo(value = "5", cnName = "维护工具-任务类别-批量修复权限")
    public static final long BATCH_SET_ACCESS = 9;

    /** 维护工具-任务类别-计算磁盘配额 */
    @BeanAttrInfo(value = "6", cnName = "维护工具-任务类别-计算磁盘配额")
    public static final long TOOLS_CALC_SITE_QUOTA = 10;

    /** 维护工具-任务类别-自动更新受控端 */
    @BeanAttrInfo(value = "7", cnName = "维护工具-任务类别-自动更新受控端")
    public static final long TOOLS_AUTO_UPDATE_CLIENT = 11;

    /** 维护工具-任务类别-站点运行状况检查 */
    @BeanAttrInfo(value = "8", cnName = "维护工具-任务类别-站点运行状况检查")
    public static final long TOOLS_SCAN_WEB_SITE = 12;

    /** 无法获取当前登录ID所返回的值 */
    @BeanAttrInfo(value = "99999999", cnName = "无法获取当前登录帐号所返回的值")
    public static final long UNKNOWN_USER_ID = 13;

    /** 无法获取当前登录帐号所返回的值 */
    @BeanAttrInfo(value = "unknown", cnName = "无法获取当前登录帐号所返回的值")
    public static final long UNKNOWN_USER = 14;

    /** 无法获取当前访问者IP地址所返回的值 */
    @BeanAttrInfo(value = "0.0.0.0", cnName = "无法获取当前访问者IP地址所返回的值")
    public static final long UNKNOWN_IP = 15;

    /** 虚拟主机购买单号前缀 */
    @BeanAttrInfo(value = "vho", cnName = "虚拟主机购买单号前缀")
    public static final long VHOST_ORDER_PREFIX = 16;

    /** 云主机购买单号前缀 */
    @BeanAttrInfo(value = "cho", cnName = "云主机主机购买单号前缀")
    public static final long CHOST_ORDER_PREFIX = 17;

    /** 数据库购买单号前缀 */
    @BeanAttrInfo(value = "dbo", cnName = "数据库购买单号前缀")
    public static final long DATABASE_ORDER_PREFIX = 18;

    /** 邮局购买单号前缀 */
    @BeanAttrInfo(value = "mso", cnName = "邮局购买单号前缀")
    public static final long MAIL_ORDER_PREFIX = 19;

    /** VPS购买单号前缀 */
    @BeanAttrInfo(value = "vmo", cnName = "VPS购买单号前缀")
    public static final long VPS_ORDER_PREFIX = 20;

    /** 虚拟主机产品ID前缀 */
    @BeanAttrInfo(value = "vh", cnName = "虚拟主机产品ID前缀")
    public static final long VHOST_PRODUCT_ID_PREFIX = 21;

    /** 云主机产品ID前缀 */
    @BeanAttrInfo(value = "ch", cnName = "云主机产品ID前缀")
    public static final long CHOST_PRODUCT_ID_PREFIX = 22;

    /** 数据库产品ID前缀 */
    @BeanAttrInfo(value = "db", cnName = "数据库产品ID前缀")
    public static final long DATABASE_PRODUCT_ID_PREFIX = 23;

    /** 邮局产品ID前缀 */
    @BeanAttrInfo(value = "ms", cnName = "邮局产品ID前缀")
    public static final long MAIL_PRODUCT_ID_PREFIX = 24;

    /** VPS产品ID前缀 */
    @BeanAttrInfo(value = "vm", cnName = "VPS产品ID前缀")
    public static final long VPS_PRODUCT_ID_PREFIX = 25;

    /** 已购买数据Bean信息 */
    @BeanAttrInfo(value = "info", cnName = "已购买数据Bean信息")
    public static final long SESSION_BEAN = 26;

    /** 日志文件 */
    @BeanAttrInfo(value = "file", cnName = "日志文件")
    public static final long LOG4J_FILE = 27;

    /** 单位 */
    @BeanAttrInfo(value = "MB", cnName = "单位")
    public static final long NUIT = 28;

    /** 链接到数据库 */
    @BeanAttrInfo(value = "conpanel/database/ConPanelDBAction!getDBInfo.action?databaseBean.dbName=", cnName = "链接到数据库")
    public static final long DATABASE_LINK_PATH = 29;

    /** 链接到邮局 */
    @BeanAttrInfo(value = "conpanel/mail/ConPanelMailAction!getMailInfo.action?mailBean.domain=", cnName = "链接到邮局")
    public static final long MAIL_LINK_PATH = 30;

    /** 链接到虚拟主机 */
    @BeanAttrInfo(value = "conpanel/vhost/ConPanelVHAction!getVHInfo.action?vhostBean.siteName=", cnName = "链接到虚拟主机")
    public static final long VHOST_LINK_PATH = 31;

    /** 链接到虚拟主机 */
    @BeanAttrInfo(value = "conpanel/chost/ConPanelCHAction!getCHInfo.action?chostBean.wanIp=", cnName = "链接到云主机")
    public static final long CHOST_LINK_PATH = 32;

    /** 退款入通用款 */
    @BeanAttrInfo(value = "退款入通用款", cnName = "退款入通用款")
    public static final long SPECIAL_MONEY = 33;

    /** 免备案转发服务器地址 */
    @BeanAttrInfo(value = "m.938030968.com", cnName = "免备案转发服务器地址")
    public static final long NOT_RECORD_URL = 34;

    /** 免备案转发服务器地址(新) */
    @BeanAttrInfo(value = "c.938030968.com", cnName = "免备案转发服务器地址(新)")
    public static final long NOT_RECORD_URL_NEW_BY_CHOST = 35;

    /** 云主机主机购买单号起始值 */
    @BeanAttrInfo(value = "00036", cnName = "云主机主机购买单号起始值")
    public static final long CHOST_ORDER_MIN_NUM = 36;

    /** 根据是否需要备案限制登陆代理控制面 */
    @BeanAttrInfo(value = "1", cnName = "根据是否需要备案限制登陆代理控制面")
    public static final long LIMIT_AGENT_LOGIN_BY_RECORD = 37;

    /** 代理用咨询反馈提示信息 */
    @BeanAttrInfo(value = "为使您的问题能得到及时准确的处理，请正确选择问题的分类，并详细描写您的问题。", cnName = "代理用咨询反馈提示信息")
    public static final long AGENT_ASK_TIP_MESSAGE = 38;

    /** 域名转出手续费 */
    @BeanAttrInfo(value = "20", cnName = "域名转出手续费")
    public static final long DOMAIN_TRANSFER_OUT_PAY = 39;

    /** 主张数据存放根目录;必须以/结尾 */
    @BeanAttrInfo(value = "D:/datacenter/", cnName = "主张数据存放根目录")
    public static final long EISS_DATA_CENTER_PATH = 40;

    /** 获取域名证书所需时间 */
    @BeanAttrInfo(value = "5", cnName = "获取域名证书所需时间")
    public static final long DOMAIN_CERTIFICATE_NEED_TIME = 41;

    /** 域名允许删除的天数 */
    @BeanAttrInfo(value = "3", cnName = "域名允许删除的天数")
    public static final long DOMAIN_DELETE_DAYS = 42;

    /**
     * 域名转入我司的顶级商
     *
     * @see com.sjdf.platform.dictionary.bean.DomainRegister
     */
    @BeanAttrInfo(value = "2,3,4,7,8,9", cnName = "域名转入我司的顶级商")
    public static final long DOMAIN_TRANSFER_OUR_COMPANY_REGISTER = 43;

    /** 域名转入我司的域名后缀 */
    @BeanAttrInfo(value = ".COM,.NET", cnName = "域名转入我司的域名后缀")
    public static final long DOMAIN_TRANSFER_OUR_COMPANY_DOMSUFFIX = 44;

    /** 域名转入我司的域名日期限制 */
    @BeanAttrInfo(value = "8", cnName = "域名转入我司的域名日期限制")
    public static final long DOMAIN_TRANSFER_OUR_COMPANY_DATE_RESTRICTION = 45;

    /** 域名转入我司的转移专用邮箱 */
    @BeanAttrInfo(value = "transferMe@51hkidc.com", cnName = "域名转入我司的转移专用邮箱")
    public static final long DOMAIN_TRANSFER_OUR_COMPANY_DEDICATE_MAIL = 46;

    /** 域名距离上次成功操作的时间的限制 */
    @BeanAttrInfo(value = "60", cnName = "域名距离上次成功操作的时间的限制")
    public static final long SINCE_THE_LAST_SUCCESSFUL = 47;

    /** 域名续费后的最大年限 */
    @BeanAttrInfo(value = "10", cnName = "域名续费后的最大年限")
    public static final long RENEWALS_MAXIMUM = 48;

    /** 域名转入我司的备注说明 */
    @BeanAttrInfo(value = "客户不需要操作，待我司处理", cnName = "域名转入我司的备注说明")
    public static final long TRANSFER_IN_NOTES = 49;

    /** 域名转出订单自动任务删除的天数限制 */
    @BeanAttrInfo(value = "30", cnName = "域名转出订单自动任务删除的天数限制")
    public static final long TRANSFER_OUT_DELETE_DAYS = 50;

    /** 主站首页新闻公告显示数目 */
    @BeanAttrInfo(value = "5", cnName = "主站首页新闻公告显示数目", systemType = SystemType.EISS)
    public static final long EISS_HOME_NEWS_BULLETIN_NUM = 51;

    /** 主站首页域名后缀最大显示个数 */
    @BeanAttrInfo(value = "9", cnName = "主站首页域名后缀最大显示个数", systemType = SystemType.EISS)
    public static final long EISS_HOME_DOMAIN_SUFFIX_MAX = 52;

    /** 修改域名所有者邮箱的到期天数限制（用于自动任务） */
    @BeanAttrInfo(value = "90", cnName = "修改域名所有者邮箱的到期天数限制（用于自动任务）")
    public static final long AUTOTASK_EDIT_REGISTER_EMAIL_DAYS = 53;

    /** 还原域名的所有者邮箱的天数条件（用于自动任务） */
    @BeanAttrInfo(value = "5", cnName = "还原域名的所有者邮箱的天数条件（用于自动任务）")
    public static final long AUTOTASK_REVERT_REGISTER_EMAIL_DAYS = 54;

    /** 域名转入我司的域名日期限制(商务中国) */
    @BeanAttrInfo(value = "15", cnName = "域名转入我司的域名日期限制(商务中国)")
    public static final long DOMAIN_TRANSFER_OUR_COMPANY_DATE_RESTRICTION_BIZCN = 55;

    /** 主站首页发票客户显示数目 */
    @BeanAttrInfo(value = "100", cnName = "主站首页发票客户显示数目")
    public static final long EISS_HOME_INVOICE_CUSTOMER_NUM = 56;

    /** 主站代理申请通知邮箱 */
    @BeanAttrInfo(value = "agent@51web.com", cnName = "主站代理申请通知邮箱")
    public static final long EISS_HOME_AGENT_APPLY_INFORM_EMAIL = 57;

    /** 商务中国授权协议发送邮件通知的频率 */
    @BeanAttrInfo(value = "30", cnName = "商务中国授权协议发送邮件通知的频率")
    public static final long BIZNCN_LICENSE_AGREEMENT_FREQUENCY = 58;

    /** 跳转首页时间 */
    @BeanAttrInfo(value = "10", cnName = "跳转首页时间")
    public static final long JUMP_HOME_TIME = 59;

    /** static1.51web.com静态服务器物理地址 */
    @BeanAttrInfo(value = "/home/wwwroot/new_static_51web", cnName = "static1.51web.com静态服务器物理地址")
    public static final long STATIC1_51WEB_PHYSICAL_ADDRESS = 60;

    /** static1.51web.com静态服务器web地址 */
    @BeanAttrInfo(value = "http://static1.51web.com", cnName = "static1.51web.com静态服务器web地址")
    public static final long STATIC1_51WEB_WEB_ADDRESS = 61;

    /** 公司联系电话 */
    @BeanAttrInfo(value = "4000-120-151", cnName = "公司联系电话")
    public static final long EISS_CONTACT_PHONE = 62;

    /** 主站前台价格总览显示的域名后缀 */
    @BeanAttrInfo(value = ".COM,.NET", cnName = "主站前台价格总览显示的域名后缀")
    public static final long EISS_HOME_PRICE_SHOW_SUFFIX = 63;

    /** cn域名自动任务确认时间 */
    @BeanAttrInfo(value = "3", cnName = "cn域名自动任务确认时间")
    public static final long DOMAINCN_REGISTER_CONFIRM = 64;

    /** 主站前台价格总览显示的域名后缀 */
    @BeanAttrInfo(value = "http://spihome.cdnhost.cn", cnName = "主站静态资源部署路径")
    public static final long EISS_HOME_STATIC_PATH = 65;

    /** 商务中国自动任务修改所有者邮箱日期限制的最小值 */
    @BeanAttrInfo(value = "45", cnName = "商务中国自动任务修改所有者邮箱日期限制的最小值")
    public static final long AUTOTASK_EDIT_REGISTER_EMAIL_MIN_DAYS = 66;

    /** 商务中国解锁的日期跨度 */
    @BeanAttrInfo(value = "30", cnName = "商务中国解锁的日期跨度")
    public static final long UNLOCK_THE_DATE_SPAN = 67;

    /** 主站根URL */
    @BeanAttrInfo(value = "http://www.51web.com", cnName = "主站根URL")
    public static final long HOME_BASE_URL = 68;

    /** 管理中心根URL */
    @BeanAttrInfo(value = "http://client.51web.com", cnName = "管理中心根URL")
    public static final long USER_BASE_URL = 69;

    /** 管理中心PHP根URL */
    @BeanAttrInfo(value = "http://user.51web.com", cnName = "管理中心PHP根URL")
    public static final long USER_PHP_BASE_URL = 70;

    /** 业务后台根URL */
    @BeanAttrInfo(value = "http://newcp.51web.com", cnName = "业务后台根URL")
    public static final long ADMIN_BASE_URL = 71;

    /** 控制面板根URL */
    @BeanAttrInfo(value = "http://www.cdnhost.cn", cnName = "控制面板根URL")
    public static final long CONPANEL_BASE_URL = 72;

    /** 帮助中心根URL */
    @BeanAttrInfo(value = "http://help.51web.com", cnName = "帮助中心根URL")
    public static final long HELP_BASE_URL = 73;

    /** 备案系统根URL */
    @BeanAttrInfo(value = "http://beian.51web.com", cnName = "备案系统根URL")
    public static final long BEIAN_BASE_URL = 74;

    /** 业务公共平台根URL */
    @BeanAttrInfo(value = "http://common.51web.com", cnName = "业务公共平台根URL")
    public static final long COMMON_BASE_URL = 75;

    /** 域名转出到期前的天数限制 */
    @BeanAttrInfo(value = "30", cnName = "域名转出到期前的天数限制")
    public static final long DOMAIN_TRANSFER_OUT_DAYS = 76;

    /** 域名转入自动任务确定的天数限制 */
    @BeanAttrInfo(value = "8", cnName = "域名转入自动任务确定的天数限制")
    public static final long DOMAIN_TRANSFER_IN_MAX_DAYS = 77;

    /** 域名过户自动任务确定的天数限制 */
    @BeanAttrInfo(value = "10", cnName = "域名过户自动任务确定的天数限制")
    public static final long DOMAIN_TRANSFER_OWNER_MAX_DAYS = 78;

    /** 云主机购买链接超时时间 */
    @BeanAttrInfo(value = "60000", cnName = "云主机购买链接超时时间")
    public static final long CHOST_BUY_CONNECT_TIME_OUT = 79;

    /** 云主机断网抄送的地址 */
    @BeanAttrInfo(value = "business@51web.com;agent@51web.com", cnName = "云主机断网抄送的地址")
    public static final long NIC_CC_PERSONNEL = 80;

    /** 特权用户 */
    @BeanAttrInfo(value = "yangleicd;huangyuanchang;", cnName = "特权用户")
    public static final long PRIVILEGED_USER = 81;

    /** 网卡恢复的时间基数（单位：天） */
    @BeanAttrInfo(value = "1", cnName = "网卡恢复的时间基数（单位：天）")
    public static final long RECOVERY_TIME_BASE = 82;

    /** 仅使用我司DNS未绑定我司产品的删除产品说明 */
    @BeanAttrInfo(value = "该域名不在我司且已没有绑定到我司产品，不能继续使用我司DNS，如果需要使用我司DNS，请先将域名绑定到我司产品。", cnName = "仅使用我司DNS未绑定我司产品,删除产品说明")
    public static final long USE_OUR_DNS_UNBOUND_OURS = 83;

    /** web工具系统备案附件清理扫描多线程个数 */
    @BeanAttrInfo(value = "10", cnName = "web工具系统备案附件清理扫描多线程个数")
    public static final long WEB_TOOLS_RECRD_ATTACHMENT_CLEAN_THREAD_COUNT = 84;

    /** 获取不到whois信息的域名后缀 */
    @BeanAttrInfo(value = ".中国;.公司;.网络", cnName = "获取不到whois信息的域名后缀")
    public static final long WHOIS_INFORMATION_ACQUISITION_LESS = 85;

    /** 首页显示微信二维码广告词 */
    @BeanAttrInfo(value = "扫一扫,三重优惠等您拿！", cnName = "微信广告词")
    public static final long HOME_SHOW_WEIXIN_ADVERT = 86;

    /** SCANV网站安全检测token值 */
    @BeanAttrInfo(value = "IP0RhYhPCHEwM6oa", cnName = "SCANV网站安全检测token值")
    public static final long SCANV_TOKEN = 87;

    /** 转发域名集合 */
    @BeanAttrInfo(value = "url-forward.cn", cnName = "转发域名集合")
    public static final long URL_FORWARD_DOMAIN = 88;

    /** 微信公众账号产品ID前缀 */
    @BeanAttrInfo(value = "wx", cnName = "微信公众账号产品ID前缀")
    public static final long WEIXIN_PRODUCT_ID_PREFIX = 89;

    /** 微信平台根URL */
    @BeanAttrInfo(value = "http://weixin.51web.com", cnName = "微信平台根URL")
    public static final long WEI_XIN_BASE_URL = 90;

    /** 微信静态服务器物理地址(static.weixin.51web.com) */
    @BeanAttrInfo(value = "/home/wwwroot/static.weixin.51web", cnName = "微信静态服务器物理地址(static.weixin.51web.com)")
    public static final long STATIC_WEI_XIN_51WEB_PHYSICAL_ADDRESS = 91;

    /** 微信静态服务器web地址(static.weixin.51web.com) */
    @BeanAttrInfo(value = "http://static.weixin.51web.com", cnName = "微信静态服务器物理地址(static.weixin.51web.com)")
    public static final long STATIC_WEI_XIN_51WEB_WEB_ADDRESS = 92;

    /** 是否收集客户信息 */
    @BeanAttrInfo(value = "93", cnName = "是否收集客户信息")
    public static final long WHETHER_COLLECT_CUSTOMER_INFO = 93L;

    /** 域名相关上传文件最小大小 */
    @BeanAttrInfo(value = "50", cnName = "上传文件最小值")
    public static final long DOMAIN_FILE_UPLOAD_MIN = 94;
    /** 域名相关上传文件最大大小 */
    @BeanAttrInfo(value = "300", cnName = "上传文件最大值")
    public static final long DOMAIN_FILE_UPLOAD_MAX = 95;
    /** 域名相关上传文件的单位 */
    @BeanAttrInfo(value = "1024", cnName = "KB")
    public static final long DOMAIN_FILE_UPLOAD_UNIT = 96;

    /** 拥有自己服务器会员名（必须用;结束） */
    @BeanAttrInfo(value = "100ucard;e8088cd;zjisp;", cnName = "拥有自己服务器会员名（必须用;结束）")
    public static final long SERVER_OWNER = 97;

    /** 代理控制面板登陆产品所属会员名称(默认为云工坊控制面板) */
    @BeanAttrInfo(value = "zjisp", cnName = "代理控制面板登陆产品所属会员名称(默认为云工坊控制面板)")
    public static final long AGENT_CONPANEL_LOGIN_OWNER = 98;

    /** 虚拟主机打开某文件的写权限时将为该用户添加该文件的完全控制权限 */
    @BeanAttrInfo(value = "reglimit", cnName = "reglimit用户名")
    public static final long WEB_USERNAME = 100;

    /** 网居会员名 */
    @BeanAttrInfo(value = "e8088cd", cnName = "网居会员名")
    public static final long SERVER_1818_OWNER = 101;

    /** 数据清理每次处理的记录数 */
    @BeanAttrInfo(value = "1000", cnName = "数据清理每次处理的记录数")
    public static final long DATA_CLEAR_RECORDS = 102;

    /** 建站超市授权账户 */
    @BeanAttrInfo(value = "51web83", cnName = "建站超市授权账户")
    public static final long SUPERMARKET_AUTHORIZED_ACCOUNT = 103;

    /**
     * 当前公司类别
     *
     * @see com.sjdf.platform.dictionary.bean.CompanyClass
     */
    @BeanAttrInfo(value = "1", cnName = "当前公司类别")
    public static final long CURR_COMPANY_CLASS = 104;

    @BeanAttrInfo(value = "5", cnName = "相同主机记录相同线路上A记录条数上限")
    public static final long DNS_RECORD_LIMIT_FOR_A = 105;

    /** 备案系统根URL */
    @BeanAttrInfo(value = "http://beian.zjisp.cn", cnName = "zjisp备案系统根URL")
    public static final long YGF_BEIAN_BASE_URL = 106;

    /** 备案系统根URL */
    @BeanAttrInfo(value = "http://beian.e8088.com", cnName = "e8088备案系统根URL")
    public static final long WANGJU_BEIAN_BASE_URL = 107;

    /** 虚拟主机免备案转发服务器地址 */
    @BeanAttrInfo(value = "96.44.183.252", cnName = "虚拟主机免备案转发服务器地址")
    public static final long NOT_RECORD_SERVER_FOR_VHOST = 108;

    /** 云主机免备案转发服务器地址 */
    @BeanAttrInfo(value = "96.44.183.245", cnName = "云主机免备案转发服务器地址")
    public static final long NOT_RECORD_SERVER_FOR_CHOST = 109;

    /** 绑定限制域名配置 (value:产品ID集合;enName:禁止绑定域名列表（绝对路径文件）;cnName:提示信息) */
    @BeanAttrInfo(value = "", enName = "", cnName = "该产品只能绑定20150508之后注册的域名或未在我司使用过的域名")
    public static final long BIND_LIMIT_DOMAIN_CONFIG = 110;

    /** 会员系统URL */
    @BeanAttrInfo(value = "http://member.cp.51web.com", cnName = "会员系统URL")
    public static final long MEMBER_SYSTEM_URL = 111;

    /** 模拟登陆控制面板根URL */
    @BeanAttrInfo(value = "http://client.51web.com", cnName = "模拟登陆控制面板根URL")
    public static final long CLIENT_BASE_URL = 112;

    /** 转发IP集合 */
    @BeanAttrInfo(value = "125.65.112.26", cnName = "转发IP集合")
    public static final long URL_FORWARD_IP = 115;

    /** 是否开启服务器状态流转控制 */
    @BeanAttrInfo(value = "1", cnName = "是否开启服务器状态流转控制(1:是,2:否)")
    public static final long IS_OPEN_SERVER_STATUS_CONTAL = 116;

    @BeanAttrInfo(value = "2", cnName = "IP使用时是否需要到世纪东方IP库验证(1:是,2:否)")
    public static final long IS_OPEN_SJDF_IP_CHECK = 117;

    @BeanAttrInfo(value = "180", cnName = "客户信息验证周期(天)")
    public static final long CUSTOMER_INFORMATION_VERIFICATION_CYCLE = 118;

    @BeanAttrInfo(value = "客户自助提交的退款出账申请，请确认如下信息进行退款出账。【工作日每天下午4点集中处理，值班和周末时间顺延到工作日处理，淘宝申请的退款系统自动拒绝！】", cnName = "客户申请退款到支付宝发送工单的内容")
    public static final long CUSTOMER_REFUND_TO_ALIPAY_WORKORDER_WORDS = 119;

    /** 节假日 */
    @BeanAttrInfo(value = "01-01,01-02,02-18,02-19,02-20,02-23,02-24,04-06,05-01,06-22,10-01,10-02,10-05,10-06,10-07", cnName = "节假日")
    public static final long HOLIDAY = 120;

    @BeanAttrInfo(value = "http://client.51web.com/user/product/chost/chostSearchAction!list.action", cnName = "会员信息验证成功后的跳转地址")
    public static final long MEMBER_VERIFY_SUCCESS_TO_HREF = 121;

    /** 节假日周末上班 */
    @BeanAttrInfo(value = "02-28,05-03,09-06,10-10", cnName = "节假日周末上班")
    public static final long WEEKEND_WORK = 122;

    /**
     * value :是该城市在p_location表中的code值
     * cnName:是该城市在p_location表中的cnName值
     */
    @BeanAttrInfo(value = "510100", cnName = "成都市")
    public static final long MEMBER_IMPORT_DEFAULT_CITY = 123;

    /** 拥有二级平台的会员名，多个以;分隔 */
    @BeanAttrInfo(value = "e8088cd;zjisp", cnName = "拥有二级平台的会员名")
    public static final long SYSTEM_PLATFORM_OWNER = 124;

    /** 财务系统根URL */
    @BeanAttrInfo(value = "http://finance.51web.com", cnName = "财务系统根URL")
    public static final long FINANCE_BASE_URL = 125;

    /** 是否开启微信关注验证 */
    @BeanAttrInfo(value = "1", cnName = "是否开启微信关注验证(1:是,2:否)")
    public static final long IS_OPEN_WECHAT_ATTENTION_CHECK = 126;

    /** IDC产品ID前缀 */
    @BeanAttrInfo(value = "idc", cnName = "IDC产品ID前缀")
    public static final long IDC_PRODUCT_ID_PREFIX = 127;

    /** IDC购买单号前缀 */
    @BeanAttrInfo(value = "idco", cnName = "IDC购买单号前缀")
    public static final long IDC_ORDER_PREFIX = 128;

    /** IDC订单单号前缀 */
    @BeanAttrInfo(value = "ion", cnName = "IDC订单单号前缀")
    public static final long IDC_ORDER_NUM_PREFIX = 129;

    /** 合同单号前缀 */
    @BeanAttrInfo(value = "ht", cnName = "合同单号前缀")
    public static final long CONTRACT_ORDER_PREFIX = 130;

    /** idc上架编号前缀 */
    @BeanAttrInfo(value = "server", cnName = "IDC上架编号前缀")
    public static final long IDC_SERVER_NUM_PREFIX = 131;

    /** idc上架提示 */
    @BeanAttrInfo(value = "如您需要工程师协助贵司安装服务器的系统和软件，请在配置信息里说明具体要求，以后调整可能会产生费用，谢谢！", cnName = "IDC上架提示")
    public static final long IDC_UP_SERVER_TIP = 132;

    /** 消息中间件发送会员集合 */
    @BeanAttrInfo(value = "", cnName = "消息中间件发送会员集合")
    public static final long ACTIVE_MQ_SEND_MEMBER = 201L;

    /** 消息中间件服务器地址 */
    @BeanAttrInfo(value = "tcp://192.168.1.250:61616", cnName = "消息中间件服务器地址")
    public static final long ACTIVE_MQ_URL = 202L;

    /** 主站首页访问地址 */
    @BeanAttrInfo(value = "/index/product/HomeIndexAction/index.html", cnName = "主站首页访问地址")
    public static final long EISS_NEW_INDEX_URL = 203L;

    /** 会员跟单点评主管 */
    @BeanAttrInfo(value = "陈静,李娅,简伟", cnName = "点评跟单主管")
    public static final long MEMBER_TRACK_COMMENT_DIRECTOR = 204L;

    /** 每台IDC服务器使用的电力 */
    @BeanAttrInfo(value = "1", cnName = "每台IDC服务器使用的电力")
    public static final long IDC_SERVER_USE_POWER = 301L;

    @BeanAttrInfo(value = "服务器整机", cnName = "IDC型号显示名称")
    public static final long IDC_MODEL_SHOW_NAME = 302L;

    @BeanAttrInfo(value = "9", cnName = "超管跟单统计组别")
    public static final long TRACK_COUNT_GROUPS = 303L;

    @BeanAttrInfo(value = "Android,iPhone,iPod,iPad,Windows Phone,MQQBrowser", cnName = "移动端代理类型")
    public static final long MOBILE_TERMINAL_USER_AGENT = 304;

    @BeanAttrInfo(value = "23", cnName = "挂机宝启动失败工单服务项目")
    public static final long CHOST_START_FAIL_ORDER_ITEM = 305;

    public static String getUploadFileTip() {
        long min = Long.valueOf(ConfigManager.getInstance().getValue(ConstGlobal.class, ConstGlobal.DOMAIN_FILE_UPLOAD_MIN));
        long max = Long.valueOf(ConfigManager.getInstance().getValue(ConstGlobal.class, ConstGlobal.DOMAIN_FILE_UPLOAD_MAX));
        String unitCnName = ConfigManager.getInstance().getCnName(ConstGlobal.class, ConstGlobal.DOMAIN_FILE_UPLOAD_UNIT);
        String attachmentSuffix = ConfigManager.getInstance().getValue(AttachmentConfigure.class, AttachmentConfigure.EISS_ATTACHMENT_SUFFIX);
        StringBuilder builder = new StringBuilder(CommonPlatformConstant.LENGTH_256);
        builder.append("支持").append(attachmentSuffix).append("格式,");
        builder.append("最小不能小于").append(min).append(unitCnName).append(",");
        builder.append("最大不超过").append(max).append(unitCnName);
        return builder.toString();
    }
}