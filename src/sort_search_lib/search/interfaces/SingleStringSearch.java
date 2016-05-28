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
package sort_search_lib.search.interfaces;

/**
 * Implementing classes offer the functionality of finding the first occurrence
 * of a {@link String} value (pattern) in the specified text
 *
 * @author Matthias Fussenegger
 */
public interface SingleStringSearch {

    /**
     * Indicates that the specified pattern has not been found
     */
    int NOT_FOUND = -1;

    /**
     * Finds the first occurrence of the pattern in the specified {@code text}
     *
     * @param text The text to be searched for pattern
     * @param pattern The pattern to be found in the text
     * @return The zero-based position of the first occurrence in the text or a
     * negative {@link Integer} if the pattern is not a substring of the text
     */
    int indexOf(char[] text, CharSequence pattern);
}
