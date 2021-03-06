package com.sjdf.platform.common.utils.base64;

import com.sjdf.platform.CommonPlatformConstant;

import java.io.IOException;
import java.io.InputStream;

/**
 * <p>
 * A base64 encoding input stream.
 * </p>
 * <p/>
 * <p>
 * A <em>Base64InputStream</em> reads from an underlying stream which is
 * supposed to be a base64 encoded stream. <em>Base64InputStream</em> decodes
 * the data read from the underlying stream and returns the decoded bytes to the
 * caller.
 * </p>
 *
 * @author ketqi
 */
public final class Base64InputStream extends InputStream {
    private static final String BAD_BASE64 = "Bad base64 stream";
    /** The underlying stream. */
    private InputStream inputStream;
    /** The buffer. */
    private int[] buffer;
    /** A counter for values in the buffer. */
    private int bufferCounter = 0;
    /** End-of-stream flag. */
    private boolean eof = false;

    /**
     * <p>
     * It builds a base64 decoding input stream.
     * </p>
     *
     * @param inputStream The underlying stream, from which the encoded data is read.
     */
    public Base64InputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public int read() throws IOException {
        if (buffer == null || bufferCounter == buffer.length) {
            if (eof) {
                return -1;
            }
            acquire();
            if (buffer.length == 0) {
                buffer = null;
                return -1;
            }
            bufferCounter = 0;
        }
        return buffer[bufferCounter++];
    }

    /**
     * Reads from the underlying stream, decodes the data and puts the decoded
     * bytes into the buffer.
     */
    private void acquire() throws IOException {
        char[] four = new char[CommonPlatformConstant.LENGTH_4];
        int i = 0;
        do {
            int b = inputStream.read();
            if (b == -1) {
                if (i != 0) {
                    throw new IOException(BAD_BASE64);
                } else {
                    buffer = new int[0];
                    eof = true;
                    return;
                }
            }
            char c = (char) b;
            if (Base64.SHARED_CHARS.indexOf(c) != -1 || c == Base64.SHARED_PAD) {
                four[i++] = c;
            } else if (c != '\r' && c != '\n') {
                throw new IOException(BAD_BASE64);
            }
        } while (i < CommonPlatformConstant.LENGTH_4);
        boolean padded = false;
        for (i = 0; i < CommonPlatformConstant.LENGTH_4; i++) {
            if (four[i] != Base64.SHARED_PAD) {
                if (padded) {
                    throw new IOException(BAD_BASE64);
                }
            } else {
                if (!padded) {
                    padded = true;
                }
            }
        }
        int l;
        if (four[CommonPlatformConstant.LENGTH_3] == Base64.SHARED_PAD) {
            if (inputStream.read() != -1) {
                throw new IOException(BAD_BASE64);
            }
            eof = true;
            if (four[CommonPlatformConstant.LENGTH_2] == Base64.SHARED_PAD) {
                l = 1;
            } else {
                l = CommonPlatformConstant.LENGTH_2;
            }
        } else {
            l = CommonPlatformConstant.LENGTH_3;
        }
        int aux = 0;
        for (i = 0; i < CommonPlatformConstant.LENGTH_4; i++) {
            if (four[i] != Base64.SHARED_PAD) {
                aux = aux | (Base64.SHARED_CHARS.indexOf(four[i]) << (CommonPlatformConstant.LENGTH_6 * (CommonPlatformConstant.LENGTH_3 - i)));
            }
        }
        buffer = new int[l];
        for (i = 0; i < l; i++) {
            buffer[i] = (aux >>> (CommonPlatformConstant.LENGTH_8 * (CommonPlatformConstant.LENGTH_2 - i))) & 0xFF;
        }
    }

    public void close() throws IOException {
        inputStream.close();
    }
}