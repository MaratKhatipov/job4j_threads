package ru.job4j.pool;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ParallelSearchTest {


    @Test
    @DisplayName("рекурсивный поиск")
    public void whenFind5ThenReturnIndex0() {
        Integer[] test = {5, 7, 8, 8, 0, 4, 2, 5, 1, 6, 8, 15, 2, 6, 7};
        int expected = ParallelSearch.indexOf(test, 5);
        Assertions.assertEquals(expected, 0);
    }

    @Test
    @DisplayName("Элемент не найден")
    public void whenCanNotFindThenReturnMinus1() {
        Integer[] test = {5, 7, 8, 8, 0, 4, 2, 5, 1, 6, 8, 15, 2, 6, 7};
        int expected = ParallelSearch.indexOf(test, 9);
        Assertions.assertEquals(expected, -1);
    }

    @Test
    @DisplayName("Линейный поиск")
    public void whenLinearSearchIndex() {
        Integer[] test = {0, 15, 5, 6, 1};
        int expected = ParallelSearch.indexOf(test, 5);
        Assertions.assertEquals(expected, 2);
    }

    @Test
    @DisplayName("Поиск по символам")
    public void test() {
        Character[] test = {'a', 'e', 'd', 'c', 'y', 'h', 'n', 'z', 'x', 'c', 'b'};
        int expected = ParallelSearch.indexOf(test, 'h');
        Assertions.assertEquals(expected, 5);
    }
}