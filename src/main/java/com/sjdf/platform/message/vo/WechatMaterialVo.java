package com.sjdf.platform.message.vo;

/**
 * @author wangpeng
 * @category 微信图文素材vo
 */
public class WechatMaterialVo {

    /**
     * @category 图文素材的媒体ID号
     */
    private String mediaId;

    /**
     * @category 第一条图文的标题
     */
    private String title;

    public WechatMaterialVo() {

    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
