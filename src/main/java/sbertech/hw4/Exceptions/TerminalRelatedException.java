package sbertech.hw4.Exceptions;

public class TerminalRelatedException extends RuntimeException {
    public TerminalRelatedException(String message) {
        super(message);
    }

    public String getMessage() {
        return getRawMessage();
    }

    public String getRawMessage() {
        return super.getMessage();
    }
}
