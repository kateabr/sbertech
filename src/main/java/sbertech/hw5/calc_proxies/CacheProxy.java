package sbertech.hw5.calc_proxies;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.HashMap;

public class CacheProxy implements InvocationHandler {
    private final Object delegate;
    private final HashMap<Integer, Object> cache = new HashMap<>();

    public static<T> T newProxyInstance(Object object, Class<? extends T> clazz) {
        return (T) Proxy.newProxyInstance(
                ClassLoader.getSystemClassLoader(),
                clazz.getInterfaces(),
                new CacheProxy(object));
    }

    private CacheProxy(Object delegate) {
        this.delegate = delegate;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.isAnnotationPresent(Cache.class)) {
            int argsHashCode = Arrays.hashCode(args);
            if (cache.containsKey(argsHashCode)) {
                System.out.println("> Cached value");
                return cache.get(argsHashCode);
            } else {
                Object res = method.invoke(delegate, args);
                cache.put(argsHashCode, res);
                System.out.println("> Value has been cached");
                return res;
            }
        }
        return method.invoke(delegate, args);
    }
}
