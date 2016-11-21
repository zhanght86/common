package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Create 2012-09-05
 *
 * @author ketqi
 * @category 咨询反馈中的问题小类
 */
@Entity
@DiscriminatorValue("ASK_TYPE")
@BeanName(name = "咨询反馈问题小类")
public class AskType extends Dictionary {
    private static final long serialVersionUID = -1747540970213296286L;
    // **********************************************域名***********************************************
    /** 国内英文域名注册 */
    @BeanAttrInfo(refClass = AskClass.class, refAttr = AskClass.DOMAIN, orderBy = 250, cnName = "国内英文域名注册", value = "域名：\n问题：")
    public static final long DOMAIN_IN_EN = 250;

    /** 万网国内域名实名认证过户 */
    @BeanAttrInfo(refClass = AskClass.class, refAttr = AskClass.DOMAIN, orderBy = 251, cnName = "万网国内域名实名认证过户", value = "过户域名：\n新域名所有者（中文）*：\n新域名所有者（英文）*：\n联系人姓名（中文）*：\n省份（中文）*：\n城市（中文）*：\n地址（中文）*：\n邮政编码*：\n电话* ： +86.\nE-mail* ：\n收过户表的邮箱：")
    public static final long DOMAIN_NET_IN_TRANSFER = 251;

    /** 国内域名备案成功解锁（新注册域名） */
    @BeanAttrInfo(refClass = AskClass.class, refAttr = AskClass.DOMAIN, orderBy = 252, cnName = "国内域名备案成功解锁（新注册域名）", value = "域名：\n备案号：")
    public static final long DOMAIN_DNS_UPDATE = 252;

    /** 国内域名申请免备案申请书 */
    @BeanAttrInfo(refClass = AskClass.class, refAttr = AskClass.DOMAIN, orderBy = 253, cnName = "国内域名申请免备案申请书", value = "域名：\n域名dns服务器:\n网站IP地址：\n网站接入商：")
    public static final long DOMAIN_IN_FREE_RECORD = 253;

    /** 国内域名实名认证 */
    @BeanAttrInfo(refClass = AskClass.class, refAttr = AskClass.DOMAIN, orderBy = 254, cnName = "国内域名实名认证", value = "1.请确认是否已上传注册联系人身份证明、组织机构代码证、声明书\n2.声明书下载址： http://faq.51web.com/doc/国内域名注册声明书.doc\n3.请确认上述资料已经上传,请审核注册资料\n4.我司审核后，请域名管理管理人注意接听cnnic打的审核信息电话(域名管理里的电话号码)\n注册的域名为:")
    public static final long DOMAIN_IN_DPI = 254;

    /** 域名续费 */
    @BeanAttrInfo(refClass = AskClass.class, refAttr = AskClass.DOMAIN, orderBy = 255, cnName = "域名续费", value = "域名：\n续费年限：")
    public static final long DOMAIN_RENEW = 255;

    /** 添加修改转发 */
    @BeanAttrInfo(refClass = AskClass.class, refAttr = AskClass.DOMAIN, orderBy = 256, cnName = "添加修改转发", value = "域名：\nurl地址：")
    public static final long DOMAIN_FORWARD = 256;

    /** 域名被HOLD处理 */
    @BeanAttrInfo(refClass = AskClass.class, refAttr = AskClass.DOMAIN, orderBy = 257, cnName = "域名被HOLD处理", value = "域名被HOLD的解锁办法：http://faq.51web.com/index.php?sid=48911&lang=zh&action=artikel&cat=5&id=91&artlang=zh\n请确认资料准备完备后提交需要解锁的域名：")
    public static final long DOMAIN_HOLD = 257;

    /** 域名问题 其他 */
    @BeanAttrInfo(refClass = AskClass.class, refAttr = AskClass.DOMAIN, orderBy = 258, cnName = "其他", value = "域名：")
    public static final long DOMAIN_OTHER = 258;

