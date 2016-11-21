package com.sjdf.platform.dictionary.bean.ers;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.Dictionary;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author sjdf
 * @category ICP备案通知信息类别
 */
@Entity
@DiscriminatorValue("RECORD_ICP_INFORM_TYPE")
@BeanName(name = "ICP备案通知信息类别")
public class RecordIcpInformType extends Dictionary {

    /**
     * 序列化
     */
    private static final long serialVersionUID = -6716708643486605346L;
    /**
     * @category 备案主体信息整改
     */
    @BeanAttrInfo(orderBy = 0, cnName = "备案主体信息整改")
    public static final long MAIN = 0L;
    /**
     * @category 备案网站信息整改
     */
    @BeanAttrInfo(orderBy = 1, cnName = "备案网站信息整改")
    public static final long WEB = 1L;

}
