package com.sjdf.platform.dictionary.bean.ers;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.Dictionary;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author sjdf
 * @category 备案状态
 */
@Entity
@DiscriminatorValue("RECORD_MAIN_STATUS")
@BeanName(name = "备案主体状态")
public class RecordMainStatus extends Dictionary {

    /**
     * serial
     */
    private static final long serialVersionUID = -7289793189147993788L;

    /** 待上报接入商 */
    @BeanAttrInfo(value = "1", cnName = "待上报接入商")
    public static final long BE_REPORT_PROVIDER = 1L;

    /** 待代理审核 */
    @BeanAttrInfo(value = "2", cnName = "待代理审核")
    public static final long BE_AGENT_AUDIT = 2L;

    /** 待接入商审核 */
    @BeanAttrInfo(value = "3", cnName = "待接入商审核")
    public static final long BE_PROVIDER_AUDIT = 3L;

    /** 已上报管局 */
    @BeanAttrInfo(value = "4", cnName = "已上报管局")
    public static final long HAVE_REPORT_GOV = 4L;

    /** 待管局审核 */
    @BeanAttrInfo(value = "5", cnName = "待管局审核")
    public static final long BE_GOV_AUDIT = 5L;

    /** 退回接入商修改 */
    @BeanAttrInfo(value = "6", cnName = "退回接入商修改")
    public static final long RETURN_PROVIDER_MODIFY = 6L;

    /** 退回接入商修改 */
    @BeanAttrInfo(value = "7", cnName = "退回主办者修改")
    public static final long RETURN_SPONSOR_MODIFY = 7L;

    /** 审核通过 */
    @BeanAttrInfo(value = "8", cnName = "审核通过")
    public static final long AUDIT_APPROVE = 8L;

    /** 待填写主体信息 */
    @BeanAttrInfo(value = "9", cnName = "待填写主体信息")
    public static final long BE_FILL_MAIN_INFO = 9L;

    /** 待填写网站信息 */
    @BeanAttrInfo(value = "10", cnName = "待填写网站信息")
    public static final long BE_FILL_WEB_INFO = 10L;

    /** 待上传附件 */
    @BeanAttrInfo(value = "11", cnName = "待上传附件")
    public static final long BE_UPLOAD_ATTACHMENT = 11L;

    /** 待变更网站信息 */
    @BeanAttrInfo(value = "12", cnName = "待变更网站信息")
    public static final long BE_CHANGE_WEB_INFO = 12L;

    /** 待收取资料原件 */
    @BeanAttrInfo(value = "13", cnName = "待收取资料原件")
    public static final long BE_COLLECT_ORIGINAL = 13L;

    /** 资料原件已收 */
    @BeanAttrInfo(value = "14", cnName = "资料原件已收")
    public static final long HAVE_COLLECT_ORIGINAL = 14L;

    /** 初审通过待上报管局 */
    @BeanAttrInfo(value = "15", cnName = "初审通过待上报管局")
    public static final long PROVIDER_FIRST_AUDIT_APPROVE = 15L;

    /** 待补充资料原件 */
    @BeanAttrInfo(value = "16", cnName = "待补充资料原件")
    public static final long BE_REPLENISH_ORIGINAL = 16L;

    /** 待上报管局 */
    @BeanAttrInfo(value = "17", cnName = "待上报管局")
    public static final long BE_REPORT_GOV = 17L;

    /** 待管局审核(待确认) */
    @BeanAttrInfo(value = "18", cnName = "待管局审核(待确认)")
    public static final long BE_GOV_AUDIT_CHECKING = 18L;

    /** 上报管局失败 */
    @BeanAttrInfo(value = "19", cnName = "上报管局失败")
    public static final long REPORT_GOV_FAIL = 19L;
}
