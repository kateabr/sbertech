package sbertech.hw4;

import sbertech.hw4.Exceptions.*;
import sbertech.hw4.terminal.Exceptions.*;

import java.util.Arrays;
import java.util.Objects;

public class PinCodeValidator {
    private final int[] pin = new int[4];
    private final TerminalBlock block;

    private void validateChar(char ch) throws NonNumericSymbolException {
        if (!Character.isDigit(ch)) {
            throw new NonNumericSymbolException(String.format("character \"%s\" is not a digit", ch));
        }
    }

    private int[] validate(String pin) throws TerminalRelatedException {
        int[] p = new int[4];
        int pPos = 0;
        Objects.requireNonNull(pin);
        for (int i=0; i<pin.length(); i++) {
            if (pPos > 3) throw new InvalidPinCodeException("pin code must be 4 characters long");
            validateChar(pin.charAt(i));
            p[pPos] = Integer.parseInt(pin.substring(i, i + 1));
            pPos++;
        }
        return p;
    }

    public PinCodeValidator(String pin) throws PinCodeValidatorException {
        System.arraycopy(validate(pin), 0, this.pin, 0, 4);
        block = new TerminalBlock(3, 1);
    }

    public void authorize(String pin) {
        if (block.isBlocked()) {
            throw new TerminalBlockedException(
                    String.format("Can't authorize: terminal is blocked. Try again around %s", block.blockedUntil()));
        }
        try {
            if(!Arrays.equals(validate(pin), this.pin)) {
                block.decreaseAttempts();
                throw new AuthorizationDeniedException(String.format("incorrect pin code (%d attempts remaining)", block.getAttemptsLeft()));
            }
        }
        catch (PinCodeValidatorException exception) {
            block.decreaseAttempts();
            throw new PinCodeValidatorException(String.format("%s (%d attempts remaining)", exception.getRawMessage(), block.getAttemptsLeft()));
        }
    }
}
