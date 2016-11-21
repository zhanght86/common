package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author frank
 * @date 2012-11-2 上午9:50:28
 * @category 备案系统常用配置
 */
@Entity
@DiscriminatorValue("RECORD_SYSTEM_CONFIGURE")
@BeanName(name = "备案系统常用配置")
public class RecordSystemConfigure extends Dictionary {

    /**
     * 序列化ID
     */
    private static final long serialVersionUID = 2882157632658724031L;

    /** 变更备案不需要生成备案相册的备案号，多个用英文（,）逗号隔开 */
    @BeanAttrInfo(value = "", orderBy = 1, cnName = "不生成备案相册备案号")
    public static final long NOT_GENERATE_ATTACHMENT_RECORD_NUM = 1;

    /** 字符个数限制--网站名称 */
    @BeanAttrInfo(value = "2", orderBy = 2, cnName = "字符个数限制--网站名称")
    public static final long STRING_QUANTITY_WEB_NAME = 2;

    /** 仅变更IP备注 */
    @BeanAttrInfo(value = "仅变更IP", orderBy = 3, cnName = "仅变更IP备注")
    public static final long ONLY_MODIFY_RECORD_IP_REMARK = 3;

    /** 上传附件的扩展名称,如(jpg,png) */
    @BeanAttrInfo(value = "*.rar,*.zip,*.jpg,*.png,*.tiff,*.doc,*.JPG,*.gif,*.pdf", orderBy = 4, cnName = "指定上传附件的扩展名称(如：*.rar,*.zip,*.jpg,*.png,*.tiff,*.doc,*.JPG,*.gif,*.pdf)")
    public static final long DEFINE_UPLOAD_ATTACHMENT_EXTENDS_NAME = 4;

    /** 限制上传附件的大小(这里的文件大小单位是指 KB,上传文件必须小于配置值) */
    @BeanAttrInfo(value = "300", orderBy = 5, cnName = "限制上传附件的大小(这里的文件大小单位是指 KB,上传文件必须小于配置值)")
    public static final long LIMIT_UPLOAD_ATTACHMENT_SIZE = 5;

    /** 配置备案新接口上报附件具体类型代码，此处代码为业务公共平台：AttachmentUseCode类的代码 */
    @BeanAttrInfo(value = "10016,10015,10012,10011,10013,10014,10019,10020,10021,10022,10018", orderBy = 6, cnName = "配置备案新接口上报附件具体类型代码,多种请用英文半角逗号隔开")
    public static final long UPLOAD_ATTACHMENT_XML_FJYT = 6;

    /** 备案上报调用接口方式，0表示旧接口，1表示新接口 */
    @BeanAttrInfo(value = "0", orderBy = 7, cnName = "备案上报调用接口方式，0表示旧接口，1表示新接口")
    public static final long INVOKE_UPLOAD_INTERFACE_WAY = 7;

    /** 上传附件截图的倍数 */
    @BeanAttrInfo(value = "0.75", orderBy = 8, cnName = "上传附件截图的倍数")
    public static final long UPLOAD_ATTACHMENT_SPLIT_MULTIPLE = 8;

    /** 信息提示--验证网站首页URL */
    @BeanAttrInfo(value = "根据网站域名，自动生成的网站首页URL字符不能超过{X}个，请删除部分域名，再次提交。", orderBy = 9, cnName = "信息提示--验证网站首页URL")
    public static final long MESSAGE_VALIDATE_WEB_INDEX_URL = 9;

    /** 验证网站首页URL限制的个数，仅填写整数 */
    @BeanAttrInfo(value = "255", orderBy = 10, cnName = "验证网站首页URL限制的个数，仅填写整数")
    public static final long WEB_INDEX_URL_STR_COUNT = 10;

    /** 验证法人代表，中文名称不能超过15个字符 */
    @BeanAttrInfo(value = "15", orderBy = 11, cnName = "法人代表，中文名称不能超过15个字符")
    public static final long LEGAL_STR_COUNT = 11;

    /** 信息提示--验证法人代表，中文名称不能超过15个字符 */
    @BeanAttrInfo(value = "法定代表人姓名必须包含至少2个中文字符或者至少{X}个英文字符!", orderBy = 12, cnName = "信息提示--验证法人代表中文名称")
    public static final long MESSAGE_LEGAL_STR_COUNT = 12;

    /** 是否需要填写管局拨测项目以及发送拨测短信，1表示需要，2表示不需要 */
    @BeanAttrInfo(value = "2", orderBy = 13, cnName = "是否需要填写管局拨测项目以及发送拨测短信，1表示需要，2表示不需要")
    public static final long NEED_FILL_CALL_TESTING = 13;

    /** 变更IP功能多线程每个线程执行变更的个数 */
    @BeanAttrInfo(value = "3", orderBy = 14, cnName = "变更IP功能多线程每个线程执行变更的个数(注意只能用数字)")
    public static final long THREAD_RUN_COUNT = 14;

    /** 是否直接取消接入，1表示直接取消接入，2表示不直接取消接入 */
    @BeanAttrInfo(value = "2", orderBy = 15, cnName = "是否直接取消接入，1表示直接取消接入，2表示不直接取消接入")
    public static final long WHETHER_CANCEL_ACCESS = 15;

    /** 前置审批对应的网站服务内容代码,目前配置：即时通信,搜索引擎,网络新闻,网络游戏,网络音乐,网络影视,网络软件/下载,网络教育 */
    @BeanAttrInfo(value = "1,13,21,5,2,17,15,14", orderBy = 16, cnName = "前置审批对应的网站服务内容代码")
    public static final long SPECIAL_WEB_SERVICE_CONTENT = 16;

