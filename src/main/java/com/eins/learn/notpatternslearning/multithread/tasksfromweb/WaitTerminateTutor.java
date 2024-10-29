package com.eins.learn.notpatternslearning.multithread.tasksfromweb;

import com.eins.learn.notpatternslearning.multithread.try1.MyThread;

public class WaitTerminateTutor {
    private final Object monitor = new Object();
    private int runningThreadNumber = 1;

    public void testThread() {
        TestThread testThread1 = new TestThread("t1");
        Thread t1 = new Thread(testThread1);
        final TestThread testThread2 = new TestThread("t2");
        Thread t2 = new Thread(testThread2);
        t1.setDaemon(true);
        System.out.println("Starting threads...");
        t1.start();
        t2.start();

        new Thread(() -> {
            MyThread.sleepAndWait(1000);
            testThread2.shouldTerminate = true;
        }).start();

        System.out.println("Waiting threads to join...");
        try {
//            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    class TestThread implements Runnable {
        boolean shouldTerminate;
        String threadName;

        TestThread(String threadName) {
            this.threadName = threadName;
        }

        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                System.out.println(threadName + ":" + i);
                synchronized (monitor) {
                    try {
                        while (!threadName.equals("t" + runningThreadNumber)) {
                            System.out.println("wait for thread " + "t" + runningThreadNumber);
                            monitor.wait();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    runningThreadNumber++;
                    if (runningThreadNumber > 2)
                        runningThreadNumber = 1;
                    monitor.notifyAll();
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (shouldTerminate)
                        return;
                }
            }
        }
    }

}