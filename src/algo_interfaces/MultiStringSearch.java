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

import java.util.Set;

/**
 *
 * @author Matthias Fussenegger
 */
public interface MultiStringSearch {

    /**
     * Finds the occurrences of the patterns in the {@code stack}. This
     * implementation only works with patterns having a fixed size, so each
     * pattern of the {@code Set} needs to have the same length
     *
     * @param text The text to be searched for the patterns
     * @param patterns The patterns to be found in the text
     * @param patternLength The fixed length of each pattern
     * @return The positions of occurrences of the patterns in the text or
     * {@code null} if no pattern is a substring of the text
     */
    int[] indexOf(char[] text, Set<String> patterns, int patternLength);
}
