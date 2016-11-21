package com.sjdf.platform.dictionary.bean.eiss;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.Dictionary;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author wangpeng
 * @category 添加修改服务器列表时的验证
 */
@Entity
@DiscriminatorValue("PUB_SERVER_VALIDATE")
@BeanName(name = "服务器列表验证信息")
public class PubServerValidate extends Dictionary {

    private static final long serialVersionUID = 7074234724538598561L;

    @BeanAttrInfo(value = "{\r\n\"机器型号\":\"R720/R720-XD/R410\",\r\n\"机器高度\":\"1U/2U\",\r\n\"CPU信息\":{\"CPU型号\":\"E5645\",\"核数\":4,\"线程\":12,\"CPU个数\":2},\r\n\"内存信息\":{\"内存型号\":\"1033\",\"每条大小\":\"16G\",\"条数\":8},\r\n\"运行盘信息\":{\"磁盘类别\":\"SAS/SSD/SATA\",\"磁盘大小\":\"600G\",\"磁盘个数\":6,\"阵列类型\":\"R5\"},\r\n\"备份盘信息\":{\"磁盘类别\":\"SAS/SSD/SATA\",\"磁盘大小\":\"600G\",\"磁盘个数\":6,\"阵列类型\":\"R5\"},\r\n\"购买时间\":\"2013-09-11\",\r\n\"过保时间\":\"2015-09-11\",\r\n\"机柜号\":\"101\",\r\n\"机柜内位置编号\":\"11\",\r\n\"操作系统版本\":\"centos6.4\",\r\n\"快速服务代码\":\"XXXXXXX\",\r\n\"其他信息\":\"除了其他信息这项外，其他项必须填写\"\r\n}", cnName = "服务器配置示例")
    public static final long CONFIGURATION_EXAMPLE = 10;

    @BeanAttrInfo(value = "{\r\n\"机器型号\":\"\",\r\n\"机器高度\":\"\",\r\n\"CPU信息\":{\"CPU型号\":\"\",\"核数\":\"\",\"线程\":\"\",\"CPU个数\":\"\"},\r\n\"内存信息\":{\"内存型号\":\"\",\"每条大小\":\"\",\"条数\":\"\"},\r\n\"运行盘信息\":{\"磁盘类别\":\"\",\"磁盘大小\":\"\",\"磁盘个数\":\"\",\"阵列类型\":\"\"},\r\n\"备份盘信息\":{\"磁盘类别\":\"\",\"磁盘大小\":\"\",\"磁盘个数\":\"\",\"阵列类型\":\"\"},\r\n\"购买时间\":\"\",\r\n\"过保时间\":\"\",\r\n\"机柜号\":\"\",\r\n\"机柜内位置编号\":\"\",\r\n\"操作系统版本\":\"\",\r\n\"快速服务代码\":\"\",\r\n\"其他信息\":\"\"\r\n}", cnName = "初始化配置")
    public static final long CONFIGURATION_INITAL = 11;

    /**
     * 以下为验证信息，每增加一条必填数据，都要增加一条对应的验证配置
     * cnName表示验证项对应的名称
     * enName表示验证该项的正则表达式
     */
    @BeanAttrInfo(value = "", enName = ".*\\S+.*", cnName = "机器型号")
    public static final long VALIDATE_MACHINE_MODLE = 21;

    @BeanAttrInfo(value = "", enName = ".*\\S+.*", cnName = "机器高度")
    public static final long VALIDATE_MACHINE_HIGHT = 22;

    @BeanAttrInfo(value = "", enName = ".*\\S+.*", cnName = "CPU信息.CPU型号")
    public static final long VALIDATE_CPU_MODLE = 23;

    @BeanAttrInfo(value = "", enName = "\\d+", cnName = "CPU信息.核数")
    public static final long VALIDATE_CPU_NUCLEI = 24;

    @BeanAttrInfo(value = "", enName = "\\d+", cnName = "CPU信息.线程")
    public static final long VALIDATE_CPU_THREAD = 25;

    @BeanAttrInfo(value = "", enName = "\\d+", cnName = "CPU信息.CPU个数")
    public static final long VALIDATE_CPU_NUMBER = 26;

    @BeanAttrInfo(value = "", enName = ".*\\S+.*", cnName = "内存信息.内存型号")
    public static final long VALIDATE_MEM_MODLE = 27;

    @BeanAttrInfo(value = "", enName = "\\d+[TtGg][Bb]?", cnName = "内存信息.每条大小")
    public static final long VALIDATE_MEM_SIZE = 28;

    @BeanAttrInfo(value = "", enName = "\\d+", cnName = "内存信息.条数")
    public static final long VALIDATE_MEM_NUMBER = 29;

    @BeanAttrInfo(value = "", enName = ".*\\S+.*", cnName = "运行盘信息.磁盘类别")
    public static final long VALIDATE_RUN_DISK_MODLE = 30;

    @BeanAttrInfo(value = "", enName = "\\d+[TtGg][Bb]?", cnName = "运行盘信息.磁盘大小")
    public static final long VALIDATE_RUN_DISK_SIZE = 31;

    @BeanAttrInfo(value = "", enName = "\\d+", cnName = "运行盘信息.磁盘个数")
    public static final long VALIDATE_RUN_DISK_NUMBER = 32;

    @BeanAttrInfo(value = "", enName = ".*\\S+.*", cnName = "运行盘信息.阵列类型")
    public static final long VALIDATE_RUN_DISK_TYPE = 33;

    @BeanAttrInfo(value = "", enName = ".*\\S+.*", cnName = "备份盘信息.磁盘类别")
    public static final long VALIDATE_BACKUP_DISK_MODLE = 34;

    @BeanAttrInfo(value = "", enName = "\\d+[TtGg][Bb]?", cnName = "备份盘信息.磁盘大小")
    public static final long VALIDATE_BACKUP_DISK_SIZE = 35;

    @BeanAttrInfo(value = "", enName = "\\d+", cnName = "备份盘信息.磁盘个数")
    public static final long VALIDATE_BACKUP_DISK_NUMBER = 36;

    @BeanAttrInfo(value = "", enName = ".*\\S+.*", cnName = "备份盘信息.阵列类型")
    public static final long VALIDATE_BACKUP_DISK_TYPE = 37;

    @BeanAttrInfo(value = "", enName = "\\d{4}-\\d{2}-\\d{2}", cnName = "购买时间")
    public static final long VALIDATE_BUY_DATE = 38;

    @BeanAttrInfo(value = "", enName = "\\d{4}-\\d{2}-\\d{2}", cnName = "过保时间")
    public static final long VALIDATE_AFTER_INSURANCE_DATE = 39;

    @BeanAttrInfo(value = "", enName = ".*\\S+.*", cnName = "机柜号")
    public static final long VALIDATE_CABINET_NUMBER = 40;

    @BeanAttrInfo(value = "", enName = ".*\\S+.*", cnName = "机柜内位置编号")
    public static final long VALIDATE_CABINET_INNER_NUMBER = 41;

    @BeanAttrInfo(value = "", enName = ".*\\S+.*", cnName = "操作系统版本")
    public static final long VALIDATE_OS_VERSION = 42;

    @BeanAttrInfo(value = "", enName = ".*\\S+.*", cnName = "快速服务代码")
    public static final long VALIDATE_SERVICE_CODE = 43;

    @BeanAttrInfo(value = "", enName = ".*", cnName = "其他信息")
    public static final long VALIDATE_OTHER_INFO = 44;
}
