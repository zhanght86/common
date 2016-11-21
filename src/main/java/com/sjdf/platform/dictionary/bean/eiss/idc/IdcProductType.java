package com.sjdf.platform.dictionary.bean.eiss.idc;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.common.utils.PlatformUtils;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.Dictionary;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.*;

/**
 * IDC产品类型
 *
 * @author wangpeng *
 * @date 2016年3月7日上午9:15:03
 */
@Entity
@DiscriminatorValue("IDC_PRODUCT_TYPE")
@BeanName(name = "IDC产品类型")
public class IdcProductType extends Dictionary {
    private static final long serialVersionUID = -7797141321043673436L;

    @BeanAttrInfo(value = "bw_quantity:10,bw_size:1000,ip:10,quantity:100", orderBy = 2, cnName = "服务器托管", enName = "server deposit")
    public static final long SERVER_DEPOSIT = 1;

    @BeanAttrInfo(value = "bw_quantity:10,bw_size:1000,ip:10,quantity:100", orderBy = 1, cnName = "服务器租用", enName = "server lease")
    public static final long SERVER_LEASE = 2;

    @BeanAttrInfo(value = "bw_quantity:10,bw_size:1000,ip:10,quantity:100", orderBy = 3, cnName = "机柜租用", enName = "cabinet lease")
    public static final long CABINET_LEASE = 3;

    @BeanAttrInfo(orderBy = 4, cnName = "硬件代购", enName = "hardware buy")
    public static final long HARDWARE_BUY = 4;

    @BeanAttrInfo(orderBy = 5, cnName = "设备托管", enName = "device deposit")
    public static final long DEVICE_DEPOSIT = 5;

    @BeanAttrInfo(orderBy = 6, cnName = "设备租用", enName = "device lease")
    public static final long DEVICE_LEASE = 6;

    @BeanAttrInfo(orderBy = 7, cnName = "增值业务", enName = "value added service")
    public static final long VALUE_ADDED_SERVICE = 7;


    private static final Set<Long> IDC_PRODUCT_TYPE_SET = new HashSet<>();

    static {
        IDC_PRODUCT_TYPE_SET.add(SERVER_DEPOSIT);
        IDC_PRODUCT_TYPE_SET.add(SERVER_LEASE);
        IDC_PRODUCT_TYPE_SET.add(CABINET_LEASE);
    }

    //idc产品列表
    public static Set<Long> idcProductType() {
        return IDC_PRODUCT_TYPE_SET;
    }

    //获取值map
    public Map<String, Long> getValueMap() {
        String value = getValue();
        if (!PlatformUtils.hasText(value)) {
            return Collections.emptyMap();
        }

        Map<String, String> mapStr = PlatformUtils.parse2Map(value);
        Map<String, Long> map = new HashMap<>();
        for (Map.Entry<String, String> entry : mapStr.entrySet()) {
            map.put(entry.getKey().trim(), Long.parseLong(entry.getValue().trim()));
        }
        return map;
    }

    //最大购买带宽数量限制
    public long getMaxBandwidthQuantity() {
        Map<String, Long> map = getValueMap();
        Long value = map.get("bw_quantity");
        return value == null ? 0L : value;
    }

    //最大购买带宽大小限制
    public long getMaxBandwidthSize() {
        Map<String, Long> map = getValueMap();
        Long value = map.get("bw_size");
        return value == null ? 0L : value;
    }

    //最大购买IP数量限制
    public long getMaxIpQuantity() {
        Map<String, Long> map = getValueMap();
        Long value = map.get("ip");
        return value == null ? 0L : value;
    }

    //最大购买数量限制
    public long getMaxBuyQuantity() {
        Map<String, Long> map = getValueMap();
        Long value = map.get("quantity");
        return value == null ? 0L : value;
    }
}
