package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author 丁yan
 * @category 需要上传附件资料的域名后缀
 * @ClassName NeedAttachmentDomSuffix
 * @Created 2013-3-20
 */
@Entity
@DiscriminatorValue("NEED_ATTACHMENT_DOM_SUFFIX")
@BeanName(name = "需要上传附件资料的域名后缀")
public class NeedAttachmentDomSuffix extends Dictionary {
    private static final long serialVersionUID = -5093854238723707936L;

    /** .cn */
    @BeanAttrInfo(value = ".cn", cnName = "CN后缀域名")
    public static final long CN = 1;

    /** .gov.cn */
    @BeanAttrInfo(value = ".gov.cn", cnName = "gov.cn后缀域名 ")
    public static final long GOV_CN = 2;

    /** .中国/.网络/.公司 */
    @BeanAttrInfo(value = ".中国/.网络/.公司", cnName = "中国/网络/公司后缀域名 ")
    public static final long CHINA_NETWORK_COMPANY = 3;

    /** .中国 */
    @BeanAttrInfo(value = ".中国", cnName = "中国")
    public static final long CHINA = 4;

    /** .网络 */
    @BeanAttrInfo(value = ".网络", cnName = "网络")
    public static final long NETWORK = 5;

    /** .公司 */
    @BeanAttrInfo(value = ".公司", cnName = "公司")
    public static final long COMPANY = 6;
}
