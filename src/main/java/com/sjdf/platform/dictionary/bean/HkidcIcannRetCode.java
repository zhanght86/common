package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * 2013-04-19
 *
 * @author 黄远昌
 * @category ICANN返回代码
 */
@Entity
@DiscriminatorValue("HKIDC_ICANN_RET_CODE")
@BeanName(name = "ICANN返回代码")
public class HkidcIcannRetCode extends Dictionary {
    private static final long serialVersionUID = 6773930969719098643L;
    /** 成功 */
    @BeanAttrInfo(value = "1000", cnName = "成功", enName = "Command completed successfully")
    public static final long SUCCESS = 1000;
    /** 请求成功 */
    @BeanAttrInfo(value = "1001", cnName = "请求成功", enName = "Command completed successfully; action pending")
    public static final long SUCCESS_PENDING = 1001;
    /** 没有消息 */
    @BeanAttrInfo(value = "1300", cnName = "成功,没有消息", enName = "Command completed successfully; no messages")
    public static final long SUCCESS_NO_MESSAGES = 1300;
    /** 消息响应成功 */
    @BeanAttrInfo(value = "1301", cnName = "消息响应成功", enName = "Command completed successfully; ack to dequeue")
    public static final long SUCCESS_ACK_TO_DEQUEUE = 1301;
    /** 成功,什么事都没做 */
    @BeanAttrInfo(value = "1301", cnName = "成功,什么事都没做", enName = "Command completed successfully; nothing done")
    public static final long SUCCESS_NO_ERROR_NOTHING_DONE = 1302;
    /** 关闭会话 */
    @BeanAttrInfo(value = "1500", cnName = "关闭会话", enName = "Command completed successfully; ending session")
    public static final long SUCCESS_ENDING_SESSION = 1500;
    /** 未知命令 */
    @BeanAttrInfo(value = "2000", cnName = "未知命令", enName = "Unknown command")
    public static final long UNKNOWN_COMMAND = 2000;
    /** 命令语法错误 */
    @BeanAttrInfo(value = "2001", cnName = "命令语法错误", enName = "Command syntax error")
    public static final long COMMAND_SYNTAX_ERROR = 2001;
    /** 命令使用错误 */
    @BeanAttrInfo(value = "2002", cnName = "命令使用错误", enName = "Command use error")
    public static final long COMMAND_USE_ERROR = 2002;
    /** 缺少所需参数 */
    @BeanAttrInfo(value = "2003", cnName = "缺少所需参数", enName = "Required parameter missing")
    public static final long REQUIRED_PARAMETER_MISSING = 2003;
    /** 参数值范围错误 */
    @BeanAttrInfo(value = "2004", cnName = "参数值范围错误", enName = "Parameter value range error")
    public static final long PARAMETER_VALUE_RANGE_ERROR = 2004;
    /** 参数值语法错误 */
    @BeanAttrInfo(value = "2005", cnName = "参数值语法错误", enName = "Parameter value syntax error")
    public static final long PARAMETER_VALUE_SYNTAX_ERROR = 2005;
    /** 未实现的协议版本 */
    @BeanAttrInfo(value = "2100", cnName = "未实现的协议版本", enName = "Unimplemented protocol version")
    public static final long UNIMPLEMENTED_PROTOCOL_VERSION = 2100;
    /** 未实现的命令 */
    @BeanAttrInfo(value = "2101", cnName = "未实现的命令", enName = "Unimplemented command")
    public static final long UNIMPLEMENTED_COMMAND = 2101;
    /** 未实现的选项 */
    @BeanAttrInfo(value = "2102", cnName = "未实现的选项", enName = "Unimplemented option")
    public static final long UNIMPLEMENTED_OPTION = 2102;
    /** 未实现的扩展 */
    @BeanAttrInfo(value = "2103", cnName = "未实现的扩展", enName = "Unimplemented extension")
    public static final long UNIMPLEMENTED_EXTENSION = 2103;
    /** 计费失败 */
    @BeanAttrInfo(value = "2104", cnName = "计费失败", enName = "Billing failure")
    public static final long BILLING_FAILURE = 2104;
    /** 不允许续费 */
    @BeanAttrInfo(value = "2105", cnName = "不允许续费", enName = "Object is not eligible for renewal")
    public static final long RENEW_PROHIBITED = 2105;
    /** 不允许转移 */
    @BeanAttrInfo(value = "2106", cnName = "不允许转移", enName = "Object is not eligible for transfer")
    public static final long TRANSFER_PROHIBITED = 2106;
    /** 验证错误 */
    @BeanAttrInfo(value = "2200", cnName = "验证错误", enName = "Authentication error")
    public static final long AUTHENTICATION_ERROR = 2200;
    /** 转移密码错误 */
    @BeanAttrInfo(value = "2201", cnName = "转移密码错误", enName = "Authorization error")
    public static final long AUTHORIZATION_ERROR = 2201;
    /** 无效的授权信息 */
    @BeanAttrInfo(value = "2202", cnName = "无效的授权信息", enName = "Invalid authorization information")
    public static final long INVALID_AUTHORIZATION_INFO = 2202;
    /** 域名转移中 */
    @BeanAttrInfo(value = "2300", cnName = "域名转移中", enName = "Object pending transfer")
    public static final long PENDING_TRANSFER = 2300;
    /** 域名未请求转移 */
    @BeanAttrInfo(value = "2301", cnName = "域名未请求转移", enName = "Object not pending transfer")
    public static final long NOT_PENDING_TRANSFER = 2301;
    /** 对象已存在 */
    @BeanAttrInfo(value = "2302", cnName = "对象已存在", enName = "Object exists")
    public static final long OBJECT_EXISTS = 2302;
    /** 对象不存在 */
    @BeanAttrInfo(value = "2303", cnName = "对象不存在", enName = "Object does not exists")
    public static final long OBJECT_NOT_EXISTS = 2303;
    /** 域名处于禁止操作状态 */
    @BeanAttrInfo(value = "2304", cnName = "域名处于禁止操作状态", enName = "Object status prohibits operation")
    public static final long PROHIBITS_OPERATION_FOR_STATUS = 2304;
    /** 域名有关联信息禁止操作 */
    @BeanAttrInfo(value = "2305", cnName = "域名有关联信息禁止操作", enName = "Object association prohibits operation")
    public static final long PROHIBITS_OPERATION_FOR_ASSOCIATION = 2305;
    /** 参数值非法 */
    @BeanAttrInfo(value = "2306", cnName = "参数值非法", enName = "Parameter value policy error")
    public static final long PARAMETER_VALUE_POLICY_ERROR = 2306;
    /** 未实现的服务 */
    @BeanAttrInfo(value = "2307", cnName = "未实现的服务", enName = "Unimplemented object service")
    public static final long UNIMPLEMENTED_OBJECT_SERVICE = 2307;
    /** 违反了数据管理条例 */
    @BeanAttrInfo(value = "2308", cnName = "违反了数据管理条例", enName = "Data management policy violation")
    public static final long DATA_MANAGEMENT_POLICY_VIOLATION = 2308;
    /** 命令执行失败 */
    @BeanAttrInfo(value = "2400", cnName = "命令执行失败", enName = "Command failed")
    public static final long COMMAND_FAILED = 2400;
    /** 命令执行失败，服务器已关闭连接 */
    @BeanAttrInfo(value = "2500", cnName = "命令执行失败，服务器已关闭连接", enName = "Command failed; server closing connection")
    public static final long SERVER_CLOSING_CONNECTION_1 = 2500;
    /** 验证错误，服务器关闭连接 */
    @BeanAttrInfo(value = "2501", cnName = "验证错误，服务器关闭连接", enName = "Authentication error; server closing connection")
    public static final long SERVER_CLOSING_CONNECTION_2 = 2501;
    /** 会话超过限制，服务器关闭连接 */
    @BeanAttrInfo(value = "2502", cnName = "会话超过限制，服务器关闭连接", enName = "Session limit exceeded; server closing connection")
    public static final long SERVER_CLOSING_CONNECTION_3 = 2502;
}
