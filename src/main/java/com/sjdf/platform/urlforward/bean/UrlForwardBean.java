package com.sjdf.platform.urlforward.bean;

import com.sjdf.platform.common.bean.BaseBean;
import org.hibernate.annotations.Index;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author laberwu
 * @category 域名转发记录
 * @ClassName UrlForwardBean
 * @Created 2012 2012-10-12 上午10:03:36
 */
@Entity
@Table(name = "p_url_forward")
public class UrlForwardBean extends BaseBean {

    /**
     * @category
     * @field long serialVersionUID
     * @created 2012 2012-10-12 上午10:04:14
     */
    private static final long serialVersionUID = 4057390607024197041L;

    /**
     * @category 域名
     */
    @Index(name = "domain")
    private String domain;

    /**
     * @category url 转发记录
     */
    private String url;

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "UrlForwardBean [id=" + getId() + ",domain=" + domain + ", url=" + url + "]";
    }

}
