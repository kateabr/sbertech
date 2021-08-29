package sbertech.hw4;

import sbertech.hw4.Exceptions.AuthorizationDeniedException;

import java.math.BigDecimal;
import java.util.Scanner;

public class TerminalImpl implements TerminalInterface {
    private PinCodeValidator validator;
    private final TerminalServer server;

    public TerminalImpl(String pin, BigDecimal sum) {
        validator = new PinCodeValidator(pin);
        server = new TerminalServer(sum);
    }

    @Override
    public BigDecimal getBalance(String pin) throws AuthorizationDeniedException {
        validator.authorize(pin);
        return server.getCurrentSum();
    }

    @Override
    public void deposit(String pin, BigDecimal sum) {
        validator.authorize(pin);
        server.deposit(sum);
    }

    @Override
    public void withdraw(String pin, BigDecimal sum) {
        validator.authorize(pin);
        server.withdraw(sum);
    }

    public static void main(String[] args) {
        Scanner pinScanner = new Scanner(System.in);
        TerminalImpl terminal = new TerminalImpl(pinScanner.nextLine(), new BigDecimal(10000));
        while (true)
//        try {
            terminal.deposit(pinScanner.nextLine(), BigDecimal.valueOf(10));
//        } catch (TerminalRelatedException exception) {
//            System.out.println(exception.getMessage());
//        }

    }
}
