package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;


/**
 * 2013-12-11
 *
 * @author 黄远昌
 * @category 域名检测
 */
@Entity
@DiscriminatorValue("DOMAIN_CHECK")
@BeanName(name = "域名检测")
public class DomainCheck extends Dictionary {
    private static final long serialVersionUID = 7555698533797033019L;
    /** 管理员停止产品原因描述 */
    @BeanAttrInfo(value = "1). 该产品被管理员停止，原因：", cnName = "管理员停止产品原因描述")
    public static final long ADMIN_STOP_PRODUCT_REASON_DES = 1;
    /** 管理员停止域名原因描述 */
    @BeanAttrInfo(value = "1). 该域名被管理员停止，原因：", cnName = "管理员停止域名原因描述")
    public static final long ADMIN_STOP_DOMAIN_REASON_DES = 11;
    /** 管理员停止解决方案描述 */
    @BeanAttrInfo(value = "2). 解决方案：", cnName = "管理员停止解决方案描述")
    public static final long ADMIN_STOP_SOLUTION_DES = 2;
    /** 管理员停止处理流程描述 */
    @BeanAttrInfo(value = "3). 须按照所示解决方案进行整改后，到咨询反馈提交超管审核并恢复访问。", cnName = "管理员停止处理流程描述")
    public static final long ADMIN_STOP_PROCESS_DES = 3;
    /** 过期停止解决方案 */
    @BeanAttrInfo(value = "请登录管理中心进行充值续费【", cnName = "过期停止解决方案（before）")
    public static final long EXPIRED_STOP_SOLUTION_BEFORE = 4;
    /** 过期停止解决方案 */
    @BeanAttrInfo(value = "】，续费后即可正常访问。", cnName = "过期停止解决方案（after）")
    public static final long EXPIRED_STOP_SOLUTION_AFTER = 5;
    /** 域名过期停止解决方案 */
    @BeanAttrInfo(value = "】，过期停止的域名续费24小时后方可正常访问。", cnName = "域名过期停止解决方案（after）")
    public static final long DOMAIN_EXPIRED_STOP_SOLUTION_AFTER = 51;
    /** 默认解决方案 */
    @BeanAttrInfo(value = "请提交咨询反馈，联络管理员处理。", cnName = "默认解决方案")
    public static final long DEFAULT_SOLUTION = 6;
    /** 域名未备案解决方案 */
    @BeanAttrInfo(value = "需要登录我司备案系统（<a href=\"http://beian.51web.com\" target=\"_blank\" style=\"color:blue\">http://beian.51web.com</a>）进行备案。", cnName = "域名未备案解决方案")
    public static final long DOMAIN_NOT_RECORD_SOLUTION = 7;
    /** 域名新增接入解决方案 */
    @BeanAttrInfo(value = "需要登录我司备案系统（<a href=\"http://beian.51web.com\" target=\"_blank\" style=\"color:blue\">http://beian.51web.com</a>）做新增接入。", cnName = "域名新增接入解决方案")
    public static final long DOMAIN_ADD_ACCESS_SOLUTION = 8;
    /** 域名变更备案解决方案 */
    @BeanAttrInfo(value = "需要登录我司备案系统（<a href=\"http://beian.51web.com\" target=\"_blank\" style=\"color:blue\">http://beian.51web.com</a>）做变更备案。", cnName = "域名变更备案解决方案 ")
    public static final long DOMAIN_CHANGE_RECORD_SOLUTION = 9;

