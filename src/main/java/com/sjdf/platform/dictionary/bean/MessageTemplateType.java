package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Create at 2012-08-02
 * 邮件或短信模板类型
 * <pre>
 *  域名管理系统101-->300
 *  企业互联网服务系统301-->
 *  云主机3051-->3999
 *  各产品到期前后续费通知模板4010-->4999
 *  微信公众平台5000-->5999
 *  备案系统相关短信邮件模板10000-->19999
 *  监控系统相关短信邮件模板20000-->29999
 *  世纪利信50000-->59999
 * </pre>
 *
 * @author 王正伟
 */
@Entity
@DiscriminatorValue("MESSAGE_TEMPLATE_TYPE")
@BeanName(name = "邮件或短信模板类型")
public class MessageTemplateType extends Dictionary {
    private static final long serialVersionUID = 552681050538982224L;

    // ====================================域名管理系统===========================================
    /** 修改域名所有者(域名过户) */
    @BeanAttrInfo(value = "mail:1,25,36,38,42", orderBy = 101, systemType = SystemType.DOMAIN, cnName = "修改域名所有者(域名过户)")
    public static final long DOMAIN_UPDATE_REGISTRANT = 101;

    /** 修改域名所有者邮箱 */
    @BeanAttrInfo(value = "mail:1,25,36,42", orderBy = 102, systemType = SystemType.DOMAIN, cnName = "修改域名所有者邮箱")
    public static final long DOMAIN_UPDATE_REGISTRANT_EMAIL = 102;

    /** 修改所有者其他资料 */
    @BeanAttrInfo(value = "mail:1,25,36,42", orderBy = 103, systemType = SystemType.DOMAIN, cnName = "修改所有者其他资料")
    public static final long DOMAIN_UPDATE_REGISTRANT_OTHERS = 103;

    /** 修改管理者邮箱 */
    @BeanAttrInfo(value = "mail:1,25,36,42", orderBy = 104, systemType = SystemType.DOMAIN, cnName = "修改管理者邮箱")
    public static final long DOMAIN_UPDATE_ADMIN_EMAIL = 104;

    /** 修改联系人其他资料 */
    @BeanAttrInfo(value = "mail:1,36,42", orderBy = 105, systemType = SystemType.DOMAIN, cnName = "修改联系人其他资料")
    public static final long DOMAIN_UPDATE_CONTACT = 105;

    /** 获取转移密 */
    @BeanAttrInfo(value = "mail:1,24,36", orderBy = 106, systemType = SystemType.DOMAIN, cnName = "获取转移密")
    public static final long DOMAIN_GET_TRANFER_PWD = 106;

    /** 域名转移注册商 */
    @BeanAttrInfo(value = "mail:1,13,38,36", orderBy = 107, systemType = SystemType.DOMAIN, cnName = "域名转移注册商")
    public static final long DOMAIN_TRANFER_PROVIDER = 107;

    /** 域名注册成功 */
    @BeanAttrInfo(value = "mail:1,7,8,36", orderBy = 108, systemType = SystemType.DOMAIN, cnName = "域名注册成功")
    public static final long DOMAIN_REGISTER_SUCCESS = 108;

    /** 域名到期通知 */
    @BeanAttrInfo(value = "mail:1,36", orderBy = 109, systemType = SystemType.DOMAIN, cnName = "域名到期通知")
    public static final long DOMAIN_EXPIRED_NOTICE = 109;

    /** 域名逾期删除通知 */
    @BeanAttrInfo(value = "mail:1,36", orderBy = 110, systemType = SystemType.DOMAIN, cnName = "域名逾期删除通知")
    public static final long DOMAIN_EXPIRED_DELETE_NOTICE = 110;

    /** 域名续费成功通知 */
    @BeanAttrInfo(value = "mail:1,8,36", orderBy = 111, systemType = SystemType.DOMAIN, cnName = "域名续费成功通知")
    public static final long DOMAIN_RENEW_SUCCESS_NOTICE = 111;

    /** 违法信息删除通知 */
    @BeanAttrInfo(value = "mail:36,38", orderBy = 112, systemType = SystemType.DOMAIN, cnName = "违法信息删除通知")
    public static final long DOMAIN_VIOLATION_DELETE_NOTICE = 112;

    /** 违法网站hold通知 */
    @BeanAttrInfo(value = "mail:1,38,40", orderBy = 113, systemType = SystemType.DOMAIN, cnName = "违法网站hold通知")
    public static final long DOMAIN_VIOLATION_HOLD_NOTICE = 113;

    /** Dns 修改通知 */
    @BeanAttrInfo(value = "mail:1,8,12,18,36,42", orderBy = 114, systemType = SystemType.DOMAIN, cnName = "Dns 修改通知")
    public static final long DOMAIN_DNS_MODIFY_NOTICE = 114;

    /** 域名信息修改成功通知 */
    @BeanAttrInfo(value = "mail:1,8,12,18,36", orderBy = 115, systemType = SystemType.DOMAIN, cnName = "域名信息修改成功通知")
    public static final long DOMAIN_INFO_MODIFY_SUCCESS_NOTICE = 115;

    /** 通知客户信息修改 */
    @BeanAttrInfo(value = "mail:1,36", orderBy = 116, systemType = SystemType.DOMAIN, cnName = "通知客户信息修改")
    public static final long DOMAIN_NOTICE_MODIFY_INFO = 116;

    /** 侵权通知 */
    @BeanAttrInfo(value = "mail:1,36,38,40", orderBy = 117, systemType = SystemType.DOMAIN, cnName = "侵权通知")
    public static final long DOMAIN_INFRINGEMENT_NOTICE = 117;

    /** 域名被提起诉讼通知 */
    @BeanAttrInfo(value = "mail:1,36", orderBy = 118, systemType = SystemType.DOMAIN, cnName = "域名被提起诉讼通知")
    public static final long DOMAIN_LITIGATION_NOTICE = 118;

    /** 仲裁通知 */
    @BeanAttrInfo(value = "mail:1,36,38", orderBy = 119, systemType = SystemType.DOMAIN, cnName = "仲裁通知")
    public static final long DOMAIN_ARBITRATION_NOTICE = 119;

    /** 域名转移情况通知 */
    @BeanAttrInfo(value = "mail:1,22,36", orderBy = 120, systemType = SystemType.DOMAIN, cnName = "域名转移情况通知")
    public static final long DOMAIN_TRANSFER_NOTICE = 120;

    /** 资料审核通过通知 */
    @BeanAttrInfo(value = "mail:1,22,36,42", orderBy = 121, systemType = SystemType.DOMAIN, cnName = "资料审核通过通知")
    public static final long DOMAIN_ATTACHMENT_VERIFY_SUCCESS_NOTICE = 121;

    /** 域名订单记录-状态更改通知 */
    @BeanAttrInfo(value = "mobile:1,22,36,43;mail:1,22,36,43,44", orderBy = 122, systemType = SystemType.EISS, cnName = "域名订单记录状态变更通知122")
    public static final long DOMAIN_ORDER_RECORD_STATUS_CHANGE_NOTICE = 122;

    /** 域名赎回成功 */
    @BeanAttrInfo(value = "mail:1,8,36", orderBy = 123, systemType = SystemType.DOMAIN, cnName = "域名赎回成功")
    public static final long DOMAIN_REDEMPTION_SUCCESS = 123;

    /** 域名转入进度通知 */
    @BeanAttrInfo(value = "mail:1,22,36", orderBy = 124, systemType = SystemType.DOMAIN, cnName = "域名转入进度通知")
    public static final long DOMAIN_TRANSFER_IN = 124;

    /** 域名转出进度通知 */
    @BeanAttrInfo(value = "mail:1,22,36", orderBy = 125, systemType = SystemType.DOMAIN, cnName = "域名转出进度通知")
    public static final long DOMAIN_TRANSFER_OUT = 125;

    /** 域名过户成功通知 */
    @BeanAttrInfo(value = "mail:1,36,42", orderBy = 126, systemType = SystemType.DOMAIN, cnName = "域名过户成功通知")
    public static final long DOMAIN_UPDATE_REGISTRANT_SUCCESS = 126;

    /** 修改域名所有者邮件成功通知 */
    @BeanAttrInfo(value = "mail:1,36,42", orderBy = 127, systemType = SystemType.DOMAIN, cnName = "修改域名所有者邮件成功通知")
    public static final long DOMAIN_UPDATE_OWNER_EMAIL_SUCCESS = 127;

    /** 修改域名所有者邮件取消或失效通知 */
    @BeanAttrInfo(value = "mail:1,36,42", orderBy = 128, systemType = SystemType.DOMAIN, cnName = "修改域名所有者邮件取消或失效通知")
    public static final long DOMAIN_UPDATE_OWNER_EMAIL_CANCEL_OR_INVALID = 128;

    /** 域名过户取消或失效通知 */
    @BeanAttrInfo(value = "mail:1,36,42", orderBy = 129, systemType = SystemType.DOMAIN, cnName = "域名过户取消或失效通知")
    public static final long DOMAIN_TRANSFER_CANCEL_OR_INVALID = 129;

    // ====================================企业互联网服务系统===========================================
    /** 未解析到我司产品域名 */
    @BeanAttrInfo(value = "mail:30", orderBy = 301, systemType = SystemType.EISS, cnName = "未解析到我司产品域名")
    public static final long EISS_DOMAIN_HOST_NOT_IN_IDC = 301;

    /** 未准确解析到我司产品域名 */
    @BeanAttrInfo(value = "mobile:29;mail:29,30", orderBy = 302, systemType = SystemType.EISS, cnName = "未准确解析到我司产品域名")
    public static final long EISS_DOMAIN_HOST_NOT_IN_PRODUCT = 302;

    /** 非国内机房产品不能备案域名 */
    @BeanAttrInfo(value = "mobile:29;mail:29,30", orderBy = 303, systemType = SystemType.EISS, cnName = "非国内机房产品不能备案域名")
    public static final long EISS_CAN_NOT_RECORD = 303;

    /** 需新增接入域名 */
    @BeanAttrInfo(value = "mobile:29;mail:29,30", orderBy = 304, systemType = SystemType.EISS, cnName = "需新增接入域名")
    public static final long EISS_NEW_ACCESS = 304;

    /** 需人工处理域名 */
    @BeanAttrInfo(value = "mobile:29;mail:29,30", orderBy = 305, systemType = SystemType.EISS, cnName = "需人工处理域名")
    public static final long EISS_NEW_305 = 305;

    /** 主站邮件模板306 */
    @BeanAttrInfo(value = "", orderBy = 306, systemType = SystemType.EISS, cnName = "主站邮件模板306")
    public static final long EISS_NEW_306 = 306;

    /** 直接取消接入域名 */
    @BeanAttrInfo(value = "mobile:29;mail:29,1", orderBy = 307, systemType = SystemType.EISS, cnName = "直接取消接入域名")
    public static final long EISS_NEW_307 = 307;

    /** 未绑定我司产品域名取消接入通知 */
    @BeanAttrInfo(value = "mail:29,30", orderBy = 308, systemType = SystemType.EISS, cnName = "未绑定我司产品域名取消接入通知")
    public static final long EISS_NEW_308 = 308;

    /** 未解析到我司产品域名取消接入通知 */
    @BeanAttrInfo(value = "mail:29,30", orderBy = 309, systemType = SystemType.EISS, cnName = "未解析到我司产品域名取消接入通知")
    public static final long EISS_NEW_309 = 309;

    /** 非国内机房产品不能备案域名取消接入通知 */
    @BeanAttrInfo(value = "mail:29,30", orderBy = 310, systemType = SystemType.EISS, cnName = "非国内机房产品不能备案域名取消接入通知")
    public static final long EISS_NEW_310 = 310;

    /** 主站邮件模板311 */
    @BeanAttrInfo(value = "", orderBy = 311, systemType = SystemType.EISS, cnName = "主站邮件模板311")
    public static final long EISS_NEW_311 = 311;

    /** 主站邮件模板312 */
    @BeanAttrInfo(value = "", orderBy = 312, systemType = SystemType.EISS, cnName = "主站邮件模板312")
    public static final long EISS_NEW_312 = 312;

    /** 主站邮件模板313 */
    @BeanAttrInfo(value = "", orderBy = 313, systemType = SystemType.EISS, cnName = "主站邮件模板313")
    public static final long EISS_NEW_313 = 313;

    /** 主站邮件模板314 */
    @BeanAttrInfo(value = "", orderBy = 314, systemType = SystemType.EISS, cnName = "主站邮件模板314")
    public static final long EISS_NEW_314 = 314;

    /** 主站邮件模板315 */
    @BeanAttrInfo(value = "", orderBy = 315, systemType = SystemType.EISS, cnName = "主站邮件模板315")
    public static final long EISS_NEW_315 = 315;

