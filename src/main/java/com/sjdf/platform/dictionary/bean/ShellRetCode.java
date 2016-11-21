package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * create at 2012-06-07
 *
 * @author 陈绍刚
 * @category SHELL命令返回代码
 */

@Entity
@DiscriminatorValue("SHELL_RET_CODE")
@BeanName(name = "SHELL命令返回代码")
public class ShellRetCode extends Dictionary {

    private static final long serialVersionUID = -515997150905512923L;
    /** 执行成功 */
    @BeanAttrInfo(value = "0", cnName = "执行成功")
    public static final long SUCCESS = 0;
    /** 执行失败 */
    @BeanAttrInfo(value = "-1", cnName = "执行失败 ")
    public static final long RUN_SHELL_ERROR = -1;
    /** 没有传入参数 */
    @BeanAttrInfo(value = "65", cnName = "没有传入参数")
    public static final long NOARGS = 65;
    /** 传入参数错误 */
    @BeanAttrInfo(value = "66", cnName = "传入参数错误")
    public static final long BADARGS = 66;
    /** 参数不不足 */
    @BeanAttrInfo(value = "67", cnName = "参数不不足")
    public static final long NOEARGS = 67;
    /** 系统繁忙 */
    @BeanAttrInfo(value = "12", cnName = "系统繁忙")
    public static final long ISBUSY = 12;
    /** 错误的系统类型 */
    @BeanAttrInfo(value = "13", cnName = "错误的系统类型")
    public static final long BADOSTYPE = 13;
    /** 网络错误 */
    @BeanAttrInfo(value = "14", cnName = "网络错误")
    public static final long NETWORKERROR = 14;
    /** 云主机已经存在 */
    @BeanAttrInfo(value = "22", cnName = "云主机已经存在")
    public static final long VMEXISTS = 22;
    /** 云主机不存在 */
    @BeanAttrInfo(value = "23", cnName = "云主机不存在")
    public static final long VMNOTEXISTS = 23;
    /** 云主机处于运行状态 */
    @BeanAttrInfo(value = "24", cnName = "云主机处于运行状态")
    public static final long VMRUNNING = 24;
    /** 云主机处于暂停状态 */
    @BeanAttrInfo(value = "25", cnName = "云主机处于暂停状态")
    public static final long VMPAUSED = 25;
    /** 云主机处于关闭状态 */
    @BeanAttrInfo(value = "26", cnName = "云主机处于关闭状态")
    public static final long VMSHUTOFF = 26;
    /** 云主机正在安装中 */
    @BeanAttrInfo(value = "27", cnName = "云主机处于安装中状态")
    public static final long VMINSTALLING = 27;
    /** 没有存储池 */
    @BeanAttrInfo(value = "32", cnName = "没有存储池")
    public static final long NOVGS = 32;
    /** 卷已存在 */
    @BeanAttrInfo(value = "33", cnName = "卷已存在")
    public static final long LVEXISTS = 33;
    /** 磁盘没有剩余空间可用 */
    @BeanAttrInfo(value = "34", cnName = "磁盘没有剩余空间可用")
    public static final long NOFREESPACE = 34;
    /** 没有XML模板文件 */
    @BeanAttrInfo(value = "35", cnName = "没有XML模板文件")
    public static final long NOXMLTPL = 35;
    /** 没有磁盘模板 */
    @BeanAttrInfo(value = "36", cnName = "没有磁盘模板")
    public static final long NOFSTPL = 36;
    /** XML模板有错误 */
    @BeanAttrInfo(value = "37", cnName = "XML模板有错误")
    public static final long XMLERROR = 37;
    /** 云主机名有误 */
    @BeanAttrInfo(value = "42", cnName = "云主机名有误")
    public static final long NAMEERROR = 42;
    /** 磁盘大小有误 */
    @BeanAttrInfo(value = "43", cnName = "磁盘大小有误")
    public static final long DATASIZEERROR = 43;
    /** IP地址类型有误 */
    @BeanAttrInfo(value = "44", cnName = "IP地址类型有误")
    public static final long IPTYPEERROR = 44;
    /** 带宽有误 */
    @BeanAttrInfo(value = "45", cnName = "带宽有误")
    public static final long BWERROR = 45;
    /** 端口已占用 */
    @BeanAttrInfo(value = "46", cnName = "端口已占用")
    public static final long PORTINUSE = 46;
    /** 正在迁移 */
    @BeanAttrInfo(value = "47", cnName = "正在迁移")
    public static final long MIGRATE_DOING = 47;
    /** 暂停迁移 */
    @BeanAttrInfo(value = "48", cnName = "暂停迁移")
    public static final long MIGRATE_PAUSE = 48;
    /** 撤销迁移 */
    @BeanAttrInfo(value = "49", cnName = "撤销迁移")
    public static final long MIGRATE_CANCEL = 49;
    /** 迁移失败 */
    @BeanAttrInfo(value = "41", cnName = "迁移失败")
    public static final long MIGRATE_FAIL = 41;
    /** 没有多余的快照可用 */
    @BeanAttrInfo(value = "50", cnName = "没有多余的快照可用")
    public static final long NOSNAP = 50;
    /** 运行盘中的快照数与备份盘中的快照数不一致 */
    @BeanAttrInfo(value = "51", cnName = "运行盘中的快照数与备份盘中的快照数不一致")
    public static final long NESNAP = 51;
    /** 运行盘中的快照列表数与备份盘中的快照列表数不一致 */
    @BeanAttrInfo(value = "52", cnName = "运行盘中的快照列表数与备份盘中的快照列表数不一致")
    public static final long NECONF = 52;
    /** 合并意外中断 */
    @BeanAttrInfo(value = "53", cnName = "合并意外中断")
    public static final long MGSTOP = 53;
    /** 合并运行盘中的系统快照失败 */
    @BeanAttrInfo(value = "54", cnName = "合并运行盘中的系统快照失败")
    public static final long MGROOT = 54;
    /** 合并备份盘中的系统快照失败 */
    @BeanAttrInfo(value = "55", cnName = "合并备份盘中的系统快照失败")
    public static final long MGBKROOT = 55;
    /** 合并运行盘中的数据快照失败 */
    @BeanAttrInfo(value = "56", cnName = "合并运行盘中的数据快照失败")
    public static final long MGDATA = 56;
    /** 合并备份盘中的数据快照失败 */
    @BeanAttrInfo(value = "57", cnName = "合并备份盘中的数据快照失败")
    public static final long MGBKDATA = 57;
    /** 云主机已经创建了快照 */
    @BeanAttrInfo(value = "58", cnName = "云主机已经创建了快照")
    public static final long NAPEXIST = 58;
    /** 运行中的云主机创建快照失败 */
    @BeanAttrInfo(value = "59", cnName = "运行中的云主机创建快照失败")
    public static final long RUNSNERROR = 59;
    /** 停止中的云主机系统盘快照创建失败 */
    @BeanAttrInfo(value = "60", cnName = "停止中的云主机系统盘快照创建失败")
    public static final long SNAPROOT = 60;
    /** 停止中的云主机数据盘快照创建失败 */
    @BeanAttrInfo(value = "61", cnName = "停止中的云主机数据盘快照创建失败")
    public static final long SNAPDATA = 61;
    /** 不能为运行中的云主机做还原或撤销操作 */
    @BeanAttrInfo(value = "62", cnName = "不能为运行中的云主机做还原或撤销操作")
    public static final long RNSNERRO = 62;
    /** 未产生还原事件，故不能做确定与撤销操作 */
    @BeanAttrInfo(value = "63", cnName = "未产生还原事件，故不能做确定与撤销操作")
    public static final long NORESN = 63;
    /** 云主机处理预览状态时引发的操作快照错误 */
    @BeanAttrInfo(value = "64", cnName = "云主机处理预览状态时引发的操作快照错误")
    public static final long VIWESNERROR = 64;
    /** 云主机域名初始化不能添加白名单 */
    @BeanAttrInfo(value = "68", cnName = "云主机IP处于免白名单状态不能添加白名单")
    public static final long DOMNOLONG = 68;
    /** 域名已经在白名单中 */
    @BeanAttrInfo(value = "69", cnName = "域名已经在白名单中")
    public static final long DOMEXIST = 69;
    /** 域名不存在 */
    @BeanAttrInfo(value = "70", cnName = "域名不存在")
    public static final long DOMNOEXIST = 70;
    /** FTP创建用户失败 */
    @BeanAttrInfo(value = "71", cnName = "FTP创建用户失败")
    public static final long UERRO = 71;
    /** 修改FTP用户密码失败 */
    @BeanAttrInfo(value = "72", cnName = "修改FTP用户密码失败")
    public static final long RPWDERRO = 72;
    /** FTP用户已经存在 */
    @BeanAttrInfo(value = "73", cnName = "FTP用户已经存在")
    public static final long EREXIST = 73;
    /** 设置用户配额失败 */
    @BeanAttrInfo(value = "74", cnName = "设置用户配额失败")
    public static final long SETQUOTAERRO = 74;
    /** 被删除的用户不存在 */
    @BeanAttrInfo(value = "75", cnName = "被删除的用户不存在")
    public static final long USERNOTEXIST = 75;
    /** 删除用户失败 */
    @BeanAttrInfo(value = "76", cnName = "删除用户失败")
    public static final long DELUSERERRO = 76;
    /** 云主机没有备份 */
    @BeanAttrInfo(value = "77", cnName = "云主机没有备份 ")
    public static final long VMNOBACK = 77;
    /** 重新定义云主机失败 */
    @BeanAttrInfo(value = "78", cnName = "重新定义云主机失败 ")
    public static final long REDEFINFAIL = 78;
    /** 修改云主机IP失败 */
    @BeanAttrInfo(value = "79", cnName = "修改云主机IP失败 ")
    public static final long CHANGEIPFAIL = 79;
    /** CPU限制 */
    @BeanAttrInfo(value = "80", cnName = "CPU限制 ")
    public static final long CPU_LIMIT = 80;
    /** CPU撤销限制 */
    @BeanAttrInfo(value = "81", cnName = "CPU撤销限制 ")
    public static final long CPU_REVOCATE = 81;
    /** CPU已经被限制 */
    @BeanAttrInfo(value = "82", cnName = "CPU已经被限制 ")
    public static final long CPU_HAS_LIMITD = 82;
}
