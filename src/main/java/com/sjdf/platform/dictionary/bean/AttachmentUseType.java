package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author frank
 * @author 刘敏
 * @category 附件用途类型
 */
@Entity
@DiscriminatorValue("ATTACHMENT_USE_TYPE")
@BeanName(name = "附件用途类型")
public class AttachmentUseType extends Dictionary {

    private static final long serialVersionUID = 2882157675429724168L;

    /** 普通附件 */
    @BeanAttrInfo(orderBy = 1, cnName = "普通附件")
    public static final long ATTACHMENT_USE_TYPE_ORDINARY_ATTACHMENT = 1;

    /** 前置审批 */
    @BeanAttrInfo(orderBy = 2, cnName = "前置审批")
    public static final long ATTACHMENT_USE_TYPE_PRE_APPROVAL_ATTACHMENT = 2;

}
