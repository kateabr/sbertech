package sbertech.hw4.Exceptions;

public class InvalidSumException extends TerminalRelatedException {
    public InvalidSumException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return String.format("Invalid sum: %s", getRawMessage());
    }
}
