package com.sjdf.platform.dictionary.bean.eiss;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.Dictionary;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Create at 2014年5月4日 下午5:58:37
 *
 * @author KETQI
 *         工单系统参数配置
 */
@Entity
@DiscriminatorValue("WORK_ORDER_CONFIG")
@BeanName(name = "工单系统参数配置")
public class WorkOrderConfig extends Dictionary {
    private static final long serialVersionUID = 2568442778133447964L;

    /** 附件个数限制 */
    @BeanAttrInfo(value = "3", cnName = "附件个数限制")
    public static final long ATTACHMENT_COUNTER = 1;

    /** 工单配额限制 */
    @BeanAttrInfo(value = "3", cnName = "工单配额限制")
    public static final long WORKORDER_QUOTA = 2;

    /** 工单附件上传路径前缀 */
    @BeanAttrInfo(value = "/home/datacenter/workorder/", cnName = "工单附件上传路径前缀")
    public static final long WORKORDER_DATACENTER_PATH = 3;

    /** 工单附件支持的文件格式 */
    @BeanAttrInfo(value = ".png,.jpeg,.jpg,.gif,.bmp", cnName = "工单附件支持的文件格式")
    public static final long WORKORDER_ATTACHMENT_FORMAT = 4;

    /** 工单附件支持的文件大小,单位K */
    @BeanAttrInfo(value = "500", cnName = "工单附件支持的文件大小,单位K")
    public static final long WORKORDER_ATTACHMENT_LENGTH = 5;

    /** 超管组别:技术支持 */
    @BeanAttrInfo(value = "3", cnName = "技术支持")
    public static final long LEVEL_TECHNICAL_SUPPORT = 6;

    /** 超管组别:行政人员 */
    @BeanAttrInfo(value = "8", cnName = "行政人员")
    public static final long LEVEL_ADMINISTRATION = 7;

    /** 工单提问次数 */
    @BeanAttrInfo(value = "3", cnName = "工单提问次数")
    public static final long ASK_COUNT = 8;

    /** 世纪东方管理中心工单话头 */
    @BeanAttrInfo(value = "尊敬的客户，感谢您选用我司产品和服务！", cnName = "世纪东方管理中心工单话头")
    public static final long SJDF_CENTER_WORK_HEAD = 9;

    /** 世纪东方管理中心工单话尾 */
    @BeanAttrInfo(value = "如有疑问请致电4000-120-151，感谢您的信赖与支持，我们总想为您做的更多更好！", cnName = "世纪东方管理中心工单话尾")
    public static final long SJDF_CENTER_WORK_TAIL = 10;

    /** 世纪东方控制面板工单话头 */
    @BeanAttrInfo(value = "尊敬的客户，感谢您选用我司产品和服务！", cnName = "世纪东方控制面板工单话头")
    public static final long SJDF_CONPANEL_WORK_HEAD = 11;

    /** 世纪东方控制面板工单话尾 */
    @BeanAttrInfo(value = "如有疑问请致电4000-120-151，感谢您的信赖与支持，我们总想为您做的更多更好！", cnName = "世纪东方控制面板工单话尾")
    public static final long SJDF_CONPANEL_WORK_TAIL = 12;

    /** 世纪东方代理平台工单话头 */
    @BeanAttrInfo(value = "尊敬的客户，感谢您选用我司产品和服务！", cnName = "世纪东方代理平台工单话头")
    public static final long SJDF_AGENT_WORK_HEAD = 13;

    /** 世纪东方代理平台工单话尾 */
    @BeanAttrInfo(value = "如有疑问请致电4000-120-151，感谢您的信赖与支持，我们总想为您做的更多更好！", cnName = "世纪东方代理平台工单话尾")
    public static final long SJDF_AGENT_WORK_TAIL = 14;

    /** 云道管理中心工单话头 */
    @BeanAttrInfo(value = "尊敬的客户，感谢您选用我司产品和服务！", cnName = "云道管理中心工单话头")
    public static final long YUNDAO_CENTER_WORK_HEAD = 15;

    /** 云道管理中心工单话尾 */
    @BeanAttrInfo(value = "如有疑问请致电4000-120-151，感谢您的信赖与支持，我们总想为您做的更多更好！", cnName = "云道管理中心工单话尾")
    public static final long YUNDAO_CENTER_WORK_TAIL = 16;

    /** 云道控制面板工单话头 */
    @BeanAttrInfo(value = "尊敬的客户，感谢您选用我司产品和服务！", cnName = "云道控制面板工单话头")
    public static final long YUNDAO_CONPANEL_WORK_HEAD = 17;

    /** 云道控制面板工单话尾 */
    @BeanAttrInfo(value = "如有疑问请致电4000-120-151，感谢您的信赖与支持，我们总想为您做的更多更好！", cnName = "云道控制面板工单话尾")
    public static final long YUNDAO_CONPANEL_WORK_TAIL = 18;

    /** 工单回复后x天，该工单评价不可进行修改 */
    @BeanAttrInfo(value = "4", cnName = "工单回复后x天，该工单评价不可进行修改")
    public static final long UNEVALUATE_DAYS = 19;

    /** 工单列表黄色时间 */
    @BeanAttrInfo(value = "30", cnName = "工单列表黄色时间(分钟)")
    public static final long WORK_ORDER_LIST_COLOR_YELLOW = 20;

    /** 工单列表红色时间 */
    @BeanAttrInfo(value = "60", cnName = "工单列表红色时间(分钟)")
    public static final long WORK_ORDER_LIST_COLOR_RED = 21;

