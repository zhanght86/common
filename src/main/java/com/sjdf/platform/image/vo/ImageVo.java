package com.sjdf.platform.image.vo;

import com.sjdf.platform.attachment.helper.AttachmentHelper;

import java.io.File;

/**
 * @author sjdf
 * @category 图片vo对象
 */
public class ImageVo {
    /**
     * @category xml转义前缀
     */
    private static final String PRE_CDATA = "<![CDATA[";
    /**
     * @category xml转义后缀
     */
    private static final String SUF_CDATA = "]]>";

    /**
     * @category 保存图片物理目录
     */
    private String savePath;
    /**
     * @category 旧的图片名称
     */
    private String oldImage;
    /**
     * @category 新的图片名称
     */
    private String newImage;
    /**
     * @category 图片内容
     */
    private File file;
    /**
     * @category 图片内容
     */
    private String imgByte;

    public String getSavePath() {
        return savePath;
    }

    public void setSavePath(String savePath) {
        this.savePath = savePath;
    }

    public String getOldImage() {
        return oldImage;
    }

    public void setOldImage(String oldImage) {
        this.oldImage = oldImage;
    }

    public String getNewImage() {
        return newImage;
    }

    public void setNewImage(String newImage) {
        this.newImage = newImage;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getImgByte() {
        return imgByte;
    }

    public void setImgByte(String imgByte) {
        this.imgByte = imgByte;
    }

    /**
     * @return
     * @category 对象转化成xml
     */
    public String imageVotoXml() {
        StringBuilder xml = new StringBuilder();
        xml.append("<image>");
        xml.append("<savePath>").append(PRE_CDATA).append(savePath).append(SUF_CDATA).append("</savePath>");
        xml.append("<oldImage>").append(PRE_CDATA).append(oldImage).append(SUF_CDATA).append("</oldImage>");
        xml.append("<newImage>").append(PRE_CDATA).append(newImage).append(SUF_CDATA).append("</newImage>");
        if (file != null) {
            xml.append("<imgByte>").append(PRE_CDATA).append(AttachmentHelper.getFileString(file)).append(SUF_CDATA).append("</imgByte>"); // 图片内容
        } else {
            xml.append("<imgByte>").append(PRE_CDATA).append(SUF_CDATA).append("</imgByte>"); // 图片内容
        }

        xml.append("</image>");

        return xml.toString();
    }

}
