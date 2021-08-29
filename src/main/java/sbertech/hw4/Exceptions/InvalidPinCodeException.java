package sbertech.hw4.Exceptions;

public class InvalidPinCodeException extends TerminalRelatedException {
    public InvalidPinCodeException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return String.format("Invalid pin code: %s", getRawMessage());
    }
}
