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

import java.util.Comparator;

/**
 *
 * @author Matthias Fussenegger
 * @param <T> Generic type parameter
 */
public interface SortingAlgorithm<T> {

    /**
     * Sorts the given array using the the natural ordering of its elements
     *
     * @param values An array containing any type
     */
    void sort(T[] values);

    /**
     * Sorts the given array using the specified comparator
     *
     * @param values An array containing any type
     * @param c The comparator used for sorting the array
     */
    void sort(T[] values, Comparator<? super T> c);
}
