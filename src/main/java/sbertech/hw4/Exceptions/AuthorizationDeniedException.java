package sbertech.hw4.Exceptions;

public class AuthorizationDeniedException extends TerminalRelatedException {
    public AuthorizationDeniedException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return String.format("Authorization denied: %s", getRawMessage());
    }
}
