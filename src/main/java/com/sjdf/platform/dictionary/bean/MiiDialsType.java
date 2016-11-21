package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/***
 * Create at 2012-07-05
 *
 * @author Chen Mohan
 * @category 管局拨测类别
 */
@Entity
@DiscriminatorValue("MII_DIALS_TYPE")
@BeanName(name = "管局拨测类别")
public class MiiDialsType extends Dictionary {

    private static final long serialVersionUID = 5470245967372926296L;

    @BeanAttrInfo(value = "1", cnName = "管局拨测需要变更备案")
    public static final long CHANGE_INFO = 1;

    @BeanAttrInfo(value = "2", cnName = "管局拨测需要新增接入")
    public static final long NEW_ACCESS = 2;

    @BeanAttrInfo(value = "3", cnName = "备案信息不真实")
    public static final long RECORD_INFO_ERROR = 3;
}
