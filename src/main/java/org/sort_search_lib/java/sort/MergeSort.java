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

import org.sort_search_lib.java.sort.api.OutPlaceSort;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * Offers the traditional Merge-Sort algorithm to sort a {@link List} consisting
 * of any type in ascending order or by using a specified {@link Comparator}.
 *
 * @author Matthias Fussenegger
 */
public final class MergeSort implements OutPlaceSort {

    @Override
    public <T> List<T> sort(List<T> list) {
        if (list == null || list.size() < 2) {
            return list;
        }

        /*sublists consisting of the even and odd-indexed elements*/
        List<T> left = new LinkedList<T>();
        List<T> right = new LinkedList<T>();

        /*divide list into equal-sized sublists*/
        for (int i = 0; i < list.size(); ++i) {
            if (i % 2 != 0) { // odd
                left.add(list.get(i));
            } else { // even
                right.add(list.get(i));
            }
        }

        /*recursively sort both sublists*/
        left = sort(left);
        right = sort(right);

        /*at the end merge the sorted sublists*/
        return mergeComparable(left, right);
    }

    @SuppressWarnings("unchecked")
    private <T> List<T> mergeComparable(List<T> left, List<T> right) {
        
        List<T> result = new ArrayList<T>(left.size() + right.size());

        while (!left.isEmpty() && !right.isEmpty()) {
            if (((Comparable<? super T>) left.get(0)).compareTo(right.get(0)) > 0) {
                result.add(right.remove(0));
            } else {
                result.add(left.remove(0));
            }
        }

        while (!left.isEmpty()) {
            result.add(left.remove(0));
        }

        while (!right.isEmpty()) {
            result.add(right.remove(0));
        }
        return result;
    }

    @Override
    public <T> List<T> sort(List<T> list, Comparator<? super T> c) {
        if (list.size() <= 1) {
            return list;
        }

        /*sublists consisting of the even and odd-indexed elements*/
        List<T> left = new LinkedList<T>();
        List<T> right = new LinkedList<T>();

        /*divide list into equal-sized sublists*/
        for (int i = 0; i < list.size(); ++i) {
            if (i % 2 != 0) { // odd
                left.add(list.get(i));
            } else { // even
                right.add(list.get(i));
            }
        }

        /*recursively sort both sublists*/
        left = sort(left, c);
        right = sort(right, c);

        /*at the end merge the sorted sublists*/
        return mergeUsingComparator(left, right, c);
    }

    private <T> List<T> mergeUsingComparator(List<T> left, List<T> right, Comparator<? super T> c) {
        
        List<T> result = new ArrayList<T>(left.size() + right.size());

        while (!left.isEmpty() && !right.isEmpty()) {
            if (c.compare(left.get(0), right.get(0)) > 0) {
                result.add(right.remove(0));
            } else {
                result.add(left.remove(0));
            }
        }

        while (!left.isEmpty()) {
            result.add(left.remove(0));
        }

        while (!right.isEmpty()) {
            result.add(right.remove(0));
        }
        return result;
    }
}