    @BeanAttrInfo(refClass = AskClass.class, refAttr = AskClass.DOMAIN, orderBy = 250, cnName = "国际域名", value = "域名：\n问题：")
    public static final long DOMAIN_INTERNATIONAL = 259;

    /** 通用网址 */
    @BeanAttrInfo(refClass = AskClass.class, refAttr = AskClass.DOMAIN, orderBy = 260, cnName = "通用网址", value = "通用网址：\n问题：")
    public static final long DOMAIN_WEBSITE = 260;

    // **********************************************备案问题***********************************************
    /** 备案信息修改 */
    @BeanAttrInfo(refClass = AskClass.class, refAttr = AskClass.RECORD, orderBy = 401, cnName = "备案信息修改", value = "主办单位名称：\n备案域名：\n修改内容：")
    public static final long RECORD_INFO_UPDATE = 401;

    /** 备案信息增加 */
    @BeanAttrInfo(refClass = AskClass.class, refAttr = AskClass.RECORD, orderBy = 402, cnName = "备案信息增加", value = "主办单位名称：\n备案域名：\n备案号：\n增加内容：")
    public static final long RECORD_INFO_ADD = 402;

    /** 其它 */
    @BeanAttrInfo(refClass = AskClass.class, refAttr = AskClass.RECORD, orderBy = 403, cnName = "其它", value = "主办单位名称：\n备案域名：\n问题：")
    public static final long RECORD_OTHER = 403;

    /** 申请背景布 */
    @BeanAttrInfo(refClass = AskClass.class, refAttr = AskClass.RECORD, orderBy = 404, cnName = "申请背景布", value = "收件地址：\n收件人：\n手机号码：\n座机号码:\n邮政编号：\n快递公司：圆通20元  顺丰25元  （请保留你选择的快递公司，删除你不选择的快递公司，并保证账户上有足够的余额扣除快递费）\n")
    public static final long RECORD_APPLICATION_BACKGROUND_FABRIC = 404;

    // **********************************************财务问题***********************************************
    /** 财务查帐入款 */
    @BeanAttrInfo(refClass = AskClass.class, refAttr = AskClass.FINANCIAL, orderBy = 51, cnName = "财务查帐入款", value = "汇款银行（支付宝请提供支付宝邮箱）：\n金额：")
    public static final long FINANCIAL_DEPOSIT = 51;

    /** 财务返款 */
    @BeanAttrInfo(refClass = AskClass.class, refAttr = AskClass.FINANCIAL, orderBy = 52, cnName = "财务返款", value = "返款金额：\n返款内容：")
    public static final long FINANCIAL_REBATE = 52;

    /** 财务退款 */
    @BeanAttrInfo(refClass = AskClass.class, refAttr = AskClass.FINANCIAL, orderBy = 53, cnName = "财务退款", value = "退款金额：\n退款银行名称（支付宝名称）：\n退款银行帐号（支付宝邮箱）：\n银行开户行名称：")
    public static final long FINANCIAL_REFUND = 53;

    /** 发票问题 */
    @BeanAttrInfo(refClass = AskClass.class, refAttr = AskClass.FINANCIAL, orderBy = 54, cnName = "发票问题", value = "发票抬头：\n发票金额：\n问题：")
    public static final long FINANCIAL_INVOICE = 54;

    /** 其它 */
    @BeanAttrInfo(refClass = AskClass.class, refAttr = AskClass.FINANCIAL, orderBy = 55, cnName = "其它")
    public static final long FINANCIAL_OTHER = 55;

    // **********************************************虚拟主机***********************************************
    /** 空间问题 空间升级续费 */
    @BeanAttrInfo(refClass = AskClass.class, refAttr = AskClass.VHOST, orderBy = 101, cnName = "空间升级续费")
    public static final long VHOST_RENEW = 101;

