package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Create at 2013年8月16日 上午10:34:58
 *
 * @author KETQI
 * @category 通用网址接口返回状态常量
 */
@Entity
@DiscriminatorValue("WEBSITE_CONSTANT")
@BeanName(name = "通用网址接口返回状态常量")
public class WebsiteConstant extends Dictionary {
    private static final long serialVersionUID = 7304256566220910235L;

    /** 程序异常代码 */
    @BeanAttrInfo(cnName = "程序异常代码")
    public static final long EXCEPTION_CODE = 100;

    /** 200 Command completed successfully(命令执行成功) */
    @BeanAttrInfo(cnName = "命令执行成功")
    public static final long COMMAND_COMPLETED_SUCCESSFULLY = 200;

    /** 210: KeyWord available(通用网址可以注册) */
    @BeanAttrInfo(cnName = "通用网址可以注册")
    public static final long KEYWORD_AVAILABLE = 210;

    /** 211 KeyWord not available(通用网址已经被注册) */
    @BeanAttrInfo(cnName = "通用网址已经被注册")
    public static final long KEYWORD_NOT_AVAILABLE = 211;

    /** 214 Contactor available(联系人可以注册) */
    @BeanAttrInfo(cnName = "联系人可以注册")
    public static final long CONTACTOR_AVAILABLE = 214;

    /** 215 Contactor not available(联系人已经被注册) */
    @BeanAttrInfo(cnName = "联系人已经被注册")
    public static final long CONTACTOR_NOT_AVAILABLE = 215;

    /** 231 There is no messages in your message queue(消息队列中没有消息) */
    @BeanAttrInfo(cnName = "消息队列中没有消息")
    public static final long NO_MESSAGES_IN_YOUR_QUEUE = 231;

    /**
     * 421 Some of data sent to registry are incorrect, DB can not operate
     * successfully(网络引起的未知错误发生)
     */
    @BeanAttrInfo(cnName = "网络引起的未知错误或者数据无效")
    public static final long NETWORK_CAUSED_BY_AN_UNKNOWN_ERROR = 421;

    /**
     * 422 The command permission has been prohibited by Registry(命令失败，Registrar
     * 没有权限执行该命令)
     */
    @BeanAttrInfo(cnName = "命令失败，Registrar 没有权限执行该命令")
    public static final long COMMAND_PERMISSION_HAS_BEEN_PROHIBITED_BY_REGISTRY = 422;

    /** 500 Invalid command name(非法的KWRRP命令名) */
    @BeanAttrInfo(cnName = "非法的KWRRP命令名")
    public static final long INVALID_COMMAND_NAME = 500;

    /** 502 Invalid entity value(非法的属性名) */
    @BeanAttrInfo(cnName = "非法的属性名")
    public static final long INVALID_ENTITY_VALUE = 502;

    /** 504 Missing required attribute(缺少的必要的属性名称) */
    @BeanAttrInfo(cnName = "缺少的必要的属性名称")
    public static final long MISSING_REQUIRED_ATTRIBUTE = 504;

    /** 505 Invalid attribute value syntax(语法错误的属性名称) */
    @BeanAttrInfo(cnName = "语法错误的属性名称")
    public static final long INVALID_ATTRIBUTE_VALUE_SYNTAX = 505;

    /** 507 Invalid command format(非法的命令格式) */
    @BeanAttrInfo(cnName = "非法的命令格式")
    public static final long INVALID_COMMAND_FORMAT = 507;

    /** 514 Contactors didnot register(操作失败，所操作的通用网址联系人没有注册) */
    @BeanAttrInfo(cnName = "操作失败，所操作的通用网址联系人没有注册")
    public static final long CONTACTORS_DIDNOT_REGISTER = 514;

    /**
     * 520 Server closing connection.Client should try opening new
     * connection(KWRRP 链接超时)
     */
    @BeanAttrInfo(cnName = "KWRRP链接超时")
    public static final long SERVER_CLOSING_CONNECTION = 520;

    /**
     * 522 The contactor is using by the KeyWord(删除联系人失败, 由于 Registrar
     * 删除的联系人被通用网址引用)
     */
    @BeanAttrInfo(cnName = "删除联系人失败, 由于 Registrar 删除的联系人被通用网址引用")
    public static final long CONTACTOR_IS_USING_BY_KEYWORD = 522;

    /**
     * 529 Operating Entity is not exist or Entity is locked or Authorization
     * failed (操作失败，由于操作不属于自己的对象，或操作对象不存在，或在操作正在转移的操作对象。)
     */
    @BeanAttrInfo(cnName = "操作失败，由于操作不属于自己的对象，或操作对象不存在，或在操作正在转移的操作对象")
    public static final long OPERATING_ENTITY_NOT_EXIST_OR_IS_LOCKED_OR_AUTHORIZATION_FAILED = 529;

    /** 531 Authorization failed(会话建立失败) */
    @BeanAttrInfo(cnName = "会话建立失败")
    public static final long AUTHORIZATION_FAILED = 531;

    /** 536 Domain already flagged for transfer(转移命令处理失败，该域名正在转移过程中) */
    @BeanAttrInfo(cnName = "转移命令处理失败，该域名正在转移过程中")
    public static final long DOMAIN_ALREADY_FLAGGED_TRANSFER = 536;

    /** 548 Key word is not up for add/renewal/transfer (当前关键字不能进行注册,续费,转移操作) */
    @BeanAttrInfo(cnName = "当前关键字不能进行注册,续费,转移操作")
    public static final long KEYWORD_NOT_FOR_RENEW = 548;

    /** 554 KeyWord already registered(注册失败，通用网址已经被注册) */
    @BeanAttrInfo(cnName = "注册失败，通用网址已经被注册")
    public static final long KEYWORD_ALREADY_REGISTERED = 554;

    /** 564 Contactor already registered(联系人已经被注册) */
    @BeanAttrInfo(cnName = "联系人已经被注册")
    public static final long CONTACTOR_ALREADY_REGISTERED = 564;

    /** 568 Domain is not up for transfer(转移命令处理失败) */
    @BeanAttrInfo(cnName = "转移命令处理失败")
    public static final long DOMAIN_IS_NOT_UP_FOR_TRANSFER = 568;

    /** 648 the registrar available fee is not enough(代理商费用不足) */
    @BeanAttrInfo(cnName = "代理商费用不足")
    public static final long REGISTRAR_AVAILABLE_FEE_NOT_ENOUGH = 648;

    /** 711 Key word is Area Collision(注册违反跨区销售) */
    @BeanAttrInfo(cnName = "注册违反跨区销售")
    public static final long KEYWORD_IS_AREA_COLLISION = 711;

    /** 712 Key word prohibits operation for police(操作违反注册政策) */
    @BeanAttrInfo(cnName = "操作违反注册政策")
    public static final long KEYWORD_PROHIBITS_OPERATION_FOR_POLICE = 712;
}
