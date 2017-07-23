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

/**
 * Implementing classes offer the functionality of finding the first occurrence
 * of a {@link String} value (pattern) in the specified text.
 *
 * @author Matthias Fussenegger
 */
public interface SingleStringSearch {

    /**
     * Indicates that the specified pattern has not been found.
     */
    int NOT_FOUND = -1;

    /**
     * Finds the first occurrence of the pattern in the specified {@code text}.
     *
     * @param text the text to be searched for pattern.
     * @param pattern the pattern to be found in the text.
     * @return the zero-based position of the first occurrence in the text or a
     * negative {@link Integer} if the pattern is not a substring of the text.
     */
    int indexOf(char[] text, CharSequence pattern);

}
