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
import java.util.List;

/**
 *
 * @author Matthias Fussenegger
 */
public interface OutPlaceSort {

    /**
     * Sorts a {@code List} using the natural ordering of its elements
     *
     * @param <T> Generic type parameter
     * @param values A {@code List} consisting of elements of any type
     * @return The sorted {@code List}
     */
    <T> List<T> sort(List<T> values);

    /**
     * Sorts a {@code List} using the specified {@code Comparator}
     *
     * @param <T> Generic type parameter
     * @param values A {@code List} consisting of elements of any type
     * @param c The {@code Comparator} used for sorting the list elements
     * @return The sorted {@code List}
     */
    <T> List<T> sort(List<T> values, Comparator<? super T> c);
}
