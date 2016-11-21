package com.sjdf.platform.dictionary.bean.eiss.idc;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.Dictionary;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * User: ketqi
 * Date: 2016-03-03 17:26
 * 当为raid0时，默认显示2块硬盘，可以继续添加，至少要有2块硬盘大小一致；
 * 当为raid1时，默认显示2块硬盘，不可以继续添加，2块硬盘大小一致；
 * 当为raid5时，默认显示3块硬盘，可以继续添加，至少要有3块硬盘大小一致，不能<3；
 * 当为raid10时，默认显示4块硬盘，可以继续添加，至少要有4块硬盘大小一致。
 * <p/>
 * value<0:表示等于
 * value>0:表示大于等于
 */
@Entity
@DiscriminatorValue("RAID_X_HARD_DISK_RELATION")
@BeanName(name = "RAID与硬盘数量关系映射")
public class RaidXHardDiskRelation extends Dictionary {
    private static final long serialVersionUID = -4784686047159184790L;

    @BeanAttrInfo(value = "2", cnName = "至少要有2块硬盘大小一致")
    public static final long RAID0 = 10;

    @BeanAttrInfo(value = "-2", cnName = "仅仅需要2块硬盘大小一致")
    public static final long RAID1 = 11;

    @BeanAttrInfo(value = "3", cnName = "至少要有3块硬盘大小一致")
    public static final long RAID5 = 15;

    @BeanAttrInfo(value = "4", cnName = "至少要有4块硬盘大小一致")
    public static final long RAID10 = 20;
}
