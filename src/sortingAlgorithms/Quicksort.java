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
public class Quicksort<T> implements SortingAlgorithm {

    /**
     * A reference to the array that will be sorted
     */
    private T[] _values;

    @Override
    @SuppressWarnings("unchecked")
    public void sort(Object[] values) {
        if (values.length > 1) {
            _values = (T[]) values;
            quicksortComparable(0, _values.length - 1);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public void sort(Object[] values, Comparator c) {
        if (values.length > 1) {
            _values = (T[]) values;
            quicksortComparator(0, _values.length - 1, c);
        }
    }

    /**
     * Main method for recursive call of Quicksort-algorithm using the specified
     * comparator for sorting the elements in the array
     *
     * @param left The left boundary of the array
     * @param right The right boundary of the array
     * @param c The comparator used for sorting the array
     */
    private void quicksortComparator(int left, int right, Comparator<? super T> c) {
        if (left < right) {
            int div = divideComparator(left, right, c);
            quicksortComparator(left, div - 1, c);
            quicksortComparator(div + 1, right, c);
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
     * @param left The left boundary of the array
     * @param right The right boundary of the array
     * @param c The comparator used for sorting the array
     * @return The new position of the pivot element
     */
    private int divideComparator(int left, int right, Comparator<? super T> c) {
        int i = left;
        int j = right - 1; //element to the left of pivot
        T pivot = _values[right];

        do {
            /*search element from the left, which is bigger than pivot*/
            while (c.compare(_values[i], pivot) <= 0 && i < right) {
                ++i;
            }

            /*search element from the right, which is smaller than pivot*/
            while (c.compare(_values[j], pivot) >= 0 && j > left) {
                --j;
            }

            if (i < j) { //swap
                T temp = _values[i];
                _values[i] = _values[j];
                _values[j] = temp;
            }
        } while (i < j);

        /*swap pivot with new final position*/
        if (c.compare(_values[i], pivot) > 0) {
            T temp = _values[i];
            _values[i] = _values[right];
            _values[right] = temp;
        }

        return i; //return position of pivot
    }

    /**
     * The division/partition method used by {@code quicksortComparator}
     *
     * @param left The left boundary of the array
     * @param right The right boundary of the array
     * @return The new position of the pivot element
     */
    @SuppressWarnings("unchecked")
    private int divideComparable(int left, int right) {
        int i = left;
        int j = right - 1; //element to the left of pivot
        T pivot = _values[right];

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
                T temp = _values[i];
                _values[i] = _values[j];
                _values[j] = temp;
            }
        } while (i < j);

        /*swap pivot with new final position*/
        if (((Comparable<? super T>) _values[i]).compareTo(pivot) > 0) {
            T temp = _values[i];
            _values[i] = _values[right];
            _values[right] = temp;
        }

        return i; //return position of pivot
    }
}