    /** 主站邮件模板316 */
    @BeanAttrInfo(value = "", orderBy = 316, systemType = SystemType.EISS, cnName = "主站邮件模板316")
    public static final long EISS_NEW_316 = 316;

    /** 主站邮件模板317 */
    @BeanAttrInfo(value = "", orderBy = 317, systemType = SystemType.EISS, cnName = "主站邮件模板317")
    public static final long EISS_NEW_317 = 317;

    /** 主站邮件模板318 */
    @BeanAttrInfo(value = "", orderBy = 318, systemType = SystemType.EISS, cnName = "主站邮件模板318")
    public static final long EISS_NEW_318 = 318;

    /** 主站邮件模板319 */
    @BeanAttrInfo(value = "", orderBy = 319, systemType = SystemType.EISS, cnName = "主站邮件模板319")
    public static final long EISS_NEW_319 = 319;

    /** 主站邮件模板320 */
    @BeanAttrInfo(value = "", orderBy = 320, systemType = SystemType.EISS, cnName = "主站邮件模板320")
    public static final long EISS_NEW_320 = 320;

    /** 主站邮件模板321 */
    @BeanAttrInfo(value = "", orderBy = 321, systemType = SystemType.EISS, cnName = "主站邮件模板321")
    public static final long EISS_NEW_321 = 321;

    /** 主站邮件模板322 */
    @BeanAttrInfo(value = "", orderBy = 322, systemType = SystemType.EISS, cnName = "主站邮件模板322")
    public static final long EISS_NEW_322 = 322;

    /** 主站邮件模板323 */
    @BeanAttrInfo(value = "", orderBy = 323, systemType = SystemType.EISS, cnName = "主站邮件模板323")
    public static final long EISS_NEW_323 = 323;

    /** 主站邮件模板324 */
    @BeanAttrInfo(value = "", orderBy = 324, systemType = SystemType.EISS, cnName = "主站邮件模板324")
    public static final long EISS_NEW_324 = 324;

    /** 主站邮件模板325 */
    @BeanAttrInfo(value = "", orderBy = 325, systemType = SystemType.EISS, cnName = "主站邮件模板325")
    public static final long EISS_NEW_325 = 325;

    /** 主站邮件模板326 */
    @BeanAttrInfo(value = "", orderBy = 326, systemType = SystemType.EISS, cnName = "主站邮件模板326")
    public static final long EISS_NEW_326 = 326;

    /** 主站邮件模板327 */
    @BeanAttrInfo(value = "", orderBy = 327, systemType = SystemType.EISS, cnName = "主站邮件模板327")
    public static final long EISS_NEW_327 = 327;

    /** 主站邮件模板328 */
    @BeanAttrInfo(value = "", orderBy = 328, systemType = SystemType.EISS, cnName = "主站邮件模板328")
    public static final long EISS_NEW_328 = 328;

    /** 主站邮件模板329 */
    @BeanAttrInfo(value = "", orderBy = 329, systemType = SystemType.EISS, cnName = "主站邮件模板329")
    public static final long EISS_NEW_329 = 329;

    /** 主站邮件模板330 */
    @BeanAttrInfo(value = "", orderBy = 330, systemType = SystemType.EISS, cnName = "主站邮件模板330")
    public static final long EISS_NEW_330 = 330;

    /** 主站邮件模板331 */
    @BeanAttrInfo(value = "", orderBy = 331, systemType = SystemType.EISS, cnName = "主站邮件模板331")
    public static final long EISS_NEW_331 = 331;

    /** 主站邮件模板332 */
    @BeanAttrInfo(value = "", orderBy = 332, systemType = SystemType.EISS, cnName = "主站邮件模板332")
    public static final long EISS_NEW_332 = 332;

    /** 主站邮件模板333 */
    @BeanAttrInfo(value = "", orderBy = 333, systemType = SystemType.EISS, cnName = "主站邮件模板333")
    public static final long EISS_NEW_333 = 333;

    /** 主站邮件模板334 */
    @BeanAttrInfo(value = "", orderBy = 334, systemType = SystemType.EISS, cnName = "主站邮件模板334")
    public static final long EISS_NEW_334 = 334;

    /** 主站邮件模板335 */
    @BeanAttrInfo(value = "", orderBy = 335, systemType = SystemType.EISS, cnName = "主站邮件模板335")
    public static final long EISS_NEW_335 = 335;

    /** 主站邮件模板336 */
    @BeanAttrInfo(value = "", orderBy = 336, systemType = SystemType.EISS, cnName = "主站邮件模板336")
    public static final long EISS_NEW_336 = 336;

    /** 主站邮件模板337 */
    @BeanAttrInfo(value = "", orderBy = 337, systemType = SystemType.EISS, cnName = "主站邮件模板337")
    public static final long EISS_NEW_337 = 337;

    /** 主站邮件模板338 */
    @BeanAttrInfo(value = "", orderBy = 338, systemType = SystemType.EISS, cnName = "主站邮件模板338")
    public static final long EISS_NEW_338 = 338;

    /** 主站邮件模板339 */
    @BeanAttrInfo(value = "", orderBy = 339, systemType = SystemType.EISS, cnName = "主站邮件模板339")
    public static final long EISS_NEW_339 = 339;

    /** 主站邮件模板340 */
    @BeanAttrInfo(value = "", orderBy = 340, systemType = SystemType.EISS, cnName = "主站邮件模板340")
    public static final long EISS_NEW_340 = 340;

    /** 主站邮件模板341 */
    @BeanAttrInfo(value = "", orderBy = 341, systemType = SystemType.EISS, cnName = "主站邮件模板341")
    public static final long EISS_NEW_341 = 341;

    /** 主站邮件模板342 */
    @BeanAttrInfo(value = "", orderBy = 342, systemType = SystemType.EISS, cnName = "主站邮件模板342")
    public static final long EISS_NEW_342 = 342;

    /** 主站邮件模板343 */
    @BeanAttrInfo(value = "", orderBy = 343, systemType = SystemType.EISS, cnName = "主站邮件模板343")
    public static final long EISS_NEW_343 = 343;

    /** 主站邮件模板344 */
    @BeanAttrInfo(value = "", orderBy = 344, systemType = SystemType.EISS, cnName = "主站邮件模板344")
    public static final long EISS_NEW_344 = 344;

    /** 主站邮件模板345 */
    @BeanAttrInfo(value = "", orderBy = 345, systemType = SystemType.EISS, cnName = "主站邮件模板345")
    public static final long EISS_NEW_345 = 345;

    /** 主站邮件模板346 */
    @BeanAttrInfo(value = "", orderBy = 346, systemType = SystemType.EISS, cnName = "主站邮件模板346")
    public static final long EISS_NEW_346 = 346;

    /** 主站邮件模板347 */
    @BeanAttrInfo(value = "", orderBy = 347, systemType = SystemType.EISS, cnName = "主站邮件模板347")
    public static final long EISS_NEW_347 = 347;

    /** 主站邮件模板348 */
    @BeanAttrInfo(value = "", orderBy = 348, systemType = SystemType.EISS, cnName = "主站邮件模板348")
    public static final long EISS_NEW_348 = 348;

    /** 主站邮件模板349 */
    @BeanAttrInfo(value = "", orderBy = 349, systemType = SystemType.EISS, cnName = "主站邮件模板349")
    public static final long EISS_NEW_349 = 349;

    /** 主站邮件模板350 */
    @BeanAttrInfo(value = "", orderBy = 350, systemType = SystemType.EISS, cnName = "主站邮件模板350")
    public static final long EISS_NEW_350 = 350;

    /** 主站邮件模板351 */
    @BeanAttrInfo(value = "", orderBy = 351, systemType = SystemType.EISS, cnName = "主站邮件模板351")
    public static final long EISS_NEW_351 = 351;

    /** 主站邮件模板352 */
    @BeanAttrInfo(value = "", orderBy = 352, systemType = SystemType.EISS, cnName = "主站邮件模板352")
    public static final long EISS_NEW_352 = 352;

    /** 主站邮件模板353 */
    @BeanAttrInfo(value = "", orderBy = 353, systemType = SystemType.EISS, cnName = "主站邮件模板353")
    public static final long EISS_NEW_353 = 353;

    /** 主站邮件模板354 */
    @BeanAttrInfo(value = "", orderBy = 354, systemType = SystemType.EISS, cnName = "主站邮件模板354")
    public static final long EISS_NEW_354 = 354;

    /** 域名注册状态更改通知355 */
    @BeanAttrInfo(value = "mobile:1,22,36,44;mail:1,22,36,44", orderBy = 355, systemType = SystemType.EISS, cnName = "域名注册状态更改通知")
    public static final long EISS_DOMAIN_STATUS_CHANGE_NOTICE = 355;

    /** 域名注册成功通知 */
    @BeanAttrInfo(value = "mail:1,7,8,18,3;mobile:1,8", orderBy = 356, systemType = SystemType.EISS, cnName = "域名注册成功通知")
    public static final long EISS_DOMAIN_REGISTER_SUCCESS_NOTICE = 356;

    /** 域名续费成功通知 */
    @BeanAttrInfo(value = "mail:1,7,8,18,3;mobile:1,8", orderBy = 357, systemType = SystemType.EISS, cnName = "域名续费成功通知")
    public static final long EISS_DOMAIN_RENEW_SUCCESS_NOTICE = 357;

    /** 域名赎回成功通知 */
    @BeanAttrInfo(value = "mail:1,7,8,18,3;mobile:1,8", orderBy = 358, systemType = SystemType.EISS, cnName = "域名赎回成功通知")
    public static final long EISS_DOMAIN_RANSOM_SUCCESS_NOTICE = 358;

    /** 域名转入成功通知 */
    @BeanAttrInfo(value = "mail:1,7,8,18,3;mobile:1,8", orderBy = 359, systemType = SystemType.EISS, cnName = "域名转入成功通知")
    public static final long EISS_DOMAIN_IN_SUCCESS_NOTICE = 359;

    /** 域名转出成功通知 */
    @BeanAttrInfo(value = "mail:1,36", orderBy = 360, systemType = SystemType.EISS, cnName = "域名转出成功通知")
    public static final long EISS_DOMAIN_OUT_SUCCESS_NOTICE = 360;

    /** 域名过户成功通知 */
    @BeanAttrInfo(value = "mail:1,36", orderBy = 361, systemType = SystemType.EISS, cnName = "域名过户成功通知")
    public static final long EISS_DOMAIN_TRANSFER_SUCCESS_NOTICE = 361;

    /** 域名转入申请通知 */
    @BeanAttrInfo(value = "mail:1,42,36,3", orderBy = 362, systemType = SystemType.EISS, cnName = "域名转入申请通知")
    public static final long EISS_DOMAIN_IN_APPLY_NOTICE = 362;

    /** 域名转出申请通知 */
    @BeanAttrInfo(value = "mail:1,42,36", orderBy = 363, systemType = SystemType.EISS, cnName = "域名转出申请通知")
    public static final long EISS_DOMAIN_OUT_APPLY_NOTICE = 363;

    /** 域名过户申请通知 */
    @BeanAttrInfo(value = "mail:1,42,36", orderBy = 364, systemType = SystemType.EISS, cnName = "域名过户申请通知")
    public static final long EISS_DOMAIN_TRANSFER_APPLY_NOTICE = 364;

    /** 域名修改所有者邮箱验证码 */
    @BeanAttrInfo(value = "mail:1,25,36", orderBy = 365, systemType = SystemType.EISS, cnName = "域名修改所有者邮箱验证码")
    public static final long EISS_DOMAIN_EDIT_EMAIL_VERIFICATION_CODE = 365;

    /** 域名修改所有者邮箱成功通知 */
    @BeanAttrInfo(value = "mail:1,36,42", orderBy = 366, systemType = SystemType.EISS, cnName = "域名修改所有者邮箱成功通知")
    public static final long EISS_DOMAIN_EDIT_EMAIL_SUCCESS_NOTICE = 366;

    /** 域名转出验证码通知 */
    @BeanAttrInfo(value = "mobile:1,24", orderBy = 368, systemType = SystemType.EISS, cnName = "域名转出验证码通知")
    public static final long EISS_DOMAIN_TRANSFER_OUT_VERIFICATION_CODE = 368;

    /** 用户找回密码验证码通知 */
    @BeanAttrInfo(value = "mail:3,25,36;mobile:3,25,36", orderBy = 367, systemType = SystemType.EISS, cnName = "用户找回密码验证码通知")
    public static final long EISS_MEMBER_FORGET_PASSWORD_VERIFICATION_CODE = 367;

    /** 主站-代理申请通知 */
    @BeanAttrInfo(value = "mail:999;", orderBy = 369, systemType = SystemType.EISS, cnName = "主站-代理申请通知")
    public static final long EISS_AGENT_APPLY_INFORM = 369;

