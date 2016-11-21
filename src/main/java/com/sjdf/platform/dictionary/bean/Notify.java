package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Create at 2012-10-10
 * <p/>
 * 通知
 *
 * @author Chen Mohan
 */
@Entity
@DiscriminatorValue("NOTIFY")
@BeanName(name = "通知")
public class Notify extends Dictionary {

    private static final long serialVersionUID = 8337948884998372722L;

    /**
     * ===========================================================================================================================================================
     * 管局拨测相关通知
     * ===========================================================================================================================================================
     */

    @BeanAttrInfo(value = "FUNC:getRecordInfoNotify1|API:http://tools.51web.com:30311/record-info-notify!api?recordInfoNotifyBean.miiDialsType=1&recordInfoNotifyBean.eissProductClass=${session.productClass}&recordInfoNotifyBean.eissProductName=${session.productName}&recordInfoNotifyBean.ersUserName=${session.yhm}&recordInfoNotifyBean.eissUserName=${session.userName}|URL:http://tools.51web.com:30311/record-info-notify?recordInfoNotifyBean.miiDialsType=1&recordInfoNotifyBean.eissProductClass=${session.productClass}&recordInfoNotifyBean.eissProductName=${session.productName}&recordInfoNotifyBean.ersUserName=${session.yhm}&recordInfoNotifyBean.eissUserName=${session.userName}", cnName = "备案信息不真实列表（2012-12-24）")
    public static final long RECORD_INFO_ERROR_1 = 1;

    @BeanAttrInfo(value = "FUNC:getRecordInfoNotify2|API:http://tools.51web.com:30311/record-info-notify!api?recordInfoNotifyBean.miiDialsType=2&recordInfoNotifyBean.eissProductClass=${session.productClass}&recordInfoNotifyBean.eissProductName=${session.productName}&recordInfoNotifyBean.ersUserName=${session.yhm}&recordInfoNotifyBean.eissUserName=${session.userName}|URL:http://tools.51web.com:30311/record-info-notify?recordInfoNotifyBean.miiDialsType=2&recordInfoNotifyBean.eissProductClass=${session.productClass}&recordInfoNotifyBean.eissProductName=${session.productName}&recordInfoNotifyBean.ersUserName=${session.yhm}&recordInfoNotifyBean.eissUserName=${session.userName}", cnName = "需要新增接入列表（2012-12-24）")
    public static final long RECORD_INFO_ERROR_2 = 2;

    @BeanAttrInfo(value = "FUNC:getRecordInfoNotify3|API:http://tools.51web.com:30311/record-info-notify!api?recordInfoNotifyBean.miiDialsType=3&recordInfoNotifyBean.eissProductClass=${session.productClass}&recordInfoNotifyBean.eissProductName=${session.productName}&recordInfoNotifyBean.ersUserName=${session.yhm}&recordInfoNotifyBean.eissUserName=${session.userName}|URL:http://tools.51web.com:30311/record-info-notify?recordInfoNotifyBean.miiDialsType=3&recordInfoNotifyBean.eissProductClass=${session.productClass}&recordInfoNotifyBean.eissProductName=${session.productName}&recordInfoNotifyBean.ersUserName=${session.yhm}&recordInfoNotifyBean.eissUserName=${session.userName}", cnName = "备案信息不真实列表（2012-12-24）")
    public static final long RECORD_INFO_ERROR_3 = 3;

    /**
     * ===========================================================================================================================================================
     * 主站产品备案接入检查相关通知
     * ===========================================================================================================================================================
     */
    /** 主站_产品备案接入检查_待取消备案接入域名（未解析到有效产品） */
    @BeanAttrInfo(value = "FUNC:domainHostNotInIdcNotify|API:http://client.cdnhost.cn/ProductRecordAccessAction!api.action?productRecordAccessBean.type=1&productRecordAccessBean.productClass=${session.productClass}&productRecordAccessBean.name=${session.productName}&productRecordAccessBean.eissUserName=${session.userName}&productRecordAccessBean.ersUserName=${session.yhm}|URL:http://client.cdnhost.cn/ProductRecordAccessAction!execute.action?productRecordAccessBean.type=1&productRecordAccessBean.productClass=${session.productClass}&productRecordAccessBean.name=${session.productName}&productRecordAccessBean.eissUserName=${session.userName}&productRecordAccessBean.ersUserName=${session.yhm}", cnName = "需取消备案接入域名（未解析到有效产品）")
    public static final long EISS_DOMAIN_HOST_NOT_IN_IDC = 4;

