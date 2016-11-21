/**
 *
 */
package com.sjdf.platform.notify.action;

import com.sjdf.platform.common.action.BaseAction;
import com.sjdf.platform.common.helper.Message;
import com.sjdf.platform.dictionary.bean.Dictionary;
import com.sjdf.platform.dictionary.bean.ExecuteStatus;
import com.sjdf.platform.dictionary.bean.MessageEngineConfig;
import com.sjdf.platform.dictionary.bean.OperatorAction;
import com.sjdf.platform.dictionary.cache.ConfigManager;
import com.sjdf.platform.notify.bean.NotifyTrack;
import com.sjdf.platform.notify.service.NotifyTrackService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * 2013-1-9 上午10:54:11
 * 通知跟踪管理
 *
 * @author ketqi
 */
public class NotifyTrackAction extends BaseAction {
    private static final long serialVersionUID = 3492527856961411666L;
    @Autowired
    private NotifyTrackService notifyTrackService;
    private NotifyTrack track;
    private List<NotifyTrack> trackList;

    /**
     * <pre>
     * URL:admin/common/NotifyTrackAction!list.action
     * </pre>
     * <p/>
     * 通知跟踪记录查询
     */
    public String list() {
        trackList = notifyTrackService.list(track, page);
        return "list";
    }

    /**
     * <pre>
     * URL:admin/common/NotifyTrackAction!handle.action
     * </pre>
     * <p/>
     * 重新通知
     */
    public String handle() {
        if (page != null) {
            Message message = notifyTrackService.reNotify(track, page);
            if (message.hasErrorMessage()) {
                tipMessage = getText(message);
            }
        } else {
            tipMessage = "notify again fail!";
        }
        if (tipMessage == null && track != null) {
            track.setStatus(0);
        }
        return "redirectList";
    }

    // 操作动作
    public List<OperatorAction> getOperateTypeList() {
        List<OperatorAction> list = new ArrayList<>();
        list.add(ConfigManager.getInstance().getDictionary(OperatorAction.class, OperatorAction.ADD));
        list.add(ConfigManager.getInstance().getDictionary(OperatorAction.class, OperatorAction.MODIFY));
        list.add(ConfigManager.getInstance().getDictionary(OperatorAction.class, OperatorAction.DELETE));
        return list;
    }

    // 消息引擎配置
    public List<? extends Dictionary> getMecList() {
        return ConfigManager.getInstance().getDictionary(MessageEngineConfig.class);
    }

    // 执行状态
    public List<ExecuteStatus> getExecuteStatusList() {
        List<ExecuteStatus> list = new ArrayList<>();
        list.add(ConfigManager.getInstance().getDictionary(ExecuteStatus.class, ExecuteStatus.BEGIN));
        list.add(ConfigManager.getInstance().getDictionary(ExecuteStatus.class, ExecuteStatus.SUCCESS));
        list.add(ConfigManager.getInstance().getDictionary(ExecuteStatus.class, ExecuteStatus.FAIL));
        list.add(ConfigManager.getInstance().getDictionary(ExecuteStatus.class, ExecuteStatus.COMPLTED));
        return list;
    }

    public NotifyTrack getTrack() {
        return track;
    }

    public void setTrack(NotifyTrack track) {
        this.track = track;
    }

    public List<NotifyTrack> getTrackList() {
        return trackList;
    }
}
