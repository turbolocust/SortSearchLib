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

/**
 *
 * @author Matthias Fussenegger
 */
public interface SingleStringSearch {

    /**
     * Indicates that the specified pattern has not been found
     */
    int NOT_FOUND = -1;

    /**
     * Finds the first occurrence of the pattern in the {@code stack}
     *
     * @param text The text to be searched for pattern
     * @param pattern The pattern to be found in the text
     * @return The position of the first occurrence in the text or a negative
     * {@code Integer} if pattern is no substring of the text
     */
    int indexOf(char[] text, String pattern);
}
