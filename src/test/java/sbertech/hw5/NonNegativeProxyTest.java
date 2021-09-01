package sbertech.hw5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import sbertech.hw5.calc_proxies.Calculator;
import sbertech.hw5.calc_proxies.CalculatorImpl;
import sbertech.hw5.calc_proxies.NonNegativeValueProxy;

public class NonNegativeProxyTest {
    @Test
    public void nonNegativeProxyTest() {
        Calculator calculator = new CalculatorImpl();
        Calculator calculator1 = NonNegativeValueProxy.newProxyInstance(calculator, calculator.getClass());

        Assertions.assertThrows(IllegalArgumentException.class, () -> calculator1.fibonacciNumbers(-8));
        Assertions.assertEquals(calculator1.fibonacciNumbers(4), 3);
    }
}
