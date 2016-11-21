package com.sjdf.platform.print.service.impl;

import com.sjdf.platform.common.helper.Message;
import com.sjdf.platform.common.service.impl.BaseServiceImpl;
import com.sjdf.platform.common.utils.Page;
import com.sjdf.platform.log.logger.SJDFLogger;
import com.sjdf.platform.log.logger.SJDFLoggerFactory;
import com.sjdf.platform.print.bean.PrintAddress;
import com.sjdf.platform.print.service.PrintAddressService;
import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * Create at 2012-11-05
 * 打印地址管理service
 *
 * @author ketqi
 */
@Service
public class PrintAddressServiceImpl extends BaseServiceImpl implements PrintAddressService {
    private static final SJDFLogger LOGGER = SJDFLoggerFactory.getSJDFLogger(PrintAddressServiceImpl.class);

    /**
     * 分页查询地址信息
     *
     * @param address 查询条件
     * @param page    分页组件
     * @return 打印地址列表
     */
    public List<PrintAddress> list(PrintAddress address, Page page) {
        DetachedCriteria criteria = DetachedCriteria.forClass(PrintAddress.class);
        if (address != null) {
            //单位名称
            if (StringUtils.hasText(address.getCompanyName())) {
                criteria.add(Restrictions.like("companyName", address.getCompanyName(), MatchMode.ANYWHERE));
            }

            //收寄件人姓名
            if (StringUtils.hasText(address.getName())) {
                criteria.add(Restrictions.like("name", address.getName(), MatchMode.ANYWHERE));
            }

            //地址
            if (StringUtils.hasText(address.getAddress())) {
                criteria.add(Restrictions.like("address", address.getAddress(), MatchMode.ANYWHERE));
            }
        }
        return baseDao.listByCriteria(criteria, page);
    }

    /**
     * 添加打印地址
     *
     * @param addressVo 待添加的打印信息
     * @return 消息组件
     */
    public Message add(PrintAddress addressVo) {
        Message message = PrintAddress.validate(addressVo);
        if (message.hasErrorMessage()) {
            return message;
        }

        baseDao.save(addressVo);
        return Message.createMessage().setInfo(addressVo.wrapUpdateContent(null, false)).setReturnData(addressVo);
    }

    /**
     * 修改打印地址
     *
     * @param addressVo 待修改的打印信息
     * @return 消息组件
     */
    public Message update(PrintAddress addressVo) {
        Message message = PrintAddress.validate(addressVo);
        if (message.hasErrorMessage()) {
            return message;
        }

        PrintAddress old;
        if (addressVo.getId() <= 0 || (old = baseDao.get(PrintAddress.class, addressVo.getId())) == null) {
            return Message.createMessage("print.addressVo.null");
        }

        String info = old.wrapUpdateContent(addressVo, true);

        addressVo.setCreateTime(old.getCreateTime());
        addressVo.setCreateUser(old.getCreateUser());
        try {
            BeanUtils.copyProperties(old, addressVo);
        } catch (IllegalAccessException | InvocationTargetException e) {
            LOGGER.error(e.getMessage(), e);
        }

        baseDao.update(old);
        return Message.createMessage().setReturnData(old).setInfo(info);
    }

    /**
     * 删除打印信息
     *
     * @param idx 待删除的信息
     * @return 消息组件
     */
    public Message del(long idx) {
        PrintAddress address = baseDao.get(PrintAddress.class, idx);
        if (address == null) {
            return Message.createMessage("print.addressVo.null");
        }
        baseDao.delete(address);
        return Message.createMessage().setReturnData(address).setInfo(address.wrapUpdateContent(null, false));
    }
}
