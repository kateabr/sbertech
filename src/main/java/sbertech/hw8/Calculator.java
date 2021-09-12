package sbertech.hw8;

public interface Calculator {
    /**
     * Расчет чисел Фибоначчи.
     * @param number порядковый номер числа (с 0)
     * @return long
     */
    @Cache(saveOnDisk = false)
    long fibonacciNumbers1(long number);

    /**
     * Расчет чисел Фибоначчи.
     * @param number порядковый номер числа (с 0)
     * @return long
     */
    @Cache(saveOnDisk = true)
    long fibonacciNumbers2(long number);
}
