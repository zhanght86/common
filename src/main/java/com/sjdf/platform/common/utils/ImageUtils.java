package com.sjdf.platform.common.utils;

import com.fmsware.gif.AnimatedGifEncoder;
import com.fmsware.gif.GifDecoder;
import com.sjdf.platform.attachment.helper.AttachmentHelper;
import com.sjdf.platform.common.utils.base64.Base64;
import com.sjdf.platform.common.utils.cmyk.jpeg.CMYKJPEGImageReader;
import com.sjdf.platform.common.utils.cmyk.jpeg.CMYKJPEGImageReaderSpi;
import com.sjdf.platform.log.logger.SJDFLogger;
import com.sjdf.platform.log.logger.SJDFLoggerFactory;
import org.im4java.core.ConvertCmd;
import org.im4java.core.IMOperation;
import org.springframework.util.StringUtils;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.stream.ImageOutputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.io.*;
import java.util.Iterator;

/**
 * 图片处理的工具类
 */
public abstract class ImageUtils {
    private static final SJDFLogger LOGGER = SJDFLoggerFactory.getSJDFLogger(ImageUtils.class);
    private static final int CONSTANT_1024 = 1024;

    /**
     * 重置图片的大小
     *
     * @param multiple         重置的倍数（新图片是源图片的倍数）
     * @param sourceImagePath  源图片的全路径（包含文件名称）
     * @param currentImagePath 新图片的全路径（包含文件名称）
     */
    public static void resizeImage(float multiple, String sourceImagePath, String currentImagePath) {

        File src = new File(sourceImagePath);
        try {
            // 构造Image对象
            Image image = ImageIO.read(src);

            // 得到源图宽
            int sourceWidth = image.getWidth(null);
            // 得到源图高
            int sourceHeight = image.getHeight(null);

            // 当前图的宽
            int currentWidth = (int) (sourceWidth * multiple);
            // 当前图的高
            int currentHeight = (int) (sourceHeight * multiple);

            BufferedImage bufferedImage = new BufferedImage(currentWidth, currentHeight, BufferedImage.TYPE_INT_RGB);
            // 绘制后的图
            bufferedImage.getGraphics().drawImage(image.getScaledInstance(currentWidth, currentHeight, Image.SCALE_SMOOTH), 0, 0, null);

            FileOutputStream fos = new FileOutputStream(currentImagePath);
            // 近JPEG编码
            ImageOutputStream ios = ImageIO.createImageOutputStream(fos);
            ImageIO.write(bufferedImage, "jpg", ios);
        } catch (Exception e) {
            LOGGER.error("重置图片的大小", e);
        }
    }

    /**
     * 按照指定的大小进行缩小图片， 如果路径填写一样，将覆盖原图片
     *
     * @param currentWidth     指定缩小的宽度
     * @param currentHeight    指定缩小的高度
     * @param sourceImagePath  图片的原路径
     * @param currentImagePath 图片的新路径
     */
    public static void resizeImage(int currentWidth, int currentHeight, String sourceImagePath, String currentImagePath) {
        File src = new File(sourceImagePath);
        try {
            // 构造Image对象
            Image image = ImageIO.read(src);

            // 得到源图宽
            int sourceWidth = image.getWidth(null);
            // 得到源图高
            int sourceHeight = image.getHeight(null);

            // 如果图片的原大小与设置的大小（宽高） 一致
            if (sourceWidth == currentWidth && sourceHeight == currentHeight) {
                return;
            }

            BufferedImage bufferedImage = new BufferedImage(currentWidth, currentHeight, BufferedImage.TYPE_INT_RGB);
            // 绘制后的图
            bufferedImage.getGraphics().drawImage(image.getScaledInstance(currentWidth, currentHeight, Image.SCALE_SMOOTH), 0, 0, null);
            FileOutputStream fos = new FileOutputStream(currentImagePath);
            // 近JPEG编码
            ImageOutputStream ios = ImageIO.createImageOutputStream(fos);
            ImageIO.write(bufferedImage, "jpg", ios);
        } catch (Exception e) {
            LOGGER.error("按照指定的大小进行缩小图片", e);
        }
    }

