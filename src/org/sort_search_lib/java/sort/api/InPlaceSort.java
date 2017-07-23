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
package org.sort_search_lib.java.sort.api;

import java.util.Comparator;

/**
 * Implementing classes offer the functionality of sorting an array consisting
 * of any type in ascending order or by using a specified {@code Comparator}.
 * The sort will be processed In-Order, meaning that the algorithm works on the
 * specified array itself, without making a copy of it.
 *
 * @author Matthias Fussenegger
 */
public interface InPlaceSort {

    /**
     * Sorts the specified array using the natural ordering of its elements in
     * ascending order.
     *
     * @param <T> type of the array elements.
     * @param values an array consisting of elements of any type.
     */
    <T> void sort(T[] values);

    /**
     * Sorts the specified array using the a {@code Comparator}.
     *
     * @param <T> type of the array elements.
     * @param values an array consisting of elements of any type.
     * @param c the {@code Comparator} used to sort the elements of the array.
     */
    <T> void sort(T[] values, Comparator<? super T> c);

}
