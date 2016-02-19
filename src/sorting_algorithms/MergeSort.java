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
package sorting_algorithms;

import algo_interfaces.OutPlaceSort;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Offers the traditional Merge-Sort algorithm to sort a {@code List} consisting
 * of any type in ascending order or by using a specified {@code Comparator}
 *
 * @author Matthias Fussenegger
 */
public class MergeSort implements OutPlaceSort {

    @Override
    public <T> List<T> sort(List<T> list) {
        if (list == null || list.size() < 2) {
            return list;
        }

        /*sublists consisting of the even and odd-indexed elements*/
        List<T> left = new ArrayList<>();
        List<T> right = new ArrayList<>();

        /*divide list into equal-sized sublists*/
        for (int i = 0; i < list.size(); ++i) {
            if (i % 2 != 0) { //odd
                left.add(list.get(i));
            } else { //even
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
        List<T> result = new ArrayList<>();

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
    @SuppressWarnings("unchecked")
    public <T> List<T> sort(List<T> list, Comparator<? super T> c) {
        if (list.size() <= 1) {
            return list;
        }

        /*sublists consisting of the even and odd-indexed elements*/
        List<T> left = new ArrayList<>();
        List<T> right = new ArrayList<>();

        /*divide list into equal-sized sublists*/
        for (int i = 0; i < list.size(); ++i) {
            if (i % 2 != 0) { //odd
                left.add(list.get(i));
            } else { //even
                right.add(list.get(i));
            }
        }

        /*recursively sort both sublists*/
        left = sort(left, c);
        right = sort(right, c);

        /*at the end merge the sorted sublists*/
        return mergeUsingComparator(left, right, c);
    }

    @SuppressWarnings("unchecked")
    private <T> List<T> mergeUsingComparator(List<T> left, List<T> right, Comparator<? super T> c) {
        List<T> result = new ArrayList<>();

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
