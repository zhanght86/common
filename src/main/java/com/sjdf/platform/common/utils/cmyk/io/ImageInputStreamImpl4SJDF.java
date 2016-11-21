package com.sjdf.platform.common.utils.cmyk.io;

import com.sjdf.platform.CommonPlatformConstant;

import javax.imageio.stream.ImageInputStreamImpl;
import java.io.IOException;
import java.nio.ByteOrder;

/**
 * @author sjdf
 * @category 图片输入流修改
 */
public abstract class ImageInputStreamImpl4SJDF extends ImageInputStreamImpl {

    byte[] byteBuf = new byte[CommonPlatformConstant.LENGTH_8192];

    public short readShort() throws IOException {
        readFully(this.byteBuf, 0, CommonPlatformConstant.LENGTH_2);

        if (this.byteOrder == ByteOrder.BIG_ENDIAN) {
            return (short) ((this.byteBuf[0] & 0xFF) << CommonPlatformConstant.LENGTH_8 | (this.byteBuf[1] & 0xFF) << 0);
        }

        return (short) ((this.byteBuf[1] & 0xFF) << CommonPlatformConstant.LENGTH_8 | (this.byteBuf[0] & 0xFF) << 0);
    }

    public int readInt() throws IOException {
        readFully(this.byteBuf, 0, CommonPlatformConstant.LENGTH_4);

        if (this.byteOrder == ByteOrder.BIG_ENDIAN) {
            return (this.byteBuf[0] & 0xFF) << CommonPlatformConstant.LENGTH_24 | (this.byteBuf[1] & 0xFF) << CommonPlatformConstant.LENGTH_16 | (this.byteBuf[CommonPlatformConstant.LENGTH_2] & 0xFF) << CommonPlatformConstant.LENGTH_8
                    | (this.byteBuf[CommonPlatformConstant.LENGTH_3] & 0xFF) << 0;
        }

        return (this.byteBuf[CommonPlatformConstant.LENGTH_3] & 0xFF) << CommonPlatformConstant.LENGTH_24 | (this.byteBuf[CommonPlatformConstant.LENGTH_2] & 0xFF) << CommonPlatformConstant.LENGTH_16 | (this.byteBuf[1] & 0xFF) << CommonPlatformConstant.LENGTH_8
                | (this.byteBuf[0] & 0xFF) << 0;
    }
}