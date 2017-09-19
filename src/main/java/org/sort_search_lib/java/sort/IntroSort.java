/*
 * The MIT License
 *
 * Copyright 2017 Matthias Fussenegger.
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
 * Offers the Introsort algorithm to sort an array consisting of any type in
 * ascending order or by using a specified {@link Comparator}.
 *
 * @author Matthias Fussenegger
 */
public final class IntroSort implements InPlaceSort {

    /**
     * Heap sort instance used to sort if depth is zero.
     */
    private final HeapSort _heapSort = new HeapSort();

    /**
     * Quicksort instance used for partitioning.
     */
    private final Quicksort _quicksort = new Quicksort();

    @Override
    public <T> void sort(T[] values) {
        if (values != null && values.length > 1) {
            int maxDepth = calculateMaxDepth(values.length);
            introsortComparable(values, 0, values.length - 1, maxDepth);
        }
    }

    @Override
    public <T> void sort(T[] values, Comparator<? super T> c) {
        if (values != null && values.length > 1) {
            int maxDepth = calculateMaxDepth(values.length);
            introsortUsingComparator(values, 0, values.length - 1, maxDepth, c);
        }
    }

    private <T> void introsortComparable(T[] values, int left, int right, int maxDepth) {
        if (left < right) {
            if (maxDepth == 0) {
                _heapSort.sort(values, left, right);
            } else if (right - left < SIZE_THRESHOLD) {
                InsertionSort.sort(values, left, right);
            } else {
                int pivot = _quicksort.divideComparable(left, right, values);
                introsortComparable(values, left, pivot - 1, maxDepth - 1);
                introsortComparable(values, pivot + 1, right, maxDepth - 1);
            }
        }
    }

    private <T> void introsortUsingComparator(T[] values, int left, int right,
            int maxDepth, Comparator<? super T> c) {
        if (left < right) {
            if (maxDepth == 0) {
                _heapSort.sort(values, left, right, c);
            } else if (right - left < SIZE_THRESHOLD) {
                InsertionSort.sort(values, left, right, c);
            } else {
                int pivot = _quicksort.divideUsingComparator(left, right, c, values);
                introsortUsingComparator(values, left, pivot - 1, maxDepth - 1, c);
                introsortUsingComparator(values, pivot + 1, right, maxDepth - 1, c);
            }
        }
    }

    private int calculateMaxDepth(int length) {
        return (int) Math.floor(Math.log(length)) * 2;
    }
}
