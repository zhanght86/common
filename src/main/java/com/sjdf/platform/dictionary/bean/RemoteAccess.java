package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Create at 2012-04-09
 *
 * @author 王正伟
 * @category 远程访问配置信息
 */
@Entity
@DiscriminatorValue("REMOTE_ACCESS")
@BeanName(name = "远程访问配置信息")
public class RemoteAccess extends Dictionary {
    private static final long serialVersionUID = 876180416845869661L;
    /** 连接密码 */
    @BeanAttrInfo(orderBy = 1, value = "&A-anB<'=R6eW_#N|,D93 g)4q?fskdW", cnName = "连接密码")
    public static final long CONNECTION_PASSWORD = 1;

    /** 打印模块访问地址 */
    @BeanAttrInfo(orderBy = 2, value = "http://common.51web.com/api/common/ExpressAction!print.action", cnName = "打印模块访问地址")
    public static final long PRING_MODULE_URL = 2;

    /** 模板添加或更新地址 */
    @BeanAttrInfo(orderBy = 3, value = "http://common.51web.com/api/common/TemplateAction!addOrUpdate.action", cnName = "模板添加或更新地址")
    public static final long TEMPLATE_ADD_OR_UPDATE_URL = 3;

    /** 获取模板数据地址 */
    @BeanAttrInfo(orderBy = 4, value = "http://common.51web.com/api/common/TemplateAction!get.action", cnName = "获取模板数据地址")
    public static final long TEMPLATE_GET_URL = 4;

    /** 日志远程保存接口 */
    @BeanAttrInfo(orderBy = 5, value = "http://common.51web.com/api/common/LogAction!api.action", cnName = "日志远程保存接口")
    public static final long LOGGER_SAVE_URL = 5;

    /** 邮件或短信获取接口 */
    @BeanAttrInfo(orderBy = 6, value = "http://common.51web.com/api/common/MessageTemplateAction!get.action", cnName = "邮件或短信获取接口")
    public static final long MESSAGE_TEMPLATE_GET_URL = 6;

    /** 主站登录验证接口 */
    @BeanAttrInfo(orderBy = 7, value = "http://client.51web.com/api/AuthenticationValidAction!api.action", cnName = "主站登录验证接口")
    public static final long EISS_LOGIN_VALID_URL = 7;

    /** 备案登录验证接口 */
    @BeanAttrInfo(orderBy = 8, value = "http://beian.51web.com/ERS/apiAction!getAuthentication.action", cnName = "备案登录验证接口")
    public static final long RECORD_LOGIN_VALID_URL = 8;

    /** 添加或更新关联信息接口 */
    @BeanAttrInfo(orderBy = 9, value = "http://common.51web.com/user/common/AssociateAction!addOrUpdate.action", cnName = "添加或更新关联信息接口")
    public static final long ASSOCIATE_SAVE_OR_UPDATE_URL = 9;

    /** 获取关联信息接口 */
    @BeanAttrInfo(orderBy = 10, value = "http://common.51web.com/user/common/AssociateAction!get.action", cnName = "获取关联信息接口")
    public static final long ASSOCIATE_GET_URL = 10;

    /** 查询手机号码归属地接口 */
    @BeanAttrInfo(orderBy = 11, value = "http://apis.juhe.cn/mobile/get?key=4ed2bbdb054bec2bfcfec05239441bb8&phone=", cnName = "查询手机号码归属地接口")
    public static final long QUERY_PHONE_NUM_PLACE_URL = 11;

    /** 备案系统大数据传输接口 */
    @BeanAttrInfo(orderBy = 12, value = "http://beian.51web.com/apiAction!getLargeData.action", cnName = "备案系统大数据传输接口")
    public static final long ERS_LARGE_DATA_URL = 12;

    /** 备案系统取消接入接口 */
    @BeanAttrInfo(orderBy = 13, value = "http://beian.51web.com/apiAction!cancelAccess.action", cnName = "备案系统取消接入接口")
    public static final long ERS_CANCEL_ACCESS = 13;

    /** 查询备案系统域名黑名单接口 */
    @BeanAttrInfo(orderBy = 14, value = "http://beian.51web.com/apiAction!getYmBlack.action", cnName = "备案系统域名黑名单接口")
    public static final long QUERY_DOMAIN_BLACK_URL = 14;

