package sbertech.hw11;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class ScalableThreadPool implements ThreadPool{
    private final Thread[] threads;
    private final BlockingQueue<Runnable> tasks;
    private boolean poolIsStarted;
    private AtomicInteger additionalThreads;

    public ScalableThreadPool(int minThreads, int maxThreads) {
        this(minThreads, maxThreads, 1000);
    }

    public ScalableThreadPool(int minThreads, int maxThreads, int maxTasks) {
        this.threads = new Thread[minThreads];
        this.additionalThreads = new AtomicInteger(maxThreads - minThreads);
        tasks = new ArrayBlockingQueue<>(maxTasks);
    }

    @Override
    public void start() {
        if (poolIsStarted) {
            return;
        }

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                while (true) {
                    try {
                        Runnable task = tasks.take();
                        ((RunnableFibonacciNumbers) task).setThreadAlias("main");
                        task.run();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            threads[i].start();
        }

        poolIsStarted = true;
    }

    @Override
    public void execute(Runnable runnable) {
        if(tasks.isEmpty() || additionalThreads.get() == 0) {
            tasks.add(runnable);
        } else {
            if (additionalThreads.getAndDecrement() > 0) {
                Thread thread = new Thread(() -> {
                    ((RunnableFibonacciNumbers) runnable).setThreadAlias("sub");
                    runnable.run();
                });
                thread.start();
            }
            additionalThreads.incrementAndGet();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ScalableThreadPool scalableThreadPool = new ScalableThreadPool(3, 6);
        scalableThreadPool.start();
        scalableThreadPool.execute(new RunnableFibonacciNumbers(45));
        scalableThreadPool.execute(new RunnableFibonacciNumbers(40));
        scalableThreadPool.execute(new RunnableFibonacciNumbers(25));
        scalableThreadPool.execute(new RunnableFibonacciNumbers(10));
        scalableThreadPool.execute(new RunnableFibonacciNumbers(5));
        scalableThreadPool.execute(new RunnableFibonacciNumbers(10));
        scalableThreadPool.execute(new RunnableFibonacciNumbers(30));
    }
}
