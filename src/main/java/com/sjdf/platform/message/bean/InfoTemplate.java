package com.sjdf.platform.message.bean;

import com.sjdf.platform.rbac.bean.TreeNode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 信息模板
 * User: ketqi
 * Date: 2015-07-15 10:27
 */
@Entity
@Table(name = "p_message_info_template")
public class InfoTemplate extends TreeNode<InfoTemplate> {
    private static final long serialVersionUID = -3518997679313907789L;
    /**
     * 消息类型
     *
     * @see com.sjdf.platform.dictionary.bean.common.MessageType
     */
    private long messageType;
    /** 标题 */
    @Column(columnDefinition = "longtext")
    private String title;

    /** 内容 */
    @Column(columnDefinition = "longtext")
    private String content;

    public long getMessageType() {
        return messageType;
    }

    public void setMessageType(long messageType) {
        this.messageType = messageType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("InfoTemplate{");
        sb.append("name='").append(getName()).append('\'');
        sb.append(", title='").append(title).append('\'');
        sb.append(", content='").append(content).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
