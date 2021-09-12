package sbertech.hw8;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DiskMap implements Map<Integer, Object> {
    private final Path dirname;

    public DiskMap(Path dirname) {
        this.dirname = dirname;
    }

    @Override
    public int size() {
        try (Stream<Path> stream = Files.walk(dirname, 1)) {
            return (int) stream.count();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public boolean containsKey(Object key) {
        return Files.exists(dirname.resolve(key.toString()));
    }

    @Override
    public boolean containsValue(Object value) {
        return values().contains(value);
    }

    @Override
    public Object get(Object key) {
        try {
            if (!Files.exists(dirname.resolve(key.toString()))) return null;
            byte[] bytes = Files.readAllBytes(dirname.resolve(key.toString()));
            try (ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(bytes))) {
                return ois.readObject();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }

    }

    @Override
    public Object put(Integer key, Object value) {
        Object oldValue = get(key);

        try (FileOutputStream fos = new FileOutputStream(dirname.resolve(key.toString()).toFile());
             ObjectOutputStream out = new ObjectOutputStream(fos)) {
            out.writeObject(value);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }

        return oldValue;
    }

    @Override
    public Object remove(Object key) {
        try {
            Object oldValue = get(key);
            Files.delete(dirname.resolve(key.toString()));
            return oldValue;
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    @Override
    public void putAll(Map<? extends Integer, ?> m) {
        for(Map.Entry<? extends Integer, ?> entry: m.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public void clear() {
        try {
            for(Path file: Files.walk(dirname, 1).collect(Collectors.toList())) {
                Files.delete(file);
            }
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    @Override
    public Set<Integer> keySet() {
        try {
            return Files.walk(dirname, 1)
                    .map(path -> Integer.parseInt(path.getFileName().toString()))
                    .collect(Collectors.toSet());
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    @Override
    public Collection<Object> values() {
        return keySet().stream().map(this::get).collect(Collectors.toList());
    }

    @Override
    public Set<Entry<Integer, Object>> entrySet() {
        return keySet().stream().map(key -> new MyEntry(key, get(key))).collect(Collectors.toSet());
    }

    private static class MyEntry implements Entry<Integer, Object> {
        private Integer key;
        private Object value;

        MyEntry(Integer key, Object value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public Integer getKey() {
            return key;
        }

        @Override
        public Object getValue() {
            return value;
        }

        @Override
        public Object setValue(Object value) {
            Object oldValue = this.value;
            this.value = value;
            return oldValue;
        }
    }
}