    /**
     * 按照指定的文件大小缩小图片，并根据配置的值，比较是否校验二进制字符串的大小
     *
     * @param size        缩小图片大小的参照值
     * @param imageString BASE64Encoder编码后的文件String
     * @return 如果返回空字符串表示没有缩小图片，否则返回缩小后的图片
     */
    public static String resizeImage(int size, String imageString) {
        // 最后需要返回的图片字符串
        String currentImageBase64Str = "";
        if (StringUtils.hasText(imageString)) {
            // 总大小
            int totalSize = imageString.getBytes().length;
            int currentSize = totalSize;
            // 规格大小
            int specificationSize = size * CONSTANT_1024;
            // 　图片大小符合规格，不处理
            if (totalSize < specificationSize) {
                return "";
            }
            float scale = 1.0F;
            do {
                try (InputStream in = AttachmentHelper.fileStringToInputStream(imageString); ByteArrayOutputStream baos = new ByteArrayOutputStream();) {
                    // 构造Image对象
                    Image image = getImage(in);
                    if (image == null) {
                        throw new Exception("the image is null");
                    }
                    // 得到源图宽，源图高
                    int imageWidth = image.getWidth(null);
                    int imageHeight = image.getHeight(null);
                    imageWidth = (int) (scale * imageWidth);
                    imageHeight = (int) (scale * imageHeight);
                    image = image.getScaledInstance(imageWidth, imageHeight, Image.SCALE_AREA_AVERAGING);
                    // Make a BufferedImage from the Image.
                    BufferedImage mBufferedImage = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);
                    Graphics2D g2 = mBufferedImage.createGraphics();
                    g2.drawImage(image, 0, 0, imageWidth, imageHeight, Color.white, null);
                    g2.dispose();
                    float[] kernelData2 = {-0.125f, -0.125f, -0.125f, -0.125f, 2, -0.125f, -0.125f, -0.125f, -0.125f};
                    Kernel kernel = new Kernel(3, 3, kernelData2);
                    ConvolveOp cOp = new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP, null);
                    mBufferedImage = cOp.filter(mBufferedImage, null);
                    // 近JPEG编码
                    ImageOutputStream ios = ImageIO.createImageOutputStream(baos);
                    ImageIO.write(mBufferedImage, "jpg", ios);
                    currentImageBase64Str = new String(Base64.encode(baos.toByteArray(), Base64.ONE_LINE_LENGTH));
                    currentSize = currentImageBase64Str.getBytes().length;
                    // 图片压缩比异常减小
                    scale -= 0.05F;
                } catch (Exception e) {
                    LOGGER.error("按照指定的文件大小缩小图片", e);
                    break;
                }
            } while (currentSize > specificationSize);
        }

        return currentImageBase64Str;
    }

    /**
     * 获取图片对象（jpg图片支持CMYK模式）
     *
     * @throws IOException
     */
    private static Image getImage(Object input) throws IOException {
        Image image = null;
        ImageReader reader = new CMYKJPEGImageReader(new CMYKJPEGImageReaderSpi());
        // Stream the image file (the original CMYK image)
        ImageInputStream in = ImageIO.createImageInputStream(input);
        reader.setInput(in);

        String format = reader.getFormatName();
        if ("JPEG".equalsIgnoreCase(format) || "JPG".equalsIgnoreCase(format)) {
            image = reader.read(0);
        }

        return image;
    }

    /**
     * 获取图片格式
     *
     * @param imageFile 图片文件
     * @return 图片后缀
     */
    public static String getImageFormatName(File imageFile) {
        try (ImageInputStream iis = ImageIO.createImageInputStream(imageFile)) {
            Iterator<ImageReader> iterator = ImageIO.getImageReaders(iis);
            if (!iterator.hasNext()) {
                return null;
            }

            ImageReader imageReader = iterator.next();
            if (imageReader != null && PlatformUtils.hasText(imageReader.getFormatName())) {
                return imageReader.getFormatName().toLowerCase();
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }

        return null;
    }

    /**
     * 裁剪图片:该方法需要本地安装GraphicsMagick，如何安装Google之
     *
     * @param srcPath 源图片路径
     * @param outPath 处理后图片路径
     * @param x       起始X坐标
     * @param y       起始Y坐标
     * @param width   裁剪宽度
     * @param height  裁剪高度
     *                返回true说明裁剪成功, 否则失败
     * @throws Exception
     */
    public static void cutImage(String srcPath, String outPath, int x, int y, int width, int height) throws Exception {
        try {
            IMOperation op = new IMOperation();
            op.addImage(srcPath);
            /** width：裁剪的宽度 * height：裁剪的高度 * x：裁剪的横坐标 * y：裁剪纵坐标 */
            op.crop(width, height, x, y);
            op.addImage(outPath);
            ConvertCmd convert = new ConvertCmd(true);
            String osName = System.getProperty("os.name").toLowerCase();
            if (osName.contains("win")) { // linux下不要设置此值，不然会报错
                convert.setSearchPath("E://Program Files//GraphicsMagick-1.3.19-Q8");
            }
            convert.run(op);
        } catch (Exception e) {
            LOGGER.error("cutImage", e);
            throw e;
        }
    }

    /**
     * 剪切图片
     *
     * @param sourcePath 待剪切图片路径
     * @param targetUrl  裁剪后保存路径（默认为源路径）
     * @param x          起始横坐标
     * @param y          起始纵坐标
     * @param width      剪切宽度
     * @param height     剪切高度
     * @return 裁剪后保存路径（图片后缀根据图片本身类型生成）
     * @throws IOException
     */
    public static String imageCut(String sourcePath, String targetUrl, int x, int y, int width, int height) throws IOException {
        File file = new File(sourcePath);
        if (!file.exists()) {
            throw new IOException("not found the image：" + sourcePath);
        }
        String targetPath = targetUrl;
        if (null == targetPath || targetPath.isEmpty()) {
            targetPath = sourcePath;
        }

        String formatName = getImageFormatName(file);
        if (null == formatName) {
            return targetPath;
        }

        formatName = formatName.toLowerCase();
        if ("jpeg".equals(formatName)) {
            formatName = "jpg";
        }

        // 防止图片后缀与图片本身类型不一致的情况
        String pathPrefix = getPathWithoutSuffix(targetPath);
        targetPath = pathPrefix + formatName;

        // GIF需要特殊处理
        if (formatName.equals(IMAGE_FORMAT.GIF.getValue())) {
            GifDecoder decoder = new GifDecoder();
            int status = decoder.read(sourcePath);
            if (status != GifDecoder.STATUS_OK) {
                throw new IOException("read image " + sourcePath + " error!");
            }

            AnimatedGifEncoder encoder = new AnimatedGifEncoder();
            encoder.start(targetPath);
            encoder.setRepeat(decoder.getLoopCount());
            for (int i = 0; i < decoder.getFrameCount(); i++) {
                encoder.setDelay(decoder.getDelay(i));
                BufferedImage childImage = decoder.getFrame(i);
                BufferedImage image = childImage.getSubimage(x, y, width,
                        height);
                encoder.addFrame(image);
            }
            encoder.finish();
        } else {
            // 普通图片
            BufferedImage image = ImageIO.read(file);
            image = image.getSubimage(x, y, width, height);
            ImageIO.write(image, formatName, new File(targetPath));
        }

        return targetPath;
    }

    /**
     * 获取不包含后缀的文件路径
     */
    public static String getPathWithoutSuffix(String src) {
        String path = src;
        int index = path.lastIndexOf(".");
        if (index > 0) {
            path = path.substring(0, index + 1);
        }
        return path;
    }

    /**
     * 图片格式
     *
     * @author sjdf
     */
    public enum IMAGE_FORMAT {
        BMP("bmp"),
        JPG("jpg"),
        WBMP("wbmp"),
        JPEG("jpeg"),
        PNG("png"),
        GIF("gif");

        private String value;

        IMAGE_FORMAT(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

}