    /** 云主机暂停解决方案 */
    @BeanAttrInfo(value = "请自行通过控制面板开关机处【恢复】云主机即可正常访问。", cnName = "云主机暂停解决方案")
    public static final long CHOST_PAUSE_SOLUTION = 701;
    /** 云主机已关机解决方案 */
    @BeanAttrInfo(value = "请自行通过控制面板开关机处【启动】云主机即可正常访问。", cnName = "云主机已关机解决方案")
    public static final long CHOST_POWEROFF_SOLUTION = 702;
    /** 云主机安装中解决方案 */
    @BeanAttrInfo(value = "请等待云主机安装成功，如果长时间未安装成功，请在系统操作中云主机迁移处进行重新安装。", cnName = "云主机安装中解决方案")
    public static final long CHOST_INSTALLING_SOLUTION = 703;
    /** 云主机超过系统流量限制停止解决方案 */
    @BeanAttrInfo(value = "需联络我司业务专员购买流量后即可正常访问。", cnName = "云主机超过系统流量限制停止解决方案")
    public static final long CHOST_SYSTEM_STOP_SOLUTION = 704;
    /** 云主机绑定域名暂停解决方案 */
    @BeanAttrInfo(value = "请自行通过控制面板白名单处点击【恢复访问】即可正常访问。", cnName = "云主机绑定域名暂停解决方案")
    public static final long CHOST_DOMAIN_PAUSE_SOLUTION = 711;
    /** 云主机绑定域名未备案解决方案 */
    @BeanAttrInfo(value = "如不愿备案，请购买免备案香港机房云服务器或将该站点迁往免备案香港虚拟主机。", cnName = "云主机绑定域名未备案解决方案")
    public static final long CHOST_DOMAIN_NOT_RECORD_SOLUTION = 712;
    /** 云主机绑定域名无法访问解决方案 */
    @BeanAttrInfo(value = "请自行检查", cnName = "云主机绑定域名无法访问解决方案")
    public static final long CHOST_DOMAIN_NO_ACCESS = 713;
    /** 云主机绑定域名免备案无法访问解决方案 */
    @BeanAttrInfo(value = "请自行检查", cnName = "云主机绑定域名免备案无法访问解决方案")
    public static final long CHOST_DOMAIN_NO_NEED_RECORD_NO_ACCESS = 714;
    /** 云主机断网描述 */
    @BeanAttrInfo(value = "断网", cnName = "云主机断网描述")
    public static final long CHOST_NIC_SWITCHOFF_DES = 720;
    /** 云主机断网原因 */
    @BeanAttrInfo(value = "1). 该产品被断网，可能原因：①云主机被攻击；②云主机对外发包。", cnName = "云主机断网原因")
    public static final long CHOST_NIC_SWITCHOFF_REASON = 721;
    /** 云主机断网解决方案 */
    @BeanAttrInfo(value = "2). 解决方案：①被攻击-关闭并迁移网站，联系我司删除域名绑定并将域名解析修改到非我司IP。②网站程序漏洞导致程序被篡改，对外发包，关闭出现问题的站点。③云主机中毒导致云主机对外发包，杀毒或者重装系统。④使用非法的程序刷网站流量，确认后关闭或者删除导致流量异常的程序或者功能。", cnName = "云主机断网解决方案")
    public static final long CHOST_NIC_SWITCHOFF_SOLUTION = 722;
    /** 云主机断网处理流程 */
    @BeanAttrInfo(value = "3). 须按照所示解决方案进行整改后，到咨询反馈提交超管审核并恢复访问。", cnName = "云主机断网处理流程")
    public static final long CHOST_NIC_SWITCHOFF_PROCESS = 723;
    /** 云主机限制带宽描述 */
    @BeanAttrInfo(value = "限制带宽", cnName = "云主机限制带宽描述")
    public static final long CHOST_BANDWIDTH_LIMITED_DES = 730;
    /** 云主机限制带宽原因 */
    @BeanAttrInfo(value = "1). 该产品被限制带宽，可能原因：①云主机被攻击；②云主机对外发包；③上传下载速度超过最大带宽限制。", cnName = "云主机限制带宽原因")
    public static final long CHOST_BANDWIDTH_LIMITED_REASON = 731;
    /** 云主机限制带宽解决方案 */
    @BeanAttrInfo(value = "2). 解决方案：①被攻击-关闭并迁移网站，联系我司删除域名绑定并将域名解析修改到非我司IP。②网站程序漏洞导致程序被篡改，对外发包，关闭出现问题的站点。③云主机中毒导致云主机对外发包，杀毒或者重装系统。④使用非法的程序刷网站流量，确认后关闭或者删除导致流量异常的程序或者功能。⑤限制下载或者上传速度在云主机限制带宽以下。", cnName = "云主机限制带宽解决方案")
    public static final long CHOST_BANDWIDTH_LIMITED_SOLUTION = 732;
    /** 云主机限制带宽处理流程 */
    @BeanAttrInfo(value = "3). 该限制会在控制面板所示时间自动取消， 但是如果不进行整改，会再次被限制。", cnName = "云主机限制带宽处理流程")
    public static final long CHOST_BANDWIDTH_LIMITED_PROCESS = 733;
    /** 云主机限制CPU描述 */
    @BeanAttrInfo(value = "限制CPU", cnName = "云主机限制CPU描述")
    public static final long CHOST_CPU_LIMITED_DES = 740;
    /** 云主机限制CPU原因 */
    @BeanAttrInfo(value = "1). 该产品被限制CPU，可能原因：①程序卡进程；②程序使用CPU过高；③云主机中毒。", cnName = "云主机限制CPU原因")
    public static final long CHOST_CPU_LIMITED_REASON = 741;
    /** 云主机限制CPU解决方案 */
    @BeanAttrInfo(value = "2). 解决方案：①找到对应站点停止访问；②关闭使用CPU过高的程序；③杀毒或者重装系统。", cnName = "云主机限制CPU解决方案")
    public static final long CHOST_CPU_LIMITED_SOLUTION = 742;
    /** 云主机限制CPU处理流程 */
    @BeanAttrInfo(value = "3). 该限制会在控制面板所示时间自动取消， 但是如果不进行整改，会再次被限制。", cnName = "云主机限制CPU处理流程")
    public static final long CHOST_CPU_LIMITED_PROCESS = 743;
    /** 云主机无法远程描述 */
    @BeanAttrInfo(value = "可否远程", cnName = "云主机无法远程描述")
    public static final long CHOST_UNABLE_REMOTE_DES = 750;
    /** 云主机无法远程原因 */
    @BeanAttrInfo(value = "1). 该产品无法远程，可能原因：①云主机处理关机状态或者暂停状态；②防火墙限制了远程连接端口；③安装杀毒软件限制了远程桌面；④云主机中毒。", cnName = "云主机无法远程原因")
    public static final long CHOST_UNABLE_REMOTE_REASON = 751;
    /** 云主机无法远程解决方案 */
    @BeanAttrInfo(value = "2). 解决方案：①启动云主机；②防火墙中云主机远程端口对外开放；③卸载杀毒软件或者服务器管理软件；④杀毒或者重装系统。", cnName = "云主机无法远程解决方案")
    public static final long CHOST_UNABLE_REMOTE_SOLUTION = 752;
    /** 云主机无法远程处理流程 */
    @BeanAttrInfo(value = "3). 按照所示解决方案进行整改后仍然无法恢复，请到咨询反馈提交。", cnName = "云主机无法远程处理流程")
    public static final long CHOST_UNABLE_REMOTE_PROCESS = 753;
    /** 云主机超出磁盘大小描述 */
    @BeanAttrInfo(value = "磁盘大小", cnName = "云主机超出磁盘大小描述")
    public static final long CHOST_EXCEED_DISK_SIZE_DES = 760;
    /** 云主机超出磁盘大小原因 */
    @BeanAttrInfo(value = "1). 该产品超出磁盘大小，可能原因：", cnName = "云主机超出磁盘大小原因")
    public static final long CHOST_EXCEED_DISK_SIZE_REASON = 761;
    /** 云主机超出磁盘大小解决方案 */
    @BeanAttrInfo(value = "2). 解决方案：", cnName = "云主机超出磁盘大小解决方案")
    public static final long CHOST_EXCEED_DISK_SIZE_SOLUTION = 762;
    /** 云主机超出磁盘大小处理流程 */
    @BeanAttrInfo(value = "3). 按照所示解决方案进行整改后，系统磁盘自动恢复写入数据功能。", cnName = "云主机超出磁盘大小处理流程")
    public static final long CHOST_EXCEED_DISK_SIZE_PROCESS = 763;

