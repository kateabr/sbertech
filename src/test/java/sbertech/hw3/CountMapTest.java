package sbertech.hw3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class CountMapTest {
    @Test
    public void testGetCount() {
        CountMapImpl<Integer> map = new CountMapImpl<Integer>();
        map.add(10);
        map.add(10);
        map.add(5);
        map.add(6);
        map.add(5);
        map.add(10);

        Assertions.assertEquals(map.getCount(5), 2);
        Assertions.assertEquals(map.getCount(6), 1);
        Assertions.assertEquals(map.getCount(10), 3);

        Assertions.assertEquals(map.size(), 3);
    }

    @Test
    public void testRemove() {
        CountMapImpl<Integer> map = new CountMapImpl<Integer>();
        map.add(10);
        map.add(10);
        map.add(5);
        map.add(6);
        map.add(5);
        map.add(10);

        Assertions.assertEquals(map.remove(5), 1);
        Assertions.assertEquals(map.remove(6), 0);
        Assertions.assertEquals(map.remove(10), 2);
        Assertions.assertEquals(map.remove(8), 0);

        Assertions.assertEquals(map.size(), 2);
    }

    @Test
    public void testAddAll() {
        CountMapImpl<Integer> map = new CountMapImpl<Integer>();
        map.add(10);
        map.add(10);
        map.add(5);
        map.add(6);
        map.add(5);
        map.add(10);

        CountMapImpl<Integer> map2 = new CountMapImpl<Integer>();

        map2.addAll(map);

        Assertions.assertEquals(map.toMap(), map2.toMap());
    }

    public void testToMap() {
        CountMapImpl<Integer> map = new CountMapImpl<Integer>();
        map.add(10);
        map.add(10);
        map.add(5);
        map.add(6);
        map.add(5);
        map.add(10);

        Map<Integer, Integer> map2 = new HashMap<>();
        map.toMap(map2);

        Assertions.assertEquals(map.toMap(), map2);
    }
}
