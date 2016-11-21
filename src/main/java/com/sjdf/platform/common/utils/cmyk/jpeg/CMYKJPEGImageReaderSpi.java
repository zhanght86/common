package com.sjdf.platform.common.utils.cmyk.jpeg;

import com.sjdf.platform.CommonPlatformConstant;

import javax.imageio.ImageReader;
import javax.imageio.spi.ImageReaderSpi;
import javax.imageio.stream.ImageInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;


/**
 * A reader for JPEG images in the CMYK color space.
 */
public class CMYKJPEGImageReaderSpi extends ImageReaderSpi {

    public CMYKJPEGImageReaderSpi() {
        super("Werner Randelshofer",
                "1.0",
                new String[]{"JPEG", "JPG"},
                new String[]{"jpg"},
                new String[]{"image/jpg"},
                "org.monte.media.jpeg.CMYKJPEGImageReader",
                new Class[]{ImageInputStream.class, InputStream.class, byte[].class},
                null,
                false,
                null,
                null,
                null,
                null,
                false,
                null,
                null,
                null,
                null
        );
    }

    @Override
    public boolean canDecodeInput(Object source) throws IOException {
        if (source instanceof ImageInputStream) {
            ImageInputStream in = (ImageInputStream) source;
            in.mark();
            // Check if file starts with a JFIF SOI magic (0xffd8=-40)
            if (in.readShort() != -CommonPlatformConstant.LENGTH_40) {
                in.reset();
                return false;
            }
            in.reset();
            return true;
        }
        return false;
    }

    @Override
    public ImageReader createReaderInstance(Object extension) throws IOException {
        return new CMYKJPEGImageReader(this);
    }

    @Override
    public String getDescription(Locale locale) {
        return "CMYK JPEG Image Reader";
    }
}
