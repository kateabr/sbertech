package sbertech.hw12;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class ExecutionManagerImpl implements ExecutionManager {
    private final ExecutorService pool;

    public ExecutionManagerImpl(int threads) {
        pool = Executors.newFixedThreadPool(threads, new ThreadFactory() {
            private static final AtomicInteger threadNumber = new AtomicInteger(0);

            @Override
            public Thread newThread(Runnable r) {
                Thread t = new Thread(Thread.currentThread().getThreadGroup(), r,
                        "ExecutionManagerThread-" + threadNumber.getAndIncrement(),
                        0);
                t.setDaemon(true);
                t.setPriority(Thread.NORM_PRIORITY);
                return t;
            }
        });
    }

    @Override
    public Context execute(Runnable callback, Runnable... tasks) {
        CompletableFuture<?>[] results = Arrays.stream(tasks)
                .map(t -> CompletableFuture.runAsync(t, pool))
                .toArray(CompletableFuture[]::new);
        CompletableFuture.allOf(results)
                .whenComplete((unused, throwable) -> callback.run());
        return new ContextImpl(results);
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutionManager executionManager = new ExecutionManagerImpl(8);
        Context context = executionManager.execute(() -> System.out.println("Done"), Stream.iterate(0, i -> i + 1)
                .map(i ->  (Runnable) () -> {
                            if (i % 10 != 0) {
                                System.out.printf("%s; running task #%d\n", Thread.currentThread().getName(), i);
                            } else {
                                throw new RuntimeException("Exception");
                            }
                        }
                )
                .limit(100)
                .toArray(Runnable[]::new));
        Thread.sleep(10);
        context.interrupt();
        System.out.printf("\nCompleted tasks: %d\n", context.getCompletedTaskCount());
        System.out.printf("Interrupted tasks: %d\n", context.getInterruptedTaskCount());
        System.out.printf("Failed tasks: %d\n", context.getFailedTaskCount());
    }
}
