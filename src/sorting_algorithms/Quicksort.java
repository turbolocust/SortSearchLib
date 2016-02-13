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
public class Quicksort implements InPlaceSort {

    /**
     * A reference to the array that will be sorted
     */
    private Object[] _values;

    @Override
    public <T> void sort(T[] values) {
        if (values.length > 1) {
            _values = values;
            quicksortComparable(0, _values.length - 1);
        }
    }

    @Override
    public <T> void sort(T[] values, Comparator<? super T> c) {
        if (values.length > 1) {
            _values = values;
            quicksortUsingComparator(0, _values.length - 1, c);
        }
    }

    /**
     * Main method for recursive call of Quicksort-algorithm using the specified
     * comparator for sorting the elements in the array
     *
     * @param <T> Generic type parameter
     * @param left The left boundary of the array
     * @param right The right boundary of the array
     * @param c The comparator used for sorting the array
     */
    private <T> void quicksortUsingComparator(int left, int right, Comparator<? super T> c) {
        if (left < right) {
            int div = divideUsingComparator(left, right, c);
            quicksortUsingComparator(left, div - 1, c);
            quicksortUsingComparator(div + 1, right, c);
        }
    }

    /**
     * Main method for recursive call of Quicksort-algorithm using the natural
     * ordering of the array elements for sorting
     *
     * @param left The left boundary of the array
     * @param right The right boundary of the array
     */
    private void quicksortComparable(int left, int right) {
        if (left < right) {
            int div = divideComparable(left, right);
            quicksortComparable(left, div - 1);
            quicksortComparable(div + 1, right);
        }
    }

    /**
     * The division/partition method used by {@code quicksortComparator}
     *
     * @param <T> Generic type parameter
     * @param left The left boundary of the array
     * @param right The right boundary of the array
     * @param c The comparator used for sorting the array
     * @return The new position of the pivot element
     */
    @SuppressWarnings("unchecked")
    private <T> int divideUsingComparator(int left, int right, Comparator<? super T> c) {
        int i = left;
        int j = right - 1; //element to the left of pivot
        T pivot = (T) _values[right];

        do {
            /*search element from the left, which is bigger than pivot*/
            while (c.compare((T) _values[i], pivot) <= 0 && i < right) {
                ++i;
            }

            /*search element from the right, which is smaller than pivot*/
            while (c.compare((T) _values[j], pivot) >= 0 && j > left) {
                --j;
            }

            if (i < j) { //swap
                T temp = (T) _values[i];
                _values[i] = _values[j];
                _values[j] = temp;
            }
        } while (i < j);

        /*swap pivot with new final position*/
        if (c.compare((T) _values[i], pivot) > 0) {
            T temp = (T) _values[i];
            _values[i] = _values[right];
            _values[right] = temp;
        }

        return i; //return position of pivot
    }

    /**
     * The division/partition method used by {@code quicksortComparator}
     *
     * @param <T> Generic type parameter
     * @param left The left boundary of the array
     * @param right The right boundary of the array
     * @return The new position of the pivot element
     */
    @SuppressWarnings("unchecked")
    private <T> int divideComparable(int left, int right) {
        int i = left;
        int j = right - 1; //element to the left of pivot
        T pivot = (T) _values[right];

        do {
            /*search element from the left, which is bigger than pivot*/
            while (((Comparable<? super T>) _values[i]).compareTo(pivot) <= 0
                    && i < right) {
                ++i;
            }

            /*search element from the right, which is smaller than pivot*/
            while (((Comparable<? super T>) _values[j]).compareTo(pivot) >= 0
                    && j > left) {
                --j;
            }

            if (i < j) { //swap
                T temp = (T) _values[i];
                _values[i] = _values[j];
                _values[j] = temp;
            }
        } while (i < j);

        /*swap pivot with new final position*/
        if (((Comparable<? super T>) _values[i]).compareTo(pivot) > 0) {
            T temp = (T) _values[i];
            _values[i] = _values[right];
            _values[right] = temp;
        }

        return i; //return position of pivot
    }
}
