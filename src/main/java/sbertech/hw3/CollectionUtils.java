package sbertech.hw3;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CollectionUtils<T> {
    public static<T> void addAll(List<? extends T> source, List<? super T> destination) {
        destination.addAll(source);
    }

    public static <T> List<T> newArrayList() {
        return new ArrayList<>();
    }

    public static <T> int indexOf(List<? super T> source, T o) {
        return source.indexOf(o);
    }

    public static <T> List<T> limit(List<T> source, int size) throws IllegalArgumentException {
        if (size < 0) throw new IllegalArgumentException();
        return source.subList(0, Math.min(size, source.size()));
    }

    public static <T> void add(List<? super T> source, T o) {
        source.add(o);
    }

    public static <T> void removeAll(List<? super T> removeFrom, List<T> c2) {
        if (removeFrom == c2) removeFrom.clear();
        else c2.forEach(removeFrom::remove);
    }

    public static <T> boolean containsAll(List<? super T> c1, List<T> c2) {
        return c1.containsAll(c2);
    }

    public static <T> boolean containsAny(List<? super T> c1, List<T> c2) {
        for(T e: c2){
            if (c1.contains(e)) return true;
        }
        return false;
    }

    public static <T extends Comparable<T>> List<T> range(List<T> list, T min, T max) {
        return list.stream().filter(e->e.compareTo(min) >= 0 && e.compareTo(max) <= 0).collect(Collectors.toList());
    }

    public static <T> List<T> range(List<T> list, T min, T max, Comparator<T> comparator) {
        return list.stream().filter(e->comparator.compare(e, min) >= 0 && comparator.compare(e, max) <= 0).collect(Collectors.toList());
    }
}

