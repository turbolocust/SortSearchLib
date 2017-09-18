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
 * Offers the Heap-Sort algorithm to sort a zero-based array consisting of any
 * type in ascending order or by using a specified {@link Comparator}.
 *
 * @author Matthias Fussenegger
 */
public final class HeapSort implements InPlaceSort {

    /**
     * Sort by respecting a specific range within the specified array.
     *
     * <blockquote>
     * <pre>
     * <b>Example:</b>
     *      Integer values = { 5, 8, 3, 2, 4, 1, 6, 5, 8, 9, 7 };
     *      HeapSort hs = new HeapSort();
     *      hs.sort( values, 2, values.length - 1 );
     *      <b>Result is: [ 5, 8, 1, 2, 3, 4, 5, 6, 7, 8, 9 ]</b>
     * </pre>
     * </blockquote>
     *
     * @param <T> type of the array elements.
     * @param values reference to the array that is to be sorted.
     * @param left the left boundary in the array.
     * @param right the right boundary in the array.
     */
    public <T> void sort(T[] values, int left, int right) {
        int n = (right + 1) - left;
        /*heapify while respecting bounds*/
        for (int i = n / 2; i >= 0; --i) {
            siftDownComparable(values, n, i, left);
        }
        /*sort-down while respecting bounds*/
        while (n - 1 > 0) {
            swap(values, left + n - 1, left);
            siftDownComparable(values, n - 1, 0, left);
            --n;
        }
    }

    /**
     * Sort by using the specified comparator and respecting a specific range
     * within the specified array. See JavaDoc of
     * {@link #sort(Object[], int, int)} for a usage example.
     *
     * @param <T> type of the array elements.
     * @param values reference to the array that is to be sorted.
     * @param left the left boundary in the array.
     * @param right the right boundary in the array.
     * @param c the {@link Comparator} used to compare the elements.
     */
    public <T> void sort(T[] values, int left, int right, Comparator<? super T> c) {
        int n = (right + 1) - left;
        /*heapify while respecting bounds*/
        for (int i = n / 2; i >= 0; --i) {
            siftDownUsingComparator(values, n, i, left, c);
        }
        /*sort-down while respecting bounds*/
        while (n - 1 > 0) {
            swap(values, left + n - 1, left);
            siftDownUsingComparator(values, n - 1, 0, left, c);
            --n;
        }
    }

    @Override
    public <T> void sort(T[] values) {
        int n = values.length;
        /*heapify*/
        for (int i = n / 2; i >= 0; --i) {
            siftDownComparable(values, n, i, 0);
        }
        /*sort-down*/
        while (n - 1 > 0) {
            swap(values, n - 1, 0);
            siftDownComparable(values, n - 1, 0, 0);
            --n;
        }
    }

    /**
     * Restores the heap condition by the natural ordering of its elements.
     *
     * @param <T> type of the array elements.
     * @param values reference to the array that is to be sorted.
     * @param end the upper bound to be considered.
     * @param parent the current index of the parent node.
     * @param low lower bound to be added when accessing indexes.
     */
    @SuppressWarnings("unchecked")
    private <T> void siftDownComparable(T[] values, int end, int parent, int low) {
        int child = parent * 2 + 1;
        while (parent * 2 + 1 < end) {
            if (child + 1 < end) {
                if (((Comparable<? super T>) values[low + child + 1])
                        .compareTo(values[low + child]) > 0) {
                    ++child; // right child is larger than left one
                }
            }
            /*swawp if child is larger than its parent*/
            if (((Comparable<? super T>) values[low + child])
                    .compareTo(values[low + parent]) > 0) {
                swap(values, low + child, low + parent);
                parent = child;
                child = 2 * parent + 1;
            } else {
                break;
            }
        }
    }

    @Override
    public <T> void sort(T[] values, Comparator<? super T> c) {
        int n = values.length;
        /*heapify*/
        for (int i = n / 2; i >= 0; --i) {
            siftDownUsingComparator(values, n, i, 0, c);
        }
        /*sort-down*/
        while (n - 1 > 0) {
            swap(values, n - 1, 0);
            siftDownUsingComparator(values, n - 1, 0, 0, c);
            --n;
        }
    }

    /**
     * Restores the heap condition by using the specified {@link Comparator}.
     *
     * @param <T> type of the array elements.
     * @param values reference to the array that is to be sorted.
     * @param end the upper bound to be considered.
     * @param parent the current index of the parent node.
     * @param low lower bound to be added when accessing indexes.
     * @param c the {@link Comparator} used to compare the elements.
     */
    private <T> void siftDownUsingComparator(T[] values, int end, int parent,
            int low, Comparator<? super T> c) {
        int child = parent * 2 + 1;
        while (parent * 2 + 1 < end) {
            if (child + 1 < end) {
                if (c.compare(values[low + child + 1], values[low + child]) > 0) {
                    ++child; // right child is larger than left one
                }
            }
            /*swawp if child is larger than its parent*/
            if (c.compare(values[low + child], values[low + parent]) > 0) {
                swap(values, low + child, low + parent);
                parent = child;
                child = 2 * parent + 1;
            } else {
                break;
            }
        }
    }

    /**
     * Swaps two elements of the specified array.
     *
     * @param <T> type of the array elements.
     * @param values the array of which to swap elements.
     * @param i the index of the first element that will be swapped.
     * @param j the index of the second element that will be swapped.
     */
    private static <T> void swap(T[] values, int i, int j) {
        T temp = values[j];
        values[j] = values[i];
        values[i] = temp;
    }
}
