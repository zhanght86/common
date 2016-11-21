package com.sjdf.platform.xss.translate;

import com.sjdf.platform.CommonPlatformConstant;

import java.io.IOException;
import java.io.Writer;
import java.util.Arrays;
import java.util.EnumSet;

/**
 * Create at 2013年11月11日 下午6:17:52
 *
 * @author KETQI
 */
public class NumericEntityUnescaper extends CharSequenceTranslator {
    /** 操作类型 */
    public static enum OPTION {
        SEMI_COLON_REQUIRED, SEMI_COLON_OPTIONAL, ERROR_IF_NO_SEMI_COLON
    }

    // Create an OptionsSet class to hide some of the conditional logic below
    private final EnumSet<OPTION> options;

    /**
     * Create a UnicodeUnescaper.
     * <p/>
     * The constructor takes a list of options, only one type of which is
     * currently available (whether to allow, error or ignore the semi-colon on
     * the end of a numeric entity to being missing).
     * <p/>
     * For example, to support numeric entities without a ';': new
     * NumericEntityUnescaper(NumericEntityUnescaper.OPTION.SEMI_COLON_OPTIONAL)
     * and to throw an IllegalArgumentException when they're missing: new
     * NumericEntityUnescaper(NumericEntityUnescaper.OPTION.ERROR_IF_NO_SEMI_COLON)
     * <p/>
     * Note that the default behaviour is to ignore them.
     *
     * @param options to apply to this unescaper
     */
    public NumericEntityUnescaper(OPTION... options) {
        if (options.length > 0) {
            this.options = EnumSet.copyOf(Arrays.asList(options));
        } else {
            this.options = EnumSet.copyOf(Arrays.asList(new OPTION[]{OPTION.SEMI_COLON_REQUIRED}));
        }
    }

    /**
     * Whether the passed in option is currently set.
     *
     * @param option to check state of
     * @return whether the option is set
     */
    public boolean isSet(OPTION option) {
        return options == null ? false : options.contains(option);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int translate(CharSequence input, int index, Writer out) throws IOException {
        int seqEnd = input.length();
        // Uses -2 to ensure there is something after the &#
        if (input.charAt(index) == '&' && index < seqEnd - CommonPlatformConstant.LENGTH_2 && input.charAt(index + 1) == '#') {
            int start = index + CommonPlatformConstant.LENGTH_2;
            boolean isHex = false;

            char firstChar = input.charAt(start);
            if (firstChar == 'x' || firstChar == 'X') {
                start++;
                isHex = true;

                // Check there's more than just an x after the &#
                if (start == seqEnd) {
                    return 0;
                }
            }

            int end = start;
            // Note that this supports character codes without a ; on the end
            while (end < seqEnd && (input.charAt(end) >= '0' && input.charAt(end) <= '9' || input.charAt(end) >= 'a' && input.charAt(end) <= 'f' || input.charAt(end) >= 'A' && input.charAt(end) <= 'F')) {
                end++;
            }

            boolean semiNext = end != seqEnd && input.charAt(end) == ';';

            if (!semiNext) {
                if (isSet(OPTION.SEMI_COLON_REQUIRED)) {
                    return 0;
                } else if (isSet(OPTION.ERROR_IF_NO_SEMI_COLON)) {
                    throw new IllegalArgumentException("Semi-colon required at end of numeric entity");
                }
            }

            int entityValue;
            try {
                if (isHex) {
                    entityValue = Integer.parseInt(input.subSequence(start, end).toString(), CommonPlatformConstant.LENGTH_16);
                } else {
                    entityValue = Integer.parseInt(input.subSequence(start, end).toString(), CommonPlatformConstant.LENGTH_10);
                }
            } catch (NumberFormatException nfe) {
                return 0;
            }

            if (entityValue > 0xFFFF) {
                char[] chrs = Character.toChars(entityValue);
                out.write(chrs[0]);
                out.write(chrs[1]);
            } else {
                out.write(entityValue);
            }

            return CommonPlatformConstant.LENGTH_2 + end - start + (isHex ? 1 : 0) + (semiNext ? 1 : 0);
        }
        return 0;
    }
}
