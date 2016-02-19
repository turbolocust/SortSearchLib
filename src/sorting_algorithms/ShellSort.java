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
 * Offers the Shell-Sort algorithm to sort an array consisting of any type in
 * ascending order or by using a specified {@link Comparator}
 *
 * @author Matthias Fussenegger
 */
public final class ShellSort implements InPlaceSort {

    @Override
    @SuppressWarnings("unchecked")
    public <T> void sort(T[] values) {
        if (values != null && values.length > 1) {
            /*calculate gap using Knuth-sequence*/
            int gap = 1;
            while (gap <= Math.ceil(values.length / 3)) {
                gap *= 3 + 1;
            }

            while (gap > 0) {
                /*do insertion sort*/
                for (int i = gap; i < values.length; ++i) {
                    T temp = values[i];
                    int insert = i;
                    while (insert >= gap && ((Comparable<? super T>) values[insert - gap]).compareTo(temp) > 0) {
                        values[insert] = values[insert - gap];
                        insert -= gap;
                    }
                    values[insert] = temp;
                }
                gap /= 3;
            }
        }
    }

    @Override
    public <T> void sort(T[] values, Comparator<? super T> c) {
        if (values != null && values.length > 1) {
            /*calculate gap using Knuth-sequence*/
            int gap = 1;
            while (gap <= Math.ceil(values.length / 3)) {
                gap *= 3 + 1;
            }

            while (gap > 0) {
                /*do insertion sort*/
                for (int i = gap; i < values.length; ++i) {
                    T temp = values[i];
                    int insert = i;
                    while (insert >= gap && c.compare(values[insert - gap], temp) > 0) {
                        values[insert] = values[insert - gap];
                        insert -= gap;
                    }
                    values[insert] = temp;
                }
                gap /= 3;
            }
        }
    }
}
