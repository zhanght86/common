package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Create at 2012-10-29
 * <p/>
 * 白名单清理
 *
 * @author Chen Mohan
 */
@Entity
@DiscriminatorValue("WHITE_LIST_CLEAR")
@BeanName(name = "白名单清理")
public class WhiteListClear extends Dictionary {

    private static final long serialVersionUID = -7085843638808747675L;

    /** 解析连续x天不在我司从白名单删除 */
    @BeanAttrInfo(value = "7", cnName = "解析连续x天不在我司从白名单删除")
    public static final long DAYS = 1;
}
