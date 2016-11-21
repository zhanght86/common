package com.sjdf.platform.common.utils.cmyk.jpeg;

import com.sjdf.platform.CommonPlatformConstant;
import com.sjdf.platform.common.utils.cmyk.io.ByteArrayImageInputStream;
import com.sjdf.platform.common.utils.cmyk.io.ImageInputStreamAdapter;
import com.sjdf.platform.log.logger.SJDFLogger;
import com.sjdf.platform.log.logger.SJDFLoggerFactory;
import com.sun.imageio.plugins.jpeg.JPEGImageReader;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.ImageTypeSpecifier;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.spi.ImageReaderSpi;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.stream.MemoryCacheImageInputStream;
import java.awt.color.ColorSpace;
import java.awt.color.ICC_ColorSpace;
import java.awt.color.ICC_Profile;
import java.awt.image.*;
import java.io.*;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Reads a JPEG image with colors in the CMYK color space.
 */
public class CMYKJPEGImageReader extends ImageReader {

    private static SJDFLogger logger = SJDFLoggerFactory.getSJDFLogger(CMYKJPEGImageReader.class);

    private boolean isYCCKInversed = true;
    private static final DirectColorModel RGB = new DirectColorModel(24, 0xff0000, 0xff00, 0xff, 0x0);
    /** When we read the header, we read the whole image. */
    private BufferedImage image;

    public CMYKJPEGImageReader(ImageReaderSpi originatingProvider) {
        super(originatingProvider);
    }

    @Override
    public int getNumImages(boolean allowSearch) throws IOException {
        return 1;
    }

    @Override
    public int getWidth(int imageIndex) throws IOException {
        readHeader();
        return image.getWidth();
    }

    @Override
    public int getHeight(int imageIndex) throws IOException {
        readHeader();
        return image.getHeight();
    }

    @Override
    public Iterator<ImageTypeSpecifier> getImageTypes(int imageIndex) throws IOException {
        readHeader();
        LinkedList<ImageTypeSpecifier> l = new LinkedList<ImageTypeSpecifier>();
        l.add(new ImageTypeSpecifier(RGB, RGB.createCompatibleSampleModel(image.getWidth(), image.getHeight())));
        return l.iterator();
    }

    @Override
    public IIOMetadata getStreamMetadata() throws IOException {
        return null;
    }

    @Override
    public IIOMetadata getImageMetadata(int imageIndex) throws IOException {
        return null;
    }

    @Override
    public BufferedImage read(int imageIndex, ImageReadParam param) throws IOException {
        if (imageIndex > 0) {
            throw new IndexOutOfBoundsException();
        }
        readHeader();
        return image;
    }

    /**
     * Reads the PGM header. Does nothing if the header has already been loaded.
     */
    private void readHeader() throws IOException {
        if (image == null) {

            ImageInputStream iis;
            Object in = getInput();

            if (in instanceof byte[]) {
                iis = new ByteArrayImageInputStream((byte[]) in);
            } else if (in instanceof ImageInputStream) {
                iis = (ImageInputStream) in;
            } else if (in instanceof InputStream) {
                iis = new MemoryCacheImageInputStream((InputStream) in);
            } else {
                throw new IOException("Can't handle input of type " + in);
            }
            image = read(iis, isYCCKInversed);
        }
    }

    /**
     * @return the YCCKInversed property.
     */
    public boolean isYCCKInversed() {
        return isYCCKInversed;
    }

    /**
     * @param newValue the new value
     */
    public void setYCCKInversed(boolean newValue) {
        this.isYCCKInversed = newValue;
    }