    /** 主站_产品备案接入检查_待变更备案域名（备案信息不真实） */
    @BeanAttrInfo(value = "FUNC:domainHostNotInProductNotify|API:http://client.cdnhost.cn/ProductRecordAccessAction!api.action?productRecordAccessBean.type=2&productRecordAccessBean.productClass=${session.productClass}&productRecordAccessBean.name=${session.productName}&productRecordAccessBean.eissUserName=${session.userName}&productRecordAccessBean.ersUserName=${session.yhm}|URL:http://client.cdnhost.cn/ProductRecordAccessAction!execute.action?productRecordAccessBean.type=2&productRecordAccessBean.productClass=${session.productClass}&productRecordAccessBean.name=${session.productName}&productRecordAccessBean.eissUserName=${session.userName}&productRecordAccessBean.ersUserName=${session.yhm}", cnName = "需变更备案域名")
    public static final long EISS_DOMAIN_HOST_NOT_IN_PRODUCT = 5;

    /** 主站_产品备案接入检查_待取消接入域名（非国内机房不能备案） */
    @BeanAttrInfo(value = "FUNC:canNotRecordNotify|API:http://client.cdnhost.cn/ProductRecordAccessAction!api.action?productRecordAccessBean.type=3&productRecordAccessBean.productClass=${session.productClass}&productRecordAccessBean.name=${session.productName}&productRecordAccessBean.eissUserName=${session.userName}&productRecordAccessBean.ersUserName=${session.yhm}|URL:http://client.cdnhost.cn/ProductRecordAccessAction!execute.action?productRecordAccessBean.type=3&productRecordAccessBean.productClass=${session.productClass}&productRecordAccessBean.name=${session.productName}&productRecordAccessBean.eissUserName=${session.userName}&productRecordAccessBean.ersUserName=${session.yhm}", cnName = "需取消接入域名（非国内机房不能备案）")
    public static final long EISS_CAN_NOT_RECORD = 6;

    /** 主站_产品备案接入检查_需新增接入域名 */
    @BeanAttrInfo(value = "FUNC:newAccessNotify|API:http://client.cdnhost.cn/ProductRecordAccessAction!api.action?productRecordAccessBean.type=4&productRecordAccessBean.productClass=${session.productClass}&productRecordAccessBean.name=${session.productName}&productRecordAccessBean.eissUserName=${session.userName}&productRecordAccessBean.ersUserName=${session.yhm}|URL:http://client.cdnhost.cn/ProductRecordAccessAction!execute.action?productRecordAccessBean.type=4&productRecordAccessBean.productClass=${session.productClass}&productRecordAccessBean.name=${session.productName}&productRecordAccessBean.eissUserName=${session.userName}&productRecordAccessBean.ersUserName=${session.yhm}", cnName = "需新增接入域名")
    public static final long EISS_NEW_ACCESS = 7;

    /** 主站_产品备案接入检查_需人工处理域名 */
    @BeanAttrInfo(value = "FUNC:manualHand|API:http://client.cdnhost.cn/ProductRecordAccessAction!api.action?productRecordAccessBean.type=5&productRecordAccessBean.productClass=${session.productClass}&productRecordAccessBean.name=${session.productName}&productRecordAccessBean.eissUserName=${session.userName}&productRecordAccessBean.ersUserName=${session.yhm}|URL:http://client.cdnhost.cn/ProductRecordAccessAction!execute.action?productRecordAccessBean.type=5&productRecordAccessBean.productClass=${session.productClass}&productRecordAccessBean.name=${session.productName}&productRecordAccessBean.eissUserName=${session.userName}&productRecordAccessBean.ersUserName=${session.yhm}", cnName = "需人工处理域名")
    public static final long EISS_MANUAL_HAND = 8;

}
