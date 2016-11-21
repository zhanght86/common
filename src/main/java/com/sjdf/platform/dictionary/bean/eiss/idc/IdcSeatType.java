package com.sjdf.platform.dictionary.bean.eiss.idc;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.Dictionary;

/**
 * 机位型号配置
 * @date 2016年3月8日下午4:04:35
 * @author wangpeng
 */
@Entity
@DiscriminatorValue("IDC_SEAT_TYPE")
@BeanName(name = "IDC机位型号配置")
public class IdcSeatType extends Dictionary{

    private static final long serialVersionUID = 4703474710114997185L;

    /**
     * value ：表示该机位使用电力
     */
    @BeanAttrInfo(value = "0.6", cnName = "1U")
    public static final long ONE_U = 1;

    @BeanAttrInfo(value = "0.8", cnName = "2U")
    public static final long TWO_U = 2;

    @BeanAttrInfo(value = "1.2", cnName = "4U")
    public static final long FOUR_U = 4;

    @BeanAttrInfo(value = "2", cnName = "8U")
    public static final long EIGHT_U = 8;
}
