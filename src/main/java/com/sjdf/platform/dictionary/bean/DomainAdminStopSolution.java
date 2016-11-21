package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author sjdf
 * @category 域名停止的解决方案
 */
@Entity
@DiscriminatorValue("DOMAIN_ADMIN_STOP_SOLUTION")
@BeanName(name = "域名停止的解决方案")
public class DomainAdminStopSolution extends Dictionary {

    /**
     * 序列化
     */
    private static final long serialVersionUID = -1669457581184184265L;

    /** 虚拟主机 */
    @BeanAttrInfo(value = "解决方案11", orderBy = 1, cnName = "解决方案1")
    public static final long SOLUTION_ONE = 1;

    /** 信产部停止-需网监备案 */
    @BeanAttrInfo(value = "1.接绵阳网监通知，此网站主办者需到网监支队办理备案手续."
            + "2.备案表下载地址：http://down.gotoip3.com/网站备案表.rar，请打印二份空白备案表到到网监处现场填写信息."
            + "3.备案地址：绵阳市长虹大道北段142号交警支队大楼11楼（高水车管所旁）监察室；联系人：杨红 ；联系电话：0816-2690727.", orderBy = 100, cnName = "信产部停止-需网监备案")
    public static final long MII_STOP_SOLUTION_ONE = 100;

    /** 备案信息不真实-不可恢复 */
    @BeanAttrInfo(value = "1.该域名逾期或整改未达到备案真实性要求，需立即进行变更备案."
            + "2.如不愿变更备案，请购买免备案香港机房云服务器或将该站点迁往免备案香港虚拟主机."
            + "3.如您变更了备案，我司审核通过且备案状态为【待管局审核】，请到咨询反馈提交超管审核并恢复访问.", orderBy = 200, cnName = "备案信息不真实-不可恢复")
    public static final long RECORD_NOT_TRUE_NOT_SOLUTION_ONE = 200;

    /** 备案信息不真实-可恢复(云主机) */
    @BeanAttrInfo(value = "1.该域名未达到备案真实性要求，需要进行变更备案（请到备案系统操作）."
            + "2.请自行通过控制面板点击【恢复访问】即可正常访问."
            + "3.请尽快进行变更备案，逾期会再次取消访问，并不可再恢复.", orderBy = 300, cnName = "备案信息不真实-可恢复(云主机)")
    public static final long RECORD_NOT_TRUE_CAN_SOLUTION_ONE = 300;

    /** 空壳网站 */
    @BeanAttrInfo(value = "该域名可能存在备案号，但是不存在接入商，需要到我司备案系统进行新增接入或新增备案.", orderBy = 400, cnName = "空壳网站")
    public static final long SHELL_SITE_SOLUTION_ONE = 400;

    /** 已解除绑定 */
    @BeanAttrInfo(value = "1.该域名无备案号，需到我司备案系统提交备案或升级产品到香港机房."
            + "2.产品升级后，需做别名解析到升级后产品的别名地址."
            + "3.我司超管会在3小时内完成数据转移，数据转移完毕后网站方可访问.", orderBy = 400, cnName = "已解除绑定")
    public static final long BIND_REMOVE_SOLUTION_ONE = 500;

    /** 备案信息不真实-可恢复(虚拟主机) */
    @BeanAttrInfo(value = "1.该域名未达到备案真实性要求，需要进行变更备案（请到备案系统操作）."
            + "2.请自行通过控制面板恢复绑定状态为【正常】即可恢复访问."
            + "3.请尽快进行变更备案，逾期会再次取消访问，并不可再恢复.", orderBy = 600, cnName = "备案信息不真实-可恢复(虚拟主机)")
    public static final long RECORD_NOT_TRUE_CAN_SOLUTION_TWO = 600;

}
