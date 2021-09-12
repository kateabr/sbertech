package sbertech.hw8;

import java.io.IOException;

public class CalculatorImpl implements sbertech.hw8.Calculator {
    @Override
    @Cache(saveOnDisk = true)
    public long fibonacciNumbers1(long number) {
        if (number <= 2) return 1;
        return fibonacciNumbers1(number - 1) + fibonacciNumbers1(number - 2);
    }

    @Override
    @Cache(saveOnDisk = false)
    public long fibonacciNumbers2(long number) {
        if (number <= 2) return 1;
        return fibonacciNumbers2(number - 1) + fibonacciNumbers2(number - 2);
    }

    public static void main(String[] args) throws NoSuchMethodException, IOException {
        Calculator calculator = new CalculatorImpl();
        Calculator proxy = CacheProxy.newProxyInstance(calculator, calculator.getClass());
        System.out.println(proxy.fibonacciNumbers1(35));
        System.out.println(proxy.fibonacciNumbers1(35));
        System.out.println(proxy.fibonacciNumbers2(35));
        System.out.println(proxy.fibonacciNumbers2(35));
    }
}
