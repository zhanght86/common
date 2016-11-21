package com.sjdf.platform.autotask.helper;

import com.sjdf.platform.common.conf.ProfileMapHelper;
import com.sjdf.platform.common.conf.ProfileProvider;
import com.sjdf.platform.log.logger.SJDFLogger;
import com.sjdf.platform.log.logger.SJDFLoggerFactory;
import com.sjdf.platform.net.HttpSocket;

/**
 * User: ketqi
 * Date: 2013-05-13 17:51
 * 自动任务管理器
 */
public class TrackerManager {
    private SJDFLogger logger = SJDFLoggerFactory.getSJDFLogger(TrackerManager.class);

    public boolean sendTrackerServer() {
        HttpSocket httpSocket = new HttpSocket();
        httpSocket.setUrl(ProfileProvider.getValue(ProfileMapHelper.INIT_CONFIG_MANAGER_URL));
        logger.info("url:" + ProfileProvider.getValue(ProfileMapHelper.INIT_CONFIG_MANAGER_URL));

        try {
            httpSocket.doGet();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
            return false;
        }
        httpSocket.getResponseData();

        return true;
    }
}
