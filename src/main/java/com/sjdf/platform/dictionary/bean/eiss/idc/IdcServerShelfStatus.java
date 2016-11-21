package com.sjdf.platform.dictionary.bean.eiss.idc;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.Dictionary;

/**
 * 服务器上下架状态
 * @date 2016年5月10日下午4:04:35
 * @author wangpeng
 */
@Entity
@DiscriminatorValue("IDC_SERVER_SHELF_STATUS")
@BeanName(name = "IDC服务器上下架状态")
public class IdcServerShelfStatus extends Dictionary{

    private static final long serialVersionUID = -1498190356975570284L;

    @BeanAttrInfo(cnName = "未上架")
    public static final long NOT = 1;

    @BeanAttrInfo(cnName = "上架中")
    public static final long SHELVING = 2;

    @BeanAttrInfo(cnName = "已上架")
    public static final long SHELVED = 3;

    @BeanAttrInfo(cnName = "下架中")
    public static final long REMOVING = 4;

    @BeanAttrInfo(cnName = "已下架")
    public static final long REMOVED = 5;
}
