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
