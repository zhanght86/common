package com.sjdf.platform.common.vo;


/**
 * 2012-8-30 下午1:58:33
 * 搜索公共类
 *
 * @author frank
 */
public class SearchVo {
    /** 展示信息--ID */
    private Long displayInfoId;
    /** 展示信息--内容 */
    private String displayInfoContent;
    /** 展示信息--信息类别 */
    private Long displayInfoInfoType;
    /** 展示信息--所属类别 */
    private String displayInfoOwnerType;
    /** 展示信息--创建时间开始 */
    private String beginOfCreateDate;
    /** 展示信息--创建时间结束 */
    private String endOfCreateDate;
    /** 展示信息--更新时间开始 */
    private String beginOfupdateDate;
    /** 展示信息--更新时间结束 */
    private String endOfUpdateDate;

    public Long getDisplayInfoId() {
        return displayInfoId;
    }

    public void setDisplayInfoId(Long displayInfoId) {
        this.displayInfoId = displayInfoId;
    }

    public String getDisplayInfoContent() {
        return displayInfoContent;
    }

    public void setDisplayInfoContent(String displayInfoContent) {
        this.displayInfoContent = displayInfoContent;
    }

    public Long getDisplayInfoInfoType() {
        return displayInfoInfoType;
    }

    public void setDisplayInfoInfoType(Long displayInfoInfoType) {
        this.displayInfoInfoType = displayInfoInfoType;
    }

    public String getDisplayInfoOwnerType() {
        return displayInfoOwnerType;
    }

    public void setDisplayInfoOwnerType(String displayInfoOwnerType) {
        this.displayInfoOwnerType = displayInfoOwnerType;
    }

    public String getBeginOfCreateDate() {
        return beginOfCreateDate;
    }

    public void setBeginOfCreateDate(String beginOfCreateDate) {
        this.beginOfCreateDate = beginOfCreateDate;
    }

    public String getEndOfCreateDate() {
        return endOfCreateDate;
    }

    public void setEndOfCreateDate(String endOfCreateDate) {
        this.endOfCreateDate = endOfCreateDate;
    }

    public String getBeginOfupdateDate() {
        return beginOfupdateDate;
    }

    public void setBeginOfupdateDate(String beginOfupdateDate) {
        this.beginOfupdateDate = beginOfupdateDate;
    }

    public String getEndOfUpdateDate() {
        return endOfUpdateDate;
    }

    public void setEndOfUpdateDate(String endOfUpdateDate) {
        this.endOfUpdateDate = endOfUpdateDate;
    }
}