    /** 虚拟主机暂停解决方案 */
    @BeanAttrInfo(value = "请自行通过控制面板【修改WEB状态】处修改为【运行中】即可正常访问。", cnName = "虚拟主机暂停解决方案")
    public static final long VHOST_PAUSE_SOLUTION = 101;
    /** 虚拟主机处理中解决方案 */
    @BeanAttrInfo(value = "请提交咨询反馈，联络管理员处理。", cnName = "虚拟主机处理中解决方案")
    public static final long VHOST_PENDING_SOLUTION = 102;
    /** 虚拟主机待开通解决方案 */
    @BeanAttrInfo(value = "请提交咨询反馈，联络管理员处理。", cnName = "虚拟主机待开通解决方案")
    public static final long VHOST_STAY_CREATE_SOLUTION = 103;
    /** 虚拟主机未开通解决方案 */
    @BeanAttrInfo(value = "请提交咨询反馈，联络管理员处理。", cnName = "虚拟主机未开通解决方案")
    public static final long VHOST_NONE_CREATE_SOLUTION = 104;
    /** 虚拟主机超过系统流量限制停止解决方案 */
    @BeanAttrInfo(value = "需联络我司业务专员购买流量后即可正常访问。", cnName = "虚拟主机超过系统流量限制停止解决方案")
    public static final long VHOST_SYSTEM_STOP_SOLUTION = 105;

