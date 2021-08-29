package sbertech.hw4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import sbertech.hw4.Exceptions.*;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

public class TerminalTest {
    @Test
    public void testPinCodeInput() {
        TerminalImpl terminal = new TerminalImpl("1234", BigDecimal.valueOf(10000));
        Assertions.assertThrows(AuthorizationDeniedException.class, () -> terminal.getBalance("1111"));
        Assertions.assertThrows(InvalidPinCodeException.class, () -> terminal.getBalance("1234q"));
        Assertions.assertThrows(InvalidPinCodeException.class, () -> terminal.getBalance("12345"));
        Assertions.assertEquals(terminal.getBalance("1234"), BigDecimal.valueOf(10000));
    }

    @Test
    public void testSumDepositionWithdrawal() {
        TerminalImpl terminal = new TerminalImpl("1234", BigDecimal.valueOf(10000));
        Assertions.assertThrows(SumNotDivisibleBy100Exception.class, () -> terminal.deposit("1234", BigDecimal.valueOf(10)));
        Assertions.assertThrows(NegativeSumException.class, () -> terminal.withdraw("1234", BigDecimal.valueOf(-1000)));
        Assertions.assertThrows(InsufficientFundsException.class, () -> terminal.withdraw("1234", BigDecimal.valueOf(11000)));
        terminal.deposit("1234", BigDecimal.valueOf(1000));
        Assertions.assertEquals(terminal.getBalance("1234"), BigDecimal.valueOf(11000));
        terminal.withdraw("1234", BigDecimal.valueOf(5000));
        Assertions.assertEquals(terminal.getBalance("1234"), BigDecimal.valueOf(6000));
    }

    @Test
    public void testTerminalBlock() throws InterruptedException {
        TerminalImpl terminal = new TerminalImpl("1234", BigDecimal.valueOf(10000));
        Assertions.assertThrows(AuthorizationDeniedException.class, () -> terminal.getBalance("1111"));
        Assertions.assertThrows(AuthorizationDeniedException.class, () -> terminal.getBalance("1111"));
        Assertions.assertThrows(AuthorizationDeniedException.class, () -> terminal.getBalance("1111"));
        Assertions.assertThrows(TerminalBlockedException.class, () -> terminal.getBalance("1111"));
        TimeUnit.MINUTES.sleep(1);
        Assertions.assertEquals(terminal.getBalance("1234"), BigDecimal.valueOf(10000));
    }
}
