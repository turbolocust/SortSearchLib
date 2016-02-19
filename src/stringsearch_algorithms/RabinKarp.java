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
 * Offers the Rabin-Karp algorithm for finding the first occurrence of a
 * {@code String} value (pattern) in the specified text
 *
 * @author Matthias Fussenegger
 */
public class RabinKarp implements SingleStringSearch {

    @Override
    public int indexOf(char[] text, String pattern) {
        if (text.length < 1 || pattern.length() < 1) {
            return 0;
        }
        int patternHash = pattern.hashCode();
        int hash = hashFromText(text, 0, pattern.length());

        /*start searching the text for occurrences of pattern*/
        for (int i = 1; i <= text.length - pattern.length(); ++i) {
            if (hash == patternHash) {
                return i;
            }
            hash = hashFromText(text, i, pattern.length());
        }
        return NOT_FOUND;
    }

    /**
     * Calculates a hash from a specified {@code offset} to a maximum @code
     * count} in {@code text}. This represents a sub-array of the original array
     * and this calculated hash is used to compare the {@code patternHash} with
     * the current position in {@code text}
     *
     * @param text The array containing {@code Characters}
     * @param offset The index to begin with
     * @param count The length of the sub-array
     * @return The hash of offset position to count in {@code text}
     */
    private int hashFromText(char[] text, int offset, int count) {
        return String.copyValueOf(text, offset, count).hashCode();
    }
}