    /** 邮局暂停解决方案 */
    @BeanAttrInfo(value = "请自行通过控制面板【设置邮局状态】处修改为【启动】即可正常访问。", cnName = "邮局暂停解决方案")
    public static final long MAIL_PAUSE_SOLUTION = 201;
    /** 邮局处理中解决方案 */
    @BeanAttrInfo(value = "请提交咨询反馈，联络管理员处理。", cnName = "邮局处理中解决方案")
    public static final long MAIL_PENDING_SOLUTION = 202;
    /** 邮局待开通解决方案 */
    @BeanAttrInfo(value = "请提交咨询反馈，联络管理员处理。", cnName = "邮局待开通解决方案")
    public static final long MAIL_STAY_CREATE_SOLUTION = 203;
    /** 邮局未开通解决方案 */
    @BeanAttrInfo(value = "请提交咨询反馈，联络管理员处理。", cnName = "邮局未开通解决方案")
    public static final long MAIL_NONE_CREATE_SOLUTION = 204;

    /** IDC不正常解决方案 */
    @BeanAttrInfo(value = "请联络机房人员【13340881075】进行确认。", cnName = "IDC不正常解决方案")
    public static final long IDC_ABNORMAL_SOLUTION = 601;

    /** 域名访问正常 */
    @BeanAttrInfo(value = "域名访问正常", cnName = "域名访问正常")
    public static final long DOMAIN_ACCESS_OK = 200;
    /** 域名访问不正常301 */
    @BeanAttrInfo(value = "301错误解决方案：①空间控制面板-网站辅助功能-【站点重定向】有无重定向记录；②空间web目录下是否存在.htaccess规则文件，并确认是否为正确的设置；③程序中使用有重定向设置，需要客户联系程序商检查。", cnName = "301错误可能原因：①使用了网站重定向功能；②使用了伪静态重定向；③程序中有重定向。")
    public static final long DOMAIN_ACCESS_ERR_301 = 301;
    /** 域名访问不正常302 */
    @BeanAttrInfo(value = "302错误解决方案：①空间控制面板-网站辅助功能-【站点重定向】有无重定向记录；②空间web目录下是否存在.htaccess规则文件，并确认是否为正确的设置；③程序中使用有重定向设置，需要客户联系程序商检查。", cnName = "302错误可能原因：①使用了网站重定向功能；②使用了伪静态重定向；③程序中有重定向。")
    public static final long DOMAIN_ACCESS_ERR_302 = 302;
    /** 域名访问不正常400 */
    @BeanAttrInfo(value = "400错误解决方案：登录空间控制面板-网站基本功能-【域名绑定】绑定域名。", cnName = "400错误可能原因：①域名没有绑定到空间；②域名没有解析到对应的空间地址。")
    public static final long DOMAIN_ACCESS_ERR_400 = 400;
    /** 域名访问不正常401 */
    @BeanAttrInfo(value = "401错误解决方案：①空间控制面板-网站基本功能-【 修改FTP密码】修改一次FTP密码；②请提交咨询反馈，联络管理员处理。", cnName = "401错误可能原因：①空间访问用户授权密码错误 ；②空间目录或者空间目录下文件没有相关用户读取权限。")
    public static final long DOMAIN_ACCESS_ERR_401 = 401;
    /** 域名访问不正常403 */
    @BeanAttrInfo(value = "403错误解决方案：空间控制面板-网站基本功能-【 设置默认首页】设置首页或者更改首页优先访问顺序。", cnName = "403错误可能原因：没有设置空间默认访问首页。")
    public static final long DOMAIN_ACCESS_ERR_403 = 403;
    /** 域名访问不正常404 */
    @BeanAttrInfo(value = "404错误解决方案：①通过FTP上传不存在的文件；②上传适合空间支持的程序或者升级到可以支持该程序的空间类型；请提交咨询反馈，联络管理员处理。", cnName = "404错误可能原因：①访问的文件不存在；②空间不支持该类文件访问；③没有设置该类程序的脚本映射。")
    public static final long DOMAIN_ACCESS_ERR_404 = 404;
    /** 域名访问不正常500 */
    @BeanAttrInfo(value = "500错误解决方案：将程序下载到本地进行检查，或者联系程序提供商进行检查，确认本地可以正常运行后重新上传到空间。", cnName = "500错误可能原因：程序内部错误，一般为程序异常。")
    public static final long DOMAIN_ACCESS_ERR_500 = 500;
    /** 域名访问不正常503 */
    @BeanAttrInfo(value = "503错误解决方案：①请提交咨询反馈，联络管理员处理；②修改被攻击域名的解析到其他地址，待停止后重新解析会原IP。", cnName = "503错误可能原因：①进程池没有启动；②网站访问量过大或者被攻击。")
    public static final long DOMAIN_ACCESS_ERR_503 = 503;
    /** 域名访问不正常（没获取到域名访问状态码） */
    @BeanAttrInfo(value = "没获取到域名访问状态码错误解决方案：自行检查", cnName = "没获取到域名访问状态码")
    public static final long DOMAIN_ACCESS_ERR_DEFAULT = 999;

}
