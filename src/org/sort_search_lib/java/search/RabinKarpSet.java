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

import org.sort_search_lib.java.search.api.MultiStringSearch;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Offers the Rabin-Karp algorithm for finding multiple patterns in a specified
 * text. This algorithm is also known as Rabin-Karp-Set, as it makes use of a
 * {@code Set} which consists of hashes of the patterns.
 *
 * @author Matthias Fussenegger
 */
public final class RabinKarpSet extends RabinKarp implements MultiStringSearch {

    @Override
    public List<Integer> indexOf(char[] text, Set<CharSequence> patterns, int patternLength) {
        if (text == null || patterns == null
                || text.length < 1 || text.length < patternLength) {
            return null;
        }
        List<Integer> occurrences = new LinkedList<Integer>();

        Set<Long> patternHashes = new HashSet<Long>();
        /*calculate hash for each pattern*/
        for (CharSequence pattern : patterns) {
            patternHashes.add(hash(pattern));
        }

        long t = 0L; // decimal value of text substring (of pattern length)
        long h = 1L; // radix^(m-1) mod Q

        /*pre-compute radix^(m-1) mod Q, where m is the pattern length*/
        for (int i = 1; i < patternLength; ++i) {
            h = (R * h) % Q;
        }

        /*preprocessing*/
        for (int i = 0; i < patternLength; ++i) {
            t = (R * t + text[i]) % Q;
        }

        /*matching*/
        for (int i = 0; i < text.length - patternLength; ++i) {
            if (patternHashes.contains(t)) { // match found
                if (patterns.contains(String.valueOf(text, i, patternLength))) {
                    occurrences.add(i);
                }
            }
            t = ((R * (t - text[i] * h)) + text[i + patternLength]) % Q;
            if (t < 0) { // convert t in case it is negative
                t += Q;
            }
        }
        return occurrences;
    }

    /**
     * Pre-computes a hash value of the specified pattern.
     *
     * @param pattern the pattern of which to compute a hash value.
     * @return the computed hash value of the pattern.
     */
    private long hash(CharSequence pattern) {
        long p = 0L;
        for (int i = 0; i < pattern.length(); ++i) {
            p = (R * p + pattern.charAt(i)) % Q;
        }
        return p;
    }
}
