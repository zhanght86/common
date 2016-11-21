package com.sjdf.platform.xss.translate;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;

/**
 * Create at 2013年11月11日 下午5:54:41
 *
 * @author KETQI
 * @category LookupTranslator
 */
public class LookupTranslator extends CharSequenceTranslator {
    private final HashMap<CharSequence, CharSequence> lookupMap;
    private final int shortest;
    private final int longest;

    /**
     * Define the lookup table to be used in translation
     *
     * @param lookup CharSequence[][] table of size [*][2]
     */
    public LookupTranslator(CharSequence[]... lookup) {
        lookupMap = new HashMap<CharSequence, CharSequence>();
        int shorTest = Integer.MAX_VALUE;
        int longTest = 0;
        if (lookup != null) {
            for (CharSequence[] seq : lookup) {
                this.lookupMap.put(seq[0], seq[1]);
                int sz = seq[0].length();
                if (sz < shorTest) {
                    shorTest = sz;
                }
                if (sz > longTest) {
                    longTest = sz;
                }
            }
        }
        shortest = shorTest;
        longest = longTest;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int translate(CharSequence input, int index, Writer out) throws IOException {
        int max = longest;
        if (index + longest > input.length()) {
            max = input.length() - index;
        }
        // descend so as to get a greedy algorithm
        for (int i = max; i >= shortest; i--) {
            CharSequence subSeq = input.subSequence(index, index + i);
            CharSequence result = lookupMap.get(subSeq);
            if (result != null) {
                out.write(result.toString());
                return i;
            }
        }
        return 0;
    }
}
