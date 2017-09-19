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
 * Offers the Insertion-Sort algorithm to sort an array consisting of any type
 * in ascending order or by using a specified {@link Comparator}.
 *
 * @author Matthias Fussenegger
 */
public final class InsertionSort implements InPlaceSort {

    /**
     * Internal API that respects a specific range within the specified array.
     * Both boundaries are considered inclusively.
     *
     * @param <T> type of the array elements.
     * @param values reference to the array that is to be sorted.
     * @param left the left boundary in the array.
     * @param right the right boundary in the array.
     */
    @SuppressWarnings("unchecked")
    static <T> void sort(T[] values, int left, int right) {
        for (int i = left + 1; i <= right; ++i) {
            T temp = values[i];
            int j = i;
            while (j > left && ((Comparable<? super T>) values[j - 1]).compareTo(temp) > 0) {
                values[j] = values[j - 1];
                --j;
            }
            values[j] = temp;
        }
    }

    /**
     * Internal API that respects a specific range within the specified array.
     * Both boundaries are considered inclusively.
     *
     * @param <T> type of the array elements.
     * @param values reference to the array that is to be sorted.
     * @param left the left boundary in the array.
     * @param right the right boundary in the array.
     * @param c the {@link Comparator} used to compare the elements.
     */
    static <T> void sort(T[] values, int left, int right, Comparator<? super T> c) {
        for (int i = left + 1; i <= right; ++i) {
            T temp = values[i];
            int j = i;
            while (j > left && c.compare(values[j - 1], temp) > 0) {
                values[j] = values[j - 1];
                --j;
            }
            values[j] = temp;
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> void sort(T[] values) {
        if (values != null && values.length > 1) {
            for (int i = 1; i < values.length; ++i) {
                T temp = values[i];
                int j = i;
                while (j > 0 && ((Comparable<? super T>) values[j - 1]).compareTo(temp) > 0) {
                    values[j] = values[j - 1];
                    --j;
                }
                values[j] = temp;
            }
        }
    }

    @Override
    public <T> void sort(T[] values, Comparator<? super T> c) {
        if (values != null && values.length > 1) {
            for (int i = 1; i < values.length; ++i) {
                T temp = values[i];
                int j = i;
                while (j > 0 && c.compare(values[j - 1], temp) > 0) {
                    values[j] = values[j - 1];
                    --j;
                }
                values[j] = temp;
            }
        }
    }
}
