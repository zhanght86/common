/**
 *
 */
package com.sjdf.platform.notify.service.impl;

import com.sjdf.platform.common.helper.Message;
import com.sjdf.platform.common.service.impl.BaseServiceImpl;
import com.sjdf.platform.common.utils.Page;
import com.sjdf.platform.dictionary.bean.Dictionary;
import com.sjdf.platform.dictionary.bean.ExecuteStatus;
import com.sjdf.platform.dictionary.bean.MessageEngineConfig;
import com.sjdf.platform.dictionary.cache.DictionaryHelper;
import com.sjdf.platform.location.bean.Location;
import com.sjdf.platform.location.helper.LocationHelper;
import com.sjdf.platform.messageTemplate.bean.MessageTemplate;
import com.sjdf.platform.messageTemplate.helper.MessageTemplateHelper;
import com.sjdf.platform.notify.MessageEngine;
import com.sjdf.platform.notify.bean.NotifyTrack;
import com.sjdf.platform.notify.service.NotifyTrackService;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.List;

/**
 * 2013-1-9 上午9:49:40
 * 消息引擎service实现
 *
 * @author ketqi
 */
@Service
public class NotifyTrackServiceImpl extends BaseServiceImpl implements NotifyTrackService {

    /**
     * 保存通知跟踪记录
     *
     * @param notifyTrack 消息引擎数据推送跟踪信息
     */
    public void save(NotifyTrack notifyTrack) {
        if (notifyTrack.getParent() != null && notifyTrack.getParent().getId() > 0) {
            NotifyTrack parent = baseDao.get(NotifyTrack.class, notifyTrack.getParent().getId());
            if (parent != null) {
                notifyTrack.setMec(parent.getMec());
                notifyTrack.setOperateType(parent.getOperateType());
                notifyTrack.setStatus(ExecuteStatus.FAIL);
                notifyTrack.setParent(parent);
            }
        }
        baseDao.save(notifyTrack);

        // 启动消息引擎
        if (notifyTrack.getParent() == null) {
            MessageEngine.getInstance().notifyAsyn(notifyTrack);
        }
    }

    /**
     * 重新通知指定的跟踪记录(默认状态设置失败)
     *
     * @param track 查询条件
     * @param page  分页条件
     */
    public Message reNotify(NotifyTrack track, Page page) {
        if (!"ALL".equals(page.getCheckId())) {
            List<Long> idList = page.getCheckIdList();
            if (idList.isEmpty()) {
                return Message.createMessage("notify.track.re.notify.null");
            }
            for (Long idx : idList) {
                NotifyTrack notifyTrack = baseDao.get(NotifyTrack.class, idx);
                if (notifyTrack != null) {
                    if (notifyTrack.getParent() != null) {
                        Hibernate.initialize(notifyTrack.getParent());
                    }
                    MessageEngine.getInstance().notifyAsyn(notifyTrack);
                } else {
                    return Message.createMessage("notify.track.not.exist");
                }
            }
        } else {
            if (track == null) {
                return Message.createMessage("notify.track.not.exist");
            }
            //防止同步所有数据,默认状态设置失败
            track.setStatus(ExecuteStatus.FAIL);
            List<NotifyTrack> list = list(track, null);
            for (NotifyTrack notifyTrack : list) {
                if (notifyTrack.getParent() != null) {
                    Hibernate.initialize(notifyTrack.getParent());
                }
                MessageEngine.getInstance().notifyAsyn(notifyTrack);
            }
        }
        return Message.createEmptyMessage();
    }

    /**
     * 分页查询
     *
     * @param track 查询条件
     * @param page  分页记录
     */
    public List<NotifyTrack> list(NotifyTrack track, Page page) {
        DetachedCriteria criteria = DetachedCriteria.forClass(NotifyTrack.class);
        if (track != null) {
            if (track.getId() > 0) {
                criteria.add(Restrictions.eq("id", track.getId()));
            }
            if (track.getParent() != null && track.getParent().getId() > 0) {
                criteria.add(Restrictions.eq("parent.id", track.getParent().getId()));
            }
            if (track.getMec() > 0) {
                criteria.add(Restrictions.eq("mec", track.getMec()));
            }
            if (track.getOperateType() > 0) {
                criteria.add(Restrictions.eq("operateType", track.getOperateType()));
            }
            if (StringUtils.hasText(track.getUrl())) {
                criteria.add(Restrictions.like("url", track.getUrl(), MatchMode.ANYWHERE));
            }
            if (track.getStatus() > 0) {
                criteria.add(Restrictions.eq("status", track.getStatus()));
            }
            if (StringUtils.hasText(track.getData())) {
                criteria.add(Restrictions.like("data", track.getData(), MatchMode.ANYWHERE));
            }
        }
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return baseDao.listByCriteria(criteria, page, Order.desc("id"));
    }

    /**
     * 配置库更新通知
     *
     * @param dictionary     配置库信息
     * @param operatorAction 操作动作
     */
    public void saveDictionary(Dictionary dictionary, long operatorAction) {
        String data = DictionaryHelper.parse(Collections.singletonList(dictionary));
        NotifyTrack notifyTrack = new NotifyTrack(MessageEngineConfig.CONFIGURATION_LIBRARY, operatorAction, data);
        save(notifyTrack);
    }

    /**
     * 邮件短信模板更新通知
     *
     * @param template       邮件短信模板
     * @param operatorAction 操作动作
     */
    public void saveMessageTemplate(MessageTemplate template, long operatorAction) {
        String data = MessageTemplateHelper.parse(Collections.singletonList(template));
        NotifyTrack notifyTrack = new NotifyTrack(MessageEngineConfig.MAIL_SMS_TEMPLATE, operatorAction, data);
        save(notifyTrack);
    }

    /**
     * 地理位置信息通知
     *
     * @param location       地理位置信息
     * @param operatorAction 操作动作
     */
    public void saveLocaiotn(Location location, long operatorAction) {
        String data = LocationHelper.parse(Collections.singletonList(location));
        NotifyTrack notifyTrack = new NotifyTrack(MessageEngineConfig.LOCATION, operatorAction, data);
        save(notifyTrack);
    }
}