    /** 主站-商务中国授权协议通知 */
    @BeanAttrInfo(value = "mail:999,3", orderBy = 370, systemType = SystemType.EISS, cnName = "主站-商务中国授权协议通知")
    public static final long EISS_BIZCN_LICENSE_AGREEMENT_TIPS = 370;

    /** 主站-用户注册成功通知 */
    @BeanAttrInfo(value = "mail:24,3", orderBy = 371, systemType = SystemType.EISS, cnName = "主站-用户注册成功通知")
    public static final long EISS_NEW_SIGN_UP_SUCCESS = 371;

    /** 虚拟主机,管理员停止-超过系统系统CPU限制 */
    @BeanAttrInfo(value = "mobile:3,18,37;mail:3,18,37", orderBy = 372, systemType = SystemType.EISS, cnName = "虚拟主机-管理员停止-超过系统系统CPU限制")
    public static final long EISS_NEW_VHOST_ADMIN_STOP_CPU_TOO_HIGH = 372;

    /** 虚拟主机,管理员停止-非法信息 */
    @BeanAttrInfo(value = "mobile:3,18,37;mail:3,18,37", orderBy = 373, systemType = SystemType.EISS, cnName = "虚拟主机-管理员停止-非法信息")
    public static final long EISS_NEW_VHOST_ADMIN_STOP_ILLEGAL = 373;

    /** 虚拟主机,管理员停止-网站被攻击 */
    @BeanAttrInfo(value = "mobile:3,18,37;mail:3,18,37", orderBy = 374, systemType = SystemType.EISS, cnName = "虚拟主机-管理员停止-网站被攻击")
    public static final long EISS_NEW_VHOST_ADMIN_STOP_ATTACK = 374;

    /** 虚拟主机,管理员停止-网站流量过大 */
    @BeanAttrInfo(value = "mobile:3,18,37;mail:3,18,37", orderBy = 375, systemType = SystemType.EISS, cnName = "虚拟主机-管理员停止-网站流量过大")
    public static final long EISS_NEW_VHOST_ADMIN_STOP_TRAFFIC_TOO_LARGE = 375;

    /** 虚拟主机,管理员停止-网站程序异常 */
    @BeanAttrInfo(value = "mobile:3,18,37;mail:3,18,37", orderBy = 376, systemType = SystemType.EISS, cnName = "虚拟主机-管理员停止-网站程序异常")
    public static final long EISS_NEW_VHOST_ADMIN_STOP_PROGRAM_EXCEPTION = 376;

    /** 虚拟主机,管理员停止 */
    @BeanAttrInfo(value = "mobile:3,18,37;mail:3,18,37", orderBy = 377, systemType = SystemType.EISS, cnName = "虚拟主机-管理员停止")
    public static final long EISS_NEW_VHOST_ADMIN_STOP = 377;

    /** 虚拟主机域名绑定域名停止(直接客户和代理用户：主站用户) */
    @BeanAttrInfo(value = "mobile:1,37;mail:1,3,36,37,44", orderBy = 378, systemType = SystemType.EISS, cnName = "虚拟主机域名绑定域名停止(直接客户和代理用户：主站用户)")
    public static final long EISS_NEW_VHOST_DOMAIN_STOP_MEMBER = 378;

    /** 虚拟主机域名绑定域名停止(代理下的客户:非主站用户) */
    @BeanAttrInfo(value = "mobile:1,37;mail:1,3,36,37,44", orderBy = 379, systemType = SystemType.EISS, cnName = "虚拟主机域名绑定域名停止(代理下的客户:非主站用户)")
    public static final long EISS_NEW_VHOST_DOMAIN_STOP_NOT_MEMBER = 379;

    /** 域名转入订单废弃通知 */
    @BeanAttrInfo(value = "mobile:1,22,36,43;mail:1,22,36,43,44", orderBy = 380, systemType = SystemType.EISS, cnName = "域名转入订单废弃通知")
    public static final long DOMAIN_TRANSFER_ORDER_RECORD_DISCARD_NOTICE = 380;

    /** 域名状态更改通知(自动任务) */
    @BeanAttrInfo(value = "mobile:1,22,36,44;mail:1,22,36,44", orderBy = 381, systemType = SystemType.EISS, cnName = "域名状态更改通知(自动任务)")
    public static final long EISS_NEW_DOMAIN_STATUS_CHANGE_NOTICE_FOR_AUTOTASK = 381;

    /** 域名强制转出通知 */
    @BeanAttrInfo(value = "mobile:1,22,36,44;mail:1,22,36,44", orderBy = 382, systemType = SystemType.EISS, cnName = "域名强制转出通知")
    public static final long EISS_DOMAIN_FORCED_TRANSFER_NOTICE = 382;

    /** 域名订单附件审核不通过通知 */
    @BeanAttrInfo(value = "mobile:1,36;mail:1,36,44", orderBy = 383, systemType = SystemType.EISS, cnName = "域名订单附件审核不通过通知")
    public static final long EISS_NEW_ORDER_ATTACHMENT_ERROR_NOTICE = 383;

    /** 域名转出订单待注册商确认结果的通知 */
    @BeanAttrInfo(value = "mail:1,36", orderBy = 384, systemType = SystemType.EISS, cnName = "域名转出订单待注册商确认结果的通知")
    public static final long EISS_NEW_ORDER_TRANSFER_OUT_REGISTERED_AUDIT_NOTICE = 384;

    /** CN域名资料审核通过后通知 */
    @BeanAttrInfo(value = "mobile:1,36;mail:1,36", orderBy = 385, systemType = SystemType.EISS, cnName = "CN域名资料审核通过后通知 ")
    public static final long EISS_NEW_CN_ORDER_TRANSFER_OUT_NOTICE = 385;

    /** 代理控制面板咨询反馈邮件通知 */
    @BeanAttrInfo(value = "mail:3,17,56,18,42,36", orderBy = 386, systemType = SystemType.EISS, cnName = "代理控制面板咨询反馈邮件通知 ")
    public static final long EISS_NEW_AGENT_CONPANEL_EMAIL_NOTIFY = 386;

    /** 域名注册失败通知 */
    @BeanAttrInfo(value = "mail:1,18,36,3;mobile:1,8", orderBy = 387, systemType = SystemType.EISS, cnName = "域名注册失败通知")
    public static final long EISS_DOMAIN_REGISTER_FAIL_NOTICE = 387;

    /** 域名续费失败通知 */
    @BeanAttrInfo(value = "mail:1,7,8,18,36,3;mobile:1,8", orderBy = 388, systemType = SystemType.EISS, cnName = "域名续费失败通知")
    public static final long EISS_DOMAIN_RENEW_FAIL_NOTICE = 388;

    /** 网站扫描结果邮件通知 */
    @BeanAttrInfo(value = "mail:1,3,36,44", orderBy = 389, systemType = SystemType.EISS, cnName = "网站扫描结果邮件通知")
    public static final long EISS_DOMAIN_SCAN_EMAIL_NOTICE = 389;

    /** 虚拟主机迁移成功邮件和短信通知 */
    @BeanAttrInfo(value = "mail:2,36,44;mobile:2", orderBy = 390, systemType = SystemType.EISS, cnName = "虚拟主机迁移成功邮件和短信通知")
    public static final long EISS_VHOST_MIGRATE_SUCCESS_NOTICE = 390;

    /** 域名whois信息确认通知 */
    @BeanAttrInfo(value = "mail:1", orderBy = 391, systemType = SystemType.EISS, cnName = "域名whois信息确认通知 ")
    public static final long EISS_DOMAIN_WHOIS_CONFIRMATION_NOTICE = 391;

    /** 用户安全信息绑定验证码通知 */
    @BeanAttrInfo(value = "mail:3,25,36;mobile:3,25,36", orderBy = 400, systemType = SystemType.EISS, cnName = "用户安全信息绑定验证码通知")
    public static final long EISS_MEMBER_SAFETYINFO_BINDING_VERIFICATION_CODE = 400;

    /** 域名过户验证码通知 */
    @BeanAttrInfo(value = "mobile:1,24", orderBy = 401, systemType = SystemType.EISS, cnName = "域名过户验证码通知")
    public static final long EISS_DOMAIN_TRANSFER_OWNER_VERIFICATION_CODE = 401;

    // ========================================云主机================================================
    /** 停止过期的云主机 (代理下的客户:非主站用户) */
    @BeanAttrInfo(value = "mobile:6,8;mail:3,6,8", orderBy = 3051, systemType = SystemType.EISS, cnName = "停止过期的云主机(代理下的客户)")
    public static final long EISS_NEW_STOP_EXPIRD_CHOST_NOT_MEMBER = 3051;

    /** 停止过期的云主机 (直接客户和代理用户：主站用户) */
    @BeanAttrInfo(value = "mobile:6,8;mail:3,6,8", orderBy = 3050, systemType = SystemType.EISS, cnName = "停止过期的云主机(直接客户和代理用户)")
    public static final long EISS_NEW_STOP_EXPIRD_CHOST_MEMBER = 3050;

    /** 停止过期域名 */
    @BeanAttrInfo(value = "mobile:1,8;mail:3,1,8", orderBy = 3052, systemType = SystemType.EISS, cnName = "停止过期域名3052")
    public static final long EISS_NEW_STOP_EXPIRD_DOMAIN = 3052;

    /** 云主机白名单管理域名停止(直接客户和代理用户：主站用户) */
    @BeanAttrInfo(value = "mobile:1,37;mail:1,3,36,37,44", orderBy = 3053, systemType = SystemType.EISS, cnName = "云主机白名单管理域名停止(直接客户和代理用户：主站用户)")
    public static final long EISS_NEW_CHOST_DOMAIN_STOP_MEMBER = 3053;

    /** 云主机白名单管理域名停止(代理下的客户:非主站用户) */
    @BeanAttrInfo(value = "mobile:1,37;mail:1,3,36,37,44", orderBy = 3054, systemType = SystemType.EISS, cnName = "云主机白名单管理域名停止(代理下的客户:非主站用户)")
    public static final long EISS_NEW_CHOST_DOMAIN_STOP_NOT_MEMBER = 3054;

    /** 管理员停止云主机(代理下的客户:非主站用户) */
    @BeanAttrInfo(value = "mobile:6,36;mail:3,6,36", orderBy = 3061, systemType = SystemType.EISS, cnName = "管理员停止云主机(代理下的客户:非主站用户)")
    public static final long EISS_NEW_ADMIN_STOP_CHOST_NOT_MEMBER = 3061;

    /** 管理员停止云主机(直接客户和代理用户：主站用户) */
    @BeanAttrInfo(value = "mobile:6,36;mail:3,6,36", orderBy = 3060, systemType = SystemType.EISS, cnName = "管理员停止云主机(直接客户和代理用户：主站用户)")
    public static final long EISS_NEW_ADMIN_STOP_CHOST_MEMBER = 3060;

    /** 管理员停止云主机-被攻击(代理下的客户:非主站用户) */
    @BeanAttrInfo(value = "mobile:6;mail:3,6", orderBy = 3071, systemType = SystemType.EISS, cnName = "管理员停止云主机-被攻击(代理下的客户:非主站用户)")
    public static final long EISS_NEW_ADMIN_STOP_CHOST_FOR_ATTACK_NOT_MEMBER = 3071;

    /** 管理员停止云主机-被攻击(直接客户和代理用户：主站用户) */
    @BeanAttrInfo(value = "mobile:6;mail:3,6", orderBy = 3070, systemType = SystemType.EISS, cnName = "管理员停止云主机-被攻击(直接客户和代理用户：主站用户)")
    public static final long EISS_NEW_ADMIN_STOP_CHOST_FOR_ATTACK_MEMBER = 3070;

    /** 管理员停止云主机-非法信息 (代理下的客户:非主站用户) */
    @BeanAttrInfo(value = "mobile:6,36,37;mail:3,6,36,37", orderBy = 3081, systemType = SystemType.EISS, cnName = "管理员停止云主机-非法信息(代理下的客户:非主站用户)")
    public static final long EISS_NEW_ADMIN_STOP_CHOST_FOR_ILLEGAL_NOT_MEMBER = 3081;

    /** 管理员停止云主机-非法信息(直接客户和代理用户：主站用户) */
    @BeanAttrInfo(value = "mobile:6,36,37;mail:3,6,36,37", orderBy = 3080, systemType = SystemType.EISS, cnName = "管理员停止云主机-非法信息(直接客户和代理用户：主站用户)")
    public static final long EISS_NEW_ADMIN_STOP_CHOST_FOR_ILLEGAL_MEMBER = 3080;

