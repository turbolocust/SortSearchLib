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
import org.sort_search_lib.java.sort.api.InPlaceSort;

/**
 * Offers the Quick-Sort algorithm (dual pivot) to sort an array consisting of
 * any type in ascending order or by using a specified Comparator.
 *
 * @author Matthias Fussenegger
 */
public class DualPivotQuicksort implements InPlaceSort {

    @Override
    public <T> void sort(T[] values) {
        if (values != null && values.length > 1) {
            if (!insertionSortTinyArray(values, null)) {
                dualPivotQuicksortComparable(0, values.length - 1, values);
            }
        }
    }

    @Override
    public <T> void sort(T[] values, Comparator<? super T> c) {
        if (values != null && values.length > 1) {
            if (!insertionSortTinyArray(values, c)) {
                dualPivotQuicksortUsingComparator(0, values.length - 1, c, values);
            }
        }
    }

    /**
     * Performs an insertion sort for tiny arrays ({@code < 27}).
     *
     * @param <T> type of the array elements.
     * @param values the array to be sorted.
     * @param c the comparator used for sorting the array.
     * @return true if insertion sort was performed, false otherwise.
     */
    private <T> boolean insertionSortTinyArray(T[] values, Comparator<? super T> c) {
        if (values.length < 27) {
            if (c != null) {
                InsertionSort.sort(values, 0, values.length - 1, c);
            } else {
                InsertionSort.sort(values, 0, values.length - 1);
            }
            return true;
        }
        return false;
    }

    /**
     * DualPivotQuicksort-algorithm using the specified comparator for sorting
     * the elements in the array.
     *
     * @param <T> type of the array elements.
     * @param left the left boundary of the array.
     * @param right the right boundary of the array.
     * @param values the array to be sorted.
     * @param c the comparator used for sorting the array.
     */
    private <T> void dualPivotQuicksortUsingComparator(int left, int right,
            Comparator<? super T> c, T[] values) {
        if (left < right) {
            if (right - left < SIZE_THRESHOLD) {
                InsertionSort.sort(values, left, right, c);
                return;
            }
            // set and choose outermost elements as pivots
            if (c.compare(values[left], values[right]) > 0) {
                swap(values, left, right);
            }
            T pivot1 = values[left];
            T pivot2 = values[right];

            // partition
            int less = left + 1;
            int great = right - 1;
            for (int k = less; k <= great; ++k) {
                if (c.compare(values[k], pivot1) < 0) {
                    swap(values, k, less);
                    ++less;
                } else if (c.compare(values[k], pivot2) >= 0) {
                    while (c.compare(values[great], pivot2) > 0) {
                        if (k >= great) {
                            break;
                        }
                        --great;
                    }
                    swap(values, k, great);
                    --great;
                    if (c.compare(values[k], pivot1) < 0) {
                        swap(values, k, less);
                        ++less;
                    }
                }
            }
            --less;
            ++great;

            // position pivots
            swap(values, left, less);
            swap(values, right, great);

            // sort partitions
            dualPivotQuicksortUsingComparator(left, less - 1, c, values);
            dualPivotQuicksortUsingComparator(less + 1, great - 1, c, values);
            dualPivotQuicksortUsingComparator(great + 1, right, c, values);
        }
    }

    /**
     * DualPivotQuicksort-algorithm using the natural ordering of the array
     * elements for sorting.
     *
     * @param <T> type of the array elements.
     * @param left the left boundary of the array.
     * @param values the array to be sorted.
     * @param right the right boundary of the array.
     */
    @SuppressWarnings("unchecked")
    private <T> void dualPivotQuicksortComparable(int left, int right, T[] values) {
        if (left < right) {
            if (right - left < SIZE_THRESHOLD) {
                InsertionSort.sort(values, left, right);
                return;
            }
            // set and choose outermost elements as pivots
            if (((Comparable<? super T>) values[left]).compareTo(values[right]) > 0) {
                swap(values, left, right);
            }
            T pivot1 = values[left];
            T pivot2 = values[right];

            // partition
            int less = left + 1;
            int great = right - 1;
            for (int k = less; k <= great; ++k) {
                if (((Comparable<? super T>) values[k]).compareTo(pivot1) < 0) {
                    swap(values, k, less);
                    ++less;
                } else if (((Comparable<? super T>) values[k]).compareTo(pivot2) >= 0) {
                    while (((Comparable<? super T>) values[great]).compareTo(pivot2) > 0) {
                        if (k >= great) {
                            break;
                        }
                        --great;
                    }
                    swap(values, k, great);
                    --great;
                    if (((Comparable<? super T>) values[k]).compareTo(pivot1) < 0) {
                        swap(values, k, less);
                        ++less;
                    }
                }
            }
            --less;
            ++great;

            // position pivots
            swap(values, left, less);
            swap(values, right, great);

            // sort partitions
            dualPivotQuicksortComparable(left, less - 1, values);
            dualPivotQuicksortComparable(less + 1, great - 1, values);
            dualPivotQuicksortComparable(great + 1, right, values);
        }
    }

    /**
     * Swaps two elements of the specified array
     *
     * @param <T> type of the array elements.
     * @param values the array of which to swap elements
     * @param i the index of the first element that will be swapped
     * @param j the index of the second element that will be swapped
     */
    private <T> void swap(T[] values, int i, int j) {
        T temp = values[j];
        values[j] = values[i];
        values[i] = temp;
    }
}
