package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Create at 2012-05-22
 *
 * @author 王正伟
 * @category 返回代码常量
 */
@Entity
@DiscriminatorValue("CONST_RET_CODE")
@BeanName(name = "返回代码常量")
public class ConstRetCode extends Dictionary {
    private static final long serialVersionUID = -1008522076706030322L;

    /** 失败 */
    @BeanAttrInfo(value = "-1", cnName = "失败")
    public static final long FAILURE = -1;
    /** 成功 */
    @BeanAttrInfo(value = "0", cnName = "成功")
    public static final long SUCCESS = 0;
    /** 无法连接服务器 */
    @BeanAttrInfo(value = "1", cnName = "无法连接服务器")
    public static final long UNABLE_TO_CONNECT_SERVER = 1;
    /** 该接口不存在 */
    @BeanAttrInfo(value = "2", cnName = "该接口不存在")
    public static final long ERFACE_NOT_FOUND = 2;
    /** 数据备份失败 */
    @BeanAttrInfo(value = "3", cnName = "数据备份失败")
    public static final long BACKUP_DATA_FAILED = 3;
    /** 还原数据失败 */
    @BeanAttrInfo(value = "4", cnName = "还原数据失败")
    public static final long RESTORE_DATA_FAILED = 4;
    /** 数据库磁盘满 */
    @BeanAttrInfo(value = "5", cnName = "数据库磁盘满")
    public static final long NOT_ENOUGH_DISK_SPACE = 5;
    /** 进程存在 */
    @BeanAttrInfo(value = "6", cnName = "进程存在")
    public static final long PROGRESS_EXIST = 6;
    /** 进程不存在 */
    @BeanAttrInfo(value = "7", cnName = "进程不存在")
    public static final long PROGRESS_NOT_EXIST = 7;
    /** 金额不足 */
    @BeanAttrInfo(value = "8", cnName = "金额不足")
    public static final long AMOUNT_NOT_ENOUGH = 8;
    /** 信息更新失败 */
    @BeanAttrInfo(value = "9", cnName = "信息更新失败")
    public static final long UPDATE_FAILED = 9;
    /** 连接密码错误 */
    @BeanAttrInfo(value = "10", cnName = "连接密码错误")
    public static final long CONNECT_PASSWORD_ERROR = 10;
    /** 未知错误 */
    public static final long UNKNOWN_ERROR = 11;
    /** 数据库连接失败 */
    public static final long DATABASE_CONNECTION_FAILED = 12;
    /** 不能删除，有关联数据存在 */
    public static final long UNABLE_TO_DELETED_HAVE_RELATED_DATA_EXIST = 13;
    /** 数据库SQL语句执行错误 */
    public static final long SQL_ERROR = 14;
    /** 未知数据库异常 */
    @BeanAttrInfo(value = "15", cnName = "未知数据库异常")
    public static final long UNKNOWN_DATABASE_EXCEPTION = 15;
    /** 网站模版修改失败 */
    public static final long WEB_TEMPLATE_SET_FAILED = 16;
    /** 添加服务器列表失败 */
    public static final long ADD_SERVER_LIST_FAILED = 17;
    /** 修改服务器列表失败 */
    public static final long SET_SERVER_LIST_FAILED = 18;
    /** 删除服务器列表失败 */
    public static final long DEL_SERVER_LIST_FAILED = 19;
    /** 托管商管理添加失败 */
    public static final long ADD_PROVIDER_FAILED = 20;
    /** 托管商管理修改失败 */
    public static final long SET_PROVIDER_FAILED = 21;
    /** 托管商管理删除失败 */
    public static final long DEL_PROVIDER_FAILED = 22;
    /** 机房设置添加失败 */
    public static final long ADD_IDC_FAILED = 23;
    /** 机房设置修改失败 */
    public static final long SET_IDC_FAILED = 24;
    /** 机房设置删除失败 */
    public static final long DEL_IDC_FAILED = 25;
    /** 产品价格导入失败 */
    public static final long PRICE_IMPORT_FAILED = 26;
    /** 产品添加失败 */
    public static final long ADD_PRODUCT_FAILED = 27;
    /** 域名绑定删除失败 */
    @BeanAttrInfo(value = "28", cnName = "域名绑定删除失败 ")
    public static final long DEL_BINDINGS_FAILED = 28;
    /** 信息查找失败 */
    @BeanAttrInfo(value = "29", cnName = "信息查找失败")
    public static final long FIND_FAILED = 29;
    /** 信息添加失败 */
    @BeanAttrInfo(value = "30", cnName = "信息添加失败 ")
    public static final long ADD_FAILED = 30;
    /** 请做别名解析到：别名地址 */
    @BeanAttrInfo(value = "10001", cnName = "请做别名解析到：别名地址 ")
    public static final long INFO_ALIASNAME_DYN = 10001;
    /** 请做别名解析到：b.938030968.com */
    @BeanAttrInfo(value = "10002", cnName = "请做别名解析到：b.938030968.com")
    public static final long INFO_ALIASNAME_FIX = 10002;
    /** 域名绑定成功 */
    @BeanAttrInfo(value = "10003", cnName = "域名绑定成功")
    public static final long INFO_BIND_SUCCESS = 10003;
    /** 删除域名绑定成功 */
    @BeanAttrInfo(value = "10004", cnName = "删除域名绑定成功")
    public static final long INFO_UNBIND_SUCCESS = 10004;