    /** 管理员停止云主机-超速(代理下的客户:非主站用户) */
    @BeanAttrInfo(value = "mobile:6,36;mail:3,6,36", orderBy = 3091, systemType = SystemType.EISS, cnName = "管理员停止云主机-超速(代理下的客户:非主站用户)")
    public static final long EISS_NEW_ADMIN_STOP_CHOST_FOR_BANDWIDTH_TOO_LARGE_NOT_MEMBER = 3091;

    /** 管理员停止云主机-超速(直接客户和代理用户：主站用户) */
    @BeanAttrInfo(value = "mobile:6,36;mail:3,6,36", orderBy = 3090, systemType = SystemType.EISS, cnName = "管理员停止云主机-超速(直接客户和代理用户：主站用户)")
    public static final long EISS_NEW_ADMIN_STOP_CHOST_FOR_BANDWIDTH_TOO_LARGE_MEMBER = 3090;

    /** 管理员停止云主机-未备案(代理下的客户:非主站用户) */
    @BeanAttrInfo(value = "mobile:6;mail:3,6", orderBy = 3101, systemType = SystemType.EISS, cnName = "管理员停止云主机-未备案(代理下的客户:非主站用户)")
    public static final long EISS_NEW_ADMIN_STOP_CHOST_FOR_NO_RECORD_NOT_MEMBER = 3101;

    /** 管理员停止云主机-未备案(直接客户和代理用户：主站用户) */
    @BeanAttrInfo(value = "mobile:6;mail:3,6", orderBy = 3100, systemType = SystemType.EISS, cnName = "管理员停止云主机-未备案(直接客户和代理用户：主站用户)")
    public static final long EISS_NEW_ADMIN_STOP_CHOST_FOR_NO_RECORD_MEMBER = 3100;

    /** 系统停止云主机-超过系统流量限制(代理下的客户:非主站用户) */
    @BeanAttrInfo(value = "mobile:6;mail:3,6", orderBy = 3111, systemType = SystemType.EISS, cnName = "系统停止云主机-超过系统流量限制(代理下的客户:非主站用户)")
    public static final long EISS_NEW_SYSTEM_STOP_CHOST_FOR_MORE_THAN_FLOW_LIMIT_NOT_MEMBER = 3111;

    /** 系统停止云主机-超过系统流量限制(直接客户和代理用户：主站用户) */
    @BeanAttrInfo(value = "mobile:6;mail:3,6", orderBy = 3110, systemType = SystemType.EISS, cnName = "系统停止云主机-超过系统流量限制(直接客户和代理用户：主站用户)")
    public static final long EISS_NEW_SYSTEM_STOP_CHOST_FOR_MORE_THAN_FLOW_LIMIT_MEMBER = 3110;

    /** 关闭云主机(代理下的客户:非主站用户) */
    @BeanAttrInfo(value = "mobile:6;mail:3,6", orderBy = 3121, systemType = SystemType.EISS, cnName = "关闭云主机(代理下的客户:非主站用户)")
    public static final long EISS_NEW_POWEROFF_CHOST_NOT_MEMBER = 3121;

    /** 关闭云主机(直接客户和代理用户：主站用户) */
    @BeanAttrInfo(value = "mobile:6;mail:3,6", orderBy = 3120, systemType = SystemType.EISS, cnName = "关闭云主机(直接客户和代理用户：主站用户)")
    public static final long EISS_NEW_POWEROFF_CHOST_MEMBER = 3120;

    /** 安装云主机(代理下的客户:非主站用户) */
    @BeanAttrInfo(value = "mobile:6;mail:3,6", orderBy = 3131, systemType = SystemType.EISS, cnName = "安装云主机(代理下的客户:非主站用户)")
    public static final long EISS_NEW_INSTALL_CHOST_NOT_MEMBER = 3131;

    /** 安装云主机(直接客户和代理用户：主站用户) */
    @BeanAttrInfo(value = "mobile:6;mail:3,6", orderBy = 3130, systemType = SystemType.EISS, cnName = "安装云主机(直接客户和代理用户：主站用户)")
    public static final long EISS_NEW_INSTALL_CHOST_MEMBER = 3130;

    /** 迁移云主机(代理下的客户:非主站用户) */
    @BeanAttrInfo(value = "mobile:6;mail:3,6", orderBy = 3141, systemType = SystemType.EISS, cnName = "迁移云主机(代理下的客户:非主站用户)")
    public static final long EISS_NEW_TRANSFER_CHOST_NOT_MEMBER = 3141;

    /** 迁移云主机(直接客户和代理用户：主站用户) */
    @BeanAttrInfo(value = "mobile:6;mail:3,6", orderBy = 3140, systemType = SystemType.EISS, cnName = "迁移云主机(直接客户和代理用户：主站用户)")
    public static final long EISS_NEW_TRANSFER_CHOST_MEMBER = 3140;

    /** 暂停云主机(代理下的客户:非主站用户) */
    @BeanAttrInfo(value = "mobile:6;mail:3,6", orderBy = 3151, systemType = SystemType.EISS, cnName = "暂停云主机(代理下的客户:非主站用户)")
    public static final long EISS_NEW_PAUSE_CHOST_NOT_MEMBER = 3151;

    /** 暂停云主机 (直接客户和代理用户：主站用户) */
    @BeanAttrInfo(value = "mobile:6;mail:3,6", orderBy = 3150, systemType = SystemType.EISS, cnName = "暂停云主机(直接客户和代理用户：主站用户)")
    public static final long EISS_NEW_PAUSE_CHOST_MEMBER = 3150;

    /** 启动云主机(代理下的客户:非主站用户) */
    @BeanAttrInfo(value = "mobile:6;mail:3,6", orderBy = 3161, systemType = SystemType.EISS, cnName = "启动云主机(代理下的客户:非主站用户)")
    public static final long EISS_NEW_START_CHOST_NOT_MEMBER = 3161;

    /** 启动云主机(直接客户和代理用户：主站用户) */
    @BeanAttrInfo(value = "mobile:6;mail:3,6", orderBy = 3160, systemType = SystemType.EISS, cnName = "启动云主机(直接客户和代理用户：主站用户)")
    public static final long EISS_NEW_START_CHOST_MEMBER = 3160;

    /** 购买云主机通知(主站用户) */
    @BeanAttrInfo(value = "mobile:6;mail:3,6,7,18,33,34,35,999", orderBy = 3170, systemType = SystemType.EISS, cnName = "购买云主机通知(主站用户)")
    public static final long EISS_NEW_BUY_CHOST_INFORM_MEMBER = 3170;

    /** 带宽类型由正常带宽改为限制带宽(代理下的客户:非主站用户) */
    @BeanAttrInfo(value = "mobile:6;mail:3,6", orderBy = 3181, systemType = SystemType.EISS, cnName = "带宽类型由正常带宽改为限制带宽(代理下的客户:非主站用户)")
    public static final long EISS_NEW_BANDWIDTH_CHANGE_TO_LIMIT_NOT_MEMBER = 3181;

    /** 带宽类型由正常带宽改为限制带宽(直接客户和代理用户：主站用户) */
    @BeanAttrInfo(value = "mobile:6;mail:3,6", orderBy = 3180, systemType = SystemType.EISS, cnName = "带宽类型由正常带宽改为限制带宽(直接客户和代理用户：主站用户)")
    public static final long EISS_NEW_BANDWIDTH_CHANGE_TO_LIMIT_MEMBER = 3180;

    /** 带宽类型由限制带宽改为正常带宽(代理下的客户:非主站用户) */
    @BeanAttrInfo(value = "mobile:6;mail:3,6", orderBy = 3191, systemType = SystemType.EISS, cnName = "带宽类型由限制带宽改为正常带宽(代理下的客户:非主站用户)")
    public static final long EISS_NEW_BANDWIDTH_CHANGE_TO_NORMAL_NOT_MEMBER = 3191;

    /** 带宽类型由限制带宽改为正常带宽(直接客户和代理用户：主站用户) */
    @BeanAttrInfo(value = "mobile:6;mail:3,6", orderBy = 3190, systemType = SystemType.EISS, cnName = "带宽类型由限制带宽改为正常带宽(直接客户和代理用户：主站用户)")
    public static final long EISS_NEW_BANDWIDTH_CHANGE_TO_NORMAL_MEMBER = 3190;

    /** 云主机网卡状态(直接客户和代理用户：主站用户) */
    @BeanAttrInfo(value = "mobile:6,36;mail:3,6,36", orderBy = 3192, systemType = SystemType.EISS, cnName = "云主机网卡状态(直接客户和代理用户：主站用户)")
    public static final long EISS_NEW_ADMIN_CHOST_DISABLE_NIC_MEMBER = 3192;

    /** 云主机网卡状态(代理下的客户：非主站用户) */
    @BeanAttrInfo(value = "mobile:6,36;mail:3,6,36", orderBy = 3193, systemType = SystemType.EISS, cnName = "云主机网卡状态(代理下的客户：非主站用户)")
    public static final long EISS_NEW_ADMIN_CHOST_DISABLE_NIC_NOT_MEMBER = 3193;

    /*
     * --------------------------------------------------------------------------
     * ------------------------------------------------- 各产品到期前后续费通知模板
     */
    // 虚拟主机
    /** 虚拟主机到期前续费通知(直接客户和代理用户：主站用户) */
    @BeanAttrInfo(value = "mobile:2,8;mail:2,3,8", orderBy = 4010, systemType = SystemType.EISS, cnName = "虚拟主机到期前续费通知(直接客户和代理用户：主站用户)")
    public static final long EISS_NEW_PRODUCT_VHOST_BEFORE_EXPIRED_MEMBER = 4010;

    /** 虚拟主机到期前续费通知(代理下的客户：非主站用户) */
    @BeanAttrInfo(value = "mobile:2,8;mail:2,3,8", orderBy = 4011, systemType = SystemType.EISS, cnName = "虚拟主机到期前续费通知(代理下的客户：非主站用户)")
    public static final long EISS_NEW_PRODUCT_VHOST_BEFORE_EXPIRED_NOT_MEMBER = 4011;

    /** 虚拟主机到期后续费通知(直接客户和代理用户：主站用户) */
    @BeanAttrInfo(value = "mobile:2,8,9;mail:2,3,8,9", orderBy = 4012, systemType = SystemType.EISS, cnName = "虚拟主机到期后续费通知(直接客户和代理用户：主站用户)")
    public static final long EISS_NEW_PRODUCT_VHOST_AFTER_EXPIRED_MEMBER = 4012;

    /** 虚拟主机到期后续费通知(代理下的客户：非主站用户) */
    @BeanAttrInfo(value = "mobile:2,8,9;mail:2,3,8,9", orderBy = 4013, systemType = SystemType.EISS, cnName = "虚拟主机到期后续费通知(代理下的客户：非主站用户)")
    public static final long EISS_NEW_PRODUCT_VHOST_AFTER_EXPIRED_NOT_MEMBER = 4013;

    /** 虚拟主机-过期关闭通知 */
    @BeanAttrInfo(value = "mobile:18,36;mail:3,18,36", systemType = SystemType.EISS, cnName = "虚拟主机-过期关闭通知")
    public static final long EISS_NEW_PRODUCT_VHOST_EXPIRED_STOP = 4014;

    /** 虚拟主机配额-超大小的通知 */
    @BeanAttrInfo(value = "mobile:18;mail:3,18", systemType = SystemType.EISS, cnName = "虚拟主机配额-超大小的通知")
    public static final long EISS_NEW_PRODUCT_VHOST_OVER_DESIGNATION_SIZE = 4015;

    // 企业邮局
    /** 企业邮局到期前续费通知(直接客户和代理用户：主站用户) */
    @BeanAttrInfo(value = "mobile:1,8;mail:1,3,8", orderBy = 4020, systemType = SystemType.EISS, cnName = "企业邮局到期前续费通知(直接客户和代理用户：主站用户)")
    public static final long EISS_NEW_PRODUCT_MAIL_BEFORE_EXPIRED_MEMBER = 4020;

    /** 企业邮局到期前续费通知(代理下的客户：非主站用户) */
    @BeanAttrInfo(value = "mobile:1,8;mail:1,3,8", orderBy = 4021, systemType = SystemType.EISS, cnName = "企业邮局到期前续费通知(代理下的客户：非主站用户)")
    public static final long EISS_NEW_PRODUCT_MAIL_BEFORE_EXPIRED_NOT_MEMBER = 4021;

    /** 企业邮局到期后续费通知(直接客户和代理用户：主站用户) */
    @BeanAttrInfo(value = "mobile:1,8,9;mail:1,3,8,9", orderBy = 4022, systemType = SystemType.EISS, cnName = "企业邮局到期后续费通知(直接客户和代理用户：主站用户)")
    public static final long EISS_NEW_PRODUCT_MAIL_AFTER_EXPIRED_MEMBER = 4022;

