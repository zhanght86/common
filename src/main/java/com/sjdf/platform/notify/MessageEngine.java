package com.sjdf.platform.notify;

import com.sjdf.platform.CommonPlatformConstant;
import com.sjdf.platform.common.helper.ApplicationContextManager;
import com.sjdf.platform.dictionary.bean.ExecuteStatus;
import com.sjdf.platform.dictionary.bean.MessageEngineConfig;
import com.sjdf.platform.dictionary.cache.ConfigManager;
import com.sjdf.platform.log.logger.SJDFLogger;
import com.sjdf.platform.log.logger.SJDFLoggerFactory;
import com.sjdf.platform.net.HttpSocket;
import com.sjdf.platform.notify.bean.NotifyTrack;
import com.sjdf.platform.notify.service.NotifyTrackService;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 2013-1-8 上午11:45:23
 * 消息引擎
 *
 * @author ketqi
 */
public final class MessageEngine {
    private static final SJDFLogger LOGGER = SJDFLoggerFactory.getSJDFLogger(MessageEngine.class);

    // 成功代码
    public static final int SUCCESS_CODE = 1000;
    // 失败代码
    public static final int FAIL_CODE = 2000;

    private static MessageEngine instance = new MessageEngine();
    private static NotifyTrackService notifyTrackService;

    private MessageEngine() {
        try {
            notifyTrackService = (NotifyTrackService) ApplicationContextManager.getBean("notifyTrackServiceImpl");
        } catch (Exception e) {
            throw new RuntimeException("消息引擎所需notifyTrackService不能为空!", e);
        }
    }

    public static MessageEngine getInstance() {
        return instance;
    }

    /**
     * 异步推送一类通知;如推送配置库修改记录到所有引用平台
     *
     * @param notifyTrack 通知跟踪记录
     */
    public void notifyAsyn(final NotifyTrack notifyTrack) {
        new Thread() {
            @Override
            public void run() {
                instance.notify(notifyTrackService, notifyTrack);
            }
        }.start();
    }

    /**
     * 推送一类通知;如推送配置库修改记录到所有引用平台
     *
     * @param notifyTrack 通知跟踪记录
     */
    public synchronized void notify(NotifyTrackService notifyTrackService, NotifyTrack notifyTrack) {
        if (notifyTrack == null) {
            return;
        }

        NotifyTrack parent = notifyTrack.getParent();
        if (parent == null) {
            MessageEngineConfig mec = ConfigManager.getInstance().getDictionary(MessageEngineConfig.class, notifyTrack.getMec());
            if (mec.getValue() != null && !"".equals(mec.getValue())) {
                String[] urls = mec.getValue().split(";");
                for (String url : urls) {
                    ResultVo resultVo = notify(mec.getAttr(), notifyTrack.getOperateType(), url, notifyTrack.getData(), notifyTrack.getExtendDataMap());
                    if (resultVo.code == FAIL_CODE) {
                        NotifyTrack temp = new NotifyTrack(notifyTrack, url, resultVo.msg);
                        notifyTrackService.save(temp);
                    }
                }
                notifyTrack.setStatus(ExecuteStatus.COMPLTED);
                notifyTrackService.update(notifyTrack);
            }
        } else {
            if (notifyTrack.getUrl() != null && !"".equals(notifyTrack.getUrl())) {
                ResultVo resultVo = notify(notifyTrack.getMec(), notifyTrack.getOperateType(), notifyTrack.getUrl(), parent.getData(), parent.getExtendDataMap());
                if (resultVo.code == SUCCESS_CODE) {
                    notifyTrack.setStatus(ExecuteStatus.SUCCESS);
                    notifyTrackService.update(notifyTrack);
                } else {
                    notifyTrack.setData(resultVo.msg);
                    notifyTrackService.update(notifyTrack);
                }
            }
        }
    }

    /**
     * @param dataType    数据类型
     * @param operateType 操作动作
     * @param url         推送地址
     * @param data        数据
     * @param extendData  扩展数据
     * @return ResultVo
     * 推送一条通知
     */
    public ResultVo notify(long dataType, long operateType, String url, String data, Map<String, String> extendData) {
        Map<String, String> postData = new HashMap<>();
        postData.put("dataType", String.valueOf(dataType));
        postData.put("operateType", String.valueOf(operateType));
        postData.put("data", data);
        if (extendData != null && !extendData.isEmpty()) {
            postData.putAll(extendData);
        }

        HttpSocket socket = new HttpSocket();
        socket.setUrl(url);
        socket.setPostData(postData);

        try {
            socket.doPost();
        } catch (Exception e) {
            LOGGER.error(url, e);
            return new ResultVo(FAIL_CODE, e.getMessage());
        }

        String result = socket.getResponseData();
        return parse(result);
    }

    /**
     * 结果值对象
     * 2013-1-8 下午2:02:58
     *
     * @author ketqi
     */
    public static class ResultVo {
        public int code;
        public String msg;

        public ResultVo() {
        }

        public ResultVo(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder(CommonPlatformConstant.LENGTH_128);
            builder.append("ResultVo [code=");
            builder.append(code);
            builder.append(", msg=");
            builder.append(msg);
            builder.append("]");
            return builder.toString();
        }

        public String toXml() {
            StringBuilder builder = new StringBuilder(CommonPlatformConstant.LENGTH_128);
            builder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
            builder.append("<result>");
            builder.append("<code>").append(code).append("</code>");
            builder.append("<msg><![CDATA[").append(msg).append("]]></msg>");
            builder.append("</result>");
            return builder.toString();
        }
    }

    /**
     * @param result 结果xml数据
     * @return 解析结果
     */
    public static ResultVo parse(String result) {
        ResultVo resultVo = new ResultVo();
        SAXReader saxReader = new SAXReader();
        try (InputStream is = new ByteArrayInputStream(result.getBytes("UTF-8"))) {
            Document document = saxReader.read(is);
            Element root = (Element) document.selectSingleNode("/result");
            @SuppressWarnings("unchecked")
            List<Element> elementList = root.elements();
            if (elementList != null && !elementList.isEmpty()) {
                for (Element element : elementList) {
                    if ("code".equals(element.getName())) {
                        resultVo.code = Integer.parseInt(element.getStringValue());
                    } else if ("msg".equals(element.getName())) {
                        resultVo.msg = element.getStringValue();
                    }
                }
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            resultVo.code = FAIL_CODE;
            resultVo.msg = e.getMessage();
        }
        return resultVo;
    }
}