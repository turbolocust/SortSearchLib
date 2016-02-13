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
package stringsearch_algorithms;

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
public class BoyerMooreTest {

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
    public void testIndexOf() {
        System.out.println("indexOf - BoyerMoore");
        String text = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, "
                + "sed diam nonumy eirmod tempor invidunt ut labore et dolore magna "
                + "aliquyam erat, sed diam voluptua. At vero eos et accusam et justo "
                + "duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata "
                + "sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, "
                + "consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut "
                + "labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et "
                + "accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no "
                + "sea takimata sanctus est Lorem ipsum dolor sit amet.";
        char[] stack = text.toCharArray();
        String needle = "sit";
        BoyerMoore instance = new BoyerMoore();
        int expResult = 18;
        int result = instance.indexOf(stack, needle);
        assertEquals(expResult, result);
    }
}
