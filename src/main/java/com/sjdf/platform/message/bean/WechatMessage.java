package com.sjdf.platform.message.bean;

import com.sjdf.platform.CommonPlatformConstant;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 2015-12-09
 *
 * @author wangpeng
 * @category 微信信息
 */
@Entity
@Table(name = "p_message_wechat")
public class WechatMessage extends BaseMessage implements Cloneable {

    private static final long serialVersionUID = 6760200437042436360L;

    /**
     * @category 是否向所有用户群发消息
     */
    private boolean isToAll;

    /**
     * @category 微信群发消息类型
     * @see com.sjdf.platform.message.constant.MassMessageType
     */
    private String massType;

    /**
     * @category 图文消息的标题
     */
    private String title;

    /**
     * @category 图文信息媒体ID号
     */
    private String mediaId;

    @Override
    public WechatMessage clone() {
        WechatMessage message = null;
        try {
            message = (WechatMessage) super.clone();
        } catch (CloneNotSupportedException e) {
            logger.error(e.getMessage(), e);
        }
        return message;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(CommonPlatformConstant.LENGTH_1024);
        sb.append("WechatMessage{");
        sb.append(super.toString());
        sb.append(", massType='").append(massType).append('\'');
        sb.append(", isToAll=").append(isToAll);
        sb.append(", title='").append(title).append('\'');
        sb.append(", mediaId='").append(mediaId).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public String getMassType() {
        return massType;
    }

    public void setMassType(String massType) {
        this.massType = massType;
    }

    public boolean isToAll() {
        return isToAll;
    }

    public void setToAll(boolean isToAll) {
        this.isToAll = isToAll;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

}
