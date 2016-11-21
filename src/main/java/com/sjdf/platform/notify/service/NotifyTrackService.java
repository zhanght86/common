/**
 *
 */
package com.sjdf.platform.notify.service;

import com.sjdf.platform.common.helper.Message;
import com.sjdf.platform.common.service.BaseService;
import com.sjdf.platform.common.utils.Page;
import com.sjdf.platform.dictionary.bean.Dictionary;
import com.sjdf.platform.location.bean.Location;
import com.sjdf.platform.messageTemplate.bean.MessageTemplate;
import com.sjdf.platform.notify.bean.NotifyTrack;

import java.util.List;

/**
 * 2013-1-9 上午9:48:49
 * 消息引擎service
 *
 * @author ketqi
 */
public interface NotifyTrackService extends BaseService {
    /**
     * 保存通知跟踪记录
     *
     * @param notifyTrack 消息引擎数据推送跟踪信息
     */
    void save(NotifyTrack notifyTrack);

    /**
     * 重新通知指定的跟踪记录
     *
     * @param track 查询条件
     * @param page  分页条件
     */
    Message reNotify(NotifyTrack track, Page page);

    /**
     * 分页查询
     *
     * @param track 查询条件
     * @param page  分页记录
     */
    List<NotifyTrack> list(NotifyTrack track, Page page);

    /**
     * 配置库更新通知
     *
     * @param dictionary     配置库信息
     * @param operatorAction 操作动作
     */
    void saveDictionary(Dictionary dictionary, long operatorAction);

    /**
     * 邮件短信模板更新通知
     *
     * @param template       邮件短信模板
     * @param operatorAction 操作动作
     */
    void saveMessageTemplate(MessageTemplate template, long operatorAction);

    /**
     * 地理位置信息通知
     *
     * @param location       地理位置信息
     * @param operatorAction 操作动作
     */
    void saveLocaiotn(Location location, long operatorAction);
}
