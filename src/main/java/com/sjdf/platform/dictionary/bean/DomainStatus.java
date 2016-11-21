package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Create at 2012-6-20
 *
 * @author 邱忠福
 * @category 域名状态
 */
@Entity
@DiscriminatorValue("DOMAIN_STATUS")
@BeanName(name = "域名状态")
public class DomainStatus extends Dictionary {
    private static final long serialVersionUID = -1428791989633087423L;
    /** 信产部停止 */
    @BeanAttrInfo(value = "1", cnName = "信产部停止-需网监备案")
    public static final long MII_STOP = 1;
    /** 管理员停止 */
    @BeanAttrInfo(value = "2", cnName = "管理员停止")
    public static final long ADMIN_STOP = 2;
    /** 无法访问 */
    @BeanAttrInfo(value = "3", cnName = "无法访问")
    public static final long NO_ACCESS = 3;
    /** 免备案无法访问 */
    @BeanAttrInfo(value = "4", cnName = "免备案无法访问")
    public static final long NO_NEED_RECORD_NO_ACCESS = 4;
    /** 备案信息不真实-不可恢复 */
    @BeanAttrInfo(value = "5", cnName = "备案信息不真实-不可恢复")
    public static final long RECORD_UNREAL_UNRECOVERABLE = 5;
    /** 备案信息不真实-可恢复 */
    @BeanAttrInfo(value = "6", cnName = "备案信息不真实-可恢复")
    public static final long RECORD_UNREAL_RECOVERABLE = 6;
    /** 未备案 */
    @BeanAttrInfo(value = "7", cnName = "未备案")
    public static final long UNRECORD = 7;
    /** 暂停访问 */
    @BeanAttrInfo(value = "8", cnName = "暂停访问")
    public static final long PAUSE_ACCESS = 8;
    /** 免备案访问正常 */
    @BeanAttrInfo(value = "9", cnName = "免备案访问正常")
    public static final long NO_NEED_RECORD_ACCESS_NORMAL = 9;
    /** 正常 */
    @BeanAttrInfo(value = "10", cnName = "正常")
    public static final long NORMAL = 10;

}
