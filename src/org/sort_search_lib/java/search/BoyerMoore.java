/*
 * The MIT License
 *
 * Copyright 2017 Matthias Fussenegger
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.sort_search_lib.java.search;

import org.sort_search_lib.java.search.api.SingleStringSearch;

/**
 * Offers the Boyer-Moore algorithm for finding the first occurrence of a
 * {@link String} value (pattern) in the specified text. This implementation
 * makes use of both, the bad-suffix and the good-suffix rule.
 *
 * @author Matthias Fussenegger
 */
public class BoyerMoore implements SingleStringSearch {

    @Override
    public int indexOf(char[] text, CharSequence pattern) {
        if (text.length < 1 || pattern.length() < 1) {
            return 0;
        }
        int skipTable[] = makeSkipTable(pattern);
        int nextTable[] = makeNextTable(pattern);
        int i = pattern.length() - 1, j;
        while (i < text.length) {
            for (j = pattern.length() - 1; pattern.charAt(j) == text[i]; --i, --j) {
                if (j == 0) {
                    return i;
                }
            }
            i += Math.max(nextTable[pattern.length() - 1 - j], skipTable[text[i]]);
        }
        return NOT_FOUND;
    }

    /**
     * Calculation of the skip table ("bad rule").
     *
     * @param pattern the pattern that is required for table creation.
     * @return an array representing the skip table.
     */
    private int[] makeSkipTable(CharSequence pattern) {
        final int ALPHABET = 256; //ASCII
        int[] table = new int[ALPHABET];
        for (int i = 0; i < table.length; ++i) {
            table[i] = pattern.length();
        }
        for (int i = 0; i < pattern.length() - 1; ++i) {
            table[pattern.charAt(i)] = pattern.length() - 1 - i;
        }
        return table;
    }

    /**
     * Calculation of the next table ("good rule").
     *
     * @param pattern the pattern that is required for table creation.
     * @return an array representing the next table.
     */
    private int[] makeNextTable(CharSequence pattern) {
        int[] table = new int[pattern.length()];
        int lastPrefixPos = pattern.length();
        boolean isPrefix = true;
        /*iterate starting from end*/
        for (int i = pattern.length() - 1; i >= 0; --i) {
            /*check if pattern from j to length is prefix*/
            for (int j = i + 1, k = 0; j < pattern.length(); ++j, ++k) {
                if (pattern.charAt(j) != pattern.charAt(k)) {
                    isPrefix = false;
                    break;
                }
            }
            if (isPrefix) {
                lastPrefixPos = i + 1;
            }
            table[pattern.length() - 1 - i] = lastPrefixPos - i + pattern.length() - 1;
        }
        /*iterate starting from beginning*/
        for (int i = 0; i < pattern.length() - 1; ++i) {
            int suffixLength = suffixLength(pattern, i);
            table[suffixLength] = pattern.length() - 1 - i + suffixLength;
        }
        return table;
    }

    /**
     * Returns the maximum length of the pattern that ends at {@code pos}. To
     * put it more generally: Returns the suffix length of the pattern.
     *
     * @param pattern the pattern that is required to determine suffix length.
     * @param pos the current position in the pattern.
     * @return the length of the suffix.
     */
    private int suffixLength(CharSequence pattern, int pos) {
        int len = 0;
        for (int i = pos, j = pattern.length() - 1;
                i >= 0 && pattern.charAt(i) == pattern.charAt(j); --i, --j) {
            len += 1;
        }
        return len;
    }

}
