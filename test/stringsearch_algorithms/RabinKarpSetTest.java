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
public class RabinKarpSetTest {

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
    public void testIndexOf() {
        System.out.println("indexOf - RabinKarpSet");
        String text = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, "
                + "sed diam nonumy eirmod tempor invidunt ut labore et dolore magna "
                + "aliquyam erat, sed diam voluptua.";
        char[] stack = text.toCharArray();
        Set<String> patterns = new HashSet<>();
        patterns.add("sit");
        patterns.add("con");
        int patternLength = 3;
        int[] expectedResult = new int[2];
        expectedResult[0] = 18;
        expectedResult[1] = 28;
        RabinKarpSet instance = new RabinKarpSet();
        int[] result = instance.indexOf(stack, patterns, patternLength);
        assertArrayEquals(expectedResult, result);
    }
}
