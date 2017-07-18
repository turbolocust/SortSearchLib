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
package org.sort_search_lib.java.search;

import java.util.HashSet;
import java.util.Set;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Matthias Fussenegger
 */
public class RabinKarpSetTest implements TestableSearch {

    public RabinKarpSetTest() {
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
     * Test of indexOf method, of class RabinKarpSet.
     */
    @Test
    @Override
    public void testIndexOf() {
        System.out.println("indexOf - RabinKarpSet");
        char[] stack = TEXT.toCharArray();
        Set<CharSequence> patterns = new HashSet<>();
        patterns.add("sit");
        patterns.add("con");
        int patternLength = 3;
        int[] expectedResult = new int[6];
        /*used a small C-program to validate these results*/
        expectedResult[0] = 18;
        expectedResult[1] = 28;
        expectedResult[2] = 286;
        expectedResult[3] = 314;
        expectedResult[4] = 324;
        expectedResult[5] = 582;
        RabinKarpSet instance = new RabinKarpSet();
        long startTime = System.nanoTime();
        int[] result = instance.convertToArray(instance.indexOf(stack, patterns, patternLength));
        System.out.println(System.nanoTime() - startTime);
        assertArrayEquals(expectedResult, result);
    }
}