    /** 空间问题 空间无法访问 */
    @BeanAttrInfo(refClass = AskClass.class, refAttr = AskClass.VHOST, orderBy = 102, cnName = "空间无法访问")
    public static final long VHOST_NO_ACCESS = 102;

    /** 空间问题 无法上传 */
    @BeanAttrInfo(refClass = AskClass.class, refAttr = AskClass.VHOST, orderBy = 103, cnName = "无法上传")
    public static final long VHOST_NO_UPLOAD = 103;

    /** 空间问题 域名无法绑定 */
    @BeanAttrInfo(refClass = AskClass.class, refAttr = AskClass.VHOST, orderBy = 104, cnName = "域名无法绑定")
    public static final long VHOST_DOMAIN_CANNOT_BIND = 104;

    /** 空间更换机房 */
    @BeanAttrInfo(refClass = AskClass.class, refAttr = AskClass.VHOST, orderBy = 105, cnName = "空间更换机房")
    public static final long VHOST_CHANGE_IDC = 105;

    /** 空间访问速度慢 */
    @BeanAttrInfo(refClass = AskClass.class, refAttr = AskClass.VHOST, orderBy = 106, cnName = "空间访问速度慢")
    public static final long VHOST_ACCESS_SLOW = 106;

    /** 网站被注入 */
    @BeanAttrInfo(refClass = AskClass.class, refAttr = AskClass.VHOST, orderBy = 107, cnName = "网站被注入")
    public static final long VHOST_WEBSITE_INJECT = 107;

    /** 空间环境配置 */
    @BeanAttrInfo(refClass = AskClass.class, refAttr = AskClass.VHOST, orderBy = 108, cnName = "空间环境配置")
    public static final long VHOST_ENV_CONFIG = 108;

    /** 智能建站 */
    @BeanAttrInfo(refClass = AskClass.class, refAttr = AskClass.VHOST, orderBy = 109, cnName = "智能建站")
    public static final long VHOST_INTELLIGENT_WEBSITE = 109;

    /** 成品模板 */
    @BeanAttrInfo(refClass = AskClass.class, refAttr = AskClass.VHOST, orderBy = 110, cnName = "成品模板")
    public static final long VHOST_TEMPLATE = 110;

    /** 建站超市 */
    @BeanAttrInfo(refClass = AskClass.class, refAttr = AskClass.VHOST, orderBy = 111, cnName = "建站超市")
    public static final long VHOST_SUPERMARKET = 111;

    /** 空间问题 其他 */
    @BeanAttrInfo(refClass = AskClass.class, refAttr = AskClass.VHOST, orderBy = 149, cnName = "其他")
    public static final long VHOST_OTHER = 149;

