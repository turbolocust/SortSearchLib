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
 *
 * @author Matthias Fussenegger
 */
public final class CocktailShakerSort implements InPlaceSort {

    @Override
    @SuppressWarnings("unchecked")
    public <T> void sort(T[] values) {
        if (values != null && values.length > 1) {
            boolean isDone = false;
            while (!isDone) { // is sorted if no elements have been swapped
                isDone = true;
                for (int i = 0; i < values.length - 2; ++i) {
                    if (((Comparable<? super T>) values[i]).compareTo(values[i + 1]) > 0) {
                        swap(values, i, i + 1);
                        isDone = false;
                    }
                }
                if (isDone) {
                    break;
                }
                isDone = true;
                for (int i = values.length - 2; i > 0; --i) {
                    if (((Comparable<? super T>) values[i]).compareTo(values[i + 1]) > 0) {
                        swap(values, i, i + 1);
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
            while (!isDone) { // is sorted if no elements have been swapped
                isDone = true;
                for (int i = 0; i < values.length - 2; ++i) {
                    if (c.compare(values[i], values[i + 1]) > 0) {
                        swap(values, i, i + 1);
                        isDone = false;
                    }
                }
                if (isDone) {
                    break;
                }
                isDone = true;
                for (int i = values.length - 2; i > 0; --i) {
                    if (c.compare(values[i], values[i + 1]) > 0) {
                        swap(values, i, i + 1);
                        isDone = false;
                    }
                }
            }
        }
    }

    private <T> void swap(T[] values, int i, int j) {
        T temp = values[j];
        values[j] = values[i];
        values[i] = temp;
    }
}
