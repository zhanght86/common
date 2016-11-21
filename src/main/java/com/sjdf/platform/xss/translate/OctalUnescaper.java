package com.sjdf.platform.xss.translate;

import com.sjdf.platform.CommonPlatformConstant;

import java.io.IOException;
import java.io.Writer;

/**
 * Create at 2013年11月11日 下午6:20:41
 *
 * @author KETQI
 * @category OctalUnescaper
 */
public class OctalUnescaper extends CharSequenceTranslator {
    private static final int OCTAL_MAX = 377;

    /**
     * {@inheritDoc}
     */
    @Override
    public int translate(CharSequence input, int index, Writer out) throws IOException {
        if (input.charAt(index) == '\\' && index < (input.length() - 1) && Character.isDigit(input.charAt(index + 1))) {
            int start = index + 1;

            int end = index + CommonPlatformConstant.LENGTH_2;
            while (end < input.length() && Character.isDigit(input.charAt(end))) {
                end++;
                if (Integer.parseInt(input.subSequence(start, end).toString(), CommonPlatformConstant.LENGTH_10) > OCTAL_MAX) {
                    end--; // rollback
                    break;
                }
            }

            out.write(Integer.parseInt(input.subSequence(start, end).toString(), CommonPlatformConstant.LENGTH_8));
            return 1 + end - start;
        }
        return 0;
    }
}