    public static BufferedImage read(ImageInputStream in, boolean inverseYCCKColors) throws IOException {
        // Seek to start of input stream
        in.seek(0);

        // Extract metadata from the JFIF stream.
        // --------------------------------------
        // In particular, we are interested into the following fields:
        int numberOfComponentsInFrame = 0;
        int app14AdobeColorTransform = 0;
        BufferedImage img = null;
        ByteArrayOutputStream app2ICCProfile = null;
        JFIFInputStream fifi = null;
        try {
            app2ICCProfile = new ByteArrayOutputStream();
            // Browse for marker segments, and extract data from those
            // which are of interest.
            fifi = new JFIFInputStream(new ImageInputStreamAdapter(in));
            for (JFIFInputStream.Segment seg = fifi.getNextSegment(); seg != null; seg = fifi.getNextSegment()) {
                if (0xffc0 <= seg.marker && seg.marker <= 0xffc3 || 0xffc5 <= seg.marker && seg.marker <= 0xffc7
                        || 0xffc9 <= seg.marker && seg.marker <= 0xffcb || 0xffcd <= seg.marker && seg.marker <= 0xffcf) {
                    // SOF0 - SOF15: Start of Frame Header marker segment
                    DataInputStream dis = new DataInputStream(fifi);
                    dis.readUnsignedByte();
                    dis.readUnsignedShort();
                    dis.readUnsignedShort();
                    numberOfComponentsInFrame = dis.readUnsignedByte();
                    // ...the rest of SOF header is not important to us.
                    // In fact, by encounterint a SOF header, we have reached
                    // the end of the metadata section we are interested in.
                    // Thus we can abort here.
                    break;

                } else if (seg.marker == 0xffe2) {
                    // APP2: Application-specific marker segment
                    if (seg.length >= CommonPlatformConstant.LENGTH_26) {
                        DataInputStream dis = new DataInputStream(fifi);
                        // Check for 12-bytes containing the null-terminated
                        // string: "ICC_PROFILE".
                        if (dis.readLong() == 0x4943435f50524f46L && dis.readInt() == 0x494c4500) {
                            // Skip 2 bytes
                            dis.skipBytes(CommonPlatformConstant.LENGTH_2);

                            // Read Adobe ICC_PROFILE int buffer. The profile is
                            // split up over
                            // multiple APP2 marker segments.
                            byte[] b = new byte[CommonPlatformConstant.LENGTH_512];
                            for (int count = dis.read(b); count != -1; count = dis.read(b)) {
                                app2ICCProfile.write(b, 0, count);
                            }
                        }
                    }
                } else if (seg.marker == 0xffee && seg.length == CommonPlatformConstant.LENGTH_12) {
                    DataInputStream dis = new DataInputStream(fifi);
                    // Check for 6-bytes containing the null-terminated
                    // string: "Adobe".
                    if (dis.readInt() == 0x41646f62L && dis.readUnsignedShort() == 0x6500) {
                        dis.readUnsignedByte();
                        dis.readUnsignedShort();
                        dis.readUnsignedShort();
                        app14AdobeColorTransform = dis.readUnsignedByte();
                    }
                }
            }

            // Read the image data
            if (numberOfComponentsInFrame != CommonPlatformConstant.LENGTH_4) {
                // Read image with YUV color encoding.
                in.seek(0);
                img = readImageFromYUVorGray(in);
            } else if (numberOfComponentsInFrame == CommonPlatformConstant.LENGTH_4) {

                // Try to instantiate an ICC_Profile from the app2ICCProfile
                ICC_Profile profile = null;
                if (app2ICCProfile.size() > 0) {
                    try {
                        profile = ICC_Profile.getInstance(new ByteArrayInputStream(app2ICCProfile.toByteArray()));
                    } catch (Exception ex) {
                        logger.error(ex.getMessage(), ex);
                    }
                }

                // In case of failure, use a Generic CMYK profile
                if (profile == null) {
                    profile = ICC_Profile.getInstance(CMYKJPEGImageReader.class
                            .getResourceAsStream("Generic CMYK Profile.icc"));
                }
                switch (app14AdobeColorTransform) {
                    case 1:
                        throw new IOException("YCbCr not supported");
                    case CommonPlatformConstant.LENGTH_2:
                        // Read image with inverted YCCK color encoding.
                        // YCCK colors are inverted?
                        in.seek(0);
                        if (inverseYCCKColors) {
                            img = readRGBImageFromInvertedYCCK(new ImageInputStreamAdapter(in), profile);
                        } else {
                            img = readRGBImageFromYCCK(new ImageInputStreamAdapter(in), profile);
                        }
                        break;
                    case 0:
                    default:
                        // Read image with RGBA color encoding.
                        in.seek(0);
                        img = readRGBAImageFromRGBA(new ImageInputStreamAdapter(in), profile);
                        break;
                }
            }
        } catch (Exception e) {
            logger.error("readBufferedImage", e);
        } finally {
            if (fifi != null) {
                fifi.close();
            }
            if (app2ICCProfile != null) {
                app2ICCProfile.close();
            }
        }

        return img;
    }

