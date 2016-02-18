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
 *
 * @author Matthias Fussenegger
 */
public class KnuthMorrisPratt implements SingleStringSearch {

    @Override
    public int indexOf(char[] text, String pattern) {
        if (text.length < 1 || pattern.length() < 1) {
            return 0;
        }

        int[] prefixTable = makePrefixTable(pattern);
        int i = search(text, pattern, prefixTable);

        return i + 1;
    }

    /**
     * Calculation of the prefix table
     *
     * @param pattern The pattern that is required for table creation
     * @return An array representing the prefix table
     */
    private int[] makePrefixTable(String pattern) {
        int[] prefixTable = new int[pattern.length() + 1];
        prefixTable[0] = -1; //first element has to be -1

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
     * The actual search of the beginning of the pattern in the text
     *
     * @param text The text to be searched for pattern
     * @param pattern The pattern to be found in text
     * @param prefixTable The previously generated prefix table
     * @return The position of the first occurrence in the text
     */
    private int search(char[] text, String pattern, int[] prefixTable) {
        int i = 0; //position in text
        int j = 0; //position in pattern

        while (j < pattern.length() && i < text.length) {
            if (j == -1 || text[i] == pattern.charAt(j)) {
                ++i;
                ++j;
            } else {
                j = prefixTable[j];
            }
        }
        if (j == pattern.length()) {
            return (i - pattern.length());
        } else {
            return NOT_FOUND;
        }
    }
}
