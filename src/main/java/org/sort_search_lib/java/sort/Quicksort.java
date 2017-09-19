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
 * Offers the Quick-Sort algorithm (single pivot) to sort an array consisting of
 * any type in ascending order or by using a specified {@link Comparator}.
 *
 * @author Matthias Fussenegger
 */
public final class Quicksort implements InPlaceSort {

    @Override
    public <T> void sort(T[] values) {
        if (values != null && values.length > 1) {
            if (!insertionSortTinyArray(values, null)) {
                quicksortComparable(0, values.length - 1, values);
            }
        }
    }

    @Override
    public <T> void sort(T[] values, Comparator<? super T> c) {
        if (values != null && values.length > 1) {
            if (!insertionSortTinyArray(values, c)) {
                quicksortUsingComparator(0, values.length - 1, c, values);
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
     * Main method for recursive call of Quicksort-algorithm using the specified
     * comparator for sorting the elements in the array.
     *
     * @param <T> type of the array elements.
     * @param left the left boundary of the array.
     * @param right the right boundary of the array.
     * @param values the array to be sorted.
     * @param c the comparator used for sorting the array.
     */
    private <T> void quicksortUsingComparator(int left, int right, Comparator<? super T> c, T[] values) {
        if (left < right) {
            if (right - left < SIZE_THRESHOLD) {
                InsertionSort.sort(values, left, right, c);
            } else {
                int div = divideUsingComparator(left, right, c, values);
                quicksortUsingComparator(left, div - 1, c, values);
                quicksortUsingComparator(div + 1, right, c, values);
            }
        }
    }

    /**
     * Main method for recursive call of Quicksort-algorithm using the natural
     * ordering of the array elements for sorting.
     *
     * @param <T> type of the array elements.
     * @param left the left boundary of the array.
     * @param values the array to be sorted.
     * @param right the right boundary of the array.
     */
    private <T> void quicksortComparable(int left, int right, T[] values) {
        if (left < right) {
            if (right - left < SIZE_THRESHOLD) {
                InsertionSort.sort(values, left, right);
            } else {
                int div = divideComparable(left, right, values);
                quicksortComparable(left, div - 1, values);
                quicksortComparable(div + 1, right, values);
            }
        }
    }

    /**
     * The division/partition method used by {@code quicksortComparator}.
     *
     * @param <T> type of the array elements.
     * @param left the left boundary of the array.
     * @param right the right boundary of the array.
     * @param c the comparator used for sorting the array.
     * @param values the array to be sorted.
     * @return the new position of the pivot element.
     */
    <T> int divideUsingComparator(int left, int right, Comparator<? super T> c, T[] values) {
        int median = medianOfThreeUsingComparator(values, left, right, c);
        int i = left, j = right - 1; // j is element to the left of pivot
        swap(values, median, right); // swap median element to rightmost position
        T pivot = values[right]; // pivot is element at rightmost position

        do {
            /*search element from the left, which is bigger than pivot*/
            while (c.compare(values[i], pivot) <= 0 && i < right) {
                ++i;
            }

            /*search element from the right, which is smaller than pivot*/
            while (c.compare(values[j], pivot) >= 0 && j > left) {
                --j;
            }

            if (i < j) { // swap
                swap(values, i, j);
            }
        } while (i < j);

        /*swap pivot with new final position*/
        if (c.compare(values[i], pivot) > 0) {
            T temp = values[i];
            values[i] = values[right];
            values[right] = temp;
        }
        return i; // return position of pivot
    }

    /**
     * The division/partition method used by {@code quicksortComparator}.
     *
     * @param <T> type of the array elements.
     * @param left the left boundary of the array.
     * @param right the right boundary of the array.
     * @param values the array to be sorted.
     * @return the new position of the pivot element.
     */
    @SuppressWarnings("unchecked")
    <T> int divideComparable(int left, int right, T[] values) {
        int median = medianOfThreeComparable(values, left, right);
        int i = left, j = right - 1; // j is element to the left of pivot
        swap(values, median, right); // swap median element to rightmost position
        T pivot = values[right]; // pivot is element at rightmost position

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

            if (i < j) { // swap
                swap(values, i, j);
            }
        } while (i < j);

        /*swap pivot with new final position*/
        if (((Comparable<? super T>) values[i]).compareTo(pivot) > 0) {
            T temp = values[i];
            values[i] = values[right];
            values[right] = temp;
        }
        return i; // return position of pivot
    }

    @SuppressWarnings("unchecked")
    private static <T> int medianOfThreeComparable(T[] values, int left, int right) {

        int mid = (left + right) / 2;

        if (((Comparable<? super T>) values[right]).compareTo(values[left]) < 0) {
            swap(values, right, left);
        }
        if (((Comparable<? super T>) values[right]).compareTo(values[mid]) < 0) {
            swap(values, right, mid);
        }
        if (((Comparable<? super T>) values[mid]).compareTo(values[left]) < 0) {
            swap(values, mid, left);
        }
        return mid;
    }

    private static <T> int medianOfThreeUsingComparator(
            T[] values, int left, int right, Comparator<? super T> c) {

        int mid = (left + right) / 2;

        if (c.compare(values[right], values[left]) < 0) {
            swap(values, right, left);
        }
        if (c.compare(values[right], values[mid]) < 0) {
            swap(values, right, mid);
        }
        if (c.compare(values[mid], values[left]) < 0) {
            swap(values, mid, left);
        }
        return mid;
    }

    private static <T> void swap(T[] values, int i, int j) {
        T temp = values[j];
        values[j] = values[i];
        values[i] = temp;
    }
}
