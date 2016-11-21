package com.sjdf.platform.attachment.helper;

import com.sjdf.platform.CommonPlatformConstant;
import com.sjdf.platform.attachment.vo.AttachmentHouseVo;
import com.sjdf.platform.common.utils.DateUtils;
import com.sjdf.platform.common.utils.PlatformUtils;
import com.sjdf.platform.common.utils.Tools;
import com.sjdf.platform.common.utils.base64.Base64;
import com.sjdf.platform.dictionary.bean.*;
import com.sjdf.platform.dictionary.cache.ConfigManager;
import com.sjdf.platform.dictionary.cache.DictionaryHelper;
import com.sjdf.platform.log.logger.SJDFLogger;
import com.sjdf.platform.log.logger.SJDFLoggerFactory;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import java.io.*;
import java.util.*;

/**
 * Create at 2013-4-17 上午11:41:40
 * 附件信息帮助类
 *
 * @author frank
 */
public abstract class AttachmentHelper {
    /** 日志记录器 */
    private static final SJDFLogger LOGGER = SJDFLoggerFactory.getSJDFLogger(AttachmentHelper.class);
    /** 文件保存的根路径 */
    public static final String SAVE_ROOT_PATH = ConfigManager.getInstance().getValue(AttachmentConfigure.class, AttachmentConfigure.COMMON_ATTACHMENT_SAVE_PATH);
    /** 正斜线 */
    public static final String SLASH_SYMBOL = ConfigManager.getInstance().getValue(AssembleStringSymbols.class, AssembleStringSymbols.SLASH);
    /** 破折号 */
    public static final String HYPHEN_SYMBOL = ConfigManager.getInstance().getValue(AssembleStringSymbols.class, AssembleStringSymbols.HYPHEN);
    /** 英文句号 */
    public static final String EN_PERIOD_SYMBOL = ConfigManager.getInstance().getValue(AssembleStringSymbols.class, AssembleStringSymbols.EN_PERIOD);
    /** 连接密码 */
    public static final String CONNPWD = ConfigManager.getInstance().getValue(RemoteAccess.class, RemoteAccess.CONNECTION_PASSWORD);
    /** 冒号 */
    public static final String COLON = ConfigManager.getInstance().getValue(AssembleStringSymbols.class, AssembleStringSymbols.COLON);
    /** 分号空格 */
    public static final String SEMICOLON = ConfigManager.getInstance().getValue(AssembleStringSymbols.class, AssembleStringSymbols.SEMICOLON_SPACE);
    /** 英文逗号 */
    public static final String COMMA = ConfigManager.getInstance().getValue(AssembleStringSymbols.class, AssembleStringSymbols.COMMA);

    public static String parse(List<AttachmentHouseVo> attachmentVoList, String attachmentPath) {
        try {
            StringBuilder xml = new StringBuilder(CommonPlatformConstant.LENGTH_5120);
            xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
            xml.append("<attachmentList>");
            for (AttachmentHouseVo attachmentHouseVo : attachmentVoList) {
                xml.append(attachmentHouseVo.toXml(attachmentPath));
            }
            xml.append("</attachmentList>");
            return xml.toString();
        } catch (Exception e) {
            LOGGER.error("解析附件", e);
        }
        return null;
    }

