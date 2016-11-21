package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Create at 2013-1-23
 *
 * @author 丁yan
 * @category 域名记录状态
 */
@Entity
@DiscriminatorValue("DOMAIN_ORDER_RECORD_STATUS")
@BeanName(name = "域名记录状态")
public class DomainOrderRecordStatus extends Dictionary {
    private static final long serialVersionUID = -6665618826346116028L;

    /** 订单处理成功 */
    @BeanAttrInfo(value = "你确定该订单已处理完毕，才能修改为该状态", cnName = "订单处理成功")
    public static final long SUCCESS = 1;

    /** 状态异常待超管处理 */
    @BeanAttrInfo(value = "该状态为系统自动设定，人工不能设置该状态", cnName = "状态异常待超管处理")
    public static final long FAIL = 2;

    /** 订单提交成功待超管处理 */
    @BeanAttrInfo(value = "该状态为系统自动设定，人工不能设置该状态。转入：需要到上家进行域名转入处理。转出：需要到上家获取转移密码。过户：需要到上家进行域名过户。过户成功后修改状态为订单处理成功，否则不做处理。赎回：赎回成功修改为订单处理成功，赎回失败修改为废弃订单", cnName = "订单提交成功待超管处理", enName = "请于第二个工作日查看处理进度")
    public static final long PROCESSING = 3;

    /** 超管处理完毕待系统处理 */
    @BeanAttrInfo(value = "超管处理完毕，需要系统进行后续处理时，需要设定为状态", cnName = "超管处理完毕待系统处理")
    public static final long MAILED = 4;

    /** 废弃订单 */
    @BeanAttrInfo(value = "该订单已无法处理，或客户要求取消该订单时，需要设定为状态。设定为该状态的同时会自动退款", cnName = "废弃订单", enName = "该订单已经无法处理或者客户要求废弃订单。")
    public static final long ABANDONED = 5;

    /** 问题件-需修改订单 */
    @BeanAttrInfo(value = "需要客户处理的订单，需要设定为该状态", cnName = "问题件-需修改订单")
    public static final long ERROR = 6;

    /** 待审核资料 */
    @BeanAttrInfo(value = "该状态为系统自动设定，人工不能设置该状态", cnName = "待审核资料")
    public static final long PENDING_REVIEW = 7;

    /** 待上传资料 */
    @BeanAttrInfo(value = "该状态为系统自动设定，人工不能设置该状态", cnName = "待上传资料", enName = "请点击【上传】按钮进行资料的上传")
    public static final long POST_ATTACHMENTS = 8;

    /** 资料审核未通过-需重新上传资料 */
    @BeanAttrInfo(value = "客户上传的资料审核未通过时，需要设定为状态", cnName = "资料审核未通过-需重新上传资料", enName = "具体原因请点击【上传】并在该页面进行查看")
    public static final long AUDIT_NOT_PASS = 9;

    /** 我司处理完毕需确认注册商处理结果 */
    @BeanAttrInfo(value = "我司已经提交了相关请求，等待注册商返回处理结果时，需要设定为状态", cnName = "我司处理完毕需确认注册商处理结果")
    public static final long WAIT_REGISTER_CONFIRM = 10;

    /** 待客户邮寄资料原件 */
    @BeanAttrInfo(value = "cn转出时，需要客户邮寄资料原件时，需要设定为状态", cnName = "待客户邮寄资料原件")
    public static final long WAIT_CLIENT_SUBMIT_INFORMATION = 11;

    /** 资料上传失败 */
    @BeanAttrInfo(value = "该状态为系统自动设定，人工不能设置该状态", cnName = "资料上传失败")
    public static final long POST_ATTACHMENTS_FAIL = 12;
}
