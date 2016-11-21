package com.sjdf.platform.dictionary.bean.sjlx;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.Dictionary;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("SJLX_COMPANY_STATUS")
@BeanName(name = "sjlx-公司状态")
public class SjlxCompanyStatus extends Dictionary {
    private static final long serialVersionUID = -2748509552824428976L;

    /** 已注册公司 */
    @BeanAttrInfo(cnName = "已注册公司")
    public static final long REGISTERED = 1;
    /** 新办企业 */
    @BeanAttrInfo(cnName = "新办企业")
    public static final long NEWCOMPANY = 2;
}
