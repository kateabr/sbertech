package sbertech.hw12;

import java.util.concurrent.Callable;
import java.util.stream.Stream;

public class Task<T> {
    private Callable<? extends T> callable;
    private volatile T result;
    private volatile TaskCallRuntimeException exception;
    public volatile String calculatorThread;

    public Task(Callable<? extends T> callable) {
        this.callable = callable;
    }

    public T get() {
        if(exception != null) {
            throw exception;
        } else if(result != null) {
            return result;
        } else {
            synchronized (this) {
                if(exception != null) {
                    throw exception;
                } else if(result != null) {
                    return result;
                } else {
                    try {
                        this.result = callable.call();
                        calculatorThread = Thread.currentThread().getName();
                        return result;
                    } catch (Exception e) {
                        exception = new TaskCallRuntimeException(e.getMessage());
                        throw exception;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Task<Integer> task = new Task<>(() -> 10);
        Stream.iterate(0, i -> i + 1)
                .parallel()
                .map(i -> (Runnable) () -> System.out.printf("Current thread: %s | Result (%d) calculated in thread %s\n", Thread.currentThread().getName(), task.get(), task.calculatorThread))
                .limit(10)
                .forEach(Runnable::run);
    }
}

