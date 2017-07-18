/*
 * The MIT License
 *
 * Copyright 2017 Matthias Fussenegger
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.sort_search_lib.java.sort;

import java.util.Comparator;
import org.sort_search_lib.java.sort.interfaces.InPlaceSort;

/**
 * Offers the Quick-Sort algorithm (single pivot) to sort an array consisting of
 * any type in ascending order or by using a specified {@link Comparator}.
 *
 * @author Matthias Fussenegger
 */
public final class Quicksort implements InPlaceSort {

    @Override
    public <T> void sort(T[] values) {
        if (values != null && values.length > 1) {
            quicksortComparable(0, values.length - 1, values);
        }
    }

    @Override
    public <T> void sort(T[] values, Comparator<? super T> c) {
        if (values != null && values.length > 1) {
            quicksortUsingComparator(0, values.length - 1, c, values);
        }
    }

    /**
     * Main method for recursive call of Quicksort-algorithm using the specified
     * comparator for sorting the elements in the array.
     *
     * @param <T> Generic type parameter.
     * @param left The left boundary of the array.
     * @param right The right boundary of the array.
     * @param values The array to be sorted.
     * @param c The comparator used for sorting the array.
     */
    private <T> void quicksortUsingComparator(int left, int right, Comparator<? super T> c, T[] values) {
        if (left < right) {
            int div = divideUsingComparator(left, right, c, values);
            quicksortUsingComparator(left, div - 1, c, values);
            quicksortUsingComparator(div + 1, right, c, values);
        }
    }

    /**
     * Main method for recursive call of Quicksort-algorithm using the natural
     * ordering of the array elements for sorting.
     *
     * @param <T> Generic type parameter.
     * @param left The left boundary of the array.
     * @param values The array to be sorted.
     * @param right The right boundary of the array.
     */
    private <T> void quicksortComparable(int left, int right, T[] values) {
        if (left < right) {
            int div = divideComparable(left, right, values);
            quicksortComparable(left, div - 1, values);
            quicksortComparable(div + 1, right, values);
        }
    }

    /**
     * The division/partition method used by {@code quicksortComparator}.
     *
     * @param <T> Generic type parameter.
     * @param left The left boundary of the array.
     * @param right The right boundary of the array.
     * @param c The comparator used for sorting the array.
     * @param values The array to be sorted.
     * @return The new position of the pivot element.
     */
    private <T> int divideUsingComparator(int left, int right, Comparator<? super T> c, T[] values) {
        int i = left;
        int j = right - 1; //element to the left of pivot
        T pivot = values[right];

        do {
            /*search element from the left, which is bigger than pivot*/
            while (c.compare(values[i], pivot) <= 0 && i < right) {
                ++i;
            }

            /*search element from the right, which is smaller than pivot*/
            while (c.compare(values[j], pivot) >= 0 && j > left) {
                --j;
            }

            if (i < j) { //swap
                T temp = values[i];
                values[i] = values[j];
                values[j] = temp;
            }
        } while (i < j);

        /*swap pivot with new final position*/
        if (c.compare(values[i], pivot) > 0) {
            T temp = values[i];
            values[i] = values[right];
            values[right] = temp;
        }
        return i; //return position of pivot
    }

    /**
     * The division/partition method used by {@code quicksortComparator}.
     *
     * @param <T> Generic type parameter.
     * @param left The left boundary of the array.
     * @param right The right boundary of the array.
     * @param values The array to be sorted.
     * @return The new position of the pivot element.
     */
    @SuppressWarnings("unchecked")
    private <T> int divideComparable(int left, int right, T[] values) {
        int i = left;
        int j = right - 1; //element to the left of pivot
        T pivot = values[right];

        do {
            /*search element from the left, which is bigger than pivot*/
            while (((Comparable<? super T>) values[i]).compareTo(pivot) <= 0
                    && i < right) {
                ++i;
            }

            /*search element from the right, which is smaller than pivot*/
            while (((Comparable<? super T>) values[j]).compareTo(pivot) >= 0
                    && j > left) {
                --j;
            }

            if (i < j) { //swap
                T temp = values[i];
                values[i] = values[j];
                values[j] = temp;
            }
        } while (i < j);

        /*swap pivot with new final position*/
        if (((Comparable<? super T>) values[i]).compareTo(pivot) > 0) {
            T temp = values[i];
            values[i] = values[right];
            values[right] = temp;
        }
        return i; //return position of pivot
    }
}
