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
package sorting_algorithms;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Matthias Fussenegger
 */
public interface TestableSort {

    /**
     * The list to be tested
     */
    List VALUES = new LinkedList<>();

    @SuppressWarnings("unchecked")
    default void addDefaultListValues() {
        VALUES.add(0);
        VALUES.add(1);
        VALUES.add(2);
        VALUES.add(3);
        VALUES.add(4);
        VALUES.add(5);
        VALUES.add(6);
        VALUES.add(7);
        VALUES.add(8);
        VALUES.add(9);
        VALUES.add(10);
        VALUES.add(11);
        VALUES.add(12);
        VALUES.add(13);
        VALUES.add(14);
        VALUES.add(15);
        VALUES.add(16);
        VALUES.add(17);
        VALUES.add(18);
        VALUES.add(19);
        VALUES.add(20);
    }
}
