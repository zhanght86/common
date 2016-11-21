package com.sjdf.platform.attachment.service.impl;

import com.sjdf.platform.CommonPlatformConstant;
import com.sjdf.platform.attachment.bean.Attachment;
import com.sjdf.platform.attachment.helper.AttachmentHelper;
import com.sjdf.platform.attachment.service.AttachmentHouseService;
import com.sjdf.platform.attachment.vo.AttachmentHouseVo;
import com.sjdf.platform.common.service.impl.BaseServiceImpl;
import com.sjdf.platform.common.utils.DateUtils;
import com.sjdf.platform.common.utils.ImageUtils;
import com.sjdf.platform.common.utils.Page;
import com.sjdf.platform.common.utils.Tools;
import com.sjdf.platform.common.utils.base64.Base64;
import com.sjdf.platform.dictionary.bean.*;
import com.sjdf.platform.dictionary.cache.ConfigManager;
import com.sjdf.platform.log.logger.SJDFLogger;
import com.sjdf.platform.log.logger.SJDFLoggerFactory;
import org.hibernate.criterion.*;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Create at 2013-4-13 下午3:16:56
 * <p/>
 * 附件库Service 实现类，实现针对附件的增加、删除、修改、分页（条件）查询
 *
 * @author frank
 */
@Service
public class AttachmentHouseServiceImpl extends BaseServiceImpl implements AttachmentHouseService {
    private static final SJDFLogger LOGGER = SJDFLoggerFactory.getSJDFLogger(AttachmentHouseServiceImpl.class);

    @Override
    public List<AttachmentHouseVo> updateOrFindAttachmentList(List<AttachmentHouseVo> attachmentVoList) throws Exception {
        List<AttachmentHouseVo> voList = new ArrayList<>();

        for (AttachmentHouseVo attachmentVo : attachmentVoList) {
            List<Attachment> matchedList = getAttachmentByCondition(attachmentVo);
            for (Attachment matchAttachment : matchedList) {
                //添加Vo 到VoList
                voToVoList(matchAttachment, voList);
            }

        }
        return voList;
    }

