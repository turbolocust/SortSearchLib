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
 * Implementing classes offer the functionality of finding multiple patterns in
 * a specified text.
 *
 * @author Matthias Fussenegger
 */
public interface MultiStringSearch {

    /**
     * Finds the occurrences of the patterns in the specified text. This
     * implementation only works with patterns having a fixed size, so each
     * pattern of the {@link Set} needs to have the same length, which is
     * specified when calling this method.
     *
     * If the length does not match the length of each pattern in the
     * {@link Set}, the results of this algorithm cannot be trusted.
     *
     * @param text the text to be searched for the patterns.
     * @param patterns {@link Set} of patterns to be searched for in the text.
     * @param patternLength the fixed length of each pattern.
     * @return zero-based occurrences of the pattern in the text or an empty
     * list if no pattern is a substring of the text, meaning that no match has
     * been found.
     */
    List<Occurrence> indexesOf(CharSequence text, Set<CharSequence> patterns, int patternLength);

    /**
     * Value class which holds the pattern and its found position in the text.
     */
    static class Occurrence {

        /**
         * Position where the mapped pattern was found in the text.
         */
        private final int _position;

        /**
         * The pattern that was found at the mapped position.
         */
        private final CharSequence _pattern;

        public Occurrence(int position, CharSequence pattern) {
            _position = position;
            _pattern = pattern;
        }

        /**
         * Returns the position where the mapped pattern was found in the text.
         *
         * @return the position of the found pattern in the text.
         */
        public int getPosition() {
            return _position;
        }

        /**
         * Returns the pattern that was found at the mapped position.
         *
         * @return the pattern that was found at the mapped position.
         */
        public CharSequence getPattern() {
            return _pattern;
        }

        @Override
        public int hashCode() {
            int hash = 5;
            hash = 97 * hash + this._position;
            hash = 97 * hash + (this._pattern != null ? this._pattern.hashCode() : 0);
            return hash;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final Occurrence other = (Occurrence) obj;
            if (this._position != other._position) {
                return false;
            }
            return !(this._pattern != other._pattern && (this._pattern == null || !this._pattern.equals(other._pattern)));
        }

        @Override
        public String toString() {
            return "Occurrence{" + "_position=" + _position + ", _pattern=" + _pattern + '}';
        }
    }
}
