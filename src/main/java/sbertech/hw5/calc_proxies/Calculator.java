package sbertech.hw5.calc_proxies;

public interface Calculator{
    /**
     * Расчет факториала числа.
     * @param number входное число
     * @return long
     */
    long calc (@NonNegative long number);

    /**
     * Расчет чисел Фибоначчи.
     * @param number порядковый номер числа (с 0)
     * @return long
     */
    @Metric
    @Cache
    long fibonacciNumbers (@NonNegative long number);
}
