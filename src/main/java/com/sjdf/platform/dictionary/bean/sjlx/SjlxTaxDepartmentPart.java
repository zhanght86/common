package com.sjdf.platform.dictionary.bean.sjlx;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.Dictionary;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * 税务机关部门
 * User: ketqi
 * Date: 2015-07-27 14:14
 */
@Entity
@DiscriminatorValue("SJLX_TAX_DEPARTMENT_PART")
@BeanName(name = "sjlx-地方税务机关部门")
public class SjlxTaxDepartmentPart extends Dictionary {
    private static final long serialVersionUID = -8045427144198647550L;

    /** 地税局第九税务所 */
    @BeanAttrInfo(value = "地税局第九税务所", cnName = "地税局第九税务所")
    public static final long DEPARTMENT_1 = 1L;
}