    private static ImageReader createNativeJPEGReader() {
        return new JPEGImageReader(new CMYKJPEGImageReaderSpi());
    }

    /**
     * Reads a CMYK JPEG image from the provided InputStream, converting the
     * colors to RGB using the provided CMYK ICC_Profile. The image data must be
     * in the CMYK color space.
     * <p/>
     * Use this method, if you have already determined that the input stream
     * contains a CMYK JPEG image.
     *
     * @param in          An InputStream, preferably an ImageInputStream, in the JPEG
     *                    File Interchange Format (JFIF).
     * @param cmykProfile An ICC_Profile for conversion from the CMYK color space to the
     *                    RGB color space. If this parameter is null, a default profile
     *                    is used.
     * @return a BufferedImage containing the decoded image converted into the
     * RGB color space.
     * @throws java.io.IOException
     */
    public static BufferedImage readRGBImageFromCMYK(InputStream in, ICC_Profile cmykProfile) throws IOException {
        ImageInputStream inputStream = null;
        ImageReader reader = createNativeJPEGReader();
        inputStream = (in instanceof ImageInputStream) ? (ImageInputStream) in : ImageIO.createImageInputStream(in);
        reader.setInput(inputStream);
        Raster raster = reader.readRaster(0, null);
        BufferedImage image = createRGBImageFromCMYK(raster, cmykProfile);
        return image;
    }

    /**
     * Reads a RGBA JPEG image from the provided InputStream, converting the
     * colors to RGBA using the provided RGBA ICC_Profile. The image data must
     * be in the RGBA color space.
     * <p/>
     * Use this method, if you have already determined that the input stream
     * contains a RGBA JPEG image.
     *
     * @param in          An InputStream, preferably an ImageInputStream, in the JPEG
     *                    File Interchange Format (JFIF).
     * @param rgbaProfile An ICC_Profile for conversion from the RGBA color space to the
     *                    RGBA color space. If this parameter is null, a default profile
     *                    is used.
     * @return a BufferedImage containing the decoded image converted into the
     * RGB color space.
     * @throws java.io.IOException
     */
    public static BufferedImage readRGBAImageFromRGBA(InputStream in, ICC_Profile rgbaProfile) throws IOException {
        ImageInputStream inputStream;
        ImageReader reader = createNativeJPEGReader();
        inputStream = (in instanceof ImageInputStream) ? (ImageInputStream) in : ImageIO.createImageInputStream(in);
        reader.setInput(inputStream);
        Raster raster = reader.readRaster(0, null);
        return createRGBAImageFromRGBA(raster, rgbaProfile);
    }

    /**
     * Reads a YCCK JPEG image from the provided InputStream, converting the
     * colors to RGB using the provided CMYK ICC_Profile. The image data must be
     * in the YCCK color space.
     * <p/>
     * Use this method, if you have already determined that the input stream
     * contains a YCCK JPEG image.
     *
     * @param in          An InputStream, preferably an ImageInputStream, in the JPEG
     *                    File Interchange Format (JFIF).
     * @param cmykProfile An ICC_Profile for conversion from the CMYK color space to the
     *                    RGB color space. If this parameter is null, a default profile
     *                    is used.
     * @return a BufferedImage containing the decoded image converted into the
     * RGB color space.
     * @throws java.io.IOException
     */
    public static BufferedImage readRGBImageFromYCCK(InputStream in, ICC_Profile cmykProfile) throws IOException {
        ImageInputStream inputStream;
        ImageReader reader = createNativeJPEGReader();
        inputStream = (in instanceof ImageInputStream) ? (ImageInputStream) in : ImageIO.createImageInputStream(in);
        reader.setInput(inputStream);
        Raster raster = reader.readRaster(0, null);
        return createRGBImageFromYCCK(raster, cmykProfile);
    }

