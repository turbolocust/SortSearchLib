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
package algo_interfaces;

import java.util.List;
import java.util.Set;

/**
 * Implementing classes offer the functionality of finding multiple
 * {@link String} values in a specified text
 *
 * @author Matthias Fussenegger
 */
public interface MultiStringSearch {

    /**
     * Finds the occurrences of the patterns in the {@code stack}. This
     * implementation only works with patterns having a fixed size, so each
     * pattern of the {@link Set} needs to have the same length, which is
     * specified when calling this method. If the length does not match the
     * length of at least one pattern in the {@link Set}, the results of this
     * algorithm cannot be trusted
     *
     * @param text The text to be searched for the patterns
     * @param patterns A {@link Set} of patterns to be found in the text
     * @param patternLength The fixed length of each pattern
     * @return The non-zero-indexed positions of pattern occurrences in the text
     * or {@code null} if no pattern is a substring of the text, meaning no
     * match has been found
     */
    List<Integer> indexOf(char[] text, Set<CharSequence> patterns, int patternLength);

    /**
     * Converts a {@link List} with {@link Integer} occurrences to an array with
     * primitive data types. The array has the same length as the {@link List}
     *
     * @param list The {@link List} to be converted
     * @return An array consisting of values of the {@link List}
     */
    default int[] convertToArray(List<Integer> list) {
        if (list.size() > 0) {
            int[] occurrences = new int[list.size()];
            int i = 0;
            /*copy each list entry to array*/
            for (Integer pos : list) {
                occurrences[i] = pos;
                ++i;
            }
            return occurrences;
        } else {
            return null;
        }
    }
}
