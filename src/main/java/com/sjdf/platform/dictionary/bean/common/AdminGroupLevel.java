package com.sjdf.platform.dictionary.bean.common;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.Dictionary;
import com.sjdf.platform.dictionary.bean.SystemType;
import com.sjdf.platform.dictionary.cache.ConfigManager;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.List;

/**
 * 超管组别
 * value:对应各系统中数据库表admin_group中的id
 * User: ketqi
 * Date: 2015-07-07 09:57
 */
@Entity
@DiscriminatorValue("ADMIN_GROUP_LEVEL")
@BeanName(name = "超管组别")
public class AdminGroupLevel extends Dictionary {
    private static final long serialVersionUID = -8262684057167306503L;

    @BeanAttrInfo(value = "5", cnName = "业务专员")
    public static final long BUSINESS_COMMISSIONER = 5;

    @BeanAttrInfo(value = "10", cnName = "工商专员", systemType = SystemType.SJLX_CMS)
    public static final long ICBC_COMMISSIONER = 10;

    @BeanAttrInfo(value = "20", cnName = "银行专员", systemType = SystemType.SJLX_CMS)
    public static final long BANK_COMMISSIONER = 20;

    @BeanAttrInfo(value = "30", cnName = "税务专员", systemType = SystemType.SJLX_CMS)
    public static final long TAX_COMMISSIONER = 30;

    @BeanAttrInfo(value = "40", cnName = "记账专员", systemType = SystemType.SJLX_CMS)
    public static final long ACCOUNT_COMMISSIONER = 40;

    /**
     * 根据当前系统类型获取超管组别
     */
    public static List<? extends Dictionary> getAdminGroupLevelList() {
        return ConfigManager.getInstance().getDictionaryList(AdminGroupLevel.class, SystemType.getCurrentSystemType());
    }
}
