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
package stringSearchAlgorithms.boyerMoore;

import Interfaces.StringSearchAlgorithm;

/**
 *
 * @author Matthias Fussenegger
 */
public class BoyerMoore implements StringSearchAlgorithm {

    @Override
    public int indexOf(char[] stack, String needle) {
        if (needle.length() == 0 || stack.length == 0) {
            return 0;
        }
        int skipTable[] = makeSkipTable(needle);
        int nextTable[] = makeNextTable(needle);
        int i = needle.length() - 1, j;
        while (i < stack.length) {
            for (j = needle.length() - 1; needle.charAt(j) == stack[i]; --i, --j) {
                if (j == 0) {
                    return i;
                }
            }
            i += Math.max(nextTable[needle.length() - 1 - j], skipTable[stack[i]]);
        }
        return -1; //not a substring
    }

    /**
     * Calculation of the skip table ("bad rule")
     *
     * @param needle The pattern that is required for table creation
     * @return An array representing the skip table
     */
    private int[] makeSkipTable(String needle) {
        final int ALPHABET = 256; //ASCII
        int[] table = new int[ALPHABET];
        for (int i = 0; i < table.length; ++i) {
            table[i] = needle.length();
        }
        for (int i = 0; i < needle.length() - 1; ++i) {
            table[needle.charAt(i)] = needle.length() - 1 - i;
        }
        return table;
    }

    /**
     * Calculation of the next table ("good rule")
     *
     * @param needle The pattern that is required for table creation
     * @return An array representing the next table
     */
    private int[] makeNextTable(String needle) {
        int[] table = new int[needle.length()];
        int lastPrefixPos = needle.length();
        boolean isPrefix = true;
        /*iterate starting from end*/
        for (int i = needle.length() - 1; i >= 0; --i) {
            /*check if needle from j to length is prefix*/
            for (int j = i + 1, k = 0; j < needle.length(); ++j, ++k) {
                if (needle.charAt(j) != needle.charAt(k)) {
                    isPrefix = false;
                    break;
                }
            }
            if (isPrefix) {
                lastPrefixPos = i + 1;
            }
            table[needle.length() - 1 - i] = lastPrefixPos - i + needle.length() - 1;
        }
        /*iterate starting from beginning*/
        for (int i = 0; i < needle.length() - 1; ++i) {
            int suffixLength = suffixLength(needle, i);
            table[suffixLength] = needle.length() - 1 - i + suffixLength;
        }
        return table;
    }

    /**
     * Returns the maximum length of the needle that ends at {@code pos}. To put
     * it more generally: Returns the suffix length of the needle
     *
     * @param needle The pattern that is required to determine suffix length
     * @param pos The current position in the needle
     * @return The length of the suffix
     */
    private int suffixLength(String needle, int pos) {
        int len = 0;
        for (int i = pos, j = needle.length() - 1;
                i >= 0 && needle.charAt(i) == needle.charAt(j); --i, --j) {
            len += 1;
        }
        return len;
    }

}
