package com.sjdf.platform.attachment.helper;

import com.sjdf.platform.attachment.vo.AttachmentHouseVo;
import com.sjdf.platform.common.utils.DateUtils;
import com.sjdf.platform.common.utils.MD5;
import com.sjdf.platform.common.utils.Tools;
import com.sjdf.platform.common.utils.base64.Base64;
import com.sjdf.platform.dictionary.bean.OperatorAction;
import com.sjdf.platform.dictionary.bean.RemoteAccess;
import com.sjdf.platform.dictionary.bean.WhetherState;
import com.sjdf.platform.dictionary.cache.ConfigManager;
import com.sjdf.platform.log.logger.SJDFLogger;
import com.sjdf.platform.log.logger.SJDFLoggerFactory;
import com.sjdf.platform.net.HttpSocket;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.*;

/**
 * 附件管理器
 *
 * @author sjdf
 */
public abstract class AttachmentManager {
    private static final SJDFLogger LOGGER = SJDFLoggerFactory.getSJDFLogger(AttachmentManager.class);

    public static List<AttachmentHouseVo> doAttachmentSocket(String url, String xml) throws Exception {
        Map<String, String> postData = new HashMap<>();
        String connpwd = ConfigManager.getInstance().getValue(RemoteAccess.class, RemoteAccess.CONNECTION_PASSWORD);
        String vertime = DateUtils.formatDateTimestamp(new Date());
        postData.put("vertime", vertime);
        postData.put("vercode", MD5.md5(new StringBuilder(connpwd).append(vertime).append(xml).toString()));
        postData.put("xml", xml);

        // 初始化
        HttpSocket httpSocket = new HttpSocket();
        httpSocket.setUrl(url);
        httpSocket.setPostData(postData);

        httpSocket.doPost();

        return AttachmentHelper.parse(httpSocket.getResponseData());
    }

    /**
     * 获取附件List
     *
     * @param voConditionList   条件VOList(每个附件获取的条件封装在一个附件VoList中)
     * @param attachmentRootDir 本地服务器存放临时附件的根路径
     * @return 附件VoList(该VoList是Common附件服务器上真实的附件信息)
     */
    public static List<AttachmentHouseVo> getAttachmentList(List<AttachmentHouseVo> voConditionList, String attachmentRootDir) {
        return attachmentList(voConditionList, attachmentRootDir, ConfigManager.getInstance().getValue(RemoteAccess.class, RemoteAccess.COMMON_ATTACHMENT_GET_API), OperatorAction.QUERY);
    }

    /**
     * 更新附件List
     *
     * @param voConditionList   需要被更新的VOList
     * @param attachmentRootDir 本地服务器存放临时附件的根路径
     * @return 附件VoList(该VoList是Common附件服务器上更新后的附件信息)
     */
    public static List<AttachmentHouseVo> modifyAttachmentList(List<AttachmentHouseVo> voConditionList, String attachmentRootDir) {
        return attachmentList(voConditionList, attachmentRootDir, ConfigManager.getInstance().getValue(RemoteAccess.class, RemoteAccess.COMMON_ATTACHMENT_MODIFY_API), OperatorAction.MODIFY);
    }

    /**
     * 保存附件List
     *
     * @param voConditionList   需要被保存的VOList
     * @param attachmentRootDir 本地服务器存放临时附件的根路径
     * @return 附件VoList(该VoList是Common附件服务器上保存后的附件信息)
     */
    public static List<AttachmentHouseVo> saveAttachmentList(List<AttachmentHouseVo> voConditionList, String attachmentRootDir) {
        return attachmentList(voConditionList, attachmentRootDir, ConfigManager.getInstance().getValue(RemoteAccess.class, RemoteAccess.COMMON_ATTACHMENT_STORE_API), OperatorAction.ADD);
    }

    /**
     * 解析文件为String
     *
     * @param voConditionList   来自Common 附件库的 VoList
     * @param attachmentRootDir 本地服务器附件真实路径路径
     */
    private static List<AttachmentHouseVo> attachmentList(List<AttachmentHouseVo> voConditionList, String attachmentRootDir, String url, long operatorAction) {
        try {
            //获取业务公共平台附件数据
            List<AttachmentHouseVo> commonVoList = doAttachmentSocket(url, AttachmentHelper.parse(voConditionList, attachmentRootDir));

            //设置本地的附件base64编码后的String信息，如果附件修改了。则先保存最新的附件到本地，然后返回本地的附件base64编码后的String信息
            for (AttachmentHouseVo attachmentHouseVo : commonVoList) {
                if (Tools.isEmpty(attachmentHouseVo.getSubSystemPath())) {
                    continue;
                }
                //文件名称路径
                String fileAllPath = attachmentRootDir + attachmentHouseVo.getSubSystemPath();
                //附件全路径，不包括文件名称
                String directory = fileAllPath.substring(0, fileAllPath.lastIndexOf(AttachmentHelper.SLASH_SYMBOL));
                //生成保存附件根路径
                AttachmentHelper.makeDir(directory);
                //文件名称
                String fileName = fileAllPath.substring(fileAllPath.lastIndexOf(AttachmentHelper.SLASH_SYMBOL) + 1);

                //表示当前附件更新过，需要保存后，再从本地获取
                if (Tools.compareLong(attachmentHouseVo.getWhetherChanged(), Long.valueOf(ConfigManager.getInstance().getValue(WhetherState.class, WhetherState.YES)))) {
                    if (!Tools.compareLong(operatorAction, OperatorAction.QUERY)) {
                        //保存文件,写入到本地服务器
                        if (!Tools.isEmpty(attachmentHouseVo.getAttachmentFileString())) {
                            InputStream is = new ByteArrayInputStream(Base64.decode(attachmentHouseVo.getAttachmentFileString().getBytes()));
                            AttachmentHelper.writerFile(is, directory, fileName);
                        }

                        //如果是更新，并更改了附件，则删除原文件
                        if (Tools.compareLong(operatorAction, OperatorAction.MODIFY)) {
                            AttachmentHelper.deleteAttachment(attachmentRootDir + attachmentHouseVo.getSourcePath());
                        }
                    }
                    //表示当前附件未更新过，直接本地获取
                } else {
                    attachmentHouseVo.selfSetAttachmentFileString(fileAllPath);
                }
            }
            return commonVoList;
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage(), e);
        }
        return Collections.emptyList();
    }
}