    /**
     * Reads an inverted-YCCK JPEG image from the provided InputStream,
     * converting the colors to RGB using the provided CMYK ICC_Profile. The
     * image data must be in the inverted-YCCK color space.
     * <p/>
     * Use this method, if you have already determined that the input stream
     * contains an inverted-YCCK JPEG image.
     *
     * @param in          An InputStream, preferably an ImageInputStream, in the JPEG
     *                    File Interchange Format (JFIF).
     * @param cmykProfile An ICC_Profile for conversion from the CMYK color space to the
     *                    RGB color space. If this parameter is null, a default profile
     *                    is used.
     * @return a BufferedImage containing the decoded image converted into the
     * RGB color space.
     * @throws java.io.IOException
     */
    public static BufferedImage readRGBImageFromInvertedYCCK(InputStream in, ICC_Profile cmykProfile)
            throws IOException {
        ImageInputStream inputStream;
        ImageReader reader = createNativeJPEGReader();
        inputStream = (in instanceof ImageInputStream) ? (ImageInputStream) in : ImageIO.createImageInputStream(in);
        reader.setInput(inputStream);
        Raster raster = reader.readRaster(0, null);
        raster = convertInvertedYCCKToCMYK(raster);
        return createRGBImageFromCMYK(raster, cmykProfile);
    }