    /** 主站系统检测域名是否在黑名单中接口 */
    @BeanAttrInfo(orderBy = 15, value = "http://client.51web.com/api/BlacklistQueryAction.check.ation", cnName = "主站系统检测域名是否在黑名单中接口")
    public static final long EISS_DOMAIN_CHECK_IN_BLACKLIST = 15;

    /** 业务公共平台展示信息接口 */
    @BeanAttrInfo(orderBy = 16, value = "http://common.51web.com/api/common/DisplayInfoAction!getDisplayInfoList.action", cnName = "业务公共平台展示信息接口")
    public static final long QUERY_DISPLAY_INFO_URL = 16;

    /** 外部系统连接密码 */
    @BeanAttrInfo(orderBy = 17, value = "C94wIOFf0PQfj3k742Nv", cnName = "外部系统连接密码")
    public static final long EXTERNAL_CONNECTION_PASSWORD = 17;

    /** 获取云主机产品域名个数限制接口 */
    @BeanAttrInfo(orderBy = 18, value = "http://client.51web.com/api/ProductInfoAction!apiChostBindings.action", cnName = "获取云主机产品域名个数限制接口")
    public static final long CLOUD_HOST_DOMAIN_LIMIT = 18;

    /** 地理位置信息初始化URL */
    @BeanAttrInfo(orderBy = 19, value = "http://common.51web.com/api/common/LocationAction!init.action", cnName = "地理位置信息初始化URL")
    public static final long LOCATION_INIT_URL = 19;

    /** 51hkidc用户名 */
    @BeanAttrInfo(orderBy = 20, value = "51web", cnName = "51hkidc用户名")
    public static final long HKIDC_USER_NAME = 20;

    /** 51hkidc连接密码 */
    @BeanAttrInfo(orderBy = 21, value = "a2c98c54ce10295dd6d6d0c20bae8bd0", cnName = "51hkidc连接密码")
    public static final long HKIDC_CONNECTION_PASSWORD = 21;

    /** 51hkidc api前缀地址 */
    @BeanAttrInfo(orderBy = 22, value = "http://api.51hkidc.com/api/hkidc/", cnName = "51hkidc api前缀地址")
    public static final long HKIDC_API_URL_PREFIX = 22;

    /** 产品验证（云主机，虚拟主机，VPS，IDC） */
    @BeanAttrInfo(orderBy = 23, value = "http://client.51web.com/api/ProductInfoAction!api.action", cnName = "产品验证")
    public static final long PRODUCT_INFO_VERIFY = 23;

    /** 获取产品（云主机，虚拟主机，VPS，IDC）可备案域名个数 */
    @BeanAttrInfo(orderBy = 24, value = "http://client.51web.com/api/ProductInfoAction!apiChostBindings.action", cnName = "获取产品可备案域名个数")
    public static final long PRODUCT_INFO_BINDING_COUNT = 24;

    /** 根据域名获取产品信息 */
    @BeanAttrInfo(orderBy = 25, value = "http://client.51web.com/api/ProductInfoAction!apiQueryProductInfoByDomain.action", cnName = "根据域名获取产品信息 ")
    public static final long PRODUCT_INFO_OF_DOMAIN = 25;

    /** 备案系统--变更IP接口 */
    @BeanAttrInfo(orderBy = 26, value = "http://beian.51web.com/apiAction!modifyIpOfWebInfo.action", cnName = "备案系统--变更IP接口")
    public static final long RECORD_APIACTION_MODIFY_IP_OF_WEB_INFO = 26;

    /** 备案系统--管局WebService查询接口 */
    @BeanAttrInfo(orderBy = 27, value = "http://sccaisp.miitbeian.gov.cn/BeianStatusWebService/queryBeianStatus", cnName = "备案系统--管局WebService查询接口")
    public static final long RECORD_WEBSERVICE_QUERY_API = 27;

    /** 备案系统--管局WebService上传和下载接口 */
    @BeanAttrInfo(orderBy = 28, value = "http://sccaisp.miitbeian.gov.cn/ISPWebService/upDownLoad", cnName = "备案系统--管局WebService上传和下载接口")
    public static final long RECORD_WEBSERVICE_UPDOWNLOAD_API = 28;

