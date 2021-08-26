package sbertech.hw3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class CollectionUtilsTest {
    @Test
    public void testAddAll() {
        List<Number> numberList = CollectionUtils.newArrayList();
        numberList.add(1);
        numberList.add(1.0);
        List<Integer> integerList = List.of(2, 3);
        List<Double> doubleList = List.of(2.0, 3.0);

        CollectionUtils.addAll(integerList, numberList);
        CollectionUtils.addAll(doubleList, numberList);

        Assertions.assertEquals(numberList, List.of(1, 1.0, 2, 3, 2.0, 3.0));
    }

    @Test
    public void testIndexOf() {
        List<Number> numberList = List.of(1, 1.0, 2, 3, 2.0, 3.0);

        Assertions.assertEquals(CollectionUtils.indexOf(numberList, 3), 3);
        Assertions.assertEquals(CollectionUtils.indexOf(numberList, 3.0), 5);
    }

    @Test
    public void testLimit() {
        List<Number> numberList = List.of(1, 1.0, 2, 3, 2.0, 3.0);

        Assertions.assertEquals(CollectionUtils.limit(numberList, 0), List.of());
        Assertions.assertEquals(CollectionUtils.limit(numberList, 10),
                CollectionUtils.limit(numberList, numberList.size()));
        Assertions.assertEquals(CollectionUtils.limit(numberList, 4), List.of(1, 1.0, 2, 3));
        Assertions.assertThrows(IllegalArgumentException.class, () -> CollectionUtils.limit(numberList, -1));
    }

    @Test
    public void testRemoveAll() {
        List<Number> numberList = CollectionUtils.newArrayList();
        numberList.add(1);
        numberList.add(1.0);
        numberList.add(2);
        numberList.add(3);
        numberList.add(2.0);
        numberList.add(3.0);
        List<Integer> integerList = List.of(2, 3);
        List<Double> doubleList = List.of(2.0, 3.0);

        CollectionUtils.removeAll(numberList, integerList);
        Assertions.assertEquals(numberList, List.of(1, 1.0, 2.0, 3.0));
        CollectionUtils.removeAll(numberList, doubleList);
        Assertions.assertEquals(numberList, List.of(1, 1.0));
        CollectionUtils.removeAll(numberList, numberList);
        Assertions.assertEquals(numberList, List.of());
    }

    @Test
    public void testContainsAll() {
        List<Number> numberList = List.of(1, 1.0, 2, 3, 2.0, 3.0);

        Assertions.assertTrue(CollectionUtils.containsAll(numberList, List.of(2, 3)));
        Assertions.assertTrue(CollectionUtils.containsAll(numberList, List.of(2.0, 3.0)));
        Assertions.assertFalse(CollectionUtils.containsAll(numberList, List.of(4, 4.0)));
        Assertions.assertTrue(CollectionUtils.containsAll(numberList, numberList));
    }

    @Test
    public void testContainsAny() {
        List<Number> numberList = List.of(1, 1.0, 2, 3, 2.0, 3.0);

        Assertions.assertTrue(CollectionUtils.containsAny(numberList, List.of(2, 3)));
        Assertions.assertTrue(CollectionUtils.containsAny(numberList, List.of(2.0, 3.0)));
        Assertions.assertTrue(CollectionUtils.containsAny(numberList, List.of(4, 4.0, 1)));
        Assertions.assertTrue(CollectionUtils.containsAny(numberList, numberList));
    }

    @Test
    public void testRangeDefaultComp() {
        List<Integer> integerList = new LinkedList<>(List.of(0, 1, 2, 3, 4, 5, 6));
        Collections.shuffle(integerList);

        List<Integer> integerSubList = CollectionUtils.range(integerList, 2, 4);
        Assertions.assertEquals(integerSubList.stream().sorted().collect(Collectors.toList()), List.of(2, 3, 4));

        integerSubList = CollectionUtils.range(integerList, -1, 10);
        Assertions.assertEquals(integerSubList.stream().sorted().collect(Collectors.toList()), List.of(0, 1, 2, 3, 4, 5, 6));

        integerSubList = CollectionUtils.range(integerList, 4, 2);
        Assertions.assertEquals(integerSubList.stream().sorted().collect(Collectors.toList()), List.of());
    }

    @Test
    public void testRangeCustomComp() {
        List<String> stringList = new LinkedList<>(List.of("a", "aa", "ccccc", "", "b", "bbbb"));
        Collections.shuffle(stringList);

        List<String> stringSubList = CollectionUtils.range(stringList, "aa", "bbbb", Comparator.comparing(String::length));
        Assertions.assertTrue(stringSubList.containsAll(List.of("aa", "bbbb")) && stringSubList.size() == 2);

        stringSubList = CollectionUtils.range(stringList, "", "ccccc", Comparator.comparing(String::length));
        Assertions.assertTrue(stringSubList.containsAll(stringList) && stringSubList.size() == stringList.size());


        List<Number> numberList = new LinkedList<>(List.of(1, 2.0, 3, 4.5));
        List<Number> numberSubList = CollectionUtils.range(numberList, 2.0, 6.0, Comparator.comparing(Number::doubleValue));
        Assertions.assertTrue(numberSubList.containsAll(List.of(2.0, 3, 4.5)) && numberSubList.size() == 3);
    }
}
