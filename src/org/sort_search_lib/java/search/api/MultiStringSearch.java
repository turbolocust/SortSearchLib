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
package org.sort_search_lib.java.search.api;

import java.util.List;
import java.util.Set;

/**
 * Implementing classes offer the functionality of finding multiple
 * {@link String} values in a specified text.
 *
 * @author Matthias Fussenegger
 */
public interface MultiStringSearch {

    /**
     * Finds the occurrences of the patterns in the specified {@code text}. This
     * implementation only works with patterns having a fixed size, so each
     * pattern of the {@link Set} needs to have the same length, which is
     * specified when calling this method.
     *
     * If the length does not match the length of at least one pattern in the
     * {@link Set}, the results of this algorithm cannot be trusted.
     *
     * @param text the text to be searched for the patterns.
     * @param patterns {@link Set} of patterns to be found in the text.
     * @param patternLength the fixed length of each pattern.
     * @return the zero-based positions of pattern occurrences in the text or
     * {@code null} if no pattern is a substring of the text, meaning no match
     * has been found.
     */
    List<Integer> indexOf(char[] text, Set<CharSequence> patterns, int patternLength);

}