    // **********************************************云主机***********************************************
    /** 网站无法打开 */
    @BeanAttrInfo(refClass = AskClass.class, refAttr = AskClass.CHOST, orderBy = 151, cnName = "网站无法打开")
    public static final long CHOST_SITE_UNACCESS = 151;
    /** 网站打开报错 */
    @BeanAttrInfo(refClass = AskClass.class, refAttr = AskClass.CHOST, orderBy = 152, cnName = "网站打开报错")
    public static final long CHOST_SITE_OPEN_ERROR = 152;
    /** 网站打开慢 */
    @BeanAttrInfo(refClass = AskClass.class, refAttr = AskClass.CHOST, orderBy = 153, cnName = "网站打开慢")
    public static final long CHOST_SITE_OPEN_SLOW = 153;
    /** 常用软件安装(ftp,mysql,mssql) */
    @BeanAttrInfo(refClass = AskClass.class, refAttr = AskClass.CHOST, orderBy = 154, cnName = "常用软件安装(ftp,mysql,mssql)")
    public static final long CHOST_SOFT_INSTALL = 154;
    /** IIS,PHP及.NET版本2,0及3.5环境配置 */
    @BeanAttrInfo(refClass = AskClass.class, refAttr = AskClass.CHOST, orderBy = 155, cnName = "IIS,PHP及.NET版本2,0及3.5环境配置")
    public static final long CHOST_SOFT_CONFIG = 155;
    /** 查杀病毒 */
    @BeanAttrInfo(refClass = AskClass.class, refAttr = AskClass.CHOST, orderBy = 156, cnName = "查杀病毒")
    public static final long CHOST_KILL_VIRUS = 156;
    /** 机器不通 */
    @BeanAttrInfo(refClass = AskClass.class, refAttr = AskClass.CHOST, orderBy = 157, cnName = "机器不通")
    public static final long CHOST_MACHINE_UNWORK = 157;
    /** 重置密码 */
    @BeanAttrInfo(refClass = AskClass.class, refAttr = AskClass.CHOST, orderBy = 158, cnName = "重置密码")
    public static final long CHOST_RESET_PASSWORD = 158;
    /** mysql数据库备份与还原 */
    @BeanAttrInfo(refClass = AskClass.class, refAttr = AskClass.CHOST, orderBy = 159, cnName = "mysql数据库备份与还原")
    public static final long CHOST_MYSQL_DATA = 159;
    /** mssql数据库备份与还原 */
    @BeanAttrInfo(refClass = AskClass.class, refAttr = AskClass.CHOST, orderBy = 160, cnName = "mssql数据库备份与还原")
    public static final long CHOST_MSSQL_DATA = 160;
    /** 数据库密码重置 */
    @BeanAttrInfo(refClass = AskClass.class, refAttr = AskClass.CHOST, orderBy = 161, cnName = "数据库密码重置")
    public static final long CHOST_RESET_DB_PWD = 161;
    /** 增加ip 增加带宽 增加域名邦定个数 */
    @BeanAttrInfo(refClass = AskClass.class, refAttr = AskClass.CHOST, orderBy = 162, cnName = "增加ip 增加带宽 增加域名邦定个数")
    public static final long CHOST_UPGRADE = 162;
    /** 申请恢复 */
    @BeanAttrInfo(refClass = AskClass.class, refAttr = AskClass.CHOST, orderBy = 163, cnName = "申请恢复")
    public static final long CHOST_RESTORATION = 163;

    /** 其他问题 */
    @BeanAttrInfo(refClass = AskClass.class, refAttr = AskClass.CHOST, orderBy = 199, cnName = "其他问题")
    public static final long CHOST_OTHER = 199;

