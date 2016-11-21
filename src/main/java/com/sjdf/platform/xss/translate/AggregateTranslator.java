package com.sjdf.platform.xss.translate;

import java.io.IOException;
import java.io.Writer;

/**
 * Create at 2013年11月11日 下午5:50:52
 *
 * @author KETQI
 * @category AggregateTranslator
 */
public class AggregateTranslator extends CharSequenceTranslator {
    private final CharSequenceTranslator[] translators;

    /**
     * Specify the translators to be used at creation time.
     *
     * @param translators CharSequenceTranslator array to aggregate
     */
    public AggregateTranslator(CharSequenceTranslator... translators) {
        if (translators != null) {
            this.translators = translators.clone();
        } else {
            this.translators = null;
        }
    }

    /**
     * The first translator to consume codepoints from the input is the
     * 'winner'. Execution stops with the number of consumed codepoints being
     * returned. {@inheritDoc}
     */
    @Override
    public int translate(CharSequence input, int index, Writer out) throws IOException {
        for (CharSequenceTranslator translator : translators) {
            int consumed = translator.translate(input, index, out);
            if (consumed != 0) {
                return consumed;
            }
        }
        return 0;
    }
}
