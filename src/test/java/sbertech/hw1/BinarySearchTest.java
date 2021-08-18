package sbertech.hw1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class BinarySearchTest {
    @Test
    public void testEmptyArray() {
        Assertions.assertEquals(-1, BinarySearch.binarySearch(1.0, new ArrayList<Double>()));
    }

    @Test
    public void testArray() {
        Assertions.assertEquals(0,
                BinarySearch.binarySearch(1.0, new ArrayList<>(List.of(1.0, 2.0, 3.0))));
        Assertions.assertEquals(1,
                BinarySearch.binarySearch(2, new ArrayList<>(List.of(1, 2, 3))));
        Assertions.assertEquals(2,
                BinarySearch.binarySearch("ab", new ArrayList<>(List.of("a", "aa", "ab"))));

        Assertions.assertEquals(-1,
                BinarySearch.binarySearch(5, new ArrayList<>(List.of(1, 2, 3))));
    }
}
