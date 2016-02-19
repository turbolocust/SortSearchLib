/*
 * Copyright (C) 2016 Matthias Fussenegger
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package stringsearch_algorithms;

import algo_interfaces.SingleStringSearch;

/**
 * Offers the Boyer-Moore algorithm for finding the first occurrence of a
 * {@code String} value (pattern) in the specified text. This implementation
 * makes use of both, the bad-suffix and the good-suffix rule
 *
 * @author Matthias Fussenegger
 */
public class BoyerMoore implements SingleStringSearch {

    @Override
    public int indexOf(char[] text, String pattern) {
        if (text.length < 1 || pattern.length() < 1) {
            return 0;
        }
        int skipTable[] = makeSkipTable(pattern);
        int nextTable[] = makeNextTable(pattern);
        int i = pattern.length() - 1, j;
        while (i < text.length) {
            for (j = pattern.length() - 1; pattern.charAt(j) == text[i]; --i, --j) {
                if (j == 0) {
                    return i + 1;
                }
            }
            i += Math.max(nextTable[pattern.length() - 1 - j], skipTable[text[i]]);
        }
        return NOT_FOUND;
    }

    /**
     * Calculation of the skip table ("bad rule")
     *
     * @param pattern The pattern that is required for table creation
     * @return An array representing the skip table
     */
    private int[] makeSkipTable(String pattern) {
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
     * Calculation of the next table ("good rule")
     *
     * @param pattern The pattern that is required for table creation
     * @return An array representing the next table
     */
    private int[] makeNextTable(String pattern) {
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
     * put it more generally: Returns the suffix length of the pattern
     *
     * @param pattern The pattern that is required to determine suffix length
     * @param pos The current position in the pattern
     * @return The length of the suffix
     */
    private int suffixLength(String pattern, int pos) {
        int len = 0;
        for (int i = pos, j = pattern.length() - 1;
                i >= 0 && pattern.charAt(i) == pattern.charAt(j); --i, --j) {
            len += 1;
        }
        return len;
    }

}
