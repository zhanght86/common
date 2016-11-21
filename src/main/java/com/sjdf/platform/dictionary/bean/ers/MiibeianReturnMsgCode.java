package com.sjdf.platform.dictionary.bean.ers;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;
import com.sjdf.platform.dictionary.bean.Dictionary;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author sjdf
 * @category　备案系统调用管局接口返回状态代码表
 */
@Entity
@DiscriminatorValue("MIIBEIAN_RETURN_MSG_CODE")
@BeanName(name = "备案系统调用管局接口返回状态代码表")
public class MiibeianReturnMsgCode extends Dictionary {

    /**
     * 序列化
     */
    private static final long serialVersionUID = -7992273081118839043L;

    /** 操作成功，数据已经下载完毕 */
    @BeanAttrInfo(value = "1", cnName = "操作成功，数据已经下载完毕")
    public static final long SUCCESS_DOWNLOAD_COMPLETE = 1L;

    /** 目前服务器端没有可以下载的数据 */
    @BeanAttrInfo(value = "2", cnName = "目前服务器端没有可以下载的数据")
    public static final long SERVER_NOT_DOWNLOAD_DATA = 2L;
}
