package sbertech.hw14;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicBoolean;

public class CacheProxy implements InvocationHandler {
    private final Object delegate;
    private final ConcurrentMap<Integer, Object> jvmCache;

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
        jvmCache = new ConcurrentHashMap<>();
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.isAnnotationPresent(Cache.class)) {
            int argsHashCode = Arrays.hashCode(args);
            AtomicBoolean computed = new AtomicBoolean();
            Object computedValue = jvmCache.computeIfAbsent(argsHashCode, integer -> {
                try {
                    computed.set(true);
                    return method.invoke(delegate, args);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            });
            if (computed.get()) {
                System.out.printf("> [%s] Value has been cached (%s) :: %s\n", computedValue.toString(), method.getName(), Thread.currentThread().getName());
            } else {
                System.out.printf("> [%s] Cached value (%s) :: %s\n", computedValue.toString(), method.getName(), Thread.currentThread().getName());
            }
            return jvmCache.get(argsHashCode);
        }
        return method.invoke(delegate, args);
    }
}
