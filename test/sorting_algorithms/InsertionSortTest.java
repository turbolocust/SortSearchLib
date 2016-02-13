/*
 * Copyright (C) 2016 Matthias
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

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertArrayEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Matthias Fussenegger
 */
public class InsertionSortTest {

    /**
     * The list to be tested
     */
    private final List _list;

    @SuppressWarnings("unchecked")
    public InsertionSortTest() {
        _list = new LinkedList<>();
        _list.add(0);
        _list.add(1);
        _list.add(2);
        _list.add(3);
        _list.add(4);
        _list.add(5);
        _list.add(6);
        _list.add(7);
        _list.add(8);
        _list.add(9);
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of sort method, of class InsertionSort.
     */
    @Test
    public void testSort_List() {
        System.out.println("sort - InsertionSort");
        InsertionSort instance = new InsertionSort();
        Collections.shuffle(_list);
        Object[] values = _list.toArray();
        instance.sort(values);
        Object[] sortedValues = _list.toArray();
        Arrays.sort(sortedValues);
        assertArrayEquals(values, sortedValues);
    }

    /**
     * Test of sort method, of class InsertionSort.
     */
    @Test
    @SuppressWarnings("unchecked")
    public void testSort_List_Comparator() {
        System.out.println("sort using comparator - InsertionSort");
        InsertionSort instance = new InsertionSort();
        Collections.shuffle(_list);
        Object[] values = _list.toArray();
        Comparator c = (Comparator) (Object o1, Object o2) -> {
            Integer v1 = (Integer) o1;
            Integer v2 = (Integer) o2;
            return v2.compareTo(v1);
        };
        instance.sort(values, c);
        Object[] sortedValues = _list.toArray();
        Arrays.sort(sortedValues, c);
        assertArrayEquals(values, sortedValues);
    }
}
