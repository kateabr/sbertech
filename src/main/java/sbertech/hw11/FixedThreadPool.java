package sbertech.hw11;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class FixedThreadPool implements ThreadPool {
    private final Thread[] threads;
    private final BlockingQueue<Runnable> tasks;
    private boolean poolIsStarted;

    public FixedThreadPool(int threads) {
        this(threads, 1000);
    }

    public FixedThreadPool(int threads, int maxTasks) {
        this.threads = new Thread[threads];
        tasks = new ArrayBlockingQueue<>(maxTasks);
    }

    @Override
    public synchronized void start() {
        if (poolIsStarted) {
            return;
        }

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                while (true) {
                    try {
                        Runnable task = tasks.take();
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
        tasks.add(runnable);
    }

    public static void main(String[] args) {
        FixedThreadPool fixedThreadPool = new FixedThreadPool(3);
        fixedThreadPool.start();
        fixedThreadPool.execute(new RunnableFibonacciNumbers(45));
        fixedThreadPool.execute(new RunnableFibonacciNumbers(40));
        fixedThreadPool.execute(new RunnableFibonacciNumbers(25));
        fixedThreadPool.execute(new RunnableFibonacciNumbers(10));
        fixedThreadPool.execute(new RunnableFibonacciNumbers(5));
        fixedThreadPool.execute(new RunnableFibonacciNumbers(10));
        fixedThreadPool.execute(new RunnableFibonacciNumbers(30));
    }
}
