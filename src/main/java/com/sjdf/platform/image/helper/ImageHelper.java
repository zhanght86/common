package com.sjdf.platform.image.helper;

import com.sjdf.platform.CommonPlatformConstant;
import com.sjdf.platform.attachment.helper.AttachmentHelper;
import com.sjdf.platform.common.utils.FileUtils;
import com.sjdf.platform.common.utils.ResultVo;
import com.sjdf.platform.dictionary.bean.ConstGlobal;
import com.sjdf.platform.dictionary.cache.ConfigManager;
import com.sjdf.platform.image.vo.ImageVo;
import com.sjdf.platform.log.logger.SJDFLogger;
import com.sjdf.platform.log.logger.SJDFLoggerFactory;
import com.sjdf.platform.pageContent.helper.PageContentHelper;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.util.StringUtils;

import java.io.*;
import java.util.List;

/**
 * @author sjdf
 * @category 图片辅助
 */
public abstract class ImageHelper {

    /**
     * @category 日志记录器
     */
    private static SJDFLogger logger = SJDFLoggerFactory.getSJDFLogger(ImageVo.class);

    public static String getImageUrl(String imageUrl) {
        // /home/wwwroot/new_static_51web/common/images/upload/pageContentManage
        String physical = ConfigManager.getInstance().getValue(ConstGlobal.class, ConstGlobal.STATIC1_51WEB_PHYSICAL_ADDRESS);
        String web = ConfigManager.getInstance().getValue(ConstGlobal.class, ConstGlobal.STATIC1_51WEB_WEB_ADDRESS);
        String[] sss = imageUrl.split(physical);
        return web + sss[sss.length - 1];
    }

    public static void saveOrUpdateImage(String xml) throws Exception {
        // 解析xml数据
        ImageVo vo = xmlToImageVo(xml);
        if (vo != null && StringUtils.hasText(vo.getSavePath())) {
            try {
                // 删除老图片
                if (StringUtils.hasText(vo.getOldImage())) {
                    File file = new File(vo.getSavePath() + vo.getOldImage());
                    file.delete();
                }
            } catch (Exception e) {
                logger.error("删除老图片", e);
            }

            // 添加图片
            if (StringUtils.hasText(vo.getNewImage()) && StringUtils.hasText(vo.getImgByte())) {
                File file = FileUtils.createFile(vo.getSavePath() + vo.getNewImage());
                try (InputStream in = AttachmentHelper.fileStringToInputStream(vo.getImgByte());
                     FileOutputStream fos = new FileOutputStream(file);
                     BufferedInputStream bis = new BufferedInputStream(in);) {
                    int bufferSize = CommonPlatformConstant.LENGTH_1024;
                    byte[] buf = new byte[bufferSize];
                    int size;
                    while ((size = bis.read(buf)) != -1) {
                        fos.write(buf, 0, size);
                    }
                } catch (Exception e) {
                    logger.error("添加图片", e);
                    throw e;
                }
            }
        }
    }

    /**
     * @param xml
     * @return
     * @category xml数据转化为图片对象
     */
    public static ImageVo xmlToImageVo(String xml) {
        ImageVo vo = null;
        if (StringUtils.hasText(xml)) {
            SAXReader saxReader = new SAXReader();
            try (InputStream is = new ByteArrayInputStream(xml.getBytes("UTF-8"))) {
                Document document = saxReader.read(is);
                Element root = (Element) document.selectSingleNode("/image");
                @SuppressWarnings("unchecked")
                List<Element> elementList = root.elements();
                if (elementList != null && !elementList.isEmpty()) {
                    vo = new ImageVo();
                    for (Element el : elementList) {
                        if ("savePath".equals(el.getName())) {
                            vo.setSavePath(el.getData().toString());
                        } else if ("oldImage".equals(el.getName())) {
                            vo.setOldImage(el.getData().toString());
                        } else if ("newImage".equals(el.getName())) {
                            vo.setNewImage(el.getData().toString());
                        } else if ("imgByte".equals(el.getName())) {
                            vo.setImgByte(el.getData().toString());
                        }
                    }
                }
            } catch (Exception e) {
                logger.error("xml数据转化为图片对象", e);
            }
        }

        return vo;
    }

    /**
     * @param url
     * @param xmlData
     * @return
     * @category 推送数据
     */
    public static ResultVo pushData(String url, String xmlData) {
        return PageContentHelper.pushData(url, xmlData);
    }

}
