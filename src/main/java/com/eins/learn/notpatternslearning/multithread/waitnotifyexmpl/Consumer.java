package com.eins.learn.notpatternslearning.multithread.waitnotifyexmpl;

import java.util.List;
import java.util.function.Predicate;

class Consumer implements Runnable {
    private final List<Integer> taskQueue;
    private Predicate<Integer> predicate;

    public Consumer(List<Integer> sharedQueue, Predicate<Integer> predicate) {
        this.taskQueue = sharedQueue;
        this.predicate = predicate;
    }

    @Override
    public void run() {
        while (true) {
            try {
                consume();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void consume() throws InterruptedException {
        synchronized (taskQueue) {
            while (taskQueue.isEmpty() || !predicate.test(taskQueue.get(0))) {
//                if (taskQueue.isEmpty()) {
//                    System.out.println("Queue is empty, " + Thread.currentThread().getName()
//                            + " is waiting , size: " + taskQueue.size());
//                } else {
//                    System.out.println("Input doesn't match the condition, " + Thread.currentThread().getName()
//                            + " is waiting , i: " + taskQueue.get(0));
//                }
                taskQueue.wait();
            }
            Thread.sleep(1000);
            int i = taskQueue.remove(0);
            System.out.print(i + "\t<--\t" + Thread.currentThread().getName() + ":\t\t[ ");
            taskQueue.forEach(e -> System.out.print(e + " "));
            System.out.println("]");
            taskQueue.notify();
        }
    }
}