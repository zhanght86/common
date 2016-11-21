package com.sjdf.platform.xss.translate;

import java.io.IOException;
import java.io.Writer;

/**
 * Create at 2013年11月11日 下午5:58:36
 *
 * @author KETQI
 * @category CodePointTranslator
 */
public abstract class CodePointTranslator extends CharSequenceTranslator {
    /**
     * Implementation of translate that maps onto the abstract translate(int, Writer) method.
     * {@inheritDoc}
     */
    @Override
    public final int translate(CharSequence input, int index, Writer out) throws IOException {
        int codepoint = Character.codePointAt(input, index);
        boolean consumed = translate(codepoint, out);
        if (consumed) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * Translate the specified codepoint into another.
     *
     * @param codepoint int character input to translate
     * @param out       Writer to optionally push the translated output to
     * @return boolean as to whether translation occurred or not
     * @throws IOException if and only if the Writer produces an IOException
     */
    public abstract boolean translate(int codepoint, Writer out) throws IOException;
}
