package com.sjdf.platform.dictionary.bean.eiss;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.Dictionary;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("MAIL_STATUS")
@BeanName(name = "邮局状态")
public class MailStatusNew extends Dictionary {

    @BeanAttrInfo(cnName = "运行中")
    public static final long RUN = 1;

    @BeanAttrInfo(cnName = "暂停中")
    public static final long PAUSE = 2;

    @BeanAttrInfo(cnName = "处理中")
    public static final long PENDING = 3;

    @BeanAttrInfo(cnName = "待开通")
    public static final long STAY_CREATE = 4;

    @BeanAttrInfo(cnName = "未开通")
    public static final long NONE_CREATE = 5;

    @BeanAttrInfo(cnName = "系统停止")
    public static final long SYSTEM_STOP = 6;

    @BeanAttrInfo(cnName = "管理员停止")
    public static final long ADMIN_STOP = 7;

    @BeanAttrInfo(cnName = "过期停止")
    public static final long STOP_EXPIRED = 8;

    @BeanAttrInfo(cnName = "过期停止升级")
    public static final long STOP_EXPIRED_UPGRADE = 9;

}
