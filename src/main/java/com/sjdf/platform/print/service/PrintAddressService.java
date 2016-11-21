package com.sjdf.platform.print.service;

import com.sjdf.platform.common.helper.Message;
import com.sjdf.platform.common.service.BaseService;
import com.sjdf.platform.common.utils.Page;
import com.sjdf.platform.print.bean.PrintAddress;

import java.util.List;

/**
 * Create at 2012-11-05
 * <p/>
 * 打印地址管理service
 *
 * @author ketqi
 */
public interface PrintAddressService extends BaseService {
    /**
     * 分页查询地址信息
     *
     * @param address 查询条件
     * @param page    分页组件
     * @return 打印地址列表
     */
    List<PrintAddress> list(PrintAddress address, Page page);

    /**
     * 添加打印地址
     *
     * @param addressVo 待添加的打印信息
     * @return 消息组件
     */
    Message add(PrintAddress addressVo);

    /**
     * 修改打印地址
     *
     * @param addressVo 待修改的打印信息
     * @return 消息组件
     */
    Message update(PrintAddress addressVo);

    /**
     * 删除打印信息
     *
     * @param idx 待删除的信息
     * @return 消息组件
     */
    Message del(long idx);
}