    /** 是否需要信息安全责任书，1表示需要，2表示不需要 */
    @BeanAttrInfo(value = "2", orderBy = 17, cnName = "是否需要信息安全责任书，1表示需要，2表示不需要")
    public static final long WHETHER_NEED_INFO_LETTERS_CERTIFICATE = 17;

    /** 是否需要网站负责人授权书，1表示需要，2表示不需要 */
    @BeanAttrInfo(value = "2", orderBy = 18, cnName = "是否需要网站负责人授权书，1表示需要，2表示不需要")
    public static final long WHETHER_NEED_WEB_RESP_WARRANT_CERTIFICATE = 18;

    /** 审核通过数据{X}天内有效，表示已通过备案 */
    @BeanAttrInfo(value = "5", orderBy = 19, cnName = "审核通过数据{X}天内有效，表示已通过备案")
    public static final long RECORD_APPROVAL_EXPIRATION_DATE = 19;

    /** 自动任务重新上报最大次数 */
    @BeanAttrInfo(value = "3", orderBy = 20, cnName = "自动任务重新上报最大次数")
    public static final long AUTO_TASK_RE_UPLOAD_MAX_COUNT = 20;

    /** 自动任务重新上报获取数据时间间隔 */
    @BeanAttrInfo(value = "30", orderBy = 21, cnName = "自动任务重新上报获取数据时间间隔")
    public static final long AUTO_TASK_RE_UPLOAD_TIME = 21;

    /** 备案系统联系电话号码 */
    @BeanAttrInfo(value = "028-82001809-848", orderBy = 22, cnName = "备案系统联系电话号码")
    public static final long ERS_TEL_NUM = 22;

    /** 境外域名后缀 */
    @BeanAttrInfo(value = "us,ue,hk,la,tw,kr", orderBy = 23, cnName = "境外域名后缀")
    public static final long OUTSIDE_DOMAIN_SUFFIX = 23L;

    /**
     * 特定省份非个人org的域名允许提交备案的单位性质,与【特定单位性质非个人org的域名只能允许提交备案的省份】组合使用
     *
     * @see com.sjdf.platform.dictionary.bean@ORG_ONLY_ALLOW_RECORD_PRO
     */
    @BeanAttrInfo(value = "3,6", orderBy = 24, cnName = "特定省份非个人org的域名允许提交备案的单位性质")
    public static final long ORG_ONLY_ALLOW_RECORD_NATURE = 24L;

    @BeanAttrInfo(value = "/image/sample/guangdong_personal_check_list_sample.jpg", orderBy = 25, cnName = "广东个人核验单填写示例地址")
    public static final long GUANGDONG_PERSONAL_CHECK_SAMPLE = 25L;

    @BeanAttrInfo(value = "/image/sample/guangdong_not_personal_check_list_sample.jpg", orderBy = 26, cnName = "广东非个人核验单填写示例地址")
    public static final long GUANGDONG_NOT_PERSONAL_CHECK_SAMPLE = 26L;

    @BeanAttrInfo(value = "/image/sample/personal_check_list_sample.jpg", orderBy = 27, cnName = "个人核验单填写示例地址")
    public static final long PERSONAL_CHECK_SAMPLE = 27L;

    @BeanAttrInfo(value = "/image/sample/not_personal_check_list_sample.jpg", orderBy = 28, cnName = "非个人核验单填写示例地址")
    public static final long NOT_PERSONAL_CHECK_SAMPLE = 28L;

    @BeanAttrInfo(value = "jdl345", orderBy = 29, cnName = "我司使用备案系统账号列表")
    public static final long OUR_ERS_USER_LIST = 29L;

    @BeanAttrInfo(value = "5", orderBy = 30, cnName = "前置审批附件上传最大个数")
    public static final long QZSP_ATTACHMENT_UPLOAD_MAX_NUM = 30L;

    @BeanAttrInfo(value = "10014,10015,10016,10019,10018", orderBy = 31, cnName = "显示附件的代码集合")
    public static final long ATTACHMENT_SHOW_SAMPLE_CODE = 31L;

    @BeanAttrInfo(value = "/image/sample/identity_sample.jpg", orderBy = 32, cnName = "身份证填写示例地址")
    public static final long IDENTITY_SAMPLE = 32L;

    @BeanAttrInfo(value = "/image/sample/web_chair_photo_sample.jpg", orderBy = 33, cnName = "网站负责人照片填写示例地址")
    public static final long WEB_CHAIR_PHPTO_SAMPLE = 33L;

    @BeanAttrInfo(value = "/image/sample/business_license_sample.jpg", orderBy = 34, cnName = "工商营业执照填写示例地址")
    public static final long BUSINESS_LICENSE_SAMPLE = 34L;

    @BeanAttrInfo(value = "/image/sample/org_code_sample.jpg", orderBy = 35, cnName = "组织机构代码证填写示例地址")
    public static final long ORG_CODE_SAMPLE = 35L;

    /** 是否需要对已通过备案的进行管局拨测通知（1表示需要，2表示不需要） */
    @BeanAttrInfo(value = "2", orderBy = 36, cnName = "是否需要对已通过备案的进行管局拨测通知（1表示需要，2表示不需要）")
    public static final long NEED_RECORDED_CALL_TESTING = 36L;

    /** 是否需要主体负责人授权书，1表示需要，2表示不需要 */
    @BeanAttrInfo(value = "1", orderBy = 37, cnName = "是否需要主体负责人授权书，1表示需要，2表示不需要")
    public static final long WHETHER_NEED_ZT_RESP_WARRANT_CERTIFICATE = 37L;
}