    // **********************************************VPS***********************************************
    /** 网站无法打开 */
    @BeanAttrInfo(refClass = AskClass.class, refAttr = AskClass.VPS, orderBy = 201, cnName = "网站无法打开")
    public static final long VPS_SITE_UNACCESS = 201;
    /** 网站打开报错 */
    @BeanAttrInfo(refClass = AskClass.class, refAttr = AskClass.VPS, orderBy = 202, cnName = "网站打开报错")
    public static final long VPS_SITE_OPEN_ERROR = 202;
    /** 网站打开慢 */
    @BeanAttrInfo(refClass = AskClass.class, refAttr = AskClass.VPS, orderBy = 203, cnName = "网站打开慢")
    public static final long VPS_SITE_OPEN_SLOW = 203;
    /** 常用软件安装(ftp,mysql,mssql) */
    @BeanAttrInfo(refClass = AskClass.class, refAttr = AskClass.VPS, orderBy = 204, cnName = "常用软件安装(ftp,mysql,mssql)")
    public static final long VPS_SOFT_INSTALL = 204;
    /** IIS,PHP及.NET版本2,0及3.5环境配置 */
    @BeanAttrInfo(refClass = AskClass.class, refAttr = AskClass.VPS, orderBy = 205, cnName = "IIS,PHP及.NET版本2,0及3.5环境配置")
    public static final long VPS_SOFT_CONFIG = 205;
    /** 查杀病毒 */
    @BeanAttrInfo(refClass = AskClass.class, refAttr = AskClass.VPS, orderBy = 206, cnName = "查杀病毒")
    public static final long VPS_KILL_VIRUS = 206;
    /** 机器不通 */
    @BeanAttrInfo(refClass = AskClass.class, refAttr = AskClass.VPS, orderBy = 207, cnName = "机器不通")
    public static final long VPS_MACHINE_UNWORK = 207;
    /** 重置密码 */
    @BeanAttrInfo(refClass = AskClass.class, refAttr = AskClass.VPS, orderBy = 208, cnName = "重置密码")
    public static final long VPS_RESET_PASSWORD = 208;
    /** mysql数据库备份与还原 */
    @BeanAttrInfo(refClass = AskClass.class, refAttr = AskClass.VPS, orderBy = 209, cnName = "mysql数据库备份与还原")
    public static final long VPS_MYSQL_DATA = 209;
    /** mssql数据库备份与还原 */
    @BeanAttrInfo(refClass = AskClass.class, refAttr = AskClass.VPS, orderBy = 210, cnName = "mssql数据库备份与还原")
    public static final long VPS_MSSQL_DATA = 210;
    /** 数据库密码重置 */
    @BeanAttrInfo(refClass = AskClass.class, refAttr = AskClass.VPS, orderBy = 211, cnName = "数据库密码重置")
    public static final long VPS_RESET_DB_PWD = 211;
    /** 增加ip 增加带宽 增加域名邦定个数 */
    @BeanAttrInfo(refClass = AskClass.class, refAttr = AskClass.VPS, orderBy = 212, cnName = "增加ip 增加带宽 增加域名邦定个数")
    public static final long VPS_UPGRADE = 212;
    /** 其他问题 */
    @BeanAttrInfo(refClass = AskClass.class, refAttr = AskClass.VPS, orderBy = 249, cnName = "其他问题")
    public static final long VPS_OTHER = 249;

    // **********************************************IDC***********************************************
    /** 安装Windows系统 */
    @BeanAttrInfo(refClass = AskClass.class, refAttr = AskClass.IDC, orderBy = 451, cnName = "安装Windows系统 ")
    public static final long IDC_INSTALL_WINDOW = 451;

    /** 安装LINUX系统 */
    @BeanAttrInfo(refClass = AskClass.class, refAttr = AskClass.IDC, orderBy = 452, cnName = "安装LINUX系统 ")
    public static final long IDC_INSTALL_LINUX = 452;

    /** 备份系统 */
    @BeanAttrInfo(refClass = AskClass.class, refAttr = AskClass.IDC, orderBy = 453, cnName = "备份系统 ")
    public static final long IDC_BACK_SYSTEM = 453;

    /** 还原系统 */
    @BeanAttrInfo(refClass = AskClass.class, refAttr = AskClass.IDC, orderBy = 454, cnName = "还原系统 ")
    public static final long IDC_RESTITUTE_SYSTEM = 454;

    /** 客户申请关机 */
    @BeanAttrInfo(refClass = AskClass.class, refAttr = AskClass.IDC, orderBy = 455, cnName = "客户申请关机")
    public static final long IDC_SHUTDOWN = 455;

    /** 客户申请断网 */
    @BeanAttrInfo(refClass = AskClass.class, refAttr = AskClass.IDC, orderBy = 456, cnName = "客户申请断网 ")
    public static final long IDC_OFF_NETWORK = 456;

    /** 客户申请开机 */
    @BeanAttrInfo(refClass = AskClass.class, refAttr = AskClass.IDC, orderBy = 457, cnName = "客户申请开机")
    public static final long IDC_BOOT = 457;

    /** 客户申请连网 */
    @BeanAttrInfo(refClass = AskClass.class, refAttr = AskClass.IDC, orderBy = 458, cnName = "客户申请连网")
    public static final long IDC_ON_NETWORK = 458;

