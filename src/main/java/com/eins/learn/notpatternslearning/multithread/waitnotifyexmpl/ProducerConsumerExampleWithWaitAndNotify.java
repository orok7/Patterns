package com.eins.learn.notpatternslearning.multithread.waitnotifyexmpl;

import java.util.ArrayList;
import java.util.List;

public class ProducerConsumerExampleWithWaitAndNotify {

    public static void main(String[] args) {
        List<Integer> taskQueue = new ArrayList<>();
        int MAX_CAPACITY = 5;
        Thread tProducer = new Thread(new Producer(taskQueue, MAX_CAPACITY), "Producer");
        Thread tConsumer1 = new Thread(new Consumer(taskQueue, i -> i%2 == 0), "Consumer1");
        Thread tConsumer2 = new Thread(new Consumer(taskQueue, i -> i%2 != 0), "Consumer2");
        tProducer.start();
        tConsumer1.start();
        tConsumer2.start();
    }

}
