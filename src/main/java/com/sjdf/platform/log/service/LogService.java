package com.sjdf.platform.log.service;

import com.sjdf.platform.common.helper.Message;
import com.sjdf.platform.common.service.BaseService;
import com.sjdf.platform.common.utils.Page;
import com.sjdf.platform.log.bean.LogBackupBean;
import com.sjdf.platform.log.bean.LogBean;
import com.sjdf.platform.log.vo.LogVo;

import java.util.List;

/**
 * 日志管理Service
 * Create at 2012-07-06
 *
 * @author 王正伟
 */
public interface LogService extends BaseService {
    /**
     * @param log  检索条件
     * @param page 分页工具
     * @return 检索的日志
     * 分页检索日志
     */
    List<LogBean> list(LogVo log, Page page);

    /**
     * @param log  检索条件
     * @param page 分页工具
     * @return 检索的备份日志
     */
    List<LogBackupBean> listHistory(LogVo log, Page page);

    /**
     * @param logger 日志
     * @return 修改资源id
     */
    Message updateResourceId(LogBean logger);
}
