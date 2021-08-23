package sbertech.hw2;

import java.util.Iterator;
import java.util.List;

public class MyBackwardsListIterator<T> implements Iterator<T> {
    private List<T> list;
    private int id;

    public MyBackwardsListIterator(List<T> l) {
        list = l;
        id = list.size();
    }

    @Override
    public boolean hasNext() {
        return id > 0;
    }

    @Override
    public T next() {
        return list.get(--id);
    }
}