    /**
     * 根据多条件获取附件信息
     *
     * @param attachmentHouseVo 封装了条件的VO
     * @return DB附件信息
     */
    public List<Attachment> getAttachmentByCondition(AttachmentHouseVo attachmentHouseVo) {
        List<Attachment> attachmentMatchedList = new ArrayList<>();
        Attachment attachment;
        try {
            StringBuilder sqlBuilder = new StringBuilder(CommonPlatformConstant.LENGTH_1024);
            sqlBuilder.append("select * from p_attachment_house where 1=1 ");
            //初始化的长度
            int initSize = sqlBuilder.length();
            //附件所属用户
            if (Tools.longIsNotNUllAndZero(attachmentHouseVo.getId())) {
                sqlBuilder.append(" and p_attachment_house.id = ").append(attachmentHouseVo.getId());
                //返回根据ID进行查询的附件信息
                attachment = baseDao.sqlQuery(Attachment.class, sqlBuilder.toString(), new Object[]{}).get(0);
                //表示需要返回新的数据，则更新common中数据库的子系统路径信息
                if (!AttachmentHelper.verifyAttachmentPath(attachment.getPath(), attachment.getSubSystemPath())) {
                    attachment.setSubSystemPath(AttachmentHelper.getSubSystemPath(attachment.getPath()));
                    baseDao.update(attachment);
                }
                attachmentMatchedList.add(attachment);
            } else {
                //附件所属用户
                if (Tools.longIsNotNUllAndZero(attachmentHouseVo.getUserId())) {
                    sqlBuilder.append(" and p_attachment_house.userId = ").append(attachmentHouseVo.getUserId());
                }
                //附件用途代码
                if (Tools.longIsNotNUllAndZero(attachmentHouseVo.getAttachmentUseCode())) {
                    sqlBuilder.append(" and p_attachment_house.attachmentUseCode = ").append(attachmentHouseVo.getAttachmentUseCode());
                }
                //附件用途类别
                if (Tools.longIsNotNUllAndZero(attachmentHouseVo.getAttachmentUseType())) {
                    sqlBuilder.append(" and p_attachment_house.attachmentUseType = ").append(attachmentHouseVo.getAttachmentUseType());
                }
                //系统类别
                if (Tools.longIsNotNUllAndZero(attachmentHouseVo.getSystemType())) {
                    sqlBuilder.append(" and p_attachment_house.systemType = ").append(attachmentHouseVo.getSystemType());
                }
                //附件格式
                if (Tools.longIsNotNUllAndZero(attachmentHouseVo.getFormat())) {
                    sqlBuilder.append(" and p_attachment_house.format = ").append(attachmentHouseVo.getFormat());
                }
                //如果没有传参数返回空
                if (initSize == sqlBuilder.length()) {
                    return attachmentMatchedList;
                }
                List<Attachment> dbAttachmentList = baseDao.sqlQuery(Attachment.class, sqlBuilder.toString(), new Object[]{});

                //校验特别标识（确定准确匹配关键字和准确的附件）
                for (Attachment dbAttachment : dbAttachmentList) {
                    //如果经过判定后找到备案相册
                    if (AttachmentHelper.compareAttachmentSpecialMark(attachmentHouseVo.getAttachmentSpecialMark(), dbAttachment.getAttachmentSpecialMark()) && AttachmentHelper.compareAttachmentSpecialMark(dbAttachment.getAttachmentSpecialMark(), attachmentHouseVo.getAttachmentSpecialMark())) {
                        attachment = dbAttachment;
                        //表示需要返回新的数据，则更新common中数据库的子系统路径信息
                        if (!AttachmentHelper.verifyAttachmentPath(attachment.getPath(), attachment.getSubSystemPath())) {
                            attachment.setSubSystemPath(AttachmentHelper.getSubSystemPath(attachment.getPath()));
                            baseDao.update(attachment);
                        }
                        attachmentMatchedList.add(dbAttachment);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage(), e);
        }
        return attachmentMatchedList;
    }

    @Override
    public List<Attachment> findAttachmentPageList(AttachmentHouseVo attachmentHouseVo, Page page, Order... orders) throws Exception {
        DetachedCriteria criteria = DetachedCriteria.forClass(Attachment.class);
        if (attachmentHouseVo != null) {
            //附件所属系统
            if (Tools.longIsNotNUllAndZero(attachmentHouseVo.getSystemType())) {
                criteria.add(Restrictions.eq("systemType", attachmentHouseVo.getSystemType()));
            }
            //附件所属类别
            if (Tools.longIsNotNUllAndZero(attachmentHouseVo.getAttachmentUseType())) {
                criteria.add(Restrictions.eq("attachmentUseType", attachmentHouseVo.getAttachmentUseType()));
            }
            //附件所属用途
            if (Tools.longIsNotNUllAndZero(attachmentHouseVo.getAttachmentUseCode())) {
                criteria.add(Restrictions.eq("attachmentUseCode", attachmentHouseVo.getAttachmentUseCode()));
            }
            //附件格式
            if (Tools.longIsNotNUllAndZero(attachmentHouseVo.getFormat())) {
                criteria.add(Restrictions.eq("format", attachmentHouseVo.getFormat()));
            }
            //附件特殊标识
            if (!Tools.isEmpty(attachmentHouseVo.getAttachmentSpecialMark())) {
                criteria.add(Restrictions.like("attachmentSpecialMark", attachmentHouseVo.getAttachmentSpecialMark(), MatchMode.ANYWHERE));
            }
            //附件大小，始末
            if (Tools.longIsNotNUllAndZero(attachmentHouseVo.getStartSize()) && Tools.longIsNotNUllAndZero(attachmentHouseVo.getEndSize())) {
                criteria.add(Restrictions.ge("size", attachmentHouseVo.getStartSize()));
                criteria.add(Restrictions.le("size", attachmentHouseVo.getEndSize()));
            }
            //附件大小，始
            if (Tools.longIsNotNUllAndZero(attachmentHouseVo.getStartSize()) && !Tools.longIsNotNUllAndZero(attachmentHouseVo.getEndSize())) {
                criteria.add(Restrictions.ge("size", attachmentHouseVo.getStartSize()));
            }
            //附件大小，末
            if (!Tools.longIsNotNUllAndZero(attachmentHouseVo.getStartSize()) && Tools.longIsNotNUllAndZero(attachmentHouseVo.getEndSize())) {
                criteria.add(Restrictions.le("size", attachmentHouseVo.getEndSize()));
            }
            //附件日期，始末
            if (!Tools.isEmpty(attachmentHouseVo.getBeginDate()) && !Tools.isEmpty(attachmentHouseVo.getEndDate())) {
                criteria.add(Restrictions.ge("updateTime", attachmentHouseVo.getBeginDate()));
                criteria.add(Restrictions.le("updateTime", attachmentHouseVo.getEndDate()));
            }
            //附件日期，始
            if (!Tools.isEmpty(attachmentHouseVo.getBeginDate()) && Tools.isEmpty(attachmentHouseVo.getEndDate())) {
                criteria.add(Restrictions.ge("updateTime", attachmentHouseVo.getBeginDate()));
            }
            //附件日期，末
            if (Tools.isEmpty(attachmentHouseVo.getBeginDate()) && !Tools.isEmpty(attachmentHouseVo.getEndDate())) {
                criteria.add(Restrictions.le("updateTime", attachmentHouseVo.getEndDate()));
            }
        }

        //附件未被删除
        criteria.add(Restrictions.eq("markDelete", Long.valueOf(ConfigManager.getInstance().getValue(WhetherState.class, WhetherState.NO))));

        criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
        List<Attachment> dbList = baseDao.listByCriteria(criteria, page, orders);

        //附件访问路径
        String attachmentAccessUrl = ConfigManager.getInstance().getValue(RemoteAccess.class, RemoteAccess.ATTACHMENT_IMAGE_ACCESS_URL);

        for (Attachment attachment : dbList) {
            attachment.setPath(attachmentAccessUrl + attachment.getPath());
            attachment.setResizePath(attachmentAccessUrl + attachment.getResizePath());
        }
        return baseDao.listByCriteria(criteria, page, orders);
    }

    @Override
    public Attachment getAttachment(Long attachmentId) throws Exception {
        return baseDao.get(Attachment.class, attachmentId);
    }

    @Override
    public AttachmentHouseVo getAttachmentHouseVo(AttachmentHouseVo attachmentHouseVo) throws Exception {

        //附件访问路径
        String attachmentAccessUrl = ConfigManager.getInstance().getValue(RemoteAccess.class, RemoteAccess.ATTACHMENT_IMAGE_ACCESS_URL);

        //初始化附件对象
        AttachmentHouseVo dbAttachmentHouseVo = new AttachmentHouseVo();
        if (attachmentHouseVo != null && Tools.longIsNotNUllAndZero(attachmentHouseVo.getId())) {
            Attachment attachment = getAttachment(attachmentHouseVo.getId());
            //设置数据
            attachment.toVo(dbAttachmentHouseVo);
            dbAttachmentHouseVo.setPath(attachmentAccessUrl + attachment.getPath());
            dbAttachmentHouseVo.setResizePath(attachmentAccessUrl + attachment.getResizePath());
        }

        return dbAttachmentHouseVo;
    }

    @Override
    public List<AttachmentHouseVo> saveAttachmentList(List<AttachmentHouseVo> attachmentVoList) throws Exception {
        List<AttachmentHouseVo> voList = new ArrayList<>();

        for (AttachmentHouseVo attachmentVo : attachmentVoList) {
            Attachment attachment = new Attachment();
            attachmentVo.toAttachment(attachment, OperatorAction.ADD);
            //首次保存
            baseDao.save(attachment);

            //保存附件
            if (!Tools.isEmpty(attachmentVo.getAttachmentFileString())) {
                saveAttachmentToFileDir(attachment, attachmentVo.getAttachmentFileString());
                //再次保存
                baseDao.update(attachment);
            }

            //添加Vo 到VoList
            voToVoList(attachment, voList);

            //设置子系统的文件路径
            attachment.setSubSystemPath(AttachmentHelper.getSubSystemPath(attachment.getPath()));
            //再次保存
            baseDao.update(attachment);
        }
        return voList;
    }

    @Override
    public List<AttachmentHouseVo> updateAttachmentList(List<AttachmentHouseVo> attachmentVoList) throws Exception {
        List<AttachmentHouseVo> voList = new ArrayList<>();

        for (AttachmentHouseVo attachmentVo : attachmentVoList) {
            //附件ID
            Attachment attachment = getAttachment(attachmentVo.getId());

            attachmentVo.toAttachment(attachment, OperatorAction.MODIFY);

            //保存附件
            if (!Tools.isEmpty(attachmentVo.getAttachmentFileString())) {
                String dbSourcePath = attachment.getPath();
                //首先进行更新附件内容
                saveAttachmentToFileDir(attachment, attachmentVo.getAttachmentFileString());
                //再次删除原附件
                AttachmentHelper.deleteAttachment(AttachmentHelper.SAVE_ROOT_PATH + dbSourcePath);
                //删除缩小图片
                AttachmentHelper.deleteAttachment(AttachmentHelper.getResizePath(dbSourcePath)[CommonPlatformConstant.LENGTH_2]);
            }

            //添加Vo 到VoList
            voToVoList(attachment, voList);

            //设置子系统的文件路径
            attachment.setSubSystemPath(AttachmentHelper.getSubSystemPath(attachment.getPath()));
            //执行更新
            baseDao.update(attachment);
        }
        return voList;
    }

    @Override
    public void updateAttachment(AttachmentHouseVo attachmentHouseVo) throws Exception {
        Attachment attachment = getAttachment(attachmentHouseVo.getId());
        attachmentHouseVo.toAttachment(attachment, OperatorAction.MODIFY);
        baseDao.saveOrUpdate(attachment);
    }

    /**
     * 生成文件名称
     *
     * @param id     附件主键ID
     * @param format 附件格式代码
     * @return 文件名称
     */
    private String generateFileName(Long id, Long format) {
        String timeStamp = DateUtils.formatDateTimestamp(new Date());
        String formatZhCn = ConfigManager.getInstance().getValue(AttachmentFormat.class, format);

        return id + AttachmentHelper.HYPHEN_SYMBOL + timeStamp + AttachmentHelper.EN_PERIOD_SYMBOL + formatZhCn;
    }

    /**
     * 保存附件到文件夹，同时更新数据库数据
     *
     * @param attachment           附件实体
     * @param attachmentFileString 附件二进制内容
     */
    private void saveAttachmentToFileDir(Attachment attachment, String attachmentFileString) {
        try {
            //文件操作
            //真实路径：如：/3/2013/2013-12-03
            StringBuilder realPathBuilder = new StringBuilder(CommonPlatformConstant.LENGTH_512);
            realPathBuilder.append(AttachmentHelper.SLASH_SYMBOL)
                    .append(attachment.getSystemType())
                    .append(AttachmentHelper.SLASH_SYMBOL)
                    .append(AttachmentHelper.getDateDir());
            //附件名称
            String fileName = generateFileName(attachment.getId(), attachment.getFormat());
            String filePath = AttachmentHelper.SAVE_ROOT_PATH + realPathBuilder.toString();
            //生成文件夹
            AttachmentHelper.makeDir(filePath);
            //保存文件
            InputStream is = AttachmentHelper.fileStringToInputStream(attachmentFileString);
            File file = AttachmentHelper.writerFile(is, filePath, fileName);

            //标记附件路径
            realPathBuilder.append(AttachmentHelper.SLASH_SYMBOL).append(fileName);
            attachment.setPath(realPathBuilder.toString());

            //标记附件大小
            attachment.setSize(file.length() / CommonPlatformConstant.LENGTH_1024);

            //只有当为图片时，才缩小
            if (Tools.compareLong(attachment.getFormat(), AttachmentFormat.JPG)) {
                String[] resizePathArray = AttachmentHelper.getResizePath(attachment.getPath());
                //生成缩小图片的文件夹
                AttachmentHelper.makeDir(resizePathArray[0]);
                //保存缩小图片
                InputStream resizeIs = new ByteArrayInputStream(Base64.decode(attachmentFileString.getBytes()));
                AttachmentHelper.writerFile(resizeIs, resizePathArray[0], fileName);
                //标记附件缩小图片的路径
                attachment.setResizePath(resizePathArray[CommonPlatformConstant.LENGTH_3]);
                //缩小图片
                String resizeRealPath = resizePathArray[CommonPlatformConstant.LENGTH_2];
                int currentWidth = Integer.valueOf(ConfigManager.getInstance().getValue(AttachmentConfigure.class, AttachmentConfigure.RESIZE_WIDTH));
                int currentHeight = Integer.valueOf(ConfigManager.getInstance().getValue(AttachmentConfigure.class, AttachmentConfigure.RESIZE_HEIGHT));
                ImageUtils.resizeImage(currentWidth, currentHeight, resizeRealPath, resizeRealPath);
            }


        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage(), e);
        }
    }

    /**
     * 将封装后的VO 添加到VOLIst
     *
     * @param attachment 数据库附件数据对象
     * @param voList     voList
     */
    private void voToVoList(Attachment attachment, List<AttachmentHouseVo> voList) {
        AttachmentHouseVo dbAttachmentHouseVo = new AttachmentHouseVo();
        //如果在数据库里未查询到数据，直接返回。
        if (attachment == null) {
            voList.add(dbAttachmentHouseVo);
            return;
        }

        attachment.toVo(dbAttachmentHouseVo);

        dbAttachmentHouseVo.setPath(attachment.getPath());
        dbAttachmentHouseVo.setSubSystemPath(AttachmentHelper.getSubSystemPath(attachment.getPath()));
        dbAttachmentHouseVo.setSourcePath(attachment.getSubSystemPath());

        //条件1：配置库中配置的是从本地获取数据
        if (ConfigManager.getInstance().getValue(AttachmentConfigure.class, AttachmentConfigure.GET_ATTACHMENT_WAY).equals(ConfigManager.getInstance().getValue(WhetherState.class, WhetherState.NO))
                //条件2：校验 Path
                && AttachmentHelper.verifyAttachmentPath(attachment.getPath(), attachment.getSubSystemPath())) {
            dbAttachmentHouseVo.setWhetherChanged(Long.valueOf(ConfigManager.getInstance().getValue(WhetherState.class, WhetherState.NO)));
        } else {
            dbAttachmentHouseVo.setWhetherChanged(Long.valueOf(ConfigManager.getInstance().getValue(WhetherState.class, WhetherState.YES)));
        }
        voList.add(dbAttachmentHouseVo);
    }
}
