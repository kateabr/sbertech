package sbertech.hw12;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;

public class ContextImpl implements Context{
    private final CompletableFuture<?>[] futures;

    public ContextImpl(CompletableFuture<?>[] results) {
        futures = results;
    }

    @Override
    public int getCompletedTaskCount() {
        return (int) Arrays.stream(futures).filter(f -> f.isDone() && !f.isCompletedExceptionally() && !f.isCancelled()).count();
    }

    @Override
    public int getFailedTaskCount() {
        return (int) Arrays.stream(futures).filter(f -> f.isCompletedExceptionally() && !f.isCancelled()).count();
    }

    @Override
    public int getInterruptedTaskCount() {
        return (int) Arrays.stream(futures).filter(CompletableFuture::isCancelled).count();
    }

    @Override
    public void interrupt() {
        Arrays.stream(futures).filter(f -> !f.isDone())
                .forEach(f -> f.cancel(false));
    }

    @Override
    public boolean isFinished() {
        return Arrays.stream(futures).allMatch(CompletableFuture::isDone);
    }
}
