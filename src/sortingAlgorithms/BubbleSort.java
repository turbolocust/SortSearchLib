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
public class BubbleSort<T> implements SortingAlgorithm {

    @Override
    @SuppressWarnings("unchecked")
    public void sort(Object[] values) {
        for (int n = values.length; n > 1; --n) {
            for (int j = 0; j < n - 1; ++j) {
                if (((Comparable<? super T>) values[j]).compareTo((T) values[j + 1]) > 0) {
                    T temp = (T) values[j];
                    values[j] = values[j + 1];
                    values[j + 1] = temp;
                }
            }
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public void sort(Object[] values, Comparator c) {
        for (int n = values.length; n > 1; --n) {
            for (int j = 0; j < n - 1; ++j) {
                if (c.compare(values[j], values[j + 1]) > 0) {
                    T temp = (T) values[j];
                    values[j] = values[j + 1];
                    values[j + 1] = temp;
                }
            }
        }
    }
}