    /** 企业邮局到期后续费通知(代理下的客户：非主站用户) */
    @BeanAttrInfo(value = "mobile:1,8,9;mail:1,3,8,9", orderBy = 4023, systemType = SystemType.EISS, cnName = "企业邮局到期后续费通知(代理下的客户：非主站用户)")
    public static final long EISS_NEW_PRODUCT_MAIL_AFTER_EXPIRED_NOT_MEMBER = 4023;

    /** 邮局停止通知 */
    @BeanAttrInfo(value = "mobile:1;mail:1,3,37", systemType = SystemType.EISS, cnName = "邮局停止通知")
    public static final long EISS_NEW_PRODUCT_MAIL_STOP = 4024;

    /** 邮局-过期关闭通知 */
    @BeanAttrInfo(value = "mobile:18,36;mail:3,18,36", systemType = SystemType.EISS, cnName = "邮局-过期关闭通知")
    public static final long EISS_NEW_PRODUCT_MAIL_EXPIRED_STOP = 4025;

    // 数据库
    /** 数据库到期前续费通知(直接客户和代理用户：主站用户) */
    @BeanAttrInfo(value = "mobile:23,8;mail:23,3,8", orderBy = 4030, systemType = SystemType.EISS, cnName = "数据库到期前续费通知(直接客户和代理用户：主站用户)")
    public static final long EISS_NEW_PRODUCT_DATABASE_BEFORE_EXPIRED_MEMBER = 4030;

    /** 数据库到期前续费通知(代理下的客户：非主站用户) */
    @BeanAttrInfo(value = "mobile:23,8;mail:23,3,8", orderBy = 4031, systemType = SystemType.EISS, cnName = "数据库到期前续费通知(代理下的客户：非主站用户)")
    public static final long EISS_NEW_PRODUCT_DATABASE_BEFORE_EXPIRED_NOT_MEMBER = 4031;

    /** 数据库到期后续费通知(直接客户和代理用户：主站用户) */
    @BeanAttrInfo(value = "mobile:23,8,9;mail:23,3,8,9", orderBy = 4032, systemType = SystemType.EISS, cnName = "数据库到期后续费通知(直接客户和代理用户：主站用户)")
    public static final long EISS_NEW_PRODUCT_DATABASE_AFTER_EXPIRED_MEMBER = 4032;

    /** 数据库到期后续费通知(代理下的客户：非主站用户) */
    @BeanAttrInfo(value = "mobile:23,8,9;mail:23,3,8,9", orderBy = 4033, systemType = SystemType.EISS, cnName = "数据库到期后续费通知(代理下的客户：非主站用户)")
    public static final long EISS_NEW_PRODUCT_DATABASE_AFTER_EXPIRED_NOT_MEMBER = 4033;

    /** 数据库-过期关闭通知 */
    @BeanAttrInfo(value = "mobile:18,36;mail:3,18,36", systemType = SystemType.EISS, cnName = "数据库-过期关闭通知")
    public static final long EISS_NEW_PRODUCT_DATABASE_EXPIRED_STOP = 4034;

    /** 数据库-超大小的通知 */
    @BeanAttrInfo(value = "mobile:18;mail:3,18", systemType = SystemType.EISS, cnName = "数据库-超大小的通知")
    public static final long EISS_NEW_PRODUCT_DATABASE_OVER_DESIGNATION_SIZE = 4035;

    // VPS
    /** VPS到期前续费通知(直接客户和代理用户：主站用户) */
    @BeanAttrInfo(value = "mobile:6,8;mail:6,3,8", orderBy = 4040, systemType = SystemType.EISS, cnName = "VPS到期前续费通知(直接客户和代理用户：主站用户)")
    public static final long EISS_NEW_PRODUCT_VPS_BEFORE_EXPIRED_MEMBER = 4040;

    /** VPS到期前续费通知(代理下的客户：非主站用户) */
    @BeanAttrInfo(value = "mobile:6,8;mail:6,3,8", orderBy = 4041, systemType = SystemType.EISS, cnName = "VPS到期前续费通知(代理下的客户：非主站用户)")
    public static final long EISS_NEW_PRODUCT_VPS_BEFORE_EXPIRED_NOT_MEMBER = 4041;

    /** VPS到期后续费通知(直接客户和代理用户：主站用户) */
    @BeanAttrInfo(value = "mobile:6,8,9;mail:6,3,8,9", orderBy = 4042, systemType = SystemType.EISS, cnName = "VPS到期后续费通知(直接客户和代理用户：主站用户)")
    public static final long EISS_NEW_PRODUCT_VPS_AFTER_EXPIRED_MEMBER = 4042;

    /** VPS到期后续费通知(代理下的客户：非主站用户) */
    @BeanAttrInfo(value = "mobile:6,8,9;mail:6,3,8,9", orderBy = 4043, systemType = SystemType.EISS, cnName = "VPS到期后续费通知(代理下的客户：非主站用户)")
    public static final long EISS_NEW_PRODUCT_VPS_AFTER_EXPIRED_NOT_MEMBER = 4043;

    // 域名
    /** 域名到期前续费通知(直接客户和代理用户：主站用户) */
    @BeanAttrInfo(value = "mobile:1,8;mail:1,3,8", orderBy = 4050, systemType = SystemType.EISS, cnName = "域名到期前续费通知(直接客户和代理用户：主站用户)")
    public static final long EISS_NEW_PRODUCT_DOMAIN_BEFORE_EXPIRED_MEMBER = 4050;

    /** 域名到期前续费通知(代理下的客户：非主站用户) */
    @BeanAttrInfo(value = "mobile:2,8;mail:2,3,8", orderBy = 4051, systemType = SystemType.EISS, cnName = "域名到期前续费通知(代理下的客户：非主站用户)")
    public static final long EISS_NEW_PRODUCT_DOMAIN_BEFORE_EXPIRED_NOT_MEMBER = 4051;

    /** 域名到期后续费通知(直接客户和代理用户：主站用户) */
    @BeanAttrInfo(value = "mobile:1,8,9;mail:1,3,8,9", orderBy = 4052, systemType = SystemType.EISS, cnName = "域名到期后续费通知(直接客户和代理用户：主站用户)")
    public static final long EISS_NEW_PRODUCT_DOMAIN_AFTER_EXPIRED_MEMBER = 4052;

    /** 域名到期后续费通知(代理下的客户：非主站用户) */
    @BeanAttrInfo(value = "mobile:1,8,9;mail:1,3,8,9", orderBy = 4053, systemType = SystemType.EISS, cnName = "域名到期后续费通知(代理下的客户：非主站用户)")
    public static final long EISS_NEW_PRODUCT_DOMAIN_AFTER_EXPIRED_NOT_MEMBER = 4053;

    // 　IDC
    /** IDC到期前续费通知(直接客户和代理用户：主站用户) */
    @BeanAttrInfo(value = "mobile:6,8;mail:6,3,8", orderBy = 4060, systemType = SystemType.EISS, cnName = "IDC到期前续费通知(直接客户和代理用户：主站用户)")
    public static final long EISS_NEW_PRODUCT_IDC_BEFORE_EXPIRED_MEMBER = 4060;

    /** IDC到期前续费通知(代理下的客户：非主站用户) */
    @BeanAttrInfo(value = "mobile:6,8;mail:6,3,8", orderBy = 4061, systemType = SystemType.EISS, cnName = "IDC到期前续费通知(代理下的客户：非主站用户)")
    public static final long EISS_NEW_PRODUCT_IDC_BEFORE_EXPIRED_NOT_MEMBER = 4061;

    /** IDC到期后续费通知(直接客户和代理用户：主站用户) */
    @BeanAttrInfo(value = "mobile:6,8,9;mail:6,3,8,9", orderBy = 4062, systemType = SystemType.EISS, cnName = "IDC到期后续费通知(直接客户和代理用户：主站用户)")
    public static final long EISS_NEW_PRODUCT_IDC_AFTER_EXPIRED_MEMBER = 4062;

    /** IDC到期后续费通知(代理下的客户：非主站用户) */
    @BeanAttrInfo(value = "mobile:6,8,9;mail:6,3,8,9", orderBy = 4063, systemType = SystemType.EISS, cnName = "IDC到期后续费通知(代理下的客户：非主站用户)")
    public static final long EISS_NEW_PRODUCT_IDC_AFTER_EXPIRED_NOT_MEMBER = 4063;

    // 　云主机
    /** 云主机到期前续费通知(直接客户和代理用户：主站用户) */
    @BeanAttrInfo(value = "mobile:6,8;mail:6,3,8", orderBy = 4070, systemType = SystemType.EISS, cnName = "云主机到期前续费通知(直接客户和代理用户：主站用户)")
    public static final long EISS_NEW_PRODUCT_CHOST_BEFORE_EXPIRED_MEMBER = 4070;

    /** 云主机到期前续费通知(代理下的客户：非主站用户) */
    @BeanAttrInfo(value = "mobile:6,8;mail:6,3,8", orderBy = 4071, systemType = SystemType.EISS, cnName = "云主机到期前续费通知(代理下的客户：非主站用户)")
    public static final long EISS_NEW_PRODUCT_CHOST_BEFORE_EXPIRED_NOT_MEMBER = 4071;

    /** 云主机到期后续费通知(直接客户和代理用户：主站用户) */
    @BeanAttrInfo(value = "mobile:6,8,9;mail:6,3,8,9", orderBy = 4072, systemType = SystemType.EISS, cnName = "云主机到期后续费通知(直接客户和代理用户：主站用户)")
    public static final long EISS_NEW_PRODUCT_CHOST_AFTER_EXPIRED_MEMBER = 4072;

    /** 云主机到期后续费通知(代理下的客户：非主站用户) */
    @BeanAttrInfo(value = "mobile:6,8,9;mail:6,3,8,9", orderBy = 4073, systemType = SystemType.EISS, cnName = "云主机到期后续费通知(代理下的客户：非主站用户)")
    public static final long EISS_NEW_PRODUCT_CHOST_AFTER_EXPIRED_NOT_MEMBER = 4073;

    /** 开通提示 */
    @BeanAttrInfo(value = "mail:81,18,7,8,82,83,84,17", orderBy = 4080, systemType = SystemType.EISS, cnName = "域名开通提示", enName = "开通提示")
    public static final long EISS_PRODUCT_INFO_TIP_OPEN_TIP = 4080L;

    /** 续费提示 */
    @BeanAttrInfo(value = "mail:81,18,7,8,82,83,84,17", orderBy = 4081, systemType = SystemType.EISS, cnName = "域名续费提示", enName = "续费提示")
    public static final long EISS_PRODUCT_INFO_TIP_RENEW_TIP = 4081L;

    /** 升级提示 */
    @BeanAttrInfo(value = "mail:81,18,7,8,82,83,84,17", orderBy = 4082, systemType = SystemType.EISS, cnName = "域名升级提示", enName = "升级提示")
    public static final long EISS_PRODUCT_INFO_TIP_UPGRADE_TIP = 4082L;

    /** 广告 */
    @BeanAttrInfo(value = "mail:81,18,7,8,82,83,84,17", orderBy = 4083, systemType = SystemType.EISS, cnName = "域名广告", enName = "广告")
    public static final long EISS_PRODUCT_INFO_TIP_ADVERTISEMENT_TIP = 4083L;

    /** 活动 */
    @BeanAttrInfo(value = "mail:81,18,7,8,82,83,84,17", orderBy = 4084, systemType = SystemType.EISS, cnName = "域名活动", enName = "活动")
    public static final long EISS_PRODUCT_INFO_TIP_ACTIVITY_TIP = 4084L;

    /** 故障通知 */
    @BeanAttrInfo(value = "mail:81,18,7,8,82,83,84,17", orderBy = 4085, systemType = SystemType.EISS, cnName = "域名故障通知", enName = "故障通知")
    public static final long EISS_PRODUCT_INFO_TIP_FAULT_TIP = 4085L;

    /** 虚机开通提示 */
    @BeanAttrInfo(value = "mail:81,18,7,8,82,83,84,17", orderBy = 4180, systemType = SystemType.EISS, cnName = "虚拟主机开通提示", enName = "开通提示")
    public static final long EISS_PRODUCT_INFO_TIP_VHOST_OPEN_TIP = 4180L;

    /** 虚机续费提示 */
    @BeanAttrInfo(value = "mail:81,18,7,8,82,83,84,17", orderBy = 4181, systemType = SystemType.EISS, cnName = "虚拟主机续费提示", enName = "续费提示")
    public static final long EISS_PRODUCT_INFO_TIP_VHOST_RENEW_TIP = 4181L;

    /** 虚机升级提示 */
    @BeanAttrInfo(value = "mail:81,18,7,8,82,83,84,17", orderBy = 4182, systemType = SystemType.EISS, cnName = "虚拟主机升级提示", enName = "升级提示")
    public static final long EISS_PRODUCT_INFO_TIP_VHOST_UPGRADE_TIP = 4182L;

