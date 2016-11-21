package com.sjdf.platform.dictionary.bean.eiss.chost;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.Dictionary;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * User: ketqi
 * Date: 2016-08-10 16:14
 * 数据库类型
 */
@Entity
@DiscriminatorValue("CHOST_TRANSFER_STATUS")
@BeanName(name = "云主机迁移状态")
public class ChostTransferStatus extends Dictionary {
    private static final long serialVersionUID = -4627033314685970075L;

    /** 迁移失败 */
    @BeanAttrInfo(cnName = "迁移失败")
    public static final long TRANSFER_FAIL = 1L;

    /** 排队中 */
    @BeanAttrInfo(cnName = "排队中")
    public static final long TRANSFER_ING = 5L;

    /** 安装中 */
    @BeanAttrInfo(cnName = "安装中")
    public static final long TRANSFER_INSTALLING = 10L;

    /** 迁移成功 */
    @BeanAttrInfo(cnName = "迁移成功")
    public static final long TRANSFER_SUCCESS = 15L;
}
