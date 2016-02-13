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
 */
public interface InPlaceSort {

    /**
     * Sorts an array using the natural ordering of its elements
     *
     * @param <T> Generic type parameter
     * @param values An array consisting of elements of any type
     */
    <T> void sort(T[] values);

    /**
     * Sorts an array using the specified {@code Comparator}
     *
     * @param <T> Generic type parameter
     * @param values An array consisting of elements of any type
     * @param c The {@code Comparator} used for sorting the array
     */
    <T> void sort(T[] values, Comparator<? super T> c);
}
