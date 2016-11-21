package com.sjdf.platform.dictionary.bean.sjlx;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.common.utils.PlatformUtils;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.Dictionary;
import com.sjdf.platform.dictionary.cache.ConfigManager;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 附件类型
 * User: ketqi
 * Date: 2015-06-011 10:35
 */
@Entity
@DiscriminatorValue("SJLX_ATTACH_TYPE")
@BeanName(name = "sjlx-附件类型")
public class SjlxAttachmentType extends Dictionary {
    private static final long serialVersionUID = -2106071721766207457L;

    @BeanAttrInfo(value = "admin:true,user:false,yearMoth:false,needSms:true", cnName = "公司证件")
    public static final long COMPANY_ATTACH = 1;

    @BeanAttrInfo(value = "admin:true,user:false,yearMoth:true", cnName = "财务报表")
    public static final long FINANCE_ATTACH = 2;

    @BeanAttrInfo(value = "admin:true,user:false,yearMoth:true", cnName = "申报表")
    public static final long APLY_ATTACH = 3;

    @BeanAttrInfo(value = "admin:true,user:false,yearMoth:true", cnName = "交接单")
    public static final long HAND_ATTACH = 4;

    @BeanAttrInfo(value = "admin:true,user:true,yearMoth:true", cnName = "记账相关")
    public static final long CHARGEUP_ATTACH = 5;

    @BeanAttrInfo(value = "admin:true,user:false,yearMoth:false", cnName = "订单附件")
    public static final long ORDER_ATTACH = 6;

    /**
     * 附件大分类
     *
     * @return 附件大分类列表
     */
    public static List<? extends Dictionary> getAttachmentType() {
        return getAttachmentType(0L);
    }

    /**
     * 附件小分类
     *
     * @param refAttr 附件大分类属性值
     * @return 附件小分类列表
     */
    public static List<? extends Dictionary> getAttachmentType(long refAttr) {
        return ConfigManager.getInstance().getRefDictionary(SjlxAttachmentType.class, refAttr);
    }

    /**
     * 附件列表
     *
     * @return map
     */
    public static Map<Dictionary, List<Dictionary>> getListMap() {
        Map<Dictionary, List<Dictionary>> map = new HashMap<>();
        List<? extends Dictionary> list = ConfigManager.getInstance().getDictionary(SjlxAttachmentType.class);
        for (Dictionary d : list) {
            Dictionary ref = d.getRef();
            if (ref == null) {
                if (!map.containsKey(d)) {
                    map.put(d, new ArrayList<Dictionary>());
                }
            } else {
                List<Dictionary> dictionaryList = map.get(ref);
                if (dictionaryList == null) {
                    dictionaryList = new ArrayList<>();
                    dictionaryList.add(d);
                    map.put(d.getRef(), dictionaryList);
                } else {
                    map.get(d.getRef()).add(d);
                }
            }
        }
        return map;
    }

    /**
     * admin:true,user:false,yearMoth:false
     * 判断是否超管可以操作
     *
     * @return bool
     */
    public boolean isAdmin() {
        String val = getValue();
        if (PlatformUtils.hasText(val)) {
            Map<String, String> map = PlatformUtils.parse2Map(val);
            String v = map.get("admin");
            if (PlatformUtils.hasText(v)) {
                return Boolean.TRUE.toString().equals(v);
            }
        }
        return false;
    }

    /**
     * admin:true,user:false,yearMoth:false
     * 判断是否用户可以操作
     *
     * @return bool
     */
    public boolean isUser() {
        String val = getValue();
        if (PlatformUtils.hasText(val)) {
            Map<String, String> map = PlatformUtils.parse2Map(val);
            String v = map.get("user");
            if (PlatformUtils.hasText(v)) {
                return Boolean.TRUE.toString().equals(v);
            }
        }
        return false;
    }

    /**
     * admin:true,user:false,yearMoth:false
     * 判断是否需要年份和月份
     *
     * @return bool
     */
    public boolean isYearMoth() {
        String val = getValue();
        if (PlatformUtils.hasText(val)) {
            Map<String, String> map = PlatformUtils.parse2Map(val);
            String v = map.get("yearMoth");
            if (PlatformUtils.hasText(v)) {
                return Boolean.TRUE.toString().equals(v);
            }
        }
        return false;
    }

    /**
     * 判断是否需要短信
     *
     * @return bool
     */
    public boolean isNeedSms() {
        String val = getValue();
        if (PlatformUtils.hasText(val)) {
            Map<String, String> map = PlatformUtils.parse2Map(val);
            String v = map.get("needSms");
            if (PlatformUtils.hasText(v)) {
                return Boolean.TRUE.toString().equals(v);
            }
        }
        return false;
    }
}