    /** 虚机广告 */
    @BeanAttrInfo(value = "mail:81,18,7,8,82,83,84,17", orderBy = 4183, systemType = SystemType.EISS, cnName = "虚拟主机广告", enName = "广告")
    public static final long EISS_PRODUCT_INFO_TIP_VHOST_ADVERTISEMENT_TIP = 4183L;

    /** 虚机活动 */
    @BeanAttrInfo(value = "mail:81,18,7,8,82,83,84,17", orderBy = 4184, systemType = SystemType.EISS, cnName = "虚拟主机活动", enName = "活动")
    public static final long EISS_PRODUCT_INFO_TIP_VHOST_ACTIVITY_TIP = 4184L;

    /** 虚机故障通知 */
    @BeanAttrInfo(value = "mail:81,18,7,8,82,83,84,17", orderBy = 4185, systemType = SystemType.EISS, cnName = "虚拟主机故障通知", enName = "故障通知")
    public static final long EISS_PRODUCT_INFO_TIP_VHOST_FAULT_TIP = 4185L;

    /** 邮局开通提示 */
    @BeanAttrInfo(value = "mail:81,18,7,8,82,83,84,17", orderBy = 4280, systemType = SystemType.EISS, cnName = "邮局开通提示", enName = "开通提示")
    public static final long EISS_PRODUCT_INFO_TIP_MAIL_OPEN_TIP = 4280L;

    /** 邮局续费提示 */
    @BeanAttrInfo(value = "mail:81,18,7,8,82,83,84,17", orderBy = 4281, systemType = SystemType.EISS, cnName = "邮局续费提示", enName = "续费提示")
    public static final long EISS_PRODUCT_INFO_TIP_MAIL_RENEW_TIP = 4281L;

    /** 邮局升级提示 */
    @BeanAttrInfo(value = "mail:81,18,7,8,82,83,84,17", orderBy = 4282, systemType = SystemType.EISS, cnName = "邮局升级提示", enName = "升级提示")
    public static final long EISS_PRODUCT_INFO_TIP_MAIL_UPGRADE_TIP = 4282L;

    /** 邮局广告 */
    @BeanAttrInfo(value = "mail:81,18,7,8,82,83,84,17", orderBy = 4283, systemType = SystemType.EISS, cnName = "邮局广告", enName = "广告")
    public static final long EISS_PRODUCT_INFO_TIP_MAIL_ADVERTISEMENT_TIP = 4283L;

    /** 邮局活动 */
    @BeanAttrInfo(value = "mail:81,18,7,8,82,83,84,17", orderBy = 4284, systemType = SystemType.EISS, cnName = "邮局活动", enName = "活动")
    public static final long EISS_PRODUCT_INFO_TIP_MAIL_ACTIVITY_TIP = 4284L;

    /** 邮局故障通知 */
    @BeanAttrInfo(value = "mail:81,18,7,8,82,83,84,17", orderBy = 4285, systemType = SystemType.EISS, cnName = "邮局故障通知", enName = "故障通知")
    public static final long EISS_PRODUCT_INFO_TIP_MAIL_FAULT_TIP = 4285L;

    /** 云主机开通提示 */
    @BeanAttrInfo(value = "mail:81,18,7,8,82,83,84,17", orderBy = 4380, systemType = SystemType.EISS, cnName = "云主机开通提示", enName = "开通提示")
    public static final long EISS_PRODUCT_INFO_TIP_CHOST_OPEN_TIP = 4380L;

    /** 云主机续费提示 */
    @BeanAttrInfo(value = "mail:81,18,7,8,82,83,84,17", orderBy = 4381, systemType = SystemType.EISS, cnName = "云主机续费提示", enName = "续费提示")
    public static final long EISS_PRODUCT_INFO_TIP_CHOST_RENEW_TIP = 4381L;

    /** 云主机升级提示 */
    @BeanAttrInfo(value = "mail:81,18,7,8,82,83,84,17", orderBy = 4382, systemType = SystemType.EISS, cnName = "云主机升级提示", enName = "升级提示")
    public static final long EISS_PRODUCT_INFO_TIP_CHOST_UPGRADE_TIP = 4382L;

    /** 云主机广告 */
    @BeanAttrInfo(value = "mail:81,18,7,8,82,83,84,17", orderBy = 4383, systemType = SystemType.EISS, cnName = "云主机广告", enName = "广告")
    public static final long EISS_PRODUCT_INFO_TIP_CHOST_ADVERTISEMENT_TIP = 4383L;

    /** 云主机活动 */
    @BeanAttrInfo(value = "mail:81,18,7,8,82,83,84,17", orderBy = 4384, systemType = SystemType.EISS, cnName = "云主机活动", enName = "活动")
    public static final long EISS_PRODUCT_INFO_TIP_CHOST_ACTIVITY_TIP = 4384L;

    /** 云主机故障通知 */
    @BeanAttrInfo(value = "mail:81,18,7,8,82,83,84,17", orderBy = 4385, systemType = SystemType.EISS, cnName = "云主机故障通知", enName = "故障通知")
    public static final long EISS_PRODUCT_INFO_TIP_CHOST_FAULT_TIP = 4385L;

    /** IDC到期前15天续费通知 */
    @BeanAttrInfo(value = "mail:3,8,17,33", systemType = SystemType.EISS, cnName = "IDC到期前15天续费通知")
    public static final long EISS_NEW_PRODUCT_IDC_BEFORE_EXPIRED_15_NOT_MEMBER = 4401;

    /** IDC到期前7天续费通知 */
    @BeanAttrInfo(value = "mail:3,8,17,33", systemType = SystemType.EISS, cnName = "IDC到期前7天续费通知")
    public static final long EISS_NEW_PRODUCT_IDC_BEFORE_EXPIRED_7_NOT_MEMBER = 4402;

    /** IDC到期前3天续费通知 */
    @BeanAttrInfo(value = "mail:3,8,17,33", systemType = SystemType.EISS, cnName = "IDC到期前3天续费通知")
    public static final long EISS_NEW_PRODUCT_IDC_BEFORE_EXPIRED_3_NOT_MEMBER = 4403;

    /** IDC到期通知 */
    @BeanAttrInfo(value = "mail:3,8,9,17,33", systemType = SystemType.EISS, cnName = "IDC到期通知")
    public static final long EISS_NEW_PRODUCT_IDC_BEFORE_EXPIRED_0_NOT_MEMBER = 4404;

    /** IDC线下合同到期前30天续费通知 */
    @BeanAttrInfo(value = "mail:51,8", systemType = SystemType.EISS, cnName = "IDC线下合同到期前30天续费通知")
    public static final long EISS_NEW_PRODUCT_IDC_CONTRACT_BEFORE_EXPIRED_30_NOT_MEMBER = 4405;

    /** IDC线下合同到期前7天续费通知 */
    @BeanAttrInfo(value = "mail:51,8", systemType = SystemType.EISS, cnName = "IDC线下合同到期前7天续费通知")
    public static final long EISS_NEW_PRODUCT_IDC_CONTRACT_BEFORE_EXPIRED_7_NOT_MEMBER = 4406;

    /** IDC线下合同到期前3天续费通知 */
    @BeanAttrInfo(value = "mail:51,8", systemType = SystemType.EISS, cnName = "IDC线下合同到期前3天续费通知")
    public static final long EISS_NEW_PRODUCT_IDC_CONTRACT_BEFORE_EXPIRED_3_NOT_MEMBER = 4407;

    /** IDC线下合同到期通知 */
    @BeanAttrInfo(value = "mail:51,8", systemType = SystemType.EISS, cnName = "IDC线下合同到期通知")
    public static final long EISS_NEW_PRODUCT_IDC_CONTRACT_BEFORE_EXPIRED_0_NOT_MEMBER = 4408;

    /** IDC订单审核完毕通知 */
    @BeanAttrInfo(value = "mail:3,119,120", systemType = SystemType.EISS, cnName = "IDC订单审核完毕通知")
    public static final long EISS_NEW_PRODUCT_IDC_ORDER_FINISH_NOTIFY = 4409;

    // ================================================微信公众平台=========================================================
    /** 微信公众平台发送手机验证码 */
    @BeanAttrInfo(value = "mobile:57,25", orderBy = 5000, systemType = SystemType.WEI_XIN, cnName = "微信公众平台发送手机验证码")
    public static final long WEIXIN_SEND_MOBILE_VERIFYCODE = 5000;

    /** 停止过期的公众账号 (直接客户和代理用户：主站用户) */
    @BeanAttrInfo(value = "mobile:58,8;mail:3,58,8", orderBy = 5001, systemType = SystemType.WEI_XIN, cnName = "停止过期的公众账号(直接客户和代理用户：主站用户)")
    public static final long WEIXIN_STOP_EXPIRD_MEMBER = 5001;

    /** 停止过期的公众账号 (代理下的客户:非主站用户) */
    @BeanAttrInfo(value = "mobile:58,8;mail:3,58,8", orderBy = 5002, systemType = SystemType.WEI_XIN, cnName = "停止过期的公众账号(代理下的客户：非主站用户)")
    public static final long WEIXIN_STOP_EXPIRD_NOT_MEMBER = 5002;

    /** 公众账号到期前续费通知(直接客户和代理用户：主站用户) */
    @BeanAttrInfo(value = "mobile:58,8;mail:3,58,8", orderBy = 5003, systemType = SystemType.WEI_XIN, cnName = "公众账号到期前续费通知(直接客户和代理用户：主站用户)")
    public static final long WEIXIN_STOP_EXPIRD_BEFORE_EXPIRED_MEMBER = 5003;

    /** 公众账号到期前续费通知(代理下的客户：非主站用户) */
    @BeanAttrInfo(value = "mobile:58,8;mail:3,58,8", orderBy = 5004, systemType = SystemType.WEI_XIN, cnName = "公众账号到期前续费通知(代理下的客户)")
    public static final long WEIXIN_STOP_EXPIRD_BEFORE_EXPIRED_NOT_MEMBER = 5004;

    /** 公众账号到期后续费通知(直接客户和代理用户：主站用户) */
    @BeanAttrInfo(value = "mobile:58,8,9;mail:3,58,8,9", orderBy = 5005, systemType = SystemType.WEI_XIN, cnName = "公众账号到期后续费通知(直接客户和代理用户：主站用户)")
    public static final long WEIXIN_STOP_EXPIRD_AFTER_EXPIRED_MEMBER = 5005;

    /** 公众账号到期后续费通知(代理下的客户：非主站用户) */
    @BeanAttrInfo(value = "mobile:58,8,9;mail:3,58,8,9", orderBy = 5006, systemType = SystemType.WEI_XIN, cnName = "公众账号到期后续费通知(代理下的客户：非主站用户)")
    public static final long WEIXIN_STOP_EXPIRD_AFTER_EXPIRED_NOT_MEMBER = 5006;

    // ====================================WEB工具系统===========================================
    /** 管局拨测通知 */
    @BeanAttrInfo(value = "mobile:29;mail:30,31,32", orderBy = 201, systemType = SystemType.EISS_TOOLS, cnName = "管局拨测通知")
    public static final long MII_DIALS = 201;

    /** 管局拨测-不可恢复解绑通知 */
    @BeanAttrInfo(value = "mobile:29;mail:29", orderBy = 202, systemType = SystemType.EISS_TOOLS, cnName = "管局拨测-不可恢复解绑通知")
    public static final long MII_DIALS_DENY_RECOVERY_STOP_BINDINGS = 202;

    /** 管局拨测-需要新增接入通知 */
    @BeanAttrInfo(orderBy = 203, systemType = SystemType.EISS_TOOLS, cnName = "管局拨测-需要新增接入通知")
    public static final long MII_DIALS_NEW_ACCESS = 203;

    /** 管局拨测-取消接入通知 */
    @BeanAttrInfo(value = "mail:1", orderBy = 204, systemType = SystemType.EISS_TOOLS, cnName = "管局拨测-取消接入通知")
    public static final long MII_DIALS_CALCEL_ACCESS = 204;

    /** 管局拨测-备案清理通知 */
    @BeanAttrInfo(value = "mobile:13,52,54,55,1,;mail:13,52,54,55,1", orderBy = 205, systemType = SystemType.EISS_TOOLS, cnName = "管局拨测-备案清理通知")
    public static final long MII_DIALS_RECORD_CLEAN_NOTICE = 205;

    /** 管局拨测-备案清理(附件)通知 */
    @BeanAttrInfo(value = "mobile:52,;mail:52", orderBy = 206, systemType = SystemType.EISS_TOOLS, cnName = "管局拨测-备案清理(附件)通知")
    public static final long MII_DIALS_RECORD_CLEAN_NOTICE_ATTACHMENT = 206;

