package com.sjdf.platform.dictionary.bean.ers;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.Dictionary;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author sdjf
 * @category sjdf 备案系统管局数据处理结果代码表
 */
@Entity
@DiscriminatorValue("MIIBEIAN_DATA_PROCESS_MSG_CODE")
@BeanName(name = "备案系统管局数据处理结果代码表")
public class MiibeianDataProcessMsgCode extends Dictionary {

    /**
     * 序列化
     */
    private static final long serialVersionUID = -7264679736613530064L;

    /** 管局审核通过 */
    @BeanAttrInfo(value = "1001", cnName = "管局审核通过")
    public static final long APPROVED = 1001L;

    /** IP备案操作成功 */
    @BeanAttrInfo(value = "2998", cnName = "IP备案操作成功")
    public static final long IP_RECORD_OPERATION_SUCCESS = 2998L;
}