    /** 部分地区或线路无法访问 */
    @BeanAttrInfo(refClass = AskClass.class, refAttr = AskClass.IDC, orderBy = 459, cnName = "部分地区或线路无法访问")
    public static final long IDC_UNACCESS = 459;

    /** 网站打开慢 */
    @BeanAttrInfo(refClass = AskClass.class, refAttr = AskClass.IDC, orderBy = 460, cnName = "网站打开慢")
    public static final long IDC_SITE_SLOW = 460;

    /** arp及异常流量检查 */
    @BeanAttrInfo(refClass = AskClass.class, refAttr = AskClass.IDC, orderBy = 461, cnName = "arp及异常流量检查")
    public static final long IDC_ARP = 461;

    /** 常用软件安装(ftp,mysql,mssql) */
    @BeanAttrInfo(refClass = AskClass.class, refAttr = AskClass.IDC, orderBy = 462, cnName = "常用软件安装(ftp,mysql,mssql)")
    public static final long IDC_SOFT_INSTALL = 462;

    /** IIS,PHP及.NET版本2,0及3.5环境配置 */
    @BeanAttrInfo(refClass = AskClass.class, refAttr = AskClass.IDC, orderBy = 463, cnName = "IIS,PHP及.NET版本2,0及3.5环境配置")
    public static final long IDC_SOFT_CONFIG = 463;

    /** 机器不通 */
    @BeanAttrInfo(refClass = AskClass.class, refAttr = AskClass.IDC, orderBy = 464, cnName = "机器不通")
    public static final long IDC_MACHINE_UNWORK = 464;

    /** 重置密码 */
    @BeanAttrInfo(refClass = AskClass.class, refAttr = AskClass.IDC, orderBy = 465, cnName = "重置密码")
    public static final long IDC_RESET_PWD = 465;

    /** 增加ip 增加带宽 增加内存增加硬盘 */
    @BeanAttrInfo(refClass = AskClass.class, refAttr = AskClass.IDC, orderBy = 466, cnName = "增加ip 增加带宽 增加内存增加硬盘")
    public static final long IDC_UPGRADE = 466;

    /** 硬件更换 */
    @BeanAttrInfo(refClass = AskClass.class, refAttr = AskClass.IDC, orderBy = 467, cnName = "硬件更换")
    public static final long IDC_HARDWARE_UPDATE = 467;

    /** 下架客户取走 */
    @BeanAttrInfo(refClass = AskClass.class, refAttr = AskClass.IDC, orderBy = 468, cnName = "下架客户取走")
    public static final long IDC_REMOVED = 468;

    /** 下架暂存机房 */
    @BeanAttrInfo(refClass = AskClass.class, refAttr = AskClass.IDC, orderBy = 469, cnName = "下架暂存机房")
    public static final long IDC_TEMP_STORE = 469;

    /** 下架客户维护 */
    @BeanAttrInfo(refClass = AskClass.class, refAttr = AskClass.IDC, orderBy = 470, cnName = "下架客户维护")
    public static final long IDC_MAINTAIN = 470;

    /** 硬件故障排查 */
    @BeanAttrInfo(refClass = AskClass.class, refAttr = AskClass.IDC, orderBy = 471, cnName = "硬件故障排查")
    public static final long IDC_HARDWARE_TROUBLE = 471;

    /** 服务器维修后上架 */
    @BeanAttrInfo(refClass = AskClass.class, refAttr = AskClass.IDC, orderBy = 472, cnName = "服务器维修后上架")
    public static final long IDC_MAINTAIN_ONLINE = 472;

    /** 服务器新上架 */
    @BeanAttrInfo(refClass = AskClass.class, refAttr = AskClass.IDC, orderBy = 473, cnName = "服务器新上架")
    public static final long IDC_SERVER_NEW_ONLINE = 473;
    /** 其他问题 */
    @BeanAttrInfo(refClass = AskClass.class, refAttr = AskClass.IDC, orderBy = 499, cnName = "其他问题")
    public static final long IDC_OTHER = 499;

