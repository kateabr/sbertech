package sbertech.hw4.Exceptions;

public class PinCodeValidatorException extends TerminalRelatedException {
    public PinCodeValidatorException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return String.format("Invalid pin code: %s", getRawMessage());
    }
}
