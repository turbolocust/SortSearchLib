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
package Interfaces;

/**
 *
 * @author Matthias Fussenegger
 */
public interface StringSearchAlgorithm {

    /**
     * Finds the first occurrence of the pattern in the {@code String}
     *
     * @param stack The text to be searched for pattern
     * @param pattern The pattern to be found in text
     * @return The position of the first occurrence in the text
     */
    int indexOf(char[] stack, String pattern);
}
