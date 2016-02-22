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
package sort_search_lib.interfaces;

import java.util.Comparator;

/**
 * Implementing classes offer the functionality of sorting an array consisting
 * of any type in ascending order or by using a specified {@code Comparator}.
 * The sort will be processed In-Order, meaning that the algorithm works on the
 * specified array itself, without making a copy of it
 *
 * @author Matthias Fussenegger
 */
public interface InPlaceSort {

    /**
     * Sorts the specified array using the natural ordering of its elements in
     * ascending order
     *
     * @param <T> Generic type parameter
     * @param values An array consisting of elements of any type
     */
    <T> void sort(T[] values);

    /**
     * Sorts the specified array using the a {@code Comparator}
     *
     * @param <T> Generic type parameter
     * @param values An array consisting of elements of any type
     * @param c The {@code Comparator} used to sort the elements of the array
     */
    <T> void sort(T[] values, Comparator<? super T> c);
}
