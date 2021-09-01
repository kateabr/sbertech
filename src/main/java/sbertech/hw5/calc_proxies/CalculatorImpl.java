package sbertech.hw5.calc_proxies;

public class CalculatorImpl implements Calculator {
    @Override
    public long calc(@NonNegative long number) {
        if (number <= 1) return 1;
        return number * calc(number - 1);
    }

    @Override
    @Cache
    public long fibonacciNumbers(@NonNegative long number) {
        if (number <= 2) return 1;
        return fibonacciNumbers(number - 1) + fibonacciNumbers(number - 2);
    }

    public static void main(String[] args) throws NoSuchMethodException {
        Calculator calculator = new CalculatorImpl();
        Calculator proxy0 = NonNegativeValueProxy.newProxyInstance(calculator, calculator.getClass());
        Calculator proxy1 = CacheProxy.newProxyInstance(proxy0, calculator.getClass());
        Calculator proxy2 = PerformanceProxy.newProxyInstance(proxy1, calculator.getClass());
        System.out.println(proxy2.fibonacciNumbers(35));
        System.out.println(proxy2.fibonacciNumbers(35));
        System.out.println(proxy2.calc(3));
//        System.out.println(proxy.getClass().getSimpleName());
//        System.out.println(proxy.calc1(3));
    }
}
