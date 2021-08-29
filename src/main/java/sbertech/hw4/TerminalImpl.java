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
        System.out.print("Set the pin code: ");
        Scanner inputScanner = new Scanner(System.in);
        TerminalImpl terminal = new TerminalImpl(inputScanner.nextLine(), new BigDecimal(1000));
        while (true) {
            System.out.print("Select an action (1 = display balance; 2 = withdraw funds; 3 = deposit funds): ");
            switch (Integer.parseInt(inputScanner.nextLine())) {
                case 1 -> {
                    System.out.print("Enter pin code to display account balance: ");
                    System.out.printf("Current balance: %f%n", terminal.getBalance(inputScanner.nextLine()));
                }
                case 2 -> {
                    System.out.print("Sum to withdraw: ");
                    BigDecimal sum = BigDecimal.valueOf(Double.parseDouble(inputScanner.nextLine()));
                    if (sum.compareTo(BigDecimal.ZERO) != 0) {
                        System.out.print("Enter pin code: ");
                        terminal.withdraw(inputScanner.nextLine(), sum);
                        System.out.println("Transaction successfully completed!");
                    }
                }
                case 3 -> {
                    System.out.print("Sum to deposit: ");
                    BigDecimal sum = BigDecimal.valueOf(Double.parseDouble(inputScanner.nextLine()));
                    if (sum.compareTo(BigDecimal.ZERO) != 0) {
                        System.out.print("Enter pin code: ");
                        terminal.deposit(inputScanner.nextLine(), sum);
                        System.out.println("Transaction successfully completed!");
                    }
                }
                default -> System.out.println("Invalid action code");
            }
        }

    }
}
