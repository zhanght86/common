package com.sjdf.platform.dictionary.bean.ers;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.Dictionary;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author sjdf
 * @category 校验结果类型
 */
@Entity
@DiscriminatorValue("VERIFY_RES_TYPE")
@BeanName(name = "校验结果类型")
public class VerifyResType extends Dictionary {

    /**
     * 序列化
     */
    private static final long serialVersionUID = 3015711061314853296L;

    /** 校验成功 */
    @BeanAttrInfo(value = "0", cnName = "校验成功")
    public static final long SUCCESS = 0L;

    /** 校验失败 */
    @BeanAttrInfo(value = "1", cnName = "校验失败")
    public static final long FAIL = 1L;

}
