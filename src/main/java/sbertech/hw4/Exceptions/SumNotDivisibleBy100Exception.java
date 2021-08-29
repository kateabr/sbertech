package sbertech.hw4.Exceptions;

public class SumNotDivisibleBy100Exception extends InvalidSumException {
    public SumNotDivisibleBy100Exception(String message) {
        super(String.format("sum not divisible by 100 (%s)", message));
    }
}