    /** 业务公共平台--附件修改接口 */
    @BeanAttrInfo(orderBy = 29, value = "http://common.51web.com/api/common/AttachmentAction!modifyAttachmentList.action", cnName = "业务公共平台--附件修改接口")
    public static final long COMMON_ATTACHMENT_MODIFY_API = 29;

    /** 业务公共平台--附件获取接口 */
    @BeanAttrInfo(orderBy = 30, value = "http://common.51web.com/api/common/AttachmentAction!getAttachmentList.action", cnName = "业务公共平台--附件获取接口")
    public static final long COMMON_ATTACHMENT_GET_API = 30;

    /** 业务公共平台--附件新增接口 */
    @BeanAttrInfo(orderBy = 31, value = "http://common.51web.com/api/common/AttachmentAction!storeAttachmentList.action", cnName = "业务公共平台--附件新增接口")
    public static final long COMMON_ATTACHMENT_STORE_API = 31;

    /** 附件图片显示的请求地址 */
    @BeanAttrInfo(value = "http://common.51web.com/admin/common/AttachmentAction!showImage.action?attachmentDisplayUrl=attachmentTempPath", orderBy = 32, cnName = "附件图片显示的请求地址")
    public static final long ATTACHMENT_IMAGE_ACCESS_URL = 32;

    /**
     * =====================业务公共平台--页面内容管理相关===================
     */
    /** 页面内容管理初始化URL */
    @BeanAttrInfo(orderBy = 33, value = "http://common.51web.com/api/common/PageContentManageAction!init.action", cnName = "页面内容管理初始化URL")
    public static final long COMMON_PAGE_CONTENT_INIT_URL = 33;

    /** linux+wdcp帮助中心url地址 */
    @BeanAttrInfo(orderBy = 34, value = "http://help.cdnhost.cn", cnName = "linux+wdcp帮助中心url地址")
    public static final long HELP_CENTER_LINUX_WDCP = 34;

    /** window+管理助手帮助中心url地址 */
    @BeanAttrInfo(orderBy = 35, value = "http://help.cdnhost.cn", cnName = "window+管理助手帮助中心url地址")
    public static final long HELP_CENTER_WINDOW_MANAGER = 35;

    /** 备案接口--根据域名判断是否能直接变更IP */
    @BeanAttrInfo(orderBy = 36, value = "http://beian.51web.com/apiAction!canModifyIp.action", cnName = "备案接口--根据域名判断是否能直接变更IP")
    public static final long RECORD_CAN_MODIFY_IP_URL = 36;

    /** 附件：保存图片到静态服务器 */
    @BeanAttrInfo(orderBy = 37, value = "http://common.51web.com/api/common/AttachmentAction!saveImage.action", cnName = "附件：保存图片到静态服务器")
    public static final long ATTACHMENT_IMAGE_STATIC = 37;

    /** common平台收集其他平台上的权限信息 */
    @BeanAttrInfo(orderBy = 38, value = "http://common.51web.com/api/common/PermissionAction!collect.action", cnName = "common平台权限信息收集URL")
    public static final long COMMON_PERMISSION_COLLECT_URL = 38;

    /** 会员信息认证平台接口URL */
    @BeanAttrInfo(orderBy = 39, value = "http://common.51web.com/api/common/AuthenticateAction!auth.action", cnName = "会员信息认证平台接口URL")
    public static final long COMMON_AUTHENTICATE_URL = 39;

    /** 主站-发送邮件接口 */
    @BeanAttrInfo(orderBy = 40, value = "http://client.51web.com/api/SendEmailAction!api.action", cnName = "主站-发送邮件接口")
    public static final long EISS_SEND_EMAIL_API = 40;

    /** 主站-发送短信接口 */
    @BeanAttrInfo(orderBy = 41, value = "http://client.51web.com/api/SendEmailAction!sendSms.action", cnName = "主站-发送短信接口")
    public static final long EISS_SEND_SMS_API = 41;

    /** 查询IP归属地接口--淘宝接口 */
    @BeanAttrInfo(orderBy = 11, value = "http://ip.taobao.com/service/getIpInfo.php?ip=", cnName = "查询IP归属地接口--淘宝接口")
    public static final long QUERY_IP_PLACE_URL = 42;

