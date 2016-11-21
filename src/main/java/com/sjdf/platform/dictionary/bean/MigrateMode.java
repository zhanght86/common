package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Create at 2012-12-14
 *
 * @author 邱忠福
 * @category 云主机迁移模式
 */
@Entity
@DiscriminatorValue("MIGRATE_MODE")
@BeanName(name = "迁移模式")
public class MigrateMode extends Dictionary {

    private static final long serialVersionUID = 5063240027259413848L;

    /** 低速模式 */
    @BeanAttrInfo(value = "d", cnName = "低速模式")
    public static final long LOW_SPEED_MODE = 1;

    /** 高速模式 */
    @BeanAttrInfo(value = "n", cnName = "高速模式")
    public static final long HIGH_SPEED_MODE = 2;

    /** 自动切换速度模式 */
    @BeanAttrInfo(value = "a", cnName = "自动切换速度模式")
    public static final long AUTO_CHANGE_SPEED_MODE = 3;

    /** 暂停迁移 */
    @BeanAttrInfo(value = "s", cnName = "暂停迁移")
    public static final long PAUSE_MIGRATE = 4;

    /** 撤销迁移 */
    @BeanAttrInfo(value = "c", cnName = "撤销迁移")
    public static final long CANCEL_MIGRATE = 5;

    /** 查询迁移状态 */
    @BeanAttrInfo(value = "q", cnName = "查询迁移状态")
    public static final long LOOK_UP_MIGRATE_STATUS = 6;

}