    /** 已受理——工单已收，万难无忧！ */
    @BeanAttrInfo(value = "工单已收，万难无忧！", cnName = "已受理——工单已收，万难无忧！")
    public static final long WORK_ORDER_WAIT = 22;

    /** 处理中——再给我一点时间，还您一个真相！ */
    @BeanAttrInfo(value = "再给我一点时间，还您一个真相！", cnName = "处理中——再给我一点时间，还您一个真相！	")
    public static final long WORK_ORDER_HANDLE = 23;

    /** 待评价——亲，请给我一个好评！ */
    @BeanAttrInfo(value = "亲，请给我一个好评！", cnName = "待评价——亲，请给我一个好评！")
    public static final long WORK_ORDER_WAIT_EVALUATE = 24;

    /** 世纪东方工单回复过滤 */
    @BeanAttrInfo(value = "世纪东方,51web,82001809,cdnhost,4000121151,800061151", cnName = "世纪东方工单回复过滤")
    public static final long WORK_ORDER_SJDF_FILTER = 25;

    /** 云道工单回复过滤 */
    @BeanAttrInfo(value = "云道,云工坊,1818,zjisp,nbisp,4008015005", cnName = "云道工单回复过滤")
    public static final long WORK_ORDER_YUNDAO_FILTER = 26;

    /** 网居工单回复过滤 */
    @BeanAttrInfo(value = "网居,冰山,icelit,e8088,4000280166", cnName = "网居工单过滤")
    public static final long WORK_ORDER_AGENT_FILTER = 27;

    /** 服务项目提交个数限制 */
    @BeanAttrInfo(value = "1", cnName = "服务项目内容最多选择一项")
    public static final long WORK_ORDER_SERVER_ITEM_LENGTH = 28;

    /** 网居管理中心工单话头 */
    @BeanAttrInfo(value = "尊敬的客户，感谢您选用我司产品和服务！", cnName = "网居管理中心工单话头")
    public static final long WANGJU_CENTER_WORK_HEAD = 29;

    /** 网居管理中心工单话尾 */
    @BeanAttrInfo(value = "如有疑问请致电4000-120-151，感谢您的信赖与支持，我们总想为您做的更多更好！", cnName = "网居管理中心工单话尾")
    public static final long WANGJU_CENTER_WORK_TAIL = 30;

    /** 网居控制面板工单话头 */
    @BeanAttrInfo(value = "尊敬的客户，感谢您选用我司产品和服务！", cnName = "网居控制面板工单话头")
    public static final long WANGJU_CONPANEL_WORK_HEAD = 31;

    /** 网居控制面板工单话尾 */
    @BeanAttrInfo(value = "您的工单正在跟踪处理中，请耐心等待反馈。", cnName = "网居控制面板工单话尾")
    public static final long WANGJU_CONPANEL_WORK_TAIL = 32;
    /** 网居控制面板跟踪 */
    @BeanAttrInfo(value = "您的工单正在跟踪处理中，请耐心等待反馈。", cnName = "网居控制面板跟踪")
    public static final long WANGJU_CONPANEL_WORK_TRACK = 33;
    /** 网居管理中心工单跟踪 */
    @BeanAttrInfo(value = "您的工单正在跟踪处理中，请耐心等待反馈。", cnName = "网居管理中心跟踪")
    public static final long WANGJU_CENTER_WORK_TRACK = 34;

    /** 云工坊控制面板跟踪 */
    @BeanAttrInfo(value = "您的工单正在跟踪处理中，请耐心等待反馈。", cnName = "云工坊控制面板跟踪")
    public static final long YGF_CONPANEL_WORK_TRACK = 35;
    /** 云工坊管理中心工单跟踪 */
    @BeanAttrInfo(value = "您的工单正在跟踪处理中，请耐心等待反馈。", cnName = "云工坊管理中心跟踪")
    public static final long YGF_CENTER_WORK_TRACK = 36;

    /** 世纪东方控制面板跟踪 */
    @BeanAttrInfo(value = "您的工单正在跟踪处理中，请耐心等待反馈。", cnName = "世纪东方控制面板跟踪")
    public static final long SJDF_CONPANEL_WORK_TRACK = 37;
    /** 世纪东方管理中心工单跟踪 */
    @BeanAttrInfo(value = "您的工单正在跟踪处理中，请耐心等待反馈。", cnName = "世纪东方管理中心跟踪")
    public static final long SJDF_CENTER_WORK_TRACK = 38;

    /** 世纪东方代理平台工单跟踪 */
    @BeanAttrInfo(value = "您的工单正在跟踪处理中，请耐心等待反馈。", cnName = "世纪东方代理平台跟踪")
    public static final long SJDF_AGENT_WORK_TRACK = 39;

    /** 智能回复TIP */
    @BeanAttrInfo(value = "该回复来自系统检测结果，若未解决问题，请继续提交此工单。", cnName = "智能回复TIP")
    public static final long INTELLIGENT_RESPONSE_TIP = 40;

    /** 工单添加成功后提示 */
    @BeanAttrInfo(value = "工单提交成功！系统会在1-5分钟内给予回复，若回复未能解决问题，请接着此工单继续提交", cnName = "工单添加成功后提示")
    public static final long WORKORDER_ADD_SUCCESS = 41;

    /** 工单未评价提醒个数 */
    @BeanAttrInfo(value = "5", cnName = "工单未评价提醒个数")
    public static final long WORKORDER_NOT_EVALUATE = 42;

    /** 接受工单的超管组别 */
    @BeanAttrInfo(value = "3,8,9,", cnName = "接受工单的超管组别")
    public static final long LEVEL_4_ACCEPT_WORK_ORDER = 43;
}