    /**
     * 解析附件XML文件为附件VO List
     *
     * @param xml 附件XML
     * @return 附件VO List
     */
    @SuppressWarnings("unchecked")
    public static List<AttachmentHouseVo> parse(String xml) throws Exception {
        List<AttachmentHouseVo> attachmentVoList = new ArrayList<>();
        SAXReader saxReader = new SAXReader();
        try (InputStream is = new ByteArrayInputStream(xml.getBytes("UTF-8"))) {
            Document document = saxReader.read(is);

            Node node = document.selectSingleNode("/error");
            if (node != null) {
                LOGGER.error(xml);
                throw new RuntimeException(((Element) node).getData().toString());
            }

            Element root = (Element) document.selectSingleNode("/attachmentList");
            List<Element> xmlDictionaryList = root.elements();
            if (xmlDictionaryList != null && !xmlDictionaryList.isEmpty()) {
                for (Element element : xmlDictionaryList) {
                    List<Element> xmlDictionary = element.elements();

                    AttachmentHouseVo attachmentHouseVo = new AttachmentHouseVo();
                    for (Element e : xmlDictionary) {
                        if (e.elements().isEmpty() && (Tools.isEmpty(e.getData().toString()) || "null".equals(e.getData().toString()))) {
                            continue;
                        }
                        if ("path".equals(e.getName())) {
                            attachmentHouseVo.setPath(e.getData().toString());
                        } else if ("userId".equals(e.getName())) {
                            attachmentHouseVo.setUserId(Long.valueOf(e.getData().toString()));
                        } else if ("attachmentUseCode".equals(e.getName())) {
                            attachmentHouseVo.setAttachmentUseCode(Long.valueOf(e.getData().toString()));
                        } else if ("attachmentUseType".equals(e.getName())) {
                            attachmentHouseVo.setAttachmentUseType(Long.valueOf(e.getData().toString()));
                        } else if ("systemType".equals(e.getName())) {
                            attachmentHouseVo.setSystemType(Long.valueOf(e.getData().toString()));
                        } else if ("markDelete".equals(e.getName())) {
                            attachmentHouseVo.setMarkDelete(Long.valueOf(e.getData().toString()));
                        } else if ("remark".equals(e.getName())) {
                            attachmentHouseVo.setRemark(e.getData().toString());
                        } else if ("byte".equals(e.getName())) {
                            attachmentHouseVo.setAttachmentFileString(e.getData().toString());
                        } else if ("username".equals(e.getName())) {
                            attachmentHouseVo.setUsername(e.getData().toString());
                        } else if ("whetherChanged".equals(e.getName())) {
                            attachmentHouseVo.setWhetherChanged(Long.valueOf(e.getData().toString()));
                        } else if ("format".equals(e.getName())) {
                            attachmentHouseVo.setFormat(Long.valueOf(e.getData().toString()));
                        } else if ("sourcePath".equals(e.getName())) {
                            attachmentHouseVo.setSourcePath(e.getData().toString());
                        } else if ("subSystemPath".equals(e.getName())) {
                            attachmentHouseVo.setSubSystemPath(e.getData().toString());
                        } else if ("attachmentId".equals(e.getName())) {
                            attachmentHouseVo.setId(Long.valueOf(e.getData().toString()));
                        } else if ("auditResult".equals(e.getName())) {
                            attachmentHouseVo.setAuditResult(Long.valueOf(e.getData().toString()));
                        } else if ("fontColor".equals(e.getName())) {
                            attachmentHouseVo.setFontColor(Long.valueOf(e.getData().toString()));
                        } else if ("createTime".equals(e.getName())) {
                            attachmentHouseVo.setBeginDate(e.getData().toString());
                        } else if ("updateTime".equals(e.getName())) {
                            attachmentHouseVo.setEndDate(e.getData().toString());
                        } else if ("attachmentUseTypeMark".equals(e.getName())) {
                            attachmentHouseVo.setAttachmentUseTypeMark(e.getData().toString());
                        } else if ("attachmentSpecialMark".equals(e.getName())) {
                            //匹配关键字的 element集合
                            List<Element> ppgjzElementList = e.elements();
                            String specialMark;
                            if (ppgjzElementList.isEmpty() && !Tools.isEmpty(e.getData().toString())) {
                                specialMark = e.getData().toString();
                            } else {
                                specialMark = parseAttachmentSpecialMark(attachmentHouseVo.getAttachmentUseType(), attachmentHouseVo.getAttachmentUseCode(), ppgjzElementList);
                            }
                            attachmentHouseVo.setAttachmentSpecialMark(specialMark);
                        }
                    }
                    attachmentVoList.add(attachmentHouseVo);
                }
            }
        }
        return attachmentVoList;
    }

    /**
     * @param sourcePath    数据库附件的Path
     * @param subSystemPath 子系统Path
     *                      校验数据库附件的Path与条件的Path 是否一致。
     * @return true:表示两个Path相同，false:表示两个Path不相同
     */
    public static boolean verifyAttachmentPath(String sourcePath, String subSystemPath) {
        String source = sourcePath, subSystem = subSystemPath;
        try {
            if (Tools.isEmpty(source) && Tools.isEmpty(subSystem)) {
                return true;
            }
            if (!Tools.isEmpty(source)) {
                source = source.substring(source.lastIndexOf(SLASH_SYMBOL) + 1, source.length());
            }
            if (!Tools.isEmpty(subSystem)) {
                subSystem = subSystem.substring(subSystem.lastIndexOf(SLASH_SYMBOL) + 1, subSystem.length());
            }
            return source.equals(subSystem);
        } catch (Exception e) {
            LOGGER.error("校验数据库附件的Path与条件的Path 是否一致", e);
        }
        return false;
    }

