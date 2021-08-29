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
        currentSum = new BigDecimal(String.valueOf(sum));
    }

    public BigDecimal getCurrentSum() {
        return currentSum;
    }

    private void verifySum(BigDecimal sum) {
        if (sum.doubleValue() % 100 != 0) throw new SumNotDivisibleBy100Exception(sum.toString());
        if (sum.doubleValue() < 0) throw new NegativeSumException(sum.toString());
    }

    public void deposit(BigDecimal sum) {
        verifySum(sum);
        currentSum = currentSum.add(sum);
    }

    public void withdraw(BigDecimal sum) {
        verifySum(sum);
        if (sum.compareTo(currentSum) > 0) throw new InsufficientFundsException(sum.toString());
        currentSum = currentSum.subtract(sum);
    }
}
