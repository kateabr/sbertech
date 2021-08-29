package sbertech.hw4.Exceptions;

public class NegativeSumException extends InvalidSumException {
    public NegativeSumException(String message) {
        super(String.format("sum is negative (%s)", message));
    }
}
