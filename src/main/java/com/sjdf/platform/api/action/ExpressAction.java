package com.sjdf.platform.api.action;

import com.sjdf.platform.CommonPlatformConstant;
import com.sjdf.platform.common.action.BaseAction;
import com.sjdf.platform.common.utils.DateUtils;
import com.sjdf.platform.common.utils.MD5;
import com.sjdf.platform.dictionary.bean.RemoteAccess;
import com.sjdf.platform.dictionary.cache.ConfigManager;
import com.sjdf.platform.print.bean.PrintAddress;
import com.sjdf.platform.print.service.PrintAddressService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
 * Create at 2012-04-12
 * <pre>
 * yto:圆通速递物流
 * ems:EMS国内特快专递邮件
 * sf:顺丰
 * ordinary:普通平信
 *
 * <pre>
 *  快递和信封打印
 * @author 王正伟
 */
public class ExpressAction extends BaseAction {
    private static final long serialVersionUID = -6648800766605760119L;
    @Autowired
    private PrintAddressService printAddressService;
    private PrintAddress fromAddressVo;
    private PrintAddress toAddressVo;
    /** 预览打印标志位;1:预览,2:选择打印,3:直接打印 */
    private int flag;
    /**
     * <pre>
     * yto:圆通速递物流
     * ems:EMS国内特快专递邮件
     * sf:顺丰
     * ordinary:普通平信
     *  打印类型
     *
     * <pre>
     */
    private String type;
    /** 打印内容 */
    private List<String> dataList;

    public String print() {
        // 连接密码
        String connpwd = ConfigManager.getInstance().getValue(RemoteAccess.class, RemoteAccess.CONNECTION_PASSWORD);

        // 验证数据有效性
        StringBuilder valid = new StringBuilder();
        valid.append(connpwd).append(vertime);
        if (!MD5.md5(valid.toString()).equals(vercode)) {
            printWrite("status:-1;result:连接密码验证失败");
            return NONE;
        }

        // 1:预览,2:选择打印,3:直接打印
        if (flag != CommonPlatformConstant.LENGTH_2) {
            // 本地地址
            if (fromAddressVo == null) {
                fromAddressVo = PrintAddress.getDefaultAddressVo();
            }
            if (toAddressVo != null && toAddressVo.getId() > 0) {
                toAddressVo = printAddressService.get(PrintAddress.class, toAddressVo.getId());
            }
        }

        return type;
    }

    /** 获取vercode和vertime封装的验证url字符串 */
    public String getVercodeAndCertime() {
        String vertime = DateUtils.formatTimestamp(new Date());
        String connpwd = ConfigManager.getInstance().getValue(RemoteAccess.class, RemoteAccess.CONNECTION_PASSWORD);
        String vercode = MD5.md5(connpwd + vertime);
        return new StringBuilder(CommonPlatformConstant.LENGTH_100).append("vercode=").append(vercode).append("&vertime=").append(vertime).toString();
    }

    public PrintAddress getFromAddressVo() {
        return fromAddressVo;
    }

    public void setFromAddressVo(PrintAddress fromAddressVo) {
        this.fromAddressVo = fromAddressVo;
    }

    public PrintAddress getToAddressVo() {
        return toAddressVo;
    }

    public void setToAddressVo(PrintAddress toAddressVo) {
        this.toAddressVo = toAddressVo;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getDataList() {
        return dataList;
    }

    public void setDataList(List<String> dataList) {
        this.dataList = dataList;
    }
}
