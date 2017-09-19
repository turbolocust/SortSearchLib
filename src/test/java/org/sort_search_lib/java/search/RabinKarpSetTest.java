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

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.sort_search_lib.java.search.api.MultiStringSearch.Occurrence;

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
        int numOfPatterns = 10, patternLength = 12;
        Set<CharSequence> patterns = new HashSet<CharSequence>();
        // generate random patterns from randomly generated text
        for (int i = 0; i < numOfPatterns; ++i) {
            patterns.add(TestableSearchUtils
                    .generateRandomPattern(TEXT, patternLength));
        }
        // get expected results (test against String method)
        List<Integer> expectedResults = new LinkedList<Integer>();
        for (CharSequence pattern : patterns) {
            expectedResults.add(TEXT.indexOf(pattern.toString()));
        }
        Collections.sort(expectedResults);
        RabinKarpSet instance = new RabinKarpSet();
        long startTime = System.nanoTime();
        List<Occurrence> result = instance.indexesOf(TEXT, patterns, patternLength);
        System.out.println(System.nanoTime() - startTime);
        // evaluate result
        int resultPos[] = new int[result.size()], i = -1;
        for (Occurrence o : result) {
            resultPos[++i] = o.getPosition();
        }
        assertArrayEquals(convertToArray(expectedResults), resultPos);
    }

    private static int[] convertToArray(List<Integer> list) {
        if (list.size() > 0) {
            int[] occurrences = new int[list.size()];
            int i = 0;
            /*copy each list entry to the array*/
            for (Integer pos : list) {
                occurrences[i] = pos;
                ++i;
            }
            return occurrences;
        }
        return new int[]{};
    }
}