    /** 财务系统接口前缀 */
    @BeanAttrInfo(value = "http://finance.51web.com/api/finance", cnName = "财务系统接口前缀")
    public static final long FINANCE_URL_PREFIX = 43;

    /** 查询身份证归属地接口 */
    @BeanAttrInfo(value = "http://api.k780.com/?app=idcard.get&appkey=10003&sign=b59bc3ef6191eb9f747dd4e83c99f2a4&format=json&idcard=", cnName = "查询身份证归属地接口")
    public static final long QUERY_ID_CARD_NUM_PLACE_URL = 44;

    /** 通过域名停止产品接口 */
    @BeanAttrInfo(value = "http://client.51web.com/api/ProductInfoAction!stopProductByDomain.action", cnName = "通过域名停止产品接口")
    public static final long STOP_PRODUCT_BY_DOMAIN = 45;

    /** 通过域名恢复产品接口 */
    @BeanAttrInfo(value = "http://client.51web.com/api/ProductInfoAction!stopProductByDomain.action", cnName = "通过域名恢复产品接口")
    public static final long START_PRODUCT_BY_DOMAIN = 46;

    /** common平台获取权限信息URL */
    @BeanAttrInfo(value = "http://common.51web.com/api/common/PermissionAction!getAll.action", cnName = "common平台获取权限信息URL")
    public static final long COMMON_PERMISSION_GETALL_URL = 47;

    /** 微信公众平台产品管理接口 */
    @BeanAttrInfo(value = "http://weixin.51web.com/api/product!saveOrUpdate.action", cnName = "微信公众平台产品管理接口")
    public static final long WEIXIN_PRODUCT = 48;

    /** 主站获取价格接口 */
    @BeanAttrInfo(value = "http://admin.51web.com/api/WeixinApiAction!getProductPrice.action", cnName = "主站获取价格接口")
    public static final long GET_PRODUCT_PRICE = 49;

    /** 财务接口 */
    @BeanAttrInfo(value = "http://apps.cp.51web.com/CaiWuGuanLi/caiWuApi.jsp", cnName = "财务接口")
    public static final long FINANCE_URL = 50;

    /** 财务接口链接密码 */
    @BeanAttrInfo(value = "Noa~1mvu}RUINrKco;([yg`1Vtg0Y_cH", cnName = "财务接口链接密码")
    public static final long FINANCE_CONN_PWD = 51;

    /** 备案系统--管局WebService备案密码校验接口 */
    @BeanAttrInfo(orderBy = 52, value = "http://sccaisp.miitbeian.gov.cn/BeianStatusWebService/verifyBamm", cnName = "备案系统--管局WebService备案密码校验接口")
    public static final long RECORD_WEBSERVICE_VERIFY_RECORD_PASSWORD_API = 52;

    /** 财务系统访问前缀 */
    @BeanAttrInfo(value = "http://finance.51web.com/", cnName = "财务系统访问前缀")
    public static final long FINANCE_HTTP_URL_PREFIX = 53;

    /** 主站系统登录微信公众平台验证码 */
    @BeanAttrInfo(value = "http://weixin.51web.com/api/eissLogin!getVerifyCode.action", cnName = "主站系统登录微信公众平台验证码")
    public static final long WEIXIN_LOGIN_VERIFYCODE = 54;

    /** 主站获取域名备案状态接口 */
    @BeanAttrInfo(value = "http://admin.51web.com/api/WeixinApiAction!getDomainRecordStatus.action", cnName = "主站获取域名备案状态接口")
    public static final long GET_DOMAIN_RECORD_STATUS = 55;

    /** 主站添加白名单接口 */
    @BeanAttrInfo(value = "http://admin.51web.com/api/WeixinApiAction!addWhiteList.action", cnName = "主站添加白名单接口")
    public static final long ADD_WHITE_LIST = 56;

    /** 主站删除白名单接口 */
    @BeanAttrInfo(value = "http://admin.51web.com/api/WeixinApiAction!deleteWhiteList.action", cnName = "主站删除白名单接口")
    public static final long DELETE_WHITE_LIST = 57;

