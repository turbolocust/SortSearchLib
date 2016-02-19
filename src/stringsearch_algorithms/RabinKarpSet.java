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
 * Offers the Rabin-Karp algorithm for finding multiple {@code String} values in
 * a specified text. This algorithm is also known as Rabin-Karp-Set, as it makes
 * use of a {@code Set} that contains different patterns as {@code CharSequence}
 *
 * @author Matthias Fussenegger
 */
public class RabinKarpSet implements MultiStringSearch {

    @Override
    public List<Integer> indexOf(char[] text, Set<CharSequence> patterns, int patternLength) {
        if (text.length < 1 || text.length < patternLength) {
            return null;
        }

        List<Integer> occurrences = new LinkedList<>();

        Set<Integer> patternHashes = new HashSet<>();
        /*calculate hash for each pattern*/
        patterns.stream().forEach((s) -> {
            patternHashes.add(s.hashCode());
        });

        int hash = hashFromText(text, 0, patternLength);
        /*start searching the text for occurrences of patterns*/
        for (int i = 1; i <= text.length - patternLength; ++i) {
            if (patternHashes.contains(hash)
                    && patterns.contains(String.valueOf(text, i - 1, patternLength))) {
                occurrences.add(i);
            }
            hash = hashFromText(text, i, patternLength);
        }
        return occurrences;
    }

    /**
     * Calculates a hash from a specified {@code offset} to a maximum @code
     * count} in {@code text}. This represents a sub-array of the original array
     * and this calculated hash is used to compare values from the
     * {@code patternHashes} with the current position in {@code text}
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
