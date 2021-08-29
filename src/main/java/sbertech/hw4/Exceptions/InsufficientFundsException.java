package sbertech.hw4.Exceptions;

public class InsufficientFundsException extends InvalidSumException {
    public InsufficientFundsException(String message) {
        super(String.format("insufficient funds to proceed (%s)", message));
    }
}
