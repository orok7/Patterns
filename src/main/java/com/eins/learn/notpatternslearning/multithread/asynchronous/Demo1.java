package com.eins.learn.notpatternslearning.multithread.asynchronous;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.function.LongBinaryOperator;
import java.util.stream.IntStream;

public class Demo1 {
    private static int count = 0;

    public static void main(String[] args) {
        LongBinaryOperator op = (x, y) -> 2 * x + y;
        LongAccumulator accumulator = new LongAccumulator(op, 1L);

        ExecutorService executor = Executors.newFixedThreadPool(2);

        IntStream.range(0, 10)
                .forEach(i -> executor.submit(() -> accumulator.accumulate(i)));

        stop(executor);

        System.out.println(accumulator.getThenReset());

        Map<String, String> map = new HashMap<>();
        map.put("key1", "2");
        map.put("key2", "3");
        map.replaceAll((key, value) -> key.equals(key) ? "d3" : value);
        map.forEach((s, s2) -> System.out.println("key: " + s + " value: " + s2));
        System.out.println(ForkJoinPool.getCommonPoolParallelism());
    }

    synchronized static void incrementSync() {
        count = count + 1;
    }

    public static void stop(ExecutorService executor) {
        try {
            executor.shutdown();
            executor.awaitTermination(60, TimeUnit.SECONDS);
        }
        catch (InterruptedException e) {
            System.err.println("termination interrupted");
        }
        finally {
            if (!executor.isTerminated()) {
                System.err.println("killing non-finished tasks");
            }
            executor.shutdownNow();
        }
    }

    public static void sleep(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }
}
