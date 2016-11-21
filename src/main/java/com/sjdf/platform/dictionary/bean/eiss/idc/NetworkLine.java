package com.sjdf.platform.dictionary.bean.eiss.idc;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.common.utils.PlatformUtils;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.Dictionary;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Map;

/**
 * User: ketqi
 * Date: 2016-03-04 13:19
 */
@Entity
@DiscriminatorValue("NETWORK_LINE")
@BeanName(name = "线路")
public class NetworkLine extends Dictionary {
    private static final long serialVersionUID = -7021343790561343388L;

    @BeanAttrInfo(orderBy = 4, cnName = "双线（电信，联通）", enName = "双线", value = "")
    public static final long DOUBLE_LINE = 1;

    @BeanAttrInfo(orderBy = 1, cnName = "电信", enName = "电信", value = "share:10")
    public static final long TELECOMMUNICATIONS = 2;

    @BeanAttrInfo(orderBy = 5, cnName = "BGP（双线/多线）", enName = "BGP", value = "share:5")
    public static final long BGP = 3;

    @BeanAttrInfo(orderBy = 6, cnName = "海外", enName = "海外", value = "share:10")
    public static final long FOREIGN = 4;

    @BeanAttrInfo(orderBy = 2, cnName = "联通", enName = "联通", value = "share:10")
    public static final long UNICOM = 5;

    @BeanAttrInfo(orderBy = 3, cnName = "移动", enName = "移动", value = "share:10")
    public static final long MOBILE = 25;

    /** 获取共享带宽大小限制,如果为0表示此线路不存在共享模式 */
    public long getShareSizeLimit() {
        Map<String, String> map = PlatformUtils.parse2Map(getValue());
        String val = map.get("share");
        if (PlatformUtils.hasText(val)) {
            return PlatformUtils.parse2Long(val);
        }
        return 0L;
    }
}