    /** 获取主站会员信息接口 */
    @BeanAttrInfo(value = "http://admin.51web.com/api/WeixinApiAction!getEissUserByNameAndPass.action", cnName = "获取主站会员信息接口")
    public static final long GET_EISS_USERINFO_API = 58;

    /** 获取超管信息接口 */
    @BeanAttrInfo(value = "http://admin.51web.com/api/WeixinApiAction!getEissAdminUser.action", cnName = "获取超管信息接口")
    public static final long GET_EISS_ADMIN_USER_API = 59;

    /** 获取会员是否存在接口 */
    @BeanAttrInfo(value = "http://admin.51web.com/api/WeixinApiAction!getEissUserByName.action", cnName = "获取会员是否存在接口")
    public static final long EISS_USER_IS_EXISTS = 60;

    /** 获取会员联系信息接口 */
    @BeanAttrInfo(value = "http://admin.51web.com/api/WeixinApiAction!getUserInfoByName.action", cnName = "获取会员联系信息接口")
    public static final long GET_EISS_USER_CONTACT_INFO = 61;

    /** 财务接口调用方式 */
    @BeanAttrInfo(value = "PHPRPC", cnName = "财务接口调用方式")
    public static final long FINANCE_CALL_MODE = 62;

    /** 财务扣款接口 */
    @BeanAttrInfo(value = "http://apps.cp.51web.com/CaiWuGuanLi/caiWuApi.jsp", cnName = "财务扣款接口")
    public static final long FINANCE_URL_KOUKUAN = 63;

    /** 财务退款接口 */
    @BeanAttrInfo(value = "http://apps.cp.51web.com/CaiWuGuanLi/caiWuApi.jsp", cnName = "财务退款接口")
    public static final long FINANCE_URL_TUIKUAN = 64;

    /** 主站API接口前缀 */
    @BeanAttrInfo(value = "http://client.51web.com/", cnName = "主站API接口前缀")
    public static final long API_PREFIX_RUL = 65;

    /** 备案主体状态查询接口 */
    @BeanAttrInfo(orderBy = 66, value = "http://beian.51web.com/apiAction!findRecordMainStatusByDomain.action", cnName = "备案主体状态查询接口")
    public static final long ERS_FIND_MAIN_STATUS = 66;

    /** 备案查询备案用户名接口 */
    @BeanAttrInfo(orderBy = 67, value = "http://beian.51web.com/apiAction!findRecordAccountByDomain.action", cnName = "备案查询备案用户名接口")
    public static final long ERS_FIND_ACCOUNT = 67;

    /** 备案查询取消接入原因 */
    @BeanAttrInfo(orderBy = 68, value = "http://beian.51web.com/apiAction!findCancelReasonByDomain.action", cnName = "备案查询取消接入原因")
    public static final long ERS_FIND_CANCEL_REASON = 68;

    /** 通过会员获取机房列表 */
    @BeanAttrInfo(orderBy = 69, value = "http://client.51web.com/api/EissBaseDataApi!getIdcList.action", cnName = "通过会员获取机房列表")
    public static final long EISS_GET_IDC_LIST_BY_MEMBER = 69;

    /** 通过会员获取服务器列表 */
    @BeanAttrInfo(orderBy = 70, value = "http://client.51web.com/api/EissBaseDataApi!getServerList.action", cnName = "通过会员获取服务器列表")
    public static final long EISS_GET_SERVER_LIST_BY_MEMBER = 70;

    /** 通过会员获取托管商列表 */
    @BeanAttrInfo(orderBy = 71, value = "http://client.51web.com/api/EissBaseDataApi!getServiceList.action", cnName = "通过会员获取托管商列表")
    public static final long EISS_GET_SERVICE_LIST_BY_MEMBER = 71;

    /** 通过会员获取子IP列表 */
    @BeanAttrInfo(orderBy = 72, value = "http://client.51web.com/api/EissBaseDataApi!getSubIpList.action", cnName = "通过会员获取子IP列表")
    public static final long EISS_GET_SUB_IP_LIST_BY_MEMBER = 72;

    /** 通过域名获取我司备案信息 */
    @BeanAttrInfo(value = "http://beian.51web.com/ERS/apiAction!getBeianInfo.action", cnName = "通过域名获取我司备案信息")
    public static final long EISS_ERS_GET_BEIAN_INFO = 73;

