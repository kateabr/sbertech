package sbertech.hw8;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CacheProxy implements InvocationHandler {
    private final Object delegate;
    private final Map<Integer, Object> jvmCache;
    private final Map<Integer, Object> diskCache;

    public static<T> T newProxyInstance(Object object, Class<? extends T> clazz) throws IOException {
        return (T) Proxy.newProxyInstance(
                ClassLoader.getSystemClassLoader(),
                clazz.getInterfaces(),
                new CacheProxy(object));
    }

    private CacheProxy(Object delegate) throws IOException {
        this.delegate = delegate;
        Path temp = Path.of("").resolve("temp");
        if (!Files.exists(temp)) {
            Files.createDirectory(temp);
        }
        diskCache = new DiskMap(temp);
        jvmCache = new HashMap<>();
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.isAnnotationPresent(Cache.class)) {
            Map<Integer, Object> cache = method.getAnnotation(Cache.class).saveOnDisk() ? diskCache : jvmCache;
            int argsHashCode = Arrays.hashCode(args);
            if (cache.containsKey(argsHashCode)) {
                System.out.printf("> Cached value (%s)\n", method.getName());
                return cache.get(argsHashCode);
            } else {
                Object res = method.invoke(delegate, args);
                cache.put(argsHashCode, res);
                System.out.printf("> Value has been cached (%s)\n", method.getName());
                return res;
            }
        }
        return method.invoke(delegate, args);
    }
}
