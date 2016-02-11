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
package sortingAlgorithms.insertionSort;

import Interfaces.SortingAlgorithm;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author Matthias Fussenegger
 * @param <T> Generic type parameter
 */
public class InsertionSort<T> implements SortingAlgorithm {

    @Override
    @SuppressWarnings("unchecked")
    public void sort(List list) {
        if (list.size() > 1) {
            for (int i = 1; i < list.size(); ++i) {
                T temp = (T) list.get(i);
                int j = i;
                while (j > 0 && ((Comparable<? super T>) list.get(j - 1)).compareTo(temp) > 0) {
                    list.set(j, list.get(j - 1));
                    --j;
                }
                list.set(j, temp);
            }
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public void sort(List list, Comparator c) {
        if (c != null) {
            if (list.size() > 1) {
                for (int i = 1; i < list.size(); ++i) {
                    T temp = (T) list.get(i);
                    int j = i;
                    while (j > 0 && c.compare(list.get(j - 1), temp) > 0) {
                        list.set(j, list.get(j - 1));
                        --j;
                    }
                    list.set(j, temp);
                }
            }
        }
    }
}