    /** 通过域名获取备案号 */
    @BeanAttrInfo(value = "http://beian.51web.com/ERS/apiAction!getBeianStatus.action", cnName = "通过域名获取备案号")
    public static final long EISS_ERS_GET_RECORD_CODE = 74;

    /** 判断域名是否在备案系统存在 */
    @BeanAttrInfo(value = "http://beian.51web.com/apiAction!existDomain.action", cnName = "判断域名是否在备案系统存在")
    public static final long EISS_ERS_CHECK_DOMAIN_EXIST = 75;

    /** 老的连接密码(主要API接口使用) */
    @BeanAttrInfo(orderBy = 200, value = "}$UZzOJ=QUiO4$}OIy@[rg.HV1kA,('E", cnName = "老的连接密码(主要API接口使用)")
    public static final long OLD_CONNECTION_PASSWORD = 200L;

    /** 虚拟主机购买接口 */
    @BeanAttrInfo(orderBy = 201, value = "http://client.51web.com/api/VhostBuyAction!api.action", cnName = "虚拟主机购买接口")
    public static final long EISS_VHOST_BUY_API = 201;

    /** 域名续费接口 */
    @BeanAttrInfo(orderBy = 202, value = "http://client.51web.com/api/DomainApiAction!renew.action", cnName = "域名续费接口")
    public static final long EISS_DOMAIN_RENEW_API = 202;

    /** 域名过户接口 */
    @BeanAttrInfo(orderBy = 203, value = "http://client.51web.com/api/DomainApiAction!transfer.action", cnName = "域名过户接口")
    public static final long EISS_DOMAIN_TRANSFER_API = 203;

    /** 域名转入接口 */
    @BeanAttrInfo(orderBy = 204, value = "http://client.51web.com/api/DomainApiAction!in.action", cnName = "域名转入接口")
    public static final long EISS_DOMAIN_IN_API = 204;

    /** 域名转出接口 */
    @BeanAttrInfo(orderBy = 205, value = "http://client.51web.com/api/DomainApiAction!out.action", cnName = "域名转出接口")
    public static final long EISS_DOMAIN_OUT_API = 205;

    /** 域名赎回接口 */
    @BeanAttrInfo(orderBy = 206, value = "http://client.51web.com/api/DomainApiAction!ransom.action", cnName = "域名赎回接口")
    public static final long EISS_DOMAIN_RANSOM_API = 206;

    /** 虚拟主机升级接口 */
    @BeanAttrInfo(orderBy = 207, value = "http://client.51web.com/api/VhostUpgradeAction!api.action", cnName = "虚拟主机升级接口")
    public static final long EISS_VHOST_UPGRADE_API = 207;

    /** 虚拟主机续费接口 */
    @BeanAttrInfo(orderBy = 208, value = "http://client.51web.com/api/VhostRenewAction!api.action", cnName = "虚拟主机续费接口")
    public static final long EISS_VHOST_RENEW_API = 208;

    /** 数据库升级接口 */
    @BeanAttrInfo(orderBy = 209, value = "http://client.51web.com/api/DatabaseUpgradeAction!api.action", cnName = "数据库升级接口")
    public static final long EISS_DB_UPGRADE_API = 209;

    /** 数据库续费接口 */
    @BeanAttrInfo(orderBy = 210, value = "http://client.51web.com/api/DatabaseRenewAction!api.action", cnName = "数据库续费接口")
    public static final long EISS_DB_RENEW_API = 210;

    /** 云主机升级接口 */
    @BeanAttrInfo(orderBy = 211, value = "http://client.51web.com/api/ChostAgentAction!chostUpgrade.action", cnName = "云主机升级接口")
    public static final long EISS_CHOST_UPGRADE_API = 211;

    /** 云主机续费接口 */
    @BeanAttrInfo(orderBy = 212, value = "http://client.51web.com/api/ChostAgentAction!chostRenew.action", cnName = "云主机续费接口")
    public static final long EISS_CHOST_RENEW_API = 212;

