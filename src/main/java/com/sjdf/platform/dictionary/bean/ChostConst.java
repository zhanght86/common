package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Create at 2012-10-29
 *
 * @author 黄远昌
 * @category 云主机常量
 */
@Entity
@DiscriminatorValue("CHOST_CONST")
@BeanName(name = "云主机常量")
public class ChostConst extends Dictionary {

    private static final long serialVersionUID = -4544770181756410006L;

    /** cpu配额权值 */
    @BeanAttrInfo(value = "2", cnName = "cpu配额权值")
    public static final long CPU_WEIGHTS = 1;

    /** 备份错误处理次数 */
    @BeanAttrInfo(value = "1", cnName = "备份错误处理次数")
    public static final long ERROR_MAX_RUN_TIME = 2;

    /** 同一用户每天可开通云主机试用个数 */
    @BeanAttrInfo(value = "1", cnName = "同一用户每天可开通云主机试用个数")
    public static final long TRIAL_MAX_NUM_PER_DAY = 3;

    /** 云主机快照截止时间 */
    @BeanAttrInfo(value = "8", cnName = "快照截止时间")
    public static final long SNAPSHOT_DEADLINE = 4;

    /** 手动选择节点最多显示可选节点（服务器）数 */
    @BeanAttrInfo(value = "10", cnName = "手动选择节点最多显示可选节点（服务器）数")
    public static final long SHOW_SERVERS_MAX_NUM = 5;

    /** 节点(服务器)一次性批量最大开设数 */
    @BeanAttrInfo(value = "10", cnName = "节点(服务器)一次性批量最大开设数")
    public static final long SERVER_BATH_CREATE_CHOST_MAX_NUM = 6;

    /** 一次性批量最大开设数 */
    @BeanAttrInfo(value = "20", cnName = "一次性批量最大开设数")
    public static final long BATH_CREATE_CHOST_MAX_NUM = 23;

    /** 一次性批量续费产品数限制 */
    @BeanAttrInfo(value = "20", cnName = "批量续费总数限制")
    public static final long BATH_RENEW_PRODUCT_MAX_NUM = 24;

    /** 大数据盘分界值（单位：GB） */
    @BeanAttrInfo(value = "200", cnName = "大数据盘分界值（单位：GB）")
    public static final long DATA_DISK_BOUNDARY_VALUE = 13;

    /** 云主机主服务器连通性检查超时时间（单位：ms） */
    @BeanAttrInfo(value = "1000", cnName = "云主机主服务器连通性检查超时时间（单位：ms）")
    public static final long CHOST_SERVER_CONNECT_TIMEOUT = 14;

    /** 是否根据监控数据中是否宕机进行过滤服务器 */
    @BeanAttrInfo(value = "1", cnName = "是否根据监控数据中是否宕机进行过滤服务器")
    public static final long IS_CONNECT_CHECK = 19;

    /** 云主机申请退款出账时提交的工单的服务项目名称 */
    @BeanAttrInfo(value = "退款出账", cnName = "云主机申请退款出账时提交的工单的服务项目名称")
    public static final long SERVER_ITEM_NAME_BY_REFUND_TO_ALIPAY = 20;

    @BeanAttrInfo(value = "60000", cnName = "二级平台请求机房数据频率")
    public static final long SECOND_PLATFORM_REQUEST_IDC_FREQUENCY = 21;

    @BeanAttrInfo(value = "13220165", cnName = "退款到支付宝时设置的超管ID")
    public static final long REFUND_TO_ALIPAY_ADMINUSER_ID = 22;

    @BeanAttrInfo(value = "30", cnName = "带宽预警查询时间")
    public static final long BANDWIDTH_WARNING = 25;

    @BeanAttrInfo(value = "win2003", cnName = "能够强制关机的模板前缀（多个以;分隔）")
    public static final long POWER_OFF_CHOST_OS = 26;

    @BeanAttrInfo(value = "winxp", cnName = "能够正常关机的模板前缀（多个以;分隔）")
    public static final long STOP_CHOST_OS = 27;

    @BeanAttrInfo(value = "ch2014062500001", cnName = "云主机试用产品列表(value:云主机产品列表,enName:服务器列表)", enName = "688,646")
    public static final long CHOST_TRY_PRODUCT_LIST = 28;

    @BeanAttrInfo(value = "1;6", cnName = "续费时默认价格单位和年限（如1年：1;6）")
    public static final long CHOST_RENEW_DEFAULT_PRICE = 29;

    @BeanAttrInfo(value = "2880616720", cnName = "云主机主服务器监控提供给机房的QQ号码")
    public static final long CHOST_SERVER_IDC_CONTACT_QQ = 30;
}
