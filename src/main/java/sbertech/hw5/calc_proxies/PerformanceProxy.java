package sbertech.hw5.calc_proxies;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class PerformanceProxy implements InvocationHandler {
    private final Object delegate;

    public static<T> T newProxyInstance(Object object, Class<? extends T> clazz) {
        return (T) Proxy.newProxyInstance(
                ClassLoader.getSystemClassLoader(),
                clazz.getInterfaces(),
                new PerformanceProxy(object));
    }

    private PerformanceProxy(Object delegate) {
        this.delegate = delegate;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.isAnnotationPresent(Metric.class)) {
            long start = System.currentTimeMillis();
            Object res = method.invoke(delegate, args);
            System.out.printf("> Work time: %d nano s\n", System.currentTimeMillis() - start);
            return res;
        }
        return method.invoke(delegate, args);
    }
}
