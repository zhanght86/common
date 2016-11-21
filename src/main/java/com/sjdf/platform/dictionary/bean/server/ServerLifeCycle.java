package com.sjdf.platform.dictionary.bean.server;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.Dictionary;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * 2015-08-25
 * 详细说明：
 * value:表示该状态可能的后续状态
 * cnName:表示该状态的中文名称
 *
 * @author wangpeng
 * @category 服务器状态配置库
 */
@Entity
@DiscriminatorValue("SERVER_LIFE_CYCLE")
@BeanName(name = "服务器生命周期")
public class ServerLifeCycle extends Dictionary {

    private static final long serialVersionUID = -2564201481552421368L;

    /**
     * @category 分配用途/业务IP/主机名
     */
    @BeanAttrInfo(value = "2", cnName = "待分配用途")
    public static final long PENDING_ASSIGN_USE = 1;

    /**
     * @category 硬件检查/环境部署/基础数据、模板同步
     */
    @BeanAttrInfo(value = "3", cnName = "待初始化环境")
    public static final long PENDING_INIT_ENVIRONMENT = 2;

    /**
     * @category 测试验证环境是否可用
     */
    @BeanAttrInfo(value = "4", cnName = "待验证测试")
    public static final long PENDING_TEST = 3;

    @BeanAttrInfo(value = "5,6", cnName = "已上线")
    public static final long ONLINE = 4;

    /**
     * @category 机器坏了，宕机了之类
     */
    @BeanAttrInfo(value = "2,4", cnName = "维护中")
    public static final long MAINTENANCE = 5;

    /**
     * @category 客户通知/数据迁移
     */
    @BeanAttrInfo(value = "4,7", cnName = "待清理产品")
    public static final long PENDING_CLEAR_PRODUCT = 6;

    /**
     * @category 回收ip/注销解析/销毁数据
     */
    @BeanAttrInfo(value = "8", cnName = "待回收资源")
    public static final long PENDING_RECYCLING_RES = 7;

    /**
     * @category 断电下架/更改服务器状态
     */
    @BeanAttrInfo(value = "9", cnName = "待机房下架")
    public static final long PENDING_OFF_LINE = 8;

    @BeanAttrInfo(value = "", cnName = "已下线")
    public static final long OFFLINE = 9;
}