    /** 邮局升级接口 */
    @BeanAttrInfo(orderBy = 213, value = "http://client.51web.com/api/MailUpgradeAction!api.action", cnName = "邮局升级接口")
    public static final long EISS_MAIL_UPGRADE_API = 213;

    /** 邮局续费接口 */
    @BeanAttrInfo(orderBy = 214, value = "http://client.51web.com/api/MailRenewAction!api.action", cnName = "邮局续费接口")
    public static final long EISS_MAIL_RENEW_API = 214;

    /** 续费处的域名转入我司功能接口 */
    @BeanAttrInfo(orderBy = 215, value = "http://client.51web.com/api/DomainApiAction!saveInOursPay.action", cnName = "续费处的域名转入我司功能接口")
    public static final long EISS_DOMAIN_SAVE_IN_OUTS_PAY_API = 215;

    /** 云主机批量购买接口 */
    @BeanAttrInfo(orderBy = 216, value = "http://client.51web.com/api/ChostBuyAction!batchBuyChost.action", cnName = "云主机批量购买接口")
    public static final long EISS_CHOST_BATCH_BUY_API = 216;

    /** 数据库购买接口 */
    @BeanAttrInfo(orderBy = 217, value = "http://client.51web.com/api/DatabaseBuyAction!api.action", cnName = "数据库购买接口")
    public static final long EISS_DATABASE_BUY_API = 217;

    /** 企业邮局购买接口 */
    @BeanAttrInfo(orderBy = 218, value = "http://client.51web.com/api/MailBuyAction!api.action", cnName = "企业邮局购买接口")
    public static final long EISS_MAIL_BUY_API = 218;

    /** 域名查询接口 */
    @BeanAttrInfo(orderBy = 219, value = "http://client.51web.com/api/DomainApiAction!query.action", cnName = "域名查询接口")
    public static final long EISS_DOMAIN_QUERY_API = 219;

    /** cn域名注册接口 */
    @BeanAttrInfo(orderBy = 220, value = "http://client.51web.com/api/CnDomainRegisterAction!api.action", cnName = "cn域名注册接口")
    public static final long EISS_DOMAIN_CN_REGISTER_API = 220;

    /** 域名注册接口 */
    @BeanAttrInfo(orderBy = 221, value = "http://client.51web.com/api/DomainApiAction!buy.action", cnName = "域名注册接口")
    public static final long EISS_DOMAIN_REGISTER_API = 221;

    /** cn域名附件上传接口 */
    @BeanAttrInfo(orderBy = 222, value = "http://client.51web.com/api/CnDomainRegisterAction!uploadCnDomainAttachmentApi.action", cnName = "cn域名附件上传接口")
    public static final long EISS_DOMAIN_CN_UPLOAD_ATTACHMENT_API = 222;

    /** 代理会员密码获取接口 */
    @BeanAttrInfo(orderBy = 223, value = "http://client.51web.com/api/EissBaseDataApi!getMemberPwd.action", cnName = "代理会员密码获取接口")
    public static final long EISS_MEMBER_PWD_API = 223;

    /** 获取指定注册商的域名api信息接口 */
    @BeanAttrInfo(orderBy = 224, value = "http://client.51web.com/api/DomainApiAction!userApi.action", cnName = "获取指定注册商的域名api信息接口")
    public static final long EISS_DOMAIN_USERAPI_INFO_API = 224;

    /** 检测注册商对该产品是否提供接口 */
    @BeanAttrInfo(orderBy = 225, value = "http://client.51web.com/api/DomainApiAction!checkDomainApi.action", cnName = "检测注册商对该产品是否提供接口")
    public static final long EISS_CHECK_DOMAIN_API = 225;

    /** 更新联系人信息接口 */
    @BeanAttrInfo(orderBy = 226, value = "http://client.51web.com/api/DomainApiAction!contactUpdateApi.action", cnName = "更新联系人信息接口")
    public static final long EISS_CONTACT_UPDATE_API = 226;

    /** 域名转移API接口 */
    @BeanAttrInfo(orderBy = 227, value = "http://client.51web.com/api/DomainApiAction!domainTransferApi.action", cnName = "域名转移API接口")
    public static final long EISS_GET_DOMAIN_TRANSFER_VO_API = 227;

