package sbertech.hw4;


import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TerminalBlock {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("H:mm");
    private boolean blocked;
    private final Duration blockTimeMinutes;
    private Instant blockedUntil;
    private static ScheduledExecutorService blockTimer;
    private final int attempts;
    private int attemptsLeft;

    public TerminalBlock(int attempts, int blockTimeMinutes) {
        if (attempts < 3) throw new IllegalArgumentException("Number of attempts can't be less than 3");
        if (blockTimeMinutes < 1) throw new IllegalArgumentException("Can't set block time to less than 1 minute");
        blocked = false;
        this.blockTimeMinutes = Duration.ofMinutes(blockTimeMinutes);
        this.attempts = attempts;
        attemptsLeft = attempts;
        blockTimer = Executors.newSingleThreadScheduledExecutor(r -> {
            Thread t = Executors.defaultThreadFactory().newThread(r);
            t.setDaemon(true);
            return t;
        });
    }

    public boolean isBlocked() {
        return blocked;
    }

    public int getAttemptsLeft() {
        return attemptsLeft;
    }

    public String blockedUntil() {
        if (!blocked) throw new UnsupportedOperationException("Terminal is not blocked");
        LocalTime blocked = blockedUntil.atZone(ZoneId.systemDefault()).toLocalTime();
        return DATE_TIME_FORMATTER.format(blocked);
    }

    private void block() {
        blocked = true;
        attemptsLeft = 0;
        blockedUntil = Instant.now().plus(blockTimeMinutes);
        blockTimer.schedule(this::unblock, blockTimeMinutes.toMinutes(), TimeUnit.MINUTES);
    }

    private void unblock() {
        blocked = false;
        attemptsLeft = attempts;
    }

    public void decreaseAttempts() {
        if (attemptsLeft > 1) attemptsLeft -= 1;
        else block();
    }
}
