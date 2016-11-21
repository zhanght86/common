package com.sjdf.platform.dictionary.bean.eiss.idc;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.Dictionary;

/**
 * idc审核角色
 * @date 2016年4月1日下午3:23:33
 * @author wangpeng
 */
@Entity
@DiscriminatorValue("IDC_ROLE")
@BeanName(name = "IDC审核角色")
public class IdcRole extends Dictionary{

    private static final long serialVersionUID = 1452201626594581484L;

    @BeanAttrInfo(cnName = "客户经理", value="业务部")
    public static final long CLIENT = 1;

    @BeanAttrInfo(cnName = "部门经理", value="业务部")
    public static final long DEPARTMENT = 2;

    @BeanAttrInfo(cnName = "业务中心", value="业务部")
    public static final long BUSINESS = 3;

    @BeanAttrInfo(cnName = "客服部门", value="客服部")
    public static final long CUSTOMER = 4;

    @BeanAttrInfo(cnName = "技术部门", value="技术部")
    public static final long TECHNICAL = 5;

    @BeanAttrInfo(cnName = "技术部门", value="技术部")
    public static final long REVIEW = 6;

    @BeanAttrInfo(cnName = "托管采购部门", value="采购部")
    public static final long DEPOSIT = 7;

    @BeanAttrInfo(cnName = "硬件采购部门", value="采购部")
    public static final long HARDWARE = 8;

    @BeanAttrInfo(cnName = "查看订单", value="超管")
    public static final long LIST_ALL = 9;

    @BeanAttrInfo(cnName = "废弃订单", value="废弃订单")
    public static final long DISCARD = 10;
}
