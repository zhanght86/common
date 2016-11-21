package com.sjdf.platform.dictionary.bean.hkidc;

import com.sjdf.platform.CommonPlatformConstant;
import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.common.utils.PlatformUtils;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.Dictionary;
import com.sjdf.platform.dictionary.cache.ConfigManager;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Calendar;

/**
 * hkidc接口访问限制
 * User: ketqi
 * Date: 2015-06-15 14:36
 */
@Entity
@DiscriminatorValue("HKIDC_API_ACCESS_RESTRICTION")
@BeanName(name = "hkidc-接口访问限制")
public class HkidcApiAccessRestriction extends Dictionary {
    private static final long serialVersionUID = -6240445286410171566L;

    @BeanAttrInfo(value = "1", cnName = "限制每天开始时间点")
    public static final long RESTRICTION_BEGIN = 1;

    @BeanAttrInfo(value = "5", cnName = "限制每天结束时间点")
    public static final long RESTRICTION_END = 2;

    @BeanAttrInfo(value = "100", cnName = "限制次数")
    public static final long RESTRICTION_COUNTER = 3;


    /** hkidc api 访问限制开始时间 */
    public static int accessRestrictionBegin() {
        String value = ConfigManager.getInstance().getValue(HkidcApiAccessRestriction.class, HkidcApiAccessRestriction.RESTRICTION_BEGIN);
        if (PlatformUtils.hasText(value)) {
            return Integer.parseInt(value);
        }
        return 1;
    }

    /** hkidc api 访问限制结束时间 */
    public static int accessRestrictionEnd() {
        String value = ConfigManager.getInstance().getValue(HkidcApiAccessRestriction.class, HkidcApiAccessRestriction.RESTRICTION_END);
        if (PlatformUtils.hasText(value)) {
            return Integer.parseInt(value);
        }
        return CommonPlatformConstant.LENGTH_5;
    }

    /** hkidc api 访问限制次数 */
    public static int accessRestrictionCounter() {
        String value = ConfigManager.getInstance().getValue(HkidcApiAccessRestriction.class, HkidcApiAccessRestriction.RESTRICTION_COUNTER);
        if (PlatformUtils.hasText(value)) {
            return Integer.parseInt(value);
        }
        return CommonPlatformConstant.LENGTH_100;
    }

    /**
     * 判断当前时间是否能访问
     *
     * @return bool
     */
    public static boolean canAccess() {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        return !(hour >= accessRestrictionBegin() && hour < accessRestrictionEnd());
    }
}
