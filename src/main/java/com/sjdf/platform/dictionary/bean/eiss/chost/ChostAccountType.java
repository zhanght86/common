package com.sjdf.platform.dictionary.bean.eiss.chost;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.Dictionary;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author Hunk
 * @category 云主机账户类型ADSL-VPN
 */
@Entity
@DiscriminatorValue("CHOST_ACCOUNT_TYPE")
@BeanName(name = "云主机账户类型ADSL-VPN")
public class ChostAccountType extends Dictionary {

    private static final long serialVersionUID = -4627033314685970075L;

    /** 云主机账户类型VPN */
    @BeanAttrInfo(value = "1", orderBy = 1, cnName = "VPN", enName = "vpn")
    public static final long VPN = 1;

    /** 云主机账户类型ADSL */
    @BeanAttrInfo(value = "2", orderBy = 2, cnName = "ADSL", enName = "adsl")
    public static final long ADSL = 2;
}
