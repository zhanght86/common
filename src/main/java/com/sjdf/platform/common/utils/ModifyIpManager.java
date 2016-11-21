package com.sjdf.platform.common.utils;

import com.sjdf.platform.common.helper.ModifyIpHelper;
import com.sjdf.platform.common.vo.ProductAndDomainVo;
import com.sjdf.platform.dictionary.bean.RemoteAccess;
import com.sjdf.platform.dictionary.cache.ConfigManager;
import com.sjdf.platform.net.HttpSocket;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Create at 2013-5-15 上午10:40:02
 * <p/>
 * 变更IP工具类
 *
 * @author frank
 */
public class ModifyIpManager {
    /** 产品类别 代码 */
    public static final long V_HOST = 1L;
    public static final long VPS = 2L;
    public static final long IDC = 3L;
    public static final long CLOUD_HOST = 7L;

    /** 接口变更IP返回状态码 */
    public static final long API_RETURN_CODE_SUCCESS = 1;
    public static final long API_RETURN_CODE_FAILED = 0;
    public static final long API_RETURN_CODE_EXCEPTION = -1;


    /**
     * 指定连接变更IP超时时间
     */
    private int timeOut;

    /**
     * 执行远程访问
     *
     * @param xml 远程访问XML参数
     * @return 访问后返回的XML数据
     * @throws Throwable
     */
    private String doSocket(String xml) throws Throwable {

        //初始化变更IP远程访问参数
        Map<String, String> postData = new HashMap<String, String>();
        String connpwd = ConfigManager.getInstance().getValue(RemoteAccess.class, RemoteAccess.CONNECTION_PASSWORD);
        String vertime = DateUtils.formatDateTimestamp(new Date());
        postData.put("currentConnPwd", connpwd);
        postData.put("timeStamp", vertime);
        postData.put("checkSum", MD5.md5(new StringBuilder(xml).append(connpwd).append(vertime).toString()));
        postData.put("xml", xml);
        //请求URL
        String url = ConfigManager.getInstance().getValue(RemoteAccess.class, RemoteAccess.RECORD_APIACTION_MODIFY_IP_OF_WEB_INFO);

        HttpSocket httpSocket = new HttpSocket();
        httpSocket.setUrl(url);//设置请求URL
        httpSocket.setPostData(postData);//设置请求数据
        httpSocket.setTimeout(timeOut);//设置超时时间
        httpSocket.setSendRedirect(true);//设置重定向

        httpSocket.doPost();

        //返回数据
        return httpSocket.getResponseData();
    }

    /**
     * 根据产品域名VOList进行变更IP
     *
     * @param voList 产品域名VOList
     * @return 产品域名VOList（变更是否成功）
     * @throws Throwable
     */
    public List<ProductAndDomainVo> modifyIp(List<ProductAndDomainVo> voList) throws Throwable {
        return ModifyIpHelper.parse(doSocket(ModifyIpHelper.parse(voList, true)));
    }

    public int getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(int timeOut) {
        this.timeOut = timeOut;
    }

}
