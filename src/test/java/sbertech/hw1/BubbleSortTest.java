package sbertech.hw1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class BubbleSortTest {
    @Test
    public void testEmptyArray() {
        Assertions.assertEquals(new ArrayList<Double>(), BubbleSort.bubbleSort(new ArrayList<Double>()));
    }

    @Test
    public void testArray() {
        Assertions.assertEquals(List.of(1.0, 2.0, 3.0),
                BubbleSort.bubbleSort(new ArrayList<>(List.of(1.0, 3.0, 2.0))));
        Assertions.assertEquals(List.of(0, 1, 2),
                BubbleSort.bubbleSort(new ArrayList<>(List.of(1, 0, 2))));
        Assertions.assertEquals(List.of("a", "aa", "ab"),
                BubbleSort.bubbleSort(new ArrayList<>(List.of("ab", "aa", "a"))));
    }
}
