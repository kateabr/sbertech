package sbertech.hw10;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MultithreadedFactorial {
    private static BigDecimal factorial(int x) {
        if (x < 2)
            return BigDecimal.ONE;
        return BigDecimal.valueOf(x).multiply(factorial(x - 1));
    }

    public static void main(String[] args) throws IOException {
        Files.lines(Paths.get("factorials.txt"))
                .parallel()
                .forEach(x -> System.out.printf("%s: %s (%s)\n", x, factorial(Integer.parseInt(x)).toString(),
                        Thread.currentThread().getName()));
    }
}