    // ====================================财务系统===========================================

    // ====================================其他系统===========================================
    /** 快递已投递通知 */
    @BeanAttrInfo(value = "mobile:18,50,51", orderBy = 9900, systemType = SystemType.OTHER, cnName = "快递已投递通知")
    public static final long OTHER_EXPRESS_SENDED_NOTICE = 9900;

    /*
     * ====================================备案系统相关短信邮件模板(10000起)======================
     */
    /** 备案系统-退回接入商到退回主办者发送的短信邮件模板 */
    @BeanAttrInfo(value = "mail:5,52,53", orderBy = 10001, systemType = SystemType.RECORD, cnName = "备案系统-退回接入商到退回主办者发送的短信邮件模板")
    public static final long ERS_RETURN_PROVIDER_TO_SPONSOR = 10001;

    /** 备案系统-网安回执单审核不通过的短信邮件模板 */
    @BeanAttrInfo(value = "mail:1", orderBy = 10002, systemType = SystemType.RECORD, cnName = "备案系统-网安回执单审核不通过的短信邮件模板")
    public static final long ERS_POLICE_RECEIPT_AUDIT_FAIL = 10002L;

    /*
     * ====================================监控系统相关短信邮件模板(20000起)======================
     */
    /** 监控系统-警告 */
    @BeanAttrInfo(value = "mail:1,36,40", orderBy = 20000, systemType = SystemType.MONITOR, cnName = "监控系统-警告")
    public static final long MONITOR_COMMON_WARN_MESSAGE_TYPE = 20000L;

    /** 监控系统-异常 */
    @BeanAttrInfo(value = "mail:1,36,40", orderBy = 20001, systemType = SystemType.MONITOR, cnName = "监控系统-异常")
    public static final long MONITOR_COMMON_EXECPTION_MESSAGE_TYPE = 20001L;


    /** 工单回复邮件通知 */
    @BeanAttrInfo(value = "mail:44", systemType = SystemType.WORK_ORDER, cnName = "工单回复邮件通知")
    public static final long WORK_ORDER_EMAIL_AUTO_REPLY = 30000L;

    /** 推荐码发送邮件 */
    @BeanAttrInfo(value = "mail:65,66,67,68,69,70,71,72,73,74,75", systemType = SystemType.EISS, cnName = "推荐码发送邮件")
    public static final long RECOMMEND_CODE = 33000L;

    /*
     * ====================================管理中心(40000起)======================
     */
    /** 用户修改会员信息中的手机号码 */
    @BeanAttrInfo(value = "mobile:3,36,57", systemType = SystemType.EISS, cnName = "用户修改会员信息中的手机号码")
    public static final long USER_UPDATE_REGISTER_MOBILE = 40000L;

    /** 用户修改会员信息中的电子邮箱 */
    @BeanAttrInfo(value = "mail:3,36,76", systemType = SystemType.EISS, cnName = "用户修改会员信息中的电子邮箱")
    public static final long USER_UPDATE_REGISTER_EMAIL = 40001L;


    /*
     * ====================================世纪利信(50000起)======================
     */
    /** 用户注册成功发送初始密码 */
    @BeanAttrInfo(value = "mobile:3,24", systemType = SystemType.SJLX_CMS, cnName = "用户注册成功发送初始密码")
    public static final long USER_REGISTER_SMS = 50000L;

    /** 用户查看公司全部信息发送验证码 */
    @BeanAttrInfo(value = "mobile:3,25,77", systemType = SystemType.SJLX_CMS, cnName = "用户查看公司全部信息发送验证码")
    public static final long USER_LOOK_COMPANY_SMS = 50001L;

    /** 用户修改公司发送验证码 */
    @BeanAttrInfo(value = "mobile:3,25,77", systemType = SystemType.SJLX_CMS, cnName = "用户修改公司发送验证码")
    public static final long USER_MODIFY_COMPANY_SMS = 50002L;

    /** 用户找回密码发送验证码 */
    @BeanAttrInfo(value = "mail:3,25,;mobile:3,25", systemType = SystemType.SJLX_CMS, cnName = "用户找回密码发送验证码")
    public static final long USER_FORGET_PWD_SMS = 50003L;

    /** 用户修改手机号发送验证码 */
    @BeanAttrInfo(value = "mobile:3,25", systemType = SystemType.SJLX_CMS, cnName = "用户修改手机号发送验证码")
    public static final long USER_MODIFY_MOBILE_SMS = 50004L;

    /** 用户修改邮箱发送验证码 */
    @BeanAttrInfo(value = "mail:3,25,;mobile:3,25", systemType = SystemType.SJLX_CMS, cnName = "用户修改邮箱发送验证码")
    public static final long USER_MODIFY_EMAIL_SMS = 50005L;

    /** 用户修改邮箱发送验证码 */
    @BeanAttrInfo(value = "mail:3,76,;mobile:3,57", systemType = SystemType.SJLX_CMS, cnName = "旧邮箱通知或者旧手机通知")
    public static final long USER_MODIFY_OLD_EMAIL_SMS = 50006L;

    /** 到期前续费通知(直接客户和代理用户：主站用户) */
    @BeanAttrInfo(value = "mobile:18,8;mail:18,3,8", systemType = SystemType.SJLX_CMS, cnName = "到期前续费通知(直接客户和代理用户：主站用户)")
    public static final long PRODUCT_BEFORE_EXPIRED_MEMBER = 50007L;

    /** 用户修改密码成功通知 */
    @BeanAttrInfo(value = "mail:3,25,;mobile:3,25", systemType = SystemType.SJLX_CMS, cnName = "用户修改密码成功通知")
    public static final long USER_MODIFY_PWD = 50008L;

    /** 会员注册验证码 */
    @BeanAttrInfo(value = "mail:3,25,;mobile:3,25", systemType = SystemType.LOAN, cnName = "会员注册验证码")
    public static final long USER_REGISTER_SEND_SMS = 50009L;


    /** 核名后发送至管理员 */
    @BeanAttrInfo(value = "mail:3,8,77,100,101,102,103,104,105,106,107,108,109,110,111,112,113,114,115,116,117,118,;mobile:3,8,77,100,101,102,103,104,105,106,107,108,109,110,111,112,113,114,115,116,117,118", systemType = SystemType.SJLX_CMS, cnName = "核名后发送至管理员")
    public static final long SENDTO_ADMIN = 50100L;


    /** 财务生日祝福 */
    @BeanAttrInfo(value = "mail:3,8,77,100,101,102,103,104,105,106,107,108,109,110,111,112,113,114,115,116,117,118,;mobile:3,8,77,100,101,102,103,104,105,106,107,108,109,110,111,112,113,114,115,116,117,118", systemType = SystemType.SJLX_CMS, cnName = "财务生日祝福")
    public static final long BIRTH_DAY_WISH_FINCAL = 50101L;

    /** 在工商专员分配时 */
    @BeanAttrInfo(value = "mail:3,8,77,100,101,102,103,104,105,106,107,108,109,110,111,112,113,114,115,116,117,118,;mobile:3,8,77,100,101,102,103,104,105,106,107,108,109,110,111,112,113,114,115,116,117,118", systemType = SystemType.SJLX_CMS, cnName = "在工商专员分配时")
    public static final long ICBC_ASSGIN = 50102L;

    /** 营业执照办理完毕 */
    @BeanAttrInfo(value = "mail:3,8,77,100,101,102,103,104,105,106,107,108,109,110,111,112,113,114,115,116,117,118,;mobile:3,8,77,100,101,102,103,104,105,106,107,108,109,110,111,112,113,114,115,116,117,118", systemType = SystemType.SJLX_CMS, cnName = "营业执照办理完毕")
    public static final long BUSINESS_LICENCE_FINISH = 50103L;

    /** 银行开户完毕 */
    @BeanAttrInfo(value = "mail:3,8,77,100,101,102,103,104,105,106,107,108,109,110,111,112,113,114,115,116,117,118,;mobile:3,8,77,100,101,102,103,104,105,106,107,108,109,110,111,112,113,114,115,116,117,118", systemType = SystemType.SJLX_CMS, cnName = "银行开户完毕")
    public static final long BANK_ACCOUNT_FINISH = 50104L;


    /** 催费 */
    @BeanAttrInfo(value = "mail:3,8,77,100,101,102,103,104,105,106,107,108,109,110,111,112,113,114,115,116,117,118,;mobile:3,8,77,100,101,102,103,104,105,106,107,108,109,110,111,112,113,114,115,116,117,118", systemType = SystemType.SJLX_CMS, cnName = "催费")
    public static final long REMINDER_FEE = 50105L;

    /** 纳税申报提醒 */
    @BeanAttrInfo(value = "mail:3,8,77,100,101,102,103,104,105,106,107,108,109,110,111,112,113,114,115,116,117,118,;mobile:3,8,77,100,101,102,103,104,105,106,107,108,109,110,111,112,113,114,115,116,117,118", systemType = SystemType.SJLX_CMS, cnName = "纳税申报提醒")
    public static final long REPORT_REMINDER = 50106L;

    /** 工商分配时经办人 */
    @BeanAttrInfo(value = "mail:3,8,77,100,101,102,103,104,105,106,107,108,109,110,111,112,113,114,115,116,117,118,;mobile:3,8,77,100,101,102,103,104,105,106,107,108,109,110,111,112,113,114,115,116,117,118", systemType = SystemType.SJLX_CMS, cnName = "工商分配时经办人")
    public static final long OP_ICBC_ASSGIN = 50107L;

    /** 核名后经办人自然人 */
    @BeanAttrInfo(value = "mail:3,8,77,100,101,102,103,104,105,106,107,108,109,110,111,112,113,114,115,116,117,118,;mobile:3,8,77,100,101,102,103,104,105,106,107,108,109,110,111,112,113,114,115,116,117,118", systemType = SystemType.SJLX_CMS, cnName = "核名后经办人自然人")
    public static final long OP_HE_MING_NATURE = 50108L;

    /** 核名后经办人法人 */
    @BeanAttrInfo(value = "mail:3,8,77,100,101,102,103,104,105,106,107,108,109,110,111,112,113,114,115,116,117,118,;mobile:3,8,77,100,101,102,103,104,105,106,107,108,109,110,111,112,113,114,115,116,117,118", systemType = SystemType.SJLX_CMS, cnName = "核名后经办人法人")
    public static final long OP_HE_MING_LEGAL = 50109L;


    /** 营业执照完毕经办人 */
    @BeanAttrInfo(value = "mail:3,8,77,100,101,102,103,104,105,106,107,108,109,110,111,112,113,114,115,116,117,118,;mobile:3,8,77,100,101,102,103,104,105,106,107,108,109,110,111,112,113,114,115,116,117,118", systemType = SystemType.SJLX_CMS, cnName = "营业执照完毕经办人")
    public static final long OP_BUSSINESS_LICENSE = 50110L;


    /** 银行开户完毕经办人 */
    @BeanAttrInfo(value = "mail:3,8,77,100,101,102,103,104,105,106,107,108,109,110,111,112,113,114,115,116,117,118,;mobile:3,8,77,100,101,102,103,104,105,106,107,108,109,110,111,112,113,114,115,116,117,118", systemType = SystemType.SJLX_CMS, cnName = "银行开户完毕经办人")
    public static final long OP_BANK_FINISH = 50111L;

    /** 税务申报完毕后经办人 */
    @BeanAttrInfo(value = "mail:3,8,77,100,101,102,103,104,105,106,107,108,109,110,111,112,113,114,115,116,117,118,;mobile:3,8,77,100,101,102,103,104,105,106,107,108,109,110,111,112,113,114,115,116,117,118", systemType = SystemType.SJLX_CMS, cnName = "税务申报完毕后经办人")
    public static final long OP_REPORT_FINISH = 50112L;


    /** 续费通知经办人到期前20天 */
    @BeanAttrInfo(value = "mail:3,8,77,100,101,102,103,104,105,106,107,108,109,110,111,112,113,114,115,116,117,118,;mobile:3,8,77,100,101,102,103,104,105,106,107,108,109,110,111,112,113,114,115,116,117,118", systemType = SystemType.SJLX_CMS, cnName = "续费通知经办人到期前20天")
    public static final long OP_RENEW_NOTICE20 = 50113L;

    /** 过期通知经办人次月5号 */
    @BeanAttrInfo(value = "mail:3,8,77,100,101,102,103,104,105,106,107,108,109,110,111,112,113,114,115,116,117,118,;mobile:3,8,77,100,101,102,103,104,105,106,107,108,109,110,111,112,113,114,115,116,117,118", systemType = SystemType.SJLX_CMS, cnName = "过期通知经办人次月5号")
    public static final long OP_RENEW_NOTICE_NEXTMONTH5 = 50114L;

