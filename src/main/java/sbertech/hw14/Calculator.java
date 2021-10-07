package sbertech.hw14;

public interface Calculator {
    /**
     * Расчет чисел Фибоначчи.
     * @param number порядковый номер числа (с 0)
     * @return long
     */
    @Cache(saveOnDisk = false)
    long fibonacciNumbers(long number);
}
