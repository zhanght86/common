package com.sjdf.platform.dictionary.bean;

import com.sjdf.platform.common.annotations.BeanName;
import com.sjdf.platform.dictionary.annotations.BeanAttrInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 2012-09-24
 *
 * @author 王正伟
 * @category 财务发票金额过滤
 */
@Entity
@DiscriminatorValue("INVOICE_FILTER")
@BeanName(name = "财务发票金额过滤")
public class InvoiceFilter extends Dictionary {
    private static final long serialVersionUID = -4920979432979971154L;

    /** 代理客户不计入可开票额产品分类信息 */
    @BeanAttrInfo(value = "Domain,IDC,Other", cnName = "代理客户不计入可开票额产品分类信息")
    public static final long AGENT_TYPE_INVOICE_FILTER = 1;

    /** 直接客户不计入可开票额产品分类信息 */
    @BeanAttrInfo(value = "IDC,Other", cnName = "直接客户不计入可开票额产品分类信息")
    public static final long DIRECT_TYPE_INVOICE_FILTER = 2;

    /** 不计入所有消费的发票的用户组id列表 */
    @BeanAttrInfo(value = "11,15", cnName = "不计入所有消费的发票的用户组id列表")
    public static final long ALL_GROUP_IDLIS = 3;

    /** 不计入所有消费的发票的用户组id列表 */
    @BeanAttrInfo(value = "8", cnName = "不计入云主机消费的发票的用户组id列表")
    public static final long CHOST_GROUP_IDLIS = 4;

    public List<String> getValue2StrList() {
        if (getValue() != null && !"".equals(getValue())) {
            return Arrays.asList(getValue().split(","));
        }
        return Collections.emptyList();
    }

    public List<Long> getValue2LongList() {
        if (getValue() != null && !"".equals(getValue())) {
            List<Long> idList = new ArrayList<>();
            String[] strs = getValue().split(",");
            for (String id : strs) {
                try {
                    idList.add(Long.parseLong(id.trim()));
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
            return idList;
        }
        return Collections.emptyList();
    }
}