    /**
     * @param path 附件路径
     *             将附件路径解析成ID和时间戳
     * @return ID和时间戳的数组
     */
    public static String[] splitPath(String path) {
        String str = path;
        String[] splitPathArray = new String[CommonPlatformConstant.LENGTH_2];
        try {
            if (Tools.isEmpty(str)) {
                return new String[0];
            }
            //截取附件主键ID和时间戳
            str = str.substring(str.lastIndexOf(SLASH_SYMBOL) + 1, str.length());

            String[] pathArray = str.split(HYPHEN_SYMBOL);
            splitPathArray[0] = pathArray[0];
            splitPathArray[1] = pathArray[1].substring(0, pathArray[1].lastIndexOf(EN_PERIOD_SYMBOL));
            return splitPathArray;
        } catch (Exception e) {
            LOGGER.error("将附件路径解析成ID和时间戳", e);
            return new String[0];
        }
    }

    /**
     * @param voList 附件VOList
     *               将附件VOList转化成Map
     * @return 附件VO Map
     */
    public static Map<Long, AttachmentHouseVo> voListToVoMap(List<AttachmentHouseVo> voList) {
        Map<Long, AttachmentHouseVo> attachmentHouseVoMap = new TreeMap<>();
        for (AttachmentHouseVo attachmentHouseVo : voList) {
            attachmentHouseVoMap.put(Long.valueOf(splitPath(attachmentHouseVo.getPath())[0]), attachmentHouseVo);
        }
        return attachmentHouseVoMap;
    }

    /**
     * @param dir 需要生成的文件夹
     *            生成文件夹
     */
    public static void makeDir(String dir) {
        try {
            //生成目标文件夹
            File targetPath = new File(dir);
            if (!targetPath.exists()) {
                targetPath.mkdirs();
            }
        } catch (Exception e) {
            LOGGER.error("生成文件夹", e);
        }
    }

    /**
     * 获取时间格式的路径
     *
     * @return 时间格式的路径
     */
    public static String getDateDir() {
        return DateUtils.formatDateToYear(new Date()) + AttachmentHelper.SLASH_SYMBOL + DateUtils.formatDate(new Date());
    }

    /**
     * 校验附件VO中的数据是否正常
     */
    public static void verifyAttachmentVoList(List<AttachmentHouseVo> voList, long operatorAction) throws Exception {
        //错误记录Builder
        StringBuilder errorBuilder = new StringBuilder();
        for (AttachmentHouseVo attachmentHouseVo : voList) {
            attachmentHouseVo.verifySelf(errorBuilder, operatorAction);
        }
        //如果校验不通过，则抛出异常
        if (!Tools.isEmpty(errorBuilder.toString())) {
            throw new Exception(errorBuilder.toString());
        }
    }

