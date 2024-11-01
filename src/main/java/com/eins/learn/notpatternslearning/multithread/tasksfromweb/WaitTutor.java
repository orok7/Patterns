package com.eins.learn.notpatternslearning.multithread.tasksfromweb;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class WaitTutor {
    private final Object monitor = new Object();
    private int t1Counter = 0, t2Counter = 0;
    private CyclicBarrier barrier = new CyclicBarrier(2, () -> {
        if (t1Counter == 0 || t2Counter == 0) {
            return;
        }
        if (t1Counter % 10 == 0 && t2Counter % 10 == 0) {
            System.out.println("--> " + t1Counter);
        }
    });

    public void testThread() {
        Thread t1 = new Thread(new TestThread("t1", 1));
        Thread t2 = new Thread(new TestThread("t2", 2));

        System.out.println("Starting threads");
        t1.start();
        t2.start();

        System.out.println("Waiting for threads");
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    class TestThread implements Runnable {
        String threadName;
        int n;

        TestThread(String threadName, int n) {
            this.threadName = threadName;
            this.n = n;
        }

        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                try {
                    barrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
                System.out.println(threadName + ":" + i);
                synchronized (monitor) {
                    if (n == 1)
                        t1Counter = i;
                    if (n == 2)
                        t2Counter = i;
                    monitor.notify();
                    Thread.yield();
                    try {
                        if (n == 1) {
                            if (i > t2Counter) {
                                System.out.println("t1 is ahead with i=" + i + ", wait for t2Counter=" + t2Counter);
                                monitor.wait();
                            }
                        }
                        if (n == 2) {
                            if (i > t1Counter) {
                                System.out.println("t2 is ahead with i=" + i + ", wait for t1Counter=" + t1Counter);
                                monitor.wait();
                            }
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Thread.yield();
            }
        }
    }
}