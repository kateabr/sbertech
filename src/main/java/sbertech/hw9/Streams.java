package sbertech.hw9;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;

public class Streams<T> {
    private final List<T> collection;

    private Streams() {
        collection = new LinkedList<>();
    }

    public static <T> Streams<T> of(List<? extends T> c) {
        Streams<T> streams = new Streams<>();
        streams.collection.addAll(c);
        return streams;
    }

    public Streams<T> filter(Predicate<? super T> predicate) {
        int id = 0;
        while (id < collection.size()) {
            T elem = collection.get(id);
            if (!predicate.test(elem)) {
                collection.remove(elem);
            } else id += 1;
        }
        return this;
    }

    public <R> Streams<R> transform(Function<? super T, R> function) {
        Streams<R> newStreams = new Streams<>();
        for (int i = 0; i < collection.size(); i++) {
            newStreams.collection.add(function.apply(collection.get(i)));
        }
        return newStreams;
    }

    public <T1, T2> Map<T1, T2> toMap(Function<? super T, T1> keyFunc, Function<? super T, T2> valFunc) {
        Map<T1, T2> res = new HashMap<>();
        for (T t : collection) {
            res.put(keyFunc.apply(t), valFunc.apply(t));
        }
        return res;
    }

    @Override
    public String toString() {
        return "Streams{" +
                "collection=" + collection +
                '}';
    }

    public static void main(String[] args) {
        List<Person> famousEnglishPeopleAndJeff = List.of(new Writer("Jane", "Austen", 1775, 1817),
                new Writer("William", "Blake", 1757, 1827),
                new Writer("Geoffrey", "Chaucer", 1343, 1400),
                new Writer("Charles", "Dickens", 1812, 1870),
                new Writer("John", "Donne", 1572, 1631),
                new Writer("George", "Eliot", 1819, 1880),
                new Writer("John", "Milton", 1608, 1674),
                new Writer("George", "Orwell", 1903, 1950),
                new Writer("Harold", "Pinter", 1930, 2008),
                new Writer("Samuel Taylor", "Coleridge", 1772, 1834),
                new Actor("Christian", "Bale", 1974),
                new Actor("Sean", "Bean", 1959),
                new Actor("Kate", "Beckinsale", 1973),
                new Actor("Dirk", "Bogarde", 1921, 1999),
                new Actor("Michael", "Caine", 1933),
                new Actor("Robert", "Carlyle", 1961),
                new Actor("John", "Cleese", 1939),
                new Actor("Sacha Baron", "Cohen", 1971),
                new Actor("Sean", "Connery", 1930, 2020),
                new Actor("Charles", "Dance", 1946),
                new Person("Jeff", "Baker", 1996));
        System.out.println(Streams.of(famousEnglishPeopleAndJeff)
                .filter(p -> p.fullYearsLived() > 60)
                .toMap(Person::initials, Person::fullYearsLived));
        System.out.println(Streams.of(famousEnglishPeopleAndJeff)
                .filter(p -> !p.isDeceased() && p.occupation() == Occupation.ACTOR)
                .transform(Person::initials));
        System.out.println(Streams.of(famousEnglishPeopleAndJeff)
                .filter(p -> p.getBornDate() > 1972)
                .toMap(Person::initials, Person::occupation));
    }
}