    /** 通过会员获取注册商列表 */
    @BeanAttrInfo(orderBy = 228, value = "http://client.51web.com/api/EissBaseDataApi!getRegisterList.action", cnName = "通过会员获取注册商列表")
    public static final long EISS_GET_REGISTER_LIST_BY_MEMBER = 228;

    /** 通过会员获取操作系统列表 */
    @BeanAttrInfo(orderBy = 229, value = "http://client.51web.com/api/EissBaseDataApi!getOsList.action", cnName = "通过会员获取操作系统列表")
    public static final long EISS_GET_OS_LIST_BY_MEMBER = 229;

    /** 代理会员Id获取接口 */
    @BeanAttrInfo(orderBy = 230, value = "http://client.51web.com/api/EissBaseDataApi!getMemberId.action", cnName = "代理会员Id获取接口")
    public static final long EISS_MEMBER_ID_API = 230;

    /** 云主机购买服务器最大数获取接口 */
    @BeanAttrInfo(orderBy = 231, value = "http://client.51web.com/api/ChostBuyAction!getServerNum.action", cnName = "云主机购买服务器最大数获取接口")
    public static final long EISS_CHOST_MAX_NUM_API = 231;

    /** 控制面板登陆接口 */
    @BeanAttrInfo(orderBy = 232, value = "http://client.51web.com/api/ConpanelLoginAction!login.action", cnName = "控制面板登陆接口")
    public static final long CONPANEL_LOGIN_API = 232;

    /** 检测云主机状态接口 */
    @BeanAttrInfo(orderBy = 233, value = "http://client.51web.com/api/ChostAgentAction!detectChost.action", cnName = "检测云主机状态接口")
    public static final long EISS_CHOST_DETECT_CHOST_API = 233;

    /** 获取云主机状态接口 */
    @BeanAttrInfo(orderBy = 234, value = "http://client.51web.com/api/ChostAgentAction!getChostStatus.action", cnName = "获取云主机状态接口")
    public static final long EISS_CHOST_GET_CHOST_STATUS_API = 234;

    /** 公共平台接口前缀 */
    @BeanAttrInfo(value = "http://common.51web.com/api/common/", cnName = "公共平台接口前缀")
    public static final long COMMON_PLATFORM_API_PREFIX_URL = 235;

    /** 二级平台进行空间问题检查 */
    @BeanAttrInfo(orderBy = 236, value = "http://client.51web.com/api/EissBaseDataApi!getWebCheckResult.action", cnName = "二级平台进行空间问题检查")
    public static final long EISS_GET_WEB_CHECK = 236;

    /** 二级平台调用主站公共平台接口前缀 */
    @BeanAttrInfo(value = "http://api.common.51web.com/api/common/", cnName = "二级平台调用主站公共平台接口前缀")
    public static final long SJDF_COMMON_PLATFORM_API_PREFIX_URL = 237;

    /** 地理位置信息同步接口 */
    @BeanAttrInfo(value = "http://common.51web.com/api/common/LocationAction!update.action", cnName = "地理位置信息同步接口")
    public static final long COMMON_LOCATION_SAVE_OR_UPDATE_API = 238;


    /** 域名异步上传附件步接口 */
    @BeanAttrInfo(value = "http://client.51web.com/api/DomainApiAction!transferAttachment.action", cnName = "域名异步上传附件异步接口")
    public static final long EISS_DOMAIN_TRANSFER_API_UPLOAD_ASYC = 250;

    /** 域名上传附件后提交 */
    @BeanAttrInfo(value = "http://client.51web.com/api/DomainApiAction!upload.action", cnName = "域名上传附件后提交接口")
    public static final long EISS_DOMAIN_TRANSFER_API_UPLOAD = 251;

    /** 获取云主机状态接口 */
    @BeanAttrInfo(orderBy = 252, value = "http://client.51web.com/api/ChostAgentAction!batchSwitchChost.action", cnName = "批量开关机接口")
    public static final long EISS_CHOST_BATCH_SWITCH_API = 252;

    /** 云主机试用开设接口 */
    @BeanAttrInfo(orderBy = 253, value = "http://client.51web.com/api/ChostTryAction!open.action", cnName = "云主机试用开设接口")
    public static final long EISS_CHOST_TRY_API = 253;
}
