package sbertech.hw14;

import java.io.IOException;
import java.util.stream.Stream;

public class CalculatorImpl implements Calculator {
    @Override
    @sbertech.hw8.Cache(saveOnDisk = true)
    public long fibonacciNumbers(long number) {
        if (number <= 2) return 1;
        return fibonacciNumbers(number - 1) + fibonacciNumbers(number - 2);
    }

    public static void main(String[] args) throws NoSuchMethodException, IOException {
        Calculator calculator = new CalculatorImpl();
        Calculator proxy = CacheProxy.newProxyInstance(calculator, calculator.getClass());
        Stream.iterate(40, i -> i).limit(10).parallel()
                .forEach(proxy::fibonacciNumbers);
    }
}
