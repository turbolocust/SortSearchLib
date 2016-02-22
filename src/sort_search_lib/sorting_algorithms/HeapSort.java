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
package sort_search_lib.sorting_algorithms;

import java.util.Comparator;
import sort_search_lib.interfaces.InPlaceSort;

/**
 * Offers the Heap-Sort algorithm to sort a zero-based array consisting of any
 * type in ascending order or by using a specified {@link Comparator}
 *
 * @author Matthias Fussenegger
 */
public final class HeapSort implements InPlaceSort {

    @Override
    @SuppressWarnings("unchecked")
    public <T> void sort(T[] values) {
        int n = values.length;
        /*heapify*/
        for (int i = n / 2; i >= 0; --i) {
            siftDownComparable(values, n, i);
        }
        /*sort-down*/
        while (n - 1 > 0) {
            swap(values, n - 1, 0);
            siftDownComparable(values, n - 1, 0);
            --n;
        }
    }

    /**
     * Restores the heap condition by the natural ordering of its elements
     *
     * @param <T> Generic type parameter
     * @param values A reference to the array that is to be sorted
     * @param end The size of {@code values}, which is the heap
     * @param parent The current index of the parent node
     */
    @SuppressWarnings("unchecked")
    private <T> void siftDownComparable(T[] values, int end, int parent) {
        int child = parent * 2 + 1;
        while (parent * 2 + 1 < end) {
            if (child + 1 < end) {
                if (((Comparable<? super T>) values[child + 1]).compareTo(values[child]) > 0) {
                    ++child; //right child is larger than left one
                }
            }
            /*swawp if child is larger than its parent*/
            if (((Comparable<? super T>) values[child]).compareTo(values[parent]) > 0) {
                swap(values, child, parent);
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
            siftDownUsingComparator(values, n, i, c);
        }
        /*sort-down*/
        while (n - 1 > 0) {
            swap(values, n - 1, 0);
            siftDownUsingComparator(values, n - 1, 0, c);
            --n;
        }
    }

    /**
     * Restores the heap condition by using the specified {@link Comparator}
     *
     * @param <T> Generic type parameter
     * @param values A reference to the array that is to be sorted
     * @param end The size of {@code values}, which is the heap
     * @param parent The current index of the parent node
     * @param c The {@link Comparator} used to compare the elements
     */
    private <T> void siftDownUsingComparator(T[] values, int end, int parent, Comparator<? super T> c) {
        int child = parent * 2 + 1;
        while (parent * 2 + 1 < end) {
            if (child + 1 < end) {
                if (c.compare(values[child + 1], values[child]) > 0) {
                    ++child; //right child is larger than left one
                }
            }
            /*swawp if child is larger than its parent*/
            if (c.compare(values[child], values[parent]) > 0) {
                swap(values, child, parent);
                parent = child;
                child = 2 * parent + 1;
            } else {
                break;
            }
        }
    }

    /**
     * Swaps two elements of the specified array
     *
     * @param <T> Generic type parameter
     * @param values The array of which to swap elements
     * @param i The index of the first element that will be swapped
     * @param j The index of the second element that will be swapped
     */
    private <T> void swap(T[] values, int i, int j) {
        T temp = values[j];
        values[j] = values[i];
        values[i] = temp;
    }
}
