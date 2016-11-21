package com.sjdf.platform.common.vo;

import java.util.Date;

import com.sjdf.platform.common.constant.WeChatConst;
import com.sjdf.platform.common.utils.PlatformUtils;

/**
 * 
 * @category 微信二维码Vo
 * @author wangpeng
 *
 */
public class WeChatQrcodeVo {

    /**
     * @category 错误返回码
     */
    private long errcode;

    /**
     * @category 错误消息
     */
    private String errmsg;

    /**
     * @category 获取的二维码ticket，凭借此ticket可以在有效时间内换取二维码。
     */
    private String ticket;

    /**
     * @category 二维码图片解析后的地址，开发者可根据该地址自行生成需要的二维码图片
     */
    private String url;

    /**
     * @category 会员名称
     */
    private String userName;
    
    /**
     * @category 是否能够使用微信登录
     */
    private Boolean checkPass = null;

    /**
     * @category 更新时间
     */
    private Date updateTime = new Date();

    public long getErrcode() {
        return errcode;
    }

    public void setErrcode(long errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Boolean getCheckPass() {
        return checkPass;
    }

    public void setCheckPass(Boolean checkPass) {
        this.checkPass = checkPass;
    }

    public String exchangeUrlWithTicket() {
        if (PlatformUtils.hasText(ticket)) {
            return WeChatConst.QRCODE_URL_TEMPLATE.replace("TICKET", ticket);
        }
        return null;
    }

    /**
     * @category 二维码是否已经过期
     * @return
     */
    public boolean isExpired () {
        return new Date().getTime() - updateTime.getTime() > WeChatConst.EXPIRE_SECONDS;
    }

    @Override
    public String toString() {
        return "WeChatQrcodeVo [errcode=" + errcode + ", errmsg=" + errmsg
                + ", ticket=" + ticket + ", url=" + url + ", userName="
                + userName + ", checkPass=" + checkPass + ", updateTime="
                + updateTime + "]";
    }
}
