package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Create at 2012-05-23
 *
 * @author 王正伟
 * @category 系统邮件状态
 */
@Entity
@DiscriminatorValue("EMAIL_STATUS")
@BeanName(name = "系统邮件状态")
public class EmailStatus extends Dictionary {
    private static final long serialVersionUID = 4065217157209388194L;

    /** 新建邮件失败 */
    @BeanAttrInfo(value = "1", orderBy = 1, cnName = "新建邮件失败")
    public static final long EMAIL_NEW_FAIL = 1;

    /** 暂存 */
    @BeanAttrInfo(value = "5", orderBy = 5, cnName = "暂存")
    public static final long EMAIL_ONLY_SAVE = 5;

    /** 邮件发送中 */
    @BeanAttrInfo(value = "10", orderBy = 10, cnName = "发送中")
    public static final long EMAIL_SENDING = 10;

    /** 邮件发送失败 */
    @BeanAttrInfo(value = "15", orderBy = 15, cnName = "发送失败")
    public static final long EMAIL_SEND_FAIL = 15;

    /** 邮件发送成功 */
    @BeanAttrInfo(value = "17", orderBy = 17, cnName = "发送成功")
    public static final long EMAIL_SEND_SUCCESS = 17;

    /** 接收失败 */
    @BeanAttrInfo(value = "21", orderBy = 21, cnName = "接收失败")
    public static final long EMAIL_RECEIVE_FAIL = 21;

    /** 接收成功 */
    @BeanAttrInfo(value = "22", orderBy = 22, cnName = "接收成功")
    public static final long EMAIL_RECEIVE_SUCCESS = 22;

    /** 处理中 */
    @BeanAttrInfo(value = "30", orderBy = 30, cnName = "处理中")
    public static final long EMAIL_HANDLING = 31;

    /** 已处理 */
    @BeanAttrInfo(value = "31", orderBy = 31, cnName = "已处理")
    public static final long EMAIL_HANDLED = 31;

    /** 无效邮件 */
    @BeanAttrInfo(value = "32", orderBy = 32, cnName = "无效邮件")
    public static final long EMAIL_VERCODE_INVALID = 32;

    /** 不处理 */
    @BeanAttrInfo(value = "33", orderBy = 33, cnName = "不处理")
    public static final long EMAIL_IGNORE = 33;

    /** 处理失败 */
    @BeanAttrInfo(value = "37", orderBy = 37, cnName = "处理失败")
    public static final long EMAIL_HANDLE_FAIL = 37;

    /** 处理成功 */
    @BeanAttrInfo(value = "38", orderBy = 38, cnName = "处理成功")
    public static final long EMAIL_HANDLE_SUCCESS = 38;

    /** 垃圾邮件 */
    @BeanAttrInfo(value = "39", orderBy = 39, cnName = "垃圾邮件")
    public static final long EMAIL_GARBAGE = 39;

    /** 同意过户 */
    @BeanAttrInfo(value = "50", orderBy = 50, cnName = "同意过户")
    public static final long EMAIL_HANDLE_AGREE_TRANSFER_OWNER = 50;
}
