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

import algo_interfaces.InPlaceSort;
import java.util.Comparator;

/**
 *
 * @author Matthias Fussenegger
 */
public class SelectionSort implements InPlaceSort {

    @Override
    @SuppressWarnings("unchecked")
    public <T> void sort(T[] values) {
        if (values != null && values.length > 1) {
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
    }

    @Override
    public <T> void sort(T[] values, Comparator<? super T> c) {
        if (values != null && values.length > 1) {
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
}
