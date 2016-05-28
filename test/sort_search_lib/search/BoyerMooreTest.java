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
package sort_search_lib.search;

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
public class BoyerMooreTest implements TestableSearch {

    public BoyerMooreTest() {
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
     * Test of indexOf method, of class BoyerMoore.
     */
    @Test
    @Override
    public void testIndexOf() {
        System.out.println("indexOf - BoyerMoore");
        char[] stack = TEXT.toCharArray();
        String needle = "gubergren";
        BoyerMoore instance = new BoyerMoore();
        int expResult = 229;
        long startTime = System.nanoTime();
        int result = instance.indexOf(stack, needle);
        System.out.println(System.nanoTime() - startTime);
        assertEquals(expResult, result);
    }

}
