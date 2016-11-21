package com.sjdf.platform.common.init;

import com.sjdf.platform.CommonPlatformConstant;
import com.sjdf.platform.dictionary.bean.SystemType;
import com.sjdf.platform.dictionary.cache.ConfigManager;
import com.sjdf.platform.log.logger.SJDFLogger;
import com.sjdf.platform.log.logger.SJDFLoggerFactory;

/**
 * Create at 2012-11-16 下午5:24:26
 * 初始化数据接口
 *
 * @author KETQI
 */
public abstract class InitManager {
    protected SJDFLogger logger = SJDFLoggerFactory.getSJDFLogger(getClass());
    private transient String data;

    public InitManager() {
        if (SystemType.getCurrentSystemType() == SystemType.EISS_COMMON && getClass().isAssignableFrom(ConfigManager.class)) {
            return;
        }

        // 发送命令获取数据的次数
        int count = 0;
        String xmlData = null;
        // 尝试连接5次
        while (count < CommonPlatformConstant.LENGTH_5) {
            logger.info("第" + count + "次尝试连接");
            xmlData = getXmlData();
            if (xmlData == null) {
                count++;
                // 暂停1分钟后在继续尝试获取xml数据
                try {
                    Thread.sleep(CommonPlatformConstant.LENGTH_60 * CommonPlatformConstant.LENGTH_1000);
                } catch (Exception e) {
                    logger.error(e.getMessage(), e);
                }
            } else {
                count = Integer.MAX_VALUE;
            }
        }

        this.data = xmlData;
    }

    // 获取数据
    protected abstract String getXmlData();

    protected String getData() {
        if (data == null) {
            logger.error("获取数据失败");
            throw new RuntimeException("获取数据失败");
        }
        return data;
    }

    protected void destroyData() {
        this.data = null;
    }
}
