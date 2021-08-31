package sbertech.hw4;

import java.math.BigDecimal;

public interface Terminal {
    public BigDecimal getBalance(String pin);

    public void deposit(String pin, BigDecimal sum);

    public void withdraw(String pin, BigDecimal sum);
}