    // **********************************************数据库问题***********************************************
    /** 数据库升级 */
    @BeanAttrInfo(refClass = AskClass.class, refAttr = AskClass.DATABASE, orderBy = 351, cnName = "数据库升级")
    public static final long DATABASE_UPGRADE = 351;

    /** 数据库备份/还原 */
    @BeanAttrInfo(refClass = AskClass.class, refAttr = AskClass.DATABASE, orderBy = 352, cnName = "数据库备份/还原")
    public static final long DATABASE_REDUCE = 352;

    /** 数据库清空 */
    @BeanAttrInfo(refClass = AskClass.class, refAttr = AskClass.DATABASE, orderBy = 353, cnName = "数据库清空")
    public static final long DATABASE_CLEAR = 353;

    /** 数据库无法连接 */
    @BeanAttrInfo(refClass = AskClass.class, refAttr = AskClass.DATABASE, orderBy = 354, cnName = "数据库无法连接")
    public static final long DATABASE_UNACCESS = 354;

    /** 数据库FTP无法使用 */
    @BeanAttrInfo(refClass = AskClass.class, refAttr = AskClass.DATABASE, orderBy = 355, cnName = "数据库FTP无法使用")
    public static final long DATABASE_FTP_UNACCESS = 355;

    /** 数据库更换机房 */
    @BeanAttrInfo(refClass = AskClass.class, refAttr = AskClass.DATABASE, orderBy = 356, cnName = "数据库更换机房")
    public static final long DATABASE_CHANGE_IDC = 356;

    /** 其他 */
    @BeanAttrInfo(refClass = AskClass.class, refAttr = AskClass.DATABASE, orderBy = 399, cnName = "其他")
    public static final long DATABASE_OTHER = 399;

    // **********************************************邮局问题***********************************************
    /** 邮局升级 */
    @BeanAttrInfo(refClass = AskClass.class, refAttr = AskClass.MAIL, orderBy = 31, cnName = "邮局升级")
    public static final long MAIL_UPGRADE = 31;

    /** 邮局无法登陆 */
    @BeanAttrInfo(refClass = AskClass.class, refAttr = AskClass.MAIL, orderBy = 32, cnName = "邮局无法登陆")
    public static final long MAIL_UNLOGIN = 32;

    /** 无法收发邮件 */
    @BeanAttrInfo(refClass = AskClass.class, refAttr = AskClass.MAIL, orderBy = 33, cnName = "无法收发邮件")
    public static final long MAIL_UNWORK = 33;

    /** 邮局管理 */
    @BeanAttrInfo(refClass = AskClass.class, refAttr = AskClass.MAIL, orderBy = 34, cnName = "邮局管理")
    public static final long MAIL_MANAGE = 34;

    /** 邮局更换域名 */
    @BeanAttrInfo(refClass = AskClass.class, refAttr = AskClass.MAIL, orderBy = 35, cnName = "邮局更换域名")
    public static final long MAIL_CHANGE_DOMAIN = 35;

    /** 其他 */
    @BeanAttrInfo(refClass = AskClass.class, refAttr = AskClass.MAIL, orderBy = 36, cnName = "其他")
    public static final long MAIL_OTHER = 36;

    // **********************************************代理平台问题***********************************************
    /*** 代理平台问题 */
    @BeanAttrInfo(refClass = AskClass.class, refAttr = AskClass.AGENT_PLATFORM, orderBy = 501, cnName = "代理平台问题")
    public static final long AGENT_PLATFORM = 501;

    // **********************************************其他问题***********************************************
    /** 其他问题 */
    @BeanAttrInfo(refClass = AskClass.class, refAttr = AskClass.OTHERS, orderBy = 1001, cnName = "其他问题")
    public static final long OTHERS = 1001;
}
