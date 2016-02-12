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
package sortingAlgorithms;

import interfaces.SortingAlgorithm;
import java.util.Comparator;

/**
 *
 * @author Matthias Fussenegger
 * @param <T> Generic type parameter
 */
public class InsertionSort<T> implements SortingAlgorithm {

    @Override
    @SuppressWarnings("unchecked")
    public void sort(Object[] values) {
        if (values.length > 1) {
            for (int i = 1; i < values.length; ++i) {
                T temp = (T) values[i];
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
    public void sort(Object[] values, Comparator c) {
        if (c != null) {
            if (values.length > 1) {
                for (int i = 1; i < values.length; ++i) {
                    T temp = (T) values[i];
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
