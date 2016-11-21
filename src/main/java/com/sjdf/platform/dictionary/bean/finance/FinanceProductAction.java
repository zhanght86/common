package com.sjdf.platform.dictionary.bean.finance;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.common.utils.PlatformUtils;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.Dictionary;
import com.sjdf.platform.dictionary.bean.ProductAction;
import com.sjdf.platform.dictionary.cache.ConfigManager;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.*;

/**
 * 财务系统产品动作
 * User: ketqi
 * Date: 2014-12-08 16:42
 */
@Entity
@DiscriminatorValue("FINANCE_PRODUCT_ACTION")
@BeanName(name = "产品动作")
public class FinanceProductAction extends Dictionary {
    private static final long serialVersionUID = 6395658488195632370L;
    private static Map<Long, List<FinanceProductAction>> cache;

    /**
     * 虚拟主机:1
     * 邮局:2
     * 数据库:3
     * 域名:5
     * IDC:6
     * 云主机:7
     * 微信:8
     * 建站超市:97
     * 外部邮局:98
     * 其他:99
     */
    /** 购买 */
    @BeanAttrInfo(value = "1,2,3,5,6,7,8,98,200", cnName = "购买", orderBy = 1)
    public static final long BUY = ProductAction.BUY;

    /** 续费 */
    @BeanAttrInfo(value = "1,2,3,5,6,7,8,98,200", cnName = "续费", orderBy = 2)
    public static final long RENEW = ProductAction.RENEW;

    /** 升级 */
    @BeanAttrInfo(value = "1,2,3,7,8,98", cnName = "升级", orderBy = 3)
    public static final long UPGRADE = ProductAction.UPGRADE;

    /** 转入 */
    @BeanAttrInfo(value = "5", cnName = "转入", orderBy = 4)
    public static final long IN = ProductAction.IN;

    /** 转出 */
    @BeanAttrInfo(value = "5", cnName = "转出", orderBy = 5)
    public static final long OUT = ProductAction.OUT;

    /** 过户 */
    @BeanAttrInfo(value = "5", cnName = "过户", orderBy = 6)
    public static final long OWNER = ProductAction.OWNER;

    /** 赎回 */
    @BeanAttrInfo(value = "5", cnName = "赎回", orderBy = 7)
    public static final long REDEEM = 7;

    /** 增加流量 */
    @BeanAttrInfo(value = "1", cnName = "增加流量", orderBy = 8)
    public static final long ADD_FLOW = 8;

    /** 增加域名 */
    @BeanAttrInfo(value = "97", cnName = "增加域名", enName = "模板编号:\n授权域名:", orderBy = 9)
    public static final long ADD_DOMIN = 9;

    /** 增加带宽 */
    @BeanAttrInfo(value = "7", cnName = "增加带宽", orderBy = 10)
    public static final long ADD_BANDWIDTH = 10;

    /** 更换域名 */
    @BeanAttrInfo(value = "97", cnName = "更换域名", enName = "模板编号:\n原授权域名:\n新授权域名:", orderBy = 11)
    public static final long CHANGE_DOMAIN = 11;

    /** 更换模板 */
    @BeanAttrInfo(value = "97", cnName = "更换模板", enName = "授权域名:\n原模板编号:\n新模板编号:", orderBy = 12)
    public static final long CHANGE_TEMPLATE = 12;

    /** 增加模块 */
    @BeanAttrInfo(value = "97", cnName = "增加模块", enName = "授权域名:\n模板名称:", orderBy = 13)
    public static final long ADD_EMPLATE = 13;

    /** 增加应用 */
    @BeanAttrInfo(value = "97", cnName = "增加应用", enName = "授权域名:\n应用名称:", orderBy = 14)
    public static final long ADD_APP = 14;

    /** 授权模板 */
    @BeanAttrInfo(value = "97", cnName = "授权模板", enName = "模板编号:\n授权域名:\n", orderBy = 15)
    public static final long AUTHORIZE_EMPLATE = 15;

    /** 快递费 */
    @BeanAttrInfo(value = "99", cnName = "快递费", enName = "", orderBy = 8)
    public static final long EXPRESS_FEE = 95;

    /** 手续费 */
    @BeanAttrInfo(value = "1,2,3,6,7,8,97,98,99", cnName = "手续费", enName = "", orderBy = 8)
    public static final long SHOU_XU_FEI = 96;

    /** 税点 */
    @BeanAttrInfo(value = "99", cnName = "税点", enName = "", orderBy = 8)
    public static final long TAX_POINTS = 97;

    /** 通用 */
    @BeanAttrInfo(cnName = "通用", enName = "", orderBy = 9)
    public static final long UNIVERSAL = 98;

    /** 其他 */
    @BeanAttrInfo(value = "1,2,3,5,6,7,8,97,98,99", cnName = "其他", enName = "", orderBy = 20)
    public static final long OTHER = 99;

    @SuppressWarnings(value = "unchecked")
    public static List<FinanceProductAction> getList(long financeProductClass) {
        if (cache == null) {
            synchronized (FinanceProductAction.class) {
                if (cache == null) {
                    Map<Long, List<FinanceProductAction>> cache = new HashMap<>();
                    List<FinanceProductAction> list = (List<FinanceProductAction>) ConfigManager.getInstance().getDictionary(FinanceProductAction.class);
                    for (FinanceProductAction d : list) {
                        if (!PlatformUtils.hasText(d.getValue())) {
                            continue;
                        }

                        for (Long cls : PlatformUtils.parse2LongList(d.getValue())) {
                            List<FinanceProductAction> actionList = cache.get(cls);
                            if (actionList == null) {
                                actionList = new ArrayList<>();
                                cache.put(cls, actionList);
                            }
                            actionList.add(d);
                        }
                    }
                    FinanceProductAction.cache = cache;
                }
            }
        }

        List<FinanceProductAction> list = cache.get(financeProductClass);
        if (list == null) {
            list = Collections.emptyList();
        }
        return list;
    }

    /** 产品动作转换为续费 */
    public static long convert2Renew(long productAction) {
        if (productAction == RENEW || productAction == IN || productAction == UNIVERSAL) {
            return RENEW;
        }
        return productAction;
    }
}
