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
 * Offers the Bubble-Sort algorithm to sort an array consisting of any type in
 * ascending order or by using a specified {@link Comparator}.
 *
 * @author Matthias Fussenegger
 */
public final class BubbleSort implements InPlaceSort {

    @Override
    @SuppressWarnings("unchecked")
    public <T> void sort(T[] values) {
        if (values != null && values.length > 1) {
            boolean isDone = false;
            for (int n = values.length; n > 1 && !isDone; --n) {
                isDone = true;
                for (int j = 0; j < n - 1; ++j) {
                    if (((Comparable<? super T>) values[j]).compareTo(values[j + 1]) > 0) {
                        T temp = values[j];
                        values[j] = values[j + 1];
                        values[j + 1] = temp;
                        isDone = false;
                    }
                }
            }
        }
    }

    @Override
    public <T> void sort(T[] values, Comparator<? super T> c) {
        if (values != null && values.length > 1) {
            boolean isDone = false;
            for (int n = values.length; n > 1 && !isDone; --n) {
                isDone = true;
                for (int j = 0; j < n - 1; ++j) {
                    if (c.compare(values[j], values[j + 1]) > 0) {
                        T temp = values[j];
                        values[j] = values[j + 1];
                        values[j + 1] = temp;
                        isDone = false;
                    }
                }
            }
        }
    }
}
