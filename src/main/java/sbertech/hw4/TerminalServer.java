package sbertech.hw4;

import sbertech.hw4.Exceptions.NegativeSumException;
import sbertech.hw4.Exceptions.InsufficientFundsException;
import sbertech.hw4.Exceptions.SumNotDivisibleBy100Exception;

import java.math.BigDecimal;
import java.util.Objects;

public class TerminalServer {
    private BigDecimal currentSum;

    public TerminalServer(BigDecimal sum) {
        Objects.requireNonNull(sum);
        currentSum = new BigDecimal(String.valueOf(sum.longValueExact()));
    }

    public BigDecimal getCurrentSum() {
        return currentSum;
    }

    private void verifySum(BigDecimal sum) {
        if (sum.doubleValue() % 100 != 0) throw new SumNotDivisibleBy100Exception(String.valueOf(sum.longValueExact()));
        if (sum.doubleValue() < 0) throw new NegativeSumException(String.valueOf(sum.longValueExact()));
    }

    public void deposit(BigDecimal sum) {
        verifySum(sum);
        currentSum = currentSum.add(sum);
    }

    public void withdraw(BigDecimal sum) {
        verifySum(sum);
        if (sum.compareTo(currentSum) > 0) throw new InsufficientFundsException(String.valueOf(sum.longValueExact()));
        currentSum = currentSum.subtract(sum);
    }
}
