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
package sort_search_lib.sorting_algorithms;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Matthias Fussenegger
 */
public interface TestableSort {

    /**
     * The {@link List} to be tested
     */
    List<Integer> VALUES = generateRandomIntegerList(999);

    static List<Integer> generateRandomIntegerList(int size) {
        List<Integer> values = new LinkedList<>();
        for (int i = 0; i < size; ++i) {
            values.add(new Random().nextInt());
        }
        Collections.shuffle(values, new Random());
        return values;
    }
}
