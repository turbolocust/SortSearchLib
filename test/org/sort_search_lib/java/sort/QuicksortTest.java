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

import java.util.Arrays;
import java.util.Comparator;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Matthias Fussenegger
 */
public class QuicksortTest implements TestableSort {

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
     * Test of sort method, of class Quicksort.
     */
    @Test
    @Override
    public void testSort() {
        System.out.println("sort - QUICKSORT");
        Quicksort instance = new Quicksort();
        Object[] values = VALUES.toArray();
        long startTime = System.nanoTime();
        instance.sort(values);
        System.out.println(System.nanoTime() - startTime);
        Object[] sortedValues = VALUES.toArray();
        Arrays.sort(sortedValues);
        assertArrayEquals(values, sortedValues);
    }

    /**
     * Test of sort method, of class Quicksort.
     */
    @Test
    @Override
    public void testSort_Comparator() {
        System.out.println("sort using comparator - Quicksort");
        Quicksort instance = new Quicksort();
        Object[] values = VALUES.toArray();
        Comparator<Object> c = (Comparator<Object>) (Object o1, Object o2) -> {
            Integer v1 = (Integer) o1;
            Integer v2 = (Integer) o2;
            return v2.compareTo(v1);
        };
        long startTime = System.nanoTime();
        instance.sort(values, c);
        System.out.println(System.nanoTime() - startTime);
        Object[] sortedValues = VALUES.toArray();
        Arrays.sort(sortedValues, c);
        assertArrayEquals(values, sortedValues);
    }

    /**
     * Test of sort method, of class Quicksort with tiny array.
     */
    @Test
    public void testSort_TinyArray() {
        System.out.println("sort - QUICKSORT - Tiny Array");
        Quicksort instance = new Quicksort();
        Object[] initialValues = TestableSort.generateRandomIntegerList(26).toArray();
        Object[] values = Arrays.copyOf(initialValues, initialValues.length);
        long startTime = System.nanoTime();
        instance.sort(values);
        System.out.println(System.nanoTime() - startTime);
        Object[] sortedValues = initialValues;
        Arrays.sort(sortedValues);
        assertArrayEquals(values, sortedValues);
    }

    /**
     * Test of sort method, of class Quicksort with tiny array.
     */
    @Test
    public void testSort_Comparator_TinyArray() {
        System.out.println("sort using comparator - Quicksort - Tiny Array");
        Quicksort instance = new Quicksort();
        Object[] initialValues = TestableSort.generateRandomIntegerList(26).toArray();
        Object[] values = Arrays.copyOf(initialValues, initialValues.length);
        Comparator<Object> c = (Comparator<Object>) (Object o1, Object o2) -> {
            Integer v1 = (Integer) o1;
            Integer v2 = (Integer) o2;
            return v2.compareTo(v1);
        };
        long startTime = System.nanoTime();
        instance.sort(values, c);
        System.out.println(System.nanoTime() - startTime);
        Object[] sortedValues = initialValues;
        Arrays.sort(sortedValues, c);
        assertArrayEquals(values, sortedValues);
    }

}
