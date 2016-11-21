package com.sjdf.platform.dictionary.bean.sjlx;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.Dictionary;

/**
 * create in 2016年7月13日
 * @category 税务申报状态
 * @author wangpeng
 */
@Entity
@DiscriminatorValue("SJLX_TAX_DECLARE_STATUS")
@BeanName(name = "sjlx-税务申报状态")
public class TaxDeclareStatus extends Dictionary {

    private static final long serialVersionUID = -30070686219799847L;

    @BeanAttrInfo(cnName="网报完毕")
    public static final long NET_FINISHED = 1;

    @BeanAttrInfo(cnName="大厅申报完毕")
    public static final long HALL_FINISHED = 2;

    @BeanAttrInfo(cnName="未到申报期")
    public static final long NOT_PERIOD = 3;

    @BeanAttrInfo(cnName="不在服务期不申报")
    public static final long NOT_IN_SERVICE  = 4;
}
