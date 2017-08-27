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

import org.sort_search_lib.java.search.api.SingleStringSearch;
import java.math.BigInteger;
import java.util.Random;

/**
 * Offers the Rabin-Karp algorithm for finding the first occurrence of a
 * specified pattern in the specified text.
 * <p>
 * See
 * <a href="https://en.wikipedia.org/wiki/Introduction_to_Algorithms">Introduction
 * To Algorithms</a> for more details regarding this implementation.
 * </p>
 *
 * @author Matthias Fussenegger
 */
public class RabinKarp implements SingleStringSearch {

    /**
     * A large probable 31-bit prime number.
     */
    protected final long Q = randomPrime();

    /**
     * Radix value for text mapping, depends on alphabet.
     */
    protected final int R = 256;

    /**
     * Calculates a random 31-bit prime number.
     *
     * @return random 31-bit prime number.
     */
    private long randomPrime() {
        BigInteger prime = BigInteger.probablePrime(31, new Random());
        return prime.longValue();
    }

    @Override
    public int indexOf(char[] text, CharSequence pattern) {
        if (text == null || pattern == null
                || text.length < 1 || pattern.length() < 1) {
            return NOT_FOUND;
        }
        long p = 0L; // decimal value of pattern
        long t = 0L; // decimal value of text substring (of pattern length)
        long h = 1L; // radix^(m-1) mod Q

        /*pre-compute radix^(m-1) mod Q, where m is the pattern length*/
        for (int i = 1; i < pattern.length(); ++i) {
            h = (R * h) % Q;
        }

        /*preprocessing*/
        for (int i = 0; i < pattern.length(); ++i) {
            p = (R * p + pattern.charAt(i)) % Q;
            t = (R * t + text[i]) % Q;
        }

        /*matching*/
        for (int i = 0; i < text.length - pattern.length(); ++i) {
            if (p == t) { // match found
                if (pattern.equals(String.valueOf(text, i, pattern.length()))) {
                    return i;
                }
            }
            t = ((R * (t - text[i] * h)) + text[i + pattern.length()]) % Q;
            if (t < 0) { // convert t in case it is negative
                t += Q;
            }
        }
        return NOT_FOUND;
    }
}
