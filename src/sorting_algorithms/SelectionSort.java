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
package sorting_algorithms;

import algo_interfaces.SortingAlgorithm;
import java.util.Comparator;

/**
 *
 * @author Matthias Fussenegger
 * @param <T> Generic type parameter
 */
public class SelectionSort<T> implements SortingAlgorithm {

    @Override
    @SuppressWarnings("unchecked")
    public void sort(Object[] values) {
        for (int i = values.length - 1; i > 0; --i) {
            int min = 0;
            for (int j = 0; j < i + 1; ++j) {
                if (((Comparable<? super T>) values[j]).compareTo((T) values[min]) > 0) {
                    min = j;
                }
            }
            T temp = (T) values[min];
            values[min] = values[i];
            values[i] = temp;
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public void sort(Object[] values, Comparator c) {
        for (int i = values.length - 1; i > 0; --i) {
            int min = 0;
            for (int j = 0; j < i + 1; ++j) {
                if (c.compare(values[j], values[min]) > 0) {
                    min = j;
                }
            }
            T temp = (T) values[min];
            values[min] = values[i];
            values[i] = temp;
        }
    }
}