    /**
     * Creates a buffered image from a raster in the YCCK color space,
     * converting the colors to RGB using the provided CMYK ICC_Profile.
     *
     * @param ycckRastor  A raster with (at least) 4 bands of samples.
     * @param cmykProfile An ICC_Profile for conversion from the CMYK color space to the
     *                    RGB color space. If this parameter is null, a default profile
     *                    is used.
     * @return a BufferedImage in the RGB color space.
     */
    public static BufferedImage createRGBImageFromYCCK(Raster ycckRastor, ICC_Profile cmykProfile) {
        Raster ycckRaster = ycckRastor;
        BufferedImage image;
        if (cmykProfile != null) {
            ycckRaster = convertYCCKtoCMYK(ycckRaster);
            image = createRGBImageFromCMYK(ycckRaster, cmykProfile);
        } else {
            int w = ycckRaster.getWidth(), h = ycckRaster.getHeight();
            int[] rgb = new int[w * h];
            int[] yAttr = ycckRaster.getSamples(0, 0, w, h, 0, (int[]) null);
            int[] cbAttr = ycckRaster.getSamples(0, 0, w, h, 1, (int[]) null);
            int[] crAttr = ycckRaster.getSamples(0, 0, w, h, CommonPlatformConstant.LENGTH_2, (int[]) null);
            int[] kAttr = ycckRaster.getSamples(0, 0, w, h, CommonPlatformConstant.LENGTH_3, (int[]) null);

            float vr, vg, vb;
            for (int i = 0, imax = yAttr.length; i < imax; i++) {
                float k = kAttr[i], y = yAttr[i], cb = cbAttr[i], cr = crAttr[i];
                vr = y + 1.402f * (cr - CommonPlatformConstant.LENGTH_128) - k;
                vg = y - 0.34414f * (cb - CommonPlatformConstant.LENGTH_128) - 0.71414f * (cr - CommonPlatformConstant.LENGTH_128) - k;
                vb = y + 1.772f * (cb - CommonPlatformConstant.LENGTH_128) - k;
                rgb[i] = (0xff & (vr < 0.0f ? 0 : vr > 255.0f ? 0xff : (int) (vr + 0.5f))) << CommonPlatformConstant.LENGTH_16
                        | (0xff & (vg < 0.0f ? 0 : vg > 255.0f ? 0xff : (int) (vg + 0.5f))) << CommonPlatformConstant.LENGTH_8
                        | (0xff & (vb < 0.0f ? 0 : vb > 255.0f ? 0xff : (int) (vb + 0.5f)));
            }

            Raster rgbRaster = Raster.createPackedRaster(new DataBufferInt(rgb, rgb.length), w, h, w, new int[]{
                    0xff0000, 0xff00, 0xff}, null);
            ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_sRGB);
            ColorModel cm = new DirectColorModel(cs, CommonPlatformConstant.LENGTH_24, 0xff0000, 0xff00, 0xff, 0x0, false, DataBuffer.TYPE_INT);

            image = new BufferedImage(cm, (WritableRaster) rgbRaster, true, null);
        }
        return image;
    }

    /**
     * Creates a buffered image from a raster in the inverted YCCK color space,
     * converting the colors to RGB using the provided CMYK ICC_Profile.
     *
     * @param ycckRastor  A raster with (at least) 4 bands of samples.
     * @param cmykProfile An ICC_Profile for conversion from the CMYK color space to the
     *                    RGB color space. If this parameter is null, a default profile
     *                    is used.
     * @return a BufferedImage in the RGB color space.
     */
    public static BufferedImage createRGBImageFromInvertedYCCK(Raster ycckRastor, ICC_Profile cmykProfile) {
        Raster ycckRaster = ycckRastor;
        BufferedImage image;
        if (cmykProfile != null) {
            ycckRaster = convertInvertedYCCKToCMYK(ycckRaster);
            image = createRGBImageFromCMYK(ycckRaster, cmykProfile);
        } else {
            int w = ycckRaster.getWidth(), h = ycckRaster.getHeight();
            int[] rgb = new int[w * h];

            int[] yAttr = ycckRaster.getSamples(0, 0, w, h, 0, (int[]) null);
            int[] cbAttr = ycckRaster.getSamples(0, 0, w, h, 1, (int[]) null);
            int[] crAttr = ycckRaster.getSamples(0, 0, w, h, CommonPlatformConstant.LENGTH_2, (int[]) null);
            int[] kAttr = ycckRaster.getSamples(0, 0, w, h, CommonPlatformConstant.LENGTH_3, (int[]) null);
            float vr, vg, vb;
            for (int i = 0, imax = yAttr.length; i < imax; i++) {
                float k = CommonPlatformConstant.LENGTH_255 - kAttr[i], y = CommonPlatformConstant.LENGTH_255 - yAttr[i], cb = CommonPlatformConstant.LENGTH_255 - cbAttr[i], cr = CommonPlatformConstant.LENGTH_255 - crAttr[i];
                vr = y + 1.402f * (cr - CommonPlatformConstant.LENGTH_128) - k;
                vg = y - 0.34414f * (cb - CommonPlatformConstant.LENGTH_128) - 0.71414f * (cr - CommonPlatformConstant.LENGTH_128) - k;
                vb = y + 1.772f * (cb - CommonPlatformConstant.LENGTH_128) - k;
                rgb[i] = (0xff & (vr < 0.0f ? 0 : vr > 255.0f ? 0xff : (int) (vr + 0.5f))) << CommonPlatformConstant.LENGTH_16
                        | (0xff & (vg < 0.0f ? 0 : vg > 255.0f ? 0xff : (int) (vg + 0.5f))) << CommonPlatformConstant.LENGTH_8
                        | (0xff & (vb < 0.0f ? 0 : vb > 255.0f ? 0xff : (int) (vb + 0.5f)));
            }

            Raster rgbRaster = Raster.createPackedRaster(new DataBufferInt(rgb, rgb.length), w, h, w, new int[]{
                    0xff0000, 0xff00, 0xff}, null);
            ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_sRGB);
            ColorModel cm = new DirectColorModel(cs, CommonPlatformConstant.LENGTH_24, 0xff0000, 0xff00, 0xff, 0x0, false, DataBuffer.TYPE_INT);

            image = new BufferedImage(cm, (WritableRaster) rgbRaster, true, null);
        }
        return image;
    }

    /**
     * Creates a buffered image from a raster in the CMYK color space,
     * converting the colors to RGB using the provided CMYK ICC_Profile.
     * <p/>
     * As seen from a comment made by 'phelps' at
     * http://bugs.sun.com/bugdatabase/view_bug.do?bug_id=4799903
     *
     * @param cmykRaster  A raster with (at least) 4 bands of samples.
     * @param cmykProfile An ICC_Profile for conversion from the CMYK color space to the
     *                    RGB color space. If this parameter is null, a default profile
     *                    is used.
     * @return a BufferedImage in the RGB color space.
     */
    public static BufferedImage createRGBImageFromCMYK(Raster cmykRaster, ICC_Profile cmykProfile) {
        BufferedImage image;
        int w = cmykRaster.getWidth();
        int h = cmykRaster.getHeight();

        if (cmykProfile != null) {
            ColorSpace cmykCS = new ICC_ColorSpace(cmykProfile);
            image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
            WritableRaster rgbRaster = image.getRaster();
            ColorSpace rgbCS = image.getColorModel().getColorSpace();
            ColorConvertOp cmykToRgb = new ColorConvertOp(cmykCS, rgbCS, null);
            cmykToRgb.filter(cmykRaster, rgbRaster);
        } else {

            int[] rgb = new int[w * h];

            int[] cAttr = cmykRaster.getSamples(0, 0, w, h, 0, (int[]) null);
            int[] mAttr = cmykRaster.getSamples(0, 0, w, h, 1, (int[]) null);
            int[] yAttr = cmykRaster.getSamples(0, 0, w, h, CommonPlatformConstant.LENGTH_2, (int[]) null);
            int[] kAttr = cmykRaster.getSamples(0, 0, w, h, CommonPlatformConstant.LENGTH_3, (int[]) null);

            for (int i = 0, imax = cAttr.length; i < imax; i++) {
                int k = kAttr[i];
                rgb[i] = (CommonPlatformConstant.LENGTH_255 - Math.min(CommonPlatformConstant.LENGTH_255, cAttr[i] + k)) << CommonPlatformConstant.LENGTH_16 | (CommonPlatformConstant.LENGTH_255 - Math.min(CommonPlatformConstant.LENGTH_255, mAttr[i] + k)) << CommonPlatformConstant.LENGTH_8
                        | (CommonPlatformConstant.LENGTH_255 - Math.min(CommonPlatformConstant.LENGTH_255, yAttr[i] + k));
            }

            Raster rgbRaster = Raster.createPackedRaster(new DataBufferInt(rgb, rgb.length), w, h, w, new int[]{
                    0xff0000, 0xff00, 0xff}, null);
            ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_sRGB);
            ColorModel cm = new DirectColorModel(cs, CommonPlatformConstant.LENGTH_24, 0xff0000, 0xff00, 0xff, 0x0, false, DataBuffer.TYPE_INT);
            image = new BufferedImage(cm, (WritableRaster) rgbRaster, true, null);
        }
        return image;
    }

    /**
     * Creates a buffered image from a raster in the RGBA color space,
     * converting the colors to RGB using the provided CMYK ICC_Profile.
     * <p/>
     * As seen from a comment made by 'phelps' at
     * http://bugs.sun.com/bugdatabase/view_bug.do?bug_id=4799903
     *
     * @param rgbaRaster  A raster with (at least) 4 bands of samples.
     * @param rgbaProfile An ICC_Profile for conversion from the CMYK color space to the
     *                    RGB color space. If this parameter is null, a default profile
     *                    is used.
     * @return a BufferedImage in the RGB color space.
     */
    public static BufferedImage createRGBAImageFromRGBA(Raster rgbaRaster, ICC_Profile rgbaProfile) {
        BufferedImage image;
        int w = rgbaRaster.getWidth();
        int h = rgbaRaster.getHeight();

        int[] rgb = new int[w * h];

        int[] rAttr = rgbaRaster.getSamples(0, 0, w, h, 0, (int[]) null);
        int[] gAttr = rgbaRaster.getSamples(0, 0, w, h, 1, (int[]) null);
        int[] bAttr = rgbaRaster.getSamples(0, 0, w, h, CommonPlatformConstant.LENGTH_2, (int[]) null);
        int[] aAttr = rgbaRaster.getSamples(0, 0, w, h, CommonPlatformConstant.LENGTH_3, (int[]) null);

        for (int i = 0, imax = rAttr.length; i < imax; i++) {
            rgb[i] = aAttr[i] << CommonPlatformConstant.LENGTH_24 | rAttr[i] << CommonPlatformConstant.LENGTH_16 | gAttr[i] << CommonPlatformConstant.LENGTH_8 | bAttr[i];
        }

        Raster rgbRaster = Raster.createPackedRaster(new DataBufferInt(rgb, rgb.length), w, h, w, new int[]{0xff0000,
                0xff00, 0xff, 0xff000000}, null);
        ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_sRGB);
        ColorModel cm = new DirectColorModel(cs, CommonPlatformConstant.LENGTH_32, 0xff0000, 0xff00, 0xff, 0x0ff000000, false, DataBuffer.TYPE_INT);
        image = new BufferedImage(cm, (WritableRaster) rgbRaster, true, null);

        return image;
    }

    /** Define tables for YCC->RGB colorspace conversion. */
    private static final int SCALEBITS = 16;
    private static final int MAXJSAMPLE = 255;
    private static final int CENTERJSAMPLE = 128;
    private static final int ONE_HALF = 1 << (SCALEBITS - 1);
    private static final int[] CR_R_TAB = new int[MAXJSAMPLE + 1];
    private static final int[] CB_B_TAB = new int[MAXJSAMPLE + 1];
    private static final int[] CR_G_TAB = new int[MAXJSAMPLE + 1];
    private static final int[] CB_G_TAB = new int[MAXJSAMPLE + 1];

    /*
     * Initialize tables for YCC->RGB colorspace conversion.
     */
    private static synchronized void buildYCCtoRGBtable() {
        if (CR_R_TAB[0] == 0) {
            for (int i = 0, x = -CENTERJSAMPLE; i <= MAXJSAMPLE; i++, x++) {
                /* i is the actual input pixel value, in the range 0..MAXJSAMPLE */
                /*
                 * The Cb or Cr value we are thinking of is x = i -
				 * CENTERJSAMPLE
				 */
				/* Cr=>R value is nearest int to 1.40200 * x */
                CR_R_TAB[i] = (int) ((1.40200 * (1 << SCALEBITS) + 0.5) * x + ONE_HALF) >> SCALEBITS;
				/* Cb=>B value is nearest int to 1.77200 * x */
                CB_B_TAB[i] = (int) ((1.77200 * (1 << SCALEBITS) + 0.5) * x + ONE_HALF) >> SCALEBITS;
				/* Cr=>G value is scaled-up -0.71414 * x */
                CR_G_TAB[i] = -(int) (0.71414 * (1 << SCALEBITS) + 0.5) * x;
				/* Cb=>G value is scaled-up -0.34414 * x */
				/* We also add in ONE_HALF so that need not do it in inner loop */
                CB_G_TAB[i] = -(int) ((0.34414) * (1 << SCALEBITS) + 0.5) * x + ONE_HALF;
            }
        }
    }

    /*
     * Adobe-style YCCK->CMYK conversion. We convert YCbCr to R=1-C, G=1-M, and
     * B=1-Y using the same conversion as above, while passing K (black)
     * unchanged. We assume build_ycc_rgb_table has been called.
     */
    private static Raster convertInvertedYCCKToCMYK(Raster ycckRaster) {
        buildYCCtoRGBtable();

        int w = ycckRaster.getWidth(), h = ycckRaster.getHeight();
        int[] ycckY = ycckRaster.getSamples(0, 0, w, h, 0, (int[]) null);
        int[] ycckCb = ycckRaster.getSamples(0, 0, w, h, 1, (int[]) null);
        int[] ycckCr = ycckRaster.getSamples(0, 0, w, h, 2, (int[]) null);
        int[] ycckK = ycckRaster.getSamples(0, 0, w, h, 3, (int[]) null);
        int[] cmyk = new int[ycckY.length];

        for (int i = 0; i < ycckY.length; i++) {
            int y = CommonPlatformConstant.LENGTH_255 - ycckY[i];
            int cb = CommonPlatformConstant.LENGTH_255 - ycckCb[i];
            int cr = CommonPlatformConstant.LENGTH_255 - ycckCr[i];
            int cmykC, cmykM, cmykY;
            // Range-limiting is essential due to noise introduced by DCT
            // losses.
            cmykC = MAXJSAMPLE - (y + CR_R_TAB[cr]); // red
            cmykM = MAXJSAMPLE - (y + // green
                    (CB_G_TAB[cb] + CR_G_TAB[cr] >> SCALEBITS));
            cmykY = MAXJSAMPLE - (y + CB_B_TAB[cb]); // blue
			/* K passes through unchanged */
            cmyk[i] = (cmykC < 0 ? 0 : (cmykC > CommonPlatformConstant.LENGTH_255) ? CommonPlatformConstant.LENGTH_255 : cmykC) << CommonPlatformConstant.LENGTH_24
                    | (cmykM < 0 ? 0 : (cmykM > CommonPlatformConstant.LENGTH_255) ? CommonPlatformConstant.LENGTH_255 : cmykM) << CommonPlatformConstant.LENGTH_16
                    | (cmykY < 0 ? 0 : (cmykY > CommonPlatformConstant.LENGTH_255) ? CommonPlatformConstant.LENGTH_255 : cmykY) << CommonPlatformConstant.LENGTH_8 | CommonPlatformConstant.LENGTH_255 - ycckK[i];
        }

        Raster cmykRaster = Raster.createPackedRaster(new DataBufferInt(cmyk, cmyk.length), w, h, w, new int[]{
                0xff000000, 0xff0000, 0xff00, 0xff}, null);
        return cmykRaster;

    }

    private static Raster convertYCCKtoCMYK(Raster ycckRaster) {
        buildYCCtoRGBtable();

        int w = ycckRaster.getWidth(), h = ycckRaster.getHeight();
        int[] ycckY = ycckRaster.getSamples(0, 0, w, h, 0, (int[]) null);
        int[] ycckCb = ycckRaster.getSamples(0, 0, w, h, 1, (int[]) null);
        int[] ycckCr = ycckRaster.getSamples(0, 0, w, h, CommonPlatformConstant.LENGTH_2, (int[]) null);
        int[] ycckK = ycckRaster.getSamples(0, 0, w, h, CommonPlatformConstant.LENGTH_3, (int[]) null);

        int[] cmyk = new int[ycckY.length];

        for (int i = 0; i < ycckY.length; i++) {
            int y = ycckY[i];
            int cb = ycckCb[i];
            int cr = ycckCr[i];
            int cmykC, cmykM, cmykY;
            // Range-limiting is essential due to noise introduced by DCT
            // losses.
            cmykC = MAXJSAMPLE - (y + CR_R_TAB[cr]); // red
            cmykM = MAXJSAMPLE - (y + // green
                    (CB_G_TAB[cb] + CR_G_TAB[cr] >> SCALEBITS));
            cmykY = MAXJSAMPLE - (y + CB_B_TAB[cb]); // blue
			/* K passes through unchanged */
            cmyk[i] = (cmykC < 0 ? 0 : (cmykC > CommonPlatformConstant.LENGTH_255) ? CommonPlatformConstant.LENGTH_255 : cmykC) << CommonPlatformConstant.LENGTH_24
                    | (cmykM < 0 ? 0 : (cmykM > CommonPlatformConstant.LENGTH_255) ? CommonPlatformConstant.LENGTH_255 : cmykM) << CommonPlatformConstant.LENGTH_16
                    | (cmykY < 0 ? 0 : (cmykY > CommonPlatformConstant.LENGTH_255) ? CommonPlatformConstant.LENGTH_255 : cmykY) << CommonPlatformConstant.LENGTH_8 | ycckK[i];
        }

        return Raster.createPackedRaster(new DataBufferInt(cmyk, cmyk.length), w, h, w, new int[]{0xff000000,
                0xff0000, 0xff00, 0xff}, null);
    }

    /**
     * Reads a JPEG image from the provided InputStream. The image data must be
     * in the YUV or the Gray color space.
     * <p/>
     * Use this method, if you have already determined that the input stream
     * contains a YUV or Gray JPEG image.
     *
     * @param in An InputStream, preferably an ImageInputStream, in the JPEG
     *           File Interchange Format (JFIF).
     * @return a BufferedImage containing the decoded image converted into the
     * RGB color space.
     * @throws java.io.IOException
     */
    public static BufferedImage readImageFromYUVorGray(ImageInputStream in) throws IOException {
        ImageReader r = createNativeJPEGReader();
        r.setInput(in);
        return r.read(0);
    }
}