    /**
     * @param filePath 文件路径
     *                 根据文件绝对路径删除附件
     * @return 是否删除成功
     */
    public static boolean deleteAttachment(String filePath) {
        try {
            if (Tools.isEmpty(filePath)) {
                return false;
            }
            File file = new File(filePath);
            if (file.isFile() && file.exists()) {
                file.delete();
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            LOGGER.error("根据文件绝对路径删除附件", e);
            return false;
        }
    }

    /**
     * 将文件输出到指定文件夹下
     *
     * @param inputStream 输入流
     * @param filePath    文件路径
     * @param fileName    文件名
     * @throws IOException
     */
    public static File writerFile(InputStream inputStream, String filePath, String fileName) throws IOException {
        String path = filePath;
        BufferedInputStream bis = new BufferedInputStream(inputStream);
        File file = new File(path);
        if (!file.isDirectory()) {
            file.mkdir();
        }
        path += "/" + fileName;
        File afterSavedFile = new File(path);
        BufferedOutputStream fos = new BufferedOutputStream(new FileOutputStream(path));

        byte[] buffer = new byte[CommonPlatformConstant.LENGTH_1024 * CommonPlatformConstant.LENGTH_10];

        for (int numRead; (numRead = bis.read(buffer)) > 0; ) {
            fos.write(buffer, 0, numRead);
        }

        fos.flush();
        fos.close();
        bis.close();
        return afterSavedFile;
    }

    /**
     * @param file 条件file
     * @return BASE64Encoder编码的byte String
     * 将file转化为BASE64Encoder编码的byte String
     */
    public static String getFileString(File file) {
        byte[] buffer = new byte[CommonPlatformConstant.LENGTH_1024];
        int length;
        try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(file)); ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            while ((length = in.read(buffer)) != -1) {
                out.write(buffer, 0, length);
            }
            return new String(Base64.encode(out.toByteArray(), Base64.ONE_LINE_LENGTH));
        } catch (IOException e) {
            LOGGER.error("将file转化为BASE64Encoder编码的byte String", e);
            return null;
        }
    }

    /**
     * @param fileString BASE64Encoder编码后的文件String
     * @return byte数组或者NULL
     * 将BASE64Encoder编码后的文件String 转化为编码的byte数组
     */
    public static byte[] fileStringToByte(String fileString) {
        try {
            return Base64.decode(fileString.getBytes());
        } catch (Exception e) {
            LOGGER.error("将BASE64Encoder编码后的文件String 转化为编码的byte数组", e);
        }
        return new byte[0];
    }

    /**
     * @param fileString 将BASE64Encoder编码后的文件String 转化为InputStream对象
     * @return InputStream对象或者NULL
     */
    public static InputStream fileStringToInputStream(String fileString) {
        try {
            return new ByteArrayInputStream(fileStringToByte(fileString));
        } catch (Exception e) {
            LOGGER.error("将BASE64Encoder编码后的文件String 转化为InputStream对象", e);
        }
        return null;
    }

    /**
     * @param fromPath 文件旧路径
     * @param toPath   文件新路径
     *                 复制文件
     * @return 操作成功，返回true 否则返回false;
     */
    public static boolean copyFile(String fromPath, String toPath) {
        try {
            int byteread;
            File oldfile = new File(fromPath);
            if (oldfile.exists()) {  //文件存在时
                InputStream inStream = new FileInputStream(fromPath);  //读入原文件
                File toFile = new File(toPath);
                FileOutputStream fs = new FileOutputStream(toFile);
                byte[] buffer = new byte[CommonPlatformConstant.LENGTH_2048];
                while ((byteread = inStream.read(buffer)) != -1) {
                    fs.write(buffer, 0, byteread);
                }
                inStream.close();
                fs.close();
            }

            return true;
        } catch (Exception e) {
            LOGGER.error("复制文件", e);
            return false;
        }
    }

    /**
     * @param commonPath 业务公共平台保存的Path
     *                   根据业务公共平台保存的Path 获取子系统的Path
     * @return 子系统的Path
     */
    public static String getSubSystemPath(String commonPath) {
        String subSystemPath = null;
        try {
            if (Tools.isEmpty(commonPath)) {
                return "";
            }
            String[] commonPathArray = commonPath.split(SLASH_SYMBOL);
            //老的备案数据
            if (commonPathArray.length == CommonPlatformConstant.LENGTH_2) {
                String suffix = commonPathArray[1];
                subSystemPath = SLASH_SYMBOL + getDateDir() + SLASH_SYMBOL + suffix;
            } else {
                //新的数据
                subSystemPath = commonPath.substring(1);
                subSystemPath = subSystemPath.substring(subSystemPath.indexOf(SLASH_SYMBOL));
            }
        } catch (Exception e) {
            LOGGER.error("根据业务公共平台保存的Path 获取子系统的Path", e);
        }
        return subSystemPath;
    }

    /**
     * @param attachmentRealPath 附件的数据库保存路径
     *                           根据附件的数据库保存路径获取其缩小图片的路径，缩小图片的文件名称，缩小图片的全路径,缩小图片DB保存路径
     * @return 附件的数组
     */
    public static String[] getResizePath(String attachmentRealPath) {
        String[] resizePath = new String[CommonPlatformConstant.LENGTH_4];
        try {
            if (Tools.isEmpty(attachmentRealPath)) {
                return resizePath;
            }
            String fileName = attachmentRealPath.substring(attachmentRealPath.lastIndexOf(SLASH_SYMBOL) + 1);
            String resizeDirectory = SAVE_ROOT_PATH + attachmentRealPath.substring(0, attachmentRealPath.lastIndexOf(SLASH_SYMBOL) + 1) + ConfigManager.getInstance().getValue(AttachmentConfigure.class, AttachmentConfigure.COMMON_RESIZE_IMAGE);
            String allPath = resizeDirectory + SLASH_SYMBOL + fileName;
            String resizeDbPath = attachmentRealPath.substring(0, attachmentRealPath.lastIndexOf(SLASH_SYMBOL) + 1) + ConfigManager.getInstance().getValue(AttachmentConfigure.class, AttachmentConfigure.COMMON_RESIZE_IMAGE) + SLASH_SYMBOL + fileName;
            resizePath[0] = resizeDirectory;
            resizePath[1] = fileName;
            resizePath[CommonPlatformConstant.LENGTH_2] = allPath;
            resizePath[CommonPlatformConstant.LENGTH_3] = resizeDbPath;
        } catch (Exception e) {
            LOGGER.error("根据附件的数据库保存路径获取其缩小图片的路径", e);
        }
        return resizePath;
    }

    public static String parseAttachmentSpecialMark(Long attachmentUseType, Long attachmentUseCode, List<Element> ppgjzElementList) {
        //匹配关键字集合
        List<String> ppgjzArray = new ArrayList<>();
        try {
            // 前置审批
            if (Tools.compareLong(attachmentUseType, AttachmentUseType.ATTACHMENT_USE_TYPE_PRE_APPROVAL_ATTACHMENT)) {
                //域名
                String domain = getValueOfElementByCondition(ppgjzElementList, KeyWordType.DOMAIN_NAME);
                ppgjzArray.add(domain);
                return splicePpgjz(ppgjzArray, SEMICOLON);
            } else {
                //附件用途--港澳居民来往内地通行证、台胞证、护照、军官证、身份证、事业法人证书、社会法人证书、军队证、组织机构代码证书
                if (Tools.compareLong(attachmentUseCode, AttachmentUseCode.ATTACHMENT_USE_CODE_TAIWAN_COMPATRIOTS_CERTIFICATE)
                        || Tools.compareLong(attachmentUseCode, AttachmentUseCode.ATTACHMENT_USE_CODE_GA_PASSPORT)
                        || Tools.compareLong(attachmentUseCode, AttachmentUseCode.ATTACHMENT_USE_CODE_PASSPORT)
                        || Tools.compareLong(attachmentUseCode, AttachmentUseCode.ATTACHMENT_USE_CODE_OFFICER_CERTIFICATE)
                        || Tools.compareLong(attachmentUseCode, AttachmentUseCode.ATTACHMENT_USE_CODE_ID_CARD)) {
                    //负责人证件姓名匹配关键字
                    //首先核实负责人
                    String certificateNamePpgjz = getValueOfElementByCondition(ppgjzElementList, KeyWordType.NAME);
                    //如果人名不存在，则核实法人
                    if (Tools.isEmpty(certificateNamePpgjz)) {
                        certificateNamePpgjz = getValueOfElementByCondition(ppgjzElementList, KeyWordType.FIRM_NAME);
                    }
                    //负责人证件号码匹配关键字
                    String certificateIDCardPpgjz = getValueOfElementByCondition(ppgjzElementList, KeyWordType.CERTIFICATE_NUM);
                    //负责人证件证件地址匹配关键字
                    String certificateAddrPpgjz = getValueOfElementByCondition(ppgjzElementList, KeyWordType.CERTIFICATE_ADDR);
                    //组合匹配关键字集合
                    if (certificateNamePpgjz != null && !"".equals(certificateNamePpgjz)) {
                        ppgjzArray.add(certificateNamePpgjz);//名称
                    }
                    if (certificateIDCardPpgjz != null && !"".equals(certificateIDCardPpgjz)) {
                        ppgjzArray.add(certificateIDCardPpgjz);//证件号码
                    }
                    if (certificateAddrPpgjz != null && !"".equals(certificateAddrPpgjz)) {
                        ppgjzArray.add(certificateAddrPpgjz);//证件住所
                    }
                    return splicePpgjz(ppgjzArray, SEMICOLON);
                }
                //附件用途--工商营业执照
                if (Tools.compareLong(attachmentUseCode, AttachmentUseCode.ATTACHMENT_USE_CODE_BUSINESS_LICENSE)
                        || Tools.compareLong(attachmentUseCode, AttachmentUseCode.ATTACHMENT_USE_CODE_CAREER_LEGAL_CERTIFICATE)
                        || Tools.compareLong(attachmentUseCode, AttachmentUseCode.ATTACHMENT_USE_CODE_SOCIETY_LEGAL_CERTIFICATE)
                        || Tools.compareLong(attachmentUseCode, AttachmentUseCode.ATTACHMENT_USE_CODE_ARMY_CERTIFICATE)
                        || Tools.compareLong(attachmentUseCode, AttachmentUseCode.ATTACHMENT_USE_CODE_ORGANIZATION_CERTIFICATE)
                        || Tools.compareLong(attachmentUseCode, AttachmentUseCode.ATTACHMENT_UNIFIED_SOCIAL_CREDIT_CERTIFICATE)
                        || Tools.compareLong(attachmentUseCode, AttachmentUseCode.ATTACHMENT_USE_CODE_MBFQY_DWDJ_CERTIFICATE)
                        || Tools.compareLong(attachmentUseCode, AttachmentUseCode.ATTACHMENT_USE_CODE_LSSWS_ZYXK_CERTIFICATE)
                        || Tools.compareLong(attachmentUseCode, AttachmentUseCode.ATTACHMENT_USE_CODE_JJH_CAREER_LEGAL_CERTIFICATE)
                        || Tools.compareLong(attachmentUseCode, AttachmentUseCode.ATTACHMENT_USE_CODE_REGISTRATION_APPLICATION_FORM)) {
                    //负责人证件姓名匹配关键字
                    String firmName = getValueOfElementByCondition(ppgjzElementList, KeyWordType.FIRM_NAME);
                    //负责人证件号码匹配关键字
                    String certificateIDCardPpgjz = getValueOfElementByCondition(ppgjzElementList, KeyWordType.CERTIFICATE_NUM);
                    //负责人证件证件地址匹配关键字
                    String certificateAddrPpgjz = getValueOfElementByCondition(ppgjzElementList, KeyWordType.CERTIFICATE_ADDR);
                    //组合匹配关键字集合
                    ppgjzArray.add(firmName);
                    ppgjzArray.add(certificateIDCardPpgjz);
                    ppgjzArray.add(certificateAddrPpgjz);
                    return splicePpgjz(ppgjzArray, SEMICOLON);
                }
                //附件用途--网站负责人相片
                if (Tools.compareLong(attachmentUseCode, AttachmentUseCode.ATTACHMENT_USE_CODE_WEB_RESP_PEOPLE_PHOTO)) {
                    //负责人证件姓名匹配关键字
                    String certificateNamePpgjz = getValueOfElementByCondition(ppgjzElementList, KeyWordType.NAME);
                    //负责人证件号码匹配关键字
                    String certificateIDCardPpgjz = getValueOfElementByCondition(ppgjzElementList, KeyWordType.CERTIFICATE_NUM);
                    //组合匹配关键字集合
                    ppgjzArray.add(certificateNamePpgjz);
                    ppgjzArray.add(certificateIDCardPpgjz);
                    return splicePpgjz(ppgjzArray, SEMICOLON);
                }
                //附件用途--网站备案信息真实性核验单
                if (Tools.compareLong(attachmentUseCode, AttachmentUseCode.ATTACHMENT_USE_CODE_WEB_VERIFI_SINGLE)) {
                    //单位名称
                    String firmName = getValueOfElementByCondition(ppgjzElementList, KeyWordType.FIRM_NAME);
                    //域名
                    String domain = getValueOfElementByCondition(ppgjzElementList, KeyWordType.DOMAIN_NAME);
                    //组合匹配关键字集合
                    ppgjzArray.add(firmName);
                    ppgjzArray.add(domain);
                    return splicePpgjz(ppgjzArray, SEMICOLON);
                }
                //附件用途--信息安全责任书
                if (Tools.compareLong(attachmentUseCode, AttachmentUseCode.ATTACHMENT_USE_CODE_INFO_LETTERS_CERTIFICATE)) {
                    //单位名称
                    String firmName = getValueOfElementByCondition(ppgjzElementList, KeyWordType.FIRM_NAME);
                    //负责人名称
                    String respPersonName = getValueOfElementByCondition(ppgjzElementList, KeyWordType.NAME);
                    //组合匹配关键字集合
                    ppgjzArray.add(firmName);
                    ppgjzArray.add(respPersonName);
                    return splicePpgjz(ppgjzArray, SEMICOLON);
                }
                //附件用途--域名证书
                if (Tools.compareLong(attachmentUseCode, AttachmentUseCode.ATTACHMENT_USE_CODE_DOMAIN_CERTIFICATE)) {
                    //域名
                    String domain = getValueOfElementByCondition(ppgjzElementList, KeyWordType.DOMAIN_NAME);
                    //组合匹配关键字集合
                    ppgjzArray.add(domain);
                    return splicePpgjz(ppgjzArray, SEMICOLON);
                }
                //附件用途--网站负责人授权书
                if (Tools.compareLong(attachmentUseCode, AttachmentUseCode.ATTACHMENT_USE_CODE_WEB_RESP_WARRANT_CERTIFICATE)) {
                    //单位名称
                    String firmName = getValueOfElementByCondition(ppgjzElementList, KeyWordType.FIRM_NAME);
                    //网站名称
                    String webName = getValueOfElementByCondition(ppgjzElementList, KeyWordType.WEB_NAME);
                    //域名
                    String domain = getValueOfElementByCondition(ppgjzElementList, KeyWordType.DOMAIN_NAME);
                    //网站负责人姓名
                    String respPersonName = getValueOfElementByCondition(ppgjzElementList, KeyWordType.NAME);
                    //网站负责人证件号码
                    String respPersonIDCardNum = getValueOfElementByCondition(ppgjzElementList, KeyWordType.CERTIFICATE_NUM);
                    //组合匹配关键字集合
                    ppgjzArray.add(firmName);
                    ppgjzArray.add(webName);
                    ppgjzArray.add(domain);
                    ppgjzArray.add(respPersonName);
                    ppgjzArray.add(respPersonIDCardNum);
                    return splicePpgjz(ppgjzArray, SEMICOLON);
                }
                // 附件用途--主体负责人授权书
                if (Tools.compareLong(attachmentUseCode, AttachmentUseCode.ATTACHMENT_USE_CODE_ZT_RESP_WARRANT_CERTIFICATE)) {
                    // 单位名称
                    String firmName = getValueOfElementByCondition(ppgjzElementList, KeyWordType.FIRM_NAME);
                    // 主体负责人姓名
                    String respPersonName = getValueOfElementByCondition(ppgjzElementList, KeyWordType.NAME);
                    // 主体负责人证件号码
                    String respPersonIDCardNum = getValueOfElementByCondition(ppgjzElementList, KeyWordType.CERTIFICATE_NUM);
                    // 组合匹配关键字集合
                    ppgjzArray.add(firmName);
                    ppgjzArray.add(respPersonName);
                    ppgjzArray.add(respPersonIDCardNum);
                    return splicePpgjz(ppgjzArray, SEMICOLON);
                }
                // 附件用途--网安回执单
                if (Tools.compareLong(attachmentUseCode, AttachmentUseCode.ATTACHMENT_USE_CODE_POLICERECEIPT)) {
                    // 网站名称
                    String webName = getValueOfElementByCondition(ppgjzElementList, KeyWordType.WEB_NAME);
                    ppgjzArray.add(webName);
                    return splicePpgjz(ppgjzArray, SEMICOLON);
                }
            }
        } catch (Exception e) {
            LOGGER.error("错误", e);
        }
        return null;
    }

    /**
     * @param ppgjzElementList 匹配关键字元素List
     * @param condition        条件
     *                         根据条件找出ppgjzElementList中需要的字符串
     * @return 筛选后的匹配关键字字符串, 格式--> key:value
     */
    private static String getValueOfElementByCondition(List<Element> ppgjzElementList, Long condition) {
        StringBuilder sb = new StringBuilder();

        try {
            //取出匹配关键字
            for (Element tempElement : ppgjzElementList) {
                //匹配关键字的key
                String key = tempElement.attributeValue("key");
                //匹配关键字的value
                String value = tempElement.attributeValue("value");
                //判断key是否是证件名称
                if (condition.toString().equals(key) && PlatformUtils.hasText(value)) {
                    sb.append(key).append(COLON).append(value);
                    break;
                }
                sb.setLength(0);
            }
            return sb.toString();
        } catch (Exception e) {
            LOGGER.error("根据条件找出ppgjzElementList中需要的字符串", e);
        }
        return null;
    }

    /**
     * @param ppgjzArray 所有的匹配关键字List
     * @param spliceStr  拼接间隔字符串
     *                   拼接所有的匹配关键字
     * @return 拼接好的【匹配关键字】字符串
     */
    private static String splicePpgjz(List<String> ppgjzArray, String spliceStr) {
        StringBuilder sb = new StringBuilder();
        try {
            for (int i = 0; i < ppgjzArray.size(); i++) {
                if (ppgjzArray.get(i) != null && !"".equals(ppgjzArray.get(i))) {
                    sb.append(ppgjzArray.get(i));
                    if (i < ppgjzArray.size() - 1) {
                        sb.append(spliceStr);
                    }
                } else {
                    sb.setLength(0);
                    return null;
                }
            }
            return sb.toString();
        } catch (Exception e) {
            LOGGER.error("拼接所有的匹配关键字", e);
        }
        return null;
    }

    /**
     * @param referPpgjzStr 参照匹配关键字字符串
     * @param parsePpgjzStr 需被解析的匹配关键字字符串
     *                      比对【需被解析的匹配关键字字符串】是否被包含于【参照匹配关键字字符串】
     * @return 比对结果，true：比对成功，false：比对失败。
     */
    public static boolean compareAttachmentSpecialMark(String referPpgjzStr, String parsePpgjzStr) {
        //标记比对是否成功,默认比对失败
        boolean flag = false;
        try {
            if (Tools.isEmpty(referPpgjzStr) || Tools.isEmpty(parsePpgjzStr)) {
                return flag;
            }
            //解析匹配关键字
            //数据库备案相册--匹配关键字
            //总的判断方式是，以三种方式拆分可拆段，通过判断传入的关键字中是否包含拆分后的字段（即源备案相册中的内容），
            //只要检测到一个没有，则返回空备案相册
            //1.以"; "的形式拆分baxc.getPpgjz(),源备案相册
            String[] sourcePpgjzArray = parsePpgjzStr.split(SEMICOLON);
            //新备案相册--匹配关键字
            String[] newPpgjzArray = referPpgjzStr.split(SEMICOLON);
            //如果以上两种备案相册--匹配关键字的个数不同，则直接返回NULL。
            if (sourcePpgjzArray.length != newPpgjzArray.length) {
                flag = false;
            } else {
                //比较两种备案相册--匹配关键字中一一对应的值是否相同
                for (String sourcePpgjzStr : sourcePpgjzArray) {
                    //2.以":"的形式拆分sourcePpgjzStr
                    String[] sourceUnit = sourcePpgjzStr.split(COLON);
                    for (String unitStr : sourceUnit) {
                        //判断unitStr是否有","，有则继续拆分
                        if (unitStr.contains(COMMA)) {
                            //3.以","的形式拆分unitStr
                            String[] sourceSmallUnit = unitStr.split(COMMA);
                            for (String smallUnit : sourceSmallUnit) {
                                if (!referPpgjzStr.contains(smallUnit)) {
                                    //只要有一个关键字没有匹配，则直接跳出循环
                                    flag = false;
                                    break;
                                } else {
                                    flag = true;
                                }
                            }
                            //只要有一个关键字没有匹配，则直接跳出循环
                            if (!flag) {
                                break;
                            }
                        } else {
                            if (!referPpgjzStr.contains(unitStr)) {
                                //只要有一个关键字没有匹配，则直接跳出循环
                                flag = false;
                                break;
                            } else {
                                flag = true;
                            }
                        }
                    }
                    //只要有一个关键字没有匹配，则直接跳出循环
                    if (!flag) {
                        break;
                    }
                }
            }
        } catch (Exception e) {
            LOGGER.error("比对【需被解析的匹配关键字字符串】是否被包含于【参照匹配关键字字符串】", e);
        }
        return flag;
    }

    /**
     * 获取map集合(附件格式在配置库中value和对应的常量数值)
     *
     * @return map集合(附件格式在配置库中value和对应的常量数值)
     */
    public static Map<String, Long> getAttachmentFormatStringMap() {
        List<AttachmentFormat> attachmentFormatList = DictionaryHelper.getDictionary(AttachmentFormat.class);
        Map<String, Long> attachmentFormatMap = new HashMap<>();
        for (AttachmentFormat attachmentFormat : attachmentFormatList) {
            attachmentFormatMap.put(attachmentFormat.getCnName(), attachmentFormat.getAttr());
        }
        return attachmentFormatMap;
    }

    /**
     * @param specialMarkStr 匹配关键字字符串
     *                       将匹配关键字字符串反转成Map
     * @return 匹配关键字Map
     */
    public static Map<Long, String> stringToSpecialMarkMap(String specialMarkStr) {
        Map<Long, String> specialMarkMap = new HashMap<>();
        try {
            if (Tools.isEmpty(specialMarkStr)) {
                throw new NullPointerException();
            }
            //1.以"; "的形式拆分unitStr
            String[] specialMarkSemicolonArray = specialMarkStr.split(SEMICOLON);
            for (String semicolonStr : specialMarkSemicolonArray) {
                //2.以":"的形式拆分unitStr
                String[] sourceColonUnit = semicolonStr.split(COLON);
                specialMarkMap.put(Long.valueOf(sourceColonUnit[0]), sourceColonUnit[1]);
            }
            return specialMarkMap;
        } catch (Exception e) {
            LOGGER.error("将匹配关键字字符串反转成Map", e);
            throw e;
        }
    }

}
