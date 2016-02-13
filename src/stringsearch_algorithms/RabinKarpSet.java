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

import algo_interfaces.MultiStringSearch;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Matthias Fussenegger
 */
public class RabinKarpSet implements MultiStringSearch {

    @Override
    public int[] indexOf(char[] stack, Set<String> patterns, int patternLength) {
        if (stack.length < 1 || stack.length < patternLength) {
            return null;
        }

        LinkedList<Integer> occurrences = new LinkedList<>();

        Set<Integer> patternHashes = new HashSet<>();
        /*calculate hash for each pattern*/
        patterns.stream().forEach((s) -> {
            patternHashes.add(s.hashCode());
        });

        int hash = hashFromStack(stack, 0, patternLength);
        for (int i = 1; i <= stack.length - patternLength; ++i) {
            if (patternHashes.contains(hash)
                    && patterns.contains(String.valueOf(stack, i - 1, patternLength))) {
                occurrences.add(i - 1);
            }
            hash = hashFromStack(stack, i, patternLength);
        }

        return convertToArray(occurrences);
    }

    /**
     * Calculates a hash from a specified {@code offset} to a maximum @code
     * count} in {@code stack}. This represents a sub-array of the original
     * array and this calculated hash is used to compare values from the
     * {@code patternHashes} with the current position in {@code stack}
     *
     * @param stack The array containing {@code Characters}
     * @param offset The index to begin with
     * @param count The length of the sub-array
     * @return The hash of offset position to count in {@code stack}
     */
    private int hashFromStack(char[] stack, int offset, int count) {
        String s = String.copyValueOf(stack, offset, count);
        return s.hashCode();
    }

    /**
     * Converts the {@code List} with occurrences to an array with primitive
     * data types. The array has the same length as the {@code List}
     *
     * @param list The {@code List} to be converted
     * @return An array containing the values of the {@code List}
     */
    private int[] convertToArray(List<Integer> list) {
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
