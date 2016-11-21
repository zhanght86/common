package com.sjdf.platform.common.utils;

import com.sjdf.platform.CommonPlatformConstant;
import org.hibernate.criterion.Order;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 用于在处理记录信息时, 对可能的庞大信息进行分页处理的分页组件.
 * Create at 2012-04-06
 *
 * @author 王正伟
 */
public class Page implements Serializable {
    private static final long serialVersionUID = 7976554253297130816L;

    /** 当前页数,默认为第一页 * */
    private int currentPage = 1;
    /** 每页显示的记录数 默认为 20条记录 * */
    private int pageSize = CommonPlatformConstant.LENGTH_20;
    /** 总记录数 * */
    private int totalCount;
    /** 页面数据复选框ID，格式为【12|13|14】，如果为【ALL】则表示所有数据 */
    private String checkId;
    /** 排序字段 */
    private String orderBy;
    /** 排序方式 */
    private String orderType;

    /** 得到当前页面 */
    public int getCurrentPage() {
        return currentPage;
    }

    /** 设置当前页面 */
    public void setCurrentPage(int currentPage) {
        if (currentPage <= 0) {
            return;
        }
        this.currentPage = currentPage;
    }

    /** 得到页面大小,一张页面上的记录数 */
    public int getPageSize() {
        return pageSize;
    }

    /** 设置单页记录数 */
    public void setPageSize(int pageSize) {
        if (pageSize <= 0) {
            return;
        }
        this.pageSize = pageSize;
    }

    /** 得到总记录数 */
    public int getTotalCount() {
        return totalCount;
    }

    /** 设置总记录数 */
    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    /**
     * 得到起始记录,此记录是当前显示数据的第一条记录的前一条记录,即前一页记录页的最后一条记录
     * 因为setCurrentPage方法的存在,使得不可能出现计算得出起始记录为负数的情况,此结果返回的 最小起始记录为 0
     */
    public int getStartIndex() {
        return (currentPage - 1) * pageSize;
    }

    /** 得到总的记录页面数,此结果页每页显示的记录数与总记录数之间的关系计算得出 * */
    public int getTotalPage() {
        int i = totalCount / pageSize;
        int j = totalCount % pageSize;
        return i + (j == 0 ? 0 : 1);
    }

    /** 是否有上一页 * */
    public boolean isHasPreviousPage() {
        return currentPage > 1;
    }

    /** 是否有下一页 * */
    public boolean isHasNextPage() {
        return currentPage < getTotalPage();
    }

    /** 【全选所有记录】【本页全选】【本页多选】复选框筛选条件 */
    public List<Long> getCheckIdList() {
        List<Long> checkIdList = new ArrayList<>();
        if (PlatformUtils.hasText(getCheckId()) && !"ALL".equals(getCheckId())) {
            String[] checkIdArray = getCheckId().split("\\|");
            for (String id : checkIdArray) {
                if (PlatformUtils.hasText(id)) {
                    checkIdList.add(Long.valueOf(id));
                }
            }
        }
        return checkIdList;
    }

    /**
     * 构建分页排序数组
     *
     * @param page   分页组件
     * @param isAsc  是否升序
     * @param params 字段列表
     * @return 排序数组
     */
    public static Order[] buildOrderArrays(Page page, boolean isAsc, String... params) {
        List<Order> orderList = new ArrayList<>();
        if (page != null && !Tools.isEmpty(page.getOrderBy())) {
            String[] columns = page.getOrderBy().split(";");
            if ("desc".equalsIgnoreCase(page.getOrderBy())) {
                for (String col : columns) {
                    orderList.add(Order.desc(col));
                }
            } else {
                for (String col : columns) {
                    orderList.add(Order.asc(col));
                }
            }
        } else {
            if (isAsc) {
                if (params != null) {
                    for (String param : params) {
                        orderList.add(Order.asc(param));
                    }
                }
            } else {
                if (params != null) {
                    for (String param : params) {
                        orderList.add(Order.desc(param));
                    }
                }
            }
        }
        Order[] orders = new Order[orderList.size()];

        return orderList.toArray(orders);
    }

    public String toXml() {
        StringBuilder xml = new StringBuilder();
        xml.append("<currentPage><![CDATA[").append(currentPage).append("]]></currentPage>");
        xml.append("<pageSize><![CDATA[").append(pageSize).append("]]></pageSize>");
        xml.append("<totalCount><![CDATA[").append(totalCount).append("]]></totalCount>");
        return xml.toString();
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getCheckId() {
        return checkId;
    }

    public void setCheckId(String checkId) {
        this.checkId = checkId;
    }

    public String orderByInfo() {
        return (getOrderBy() != null && !"".equals(getOrderBy())) ? getOrderBy() : "";
    }

    public String orderTypeInfo() {
        return (getOrderType() != null && !"".equals(getOrderType())) ? getOrderType() : "";
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(CommonPlatformConstant.LENGTH_64);
        sb.append("Page{");
        sb.append("totalCount=").append(totalCount);
        sb.append(", pageSize=").append(pageSize);
        sb.append(", currentPage=").append(currentPage);
        sb.append('}');
        return sb.toString();
    }
}