    /** 过期通知经办人次月13号 */
    @BeanAttrInfo(value = "mail:3,8,77,100,101,102,103,104,105,106,107,108,109,110,111,112,113,114,115,116,117,118,;mobile:3,8,77,100,101,102,103,104,105,106,107,108,109,110,111,112,113,114,115,116,117,118", systemType = SystemType.SJLX_CMS, cnName = "过期通知经办人次月13号")
    public static final long OP_RENEW_NOTICE_NEXTMONTH13 = 50115L;


    /** 核名后法人自然人 */
    @BeanAttrInfo(value = "mail:3,8,77,100,101,102,103,104,105,106,107,108,109,110,111,112,113,114,115,116,117,118,;mobile:3,8,77,100,101,102,103,104,105,106,107,108,109,110,111,112,113,114,115,116,117,118", systemType = SystemType.SJLX_CMS, cnName = "核名后法人自然人")
    public static final long LEGAL_HE_MING_NATURE = 50116L;

    /** 核名后法人法人 */
    @BeanAttrInfo(value = "mail:3,8,77,100,101,102,103,104,105,106,107,108,109,110,111,112,113,114,115,116,117,118,;mobile:3,8,77,100,101,102,103,104,105,106,107,108,109,110,111,112,113,114,115,116,117,118", systemType = SystemType.SJLX_CMS, cnName = "核名后法人法人")
    public static final long LEGAL_HE_MING_LEGAL = 50117L;


    /** 营业执照完毕法人 */
    @BeanAttrInfo(value = "mail:3,8,77,100,101,102,103,104,105,106,107,108,109,110,111,112,113,114,115,116,117,118,;mobile:3,8,77,100,101,102,103,104,105,106,107,108,109,110,111,112,113,114,115,116,117,118", systemType = SystemType.SJLX_CMS, cnName = "营业执照完毕法人")
    public static final long LEGAL_BUSSINESS_LICENSE = 50118L;


    /** 银行开户完毕法人 */
    @BeanAttrInfo(value = "mail:3,8,77,100,101,102,103,104,105,106,107,108,109,110,111,112,113,114,115,116,117,118,;mobile:3,8,77,100,101,102,103,104,105,106,107,108,109,110,111,112,113,114,115,116,117,118", systemType = SystemType.SJLX_CMS, cnName = "银行开户完毕法人")
    public static final long LEGAL_BANK_FINISH = 50119L;

    /** 税务申报完毕后法人 */
    @BeanAttrInfo(value = "mail:3,8,77,100,101,102,103,104,105,106,107,108,109,110,111,112,113,114,115,116,117,118,;mobile:3,8,77,100,101,102,103,104,105,106,107,108,109,110,111,112,113,114,115,116,117,118", systemType = SystemType.SJLX_CMS, cnName = "税务申报完毕后法人")
    public static final long LEGAL_REPORT_FINISH = 50120L;


    /** 续费通知法人到期前20天 */
    @BeanAttrInfo(value = "mail:3,8,77,100,101,102,103,104,105,106,107,108,109,110,111,112,113,114,115,116,117,118,;mobile:3,8,77,100,101,102,103,104,105,106,107,108,109,110,111,112,113,114,115,116,117,118", systemType = SystemType.SJLX_CMS, cnName = "续费通知法人到期前20天")
    public static final long LEGAL_RENEW_NOTICE20 = 50121L;

    /** 过期通知法人次月5号 */
    @BeanAttrInfo(value = "mail:3,8,77,100,101,102,103,104,105,106,107,108,109,110,111,112,113,114,115,116,117,118,;mobile:3,8,77,100,101,102,103,104,105,106,107,108,109,110,111,112,113,114,115,116,117,118", systemType = SystemType.SJLX_CMS, cnName = "过期通知法人次月5号")
    public static final long LEGAL_RENEW_NOTICE_NEXTMONTH5 = 50122L;

    /** 过期通知法人次月13号 */
    @BeanAttrInfo(value = "mail:3,8,77,100,101,102,103,104,105,106,107,108,109,110,111,112,113,114,115,116,117,118,;mobile:3,8,77,100,101,102,103,104,105,106,107,108,109,110,111,112,113,114,115,116,117,118", systemType = SystemType.SJLX_CMS, cnName = "过期通知法人次月13号")
    public static final long LEGAL_RENEW_NOTICE_NEXTMONTH13 = 50123L;

    /** 法人生日祝福 */
    @BeanAttrInfo(value = "mail:3,8,77,100,101,102,103,104,105,106,107,108,109,110,111,112,113,114,115,116,117,118,;mobile:3,8,77,100,101,102,103,104,105,106,107,108,109,110,111,112,113,114,115,116,117,118", systemType = SystemType.SJLX_CMS, cnName = "法人生日祝福")
    public static final long BIRTH_DAY_WISH_LEGAL = 50124L;

    /** 税务报道完毕后法人 */
    @BeanAttrInfo(value = "mail:3,8,77,100,101,102,103,104,105,106,107,108,109,110,111,112,113,114,115,116,117,118,;mobile:3,8,77,100,101,102,103,104,105,106,107,108,109,110,111,112,113,114,115,116,117,118", systemType = SystemType.SJLX_CMS, cnName = "税务报道完毕后法人")
    public static final long LEGAL_TAX_FINISH = 50125L;

    /** 税务报道完毕后经办人 */
    @BeanAttrInfo(value = "mail:3,8,77,100,101,102,103,104,105,106,107,108,109,110,111,112,113,114,115,116,117,118,;mobile:3,8,77,100,101,102,103,104,105,106,107,108,109,110,111,112,113,114,115,116,117,118", systemType = SystemType.SJLX_CMS, cnName = "税务报道完毕后经办人")
    public static final long OP_TAX_FINISH = 50126L;

    /** 用户查看公司附件送验证码 */
    @BeanAttrInfo(value = "mobile:3,25,77", systemType = SystemType.SJLX_CMS, cnName = "用户查看公司附件送验证码")
    public static final long USER_LOOK_COMPANY_ATTACH = 50127L;

    /** 用户下载公司附件送验证码 */
    @BeanAttrInfo(value = "mobile:3,25,77", systemType = SystemType.SJLX_CMS, cnName = "用户下载公司附件送验证码")
    public static final long USER_DOWN_COMPANY_ATTACH = 50128L;

    /** 用户删除公司附件送验证码 */
    @BeanAttrInfo(value = "mobile:3,25,77", systemType = SystemType.SJLX_CMS, cnName = "用户删除公司附件送验证码")
    public static final long USER_DELETE_COMPANY_ATTACH = 50129L;

    /** 用户域名被攻击的通知模板 */
    @BeanAttrInfo(value = "mail:1,3;mobile:1,3", systemType = SystemType.EISS, cnName = "用户域名被攻击的通知模板")
    public static final long DOMAIN_BE_ATTACKED_INFORM = 60000L;


    /** 核名申请提交 */
    @BeanAttrInfo(value = "mail:3,8,77,100,101,102,103,104,105,106,107,108,109,110,111,112,113,114,115,116,117,118,;mobile:3,8,77,100,101,102,103,104,105,106,107,108,109,110,111,112,113,114,115,116,117,118", systemType = SystemType.SJLX_CMS, cnName = "核名申请提交")
    public static final long PROCESS1_SMS = 50150L;

    /** 领取核名通知 */
    @BeanAttrInfo(value = "mail:3,8,77,100,101,102,103,104,105,106,107,108,109,110,111,112,113,114,115,116,117,118,;mobile:3,8,77,100,101,102,103,104,105,106,107,108,109,110,111,112,113,114,115,116,117,118", systemType = SystemType.SJLX_CMS, cnName = "领取核名通知")
    public static final long PROCESS2_SMS = 50151L;

    /** 执照办理申请 */
    @BeanAttrInfo(value = "mail:3,8,77,100,101,102,103,104,105,106,107,108,109,110,111,112,113,114,115,116,117,118,;mobile:3,8,77,100,101,102,103,104,105,106,107,108,109,110,111,112,113,114,115,116,117,118", systemType = SystemType.SJLX_CMS, cnName = "执照办理申请")
    public static final long PROCESS3_SMS = 50152L;

    /** 领取营业执照 */
    @BeanAttrInfo(value = "mail:3,8,77,100,101,102,103,104,105,106,107,108,109,110,111,112,113,114,115,116,117,118,;mobile:3,8,77,100,101,102,103,104,105,106,107,108,109,110,111,112,113,114,115,116,117,118", systemType = SystemType.SJLX_CMS, cnName = "领取营业执照")
    public static final long PROCESS4_SMS = 50153L;
    /** 银行开户申请 */
    @BeanAttrInfo(value = "mail:3,8,77,100,101,102,103,104,105,106,107,108,109,110,111,112,113,114,115,116,117,118,;mobile:3,8,77,100,101,102,103,104,105,106,107,108,109,110,111,112,113,114,115,116,117,118", systemType = SystemType.SJLX_CMS, cnName = "银行开户申请")
    public static final long PROCESS5_SMS = 50154L;
    /** 领取开户许可证 */
    @BeanAttrInfo(value = "mail:3,8,77,100,101,102,103,104,105,106,107,108,109,110,111,112,113,114,115,116,117,118,;mobile:3,8,77,100,101,102,103,104,105,106,107,108,109,110,111,112,113,114,115,116,117,118", systemType = SystemType.SJLX_CMS, cnName = "领取开户许可证")
    public static final long PROCESS6_SMS = 50155L;
    /** 银税协议签订 */
    @BeanAttrInfo(value = "mail:3,8,77,100,101,102,103,104,105,106,107,108,109,110,111,112,113,114,115,116,117,118,;mobile:3,8,77,100,101,102,103,104,105,106,107,108,109,110,111,112,113,114,115,116,117,118", systemType = SystemType.SJLX_CMS, cnName = "银税协议签订")
    public static final long PROCESS7_SMS = 50156L;
    /** 首次地税报道 */
    @BeanAttrInfo(value = "mail:3,8,77,100,101,102,103,104,105,106,107,108,109,110,111,112,113,114,115,116,117,118,;mobile:3,8,77,100,101,102,103,104,105,106,107,108,109,110,111,112,113,114,115,116,117,118", systemType = SystemType.SJLX_CMS, cnName = "首次地税报道")
    public static final long PROCESS8_SMS = 50157L;
    /** 二次税务报道 */
    @BeanAttrInfo(value = "mail:3,8,77,100,101,102,103,104,105,106,107,108,109,110,111,112,113,114,115,116,117,118,;mobile:3,8,77,100,101,102,103,104,105,106,107,108,109,110,111,112,113,114,115,116,117,118", systemType = SystemType.SJLX_CMS, cnName = "二次税务报道")
    public static final long PROCESS9_SMS = 50158L;
    /** 证件办理申请提交 */
    @BeanAttrInfo(value = "mail:3,8,77,100,101,102,103,104,105,106,107,108,109,110,111,112,113,114,115,116,117,118,;mobile:3,8,77,100,101,102,103,104,105,106,107,108,109,110,111,112,113,114,115,116,117,118", systemType = SystemType.SJLX_CMS, cnName = "证件办理申请提交")
    public static final long PROCESS10_SMS = 50159L;
    /** 领取相关证件 */
    @BeanAttrInfo(value = "mail:3,8,77,100,101,102,103,104,105,106,107,108,109,110,111,112,113,114,115,116,117,118,;mobile:3,8,77,100,101,102,103,104,105,106,107,108,109,110,111,112,113,114,115,116,117,118", systemType = SystemType.SJLX_CMS, cnName = "领取相关证件")
    public static final long PROCESS11_SMS = 50160L;
    /** 办理增值服务 */
    @BeanAttrInfo(value = "mail:3,8,77,100,101,102,103,104,105,106,107,108,109,110,111,112,113,114,115,116,117,118,;mobile:3,8,77,100,101,102,103,104,105,106,107,108,109,110,111,112,113,114,115,116,117,118", systemType = SystemType.SJLX_CMS, cnName = "办理增值服务")
    public static final long PROCESS12_SMS = 50161L;

    /** 领取三章 */
    @BeanAttrInfo(value = "mail:3,8,77,100,101,102,103,104,105,106,107,108,109,110,111,112,113,114,115,116,117,118,;mobile:3,8,77,100,101,102,103,104,105,106,107,108,109,110,111,112,113,114,115,116,117,118", systemType = SystemType.SJLX_CMS, cnName = "领取三章")
    public static final long PROCESS13_SMS = 50162L;

    //=====================业务公共平台================
    /** 短信发送失败通知邮件 */
    @BeanAttrInfo(value = "mail:57,40,42", systemType = SystemType.EISS_COMMON, cnName = "短信发送失败通知邮件")
    public static final long COMMON_MESSAGE_SMS_SEND_FAIL_NOTIFY = 70000L;
}