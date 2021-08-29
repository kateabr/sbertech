package sbertech.hw4.Exceptions;

public class NonNumericSymbolException extends InvalidPinCodeException {
    public NonNumericSymbolException(String message) {
        super(message);
    }
}
