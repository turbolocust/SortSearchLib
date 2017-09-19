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

import static org.junit.Assert.assertArrayEquals;

import java.util.Arrays;
import java.util.Comparator;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Matthias Fussenegger
 */
public class HeapSortTest implements TestableSort {

    public HeapSortTest() {
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
     * Test of sort method, of class HeapSort.
     */
    @Test
    @Override
    public void testSort() {
        System.out.println("sort - HeapSort");
        HeapSort instance = new HeapSort();
        Object[] values = VALUES.toArray();
        long startTime = System.nanoTime();
        instance.sort(values);
        System.out.println(System.nanoTime() - startTime);
        Object[] sortedValues = VALUES.toArray();
        Arrays.sort(sortedValues);
        assertArrayEquals(values, sortedValues);
    }

    /**
     * Test of sort method, of class HeapSort.
     */
    @Test
    @Override
    public void testSort_Comparator() {
        System.out.println("sort using comparator - HeapSort");
        HeapSort instance = new HeapSort();
        Integer[] values = new Integer[VALUES.size()];
        values = VALUES.toArray(values);
        Comparator<Integer> c = TestableSortUtils.createIntegerComparator();
        long startTime = System.nanoTime();
        instance.sort(values, c);
        System.out.println(System.nanoTime() - startTime);
        Integer[] sortedValues = new Integer[VALUES.size()];
        sortedValues = VALUES.toArray(sortedValues);
        Arrays.sort(sortedValues, c);
        assertArrayEquals(values, sortedValues);
    }

}
