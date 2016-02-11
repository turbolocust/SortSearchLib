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
package sortingAlgorithms.quicksort;

import Interfaces.SortingAlgorithm;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author Matthias Fussenegger
 * @param <T> Generic type parameter
 */
public class Quicksort<T> implements SortingAlgorithm {

    /**
     * A reference to the list that will be sorted
     */
    private List<T> _list;

    @Override
    @SuppressWarnings("unchecked")
    public void sort(List list) {
        if (list.size() > 1) {
            _list = list;
            quicksortComparable(0, _list.size() - 1);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public void sort(List list, Comparator c) {
        if (list.size() > 1) {
            _list = list;
            quicksortComparator(0, _list.size() - 1, c);
        }
    }

    /**
     * Main method for recursive call of Quicksort-algorithm using the specified
     * comparator for sorting the elements of the list
     *
     * @param left The left boundary of the list
     * @param right The right boundary of the list
     * @param c The comparator used for sorting the list
     */
    private void quicksortComparator(int left, int right, Comparator<? super T> c) {
        if (left < right) {
            int div = divideComparator(left, right, c);
            quicksortComparator(left, div - 1, c);
            quicksortComparator(div + 1, right, c);
        }
    }

    /**
     * Main method for recursive call of Quicksort-algorithm using the natural
     * ordering of the list elements for sorting
     *
     * @param left The left boundary of the list
     * @param right The right boundary of the list
     */
    private void quicksortComparable(int left, int right) {
        if (left < right) {
            int div = divideComparable(left, right);
            quicksortComparable(left, div - 1);
            quicksortComparable(div + 1, right);
        }
    }

    /**
     * The division/partition method used by {@code quicksortComparator}
     *
     * @param left The left boundary of the list
     * @param right The right boundary of the list
     * @param c The comparator used for sorting the list
     * @return The new position of the pivot element
     */
    private int divideComparator(int left, int right, Comparator<? super T> c) {
        int i = left;
        int j = right - 1; //element to the left of pivot
        T pivot = _list.get(right);

        do {
            /*search element from the left, which is bigger than pivot*/
            while (c.compare(_list.get(i), pivot) <= 0 && i < right) {
                ++i;
            }

            /*search element from the right, which is smaller than pivot*/
            while (c.compare(_list.get(j), pivot) >= 0 && j > left) {
                --j;
            }

            if (i < j) { //swap
                T temp = _list.get(i);
                _list.set(i, _list.get(j));
                _list.set(j, temp);
            }
        } while (i < j);

        /*swap pivot with new final position*/
        if (c.compare(_list.get(i), pivot) > 0) {
            T temp = _list.get(i);
            _list.set(i, _list.get(right));
            _list.set(right, temp);
        }

        return i; //return position of pivot
    }

    /**
     * The division/partition method used by {@code quicksortComparable}
     *
     * @param left The left boundary of the list
     * @param right The right boundary of the list
     * @return The new position of the pivot element
     */
    @SuppressWarnings("unchecked")
    private int divideComparable(int left, int right) {
        int i = left;
        int j = right - 1; //element to the left of pivot
        T pivot = _list.get(right);

        do {
            /*search element from the left, which is bigger than pivot*/
            while (((Comparable<? super T>) _list.get(i)).compareTo(pivot) <= 0
                    && i < right) {
                ++i;
            }

            /*search element from the right, which is smaller than pivot*/
            while (((Comparable<? super T>) _list.get(j)).compareTo(pivot) >= 0
                    && j > left) {
                --j;
            }

            if (i < j) { //swap
                T temp = _list.get(i);
                _list.set(i, _list.get(j));
                _list.set(j, temp);
            }
        } while (i < j);

        /*swap pivot with new final position*/
        if (((Comparable<? super T>) _list.get(i)).compareTo(pivot) > 0) {
            T temp = _list.get(i);
            _list.set(i, _list.get(right));
            _list.set(right, temp);
        }

        return i; //return position of pivot
    }
}
