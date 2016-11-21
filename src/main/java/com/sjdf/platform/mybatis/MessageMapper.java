package com.sjdf.platform.mybatis;

import com.sjdf.platform.message.bean.EmailMessage;
import com.sjdf.platform.message.bean.SMSMessage;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 消息管理
 * User: ketqi
 * Date: 2015-07-16 10:40
 */
public interface MessageMapper {
    /**
     * 短信查询
     *
     * @param params 参数
     * @return 短信列表
     */
    List<SMSMessage> querySms(Map<String, Object> params);

    /**
     * 邮件查询
     *
     * @param params 参数
     * @return 邮件列表
     */
    List<EmailMessage> queryEmail(Map<String, Object> params);

    /**
     * 短信查询数目
     *
     * @param params 参数
     * @return 短信数目
     */
    int countSms(Map<String, Object> params);

    /**
     * 邮件查询数目
     *
     * @param params 参数
     * @return 邮件数目
     */
    int countEmail(Map<String, Object> params);

    /**
     * 短信查询
     *
     * @param idx 主键
     * @return 短信列表
     */
    SMSMessage getSms(@Param("idx") long idx);

    /**
     * 邮件查询
     *
     * @param idx 主键
     * @return 邮件列表
     */
    EmailMessage getEmail(@Param("idx") long idx);
}
