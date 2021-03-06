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
 * Offers the Knuth-Morris-Pratt algorithm for finding the first occurrence of a
 * specified pattern in the specified text.
 *
 * @author Matthias Fussenegger
 */
public class KnuthMorrisPratt implements SingleStringSearch {

    @Override
    public int indexOf(String text, CharSequence pattern) {
        if (text.length() < 1 || pattern.length() < 1) {
            return NOT_FOUND;
        }
        int[] prefixTable = makePrefixTable(pattern);
        return search(text, pattern, prefixTable);
    }

    /**
     * Calculation of the prefix table.
     *
     * @param pattern the pattern that is required for table creation.
     * @return an array representing the prefix table.
     */
    private static int[] makePrefixTable(CharSequence pattern) {
        int[] prefixTable = new int[pattern.length() + 1];
        prefixTable[0] = -1; // first element has to be -1

        for (int i = 0, j = -1; i < pattern.length();) {
            while (j >= 0 && pattern.charAt(j) != pattern.charAt(i)) {
                j = prefixTable[j];
            }
            ++i;
            ++j;
            prefixTable[i] = j;
        }
        return prefixTable;
    }

    /**
     * The actual search of the beginning of the pattern in the text.
     *
     * @param text the text to be searched for pattern.
     * @param pattern the pattern to be found in text.
     * @param prefixTable the previously generated prefix table.
     * @return the position of the first occurrence in the text.
     */
    private static int search(String text, CharSequence pattern, int[] prefixTable) {
        int i = 0; // position in text
        int j = 0; // position in pattern

        while (j < pattern.length() && i < text.length()) {
            if (j == -1 || text.charAt(i) == pattern.charAt(j)) {
                ++i;
                ++j;
            } else {
                j = prefixTable[j];
            }
        }
        if (j == pattern.length()) {
            return (i - pattern.length());
        }
		return NOT_FOUND;
    }
}
