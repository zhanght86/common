package com.sjdf.platform.dictionary.bean.eiss.idc;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.Dictionary;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * User: ketqi
 * Date: 2016-09-05 14:22
 */
@Entity
@DiscriminatorValue("IDC_STORAGE_STATUS")
@BeanName(name = "IDC 入库状态")
public class IdcStorageStatus extends Dictionary {
    private static final long serialVersionUID = 583747823852048890L;

    @BeanAttrInfo(cnName = "已入库")
    public static final long STORAGED = 1L;

    @BeanAttrInfo(cnName = "未入库")
    public static final long UN_STORAGED = 5L;
}
