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

import algo_interfaces.SingleStringSearch;
import java.math.BigInteger;
import java.util.Random;

/**
 * Offers the Rabin-Karp algorithm for finding the first occurrence of a
 * {@link String} value (pattern) in the specified text. See
 * {@link https://en.wikipedia.org/wiki/Introduction_to_Algorithms} for more
 * details regarding the implementation
 *
 * @author Matthias Fussenegger
 */
public class RabinKarp implements SingleStringSearch {

    /**
     * A large probable 31-bit prime number
     */
    protected final long Q = randomPrime();

    /**
     * Radix value for text mapping, depends on alphabet
     */
    protected final int R = 256;

    /**
     * Calculates a random 31-bit prime number
     *
     * @return A random 31-bit prime number
     */
    private long randomPrime() {
        BigInteger prime = BigInteger.probablePrime(31, new Random());
        return prime.longValue();
    }

    @Override
    public int indexOf(char[] text, CharSequence pattern) {
        if (text.length < 1 || pattern.length() < 1) {
            return NOT_FOUND;
        }
        long p = 0L; //decimal value of pattern
        long t = 0L; //decimal value of text substring (of pattern length)
        long h = 1L; //radix^(m-1) mod Q

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
            if (p == t) { //match found
                if (pattern.equals(String.valueOf(text, i, pattern.length()))) {
                    return i;
                }
            }
            t = ((R * (t - text[i] * h)) + text[i + pattern.length()]) % Q;
            if (t < 0) { //convert t in case it is negative
                t += Q;
            }
        }
        return NOT_FOUND;
    }
}
