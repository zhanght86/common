package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Create at 2013年8月8日 下午5:18:10
 *
 * @author KETQI
 * @category 主站资源文件中文配置库
 */
@Entity
@DiscriminatorValue("EISS_NEW_SOURCE_ZH_CN")
@BeanName(name = "主站资源文件中文配置库")
public class EissNewSourceZhCn extends Dictionary {

    /** 序列化ID */
    private static final long serialVersionUID = 2882157632658724039L;

    /** 如果删除该域名的解析，我司会在10天内取消该域名的备案接入。 */
    @BeanAttrInfo(value = "如果删除该域名的解析，我司会在{DAY}天内取消该域名的备案接入。", orderBy = 1, cnName = "删除域名解析提示信息")
    public static final long DELETE_DOMAIN_PARSE_TIP = 1;

    /** 修改域名解析提示信息非国内 */
    @BeanAttrInfo(value = "您修改后的IP不在我司或非国内机房，我司会在{DAY}天内取消该域名的备案接入。", orderBy = 2, cnName = "修改域名解析提示信息非国内")
    public static final long UPDATE_DOMAIN_PARSE_TIP_FOREIGN = 2;

    /** 修改域名解析提示信息国内 */
    @BeanAttrInfo(value = "您修改后的IP与备案IP不一致，我司会在{DAY}天内自动为您进行备案IP变更", orderBy = 3, cnName = "修改域名解析提示信息国内")
    public static final long UPDATE_DOMAIN_PARSE_TIP_DOMESTIC_ONE = 3;

    /** 修改域名解析提示信息国内 */
    @BeanAttrInfo(value = "1. 您修改后的IP与备案IP不一致，请速登录备案系统进行变更备案\r\n2. 如果{DAY}天内不提交变更备案，我司会停止该域名所绑定的产品。\r\n3. 如果您进行了变更备案并且通过了超管审核，则系统会自动在1小时内恢复产品", orderBy = 4, cnName = "修改域名解析提示信息国内")
    public static final long UPDATE_DOMAIN_PARSE_TIP_DOMESTIC_TWO = 4;

    /** 如果删除该域名的绑定，我司会在{DAY}天内取消该域名的备案接入。 */
    @BeanAttrInfo(value = "如果删除该域名的绑定，我司会在{DAY}天内取消该域名的备案接入。", orderBy = 5, cnName = "需备案机房删除绑定提示信息")
    public static final long DELETE_BINDING_TIP = 5;

    /** 不需备案机房增加绑定提示信息 */
    @BeanAttrInfo(value = "该域名在我司存在备案接入，如您绑定到国外机房，我司会在{DAY}天内取消该域名的备案接入。", orderBy = 6, cnName = "不需备案机房增加绑定提示信息")
    public static final long ADD_BINDING_TIP = 6;

}
