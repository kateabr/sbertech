package sbertech.hw5.calc_proxies;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Proxy;

public class NonNegativeValueProxy implements InvocationHandler {
    private final Object delegate;

    public static<T> T newProxyInstance(Object object, Class<? extends T> clazz) {
        return (T) Proxy.newProxyInstance(
                ClassLoader.getSystemClassLoader(),
                clazz.getInterfaces(),
                new NonNegativeValueProxy(object));
    }

    private NonNegativeValueProxy(Object delegate) {
        this.delegate = delegate;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        for (int i = 0; i < method.getParameterAnnotations().length; i++) {
            for (Parameter p : method.getParameters()) {
                if (p.isAnnotationPresent(NonNegative.class)) {
                    long arg = (long) args[i];
                    if (arg < p.getAnnotation(NonNegative.class).value()) {
                        throw new IllegalArgumentException(String.format("Negative parameter value: %d", arg));
                    }
                }
            }
        }
        return method.invoke(delegate, args);
    }
}
