package com.sjdf.platform.print.bean;

import com.sjdf.platform.common.annotations.ModifyRecord;
import com.sjdf.platform.common.bean.BaseBean;
import com.sjdf.platform.common.helper.Message;
import com.sjdf.platform.dictionary.bean.CompanyInfo;
import com.sjdf.platform.dictionary.cache.ConfigManager;
import org.springframework.util.StringUtils;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Create at 2012-11-05
 * 打印地址
 *
 * @author ketqi
 */
@Entity
@Table(name = "p_print_address")
public class PrintAddress extends BaseBean {
    private static final long serialVersionUID = -191995194735559761L;
    /** 所在省份 */
    private String province;
    /** 收寄局 */
    private String originalOffic;
    /** 日期 */
    private String date;
    /** 收寄件人姓名 */
    private String name;
    /** 座机电话 */
    private String phone;
    /** 移动电话 */
    private String mobilePhone;
    /** 单位名称 */
    private String companyName;
    /** 地址 */
    private String address;
    /** 内件品名 */
    private String contentName;
    /** 数量 */
    private String amount;
    /** 邮编 */
    private String post;

    @ModifyRecord(name = "所在省份")
    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    @ModifyRecord(name = "收寄局")
    public String getOriginalOffic() {
        return originalOffic;
    }

    public void setOriginalOffic(String originalOffic) {
        this.originalOffic = originalOffic;
    }

    @ModifyRecord(name = "日期")
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @ModifyRecord(name = "收寄件人姓名")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ModifyRecord(name = "座机电话")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @ModifyRecord(name = "移动电话")
    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    @ModifyRecord(name = "单位名称")
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @ModifyRecord(name = "地址")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @ModifyRecord(name = "内件品名")
    public String getContentName() {
        return contentName;
    }

    public void setContentName(String contentName) {
        this.contentName = contentName;
    }

    @ModifyRecord(name = "数量")
    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    @ModifyRecord(name = "邮编")
    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    /** 创建默认打印地址 */
    public static PrintAddress getDefaultAddressVo() {
        PrintAddress addressVo = new PrintAddress();
        addressVo.setDate(new SimpleDateFormat("yyyy  MM  dd").format(new Date()));
        addressVo.setAddress(ConfigManager.getInstance().getValue(CompanyInfo.class, CompanyInfo.EXPRESS_DELIVERY_ADDRESS));
        //addressVo.setCompanyName(ConfigManager.getInstance().getValue(CompanyInfo.class, CompanyInfo.EXPRESS_DELIVERY_COMPANY_NAME));
        addressVo.setName(ConfigManager.getInstance().getValue(CompanyInfo.class, CompanyInfo.EXPRESS_DELIVERY_FROM));
        addressVo.setOriginalOffic(ConfigManager.getInstance().getValue(CompanyInfo.class, CompanyInfo.EXPRESS_DELIVERY_CITY));
        addressVo.setPhone(ConfigManager.getInstance().getValue(CompanyInfo.class, CompanyInfo.EXPRESS_DELIVERY_PHONE));
        addressVo.setMobilePhone(ConfigManager.getInstance().getValue(CompanyInfo.class, CompanyInfo.EXPRESS_DELIVERY_MOBILE_PHONE));
        addressVo.setProvince(ConfigManager.getInstance().getValue(CompanyInfo.class, CompanyInfo.EXPRESS_DELIVERY_PROVINCE));
        return addressVo;
    }

    /** 验证 */
    public static Message validate(PrintAddress addressVo) {
        if (addressVo == null) {
            return Message.createMessage("print.addressVo.null");
        }

        if (!StringUtils.hasText(addressVo.getName())) {
            return Message.createMessage("print.addressVo.name.null");
        }

        if (!StringUtils.hasText(addressVo.getCompanyName())) {
            return Message.createMessage("print.addressVo.companyName.null");
        }

        if (!StringUtils.hasText(addressVo.getAddress())) {
            return Message.createMessage("print.addressVo.address.null");
        }

        return Message.createEmptyMessage();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PrintAddress{");
        sb.append("province='").append(province).append('\'');
        sb.append(", originalOffic='").append(originalOffic).append('\'');
        sb.append(", date='").append(date).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", phone='").append(phone).append('\'');
        sb.append(", mobilePhone='").append(mobilePhone).append('\'');
        sb.append(", companyName='").append(companyName).append('\'');
        sb.append(", address='").append(address).append('\'');
        sb.append(", contentName='").append(contentName).append('\'');
        sb.append(", amount='").append(amount).append('\'');
        sb.append(", post='").append(post).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
