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

/**
 *
 * @author Matthias Fussenegger
 */
public interface TestableSearch {

    /**
     * Random string to be tested.
     */
    String TEXT = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, "
            + "sed diam nonumy eirmod tempor invidunt ut labore et dolore magna "
            + "aliquyam erat, sed diam voluptua. At vero eos et accusam et justo "
            + "duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata "
            + "sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, "
            + "consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut "
            + "labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et "
            + "accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no "
            + "sea takimata sanctus est Lorem ipsum dolor sit amet.";

    void testIndexOf();

}
