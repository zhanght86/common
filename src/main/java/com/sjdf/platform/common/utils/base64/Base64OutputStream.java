package com.sjdf.platform.common.utils.base64;

import com.sjdf.platform.CommonPlatformConstant;

import java.io.IOException;
import java.io.OutputStream;

/**
 * <p>
 * A base64 decoding output stream.
 * </p>
 * <p/>
 * <p>
 * It encodes in base64 everything passed to the stream, and it puts the encoded
 * data into the underlying stream.
 * </p>
 *
 * @author ketqi
 */
public final class Base64OutputStream extends OutputStream {
    /**
     * The underlying stream.
     */
    private OutputStream outputStream = null;
    /**
     * A value buffer.
     */
    private int buffer = 0;
    /**
     * How many bytes are currently in the value buffer?
     */
    private int byteCounter = 0;
    /**
     * A counter for the current line length.
     */
    private int lineCounter = 0;
    /**
     * The requested line length.
     */
    private int lineLength = 0;

    /**
     * <p>
     * It builds a base64 encoding output stream writing the encoded data in the
     * given underlying stream.
     * </p>
     * <p/>
     * <p>
     * The encoded data is wrapped to a new line (with a CRLF sequence) every 76
     * bytes sent to the underlying stream.
     * </p>
     *
     * @param outputStream The underlying stream.
     */
    public Base64OutputStream(OutputStream outputStream) {
        this(outputStream, CommonPlatformConstant.LENGTH_76);
    }

    /**
     * <p>
     * It builds a base64 encoding output stream writing the encoded data in the
     * given underlying stream.
     * </p>
     * <p/>
     * <p>
     * The encoded data is wrapped to a new line (with a CRLF sequence) every
     * <em>wrapAt</em> bytes sent to the underlying stream. If the
     * <em>wrapAt</em> supplied value is less than 1 the encoded data will not
     * be wrapped.
     * </p>
     *
     * @param outputStream The underlying stream.
     * @param wrapAt       The max line length for encoded data. If less than 1 no wrap
     *                     is applied.
     */
    public Base64OutputStream(OutputStream outputStream, int wrapAt) {
        this.outputStream = outputStream;
        this.lineLength = wrapAt;
    }

    public void write(int b) throws IOException {
        int value = (b & 0xFF) << (CommonPlatformConstant.LENGTH_16 - (byteCounter * CommonPlatformConstant.LENGTH_8));
        buffer = buffer | value;
        byteCounter++;
        if (byteCounter == CommonPlatformConstant.LENGTH_3) {
            commit();
        }
    }

    public void close() throws IOException {
        commit();
        outputStream.close();
    }

    /**
     * <p>
     * It commits 4 bytes to the underlying stream.
     * </p>
     */
    protected void commit() throws IOException {
        if (byteCounter > 0) {
            if (lineLength > 0 && lineCounter == lineLength) {
                outputStream.write("\r\n".getBytes());
                lineCounter = 0;
            }
            char b1 = Base64.SHARED_CHARS.charAt((buffer << CommonPlatformConstant.LENGTH_8) >>> CommonPlatformConstant.LENGTH_26);
            char b2 = Base64.SHARED_CHARS.charAt((buffer << CommonPlatformConstant.LENGTH_14) >>> CommonPlatformConstant.LENGTH_26);
            char b3 = (byteCounter < CommonPlatformConstant.LENGTH_2) ? Base64.SHARED_PAD : Base64.SHARED_CHARS.charAt((buffer << CommonPlatformConstant.LENGTH_20) >>> CommonPlatformConstant.LENGTH_26);
            char b4 = (byteCounter < CommonPlatformConstant.LENGTH_3) ? Base64.SHARED_PAD : Base64.SHARED_CHARS.charAt((buffer << CommonPlatformConstant.LENGTH_26) >>> CommonPlatformConstant.LENGTH_26);
            outputStream.write(b1);
            outputStream.write(b2);
            outputStream.write(b3);
            outputStream.write(b4);
            lineCounter += CommonPlatformConstant.LENGTH_4;
            byteCounter = 0;
            buffer = 0;
        }
    }
}