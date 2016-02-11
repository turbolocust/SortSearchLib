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
package stringSearchAlgorithms.knuthMorrisPratt;

import Interfaces.StringSearchAlgorithm;

/**
 *
 * @author Matthias Fussenegger
 */
public class KnuthMorrisPratt implements StringSearchAlgorithm {

    @Override
    public int indexOf(char[] stack, String needle) {
        if (needle.length() == 0 || stack.length == 0) {
            return 0;
        }

        int[] prefixTable = makePrefixTable(needle);
        int i = search(stack, needle, prefixTable);

        return i;
    }

    /**
     * Calculation of the prefix table
     *
     * @param needle The pattern that is required for table creation
     * @return An array representing the prefix table
     */
    private int[] makePrefixTable(String needle) {
        int[] prefixTable = new int[needle.length() + 1];
        prefixTable[0] = -1; //first element has to be -1

        for (int i = 0, j = -1; i < needle.length();) {
            while (j >= 0 && needle.charAt(j) != needle.charAt(i)) {
                j = prefixTable[j];
            }
            ++i;
            ++j;
            prefixTable[i] = j;
        }
        return prefixTable;
    }

    /**
     * The actual search of the beginning of the needle in the stack
     *
     * @param stack The stack to be searched for needle
     * @param needle The needle to be found in text
     * @param prefixTable The previously generated prefix table
     * @return The position of the first occurrence in the text
     */
    private int search(char[] stack, String needle, int[] prefixTable) {
        int i = 0; //position in stack
        int j = 0; //position in needle

        while (j < needle.length() && i < stack.length) {
            if (j == -1 || stack[i] == needle.charAt(j)) {
                ++i;
                ++j;
            } else {
                j = prefixTable[j];
            }
        }
        if (j == needle.length()) {
            return (i - needle.length());
        } else {
            return -1; //not a substring
        }
    }
}