    /** 云主机公共常量 */
    /** 产品分类已经存在 */
    @BeanAttrInfo(value = "10005", cnName = "产品分类已经存在 ")
    public static final long PRODUCT_CLASS_EXIST = 10005;
    /** 产品名称已经存在 */
    @BeanAttrInfo(value = "10006", cnName = "产品名称已经存在 ")
    public static final long PRODUCT_NAME_EXIST = 10006;
    /** 产品已存在购买记录，不能删除 */
    @BeanAttrInfo(value = "10007", cnName = "产品已存在购买记录，不能删除 ")
    public static final long PRODUCT_DELETE_HAS_BUYED = 10007;
    /** 添加子IP失败 */
    @BeanAttrInfo(value = "10008", cnName = "添加子IP失败 ")
    public static final long ADD_SUBIP_FAILED = 10008;
    /** 更新子IP失败 */
    @BeanAttrInfo(value = "10009", cnName = "更新子IP失败 ")
    public static final long UPDATE_SUBIP_FAILED = 10009;
    /** 删除子IP失败 */
    @BeanAttrInfo(value = "10010", cnName = "删除子IP失败 ")
    public static final long DEL_SUBIP_FAILED = 10010;
    /** 子IP已经存在 */
    @BeanAttrInfo(value = "10011", cnName = "子IP已经存在")
    public static final long SUBIP_HAS_EXIST = 10011;
    /** 主服务器已经被使用 */
    @BeanAttrInfo(value = "10012", cnName = "主服务器已经被使用")
    public static final long SUBIP_SERVER_HAS_USED = 10012;
    /** 该服务器已被共享IP使用 */
    @BeanAttrInfo(value = "10013", cnName = "该服务器已被共享IP使用")
    public static final long SUBIP_SERVER_USED_SHAREIP = 10013;
    /** 子IP存在购买记录 */
    @BeanAttrInfo(value = "10014", cnName = "子IP存在购买记录")
    public static final long SUBIP_HAS_BUYED = 10014;
    /** 操作系统名称已经存在 */
    @BeanAttrInfo(value = "10015", cnName = "操作系统名称已经存在 ")
    public static final long OS_NAME_HAS_EXIST = 10015;
    /** 操作系统已经存在购买记录 */
    @BeanAttrInfo(value = "10016", cnName = "操作系统已经存在购买记录")
    public static final long OS_HAS_BUYED = 10016;
    /** 域名注册成功 */
    @BeanAttrInfo(value = "11001", cnName = "域名注册成功")
    public static final long DOMAIN_REGISTER_SUCCEED = 11001;
    /** 域名注册失败 */
    @BeanAttrInfo(value = "11002", cnName = "域名注册失败")
    public static final long DOMAIN_REGISTER_FAILED = 11002;
    /** 域名已被注册 */
    @BeanAttrInfo(value = "11003", cnName = "域名已被注册")
    public static final long DOMAIN_REGISTERED = 11003;
    /** 域名续费成功 */
    @BeanAttrInfo(value = "11004", cnName = "域名续费成功")
    public static final long DOMAIN_RENEW_SUCCEED = 11004;
    /** 域名续费失败 */
    @BeanAttrInfo(value = "11005", cnName = "域名续费失败")
    public static final long DOMAIN_RENEW_FAILED = 11005;
    /** 域名赎回成功 */
    @BeanAttrInfo(value = "11006", cnName = "域名赎回成功")
    public static final long DOMAIN_RANSOM_SUCCEED = 11006;
    /** 域名赎回失败 */
    @BeanAttrInfo(value = "11007", cnName = "域名赎回失败")
    public static final long DOMAIN_RANSOM_FAILED = 11007;
    /** 域名转入请求成功 */
    @BeanAttrInfo(value = "11008", cnName = "域名转入请求成功")
    public static final long DOMAIN_TRANSFER_IN_REQUEST_SUCCEED = 11008;
    /** 域名转入请求失败 */
    @BeanAttrInfo(value = "11009", cnName = "域名转入请求失败")
    public static final long DOMAIN_TRANSFER_IN_REQUEST_FAILED = 11009;
    /** 域名转入成功 */
    @BeanAttrInfo(value = "11010", cnName = "域名转入成功")
    public static final long DOMAIN_TRANSFER_IN_SUCCEED = 11010;
    /** 域名转入失败，转出注册商拒绝转移。 */
    @BeanAttrInfo(value = "11011", cnName = "域名转入失败，转出注册商拒绝转移。")
    public static final long DOMAIN_TRANSFER_IN_FAILED = 11011;
    /** 获取转移密码成功 */
    @BeanAttrInfo(value = "11012", cnName = "转移密码（7天有效）已经发送到域名所有者邮箱，请到转入方申请转入。30天内未转出成功，系统自动废弃订单。")
    public static final long DOMAIN_TRANSFER_OUT_REQUEST_SUCCEED = 11012;
    /** 获取转移密码失败 */
    @BeanAttrInfo(value = "11013", cnName = "获取转移密码失败")
    public static final long DOMAIN_TRANSFER_OUT_REQUEST_FAILED = 11013;
    /** 域名转出成功 */
    @BeanAttrInfo(value = "11014", cnName = "域名转出成功")
    public static final long DOMAIN_TRANSFER_OUT_SUCCEED = 11014;
    /** 域名转出失败，拒绝转移。 */
    @BeanAttrInfo(value = "11015", cnName = "域名转出失败，拒绝转移。", enName = "因30天内未转出成功，该订单已废弃。")
    public static final long DOMAIN_TRANSFER_OUT_FAILED = 11015;
    /** 域名过户请求成功 */
    @BeanAttrInfo(value = "11016", cnName = "请通过域名所有者邮箱确认过户邮件。确认后，我司系统才会自动进行过户处理。")
    public static final long DOMAIN_TRANSFER_OWNER_REQUEST_SUCCEED = 11016;
    /** 域名过户请求失败 */
    @BeanAttrInfo(value = "11017", cnName = "域名过户请求失败")
    public static final long DOMAIN_TRANSFER_OWNER_REQUEST_FAILED = 11017;
    /** 域名过户成功 */
    @BeanAttrInfo(value = "11018", cnName = "域名过户成功", enName = "请到产品控制面板完善补充注册信息")
    public static final long DOMAIN_TRANSFER_OWNER_SUCCEED = 11018;
    /** 域名过户失败 */
    @BeanAttrInfo(value = "11019", cnName = "域名过户失败")
    public static final long DOMAIN_TRANSFER_OWNER_FAILED = 11019;
    /** 域名过户失败 */
    @BeanAttrInfo(value = "11020", cnName = "域名过户失败，请填写详细的过户信息", enName = "请点击【修改】按钮进行过户信息的填写")
    public static final long DOMAIN_TRANSFER_OWNER_INFO_ERROR = 11020;
    /** 获取ID失败 */
    @BeanAttrInfo(value = "4515", cnName = "获取ID失败")
    public static final long NOT_ENOUGH_NUM = 4515;

    @BeanAttrInfo(value = "35", cnName = "断网")
    public static final long BROKEN_NETWORK = 35;

    /*挂机宝回复时模板状态*/
    @BeanAttrInfo(value = "40", cnName = "1小时未重启重装")
    public static final long WORKORDER_REPLY = 40;
}
