package com.sjdf.platform.dictionary.bean.eiss;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.common.utils.PlatformUtils;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.Dictionary;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Collections;
import java.util.List;

/**
 * 第三方登录帐号,cnName:用户名,enName:密码,value:机房id列表
 * User: ketqi
 * Date: 2016-10-11 10:31
 */
@Entity
@DiscriminatorValue("EISS_THIRD_CERTIFICATION")
@BeanName(name = "第三方登录帐号")
public class ThirdCertification extends Dictionary {
    @BeanAttrInfo(cnName = "7x24", enName = "123456", value = "")
    public static final long ACCOUNT_7X24 = 1L;

    public List<Long> getIdcIdList() {
        if (!PlatformUtils.hasText(getValue())) {
            return Collections.emptyList();
        }

        return PlatformUtils.parse2LongList(getValue());
    }
}
