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

import java.util.Comparator;
import java.util.List;

/**
 *
 * @author Matthias Fussenegger
 * @param <T> Generic type parameter
 */
public interface SortingAlgorithm<T> {

    /**
     * Sorts the given list using the the natural ordering of the list elements
     *
     * @param list A list of any type
     */
    void sort(List<T> list);

    /**
     * Sorts the given list using the specified comparator
     *
     * @param list A list of any type
     * @param c The comparator used for sorting the list elements
     */
    void sort(List<T> list, Comparator<? super T> c);
}
