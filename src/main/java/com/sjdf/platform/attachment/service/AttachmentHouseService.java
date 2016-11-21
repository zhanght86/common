package com.sjdf.platform.attachment.service;

import com.sjdf.platform.attachment.bean.Attachment;
import com.sjdf.platform.attachment.vo.AttachmentHouseVo;
import com.sjdf.platform.common.service.BaseService;
import com.sjdf.platform.common.utils.Page;
import org.hibernate.criterion.Order;

import java.util.List;

/**
 * Create at 2013-4-13 下午3:15:44
 * <p/>
 * 附件库Service 接口类
 *
 * @author frank
 */
public interface AttachmentHouseService extends BaseService {
    /**
     * 根据VO条件获取附件集合(如果存在更新，则执行更新操作，然后返回查询的数据)
     *
     * @param attachmentVoList 查询条件，附件集合
     * @return 附件集合
     * @throws Exception
     */
    List<AttachmentHouseVo> updateOrFindAttachmentList(List<AttachmentHouseVo> attachmentVoList) throws Exception;

    /**
     * 根据附件VO和分页条件，获取附件的分页信息
     *
     * @param attachmentHouseVo 附件VO
     * @return 附件的信息分页后的集合
     * @throws Exception
     */
    List<Attachment> findAttachmentPageList(AttachmentHouseVo attachmentHouseVo, Page page, Order... orders) throws Exception;

    /**
     * 获取单个附件
     *
     * @param attachmentId 附件ID
     * @return 单个附件
     * @throws Exception
     */
    Attachment getAttachment(Long attachmentId) throws Exception;

    /**
     * 根据条件获取附件VO对象
     *
     * @param attachmentHouseVo 条件
     * @return 附件VO对象
     * @throws Exception
     */
    AttachmentHouseVo getAttachmentHouseVo(AttachmentHouseVo attachmentHouseVo) throws Exception;

    /**
     * 保存多个附件
     *
     * @param attachmentVoList 需要被保存的多个附件List
     * @return 保存后的附件List
     * @throws Exception
     */
    List<AttachmentHouseVo> saveAttachmentList(List<AttachmentHouseVo> attachmentVoList) throws Exception;

    /**
     * 更新附件集合
     *
     * @param attachmentVoList 需要被更新的附件
     * @return 更新后的附件信息
     * @throws Exception
     */
    List<AttachmentHouseVo> updateAttachmentList(List<AttachmentHouseVo> attachmentVoList) throws Exception;

    /**
     * 更新单个附件
     *
     * @param attachmentHouseVo 需要被更新的附件信息Vo实体
     * @throws Exception
     */
    void updateAttachment(AttachmentHouseVo attachmentHouseVo) throws Exception;
}
