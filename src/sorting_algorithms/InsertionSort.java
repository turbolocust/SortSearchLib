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

import java.util.Comparator;
import algo_interfaces.InPlaceSort;

/**
 *
 * @author Matthias Fussenegger
 */
public class InsertionSort implements InPlaceSort {

    @Override
    @SuppressWarnings("unchecked")
    public <T> void sort(T[] values) {
        if (values.length > 1) {
            for (int i = 1; i < values.length; ++i) {
                T temp = values[i];
                int j = i;
                while (j > 0 && ((Comparable<? super T>) values[j - 1]).compareTo(temp) > 0) {
                    values[j] = values[j - 1];
                    --j;
                }
                values[j] = temp;
            }
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> void sort(T[] values, Comparator<? super T> c) {
        if (c != null) {
            if (values.length > 1) {
                for (int i = 1; i < values.length; ++i) {
                    T temp = values[i];
                    int j = i;
                    while (j > 0 && c.compare(values[j - 1], temp) > 0) {
                        values[j] = values[j - 1];
                        --j;
                    }
                    values[j] = temp;
                }
            }
        }
    }
}
