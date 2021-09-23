package sbertech.hw11;

import java.math.BigDecimal;
import java.util.Optional;

public class RunnableFibonacciNumbers implements Runnable {
    private String threadAlias;
    private int number;

    private BigDecimal fib(int x) {
        if (x < 2)
            return BigDecimal.ONE;
        return BigDecimal.valueOf(x).add(fib(x - 1)).add(fib((x - 2)));
    }

    public void setThreadAlias(String threadAlias) {
        this.threadAlias = threadAlias;
    }

    public RunnableFibonacciNumbers(int number) {
        this.number = number;
    }

    @Override
    public void run() {
        if(threadAlias == null) {
            System.out.printf("%d: %s (%s)\n", number, fib(number).toString(), Thread.currentThread().getName());
        } else {
            System.out.printf("%d: %s (%s::%s)\n", number, fib(number).toString(), Thread.currentThread().getName(), threadAlias);
        }
    }
}
