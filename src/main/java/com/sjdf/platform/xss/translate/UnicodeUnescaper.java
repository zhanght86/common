package com.sjdf.platform.xss.translate;

import com.sjdf.platform.CommonPlatformConstant;

import java.io.IOException;
import java.io.Writer;

/**
 * Create at 2013年11月11日 下午6:19:12
 *
 * @author KETQI
 * @category UnicodeUnescaper
 */
public class UnicodeUnescaper extends CharSequenceTranslator {
    @Override
    public int translate(CharSequence input, int index, Writer out) throws IOException {
        if (input.charAt(index) == '\\' && index + 1 < input.length() && input.charAt(index + 1) == 'u') {
            // consume optional additional 'u' chars
            int i = CommonPlatformConstant.LENGTH_2;
            while (index + i < input.length() && input.charAt(index + i) == 'u') {
                i++;
            }

            if (index + i < input.length() && input.charAt(index + i) == '+') {
                i++;
            }

            if (index + i + CommonPlatformConstant.LENGTH_4 <= input.length()) {
                // Get 4 hex digits
                CharSequence unicode = input.subSequence(index + i, index + i + CommonPlatformConstant.LENGTH_4);

                try {
                    int value = Integer.parseInt(unicode.toString(), CommonPlatformConstant.LENGTH_16);
                    out.write((char) value);
                } catch (NumberFormatException nfe) {
                    throw new IllegalArgumentException("Unable to parse unicode value: " + unicode, nfe);
                }
                return i + CommonPlatformConstant.LENGTH_4;
            } else {
                throw new IllegalArgumentException("Less than 4 hex digits in unicode value: '" + input.subSequence(index, input.length())
                        + "' due to end of CharSequence");
            }
        }
        return 0;
    }
}
