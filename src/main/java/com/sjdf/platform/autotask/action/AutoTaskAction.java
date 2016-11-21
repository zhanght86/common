package com.sjdf.platform.autotask.action;

import com.sjdf.platform.autotask.bean.AutoTaskTracker;
import com.sjdf.platform.autotask.vo.AutoTaskVo;
import com.sjdf.platform.common.action.BaseAction;

import java.util.List;

/**
 * Create at 2012-11-07
 * 自动任务跟踪Action
 *
 * @author ketqi
 */
public class AutoTaskAction extends BaseAction {
    private static final long serialVersionUID = 6723549198973769498L;
    private AutoTaskTracker tracker;
    private List<AutoTaskTracker> trackerList;
    private List<AutoTaskVo> autoTaskVoList;

    /**
     * <pre>
     * URL:admin/common/AutoTaskAction!index.action
     * </pre>
     *
     * @return 所有自动任务类别列表
     */
    public String index() {
        return "index";
    }

    /**
     * <pre>
     * URL:admin/common/AutoTaskAction!list.action
     * </pre>
     *
     * @return 自动任务跟踪记录列表
     */
    public String list() {

        return "list";
    }

    /**
     * <pre>
     * URL:user/common/AutoTaskAction!tracker.action
     * </pre>
     *
     * @return 自动任务数据收集
     */
    public String tracker() {

        return NONE;
    }

    public String handleType() {

        return NONE;
    }

    public String addTracker() {

        return NONE;
    }

    public AutoTaskTracker getTracker() {
        return tracker;
    }

    public void setTracker(AutoTaskTracker tracker) {
        this.tracker = tracker;
    }

    public List<AutoTaskTracker> getTrackerList() {
        return trackerList;
    }

    public void setTrackerList(List<AutoTaskTracker> trackerList) {
        this.trackerList = trackerList;
    }

    public List<AutoTaskVo> getAutoTaskVoList() {
        return autoTaskVoList;
    }

    public void setAutoTaskVoList(List<AutoTaskVo> autoTaskVoList) {
        this.autoTaskVoList = autoTaskVoList;
    }
}
