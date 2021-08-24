package sbertech.hw3;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class CountMapImpl<T> implements CountMap<T> {
    private Map<T, Integer> data;

    public CountMapImpl() {
        data = new HashMap<>();
    }

    public void add(T o) {
        data.put(o, data.containsKey(o) ? data.get(o) + 1 : 1);
    }

    public int getCount(T o) {
        return data.getOrDefault(o, 0);
    }

    //current count
    public int remove(T o) {
        if (getCount(o) == 0) return 0;
        if (getCount(o) == 1) {
            data.remove(o);
            return 0;
        }
        data.put(o, data.get(o) - 1);
        return getCount(o);
    }

    public int size() {
        return data.size();
    }

    private void addPair(T key, Integer value) {
        if (data.containsKey(key)) data.put(key, data.get(key) + value);
        else data.put(key, value);
    }

    public void addAll(CountMap<? extends T> source) {
        source.toMap().forEach(this::addPair);
    }

    public Map<T, Integer> toMap() {
        return Collections.unmodifiableMap(data);
    }

    public void toMap(Map<? super T, Integer> destination) {
        destination.putAll(data);
    }
}
