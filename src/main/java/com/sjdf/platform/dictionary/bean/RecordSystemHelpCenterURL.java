package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Create at 2013年8月8日 下午5:19:37
 *
 * @author KETQI
 * @category 备案帮助中心链接
 */
@Entity
@DiscriminatorValue("RECORD_SYSTEM_HELP_CENTER_URL")
@BeanName(name = "备案帮助中心链接")
public class RecordSystemHelpCenterURL extends Dictionary {

    private static final long serialVersionUID = 2882157632658724033L;

    /** 帮助中心首页 */
    @BeanAttrInfo(orderBy = 1, cnName = "帮助中心", value = "http://help.cdnhost.cn/")
    public static final long HELP_CENTER_INDEX = 1;

    /** 备案资料下载 */
    @BeanAttrInfo(orderBy = 2, cnName = "备案资料下载", value = "http://help.cdnhost.cn/menu.php?id=99")
    public static final long DOWNLOAD_RECORD_FILES = 2;

    /** 各省通信管理局电话 */
    @BeanAttrInfo(orderBy = 3, cnName = "各省通信管理局电话", value = "http://help.cdnhost.cn/menu.php?id=136#art894")
    public static final long AUTHORITY_RECORD_PHONE_NUMBER = 3;

    /** 备案FAQ */
    @BeanAttrInfo(orderBy = 4, cnName = "备案问与答", value = "http://help.cdnhost.cn/menu.php?id=136")
    public static final long RECORD_FAQ = 4;

    /** 各省备案相关要求 */
    @BeanAttrInfo(orderBy = 5, cnName = "各省备案相关要求", value = "http://help.cdnhost.cn/menu.php?id=122")
    public static final long PROVINCE_RECORD_AUDIT = 5;

    /** 如何找回ICP备案密码？ */
    @BeanAttrInfo(orderBy = 6, cnName = "如何找回ICP备案密码？", value = "http://help.cdnhost.cn/menu.php?id=136#art793")
    public static final long HOW_FIND_ICP_RECORD_PASSWORD = 6;

}
